package com.sigma.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientReport {

    // =====================================================
    // FIELDS
    // =====================================================

    private final StringProperty reportName;
    private final StringProperty patientName;
    private final StringProperty reportType;
    private final StringProperty date;
    private final StringProperty status;
    private final StringProperty action;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public PatientReport(
            String reportName,
            String patientName,
            String reportType,
            String date,
            String status,
            String action) {

        this.reportName = new SimpleStringProperty(reportName);

        this.patientName = new SimpleStringProperty(patientName);

        this.reportType = new SimpleStringProperty(reportType);

        this.date = new SimpleStringProperty(date);

        this.status = new SimpleStringProperty(status);

        this.action = new SimpleStringProperty(action);
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public String getReportName() {
        return reportName.get();
    }

    public String getPatientName() {
        return patientName.get();
    }

    public String getReportType() {
        return reportType.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getAction() {
        return action.get();
    }

    // =====================================================
    // PROPERTY METHODS
    // =====================================================

    public StringProperty reportNameProperty() {
        return reportName;
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public StringProperty reportTypeProperty() {
        return reportType;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty actionProperty() {
        return action;
    }

    // =====================================================
    // SETTERS
    // =====================================================

    public void setReportName(String reportName) {
        this.reportName.set(reportName);
    }

    public void setPatientName(String patientName) {
        this.patientName.set(patientName);
    }

    public void setReportType(String reportType) {
        this.reportType.set(reportType);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setAction(String action) {
        this.action.set(action);
    }
}