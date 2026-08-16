package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SettingsPage {

        public static void show() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                // =================================================
                // HEADER
                // =================================================

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

                header.getChildren().addAll(
                                heading,
                                spacer,
                                Theme.backButton());

                // =================================================
                // SETTINGS MENU
                // =================================================

                VBox menu = new VBox(8);

                menu.setPrefWidth(230);
                menu.setMinWidth(230);
                menu.setMaxWidth(230);

                menu.setPadding(
                                new Insets(15));

                menu.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 12;");

                String[] icons = {
                                "♙",
                                "♙",
                                "▣",
                                "▱",
                                "□",
                                "▱",
                                "◉",
                                "⚙",
                                "▣"
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

                        Button b = new Button();

                        b.setMaxWidth(
                                        Double.MAX_VALUE);

                        b.setAlignment(
                                        Pos.CENTER_LEFT);

                        // ==============================
                        // ICON
                        // ==============================

                        Label iconLabel = new Label(icons[i]);

                        iconLabel.setFont(
                                        javafx.scene.text.Font.font(
                                                        Theme.FONT,
                                                        javafx.scene.text.FontWeight.BOLD,
                                                        20));

                        iconLabel.setTextFill(
                                        Color.web(
                                                        i == 0
                                                                        ? Theme.PRIMARY
                                                                        : Theme.TEXT));

                        // ==============================
                        // TEXT
                        // ==============================

                        Label textLabel = new Label(options[i]);

                        textLabel.setFont(
                                        javafx.scene.text.Font.font(
                                                        Theme.FONT,
                                                        javafx.scene.text.FontWeight.BOLD,
                                                        13));

                        textLabel.setTextFill(
                                        Color.web(
                                                        i == 0
                                                                        ? Theme.PRIMARY
                                                                        : Theme.TEXT));

                        // ==============================
                        // ICON + TEXT
                        // ==============================

                        HBox content = new HBox(10);

                        content.setAlignment(
                                        Pos.CENTER_LEFT);

                        content.getChildren().addAll(
                                        iconLabel,
                                        textLabel);

                        b.setGraphic(content);

                        // ==============================
                        // ACTIVE BUTTON
                        // ==============================

                        if (i == 0) {

                                b.setStyle(
                                                "-fx-background-color: " +
                                                                Theme.PRIMARY_LIGHT + ";" +
                                                                "-fx-background-radius: 8;" +
                                                                "-fx-padding: 10;");

                        } else {

                                b.setStyle(
                                                "-fx-background-color: transparent;" +
                                                                "-fx-padding: 10;" +
                                                                "-fx-cursor: hand;");
                        }

                        menu.getChildren().add(b);
                }
                // =================================================
                // ACCOUNT FORM
                // =================================================

                VBox form = Theme.card();

                /*
                 * IMPORTANT:
                 *
                 * Top = 25
                 * Right = 65 <-- मुख्य बदल
                 * Bottom = 25
                 * Left = 25
                 *
                 * यामुळे right side ला जास्त space मिळेल.
                 */
                form.setPadding(
                                new Insets(25, 65, 25, 25));

                /*
                 * प्रत्येक field मध्ये vertical spacing.
                 */
                form.setSpacing(12);

                Label formTitle = new Label("Account Settings");

                formTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                formTitle.setTextFill(
                                Color.web(Theme.TEXT));

                // =================================================
                // FULL NAME
                // =================================================

                TextField fullName = field(
                                "Full Name",
                                "Dr. Anjali Mehta");

                // =================================================
                // EMAIL
                // =================================================

                TextField email = field(
                                "Email",
                                "anjalimehta@maacare.com");

                // =================================================
                // PHONE
                // =================================================

                TextField phone = field(
                                "Phone Number",
                                "9876543210");

                // =================================================
                // SPECIALIZATION
                // =================================================

                ComboBox<String> specialization = new ComboBox<>();

                specialization.getItems().addAll(
                                "Obstetrician & Gynecologist",
                                "Gynecologist",
                                "Pediatrician");

                specialization.setValue(
                                "Obstetrician & Gynecologist");

                specialization.setMaxWidth(
                                Double.MAX_VALUE);

                specialization.setPrefHeight(38);

                // =================================================
                // LICENSE
                // =================================================

                TextField license = field(
                                "License No.",
                                "GYN/2020/12345");

                // =================================================
                // SAVE BUTTON
                // =================================================

                Button save = Theme.primaryButton(
                                "Save Changes");

                HBox saveBox = new HBox(save);

                saveBox.setAlignment(
                                Pos.CENTER_RIGHT);

                /*
                 * Button आणि right edge मध्येही
                 * form च्या 65px padding मुळे proper space राहील.
                 */

                // =================================================
                // ADD FORM CONTENT
                // =================================================

                form.getChildren().addAll(
                                formTitle,
                                fullName,
                                email,
                                phone,
                                label("Specialization"),
                                specialization,
                                license,
                                saveBox);

                // =================================================
                // CENTER AREA
                // =================================================

                HBox center = new HBox(18);

                /*
                 * Right side ला additional 35px space.
                 *
                 * त्यामुळे card window च्या अगदी edge ला
                 * चिकटणार नाही.
                 */
                center.setPadding(
                                new Insets(0, 35, 0, 0));

                HBox.setHgrow(
                                form,
                                Priority.ALWAYS);

                center.getChildren().addAll(
                                menu,
                                form);

                // =================================================
                // ROOT
                // =================================================

                root.setTop(header);

                BorderPane.setMargin(
                                header,
                                new Insets(0, 0, 20, 0));

                root.setCenter(center);

                // =================================================
                // SCENE
                // =================================================

                Scene scene = new Scene(
                                root);

                DoctorDashboard.changeScene(scene);

        }

        // =================================================
        // LABEL
        // =================================================

        private static Label label(String text) {

                Label label = new Label(text);

                label.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                10));

                label.setTextFill(
                                Color.web(Theme.TEXT));

                return label;
        }

        // =================================================
        // TEXT FIELD
        // =================================================

        private static TextField field(
                        String labelText,
                        String value) {

                VBox box = new VBox(5);

                Label label = label(labelText);

                TextField field = new TextField(value);

                field.setPrefHeight(38);

                /*
                 * Field available width मध्ये राहील,
                 * पण form च्या 65px right padding मुळे
                 * उजव्या edge पासून space राहील.
                 */
                field.setMaxWidth(
                                Double.MAX_VALUE);

                box.getChildren().addAll(
                                label,
                                field);

                /*
                 * Existing method TextField return करते,
                 * म्हणून field चीच width वापरली जाते.
                 */
                return field;
        }
}