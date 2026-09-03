package com.sigma.controller;

import java.util.List;

import com.sigma.dao.Ashabeneficiariesdao;
import com.sigma.dao.Ashavisit;
import com.sigma.model.AshaBeneficiary;
import com.sigma.model.AshaWorkerVisitModel;

public class DashboardController {

    private final Ashabeneficiariesdao beneficiaryDAO;
    private final Ashavisit visitDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardController() {

        beneficiaryDAO = new Ashabeneficiariesdao();
        visitDAO = new Ashavisit();
    }

    // =========================================================
    // PREGNANT WOMEN COUNT
    // =========================================================

    public int getPregnantWomenCount() {

        List<AshaBeneficiary> list =
                beneficiaryDAO.getAshaBeneficiaries();

        int count = 0;

        for (AshaBeneficiary beneficiary : list) {

            if (beneficiary.getCategory() != null &&
                    beneficiary.getCategory()
                            .equalsIgnoreCase("Pregnant Women")) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // CHILDREN COUNT
    // =========================================================

    public int getChildrenCount() {

        List<AshaBeneficiary> list =
                beneficiaryDAO.getAshaBeneficiaries();

        int count = 0;

        for (AshaBeneficiary beneficiary : list) {

            if (beneficiary.getCategory() != null &&
                    beneficiary.getCategory()
                            .equalsIgnoreCase("Children")) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // TOTAL HOME VISITS
    // =========================================================

    public int getHomeVisitCount() {

        List<AshaWorkerVisitModel> visits =
                visitDAO.getVisits();

        return visits.size();
    }

    // =========================================================
    // TOTAL IMMUNIZATIONS
    // =========================================================

    public int getImmunizationCount() {

        List<AshaWorkerVisitModel> visits =
                visitDAO.getVisits();

        int count = 0;

        for (AshaWorkerVisitModel visit : visits) {

            if (visit.getPurpose() != null &&
                    visit.getPurpose()
                            .equalsIgnoreCase("Immunization")) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // GET ALL BENEFICIARIES
    // =========================================================

    public List<AshaBeneficiary> getAllBeneficiaries() {

        return beneficiaryDAO.getAshaBeneficiaries();
    }

    // =========================================================
    // GET ALL VISITS
    // =========================================================

    public List<AshaWorkerVisitModel> getAllVisits() {

        return visitDAO.getVisits();
    }
}