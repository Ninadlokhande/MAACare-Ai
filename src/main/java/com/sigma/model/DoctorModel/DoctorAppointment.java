
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
    // CONSTRUCTOR
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

        this.appointmentId = new SimpleStringProperty(
                appointmentId == null ? "" : appointmentId);

        this.doctorId = new SimpleStringProperty(
                doctorId == null ? "" : doctorId);

        this.patientId = new SimpleStringProperty(
                patientId == null ? "" : patientId);

        this.date = new SimpleStringProperty(
                date == null ? "" : date);

        this.time = new SimpleStringProperty(
                time == null ? "" : time);

        this.patient = new SimpleStringProperty(
                patient == null ? "" : patient);

        this.type = new SimpleStringProperty(
                type == null ? "" : type);

        this.status = new SimpleStringProperty(
                status == null ? "" : status);

        this.payment = new SimpleStringProperty(
                payment == null ? "" : payment);

        this.action = new SimpleStringProperty(
                action == null ? "View" : action);
    }

    // =====================================================
    // BACKWARD COMPATIBLE CONSTRUCTOR
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

    public void setAppointmentId(String value) {
        appointmentId.set(value);
    }

    public void setDoctorId(String value) {
        doctorId.set(value);
    }

    public void setPatientId(String value) {
        patientId.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setTime(String value) {
        time.set(value);
    }

    public void setPatient(String value) {
        patient.set(value);
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

    public void setAction(String value) {
        action.set(value);
    }

    // =====================================================
    // TO STRING
    // =====================================================

    @Override
    public String toString() {
        return "DoctorAppointment{" +
                "appointmentId='" + getAppointmentId() + '\'' +
                ", doctorId='" + getDoctorId() + '\'' +
                ", patientId='" + getPatientId() + '\'' +
                ", date='" + getDate() + '\'' +
                ", time='" + getTime() + '\'' +
                ", patient='" + getPatient() + '\'' +
                ", type='" + getType() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", payment='" + getPayment() + '\'' +
                '}';
    }
}
