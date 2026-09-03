package com.sigma.controller;

import java.util.List;

import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QuerySnapshot;

import com.sigma.dao.Ashabeneficiariesdao;
import com.sigma.model.AshaBeneficiary;

public class Ashabeneficiariescontroller {

    private final Ashabeneficiariesdao dao =
            new Ashabeneficiariesdao();

    // =========================================================
    // ADD
    // =========================================================

    public boolean addAshaBeneficiary(
            int id,
            String name,
            String category,
            String age,
            String village,
            String status,
            String lastVisit) {

        AshaBeneficiary beneficiary =
                new AshaBeneficiary(
                        id,
                        name,
                        category,
                        age,
                        village,
                        status,
                        lastVisit
                );

        return dao.saveAshaBeneficiary(
                beneficiary
        );
    }

    // =========================================================
    // GET BY ID
    // =========================================================

    public AshaBeneficiary getAshaBeneficiary(
            int id) {

        return dao.getAshaBeneficiary(id);
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateAshaBeneficiary(
            int id,
            String name,
            String category,
            String age,
            String village,
            String status,
            String lastVisit) {

        AshaBeneficiary beneficiary =
                new AshaBeneficiary(
                        id,
                        name,
                        category,
                        age,
                        village,
                        status,
                        lastVisit
                );

        return dao.updateAshaBeneficiary(
                beneficiary
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteAshaBeneficiary(
            int id) {

        return dao.deleteAshaBeneficiary(
                id
        );
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<AshaBeneficiary>
    getAllAshaBeneficiaries() {

        return dao.getAshaBeneficiaries();
    }

    // =========================================================
    // REAL-TIME LISTENER
    // =========================================================

    public ListenerRegistration
    listenToAshaBeneficiaries(
            EventListener<QuerySnapshot> listener) {

        return dao.listenToAshaBeneficiaries(
                listener
        );
    }
}
