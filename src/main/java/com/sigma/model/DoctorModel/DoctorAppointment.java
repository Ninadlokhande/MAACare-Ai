package com.sigma.model.DoctorModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorAppointment {

    // =====================================================
    // PROPERTIES
    // =====================================================

    private final StringProperty appointmentId;
    private final StringProperty doctorId;
    private final StringProperty patientId;
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty patient;
    private final StringProperty type;
    private final StringProperty status;
    private final StringProperty payment;
    private final StringProperty action;

    // =====================================================
    // MAIN CONSTRUCTOR - FIREBASE
    // =====================================================

    public DoctorAppointment(
            String appointmentId,
            String doctorId,
            String patientId,
            String date,
            String time,
            String patient,
            String type,
            String status,
            String payment,
            String action) {

        this.appointmentId =
                new SimpleStringProperty(appointmentId);

        this.doctorId =
                new SimpleStringProperty(doctorId);

        this.patientId =
                new SimpleStringProperty(patientId);

        this.date =
                new SimpleStringProperty(date);

        this.time =
                new SimpleStringProperty(time);

        this.patient =
                new SimpleStringProperty(patient);

        this.type =
                new SimpleStringProperty(type);

        this.status =
                new SimpleStringProperty(status);

        this.payment =
                new SimpleStringProperty(payment);

        this.action =
                new SimpleStringProperty(action);
    }

    // =====================================================
    // OLD CONSTRUCTOR - DAO COMPATIBILITY
    // =====================================================

    public DoctorAppointment(
            String time,
            String patient,
            String type,
            String status,
            String payment,
            String action) {

        this(
                "",
                "",
                "",
                "",
                time,
                patient,
                type,
                status,
                payment,
                action);
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public String getAppointmentId() {
        return appointmentId.get();
    }

    public String getDoctorId() {
        return doctorId.get();
    }

    public String getPatientId() {
        return patientId.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getPatient() {
        return patient.get();
    }

    public String getType() {
        return type.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getPayment() {
        return payment.get();
    }

    public String getAction() {
        return action.get();
    }

    // =====================================================
    // PROPERTY METHODS
    // =====================================================

    public StringProperty appointmentIdProperty() {
        return appointmentId;
    }

    public StringProperty doctorIdProperty() {
        return doctorId;
    }

    public StringProperty patientIdProperty() {
        return patientId;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty patientProperty() {
        return patient;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty paymentProperty() {
        return payment;
    }

    public StringProperty actionProperty() {
        return action;
    }

    // =====================================================
    // SETTERS
    // =====================================================

    public void setAppointmentId(String appointmentId) {
        this.appointmentId.set(appointmentId);
    }

    public void setDoctorId(String doctorId) {
        this.doctorId.set(doctorId);
    }

    public void setPatientId(String patientId) {
        this.patientId.set(patientId);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setPatient(String patient) {
        this.patient.set(patient);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setPayment(String payment) {
        this.payment.set(payment);
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    // =====================================================
    // DETAILS
    // =====================================================

    public String getDetails() {

        return "Appointment ID: " + getAppointmentId()
                + "\nDoctor ID: " + getDoctorId()
                + "\nPatient ID: " + getPatientId()
                + "\nPatient: " + getPatient()
                + "\nDate: " + getDate()
                + "\nTime: " + getTime()
                + "\nType: " + getType()
                + "\nStatus: " + getStatus()
                + "\nPayment: " + getPayment();
    }
}