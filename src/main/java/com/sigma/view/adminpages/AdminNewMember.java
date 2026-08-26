package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminNewMember {

    private BorderPane root;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private TextField fullNameField;
    private TextField emailField;
    private TextField phoneField;
    private TextField registrationIdField;
    private TextField qualificationField;
    private TextField addressField;

    private ComboBox<String> memberTypeCombo;
    private ComboBox<String> genderCombo;

    private DatePicker dobPicker;

    private TextArea additionalInfoArea;

    // =========================================================
    // COLORS
    // =========================================================

    private static final String PURPLE = "#713CC3";
    private static final String DARK = "#24234F";
    private static final String GREY = "#77778D";
    private static final String BORDER = "#E7E1EF";
    private static final String BACKGROUND = "#FAF8FD";

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public BorderPane getNewMemberRoot() {

        root = new BorderPane();

        root.setStyle(
            "-fx-background-color: " + BACKGROUND + ";"
        );

        // =====================================================
        // TOP PAGE HEADER
        // =====================================================

        VBox header = createHeader();

        // =====================================================
        // FORM
        // =====================================================

        VBox content = createForm();

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: " + BACKGROUND + ";"
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.setTop(header);
        root.setCenter(scrollPane);

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header = new VBox(6);

        header.setPadding(
            new Insets(30, 40, 15, 40)
        );

        Label title = new Label(
            "Add New Member"
        );

        title.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                30
            )
        );

        title.setTextFill(
            Color.web(DARK)
        );

        Label subtitle = new Label(
            "Register a new healthcare provider or worker on the MaaCare AI platform."
        );

        subtitle.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 14px;"
        );

        header.getChildren().addAll(
            title,
            subtitle
        );

        return header;
    }

    // =========================================================
    // MAIN FORM
    // =========================================================

    private VBox createForm() {

        VBox main = new VBox(20);

        main.setPadding(
            new Insets(15, 40, 40, 40)
        );

        // =====================================================
        // MEMBER TYPE
        // =====================================================

        VBox memberTypeCard =
            createCard();

        Label memberTypeTitle =
            createSectionTitle(
                "1. Member Type"
            );

        Label memberTypeDescription =
            new Label(
                "Select the type of healthcare member you want to register."
            );

        memberTypeDescription.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        memberTypeCombo =
            new ComboBox<>();

        memberTypeCombo.getItems().addAll(
            "Doctor",
            "Hospital",
            "ASHA Worker",
            "Ambulance Provider"
        );

        memberTypeCombo.setPromptText(
            "Select member type"
        );

        memberTypeCombo.setPrefHeight(42);

        memberTypeCombo.setMaxWidth(
            Double.MAX_VALUE
        );

        styleComboBox(
            memberTypeCombo
        );

        memberTypeCard.getChildren().addAll(
            memberTypeTitle,
            memberTypeDescription,
            memberTypeCombo
        );

        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        VBox personalCard =
            createCard();

        Label personalTitle =
            createSectionTitle(
                "2. Personal Information"
            );

        Label personalDescription =
            new Label(
                "Enter the basic details of the new member."
            );

        personalDescription.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        GridPane personalGrid =
            new GridPane();

        personalGrid.setHgap(20);
        personalGrid.setVgap(15);

        fullNameField =
            createTextField(
                "Enter full name"
            );

        emailField =
            createTextField(
                "Enter email address"
            );

        phoneField =
            createTextField(
                "Enter phone number"
            );

        registrationIdField =
            createTextField(
                "Enter registration / ID number"
            );

        qualificationField =
            createTextField(
                "Enter qualification"
            );

        addressField =
            createTextField(
                "Enter address"
            );

        genderCombo =
            new ComboBox<>();

        genderCombo.getItems().addAll(
            "Male",
            "Female",
            "Other"
        );

        genderCombo.setPromptText(
            "Select gender"
        );

        genderCombo.setPrefHeight(42);

        genderCombo.setMaxWidth(
            Double.MAX_VALUE
        );

        styleComboBox(
            genderCombo
        );

        dobPicker =
            new DatePicker();

        dobPicker.setPromptText(
            "Select date of birth"
        );

        dobPicker.setPrefHeight(42);

        dobPicker.setMaxWidth(
            Double.MAX_VALUE
        );

        styleDatePicker(
            dobPicker
        );

        personalGrid.add(
            createFieldBox(
                "Full Name",
                fullNameField
            ),
            0,
            0
        );

        personalGrid.add(
            createFieldBox(
                "Email Address",
                emailField
            ),
            1,
            0
        );

        personalGrid.add(
            createFieldBox(
                "Phone Number",
                phoneField
            ),
            0,
            1
        );

        personalGrid.add(
            createFieldBox(
                "Registration / ID",
                registrationIdField
            ),
            1,
            1
        );

        personalGrid.add(
            createFieldBox(
                "Qualification",
                qualificationField
            ),
            0,
            2
        );

        personalGrid.add(
            createFieldBox(
                "Gender",
                genderCombo
            ),
            1,
            2
        );

        personalGrid.add(
            createFieldBox(
                "Date of Birth",
                dobPicker
            ),
            0,
            3
        );

        personalGrid.add(
            createFieldBox(
                "Address",
                addressField
            ),
            1,
            3
        );

        ColumnConstraintsHelper.setGrow(
            personalGrid
        );

        personalCard.getChildren().addAll(
            personalTitle,
            personalDescription,
            personalGrid
        );

        // =====================================================
        // ADDITIONAL INFORMATION
        // =====================================================

        VBox additionalCard =
            createCard();

        Label additionalTitle =
            createSectionTitle(
                "3. Additional Information"
            );

        Label additionalDescription =
            new Label(
                "Add any additional information required for verification."
            );

        additionalDescription.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        additionalInfoArea =
            new TextArea();

        additionalInfoArea.setPromptText(
            "Enter additional information..."
        );

        additionalInfoArea.setWrapText(
            true
        );

        additionalInfoArea.setPrefRowCount(
            5
        );

        additionalInfoArea.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;"
        );

        additionalCard.getChildren().addAll(
            additionalTitle,
            additionalDescription,
            additionalInfoArea
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
            new HBox(12);

        buttons.setAlignment(
            Pos.CENTER_RIGHT
        );

        Button clearButton =
            new Button(
                "Clear"
            );

        styleClearButton(
            clearButton
        );

        Button registerButton =
            new Button(
                "✓  Register Member"
            );

        styleRegisterButton(
            registerButton
        );

        clearButton.setOnAction(e -> {

            clearForm();

            System.out.println(
                "[ADMIN] New member form cleared"
            );
        });

        registerButton.setOnAction(e -> {

            registerMember();
        });

        buttons.getChildren().addAll(
            clearButton,
            registerButton
        );

        // =====================================================
        // ADD TO MAIN
        // =====================================================

        main.getChildren().addAll(
            memberTypeCard,
            personalCard,
            additionalCard,
            buttons
        );

        return main;
    }

    // =========================================================
    // CARD
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
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 15px;" +
            "-fx-effect: dropshadow(gaussian, rgba(60,30,80,0.06), 12, 0, 0, 4);"
        );

        return card;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private Label createSectionTitle(
            String text) {

        Label label =
            new Label(text);

        label.setStyle(
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        return label;
    }

    // =========================================================
    // FIELD BOX
    // =========================================================

    private VBox createFieldBox(
            String labelText,
            javafx.scene.Node field) {

        VBox box =
            new VBox(7);

        Label label =
            new Label(labelText);

        label.setStyle(
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
        );

        box.getChildren().addAll(
            label,
            field
        );

        GridPane.setHgrow(
            box,
            Priority.ALWAYS
        );

        return box;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private TextField createTextField(
            String prompt) {

        TextField field =
            new TextField();

        field.setPromptText(
            prompt
        );

        field.setPrefHeight(
            42
        );

        field.setMaxWidth(
            Double.MAX_VALUE
        );

        field.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 0 12px;"
        );

        return field;
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<String> combo) {

        combo.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;"
        );
    }

    // =========================================================
    // DATE PICKER STYLE
    // =========================================================

    private void styleDatePicker(
            DatePicker picker) {

        picker.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;"
        );
    }

    // =========================================================
    // CLEAR BUTTON
    // =========================================================

    private void styleClearButton(
            Button button) {

        String normal =
            "-fx-background-color: white;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-border-color: " + PURPLE + ";" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;";

        String hover =
            "-fx-background-color: #F1E8FF;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-border-color: " + PURPLE + ";" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;";

        button.setStyle(
            normal
        );

        button.setOnMouseEntered(
            e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
            e -> button.setStyle(normal)
        );
    }

    // =========================================================
    // REGISTER BUTTON
    // =========================================================

    private void styleRegisterButton(
            Button button) {

        String normal =
            "-fx-background-color: " + PURPLE + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 9px;" +
            "-fx-border-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;";

        String hover =
            "-fx-background-color: #5E2FA5;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 9px;" +
            "-fx-border-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;";

        button.setStyle(
            normal
        );

        button.setOnMouseEntered(
            e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
            e -> button.setStyle(normal)
        );
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        if (fullNameField != null)
            fullNameField.clear();

        if (emailField != null)
            emailField.clear();

        if (phoneField != null)
            phoneField.clear();

        if (registrationIdField != null)
            registrationIdField.clear();

        if (qualificationField != null)
            qualificationField.clear();

        if (addressField != null)
            addressField.clear();

        if (additionalInfoArea != null)
            additionalInfoArea.clear();

        if (memberTypeCombo != null)
            memberTypeCombo.getSelectionModel().clearSelection();

        if (genderCombo != null)
            genderCombo.getSelectionModel().clearSelection();

        if (dobPicker != null)
            dobPicker.setValue(null);
    }

    // =========================================================
    // REGISTER MEMBER
    // =========================================================

    private void registerMember() {

        String name =
            fullNameField.getText().trim();

        String email =
            emailField.getText().trim();

        String phone =
            phoneField.getText().trim();

        String registration =
            registrationIdField.getText().trim();

        String qualification =
            qualificationField.getText().trim();

        String address =
            addressField.getText().trim();

        String memberType =
            memberTypeCombo.getValue();

        String gender =
            genderCombo.getValue();

        // =====================================================
        // BASIC VALIDATION
        // =====================================================

        if (
            memberType == null ||
            memberType.isEmpty()
        ) {

            showValidationMessage(
                "Please select a member type."
            );

            return;
        }

        if (name.isEmpty()) {

            showValidationMessage(
                "Please enter the member's full name."
            );

            fullNameField.requestFocus();

            return;
        }

        if (email.isEmpty()) {

            showValidationMessage(
                "Please enter an email address."
            );

            emailField.requestFocus();

            return;
        }

        if (phone.isEmpty()) {

            showValidationMessage(
                "Please enter a phone number."
            );

            phoneField.requestFocus();

            return;
        }

        if (registration.isEmpty()) {

            showValidationMessage(
                "Please enter the registration / ID number."
            );

            registrationIdField.requestFocus();

            return;
        }

        if (qualification.isEmpty()) {

            showValidationMessage(
                "Please enter the qualification."
            );

            qualificationField.requestFocus();

            return;
        }

        if (gender == null) {

            showValidationMessage(
                "Please select gender."
            );

            return;
        }

        if (dobPicker.getValue() == null) {

            showValidationMessage(
                "Please select date of birth."
            );

            return;
        }

        if (address.isEmpty()) {

            showValidationMessage(
                "Please enter the address."
            );

            addressField.requestFocus();

            return;
        }

        // =====================================================
        // SUCCESS
        // =====================================================

        System.out.println(
            "=========================================="
        );

        System.out.println(
            "[ADMIN] NEW MEMBER REGISTERED"
        );

        System.out.println(
            "Member Type: " + memberType
        );

        System.out.println(
            "Name: " + name
        );

        System.out.println(
            "Email: " + email
        );

        System.out.println(
            "Phone: " + phone
        );

        System.out.println(
            "Registration ID: " + registration
        );

        System.out.println(
            "Qualification: " + qualification
        );

        System.out.println(
            "Gender: " + gender
        );

        System.out.println(
            "DOB: " + dobPicker.getValue()
        );

        System.out.println(
            "Address: " + address
        );

        System.out.println(
            "=========================================="
        );

        showSuccessMessage(
            "Member registered successfully."
        );

        clearForm();
    }

    // =========================================================
    // VALIDATION MESSAGE
    // =========================================================

    private void showValidationMessage(
            String message) {

        Label messageLabel =
            new Label(
                message
            );

        messageLabel.setStyle(
            "-fx-text-fill: #D93025;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );

        VBox box =
            new VBox(
                messageLabel
            );

        box.setPadding(
            new Insets(10)
        );

        box.setAlignment(
            Pos.CENTER
        );

        // Simple console output for now.
        System.out.println(
            "[ADMIN] " + message
        );
    }

    // =========================================================
    // SUCCESS MESSAGE
    // =========================================================

    private void showSuccessMessage(
            String message) {

        System.out.println(
            "[ADMIN] SUCCESS: " + message
        );
    }

    // =========================================================
    // GRID COLUMN HELPER
    // =========================================================

    private static class ColumnConstraintsHelper {

        static void setGrow(
                GridPane grid) {

            javafx.scene.layout.ColumnConstraints c1 =
                new javafx.scene.layout.ColumnConstraints();

            javafx.scene.layout.ColumnConstraints c2 =
                new javafx.scene.layout.ColumnConstraints();

            c1.setHgrow(
                Priority.ALWAYS
            );

            c2.setHgrow(
                Priority.ALWAYS
            );

            c1.setPercentWidth(
                50
            );

            c2.setPercentWidth(
                50
            );

            grid.getColumnConstraints()
                .addAll(
                    c1,
                    c2
                );
        }
    }
}