package com.sigma.dao.doctorDao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.model.DoctorModel.PatientReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientReportDAO {

        private static final String COLLECTION = "patientReports";

        private final Firestore firestore;

        public PatientReportDAO() {
                firestore = FirebaseConfig.getFirestore();
        }

        // =====================================================
        // GET ALL REPORTS FROM FIRESTORE
        // =====================================================

        public List<PatientReport> getAllReports() {

                List<PatientReport> reports = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = firestore
                                        .collection(COLLECTION)
                                        .get()
                                        .get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                String reportName = getString(document, "reportName");

                                String patientName = getString(document, "patientName");

                                String reportType = getString(document, "reportType");

                                String date = getString(document, "date");

                                String status = getString(document, "status");

                                String action = getString(document, "action");

                                PatientReport report = new PatientReport(
                                                reportName,
                                                patientName,
                                                reportType,
                                                date,
                                                status,
                                                action);

                                reports.add(report);
                        }

                        System.out.println(
                                        "[FIRESTORE REPORT] Loaded "
                                                        + reports.size()
                                                        + " reports.");

                } catch (Exception e) {

                        System.out.println(
                                        "[FIRESTORE REPORT ERROR] "
                                                        + "Unable to load reports.");

                        e.printStackTrace();
                }

                return reports;
        }

        // =====================================================
        // ADD REPORT TO FIRESTORE
        // =====================================================

        public void addReport(PatientReport report) {

                if (report == null) {
                        return;
                }

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "reportName",
                                        report.getReportName());

                        data.put(
                                        "patientName",
                                        report.getPatientName());

                        data.put(
                                        "reportType",
                                        report.getReportType());

                        data.put(
                                        "date",
                                        report.getDate());

                        data.put(
                                        "status",
                                        report.getStatus());

                        data.put(
                                        "action",
                                        report.getAction());

                        firestore
                                        .collection(COLLECTION)
                                        .add(data)
                                        .get();

                        System.out.println(
                                        "[FIRESTORE REPORT] "
                                                        + "Report saved successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "[FIRESTORE REPORT ERROR] "
                                                        + "Unable to save report.");

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to save report to Firestore.",
                                        e);
                }
        }

        // =====================================================
        // DELETE REPORT
        // =====================================================

        public void deleteReport(PatientReport report) {

                if (report == null) {
                        return;
                }

                try {

                        QuerySnapshot snapshot = firestore
                                        .collection(COLLECTION)
                                        .get()
                                        .get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                String reportName = getString(document, "reportName");

                                String patientName = getString(document, "patientName");

                                String reportType = getString(document, "reportType");

                                String date = getString(document, "date");

                                String status = getString(document, "status");

                                String action = getString(document, "action");

                                boolean sameReport = safeEquals(
                                                reportName,
                                                report.getReportName())
                                                &&
                                                safeEquals(
                                                                patientName,
                                                                report.getPatientName())
                                                &&
                                                safeEquals(
                                                                reportType,
                                                                report.getReportType())
                                                &&
                                                safeEquals(
                                                                date,
                                                                report.getDate())
                                                &&
                                                safeEquals(
                                                                status,
                                                                report.getStatus())
                                                &&
                                                safeEquals(
                                                                action,
                                                                report.getAction());

                                if (sameReport) {

                                        firestore
                                                        .collection(COLLECTION)
                                                        .document(document.getId())
                                                        .delete()
                                                        .get();

                                        System.out.println(
                                                        "[FIRESTORE REPORT] "
                                                                        + "Report deleted successfully.");

                                        return;
                                }
                        }

                        System.out.println(
                                        "[FIRESTORE REPORT] "
                                                        + "Report not found.");

                } catch (Exception e) {

                        System.out.println(
                                        "[FIRESTORE REPORT ERROR] "
                                                        + "Unable to delete report.");

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to delete report from Firestore.",
                                        e);
                }
        }

        // =====================================================
        // HELPER
        // =====================================================

        private String getString(
                        DocumentSnapshot document,
                        String field) {

                String value = document.getString(field);

                return value == null ? "" : value;
        }

        private boolean safeEquals(
                        String first,
                        String second) {

                if (first == null) {
                        first = "";
                }

                if (second == null) {
                        second = "";
                }

                return first.equals(second);
        }
}