package com.sigma.model;

public class MotherVaccinationmodel {

    // =========================================================
    // BASIC INFORMATION
    // =========================================================

    private String id;

    private String motherId;

    private String person;

    // =========================================================
    // VACCINATION INFORMATION
    // =========================================================

    private String vaccineName;

    private String dose;

    private String dueDate;

    private String completedDate;

    private String status;

    private String description;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required for Firebase
    // =========================================================

    public MotherVaccinationmodel() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherVaccinationmodel(
            String id,
            String motherId,
            String person,
            String vaccineName,
            String dose,
            String dueDate,
            String completedDate,
            String status,
            String description) {

        this.id = id;
        this.motherId = motherId;
        this.person = person;
        this.vaccineName = vaccineName;
        this.dose = dose;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.status = status;
        this.description = description;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public String getId() {
        return id;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getPerson() {
        return person;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getDose() {
        return dose;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setId(String id) {
        this.id = id;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}