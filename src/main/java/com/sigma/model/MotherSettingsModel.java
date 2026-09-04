package com.sigma.model;

public class MotherSettingsModel {

    // =========================================================
    // BASIC INFORMATION
    // =========================================================

    private String motherId;

    // =========================================================
    // NOTIFICATION SETTINGS
    // =========================================================

    private boolean notificationsEnabled;
    private boolean medicineReminderEnabled;
    private boolean appointmentReminderEnabled;
    private boolean vaccinationReminderEnabled;

    // =========================================================
    // LANGUAGE
    // =========================================================

    private String language;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // Required for Firebase Firestore
    // =========================================================

    public MotherSettingsModel() {
    }

    // =========================================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================================

    public MotherSettingsModel(
            String motherId,
            boolean notificationsEnabled,
            boolean medicineReminderEnabled,
            boolean appointmentReminderEnabled,
            boolean vaccinationReminderEnabled,
            String language) {

        this.motherId = motherId;
        this.notificationsEnabled = notificationsEnabled;
        this.medicineReminderEnabled = medicineReminderEnabled;
        this.appointmentReminderEnabled = appointmentReminderEnabled;
        this.vaccinationReminderEnabled = vaccinationReminderEnabled;
        this.language = language;
    }

    // =========================================================
    // MOTHER ID
    // =========================================================

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    // =========================================================
    // NOTIFICATIONS
    // =========================================================

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    // =========================================================
    // MEDICINE REMINDER
    // =========================================================

    public boolean isMedicineReminderEnabled() {
        return medicineReminderEnabled;
    }

    public void setMedicineReminderEnabled(boolean medicineReminderEnabled) {
        this.medicineReminderEnabled = medicineReminderEnabled;
    }

    // =========================================================
    // APPOINTMENT REMINDER
    // =========================================================

    public boolean isAppointmentReminderEnabled() {
        return appointmentReminderEnabled;
    }

    public void setAppointmentReminderEnabled(boolean appointmentReminderEnabled) {
        this.appointmentReminderEnabled = appointmentReminderEnabled;
    }

    // =========================================================
    // VACCINATION REMINDER
    // =========================================================

    public boolean isVaccinationReminderEnabled() {
        return vaccinationReminderEnabled;
    }

    public void setVaccinationReminderEnabled(boolean vaccinationReminderEnabled) {
        this.vaccinationReminderEnabled = vaccinationReminderEnabled;
    }

    // =========================================================
    // LANGUAGE
    // =========================================================

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}