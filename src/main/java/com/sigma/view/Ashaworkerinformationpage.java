package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Ashaworkerinformationpage {

    private final String PRIMARY_PINK = "#C62A78";
    private final String TEXT_DARK = "#333333";
    private final String BORDER = "#D0D0D0";
    private final String BG = "#F5F6F8";

    public void show(Stage stage) {

        // ---------------- TITLE ----------------

        Label title = new Label("Create ASHA Worker Account");
        title.setFont(Font.font("System", FontWeight.BOLD, 28));
        title.setTextFill(Color.web(TEXT_DARK));

        Label subtitle = new Label(
                "Enter your ASHA worker information to create your MaaCare AI account"
        );

        subtitle.setFont(Font.font("System", FontWeight.NORMAL, 14));
        subtitle.setTextFill(Color.web("#777777"));

        // ---------------- ASHA NAME ----------------

        Label nameLabel = createLabel("ASHA Worker Name");

        TextField nameField = createTextField(
                "Enter ASHA worker name"
        );

        // ---------------- EMAIL ----------------

        Label emailLabel = createLabel("ASHA Worker Email");

        TextField emailField = createTextField(
                "Enter ASHA worker email"
        );

        // ---------------- MOBILE ----------------

        Label mobileLabel = createLabel("Mobile Number");

        TextField mobileField = createTextField(
                "Enter ASHA worker mobile number"
        );

        // ---------------- ADDRESS ----------------

        Label addressLabel = createLabel("ASHA Worker Address");

        TextArea addressField = new TextArea();
        addressField.setPromptText("Enter complete address");
        addressField.setPrefHeight(75);
        addressField.setWrapText(true);

        setTextAreaStyle(addressField);

        // ---------------- ASHA ID ----------------

        Label idLabel = createLabel("ASHA Worker ID");

        TextField idField = createTextField(
                "Enter ASHA worker ID"
        );

        // ---------------- WORKER TYPE ----------------

        Label typeLabel = createLabel("Worker Type");

        ComboBox<String> typeComboBox = new ComboBox<>();

        typeComboBox.getItems().addAll(
                "ASHA Worker",
                "ASHA Supervisor"
        );

        typeComboBox.setPromptText("Select worker type");
        typeComboBox.setMaxWidth(Double.MAX_VALUE);

        typeComboBox.setPrefHeight(38);

        typeComboBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4;" +
                "-fx-background-radius: 4;" +
                "-fx-font-size: 13px;"
        );

        // ---------------- PASSWORD ----------------

        Label passwordLabel = createLabel("Password");

        PasswordField passwordField = new PasswordField();

        passwordField.setPromptText("Enter password");

        passwordField.setPrefHeight(38);
        passwordField.setMaxWidth(Double.MAX_VALUE);

        passwordField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4;" +
                "-fx-background-radius: 4;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 13px;"
        );

        // ---------------- CONFIRM PASSWORD ----------------

        Label confirmPasswordLabel = createLabel("Confirm Password");

        PasswordField confirmPasswordField = new PasswordField();

        confirmPasswordField.setPromptText("Confirm password");

        confirmPasswordField.setPrefHeight(38);
        confirmPasswordField.setMaxWidth(Double.MAX_VALUE);

        confirmPasswordField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4;" +
                "-fx-background-radius: 4;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 13px;"
        );

        // ---------------- CREATE ACCOUNT BUTTON ----------------

        Button createAccountButton =
                new Button("Create ASHA Worker Account");

        createAccountButton.setPrefHeight(46);
        createAccountButton.setMaxWidth(300);

        createAccountButton.setFont(
                Font.font("System", FontWeight.BOLD, 15)
        );

        createAccountButton.setTextFill(Color.WHITE);

        createAccountButton.setStyle(
        "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
        "-fx-background-radius: 10;" +
        "-fx-cursor: hand;"
);

        // Hover effect
        createAccountButton.setOnMouseEntered(e ->
        createAccountButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #D63B7D, #843DB5);" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        )
);

        createAccountButton.setStyle(
        "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
        "-fx-background-radius: 10;" +
        "-fx-cursor: hand;"
);

        // ---------------- BACK TO LOGIN ----------------

        Button backButton = new Button("← Back to Login");

        backButton.setFont(
                Font.font("System", FontWeight.NORMAL, 14)
        );

        backButton.setTextFill(Color.web("#333333"));

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;"
        );

        // ---------------- CREATE ACCOUNT ACTION ----------------

        createAccountButton.setOnAction(e -> {

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String mobile = mobileField.getText().trim();
            String address = addressField.getText().trim();
            String workerId = idField.getText().trim();
            String workerType = typeComboBox.getValue();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Validation

            if (name.isEmpty() ||
                    email.isEmpty() ||
                    mobile.isEmpty() ||
                    address.isEmpty() ||
                    workerId.isEmpty() ||
                    workerType == null ||
                    password.isEmpty() ||
                    confirmPassword.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Please fill all the fields."
                );

                return;
            }

            if (!password.equals(confirmPassword)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Password and Confirm Password do not match."
                );

                return;
            }

            if (mobile.length() != 10) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Please enter a valid 10 digit mobile number."
                );

                return;
            }

            // ----------------------------------------
            // Firebase saving code can be added here
            // ----------------------------------------

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "ASHA Worker account created successfully!"
            );

            // After successful signup you can open Login page here.
            // Example:
            //
            // new LoginPage().show(stage);
        });

        // ---------------- BACK BUTTON ----------------

       backButton.setOnAction(e -> {

    Loginpage loginPage = new Loginpage();

    Scene loginScene = loginPage.gotologinpage();

    stage.setScene(loginScene);

    stage.setMaximized(true);
});;

        // ---------------- FORM ----------------

        VBox form = new VBox(6);

        form.getChildren().addAll(

                nameLabel,
                nameField,

                emailLabel,
                emailField,

                mobileLabel,
                mobileField,

                addressLabel,
                addressField,

                idLabel,
                idField,

                typeLabel,
                typeComboBox,

                passwordLabel,
                passwordField,

                confirmPasswordLabel,
                confirmPasswordField
        );

        form.setPrefWidth(500);

        // ---------------- BUTTON AREA ----------------

        VBox buttonArea = new VBox(14);

        buttonArea.setAlignment(Pos.CENTER);

        buttonArea.getChildren().addAll(
                createAccountButton,
                backButton
        );

        // ---------------- MAIN CARD ----------------

        VBox card = new VBox(15);

        card.setAlignment(Pos.TOP_CENTER);

        card.setPadding(
                new Insets(38, 45, 35, 45)
        );

        card.setMaxWidth(590);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #EEEEEE;" +
                "-fx-border-radius: 20;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 20, 0, 0, 5);"
        );

        card.getChildren().addAll(
                title,
                subtitle,
                form,
                buttonArea
        );

        // ---------------- ROOT ----------------

        StackPane root = new StackPane();

        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        root.getChildren().add(card);

        // ---------------- SCROLL ----------------

        ScrollPane scrollPane = new ScrollPane(root);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        // ---------------- SCENE ----------------

        Scene scene = new Scene(
                scrollPane,
                1300,
                650
        );

        stage.setScene(scene);

        stage.setTitle("MaaCare AI - ASHA Worker Information");

        stage.show();
    }

    // =================================================
    // CREATE LABEL
    // =================================================

    private Label createLabel(String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        label.setTextFill(
                Color.web(TEXT_DARK)
        );

        return label;
    }

    // =================================================
    // CREATE TEXT FIELD
    // =================================================

    private TextField createTextField(String prompt) {

        TextField field = new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(38);

        field.setMaxWidth(Double.MAX_VALUE);

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4;" +
                "-fx-background-radius: 4;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 13px;"
        );

        return field;
    }

    // =================================================
    // TEXT AREA STYLE
    // =================================================

    private void setTextAreaStyle(TextArea area) {

        area.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 4;" +
                "-fx-background-radius: 4;" +
                "-fx-font-size: 13px;"
        );
    }

    // =================================================
    // ALERT
    // =================================================

    private void showAlert(
            Alert.AlertType type,
            String message
    ) {

        Alert alert = new Alert(type);

        alert.setTitle("MaaCare AI");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}
