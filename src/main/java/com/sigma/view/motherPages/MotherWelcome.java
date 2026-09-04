package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.Period;

import com.sigma.controller.MotherWlcController;
import com.sigma.model.MotherWlcModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MotherWelcome {

    public static Stage motherWelcomeStage;

    private Scene motherWelcomeScene;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherWelcome() {
        createMotherWelcomePage();
    }

    // =========================================================
    // CREATE MOTHER WELCOME PAGE
    // =========================================================

    private void createMotherWelcomePage() {

        // =========================================================
        // LEFT PANEL
        // =========================================================

        VBox leftPanel = new VBox(20);

        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPadding(new Insets(40));
        leftPanel.setPrefWidth(500);
        leftPanel.setMinWidth(450);

        Image img = new Image(
                getClass().getResource(
                        "/assets/images/logo/logo.png"
                ).toExternalForm()
        );

        ImageView logo = new ImageView(img);

        logo.setFitHeight(200);
        logo.setPreserveRatio(true);

        Label appName = new Label("MaaCare AI");

        appName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        appName.setTextFill(
                Color.web("#E84A87")
        );

        Label tagline = new Label(
                "Smart Care For Every Mother And Baby."
        );

        tagline.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        tagline.setTextFill(
                Color.web("#6B6B6B")
        );

        tagline.setAlignment(Pos.CENTER);

        tagline.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        Label welcomeText =
                new Label("Welcome, Mother! 💗");

        welcomeText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        welcomeText.setTextFill(
                Color.web("#333333")
        );

        leftPanel.getChildren().addAll(
                logo,
                appName,
                tagline,
                welcomeText
        );

        // =========================================================
        // FORM CARD
        // =========================================================

        VBox formCard = new VBox(25);

        formCard.setPadding(
                new Insets(30)
        );

        formCard.setPrefWidth(650);
        formCard.setMaxWidth(650);

        Label title =
                new Label("Let's get to know you");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        title.setTextFill(
                Color.web("#333333")
        );

        Label subtitle =
                new Label(
                        "Enter a few basic details to personalize your MaaCare journey."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#777777")
        );

        // =========================================================
        // BASIC INFORMATION
        // =========================================================

        Label basicTitle =
                new Label("Basic Information");

        basicTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        basicTitle.setTextFill(
                Color.web("#B83B63")
        );

        TextField nameField = new TextField();

        nameField.setPromptText("Full Name");
        nameField.setPrefWidth(280);

        styleTextField(nameField);

        DatePicker dobDatePicker = new DatePicker();

        dobDatePicker.setPromptText("Date of Birth");
        dobDatePicker.setPrefWidth(280);

        styleDatePicker(dobDatePicker);

        TextField locationField = new TextField();

        locationField.setPromptText("City / Location");
        locationField.setPrefWidth(280);

        styleTextField(locationField);

        TextField weightField = new TextField();

        weightField.setPromptText("Weight (kg)");
        weightField.setPrefWidth(280);

        styleTextField(weightField);

        ComboBox<String> bloodGroupBox =
                new ComboBox<>();

        bloodGroupBox.getItems().addAll(
                "A+",
                "A-",
                "B+",
                "B-",
                "AB+",
                "AB-",
                "O+",
                "O-"
        );

        bloodGroupBox.setPromptText("Blood Group");
        bloodGroupBox.setPrefWidth(280);

        styleComboBox(bloodGroupBox);

        ComboBox<String> medicalConditionBox =
                new ComboBox<>();

        medicalConditionBox.getItems().addAll(
                "None",
                "Diabetes",
                "High Blood Pressure",
                "Thyroid",
                "Asthma",
                "Other"
        );

        medicalConditionBox.setPromptText(
                "Medical Condition"
        );

        medicalConditionBox.setPrefWidth(280);

        styleComboBox(medicalConditionBox);

        // =========================================================
        // PREGNANCY INFORMATION
        // =========================================================

        Label pregnancyTitle =
                new Label("Pregnancy Information");

        pregnancyTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        pregnancyTitle.setTextFill(
                Color.web("#B83B63")
        );

        Label lmpLabel =
                new Label(
                        "Last Menstrual Period (LMP)"
                );

        lmpLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        lmpLabel.setTextFill(
                Color.web("#555555")
        );

        DatePicker lmpDatePicker =
                new DatePicker();

        lmpDatePicker.setPromptText(
                "Select LMP date"
        );

        lmpDatePicker.setPrefWidth(580);

        styleDatePicker(lmpDatePicker);

        Label eddLabel =
                new Label(
                        "Expected Delivery Date"
                );

        eddLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        eddLabel.setTextFill(
                Color.web("#555555")
        );

        Label eddValue =
                new Label(
                        "Will be calculated automatically"
                );

        eddValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        eddValue.setTextFill(
                Color.web("#B83B63")
        );

        // =========================================================
        // EDD CALCULATION
        // =========================================================

        lmpDatePicker.setOnAction(event -> {

            LocalDate lmpDate =
                    lmpDatePicker.getValue();

            if (lmpDate != null) {

                LocalDate today =
                        LocalDate.now();

                if (!lmpDate.isAfter(today)) {

                    LocalDate eddDate =
                            lmpDate.plusDays(280);

                    eddValue.setText(
                            eddDate.getDayOfMonth()
                                    + " "
                                    + eddDate.getMonth()
                                    + " "
                                    + eddDate.getYear()
                    );

                } else {

                    eddValue.setText(
                            "Invalid LMP date"
                    );
                }
            }
        });

        // =========================================================
        // CONTINUE BUTTON
        // =========================================================

        Button continueButton =
                new Button("Continue →");

        continueButton.setPrefWidth(200);
        continueButton.setPrefHeight(45);

        continueButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #F54B87, #9B4DCC);"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 15px;"
                        + "-fx-padding: 12px 35px;"
                        + "-fx-background-radius: 30px;"
                        + "-fx-border-radius: 30px;"
                        + "-fx-cursor: hand;"
        );

        // =========================================================
        // CONTINUE BUTTON ACTION
        // =========================================================

        continueButton.setOnAction(event -> {

            try {

                String name =
                        nameField.getText().trim();

                LocalDate dob =
                        dobDatePicker.getValue();

                String location =
                        locationField.getText().trim();

                String weight =
                        weightField.getText().trim();

                String bloodGroup =
                        bloodGroupBox.getValue();

                String medicalCondition =
                        medicalConditionBox.getValue();

                LocalDate lmp =
                        lmpDatePicker.getValue();

                LocalDate today =
                        LocalDate.now();

                // =================================================
                // 1. EMPTY FIELD VALIDATION
                // =================================================

                if (name.isEmpty()
                        || dob == null
                        || location.isEmpty()
                        || weight.isEmpty()
                        || bloodGroup == null
                        || medicalCondition == null
                        || lmp == null) {

                    showError(
                            "Please fill all required details."
                    );

                    return;
                }

                // =================================================
                // 2. NAME VALIDATION
                // =================================================

                if (!name.matches("[a-zA-Z ]+")) {

                    showError(
                            "Name should contain only letters and spaces."
                    );

                    return;
                }

                if (name.length() < 2) {

                    showError(
                            "Please enter a valid full name."
                    );

                    return;
                }

                // =================================================
                // 3. LOCATION VALIDATION
                // =================================================

                if (!location.matches("[a-zA-Z ]+")) {

                    showError(
                            "City / Location should contain only letters and spaces."
                    );

                    return;
                }

                // =================================================
                // 4. DOB VALIDATION
                // =================================================

                if (dob.isAfter(today)) {

                    showError(
                            "Date of Birth cannot be a future date."
                    );

                    return;
                }

                int age =
                        Period.between(
                                dob,
                                today
                        ).getYears();

                if (age < 18) {

                    showError(
                            "Please enter a valid Date of Birth."
                                    + "\nAge must be at least 18 years."
                    );

                    return;
                }

                if (age > 100) {

                    showError(
                            "Please enter a valid Date of Birth."
                    );

                    return;
                }

                // =================================================
                // 5. WEIGHT VALIDATION
                // =================================================

                double weightValue;

                try {

                    if (!weight.matches(
                            "\\d+(\\.\\d+)?"
                    )) {

                        showError(
                                "Weight must contain numbers only."
                        );

                        return;
                    }

                    weightValue =
                            Double.parseDouble(weight);

                } catch (NumberFormatException e) {

                    showError(
                            "Please enter a valid weight."
                    );

                    return;
                }

                if (weightValue < 20
                        || weightValue > 200) {

                    showError(
                            "Please enter a realistic weight between 20 kg and 200 kg."
                    );

                    return;
                }

                // =================================================
                // 6. LMP FUTURE DATE
                // =================================================

                if (lmp.isAfter(today)) {

                    showError(
                            "LMP date cannot be a future date."
                    );

                    return;
                }

                // =================================================
                // 7. LMP AFTER DOB
                // =================================================

                if (!lmp.isAfter(dob)) {

                    showError(
                            "LMP date must be after the Date of Birth."
                    );

                    return;
                }

                // =================================================
                // 8. LMP TOO OLD
                // =================================================

                LocalDate earliestLmp =
                        today.minusDays(294);

                if (lmp.isBefore(earliestLmp)) {

                    showError(
                            "The LMP date is too old for the current pregnancy."
                                    + "\nPlease enter the correct LMP date."
                    );

                    return;
                }

                // =================================================
                // 9. CALCULATE EDD
                // =================================================

                LocalDate edd =
                        lmp.plusDays(280);

                // =================================================
                // 10. CREATE MOTHER MODEL
                // =================================================

                MotherWlcModel motherModel =
                        new MotherWlcModel(
                                null,
                                name,
                                dob,
                                location,
                                weightValue,
                                bloodGroup,
                                medicalCondition,
                                lmp,
                                edd
                        );

                // =================================================
                // 11. SAVE TO FIREBASE
                // =================================================

                MotherWlcController motherController =
                        new MotherWlcController();

                boolean saved =
                        motherController.saveMotherData(
                                motherModel
                        );

                // =================================================
                // 12. SAVE FAILED
                // =================================================

                if (!saved) {

                    showError(
                            "Mother data could not be saved to Firebase."
                    );

                    return;
                }

                // =================================================
                // 13. SUCCESS CONSOLE
                // =================================================

                System.out.println(
                        "======================================"
                );

                System.out.println(
                        "Mother data saved successfully!"
                );

                System.out.println(
                        "Mother ID: "
                                + motherModel.getMotherId()
                );

                System.out.println(
                        "Mother Name: "
                                + motherModel.getName()
                );

                System.out.println(
                        "Date of Birth: "
                                + motherModel.getDateOfBirth()
                );

                System.out.println(
                        "Location: "
                                + motherModel.getLocation()
                );

                System.out.println(
                        "Weight: "
                                + motherModel.getWeight()
                                + " kg"
                );

                System.out.println(
                        "Blood Group: "
                                + motherModel.getBloodGroup()
                );

                System.out.println(
                        "Medical Condition: "
                                + motherModel.getMedicalCondition()
                );

                System.out.println(
                        "LMP: "
                                + motherModel.getLmpDate()
                );

                System.out.println(
                        "EDD: "
                                + motherModel.getEddDate()
                );

                System.out.println(
                        "======================================"
                );

                // =================================================
                // 14. GO TO MOTHER DASHBOARD
                // =================================================

                MotherDashBoard dashboard =
                        new MotherDashBoard(
                                motherModel
                        );

                Scene dashboardScene =
                        dashboard.getmotherDashboardScene();

                Stage currentStage =
                        (Stage) continueButton
                                .getScene()
                                .getWindow();

                currentStage.setScene(
                        dashboardScene
                );

                currentStage.setMaximized(true);

            } catch (Exception e) {

                e.printStackTrace();

                showError(
                        "Something went wrong while processing mother data."
                );
            }
        });

        // =========================================================
        // FORM ROWS
        // =========================================================

        HBox row1 =
                new HBox(
                        25,
                        nameField,
                        dobDatePicker
                );

        row1.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox row2 =
                new HBox(
                        25,
                        locationField,
                        weightField
                );

        row2.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox row3 =
                new HBox(
                        25,
                        bloodGroupBox,
                        medicalConditionBox
                );

        row3.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // FORM CARD CONTENT
        // =========================================================

        formCard.getChildren().addAll(
                title,
                subtitle,
                basicTitle,
                row1,
                row2,
                row3,
                pregnancyTitle,
                lmpLabel,
                lmpDatePicker,
                eddLabel,
                eddValue,
                continueButton
        );

        formCard.setStyle(
                "-fx-background-color: rgba(255,255,255,0.95);"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-width: 1;"
        );

        // =========================================================
        // MAIN PANE
        // =========================================================

        HBox mainPane =
                new HBox(80);

        mainPane.setAlignment(
                Pos.CENTER
        );

        mainPane.setPadding(
                new Insets(
                        30,
                        50,
                        30,
                        50
                )
        );

        mainPane.getChildren().addAll(
                leftPanel,
                formCard
        );

        mainPane.setStyle(
                "-fx-background-color: linear-gradient("
                        + "to bottom right, "
                        + "#FFFFFF 0%, "
                        + "#FFF4F8 50%, "
                        + "#F0E7FF 100%"
                        + ");"
        );

        // =========================================================
        // SCROLL PANE
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainPane
                );

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        // =========================================================
        // SCENE
        // =========================================================

        motherWelcomeScene =
                new Scene(
                        scrollPane,
                        1200,
                        750
                );
    }

    // =============================================================
    // GET MOTHER WELCOME SCENE
    // =============================================================

    public Scene getMotherWelcomeScene() {
        return motherWelcomeScene;
    }

    // =============================================================
    // TEXT FIELD STYLE
    // =============================================================

    private void styleTextField(
            TextField field) {

        field.setPrefHeight(42);

        field.setStyle(
                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-padding: 8 12;"
        );
    }

    // =============================================================
    // COMBO BOX STYLE
    // =============================================================

    private void styleComboBox(
            ComboBox<String> box) {

        box.setPrefHeight(42);

        box.setStyle(
                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );
    }

    // =============================================================
    // DATE PICKER STYLE
    // =============================================================

    private void styleDatePicker(
            DatePicker picker) {

        picker.setPrefHeight(42);

        picker.setStyle(
                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );
    }

    // =============================================================
    // ERROR ALERT
    // =============================================================

    private void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "MaaCare AI"
        );

        alert.setHeaderText(
                "Invalid Information"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}