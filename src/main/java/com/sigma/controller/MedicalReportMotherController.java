package com.sigma.controller;

import com.google.cloud.firestore.ListenerRegistration;
import com.sigma.dao.MedicalReportDAO;
import com.sigma.model.MedicalReportMother;

import java.util.List;

public class MedicalReportMotherController {

    private final MedicalReportDAO dao;

    public MedicalReportMotherController() {

        dao = new MedicalReportDAO();
    }

    public List<MedicalReportMother> getAllReports() {

        return dao.getAllReports();
    }

    public List<MedicalReportMother> getReportsByMotherId(
            String motherId) {

        return dao.getReportsByMotherId(
                motherId);
    }

    public MedicalReportMother getReportById(
            String reportId) {

        return dao.getReportById(
                reportId);
    }

    public boolean saveReport(
            MedicalReportMother report) {

        return dao.saveReport(report);
    }

    public boolean updateReport(
            MedicalReportMother report) {

        return dao.updateReport(report);
    }

    public boolean deleteReport(
            String reportId) {

        return dao.deleteReport(
                reportId);
    }

    public ListenerRegistration listenReportsByMotherId(
            String motherId,
            MedicalReportDAO.OnReportsChangedListener listener) {

        return dao.listenReportsByMotherId(
                motherId,
                listener);
    }
}