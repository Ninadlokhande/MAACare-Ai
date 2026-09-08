package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.sigma.model.MedicalReportMother;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalReportDAO {

        private final Firestore db;

        private static final String COLLECTION = "medicalReports";

        public MedicalReportDAO() {

                db = com.google.firebase.cloud.FirestoreClient
                                .getFirestore();

                System.out.println(
                                "MedicalReportDAO connected to Firebase");
        }

        // =====================================================
        // GET ALL
        // =====================================================

        public List<MedicalReportMother> getAllReports() {

                List<MedicalReportMother> reports = new ArrayList<>();

                try {

                        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();

                        QuerySnapshot snapshot = future.get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                MedicalReportMother report = documentToReport(document);

                                if (report != null) {
                                        reports.add(report);
                                }
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return reports;
        }

        // =====================================================
        // GET BY MOTHER ID
        // =====================================================

        public List<MedicalReportMother> getReportsByMotherId(String motherId) {

                List<MedicalReportMother> reports = new ArrayList<>();

                if (motherId == null ||
                                motherId.trim().isEmpty()) {

                        return reports;
                }

                try {

                        QuerySnapshot snapshot = db.collection(COLLECTION)
                                        .whereEqualTo(
                                                        "motherId",
                                                        motherId.trim())
                                        .get()
                                        .get();

                        for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                                MedicalReportMother report = documentToReport(document);

                                if (report != null) {
                                        reports.add(report);
                                }
                        }

                } catch (Exception e) {

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

                        return null;
                }

                return db.collection(COLLECTION)
                                .whereEqualTo(
                                                "motherId",
                                                motherId.trim())
                                .addSnapshotListener(
                                                (snapshots, error) -> {

                                                        if (error != null) {

                                                                System.out.println(
                                                                                "Medical report listener error");

                                                                error.printStackTrace();

                                                                return;
                                                        }

                                                        List<MedicalReportMother> reports = new ArrayList<>();

                                                        if (snapshots != null) {

                                                                for (DocumentSnapshot document : snapshots
                                                                                .getDocuments()) {

                                                                        MedicalReportMother report = documentToReport(
                                                                                        document);

                                                                        if (report != null) {
                                                                                reports.add(report);
                                                                        }
                                                                }
                                                        }

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
        // GET ONE
        // =====================================================

        public MedicalReportMother getReportById(
                        String reportId) {

                if (reportId == null ||
                                reportId.trim().isEmpty()) {

                        return null;
                }

                try {

                        DocumentSnapshot document = db.collection(COLLECTION)
                                        .document(reportId)
                                        .get()
                                        .get();

                        if (!document.exists()) {
                                return null;
                        }

                        return documentToReport(
                                        document);

                } catch (Exception e) {

                        e.printStackTrace();

                        return null;
                }
        }

        // =====================================================
        // SAVE
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

                        Map<String, Object> data = reportToMap(report);

                        db.collection(COLLECTION)
                                        .document(
                                                        report.getReportId())
                                        .set(data)
                                        .get();

                        System.out.println(
                                        "Medical report saved: "
                                                        + report.getReportId());

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();

                        return false;
                }
        }

        // =====================================================
        // UPDATE
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

                        Map<String, Object> data = reportToMap(report);

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
        // DELETE
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

                Map<String, Object> data = new HashMap<>();

                data.put(
                                "reportId",
                                report.getReportId());

                data.put(
                                "motherId",
                                report.getMotherId());

                data.put(
                                "reportName",
                                report.getReportName());

                data.put(
                                "reportDate",
                                report.getReportDate());

                data.put(
                                "hospitalName",
                                report.getHospitalName());

                data.put(
                                "doctorName",
                                report.getDoctorName());

                data.put(
                                "fileUrl",
                                report.getFileUrl());

                return data;
        }

        // =====================================================
        // FIRESTORE → MODEL
        // =====================================================

        private MedicalReportMother documentToReport(
                        DocumentSnapshot document) {

                try {

                        MedicalReportMother report = new MedicalReportMother();

                        String reportId = document.getString(
                                        "reportId");

                        if (reportId == null ||
                                        reportId.trim().isEmpty()) {

                                reportId = document.getId();
                        }

                        report.setReportId(reportId);

                        report.setMotherId(
                                        document.getString(
                                                        "motherId"));

                        report.setReportName(
                                        document.getString(
                                                        "reportName"));

                        report.setReportDate(
                                        document.getString(
                                                        "reportDate"));

                        report.setHospitalName(
                                        document.getString(
                                                        "hospitalName"));

                        report.setDoctorName(
                                        document.getString(
                                                        "doctorName"));

                        report.setFileUrl(
                                        document.getString(
                                                        "fileUrl"));

                        return report;

                } catch (Exception e) {

                        e.printStackTrace();

                        return null;
                }
        }
}