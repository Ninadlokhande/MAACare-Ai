package com.sigma.model;

import java.time.LocalDate;

public class MotherWlcModel {

    private String motherId;
    private String name;
    private LocalDate dateOfBirth;
    private String location;
    private double weight;
    private String bloodGroup;
    private String medicalCondition;
    private LocalDate lmpDate;
    private LocalDate eddDate;

    private String address;
    private String phone;
    private String allergies;
    private String maritalStatus;

    private String familyMemberName;
    private String familyRelationship;
    private String familyPhone;

    private String emergencyName;
    private String emergencyPhone;
    private String emergencyRelationship;

    private String profilePhotoPath;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // =========================================================

    public MotherWlcModel() {
    }

    // =========================================================
    // BASIC INFORMATION CONSTRUCTOR
    // Used by Mother Welcome UI
    // =========================================================

    public MotherWlcModel(
            String motherId,
            String name,
            LocalDate dateOfBirth,
            String location,
            double weight,
            String bloodGroup,
            String medicalCondition,
            LocalDate lmpDate,
            LocalDate eddDate) {

        this.motherId = motherId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.medicalCondition = medicalCondition;
        this.lmpDate = lmpDate;
        this.eddDate = eddDate;
    }

    // =========================================================
    // FULL CONSTRUCTOR
    // =========================================================

    public MotherWlcModel(
            String motherId,
            String name,
            LocalDate dateOfBirth,
            String location,
            double weight,
            String bloodGroup,
            String medicalCondition,
            LocalDate lmpDate,
            LocalDate eddDate,
            String address,
            String phone,
            String allergies,
            String maritalStatus,
            String familyMemberName,
            String familyRelationship,
            String familyPhone,
            String emergencyName,
            String emergencyPhone,
            String emergencyRelationship,
            String profilePhotoPath) {

        this.motherId = motherId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.medicalCondition = medicalCondition;
        this.lmpDate = lmpDate;
        this.eddDate = eddDate;
        this.address = address;
        this.phone = phone;
        this.allergies = allergies;
        this.maritalStatus = maritalStatus;
        this.familyMemberName = familyMemberName;
        this.familyRelationship = familyRelationship;
        this.familyPhone = familyPhone;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.emergencyRelationship = emergencyRelationship;
        this.profilePhotoPath = profilePhotoPath;
    }

    // =========================================================
    // GETTERS & SETTERS
    // =========================================================

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public LocalDate getLmpDate() {
        return lmpDate;
    }

    public void setLmpDate(LocalDate lmpDate) {
        this.lmpDate = lmpDate;
    }

    public LocalDate getEddDate() {
        return eddDate;
    }

    public void setEddDate(LocalDate eddDate) {
        this.eddDate = eddDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getFamilyMemberName() {
        return familyMemberName;
    }

    public void setFamilyMemberName(String familyMemberName) {
        this.familyMemberName = familyMemberName;
    }

    public String getFamilyRelationship() {
        return familyRelationship;
    }

    public void setFamilyRelationship(String familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }

    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }
}