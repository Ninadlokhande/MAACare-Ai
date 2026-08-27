package com.sigma.controller.doctorController;

import com.sigma.model.DoctorModel.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientController {

    private final ObservableList<Patient> patients;

    public PatientController() {

        patients = FXCollections.observableArrayList();

        loadPatients();
    }

    // =====================================================
    // HARD-CODED PATIENT DATA
    // =====================================================

    private void loadPatients() {

        patients.addAll(

                new Patient(
                        "Priya Sharma",
                        "28 Y / Female",
                        "9876543210",
                        "07 May 2024",
                        "14 May 2024",
                        ""),

                new Patient(
                        "Neha Kulkarni",
                        "32 Y / Female",
                        "9765432109",
                        "05 May 2024",
                        "15 May 2024",
                        ""),

                new Patient(
                        "Sneha Patil",
                        "26 Y / Female",
                        "9988776655",
                        "04 May 2024",
                        "18 May 2024",
                        ""),

                new Patient(
                        "Ayesha Khan",
                        "29 Y / Female",
                        "9871234567",
                        "03 May 2024",
                        "17 May 2024",
                        ""),

                new Patient(
                        "Ritika Singh",
                        "30 Y / Female",
                        "9812345670",
                        "01 May 2024",
                        "12 May 2024",
                        ""));
    }

    // =====================================================
    // GET PATIENTS
    // =====================================================

    public ObservableList<Patient> getPatients() {

        return patients;
    }

    // =====================================================
    // ADD PATIENT
    // =====================================================

    public void addPatient(
            String name,
            String age,
            String gender,
            String contact) {

        if (name == null || name.trim().isEmpty()) {
            return;
        }

        String ageGender = age + " Y / " + gender;

        Patient patient = new Patient(
                name,
                ageGender,
                contact,
                "New",
                "Not Scheduled",
                "");

        patients.add(patient);

        System.out.println(
                "Patient Added Successfully: " + name);
    }

    // =====================================================
    // SEARCH
    // =====================================================

    public ObservableList<Patient> searchPatients(
            String searchText) {

        ObservableList<Patient> filtered = FXCollections.observableArrayList();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            filtered.addAll(patients);
            return filtered;
        }

        String search = searchText.toLowerCase().trim();

        for (Patient patient : patients) {

            if (patient.getName()
                    .toLowerCase()
                    .contains(search)
                    ||
                    patient.getContact()
                            .contains(search)) {

                filtered.add(patient);
            }
        }

        return filtered;
    }

    // =====================================================
    // FILTER BY GENDER
    // =====================================================

    public ObservableList<Patient> filterByGender(
            String gender) {

        ObservableList<Patient> filtered = FXCollections.observableArrayList();

        if (gender == null ||
                gender.equals("All")) {

            filtered.addAll(patients);
            return filtered;
        }

        for (Patient patient : patients) {

            if (patient.getAge()
                    .toLowerCase()
                    .contains(gender.toLowerCase())) {

                filtered.add(patient);
            }
        }

        return filtered;
    }

    // =====================================================
    // VIEW PATIENT
    // =====================================================

    public void viewPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        System.out.println(
                "Viewing Patient: " +
                        patient.getName());

        System.out.println(
                "Age/Gender: " +
                        patient.getAge());

        System.out.println(
                "Contact: " +
                        patient.getContact());
    }

    // =====================================================
    // EDIT PATIENT
    // =====================================================

    public void editPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        System.out.println(
                "Editing Patient: " +
                        patient.getName());
    }
}