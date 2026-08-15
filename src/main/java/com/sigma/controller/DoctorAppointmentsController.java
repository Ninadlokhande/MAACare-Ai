package com.sigma.controller;

import com.sigma.model.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DoctorAppointmentsController {

    private final ObservableList<Appointment> appointments;

    public DoctorAppointmentsController() {

        appointments = FXCollections.observableArrayList();

        loadSampleAppointments();
    }

    // =====================================================
    // SAMPLE DATA
    // =====================================================

    private void loadSampleAppointments() {

        appointments.add(
                new Appointment(
                        "09:30 AM",
                        "Priya Sharma",
                        "28 Y | 24 Weeks Pregnant",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        "2026-08-14"));

        appointments.add(
                new Appointment(
                        "10:15 AM",
                        "Neha Kulkarni",
                        "32 Y | Routine Checkup",
                        "Routine Checkup",
                        "Confirmed",
                        "Paid",
                        "2026-08-14"));

        appointments.add(
                new Appointment(
                        "11:00 AM",
                        "Sneha Patil",
                        "26 Y | First Consultation",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        "2026-08-14"));

        appointments.add(
                new Appointment(
                        "12:00 PM",
                        "Ritika Singh",
                        "30 Y | Ultrasound Follow-up",
                        "Ultrasound",
                        "Pending",
                        "Pending",
                        "2026-08-14"));
    }

    // =====================================================
    // GET ALL APPOINTMENTS
    // =====================================================

    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    // =====================================================
    // ADD APPOINTMENT
    // =====================================================

    public void addAppointment(
            String time,
            String patient,
            String details,
            String type,
            String status,
            String payment,
            String date) {

        Appointment appointment = new Appointment(
                time,
                patient,
                details,
                type,
                status,
                payment,
                date);

        appointments.add(appointment);
    }

    // =====================================================
    // ADD EXISTING OBJECT
    // =====================================================

    public void addAppointment(
            Appointment appointment) {

        if (appointment != null) {
            appointments.add(appointment);
        }
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public void updateAppointmentStatus(
            Appointment appointment,
            String status) {

        if (appointment != null) {
            appointment.setStatus(status);
        }
    }

    // =====================================================
    // UPDATE PAYMENT
    // =====================================================

    public void updatePayment(
            Appointment appointment,
            String payment) {

        if (appointment != null) {
            appointment.setPayment(payment);
        }
    }

    // =====================================================
    // DELETE APPOINTMENT
    // =====================================================

    public void deleteAppointment(
            Appointment appointment) {

        if (appointment != null) {
            appointments.remove(appointment);
        }
    }
}