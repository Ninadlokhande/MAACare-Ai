package com.sigma.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.sigma.model.MedicalReportMother;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalReportDAO {

    // =====================================================
    // FIRESTORE
    // =====================================================

    private final Firestore db;

    private static final String COLLECTION =
            "medicalReports";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public MedicalReportDAO() {

        db = FirestoreClient.getFirestore();

        System.out.println(
                "MedicalReportDAO connected to Firebase"
        );
    }

    // =====================================================
    // GET ALL REPORTS
    // =====================================================

    public List<MedicalReportMother> getAllReports() {

        List<MedicalReportMother> reports =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicalReportMother report =
                        documentToReport(document);

                if (report != null) {
                    reports.add(report);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching medical reports"
            );

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

            return reports;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "motherId",
                              motherId
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                MedicalReportMother report =
                        documentToReport(document);

                if (report != null) {
                    reports.add(report);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching reports for mother: "
                            + motherId
            );

            e.printStackTrace();
        }

        return reports;
    }

    // =====================================================
    // GET REPORT BY ID
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

            return documentToReport(document);

        } catch (Exception e) {

            System.out.println(
                    "Error fetching report: "
                            + reportId
            );

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
            report.getReportId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    reportToMap(report);

            db.collection(COLLECTION)
              .document(report.getReportId())
              .set(data)
              .get();

            System.out.println(
                    "Medical report saved successfully: "
                            + report.getReportId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving medical report"
            );

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
            report.getReportId().trim().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    reportToMap(report);

            db.collection(COLLECTION)
              .document(report.getReportId())
              .set(
                      data,
                      SetOptions.merge()
              )
              .get();

            System.out.println(
                    "Medical report updated successfully: "
                            + report.getReportId()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating medical report"
            );

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
                    "Medical report deleted successfully: "
                            + reportId
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting medical report"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // MODEL → FIRESTORE MAP
    // =====================================================

    private Map<String, Object> reportToMap(
            MedicalReportMother report) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "reportId",
                report.getReportId()
        );

        // data.put(
        //         "motherId",
        //         report.getMotherId()
        // );

        data.put(
                "reportName",
                report.getReportName()
        );

        data.put(
                "reportDate",
                report.getReportDate()
        );

        data.put(
                "hospitalName",
                report.getHospitalName()
        );

        data.put(
                "doctorName",
                report.getDoctorName()
        );

        data.put(
                "fileUrl",
                report.getFileUrl()
        );

        return data;
    }

    // =====================================================
    // FIRESTORE → MODEL
    // =====================================================

    private MedicalReportMother documentToReport(
            DocumentSnapshot document) {

        try {

            MedicalReportMother report =
                    new MedicalReportMother();

            report.setReportId(
                    document.getString("reportId")
            );

        //     report.setMotherId(
        //             document.getString("motherId")
        //     );

            report.setReportName(
                    document.getString("reportName")
            );

            report.setReportDate(
                    document.getString("reportDate")
            );

            report.setHospitalName(
                    document.getString("hospitalName")
            );

            report.setDoctorName(
                    document.getString("doctorName")
            );

            report.setFileUrl(
                    document.getString("fileUrl")
            );

            return report;

        } catch (Exception e) {

            System.out.println(
                    "Error converting Firestore document"
            );

            e.printStackTrace();

            return null;
        }
    }
}