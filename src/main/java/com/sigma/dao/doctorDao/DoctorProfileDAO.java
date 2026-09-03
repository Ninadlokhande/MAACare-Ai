package com.sigma.dao.doctorDao;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.sigma.model.DoctorModel.DoctorProfileModel;

public class DoctorProfileDAO {

        private final Firestore db;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public DoctorProfileDAO(Firestore db) {
                this.db = db;
        }

        // =====================================================
        // GET DOCTOR PROFILE
        // =====================================================

        public DoctorProfileModel getDoctorInformation(String uid) {

                DoctorProfileModel doctor = new DoctorProfileModel();

                if (uid == null || uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PROFILE] UID is empty.");

                        return doctor;
                }

                try {

                        DocumentReference docRef = db.collection("doctors").document(uid);

                        ApiFuture<DocumentSnapshot> future = docRef.get();

                        DocumentSnapshot document = future.get();

                        // =================================================
                        // DOCUMENT NOT FOUND
                        // =================================================

                        if (!document.exists()) {

                                System.out.println(
                                                "[DOCTOR PROFILE] No profile found for UID: "
                                                                + uid);

                                return doctor;
                        }

                        // =================================================
                        // PERSONAL INFORMATION
                        // =================================================

                        doctor.setFirstName(
                                        getString(document, "firstName"));

                        doctor.setLastName(
                                        getString(document, "lastName"));

                        doctor.setGender(
                                        getString(document, "gender"));

                        doctor.setDob(
                                        getString(document, "dob"));

                        // =================================================
                        // CONTACT INFORMATION
                        // =================================================

                        doctor.setPhone(
                                        getString(document, "phone"));

                        doctor.setEmail(
                                        getString(document, "email"));

                        doctor.setAddress(
                                        getString(document, "address"));

                        // =================================================
                        // PROFESSIONAL INFORMATION
                        // =================================================

                        doctor.setSpecialization(
                                        getString(document, "specialization"));

                        doctor.setQualification(
                                        getString(document, "qualification"));

                        doctor.setExperience(
                                        getString(document, "experience"));

                        doctor.setMedicalLicense(
                                        getString(document, "medicalLicense"));

                        // =================================================
                        // CLINIC INFORMATION
                        // =================================================

                        doctor.setClinicName(
                                        getString(document, "clinicName"));

                        doctor.setClinicAddress(
                                        getString(document, "clinicAddress"));

                        System.out.println(
                                        "[DOCTOR PROFILE] Profile loaded successfully.");

                        return doctor;

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Error loading profile.");

                        e.printStackTrace();

                        return doctor;
                }
        }

        // =====================================================
        // UPDATE DOCTOR PROFILE
        // =====================================================

        public boolean updateDoctorInformation(
                        String uid,
                        DoctorProfileModel doctor) {

                // =================================================
                // CHECK UID
                // =================================================

                if (uid == null || uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Cannot save. UID is empty.");

                        return false;
                }

                // =================================================
                // CHECK MODEL
                // =================================================

                if (doctor == null) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Cannot save. Doctor object is null.");

                        return false;
                }

                try {

                        Map<String, Object> data = new HashMap<>();

                        // =================================================
                        // PERSONAL INFORMATION
                        // =================================================

                        data.put(
                                        "firstName",
                                        safe(doctor.getFirstName()));

                        data.put(
                                        "lastName",
                                        safe(doctor.getLastName()));

                        data.put(
                                        "gender",
                                        safe(doctor.getGender()));

                        data.put(
                                        "dob",
                                        safe(doctor.getDob()));

                        // =================================================
                        // CONTACT INFORMATION
                        // =================================================

                        data.put(
                                        "phone",
                                        safe(doctor.getPhone()));

                        data.put(
                                        "email",
                                        safe(doctor.getEmail()));

                        data.put(
                                        "address",
                                        safe(doctor.getAddress()));

                        // =================================================
                        // PROFESSIONAL INFORMATION
                        // =================================================

                        data.put(
                                        "specialization",
                                        safe(doctor.getSpecialization()));

                        data.put(
                                        "qualification",
                                        safe(doctor.getQualification()));

                        data.put(
                                        "experience",
                                        safe(doctor.getExperience()));

                        data.put(
                                        "medicalLicense",
                                        safe(doctor.getMedicalLicense()));

                        // =================================================
                        // CLINIC INFORMATION
                        // =================================================

                        data.put(
                                        "clinicName",
                                        safe(doctor.getClinicName()));

                        data.put(
                                        "clinicAddress",
                                        safe(doctor.getClinicAddress()));

                        // =================================================
                        // FIRESTORE
                        // =================================================

                        DocumentReference docRef = db.collection("doctors")
                                        .document(uid);

                        /*
                         * IMPORTANT:
                         *
                         * update() is NOT used here because the doctor
                         * document may not exist yet.
                         *
                         * set(data, SetOptions.merge()) means:
                         *
                         * 1. Create document if it does not exist.
                         * 2. Update only the fields inside data.
                         * 3. Existing photoUrl will NOT be deleted.
                         */

                        docRef.set(
                                        data,
                                        com.google.cloud.firestore.SetOptions.merge()).get();

                        System.out.println(
                                        "[DOCTOR PROFILE] Profile saved successfully to Firestore.");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Error saving profile to Firestore.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =====================================================
        // GET DOCTOR PHOTO URL
        // =====================================================

        public String getDoctorPhotoUrl(String uid) {

                if (uid == null || uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PHOTO] UID is empty.");

                        return "";
                }

                try {

                        DocumentReference docRef = db.collection("doctors")
                                        .document(uid);

                        DocumentSnapshot document = docRef.get().get();

                        if (!document.exists()) {

                                System.out.println(
                                                "[DOCTOR PHOTO] Doctor document not found.");

                                return "";
                        }

                        /*
                         * Main field:
                         * photoUrl
                         */

                        String photoUrl = getString(document, "photoUrl");

                        if (!photoUrl.isEmpty()) {

                                return photoUrl;
                        }

                        /*
                         * Backward compatibility:
                         * If existing data uses another field name.
                         */

                        photoUrl = getString(document, "profilePhotoUrl");

                        if (!photoUrl.isEmpty()) {

                                return photoUrl;
                        }

                        photoUrl = getString(document, "photoURL");

                        if (!photoUrl.isEmpty()) {

                                return photoUrl;
                        }

                        photoUrl = getString(document, "cloudinaryUrl");

                        if (!photoUrl.isEmpty()) {

                                return photoUrl;
                        }

                        return "";

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Error loading doctor photo URL.");

                        e.printStackTrace();

                        return "";
                }
        }

        // =====================================================
        // SAVE DOCTOR PHOTO URL
        // =====================================================

        public boolean saveDoctorPhotoUrl(
                        String uid,
                        String photoUrl) {

                if (uid == null || uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Cannot save photo. UID is empty.");

                        return false;
                }

                if (photoUrl == null ||
                                photoUrl.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Cannot save photo. URL is empty.");

                        return false;
                }

                try {

                        DocumentReference docRef = db.collection("doctors")
                                        .document(uid);

                        Map<String, Object> photoData = new HashMap<>();

                        photoData.put(
                                        "photoUrl",
                                        photoUrl.trim());

                        /*
                         * merge() is very important.
                         *
                         * It will NOT remove:
                         *
                         * firstName
                         * lastName
                         * email
                         * phone
                         * specialization
                         * clinicName
                         * etc.
                         *
                         * Only photoUrl will be added/updated.
                         */

                        docRef.set(
                                        photoData,
                                        com.google.cloud.firestore.SetOptions.merge()).get();

                        System.out.println(
                                        "[DOCTOR PHOTO] Photo URL saved successfully.");

                        System.out.println(
                                        "[DOCTOR PHOTO] URL = " + photoUrl);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Error saving photo URL.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =====================================================
        // DELETE DOCTOR PHOTO URL
        // =====================================================

        public boolean removeDoctorPhoto(String uid) {

                if (uid == null || uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Cannot remove photo. UID is empty.");

                        return false;
                }

                try {

                        DocumentReference docRef = db.collection("doctors")
                                        .document(uid);

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "photoUrl",
                                        com.google.cloud.firestore.FieldValue.delete());

                        docRef.set(
                                        data,
                                        com.google.cloud.firestore.SetOptions.merge()).get();

                        System.out.println(
                                        "[DOCTOR PHOTO] Photo URL removed successfully.");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PHOTO] Error removing photo URL.");

                        e.printStackTrace();

                        return false;
                }
        }

        // =====================================================
        // GET STRING SAFELY
        // =====================================================

        private String getString(
                        DocumentSnapshot document,
                        String field) {

                String value = document.getString(field);

                return value == null
                                ? ""
                                : value.trim();
        }

        // =====================================================
        // SAFE STRING
        // =====================================================

        private String safe(String value) {

                return value == null
                                ? ""
                                : value.trim();
        }
}