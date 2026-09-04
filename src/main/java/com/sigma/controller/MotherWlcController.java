package com.sigma.controller;

import com.sigma.dao.MotherWlcDAO;
import com.sigma.model.MotherWlcModel;

public class MotherWlcController {


// =========================================================
// DAO
// =========================================================

private final MotherWlcDAO motherDAO;


// =========================================================
// CONSTRUCTOR
// =========================================================

public MotherWlcController() {

    motherDAO = new MotherWlcDAO();
}


// =========================================================
// SAVE MOTHER DATA
// =========================================================

public boolean saveMotherData(
        MotherWlcModel motherModel) {

    if (motherModel == null) {
        return false;
    }

    return motherDAO.saveMother(
            motherModel
    );
}


// =========================================================
// GET FIRST MOTHER DATA
// CURRENT UI SUPPORT
// =========================================================

public MotherWlcModel loadMotherData() {

    return motherDAO.getMother();
}


// =========================================================
// GET MOTHER DATA BY ID
// =========================================================

public MotherWlcModel loadMotherData(
        String motherId) {

    if (motherId == null ||
            motherId.isEmpty()) {

        return null;
    }

    return motherDAO.getMother(
            motherId
    );
}


// =========================================================
// UPDATE MOTHER DATA
// =========================================================

public boolean updateMotherData(
        MotherWlcModel motherModel) {

    if (motherModel == null) {
        return false;
    }

    return motherDAO.updateMother(
            motherModel
    );
}


// =========================================================
// DELETE FIRST MOTHER
// =========================================================

public boolean deleteMotherData() {

    return motherDAO.deleteMother();
}


// =========================================================
// DELETE MOTHER BY ID
// =========================================================

public boolean deleteMotherData(
        String motherId) {

    if (motherId == null ||
            motherId.isEmpty()) {

        return false;
    }

    return motherDAO.deleteMother(
            motherId
    );
}


}
