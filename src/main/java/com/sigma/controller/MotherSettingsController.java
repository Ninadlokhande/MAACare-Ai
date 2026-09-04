package com.sigma.controller;

import com.sigma.dao.MotherSettingsDAO;
import com.sigma.model.MotherSettingsModel;

public class MotherSettingsController {

    private final MotherSettingsDAO settingsDAO;

    public MotherSettingsController() {
        settingsDAO = new MotherSettingsDAO();
    }

    // =========================================================
    // SAVE SETTINGS
    // =========================================================

    public boolean saveSettings(
            MotherSettingsModel settings) {

        if (settings == null) {
            return false;
        }

        return settingsDAO.saveSettings(settings);
    }

    // =========================================================
    // LOAD SETTINGS
    // =========================================================

    public MotherSettingsModel loadSettings(
            String motherId) {

        if (motherId == null
                || motherId.isEmpty()) {

            return null;
        }

        return settingsDAO.getSettings(motherId);
    }

    // =========================================================
    // UPDATE SETTINGS
    // =========================================================

    public boolean updateSettings(
            MotherSettingsModel settings) {

        if (settings == null) {
            return false;
        }

        return settingsDAO.updateSettings(settings);
    }

    // =========================================================
    // DELETE SETTINGS
    // =========================================================

    public boolean deleteSettings(
            String motherId) {

        if (motherId == null
                || motherId.isEmpty()) {

            return false;
        }

        return settingsDAO.deleteSettings(motherId);
    }
}