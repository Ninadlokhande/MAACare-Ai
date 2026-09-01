package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.AshaWorkerVisitModel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class Ashavisit {

    private static final String COLLECTION =
            "AshaWorkerVisits";

    private final Firestore db =
            FirebaseConfig.getFirestore();


    // =========================================================
    // SAVE VISIT
    // =========================================================

    public boolean saveVisit(
            AshaWorkerVisitModel visit) {

        try {

            if (visit == null) {
                System.out.println("Visit is NULL");
                return false;
            }

            db.collection(COLLECTION)
                    .add(visit)
                    .get();

            System.out.println(
                    "Visit Data Inserted Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "ERROR SAVING VISIT:"
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL VISITS
    // =========================================================

    public List<AshaWorkerVisitModel> getVisits() {

        List<AshaWorkerVisitModel> list =
                new ArrayList<>();

        try {

            System.out.println(
                    "Reading Firestore collection: "
                            + COLLECTION
            );

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            System.out.println(
                    "Firestore documents found: "
                            + snapshot.size()
            );


            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                try {

                    AshaWorkerVisitModel visit =
                            doc.toObject(
                                    AshaWorkerVisitModel.class
                            );

                    if (visit != null) {

                        list.add(visit);

                        System.out.println(
                                "Visit loaded: "
                                        + doc.getId()
                                        + " | "
                                        + visit.getName()
                                        + " | "
                                        + visit.getDate()
                        );

                    }

                } catch (Exception ex) {

                    System.out.println(
                            "Error converting document: "
                                    + doc.getId()
                    );

                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR READING VISITS:"
            );

            e.printStackTrace();
        }

        return list;
    }
    // =========================================================
// DELETE ALL VISITS BY BENEFICIARY ID
// =========================================================

// =========================================================
// DELETE ALL VISITS BY BENEFICIARY ID
// =========================================================

public boolean deleteVisitsByBeneficiaryId(
        int beneficiaryId) {

    try {

        QuerySnapshot snapshot =
                db.collection(COLLECTION)
                        .whereEqualTo(
                                "beneficiaryId",
                                beneficiaryId
                        )
                        .get()
                        .get();

        System.out.println(
                "Visits found for beneficiary "
                        + beneficiaryId
                        + " = "
                        + snapshot.size()
        );

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            db.collection(COLLECTION)
                    .document(doc.getId())
                    .delete()
                    .get();

            System.out.println(
                    "Deleted visit document: "
                            + doc.getId()
            );
        }

        return true;

    } catch (Exception e) {

        System.out.println(
                "ERROR DELETING VISITS BY BENEFICIARY ID:"
        );

        e.printStackTrace();

        return false;
    }
}

    // =========================================================
    // GET ONE VISIT
    // =========================================================

    public AshaWorkerVisitModel getVisit(
            String documentId) {

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                            .document(documentId)
                            .get()
                            .get();

            if (document.exists()) {

                return document.toObject(
                        AshaWorkerVisitModel.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateVisit(
            String documentId,
            AshaWorkerVisitModel visit) {

        try {

            db.collection(COLLECTION)
                    .document(documentId)
                    .set(visit)
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteVisit(
            String documentId) {

        try {

            db.collection(COLLECTION)
                    .document(documentId)
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET BY BENEFICIARY ID
    // =========================================================

    public List<AshaWorkerVisitModel>
    getVisitsByBeneficiaryId(
            int beneficiaryId) {

        List<AshaWorkerVisitModel> list =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection(COLLECTION)
                            .whereEqualTo(
                                    "beneficiaryId",
                                    beneficiaryId
                            )
                            .get()
                            .get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                AshaWorkerVisitModel visit =
                        doc.toObject(
                                AshaWorkerVisitModel.class
                        );

                if (visit != null) {
                    list.add(visit);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // COUNT
    // =========================================================

    public int getTotalVisitCount(
            int beneficiaryId) {

        try {

            QuerySnapshot snapshot =
                    db.collection(COLLECTION)
                            .whereEqualTo(
                                    "beneficiaryId",
                                    beneficiaryId
                            )
                            .get()
                            .get();

            return snapshot.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }
}
