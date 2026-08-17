package com.sigma.model;

public class Settings {

    // ==========================================
    // VARIABLES
    // ==========================================

    private String fullName;
    private String email;
    private String phone;
    private String specialization;
    private String license;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public Settings(
            String fullName,
            String email,
            String phone,
            String specialization,
            String license) {

        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.license = license;
    }

    // ==========================================
    // GETTERS
    // ==========================================

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

    public String getLicense() {
        return license;
    }

    // ==========================================
    // SETTERS
    // ==========================================

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}