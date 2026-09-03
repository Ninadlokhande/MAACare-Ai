package com.sigma.model;

import java.time.LocalDate;

public class MotherWlcModel {

    private String name;
    private LocalDate dateOfBirth;
    private String location;
    private double weight;
    private String bloodGroup;
    private String medicalCondition;
    private LocalDate lmpDate;
    private LocalDate eddDate;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================

    public MotherWlcModel() {
    }

    // =========================
    // PARAMETERIZED CONSTRUCTOR
    // =========================

    public MotherWlcModel(
            String name,
            LocalDate dateOfBirth,
            String location,
            double weight,
            String bloodGroup,
            String medicalCondition,
            LocalDate lmpDate,
            LocalDate eddDate) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.medicalCondition = medicalCondition;
        this.lmpDate = lmpDate;
        this.eddDate = eddDate;
    }

    // =========================
    // NAME
    // =========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =========================
    // DATE OF BIRTH
    // =========================

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // =========================
    // LOCATION
    // =========================

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // =========================
    // WEIGHT
    // =========================

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // =========================
    // BLOOD GROUP
    // =========================

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // =========================
    // MEDICAL CONDITION
    // =========================

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    // =========================
    // LMP DATE
    // =========================

    public LocalDate getLmpDate() {
        return lmpDate;
    }

    public void setLmpDate(LocalDate lmpDate) {
        this.lmpDate = lmpDate;
    }

    // =========================
    // EDD DATE
    // =========================

    public LocalDate getEddDate() {
        return eddDate;
    }

    public void setEddDate(LocalDate eddDate) {
        this.eddDate = eddDate;
    }
}