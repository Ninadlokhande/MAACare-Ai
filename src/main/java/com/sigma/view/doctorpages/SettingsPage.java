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

        private static SettingsController controller;

        private static VBox contentArea;

        private static String activeOption = "Account Settings";

        // =====================================================
        // SHOW
        // =====================================================

        public static void show() {

                controller = new SettingsController();

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setPadding(
                                new Insets(
                                                28,
                                                35,
                                                28,
                                                35));

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Settings",
                                "Manage your account and preferences.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // =====================================================
                // SIDEBAR
                // =====================================================

                VBox menu = createMenu();

                // =====================================================
                // CONTENT AREA
                // =====================================================

                contentArea = new VBox();

                contentArea.setFillWidth(true);

                showAccountSettings();

                // =====================================================
                // CENTER
                // =====================================================

                HBox center = new HBox(18);

                center.setPadding(
                                new Insets(
                                                0,
                                                35,
                                                0,
                                                0));

                menu.setPrefWidth(250);
                menu.setMinWidth(250);

                HBox.setHgrow(
                                contentArea,
                                Priority.ALWAYS);

                center.getChildren().addAll(
                                menu,
                                contentArea);

                root.setTop(header);

                BorderPane.setMargin(
                                header,
                                new Insets(
                                                0,
                                                0,
                                                20,
                                                0));

                root.setCenter(center);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(root);

                DoctorDashboard.changeScene(
                                scene);
        }

        // =====================================================
        // CREATE MENU
        // =====================================================

        private static VBox createMenu() {

                VBox menu = new VBox(8);

                menu.setPadding(
                                new Insets(15));

                menu.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 12;");

                String[] icons = {

                                "👤",
                                "🪪",
                                "🔐",
                                "🔔",
                                "⏰",
                                "💬",
                                "🎨",
                                "⚙",
                                "🛡"
                };

                String[] options = {

                                "Account Settings",
                                "Profile Settings",
                                "Password & Security",
                                "Notification Preferences",
                                "Appointment Reminders",
                                "Message Notifications",
                                "Appearance",
                                "System Preferences",
                                "Data & Privacy"
                };

                for (int i = 0; i < options.length; i++) {

                        Button button = createMenuButton(
                                        icons[i],
                                        options[i]);

                        final String option = options[i];

                        button.setOnAction(
                                        e -> {

                                                activeOption = option;

                                                updateMenuStyle(
                                                                menu,
                                                                option);

                                                openOption(
                                                                option);
                                        });

                        menu.getChildren()
                                        .add(button);
                }

                updateMenuStyle(
                                menu,
                                activeOption);

                return menu;
        }

        // =====================================================
        // MENU BUTTON
        // =====================================================

        private static Button createMenuButton(
                        String icon,
                        String text) {

                Button button = new Button();

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                "Segoe UI Emoji",
                                                18));

                iconLabel.setMinWidth(28);

                Label textLabel = new Label(text);

                textLabel.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                13));

                HBox box = new HBox(10);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                box.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(box);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-padding: 10;" +
                                                "-fx-cursor: hand;");

                return button;
        }

        // =====================================================
        // MENU STYLE
        // =====================================================

        private static void updateMenuStyle(
                        VBox menu,
                        String selected) {

                for (int i = 0; i < menu.getChildren().size(); i++) {

                        Button button = (Button) menu.getChildren()
                                        .get(i);

                        HBox box = (HBox) button.getGraphic();

                        Label icon = (Label) box.getChildren()
                                        .get(0);

                        Label text = (Label) box.getChildren()
                                        .get(1);

                        String option = text.getText();

                        if (option.equals(selected)) {

                                button.setStyle(
                                                "-fx-background-color: " +
                                                                Theme.PRIMARY_LIGHT + ";" +
                                                                "-fx-background-radius: 8;" +
                                                                "-fx-padding: 10;" +
                                                                "-fx-cursor: hand;");

                                icon.setTextFill(
                                                Color.web(
                                                                Theme.PRIMARY));

                                text.setTextFill(
                                                Color.web(
                                                                Theme.PRIMARY));

                        } else {

                                button.setStyle(
                                                "-fx-background-color: transparent;" +
                                                                "-fx-padding: 10;" +
                                                                "-fx-cursor: hand;");

                                icon.setTextFill(
                                                Color.web(
                                                                Theme.TEXT));

                                text.setTextFill(
                                                Color.web(
                                                                Theme.TEXT));
                        }
                }
        }

        // =====================================================
        // OPEN OPTION
        // =====================================================

        private static void openOption(
                        String option) {

                switch (option) {

                        case "Account Settings":
                                showAccountSettings();
                                break;

                        case "Profile Settings":
                                showProfileSettings();
                                break;

                        case "Password & Security":
                                showPasswordSecurity();
                                break;

                        case "Notification Preferences":
                                showNotificationPreferences();
                                break;

                        case "Appointment Reminders":
                                showAppointmentReminders();
                                break;

                        case "Message Notifications":
                                showMessageNotifications();
                                break;

                        case "Appearance":
                                showAppearance();
                                break;

                        case "System Preferences":
                                showSystemPreferences();
                                break;

                        case "Data & Privacy":
                                showDataPrivacy();
                                break;
                }
        }

        // =====================================================
        // ACCOUNT SETTINGS
        // =====================================================

        private static void showAccountSettings() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "Account Settings",
                                "Update your basic account information.");

                TextField name = field(
                                "Full Name",
                                settings.getFullName());

                TextField email = field(
                                "Email",
                                settings.getEmail());

                TextField phone = field(
                                "Phone Number",
                                settings.getPhone());

                ComboBox<String> specialization = new ComboBox<>();

                specialization.getItems().addAll(
                                "Obstetrician & Gynecologist",
                                "Gynecologist",
                                "Pediatrician");

                specialization.setValue(
                                settings.getSpecialization());

                specialization.setPrefHeight(38);

                specialization.setMaxWidth(
                                Double.MAX_VALUE);

                TextField license = field(
                                "License No.",
                                settings.getLicense());

                VBox form = new VBox(12);

                form.getChildren().addAll(

                                name,
                                email,
                                phone,

                                label(
                                                "Specialization"),

                                specialization,

                                license);

                Button save = Theme.primaryButton(
                                "Save Changes");

                save.setOnAction(
                                e -> {

                                        controller.saveSettings(

                                                        name.getText(),

                                                        email.getText(),

                                                        phone.getText(),

                                                        specialization
                                                                        .getValue(),

                                                        license.getText());

                                        success(
                                                        "Account settings saved successfully.");
                                });

                HBox saveBox = new HBox(save);

                saveBox.setAlignment(
                                Pos.CENTER_RIGHT);

                card.getChildren().addAll(
                                form,
                                saveBox);

                setContent(card);
        }

        // =====================================================
        // PROFILE SETTINGS
        // =====================================================

        private static void showProfileSettings() {

                VBox card = createCard(
                                "Profile Settings",
                                "Manage your complete doctor profile.");

                Label info = new Label(
                                "Edit your personal, professional and clinic information from the Doctor Profile page.");

                info.setWrapText(true);

                Button openProfile = Theme.primaryButton(
                                "✏ Edit Doctor Profile");

                openProfile.setOnAction(
                                e -> DoctorProfile.show());

                card.getChildren().addAll(
                                info,
                                openProfile);

                setContent(card);
        }

        // =====================================================
        // PASSWORD
        // =====================================================

        private static void showPasswordSecurity() {

                VBox card = createCard(
                                "Password & Security",
                                "Change your account password.");

                PasswordField oldPassword = new PasswordField();

                oldPassword.setPromptText(
                                "Current password");

                PasswordField newPassword = new PasswordField();

                newPassword.setPromptText(
                                "New password");

                PasswordField confirmPassword = new PasswordField();

                confirmPassword.setPromptText(
                                "Confirm new password");

                Button save = Theme.primaryButton(
                                "Change Password");

                save.setOnAction(
                                e -> {

                                        boolean success = controller.changePassword(

                                                        oldPassword.getText(),

                                                        newPassword.getText(),

                                                        confirmPassword
                                                                        .getText());

                                        if (success) {

                                                success(
                                                                "Password changed successfully.");

                                                oldPassword.clear();
                                                newPassword.clear();
                                                confirmPassword.clear();

                                        } else {

                                                warning(
                                                                "Please check your password details. New password must match confirmation and contain at least 6 characters.");
                                        }
                                });

                card.getChildren().addAll(

                                label("Current Password"),
                                oldPassword,

                                label("New Password"),
                                newPassword,

                                label("Confirm Password"),
                                confirmPassword,

                                save);

                setContent(card);
        }

        // =====================================================
        // NOTIFICATIONS
        // =====================================================

        private static void showNotificationPreferences() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "Notification Preferences",
                                "Choose how you want to receive notifications.");

                CheckBox email = new CheckBox(
                                "Email Notifications");

                email.setSelected(
                                settings.isEmailNotifications());

                CheckBox appointment = new CheckBox(
                                "Appointment Notifications");

                appointment.setSelected(
                                settings.isAppointmentReminders());

                CheckBox message = new CheckBox(
                                "Message Notifications");

                message.setSelected(
                                settings.isMessageNotifications());

                Button save = Theme.primaryButton(
                                "Save Preferences");

                save.setOnAction(
                                e -> {

                                        controller.saveNotifications(

                                                        appointment.isSelected(),

                                                        message.isSelected(),

                                                        email.isSelected());

                                        success(
                                                        "Notification preferences saved.");
                                });

                card.getChildren().addAll(
                                email,
                                appointment,
                                message,
                                save);

                setContent(card);
        }

        // =====================================================
        // APPOINTMENT REMINDERS
        // =====================================================

        private static void showAppointmentReminders() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "Appointment Reminders",
                                "Manage reminders for upcoming appointments.");

                CheckBox reminder = new CheckBox(
                                "Enable appointment reminders");

                reminder.setSelected(
                                settings.isAppointmentReminders());

                ComboBox<String> timing = new ComboBox<>();

                timing.getItems().addAll(
                                "15 minutes before",
                                "30 minutes before",
                                "1 hour before",
                                "1 day before");

                timing.setValue(
                                "30 minutes before");

                timing.setPrefHeight(38);

                Button save = Theme.primaryButton(
                                "Save Reminder Settings");

                save.setOnAction(
                                e -> {

                                        controller.saveNotifications(

                                                        reminder.isSelected(),

                                                        settings.isMessageNotifications(),

                                                        settings.isEmailNotifications());

                                        success(
                                                        "Appointment reminder settings saved.");
                                });

                card.getChildren().addAll(

                                reminder,

                                label("Reminder Timing"),

                                timing,

                                save);

                setContent(card);
        }

        // =====================================================
        // MESSAGE NOTIFICATIONS
        // =====================================================

        private static void showMessageNotifications() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "Message Notifications",
                                "Manage notifications for patient messages.");

                CheckBox message = new CheckBox(
                                "Notify me when a patient sends a message");

                message.setSelected(
                                settings.isMessageNotifications());

                Button save = Theme.primaryButton(
                                "Save Settings");

                save.setOnAction(
                                e -> {

                                        controller.saveNotifications(

                                                        settings.isAppointmentReminders(),

                                                        message.isSelected(),

                                                        settings.isEmailNotifications());

                                        success(
                                                        "Message notification settings saved.");
                                });

                card.getChildren().addAll(
                                message,
                                save);

                setContent(card);
        }

        // =====================================================
        // APPEARANCE
        // =====================================================

        private static void showAppearance() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "Appearance",
                                "Customize how the application looks.");

                ComboBox<String> appearance = new ComboBox<>();

                appearance.getItems().addAll(
                                "Light",
                                "Dark",
                                "System Default");

                appearance.setValue(
                                settings.getAppearance());

                appearance.setPrefHeight(38);

                Button save = Theme.primaryButton(
                                "Save Appearance");

                save.setOnAction(
                                e -> {

                                        controller.saveAppearance(
                                                        appearance.getValue());

                                        success(
                                                        "Appearance preference saved.");
                                });

                card.getChildren().addAll(

                                label("Theme"),

                                appearance,

                                save);

                setContent(card);
        }

        // =====================================================
        // SYSTEM PREFERENCES
        // =====================================================

        private static void showSystemPreferences() {

                Settings settings = controller.getDoctorSettings();

                VBox card = createCard(
                                "System Preferences",
                                "Manage application preferences.");

                ComboBox<String> language = new ComboBox<>();

                language.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                language.setValue(
                                settings.getLanguage());

                language.setPrefHeight(38);

                Button save = Theme.primaryButton(
                                "Save Preferences");

                save.setOnAction(
                                e -> {

                                        controller.saveLanguage(
                                                        language.getValue());

                                        success(
                                                        "System preferences saved.");
                                });

                card.getChildren().addAll(

                                label("Language"),

                                language,

                                save);

                setContent(card);
        }

        // =====================================================
        // DATA & PRIVACY
        // =====================================================

        private static void showDataPrivacy() {

                VBox card = createCard(
                                "Data & Privacy",
                                "Manage your account data and privacy.");

                Label info = new Label(
                                "Your doctor profile and application information are stored securely. Data export and deletion options can be connected to Firestore later.");

                info.setWrapText(true);

                Button export = new Button(
                                "Export My Data");

                export.setOnAction(
                                e -> success(
                                                "Data export feature is ready to be connected with Firestore."));

                Button delete = new Button(
                                "Request Data Deletion");

                delete.setOnAction(
                                e -> warning(
                                                "Data deletion requires confirmation and Firestore integration."));

                card.getChildren().addAll(
                                info,
                                export,
                                delete);

                setContent(card);
        }

        // =====================================================
        // SET CONTENT
        // =====================================================

        private static void setContent(
                        VBox content) {

                contentArea.getChildren().clear();

                contentArea.getChildren()
                                .add(content);
        }

        // =====================================================
        // CARD
        // =====================================================

        private static VBox createCard(
                        String title,
                        String subtitle) {

                VBox card = Theme.card();

                card.setPadding(
                                new Insets(25));

                card.setSpacing(15);

                Label heading = new Label(title);

                heading.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                18));

                heading.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                Label sub = new Label(subtitle);

                sub.setFont(
                                Font.font(
                                                Theme.FONT,
                                                12));

                sub.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

                card.getChildren().addAll(
                                heading,
                                sub);

                return card;
        }

        // =====================================================
        // FIELD
        // =====================================================

        private static TextField field(
                        String title,
                        String value) {

                TextField field = new TextField(value);

                field.setPrefHeight(38);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                return field;
        }

        // =====================================================
        // LABEL
        // =====================================================

        private static Label label(
                        String text) {

                Label label = new Label(text);

                label.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                11));

                label.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                return label;
        }

        // =====================================================
        // SUCCESS
        // =====================================================

        private static void success(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Success");

                alert.setHeaderText(null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // =====================================================
        // WARNING
        // =====================================================

        private static void warning(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.WARNING);

                alert.setTitle(
                                "Warning");

                alert.setHeaderText(null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

}
