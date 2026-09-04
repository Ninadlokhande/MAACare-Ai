package com.sigma.controller.doctorController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorAppointmentController {

        private static final String COLLECTION = "appointments";
        private static final String DEFAULT_DOCTOR_ID = "D001";

        private final ObservableList<DoctorAppointment> appointments;
        private final Map<DoctorAppointment, String> documentIds;
        private final Firestore firestore;

        // ============================================================
        // CONSTRUCTOR
        // ============================================================

        public DoctorAppointmentController() {

                appointments = FXCollections.observableArrayList();
                documentIds = new HashMap<>();

                firestore = FirebaseConfig.getFirestore();

                loadAppointments();
        }

        // ============================================================
        // LOAD APPOINTMENTS
        // ============================================================

        public void loadAppointments() {

                try {

                        if (firestore == null) {

                                System.out.println(
                                                "[APPOINTMENT] Firestore is not initialized.");

                                return;
                        }

                        appointments.clear();
                        documentIds.clear();

                        List<QueryDocumentSnapshot> documents = firestore.collection(COLLECTION)
                                        .get()
                                        .get()
                                        .getDocuments();

                        for (QueryDocumentSnapshot doc : documents) {

                                String appointmentId = getString(doc, "appointmentId");

                                if (appointmentId.isEmpty()) {
                                        appointmentId = doc.getId();
                                }

                                String doctorId = getString(doc, "doctorId");

                                String patientId = getString(doc, "patientId");

                                String date = getString(doc, "date");

                                String time = getString(doc, "time");

                                String patient = getString(doc, "patient");

                                String type = getString(doc, "type");

                                String status = getString(doc, "status");

                                String payment = getString(doc, "payment");

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

                                // VERY IMPORTANT
                                // Store actual Firestore document ID
                                documentIds.put(
                                                appointment,
                                                doc.getId());
                        }

                        System.out.println(
                                        "[APPOINTMENT] Loaded "
                                                        + appointments.size()
                                                        + " appointments.");

                } catch (Exception e) {

                        System.out.println(
                                        "[APPOINTMENT ERROR] Unable to load appointments.");

                        e.printStackTrace();
                }
        }

        // ============================================================
        // GET STRING SAFELY
        // ============================================================

        private String getString(
                        QueryDocumentSnapshot doc,
                        String field) {

                try {

                        Object value = doc.get(field);

                        return value == null
                                        ? ""
                                        : String.valueOf(value);

                } catch (Exception e) {

                        return "";
                }
        }

        // ============================================================
        // REFRESH
        // ============================================================

        public void refreshAppointments() {

                loadAppointments();
        }

        // ============================================================
        // GET ALL APPOINTMENTS
        // ============================================================

        public ObservableList<DoctorAppointment> getAppointments() {

                return appointments;
        }

        // ============================================================
        // TODAY DATE
        // ============================================================

        public String getTodayDate() {

                return LocalDate.now().toString();
        }

        // ============================================================
        // TODAY APPOINTMENT COUNT
        // ============================================================

        public int getTodayAppointmentCount() {

                String today = LocalDate.now().toString();

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if (today.equals(
                                        appointment.getDate())) {

                                count++;
                        }
                }

                return count;
        }

        // ============================================================
        // TODAY APPOINTMENTS
        // ============================================================

        public ObservableList<DoctorAppointment> getTodayAppointments() {

                String today = LocalDate.now().toString();

                ObservableList<DoctorAppointment> todayAppointments = FXCollections.observableArrayList();

                for (DoctorAppointment appointment : appointments) {

                        if (today.equals(
                                        appointment.getDate())) {

                                todayAppointments.add(
                                                appointment);
                        }
                }

                todayAppointments.sort(
                                (a, b) -> a.getTime()
                                                .compareToIgnoreCase(
                                                                b.getTime()));

                return todayAppointments;
        }

        // ============================================================
        // TOTAL COUNT
        // ============================================================

        public int getAppointmentCount() {

                return appointments.size();
        }

        // ============================================================
        // CONFIRMED COUNT
        // ============================================================

        public int getConfirmedCount() {

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if ("Confirmed".equalsIgnoreCase(
                                        appointment.getStatus())) {

                                count++;
                        }
                }

                return count;
        }

        // ============================================================
        // PENDING COUNT
        // ============================================================

        public int getPendingCount() {

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if ("Pending".equalsIgnoreCase(
                                        appointment.getStatus())) {

                                count++;
                        }
                }

                return count;
        }

        // ============================================================
        // TODAY CONFIRMED COUNT
        // ============================================================

        public int getTodayConfirmedCount() {

                String today = LocalDate.now().toString();

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if (today.equals(
                                        appointment.getDate())
                                        &&
                                        "Confirmed".equalsIgnoreCase(
                                                        appointment.getStatus())) {

                                count++;
                        }
                }

                return count;
        }

        // ============================================================
        // TODAY PENDING COUNT
        // ============================================================

        public int getTodayPendingCount() {

                String today = LocalDate.now().toString();

                int count = 0;

                for (DoctorAppointment appointment : appointments) {

                        if (today.equals(
                                        appointment.getDate())
                                        &&
                                        "Pending".equalsIgnoreCase(
                                                        appointment.getStatus())) {

                                count++;
                        }
                }

                return count;
        }

        // ============================================================
        // ADD APPOINTMENT
        // ============================================================

        public DoctorAppointment addAppointment(
                        String doctorId,
                        String patientId,
                        String date,
                        String time,
                        String patient,
                        String type,
                        String status,
                        String payment) {

                try {

                        if (firestore == null) {

                                System.out.println(
                                                "[APPOINTMENT] Firestore unavailable.");

                                return null;
                        }

                        if (doctorId == null ||
                                        doctorId.trim().isEmpty()) {

                                doctorId = DEFAULT_DOCTOR_ID;
                        }

                        if (patientId == null) {
                                patientId = "";
                        }

                        if (date == null) {
                                date = "";
                        }

                        if (time == null) {
                                time = "";
                        }

                        if (patient == null) {
                                patient = "";
                        }

                        if (type == null ||
                                        type.trim().isEmpty()) {

                                type = "Consultation";
                        }

                        if (status == null ||
                                        status.trim().isEmpty()) {

                                status = "Pending";
                        }

                        if (payment == null ||
                                        payment.trim().isEmpty()) {

                                payment = "Unpaid";
                        }

                        if (patient.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT] Patient name required.");

                                return null;
                        }

                        if (date.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT] Date required.");

                                return null;
                        }

                        if (time.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT] Time required.");

                                return null;
                        }

                        // ----------------------------------------------------
                        // CREATE NEW FIRESTORE DOCUMENT
                        // ----------------------------------------------------

                        DocumentReference docRef = firestore.collection(COLLECTION)
                                        .document();

                        String appointmentId = docRef.getId();

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "appointmentId",
                                        appointmentId);

                        data.put(
                                        "doctorId",
                                        doctorId);

                        data.put(
                                        "patientId",
                                        patientId);

                        data.put(
                                        "date",
                                        date);

                        data.put(
                                        "time",
                                        time);

                        data.put(
                                        "patient",
                                        patient);

                        data.put(
                                        "type",
                                        type);

                        data.put(
                                        "status",
                                        status);

                        data.put(
                                        "payment",
                                        payment);

                        WriteResult result = docRef.set(data).get();

                        // ----------------------------------------------------
                        // CREATE LOCAL OBJECT
                        // ----------------------------------------------------

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

                        appointments.add(
                                        appointment);

                        documentIds.put(
                                        appointment,
                                        appointmentId);

                        System.out.println(
                                        "[APPOINTMENT] Added successfully: "
                                                        + appointmentId);

                        return appointment;

                } catch (Exception e) {

                        System.out.println(
                                        "[APPOINTMENT ERROR] Unable to add.");

                        e.printStackTrace();

                        return null;
                }
        }

        // ============================================================
        // UPDATE APPOINTMENT
        // ============================================================
        // THIS METHOD FIXES THE EDIT ERROR
        // ============================================================

        public boolean updateAppointment(
                        String appointmentId,
                        String patientId,
                        String date,
                        String time,
                        String patient,
                        String type,
                        String status,
                        String payment) {

                try {

                        // ----------------------------------------------------
                        // FIRESTORE CHECK
                        // ----------------------------------------------------

                        if (firestore == null) {

                                System.out.println(
                                                "[APPOINTMENT UPDATE] Firestore unavailable.");

                                return false;
                        }

                        // ----------------------------------------------------
                        // APPOINTMENT ID CHECK
                        // ----------------------------------------------------

                        if (appointmentId == null ||
                                        appointmentId.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT UPDATE] Appointment ID missing.");

                                return false;
                        }

                        // ----------------------------------------------------
                        // VALIDATION
                        // ----------------------------------------------------

                        if (patient == null ||
                                        patient.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT UPDATE] Patient name required.");

                                return false;
                        }

                        if (date == null ||
                                        date.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT UPDATE] Date required.");

                                return false;
                        }

                        if (time == null ||
                                        time.trim().isEmpty()) {

                                System.out.println(
                                                "[APPOINTMENT UPDATE] Time required.");

                                return false;
                        }

                        if (patientId == null) {
                                patientId = "";
                        }

                        if (type == null ||
                                        type.trim().isEmpty()) {

                                type = "Consultation";
                        }

                        if (status == null ||
                                        status.trim().isEmpty()) {

                                status = "Pending";
                        }

                        if (payment == null ||
                                        payment.trim().isEmpty()) {

                                payment = "Unpaid";
                        }

                        // ----------------------------------------------------
                        // FIND FIRESTORE DOCUMENT ID
                        // ----------------------------------------------------

                        String documentId = null;

                        for (Map.Entry<DoctorAppointment, String> entry : documentIds.entrySet()) {

                                DoctorAppointment appointment = entry.getKey();

                                if (appointment != null
                                                &&
                                                appointmentId.equals(
                                                                appointment.getAppointmentId())) {

                                        documentId = entry.getValue();

                                        break;
                                }
                        }

                        // ----------------------------------------------------
                        // FALLBACK
                        // ----------------------------------------------------

                        if (documentId == null ||
                                        documentId.trim().isEmpty()) {

                                documentId = appointmentId;
                        }

                        // ----------------------------------------------------
                        // DEBUG
                        // ----------------------------------------------------

                        System.out.println(
                                        "====================================");

                        System.out.println(
                                        "[APPOINTMENT UPDATE] Updating Firestore");

                        System.out.println(
                                        "Document ID : "
                                                        + documentId);

                        System.out.println(
                                        "Appointment ID : "
                                                        + appointmentId);

                        System.out.println(
                                        "Patient ID : "
                                                        + patientId);

                        System.out.println(
                                        "Patient : "
                                                        + patient);

                        System.out.println(
                                        "Date : "
                                                        + date);

                        System.out.println(
                                        "Time : "
                                                        + time);

                        System.out.println(
                                        "Type : "
                                                        + type);

                        System.out.println(
                                        "Status : "
                                                        + status);

                        System.out.println(
                                        "Payment : "
                                                        + payment);

                        System.out.println(
                                        "====================================");

                        // ----------------------------------------------------
                        // CREATE UPDATED DATA
                        // ----------------------------------------------------

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "patientId",
                                        patientId.trim());

                        data.put(
                                        "patient",
                                        patient.trim());

                        data.put(
                                        "date",
                                        date.trim());

                        data.put(
                                        "time",
                                        time.trim());

                        data.put(
                                        "type",
                                        type.trim());

                        data.put(
                                        "status",
                                        status.trim());

                        data.put(
                                        "payment",
                                        payment.trim());

                        // ----------------------------------------------------
                        // UPDATE FIRESTORE
                        // ----------------------------------------------------

                        firestore.collection(COLLECTION)
                                        .document(documentId)
                                        .update(data)
                                        .get();

                        System.out.println(
                                        "[APPOINTMENT UPDATE] Firestore updated successfully.");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "[APPOINTMENT UPDATE ERROR] Unable to update appointment.");

                        e.printStackTrace();

                        return false;
                }
        }

        // ============================================================
        // DELETE APPOINTMENT
        // ============================================================

        public boolean deleteAppointment(
                        DoctorAppointment appointment) {

                try {

                        if (firestore == null ||
                                        appointment == null) {

                                return false;
                        }

                        String documentId = documentIds.get(
                                        appointment);

                        if (documentId == null ||
                                        documentId.isEmpty()) {

                                documentId = appointment.getAppointmentId();
                        }

                        if (documentId == null ||
                                        documentId.isEmpty()) {

                                return false;
                        }

                        firestore.collection(COLLECTION)
                                        .document(documentId)
                                        .delete()
                                        .get();

                        appointments.remove(
                                        appointment);

                        documentIds.remove(
                                        appointment);

                        System.out.println(
                                        "[APPOINTMENT] Deleted: "
                                                        + documentId);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "[APPOINTMENT ERROR] Unable to delete.");

                        e.printStackTrace();

                        return false;
                }
        }

        // ============================================================
        // SEARCH
        // ============================================================

        public ObservableList<DoctorAppointment> searchAppointments(String keyword) {

                ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

                if (keyword == null ||
                                keyword.trim().isEmpty()) {

                        result.addAll(
                                        appointments);

                        return result;
                }

                String search = keyword.toLowerCase().trim();

                for (DoctorAppointment appointment : appointments) {

                        if (safeLower(
                                        appointment.getPatient())
                                        .contains(search)

                                        ||

                                        safeLower(
                                                        appointment.getPatientId())
                                                        .contains(search)

                                        ||

                                        safeLower(
                                                        appointment.getType())
                                                        .contains(search)

                                        ||

                                        safeLower(
                                                        appointment.getStatus())
                                                        .contains(search)

                                        ||

                                        safeLower(
                                                        appointment.getDate())
                                                        .contains(search)

                                        ||

                                        safeLower(
                                                        appointment.getTime())
                                                        .contains(search)) {

                                result.add(
                                                appointment);
                        }
                }

                return result;
        }

        // ============================================================
        // FILTER STATUS
        // ============================================================

        public ObservableList<DoctorAppointment> filterByStatus(String status) {

                ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

                if (status == null ||
                                status.equalsIgnoreCase("All")) {

                        result.addAll(
                                        appointments);

                        return result;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (status.equalsIgnoreCase(
                                        appointment.getStatus())) {

                                result.add(
                                                appointment);
                        }
                }

                return result;
        }

        // ============================================================
        // FILTER TYPE
        // ============================================================

        public ObservableList<DoctorAppointment> filterByType(String type) {

                ObservableList<DoctorAppointment> result = FXCollections.observableArrayList();

                if (type == null ||
                                type.equalsIgnoreCase("All")) {

                        result.addAll(
                                        appointments);

                        return result;
                }

                for (DoctorAppointment appointment : appointments) {

                        if (type.equalsIgnoreCase(
                                        appointment.getType())) {

                                result.add(
                                                appointment);
                        }
                }

                return result;
        }

        // ============================================================
        // SAFE LOWERCASE
        // ============================================================

        private String safeLower(String value) {

                if (value == null) {
                        return "";
                }

                return value
                                .trim()
                                .toLowerCase();
        }

        // ============================================================
        // DEFAULT DOCTOR ID
        // ============================================================

        public String getDefaultDoctorId() {

                return DEFAULT_DOCTOR_ID;
        }
}