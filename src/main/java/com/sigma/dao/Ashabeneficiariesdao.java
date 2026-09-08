package com.sigma.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QuerySnapshot;

import com.sigma.config.FirebaseConfig;
import com.sigma.model.AshaBeneficiary;

public class Ashabeneficiariesdao {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // COLLECTION NAME
    // =========================================================

    private static final String COLLECTION =
            "AshaBeneficiaries";

    // =========================================================
    // SAVE
    // =========================================================

    public boolean saveAshaBeneficiary(
            AshaBeneficiary beneficiary) {

        try {

            db.collection(COLLECTION)
                    .document(
                            String.valueOf(
                                    beneficiary.getId()
                            )
                    )
                    .set(beneficiary)
                    .get();

            System.out.println(
                    "Data Inserted Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error inserting beneficiary:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET BY ID
    // =========================================================

    public AshaBeneficiary getAshaBeneficiary(
            int id) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection(COLLECTION)
                            .document(
                                    String.valueOf(id)
                            )
                            .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        AshaBeneficiary.class
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting beneficiary:"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateAshaBeneficiary(
            AshaBeneficiary beneficiary) {

        try {

            db.collection(COLLECTION)
                    .document(
                            String.valueOf(
                                    beneficiary.getId()
                            )
                    )
                    .set(beneficiary)
                    .get();

            System.out.println(
                    "Data Updated Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating beneficiary:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteAshaBeneficiary(
            int id) {

        try {

            db.collection(COLLECTION)
                    .document(
                            String.valueOf(id)
                    )
                    .delete()
                    .get();

            System.out.println(
                    "Data Deleted Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting beneficiary:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // RENUMBER BENEFICIARIES
    // =========================================================

    public boolean renumberAshaBeneficiaries() {

        try {

            // Get all beneficiaries
            List<AshaBeneficiary> list =
                    getAshaBeneficiaries();

            if (list == null || list.isEmpty()) {

                System.out.println(
                        "No beneficiaries available for renumbering."
                );

                return true;
            }

            // -------------------------------------------------
            // STEP 1:
            // Sort according to existing ID
            // -------------------------------------------------

            list.sort(
                    Comparator.comparingInt(
                            AshaBeneficiary::getId
                    )
            );

            // -------------------------------------------------
            // STEP 2:
            // Save all existing records temporarily
            // -------------------------------------------------

            for (AshaBeneficiary beneficiary : list) {

                int oldId =
                        beneficiary.getId();

                db.collection(COLLECTION)
                        .document(
                                "temp_" + oldId
                        )
                        .set(beneficiary)
                        .get();
            }

            // -------------------------------------------------
            // STEP 3:
            // Delete original numeric documents
            // -------------------------------------------------

            for (AshaBeneficiary beneficiary : list) {

                int oldId =
                        beneficiary.getId();

                db.collection(COLLECTION)
                        .document(
                                String.valueOf(oldId)
                        )
                        .delete()
                        .get();
            }

            // -------------------------------------------------
            // STEP 4:
            // Create new continuous IDs
            // -------------------------------------------------

            int newId = 1;

            for (AshaBeneficiary beneficiary : list) {

                beneficiary.setId(newId);

                db.collection(COLLECTION)
                        .document(
                                String.valueOf(newId)
                        )
                        .set(beneficiary)
                        .get();

                newId++;
            }

            // -------------------------------------------------
            // STEP 5:
            // Delete temporary documents
            // -------------------------------------------------

            for (int i = 1; i <= list.size(); i++) {

                db.collection(COLLECTION)
                        .document(
                                "temp_" + i
                        )
                        .delete()
                        .get();
            }

            System.out.println(
                    "Beneficiary IDs renumbered successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error renumbering beneficiaries:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<AshaBeneficiary>
    getAshaBeneficiaries() {

        List<AshaBeneficiary> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            System.out.println(
                    "Firebase documents found: "
                            + snapshot.getDocuments().size()
            );

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                try {

                    AshaBeneficiary beneficiary =
                            doc.toObject(
                                    AshaBeneficiary.class
                            );

                    if (beneficiary != null) {

                        list.add(beneficiary);

                        System.out.println(
                                "Loaded beneficiary: "
                                        + beneficiary
                        );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Unable to convert document: "
                                    + doc.getId()
                    );

                    e.printStackTrace();
                }
            }

            // -------------------------------------------------
            // Keep beneficiaries in ID order
            // -------------------------------------------------

            list.sort(
                    Comparator.comparingInt(
                            AshaBeneficiary::getId
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Error loading beneficiaries:"
            );

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // REAL-TIME LISTENER
    // =========================================================

    public ListenerRegistration
    listenToAshaBeneficiaries(
            EventListener<QuerySnapshot> listener) {

        return db.collection(COLLECTION)
                .addSnapshotListener(listener);
    }
}