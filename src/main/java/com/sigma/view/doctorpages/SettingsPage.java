package com.sigma.view.doctorpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsPage {

        // =========================================================
        // SHOW SETTINGS PAGE ON SAME COMMON STAGE
        // =========================================================
        public static void display() {

                Stage stage = DoctorDashboard.getCommonStage();

                if (stage == null) {
                        return;
                }

                VBox pageContent = createSettingsContent();

                ScrollPane scrollPane = new ScrollPane(pageContent);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: transparent;");

                BorderPane root = new BorderPane();

                // Same dashboard sidebar
                root.setLeft(DoctorDashboard.createSidebar());

                root.setCenter(scrollPane);

                Scene scene = new Scene(
                                root,
                                DoctorTheme.WIDTH,
                                DoctorTheme.HEIGHT);

                DoctorTheme.applyBackground(root);

                // SAME SCENE / SAME STAGE
                stage.setScene(scene);

                stage.setTitle("MaaCare AI - Settings");

                // Preserve common stage settings
                stage.setResizable(true);

                if (!stage.isMaximized()) {
                        stage.setWidth(DoctorTheme.WIDTH);
                        stage.setHeight(DoctorTheme.HEIGHT);
                        stage.centerOnScreen();
                }

                stage.show();
                stage.toFront();
        }

        // =========================================================
        // SETTINGS CONTENT
        // =========================================================
        private static VBox createSettingsContent() {

                VBox content = new VBox(22);

                content.setPadding(new Insets(30));
                content.setAlignment(Pos.TOP_LEFT);
                content.setFillWidth(true);

                content.setMinWidth(0);

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);

                VBox titleBox = new VBox(5);

                Label title = new Label("Settings");
                title.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                28));
                title.setTextFill(Color.web(DoctorTheme.TEXT));

                Label subtitle = new Label(
                                "Manage your MaaCare AI account and application preferences");

                subtitle.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                14));

                subtitle.setTextFill(
                                Color.web(DoctorTheme.SECONDARY_TEXT));

                titleBox.getChildren().addAll(title, subtitle);

                header.getChildren().add(titleBox);

                // =====================================================
                // ACCOUNT SETTINGS
                // =====================================================

                VBox accountCard = DoctorTheme.card();

                Label accountTitle = new Label("Account Settings");
                accountTitle.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));
                accountTitle.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label emailLabel = new Label("Email");
                emailLabel.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                14));

                TextField emailField = new TextField();

                emailField.setPromptText("Doctor email");
                emailField.setPrefHeight(42);

                emailField.setStyle(
                                "-fx-background-color: #FFF9FC;" +
                                                "-fx-border-color: #F0D8E3;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 0 12;");

                Label passwordLabel = new Label("Password");

                passwordLabel.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                14));

                PasswordField passwordField = new PasswordField();

                passwordField.setPromptText("Enter new password");
                passwordField.setPrefHeight(42);

                passwordField.setStyle(
                                "-fx-background-color: #FFF9FC;" +
                                                "-fx-border-color: #F0D8E3;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 0 12;");

                Button saveAccountButton = new Button("Save Changes");

                saveAccountButton.setPrefHeight(40);
                saveAccountButton.setStyle(
                                "-fx-background-color: " + DoctorTheme.PRIMARY + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 0 20;");

                accountCard.getChildren().addAll(
                                accountTitle,
                                emailLabel,
                                emailField,
                                passwordLabel,
                                passwordField,
                                saveAccountButton);

                // =====================================================
                // NOTIFICATION SETTINGS
                // =====================================================

                VBox notificationCard = DoctorTheme.card();

                Label notificationTitle = new Label("Notifications");

                notificationTitle.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));

                notificationTitle.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                CheckBox appointmentNotification = new CheckBox("Appointment notifications");

                CheckBox patientNotification = new CheckBox("New patient notifications");

                CheckBox reportNotification = new CheckBox("Patient report notifications");

                appointmentNotification.setSelected(true);
                patientNotification.setSelected(true);
                reportNotification.setSelected(true);

                notificationCard.getChildren().addAll(
                                notificationTitle,
                                appointmentNotification,
                                patientNotification,
                                reportNotification);

                // =====================================================
                // APPEARANCE SETTINGS
                // =====================================================

                VBox appearanceCard = DoctorTheme.card();

                Label appearanceTitle = new Label("Appearance");

                appearanceTitle.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));

                appearanceTitle.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label themeLabel = new Label("Theme");

                themeLabel.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                14));

                ComboBox<String> themeCombo = new ComboBox<>();

                themeCombo.getItems().addAll(
                                "Light",
                                "Lavender",
                                "Pink");

                themeCombo.setValue("Pink");

                themeCombo.setPrefHeight(40);

                appearanceCard.getChildren().addAll(
                                appearanceTitle,
                                themeLabel,
                                themeCombo);

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = DoctorTheme.backButton();

                backButton.setOnAction(e -> DoctorDashboard.showDashboard());

                // =====================================================
                // ADD EVERYTHING
                // =====================================================

                content.getChildren().addAll(
                                header,
                                accountCard,
                                notificationCard,
                                appearanceCard,
                                backButton);

                return content;
        }
}