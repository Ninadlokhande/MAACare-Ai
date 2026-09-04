
package com.sigma.model.DoctorModel;

public class Settings {

    // =========================================================
    // DOCTOR PROFILE
    // =========================================================

    private String fullName;
    private String email;
    private String phone;

    private String specialization;
    private String qualification;
    private String license;
    private String experience;

    // =========================================================
    // CLINIC INFORMATION
    // =========================================================

    private String clinicName;
    private String clinicAddress;

    // =========================================================
    // AVAILABILITY
    // =========================================================

    private String consultationDays;
    private String startTime;
    private String endTime;

    // =========================================================
    // APPOINTMENT SETTINGS
    // =========================================================

    private String appointmentDuration;
    private boolean autoConfirmAppointments;
    private boolean appointmentReminders;

    // =========================================================
    // NOTIFICATIONS
    // =========================================================

    private boolean messageNotifications;
    private boolean emailNotifications;
    private boolean reportNotifications;

    // =========================================================
    // APPEARANCE
    // =========================================================

    private String appearance;
    private String language;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Settings() {

        fullName = "";
        email = "";
        phone = "";

        specialization = "";
        qualification = "";
        license = "";
        experience = "";

        clinicName = "";
        clinicAddress = "";

        consultationDays = "Monday - Friday";
        startTime = "09:00 AM";
        endTime = "05:00 PM";

        appointmentDuration = "30 Minutes";
        autoConfirmAppointments = false;
        appointmentReminders = true;

        messageNotifications = true;
        emailNotifications = true;
        reportNotifications = true;

        appearance = "Light";
        language = "English";
    }

    // =========================================================
    // PROFILE GETTERS / SETTERS
    // =========================================================

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    // =========================================================
    // CLINIC GETTERS / SETTERS
    // =========================================================

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    // =========================================================
    // AVAILABILITY
    // =========================================================

    public String getConsultationDays() {
        return consultationDays;
    }

    public void setConsultationDays(String consultationDays) {
        this.consultationDays = consultationDays;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // =========================================================
    // APPOINTMENT SETTINGS
    // =========================================================

    public String getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(String appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public boolean isAutoConfirmAppointments() {
        return autoConfirmAppointments;
    }

    public void setAutoConfirmAppointments(
            boolean autoConfirmAppointments) {

        this.autoConfirmAppointments = autoConfirmAppointments;
    }

    public boolean isAppointmentReminders() {
        return appointmentReminders;
    }

    public void setAppointmentReminders(
            boolean appointmentReminders) {

        this.appointmentReminders = appointmentReminders;
    }

    // =========================================================
    // NOTIFICATIONS
    // =========================================================

    public boolean isMessageNotifications() {
        return messageNotifications;
    }

    public void setMessageNotifications(
            boolean messageNotifications) {

        this.messageNotifications = messageNotifications;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(
            boolean emailNotifications) {

        this.emailNotifications = emailNotifications;
    }

    public boolean isReportNotifications() {
        return reportNotifications;
    }

    public void setReportNotifications(
            boolean reportNotifications) {

        this.reportNotifications = reportNotifications;
    }

    // =========================================================
    // APPEARANCE
    // =========================================================

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
