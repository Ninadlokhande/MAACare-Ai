package com.sigma.model;

public class BedBooking {
    String number;
    String bookingID;
    String PatientName;
    String Department;
    String BedNo;
    String BedType;
    String CheckinDate; 
    String ExpectedCheckout;
    String Status;  

    // Constructor  


    public BedBooking(){ 

    }


    public BedBooking(String number,
        String bookingID,
        String PatientName,
        String Department,
        String BedNo,
        String BedType,
        String CheckinDate,
        String ExpectedCheckout,
        String Status
    ){ 
        this.number=number;
        this.bookingID=bookingID;
        this.PatientName=PatientName;
        this.Department=Department;
        this.BedNo=BedNo;
        this.BedType=BedType;
        this.CheckinDate=CheckinDate;
        this.ExpectedCheckout=ExpectedCheckout;
        this.Status=Status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
       this. bookingID = bookingID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getBedNo() {
        return BedNo;
    }   

    public void setBedNo(String bedNo) {
        BedNo = bedNo;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String bedType) {
        BedType = bedType;
    }

    public String getCheckinDate() {
        return CheckinDate;
    }

    public void setCheckinDate(String checkinDate) {
        CheckinDate = checkinDate;
    }

    public String getExpectedCheckout() {
        return ExpectedCheckout;
    }

    public void setExpectedCheckout(String expectedCheckout) {
        ExpectedCheckout = expectedCheckout;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

     
}
