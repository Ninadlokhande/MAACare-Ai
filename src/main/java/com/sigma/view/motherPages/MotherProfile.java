package com.sigma.view.motherPages;

import com.sigma.model.MotherWlcModel;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;


// =========================================================
// MOTHER PROFILE
// =========================================================

public class MotherProfile {

    // =========================================================
    // MODEL
    // =========================================================

    private MotherWlcModel motherModel;


    // =========================================================
    // DASHBOARD IMAGE
    // =========================================================

    private final String DASHBOARD_IMAGE =
            "/assets/images/logo/ChatGPT Image Aug 15, 2026, 01_33_52 PM.png";


    // =========================================================
    // COLORS
    // =========================================================

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String LIGHT_PINK = "#FFEAF3";
    private final String BORDER = "#E7DCE8";
    private final String TEXT_GREY = "#77778D";


    // =========================================================
    // EDIT MODE
    // =========================================================

    private boolean editMode = false;

    private Button editButton;


    // =========================================================
    // BASIC INFORMATION FIELDS
    // =========================================================

    private TextField nameField;
    private DatePicker dobPicker;
    private TextField locationField;
    private TextField weightField;
    private TextField bloodGroupField;
    private TextField medicalConditionField;


    // =========================================================
    // PREGNANCY INFORMATION FIELDS
    // =========================================================

    private DatePicker lmpPicker;
    private DatePicker eddPicker;


    // =========================================================
    // ADDITIONAL INFORMATION FIELDS
    // =========================================================

    private TextField addressField;
    private TextField phoneField;
    private TextField allergiesField;

    private ComboBox<String> maritalStatusBox;


    // =========================================================
    // FAMILY INFORMATION FIELDS
    // =========================================================

    private TextField familyNameField;
    private TextField familyPhoneField;

    private ComboBox<String> familyRelationshipBox;


    // =========================================================
    // EMERGENCY INFORMATION FIELDS
    // =========================================================

    private TextField emergencyNameField;
    private TextField emergencyPhoneField;

    private ComboBox<String> emergencyRelationshipBox;


    // =========================================================
    // ALL EDITABLE FIELDS
    // =========================================================

    private List<Node> editableFields =
            new ArrayList<>();


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherProfile(MotherWlcModel motherModel) {

        this.motherModel = motherModel;
    }


    // =========================================================
    // CREATE PROFILE PAGE
    // =========================================================

    public VBox createProfilePage() {

        VBox page = new VBox();

        page.setSpacing(22);

        page.setPadding(
                new Insets(
                        10,
                        5,
                        30,
                        5
                )
        );


        // =====================================================
        // PAGE HEADER
        // =====================================================

        HBox pageHeader = new HBox();

        pageHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        pageHeader.setSpacing(15);


        VBox titleBox = new VBox();

        titleBox.setSpacing(4);


        Label title =
                new Label("My Profile 👩🏻‍🍼");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        Label subtitle =
                new Label(
                        "Manage your personal and pregnancy information 💗"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + TEXT_GREY + ";"
        );


        titleBox.getChildren().addAll(
                title,
                subtitle
        );


        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        // =====================================================
        // EDIT BUTTON
        // =====================================================

        editButton =
                createGradientButton(
                        "✏ Edit Profile"
                );


        editButton.setOnAction(
                e -> toggleEditMode()
        );


        pageHeader.getChildren().addAll(
                titleBox,
                editButton
        );


        // =====================================================
        // PROFILE HEADER CARD
        // =====================================================

        HBox profileCard =
                createProfileHeaderCard();


        // =====================================================
        // BASIC INFORMATION
        // =====================================================

        VBox basicCard =
                createBasicInformationCard();


        // =====================================================
        // PREGNANCY INFORMATION
        // =====================================================

        VBox pregnancyCard =
                createPregnancyInformationCard();


        // =====================================================
        // ADDITIONAL INFORMATION
        // =====================================================

        VBox additionalCard =
                createAdditionalInformationCard();


        // =====================================================
        // FAMILY INFORMATION
        // =====================================================

        VBox familyCard =
                createFamilyInformationCard();


        // =====================================================
        // EMERGENCY INFORMATION
        // =====================================================

        VBox emergencyCard =
                createEmergencyInformationCard();


        // =====================================================
        // BUTTONS
        // =====================================================

        HBox bottomButtons =
                new HBox();

        bottomButtons.setSpacing(15);

        bottomButtons.setAlignment(
                Pos.CENTER_RIGHT
        );


        Button saveButton =
                createGradientButton(
                        "💾 Save Changes"
                );


        Button logoutButton =
                new Button(
                        "🚪 Logout"
                );

        logoutButton.setPrefHeight(42);

        logoutButton.setPrefWidth(120);

        logoutButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: " + PINK + ";" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: " + PINK + ";" +
                "-fx-border-radius: 22;" +
                "-fx-cursor: hand;"
        );


        // =====================================================
        // SAVE ACTION
        // =====================================================

        saveButton.setOnAction(
                e -> saveProfile()
        );


        // =====================================================
        // LOGOUT ACTION
        // =====================================================

        logoutButton.setOnAction(
                e -> showMessage(
                        "Logout",
                        "Logout functionality will be connected later."
                )
        );


        bottomButtons.getChildren().addAll(
                logoutButton,
                saveButton
        );


        // =====================================================
        // ADD ALL
        // =====================================================

        page.getChildren().addAll(

                pageHeader,

                profileCard,

                basicCard,

                pregnancyCard,

                additionalCard,

                familyCard,

                emergencyCard,

                bottomButtons
        );


        // =====================================================
        // INITIAL STATE
        // =====================================================

        setEditableState(false);


        return page;
    }


    // =========================================================
    // PROFILE HEADER CARD
    // =========================================================

    private HBox createProfileHeaderCard() {

        HBox card =
                new HBox();

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setSpacing(25);

        card.setPadding(
                new Insets(25)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 20;"
        );


        // =====================================================
        // PROFILE PHOTO
        // =====================================================

        VBox photoBox =
                new VBox();

        photoBox.setAlignment(
                Pos.CENTER
        );

        photoBox.setSpacing(10);


        ImageView profileImage =
                new ImageView();


        // =====================================================
        // BIGGER PROFILE IMAGE
        // =====================================================

        profileImage.setFitWidth(140);

        profileImage.setFitHeight(140);

        profileImage.setPreserveRatio(false);


        Label profileCircle =
                new Label(
                        getInitial()
                );

        profileCircle.setAlignment(
                Pos.CENTER
        );

        profileCircle.setPrefSize(
                140,
                140
        );

        profileCircle.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " + PINK + ";" +
                "-fx-font-size: 48px;" +
                "-fx-font-weight: bold;"
        );


        // =====================================================
        // CHANGE PHOTO BUTTON
        // =====================================================

        Button changePhoto =
                new Button(
                        "📷 Change Photo"
                );

        changePhoto.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + PINK + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );


        changePhoto.setOnAction(
                e -> chooseProfilePhoto(
                        profileImage,
                        profileCircle
                )
        );


        photoBox.getChildren().addAll(
                profileCircle,
                changePhoto
        );


        // =====================================================
        // PROFILE DETAILS
        // =====================================================

        VBox details =
                new VBox();

        details.setSpacing(7);


        Label name =
                new Label(
                        safeValue(
                                motherModel.getName(),
                                "Mother"
                        )
                );

        name.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        Label role =
                new Label(
                        "🤰 Mother / Patient"
                );

        role.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + TEXT_GREY + ";"
        );


        Label location =
                new Label(
                        "📍 " +
                        safeValue(
                                motherModel.getLocation(),
                                "Location not provided"
                        )
                );

        location.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #666680;"
        );


        Label blood =
                new Label(
                        "🩸 Blood Group: " +
                        safeValue(
                                motherModel.getBloodGroup(),
                                "Not provided"
                        )
                );

        blood.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #666680;"
        );


        details.getChildren().addAll(
                name,
                role,
                location,
                blood
        );


        HBox.setHgrow(
                details,
                Priority.ALWAYS
        );


        // =====================================================
        // DASHBOARD IMAGE
        // =====================================================

        VBox dashboardImageBox =
                new VBox();

        dashboardImageBox.setAlignment(
                Pos.CENTER
        );

        dashboardImageBox.setPrefWidth(400);


        ImageView dashboardImage =
                new ImageView();


        try {

            if (getClass()
                    .getResource(DASHBOARD_IMAGE) != null) {

                Image image =
                        new Image(
                                getClass()
                                        .getResource(
                                                DASHBOARD_IMAGE
                                        )
                                        .toExternalForm()
                        );

                dashboardImage.setImage(
                        image
                );
            }

        } catch (Exception ex) {

            System.out.println(
                    "Dashboard image not found: "
                            + DASHBOARD_IMAGE
            );
        }


        dashboardImage.setFitWidth(300);

        dashboardImage.setFitHeight(200);

        dashboardImage.setPreserveRatio(true);


        dashboardImageBox.getChildren().add(
                dashboardImage
        );


        // =====================================================
        // ADD TO CARD
        // =====================================================

        card.getChildren().addAll(
                photoBox,
                details,
                dashboardImageBox
        );


        return card;
    }


    // =========================================================
    // BASIC INFORMATION CARD
    // =========================================================

    private VBox createBasicInformationCard() {

        VBox card =
                createCard();


        HBox heading =
                createHeading(
                        "👤  Basic Information",
                        FontAwesomeIcon.USER
                );


        GridPane grid =
                createGrid();


        // =====================================================
        // FULL NAME
        // =====================================================

        nameField =
                createTextField(
                        "Full Name"
                );

        nameField.setText(
                safeValue(
                        motherModel.getName(),
                        ""
                )
        );


        // =====================================================
        // DATE OF BIRTH
        // =====================================================

        dobPicker =
                new DatePicker(
                        motherModel.getDateOfBirth()
                );

        styleDatePicker(
                dobPicker
        );


        // =====================================================
        // LOCATION
        // =====================================================

        locationField =
                createTextField(
                        "City / Location"
                );

        locationField.setText(
                safeValue(
                        motherModel.getLocation(),
                        ""
                )
        );


        // =====================================================
        // WEIGHT
        // =====================================================

        weightField =
                createTextField(
                        "Weight in kg"
                );

                weightField.setText(
                    String.valueOf(
                            motherModel.getWeight()
                    )
            );
       


        // =====================================================
        // BLOOD GROUP
        // =====================================================

        bloodGroupField =
                createTextField(
                        "Blood Group"
                );

        bloodGroupField.setText(
                safeValue(
                        motherModel.getBloodGroup(),
                        ""
                )
        );


        // =====================================================
        // MEDICAL CONDITION
        // =====================================================

        medicalConditionField =
                createTextField(
                        "Medical Condition"
                );

        medicalConditionField.setText(
                safeValue(
                        motherModel.getMedicalCondition(),
                        ""
                )
        );


        // =====================================================
        // ADD TO GRID
        // =====================================================

        grid.add(
                createFieldContainer(
                        "Full Name",
                        nameField
                ),
                0,
                0
        );


        grid.add(
                createFieldContainer(
                        "Date of Birth 🎂",
                        dobPicker
                ),
                1,
                0
        );


        grid.add(
                createFieldContainer(
                        "City / Location 📍",
                        locationField
                ),
                0,
                1
        );


        grid.add(
                createFieldContainer(
                        "Weight ⚖️",
                        weightField
                ),
                1,
                1
        );


        grid.add(
                createFieldContainer(
                        "Blood Group 🩸",
                        bloodGroupField
                ),
                0,
                2
        );


        grid.add(
                createFieldContainer(
                        "Medical Condition 🏥",
                        medicalConditionField
                ),
                1,
                2
        );


        // =====================================================
        // REGISTER FIELDS
        // =====================================================

        editableFields.add(
                nameField
        );

        editableFields.add(
                dobPicker
        );

        editableFields.add(
                locationField
        );

        editableFields.add(
                weightField
        );

        editableFields.add(
                bloodGroupField
        );

        editableFields.add(
                medicalConditionField
        );


        card.getChildren().addAll(
                heading,
                new Separator(),
                grid
        );


        return card;
    }


    // =========================================================
    // PREGNANCY INFORMATION CARD
    // =========================================================

    private VBox createPregnancyInformationCard() {

        VBox card =
                createCard();


        HBox heading =
                createHeading(
                        "💗  Pregnancy Information",
                        FontAwesomeIcon.HEART
                );


        GridPane grid =
                createGrid();


        // =====================================================
        // LMP
        // =====================================================

        lmpPicker =
                new DatePicker(
                        motherModel.getLmpDate()
                );

        styleDatePicker(
                lmpPicker
        );


        // =====================================================
        // EDD
        // =====================================================

        eddPicker =
                new DatePicker(
                        motherModel.getEddDate()
                );

        styleDatePicker(
                eddPicker
        );


        grid.add(
                createFieldContainer(
                        "Last Menstrual Period 🩸",
                        lmpPicker
                ),
                0,
                0
        );


        grid.add(
                createFieldContainer(
                        "Expected Delivery Date 👶",
                        eddPicker
                ),
                1,
                0
        );


        // =====================================================
        // REGISTER
        // =====================================================

        editableFields.add(
                lmpPicker
        );

        editableFields.add(
                eddPicker
        );


        card.getChildren().addAll(
                heading,
                new Separator(),
                grid
        );


        return card;
    }


    // =========================================================
    // ADDITIONAL INFORMATION CARD
    // =========================================================

    private VBox createAdditionalInformationCard() {

        VBox card =
                createCard();


        HBox heading =
                createHeading(
                        "🏠  Additional Information",
                        FontAwesomeIcon.HOME
                );


        GridPane grid =
                createGrid();


        // =====================================================
        // ADDRESS
        // =====================================================

        addressField =
                createTextField(
                        "Full Address"
                );

        addressField.setText(
                safeValue(
                        motherModel.getAddress(),
                        ""
                )
        );


        // =====================================================
        // PHONE
        // =====================================================

        phoneField =
                createTextField(
                        "Phone Number"
                );

        phoneField.setText(
                safeValue(
                        motherModel.getPhone(),
                        ""
                )
        );


        // =====================================================
        // ALLERGIES
        // =====================================================

        allergiesField =
                createTextField(
                        "Allergies"
                );

        allergiesField.setText(
                safeValue(
                        motherModel.getAllergies(),
                        ""
                )
        );


        // =====================================================
        // MARITAL STATUS
        // =====================================================

        maritalStatusBox =
                new ComboBox<>();

        maritalStatusBox.getItems().addAll(
                "Single",
                "Married",
                "Other"
        );

        maritalStatusBox.setPromptText(
                "Select marital status"
        );

        styleComboBox(
                maritalStatusBox
        );


        if (motherModel.getMaritalStatus() != null) {

            maritalStatusBox.setValue(
                    motherModel.getMaritalStatus()
            );
        }


        // =====================================================
        // GRID
        // =====================================================

        grid.add(
                createFieldContainer(
                        "Address 🏠",
                        addressField
                ),
                0,
                0
        );


        grid.add(
                createFieldContainer(
                        "Phone Number 📱",
                        phoneField
                ),
                1,
                0
        );


        grid.add(
                createFieldContainer(
                        "Allergies ⚠️",
                        allergiesField
                ),
                0,
                1
        );


        grid.add(
                createFieldContainer(
                        "Marital Status 💍",
                        maritalStatusBox
                ),
                1,
                1
        );


        // =====================================================
        // REGISTER
        // =====================================================

        editableFields.add(
                addressField
        );

        editableFields.add(
                phoneField
        );

        editableFields.add(
                allergiesField
        );

        editableFields.add(
                maritalStatusBox
        );


        card.getChildren().addAll(
                heading,
                new Separator(),
                grid
        );


        return card;
    }


    // =========================================================
    // FAMILY INFORMATION CARD
    // =========================================================

    private VBox createFamilyInformationCard() {

        VBox card =
                createCard();


        HBox heading =
                createHeading(
                        "👨‍👩‍👧  Family Information",
                        FontAwesomeIcon.USERS
                );


        GridPane grid =
                createGrid();


        // =====================================================
        // FAMILY MEMBER NAME
        // =====================================================

        familyNameField =
                createTextField(
                        "Family Member Name"
                );

        familyNameField.setText(
                safeValue(
                        motherModel.getFamilyMemberName(),
                        ""
                )
        );


        // =====================================================
        // RELATIONSHIP
        // =====================================================

        familyRelationshipBox =
                new ComboBox<>();

        familyRelationshipBox.getItems().addAll(
                "Husband",
                "Mother",
                "Father",
                "Guardian",
                "Other"
        );

        familyRelationshipBox.setPromptText(
                "Relationship"
        );

        styleComboBox(
                familyRelationshipBox
        );


        if (motherModel.getFamilyRelationship() != null) {

            familyRelationshipBox.setValue(
                    motherModel.getFamilyRelationship()
            );
        }


        // =====================================================
        // FAMILY PHONE
        // =====================================================

        familyPhoneField =
                createTextField(
                        "Family Member Phone"
                );

        familyPhoneField.setText(
                safeValue(
                        motherModel.getFamilyPhone(),
                        ""
                )
        );


        // =====================================================
        // GRID
        // =====================================================

        grid.add(
                createFieldContainer(
                        "Family Member Name 👤",
                        familyNameField
                ),
                0,
                0
        );


        grid.add(
                createFieldContainer(
                        "Relationship 🤝",
                        familyRelationshipBox
                ),
                1,
                0
        );


        grid.add(
                createFieldContainer(
                        "Contact Number 📱",
                        familyPhoneField
                ),
                0,
                1
        );


        // =====================================================
        // REGISTER
        // =====================================================

        editableFields.add(
                familyNameField
        );

        editableFields.add(
                familyRelationshipBox
        );

        editableFields.add(
                familyPhoneField
        );


        card.getChildren().addAll(
                heading,
                new Separator(),
                grid
        );


        return card;
    }


    // =========================================================
    // EMERGENCY INFORMATION CARD
    // =========================================================

    private VBox createEmergencyInformationCard() {

        VBox card =
                createCard();


        HBox heading =
                createHeading(
                        "🚨  Emergency Contact",
                        FontAwesomeIcon.AMBULANCE
                );


        GridPane grid =
                createGrid();


        // =====================================================
        // EMERGENCY NAME
        // =====================================================

        emergencyNameField =
                createTextField(
                        "Emergency Contact Name"
                );

        emergencyNameField.setText(
                safeValue(
                        motherModel.getEmergencyName(),
                        ""
                )
        );


        // =====================================================
        // EMERGENCY PHONE
        // =====================================================

        emergencyPhoneField =
                createTextField(
                        "Emergency Contact Number"
                );

        emergencyPhoneField.setText(
                safeValue(
                        motherModel.getEmergencyPhone(),
                        ""
                )
        );


        // =====================================================
        // RELATIONSHIP
        // =====================================================

        emergencyRelationshipBox =
                new ComboBox<>();

        emergencyRelationshipBox.getItems().addAll(
                "Husband",
                "Mother",
                "Father",
                "Guardian",
                "Other"
        );

        emergencyRelationshipBox.setPromptText(
                "Relationship"
        );

        styleComboBox(
                emergencyRelationshipBox
        );


        if (motherModel.getEmergencyRelationship() != null) {

            emergencyRelationshipBox.setValue(
                    motherModel.getEmergencyRelationship()
            );
        }


        // =====================================================
        // GRID
        // =====================================================

        grid.add(
                createFieldContainer(
                        "Contact Name 👤",
                        emergencyNameField
                ),
                0,
                0
        );


        grid.add(
                createFieldContainer(
                        "Contact Number 📱",
                        emergencyPhoneField
                ),
                1,
                0
        );


        grid.add(
                createFieldContainer(
                        "Relationship 🤝",
                        emergencyRelationshipBox
                ),
                0,
                1
        );


        // =====================================================
        // REGISTER
        // =====================================================

        editableFields.add(
                emergencyNameField
        );

        editableFields.add(
                emergencyPhoneField
        );

        editableFields.add(
                emergencyRelationshipBox
        );


        card.getChildren().addAll(
                heading,
                new Separator(),
                grid
        );


        return card;
    }


    // =========================================================
    // TOGGLE EDIT MODE
    // =========================================================

    private void toggleEditMode() {

        editMode =
                !editMode;


        setEditableState(
                editMode
        );


        if (editMode) {

            editButton.setText(
                    "✏ Editing..."
            );

            editButton.setStyle(
                    "-fx-background-color: linear-gradient(" +
                    "to right, #9B4DCC, #F54B87);" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 22;" +
                    "-fx-cursor: hand;"
            );

        } else {

            editButton.setText(
                    "✏ Edit Profile"
            );

            editButton.setStyle(
                    "-fx-background-color: linear-gradient(" +
                    "to right, #F54B87, #9B4DCC);" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 22;" +
                    "-fx-cursor: hand;"
            );
        }
    }


    // =========================================================
    // ENABLE / DISABLE EDITABLE FIELDS
    // =========================================================

    private void setEditableState(
            boolean enabled) {

        for (Node node :
                editableFields) {

            node.setDisable(
                    !enabled
            );
        }
    }


    // =========================================================
    // SAVE PROFILE
    // =========================================================

    private void saveProfile() {

        // =====================================================
        // CHECK EDIT MODE
        // =====================================================

        if (!editMode) {

            showMessage(
                    "Edit Profile",
                    "Please click Edit Profile first."
            );

            return;
        }


        // =====================================================
        // BASIC INFORMATION
        // =====================================================

        motherModel.setName(
                nameField.getText()
        );


        motherModel.setDateOfBirth(
                dobPicker.getValue()
        );


        motherModel.setLocation(
                locationField.getText()
        );


        // =====================================================
        // WEIGHT
        // =====================================================

        if (!weightField
                .getText()
                .trim()
                .isEmpty()) {

            try {

                motherModel.setWeight(
                        Double.parseDouble(
                                weightField
                                        .getText()
                                        .trim()
                        )
                );

            } catch (
                    NumberFormatException ex) {

                showMessage(
                        "Invalid Weight",
                        "Please enter a valid weight."
                );

                return;
            }

        }


        // =====================================================
        // BLOOD GROUP
        // =====================================================

        motherModel.setBloodGroup(
                bloodGroupField.getText()
        );


        // =====================================================
        // MEDICAL CONDITION
        // =====================================================

        motherModel.setMedicalCondition(
                medicalConditionField.getText()
        );


        // =====================================================
        // PREGNANCY INFORMATION
        // =====================================================

        motherModel.setLmpDate(
                lmpPicker.getValue()
        );


        motherModel.setEddDate(
                eddPicker.getValue()
        );


        // =====================================================
        // ADDITIONAL INFORMATION
        // =====================================================

        motherModel.setAddress(
                addressField.getText()
        );


        motherModel.setPhone(
                phoneField.getText()
        );


        motherModel.setAllergies(
                allergiesField.getText()
        );


        motherModel.setMaritalStatus(
                maritalStatusBox.getValue()
        );


        // =====================================================
        // FAMILY INFORMATION
        // =====================================================

        motherModel.setFamilyMemberName(
                familyNameField.getText()
        );


        motherModel.setFamilyRelationship(
                familyRelationshipBox.getValue()
        );


        motherModel.setFamilyPhone(
                familyPhoneField.getText()
        );


        // =====================================================
        // EMERGENCY INFORMATION
        // =====================================================

        motherModel.setEmergencyName(
                emergencyNameField.getText()
        );


        motherModel.setEmergencyPhone(
                emergencyPhoneField.getText()
        );


        motherModel.setEmergencyRelationship(
                emergencyRelationshipBox.getValue()
        );


        // =====================================================
        // EXIT EDIT MODE
        // =====================================================

        editMode = false;


        setEditableState(
                false
        );


        editButton.setText(
                "✏ Edit Profile"
        );


        editButton.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right, #F54B87, #9B4DCC);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 22;" +
                "-fx-cursor: hand;"
        );


        showMessage(
                "Profile Saved 💗",
                "Your profile information has been updated successfully."
        );
    }


    // =========================================================
    // PROFILE PHOTO
    // =========================================================

    private void chooseProfilePhoto(
            ImageView profileImage,
            Label profileCircle) {

        FileChooser fileChooser =
                new FileChooser();


        fileChooser.setTitle(
                "Choose Profile Photo"
        );


        fileChooser
                .getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Image Files",
                                "*.png",
                                "*.jpg",
                                "*.jpeg"
                        )
                );


        File selectedFile =
                fileChooser.showOpenDialog(
                        null
                );


        if (selectedFile != null) {

            try {

                Image image =
                        new Image(
                                selectedFile
                                        .toURI()
                                        .toString()
                        );


                profileImage.setImage(
                        image
                );


                profileImage.setFitWidth(
                        140
                );


                profileImage.setFitHeight(
                        140
                );


                profileImage.setPreserveRatio(
                        false
                );


                profileCircle.setGraphic(
                        profileImage
                );


                profileCircle.setText(
                        ""
                );


            } catch (Exception ex) {

                showMessage(
                        "Image Error",
                        "Unable to load selected image."
                );
            }
        }
    }


    // =========================================================
    // GENERIC CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox();

        card.setSpacing(15);

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;"
        );


        return card;
    }


    // =========================================================
    // CARD HEADING
    // =========================================================

    private HBox createHeading(
            String text,
            FontAwesomeIcon iconType) {

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        heading.setSpacing(10);


        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                        iconType
                );

        icon.setSize(
                "19"
        );

        icon.setFill(
                Color.web(PURPLE)
        );


        Label title =
                new Label(
                        text
                );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        heading.getChildren().addAll(
                icon,
                title
        );


        return heading;
    }


    // =========================================================
    // FIELD CONTAINER
    // =========================================================

    private VBox createFieldContainer(
            String labelText,
            Node field) {

        VBox box =
                new VBox();

        box.setSpacing(
                6
        );


        Label label =
                new Label(
                        labelText
                );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT_GREY + ";"
        );


        box.getChildren().addAll(
                label,
                field
        );


        return box;
    }


    // =========================================================
    // GRID
    // =========================================================

    private GridPane createGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(
                18
        );

        grid.setVgap(
                15
        );


        ColumnConstraints col1 =
                new ColumnConstraints();

        ColumnConstraints col2 =
                new ColumnConstraints();


        col1.setPercentWidth(
                50
        );

        col2.setPercentWidth(
                50
        );


        grid.getColumnConstraints().addAll(
                col1,
                col2
        );


        return grid;
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
                "-fx-background-color: #FFF9FA;" +
                "-fx-border-color: #E7B8C5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 12;"
        );


        return field;
    }


    // =========================================================
    // DATE PICKER
    // =========================================================

    private void styleDatePicker(
            DatePicker picker) {

        picker.setPrefHeight(
                42
        );


        picker.setMaxWidth(
                Double.MAX_VALUE
        );


        picker.setStyle(
                "-fx-background-color: #FFF9FA;" +
                "-fx-border-color: #E7B8C5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );
    }


    // =========================================================
    // COMBO BOX
    // =========================================================

    private void styleComboBox(
            ComboBox<String> box) {

        box.setPrefHeight(
                42
        );


        box.setMaxWidth(
                Double.MAX_VALUE
        );


        box.setStyle(
                "-fx-background-color: #FFF9FA;" +
                "-fx-border-color: #E7B8C5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );
    }


    // =========================================================
    // GRADIENT BUTTON
    // =========================================================

    private Button createGradientButton(
            String text) {

        Button button =
                new Button(
                        text
                );


        button.setPrefHeight(
                42
        );


        button.setPrefWidth(
                150
        );


        button.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right, #F54B87, #9B4DCC);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 22;" +
                "-fx-cursor: hand;"
        );


        return button;
    }


    // =========================================================
    // PROFILE INITIAL
    // =========================================================

    private String getInitial() {

        if (motherModel == null
                || motherModel.getName() == null
                || motherModel.getName().isEmpty()) {

            return "M";
        }


        return motherModel
                .getName()
                .substring(0, 1)
                .toUpperCase();
    }


    // =========================================================
    // FORMAT DATE
    // =========================================================

    private String formatDate(
            LocalDate date) {

        if (date == null) {

            return "Not provided";
        }


        return date.format(
                DateTimeFormatter.ofPattern(
                        "dd MMM yyyy"
                )
        );
    }


    // =========================================================
    // SAFE VALUE
    // =========================================================

    private String safeValue(
            String value,
            String defaultValue) {

        if (value == null
                || value.trim().isEmpty()) {

            return defaultValue;
        }


        return value;
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                "MaaCare AI"
        );


        alert.setHeaderText(
                title
        );


        alert.setContentText(
                message
        );


        alert.showAndWait();
    }
}