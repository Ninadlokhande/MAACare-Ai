package com.sigma.model;

public class Labrecords {

    // =====================================================
    // EXISTING FIELDS
    // =====================================================

    private String number;
    private String PatientName;
    private String TestName;
    private String Department;
    private String Date;
    private String Status;
    private String Results;
    private String documentUrl;

    // =====================================================
    // NEW FIELDS
    // =====================================================

    /*
     * Firebase UID of the Mother.
     *
     * This helps identify which mother's report
     * this laboratory record belongs to.
     */
    private String motherId;

    /*
     * ID of the original Doctor report.
     *
     * This prevents duplicate Hospital records
     * for the same Doctor report.
     */
    private String sourceReportId;

    // =====================================================
    // EMPTY CONSTRUCTOR
    // Required by Firestore
    // =====================================================

    public Labrecords() {
    }

    // =====================================================
    // EXISTING CONSTRUCTOR
    // =====================================================

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

    // =====================================================
    // NUMBER
    // =====================================================

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // =====================================================
    // PATIENT NAME
    // =====================================================

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    // =====================================================
    // TEST NAME
    // =====================================================

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String TestName) {
        this.TestName = TestName;
    }

    // =====================================================
    // DEPARTMENT
    // =====================================================

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    // =====================================================
    // DATE
    // =====================================================

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    // =====================================================
    // STATUS
    // =====================================================

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    // =====================================================
    // RESULTS
    // =====================================================

    public String getResults() {
        return Results;
    }

    public void setResults(String Results) {
        this.Results = Results;
    }

    // =====================================================
    // DOCUMENT URL
    // =====================================================

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    // =====================================================
    // MOTHER ID
    // =====================================================

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    // =====================================================
    // SOURCE REPORT ID
    // =====================================================

    public String getSourceReportId() {
        return sourceReportId;
    }

    public void setSourceReportId(String sourceReportId) {
        this.sourceReportId = sourceReportId;
    }

    // =====================================================
    // TO STRING
    // =====================================================

    @Override
    public String toString() {

        return "Labrecords{" +
                "number='" + number + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", TestName='" + TestName + '\'' +
                ", Department='" + Department + '\'' +
                ", Date='" + Date + '\'' +
                ", Status='" + Status + '\'' +
                ", Results='" + Results + '\'' +
                ", documentUrl='" + documentUrl + '\'' +
                ", motherId='" + motherId + '\'' +
                ", sourceReportId='" + sourceReportId + '\'' +
                '}';
    }
}