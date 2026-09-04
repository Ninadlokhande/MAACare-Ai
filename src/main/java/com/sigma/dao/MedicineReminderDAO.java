package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.MedicineReminderModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineReminderDAO {

    private final Firestore db;

    private static final String COLLECTION =
            "medicineReminders";


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MedicineReminderDAO() {

        db = FirebaseConfig.getFirestore();

        System.out.println(
                "MedicineReminderDAO connected to Firebase"
        );
    }


    // =========================================================
    // GET ALL MEDICINES
    // =========================================================

    public List<MedicineReminderModel> getAllMedicines() {

        List<MedicineReminderModel> medicines =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicineReminderModel medicine =
                        documentToMedicine(document);

                if (medicine != null) {
                    medicines.add(medicine);
                }
            }

            System.out.println(
                    "Medicines fetched: " +
                    medicines.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error fetching medicines"
            );

            e.printStackTrace();
        }

        return medicines;
    }


    // =========================================================
    // GET MEDICINES BY MOTHER ID
    // =========================================================

    public List<MedicineReminderModel> getMedicinesByMotherId(
            String motherId) {

        List<MedicineReminderModel> medicines =
                new ArrayList<>();

        if (motherId == null ||
            motherId.trim().isEmpty()) {

            return medicines;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .whereEqualTo(
                                    "motherId",
                                    motherId
                            )
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicineReminderModel medicine =
                        documentToMedicine(document);

                if (medicine != null) {
                    medicines.add(medicine);
                }
            }

            System.out.println(
                    "Medicines fetched for mother: " +
                    motherId +
                    " = " +
                    medicines.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error fetching medicines for mother"
            );

            e.printStackTrace();
        }

        return medicines;
    }


    // =========================================================
    // GET MEDICINE BY ID
    // =========================================================

    public MedicineReminderModel getMedicineById(
            String medicineId) {

        if (medicineId == null ||
            medicineId.trim().isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                            .document(medicineId)
                            .get()
                            .get();

            if (!document.exists()) {
                return null;
            }

            return documentToMedicine(document);

        } catch (Exception e) {

            System.out.println(
                    "Error fetching medicine by ID"
            );

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // GET MEDICINE BY NAME
    // =========================================================

    public MedicineReminderModel getMedicineByName(
            String medicineName) {

        if (medicineName == null) {
            return null;
        }

        try {

            QuerySnapshot snapshot =
                    db.collection(COLLECTION)
                            .whereEqualTo(
                                    "medicineName",
                                    medicineName
                            )
                            .limit(1)
                            .get()
                            .get();

            if (snapshot.isEmpty()) {
                return null;
            }

            return documentToMedicine(
                    snapshot.getDocuments().get(0)
            );

        } catch (Exception e) {

            System.out.println(
                    "Error fetching medicine by name"
            );

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // SAVE MEDICINE
    // =========================================================

    public boolean saveMedicine(
            MedicineReminderModel medicine) {

        if (medicine == null) {
            return false;
        }

        try {

            String medicineId =
                    medicine.getMedicineId();

            if (medicineId == null ||
                medicineId.trim().isEmpty()) {

                medicineId =
                        db.collection(COLLECTION)
                                .document()
                                .getId();

                medicine.setMedicineId(
                        medicineId
                );
            }

            Map<String, Object> data =
                    medicineToMap(medicine);

            db.collection(COLLECTION)
                    .document(medicineId)
                    .set(data)
                    .get();

            System.out.println(
                    "Medicine saved successfully: " +
                    medicine.getMedicineName()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving medicine"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // UPDATE MEDICINE
    // =========================================================

    public boolean updateMedicine(
            MedicineReminderModel medicine) {

        if (medicine == null ||
            medicine.getMedicineId() == null ||
            medicine.getMedicineId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    medicineToMap(medicine);

            db.collection(COLLECTION)
                    .document(
                            medicine.getMedicineId()
                    )
                    .set(data)
                    .get();

            System.out.println(
                    "Medicine updated successfully: " +
                    medicine.getMedicineName()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating medicine"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE MEDICINE
    // =========================================================

    public boolean deleteMedicine(
            String medicineId) {

        if (medicineId == null ||
            medicineId.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
                    .document(medicineId)
                    .delete()
                    .get();

            System.out.println(
                    "Medicine deleted: " +
                    medicineId
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting medicine"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // MARK AS TAKEN
    // =========================================================

    public boolean markAsTaken(
            String medicineId) {

        if (medicineId == null ||
            medicineId.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
                    .document(medicineId)
                    .update(
                            "taken",
                            true
                    )
                    .get();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error marking medicine as taken"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // MARK AS NOT TAKEN
    // =========================================================

    public boolean markAsNotTaken(
            String medicineId) {

        if (medicineId == null ||
            medicineId.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
                    .document(medicineId)
                    .update(
                            "taken",
                            false
                    )
                    .get();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error marking medicine as not taken"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CONVERT MODEL → FIREBASE MAP
    // =========================================================

    private Map<String, Object> medicineToMap(
            MedicineReminderModel medicine) {

        Map<String, Object> map =
                new HashMap<>();

        map.put(
                "medicineId",
                medicine.getMedicineId()
        );

        map.put(
                "motherId",
                medicine.getMotherId()
        );

        map.put(
                "medicineName",
                medicine.getMedicineName()
        );

        map.put(
                "medicineType",
                medicine.getMedicineType()
        );

        map.put(
                "period",
                medicine.getPeriod()
        );

        map.put(
                "time",
                medicine.getTime()
        );

        map.put(
                "instruction",
                medicine.getInstruction()
        );

        map.put(
                "dosage",
                medicine.getDosage()
        );

        map.put(
                "frequency",
                medicine.getFrequency()
        );

        map.put(
                "prescribedBy",
                medicine.getPrescribedBy()
        );

        map.put(
                "prescribedDate",
                medicine.getPrescribedDate()
        );

        map.put(
                "taken",
                medicine.isTaken()
        );

        map.put(
                "notificationsEnabled",
                medicine.isNotificationsEnabled()
        );

        map.put(
                "earlyReminderEnabled",
                medicine.isEarlyReminderEnabled()
        );

        map.put(
                "dailySummaryEnabled",
                medicine.isDailySummaryEnabled()
        );

        return map;
    }


    // =========================================================
    // CONVERT FIREBASE → MODEL
    // =========================================================

    private MedicineReminderModel documentToMedicine(
            DocumentSnapshot document) {

        try {

            MedicineReminderModel medicine =
                    new MedicineReminderModel();

            medicine.setMedicineId(
                    document.getString("medicineId")
            );

            medicine.setMotherId(
                    document.getString("motherId")
            );

            medicine.setMedicineName(
                    document.getString("medicineName")
            );

            medicine.setMedicineType(
                    document.getString("medicineType")
            );

            medicine.setPeriod(
                    document.getString("period")
            );

            medicine.setTime(
                    document.getString("time")
            );

            medicine.setInstruction(
                    document.getString("instruction")
            );

            medicine.setDosage(
                    document.getString("dosage")
            );

            medicine.setFrequency(
                    document.getString("frequency")
            );

            medicine.setPrescribedBy(
                    document.getString("prescribedBy")
            );

            medicine.setPrescribedDate(
                    document.getString("prescribedDate")
            );

            medicine.setTaken(
                    getBoolean(
                            document.get("taken")
                    )
            );

            medicine.setNotificationsEnabled(
                    getBoolean(
                            document.get(
                                    "notificationsEnabled"
                            )
                    )
            );

            medicine.setEarlyReminderEnabled(
                    getBoolean(
                            document.get(
                                    "earlyReminderEnabled"
                            )
                    )
            );

            medicine.setDailySummaryEnabled(
                getBoolean(
                        document.get(
                                "dailySummaryEnabled"
                        )
                )
            );
            
            return medicine;

        } catch (Exception e) {

            System.out.println(
                    "Error converting Firebase document"
            );

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // BOOLEAN HELPER
    // =========================================================

    private boolean getBoolean(Object value) {

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        return false;
    }
}