package com.sigma.model;

public class DoctorBasicInformationModel {

    // =====================================================
    // PERSONAL INFORMATION
    // =====================================================

    private String firstName;
    private String lastName;
    private String gender;
    private String dob;

    // =====================================================
    // CONTACT INFORMATION
    // =====================================================

    private String phone;
    private String email;
    private String address;

    // =====================================================
    // PROFESSIONAL INFORMATION
    // =====================================================

    private String specialization;
    private String qualification;
    private String experience;
    private String medicalLicense;

    // =====================================================
    // CLINIC INFORMATION
    // =====================================================

    private String clinicName;
    private String clinicAddress;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // =====================================================

    public DoctorBasicInformationModel() {
    }

    // =====================================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================================

    public DoctorBasicInformationModel(
            String firstName,
            String lastName,
            String gender,
            String dob,
            String phone,
            String email,
            String address,
            String specialization,
            String qualification,
            String experience,
            String medicalLicense,
            String clinicName,
            String clinicAddress) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.medicalLicense = medicalLicense;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
    }

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }

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

    // =====================================================
    // FULL NAME
    // =====================================================

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // =====================================================
    // TO STRING
    // =====================================================

    @Override
    public String toString() {
        return "DoctorBasicInformation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                ", qualification='" + qualification + '\'' +
                ", experience='" + experience + '\'' +
                ", medicalLicense='" + medicalLicense + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                '}';
    }
}