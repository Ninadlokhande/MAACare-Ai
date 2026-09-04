package com.sigma.model;

public class MedicineReminderModel {

    // =========================================================
    // BASIC INFORMATION
    // =========================================================

    private String medicineId;
    private String motherId;

    // =========================================================
    // MEDICINE INFORMATION
    // =========================================================

    private String medicineName;
    private String medicineType;
    private String period;
    private String time;
    private String instruction;

    // =========================================================
    // PRESCRIPTION INFORMATION
    // =========================================================

    private String dosage;
    private String frequency;
    private String prescribedBy;
    private String prescribedDate;

    // =========================================================
    // STATUS
    // =========================================================

    private boolean taken;

    // =========================================================
    // REMINDER SETTINGS
    // =========================================================

    private boolean notificationsEnabled;
    private boolean earlyReminderEnabled;
    private boolean dailySummaryEnabled;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required for Firebase
    // =========================================================

    public MedicineReminderModel() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MedicineReminderModel(
        String medicineName,
        String medicineType,
        String period,
        String time,
        String instruction,
        String dosage,
        String frequency,
        String prescribedBy,
        String prescribedDate,
        boolean taken,
        boolean notificationsEnabled,
        boolean earlyReminderEnabled,
        boolean dailySummaryEnabled) {

    this.medicineName = medicineName;
    this.medicineType = medicineType;
    this.period = period;
    this.time = time;
    this.instruction = instruction;
    this.dosage = dosage;
    this.frequency = frequency;
    this.prescribedBy = prescribedBy;
    this.prescribedDate = prescribedDate;
    this.taken = taken;
    this.notificationsEnabled = notificationsEnabled;
    this.earlyReminderEnabled = earlyReminderEnabled;
    this.dailySummaryEnabled = dailySummaryEnabled;
}
    // =========================================================
    // GETTERS
    // =========================================================

    public String getMedicineId() {
        return medicineId;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public String getPeriod() {
        return period;
    }

    public String getTime() {
        return time;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public String getPrescribedDate() {
        return prescribedDate;
    }

    public boolean isTaken() {
        return taken;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public boolean isEarlyReminderEnabled() {
        return earlyReminderEnabled;
    }

    public boolean isDailySummaryEnabled() {
        return dailySummaryEnabled;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    public void setPrescribedDate(String prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setNotificationsEnabled(
            boolean notificationsEnabled) {

        this.notificationsEnabled = notificationsEnabled;
    }

    public void setEarlyReminderEnabled(
            boolean earlyReminderEnabled) {

        this.earlyReminderEnabled = earlyReminderEnabled;
    }

    public void setDailySummaryEnabled(
            boolean dailySummaryEnabled) {

        this.dailySummaryEnabled = dailySummaryEnabled;
    }
}