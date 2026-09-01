package com.sigma.model;


public class HospitalAdminProfile{

    private boolean appointmentNotification;
private boolean labNotification;
private boolean emergencyNotification;
private boolean bedNotification;
private String profileImageUrl;


    String hospitalName;
String phoneNumber;
String hospitalType;
String address;
String hospitalEmail; 
    String adminName;
    String role;
    String email;
    String contactNumber;
    String MemberSince;
    boolean verified;  
String establishedYear;
   

String lastLoginDate;
String lastLoginTime;
//String loginIp;
//String loginLocation;
String activeSession;
String sessionDuration;
String accountStatus;
String systemStatus; 

public HospitalAdminProfile(){ 
    
}

public HospitalAdminProfile(
    String hospitalName,
String phoneNumber,
String hospitalType,
String address,
String hospitalEmail,
    String adminName,
    String role ,
    String email,
    String contactNumber,
    String MemberSince,
    boolean verified, 
String establishedYear,
   

String lastLoginDate,
String lastLoginTime,
//String loginIp,
//String loginLocation,
String activeSession,
String sessionDuration,
String accountStatus,
String systemStatus

 ){ 
this.hospitalName=hospitalName;
this.phoneNumber=phoneNumber;
this.hospitalType=hospitalType;
this.address=address;
this.hospitalEmail=hospitalEmail;
this.adminName=adminName;
this.role=role;
this.email=email;
this.contactNumber=contactNumber;
this.MemberSince=MemberSince;
this.verified=verified;
this.establishedYear=establishedYear;
this.lastLoginDate=lastLoginDate; 
this.lastLoginTime=lastLoginTime;
//this.loginIp=loginIp;
//this.loginLocation=loginLocation;
this.activeSession=activeSession;
this.sessionDuration=sessionDuration;
this.accountStatus=accountStatus;
this.systemStatus=systemStatus;
}

public String getHospitalName() {
    return hospitalName;
}

public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getHospitalType() {
    return hospitalType;
}

public void setHospitalType(String hospitalType) {
    this.hospitalType = hospitalType;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public String getHospitalEmail() {
    return hospitalEmail;
}

public void setHospitalEmail(String hospitalEmail) {
    this.hospitalEmail = hospitalEmail;
}

public String getAdminName() {
    return adminName;
}

public void setAdminName(String adminName) {
    this.adminName = adminName;
}

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getContactNumber() {
    return contactNumber;
}

public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
}

public String getMemberSince() {
    return MemberSince;
}

public void setMemberSince(String memberSince) {
    MemberSince = memberSince;
}

public boolean isVerified() {
    return verified;
}

public void setVerified(boolean verified) {
    this.verified = verified;
}

public String getEstablishedYear() {
    return establishedYear;
}

public void setEstablishedYear(String establishedYear) {
    this.establishedYear = establishedYear;
}

public String getLastLoginDate() {
    return lastLoginDate;
}

public void setLastLoginDate(String lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
}

public String getLastLoginTime() {
    return lastLoginTime;
}

public void setLastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
}

/*public String getLoginIp() {
    return loginIp;
}*/

/*public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
}*/

/*public String getLoginLocation() {
    return loginLocation;
}*/

/*public void setLoginLocation(String loginLocation) {
    this.loginLocation = loginLocation;
}*/

public String getActiveSession() {
    return activeSession;
}

public void setActiveSession(String activeSession) {
    this.activeSession = activeSession;
}

public String getSessionDuration() {
    return sessionDuration;
}

public void setSessionDuration(String sessionDuration) {
    this.sessionDuration = sessionDuration;
}

public String getAccountStatus() {
    return accountStatus;
}

public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
}

public String getSystemStatus() {
    return systemStatus;
}

public void setSystemStatus(String systemStatus) {
    this.systemStatus = systemStatus;
}    
public boolean isAppointmentNotification() {
    return appointmentNotification;
}

public void setAppointmentNotification(boolean appointmentNotification) {
    this.appointmentNotification = appointmentNotification;
}

public boolean isLabNotification() {
    return labNotification;
}

public void setLabNotification(boolean labNotification) {
    this.labNotification = labNotification;
}

public boolean isEmergencyNotification() {
    return emergencyNotification;
}

public void setEmergencyNotification(boolean emergencyNotification) {
    this.emergencyNotification = emergencyNotification;
}

public boolean isBedNotification() {
    return bedNotification;
}

public void setBedNotification(boolean bedNotification) {
    this.bedNotification = bedNotification;
}


public String getProfileImageUrl() {
    return profileImageUrl;
}

public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
}


}
