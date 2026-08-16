package com.sigma.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private final StringProperty time;
    private final StringProperty patient;
    private final StringProperty type;
    private final StringProperty status;
    private final StringProperty payment;
    private final StringProperty action;

    public Appointment(
            String time,
            String patient,
            String type,
            String status,
            String payment,
            String action) {

        this.time = new SimpleStringProperty(time);

        this.patient = new SimpleStringProperty(patient);

        this.type = new SimpleStringProperty(type);

        this.status = new SimpleStringProperty(status);

        this.payment = new SimpleStringProperty(payment);

        this.action = new SimpleStringProperty(action);
    }

    // =====================================================
    // GETTERS
    // =====================================================

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
}