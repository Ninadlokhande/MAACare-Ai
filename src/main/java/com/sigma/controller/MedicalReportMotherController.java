package com.sigma.controller;

import com.sigma.dao.MedicalReportDAO;
import com.sigma.model.MedicalReportMother;

import java.util.List;

public class MedicalReportMotherController {

    private final MedicalReportDAO medicalReportDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MedicalReportMotherController() {

        medicalReportDAO =
                new MedicalReportDAO();
    }

    // =========================================================
    // GET ALL REPORTS
    // =========================================================

    public List<MedicalReportMother> getAllReports() {

        return medicalReportDAO
                .getAllReports();
    }

    // =========================================================
    // GET REPORTS BY MOTHER ID
    // =========================================================

    public List<MedicalReportMother> getReportsByMotherId(
            String motherId) {

        return medicalReportDAO
                .getReportsByMotherId(motherId);
    }

    // =========================================================
    // GET REPORT BY ID
    // =========================================================

    public MedicalReportMother getReportById(
            String reportId) {

        return medicalReportDAO
                .getReportById(reportId);
    }

    // =========================================================
    // SAVE REPORT
    // =========================================================

    public boolean saveReport(
            MedicalReportMother report) {

        if (report == null) {
            return false;
        }

        return medicalReportDAO
                .saveReport(report);
    }

    // =========================================================
    // UPDATE REPORT
    // =========================================================

    public boolean updateReport(
            MedicalReportMother report) {

        if (report == null) {
            return false;
        }

        return medicalReportDAO
                .updateReport(report);
    }

    // =========================================================
    // DELETE REPORT
    // =========================================================

    public boolean deleteReport(
            String reportId) {

        return medicalReportDAO
                .deleteReport(reportId);
    }
}