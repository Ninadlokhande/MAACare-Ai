package com.sigma.model;

public class Admin {

    private int adminId;
    private String name;
    private String email;
    private String phone;
    private String profileImage;
    private String role;

    // Default constructor
    public Admin() {
    }

    // Constructor
    public Admin(
            int adminId,
            String name,
            String email,
            String phone,
            String profileImage,
            String role) {

        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
        this.role = role;
    }

    // Admin ID
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Profile Image
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    // Role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}