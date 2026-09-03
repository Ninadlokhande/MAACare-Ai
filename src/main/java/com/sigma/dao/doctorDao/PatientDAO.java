package com.sigma.dao.doctorDao;

import com.sigma.model.DoctorModel.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientDAO {

    // =====================================================
    // PATIENT DATA
    // =====================================================

    private final ObservableList<Patient> patients;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public PatientDAO() {

        patients = FXCollections.observableArrayList();

        loadPatients();
    }

    // =====================================================
    // LOAD HARD-CODED PATIENTS
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
    // GET ALL PATIENTS
    // =====================================================

    public ObservableList<Patient> getAllPatients() {

        return patients;
    }

    // =====================================================
    // GET PATIENT BY NAME
    // =====================================================

    public Patient getPatientByName(String name) {

        if (name == null) {
            return null;
        }

        for (Patient patient : patients) {

            if (patient.getName()
                    .equalsIgnoreCase(name.trim())) {

                return patient;
            }
        }

        return null;
    }

    // =====================================================
    // ADD PATIENT
    // =====================================================

    public void addPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        patients.add(patient);

        System.out.println(
                "Patient added successfully: "
                        + patient.getName());
    }

    // =====================================================
    // UPDATE PATIENT
    // =====================================================

    public void updatePatient(Patient patient) {

        if (patient == null) {
            return;
        }

        Patient existing = getPatientByName(patient.getName());

        if (existing != null) {

            existing.setAge(patient.getAge());
            existing.setContact(patient.getContact());
            existing.setLastVisit(patient.getLastVisit());
            existing.setNextVisit(patient.getNextVisit());
            existing.setAction(patient.getAction());

            System.out.println(
                    "Patient updated successfully: "
                            + patient.getName());
        }
    }

    // =====================================================
    // DELETE PATIENT
    // =====================================================

    public void deletePatient(Patient patient) {

        if (patient == null) {
            return;
        }

        patients.remove(patient);

        System.out.println(
                "Patient deleted successfully: "
                        + patient.getName());
    }

    // =====================================================
    // SEARCH PATIENTS
    // =====================================================

    public ObservableList<Patient> searchPatients(
            String searchText) {

        ObservableList<Patient> result = FXCollections.observableArrayList();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            result.addAll(patients);

            return result;
        }

        String search = searchText.toLowerCase().trim();

        for (Patient patient : patients) {

            if (patient.getName()
                    .toLowerCase()
                    .contains(search)
                    ||
                    patient.getContact()
                            .contains(search)) {

                result.add(patient);
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY GENDER
    // =====================================================

    public ObservableList<Patient> filterByGender(
            String gender) {

        ObservableList<Patient> result = FXCollections.observableArrayList();

        if (gender == null ||
                gender.equalsIgnoreCase("All")) {

            result.addAll(patients);

            return result;
        }

        for (Patient patient : patients) {

            if (patient.getAge()
                    .toLowerCase()
                    .contains(gender.toLowerCase())) {

                result.add(patient);
            }
        }

        return result;
    }

    // =====================================================
    // CLEAR FILTER
    // =====================================================

    public ObservableList<Patient> clearFilters() {

        return FXCollections.observableArrayList(
                patients);
    }
}