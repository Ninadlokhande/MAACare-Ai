package com.sigma.model;

public class BedBooking {

    // =====================================================
    // FIELDS
    // =====================================================

    private String number;
    private String bookingID;
    private String motherUid;
    private String patientName;
    private String hospitalName;
    private String department;
    private String bedNo;
    private String bedType;
    private String checkinDate;
    private String expectedCheckout;
    private String status;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // IMPORTANT FOR FIREBASE
    // =====================================================

    public BedBooking() {
    }

    // =====================================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================================

    public BedBooking(
            String number,
            String bookingID,
            String patientName,
            String hospitalName,
            String department,
            String bedNo,
            String bedType,
            String checkinDate,
            String expectedCheckout,
            String status) {

        this.number = number;
        this.bookingID = bookingID;
        this.patientName = patientName;
        this.hospitalName = hospitalName;
        this.department = department;
        this.bedNo = bedNo;
        this.bedType = bedType;
        this.checkinDate = checkinDate;
        this.expectedCheckout = expectedCheckout;
        this.status = status;
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
    // BOOKING ID
    // =====================================================

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    // =====================================================
    // MOTHER UID
    // =====================================================

    public String getMotherUid() {
        return motherUid;
    }

    public void setMotherUid(String motherUid) {
        this.motherUid = motherUid;
    }

    // =====================================================
    // PATIENT NAME
    // =====================================================

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    // =====================================================
    // HOSPITAL NAME
    // =====================================================

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    // =====================================================
    // DEPARTMENT
    // =====================================================

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // =====================================================
    // BED NUMBER
    // =====================================================

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    // =====================================================
    // BED TYPE
    // =====================================================

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    // =====================================================
    // CHECK-IN DATE
    // =====================================================

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    // =====================================================
    // EXPECTED CHECK-OUT
    // =====================================================

    public String getExpectedCheckout() {
        return expectedCheckout;
    }

    public void setExpectedCheckout(String expectedCheckout) {
        this.expectedCheckout = expectedCheckout;
    }

    // =====================================================
    // STATUS
    // =====================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =====================================================
    // TO STRING
    // Useful for debugging
    // =====================================================

    @Override
    public String toString() {
        return "BedBooking{" +
                "number='" + number + '\'' +
                ", bookingID='" + bookingID + '\'' +
                ", motherUid='" + motherUid + '\'' +
                ", patientName='" + patientName + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", department='" + department + '\'' +
                ", bedNo='" + bedNo + '\'' +
                ", bedType='" + bedType + '\'' +
                ", checkinDate='" + checkinDate + '\'' +
                ", expectedCheckout='" + expectedCheckout + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}