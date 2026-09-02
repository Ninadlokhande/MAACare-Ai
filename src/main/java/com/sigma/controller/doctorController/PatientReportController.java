package com.sigma.controller.doctorController;

import java.util.List;

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
    // LOAD REPORTS FROM FIRESTORE
    // =====================================================

    private void loadReports() {

        try {

            reports.clear();

            List<PatientReport> loadedReports = patientReportDAO.getAllReports();

            if (loadedReports != null) {

                reports.addAll(
                        loadedReports);
            }

            System.out.println(
                    "[REPORT] Loaded "
                            + reports.size()
                            + " reports from Firestore.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] "
                            + "Unable to load reports.");

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ALL REPORTS
    // =====================================================

    public ObservableList<PatientReport> getReports() {

        return reports;
    }

    // =====================================================
    // GET REPORT COUNT
    // =====================================================

    public int getReportCount() {

        return reports.size();
    }

    // =====================================================
    // ADD REPORT
    // =====================================================

    public void addReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        try {

            // First save to Firestore
            patientReportDAO.addReport(
                    report);

            // Then add to local list
            reports.add(report);

            System.out.println(
                    "[REPORT] Report added successfully.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] "
                            + "Unable to add report.");

            e.printStackTrace();

            throw e;
        }
    }

    // =====================================================
    // DELETE REPORT
    // =====================================================

    public void deleteReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        try {

            // Delete from Firestore
            patientReportDAO.deleteReport(
                    report);

            // Delete from local list
            reports.remove(report);

            System.out.println(
                    "[REPORT] Report deleted successfully.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] "
                            + "Unable to delete report.");

            e.printStackTrace();

            throw e;
        }
    }

    // =====================================================
    // SEARCH REPORTS
    // =====================================================

    public ObservableList<PatientReport> searchReports(
            String searchText) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (searchText == null
                || searchText.trim().isEmpty()) {

            filtered.addAll(reports);

            return filtered;
        }

        String search = searchText.trim().toLowerCase();

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
                || selectedStatus.equalsIgnoreCase(
                        "All Status")) {

            filtered.addAll(reports);

            return filtered;
        }

        for (PatientReport report : reports) {

            String status = report.getStatus() == null
                    ? ""
                    : report.getStatus();

            if (selectedStatus.equalsIgnoreCase(status)) {

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

        System.out.println(
                "[REPORT] Reports refreshed from Firestore.");
    }

    // =====================================================
    // REPORT COUNT THIS WEEK
    // =====================================================

    public int getReportCountThisWeek() {

        return reports.size();
    }
}