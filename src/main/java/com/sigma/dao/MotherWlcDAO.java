
package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.MotherWlcModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MotherWlcDAO {

    private final Firestore db;

    private static final String COLLECTION_NAME = "mothers";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherWlcDAO() {

        db = FirebaseConfig.getFirestore();

        System.out.println(
                "MotherWlcDAO connected to Firebase"
        );
    }

    // =========================================================
    // SAVE MOTHER
    // =========================================================

    public boolean saveMother(MotherWlcModel mother) {

        if (mother == null) {
            return false;
        }

        try {

            // Generate Mother ID if not present
            if (mother.getMotherId() == null
                    || mother.getMotherId().isEmpty()) {

                mother.setMotherId(
                        "MOTHER_"
                                + UUID.randomUUID()
                                        .toString()
                                        .substring(0, 8)
                );
            }

            // -------------------------------------------------
            // Convert Model -> Firebase Map
            // -------------------------------------------------

            Map<String, Object> data =
                    convertToMap(mother);

            // -------------------------------------------------
            // Save to Firestore
            // -------------------------------------------------

            db.collection(COLLECTION_NAME)
                    .document(mother.getMotherId())
                    .set(data)
                    .get();

            System.out.println(
                    "Mother saved successfully: "
                            + mother.getMotherId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving mother!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET MOTHER BY ID
    // =========================================================

    public MotherWlcModel getMother(String motherId) {

        if (motherId == null
                || motherId.isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION_NAME)
                            .document(motherId)
                            .get()
                            .get();

            if (document.exists()) {

                MotherWlcModel mother =
                        convertFromDocument(document);

                System.out.println(
                        "Mother fetched successfully!"
                );

                return mother;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching mother!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // GET FIRST MOTHER
    // =========================================================

    public MotherWlcModel getMother() {

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION_NAME)
                            .limit(1)
                            .get();

            List<QueryDocumentSnapshot> documents =
                    future.get()
                            .getDocuments();

            if (!documents.isEmpty()) {

                MotherWlcModel mother =
                        convertFromDocument(
                                documents.get(0)
                        );

                System.out.println(
                        "Mother data loaded successfully!"
                );

                return mother;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading mother!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE MOTHER
    // =========================================================

    public boolean updateMother(
            MotherWlcModel mother) {

        if (mother == null
                || mother.getMotherId() == null
                || mother.getMotherId().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    convertToMap(mother);

            db.collection(COLLECTION_NAME)
                    .document(mother.getMotherId())
                    .set(data)
                    .get();

            System.out.println(
                    "Mother updated successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating mother!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE MOTHER BY ID
    // =========================================================

    public boolean deleteMother(
            String motherId) {

        if (motherId == null
                || motherId.isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION_NAME)
                    .document(motherId)
                    .delete()
                    .get();

            System.out.println(
                    "Mother deleted successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting mother!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE FIRST MOTHER
    // =========================================================

    public boolean deleteMother() {

        MotherWlcModel mother =
                getMother();

        if (mother == null) {
            return false;
        }

        return deleteMother(
                mother.getMotherId()
        );
    }

    // =========================================================
    // MODEL -> FIREBASE MAP
    // =========================================================

    private Map<String, Object> convertToMap(
            MotherWlcModel mother) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "motherId",
                mother.getMotherId()
        );

        data.put(
                "name",
                mother.getName()
        );

        // LocalDate -> String
        data.put(
                "dateOfBirth",
                mother.getDateOfBirth() != null
                        ? mother.getDateOfBirth().toString()
                        : null
        );

        data.put(
                "location",
                mother.getLocation()
        );

        data.put(
                "weight",
                mother.getWeight()
        );

        data.put(
                "bloodGroup",
                mother.getBloodGroup()
        );

        data.put(
                "medicalCondition",
                mother.getMedicalCondition()
        );

        // LocalDate -> String
        data.put(
                "lmpDate",
                mother.getLmpDate() != null
                        ? mother.getLmpDate().toString()
                        : null
        );

        // LocalDate -> String
        data.put(
                "eddDate",
                mother.getEddDate() != null
                        ? mother.getEddDate().toString()
                        : null
        );

        // =====================================================
        // ADDITIONAL MOTHER INFORMATION
        // =====================================================

        data.put(
                "address",
                mother.getAddress()
        );

        data.put(
                "phone",
                mother.getPhone()
        );

        data.put(
                "allergies",
                mother.getAllergies()
        );

        data.put(
                "maritalStatus",
                mother.getMaritalStatus()
        );

        data.put(
                "familyMemberName",
                mother.getFamilyMemberName()
        );

        data.put(
                "familyRelationship",
                mother.getFamilyRelationship()
        );

        data.put(
                "familyPhone",
                mother.getFamilyPhone()
        );

        data.put(
                "emergencyName",
                mother.getEmergencyName()
        );

        data.put(
                "emergencyPhone",
                mother.getEmergencyPhone()
        );

        data.put(
                "emergencyRelationship",
                mother.getEmergencyRelationship()
        );

        data.put(
                "profilePhotoPath",
                mother.getProfilePhotoPath()
        );

        return data;
    }

    // =========================================================
    // FIREBASE DOCUMENT -> MODEL
    // =========================================================

    private MotherWlcModel convertFromDocument(
            DocumentSnapshot document) {

        MotherWlcModel mother =
                new MotherWlcModel();

        // -----------------------------------------------------
        // Basic Information
        // -----------------------------------------------------

        mother.setMotherId(
                document.getString("motherId")
        );

        mother.setName(
                document.getString("name")
        );

        String dob =
                document.getString("dateOfBirth");

        if (dob != null && !dob.isEmpty()) {

            mother.setDateOfBirth(
                    LocalDate.parse(dob)
            );
        }

        mother.setLocation(
                document.getString("location")
        );

        Double weight =
                document.getDouble("weight");

        if (weight != null) {

            mother.setWeight(weight);
        }

        mother.setBloodGroup(
                document.getString("bloodGroup")
        );

        mother.setMedicalCondition(
                document.getString(
                        "medicalCondition"
                )
        );

        // -----------------------------------------------------
        // Pregnancy Information
        // -----------------------------------------------------

        String lmp =
                document.getString("lmpDate");

        if (lmp != null && !lmp.isEmpty()) {

            mother.setLmpDate(
                    LocalDate.parse(lmp)
            );
        }

        String edd =
                document.getString("eddDate");

        if (edd != null && !edd.isEmpty()) {

            mother.setEddDate(
                    LocalDate.parse(edd)
            );
        }

        // =====================================================
        // ADDITIONAL INFORMATION
        // =====================================================

        mother.setAddress(
                document.getString("address")
        );

        mother.setPhone(
                document.getString("phone")
        );

        mother.setAllergies(
                document.getString("allergies")
        );

        mother.setMaritalStatus(
                document.getString("maritalStatus")
        );

        mother.setFamilyMemberName(
                document.getString("familyMemberName")
        );

        mother.setFamilyRelationship(
                document.getString(
                        "familyRelationship"
                )
        );

        mother.setFamilyPhone(
                document.getString("familyPhone")
        );

        mother.setEmergencyName(
                document.getString("emergencyName")
        );

        mother.setEmergencyPhone(
                document.getString("emergencyPhone")
        );

        mother.setEmergencyRelationship(
                document.getString(
                        "emergencyRelationship"
                )
        );

        mother.setProfilePhotoPath(
                document.getString(
                        "profilePhotoPath"
                )
        );

        return mother;
    }
}
