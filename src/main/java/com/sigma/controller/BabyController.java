package com.sigma.controller;

import com.sigma.dao.BabyDAO;
import com.sigma.model.BabyModel;

import java.util.List;

public class BabyController {

    // =====================================================
    // DAO
    // =====================================================

    private final BabyDAO babyDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BabyController() {

        babyDAO = new BabyDAO();
    }

    // =====================================================
    // GET ALL BABIES
    // =====================================================

    public List<BabyModel> getAllBabies() {

        return babyDAO.getAllBabies();
    }

    // =====================================================
    // GET BABY BY ID
    // =====================================================

    public BabyModel getBabyById(
            String babyId) {

        return babyDAO.getBabyById(
                babyId
        );
    }

    // =====================================================
    // GET BABY BY MOTHER ID
    // =====================================================

    public BabyModel getBabyByMotherId(
            String motherId) {

        return babyDAO.getBabyByMotherId(
                motherId
        );
    }

    // =====================================================
    // SAVE BABY
    // =====================================================

    public boolean saveBaby(
            BabyModel baby) {

        if (baby == null) {
            return false;
        }

        return babyDAO.saveBaby(
                baby
        );
    }

    // =====================================================
    // UPDATE BABY
    // =====================================================

    public boolean updateBaby(
            BabyModel baby) {

        if (baby == null) {
            return false;
        }

        return babyDAO.updateBaby(
                baby
        );
    }

    // =====================================================
    // DELETE BABY
    // =====================================================

    public boolean deleteBaby(
            String babyId) {

        return babyDAO.deleteBaby(
                babyId
        );
    }
}