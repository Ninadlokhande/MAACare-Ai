package com.sigma.dao.doctorDao;

import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DoctorAppointmentDAO {

    // =====================================================
    // APPOINTMENT DATA
    // =====================================================

    private final ObservableList<DoctorAppointment> appointments;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DoctorAppointmentDAO() {

        appointments = FXCollections.observableArrayList();

        loadAppointments();
    }

    // =====================================================
    // LOAD HARD-CODED APPOINTMENTS
    // =====================================================

    private void loadAppointments() {

        appointments.addAll(

                new DoctorAppointment(
                        "09:30 AM",
                        "Priya Sharma",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        ""),

                new DoctorAppointment(
                        "10:15 AM",
                        "Neha Kulkarni",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        ""),

                new DoctorAppointment(
                        "11:00 AM",
                        "Sneha Patil",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        ""),

                new DoctorAppointment(
                        "12:00 PM",
                        "Ritika Singh",
                        "Follow-up",
                        "Pending",
                        "Pending",
                        ""),

                new DoctorAppointment(
                        "02:00 PM",
                        "Ayesha Khan",
                        "Consultation",
                        "Confirmed",
                        "Paid",
                        ""),

                new DoctorAppointment(
                        "03:00 PM",
                        "Pooja Iyer",
                        "Consultation",
                        "Cancelled",
                        "Refunded",
                        ""));
    }

    // =====================================================
    // GET ALL APPOINTMENTS
    // =====================================================

    public ObservableList<DoctorAppointment> getAllAppointments() {

        return appointments;
    }

    // =====================================================
    // GET APPOINTMENT BY PATIENT
    // =====================================================

    public DoctorAppointment getAppointmentByPatient(
            String patientName) {

        if (patientName == null) {
            return null;
        }

        for (DoctorAppointment appointment : appointments) {

            if (appointment.getPatient()
                    .equalsIgnoreCase(
                            patientName.trim())) {

                return appointment;
            }
        }

        return null;
    }

    // =====================================================
    // ADD APPOINTMENT
    // =====================================================

    public void addAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return;
        }

        appointments.add(appointment);

        System.out.println(
                "Appointment added successfully.");
    }

    // =====================================================
    // UPDATE APPOINTMENT
    // =====================================================

    public void updateAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return;
        }

        DoctorAppointment existing = getAppointmentByPatient(
                appointment.getPatient());

        if (existing != null) {

            existing.setTime(
                    appointment.getTime());

            existing.setType(
                    appointment.getType());

            existing.setStatus(
                    appointment.getStatus());

            existing.setPayment(
                    appointment.getPayment());

            existing.setAction(
                    appointment.getAction());

            System.out.println(
                    "Appointment updated successfully.");
        }
    }

    // =====================================================
    // DELETE APPOINTMENT
    // =====================================================

    public void deleteAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return;
        }

        appointments.remove(appointment);

        System.out.println(
                "Appointment deleted successfully.");
    }

    // =====================================================
    // SEARCH APPOINTMENTS
    // =====================================================

    public ObservableList<DoctorAppointment> searchAppointments(
            String searchText) {

        ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            result.addAll(appointments);

            return result;
        }

        String search = searchText.toLowerCase().trim();

        for (DoctorAppointment appointment : appointments) {

            if (appointment.getPatient()
                    .toLowerCase()
                    .contains(search)) {

                result.add(appointment);
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public ObservableList<DoctorAppointment> filterByStatus(
            String status) {

        ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

        if (status == null ||
                status.equals("All Status")) {

            result.addAll(appointments);

            return result;
        }

        for (DoctorAppointment appointment : appointments) {

            if (appointment.getStatus()
                    .equalsIgnoreCase(status)) {

                result.add(appointment);
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY TYPE
    // =====================================================

    public ObservableList<DoctorAppointment> filterByType(
            String type) {

        ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

        if (type == null ||
                type.equals("All Appointment Types")) {

            result.addAll(appointments);

            return result;
        }

        for (DoctorAppointment appointment : appointments) {

            if (appointment.getType()
                    .equalsIgnoreCase(type)) {

                result.add(appointment);
            }
        }

        return result;
    }

    // =====================================================
    // CLEAR FILTERS
    // =====================================================

    public ObservableList<DoctorAppointment> clearFilters() {

        return FXCollections.observableArrayList(
                appointments);
    }
}