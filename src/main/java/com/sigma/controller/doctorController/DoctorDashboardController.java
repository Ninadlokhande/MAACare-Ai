package com.sigma.controller.doctorController;

public class DoctorDashboardController {

    // =====================================================
    // DASHBOARD DATA
    // =====================================================

    public int getTodayAppointments() {
        return 18;
    }

    public int getNewPatientsThisWeek() {
        return 6;
    }

    public int getReportsThisWeek() {
        return 32;
    }

    public double getAverageRating() {
        return 4.8;
    }

    // =====================================================
    // DASHBOARD ACTIONS
    // =====================================================

    public void addAppointment() {

        System.out.println(
                "Opening Add Appointment...");

        // Later:
        // DoctorAppointmentsPage.show();
    }

    public void addPatient() {

        System.out.println(
                "Opening Add Patient...");

        // Later:
        // PatientsPage.show();
    }

    public void writePrescription() {

        System.out.println(
                "Opening Prescription...");

        // Later:
        // PrescriptionPage.show();
    }

    public void uploadReport() {

        System.out.println(
                "Opening Upload Report...");

        // Later:
        // PatientReportsPage.show();
    }

    public void sendMessage() {

        System.out.println(
                "Opening Messages...");

        // Later:
        // MessagesPage.show();
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    public void logout() {

        System.out.println(
                "Doctor logged out.");

        // Later:
        // LoginPage.show();
    }
}
