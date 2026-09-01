package com.sigma.model;

public class AshaProfileModel {

    private String name;
    private String ashaId;
    private String phoneNumber;
    private String email;
    private String address;
    private String dateOfJoining;
    private String profileImage;
    private boolean active;

    public AshaProfileModel() {
    }

    public AshaProfileModel(
            String name,
            String ashaId,
            String phoneNumber,
            String email,
            String address,
            String dateOfJoining,
            String profileImage,
            boolean active) {

        this.name = name;
        this.ashaId = ashaId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
        this.profileImage = profileImage;
        this.active = active;
    }

    // =========================
    // GETTERS
    // =========================

    public String getName() {
        return name;
    }

    public String getAshaId() {
        return ashaId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

   public String getProfileImage() {
        return profileImage;
    }

    public boolean isActive() {
        return active;
    }

    // =========================
    // SETTERS
    // =========================

    public void setName(String name) {
        this.name = name;
    }

    public void setAshaId(String ashaId) {
        this.ashaId = ashaId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

   public void setProfileImage(String profileImage) {
       this.profileImage = profileImage;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AshaProfileModel{" +
                "name='" + name + '\'' +
                ", ashaId='" + ashaId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", active=" + active +
                '}';
    }

//public String dateOfJoining() {
       // // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'dateOfJoining'");
   // }
}
