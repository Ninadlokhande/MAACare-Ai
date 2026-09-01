package com.sigma.model;

import java.util.Date;

public class Notification {

    // =====================================================
    // FIELDS
    // =====================================================

    private String id;
    private String message;
    private String type;
    private boolean read;
    private Date timestamp;


    // =====================================================
    // DEFAULT CONSTRUCTOR
    // Firestore 
    // =====================================================

    public Notification() {
    }


    // =====================================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================================

    public Notification(
            String id,
            String message,
            String type,
            boolean read,
            Date timestamp) {

        this.id = id;
        this.message = message;
        this.type = type;
        this.read = read;
        this.timestamp = timestamp;
    }


    // =====================================================
    // GET ID
    // =====================================================

    public String getId() {
        return id;
    }


    // =====================================================
    // SET ID
    // =====================================================

    public void setId(String id) {
        this.id = id;
    }


    // =====================================================
    // GET MESSAGE
    // =====================================================

    public String getMessage() {
        return message;
    }


    // =====================================================
    // SET MESSAGE
    // =====================================================

    public void setMessage(String message) {
        this.message = message;
    }


    // =====================================================
    // GET TYPE
    // =====================================================

    public String getType() {
        return type;
    }


    // =====================================================
    // SET TYPE
    // =====================================================

    public void setType(String type) {
        this.type = type;
    }


    // =====================================================
    // GET READ
    // =====================================================

    public boolean isRead() {
        return read;
    }


    // =====================================================
    // SET READ
    // =====================================================

    public void setRead(boolean read) {
        this.read = read;
    }


    // =====================================================
    // GET TIMESTAMP
    // =====================================================

    public Date getTimestamp() {
        return timestamp;
    }


    // =====================================================
    // SET TIMESTAMP
    // =====================================================

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}