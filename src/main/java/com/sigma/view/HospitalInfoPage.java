
package com.sigma.view;

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

public class HospitalInfoPage {

    public static void show(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title = new Label("Create Hospital Account");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("#17184F"));

        Label subtitle = new Label(
                "Enter your hospital iqnformation to create your MaaCare AI account"
        );
        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setTextFill(Color.web("#77758A"));

        // =========================
        // HOSPITAL NAME
        // =========================

        TextField hospitalName = new TextField();
        hospitalName.setPromptText("Enter hospital name");
        hospitalName.setPrefHeight(40);

        // =========================
        // HOSPITAL EMAIL
        // =========================

        TextField email = new TextField();
        email.setPromptText("Enter hospital email");
        email.setPrefHeight(40);

        // =========================
        // MOBILE NUMBER
        // =========================

        TextField mobile = new TextField();
        mobile.setPromptText("Enter hospital mobile number");
        mobile.setPrefHeight(40);

        // =========================
        // HOSPITAL ADDRESS
        // =========================

        TextArea address = new TextArea();
        address.setPromptText("Enter complete hospital address");
        address.setPrefRowCount(3);
        address.setWrapText(true);
        address.setPrefHeight(80);

        // =========================
        // REGISTRATION NUMBER
        // =========================

        TextField registrationNumber = new TextField();
        registrationNumber.setPromptText(
                "Enter hospital registration number"
        );
        registrationNumber.setPrefHeight(40);

        // =========================
        // HOSPITAL TYPE
        // =========================

        ComboBox<String> hospitalType = new ComboBox<>();

        hospitalType.getItems().addAll(
                "Government Hospital",
                "Private Hospital",
                "Public Hospital",
                "Maternity Hospital",
                "Speciality Hospital"
        );

        hospitalType.setPromptText("Select hospital type");
        hospitalType.setPrefHeight(40);
        hospitalType.setMaxWidth(Double.MAX_VALUE);

        // =========================
        // PASSWORD
        // =========================

        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");
        password.setPrefHeight(40);

        // =========================
        // CONFIRM PASSWORD
        // =========================

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm password");
        confirmPassword.setPrefHeight(40);

        // =========================
        // FORM
        // =========================

        VBox form = new VBox(8);

        form.getChildren().addAll(

                createLabel("Hospital Name"),
                hospitalName,

                createLabel("Hospital Email"),
                email,

                createLabel("Mobile Number"),
                mobile,

                createLabel("Hospital Address"),
                address,

                createLabel("Hospital Registration Number"),
                registrationNumber,

                createLabel("Hospital Type"),
                hospitalType,

                createLabel("Password"),
                password,

                createLabel("Confirm Password"),
                confirmPassword
        );

        // =========================
        // CREATE ACCOUNT BUTTON
        // =========================

        Button signupButton = new Button(
                "Create Hospital Account"
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

        // =========================
        // BACK BUTTON
        // =========================

        Button backButton = new Button(
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

            Loginpage loginpage = new Loginpage();

            stage.setScene(
                    loginpage.gotologinpage()
            );

        });

        // =========================
        // SIGNUP BUTTON ACTION
        // =========================

        signupButton.setOnAction(e -> {

            String name =
                    hospitalName.getText().trim();

            String mail =
                    email.getText().trim();

            String phone =
                    mobile.getText().trim();

            String hospitalAddress =
                    address.getText().trim();

            String registration =
                    registrationNumber.getText().trim();

            String type =
                    hospitalType.getValue();

            String pass =
                    password.getText();

            String confirmPass =
                    confirmPassword.getText();

            // =========================
            // EMPTY FIELD VALIDATION
            // =========================

            if (name.isEmpty()
                    || mail.isEmpty()
                    || phone.isEmpty()
                    || hospitalAddress.isEmpty()
                    || registration.isEmpty()
                    || type == null
                    || pass.isEmpty()
                    || confirmPass.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all hospital information."
                );

                return;
            }

            // =========================
            // EMAIL VALIDATION
            // =========================

            if (!mail.contains("@")
                    || !mail.contains(".")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Email",
                        "Please enter a valid hospital email."
                );

                email.requestFocus();

                return;
            }

            // =========================
            // MOBILE VALIDATION
            // =========================

            if (!phone.matches("\\d{10}")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Mobile Number",
                        "Mobile number must contain exactly 10 digits."
                );

                mobile.requestFocus();

                return;
            }

            // =========================
            // PASSWORD MATCH
            // =========================

            if (!pass.equals(confirmPass)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Password Error",
                        "Password and Confirm Password do not match."
                );

                confirmPassword.requestFocus();

                return;
            }

            // =========================
            // SUCCESS
            // =========================

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Hospital Account Created",
                    "Hospital account created successfully!"
            );

            // =========================
            // OPEN DASHBOARD
            // =========================

            Dashboard dashboard = new Dashboard();

            dashboard.show(stage);

            stage.setMaximized(true);
        });

        // =========================
        // BUTTON BOX
        // =========================

        VBox buttons = new VBox(10);

        buttons.setAlignment(Pos.CENTER);

        buttons.getChildren().addAll(
                signupButton,
                backButton
        );

        // =========================
        // CARD
        // =========================

        VBox card = new VBox(20);

        card.setAlignment(Pos.TOP_CENTER);

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

        StackPane root = new StackPane();

        root.setAlignment(Pos.CENTER);

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

        Scene scene = new Scene(
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
                "MaaCare AI - Hospital Signup"
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