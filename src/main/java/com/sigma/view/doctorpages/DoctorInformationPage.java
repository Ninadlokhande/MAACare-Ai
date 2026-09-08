package com.sigma.view.doctorpages;

import com.sigma.controller.Controller;
import com.sigma.view.Loginpage;
import com.sigma.view.Welcomepage;

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

public class DoctorInformationPage {

    // =========================================================
    // SHOW DOCTOR INFORMATION PAGE
    // =========================================================

    public void show(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title = new Label("Create Doctor Account");

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

        Label subtitle = new Label(
                "Enter your doctor information to create your MaaCare AI account"
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#77758A")
        );

        // =========================================================
        // DOCTOR NAME
        // =========================================================

        TextField doctorName = new TextField();

        doctorName.setPromptText(
                "Enter doctor name"
        );

        doctorName.setPrefHeight(40);

        // =========================================================
        // DOCTOR EMAIL
        // =========================================================

        TextField email = new TextField();

        email.setPromptText(
                "Enter doctor email"
        );

        email.setPrefHeight(40);

        // =========================================================
        // MOBILE NUMBER
        // =========================================================

        TextField mobile = new TextField();

        mobile.setPromptText(
                "Enter doctor mobile number"
        );

        mobile.setPrefHeight(40);

        // =========================================================
        // DOCTOR ADDRESS
        // =========================================================

        TextArea address = new TextArea();

        address.setPromptText(
                "Enter complete doctor address"
        );

        address.setPrefRowCount(3);

        address.setWrapText(true);

        address.setPrefHeight(80);

        // =========================================================
        // DOCTOR ID
        // =========================================================

        TextField doctorId = new TextField();

        doctorId.setPromptText(
                "Enter doctor ID"
        );

        doctorId.setPrefHeight(40);

        // =========================================================
        // SPECIALIZATION
        // =========================================================

        ComboBox<String> specialization =
                new ComboBox<>();

        specialization.getItems().addAll(
                "Gynecologist",
                "Obstetrician",
                "Pediatrician",
                "General Physician",
                "Child Specialist",
                "Nutritionist",
                "Other"
        );

        specialization.setPromptText(
                "Select specialization"
        );

        specialization.setPrefHeight(40);

        specialization.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================================================
        // PASSWORD
        // =========================================================

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter password"
        );

        password.setPrefHeight(40);

        // =========================================================
        // CONFIRM PASSWORD
        // =========================================================

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Confirm password"
        );

        confirmPassword.setPrefHeight(40);

        // =========================================================
        // FORM
        // =========================================================

        VBox form = new VBox(8);

        form.getChildren().addAll(

                createLabel("Doctor Name"),
                doctorName,

                createLabel("Doctor Email"),
                email,

                createLabel("Mobile Number"),
                mobile,

                createLabel("Doctor Address"),
                address,

                createLabel("Doctor ID"),
                doctorId,

                createLabel("Specialization"),
                specialization,

                createLabel("Password"),
                password,

                createLabel("Confirm Password"),
                confirmPassword
        );

        // =========================================================
        // CREATE ACCOUNT BUTTON
        // =========================================================

        Button signupButton =
                new Button(
                        "Create Doctor Account"
                );

        signupButton.setPrefWidth(280);

        signupButton.setPrefHeight(45);

        signupButton.setStyle(
                "-fx-background-color: #E83E83;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // BACK BUTTON
        // =========================================================

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

        // =========================================================
        // SIGNUP BUTTON ACTION
        // =========================================================

        signupButton.setOnAction(e -> {

            String name =
                    doctorName.getText().trim();

            String mail =
                    email.getText().trim();

            String phone =
                    mobile.getText().trim();

            String doctorAddress =
                    address.getText().trim();

            String id =
                    doctorId.getText().trim();

            String selectedSpecialization =
                    specialization.getValue();

            String pass =
                    password.getText();

            String confirmPass =
                    confirmPassword.getText();

            // =====================================================
            // EMPTY FIELD VALIDATION
            // =====================================================

            if (name.isEmpty()
                    || mail.isEmpty()
                    || phone.isEmpty()
                    || doctorAddress.isEmpty()
                    || id.isEmpty()
                    || selectedSpecialization == null
                    || pass.isEmpty()
                    || confirmPass.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all doctor information."
                );

                return;
            }

            // =====================================================
            // EMAIL VALIDATION
            // =====================================================

            if (!mail.contains("@")
                    || !mail.contains(".")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Email",
                        "Please enter a valid doctor email."
                );

                email.requestFocus();

                return;
            }

            // =====================================================
            // MOBILE VALIDATION
            // =====================================================

            if (!phone.matches("\\d{10}")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Mobile Number",
                        "Mobile number must contain exactly 10 digits."
                );

                mobile.requestFocus();

                return;
            }

            // =====================================================
            // PASSWORD LENGTH
            // =====================================================

            if (pass.length() < 6) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Weak Password",
                        "Password must contain at least 6 characters."
                );

                password.requestFocus();

                return;
            }

            // =====================================================
            // PASSWORD MATCH
            // =====================================================

            if (!pass.equals(confirmPass)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Password Error",
                        "Password and Confirm Password do not match."
                );

                confirmPassword.requestFocus();

                return;
            }

            // =====================================================
            // FIREBASE SIGNUP
            // =====================================================

            signupButton.setDisable(true);

            Controller controller =
                    new Controller();

            Task<Boolean> signupTask =
                    new Task<>() {

                        @Override
                        protected Boolean call()
                                throws Exception {

                            return controller.signup(
                                    mail,
                                    pass
                            );
                        }
                    };

            signupTask.setOnSucceeded(event -> {

                signupButton.setDisable(false);

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
                                    "Doctor account was created, but Firebase UID was not received."
                            );

                            return;
                        }

                        // =========================================
                        // OPEN DOCTOR DASHBOARD
                        // =========================================

                        DoctorDashboard.setCurrentDoctorUid(
                                firebaseUid
                        );

                        DoctorDashboard.showDashboard(
                                stage,
                                firebaseUid
                        );

                        stage.setMaximized(true);

                        stage.show();

                        stage.toFront();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Dashboard Error",
                                "Doctor account created, but dashboard could not be opened."
                        );
                    }

                } else {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Signup Failed",
                            "Doctor account could not be created. Please try again."
                    );
                }
            });

            signupTask.setOnFailed(event -> {

                signupButton.setDisable(false);

                Throwable error =
                        signupTask.getException();

                String message =
                        error != null
                                ? error.getMessage()
                                : "Unable to create doctor account.";

                showAlert(
                        Alert.AlertType.ERROR,
                        "Signup Failed",
                        message
                );
            });

            Thread thread =
                    new Thread(signupTask);

            thread.setDaemon(true);

            thread.start();
        });

        // =========================================================
        // BUTTON BOX
        // =========================================================

        VBox buttons =
                new VBox(10);

        buttons.setAlignment(
                Pos.CENTER
        );

        buttons.getChildren().addAll(
                signupButton,
                backButton
        );

        // =========================================================
        // CARD
        // =========================================================

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

        // =========================================================
        // ROOT
        // =========================================================

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

        // =========================================================
        // SCROLL PANE
        // =========================================================

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

        // =========================================================
        // SCENE SETTINGS
        // =========================================================

        Rectangle2D bounds =
                com.sigma.view.scenesettings.rectanguler2d;

        Scene scene =
                new Scene(
                        scrollPane,
                        bounds.getWidth(),
                        bounds.getHeight()
                );

        // =========================================================
        // SET SCENE
        // =========================================================

        stage.setScene(scene);

        stage.setX(
                bounds.getMinX()
        );

        stage.setY(
                bounds.getMinY()
        );

        stage.setTitle(
                "MaaCare AI - Doctor Signup"
        );

        stage.show();
    }

    // =========================================================
    // LABEL METHOD
    // =========================================================

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

    // =========================================================
    // ALERT METHOD
    // =========================================================

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