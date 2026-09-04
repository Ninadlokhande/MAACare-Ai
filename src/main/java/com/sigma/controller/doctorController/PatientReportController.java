package com.sigma.controller.doctorController;

import java.util.List;

import com.sigma.dao.doctorDao.PatientReportDAO;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientReportController {

    private final PatientReportDAO patientReportDAO;
    private final ObservableList<PatientReport> reports;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PatientReportController() {

        patientReportDAO = new PatientReportDAO();

        reports = FXCollections.observableArrayList();

        loadReports();
    }

    // =========================================================
    // LOAD REPORTS
    // =========================================================

    private void loadReports() {

        try {

            reports.clear();

            List<PatientReport> loadedReports = patientReportDAO.getAllReports();

            if (loadedReports != null) {
                reports.addAll(loadedReports);
            }

            System.out.println(
                    "[REPORT] Loaded "
                            + reports.size()
                            + " reports from Firestore.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] Unable to load reports.");

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
    // GET REPORT COUNT
    // =========================================================

    public int getReportCount() {
        return reports.size();
    }

    // =========================================================
    // ADD REPORT
    // =========================================================

    public void addReport(PatientReport report) {

        if (report == null) {

            System.out.println(
                    "[REPORT ERROR] Report is null.");

            return;
        }

        try {

            patientReportDAO.addReport(report);

            /*
             * Add to local ObservableList only after
             * Firestore operation is successful.
             */
            reports.add(report);

            System.out.println(
                    "[REPORT] Report added successfully.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] Unable to add report.");

            e.printStackTrace();

            /*
             * IMPORTANT:
             * Do NOT use: throw e;
             *
             * Exception is converted into RuntimeException,
             * so PatientReportsPage can handle it without
             * checked-exception compilation errors.
             */
            throw new RuntimeException(
                    "Unable to add patient report.",
                    e);
        }
    }

    // =========================================================
    // DELETE REPORT
    // =========================================================

    public void deleteReport(PatientReport report) {

        if (report == null) {

            System.out.println(
                    "[REPORT ERROR] Report is null.");

            return;
        }

        try {

            patientReportDAO.deleteReport(report);

            /*
             * Remove from local list only after
             * successful Firestore deletion.
             */
            reports.remove(report);

            System.out.println(
                    "[REPORT] Report deleted successfully.");

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] Unable to delete report.");

            e.printStackTrace();

            /*
             * FIX for "throw e" compilation error.
             */
            throw new RuntimeException(
                    "Unable to delete patient report.",
                    e);
        }
    }

    // =========================================================
    // SEARCH REPORTS
    // =========================================================

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
                    : report.getReportName().toLowerCase();

            String patientName = report.getPatientName() == null
                    ? ""
                    : report.getPatientName().toLowerCase();

            String reportType = report.getReportType() == null
                    ? ""
                    : report.getReportType().toLowerCase();

            if (reportName.contains(search)
                    || patientName.contains(search)
                    || reportType.contains(search)) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =========================================================
    // FILTER BY STATUS
    // =========================================================

    public ObservableList<PatientReport> filterByStatus(
            String selectedStatus) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        if (selectedStatus == null
                || selectedStatus.trim().isEmpty()
                || selectedStatus.equalsIgnoreCase("All Status")) {

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

    // =========================================================
    // SEARCH + STATUS FILTER TOGETHER
    // =========================================================

    public ObservableList<PatientReport> filterReports(
            String searchText,
            String selectedStatus) {

        ObservableList<PatientReport> filtered = FXCollections.observableArrayList();

        String search = searchText == null
                ? ""
                : searchText.trim().toLowerCase();

        boolean allStatus = selectedStatus == null
                || selectedStatus.trim().isEmpty()
                || selectedStatus.equalsIgnoreCase("All Status");

        for (PatientReport report : reports) {

            String reportName = report.getReportName() == null
                    ? ""
                    : report.getReportName().toLowerCase();

            String patientName = report.getPatientName() == null
                    ? ""
                    : report.getPatientName().toLowerCase();

            String reportType = report.getReportType() == null
                    ? ""
                    : report.getReportType().toLowerCase();

            String status = report.getStatus() == null
                    ? ""
                    : report.getStatus();

            boolean matchesSearch = search.isEmpty()
                    || reportName.contains(search)
                    || patientName.contains(search)
                    || reportType.contains(search);

                boolean matchesStatus = allStatus
                    || selectedStatus != null
                        && selectedStatus.equalsIgnoreCase(status);

            if (matchesSearch && matchesStatus) {

                filtered.add(report);
            }
        }

        return filtered;
    }

    // =========================================================
    // CLEAR FILTERS
    // =========================================================

    public ObservableList<PatientReport> clearFilters() {

        return FXCollections.observableArrayList(reports);
    }

    // =========================================================
    // REFRESH REPORTS
    // =========================================================

    public void refreshReports() {

        loadReports();

        System.out.println(
                "[REPORT] Reports refreshed from Firestore.");
    }

    // =========================================================
    // REPORT COUNT THIS WEEK
    // =========================================================

    public int getReportCountThisWeek() {

        /*
         * Current implementation returns total reports.
         * Date-based filtering can be added later if required.
         */
        return reports.size();
    }

    // =========================================================
    // UPDATE REPORT STATUS
    // =========================================================

    public void updateReportStatus(
            PatientReport report,
            String newStatus) {

        if (report == null) {
            return;
        }

        if (newStatus == null
                || newStatus.trim().isEmpty()) {
            return;
        }

        try {

            patientReportDAO.updateReportStatus(
                    report,
                    newStatus);

            /*
             * Update local ObservableList model also.
             */
            report.setStatus(newStatus);

            System.out.println(
                    "[REPORT] Report status updated: "
                            + newStatus);

        } catch (Exception e) {

            System.out.println(
                    "[REPORT ERROR] Unable to update report status.");

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to update patient report status.",
                    e);
        }
    }
}