package com.sigma.controller.doctorController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorAppointmentController {

    // =========================================================
    // FIREBASE
    // =========================================================

    private static final String COLLECTION = "appointments";

    /*
     * Current Doctor ID
     *
     * For now Doctor account = D001
     */
    private static final String DEFAULT_DOCTOR_ID = "D001";

    private final ObservableList<DoctorAppointment> appointments;

    /*
     * JavaFX Appointment Object -> Firestore Document ID
     */
    private final Map<DoctorAppointment, String> documentIds;

    private final Firestore firestore;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DoctorAppointmentController() {

        appointments = FXCollections.observableArrayList();

        documentIds = new HashMap<>();

        firestore = FirebaseConfig.getFirestore();

        loadAppointments();
    }

    // =========================================================
    // LOAD DOCTOR APPOINTMENTS
    // =========================================================

    public void loadAppointments() {

        appointments.clear();
        documentIds.clear();

        try {

            List<QueryDocumentSnapshot> documents =
                    firestore
                            .collection(COLLECTION)
                            .whereEqualTo(
                                    "doctorId",
                                    DEFAULT_DOCTOR_ID
                            )
                            .get()
                            .get()
                            .getDocuments();

            for (QueryDocumentSnapshot doc : documents) {

                DoctorAppointment appointment =
                        createAppointmentFromDocument(doc);

                if (appointment != null) {

                    appointments.add(appointment);

                    documentIds.put(
                            appointment,
                            doc.getId()
                    );
                }
            }

            System.out.println(
                    "[FIREBASE] Doctor appointments loaded: "
                            + appointments.size()
            );

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error loading doctor appointments"
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // CREATE MODEL FROM FIRESTORE DOCUMENT
    // =========================================================

    private DoctorAppointment createAppointmentFromDocument(
            QueryDocumentSnapshot doc) {

        try {

            String appointmentId =
                    getString(doc, "appointmentId");

            String doctorId =
                    getString(doc, "doctorId");

            String patientId =
                    getString(doc, "patientId");

            String date =
                    getString(doc, "date");

            String time =
                    getString(doc, "time");

            String patient =
                    getString(doc, "patient");

            String type =
                    getString(doc, "type");

            String status =
                    getString(doc, "status");

            String payment =
                    getString(doc, "payment");

            if (appointmentId == null ||
                    appointmentId.trim().isEmpty()) {

                appointmentId = doc.getId();
            }

            return new DoctorAppointment(
                    appointmentId,
                    doctorId,
                    patientId,
                    date,
                    time,
                    patient,
                    type,
                    status,
                    payment,
                    ""
            );

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error converting appointment document"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // SAFE STRING READER
    // =========================================================

    private String getString(
            QueryDocumentSnapshot doc,
            String field) {

        try {

            String value =
                    doc.getString(field);

            return value == null
                    ? ""
                    : value;

        } catch (Exception e) {

            return "";
        }
    }

    // =========================================================
    // ADD APPOINTMENT
    // =========================================================

    /*
     * Mother -> Book Appointment
     *
     * Default status = Pending
     */

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

            // =====================================================
            // DEFAULT DOCTOR
            // =====================================================

            if (doctorId == null ||
                    doctorId.trim().isEmpty()) {

                doctorId =
                        DEFAULT_DOCTOR_ID;
            }

            // =====================================================
            // DEFAULT VALUES
            // =====================================================

            if (patientId == null) {
                patientId = "";
            }

            if (date == null) {
                date = "";
            }

            if (time == null) {
                time = "";
            }

            if (patient == null ||
                    patient.trim().isEmpty()) {

                patient =
                        "Unknown Patient";
            }

            if (type == null ||
                    type.trim().isEmpty()) {

                type =
                        "Consultation";
            }

            if (status == null ||
                    status.trim().isEmpty()) {

                status =
                        "Pending";
            }

            if (payment == null ||
                    payment.trim().isEmpty()) {

                payment =
                        "Unpaid";
            }

            // =====================================================
            // GENERATE APPOINTMENT ID
            // =====================================================

            String appointmentId =
                    "APT-" + System.currentTimeMillis();

            // =====================================================
            // FIRESTORE DATA
            // =====================================================

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "appointmentId",
                    appointmentId
            );

            data.put(
                    "doctorId",
                    doctorId
            );

            data.put(
                    "patientId",
                    patientId
            );

            data.put(
                    "date",
                    date
            );

            data.put(
                    "time",
                    time
            );

            data.put(
                    "patient",
                    patient
            );

            data.put(
                    "type",
                    type
            );

            data.put(
                    "status",
                    status
            );

            data.put(
                    "payment",
                    payment
            );

            // =====================================================
            // SAVE TO FIRESTORE
            // =====================================================

            DocumentReference documentReference =
                    firestore
                            .collection(COLLECTION)
                            .document();

            documentReference
                    .set(data)
                    .get();

            // =====================================================
            // CREATE LOCAL MODEL
            // =====================================================

            DoctorAppointment appointment =
                    new DoctorAppointment(
                            appointmentId,
                            doctorId,
                            patientId,
                            date,
                            time,
                            patient,
                            type,
                            status,
                            payment,
                            ""
                    );

            // =====================================================
            // ADD TO DOCTOR LIST
            // =====================================================

            if (DEFAULT_DOCTOR_ID.equals(doctorId)) {

                appointments.add(
                        0,
                        appointment
                );

                documentIds.put(
                        appointment,
                        documentReference.getId()
                );
            }

            System.out.println(
                    "[FIREBASE] Appointment added successfully: "
                            + appointmentId
            );

            System.out.println(
                    "[FIREBASE] Doctor ID: "
                            + doctorId
            );

            System.out.println(
                    "[FIREBASE] Patient ID: "
                            + patientId
            );

            System.out.println(
                    "[FIREBASE] Status: "
                            + status
            );

            return appointment;

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error adding appointment"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    public boolean updateAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return false;
        }

        try {

            String documentId =
                    documentIds.get(appointment);

            if (documentId == null ||
                    documentId.trim().isEmpty()) {

                documentId =
                        findDocumentIdByAppointmentId(
                                appointment.getAppointmentId()
                        );
            }

            if (documentId == null ||
                    documentId.trim().isEmpty()) {

                return false;
            }

            Map<String, Object> updates =
                    new HashMap<>();

            updates.put(
                    "patientId",
                    appointment.getPatientId()
            );

            updates.put(
                    "patient",
                    appointment.getPatient()
            );

            updates.put(
                    "date",
                    appointment.getDate()
            );

            updates.put(
                    "time",
                    appointment.getTime()
            );

            updates.put(
                    "type",
                    appointment.getType()
            );

            updates.put(
                    "status",
                    appointment.getStatus()
            );

            updates.put(
                    "payment",
                    appointment.getPayment()
            );

            firestore
                    .collection(COLLECTION)
                    .document(documentId)
                    .update(updates)
                    .get();

            System.out.println(
                    "[FIREBASE] Appointment updated: "
                            + appointment.getAppointmentId()
            );

            return true;

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error updating appointment"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE APPOINTMENT USING ID
    // =========================================================

    public boolean updateAppointment(
            String appointmentId,
            String patientId,
            String date,
            String time,
            String patient,
            String type,
            String status,
            String payment) {

        if (appointmentId == null ||
                appointmentId.trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> updates =
                    new HashMap<>();

            updates.put(
                    "patientId",
                    patientId == null ? "" : patientId
            );

            updates.put(
                    "patient",
                    patient == null ? "" : patient
            );

            updates.put(
                    "date",
                    date == null ? "" : date
            );

            updates.put(
                    "time",
                    time == null ? "" : time
            );

            updates.put(
                    "type",
                    type == null ? "" : type
            );

            updates.put(
                    "status",
                    status == null ? "" : status
            );

            updates.put(
                    "payment",
                    payment == null ? "" : payment
            );

            String documentId =
                    findDocumentIdByAppointmentId(
                            appointmentId
                    );

            if (documentId == null) {

                System.err.println(
                        "[FIREBASE] Appointment not found: "
                                + appointmentId
                );

                return false;
            }

            firestore
                    .collection(COLLECTION)
                    .document(documentId)
                    .update(updates)
                    .get();

            // =====================================================
            // UPDATE LOCAL MODEL
            // =====================================================

            for (DoctorAppointment appointment :
                    appointments) {

                if (appointmentId.equals(
                        appointment.getAppointmentId())) {

                    appointment.setPatientId(
                            patientId
                    );

                    appointment.setPatient(
                            patient
                    );

                    appointment.setDate(
                            date
                    );

                    appointment.setTime(
                            time
                    );

                    appointment.setType(
                            type
                    );

                    appointment.setStatus(
                            status
                    );

                    appointment.setPayment(
                            payment
                    );

                    break;
                }
            }

            System.out.println(
                    "[FIREBASE] Appointment updated: "
                            + appointmentId
            );

            return true;

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error updating appointment"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    public boolean updateAppointmentStatus(
            String appointmentId,
            String status) {

        if (appointmentId == null ||
                appointmentId.trim().isEmpty()) {

            return false;
        }

        if (status == null ||
                status.trim().isEmpty()) {

            return false;
        }

        try {

            String documentId =
                    findDocumentIdByAppointmentId(
                            appointmentId
                    );

            if (documentId == null) {

                System.err.println(
                        "[FIREBASE] Appointment not found: "
                                + appointmentId
                );

                return false;
            }

            firestore
                    .collection(COLLECTION)
                    .document(documentId)
                    .update(
                            "status",
                            status
                    )
                    .get();

            // =====================================================
            // UPDATE LOCAL MODEL
            // =====================================================

            for (DoctorAppointment appointment :
                    appointments) {

                if (appointmentId.equals(
                        appointment.getAppointmentId())) {

                    appointment.setStatus(
                            status
                    );

                    break;
                }
            }

            System.out.println(
                    "[FIREBASE] Appointment status updated: "
                            + appointmentId
                            + " -> "
                            + status
            );

            return true;

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error updating appointment status"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CONFIRM APPOINTMENT
    // =========================================================

    public boolean confirmAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return false;
        }

        return updateAppointmentStatus(
                appointment.getAppointmentId(),
                "Confirmed"
        );
    }

    // =========================================================
    // CANCEL / REJECT APPOINTMENT
    // =========================================================

    public boolean cancelAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return false;
        }

        return updateAppointmentStatus(
                appointment.getAppointmentId(),
                "Cancelled"
        );
    }

    // =========================================================
    // DELETE APPOINTMENT
    // =========================================================

    public boolean deleteAppointment(
            DoctorAppointment appointment) {

        if (appointment == null) {
            return false;
        }

        try {

            String documentId =
                    documentIds.get(appointment);

            if (documentId == null ||
                    documentId.trim().isEmpty()) {

                documentId =
                        findDocumentIdByAppointmentId(
                                appointment.getAppointmentId()
                        );
            }

            if (documentId == null ||
                    documentId.trim().isEmpty()) {

                return false;
            }

            firestore
                    .collection(COLLECTION)
                    .document(documentId)
                    .delete()
                    .get();

            appointments.remove(
                    appointment
            );

            documentIds.remove(
                    appointment
            );

            System.out.println(
                    "[FIREBASE] Appointment deleted: "
                            + appointment.getAppointmentId()
            );

            return true;

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error deleting appointment"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public ObservableList<DoctorAppointment>
    getAppointments() {

        return appointments;
    }

    // =========================================================
    // TODAY'S APPOINTMENTS
    // =========================================================

    /*
     * DoctorDashboard uses:
     *
     * appointmentController.getTodayAppointments();
     *
     * Firestore date format:
     *
     * yyyy-MM-dd
     *
     * Example:
     * 2026-09-07
     */

    public ObservableList<DoctorAppointment>
    getTodayAppointments() {

        ObservableList<DoctorAppointment>
                todayAppointments =
                FXCollections.observableArrayList();

        String today =
                LocalDate.now().toString();

        for (DoctorAppointment appointment :
                appointments) {

            if (appointment == null) {
                continue;
            }

            String appointmentDate =
                    appointment.getDate();

            if (appointmentDate == null ||
                    appointmentDate.trim().isEmpty()) {

                continue;
            }

            if (today.equals(
                    appointmentDate.trim())) {

                todayAppointments.add(
                        appointment
                );
            }
        }

        return todayAppointments;
    }

    // =========================================================
    // TODAY'S APPOINTMENT COUNT
    // =========================================================

    /*
     * DoctorDashboard uses:
     *
     * appointmentController.getTodayAppointmentCount();
     */

    public int getTodayAppointmentCount() {

        return getTodayAppointments().size();
    }

    // =========================================================
    // REFRESH
    // =========================================================

    public void refreshAppointments() {

        loadAppointments();
    }

    // =========================================================
    // SEARCH
    // =========================================================

    public ObservableList<DoctorAppointment>
    searchAppointments(
            String keyword) {

        ObservableList<DoctorAppointment>
                result =
                FXCollections.observableArrayList();

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            result.addAll(
                    appointments
            );

            return result;
        }

        String search =
                keyword
                        .toLowerCase()
                        .trim();

        for (DoctorAppointment appointment :
                appointments) {

            if (appointment == null) {
                continue;
            }

            String patient =
                    safeLower(
                            appointment.getPatient()
                    );

            String patientId =
                    safeLower(
                            appointment.getPatientId()
                    );

            String appointmentId =
                    safeLower(
                            appointment.getAppointmentId()
                    );

            String date =
                    safeLower(
                            appointment.getDate()
                    );

            String type =
                    safeLower(
                            appointment.getType()
                    );

            String status =
                    safeLower(
                            appointment.getStatus()
                    );

            String time =
                    safeLower(
                            appointment.getTime()
                    );

            if (
                    patient.contains(search)
                            ||
                    patientId.contains(search)
                            ||
                    appointmentId.contains(search)
                            ||
                    date.contains(search)
                            ||
                    time.contains(search)
                            ||
                    type.contains(search)
                            ||
                    status.contains(search)
            ) {

                result.add(
                        appointment
                );
            }
        }

        return result;
    }

    // =========================================================
    // FILTER BY STATUS
    // =========================================================

    public ObservableList<DoctorAppointment>
    filterByStatus(
            String status) {

        ObservableList<DoctorAppointment>
                result =
                FXCollections.observableArrayList();

        if (status == null ||
                status.trim().isEmpty() ||
                status.equalsIgnoreCase("All")) {

            result.addAll(
                    appointments
            );

            return result;
        }

        for (DoctorAppointment appointment :
                appointments) {

            if (appointment == null) {
                continue;
            }

            if (status.equalsIgnoreCase(
                    appointment.getStatus())) {

                result.add(
                        appointment
                );
            }
        }

        return result;
    }

    // =========================================================
    // PENDING
    // =========================================================

    public ObservableList<DoctorAppointment>
    getPendingAppointments() {

        return filterByStatus(
                "Pending"
        );
    }

    // =========================================================
    // CONFIRMED
    // =========================================================

    public ObservableList<DoctorAppointment>
    getConfirmedAppointments() {

        return filterByStatus(
                "Confirmed"
        );
    }

    // =========================================================
    // CANCELLED
    // =========================================================

    public ObservableList<DoctorAppointment>
    getCancelledAppointments() {

        return filterByStatus(
                "Cancelled"
        );
    }

    // =========================================================
    // COMPLETED
    // =========================================================

    public ObservableList<DoctorAppointment>
    getCompletedAppointments() {

        return filterByStatus(
                "Completed"
        );
    }

    // =========================================================
    // GET APPOINTMENTS FOR MOTHER / PATIENT
    // =========================================================

    /*
     * Mother side can use:
     *
     * controller.getAppointmentsForPatient(motherUid);
     *
     * Same Firestore collection is used.
     */

    public ObservableList<DoctorAppointment>
    getAppointmentsForPatient(
            String patientId) {

        ObservableList<DoctorAppointment>
                result =
                FXCollections.observableArrayList();

        if (patientId == null ||
                patientId.trim().isEmpty()) {

            return result;
        }

        try {

            List<QueryDocumentSnapshot> documents =
                    firestore
                            .collection(COLLECTION)
                            .whereEqualTo(
                                    "patientId",
                                    patientId
                            )
                            .get()
                            .get()
                            .getDocuments();

            for (QueryDocumentSnapshot doc :
                    documents) {

                DoctorAppointment appointment =
                        createAppointmentFromDocument(
                                doc
                        );

                if (appointment != null) {

                    result.add(
                            appointment
                    );
                }
            }

            System.out.println(
                    "[FIREBASE] Patient appointments loaded: "
                            + result.size()
            );

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error loading patient appointments"
            );

            e.printStackTrace();
        }

        return result;
    }

    // =========================================================
    // GET SINGLE APPOINTMENT BY ID
    // =========================================================

    public DoctorAppointment getAppointmentById(
            String appointmentId) {

        if (appointmentId == null ||
                appointmentId.trim().isEmpty()) {

            return null;
        }

        for (DoctorAppointment appointment :
                appointments) {

            if (appointmentId.equals(
                    appointment.getAppointmentId())) {

                return appointment;
            }
        }

        return null;
    }

    // =========================================================
    // FIND FIRESTORE DOCUMENT ID
    // =========================================================

    /*
     * appointmentId and Firestore document ID
     * are different.
     *
     * Example:
     *
     * Firestore document ID:
     * X7aBc123...
     *
     * appointmentId:
     * APT-175...
     */

    private String findDocumentIdByAppointmentId(
            String appointmentId) {

        if (appointmentId == null ||
                appointmentId.trim().isEmpty()) {

            return null;
        }

        try {

            List<QueryDocumentSnapshot> documents =
                    firestore
                            .collection(COLLECTION)
                            .whereEqualTo(
                                    "appointmentId",
                                    appointmentId
                            )
                            .get()
                            .get()
                            .getDocuments();

            if (documents.isEmpty()) {
                return null;
            }

            return documents.get(0).getId();

        } catch (Exception e) {

            System.err.println(
                    "[FIREBASE ERROR] Error finding appointment document"
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // DEFAULT DOCTOR ID
    // =========================================================

    public String getDefaultDoctorId() {

        return DEFAULT_DOCTOR_ID;
    }

    // =========================================================
    // SAFE LOWER
    // =========================================================

    private String safeLower(
            String value) {

        if (value == null) {
            return "";
        }

        return value
                .trim()
                .toLowerCase();
    }
}