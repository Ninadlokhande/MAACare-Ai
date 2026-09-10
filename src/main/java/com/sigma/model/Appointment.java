
package com.sigma.model;

public class Appointment {

    private String number;
    private String patient;
    private String doctor;
    private String date;
    private String time;
    private String department;
    private String status;

    // =========================================================
    // NEW FIELDS FOR HOSPITAL APPOINTMENT
    // =========================================================
    private String hospital;
    private String motherUid;
    private String appointmentType;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required by Firebase Firestore
    // =========================================================
    public Appointment() {
    }

    // =========================================================
    // EXISTING CONSTRUCTOR
    // DO NOT REMOVE
    // Existing Doctor/Hospital appointment code can still use it
    // =========================================================
    public Appointment(
            String number,
            String patient,
            String doctor,
            String date,
            String time,
            String department,
            String status) {

        this.number = number;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.department = department;
        this.status = status;
    }

    // =========================================================
    // NEW CONSTRUCTOR
    // FOR HOSPITAL APPOINTMENTS
    // =========================================================
    public Appointment(
            String number,
            String patient,
            String doctor,
            String date,
            String time,
            String department,
            String status,
            String hospital,
            String motherUid,
            String appointmentType) {

        this.number = number;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.department = department;
        this.status = status;
        this.hospital = hospital;
        this.motherUid = motherUid;
        this.appointmentType = appointmentType;
    }

    // =========================================================
    // GETTERS / SETTERS
    // =========================================================

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =========================================================
    // NEW HOSPITAL FIELDS
    // =========================================================

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getMotherUid() {
        return motherUid;
    }

    public void setMotherUid(String motherUid) {
        this.motherUid = motherUid;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
}

