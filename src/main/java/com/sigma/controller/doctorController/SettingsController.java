
package com.sigma.controller.doctorController;

import com.sigma.dao.doctorDao.SettingsDAO;
import com.sigma.model.DoctorModel.Settings;

public class SettingsController {

        private final SettingsDAO dao;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public SettingsController() {
                dao = new SettingsDAO();
        }

        // =========================================================
        // GET SETTINGS
        // =========================================================

        public Settings getDoctorSettings() {
                return dao.getSettings();
        }

        // =========================================================
        // SAVE PROFILE
        // =========================================================

        public boolean saveProfile(
                        String fullName,
                        String specialization,
                        String qualification,
                        String license,
                        String experience) {

                if (isEmpty(fullName)
                                || isEmpty(specialization)) {

                        return false;
                }

                Settings settings = dao.getSettings();

                settings.setFullName(
                                clean(fullName));

                settings.setSpecialization(
                                clean(specialization));

                settings.setQualification(
                                clean(qualification));

                settings.setLicense(
                                clean(license));

                settings.setExperience(
                                clean(experience));

                dao.updateSettings(settings);

                return true;
        }

        // =========================================================
        // SAVE CONTACT / CLINIC
        // =========================================================

        public boolean saveClinic(
                        String email,
                        String phone,
                        String clinicName,
                        String clinicAddress) {

                if (isEmpty(email)
                                || isEmpty(phone)
                                || isEmpty(clinicName)) {

                        return false;
                }

                Settings settings = dao.getSettings();

                settings.setEmail(
                                clean(email));

                settings.setPhone(
                                clean(phone));

                settings.setClinicName(
                                clean(clinicName));

                settings.setClinicAddress(
                                clean(clinicAddress));

                dao.updateSettings(settings);

                return true;
        }

        // =========================================================
        // SAVE AVAILABILITY
        // =========================================================

        public boolean saveAvailability(
                        String consultationDays,
                        String startTime,
                        String endTime) {

                if (isEmpty(consultationDays)
                                || isEmpty(startTime)
                                || isEmpty(endTime)) {

                        return false;
                }

                dao.updateAvailability(
                                clean(consultationDays),
                                clean(startTime),
                                clean(endTime));

                return true;
        }

        // =========================================================
        // SAVE APPOINTMENT SETTINGS
        // =========================================================

        public void saveAppointmentSettings(
                        String duration,
                        boolean autoConfirm,
                        boolean reminders) {

                dao.updateAppointmentSettings(
                                duration,
                                autoConfirm,
                                reminders);
        }

        // =========================================================
        // SAVE NOTIFICATIONS
        // =========================================================

        public void saveNotifications(
                        boolean appointmentReminders,
                        boolean messageNotifications,
                        boolean emailNotifications,
                        boolean reportNotifications) {

                dao.updateNotifications(
                                appointmentReminders,
                                messageNotifications,
                                emailNotifications,
                                reportNotifications);
        }

        // =========================================================
        // APPEARANCE
        // =========================================================

        public void saveAppearance(
                        String appearance) {

                if (isEmpty(appearance)) {
                        return;
                }

                dao.updateAppearance(
                                appearance);
        }

        // =========================================================
        // LANGUAGE
        // =========================================================

        public void saveLanguage(
                        String language) {

                if (isEmpty(language)) {
                        return;
                }

                dao.updateLanguage(
                                language);
        }

        // =========================================================
        // PASSWORD
        // =========================================================

        public boolean changePassword(
                        String oldPassword,
                        String newPassword,
                        String confirmPassword) {

                if (isEmpty(oldPassword)
                                || isEmpty(newPassword)
                                || isEmpty(confirmPassword)) {

                        return false;
                }

                if (!newPassword.equals(
                                confirmPassword)) {

                        return false;
                }

                if (newPassword.length() < 6) {

                        return false;
                }

                /*
                 * IMPORTANT:
                 * This validates the password only.
                 *
                 * Actual Firebase Authentication password
                 * update should be implemented using the
                 * currently logged-in Firebase user.
                 */

                System.out.println(
                                "[SETTINGS] Password validation successful.");

                return true;
        }

        // =========================================================
        // HELPERS
        // =========================================================

        private boolean isEmpty(String value) {

                return value == null
                                || value.trim().isEmpty();
        }

        private String clean(String value) {

                return value == null
                                ? ""
                                : value.trim();
        }
}
