
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.SettingsController;
import com.sigma.model.DoctorModel.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SettingsPage {

        // ================= COLORS =================

        private static final String PINK = "#E84A87";
        private static final String DARK_PINK = "#D93678";
        private static final String LIGHT_PINK = "#FFEAF3";

        private static final String PURPLE = "#9B4DCC";
        private static final String LIGHT_PURPLE = "#F3ECFF";

        private static final String TEXT = "#24234F";
        private static final String SECONDARY = "#77778D";
        private static final String BORDER = "#E7DCE8";
        private static final String WHITE = "#FFFFFF";

        private final SettingsController controller;

        // Profile
        private TextField fullNameField;
        private TextField emailField;
        private TextField phoneField;
        private TextField specializationField;
        private TextField qualificationField;
        private TextField licenseField;
        private TextField experienceField;

        // Clinic
        private TextField clinicNameField;
        private TextField clinicAddressField;

        // Availability
        private ComboBox<String> daysCombo;
        private ComboBox<String> startTimeCombo;
        private ComboBox<String> endTimeCombo;

        // Appointment
        private ComboBox<String> durationCombo;
        private CheckBox autoConfirmCheck;

        // Notifications
        private CheckBox appointmentReminderCheck;
        private CheckBox messageNotificationCheck;
        private CheckBox emailNotificationCheck;
        private CheckBox reportNotificationCheck;

        // Security
        private PasswordField oldPasswordField;
        private PasswordField newPasswordField;
        private PasswordField confirmPasswordField;

        // Appearance
        private ComboBox<String> appearanceCombo;
        private ComboBox<String> languageCombo;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public SettingsPage() {

                controller = new SettingsController();
        }

        // =========================================================
        // DISPLAY SETTINGS PAGE
        // =========================================================
        //
        // This method is called from DoctorDashboard:
        //
        // SettingsPage.display();
        //
        // It uses the SAME Dashboard Stage.
        // No new Stage is created.
        // =========================================================

        public static void display() {

                SettingsPage settingsPage = new SettingsPage();

                Scene settingsScene = settingsPage.getScene();

                DoctorDashboard.changeScene(
                                settingsScene);
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public Scene getScene() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: #FAF9FC;");

                // ================= HEADER =================

                VBox header = new VBox(5);

                header.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                15,
                                                30));

                header.setStyle(
                                "-fx-background-color: white; "
                                                + "-fx-border-color: "
                                                + BORDER + "; "
                                                + "-fx-border-width: "
                                                + "0 0 1 0;");

                Button backButton = createBackButton();

                Label title = new Label("Settings");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                title.setTextFill(
                                Color.web(TEXT));

                Label subtitle = new Label(
                                "Manage your professional profile, clinic information, appointments and preferences.");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                subtitle.setTextFill(
                                Color.web(SECONDARY));

                header.getChildren()
                                .addAll(
                                                backButton,
                                                title,
                                                subtitle);

                root.setTop(header);

                // ================= CONTENT =================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                30,
                                                30));

                // Intro
                content.getChildren()
                                .add(
                                                createInfoCard(
                                                                "Doctor Settings",
                                                                "Update your information and preferences. "
                                                                                + "Changes will be saved to your doctor profile."));

                // Sections
                content.getChildren()
                                .add(
                                                createProfessionalProfileSection());

                content.getChildren()
                                .add(
                                                createClinicSection());

                content.getChildren()
                                .add(
                                                createAvailabilitySection());

                content.getChildren()
                                .add(
                                                createAppointmentSection());

                content.getChildren()
                                .add(
                                                createNotificationSection());

                content.getChildren()
                                .add(
                                                createSecuritySection());

                content.getChildren()
                                .add(
                                                createAppearanceSection());

                content.getChildren()
                                .add(
                                                createPrivacySection());

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent; "
                                                + "-fx-border-color: transparent;");

                root.setCenter(scrollPane);

                loadSettings();

                return new Scene(
                                root,
                                1200,
                                800);
        }

        // =========================================================
        // PROFESSIONAL PROFILE
        // =========================================================

        private VBox createProfessionalProfileSection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "👨‍⚕ Professional Profile",
                                                                "Enter your professional and medical information."));

                fullNameField = new TextField();
                emailField = new TextField();
                phoneField = new TextField();
                specializationField = new TextField();
                qualificationField = new TextField();
                licenseField = new TextField();
                experienceField = new TextField();

                box.getChildren()
                                .add(
                                                createField(
                                                                "Full Name",
                                                                "Enter your complete name as it should appear to patients.",
                                                                "e.g. Dr. Priya Sharma",
                                                                fullNameField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Email Address",
                                                                "Use an active email address for important communication.",
                                                                "e.g. doctor@example.com",
                                                                emailField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Phone Number",
                                                                "Enter the phone number patients can use to contact you.",
                                                                "e.g. +91 9876543210",
                                                                phoneField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Specialization",
                                                                "Mention your medical specialization.",
                                                                "e.g. Gynecologist",
                                                                specializationField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Qualification",
                                                                "Enter your medical degrees or qualifications.",
                                                                "e.g. MBBS, MD",
                                                                qualificationField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Medical License",
                                                                "Enter your valid medical registration/license number.",
                                                                "e.g. MMC123456",
                                                                licenseField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Experience",
                                                                "Mention your total experience in the medical field.",
                                                                "e.g. 8 Years",
                                                                experienceField));

                Button saveButton = createPrimaryButton(
                                "Save Professional Profile");

                saveButton.setOnAction(e -> {

                        controller.saveProfile(
                                        fullNameField.getText(),
                                        specializationField.getText(),
                                        qualificationField.getText(),
                                        licenseField.getText(),
                                        experienceField.getText());

                        showSuccess(
                                        "Professional profile updated successfully.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // CLINIC
        // =========================================================

        private VBox createClinicSection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🏥 Clinic & Contact Information",
                                                                "Add your clinic details so patients know where and how to contact you."));

                clinicNameField = new TextField();

                clinicAddressField = new TextField();

                box.getChildren()
                                .add(
                                                createField(
                                                                "Clinic Name",
                                                                "Enter the official name of your clinic or hospital.",
                                                                "e.g. MaaCare Women's Clinic",
                                                                clinicNameField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Clinic Address",
                                                                "Enter the complete clinic address including area and city.",
                                                                "e.g. 123 Main Road, Kothrud, Pune",
                                                                clinicAddressField));

                Button saveButton = createPrimaryButton(
                                "Save Clinic Information");

                saveButton.setOnAction(e -> {

                        controller.saveClinic(
                                        emailField.getText(),
                                        phoneField.getText(),
                                        clinicNameField.getText(),
                                        clinicAddressField.getText());

                        showSuccess(
                                        "Clinic information updated successfully.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // AVAILABILITY
        // =========================================================

        private VBox createAvailabilitySection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🗓 Consultation Availability",
                                                                "Select the days and time when patients can book appointments."));

                daysCombo = new ComboBox<>();

                daysCombo.getItems()
                                .addAll(
                                                "Monday - Friday",
                                                "Monday - Saturday",
                                                "Monday, Wednesday & Friday",
                                                "Tuesday, Thursday & Saturday",
                                                "All Days");

                daysCombo.setValue(
                                "Monday - Friday");

                startTimeCombo = new ComboBox<>();

                startTimeCombo.getItems()
                                .addAll(
                                                "08:00 AM",
                                                "09:00 AM",
                                                "10:00 AM",
                                                "11:00 AM",
                                                "12:00 PM",
                                                "01:00 PM",
                                                "02:00 PM",
                                                "03:00 PM",
                                                "04:00 PM",
                                                "05:00 PM",
                                                "06:00 PM",
                                                "07:00 PM");

                startTimeCombo.setValue(
                                "10:00 AM");

                endTimeCombo = new ComboBox<>();

                endTimeCombo.getItems()
                                .addAll(
                                                "12:00 PM",
                                                "01:00 PM",
                                                "02:00 PM",
                                                "03:00 PM",
                                                "04:00 PM",
                                                "05:00 PM",
                                                "06:00 PM",
                                                "07:00 PM",
                                                "08:00 PM",
                                                "09:00 PM");

                endTimeCombo.setValue(
                                "05:00 PM");

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Available Days",
                                                                "Choose the days on which you accept patient appointments.",
                                                                daysCombo));

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Starting Time",
                                                                "Select the time from which consultations begin.",
                                                                startTimeCombo));

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Closing Time",
                                                                "Select the time after which new appointments should not be booked.",
                                                                endTimeCombo));

                Button saveButton = createPrimaryButton(
                                "Save Availability");

                saveButton.setOnAction(e -> {

                        controller.saveAvailability(
                                        daysCombo.getValue(),
                                        startTimeCombo.getValue(),
                                        endTimeCombo.getValue());

                        showSuccess(
                                        "Consultation availability saved.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // APPOINTMENTS
        // =========================================================

        private VBox createAppointmentSection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "📅 Appointment Preferences",
                                                                "Control how patient appointments are scheduled and confirmed."));

                durationCombo = new ComboBox<>();

                durationCombo.getItems()
                                .addAll(
                                                "15 Minutes",
                                                "30 Minutes",
                                                "45 Minutes",
                                                "60 Minutes");

                durationCombo.setValue(
                                "30 Minutes");

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Appointment Duration",
                                                                "Choose how much time should be reserved for each patient.",
                                                                durationCombo));

                autoConfirmCheck = new CheckBox(
                                "Automatically confirm patient appointments");

                autoConfirmCheck.setSelected(false);

                autoConfirmCheck.setTextFill(
                                Color.web(TEXT));

                autoConfirmCheck.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                Label autoConfirmHint = new Label(
                                "When enabled, new appointment requests will be confirmed automatically. "
                                                + "When disabled, you can review them before confirming.");

                autoConfirmHint.setWrapText(true);

                autoConfirmHint.setTextFill(
                                Color.web(SECONDARY));

                autoConfirmHint.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                VBox autoConfirmBox = new VBox(5);

                autoConfirmBox.setPadding(
                                new Insets(
                                                8,
                                                0,
                                                8,
                                                0));

                autoConfirmBox.getChildren()
                                .addAll(
                                                autoConfirmCheck,
                                                autoConfirmHint);

                box.getChildren()
                                .add(autoConfirmBox);

                Button saveButton = createPrimaryButton(
                                "Save Appointment Preferences");

                saveButton.setOnAction(e -> {

                        controller.saveAppointmentSettings(
                                        durationCombo.getValue(),
                                        autoConfirmCheck.isSelected(),
                                        appointmentReminderCheck != null
                                                        && appointmentReminderCheck.isSelected());

                        showSuccess(
                                        "Appointment preferences saved.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // NOTIFICATIONS
        // =========================================================

        private VBox createNotificationSection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🔔 Notification Preferences",
                                                                "Choose which notifications you want to receive."));

                appointmentReminderCheck = new CheckBox(
                                "Appointment Reminders");

                messageNotificationCheck = new CheckBox(
                                "Patient Messages");

                emailNotificationCheck = new CheckBox(
                                "Email Notifications");

                reportNotificationCheck = new CheckBox(
                                "Report Notifications");

                appointmentReminderCheck.setSelected(true);
                messageNotificationCheck.setSelected(true);
                emailNotificationCheck.setSelected(true);
                reportNotificationCheck.setSelected(true);

                box.getChildren()
                                .add(
                                                createCheckBoxRow(
                                                                appointmentReminderCheck,
                                                                "Get reminders about upcoming appointments, missed appointments and schedule changes."));

                box.getChildren()
                                .add(
                                                createCheckBoxRow(
                                                                messageNotificationCheck,
                                                                "Receive a notification when a patient sends a new message or enquiry."));

                box.getChildren()
                                .add(
                                                createCheckBoxRow(
                                                                emailNotificationCheck,
                                                                "Receive important account and appointment updates through your registered email."));

                box.getChildren()
                                .add(
                                                createCheckBoxRow(
                                                                reportNotificationCheck,
                                                                "Get notified when patient reports are uploaded or their status is updated."));

                Button saveButton = createPrimaryButton(
                                "Save Notification Preferences");

                saveButton.setOnAction(e -> {

                        controller.saveNotifications(
                                        appointmentReminderCheck.isSelected(),
                                        messageNotificationCheck.isSelected(),
                                        emailNotificationCheck.isSelected(),
                                        reportNotificationCheck.isSelected());

                        showSuccess(
                                        "Notification preferences saved.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // SECURITY
        // =========================================================

        private VBox createSecuritySection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🔐 Security",
                                                                "Change your account password and keep your doctor account secure."));

                oldPasswordField = new PasswordField();

                newPasswordField = new PasswordField();

                confirmPasswordField = new PasswordField();

                box.getChildren()
                                .add(
                                                createField(
                                                                "Current Password",
                                                                "Enter your existing password to verify your account.",
                                                                "Enter current password",
                                                                oldPasswordField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "New Password",
                                                                "Use at least 6 characters. A stronger password is recommended.",
                                                                "Enter new password",
                                                                newPasswordField));

                box.getChildren()
                                .add(
                                                createField(
                                                                "Confirm New Password",
                                                                "Re-enter the new password. Both passwords must match.",
                                                                "Re-enter new password",
                                                                confirmPasswordField));

                Button changePasswordButton = createPrimaryButton(
                                "Change Password");

                changePasswordButton.setOnAction(e -> {

                        boolean success = controller.changePassword(
                                        oldPasswordField.getText(),
                                        newPasswordField.getText(),
                                        confirmPasswordField.getText());

                        if (success) {

                                showSuccess(
                                                "Password updated successfully.");

                                oldPasswordField.clear();
                                newPasswordField.clear();
                                confirmPasswordField.clear();

                        } else {

                                showError(
                                                "Password change failed. "
                                                                + "Check the entered passwords and make sure the new password has at least 6 characters.");
                        }
                });

                box.getChildren()
                                .add(changePasswordButton);

                return box;
        }

        // =========================================================
        // APPEARANCE
        // =========================================================

        private VBox createAppearanceSection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🎨 Appearance & Language",
                                                                "Customize how the application looks and which language is displayed."));

                appearanceCombo = new ComboBox<>();

                appearanceCombo.getItems()
                                .addAll(
                                                "Light",
                                                "Dark",
                                                "System Default");

                appearanceCombo.setValue(
                                "Light");

                languageCombo = new ComboBox<>();

                languageCombo.getItems()
                                .addAll(
                                                "English",
                                                "Hindi",
                                                "Marathi");

                languageCombo.setValue(
                                "English");

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Application Theme",
                                                                "Select the appearance of the MaaCare AI application.",
                                                                appearanceCombo));

                box.getChildren()
                                .add(
                                                createComboField(
                                                                "Application Language",
                                                                "Choose the language you want to use in the application.",
                                                                languageCombo));

                Button saveButton = createPrimaryButton(
                                "Save Appearance Settings");

                saveButton.setOnAction(e -> {

                        controller.saveAppearance(
                                        appearanceCombo.getValue());

                        controller.saveLanguage(
                                        languageCombo.getValue());

                        showSuccess(
                                        "Appearance and language settings saved.");
                });

                box.getChildren()
                                .add(saveButton);

                return box;
        }

        // =========================================================
        // PRIVACY
        // =========================================================

        private VBox createPrivacySection() {

                VBox box = createSectionBox();

                box.getChildren()
                                .add(
                                                createSectionHeader(
                                                                "🛡 Privacy & Data",
                                                                "Manage your account data and privacy-related actions."));

                Label privacyText = new Label(
                                "Your profile and professional information are stored securely. "
                                                + "Use these options only when you need to manage your account data.");

                privacyText.setWrapText(true);

                privacyText.setTextFill(
                                Color.web(SECONDARY));

                privacyText.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                Button exportButton = createLightPurpleButton(
                                "Export My Data");

                Button deleteButton = createLightPurpleButton(
                                "Delete Account");

                exportButton.setOnAction(e -> showInfo(
                                "Export Data",
                                "Data export functionality can be connected to Firebase when required."));

                deleteButton.setOnAction(e -> showInfo(
                                "Delete Account",
                                "Account deletion requires Firebase Authentication and Firestore deletion handling."));

                HBox buttons = new HBox(12);

                buttons.getChildren()
                                .addAll(
                                                exportButton,
                                                deleteButton);

                box.getChildren()
                                .addAll(
                                                privacyText,
                                                buttons);

                return box;
        }

        // =========================================================
        // LOAD SETTINGS
        // =========================================================

        private void loadSettings() {

                try {

                        Settings settings = controller.getDoctorSettings();

                        if (settings == null) {
                                return;
                        }

                        fullNameField.setText(
                                        safe(settings.getFullName()));

                        emailField.setText(
                                        safe(settings.getEmail()));

                        phoneField.setText(
                                        safe(settings.getPhone()));

                        specializationField.setText(
                                        safe(settings.getSpecialization()));

                        qualificationField.setText(
                                        safe(settings.getQualification()));

                        licenseField.setText(
                                        safe(settings.getLicense()));

                        experienceField.setText(
                                        safe(settings.getExperience()));

                        clinicNameField.setText(
                                        safe(settings.getClinicName()));

                        clinicAddressField.setText(
                                        safe(settings.getClinicAddress()));

                        if (settings.getConsultationDays() != null) {

                                daysCombo.setValue(
                                                settings.getConsultationDays());
                        }

                        if (settings.getStartTime() != null) {

                                startTimeCombo.setValue(
                                                settings.getStartTime());
                        }

                        if (settings.getEndTime() != null) {

                                endTimeCombo.setValue(
                                                settings.getEndTime());
                        }

                        if (settings.getAppointmentDuration() != null) {

                                durationCombo.setValue(
                                                settings.getAppointmentDuration());
                        }

                        autoConfirmCheck.setSelected(
                                        settings.isAutoConfirmAppointments());

                        appointmentReminderCheck.setSelected(
                                        settings.isAppointmentReminders());

                        messageNotificationCheck.setSelected(
                                        settings.isMessageNotifications());

                        reportNotificationCheck.setSelected(
                                        settings.isReportNotifications());

                        emailNotificationCheck.setSelected(
                                        settings.isEmailNotifications());

                        if (settings.getAppearance() != null) {

                                appearanceCombo.setValue(
                                                settings.getAppearance());
                        }

                        if (settings.getLanguage() != null) {

                                languageCombo.setValue(
                                                settings.getLanguage());
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Unable to load settings: "
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // SECTION HEADER
        // =========================================================

        private VBox createSectionHeader(
                        String title,
                        String description) {

                VBox box = new VBox(4);

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                titleLabel.setTextFill(
                                Color.web(TEXT));

                Label descriptionLabel = new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                descriptionLabel.setTextFill(
                                Color.web(SECONDARY));

                box.getChildren()
                                .addAll(
                                                titleLabel,
                                                descriptionLabel);

                return box;
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private VBox createField(
                        String label,
                        String hint,
                        String prompt,
                        TextField field) {

                VBox box = new VBox(5);

                Label labelText = new Label(label);

                labelText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                labelText.setTextFill(
                                Color.web(TEXT));

                Label hintText = new Label(hint);

                hintText.setWrapText(true);

                hintText.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                hintText.setTextFill(
                                Color.web(SECONDARY));

                field.setPromptText(prompt);

                field.setPrefHeight(38);

                field.setStyle(
                                "-fx-background-radius: 8; "
                                                + "-fx-border-radius: 8; "
                                                + "-fx-border-color: "
                                                + BORDER + "; "
                                                + "-fx-background-color: white;");

                box.getChildren()
                                .addAll(
                                                labelText,
                                                hintText,
                                                field);

                return box;
        }

        // =========================================================
        // COMBO BOX FIELD
        // =========================================================

        private VBox createComboField(
                        String label,
                        String hint,
                        ComboBox<String> combo) {

                VBox box = new VBox(5);

                Label labelText = new Label(label);

                labelText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                labelText.setTextFill(
                                Color.web(TEXT));

                Label hintText = new Label(hint);

                hintText.setWrapText(true);

                hintText.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                hintText.setTextFill(
                                Color.web(SECONDARY));

                combo.setMaxWidth(
                                Double.MAX_VALUE);

                combo.setPrefHeight(38);

                box.getChildren()
                                .addAll(
                                                labelText,
                                                hintText,
                                                combo);

                return box;
        }

        // =========================================================
        // CHECKBOX ROW
        // =========================================================

        private VBox createCheckBoxRow(
                        CheckBox checkBox,
                        String description) {

                VBox box = new VBox(4);

                checkBox.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                checkBox.setTextFill(
                                Color.web(TEXT));

                Label descriptionLabel = new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setPadding(
                                new Insets(
                                                0,
                                                0,
                                                0,
                                                25));

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                descriptionLabel.setTextFill(
                                Color.web(SECONDARY));

                box.setPadding(
                                new Insets(
                                                5,
                                                0,
                                                5,
                                                0));

                box.getChildren()
                                .addAll(
                                                checkBox,
                                                descriptionLabel);

                return box;
        }

        // =========================================================
        // SECTION BOX
        // =========================================================

        private VBox createSectionBox() {

                VBox box = new VBox(15);

                box.setPadding(
                                new Insets(20));

                box.setStyle(
                                "-fx-background-color: white; "
                                                + "-fx-background-radius: 14; "
                                                + "-fx-border-radius: 14; "
                                                + "-fx-border-color: "
                                                + BORDER + ";");

                return box;
        }

        // =========================================================
        // INFO CARD
        // =========================================================

        private VBox createInfoCard(
                        String title,
                        String description) {

                VBox box = new VBox(5);

                box.setPadding(
                                new Insets(18));

                box.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PINK + "; "
                                                + "-fx-background-radius: 12;");

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                titleLabel.setTextFill(
                                Color.web(DARK_PINK));

                Label descriptionLabel = new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                descriptionLabel.setTextFill(
                                Color.web(TEXT));

                box.getChildren()
                                .addAll(
                                                titleLabel,
                                                descriptionLabel);

                return box;
        }

        // =========================================================
        // BUTTONS
        // =========================================================

        private Button createPrimaryButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefHeight(38);

                button.setPadding(
                                new Insets(
                                                8,
                                                18,
                                                8,
                                                18));

                button.setStyle(
                                "-fx-background-color: "
                                                + PINK + "; "
                                                + "-fx-text-fill: white; "
                                                + "-fx-font-family: Arial; "
                                                + "-fx-font-weight: bold; "
                                                + "-fx-background-radius: 8;");

                button.setOnMouseEntered(e -> button.setStyle(
                                "-fx-background-color: "
                                                + DARK_PINK + "; "
                                                + "-fx-text-fill: white; "
                                                + "-fx-font-family: Arial; "
                                                + "-fx-font-weight: bold; "
                                                + "-fx-background-radius: 8;"));

                button.setOnMouseExited(e -> button.setStyle(
                                "-fx-background-color: "
                                                + PINK + "; "
                                                + "-fx-text-fill: white; "
                                                + "-fx-font-family: Arial; "
                                                + "-fx-font-weight: bold; "
                                                + "-fx-background-radius: 8;"));

                return button;
        }

        private Button createLightPurpleButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefHeight(36);

                button.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PURPLE + "; "
                                                + "-fx-text-fill: "
                                                + PURPLE + "; "
                                                + "-fx-font-family: Arial; "
                                                + "-fx-font-weight: bold; "
                                                + "-fx-background-radius: 8;");

                return button;
        }

        // =========================================================
        // BACK BUTTON
        // =========================================================

        private Button createBackButton() {

                Button button = new Button(
                                "← Back to Dashboard");

                button.setStyle(
                                "-fx-background-color: transparent; "
                                                + "-fx-text-fill: "
                                                + PURPLE + "; "
                                                + "-fx-font-family: Arial; "
                                                + "-fx-font-weight: bold; "
                                                + "-fx-font-size: 13px;");

                button.setOnAction(
                                e -> DoctorDashboard.showDashboard());

                return button;
        }

        // =========================================================
        // ALERTS
        // =========================================================

        private void showSuccess(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle("Success");

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        private void showError(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle("Error");

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        private void showInfo(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }
}
