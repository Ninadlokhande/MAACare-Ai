
package com.sigma.dao.doctorDao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.model.DoctorModel.Settings;

import java.util.HashMap;
import java.util.Map;

public class SettingsDAO {

        private static final String COLLECTION = "doctorSettings";

        private static final String DOCUMENT_ID = "D001";

        private final Firestore firestore;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public SettingsDAO() {

                firestore = FirebaseConfig.getFirestore();
        }

        // =========================================================
        // GET SETTINGS
        // =========================================================

        public Settings getSettings() {

                Settings settings = new Settings();

                try {

                        DocumentSnapshot document = firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                System.out.println(
                                                "[SETTINGS] No settings found. "
                                                                + "Using default settings.");

                                return settings;
                        }

                        // -------------------------------------------------
                        // PROFILE
                        // -------------------------------------------------

                        settings.setFullName(
                                        getString(document, "fullName"));

                        settings.setEmail(
                                        getString(document, "email"));

                        settings.setPhone(
                                        getString(document, "phone"));

                        settings.setSpecialization(
                                        getString(document, "specialization"));

                        settings.setQualification(
                                        getString(document, "qualification"));

                        settings.setLicense(
                                        getString(document, "license"));

                        settings.setExperience(
                                        getString(document, "experience"));

                        // -------------------------------------------------
                        // CLINIC
                        // -------------------------------------------------

                        settings.setClinicName(
                                        getString(document, "clinicName"));

                        settings.setClinicAddress(
                                        getString(document, "clinicAddress"));

                        // -------------------------------------------------
                        // AVAILABILITY
                        // -------------------------------------------------

                        settings.setConsultationDays(
                                        getString(
                                                        document,
                                                        "consultationDays"));

                        settings.setStartTime(
                                        getString(
                                                        document,
                                                        "startTime"));

                        settings.setEndTime(
                                        getString(
                                                        document,
                                                        "endTime"));

                        // -------------------------------------------------
                        // APPOINTMENTS
                        // -------------------------------------------------

                        settings.setAppointmentDuration(
                                        getString(
                                                        document,
                                                        "appointmentDuration"));

                        settings.setAutoConfirmAppointments(
                                        getBoolean(
                                                        document,
                                                        "autoConfirmAppointments"));

                        settings.setAppointmentReminders(
                                        getBoolean(
                                                        document,
                                                        "appointmentReminders"));

                        // -------------------------------------------------
                        // NOTIFICATIONS
                        // -------------------------------------------------

                        settings.setMessageNotifications(
                                        getBoolean(
                                                        document,
                                                        "messageNotifications"));

                        settings.setEmailNotifications(
                                        getBoolean(
                                                        document,
                                                        "emailNotifications"));

                        settings.setReportNotifications(
                                        getBoolean(
                                                        document,
                                                        "reportNotifications"));

                        // -------------------------------------------------
                        // APPEARANCE
                        // -------------------------------------------------

                        settings.setAppearance(
                                        getString(
                                                        document,
                                                        "appearance"));

                        settings.setLanguage(
                                        getString(
                                                        document,
                                                        "language"));

                        System.out.println(
                                        "[SETTINGS] Settings loaded successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "[SETTINGS ERROR] Unable to load settings.");

                        e.printStackTrace();
                }

                return settings;
        }

        // =========================================================
        // UPDATE ALL SETTINGS
        // =========================================================

        public void updateSettings(
                        Settings settings) {

                if (settings == null) {
                        return;
                }

                try {

                        Map<String, Object> data = new HashMap<>();

                        // -------------------------------------------------
                        // PROFILE
                        // -------------------------------------------------

                        data.put(
                                        "fullName",
                                        settings.getFullName());

                        data.put(
                                        "email",
                                        settings.getEmail());

                        data.put(
                                        "phone",
                                        settings.getPhone());

                        data.put(
                                        "specialization",
                                        settings.getSpecialization());

                        data.put(
                                        "qualification",
                                        settings.getQualification());

                        data.put(
                                        "license",
                                        settings.getLicense());

                        data.put(
                                        "experience",
                                        settings.getExperience());

                        // -------------------------------------------------
                        // CLINIC
                        // -------------------------------------------------

                        data.put(
                                        "clinicName",
                                        settings.getClinicName());

                        data.put(
                                        "clinicAddress",
                                        settings.getClinicAddress());

                        // -------------------------------------------------
                        // AVAILABILITY
                        // -------------------------------------------------

                        data.put(
                                        "consultationDays",
                                        settings.getConsultationDays());

                        data.put(
                                        "startTime",
                                        settings.getStartTime());

                        data.put(
                                        "endTime",
                                        settings.getEndTime());

                        // -------------------------------------------------
                        // APPOINTMENT
                        // -------------------------------------------------

                        data.put(
                                        "appointmentDuration",
                                        settings.getAppointmentDuration());

                        data.put(
                                        "autoConfirmAppointments",
                                        settings.isAutoConfirmAppointments());

                        data.put(
                                        "appointmentReminders",
                                        settings.isAppointmentReminders());

                        // -------------------------------------------------
                        // NOTIFICATIONS
                        // -------------------------------------------------

                        data.put(
                                        "messageNotifications",
                                        settings.isMessageNotifications());

                        data.put(
                                        "emailNotifications",
                                        settings.isEmailNotifications());

                        data.put(
                                        "reportNotifications",
                                        settings.isReportNotifications());

                        // -------------------------------------------------
                        // APPEARANCE
                        // -------------------------------------------------

                        data.put(
                                        "appearance",
                                        settings.getAppearance());

                        data.put(
                                        "language",
                                        settings.getLanguage());

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                        System.out.println(
                                        "[SETTINGS] Settings saved successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "[SETTINGS ERROR] Unable to save settings.");

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to save doctor settings.",
                                        e);
                }
        }

        // =========================================================
        // NOTIFICATIONS
        // =========================================================

        public void updateNotifications(
                        boolean appointmentReminders,
                        boolean messageNotifications,
                        boolean emailNotifications,
                        boolean reportNotifications) {

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "appointmentReminders",
                                        appointmentReminders);

                        data.put(
                                        "messageNotifications",
                                        messageNotifications);

                        data.put(
                                        "emailNotifications",
                                        emailNotifications);

                        data.put(
                                        "reportNotifications",
                                        reportNotifications);

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to update notifications.",
                                        e);
                }
        }

        // =========================================================
        // APPEARANCE
        // =========================================================

        public void updateAppearance(
                        String appearance) {

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "appearance",
                                        appearance);

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to update appearance.",
                                        e);
                }
        }

        // =========================================================
        // LANGUAGE
        // =========================================================

        public void updateLanguage(
                        String language) {

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "language",
                                        language);

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to update language.",
                                        e);
                }
        }

        // =========================================================
        // AVAILABILITY
        // =========================================================

        public void updateAvailability(
                        String consultationDays,
                        String startTime,
                        String endTime) {

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "consultationDays",
                                        consultationDays);

                        data.put(
                                        "startTime",
                                        startTime);

                        data.put(
                                        "endTime",
                                        endTime);

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to update availability.",
                                        e);
                }
        }

        // =========================================================
        // APPOINTMENT SETTINGS
        // =========================================================

        public void updateAppointmentSettings(
                        String duration,
                        boolean autoConfirm,
                        boolean reminders) {

                try {

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "appointmentDuration",
                                        duration);

                        data.put(
                                        "autoConfirmAppointments",
                                        autoConfirm);

                        data.put(
                                        "appointmentReminders",
                                        reminders);

                        firestore
                                        .collection(COLLECTION)
                                        .document(DOCUMENT_ID)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(
                                        "Unable to update appointment settings.",
                                        e);
                }
        }

        // =========================================================
        // HELPERS
        // =========================================================

        private String getString(
                        DocumentSnapshot document,
                        String field) {

                String value = document.getString(field);

                return value == null
                                ? ""
                                : value;
        }

        private boolean getBoolean(
                        DocumentSnapshot document,
                        String field) {

                Boolean value = document.getBoolean(field);

                return value != null && value;
        }
}
