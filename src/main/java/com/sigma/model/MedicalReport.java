package com.sigma.model;

public class MedicalReport {

    private String reportId;
    private String reportName;
    private String reportDate;
    private String hospitalName;
    private String doctorName;
    private String fileUrl;

    // Empty constructor required for Firebase Firestore
    public MedicalReport() {
    }

    // Parameterized constructor
    public MedicalReport(
            String reportId,
            String reportName,
            String reportDate,
            String hospitalName,
            String doctorName,
            String fileUrl) {

        this.reportId = reportId;
        this.reportName = reportName;
        this.reportDate = reportDate;
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}