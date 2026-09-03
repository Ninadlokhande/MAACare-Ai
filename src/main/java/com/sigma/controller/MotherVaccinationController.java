package com.sigma.controller;

import com.sigma.dao.MotherVaccinationDAO;
import com.sigma.model.MotherVaccinationmodel;

import java.util.List;

public class MotherVaccinationController {

    private final MotherVaccinationDAO vaccinationDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherVaccinationController() {

        vaccinationDAO =
                new MotherVaccinationDAO();
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<MotherVaccinationmodel>
    getAllVaccinations() {

        return vaccinationDAO
                .getAllVaccinations();
    }

    // =========================================================
    // GET BY MOTHER ID
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByMotherId(
            String motherId) {

        return vaccinationDAO
                .getVaccinationsByMotherId(
                        motherId
                );
    }

    // =========================================================
    // GET BY ID
    // =========================================================

    public MotherVaccinationmodel
    getVaccinationById(
            String id) {

        return vaccinationDAO
                .getVaccinationById(id);
    }

    // =========================================================
    // GET BY PERSON
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByPerson(
            String motherId,
            String person) {

        return vaccinationDAO
                .getVaccinationsByPerson(
                        motherId,
                        person
                );
    }

    // =========================================================
    // GET BY STATUS
    // =========================================================

    public List<MotherVaccinationmodel>
    getVaccinationsByStatus(
            String motherId,
            String status) {

        return vaccinationDAO
                .getVaccinationsByStatus(
                        motherId,
                        status
                );
    }

    // =========================================================
    // SAVE
    // =========================================================

    public boolean saveVaccination(
            MotherVaccinationmodel vaccination) {

        if (vaccination == null) {
            return false;
        }

        return vaccinationDAO
                .saveVaccination(
                        vaccination
                );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateVaccination(
            MotherVaccinationmodel vaccination) {

        if (vaccination == null) {
            return false;
        }

        return vaccinationDAO
                .updateVaccination(
                        vaccination
                );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteVaccination(
            String id) {

        return vaccinationDAO
                .deleteVaccination(id);
    }

    // =========================================================
    // MARK AS COMPLETED
    // =========================================================

    public boolean markAsCompleted(
            String id,
            String completedDate) {

        return vaccinationDAO
                .markAsCompleted(
                        id,
                        completedDate
                );
    }
}