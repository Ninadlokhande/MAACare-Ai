package com.sigma.model.DoctorModel;

import java.util.Date;

public class FeedbackModel {

    private String feedbackId;
    private String doctorUid;
    private String patientUid;
    private String patientName;
    private double rating;
    private String comment;
    private Date timestamp;

    public FeedbackModel() {
    }

    public FeedbackModel(
            String feedbackId,
            String doctorUid,
            String patientUid,
            String patientName,
            double rating,
            String comment,
            Date timestamp) {

        this.feedbackId = feedbackId;
        this.doctorUid = doctorUid;
        this.patientUid = patientUid;
        this.patientName = patientName;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getDoctorUid() {
        return doctorUid;
    }

    public void setDoctorUid(String doctorUid) {
        this.doctorUid = doctorUid;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}