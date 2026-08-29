package com.sigma.dao.doctorDao;

import com.sigma.model.DoctorModel.Settings;

public class SettingsDAO {

        private Settings settings;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================

        public SettingsDAO() {

                settings = new Settings(
                                "Dr. Anjali Mehta",
                                "anjalimehta@maacare.com",
                                "9876543210",
                                "Obstetrician & Gynecologist",
                                "GYN/2020/12345");
        }

        // =====================================================
        // GET SETTINGS
        // =====================================================

        public Settings getSettings() {

                return settings;
        }

        // =====================================================
        // UPDATE SETTINGS
        // =====================================================

        public void updateSettings(
                        Settings settings) {

                if (settings == null) {
                        return;
                }

                this.settings = settings;

                System.out.println(
                                "Settings updated successfully.");
        }

        // =====================================================
        // UPDATE NOTIFICATIONS
        // =====================================================

        public void updateNotifications(
                        boolean appointmentReminders,
                        boolean messageNotifications,
                        boolean emailNotifications) {

                settings.setAppointmentReminders(
                                appointmentReminders);

                settings.setMessageNotifications(
                                messageNotifications);

                settings.setEmailNotifications(
                                emailNotifications);

                System.out.println(
                                "Notification settings updated.");
        }

        // =====================================================
        // UPDATE APPEARANCE
        // =====================================================

        public void updateAppearance(
                        String appearance) {

                settings.setAppearance(
                                appearance);

                System.out.println(
                                "Appearance updated: "
                                                + appearance);
        }

        // =====================================================
        // UPDATE LANGUAGE
        // =====================================================

        public void updateLanguage(
                        String language) {

                settings.setLanguage(
                                language);

                System.out.println(
                                "Language updated: "
                                                + language);
        }
}