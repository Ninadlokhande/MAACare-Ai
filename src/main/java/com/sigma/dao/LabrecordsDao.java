package com.sigma.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.Labrecords;

public class LabrecordsDao {

    // =====================================================
    // FIRESTORE
    // =====================================================

    private final Firestore db = FirebaseConfig.getFirestore();

    private static final String COLLECTION = "labrecords";

    // =====================================================
    // NORMAL SAVE
    // =====================================================

    public void saveLabrecord(
            Labrecords labrecord) {

        try {

            if (labrecord == null) {

                System.out.println(
                        "Lab record is null.");

                return;
            }

            if (labrecord.getNumber() == null ||
                    labrecord.getNumber().trim().isEmpty()) {

                System.out.println(
                        "Lab record number is missing.");

                return;
            }

            db.collection(COLLECTION)
                    .document(labrecord.getNumber())
                    .create(labrecord)
                    .get();

            System.out.println(
                    "Lab Record Data Inserted");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // DOCTOR REPORT → HOSPITAL
    // =====================================================

    public void saveDoctorReportToHospital(
            Labrecords labrecord,
            String sourceReportId) {

        try {

            if (labrecord == null) {

                System.out.println(
                        "Lab record is null.");

                return;
            }

            if (sourceReportId == null ||
                    sourceReportId.trim().isEmpty()) {

                System.out.println(
                        "Source report ID is missing.");

                return;
            }

            /*
             * Same Doctor report = Same Hospital document.
             *
             * This prevents duplicate records.
             */

            String documentId = "DOC-" + sourceReportId;

            Map<String, Object> data = new HashMap<>();

            // ---------------------------------------------
            // BASIC DATA
            // ---------------------------------------------

            data.put(
                    "number",
                    labrecord.getNumber());

            data.put(
                    "PatientName",
                    labrecord.getPatientName());

            data.put(
                    "TestName",
                    labrecord.getTestName());

            data.put(
                    "Department",
                    labrecord.getDepartment());

            data.put(
                    "Date",
                    labrecord.getDate());

            data.put(
                    "Status",
                    labrecord.getStatus());

            data.put(
                    "Results",
                    labrecord.getResults());

            // ---------------------------------------------
            // SAME CLOUDINARY URL
            // ---------------------------------------------

            data.put(
                    "documentUrl",
                    labrecord.getDocumentUrl());

            // ---------------------------------------------
            // MOTHER ID
            // ---------------------------------------------

            data.put(
                    "motherId",
                    labrecord.getMotherId());

            // ---------------------------------------------
            // ORIGINAL DOCTOR REPORT ID
            // ---------------------------------------------

            data.put(
                    "sourceReportId",
                    sourceReportId);

            // ---------------------------------------------
            // SAVE / UPDATE
            // ---------------------------------------------

            db.collection(COLLECTION)
                    .document(documentId)
                    .set(
                            data,
                            SetOptions.merge())
                    .get();

            System.out.println(
                    "Doctor report saved to Hospital.");

            System.out.println(
                    "Hospital document ID: "
                            + documentId);

        } catch (Exception e) {

            System.out.println(
                    "Error saving Doctor report "
                            + "to Hospital.");

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ONE
    // =====================================================

    public Labrecords getLabrecord(
            String number) {

        try {

            if (number == null ||
                    number.trim().isEmpty()) {

                return null;
            }

            ApiFuture<DocumentSnapshot> future = db.collection(COLLECTION)
                    .document(number)
                    .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {

                return document.toObject(
                        Labrecords.class);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // UPDATE
    // =====================================================

    public void updateLabrecord(
            Labrecords labrecord) {

        try {

            if (labrecord == null ||
                    labrecord.getNumber() == null ||
                    labrecord.getNumber()
                            .trim()
                            .isEmpty()) {

                return;
            }

            db.collection(COLLECTION)
                    .document(
                            labrecord.getNumber())
                    .set(labrecord)
                    .get();

            System.out.println(
                    "Lab Record Data Updated");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    public void deleteLabrecord(
            String number) {

        try {

            if (number == null ||
                    number.trim().isEmpty()) {

                return;
            }

            db.collection(COLLECTION)
                    .document(number)
                    .delete()
                    .get();

            System.out.println(
                    "Lab Record Data Deleted");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public List<Labrecords> getLabrecords() {

        List<Labrecords> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                    .get();

            QuerySnapshot snapshot = future.get();

            for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {

                Labrecords labrecord = doc.toObject(
                        Labrecords.class);

                if (labrecord != null) {

                    list.add(labrecord);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =====================================================
    // GET BY SOURCE REPORT ID
    // =====================================================

    public Labrecords getBySourceReportId(
            String sourceReportId) {

        if (sourceReportId == null ||
                sourceReportId.trim().isEmpty()) {

            return null;
        }

        try {

            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                    .whereEqualTo(
                            "sourceReportId",
                            sourceReportId)
                    .limit(1)
                    .get();

            QuerySnapshot snapshot = future.get();

            if (!snapshot.isEmpty()) {

                DocumentSnapshot document = snapshot
                        .getDocuments()
                        .get(0);

                return document.toObject(
                        Labrecords.class);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}