package com.sigma.view.doctorpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DoctorProfilePage {

        private static Scene profileScene;

        // =========================================================
        // SHOW PROFILE PAGE
        // =========================================================

        public static void show() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                // =========================================================
                // MAIN CONTENT
                // =========================================================

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(28, 35, 28, 35));

                Theme.applyBackground(content);

                // =========================================================
                // HEADER
                // =========================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Doctor Profile",
                                "View and manage your professional information.");

                HBox.setHgrow(
                                heading,
                                Priority.ALWAYS);

                Button backButton = Theme.backButton();

                header.getChildren().addAll(
                                heading,
                                backButton);

                // =========================================================
                // PROFILE SUMMARY CARD
                // =========================================================

                VBox profileCard = Theme.card();

                profileCard.setSpacing(12);

                profileCard.setAlignment(
                                Pos.CENTER_LEFT);

                profileCard.setPadding(
                                new Insets(22));

                // =========================================================
                // PROFILE TOP
                // =========================================================

                HBox profileTop = new HBox(18);

                profileTop.setAlignment(
                                Pos.CENTER_LEFT);

                // Doctor icon
                Label doctorIcon = new Label("👩🏻‍⚕️");

                doctorIcon.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';" +
                                                "-fx-font-size: 58px;" +
                                                "-fx-background-color: #F3ECFF;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-padding: 12;");

                // Doctor basic information
                VBox doctorInfo = new VBox(5);

                Label doctorName = new Label(
                                "Dr. Anjali Mehta");

                doctorName.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                22));

                doctorName.setTextFill(
                                Color.web(Theme.TEXT));

                Label specialization = new Label(
                                "Obstetrician & Gynecologist");

                specialization.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                14));

                specialization.setTextFill(
                                Color.web(Theme.PRIMARY));

                Label hospital = new Label(
                                "MaaCare Women's Hospital");

                hospital.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.NORMAL,
                                                13));

                hospital.setTextFill(
                                Color.web(Theme.SECONDARY_TEXT));

                Label status = new Label(
                                "● Online");

                status.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                12));

                status.setTextFill(
                                Color.web(Theme.GREEN));

                doctorInfo.getChildren().addAll(
                                doctorName,
                                specialization,
                                hospital,
                                status);

                profileTop.getChildren().addAll(
                                doctorIcon,
                                doctorInfo);

                profileCard.getChildren().add(
                                profileTop);

                // =========================================================
                // INFORMATION CONTAINER
                // =========================================================

                HBox information = new HBox(18);

                // =========================================================
                // PERSONAL INFORMATION
                // =========================================================

                VBox personalInfo = Theme.card();

                personalInfo.setSpacing(15);

                HBox.setHgrow(
                                personalInfo,
                                Priority.ALWAYS);

                Label personalTitle = new Label(
                                "Personal Information");

                personalTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                17));

                personalTitle.setTextFill(
                                Color.web(Theme.TEXT));

                personalInfo.getChildren().addAll(
                                personalTitle,

                                infoRow(
                                                "👤",
                                                "Full Name",
                                                "Dr. Anjali Mehta"),

                                infoRow(
                                                "📧",
                                                "Email",
                                                "anjali.mehta@example.com"),

                                infoRow(
                                                "📱",
                                                "Phone Number",
                                                "+91 98765 43210"),

                                infoRow(
                                                "🏙",
                                                "City",
                                                "Pune"));

                // =========================================================
                // PROFESSIONAL INFORMATION
                // =========================================================

                VBox professionalInfo = Theme.card();

                professionalInfo.setSpacing(15);

                HBox.setHgrow(
                                professionalInfo,
                                Priority.ALWAYS);

                Label professionalTitle = new Label(
                                "Professional Information");

                professionalTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                17));

                professionalTitle.setTextFill(
                                Color.web(Theme.TEXT));

                professionalInfo.getChildren().addAll(
                                professionalTitle,

                                infoRow(
                                                "🩺",
                                                "Specialization",
                                                "Obstetrician & Gynecologist"),

                                infoRow(
                                                "🎓",
                                                "Qualification",
                                                "MBBS, MD"),

                                infoRow(
                                                "💼",
                                                "Experience",
                                                "12 Years"),

                                infoRow(
                                                "📋",
                                                "Medical License",
                                                "GYN/2020/12345"),

                                infoRow(
                                                "🏥",
                                                "Hospital / Clinic",
                                                "MaaCare Women's Hospital"));

                information.getChildren().addAll(
                                personalInfo,
                                professionalInfo);

                // =========================================================
                // EDIT PROFILE BUTTON
                // =========================================================

                Button editButton = Theme.primaryButton(
                                "✏  Edit Profile");

                editButton.setPrefWidth(145);

                editButton.setOnAction(e -> {

                        System.out.println(
                                        "Edit Profile button clicked");

                        // Later:
                        // DoctorProfileEditPage.show();
                });

                HBox buttonBox = new HBox(
                                editButton);

                buttonBox.setAlignment(
                                Pos.CENTER_RIGHT);

                // =========================================================
                // ADD CONTENT
                // =========================================================

                content.getChildren().addAll(
                                header,
                                profileCard,
                                information,
                                buttonBox);

                // =========================================================
                // ROOT
                // =========================================================

                root.setCenter(content);

                // =========================================================
                // SCENE
                // =========================================================

                profileScene = new Scene(root);

                // =========================================================
                // OPEN PROFILE
                // =========================================================

                DoctorDashboard.changeScene(
                                profileScene);
        }

        // =========================================================
        // INFORMATION ROW
        // =========================================================

        private static HBox infoRow(
                        String icon,
                        String title,
                        String value) {

                HBox row = new HBox(12);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                // ---------------------------------------------------------
                // ICON
                // ---------------------------------------------------------

                Label iconLabel = new Label(
                                icon);

                iconLabel.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-background-color: #F3ECFF;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 7;");

                // ---------------------------------------------------------
                // TEXT
                // ---------------------------------------------------------

                VBox text = new VBox(2);

                Label titleLabel = new Label(
                                title);

                titleLabel.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                11));

                titleLabel.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

                Label valueLabel = new Label(
                                value);

                valueLabel.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                13));

                valueLabel.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                text.getChildren().addAll(
                                titleLabel,
                                valueLabel);

                row.getChildren().addAll(
                                iconLabel,
                                text);

                return row;
        }
}