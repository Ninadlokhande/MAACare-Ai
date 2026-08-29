package com.sigma.controller.doctorController;

import com.sigma.dao.doctorDao.DoctorBasicInformationDAO;
import com.sigma.model.DoctorModel.DoctorBasicInformationModel;

public class DoctorBasicInformationController {

        private final DoctorBasicInformationDAO dao;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public DoctorBasicInformationController() {

                dao = new DoctorBasicInformationDAO();
        }

        // =====================================================
        // GET PROFILE
        // =====================================================

        public DoctorBasicInformationModel getDoctorInformation() {

                return dao.getDoctorInformation();
        }

        // =====================================================
        // UPDATE PROFILE
        // =====================================================

        public void updateProfile(
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

                DoctorBasicInformationModel doctor = dao.getDoctorInformation();

                doctor.setFirstName(firstName);
                doctor.setLastName(lastName);
                doctor.setGender(gender);
                doctor.setDob(dob);

                doctor.setPhone(phone);
                doctor.setEmail(email);
                doctor.setAddress(address);

                doctor.setSpecialization(
                                specialization);

                doctor.setQualification(
                                qualification);

                doctor.setExperience(
                                experience);

                doctor.setMedicalLicense(
                                medicalLicense);

                doctor.setClinicName(
                                clinicName);

                doctor.setClinicAddress(
                                clinicAddress);

                dao.updateDoctorInformation(
                                doctor);
        }
}