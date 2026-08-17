package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DoctorProfilePage {

    private static Scene profileScene;

    public static void show() {

        BorderPane root = new BorderPane();

        Theme.applyBackground(root);

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(25, 30, 25, 30));

        Theme.applyBackground(content);

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox(15);

        header.setAlignment(
                Pos.CENTER_LEFT);

        Button backButton = new Button("← Back to Dashboard");

        backButton.setStyle(
                "-fx-background-color: " +
                        Theme.PRIMARY + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: '" +
                        Theme.FONT + "';" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: BOLD;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 9 15;" +
                        "-fx-cursor: hand;");

        backButton.setOnAction(
                e -> DoctorDashboard.showDashboard());

        Label title = new Label(
                "Doctor Profile");

        title.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        22));

        title.setTextFill(
                Color.web(Theme.TEXT));

        header.getChildren().addAll(
                backButton,
                title);

        // =====================================================
        // PROFILE CARD
        // =====================================================

        VBox profileCard = Theme.card();

        profileCard.setSpacing(15);

        Label profileIcon = new Label("👨🏻‍⚕️");

        profileIcon.setStyle(
                "-fx-font-family: 'Segoe UI Emoji';" +
                        "-fx-font-size: 55px;");

        Label name = new Label(
                "Dr. Anjali Mehta");

        name.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        20));

        name.setTextFill(
                Color.web(Theme.TEXT));

        Label specialization = new Label(
                "Obstetrician & Gynecologist");

        specialization.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        13));

        specialization.setTextFill(
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

        profileCard.getChildren().addAll(
                profileIcon,
                name,
                specialization,
                status);

        // =====================================================
        // INFORMATION
        // =====================================================

        HBox information = new HBox(20);

        VBox personalInfo = Theme.card();

        HBox.setHgrow(
                personalInfo,
                Priority.ALWAYS);

        Label personalTitle = new Label(
                "Personal Information");

        personalTitle.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        16));

        personalTitle.setTextFill(
                Color.web(Theme.TEXT));

        personalInfo.getChildren().addAll(
                personalTitle,
                infoRow("👤", "Full Name",
                        "Dr. Anjali Mehta"),
                infoRow("📧", "Email",
                        "anjali.mehta@example.com"),
                infoRow("📱", "Phone",
                        "+91 98765 43210"),
                infoRow("🎂", "Date of Birth",
                        "15 June 1988"));

        VBox professionalInfo = Theme.card();

        HBox.setHgrow(
                professionalInfo,
                Priority.ALWAYS);

        Label professionalTitle = new Label(
                "Professional Information");

        professionalTitle.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        16));

        professionalTitle.setTextFill(
                Color.web(Theme.TEXT));

        professionalInfo.getChildren().addAll(
                professionalTitle,
                infoRow("🩺", "Specialization",
                        "Obstetrician & Gynecologist"),
                infoRow("🎓", "Qualification",
                        "MBBS, MD"),
                infoRow("💼", "Experience",
                        "12 Years"),
                infoRow("🏥", "Hospital",
                        "MaaCare Women's Hospital"));

        information.getChildren().addAll(
                personalInfo,
                professionalInfo);

        // =====================================================
        // EDIT BUTTON
        // =====================================================

        Button editButton = new Button(
                "✏️  Edit Profile");

        editButton.setStyle(
                "-fx-background-color: " +
                        Theme.PRIMARY + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: '" +
                        Theme.FONT + "';" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: BOLD;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 10 18;" +
                        "-fx-cursor: hand;");

        HBox buttonBox = new HBox(
                editButton);

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT);

        content.getChildren().addAll(
                header,
                profileCard,
                information,
                buttonBox);

        root.setCenter(content);

        profileScene = new Scene(root);

        DoctorDashboard.changeScene(
                profileScene);
    }

    // =====================================================
    // INFORMATION ROW
    // =====================================================

    private static HBox infoRow(
            String icon,
            String title,
            String value) {

        HBox row = new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-font-family: 'Segoe UI Emoji';" +
                        "-fx-font-size: 18px;");

        VBox text = new VBox(2);

        Label titleLabel = new Label(title);

        titleLabel.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        11));

        titleLabel.setTextFill(
                Color.web(Theme.SECONDARY_TEXT));

        Label valueLabel = new Label(value);

        valueLabel.setFont(
                javafx.scene.text.Font.font(
                        Theme.FONT,
                        javafx.scene.text.FontWeight.BOLD,
                        13));

        valueLabel.setTextFill(
                Color.web(Theme.TEXT));

        text.getChildren().addAll(
                titleLabel,
                valueLabel);

        row.getChildren().addAll(
                iconLabel,
                text);

        return row;
    }
}