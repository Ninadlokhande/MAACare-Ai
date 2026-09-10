
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.SettingsController;
import com.sigma.model.DoctorModel.Settings;
import com.sigma.view.scenesettings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SettingsPage {

        // =========================================================
        // COLORS
        // SAME MOTHER DASHBOARD BACKGROUND / PAGE COLORS
        // =========================================================

        private static final String PINK = "#E84A87";
        private static final String DARK_PINK = "#C5306D";
        private static final String LIGHT_PINK = "#FFEAF3";

        private static final String PURPLE = "#9B4DCC";
        private static final String DARK_PURPLE = "#7B35A8";
        private static final String LIGHT_PURPLE = "#F3ECFF";

        private static final String TEXT = "#3B2140";
        private static final String SECONDARY = "#806A78";
        private static final String BORDER = "#E7DCE8";

        // MotherDashboard page background
        private static final String PAGE_BACKGROUND = "#FFF8FC";

        // =========================================================
        // CONTROLLER
        // =========================================================

        private final SettingsController controller;

        // =========================================================
        // PROFILE FIELDS
        // =========================================================

        private TextField fullNameField;
        private TextField emailField;
        private TextField phoneField;
        private TextField specializationField;
        private TextField qualificationField;
        private TextField licenseField;
        private TextField experienceField;

        private TextField clinicNameField;
        private TextField clinicAddressField;

        // =========================================================
        // AVAILABILITY
        // =========================================================

        private ComboBox<String> daysCombo;
        private ComboBox<String> startTimeCombo;
        private ComboBox<String> endTimeCombo;

        // =========================================================
        // APPOINTMENTS
        // =========================================================

        private ComboBox<String> durationCombo;
        private CheckBox autoConfirmCheck;

        // =========================================================
        // NOTIFICATIONS
        // =========================================================

        private CheckBox appointmentReminderCheck;
        private CheckBox messageNotificationCheck;
        private CheckBox emailNotificationCheck;
        private CheckBox reportNotificationCheck;

        // =========================================================
        // SECURITY
        // =========================================================

        private PasswordField oldPasswordField;
        private PasswordField newPasswordField;
        private PasswordField confirmPasswordField;

        // =========================================================
        // APPEARANCE
        // =========================================================

        private ComboBox<String> appearanceCombo;
        private ComboBox<String> languageCombo;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public SettingsPage() {
                controller = new SettingsController();
        }

        // =========================================================
        // DISPLAY
        // =========================================================

        public static void display() {

                try {

                        SettingsPage settingsPage = new SettingsPage();

                        Scene scene = settingsPage.getScene();

                        DoctorDashboard.changeScene(scene);

                } catch (Exception e) {

                        System.out.println(
                                        "[SETTINGS] Unable to open Settings page.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // GET SCENE
        // SIDEBAR = LEFT
        // SETTINGS HEADER + CONTENT = CENTER
        // =========================================================

        public Scene getScene() {

                // =====================================================
                // OUTER PAGE
                // =====================================================

                BorderPane page = new BorderPane();

                // MotherDashboard page background
                page.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND
                                                + ";");

                // =====================================================
                // SIDEBAR
                // =====================================================

                page.setLeft(
                                DoctorDashboard.createSidebar(
                                                "Settings"));

                // =====================================================
                // MIDDLE CONTENT ROOT
                // =====================================================

                VBox middleContent = new VBox();

                middleContent.setFillWidth(true);

                // MotherDashboard background
                middleContent.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND
                                                + ";");

                // =====================================================
                // SETTINGS HEADER
                // =====================================================

                VBox header = new VBox(5);

                header.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                15,
                                                30));

                header.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-width: 0 0 1 0;");

                Label title = new Label("Settings");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                title.setTextFill(
                                Color.web(TEXT));

                Label subtitle = new Label(
                                "Manage your professional profile, clinic information, "
                                                + "appointments and preferences.");

                subtitle.setFont(
                                Font.font("Arial", 13));

                subtitle.setTextFill(
                                Color.web(SECONDARY));

                header.getChildren().addAll(
                                title,
                                subtitle);

                // =====================================================
                // SETTINGS CONTENT
                // =====================================================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                30,
                                                30));

                content.setFillWidth(true);

                // MotherDashboard page background
                content.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND
                                                + ";");

                // =====================================================
                // DOCTOR SETTINGS INFO CARD
                // =====================================================

                content.getChildren().add(
                                createInfoCard(
                                                "Doctor Settings",
                                                "Update your information and preferences. "
                                                                + "Changes will be saved to your doctor profile."));

                // =====================================================
                // PROFESSIONAL PROFILE
                // =====================================================

                content.getChildren().add(
                                createProfessionalProfileSection());

                // =====================================================
                // CLINIC
                // =====================================================

                content.getChildren().add(
                                createClinicSection());

                // =====================================================
                // AVAILABILITY
                // =====================================================

                content.getChildren().add(
                                createAvailabilitySection());

                // =====================================================
                // APPOINTMENT
                // =====================================================

                content.getChildren().add(
                                createAppointmentSection());

                // =====================================================
                // NOTIFICATIONS
                // =====================================================

                content.getChildren().add(
                                createNotificationSection());

                // =====================================================
                // SECURITY
                // =====================================================

                content.getChildren().add(
                                createSecuritySection());

                // =====================================================
                // APPEARANCE
                // =====================================================

                content.getChildren().add(
                                createAppearanceSection());

                // =====================================================
                // PRIVACY
                // =====================================================

                content.getChildren().add(
                                createPrivacySection());

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                // MotherDashboard background
                scrollPane.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND + ";"
                                                + "-fx-border-color: transparent;");

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                // =====================================================
                // ADD HEADER + SCROLL CONTENT TO MIDDLE
                // =====================================================

                middleContent.getChildren().addAll(
                                header,
                                scrollPane);

                // =====================================================
                // PUT MIDDLE CONTENT IN CENTER
                // =====================================================

                page.setCenter(
                                middleContent);

                // =====================================================
                // LOAD SETTINGS
                // =====================================================

                loadSettings();

                // =====================================================
                // COMMON SCREEN SIZE
                // =====================================================

                Rectangle2D bounds =
                                scenesettings.rectanguler2d;

                double width =
                                bounds.getWidth();

                double height =
                                bounds.getHeight();

                // =====================================================
                // RETURN SCENE
                // =====================================================

                return new Scene(
                                page,
                                width,
                                height);
        }

        // =========================================================
        // PROFESSIONAL PROFILE
        // =========================================================

        private VBox createProfessionalProfileSection() {

                VBox box = createSectionBox();

                box.getChildren().add(
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

                box.getChildren().add(
                                createField(
                                                "Full Name",
                                                "Enter your complete name as it should appear to patients.",
                                                "e.g. Dr. Priya Sharma",
                                                fullNameField));

                box.getChildren().add(
                                createField(
                                                "Email Address",
                                                "Use an active email address for important communication.",
                                                "e.g. doctor@example.com",
                                                emailField));

                box.getChildren().add(
                                createField(
                                                "Phone Number",
                                                "Enter the phone number patients can use to contact you.",
                                                "e.g. +91 9876543210",
                                                phoneField));

                box.getChildren().add(
                                createField(
                                                "Specialization",
                                                "Mention your medical specialization.",
                                                "e.g. Gynecologist",
                                                specializationField));

                box.getChildren().add(
                                createField(
                                                "Qualification",
                                                "Enter your medical degrees or qualifications.",
                                                "e.g. MBBS, MD",
                                                qualificationField));

                box.getChildren().add(
                                createField(
                                                "Medical License",
                                                "Enter your valid medical registration/license number.",
                                                "e.g. MMC123456",
                                                licenseField));

                box.getChildren().add(
                                createField(
                                                "Experience",
                                                "Mention your total experience in the medical field.",
                                                "e.g. 8 Years",
                                                experienceField));

                Button saveButton =
                                createPrimaryButton(
                                                "Save Professional Profile");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveProfile(
                                                                fullNameField.getText(),
                                                                specializationField.getText(),
                                                                qualificationField.getText(),
                                                                licenseField.getText(),
                                                                experienceField.getText());

                                                showSuccess(
                                                                "Professional profile updated successfully.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save professional profile.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // CLINIC
        // =========================================================

        private VBox createClinicSection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "🏥 Clinic & Contact Information",
                                                "Add your clinic details so patients know where and how to contact you."));

                clinicNameField = new TextField();
                clinicAddressField = new TextField();

                box.getChildren().add(
                                createField(
                                                "Clinic Name",
                                                "Enter the official name of your clinic or hospital.",
                                                "e.g. MaaCare Women's Clinic",
                                                clinicNameField));

                box.getChildren().add(
                                createField(
                                                "Clinic Address",
                                                "Enter the complete clinic address including area and city.",
                                                "e.g. 123 Main Road, Kothrud, Pune",
                                                clinicAddressField));

                Button saveButton =
                                createPrimaryButton(
                                                "Save Clinic Information");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveClinic(
                                                                emailField.getText(),
                                                                phoneField.getText(),
                                                                clinicNameField.getText(),
                                                                clinicAddressField.getText());

                                                showSuccess(
                                                                "Clinic information updated successfully.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save clinic information.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // AVAILABILITY
        // =========================================================

        private VBox createAvailabilitySection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "🗓 Consultation Availability",
                                                "Select the days and time when patients can book appointments."));

                daysCombo = new ComboBox<>();

                daysCombo.getItems().addAll(
                                "Monday - Friday",
                                "Monday - Saturday",
                                "Monday, Wednesday & Friday",
                                "Tuesday, Thursday & Saturday",
                                "All Days");

                daysCombo.setValue(
                                "Monday - Friday");

                startTimeCombo = new ComboBox<>();

                startTimeCombo.getItems().addAll(
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

                endTimeCombo.getItems().addAll(
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

                box.getChildren().add(
                                createComboField(
                                                "Available Days",
                                                "Choose the days on which you accept patient appointments.",
                                                daysCombo));

                box.getChildren().add(
                                createComboField(
                                                "Starting Time",
                                                "Select the time from which consultations begin.",
                                                startTimeCombo));

                box.getChildren().add(
                                createComboField(
                                                "Closing Time",
                                                "Select the time after which new appointments should not be booked.",
                                                endTimeCombo));

                Button saveButton =
                                createPrimaryButton(
                                                "Save Availability");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveAvailability(
                                                                daysCombo.getValue(),
                                                                startTimeCombo.getValue(),
                                                                endTimeCombo.getValue());

                                                showSuccess(
                                                                "Consultation availability saved.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save consultation availability.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // APPOINTMENT
        // =========================================================

        private VBox createAppointmentSection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "📅 Appointment Preferences",
                                                "Control how patient appointments are scheduled and confirmed."));

                durationCombo = new ComboBox<>();

                durationCombo.getItems().addAll(
                                "15 Minutes",
                                "30 Minutes",
                                "45 Minutes",
                                "60 Minutes");

                durationCombo.setValue(
                                "30 Minutes");

                box.getChildren().add(
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
                                Font.font("Arial", 12));

                VBox autoConfirmBox =
                                new VBox(5);

                autoConfirmBox.setPadding(
                                new Insets(
                                                8,
                                                0,
                                                8,
                                                0));

                autoConfirmBox.getChildren().addAll(
                                autoConfirmCheck,
                                autoConfirmHint);

                box.getChildren().add(
                                autoConfirmBox);

                Button saveButton =
                                createPrimaryButton(
                                                "Save Appointment Preferences");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveAppointmentSettings(
                                                                durationCombo.getValue(),
                                                                autoConfirmCheck.isSelected(),
                                                                appointmentReminderCheck != null
                                                                                && appointmentReminderCheck.isSelected());

                                                showSuccess(
                                                                "Appointment preferences saved.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save appointment preferences.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // NOTIFICATIONS
        // =========================================================

        private VBox createNotificationSection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "🔔 Notification Preferences",
                                                "Choose which notifications you want to receive."));

                appointmentReminderCheck =
                                new CheckBox(
                                                "Appointment Reminders");

                messageNotificationCheck =
                                new CheckBox(
                                                "Patient Messages");

                emailNotificationCheck =
                                new CheckBox(
                                                "Email Notifications");

                reportNotificationCheck =
                                new CheckBox(
                                                "Report Notifications");

                appointmentReminderCheck.setSelected(true);
                messageNotificationCheck.setSelected(true);
                emailNotificationCheck.setSelected(true);
                reportNotificationCheck.setSelected(true);

                box.getChildren().add(
                                createCheckBoxRow(
                                                appointmentReminderCheck,
                                                "Get reminders about upcoming appointments, missed appointments and schedule changes."));

                box.getChildren().add(
                                createCheckBoxRow(
                                                messageNotificationCheck,
                                                "Receive a notification when a patient sends a new message or enquiry."));

                box.getChildren().add(
                                createCheckBoxRow(
                                                emailNotificationCheck,
                                                "Receive important account and appointment updates through your registered email."));

                box.getChildren().add(
                                createCheckBoxRow(
                                                reportNotificationCheck,
                                                "Get notified when patient reports are uploaded or their status is updated."));

                Button saveButton =
                                createPrimaryButton(
                                                "Save Notification Preferences");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveNotifications(
                                                                appointmentReminderCheck.isSelected(),
                                                                messageNotificationCheck.isSelected(),
                                                                emailNotificationCheck.isSelected(),
                                                                reportNotificationCheck.isSelected());

                                                showSuccess(
                                                                "Notification preferences saved.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save notification preferences.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // SECURITY
        // =========================================================

        private VBox createSecuritySection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "🔐 Security",
                                                "Change your account password and keep your doctor account secure."));

                oldPasswordField =
                                new PasswordField();

                newPasswordField =
                                new PasswordField();

                confirmPasswordField =
                                new PasswordField();

                box.getChildren().add(
                                createField(
                                                "Current Password",
                                                "Enter your existing password to verify your account.",
                                                "Enter current password",
                                                oldPasswordField));

                box.getChildren().add(
                                createField(
                                                "New Password",
                                                "Use at least 6 characters. A stronger password is recommended.",
                                                "Enter new password",
                                                newPasswordField));

                box.getChildren().add(
                                createField(
                                                "Confirm New Password",
                                                "Re-enter the new password. Both passwords must match.",
                                                "Re-enter new password",
                                                confirmPasswordField));

                Button changePasswordButton =
                                createPrimaryButton(
                                                "Change Password");

                changePasswordButton.setOnAction(
                                e -> {

                                        try {

                                                boolean success =
                                                                controller.changePassword(
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
                                                                                        + "Check the entered passwords and make sure "
                                                                                        + "the new password has at least 6 characters.");
                                                }

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to change password.");
                                        }
                                });

                box.getChildren().add(
                                changePasswordButton);

                return box;
        }

        // =========================================================
        // APPEARANCE
        // =========================================================

        private VBox createAppearanceSection() {

                VBox box = createSectionBox();

                box.getChildren().add(
                                createSectionHeader(
                                                "🎨 Appearance & Language",
                                                "Customize how the application looks and which language is displayed."));

                appearanceCombo =
                                new ComboBox<>();

                appearanceCombo.getItems().addAll(
                                "Light",
                                "Dark",
                                "System Default");

                appearanceCombo.setValue(
                                "Light");

                languageCombo =
                                new ComboBox<>();

                languageCombo.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                languageCombo.setValue(
                                "English");

                box.getChildren().add(
                                createComboField(
                                                "Application Theme",
                                                "Select the appearance of the MaaCare AI application.",
                                                appearanceCombo));

                box.getChildren().add(
                                createComboField(
                                                "Application Language",
                                                "Choose the language you want to use in the application.",
                                                languageCombo));

                Button saveButton =
                                createPrimaryButton(
                                                "Save Appearance Settings");

                saveButton.setOnAction(
                                e -> {

                                        try {

                                                controller.saveAppearance(
                                                                appearanceCombo.getValue());

                                                controller.saveLanguage(
                                                                languageCombo.getValue());

                                                showSuccess(
                                                                "Appearance and language settings saved.");

                                        } catch (Exception ex) {

                                                ex.printStackTrace();

                                                showError(
                                                                "Unable to save appearance settings.");
                                        }
                                });

                box.getChildren().add(
                                saveButton);

                return box;
        }

        // =========================================================
        // PRIVACY
        // =========================================================

        private VBox createPrivacySection() {

                VBox box = createSectionBox();

                box.getChildren().add(
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
                                Font.font("Arial", 12));

                // =====================================================
                // EXPORT BUTTON
                // =====================================================

                Button exportButton =
                                createLightPurpleButton(
                                                "Export My Data");

                // =====================================================
                // DELETE BUTTON
                // =====================================================

                Button deleteButton =
                                createLightPurpleButton(
                                                "Delete Account");

                exportButton.setOnAction(
                                e -> showInfo(
                                                "Export Data",
                                                "Data export functionality can be connected to Firebase when required."));

                deleteButton.setOnAction(
                                e -> showInfo(
                                                "Delete Account",
                                                "Account deletion requires Firebase Authentication and Firestore deletion handling."));

                HBox buttons =
                                new HBox(12);

                buttons.setAlignment(
                                Pos.CENTER_LEFT);

                buttons.getChildren().addAll(
                                exportButton,
                                deleteButton);

                box.getChildren().addAll(
                                privacyText,
                                buttons);

                return box;
        }

        // =========================================================
        // LOAD SETTINGS
        // =========================================================

        private void loadSettings() {

                try {

                        Settings settings =
                                        controller.getDoctorSettings();

                        if (settings == null) {
                                return;
                        }

                        // =====================================================
                        // PROFILE
                        // =====================================================

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

                        // =====================================================
                        // CLINIC
                        // =====================================================

                        clinicNameField.setText(
                                        safe(settings.getClinicName()));

                        clinicAddressField.setText(
                                        safe(settings.getClinicAddress()));

                        // =====================================================
                        // AVAILABILITY
                        // =====================================================

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

                        // =====================================================
                        // APPOINTMENT
                        // =====================================================

                        if (settings.getAppointmentDuration() != null) {

                                durationCombo.setValue(
                                                settings.getAppointmentDuration());
                        }

                        autoConfirmCheck.setSelected(
                                        settings.isAutoConfirmAppointments());

                        // =====================================================
                        // NOTIFICATIONS
                        // =====================================================

                        appointmentReminderCheck.setSelected(
                                        settings.isAppointmentReminders());

                        messageNotificationCheck.setSelected(
                                        settings.isMessageNotifications());

                        emailNotificationCheck.setSelected(
                                        settings.isEmailNotifications());

                        reportNotificationCheck.setSelected(
                                        settings.isReportNotifications());

                        // =====================================================
                        // APPEARANCE
                        // =====================================================

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
                                        "[SETTINGS] Unable to load settings: "
                                                        + e.getMessage());

                        e.printStackTrace();
                }
        }

        // =========================================================
        // SECTION HEADER
        // =========================================================

        private VBox createSectionHeader(
                        String title,
                        String description) {

                VBox box = new VBox(4);

                Label titleLabel =
                                new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                titleLabel.setTextFill(
                                Color.web(PURPLE));

                Label descriptionLabel =
                                new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                descriptionLabel.setTextFill(
                                Color.web(SECONDARY));

                box.getChildren().addAll(
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

                Label labelText =
                                new Label(label);

                labelText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                labelText.setTextFill(
                                Color.web(TEXT));

                Label hintText =
                                new Label(hint);

                hintText.setWrapText(true);

                hintText.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                hintText.setTextFill(
                                Color.web(SECONDARY));

                field.setPromptText(prompt);

                field.setPrefHeight(38);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-radius: 8;"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-background-color: white;"
                                                + "-fx-text-fill: "
                                                + TEXT + ";"
                                                + "-fx-focus-color: "
                                                + PURPLE + ";"
                                                + "-fx-faint-focus-color: "
                                                + LIGHT_PURPLE + ";");

                box.getChildren().addAll(
                                labelText,
                                hintText,
                                field);

                return box;
        }

        // =========================================================
        // COMBO BOX
        // =========================================================

        private VBox createComboField(
                        String label,
                        String hint,
                        ComboBox<String> combo) {

                VBox box = new VBox(5);

                Label labelText =
                                new Label(label);

                labelText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                labelText.setTextFill(
                                Color.web(TEXT));

                Label hintText =
                                new Label(hint);

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

                styleComboBox(combo);

                box.getChildren().addAll(
                                labelText,
                                hintText,
                                combo);

                return box;
        }

        // =========================================================
        // CHECKBOX
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

                Label descriptionLabel =
                                new Label(description);

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

                box.getChildren().addAll(
                                checkBox,
                                descriptionLabel);

                return box;
        }

        // =========================================================
        // SECTION CARD
        // WHITE CARD - MOTHER DASHBOARD STYLE
        // =========================================================

        private VBox createSectionBox() {

                VBox box = new VBox(15);

                box.setPadding(
                                new Insets(20));

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 18;"
                                                + "-fx-border-radius: 18;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-width: 1;");

                return box;
        }

        // =========================================================
        // INFO CARD
        // MOTHER DASHBOARD LIGHT PINK CARD
        // =========================================================

        private VBox createInfoCard(
                        String title,
                        String description) {

                VBox box = new VBox(5);

                box.setPadding(
                                new Insets(18));

                box.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PINK + ";"
                                                + "-fx-background-radius: 18;"
                                                + "-fx-border-color: "
                                                + "#F5C7DB" + ";"
                                                + "-fx-border-radius: 18;"
                                                + "-fx-border-width: 1;");

                Label titleLabel =
                                new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                titleLabel.setTextFill(
                                Color.web(PURPLE));

                Label descriptionLabel =
                                new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                descriptionLabel.setTextFill(
                                Color.web(TEXT));

                box.getChildren().addAll(
                                titleLabel,
                                descriptionLabel);

                return box;
        }

        // =========================================================
        // PRIMARY BUTTON
        // =========================================================

        private Button createPrimaryButton(
                        String text) {

                Button button =
                                new Button(text);

                button.setPrefHeight(40);

                button.setPadding(
                                new Insets(
                                                10,
                                                22,
                                                10,
                                                22));

                setGradientButtonStyle(button);

                button.setOnMouseEntered(
                                e -> setGradientButtonHoverStyle(button));

                button.setOnMouseExited(
                                e -> setGradientButtonStyle(button));

                return button;
        }

        // =========================================================
        // OTHER BUTTONS
        // =========================================================

        private Button createLightPurpleButton(
                        String text) {

                Button button =
                                new Button(text);

                button.setPrefHeight(40);

                button.setPadding(
                                new Insets(
                                                9,
                                                20,
                                                9,
                                                20));

                setGradientButtonStyle(button);

                button.setOnMouseEntered(
                                e -> setGradientButtonHoverStyle(button));

                button.setOnMouseExited(
                                e -> setGradientButtonStyle(button));

                return button;
        }

        // =========================================================
        // GRADIENT BUTTON
        // =========================================================

        private void setGradientButtonStyle(
                        Button button) {

                button.setStyle(
                                "-fx-background-color: linear-gradient("
                                                + "to right, #F54B87, #9B4DCC);"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-padding: 10px 22px;"
                                                + "-fx-background-radius: 20px;"
                                                + "-fx-border-radius: 20px;"
                                                + "-fx-border-color: transparent;"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // GRADIENT HOVER
        // =========================================================

        private void setGradientButtonHoverStyle(
                        Button button) {

                button.setStyle(
                                "-fx-background-color: linear-gradient("
                                                + "to right, #9B4DCC, #F54B87);"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-padding: 10px 22px;"
                                                + "-fx-background-radius: 20px;"
                                                + "-fx-border-radius: 20px;"
                                                + "-fx-border-color: transparent;"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // PRIMARY BUTTON STYLE
        // =========================================================

        private void setPrimaryButtonStyle(
                        Button button,
                        String backgroundColor) {

                setGradientButtonStyle(button);
        }

        // =========================================================
        // LIGHT BUTTON STYLE
        // =========================================================

        private void setLightButtonStyle(
                        Button button,
                        String backgroundColor,
                        String textColor,
                        String borderColor) {

                setGradientButtonStyle(button);
        }

        // =========================================================
        // COMBO BOX STYLE
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> comboBox) {

                comboBox.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PINK + ";"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-radius: 8px;"
                                                + "-fx-background-radius: 8px;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: "
                                                + PURPLE + ";");
        }

        // =========================================================
        // ALERT BUTTONS
        // =========================================================

        private void styleAlertButtons(
                        Alert alert) {

                alert.setOnShown(
                                e -> {

                                        Button okButton =
                                                        (Button) alert
                                                                        .getDialogPane()
                                                                        .lookupButton(
                                                                                        ButtonType.OK);

                                        Button cancelButton =
                                                        (Button) alert
                                                                        .getDialogPane()
                                                                        .lookupButton(
                                                                                        ButtonType.CANCEL);

                                        if (okButton != null) {

                                                setGradientButtonStyle(
                                                                okButton);

                                                okButton.setOnMouseEntered(
                                                                event ->
                                                                                setGradientButtonHoverStyle(
                                                                                                okButton));

                                                okButton.setOnMouseExited(
                                                                event ->
                                                                                setGradientButtonStyle(
                                                                                                okButton));
                                        }

                                        if (cancelButton != null) {

                                                setGradientButtonStyle(
                                                                cancelButton);

                                                cancelButton.setOnMouseEntered(
                                                                event ->
                                                                                setGradientButtonHoverStyle(
                                                                                                cancelButton));

                                                cancelButton.setOnMouseExited(
                                                                event ->
                                                                                setGradientButtonStyle(
                                                                                                cancelButton));
                                        }
                                });
        }

        // =========================================================
        // SUCCESS ALERT
        // =========================================================

        private void showSuccess(
                        String message) {

                Alert alert =
                                new Alert(
                                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Success");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                styleAlertButtons(
                                alert);

                alert.showAndWait();
        }

        // =========================================================
        // ERROR ALERT
        // =========================================================

        private void showError(
                        String message) {

                Alert alert =
                                new Alert(
                                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Error");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                styleAlertButtons(
                                alert);

                alert.showAndWait();
        }

        // =========================================================
        // INFO ALERT
        // =========================================================

        private void showInfo(
                        String title,
                        String message) {

                Alert alert =
                                new Alert(
                                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                title);

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                styleAlertButtons(
                                alert);

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