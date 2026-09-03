package com.sigma.controller.doctorController;

import com.sigma.dao.doctorDao.DoctorDashboardDAO;
import com.sigma.model.DoctorModel.DoctorDashboardModel;

public class DoctorDashboardController {

    private final DoctorDashboardDAO dashboardDAO;

    public DoctorDashboardController() {
        dashboardDAO = new DoctorDashboardDAO();

        System.out.println("[DOCTOR CONTROLLER] Controller initialized");
    }

    // =========================================================
    // GET DASHBOARD DATA
    // =========================================================

    public DoctorDashboardModel getDashboardData() {

        try {

            DoctorDashboardModel data = dashboardDAO.getDashboardData();

            System.out.println(
                    "[DOCTOR CONTROLLER] Dashboard data loaded");

            return data;

        } catch (Exception e) {

            System.out.println(
                    "[DOCTOR CONTROLLER] Failed to load dashboard data");

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // DASHBOARD ACTIONS
    // =========================================================

    public void addAppointment() {

        System.out.println(
                "[DOCTOR] Opening Add Appointment...");
    }

    public void addPatient() {

        System.out.println(
                "[DOCTOR] Opening Add Patient...");
    }

    public void writePrescription() {

        System.out.println(
                "[DOCTOR] Opening Prescription...");
    }

    public void uploadReport() {

        System.out.println(
                "[DOCTOR] Opening Upload Report...");
    }

    public void sendMessage() {

        System.out.println(
                "[DOCTOR] Opening Messages...");
    }

    // =========================================================
    // LOGOUT REMOVED
    // =========================================================
}