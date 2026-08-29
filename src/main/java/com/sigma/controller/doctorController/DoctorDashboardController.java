package com.sigma.controller.doctorController;

import com.sigma.dao.doctorDao.DoctorDashboardDAO;
import com.sigma.model.DoctorModel.DoctorDashboardModel;

public class DoctorDashboardController {

    private final DoctorDashboardDAO dashboardDAO;

    public DoctorDashboardController() {
        dashboardDAO = new DoctorDashboardDAO();
    }

    // =====================================================
    // DASHBOARD DATA
    // =====================================================

    public DoctorDashboardModel getDashboardData() {
        return dashboardDAO.getDashboardData();
    }

    // =====================================================
    // QUICK ACTIONS
    // =====================================================

    public void addAppointment() {
        System.out.println("Opening Add Appointment...");
    }

    public void addPatient() {
        System.out.println("Opening Add Patient...");
    }

    public void writePrescription() {
        System.out.println("Prescription button clicked.");
    }

    public void uploadReport() {
        System.out.println("Opening Upload Report...");
    }

    public void sendMessage() {
        System.out.println("Message button clicked.");
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    public void logout() {
        System.out.println("Doctor logged out.");
    }
}