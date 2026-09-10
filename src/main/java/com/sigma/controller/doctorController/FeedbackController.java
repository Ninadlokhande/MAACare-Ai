package com.sigma.controller.doctorController;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.sigma.config.FirebaseConfig;
import com.sigma.dao.doctorDao.FeedbackDAO;
import com.sigma.model.DoctorModel.FeedbackModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class FeedbackController {

    private final FeedbackDAO dao;

    private ListenerRegistration feedbackListener;

    public FeedbackController(Firestore db) {

        this.dao = new FeedbackDAO(db);
    }

    // =========================================================
    // ADD FEEDBACK
    // =========================================================

    public boolean addFeedback(
            String doctorUid,
            String patientUid,
            String patientName,
            double rating,
            String comment) {

        if (doctorUid == null ||
                doctorUid.trim().isEmpty()) {

            System.out.println(
                    "[FEEDBACK CONTROLLER] Doctor UID missing.");

            return false;
        }

        if (rating < 1 ||
                rating > 5) {

            System.out.println(
                    "[FEEDBACK CONTROLLER] Invalid rating.");

            return false;
        }

        return dao.addFeedback(
                doctorUid,
                patientUid,
                patientName,
                rating,
                comment);
    }

    // =========================================================
    // GET FEEDBACK
    // =========================================================

    public List<FeedbackModel> getDoctorFeedback(
            String doctorUid) {

        return dao.getDoctorFeedback(
                doctorUid);
    }

    // =========================================================
    // REALTIME LISTENER
    // =========================================================

    public void startRealtimeFeedbackListener(
            String doctorUid,
            Consumer<List<FeedbackModel>> callback) {

        stopRealtimeFeedbackListener();

        if (doctorUid == null ||
                doctorUid.trim().isEmpty()) {

            System.out.println(
                    "[FEEDBACK REALTIME] Doctor UID is empty.");

            return;
        }

        try {

            Firestore db = FirebaseConfig.getFirestore();

            feedbackListener = db.collection("feedback")
                    .whereEqualTo(
                            "doctorUid",
                            doctorUid)
                    .addSnapshotListener(
                            (snapshot, error) -> {

                                // ---------------------------------
                                // ERROR
                                // ---------------------------------

                                if (error != null) {

                                    System.out.println(
                                            "[FEEDBACK REALTIME] Error: "
                                                    + error.getMessage());

                                    error.printStackTrace();

                                    return;
                                }

                                if (snapshot == null) {

                                    return;
                                }

                                // ---------------------------------
                                // CREATE LIST
                                // ---------------------------------

                                List<FeedbackModel> feedbackList = new ArrayList<>();

                                for (DocumentSnapshot document : snapshot.getDocuments()) {

                                    try {

                                        FeedbackModel feedback = document.toObject(
                                                FeedbackModel.class);

                                        if (feedback != null) {

                                            feedback.setFeedbackId(
                                                    document.getId());

                                            feedbackList.add(
                                                    feedback);
                                        }

                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }
                                }

                                // ---------------------------------
                                // NEWEST FIRST
                                // ---------------------------------

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

                                System.out.println(
                                        "[FEEDBACK REALTIME] "
                                                + feedbackList.size()
                                                + " feedback found.");

                                // ---------------------------------
                                // SEND TO DASHBOARD
                                // ---------------------------------

                                if (callback != null) {

                                    callback.accept(
                                            feedbackList);
                                }
                            });

            System.out.println(
                    "[FEEDBACK REALTIME] Listener started.");

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "[FEEDBACK REALTIME] Listener start failed.");
        }
    }

    // =========================================================
    // STOP LISTENER
    // =========================================================

    public void stopRealtimeFeedbackListener() {

        if (feedbackListener != null) {

            feedbackListener.remove();

            feedbackListener = null;

            System.out.println(
                    "[FEEDBACK REALTIME] Listener stopped.");
        }
    }
}