package com.sigma.view.motherPages;

import java.time.LocalDate;

import com.sigma.model.MotherWlcModel;

import javafx.application.Application;
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

public class MotherWelcome extends Application {
        public static Stage motherWelcomeStage;
        private Scene motherWelcomeScene;

    @Override
    public void start(Stage motherWelcomeStage) throws Exception {
        MotherWelcome.motherWelcomeStage = motherWelcomeStage;

        // =========================
        // LEFT SIDE - BRANDING
        // =========================

        VBox leftPanel = new VBox(20);

        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPadding(new Insets(40));
        leftPanel.setPrefWidth(500);
        leftPanel.setMinWidth(450);

        // Logo
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
                Font.font("Arial", FontWeight.BOLD, 30)
        );

        appName.setTextFill(
                Color.web("#E84A87")
        );

        Label tagline = new Label(
                "Smart Care For Every Mother And Baby."
        );

        tagline.setFont(
                Font.font("Arial", FontWeight.NORMAL, 16)
        );

        tagline.setTextFill(
                Color.web("#6B6B6B")
        );

        tagline.setAlignment(Pos.CENTER);

        tagline.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        Label welcomeText = new Label(
                "Welcome, Mother! 💗"
        );

        welcomeText.setFont(
                Font.font("Arial", FontWeight.BOLD, 24)
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


        // =========================
        // RIGHT SIDE - FORM
        // =========================

        VBox formCard = new VBox(25);

        formCard.setPadding(
                new Insets(30)
        );

        formCard.setPrefWidth(650);
        formCard.setMaxWidth(650);


        // =========================
        // TITLE
        // =========================

        Label title = new Label(
                "Let's get to know you"
        );

        title.setFont(
                Font.font("Arial", FontWeight.BOLD, 25)
        );

        title.setTextFill(
                Color.web("#333333")
        );


        Label subtitle = new Label(
                "Enter a few basic details to personalize your MaaCare journey."
        );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web("#777777")
        );


        // =========================
        // BASIC INFORMATION
        // =========================

        Label basicTitle = new Label(
                "Basic Information"
        );

        basicTitle.setFont(
                Font.font("Arial", FontWeight.BOLD, 18)
        );

        basicTitle.setTextFill(
                Color.web("#B83B63")
        );


        // Full Name

        TextField nameField = new TextField();

        nameField.setPromptText(
                "Full Name"
        );

        nameField.setPrefWidth(280);

        styleTextField(nameField);


        // Date of Birth

        DatePicker dobDatePicker =
                new DatePicker();

        dobDatePicker.setPromptText(
                "Date of Birth"
        );

        dobDatePicker.setPrefWidth(280);

        styleDatePicker(
                dobDatePicker
        );


        // Location

        TextField locationField = new TextField();

        locationField.setPromptText("City / Location");

        locationField.setPrefWidth(280);

        styleTextField(
                locationField
        );


        // Weight

        TextField weightField =
                new TextField();

        weightField.setPromptText(
                "Weight (kg)"
        );

        weightField.setPrefWidth(280);

        styleTextField(
                weightField
        );


        // Blood Group

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

        bloodGroupBox.setPromptText(
                "Blood Group"
        );

        bloodGroupBox.setPrefWidth(280);

        styleComboBox(
                bloodGroupBox
        );


        // Medical Condition

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

        styleComboBox(
                medicalConditionBox
        );


        // =========================
        // PREGNANCY INFORMATION
        // =========================

        Label pregnancyTitle =
                new Label(
                        "Pregnancy Information"
                );

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

        lmpDatePicker.setPrefWidth(
                580
        );

        styleDatePicker(
                lmpDatePicker
        );


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


        // =========================
        // EDD CALCULATION
        // =========================

        lmpDatePicker.setOnAction(event -> {

            LocalDate lmpDate =
                    lmpDatePicker.getValue();

            if (lmpDate != null) {

                LocalDate eddDate =
                        lmpDate.plusDays(280);

                eddValue.setText(
                        eddDate.getDayOfMonth()
                                + " "
                                + eddDate.getMonth()
                                + " "
                                + eddDate.getYear()
                );
            }
        });


        // =========================
        // CONTINUE BUTTON
        // =========================

        Button continueButton =
                new Button(
                        "Continue →"
                );

        continueButton.setPrefWidth(
                200
        );

        continueButton.setPrefHeight(
                45
        );

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


        // =========================
        // BUTTON ACTION
        // =========================

        continueButton.setOnAction(event -> {

            String name =
                    nameField.getText();

            LocalDate dob =
                    dobDatePicker.getValue();

            String location =
                    locationField.getText();

            String weight =
                    weightField.getText();

            String bloodGroup =
                    bloodGroupBox.getValue();

            String medicalCondition =
                    medicalConditionBox.getValue();

            LocalDate lmp =
                    lmpDatePicker.getValue();


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


            LocalDate edd =
                    lmp.plusDays(280);

            MotherWlcModel motherModel =
                new MotherWlcModel(
                name,
                dob,
                location,
                Double.parseDouble(weight),
                bloodGroup,
                medicalCondition,
                lmp,
                edd
        );


            System.out.println(
                    "Mother Name: " + name
            );

            System.out.println(
                    "Date of Birth: " + dob
            );

            System.out.println(
                    "Location: " + location
            );

            System.out.println(
                    "Weight: " + weight + " kg"
            );

            System.out.println(
                    "Blood Group: " + bloodGroup
            );

            System.out.println(
                    "Medical Condition: "
                            + medicalCondition
            );

            System.out.println(
                    "LMP: " + lmp
            );

            System.out.println(
                    "EDD: " + edd
            );


            // Dashboard navigation
            MotherDashBoard dashboard =
                new MotherDashBoard(motherModel);

                motherWelcomeStage.setScene(
                dashboard.getmotherDashboardScene()
        );
            
            
        });


        // =========================
        // FORM ROWS
        // =========================

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


        // =========================
        // ADD FORM CONTENT
        // =========================

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


        // =========================
        // FORM CARD STYLE
        // =========================

        formCard.setStyle(

                "-fx-background-color: rgba(255,255,255,0.95);"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-width: 1;"
        );


        // =========================
        // MAIN LAYOUT
        // =========================

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


        

        leftPanel.setAlignment(
                Pos.CENTER
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


        // =========================
        // SCROLL
        // =========================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainPane
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setFitToHeight(
                true
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );


        // =========================
        // SCENE
        // =========================

        Scene motherWelcomeScene =
                new Scene(
                        scrollPane,
                        1200,
                        750
                );


                motherWelcomeStage.setTitle("MaaCare AI - Welcome Mother");
                motherWelcomeStage.setScene(motherWelcomeScene);
                
                motherWelcomeStage.setMaximized(true);
                
                motherWelcomeStage.show();

       
    }


    // =========================
    // TEXT FIELD STYLE
    // =========================

    private void styleTextField(
            TextField field
    ) {

        field.setPrefHeight(
                42
        );

        field.setStyle(

                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-padding: 8 12;"
        );
    }


    // =========================
    // COMBOBOX STYLE
    // =========================

    private void styleComboBox(
            ComboBox<String> box
    ) {

        box.setPrefHeight(
                42
        );

        box.setStyle(

                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );
    }


    // =========================
    // DATEPICKER STYLE
    // =========================

    private void styleDatePicker(
            DatePicker picker
    ) {

        picker.setPrefHeight(
                42
        );

        picker.setStyle(

                "-fx-background-color: #FFF9FA;"
                        + "-fx-border-color: #E7B8C5;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );
    }


    // =========================
    // ERROR MESSAGE
    // =========================

    private void showError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "MaaCare AI"
        );

        alert.setHeaderText(
                "Incomplete Information"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}