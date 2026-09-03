package com.sigma.model.DoctorModel;

public class DoctorDashboardModel {

    private int todayAppointments;
    private int newPatientsThisWeek;
    private int reportsThisWeek;
    private double averageRating;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DoctorDashboardModel(
            int todayAppointments,
            int newPatientsThisWeek,
            int reportsThisWeek,
            double averageRating) {

        this.todayAppointments = todayAppointments;
        this.newPatientsThisWeek = newPatientsThisWeek;
        this.reportsThisWeek = reportsThisWeek;
        this.averageRating = averageRating;
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public int getTodayAppointments() {

        return todayAppointments;
    }

    public int getNewPatientsThisWeek() {

        return newPatientsThisWeek;
    }

    public int getReportsThisWeek() {

        return reportsThisWeek;
    }

    public double getAverageRating() {

        return averageRating;
    }

    // =====================================================
    // SETTERS
    // =====================================================

    public void setTodayAppointments(int todayAppointments) {

        this.todayAppointments = todayAppointments;
    }

    public void setNewPatientsThisWeek(int newPatientsThisWeek) {

        this.newPatientsThisWeek = newPatientsThisWeek;
    }

    public void setReportsThisWeek(int reportsThisWeek) {

        this.reportsThisWeek = reportsThisWeek;
    }

    public void setAverageRating(double averageRating) {

        this.averageRating = averageRating;
    }
}