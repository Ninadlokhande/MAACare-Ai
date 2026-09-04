package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.BabyModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BabyDAO {

    // =====================================================
    // FIRESTORE
    // =====================================================

    private final Firestore db;

    private static final String COLLECTION = "babies";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BabyDAO() {

        db = FirebaseConfig.getFirestore();

        System.out.println("BabyDAO connected to Firebase");
    }

    // =====================================================
    // GET ALL BABIES
    // =====================================================

    public List<BabyModel> getAllBabies() {

        List<BabyModel> babies = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .get();

            List<QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            for (DocumentSnapshot document : documents) {

                BabyModel baby =
                        documentToBaby(document);

                if (baby != null) {
                    babies.add(baby);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting babies from Firebase"
            );

            e.printStackTrace();
        }

        return babies;
    }

    // =====================================================
    // GET BABY BY ID
    // =====================================================

    public BabyModel getBabyById(String babyId) {

        if (babyId == null ||
            babyId.trim().isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                      .document(babyId)
                      .get()
                      .get();

            if (!document.exists()) {
                return null;
            }

            return documentToBaby(document);

        } catch (Exception e) {

            System.out.println(
                    "Error getting baby by ID"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET BABY BY MOTHER ID
    // =====================================================

    public BabyModel getBabyByMotherId(
            String motherId) {

        if (motherId == null ||
            motherId.trim().isEmpty()) {

            return null;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "motherId",
                              motherId
                      )
                      .limit(1)
                      .get();

            List<QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            if (documents.isEmpty()) {
                return null;
            }

            return documentToBaby(
                    documents.get(0)
            );

        } catch (Exception e) {

            System.out.println(
                    "Error getting baby by mother ID"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // SAVE BABY
    // =====================================================

    public boolean saveBaby(
            BabyModel baby) {

        if (baby == null ||
            baby.getBabyId() == null ||
            baby.getBabyId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    babyToMap(baby);

            db.collection(COLLECTION)
              .document(baby.getBabyId())
              .set(data)
              .get();

            System.out.println(
                    "Baby saved successfully: "
                    + baby.getBabyId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving baby"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE BABY
    // =====================================================

    public boolean updateBaby(
            BabyModel updatedBaby) {

        if (updatedBaby == null ||
            updatedBaby.getBabyId() == null ||
            updatedBaby.getBabyId().trim().isEmpty()) {

            return false;
        }

        try {

            DocumentReference reference =
                    db.collection(COLLECTION)
                      .document(
                              updatedBaby.getBabyId()
                      );

            DocumentSnapshot document =
                    reference.get().get();

            if (!document.exists()) {
                return false;
            }

            Map<String, Object> data =
                    babyToMap(updatedBaby);

            reference.set(data).get();

            System.out.println(
                    "Baby updated successfully: "
                    + updatedBaby.getBabyId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating baby"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE BABY
    // =====================================================

    public boolean deleteBaby(
            String babyId) {

        if (babyId == null ||
            babyId.trim().isEmpty()) {

            return false;
        }

        try {

            DocumentReference reference =
                    db.collection(COLLECTION)
                      .document(babyId);

            DocumentSnapshot document =
                    reference.get().get();

            if (!document.exists()) {
                return false;
            }

            reference.delete().get();

            System.out.println(
                    "Baby deleted successfully: "
                    + babyId
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting baby"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // BABY MODEL → FIRESTORE MAP
    // =====================================================

    private Map<String, Object> babyToMap(
            BabyModel baby) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "babyId",
                baby.getBabyId()
        );

        data.put(
                "motherId",
                baby.getMotherId()
        );

        data.put(
                "babyName",
                baby.getBabyName()
        );

        data.put(
                "dateOfBirth",
                baby.getDateOfBirth() != null
                        ? baby.getDateOfBirth().toString()
                        : null
        );

        data.put(
                "gender",
                baby.getGender()
        );

        data.put(
                "bloodGroup",
                baby.getBloodGroup()
        );

        data.put(
                "birthWeight",
                baby.getBirthWeight()
        );

        data.put(
                "birthHeight",
                baby.getBirthHeight()
        );

        data.put(
                "currentWeight",
                baby.getCurrentWeight()
        );

        data.put(
                "currentHeight",
                baby.getCurrentHeight()
        );

        data.put(
                "headCircumference",
                baby.getHeadCircumference()
        );

        // =================================================
        // GROWTH HISTORY
        // =================================================

        List<Map<String, Object>> growthHistory =
                new ArrayList<>();

        if (baby.getGrowthHistory() != null) {

            for (
                BabyModel.GrowthRecord record
                : baby.getGrowthHistory()
            ) {

                Map<String, Object> growth =
                        new HashMap<>();

                growth.put(
                        "date",
                        record.getDate() != null
                                ? record.getDate().toString()
                                : null
                );

                growth.put(
                        "weight",
                        record.getWeight()
                );

                growth.put(
                        "height",
                        record.getHeight()
                );

                growth.put(
                        "headCircumference",
                        record.getHeadCircumference()
                );

                growthHistory.add(growth);
            }
        }

        data.put(
                "growthHistory",
                growthHistory
        );

        return data;
    }

    // =====================================================
    // FIRESTORE DOCUMENT → BABY MODEL
    // =====================================================

    private BabyModel documentToBaby(
            DocumentSnapshot document) {

        try {

            BabyModel baby =
                    new BabyModel();

            baby.setBabyId(
                    document.getString("babyId")
            );

            baby.setMotherId(
                    document.getString("motherId")
            );

            baby.setBabyName(
                    document.getString("babyName")
            );

            String dob =
                    document.getString("dateOfBirth");

            if (dob != null &&
                !dob.isEmpty()) {

                baby.setDateOfBirth(
                        LocalDate.parse(dob)
                );
            }

            baby.setGender(
                    document.getString("gender")
            );

            baby.setBloodGroup(
                    document.getString("bloodGroup")
            );

            Double birthWeight =
                    document.getDouble("birthWeight");

            if (birthWeight != null) {
                baby.setBirthWeight(
                        birthWeight
                );
            }

            Double birthHeight =
                    document.getDouble("birthHeight");

            if (birthHeight != null) {
                baby.setBirthHeight(
                        birthHeight
                );
            }

            Double currentWeight =
                    document.getDouble("currentWeight");

            if (currentWeight != null) {
                baby.setCurrentWeight(
                        currentWeight
                );
            }

            Double currentHeight =
                    document.getDouble("currentHeight");

            if (currentHeight != null) {
                baby.setCurrentHeight(
                        currentHeight
                );
            }

            Double headCircumference =
                    document.getDouble(
                            "headCircumference"
                    );

            if (headCircumference != null) {
                baby.setHeadCircumference(
                        headCircumference
                );
            }

            // =============================================
            // GROWTH HISTORY
            // =============================================

            List<Map<String, Object>> growthHistory =
                    (List<Map<String, Object>>)
                            document.get("growthHistory");

            if (growthHistory != null) {

                for (
                    Map<String, Object> growth
                    : growthHistory
                ) {

                    String dateString =
                            (String) growth.get("date");

                    LocalDate date =
                            dateString != null
                                    ? LocalDate.parse(dateString)
                                    : null;

                    double weight =
                            getDouble(
                                    growth.get("weight")
                            );

                    double height =
                            getDouble(
                                    growth.get("height")
                            );

                    double head =
                            getDouble(
                                    growth.get(
                                            "headCircumference"
                                    )
                            );

                    if (date != null) {

                        baby.addGrowthRecord(
                                date,
                                weight,
                                height,
                                head
                        );
                    }
                }
            }

            return baby;

        } catch (Exception e) {

            System.out.println(
                    "Error converting Firebase document to BabyModel"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // SAFE DOUBLE CONVERSION
    // =====================================================

    private double getDouble(
            Object value) {

        if (value instanceof Number) {

            return ((Number) value).doubleValue();
        }

        return 0.0;
    }
}