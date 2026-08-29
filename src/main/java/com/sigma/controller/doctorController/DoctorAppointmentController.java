
package com.sigma.controller.doctorController;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.sigma.config.FirestoreService;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorAppointmentController {

        // =====================================================
        // FIRESTORE COLLECTION
        // =====================================================

        private static final String COLLECTION = "appointments";

        // =====================================================
        // DEFAULT DOCTOR ID
        // =====================================================

        private static final String DEFAULT_DOCTOR_ID = "D001";

        // =====================================================
        // LIST
        // =====================================================

        private final ObservableList<DoctorAppointment> appointments;

        // =====================================================
        // FIRESTORE DOCUMENT IDS
        // =====================================================

        private final Map<DoctorAppointment, String> documentIds;

        // =====================================================
        // FIRESTORE SERVICE
        // =====================================================

        private final FirestoreService firestore;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public DoctorAppointmentController() {

                appointments = FXCollections.observableArrayList();

                documentIds = new HashMap<>();

                firestore = new FirestoreService();

                loadAppointments();
        }

        // =====================================================
        // LOAD APPOINTMENTS
        // =====================================================

        private void loadAppointments() {

                appointments.clear();
                documentIds.clear();

                try {

                        List<QueryDocumentSnapshot> documents = firestore.getDocuments(COLLECTION);

                        for (QueryDocumentSnapshot document : documents) {

                                Map<String, Object> data = document.getData();

                                String appointmentId = document.getId();

                                String doctorId = getString(data, "doctorId");

                                String patientId = getString(data, "patientId");

                                String date = getString(data, "date");

                                String time = getString(data, "time");

                                String patient = getString(data, "patient");

                                String type = getString(data, "type");

                                String status = getString(data, "status");

                                String payment = getString(data, "payment");

                                DoctorAppointment appointment = new DoctorAppointment(
                                                appointmentId,
                                                doctorId,
                                                patientId,
                                                date,
                                                time,
                                                patient,
                                                type,
                                                status,
                                                payment,
                                                "View");

                                appointments.add(appointment);

                                documentIds.put(
                                                appointment,
                                                appointmentId);
                        }

                        System.out.println(
                                        "Appointments loaded from Firestore: "
                                                        + appointments.size());

                } catch (Exception e) {

                        System.out.println(
                                        "Failed to load appointments from Firestore.");

                        e.printStackTrace();
                }
        }

        // =====================================================
        // SAFE STRING
        // =====================================================

        private String getString(
                        Map<String, Object> data,
                        String key) {

                Object value = data.get(key);

                if (value == null) {
                        return "";
                }

                return value.toString();
        }

        // =====================================================
        // REFRESH
        // =====================================================

        public void refreshAppointments() {
                loadAppointments();
        }

        // =====================================================
        // GET APPOINTMENTS
        // =====================================================

        public ObservableList<DoctorAppointment> getAppointments() {
                return appointments;
        }

        // =====================================================
        // ADD APPOINTMENT
        // =====================================================

        public DoctorAppointment addAppointment(
                        String doctorId,
                        String patientId,
                        String date,
                        String time,
                        String patient,
                        String type,
                        String status,
                        String payment) {

                if (patient == null ||
                                patient.trim().isEmpty()) {

                        System.out.println(
                                        "Patient name is required.");

                        return null;
                }

                // -------------------------------------------------
                // DEFAULT VALUES
                // -------------------------------------------------

                if (doctorId == null ||
                                doctorId.trim().isEmpty()) {

                        doctorId = DEFAULT_DOCTOR_ID;
                }

                if (status == null ||
                                status.trim().isEmpty()) {

                        status = "Pending";
                }

                if (payment == null ||
                                payment.trim().isEmpty()) {

                        payment = "Pending";
                }

                // -------------------------------------------------
                // FIRESTORE DATA
                // -------------------------------------------------

                Map<String, Object> data = new HashMap<>();

                data.put(
                                "doctorId",
                                doctorId);

                data.put(
                                "patientId",
                                patientId == null ? "" : patientId);

                data.put(
                                "date",
                                date == null ? "" : date);

                data.put(
                                "time",
                                time == null ? "" : time);

                data.put(
                                "patient",
                                patient.trim());

                data.put(
                                "type",
                                type == null ? "" : type);

                data.put(
                                "status",
                                status);

                data.put(
                                "payment",
                                payment);

                // -------------------------------------------------
                // SAVE
                // -------------------------------------------------

                String documentId = firestore.addDocument(
                                COLLECTION,
                                data);

                if (documentId == null) {

                        System.out.println(
                                        "Appointment could not be saved.");

                        return null;
                }

                // -------------------------------------------------
                // CREATE MODEL
                // -------------------------------------------------

                DoctorAppointment appointment = new DoctorAppointment(
                                documentId,
                                doctorId,
                                patientId,
                                date,
                                time,
                                patient.trim(),
                                type,
                                status,
                                payment,
                                "View");

                appointments.add(appointment);

                documentIds.put(
                                appointment,
                                documentId);

                System.out.println(
                                "Appointment added successfully.");

                System.out.println(
                                "Document ID: " + documentId);

                return appointment;
        }

        // =====================================================
        // BACKWARD COMPATIBLE ADD METHOD
        // =====================================================

        public void addAppointment(
                        String time,
                        String patient,
                        String type,
                        String status,
                        String payment) {

                addAppointment(
                                DEFAULT_DOCTOR_ID,
                                "",
                                "",
                                time,
                                patient,
                                type,
                                status,
                                payment);
        }

        // =====================================================
        // UPDATE APPOINTMENT
        // =====================================================

        public boolean updateAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return false;
                }

                String documentId = documentIds.get(appointment);

                if (documentId == null ||
                                documentId.trim().isEmpty()) {

                        documentId = appointment.getAppointmentId();
                }

                if (documentId == null ||
                                documentId.trim().isEmpty()) {

                        System.out.println(
                                        "Appointment document ID not found.");

                        return false;
                }

                Map<String, Object> data = new HashMap<>();

                data.put(
                                "doctorId",
                                appointment.getDoctorId());

                data.put(
                                "patientId",
                                appointment.getPatientId());

                data.put(
                                "date",
                                appointment.getDate());

                data.put(
                                "time",
                                appointment.getTime());

                data.put(
                                "patient",
                                appointment.getPatient());

                data.put(
                                "type",
                                appointment.getType());

                data.put(
                                "status",
                                appointment.getStatus());

                data.put(
                                "payment",
                                appointment.getPayment());

                boolean updated = firestore.updateDocument(
                                COLLECTION,
                                documentId,
                                data);

                if (updated) {

                        System.out.println(
                                        "Appointment updated successfully.");

                        return true;
                }

                System.out.println(
                                "Appointment update failed.");

                return false;
        }

        // =====================================================
        // VIEW APPOINTMENT
        // =====================================================
        public void editAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return;
                }

                System.out.println(
                                "Editing Appointment: "
                                                + appointment.getPatient());

                System.out.println(
                                "Document ID: "
                                                + getDocumentId(appointment));

                // Current values can be modified through setters
                // and then saved using updateAppointment().
                updateAppointment(appointment);
        }

        public void viewAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return;
                }

                System.out.println(
                                "========================================");

                System.out.println(
                                "VIEW APPOINTMENT");

                System.out.println(
                                "========================================");

                System.out.println(
                                "Document ID: "
                                                + getDocumentId(appointment));

                System.out.println(
                                "Doctor ID: "
                                                + appointment.getDoctorId());

                System.out.println(
                                "Patient ID: "
                                                + appointment.getPatientId());

                System.out.println(
                                "Date: "
                                                + appointment.getDate());

                System.out.println(
                                "Time: "
                                                + appointment.getTime());

                System.out.println(
                                "Patient: "
                                                + appointment.getPatient());

                System.out.println(
                                "Type: "
                                                + appointment.getType());

                System.out.println(
                                "Status: "
                                                + appointment.getStatus());

                System.out.println(
                                "Payment: "
                                                + appointment.getPayment());

                System.out.println(
                                "========================================");
        }

        // =====================================================
        // GET DOCUMENT ID
        // =====================================================

        public String getDocumentId(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return null;
                }

                String id = documentIds.get(appointment);

                if (id == null ||
                                id.isEmpty()) {

                        id = appointment.getAppointmentId();
                }

                return id;
        }

        // =====================================================
        // DELETE
        // =====================================================

        public boolean deleteAppointment(
                        DoctorAppointment appointment) {

                if (appointment == null) {
                        return false;
                }

                String documentId = getDocumentId(appointment);

                if (documentId == null ||
                                documentId.isEmpty()) {

                        System.out.println(
                                        "Firestore document ID not found.");

                        return false;
                }

                boolean deleted = firestore.deleteDocument(
                                COLLECTION,
                                documentId);

                if (deleted) {

                        appointments.remove(
                                        appointment);

                        documentIds.remove(
                                        appointment);

                        System.out.println(
                                        "Appointment deleted successfully.");

                        return true;
                }

                System.out.println(
                                "Appointment delete failed.");

                return false;
        }

        // =====================================================
        // SEARCH
        // =====================================================

        public ObservableList<DoctorAppointment> searchAppointments(
                        String searchText) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (searchText == null ||
                                searchText.trim().isEmpty()) {

                        filtered.addAll(
                                        appointments);

                        return filtered;
                }

                String search = searchText
                                .toLowerCase()
                                .trim();

                for (DoctorAppointment appointment : appointments) {

                        if (contains(
                                        appointment.getPatient(),
                                        search)

                                        || contains(
                                                        appointment.getType(),
                                                        search)

                                        || contains(
                                                        appointment.getStatus(),
                                                        search)

                                        || contains(
                                                        appointment.getTime(),
                                                        search)

                                        || contains(
                                                        appointment.getPayment(),
                                                        search)

                                        || contains(
                                                        appointment.getDate(),
                                                        search)) {

                                filtered.add(
                                                appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // FILTER STATUS
        // =====================================================

        public ObservableList<DoctorAppointment> filterByStatus(
                        String status) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (status == null ||
                                status.equalsIgnoreCase("All Status") ||
                                status.equalsIgnoreCase("All")) {

                        filtered.addAll(
                                        appointments);

                        return filtered;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (appointment.getStatus()
                                        .equalsIgnoreCase(status)) {

                                filtered.add(
                                                appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // FILTER TYPE
        // =====================================================

        public ObservableList<DoctorAppointment> filterByType(
                        String type) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (type == null ||
                                type.equalsIgnoreCase("All Appointment Types") ||
                                type.equalsIgnoreCase("All")) {

                        filtered.addAll(
                                        appointments);

                        return filtered;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (appointment.getType()
                                        .equalsIgnoreCase(type)) {

                                filtered.add(
                                                appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // FILTER DATE
        // =====================================================

        public ObservableList<DoctorAppointment> filterByDate(
                        String selectedDate) {

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                if (selectedDate == null ||
                                selectedDate.trim().isEmpty()) {

                        filtered.addAll(
                                        appointments);

                        return filtered;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (appointment.getDate()
                                        .equals(selectedDate)) {

                                filtered.add(
                                                appointment);
                        }
                }

                return filtered;
        }

        // =====================================================
        // HELPER
        // =====================================================

        private boolean contains(
                        String value,
                        String search) {

                return value != null
                                && value
                                                .toLowerCase()
                                                .contains(search);
        }

        // =====================================================
        // COUNTS
        // =====================================================

        public int getAppointmentCount() {
                return appointments.size();
        }

        public int getConfirmedCount() {

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if (appointment.getStatus()
                                        .equalsIgnoreCase("Confirmed")) {

                                count++;
                        }
                }

                return count;
        }

        public int getPendingCount() {

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if (appointment.getStatus()
                                        .equalsIgnoreCase("Pending")) {

                                count++;
                        }
                }

                return count;
        }

        // =====================================================
        // DEMO DATA
        // =====================================================

        public void loadDemoAppointmentsToFirestore() {

                addAppointment(
                                "D001",
                                "P001",
                                "2026-08-29",
                                "09:30 AM",
                                "Priya Sharma",
                                "Consultation",
                                "Confirmed",
                                "Paid");

                addAppointment(
                                "D001",
                                "P002",
                                "2026-08-29",
                                "10:15 AM",
                                "Neha Kulkarni",
                                "Routine Checkup",
                                "Confirmed",
                                "Paid");

                addAppointment(
                                "D001",
                                "P003",
                                "2026-08-29",
                                "11:00 AM",
                                "Sneha Patil",
                                "First Consultation",
                                "Confirmed",
                                "Pending");

                addAppointment(
                                "D001",
                                "P004",
                                "2026-08-29",
                                "12:00 PM",
                                "Ritika Singh",
                                "Ultrasound Follow-up",
                                "Pending",
                                "Paid");

                addAppointment(
                                "D001",
                                "P001",
                                "2026-08-30",
                                "01:00 PM",
                                "Priya Sharma",
                                "Pregnancy Checkup",
                                "Confirmed",
                                "Paid");

                System.out.println(
                                "Demo appointments added.");
        }
}
