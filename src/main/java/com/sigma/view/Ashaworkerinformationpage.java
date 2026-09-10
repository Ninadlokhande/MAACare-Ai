package com.sigma.view;

import com.sigma.controller.Controller;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Ashaworkerinformationpage {

    public void show(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("Create ASHA Worker Account");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web("#17184F")
        );

        // =========================
        // SUBTITLE
        // =========================

        Label subtitle =
                new Label(
                        "Enter your ASHA worker information to create your MaaCare AI account"
                );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web("#77758A")
        );

        // =========================
        // ASHA WORKER NAME
        // =========================

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter ASHA worker name"
        );

        nameField.setPrefHeight(40);

        // =========================
        // ASHA WORKER EMAIL
        // =========================

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter ASHA worker email"
        );

        emailField.setPrefHeight(40);

        // =========================
        // MOBILE NUMBER
        // =========================

        TextField mobileField =
                new TextField();

        mobileField.setPromptText(
                "Enter ASHA worker mobile number"
        );

        mobileField.setPrefHeight(40);

        // =========================
        // ASHA WORKER ADDRESS
        // =========================

        TextArea addressField =
                new TextArea();

        addressField.setPromptText(
                "Enter complete ASHA worker address"
        );

        addressField.setPrefRowCount(3);

        addressField.setWrapText(true);

        addressField.setPrefHeight(80);

        // =========================
        // ASHA WORKER ID
        // =========================

        TextField idField =
                new TextField();

        idField.setPromptText(
                "Enter ASHA worker ID"
        );

        idField.setPrefHeight(40);

        // =========================
        // WORKER TYPE
        // =========================

        ComboBox<String> typeComboBox =
                new ComboBox<>();

        typeComboBox.getItems().addAll(
                "ASHA Worker",
                "ASHA Supervisor"
        );

        typeComboBox.setPromptText(
                "Select worker type"
        );

        typeComboBox.setPrefHeight(40);

        typeComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================
        // PASSWORD
        // =========================

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter password"
        );

        passwordField.setPrefHeight(40);

        passwordField.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================
        // CONFIRM PASSWORD
        // =========================

        PasswordField confirmPasswordField =
                new PasswordField();

        confirmPasswordField.setPromptText(
                "Confirm password"
        );

        confirmPasswordField.setPrefHeight(40);

        confirmPasswordField.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================
        // FORM
        // =========================

        VBox form =
                new VBox(8);

        form.getChildren().addAll(

                createLabel("ASHA Worker Name"),
                nameField,

                createLabel("ASHA Worker Email"),
                emailField,

                createLabel("Mobile Number"),
                mobileField,

                createLabel("ASHA Worker Address"),
                addressField,

                createLabel("ASHA Worker ID"),
                idField,

                createLabel("Worker Type"),
                typeComboBox,

                createLabel("Password"),
                passwordField,

                createLabel("Confirm Password"),
                confirmPasswordField
        );

        // =========================
        // CREATE ACCOUNT BUTTON
        // =========================

        Button createAccountButton =
                new Button(
                        "Create ASHA Worker Account"
                );

        createAccountButton.setPrefWidth(280);

        createAccountButton.setPrefHeight(45);

        createAccountButton.setStyle(
                "-fx-background-color: #E83E83;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        // =========================
        // BACK BUTTON
        // =========================

        Button backButton =
                new Button(
                        "← Back to Login"
                );

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8056C5;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        // =========================
        // BACK ACTION
        // =========================

        backButton.setOnAction(e -> {

            Loginpage loginpage =
                    new Loginpage();

            stage.setScene(
                    loginpage.gotologinpage()
            );

            stage.setMaximized(true);

            stage.show();

            stage.toFront();
        });

        // =========================
        // CREATE ACCOUNT ACTION
        // =========================

        createAccountButton.setOnAction(e -> {

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String mobile =
                    mobileField.getText().trim();

            String address =
                    addressField.getText().trim();

            String workerId =
                    idField.getText().trim();

            String workerType =
                    typeComboBox.getValue();

            String password =
                    passwordField.getText();

            String confirmPassword =
                    confirmPasswordField.getText();

            // =========================
            // EMPTY FIELD VALIDATION
            // =========================

            if (name.isEmpty()
                    || email.isEmpty()
                    || mobile.isEmpty()
                    || address.isEmpty()
                    || workerId.isEmpty()
                    || workerType == null
                    || password.isEmpty()
                    || confirmPassword.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all ASHA worker information."
                );

                return;
            }

            // =========================
            // EMAIL VALIDATION
            // =========================

            if (!email.contains("@")
                    || !email.contains(".")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Email",
                        "Please enter a valid ASHA worker email."
                );

                emailField.requestFocus();

                return;
            }

            // =========================
            // MOBILE VALIDATION
            // =========================

            if (!mobile.matches("\\d{10}")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Mobile Number",
                        "Mobile number must contain exactly 10 digits."
                );

                mobileField.requestFocus();

                return;
            }

            // =========================
            // PASSWORD LENGTH
            // =========================

            if (password.length() < 6) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Weak Password",
                        "Password must contain at least 6 characters."
                );

                passwordField.requestFocus();

                return;
            }

            // =========================
            // PASSWORD MATCH
            // =========================

            if (!password.equals(confirmPassword)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Password Error",
                        "Password and Confirm Password do not match."
                );

                confirmPasswordField.requestFocus();

                return;
            }

            // =========================
            // DISABLE BUTTON
            // =========================

            createAccountButton.setDisable(true);

            // =========================
            // FIREBASE SIGNUP
            // =========================

            Controller controller =
                    new Controller();

            Task<Boolean> signupTask =
                    new Task<>() {

                        @Override
                        protected Boolean call()
                                throws Exception {

                            return controller.signup(
                                    email,
                                    password
                            );
                        }
                    };

            // =========================
            // SUCCESS
            // =========================

            signupTask.setOnSucceeded(event -> {

                createAccountButton.setDisable(false);

                Boolean success =
                        signupTask.getValue();

                if (Boolean.TRUE.equals(success)) {

                    try {

                        String firebaseUid =
                                controller.getFirebaseUid();

                        if (firebaseUid == null
                                || firebaseUid.trim().isEmpty()) {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Signup Error",
                                    "ASHA account was created, but Firebase UID was not received."
                            );

                            return;
                        }

                        // =========================
                        // OPEN ASHA DASHBOARD
                        // =========================

                        Asha_workerdashboard ashaDashboard =
                                new Asha_workerdashboard();

                        Scene ashaScene =
                                ashaDashboard.run();

                        stage.setScene(
                                ashaScene
                        );

                        stage.setMaximized(true);

                        stage.show();

                        stage.toFront();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Dashboard Error",
                                "ASHA account created, but dashboard could not be opened."
                        );
                    }

                } else {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Signup Failed",
                            "ASHA Worker account could not be created. Please try again."
                    );
                }
            });

            // =========================
            // SIGNUP FAILED
            // =========================

            signupTask.setOnFailed(event -> {

                createAccountButton.setDisable(false);

                Throwable error =
                        signupTask.getException();

                String message =
                        error != null
                                ? error.getMessage()
                                : "Unable to create ASHA Worker account.";

                showAlert(
                        Alert.AlertType.ERROR,
                        "Signup Failed",
                        message
                );
            });

            // =========================
            // START THREAD
            // =========================

            Thread thread =
                    new Thread(signupTask);

            thread.setDaemon(true);

            thread.start();
        });

        // =========================
        // BUTTON BOX
        // =========================

        VBox buttons =
                new VBox(10);

        buttons.setAlignment(
                Pos.CENTER
        );

        buttons.getChildren().addAll(
                createAccountButton,
                backButton
        );

        // =========================
        // CARD
        // =========================

        VBox card =
                new VBox(20);

        card.setAlignment(
                Pos.TOP_CENTER
        );

        card.setPadding(
                new Insets(35)
        );

        card.setMaxWidth(550);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-effect: dropshadow(" +
                "gaussian," +
                "rgba(0,0,0,0.15)," +
                "15," +
                "0," +
                "0," +
                "5" +
                ");"
        );

        card.getChildren().addAll(
                title,
                subtitle,
                form,
                buttons
        );

        // =========================
        // ROOT
        // =========================

        StackPane root =
                new StackPane();

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: #FCFAFD;"
        );

        root.getChildren().add(card);

        // =========================
        // SCROLL PANE
        // =========================

        ScrollPane scrollPane =
                new ScrollPane(root);

        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: #FCFAFD;"
        );

        // =========================
        // SCENE SETTINGS
        // =========================

        Rectangle2D bounds =
                scenesettings.rectanguler2d;

        Scene scene =
                new Scene(
                        scrollPane,
                        bounds.getWidth(),
                        bounds.getHeight()
                );

        // =========================
        // SET SCENE
        // =========================

        stage.setScene(scene);

        stage.setX(
                bounds.getMinX()
        );

        stage.setY(
                bounds.getMinY()
        );

        stage.setTitle(
                "MaaCare AI - ASHA Worker Signup"
        );

        stage.show();
    }

    // =========================
    // LABEL METHOD
    // =========================

    private static Label createLabel(
            String text) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        label.setTextFill(
                Color.web("#17184F")
        );

        return label;
    }

    // =========================
    // ALERT METHOD
    // =========================

    private static void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}