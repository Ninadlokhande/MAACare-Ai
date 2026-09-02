
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

                if (uid == null ||
                                uid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PROFILE] UID is empty.");

                        return doctor;
                }

                try {

                        DocumentReference docRef = db.collection("doctors")
                                        .document(uid);

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

                if (uid == null ||
                                uid.trim().isEmpty()) {

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
                         * set() will:
                         *
                         * 1. Create document if it does not exist.
                         * 2. Replace/update document if it exists.
                         */

                        docRef.set(data).get();

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
        // GET STRING SAFELY
        // =====================================================

        private String getString(
                        DocumentSnapshot document,
                        String field) {

                String value = document.getString(field);

                return value == null ? "" : value;
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
