package com.sigma.model;

public class PregnancyWeekModel {

// =========================================================
// BASIC INFORMATION
// =========================================================

private int week;

// =========================================================
// WEEK INFORMATION
// =========================================================

private String overview;
private String symptoms;
private String careTips;

// =========================================================
// MOTHER INFORMATION
// =========================================================

private String motherChanges;

// =========================================================
// BABY INFORMATION
// =========================================================

private String babyDevelopment;
private String babySize;

// =========================================================
// ADDITIONAL INFORMATION
// =========================================================

private String developmentDetails;
private String tips;
private String milestone;

// =========================================================
// DEFAULT CONSTRUCTOR
// Required for Firebase Firestore
// =========================================================

public PregnancyWeekModel() {
}

// =========================================================
// 7 PARAMETER CONSTRUCTOR
// Used by your current PregnancyWeekDAO
// =========================================================

public PregnancyWeekModel(
        int week,
        String overview,
        String symptoms,
        String careTips,
        String babyDevelopment,
        String babySize,
        String developmentDetails) {

    this.week = week;
    this.overview = overview;
    this.symptoms = symptoms;
    this.careTips = careTips;
    this.babyDevelopment = babyDevelopment;
    this.babySize = babySize;
    this.developmentDetails = developmentDetails;
}

// =========================================================
// FULL CONSTRUCTOR
// =========================================================

public PregnancyWeekModel(
        int week,
        String overview,
        String symptoms,
        String careTips,
        String motherChanges,
        String babyDevelopment,
        String babySize,
        String developmentDetails,
        String tips,
        String milestone) {

    this.week = week;
    this.overview = overview;
    this.symptoms = symptoms;
    this.careTips = careTips;
    this.motherChanges = motherChanges;
    this.babyDevelopment = babyDevelopment;
    this.babySize = babySize;
    this.developmentDetails = developmentDetails;
    this.tips = tips;
    this.milestone = milestone;
}

// =========================================================
// GETTERS
// =========================================================

public int getWeek() {
    return week;
}

public String getOverview() {
    return overview;
}

public String getSymptoms() {
    return symptoms;
}

public String getCareTips() {
    return careTips;
}

public String getMotherChanges() {
    return motherChanges;
}

public String getBabyDevelopment() {
    return babyDevelopment;
}

public String getBabySize() {
    return babySize;
}

public String getDevelopmentDetails() {
    return developmentDetails;
}

public String getTips() {
    return tips;
}

public String getMilestone() {
    return milestone;
}

// =========================================================
// SETTERS
// =========================================================

public void setWeek(int week) {
    this.week = week;
}

public void setOverview(String overview) {
    this.overview = overview;
}

public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
}

public void setCareTips(String careTips) {
    this.careTips = careTips;
}

public void setMotherChanges(String motherChanges) {
    this.motherChanges = motherChanges;
}

public void setBabyDevelopment(String babyDevelopment) {
    this.babyDevelopment = babyDevelopment;
}

public void setBabySize(String babySize) {
    this.babySize = babySize;
}

public void setDevelopmentDetails(String developmentDetails) {
    this.developmentDetails = developmentDetails;
}

public void setTips(String tips) {
    this.tips = tips;
}

public void setMilestone(String milestone) {
    this.milestone = milestone;
}


}
