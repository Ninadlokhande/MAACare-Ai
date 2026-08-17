package com.sigma.model;

public class DoctorBasicInformationModel {

    private String fullName;
    private String email;
    private String phone;
    private String specialization;
    private String qualification;
    private String experience;
    private String license;

    public DoctorBasicInformationModel(
            String fullName,
            String email,
            String phone,
            String specialization,
            String qualification,
            String experience,
            String license) {

        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.license = license;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public String getExperience() {
        return experience;
    }

    public String getLicense() {
        return license;
    }
}