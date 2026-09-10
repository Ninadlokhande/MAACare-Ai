package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

import com.sigma.model.MedicalReportMother;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalReportDAO {

    private final Firestore db;

    // =====================================================
    // IMPORTANT
    // Doctor Reports are stored in "patientReports"
    // Mother Reports must read from the same collection.
    // =====================================================

    private static final String COLLECTION =
            "patientReports";

    public MedicalReportDAO() {

        db = com.google.firebase.cloud.FirestoreClient
                .getFirestore();

        System.out.println(
                "MedicalReportDAO connected to Firebase");
    }

    // =====================================================
    // GET ALL REPORTS
    // =====================================================

    public List<MedicalReportMother> getAllReports() {

        List<MedicalReportMother> reports =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION).get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicalReportMother report =
                        documentToReport(document);

                if (report != null) {

                    reports.add(report);
                }
            }

            System.out.println(
                    "[MOTHER REPORTS] Total reports = "
                            + reports.size());

        } catch (Exception e) {

            System.out.println(
                    "[MOTHER REPORTS ERROR] Unable to load all reports.");

            e.printStackTrace();
        }

        return reports;
    }

    // =====================================================
    // GET REPORTS BY MOTHER ID
    // =====================================================

    public List<MedicalReportMother> getReportsByMotherId(
            String motherId) {

        List<MedicalReportMother> reports =
                new ArrayList<>();

        if (motherId == null ||
                motherId.trim().isEmpty()) {

            System.out.println(
                    "[MOTHER REPORTS] Mother ID is empty.");

            return reports;
        }

        String uid =
                motherId.trim();

        try {

            System.out.println(
                    "[MOTHER REPORTS] Searching reports for motherId = "
                            + uid);

            QuerySnapshot snapshot =
                    db.collection(COLLECTION)
                            .whereEqualTo(
                                    "motherId",
                                    uid)
                            .get()
                            .get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicalReportMother report =
                        documentToReport(document);

                if (report != null) {

                    reports.add(report);
                }
            }

            System.out.println(
                    "[MOTHER REPORTS] Firebase reports found = "
                            + reports.size()
                            + " for motherId = "
                            + uid);

        } catch (Exception e) {

            System.out.println(
                    "[MOTHER REPORTS ERROR] Error fetching reports for motherId = "
                            + uid);

            e.printStackTrace();
        }

        return reports;
    }

    // =====================================================
    // REALTIME LISTENER
    // =====================================================

    public ListenerRegistration listenReportsByMotherId(
            String motherId,
            OnReportsChangedListener listener) {

        if (motherId == null ||
                motherId.trim().isEmpty()) {

            System.out.println(
                    "[MOTHER REPORTS] Listener not started. Mother ID missing.");

            return null;
        }

        String uid =
                motherId.trim();

        System.out.println(
                "[MOTHER REPORTS] Starting listener for motherId = "
                        + uid);

        return db.collection(COLLECTION)
                .whereEqualTo(
                        "motherId",
                        uid)
                .addSnapshotListener(
                        (snapshots, error) -> {

                            if (error != null) {

                                System.out.println(
                                        "[MOTHER REPORTS LISTENER ERROR]");

                                error.printStackTrace();

                                return;
                            }

                            List<MedicalReportMother> reports =
                                    new ArrayList<>();

                            if (snapshots != null) {

                                for (DocumentSnapshot document :
                                        snapshots.getDocuments()) {

                                    MedicalReportMother report =
                                            documentToReport(document);

                                    if (report != null) {

                                        reports.add(report);
                                    }
                                }
                            }

                            System.out.println(
                                    "[MOTHER REPORTS LISTENER] Reports = "
                                            + reports.size()
                                            + " for motherId = "
                                            + uid);

                            if (listener != null) {

                                listener.onChanged(
                                        reports);
                            }
                        });
    }

    // =====================================================
    // LISTENER
    // =====================================================

    public interface OnReportsChangedListener {

        void onChanged(
                List<MedicalReportMother> reports);
    }

    // =====================================================
    // GET ONE REPORT
    // =====================================================

    public MedicalReportMother getReportById(
            String reportId) {

        if (reportId == null ||
                reportId.trim().isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                            .document(reportId)
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            return documentToReport(
                    document);

        } catch (Exception e) {

            System.out.println(
                    "[MOTHER REPORTS ERROR] Unable to get report: "
                            + reportId);

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // SAVE REPORT
    // =====================================================

    public boolean saveReport(
            MedicalReportMother report) {

        if (report == null ||
                report.getReportId() == null ||
                report.getReportId()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    reportToMap(report);

            db.collection(COLLECTION)
                    .document(
                            report.getReportId())
                    .set(data)
                    .get();

            System.out.println(
                    "[MOTHER REPORTS] Report saved = "
                            + report.getReportId());

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE REPORT
    // =====================================================

    public boolean updateReport(
            MedicalReportMother report) {

        if (report == null ||
                report.getReportId() == null ||
                report.getReportId()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    reportToMap(report);

            db.collection(COLLECTION)
                    .document(
                            report.getReportId())
                    .set(
                            data,
                            SetOptions.merge())
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE REPORT
    // =====================================================

    public boolean deleteReport(
            String reportId) {

        if (reportId == null ||
                reportId.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
                    .document(reportId)
                    .delete()
                    .get();

            System.out.println(
                    "[MOTHER REPORTS] Deleted report = "
                            + reportId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // MODEL → FIRESTORE
    // =====================================================

    private Map<String, Object> reportToMap(
            MedicalReportMother report) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "reportId",
                safeString(
                        report.getReportId()));

        data.put(
                "motherId",
                safeString(
                        report.getMotherId()));

        data.put(
                "reportName",
                safeString(
                        report.getReportName()));

        // Shared Doctor field
        data.put(
                "date",
                safeString(
                        report.getReportDate()));

        // Shared Doctor field
        data.put(
                "reportUrl",
                safeString(
                        report.getFileUrl()));

        data.put(
                "hospitalName",
                safeString(
                        report.getHospitalName()));

        data.put(
                "doctorName",
                safeString(
                        report.getDoctorName()));

        return data;
    }

    // =====================================================
    // FIRESTORE → MOTHER MODEL
    // =====================================================

    private MedicalReportMother documentToReport(
            DocumentSnapshot document) {

        if (document == null ||
                !document.exists()) {

            return null;
        }

        try {

            MedicalReportMother report =
                    new MedicalReportMother();

            // -------------------------------------------------
            // REPORT ID
            // -------------------------------------------------

            String reportId =
                    getString(
                            document,
                            "reportId");

            if (reportId.isEmpty()) {

                reportId =
                        document.getId();
            }

            report.setReportId(
                    reportId);

            // -------------------------------------------------
            // MOTHER ID
            // -------------------------------------------------

            report.setMotherId(
                    getString(
                            document,
                            "motherId"));

            // -------------------------------------------------
            // REPORT NAME
            // -------------------------------------------------

            report.setReportName(
                    getString(
                            document,
                            "reportName"));

            // -------------------------------------------------
            // DATE
            // -------------------------------------------------

            String reportDate =
                    getString(
                            document,
                            "date");

            // Backward compatibility
            if (reportDate.isEmpty()) {

                reportDate =
                        getString(
                                document,
                                "reportDate");
            }

            report.setReportDate(
                    reportDate);

            // -------------------------------------------------
            // REPORT URL
            // -------------------------------------------------

            String fileUrl =
                    getString(
                            document,
                            "reportUrl");

            // Backward compatibility
            if (fileUrl.isEmpty()) {

                fileUrl =
                        getString(
                                document,
                                "fileUrl");
            }

            if (fileUrl.isEmpty()) {

                fileUrl =
                        getString(
                                document,
                                "url");
            }

            report.setFileUrl(
                    fileUrl);

            // -------------------------------------------------
            // HOSPITAL
            // -------------------------------------------------

            report.setHospitalName(
                    getString(
                            document,
                            "hospitalName"));

            // -------------------------------------------------
            // DOCTOR
            // -------------------------------------------------

            report.setDoctorName(
                    getString(
                            document,
                            "doctorName"));

            System.out.println(
                    "[MOTHER REPORT] Loaded: "
                            + report.getReportName()
                            + " | motherId="
                            + report.getMotherId()
                            + " | date="
                            + report.getReportDate()
                            + " | url="
                            + report.getFileUrl());

            return report;

        } catch (Exception e) {

            System.out.println(
                    "[MOTHER REPORTS ERROR] Unable to convert document.");

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // SAFE STRING
    // =====================================================

    private String safeString(
            String value) {

        return value == null
                ? ""
                : value;
    }

    private String getString(
            DocumentSnapshot document,
            String fieldName) {

        if (document == null ||
                fieldName == null) {

            return "";
        }

        Object value =
                document.get(fieldName);

        return value == null
                ? ""
                : String.valueOf(value);
    }
}