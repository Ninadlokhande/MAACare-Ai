package com.sigma.dao;

import com.sigma.model.DoctorModel.DoctorBasicInformationModel;

public class DoctorBasicInformationDAO {

    private DoctorBasicInformationModel doctor;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DoctorBasicInformationDAO() {

        doctor = new DoctorBasicInformationModel(

                "Anjali",
                "Mehta",
                "Female",
                "15/08/1990",

                "9876543210",
                "anjalimehta@maacare.com",
                "Pune, Maharashtra",

                "Obstetrician & Gynecologist",
                "MBBS, MD",
                "8 Years",
                "GYN/2020/12345",

                "MaaCare Women's Clinic",
                "Pune, Maharashtra");
    }

    // =====================================================
    // GET DOCTOR
    // =====================================================

    public DoctorBasicInformationModel getDoctorInformation() {

        return doctor;
    }

    // =====================================================
    // UPDATE DOCTOR
    // =====================================================

    public void updateDoctorInformation(
            DoctorBasicInformationModel doctor) {

        if (doctor == null) {
            return;
        }

        this.doctor = doctor;

        System.out.println(
                "Doctor profile updated successfully.");
    }
}