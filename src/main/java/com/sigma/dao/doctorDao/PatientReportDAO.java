package com.sigma.dao.doctorDao;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.model.DoctorModel.PatientReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class PatientReportDAO {

        private final Firestore db;

        // Firestore collection name
        private static final String COLLECTION = "patientReports";

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public PatientReportDAO() {
                db = FirebaseConfig.getFirestore();
        }

        // =========================================================
        // ADD REPORT
        // =========================================================

        public void addReport(PatientReport report) throws Exception {

                if (report == null) {
                        throw new IllegalArgumentException("Report cannot be null.");
                }

                try {

                        // If reportId is available, use it.
                        // Otherwise Firestore will generate an ID.
                        String reportId = null;

                        if (report.getReportName() != null
                                        && !report.getReportName().trim().isEmpty()) {

                                reportId = report.getReportName()
                                                .trim()
                                                .replaceAll("[^a-zA-Z0-9_-]", "_");
                        }

                        DocumentReference documentReference;

                        if (reportId != null && !reportId.isEmpty()) {
                                documentReference = db.collection(COLLECTION)
                                                .document(reportId);
                        } else {
                                documentReference = db.collection(COLLECTION).document();
                        }

                        Map<String, Object> data = new HashMap<>();

                        data.put("reportName",
                                        safeString(report.getReportName()));

                        data.put("patientName",
                                        safeString(report.getPatientName()));

                        data.put("reportType",
                                        safeString(report.getReportType()));

                        data.put("date",
                                        safeString(report.getDate()));

                        data.put("status",
                                        safeString(report.getStatus()));

                        data.put("action",
                                        safeString(report.getAction()));

                        // =================================================
                        // IMPORTANT:
                        // Uploaded Cloudinary / Firebase file URL
                        // =================================================

                        String reportUrl = report.getReportUrl();

                        if (reportUrl != null && !reportUrl.trim().isEmpty()) {
                                data.put("reportUrl", reportUrl.trim());
                        }

                        documentReference.set(data, SetOptions.merge()).get();

                        System.out.println(
                                        "[REPORT DAO] Report added successfully.");

                        System.out.println(
                                        "[REPORT DAO] Document ID: "
                                                        + documentReference.getId());

                        System.out.println(
                                        "[REPORT DAO] Report URL: "
                                                        + safeString(report.getReportUrl()));

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

        public List<PatientReport> getAllReports() throws Exception {

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

                        System.out.println(
                                        "[REPORT DAO] Total reports loaded: "
                                                        + reports.size());

                        return reports;

                } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        System.out.println(
                                        "[REPORT DAO ERROR] Thread interrupted while loading reports.");

                        throw e;

                } catch (ExecutionException e) {

                        System.out.println(
                                        "[REPORT DAO ERROR] Firestore error while loading reports.");

                        throw e;
                }
        }

        // =========================================================
        // CONVERT FIRESTORE DOCUMENT → PATIENT REPORT MODEL
        // =========================================================

        private PatientReport documentToModel(DocumentSnapshot document) {

                if (document == null || !document.exists()) {
                        return null;
                }

                try {

                        String reportName = getString(document, "reportName");

                        String patientName = getString(document, "patientName");

                        String reportType = getString(document, "reportType");

                        String date = getString(document, "date");

                        String status = getString(document, "status");

                        String action = getString(document, "action");

                        // =================================================
                        // IMPORTANT:
                        // Load reportUrl from Firestore
                        // =================================================

                        String reportUrl = getString(document, "reportUrl");

                        /*
                         * Backward compatibility:
                         * जर जुन्या documents मध्ये URL वेगळ्या field मध्ये असेल
                         * तर ते सुद्धा check केले जातील.
                         */

                        if (reportUrl.isEmpty()) {

                                reportUrl = getString(document, "fileUrl");
                        }

                        if (reportUrl.isEmpty()) {

                                reportUrl = getString(document, "url");
                        }

                        if (action.isEmpty()) {
                                action = "View";
                        }

                        return new PatientReport(
                                        reportName,
                                        patientName,
                                        reportType,
                                        date,
                                        status,
                                        action,
                                        reportUrl);

                } catch (Exception e) {

                        System.out.println(
                                        "[REPORT DAO ERROR] Unable to convert Firestore document.");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // DELETE REPORT
        // =========================================================

        public void deleteReport(PatientReport report) throws Exception {

                if (report == null) {
                        return;
                }

                try {

                        /*
                         * Since the current PatientReport model does not contain
                         * Firestore document ID, find the matching document first.
                         */

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "reportName",
                                                        safeString(report.getReportName()))
                                        .whereEqualTo(
                                                        "patientName",
                                                        safeString(report.getPatientName()))
                                        .get()
                                        .get();

                        boolean deleted = false;

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                String firestoreUrl = getString(document, "reportUrl");

                                String modelUrl = safeString(report.getReportUrl());

                                /*
                                 * Match URL when available.
                                 * This prevents deleting the wrong report if two reports
                                 * have the same name/patient.
                                 */

                                if (!modelUrl.isEmpty()) {

                                        if (!modelUrl.equals(firestoreUrl)) {
                                                continue;
                                        }
                                }

                                document.getReference().delete().get();

                                System.out.println(
                                                "[REPORT DAO] Report deleted: "
                                                                + document.getId());

                                deleted = true;
                                break;
                        }

                        if (!deleted) {

                                System.out.println(
                                                "[REPORT DAO] Matching report not found.");
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "[REPORT DAO ERROR] Unable to delete report.");

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // GET REPORTS BY PATIENT
        // =========================================================

        public List<PatientReport> getReportsByPatient(
                        String patientName) throws Exception {

                List<PatientReport> reports = new ArrayList<>();

                if (patientName == null || patientName.trim().isEmpty()) {
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

                        System.out.println(
                                        "[REPORT DAO ERROR] Unable to get patient reports.");

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // UPDATE REPORT STATUS
        // =========================================================

        public void updateReportStatus(
                        PatientReport report,
                        String newStatus) throws Exception {

                if (report == null) {
                        return;
                }

                if (newStatus == null || newStatus.trim().isEmpty()) {
                        return;
                }

                try {

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "reportName",
                                                        safeString(report.getReportName()))
                                        .whereEqualTo(
                                                        "patientName",
                                                        safeString(report.getPatientName()))
                                        .get()
                                        .get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                String firestoreUrl = getString(document, "reportUrl");

                                String modelUrl = safeString(report.getReportUrl());

                                if (!modelUrl.isEmpty()
                                                && !modelUrl.equals(firestoreUrl)) {
                                        continue;
                                }

                                Map<String, Object> update = new HashMap<>();

                                update.put(
                                                "status",
                                                newStatus.trim());

                                document.getReference()
                                                .set(update, SetOptions.merge())
                                                .get();

                                System.out.println(
                                                "[REPORT DAO] Status updated to: "
                                                                + newStatus);

                                break;
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "[REPORT DAO ERROR] Unable to update report status.");

                        e.printStackTrace();

                        throw e;
                }
        }

        // =========================================================
        // GET STRING SAFELY
        // =========================================================

        private String getString(
                        DocumentSnapshot document,
                        String fieldName) {

                if (document == null || fieldName == null) {
                        return "";
                }

                Object value = document.get(fieldName);

                if (value == null) {
                        return "";
                }

                return String.valueOf(value);
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safeString(String value) {

                return value == null ? "" : value;
        }
}