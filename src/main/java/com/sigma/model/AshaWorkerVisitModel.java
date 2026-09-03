package com.sigma.model;

public class AshaWorkerVisitModel {

    private int beneficiaryId;
    private String name;
    private String location;
    private String date;
    private String purpose;
    private String time;
    private String condition;


    // =========================================================
    // EMPTY CONSTRUCTOR
    // REQUIRED BY FIRESTORE
    // =========================================================

    public AshaWorkerVisitModel() {
    }


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AshaWorkerVisitModel(
            int beneficiaryId,
            String name,
            String location,
            String date,
            String purpose,
            String time,
            String condition) {

        this.beneficiaryId = beneficiaryId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.purpose = purpose;
        this.time = time;
        this.condition = condition;
    }


    // =========================================================
    // GETTERS
    // =========================================================

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getTime() {
        return time;
    }

    public String getCondition() {
        return condition;
    }


    // =========================================================
    // SETTERS
    // =========================================================

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
