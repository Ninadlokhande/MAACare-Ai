package com.sigma.controller;

import java.util.List;

import com.sigma.dao.Ashavisit;
import com.sigma.model.AshaBeneficiary;
import com.sigma.model.AshaWorkerVisitModel;

public class Ashavisitcontroller {

    private final Ashavisit dao =
            new Ashavisit();
            // =========================================================
// DELETE ALL VISITS BY BENEFICIARY ID
// =========================================================

public boolean deleteVisitsByBeneficiaryId(
        int beneficiaryId) {

    return dao.deleteVisitsByBeneficiaryId(
            beneficiaryId
    );
}


    // =========================================================
    // ADD VISIT
    // =========================================================

    public boolean addVisit(
            AshaBeneficiary beneficiary,
            String date,
            String purpose,
            String time) {

        if (beneficiary == null) {
            return false;
        }

        AshaWorkerVisitModel visit =
                new AshaWorkerVisitModel(
                        beneficiary.getId(),
                        beneficiary.getName(),
                        beneficiary.getVillage(),
                        date,
                        purpose,
                        time,
                        beneficiary.getStatus()
                );

        return dao.saveVisit(visit);
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<AshaWorkerVisitModel>
    getAllVisits() {

        return dao.getVisits();
    }


    // =========================================================
    // GET BY BENEFICIARY
    // =========================================================

    public List<AshaWorkerVisitModel>
    getVisitsByBeneficiaryId(
            int beneficiaryId) {

        return dao.getVisitsByBeneficiaryId(
                beneficiaryId
        );
    }


    // =========================================================
    // COUNT
    // =========================================================

    public int getTotalVisitCount(
            int beneficiaryId) {

        return dao.getTotalVisitCount(
                beneficiaryId
        );
    }
}
