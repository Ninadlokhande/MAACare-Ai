package com.sigma.controller.doctorController;

import java.util.List;

import com.sigma.dao.doctorDao.PatientReportDAO;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientReportController {

    private final PatientReportDAO patientReportDAO;

    private final ObservableList<PatientReport> reports;

    public PatientReportController() {

        patientReportDAO = new PatientReportDAO();

        reports = FXCollections.observableArrayList();

        loadReports();
    }

    // =========================================================
    // LOAD
    // =========================================================

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
                            + " reports.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // GET REPORTS
    // =========================================================

    public ObservableList<PatientReport> getReports() {

        return reports;
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getReportCount() {

        return reports.size();
    }

    // =========================================================
    // ADD
    // =========================================================

    public void addReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        try {

            patientReportDAO.addReport(
                    report);

            reports.add(report);

            System.out.println(
                    "[REPORT] Added successfully.");

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to add patient report.",
                    e);
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deleteReport(
            PatientReport report) {

        if (report == null) {
            return;
        }

        try {

            patientReportDAO.deleteReport(
                    report);

            reports.remove(report);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to delete patient report.",
                    e);
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    public ObservableList<PatientReport> searchReports(
            String searchText) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            filtered.addAll(reports);

            return filtered;
        }

        String search = searchText.trim()
                .toLowerCase();

        for (PatientReport report : reports) {

            String reportName = report.getReportName()
                    .toLowerCase();

            String patientName = report.getPatientName()
                    .toLowerCase();

            String reportType = report.getReportType()
                    .toLowerCase();

            if (reportName.contains(search) ||
                    patientName.contains(search) ||
                    reportType.contains(search)) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =========================================================
    // FILTER STATUS
    // =========================================================

    public ObservableList<PatientReport> filterByStatus(
            String selectedStatus) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (selectedStatus == null ||
                selectedStatus.trim().isEmpty() ||
                selectedStatus.equalsIgnoreCase(
                        "All Status")) {

            filtered.addAll(reports);

            return filtered;
        }

        for (PatientReport report : reports) {

            if (selectedStatus.equalsIgnoreCase(
                    report.getStatus())) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =========================================================
    // SEARCH + STATUS
    // =========================================================

    public ObservableList<PatientReport> filterReports(
            String searchText,
            String selectedStatus) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        String search = searchText == null
                ? ""
                : searchText
                        .trim()
                        .toLowerCase();

        boolean allStatus = selectedStatus == null ||
                selectedStatus.trim().isEmpty() ||
                selectedStatus.equalsIgnoreCase(
                        "All Status");

        for (PatientReport report : reports) {

            boolean matchesSearch = search.isEmpty()
                    || report.getReportName()
                            .toLowerCase()
                            .contains(search)
                    || report.getPatientName()
                            .toLowerCase()
                            .contains(search)
                    || report.getReportType()
                            .toLowerCase()
                            .contains(search);

            boolean matchesStatus = allStatus
                    || selectedStatus.equalsIgnoreCase(
                            report.getStatus());

            if (matchesSearch &&
                    matchesStatus) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =========================================================
    // CLEAR
    // =========================================================

    public ObservableList<PatientReport> clearFilters() {

        return FXCollections.observableArrayList(
                reports);
    }

    // =========================================================
    // REFRESH
    // =========================================================

    public void refreshReports() {

        loadReports();
    }

    // =========================================================
    // COUNT THIS WEEK
    // =========================================================

    public int getReportCountThisWeek() {

        return reports.size();
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    public void updateReportStatus(
            PatientReport report,
            String newStatus) {

        if (report == null ||
                newStatus == null ||
                newStatus.trim().isEmpty()) {

            return;
        }

        try {

            patientReportDAO.updateReportStatus(
                    report,
                    newStatus);

            report.setStatus(
                    newStatus);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to update report status.",
                    e);
        }
    }
}