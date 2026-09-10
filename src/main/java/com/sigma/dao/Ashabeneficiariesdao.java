package com.sigma.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QuerySnapshot;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.AshaBeneficiary;
//import com.sigma.controller.Controller;

public class Ashabeneficiariesdao {

    private final Firestore db =
            FirebaseConfig.getFirestore();
            //private final Controller controller = new Controller();

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
               // beneficiary.setUserUid(controller.getUserUID());

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
    // GET ALL
    // =========================================================
    public List<AshaBeneficiary>
getAshaBeneficiaries() {

    List<AshaBeneficiary> list =
            new ArrayList<>();

    try {

        ApiFuture<QuerySnapshot> future =
                db.collection(COLLECTION)
                       // .whereEqualTo(
                               // "userUid"
                               // controller.getUserUID()
                      //  )
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
