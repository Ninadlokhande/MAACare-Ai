package com.sigma.model;

public class Labrecords {

    // =========================================================
    // FIELDS
    // =========================================================

    private String number;
    private String PatientName;
    private String TestName;
    private String Department;
    private String Date;
    private String Status;
    private String Results;

    // Cloudinary document URL
    private String documentUrl;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public Labrecords() {

    }


    // =========================================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================================

    public Labrecords(
            String number,
            String PatientName,
            String TestName,
            String Department,
            String Date,
            String Status,
            String Results,
            String documentUrl) {

        this.number = number;
        this.PatientName = PatientName;
        this.TestName = TestName;
        this.Department = Department;
        this.Date = Date;
        this.Status = Status;
        this.Results = Results;
        this.documentUrl = documentUrl;
    }


    // =========================================================
    // NUMBER
    // =========================================================

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    // =========================================================
    // PATIENT NAME
    // =========================================================

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }


    // =========================================================
    // TEST NAME
    // =========================================================

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }


    // =========================================================
    // DEPARTMENT
    // =========================================================

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }


    // =========================================================
    // DATE
    // =========================================================

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    // =========================================================
    // STATUS
    // =========================================================

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    // =========================================================
    // RESULTS
    // =========================================================

    public String getResults() {
        return Results;
    }

    public void setResults(String results) {
        Results = results;
    }


    // =========================================================
    // DOCUMENT URL
    // =========================================================

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
}