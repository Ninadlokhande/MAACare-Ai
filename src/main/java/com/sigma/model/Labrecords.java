package com.sigma.model;


public class Labrecords {
    private String number;
    private String PatientName;
    private String TestName;
    private String Department;
    private String  Date;
    private String Status;
    private String Results; 

    // Constructor  

    public Labrecords(){ 
        
    }

    public Labrecords(String number,
        String PatientName,
        String TestName,
        String Department,
        String Date,
        String Status,
        String Results) { 
            this.number=number;
            this.PatientName=PatientName;
            this.TestName=TestName;
            this.Department=Department;
            this.Date=Date;
            this.Status=Status;
            this.Results=Results;
        }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResults() {
        return Results;
    }

    public void setResults(String results) {
        Results = results;
    }


    
    
}

