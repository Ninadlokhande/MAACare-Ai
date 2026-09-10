package com.sigma.dao.doctorDao;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.DoctorModel.PatientReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PatientReportDAO {

        private final Firestore db;

        private static final String COLLECTION = "patientReports";

        public PatientReportDAO() {
                db = FirebaseConfig.getFirestore();
        }

        // =========================================================
        // ADD REPORT
        // =========================================================

        public void addReport(PatientReport report) throws Exception {

                if (report == null) {
                        throw new IllegalArgumentException(
                                        "Report cannot be null.");
                }

                try {

                        String reportId = report.getReportId();

                        if (reportId == null ||
                                        reportId.trim().isEmpty()) {

                                reportId = UUID.randomUUID().toString();

                                report.setReportId(reportId);
                        }

                        DocumentReference documentReference = db.collection(COLLECTION)
                                        .document(reportId);

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "reportId",
                                        safeString(report.getReportId()));

                        data.put(
                                        "motherId",
                                        safeString(report.getMotherId()));

                        data.put(
                                        "reportName",
                                        safeString(report.getReportName()));

                        data.put(
                                        "patientName",
                                        safeString(report.getPatientName()));

                        data.put(
                                        "reportType",
                                        safeString(report.getReportType()));

                        data.put(
                                        "date",
                                        safeString(report.getDate()));

                        data.put(
                                        "status",
                                        safeString(report.getStatus()));

                        data.put(
                                        "action",
                                        safeString(report.getAction()));

                        data.put(
                                        "reportUrl",
                                        safeString(report.getReportUrl()));

                        documentReference
                                        .set(data, SetOptions.merge())
                                        .get();

                        System.out.println(
                                        "[REPORT DAO] Report saved successfully.");

                        System.out.println(
                                        "[REPORT DAO] Report ID = "
                                                        + reportId);

                        System.out.println(
                                        "[REPORT DAO] Mother ID = "
                                                        + report.getMotherId());

                } catch (Exception e) {

                        System.out.println(
                                        "[REPORT DAO ERROR] Unable to add report.");

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // GET ALL REPORTS
        // =========================================================

        public List<PatientReport> getAllReports()
                        throws Exception {

                List<PatientReport> reports = new ArrayList<>();

                try {

                        Query query = db.collection(COLLECTION);

                        QuerySnapshot snapshot = query.get().get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                PatientReport report = documentToModel(document);

                                if (report != null) {
                                        reports.add(report);
                                }
                        }

                        return reports;

                } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        throw e;

                } catch (ExecutionException e) {

                        throw e;
                }
        }

        // =========================================================
        // GET REPORTS BY PATIENT
        // =========================================================

        public List<PatientReport> getReportsByPatient(
                        String patientName) throws Exception {

                List<PatientReport> reports = new ArrayList<>();

                if (patientName == null ||
                                patientName.trim().isEmpty()) {

                        return reports;
                }

                try {

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "patientName",
                                                        patientName.trim())
                                        .get()
                                        .get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                PatientReport report = documentToModel(document);

                                if (report != null) {
                                        reports.add(report);
                                }
                        }

                        return reports;

                } catch (Exception e) {

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // GET REPORTS BY MOTHER ID
        // =========================================================

        public List<PatientReport> getReportsByMotherId(
                        String motherId) throws Exception {

                List<PatientReport> reports = new ArrayList<>();

                if (motherId == null ||
                                motherId.trim().isEmpty()) {

                        return reports;
                }

                QuerySnapshot snapshot = db.collection(COLLECTION)
                                .whereEqualTo(
                                                "motherId",
                                                motherId.trim())
                                .get()
                                .get();

                for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                        PatientReport report = documentToModel(document);

                        if (report != null) {
                                reports.add(report);
                        }
                }

                return reports;
        }

        // =========================================================
        // CONVERT FIRESTORE → MODEL
        // =========================================================

        private PatientReport documentToModel(
                        DocumentSnapshot document) {

                if (document == null ||
                                !document.exists()) {

                        return null;
                }

                try {

                        String reportId = getString(
                                        document,
                                        "reportId");

                        if (reportId.isEmpty()) {

                                reportId = document.getId();
                        }

                        String motherId = getString(
                                        document,
                                        "motherId");

                        String reportName = getString(
                                        document,
                                        "reportName");

                        String patientName = getString(
                                        document,
                                        "patientName");

                        String reportType = getString(
                                        document,
                                        "reportType");

                        String date = getString(
                                        document,
                                        "date");

                        String status = getString(
                                        document,
                                        "status");

                        String action = getString(
                                        document,
                                        "action");

                        String reportUrl = getString(
                                        document,
                                        "reportUrl");

                        if (reportUrl.isEmpty()) {

                                reportUrl = getString(
                                                document,
                                                "fileUrl");
                        }

                        if (reportUrl.isEmpty()) {

                                reportUrl = getString(
                                                document,
                                                "url");
                        }

                        if (action.isEmpty()) {
                                action = "View";
                        }

                        return new PatientReport(
                                        reportId,
                                        motherId,
                                        reportName,
                                        patientName,
                                        reportType,
                                        date,
                                        status,
                                        action,
                                        reportUrl);

                } catch (Exception e) {

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // DELETE REPORT
        // =========================================================

        public void deleteReport(
                        PatientReport report)
                        throws Exception {

                if (report == null) {
                        return;
                }

                try {

                        String reportId = safeString(
                                        report.getReportId());

                        if (!reportId.isEmpty()) {

                                db.collection(COLLECTION)
                                                .document(reportId)
                                                .delete()
                                                .get();

                                System.out.println(
                                                "[REPORT DAO] Deleted: "
                                                                + reportId);

                                return;
                        }

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "reportName",
                                                        safeString(
                                                                        report.getReportName()))
                                        .whereEqualTo(
                                                        "patientName",
                                                        safeString(
                                                                        report.getPatientName()))
                                        .get()
                                        .get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                document.getReference()
                                                .delete()
                                                .get();

                                break;
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // UPDATE STATUS
        // =========================================================

        public void updateReportStatus(
                        PatientReport report,
                        String newStatus)
                        throws Exception {

                if (report == null ||
                                newStatus == null ||
                                newStatus.trim().isEmpty()) {

                        return;
                }

                try {

                        String reportId = safeString(
                                        report.getReportId());

                        if (!reportId.isEmpty()) {

                                Map<String, Object> update = new HashMap<>();

                                update.put(
                                                "status",
                                                newStatus.trim());

                                db.collection(COLLECTION)
                                                .document(reportId)
                                                .set(
                                                                update,
                                                                SetOptions.merge())
                                                .get();

                                return;
                        }

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "reportName",
                                                        safeString(
                                                                        report.getReportName()))
                                        .whereEqualTo(
                                                        "patientName",
                                                        safeString(
                                                                        report.getPatientName()))
                                        .get()
                                        .get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                Map<String, Object> update = new HashMap<>();

                                update.put(
                                                "status",
                                                newStatus.trim());

                                document.getReference()
                                                .set(
                                                                update,
                                                                SetOptions.merge())
                                                .get();

                                break;
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safeString(String value) {

                return value == null ? "" : value;
        }

        private String getString(
                        DocumentSnapshot document,
                        String fieldName) {

                if (document == null ||
                                fieldName == null) {

                        return "";
                }

                Object value = document.get(fieldName);

                return value == null
                                ? ""
                                : String.valueOf(value);
        }
}