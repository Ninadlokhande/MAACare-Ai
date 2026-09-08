package com.sigma.model.DoctorModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientReport {

    private final StringProperty reportId;
    private final StringProperty motherId;
    private final StringProperty reportName;
    private final StringProperty patientName;
    private final StringProperty reportType;
    private final StringProperty date;
    private final StringProperty status;
    private final StringProperty action;
    private final StringProperty reportUrl;

    public PatientReport(
            String reportName,
            String patientName,
            String reportType,
            String date,
            String status,
            String action) {

        this(
                "",
                "",
                reportName,
                patientName,
                reportType,
                date,
                status,
                action,
                "");
    }

    public PatientReport(
            String reportName,
            String patientName,
            String reportType,
            String date,
            String status,
            String action,
            String reportUrl) {

        this(
                "",
                "",
                reportName,
                patientName,
                reportType,
                date,
                status,
                action,
                reportUrl);
    }

    public PatientReport(
            String reportId,
            String motherId,
            String reportName,
            String patientName,
            String reportType,
            String date,
            String status,
            String action,
            String reportUrl) {

        this.reportId = new SimpleStringProperty(
                reportId == null ? "" : reportId);

        this.motherId = new SimpleStringProperty(
                motherId == null ? "" : motherId);

        this.reportName = new SimpleStringProperty(
                reportName == null ? "" : reportName);

        this.patientName = new SimpleStringProperty(
                patientName == null ? "" : patientName);

        this.reportType = new SimpleStringProperty(
                reportType == null ? "" : reportType);

        this.date = new SimpleStringProperty(
                date == null ? "" : date);

        this.status = new SimpleStringProperty(
                status == null ? "" : status);

        this.action = new SimpleStringProperty(
                action == null ? "" : action);

        this.reportUrl = new SimpleStringProperty(
                reportUrl == null ? "" : reportUrl);
    }

    public String getReportId() {
        return reportId.get();
    }

    public void setReportId(String reportId) {
        this.reportId.set(
                reportId == null ? "" : reportId);
    }

    public StringProperty reportIdProperty() {
        return reportId;
    }

    public String getMotherId() {
        return motherId.get();
    }

    public void setMotherId(String motherId) {
        this.motherId.set(
                motherId == null ? "" : motherId);
    }

    public StringProperty motherIdProperty() {
        return motherId;
    }

    public String getReportName() {
        return reportName.get();
    }

    public void setReportName(String reportName) {
        this.reportName.set(
                reportName == null ? "" : reportName);
    }

    public StringProperty reportNameProperty() {
        return reportName;
    }

    public String getPatientName() {
        return patientName.get();
    }

    public void setPatientName(String patientName) {
        this.patientName.set(
                patientName == null ? "" : patientName);
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public String getReportType() {
        return reportType.get();
    }

    public void setReportType(String reportType) {
        this.reportType.set(
                reportType == null ? "" : reportType);
    }

    public StringProperty reportTypeProperty() {
        return reportType;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(
                date == null ? "" : date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(
                status == null ? "" : status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getAction() {
        return action.get();
    }

    public void setAction(String action) {
        this.action.set(
                action == null ? "" : action);
    }

    public StringProperty actionProperty() {
        return action;
    }

    public String getReportUrl() {
        return reportUrl.get();
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl.set(
                reportUrl == null ? "" : reportUrl);
    }

    public StringProperty reportUrlProperty() {
        return reportUrl;
    }

    @Override
    public String toString() {
        return "PatientReport{" +
                "reportId='" + getReportId() + '\'' +
                ", motherId='" + getMotherId() + '\'' +
                ", reportName='" + getReportName() + '\'' +
                ", patientName='" + getPatientName() + '\'' +
                ", reportType='" + getReportType() + '\'' +
                ", date='" + getDate() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", action='" + getAction() + '\'' +
                ", reportUrl='" + getReportUrl() + '\'' +
                '}';
    }
}