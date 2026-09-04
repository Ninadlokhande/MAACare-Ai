package com.sigma.dao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.sigma.config.FirebaseConfig;
import com.sigma.model.MotherSettingsModel;

import java.util.HashMap;
import java.util.Map;

public class MotherSettingsDAO {

    private final Firestore db;

    private static final String COLLECTION_NAME = "motherSettings";

    public MotherSettingsDAO() {

        db = FirebaseConfig.getFirestore();

        System.out.println(
                "MotherSettingsDAO connected to Firebase"
        );
    }

    // =========================================================
    // SAVE SETTINGS
    // =========================================================

    public boolean saveSettings(
            MotherSettingsModel settings) {

        if (settings == null
                || settings.getMotherId() == null
                || settings.getMotherId().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    convertToMap(settings);

            db.collection(COLLECTION_NAME)
                    .document(settings.getMotherId())
                    .set(data)
                    .get();

            System.out.println(
                    "Mother settings saved successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving mother settings!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET SETTINGS
    // =========================================================

    public MotherSettingsModel getSettings(
            String motherId) {

        if (motherId == null
                || motherId.isEmpty()) {

            return null;
        }

        try {

            DocumentSnapshot document =
                    db.collection(COLLECTION_NAME)
                            .document(motherId)
                            .get()
                            .get();

            if (document.exists()) {

                System.out.println(
                        "Mother settings fetched successfully!"
                );

                return convertFromDocument(document);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error fetching mother settings!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE SETTINGS
    // =========================================================

    public boolean updateSettings(
            MotherSettingsModel settings) {

        if (settings == null
                || settings.getMotherId() == null
                || settings.getMotherId().isEmpty()) {

            return false;
        }

        try {

            Map<String, Object> data =
                    convertToMap(settings);

            db.collection(COLLECTION_NAME)
                    .document(settings.getMotherId())
                    .set(data)
                    .get();

            System.out.println(
                    "Mother settings updated successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error updating mother settings!"
            );

            e.printStackTrace();

            return false;
        }
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

        try {

            db.collection(COLLECTION_NAME)
                    .document(motherId)
                    .delete()
                    .get();

            System.out.println(
                    "Mother settings deleted successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting mother settings!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CONVERT MODEL → FIRESTORE MAP
    // =========================================================

    private Map<String, Object> convertToMap(
            MotherSettingsModel settings) {

        Map<String, Object> data =
                new HashMap<>();

        data.put(
                "motherId",
                settings.getMotherId()
        );

        data.put(
                "notificationsEnabled",
                settings.isNotificationsEnabled()
        );

        data.put(
                "medicineReminderEnabled",
                settings.isMedicineReminderEnabled()
        );

        data.put(
                "appointmentReminderEnabled",
                settings.isAppointmentReminderEnabled()
        );

        data.put(
                "vaccinationReminderEnabled",
                settings.isVaccinationReminderEnabled()
        );

        data.put(
                "language",
                settings.getLanguage()
        );

        return data;
    }

    // =========================================================
    // CONVERT FIRESTORE → MODEL
    // =========================================================

    private MotherSettingsModel convertFromDocument(
            DocumentSnapshot document) {

        MotherSettingsModel settings =
                new MotherSettingsModel();

        settings.setMotherId(
                document.getString("motherId")
        );

        Boolean notifications =
                document.getBoolean(
                        "notificationsEnabled"
                );

        if (notifications != null) {
            settings.setNotificationsEnabled(
                    notifications
            );
        }

        Boolean medicineReminder =
                document.getBoolean(
                        "medicineReminderEnabled"
                );

        if (medicineReminder != null) {
            settings.setMedicineReminderEnabled(
                    medicineReminder
            );
        }

        Boolean appointmentReminder =
                document.getBoolean(
                        "appointmentReminderEnabled"
                );

        if (appointmentReminder != null) {
            settings.setAppointmentReminderEnabled(
                    appointmentReminder
            );
        }

        Boolean vaccinationReminder =
                document.getBoolean(
                        "vaccinationReminderEnabled"
                );

        if (vaccinationReminder != null) {
            settings.setVaccinationReminderEnabled(
                    vaccinationReminder
            );
        }

        settings.setLanguage(
                document.getString("language")
        );

        return settings;
    }
}