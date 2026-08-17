package com.sigma.view.adminpages;

import com.sigma.view.scenesettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminProfile {

    private Scene adminProfileScene;

    public Scene getAdminProfileScene() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #FAF8FD;");

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(20, 35, 20, 35));
        topBar.setStyle("-fx-background-color: white;-fx-border-color: #E8E2EF;-fx-border-width: 0 0 1 0;");

        HBox topLeft = new HBox(20);
        topLeft.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("←  Back to Dashboard");
        backButton.setStyle("-fx-background-color: #F1E8FF;-fx-text-fill: #713CC3;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;");

        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;"));

        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #F1E8FF;-fx-text-fill: #713CC3;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;"));

        VBox headingBox = new VBox(5);

        Label pageTitle = new Label("Admin Profile");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        pageTitle.setTextFill(Color.web("#24234F"));

        Label breadcrumb = new Label("Dashboard   ›   Admin Profile");
        breadcrumb.setStyle("-fx-text-fill: #713CC3;-fx-font-size: 13px;");

        headingBox.getChildren().addAll(pageTitle, breadcrumb);
        topLeft.getChildren().addAll(backButton, headingBox);

        Button notification = new Button("🔔  3");
        notification.setStyle("-fx-background-color: #F5EEFF;-fx-text-fill: #713CC3;-fx-background-radius: 12px;-fx-font-size: 14px;-fx-font-weight: bold;-fx-cursor: hand;");

        Label adminTop = new Label("Admin\nSuper Administrator");
        adminTop.setStyle("-fx-text-fill: #24234F;-fx-font-size: 13px;-fx-font-weight: bold;");

        HBox topRight = new HBox(15, notification, adminTop);
        topRight.setAlignment(Pos.CENTER_RIGHT);

        topBar.setLeft(topLeft);
        topBar.setRight(topRight);

        VBox content = new VBox(22);
        content.setPadding(new Insets(30, 40, 40, 40));

        HBox profileCard = new HBox(35);
        profileCard.setPadding(new Insets(25));
        profileCard.setAlignment(Pos.CENTER_LEFT);
        profileCard.setStyle("-fx-background-color: white;-fx-background-radius: 18px;-fx-border-color: #E8E2EF;-fx-border-radius: 18px;-fx-effect: dropshadow(gaussian, rgba(80,50,120,0.08), 15, 0, 0, 4);");

        Image profileImage = null;

        StackPane profileImageContainer = new StackPane();
        profileImageContainer.setPrefSize(180, 180);
        profileImageContainer.setMaxSize(180, 180);

        Circle profileBackground = new Circle(90);
        profileBackground.setFill(Color.web("#F0E8FF"));
        profileImageContainer.getChildren().add(profileBackground);

        ImageView profileImageView = new ImageView();
        profileImageView.setPreserveRatio(true);
        profileImageView.setSmooth(true);
        profileImageView.setFitWidth(165);
        profileImageView.setFitHeight(165);

        if (profileImage != null) {
            profileImageView.setImage(profileImage);
            Circle clip = new Circle(82.5, 82.5, 82.5);
            profileImageView.setClip(clip);
            profileImageContainer.getChildren().add(profileImageView);
        } else {
            Label noImage = new Label("ADMIN");
            noImage.setStyle("-fx-text-fill: #713CC3;-fx-font-size: 22px;-fx-font-weight: bold;");
            profileImageContainer.getChildren().add(noImage);
        }

        Button changeImage = new Button("📷");
        changeImage.setPrefSize(42, 42);
        changeImage.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 21px;-fx-font-size: 16px;-fx-cursor: hand;");
        StackPane.setAlignment(changeImage, Pos.BOTTOM_RIGHT);
        profileImageContainer.getChildren().add(changeImage);

        VBox adminInfo = new VBox(10);

        Label adminName = new Label("Super Administrator");
        adminName.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        adminName.setTextFill(Color.web("#24234F"));

        Label role = new Label("System Administrator");
        role.setStyle("-fx-background-color: #F0E5FF;-fx-text-fill: #713CC3;-fx-background-radius: 15px;-fx-padding: 6 12 6 12;-fx-font-size: 12px;-fx-font-weight: bold;");

        Label email = new Label("✉   admin@maacareai.com");
        email.setStyle("-fx-text-fill: #555570;-fx-font-size: 14px;");

        Label phone = new Label("☎   +91 98765 43210");
        phone.setStyle("-fx-text-fill: #555570;-fx-font-size: 14px;");

        Label joined = new Label("▣   Joined on 15 March 2024");
        joined.setStyle("-fx-text-fill: #555570;-fx-font-size: 14px;");

        Label location = new Label("⌖   India");
        location.setStyle("-fx-text-fill: #555570;-fx-font-size: 14px;");

        adminInfo.getChildren().addAll(adminName, role, email, phone, joined, location);

        HBox statistics = new HBox(12);
        statistics.getChildren().addAll(createStatCard("12,450", "Total Users", "♟"), createStatCard("184", "Doctors", "⚕"), createStatCard("42", "Hospitals", "▣"), createStatCard("156", "ASHA Workers", "♟"));

        profileCard.getChildren().addAll(profileImageContainer, adminInfo, statistics);

        HBox lowerSection = new HBox(20);

        VBox personalCard = new VBox(18);
        personalCard.setPadding(new Insets(25));
        personalCard.setStyle("-fx-background-color: white;-fx-background-radius: 18px;-fx-border-color: #E8E2EF;-fx-border-radius: 18px;");

        HBox personalHeader = new HBox();

        Label personalTitle = new Label("👤   Personal Information");
        personalTitle.setStyle("-fx-text-fill: #24234F;-fx-font-size: 18px;-fx-font-weight: bold;");

        Button editProfile = new Button("✎  Edit Profile");
        editProfile.setStyle("-fx-background-color: white;-fx-text-fill: #713CC3;-fx-border-color: #713CC3;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-font-weight: bold;-fx-cursor: hand;");

        HBox.setHgrow(personalTitle, Priority.ALWAYS);
        personalHeader.getChildren().addAll(personalTitle, editProfile);

        TextField fullName = createField("Full Name", "Super Administrator");

        HBox emailPhone = new HBox(15);
        VBox emailBox = createFieldBox("Email Address", "admin@maacareai.com");
        VBox phoneBox = createFieldBox("Phone Number", "+91 98765 43210");
        HBox.setHgrow(emailBox, Priority.ALWAYS);
        HBox.setHgrow(phoneBox, Priority.ALWAYS);
        emailPhone.getChildren().addAll(emailBox, phoneBox);

        HBox dobGender = new HBox(15);
        VBox dobBox = createFieldBox("Date of Birth", "15/03/1990");
        VBox genderBox = createFieldBox("Gender", "Male");
        HBox.setHgrow(dobBox, Priority.ALWAYS);
        HBox.setHgrow(genderBox, Priority.ALWAYS);
        dobGender.getChildren().addAll(dobBox, genderBox);

        VBox addressBox = createFieldBox("Address", "India");

        Label aboutLabel = new Label("About");
        aboutLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 12px;-fx-font-weight: bold;");

        TextArea about = new TextArea("System administrator with full access to manage MaaCare AI platform and ensure smooth operations.");
        about.setWrapText(true);
        about.setPrefRowCount(4);
        about.setStyle("-fx-background-color: white;-fx-border-color: #DED9E8;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-font-size: 13px;");

        personalCard.getChildren().addAll(personalHeader, fullName, emailPhone, dobGender, addressBox, aboutLabel, about);

        VBox rightSection = new VBox(20);

        VBox securityCard = new VBox(18);
        securityCard.setPadding(new Insets(25));
        securityCard.setStyle("-fx-background-color: white;-fx-background-radius: 18px;-fx-border-color: #E8E2EF;-fx-border-radius: 18px;");

        Label securityTitle = new Label("🛡   Security Settings");
        securityTitle.setStyle("-fx-text-fill: #24234F;-fx-font-size: 18px;-fx-font-weight: bold;");

        HBox passwordRow = new HBox();

        VBox passwordInfo = new VBox(4);
        Label password = new Label("Password");
        password.setStyle("-fx-text-fill: #24234F;-fx-font-weight: bold;");

        Label dots = new Label("••••••••••••");
        dots.setStyle("-fx-text-fill: #77778D;-fx-font-size: 13px;");
        passwordInfo.getChildren().addAll(password, dots);

        Button changePassword = new Button("Change Password");
        changePassword.setStyle("-fx-background-color: white;-fx-text-fill: #713CC3;-fx-border-color: #713CC3;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-cursor: hand;");

        HBox.setHgrow(passwordInfo, Priority.ALWAYS);
        passwordRow.getChildren().addAll(passwordInfo, changePassword);

        HBox twoFactor = new HBox();

        VBox twoFactorText = new VBox(4);
        Label twoFactorTitle = new Label("Two-Factor Authentication");
        twoFactorTitle.setStyle("-fx-text-fill: #24234F;-fx-font-weight: bold;");

        Label twoFactorSubtitle = new Label("Add an extra layer of security");
        twoFactorSubtitle.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;");

        twoFactorText.getChildren().addAll(twoFactorTitle, twoFactorSubtitle);

        Button twoFactorButton = new Button("ON");
        twoFactorButton.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 15px;-fx-font-weight: bold;-fx-cursor: hand;");

        HBox.setHgrow(twoFactorText, Priority.ALWAYS);
        twoFactor.getChildren().addAll(twoFactorText, twoFactorButton);

        HBox sessions = new HBox();

        VBox sessionText = new VBox(4);
        Label sessionTitle = new Label("Active Sessions");
        sessionTitle.setStyle("-fx-text-fill: #24234F;-fx-font-weight: bold;");

        Label sessionSubtitle = new Label("You are currently logged in on 1 device");
        sessionSubtitle.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;");

        sessionText.getChildren().addAll(sessionTitle, sessionSubtitle);

        Button viewSessions = new Button("View Sessions");
        viewSessions.setStyle("-fx-background-color: white;-fx-text-fill: #713CC3;-fx-border-color: #713CC3;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-cursor: hand;");

        HBox.setHgrow(sessionText, Priority.ALWAYS);
        sessions.getChildren().addAll(sessionText, viewSessions);

        securityCard.getChildren().addAll(securityTitle, passwordRow, twoFactor, sessions);

        VBox activityCard = new VBox(15);
        activityCard.setPadding(new Insets(25));
        activityCard.setStyle("-fx-background-color: white;-fx-background-radius: 18px;-fx-border-color: #E8E2EF;-fx-border-radius: 18px;");

        HBox activityHeader = new HBox();

        Label activityTitle = new Label("◷   Recent Activity");
        activityTitle.setStyle("-fx-text-fill: #24234F;-fx-font-size: 18px;-fx-font-weight: bold;");

        Button viewActivity = new Button("View All Activity");
        viewActivity.setStyle("-fx-background-color: white;-fx-text-fill: #713CC3;-fx-border-color: #713CC3;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-cursor: hand;");

        HBox.setHgrow(activityTitle, Priority.ALWAYS);
        activityHeader.getChildren().addAll(activityTitle, viewActivity);

        activityCard.getChildren().add(activityHeader);

        activityCard.getChildren().addAll(
            createActivity("✓", "Approved doctor registration", "Dr. Priya Sharma", "15 Aug 2026, 06:15 PM"),
            createActivity("+", "Added new hospital", "City Care Hospital", "15 Aug 2026, 04:42 PM"),
            createActivity("▤", "Generated verification report", "Monthly Verification Report", "15 Aug 2026, 02:30 PM"),
            createActivity("⚙", "Updated system settings", "Notification preferences updated", "14 Aug 2026, 11:20 AM")
        );

        rightSection.getChildren().addAll(securityCard, activityCard);

        HBox.setHgrow(personalCard, Priority.ALWAYS);
        HBox.setHgrow(rightSection, Priority.ALWAYS);

        lowerSection.getChildren().addAll(personalCard, rightSection);

        content.getChildren().addAll(profileCard, lowerSection);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent;-fx-background: #FAF8FD;");

        root.setTop(topBar);
        root.setCenter(scrollPane);

        adminProfileScene = new Scene(root, scenesettings.rectanguler2d.getWidth(), scenesettings.rectanguler2d.getHeight());

        return adminProfileScene;
    }

    private VBox createStatCard(String value, String title, String icon) {

        VBox card = new VBox(6);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setPrefWidth(145);
        card.setStyle("-fx-background-color: #FCFAFF;-fx-background-radius: 14px;-fx-border-color: #E8E0F5;-fx-border-radius: 14px;");

        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-background-color: #F0E5FF;-fx-text-fill: #713CC3;-fx-background-radius: 30px;-fx-padding: 8px;-fx-font-size: 18px;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 22px;-fx-font-weight: bold;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;");

        card.getChildren().addAll(iconLabel, valueLabel, titleLabel);

        return card;
    }

    private TextField createField(String label, String value) {

        TextField field = new TextField(value);
        field.setPrefHeight(42);
        field.setStyle("-fx-background-color: white;-fx-border-color: #DED9E8;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-font-size: 13px;");

        return field;
    }

    private VBox createFieldBox(String label, String value) {

        VBox box = new VBox(7);

        Label fieldLabel = new Label(label);
        fieldLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 12px;-fx-font-weight: bold;");

        TextField field = new TextField(value);
        field.setPrefHeight(42);
        field.setStyle("-fx-background-color: white;-fx-border-color: #DED9E8;-fx-border-radius: 8px;-fx-background-radius: 8px;-fx-font-size: 13px;");

        box.getChildren().addAll(fieldLabel, field);

        return box;
    }

    private HBox createActivity(String icon, String title, String subtitle, String time) {

        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(8, 0, 8, 0));

        Label iconLabel = new Label(icon);
        iconLabel.setPrefSize(38, 38);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setStyle("-fx-background-color: #F0E5FF;-fx-text-fill: #713CC3;-fx-background-radius: 20px;-fx-font-size: 16px;-fx-font-weight: bold;");

        VBox text = new VBox(3);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 12px;-fx-font-weight: bold;");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setStyle("-fx-text-fill: #77778D;-fx-font-size: 11px;");

        text.getChildren().addAll(titleLabel, subtitleLabel);

        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill: #88869A;-fx-font-size: 10px;");

        HBox.setHgrow(text, Priority.ALWAYS);

        row.getChildren().addAll(iconLabel, text, timeLabel);

        return row;
    }
}