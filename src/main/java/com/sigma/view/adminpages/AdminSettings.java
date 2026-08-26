package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.effect.DropShadow;


/**
 * MaaCare AI
 *
 * ADMIN SETTINGS PAGE
 *
 * This page is designed to be created ONCE by AdminDashboard
 * and then reused.
 *
 * AdminDashboard:
 *
 *      private AdminSettings settingsPage;
 *      private Node settingsRoot;
 *
 *      settingsPage = new AdminSettings();
 *      settingsRoot = settingsPage.getSettingsRoot();
 *
 * Then:
 *
 *      root.setCenter(settingsRoot);
 *
 * No new AdminSettings object is created when the button
 * is pressed again.
 */
public class AdminSettings {


    // =========================================================
    // MAIN ROOT
    // =========================================================

    private VBox root;


    // =========================================================
    // ADMIN DATA
    // =========================================================

    private String adminName = "Admin";

    private String adminEmail = "admin@maacareai.com";

    private String adminRole = "Administrator";

    private String adminPhone = "";


    // =========================================================
    // PROFILE FIELDS
    // =========================================================

    private TextField nameField;

    private TextField emailField;

    private TextField roleField;

    private TextField phoneField;


    // =========================================================
    // PASSWORD FIELDS
    // =========================================================

    private PasswordField currentPasswordField;

    private PasswordField newPasswordField;

    private PasswordField confirmPasswordField;


    // =========================================================
    // STATUS
    // =========================================================

    private Label statusLabel;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminSettings() {

        createPage();
    }


    // =========================================================
    // CREATE PAGE
    // =========================================================

    private void createPage() {

        // -----------------------------------------------------
        // ROOT
        // -----------------------------------------------------

        root =
            new VBox(20);

        root.setPadding(
            new Insets(30)
        );

        root.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        // -----------------------------------------------------
        // PAGE TITLE
        // -----------------------------------------------------

        Label title =
            new Label(
                "Settings"
            );

        title.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;"
        );


        Label subtitle =
            new Label(
                "Manage your administrator account and security settings."
            );

        subtitle.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 14px;"
        );


        VBox heading =
            new VBox(
                5,
                title,
                subtitle
            );


        // -----------------------------------------------------
        // PROFILE CARD
        // -----------------------------------------------------

        VBox profileCard =
            createProfileCard();


        // -----------------------------------------------------
        // SECURITY CARD
        // -----------------------------------------------------

        VBox securityCard =
            createSecurityCard();


        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

        statusLabel =
            new Label();

        statusLabel.setVisible(
            false
        );

        statusLabel.setManaged(
            false
        );

        statusLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );


        // -----------------------------------------------------
        // ADD EVERYTHING
        // -----------------------------------------------------

        root.getChildren().addAll(

            heading,

            profileCard,

            securityCard,

            statusLabel
        );
    }


    // =========================================================
    // PROFILE CARD
    // =========================================================

    private VBox createProfileCard() {

        VBox card =
            createCard();


        // -----------------------------------------------------
        // CARD TITLE
        // -----------------------------------------------------

        Label title =
            new Label(
                "Administrator Profile"
            );

        title.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );


        Label description =
            new Label(
                "Update the basic information associated with your admin account."
            );

        description.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 12px;"
        );


        // -----------------------------------------------------
        // NAME
        // -----------------------------------------------------

        Label nameLabel =
            createFieldLabel(
                "Name"
            );


        nameField =
            new TextField(
                adminName
            );

        styleTextField(
            nameField
        );


        // -----------------------------------------------------
        // EMAIL
        // -----------------------------------------------------

        Label emailLabel =
            createFieldLabel(
                "Email"
            );


        emailField =
            new TextField(
                adminEmail
            );

        styleTextField(
            emailField
        );


        // -----------------------------------------------------
        // ROLE
        // -----------------------------------------------------

        Label roleLabel =
            createFieldLabel(
                "Role"
            );


        roleField =
            new TextField(
                adminRole
            );

        styleTextField(
            roleField
        );

        roleField.setEditable(
            false
        );

        roleField.setStyle(
            "-fx-background-color: #F1EEF5;" +
            "-fx-background-radius: 8px;" +
            "-fx-border-color: #DDD7E6;" +
            "-fx-border-radius: 8px;" +
            "-fx-padding: 10px;"
        );


        // -----------------------------------------------------
        // PHONE
        // -----------------------------------------------------

        Label phoneLabel =
            createFieldLabel(
                "Phone"
            );


        phoneField =
            new TextField(
                adminPhone
            );

        phoneField.setPromptText(
            "Enter phone number"
        );

        styleTextField(
            phoneField
        );


        // -----------------------------------------------------
        // ROW 1
        // -----------------------------------------------------

        VBox nameBox =
            createFieldBox(
                nameLabel,
                nameField
            );


        VBox emailBox =
            createFieldBox(
                emailLabel,
                emailField
            );


        HBox row1 =
            new HBox(
                20,
                nameBox,
                emailBox
            );


        HBox.setHgrow(
            nameBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            emailBox,
            Priority.ALWAYS
        );


        // -----------------------------------------------------
        // ROW 2
        // -----------------------------------------------------

        VBox roleBox =
            createFieldBox(
                roleLabel,
                roleField
            );


        VBox phoneBox =
            createFieldBox(
                phoneLabel,
                phoneField
            );


        HBox row2 =
            new HBox(
                20,
                roleBox,
                phoneBox
            );


        HBox.setHgrow(
            roleBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            phoneBox,
            Priority.ALWAYS
        );


        // -----------------------------------------------------
        // SAVE BUTTON
        // -----------------------------------------------------

        Button saveButton =
            new Button(
                "Save Changes"
            );

        saveButton.setPrefHeight(
            40
        );

        saveButton.setPrefWidth(
            140
        );

        saveButton.setStyle(
            "-fx-background-color: #713CC3;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );


        saveButton.setOnAction(
            e -> saveProfile()
        );


        // -----------------------------------------------------
        // CARD CONTENT
        // -----------------------------------------------------

        card.getChildren().addAll(

            title,

            description,

            row1,

            row2,

            saveButton
        );


        return card;
    }


    // =========================================================
    // SECURITY CARD
    // =========================================================

    private VBox createSecurityCard() {

        VBox card =
            createCard();


        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label title =
            new Label(
                "Security"
            );

        title.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );


        Label description =
            new Label(
                "Change your administrator password."
            );

        description.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 12px;"
        );


        // -----------------------------------------------------
        // CURRENT PASSWORD
        // -----------------------------------------------------

        Label currentLabel =
            createFieldLabel(
                "Current Password"
            );


        currentPasswordField =
            new PasswordField();

        currentPasswordField.setPromptText(
            "Enter current password"
        );

        styleTextField(
            currentPasswordField
        );


        // -----------------------------------------------------
        // NEW PASSWORD
        // -----------------------------------------------------

        Label newLabel =
            createFieldLabel(
                "New Password"
            );


        newPasswordField =
            new PasswordField();

        newPasswordField.setPromptText(
            "Enter new password"
        );

        styleTextField(
            newPasswordField
        );


        // -----------------------------------------------------
        // CONFIRM PASSWORD
        // -----------------------------------------------------

        Label confirmLabel =
            createFieldLabel(
                "Confirm New Password"
            );


        confirmPasswordField =
            new PasswordField();

        confirmPasswordField.setPromptText(
            "Confirm new password"
        );

        styleTextField(
            confirmPasswordField
        );


        // -----------------------------------------------------
        // ROW
        // -----------------------------------------------------

        VBox currentBox =
            createFieldBox(
                currentLabel,
                currentPasswordField
            );


        VBox newBox =
            createFieldBox(
                newLabel,
                newPasswordField
            );


        VBox confirmBox =
            createFieldBox(
                confirmLabel,
                confirmPasswordField
            );


        HBox passwordRow =
            new HBox(
                20,
                currentBox,
                newBox,
                confirmBox
            );


        HBox.setHgrow(
            currentBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            newBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            confirmBox,
            Priority.ALWAYS
        );


        // -----------------------------------------------------
        // CHANGE PASSWORD BUTTON
        // -----------------------------------------------------

        Button changePasswordButton =
            new Button(
                "Change Password"
            );


        changePasswordButton.setPrefHeight(
            40
        );


        changePasswordButton.setPrefWidth(
            160
        );


        changePasswordButton.setStyle(
            "-fx-background-color: #EDE4FA;" +
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );


        changePasswordButton.setOnAction(
            e -> changePassword()
        );


        // -----------------------------------------------------
        // CARD CONTENT
        // -----------------------------------------------------

        card.getChildren().addAll(

            title,

            description,

            passwordRow,

            changePasswordButton
        );


        return card;
    }


    // =========================================================
    // SAVE PROFILE
    // =========================================================

    private void saveProfile() {

        String name =
            nameField.getText()
                .trim();


        String email =
            emailField.getText()
                .trim();


        String phone =
            phoneField.getText()
                .trim();


        // -----------------------------------------------------
        // BASIC VALIDATION
        // -----------------------------------------------------

        if (name.isEmpty()) {

            showStatus(
                "Name cannot be empty.",
                false
            );

            return;
        }


        if (email.isEmpty()) {

            showStatus(
                "Email cannot be empty.",
                false
            );

            return;
        }


        // -----------------------------------------------------
        // UPDATE LOCAL DATA
        // -----------------------------------------------------

        adminName =
            name;

        adminEmail =
            email;

        adminPhone =
            phone;


        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        showStatus(
            "Profile changes saved successfully.",
            true
        );
    }


    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void changePassword() {

        String currentPassword =
            currentPasswordField
                .getText();


        String newPassword =
            newPasswordField
                .getText();


        String confirmPassword =
            confirmPasswordField
                .getText();


        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (currentPassword.isEmpty()) {

            showStatus(
                "Enter your current password.",
                false
            );

            return;
        }


        if (newPassword.isEmpty()) {

            showStatus(
                "Enter a new password.",
                false
            );

            return;
        }


        if (newPassword.length() < 6) {

            showStatus(
                "New password must contain at least 6 characters.",
                false
            );

            return;
        }


        if (!newPassword.equals(
                confirmPassword)) {

            showStatus(
                "New passwords do not match.",
                false
            );

            return;
        }


        // -----------------------------------------------------
        // CLEAR FIELDS
        // -----------------------------------------------------

        currentPasswordField.clear();

        newPasswordField.clear();

        confirmPasswordField.clear();


        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        showStatus(
            "Password changed successfully.",
            true
        );
    }


    // =========================================================
    // STATUS MESSAGE
    // =========================================================

    private void showStatus(
            String message,
            boolean success) {

        statusLabel.setText(
            message
        );


        if (success) {

            statusLabel.setTextFill(
                Color.web("#20A56A")
            );

        } else {

            statusLabel.setTextFill(
                Color.web("#D94A4A")
            );
        }


        statusLabel.setVisible(
            true
        );

        statusLabel.setManaged(
            true
        );
    }


    // =========================================================
    // CREATE CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
            new VBox(15);


        card.setPadding(
            new Insets(22)
        );


        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();


        shadow.setRadius(
            12
        );


        shadow.setOffsetY(
            4
        );


        shadow.setColor(
            Color.rgb(
                60,
                30,
                80,
                0.06
            )
        );


        card.setEffect(
            shadow
        );


        return card;
    }


    // =========================================================
    // FIELD LABEL
    // =========================================================

    private Label createFieldLabel(
            String text) {

        Label label =
            new Label(
                text
            );


        label.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
        );


        return label;
    }


    // =========================================================
    // FIELD BOX
    // =========================================================

    private VBox createFieldBox(
            Label label,
            Node field) {

        VBox box =
            new VBox(7);


        box.getChildren().addAll(

            label,

            field
        );


        HBox.setHgrow(
            box,
            Priority.ALWAYS
        );


        return box;
    }


    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field) {

        field.setPrefHeight(
            40
        );


        field.setMaxWidth(
            Double.MAX_VALUE
        );


        field.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #DDD7E6;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 10px;" +
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 12px;"
        );
    }


    // =========================================================
    // GET SETTINGS ROOT
    // =========================================================

    public VBox getSettingsRoot() {

        return root;
    }


    // =========================================================
    // GETTERS
    // =========================================================

    public String getAdminName() {

        return adminName;
    }


    public String getAdminEmail() {

        return adminEmail;
    }


    public String getAdminRole() {

        return adminRole;
    }


    public String getAdminPhone() {

        return adminPhone;
    }


    // =========================================================
    // SETTERS
    // =========================================================

    public void setAdminName(
            String adminName) {

        this.adminName =
            adminName;

        if (nameField != null) {

            nameField.setText(
                adminName
            );
        }
    }


    public void setAdminEmail(
            String adminEmail) {

        this.adminEmail =
            adminEmail;

        if (emailField != null) {

            emailField.setText(
                adminEmail
            );
        }
    }


    public void setAdminPhone(
            String adminPhone) {

        this.adminPhone =
            adminPhone;

        if (phoneField != null) {

            phoneField.setText(
                adminPhone
            );
        }
    }
}