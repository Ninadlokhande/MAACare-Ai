package com.sigma.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private final StringProperty time;
    private final StringProperty patient;
    private final StringProperty details;
    private final StringProperty type;
    private final StringProperty status;
    private final StringProperty payment;
    private final StringProperty date;

    public Appointment(
            String time,
            String patient,
            String details,
            String type,
            String status,
            String payment,
            String date) {

        this.time = new SimpleStringProperty(time);
        this.patient = new SimpleStringProperty(patient);
        this.details = new SimpleStringProperty(details);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.payment = new SimpleStringProperty(payment);
        this.date = new SimpleStringProperty(date);
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty patientProperty() {
        return patient;
    }

    public StringProperty detailsProperty() {
        return details;
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

    public StringProperty dateProperty() {
        return date;
    }

    public String getTime() {
        return time.get();
    }

    public String getPatient() {
        return patient.get();
    }

    public String getDetails() {
        return details.get();
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

    public String getDate() {
        return date.get();
    }

    public void setTime(String value) {
        time.set(value);
    }

    public void setPatient(String value) {
        patient.set(value);
    }

    public void setDetails(String value) {
        details.set(value);
    }

    public void setType(String value) {
        type.set(value);
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public void setPayment(String value) {
        payment.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public String getDoctor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDoctor'");
    }
}