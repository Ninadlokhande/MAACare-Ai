package com.sigma.controller.doctorController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.model.DoctorModel.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientController {

    private static final String COLLECTION = "patients";

    // Default doctor ID for now
    private static final String DEFAULT_DOCTOR_ID = "D001";

    private final ObservableList<Patient> patients;
    private final ObservableList<Patient> newPatientsThisWeek;
    private final Map<Patient, String> documentIds;

    private final Firestore firestore;

    public PatientController() {

        patients = FXCollections.observableArrayList();
        newPatientsThisWeek = FXCollections.observableArrayList();
        documentIds = new HashMap<>();

        firestore = FirebaseConfig.getFirestore();

        loadPatients();
    }

    // =========================================================
    // LOAD PATIENTS FROM FIRESTORE
    // =========================================================

    public void loadPatients() {

        try {

            if (firestore == null) {
                System.out.println("[PATIENT] Firestore is not initialized.");
                return;
            }

            patients.clear();
            newPatientsThisWeek.clear();
            documentIds.clear();

            List<QueryDocumentSnapshot> documents = firestore.collection(COLLECTION)
                    .get()
                    .get()
                    .getDocuments();

            LocalDate today = LocalDate.now();
            LocalDate sevenDaysAgo = today.minusDays(6);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (QueryDocumentSnapshot doc : documents) {

                String patientId = getString(doc, "patientId");
                String doctorId = getString(doc, "doctorId");

                String name = getString(doc, "name");
                String age = getString(doc, "age");
                String gender = getString(doc, "gender");
                String contact = getString(doc, "contact");

                String lastVisit = getString(doc, "lastVisit");
                String nextVisit = getString(doc, "nextVisit");

                String createdDate = getString(doc, "createdDate");

                if (patientId.isEmpty()) {
                    patientId = doc.getId();
                }

                Patient patient = new Patient(
                        patientId,
                        doctorId,
                        name,
                        age,
                        gender,
                        contact,
                        lastVisit,
                        nextVisit,
                        "",
                        createdDate);

                patients.add(patient);

                documentIds.put(patient, doc.getId());

                // ---------------------------------------------
                // NEW PATIENTS THIS WEEK
                // ---------------------------------------------

                if (!createdDate.isEmpty()) {

                    try {

                        LocalDate created = LocalDate.parse(createdDate, formatter);

                        if (!created.isBefore(sevenDaysAgo)
                                && !created.isAfter(today)) {

                            newPatientsThisWeek.add(patient);
                        }

                    } catch (Exception ignored) {
                        // Invalid date will simply be ignored
                    }
                }
            }

            System.out.println(
                    "[PATIENT] Loaded "
                            + patients.size()
                            + " patients from Firestore.");

            System.out.println(
                    "[PATIENT] New patients this week: "
                            + newPatientsThisWeek.size());

        } catch (Exception e) {

            System.out.println(
                    "[PATIENT ERROR] Unable to load patients.");

            e.printStackTrace();
        }
    }

    // =========================================================
    // FIRESTORE FIELD READER
    // =========================================================

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

    // =========================================================
    // ADD PATIENT
    // =========================================================

    public Patient addPatient(
            String doctorId,
            String name,
            String age,
            String gender,
            String contact,
            String lastVisit,
            String nextVisit) {

        try {

            if (firestore == null) {

                System.out.println(
                        "[PATIENT] Firestore is not initialized.");

                return null;
            }

            // Default doctor ID
            if (doctorId == null
                    || doctorId.trim().isEmpty()) {

                doctorId = DEFAULT_DOCTOR_ID;
            }

            name = name == null ? "" : name.trim();
            age = age == null ? "" : age.trim();
            gender = gender == null ? "" : gender.trim();
            contact = contact == null ? "" : contact.trim();
            lastVisit = lastVisit == null ? "" : lastVisit.trim();
            nextVisit = nextVisit == null ? "" : nextVisit.trim();

            // Required fields
            if (name.isEmpty()
                    || age.isEmpty()
                    || gender.isEmpty()
                    || contact.isEmpty()) {

                System.out.println(
                        "[PATIENT] Required fields are missing.");

                return null;
            }

            // ---------------------------------------------
            // CREATE FIRESTORE DOCUMENT
            // ---------------------------------------------

            DocumentReference docRef = firestore.collection(COLLECTION).document();

            String patientId = docRef.getId();

            String createdDate = LocalDate.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Map<String, Object> data = new HashMap<>();

            data.put("patientId", patientId);
            data.put("doctorId", doctorId);
            data.put("name", name);
            data.put("age", age);
            data.put("gender", gender);
            data.put("contact", contact);
            data.put("lastVisit", lastVisit);
            data.put("nextVisit", nextVisit);
            data.put("createdDate", createdDate);

            // Save to Firestore
            docRef.set(data).get();

            // ---------------------------------------------
            // CREATE LOCAL PATIENT OBJECT
            // ---------------------------------------------

            Patient patient = new Patient(
                    patientId,
                    doctorId,
                    name,
                    age,
                    gender,
                    contact,
                    lastVisit,
                    nextVisit,
                    "",
                    createdDate);

            // Add to observable list
            patients.add(patient);

            documentIds.put(
                    patient,
                    patientId);

            // New patient this week
            newPatientsThisWeek.add(patient);

            System.out.println(
                    "[PATIENT] Patient added successfully: "
                            + name);

            return patient;

        } catch (Exception e) {

            System.out.println(
                    "[PATIENT ERROR] Unable to add patient.");

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // SIMPLE ADD PATIENT METHOD
    // =========================================================
    // This method avoids getDefaultDoctorId() error.
    // AddPatientPage can directly use this method.

    public Patient addPatient(
            String name,
            String age,
            String gender,
            String contact,
            String lastVisit,
            String nextVisit) {

        return addPatient(
                DEFAULT_DOCTOR_ID,
                name,
                age,
                gender,
                contact,
                lastVisit,
                nextVisit);
    }

    // =========================================================
    // OPTIONAL SIMPLE VERSION
    // =========================================================

    public Patient addPatient(
            String name,
            String age,
            String gender,
            String contact) {

        return addPatient(
                DEFAULT_DOCTOR_ID,
                name,
                age,
                gender,
                contact,
                "New",
                "Not Scheduled");
    }

    // =========================================================
    // REFRESH PATIENTS
    // =========================================================

    public void refreshPatients() {

        loadPatients();
    }

    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    public ObservableList<Patient> getPatients() {

        return patients;
    }

    // =========================================================
    // GET NEW PATIENTS THIS WEEK
    // =========================================================

    public ObservableList<Patient> getNewPatientsThisWeek() {

        return newPatientsThisWeek;
    }

    public int getNewPatientsThisWeekCount() {

        return newPatientsThisWeek.size();
    }

    // =========================================================
    // TOTAL PATIENT COUNT
    // =========================================================

    public int getPatientCount() {

        return patients.size();
    }

    // =========================================================
    // SEARCH PATIENTS
    // =========================================================

    public ObservableList<Patient> searchPatients(
            String keyword) {

        ObservableList<Patient> result = FXCollections.observableArrayList();

        if (keyword == null
                || keyword.trim().isEmpty()) {

            result.addAll(patients);
            return result;
        }

        String search = keyword.trim().toLowerCase();

        for (Patient patient : patients) {

            if (patient.getName()
                    .toLowerCase()
                    .contains(search)

                    || patient.getContact()
                            .toLowerCase()
                            .contains(search)

                    || patient.getAge()
                            .toLowerCase()
                            .contains(search)

                    || patient.getGender()
                            .toLowerCase()
                            .contains(search)) {

                result.add(patient);
            }
        }

        return result;
    }

    // =========================================================
    // FILTER BY GENDER
    // =========================================================

    public ObservableList<Patient> filterByGender(
            String gender) {

        ObservableList<Patient> result = FXCollections.observableArrayList();

        if (gender == null
                || gender.equalsIgnoreCase("All")
                || gender.trim().isEmpty()) {

            result.addAll(patients);
            return result;
        }

        for (Patient patient : patients) {

            if (gender.equalsIgnoreCase(
                    patient.getGender())) {

                result.add(patient);
            }
        }

        return result;
    }

    // =========================================================
    // VIEW PATIENT
    // =========================================================

    public void viewPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        System.out.println(
                "[PATIENT] View patient: "
                        + patient.getName());

        // Your existing PatientDetails page
        // can be opened from here.
    }

    // =========================================================
    // EDIT PATIENT
    // =========================================================

    public void editPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        System.out.println(
                "[PATIENT] Edit patient: "
                        + patient.getName());

        // Edit functionality can be connected
        // to Firestore update later.
    }

    // =========================================================
    // DELETE PATIENT
    // =========================================================

    public boolean deletePatient(Patient patient) {

        try {

            if (firestore == null
                    || patient == null) {

                return false;
            }

            String documentId = documentIds.get(patient);

            if (documentId == null
                    || documentId.isEmpty()) {

                documentId = patient.getPatientId();
            }

            if (documentId == null
                    || documentId.isEmpty()) {

                return false;
            }

            // Delete from Firestore
            firestore.collection(COLLECTION)
                    .document(documentId)
                    .delete()
                    .get();

            // Delete from local list
            patients.remove(patient);
            newPatientsThisWeek.remove(patient);
            documentIds.remove(patient);

            System.out.println(
                    "[PATIENT] Patient deleted: "
                            + patient.getName());

            return true;

        } catch (Exception e) {

            System.out.println(
                    "[PATIENT ERROR] Unable to delete patient.");

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DEFAULT DOCTOR ID
    // =========================================================

    public String getDefaultDoctorId() {

        return DEFAULT_DOCTOR_ID;
    }
}