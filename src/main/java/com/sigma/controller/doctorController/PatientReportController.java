package com.sigma.controller.doctorController;

import com.sigma.dao.doctorDao.PatientReportDAO;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientReportController {

    // =====================================================
    // DAO
    // =====================================================

    private final PatientReportDAO patientReportDAO;

    // =====================================================
    // REPORT LIST
    // =====================================================

    private final ObservableList<PatientReport> reports;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public PatientReportController() {

        patientReportDAO = new PatientReportDAO();

        reports = FXCollections.observableArrayList();

        loadReports();
    }

    // =====================================================
    // LOAD REPORTS
    // =====================================================

    private void loadReports() {

        reports.clear();

        reports.addAll(
                patientReportDAO.getAllReports());
    }

    // =====================================================
    // GET ALL REPORTS
    // =====================================================

    public ObservableList<PatientReport> getReports() {

        return reports;
    }

    // =====================================================
    // ADD REPORT
    // =====================================================

    public void addReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        // Add to current list
        reports.add(report);

        // Send to DAO
        patientReportDAO.addReport(report);
    }

    // =====================================================
    // DELETE REPORT
    // =====================================================

    public void deleteReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        reports.remove(report);

        patientReportDAO.deleteReport(report);
    }

    // =====================================================
    // SEARCH REPORTS
    // =====================================================

    public ObservableList<PatientReport> searchReports(
            String searchText) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            filtered.addAll(reports);

            return filtered;
        }

        String search = searchText
                .trim()
                .toLowerCase();

        for (PatientReport report : reports) {

            String reportName = report.getReportName() == null
                    ? ""
                    : report.getReportName()
                            .toLowerCase();

            String patientName = report.getPatientName() == null
                    ? ""
                    : report.getPatientName()
                            .toLowerCase();

            String reportType = report.getReportType() == null
                    ? ""
                    : report.getReportType()
                            .toLowerCase();

            if (reportName.contains(search)
                    || patientName.contains(search)
                    || reportType.contains(search)) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public ObservableList<PatientReport> filterByStatus(
            String selectedStatus) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (selectedStatus == null
                || selectedStatus.equals("All Status")) {

            filtered.addAll(reports);

            return filtered;
        }

        for (PatientReport report : reports) {

            if (selectedStatus.equals(
                    report.getStatus())) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =====================================================
    // CLEAR FILTERS
    // =====================================================

    public ObservableList<PatientReport> clearFilters() {

        return FXCollections.observableArrayList(
                reports);
    }

    // =====================================================
    // REFRESH REPORTS
    // =====================================================

    public void refreshReports() {

        loadReports();
    }
}
