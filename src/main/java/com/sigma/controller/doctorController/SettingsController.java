package com.sigma.controller.doctorController;

import com.sigma.dao.SettingsDAO;
import com.sigma.model.DoctorModel.Settings;

public class SettingsController {

        private final SettingsDAO dao;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public SettingsController() {

                dao = new SettingsDAO();
        }

        // =====================================================
        // GET SETTINGS
        // =====================================================

        public Settings getDoctorSettings() {

                return dao.getSettings();
        }

        // =====================================================
        // SAVE ACCOUNT SETTINGS
        // =====================================================

        public void saveSettings(
                        String fullName,
                        String email,
                        String phone,
                        String specialization,
                        String license) {

                Settings settings = dao.getSettings();

                settings.setFullName(
                                fullName);

                settings.setEmail(
                                email);

                settings.setPhone(
                                phone);

                settings.setSpecialization(
                                specialization);

                settings.setLicense(
                                license);

                dao.updateSettings(
                                settings);
        }

        // =====================================================
        // NOTIFICATIONS
        // =====================================================

        public void saveNotifications(
                        boolean appointmentReminders,
                        boolean messageNotifications,
                        boolean emailNotifications) {

                dao.updateNotifications(
                                appointmentReminders,
                                messageNotifications,
                                emailNotifications);
        }

        // =====================================================
        // APPEARANCE
        // =====================================================

        public void saveAppearance(
                        String appearance) {

                dao.updateAppearance(
                                appearance);
        }

        // =====================================================
        // LANGUAGE
        // =====================================================

        public void saveLanguage(
                        String language) {

                dao.updateLanguage(
                                language);
        }

        // =====================================================
        // PASSWORD
        // =====================================================

        public boolean changePassword(
                        String oldPassword,
                        String newPassword,
                        String confirmPassword) {

                if (oldPassword == null
                                || newPassword == null
                                || confirmPassword == null) {

                        return false;
                }

                if (oldPassword.isEmpty()
                                || newPassword.isEmpty()
                                || confirmPassword.isEmpty()) {

                        return false;
                }

                if (!newPassword.equals(
                                confirmPassword)) {

                        return false;
                }

                if (newPassword.length() < 6) {

                        return false;
                }

                System.out.println(
                                "Password changed successfully.");

                return true;
        }
}