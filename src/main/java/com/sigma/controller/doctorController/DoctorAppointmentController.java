package com.sigma.controller.doctorController;

import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DoctorAppointmentController {

        // =====================================================
        // ALL APPOINTMENTS
        // =====================================================

        private final ObservableList<DoctorAppointment> appointments;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public DoctorAppointmentController() {

                appointments = FXCollections.observableArrayList();

                loadAppointments();
        }

        // =====================================================
        // LOAD APPOINTMENTS
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
        // GET APPOINTMENTS
        // =====================================================

        public ObservableList<DoctorAppointment> getAppointments() {

                return appointments;
        }

        // =====================================================
        // VIEW APPOINTMENT
        // =====================================================

        public void viewAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return;
                }

                System.out.println(
                                "Viewing Appointment");

                System.out.println(
                                "Patient: " +
                                                appointment.patientProperty().get());

                System.out.println(
                                "Time: " +
                                                appointment.timeProperty().get());

                System.out.println(
                                "Type: " +
                                                appointment.typeProperty().get());

                System.out.println(
                                "Status: " +
                                                appointment.statusProperty().get());
        }

        // =====================================================
        // EDIT APPOINTMENT
        // =====================================================

        public void editAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return;
                }

                System.out.println(
                                "Editing Appointment");

                System.out.println(
                                "Patient: " +
                                                appointment.patientProperty().get());
        }

        // =====================================================
        // SEARCH
        // =====================================================

        public ObservableList<DoctorAppointment> searchAppointments(
                        String searchText) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (searchText == null ||
                                searchText.trim().isEmpty()) {

                        filtered.addAll(appointments);

                        return filtered;
                }

                String search = searchText.toLowerCase().trim();

                for (DoctorAppointment appointment : appointments) {

                        String patient = appointment
                                        .patientProperty()
                                        .get()
                                        .toLowerCase();

                        if (patient.contains(search)) {

                                filtered.add(appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // FILTER BY STATUS
        // =====================================================

        public ObservableList<DoctorAppointment> filterByStatus(
                        String selectedStatus) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (selectedStatus == null ||
                                selectedStatus.equals("All Status")) {

                        filtered.addAll(appointments);

                        return filtered;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (appointment
                                        .statusProperty()
                                        .get()
                                        .equals(selectedStatus)) {

                                filtered.add(appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // FILTER BY TYPE
        // =====================================================

        public ObservableList<DoctorAppointment> filterByType(
                        String selectedType) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (selectedType == null ||
                                selectedType.equals("All Appointment Types")) {

                        filtered.addAll(appointments);

                        return filtered;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (appointment
                                        .typeProperty()
                                        .get()
                                        .equals(selectedType)) {

                                filtered.add(appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // CLEAR FILTER
        // =====================================================

        public ObservableList<DoctorAppointment> clearFilters() {

                return FXCollections.observableArrayList(
                                appointments);
        }
}