package com.sigma.model.DoctorModel;

public class Settings {

    private String fullName;
    private String email;
    private String phone;
    private String specialization;
    private String license;

    private boolean appointmentReminders;
    private boolean messageNotifications;
    private boolean emailNotifications;

    private String appearance;
    private String language;

    public Settings() {
    }

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

        this.appointmentReminders = true;
        this.messageNotifications = true;
        this.emailNotifications = true;

        this.appearance = "Light";
        this.language = "English";
    }

    // =====================================================
    // GETTERS
    // =====================================================

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

    public boolean isAppointmentReminders() {
        return appointmentReminders;
    }

    public boolean isMessageNotifications() {
        return messageNotifications;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getLanguage() {
        return language;
    }

    // =====================================================
    // SETTERS
    // =====================================================

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

    public void setAppointmentReminders(
            boolean appointmentReminders) {

        this.appointmentReminders = appointmentReminders;
    }

    public void setMessageNotifications(
            boolean messageNotifications) {

        this.messageNotifications = messageNotifications;
    }

    public void setEmailNotifications(
            boolean emailNotifications) {

        this.emailNotifications = emailNotifications;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}