package com.sigma.model;


//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class Appointment { 
    private String number;
    private String patient;
    private  String doctor;
    private  String date;
    private  String time;
    private String department;
    private  String status;

    //Constructor  


    public Appointment(){ 
        
    }

    public Appointment(String number,
        String patient,
        String doctor,
        String date,
        String time,
        String department,
        String status){ 
            this.number=number;
            this.patient=patient;
            this.doctor=doctor;
            this.date=date;
            this.time=time;
            this.department=department;
            this.status=status;
        }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

