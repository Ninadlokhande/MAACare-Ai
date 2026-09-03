package com.sigma.controller;

import com.sigma.dao.MedicineReminderDAO;
import com.sigma.model.MedicineReminderModel;

import java.util.List;

public class MedicineReminderController {

    private final MedicineReminderDAO medicineReminderDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MedicineReminderController() {

        medicineReminderDAO =
                new MedicineReminderDAO();
    }


    // =========================================================
    // GET ALL MEDICINES
    // =========================================================

    public List<MedicineReminderModel> getAllMedicines() {

        return medicineReminderDAO
                .getAllMedicines();
    }


    // =========================================================
    // GET MEDICINES BY MOTHER ID
    // =========================================================

    public List<MedicineReminderModel>
    getMedicinesByMotherId(String motherId) {

        return medicineReminderDAO
                .getMedicinesByMotherId(motherId);
    }


    // =========================================================
    // GET MEDICINE BY ID
    // =========================================================

    public MedicineReminderModel getMedicineById(
            String medicineId) {

        return medicineReminderDAO
                .getMedicineById(medicineId);
    }


    // =========================================================
    // GET MEDICINE BY NAME
    // =========================================================

    public MedicineReminderModel getMedicineByName(
            String medicineName) {

        return medicineReminderDAO
                .getMedicineByName(medicineName);
    }


    // =========================================================
    // SAVE MEDICINE
    // =========================================================

    public boolean saveMedicine(
            MedicineReminderModel medicine) {

        if (medicine == null) {
            return false;
        }

        return medicineReminderDAO
                .saveMedicine(medicine);
    }


    // =========================================================
    // UPDATE MEDICINE
    // =========================================================

    public boolean updateMedicine(
            MedicineReminderModel medicine) {

        if (medicine == null) {
            return false;
        }

        return medicineReminderDAO
                .updateMedicine(medicine);
    }


    // =========================================================
    // DELETE MEDICINE
    // =========================================================

    public boolean deleteMedicine(
            String medicineId) {

        return medicineReminderDAO
                .deleteMedicine(medicineId);
    }


    // =========================================================
    // MARK AS TAKEN
    // =========================================================

    public boolean markAsTaken(
            String medicineId) {

        return medicineReminderDAO
                .markAsTaken(medicineId);
    }


    // =========================================================
    // MARK AS NOT TAKEN
    // =========================================================

    public boolean markAsNotTaken(
            String medicineId) {

        return medicineReminderDAO
                .markAsNotTaken(medicineId);
    }
}