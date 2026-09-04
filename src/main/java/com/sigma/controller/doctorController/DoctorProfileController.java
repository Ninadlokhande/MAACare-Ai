package com.sigma.controller.doctorController;

import com.google.cloud.firestore.Firestore;
import com.sigma.dao.doctorDao.DoctorProfileDAO;
import com.sigma.model.DoctorModel.DoctorProfileModel;

public class DoctorProfileController {

        private final DoctorProfileDAO dao;

        private String doctorUid;

        public DoctorProfileController(Firestore db) {

                this.dao = new DoctorProfileDAO(db);
        }

        public DoctorProfileController(
                        Firestore db,
                        String doctorUid) {

                this.dao = new DoctorProfileDAO(db);

                this.doctorUid = doctorUid;

                System.out.println(
                                "[DOCTOR PROFILE CONTROLLER] UID = "
                                                + doctorUid);
        }

        public void setDoctorUid(String doctorUid) {

                this.doctorUid = doctorUid;

                System.out.println(
                                "[DOCTOR PROFILE CONTROLLER] UID updated = "
                                                + doctorUid);
        }

        public String getDoctorUid() {

                return doctorUid;
        }

        public DoctorProfileModel getDoctorInformation() {

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PROFILE] UID is not available.");

                        return new DoctorProfileModel();
                }

                return dao.getDoctorInformation(
                                doctorUid);
        }

        public boolean updateProfile(
                        String firstName,
                        String lastName,
                        String gender,
                        String dob,
                        String phone,
                        String email,
                        String address,
                        String specialization,
                        String qualification,
                        String experience,
                        String medicalLicense,
                        String clinicName,
                        String clinicAddress) {

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        System.out.println(
                                        "[DOCTOR PROFILE] UID is empty.");

                        return false;
                }

                DoctorProfileModel doctor = new DoctorProfileModel();

                doctor.setFirstName(firstName);
                doctor.setLastName(lastName);
                doctor.setGender(gender);
                doctor.setDob(dob);
                doctor.setPhone(phone);
                doctor.setEmail(email);
                doctor.setAddress(address);
                doctor.setSpecialization(specialization);
                doctor.setQualification(qualification);
                doctor.setExperience(experience);
                doctor.setMedicalLicense(medicalLicense);
                doctor.setClinicName(clinicName);
                doctor.setClinicAddress(clinicAddress);

                return dao.updateDoctorInformation(
                                doctorUid,
                                doctor);
        }

        // =========================================================
        // GET PHOTO URL
        // =========================================================

        public String getDoctorPhotoUrl() {

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        return "";
                }

                return dao.getDoctorPhotoUrl(
                                doctorUid);
        }

        // =========================================================
        // SAVE PHOTO URL
        // =========================================================

        public boolean saveDoctorPhotoUrl(
                        String photoUrl) {

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        return false;
                }

                if (photoUrl == null
                                || photoUrl.trim().isEmpty()) {

                        return false;
                }

                return dao.saveDoctorPhotoUrl(
                                doctorUid,
                                photoUrl);
        }

        // =========================================================
        // REMOVE PHOTO
        // =========================================================

        public boolean removeDoctorPhoto() {

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        return false;
                }

                return dao.removeDoctorPhoto(
                                doctorUid);
        }
}