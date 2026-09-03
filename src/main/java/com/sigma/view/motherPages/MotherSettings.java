package com.sigma.view.motherPages;

import com.sigma.controller.MotherSettingsController;
import com.sigma.model.MotherSettingsModel;
import com.sigma.model.MotherWlcModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MotherSettings {

    private final MotherSettingsController controller;
    private final MotherWlcModel motherModel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherSettings(MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        controller = new MotherSettingsController();
    }


    // =========================================================
    // CREATE SETTINGS PAGE
    // =========================================================

    public VBox createSettingsPage() {

        VBox mainBox = new VBox();

        mainBox.setSpacing(22);

        mainBox.setPadding(
                new Insets(10, 5, 30, 5)
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label title = new Label("Settings");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label subtitle = new Label(
                "Manage your MaaCare AI preferences"
        );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;"
        );


        VBox heading = new VBox();

        heading.setSpacing(5);

        heading.getChildren().addAll(
                title,
                subtitle
        );


        // =====================================================
        // NOTIFICATION SETTINGS CARD
        // =====================================================

        VBox notificationCard = createWhiteCard();


        Label notificationTitle =
                new Label("🔔  Notification Settings");

        notificationTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label notificationSubtitle =
                new Label(
                        "Choose which reminders you want to receive."
                );

        notificationSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        CheckBox notifications =
                createCheckBox("Enable Notifications");


        CheckBox medicineReminder =
                createCheckBox("Medicine Reminders");


        CheckBox appointmentReminder =
                createCheckBox("Appointment Reminders");


        CheckBox vaccinationReminder =
                createCheckBox("Vaccination Reminders");


        VBox notificationOptions =
                new VBox();

        notificationOptions.setSpacing(12);

        notificationOptions.getChildren().addAll(
                notifications,
                medicineReminder,
                appointmentReminder,
                vaccinationReminder
        );


        notificationCard.getChildren().addAll(
                notificationTitle,
                notificationSubtitle,
                notificationOptions
        );


        // =====================================================
        // LANGUAGE PREFERENCE CARD
        // =====================================================

        VBox languageCard = createWhiteCard();


        Label languageTitle =
                new Label("🌐  Language Preference");

        languageTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label languageSubtitle =
                new Label(
                        "Language selection will be available in a future update."
                );

        languageSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        ComboBox<String> language =
                new ComboBox<>();

        language.getItems().addAll(
                "English",
                "Marathi",
                "Hindi"
        );

        language.setPrefWidth(220);

        language.setValue("English");

        // Currently not functional
        language.setDisable(true);


        languageCard.getChildren().addAll(
                languageTitle,
                languageSubtitle,
                language
        );


        // =====================================================
        // LOAD EXISTING SETTINGS
        // =====================================================

        if (motherModel != null
                && motherModel.getMotherId() != null
                && !motherModel.getMotherId().isEmpty()) {

            MotherSettingsModel existingSettings =
                    controller.loadSettings(
                            motherModel.getMotherId()
                    );


            if (existingSettings != null) {

                notifications.setSelected(
                        existingSettings.isNotificationsEnabled()
                );


                medicineReminder.setSelected(
                        existingSettings.isMedicineReminderEnabled()
                );


                appointmentReminder.setSelected(
                        existingSettings.isAppointmentReminderEnabled()
                );


                vaccinationReminder.setSelected(
                        existingSettings.isVaccinationReminderEnabled()
                );


                if (existingSettings.getLanguage() != null) {

                    language.setValue(
                            existingSettings.getLanguage()
                    );
                }
            }
        }


        // =====================================================
        // ACCOUNT CARD
        // =====================================================

        VBox accountCard = createWhiteCard();


        Label accountTitle =
                new Label("👤  Account");

        accountTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label accountSubtitle =
                new Label(
                        "Manage your account."
                );

        accountSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        // =====================================================
        // CHANGE PASSWORD
        // =====================================================

        Button changePasswordButton =
                createActionButton("Change Password");


        changePasswordButton.setOnAction(e -> {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Change Password feature will be connected here."
            );

        });


        // =====================================================
        // LOGOUT
        // =====================================================

        Button logoutButton =
                createActionButton("Logout");


        logoutButton.setOnAction(e -> {

            Alert alert =
                    new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("MaaCare AI");

            alert.setHeaderText("Logout");

            alert.setContentText(
                    "Are you sure you want to logout?"
            );


            alert.showAndWait().ifPresent(response -> {

                if (response ==
                        javafx.scene.control.ButtonType.OK) {

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Logout feature will be connected here."
                    );
                }
            });

        });


        accountCard.getChildren().addAll(
                accountTitle,
                accountSubtitle,
                changePasswordButton,
                logoutButton
        );


        // =====================================================
        // PRIVACY & SECURITY CARD
        // =====================================================

        VBox privacyCard = createWhiteCard();


        Label privacyTitle =
                new Label("🔒  Privacy & Security");

        privacyTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label privacySubtitle =
                new Label(
                        "Manage your privacy and data preferences."
                );

        privacySubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        Button privacyButton =
                createActionButton("Privacy Settings");


        Button dataPrivacyButton =
                createActionButton("Data & Privacy");


        privacyButton.setOnAction(e -> {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Privacy Settings feature will be connected here."
            );

        });


        dataPrivacyButton.setOnAction(e -> {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Data & Privacy feature will be connected here."
            );

        });


        privacyCard.getChildren().addAll(
                privacyTitle,
                privacySubtitle,
                privacyButton,
                dataPrivacyButton
        );


        // =====================================================
        // SAVE BUTTON
        // =====================================================

        Button saveButton =
                new Button("Save Settings");

        saveButton.setPrefHeight(42);

        saveButton.setPrefWidth(170);

        saveButton.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right, #F54B87, #9B4DCC);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 8px 20px;"
        );


        saveButton.setOnAction(e -> {

            if (motherModel == null
                    || motherModel.getMotherId() == null
                    || motherModel.getMotherId().isEmpty()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Mother information is missing."
                );

                return;
            }


            MotherSettingsModel settings =
                    new MotherSettingsModel(

                            motherModel.getMotherId(),

                            notifications.isSelected(),

                            medicineReminder.isSelected(),

                            appointmentReminder.isSelected(),

                            vaccinationReminder.isSelected(),

                            language.getValue()
                    );


            boolean success =
                    controller.updateSettings(settings);


            if (!success) {

                success =
                        controller.saveSettings(settings);
            }


            if (success) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Settings saved successfully!"
                );

            } else {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Failed to save settings."
                );
            }
        });


        // =====================================================
        // ABOUT CARD
        // =====================================================

        VBox aboutCard =
                createWhiteCard();


        Label aboutTitle =
                new Label("ℹ️  About MaaCare AI");

        aboutTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label aboutText =
                new Label(
                        "MaaCare AI is a smart maternal and " +
                        "baby care support system designed to " +
                        "make the pregnancy journey safer and easier."
                );

        aboutText.setWrapText(true);

        aboutText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;" +
                "-fx-line-spacing: 4px;"
        );


        Label version =
                new Label("App Version 1.0");

        version.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #9999AA;"
        );


        aboutCard.getChildren().addAll(
                aboutTitle,
                aboutText,
                version
        );


        // =====================================================
        // SAVE BOX
        // =====================================================

        HBox saveBox = new HBox();

        saveBox.setAlignment(
                Pos.CENTER_LEFT
        );

        saveBox.getChildren().add(
                saveButton
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        mainBox.getChildren().addAll(

                heading,

                notificationCard,

                languageCard,

                accountCard,

                privacyCard,

                saveBox,

                aboutCard
        );


        return mainBox;
    }


    // =========================================================
    // CREATE CHECKBOX
    // =========================================================

    private CheckBox createCheckBox(String text) {

        CheckBox checkBox =
                new CheckBox(text);

        checkBox.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #24234F;"
        );

        return checkBox;
    }


    // =========================================================
    // CREATE ACTION BUTTON
    // =========================================================

    private Button createActionButton(String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(38);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setStyle(
                "-fx-background-color: #F8F5FA;" +
                "-fx-text-fill: #24234F;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8px 14px;"
        );

        return button;
    }


    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
                new VBox();

        card.setSpacing(10);

        card.setPadding(
                new Insets(20)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E7DCE8;" +
                "-fx-border-radius: 18;"
        );


        VBox.setVgrow(
                card,
                Priority.NEVER
        );


        return card;
    }


    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle("MaaCare AI");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}