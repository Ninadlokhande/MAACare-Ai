package com.sigma.dao.doctorDao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.sigma.model.DoctorModel.FeedbackModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackDAO {

    private final Firestore db;

    public FeedbackDAO(Firestore db) {
        this.db = db;
    }

    // =========================================================
    // SAVE FEEDBACK
    // =========================================================

    public boolean addFeedback(
            String doctorUid,
            String patientUid,
            String patientName,
            double rating,
            String comment) {

        try {

            if (doctorUid == null ||
                    doctorUid.trim().isEmpty()) {

                System.out.println(
                        "[FEEDBACK DAO] Doctor UID is empty.");

                return false;
            }

            if (rating < 1 || rating > 5) {

                System.out.println(
                        "[FEEDBACK DAO] Rating must be between 1 and 5.");

                return false;
            }

            String feedbackId = db.collection("feedback")
                    .document()
                    .getId();

            Map<String, Object> data = new HashMap<>();

            data.put(
                    "feedbackId",
                    feedbackId);

            data.put(
                    "doctorUid",
                    doctorUid.trim());

            data.put(
                    "patientUid",
                    patientUid == null
                            ? ""
                            : patientUid.trim());

            data.put(
                    "patientName",
                    patientName == null ||
                            patientName.trim().isEmpty()
                                    ? "Patient"
                                    : patientName.trim());

            data.put(
                    "rating",
                    rating);

            data.put(
                    "comment",
                    comment == null
                            ? ""
                            : comment.trim());

            data.put(
                    "timestamp",
                    new Date());

            db.collection("feedback")
                    .document(feedbackId)
                    .set(data)
                    .get();

            System.out.println(
                    "[FEEDBACK DAO] Feedback saved successfully.");

            System.out.println(
                    "[FEEDBACK DAO] Feedback ID: "
                            + feedbackId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "[FEEDBACK DAO] Save error: "
                            + e.getMessage());

            return false;
        }
    }

    // =========================================================
    // GET ALL FEEDBACK FOR DOCTOR
    // =========================================================

    public List<FeedbackModel> getDoctorFeedback(
            String doctorUid) {

        List<FeedbackModel> feedbackList = new ArrayList<>();

        try {

            if (doctorUid == null ||
                    doctorUid.trim().isEmpty()) {

                return feedbackList;
            }

            QuerySnapshot snapshot = db.collection("feedback")
                    .whereEqualTo(
                            "doctorUid",
                            doctorUid)
                    .get()
                    .get();

            for (DocumentSnapshot document : snapshot.getDocuments()) {

                FeedbackModel feedback = document.toObject(
                        FeedbackModel.class);

                if (feedback != null) {

                    feedback.setFeedbackId(
                            document.getId());

                    feedbackList.add(
                            feedback);
                }
            }

            // -------------------------------------------------
            // NEWEST FIRST
            // -------------------------------------------------

            feedbackList.sort(
                    (a, b) -> {

                        Date dateA = a.getTimestamp();

                        Date dateB = b.getTimestamp();

                        if (dateA == null &&
                                dateB == null) {

                            return 0;
                        }

                        if (dateA == null) {
                            return 1;
                        }

                        if (dateB == null) {
                            return -1;
                        }

                        return dateB.compareTo(
                                dateA);
                    });

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "[FEEDBACK DAO] Load error: "
                            + e.getMessage());
        }

        return feedbackList;
    }
}