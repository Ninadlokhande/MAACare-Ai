package com.sigma.view.doctorpages;

import com.sigma.view.scenesettings;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorBasicInformationPage extends Application {

        // =====================================================
        // START
        // =====================================================

        @Override
        public void start(Stage stage) {

                stage.setTitle(
                                "MaaCare AI - Doctor Basic Information");

                BorderPane root = new BorderPane();

                DoctorTheme.applyBackground(root);

                root.setPadding(
                                new Insets(
                                                30,
                                                50,
                                                30,
                                                50));

                // =====================================================
                // MAIN CONTAINER
                // =====================================================

                VBox container = new VBox(20);

                container.setMinWidth(650);
                container.setMaxWidth(Double.MAX_VALUE);
                container.setFillWidth(true);

                container.setPadding(
                                new Insets(30));

                container.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: " +
                                                DoctorTheme.BORDER + ";" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-effect: dropshadow(" +
                                                "gaussian, rgba(154,139,194,0.15)," +
                                                "18, 0.2, 0, 4);");

                // =====================================================
                // HEADER
                // =====================================================

                VBox heading = new VBox(6);

                Label title = new Label(
                                "Complete Your Profile");

                title.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                24));

                title.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label subtitle = new Label(
                                "Please provide your basic information to set up your doctor profile.");

                subtitle.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.NORMAL,
                                                12));

                subtitle.setTextFill(
                                Color.web(DoctorTheme.SECONDARY_TEXT));

                heading.getChildren().addAll(
                                title,
                                subtitle);

                // =====================================================
                // PERSONAL INFORMATION
                // =====================================================

                Label personalTitle = sectionTitle("Personal Information");

                TextField firstName = textField("Enter first name");

                TextField lastName = textField("Enter last name");

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "Female",
                                "Male",
                                "Other");

                gender.setPromptText(
                                "Select gender");

                styleComboBox(gender);

                DatePicker dob = new DatePicker();

                dob.setPromptText(
                                "Select date of birth");

                dob.setMaxWidth(
                                Double.MAX_VALUE);

                dob.setPrefHeight(40);

                // =====================================================
                // PERSONAL GRID
                // =====================================================

                GridPane personalGrid = new GridPane();

                personalGrid.setHgap(20);
                personalGrid.setVgap(8);

                ColumnConstraints col1 = new ColumnConstraints();

                col1.setPercentWidth(50);
                col1.setHgrow(Priority.ALWAYS);
                col1.setFillWidth(true);

                ColumnConstraints col2 = new ColumnConstraints();

                col2.setPercentWidth(50);
                col2.setHgrow(Priority.ALWAYS);
                col2.setFillWidth(true);

                personalGrid.getColumnConstraints()
                                .addAll(col1, col2);

                personalGrid.add(
                                fieldBox(
                                                "First Name",
                                                firstName),
                                0, 0);

                personalGrid.add(
                                fieldBox(
                                                "Last Name",
                                                lastName),
                                1, 0);

                personalGrid.add(
                                fieldBox(
                                                "Gender",
                                                gender),
                                0, 1);

                personalGrid.add(
                                fieldBox(
                                                "Date of Birth",
                                                dob),
                                1, 1);

                // =====================================================
                // CONTACT INFORMATION
                // =====================================================

                Label contactTitle = sectionTitle("Contact Information");

                TextField phone = textField("Enter mobile number");

                TextField email = textField("Enter email address");

                TextField address = textField("Enter residential address");

                GridPane contactGrid = new GridPane();

                contactGrid.setHgap(20);
                contactGrid.setVgap(8);

                contactGrid.getColumnConstraints()
                                .addAll(col1, col2);

                contactGrid.add(
                                fieldBox(
                                                "Mobile Number",
                                                phone),
                                0, 0);

                contactGrid.add(
                                fieldBox(
                                                "Email Address",
                                                email),
                                1, 0);

                contactGrid.add(
                                fieldBox(
                                                "Address",
                                                address),
                                0, 1);

                // =====================================================
                // PROFESSIONAL INFORMATION
                // =====================================================

                Label professionalTitle = sectionTitle(
                                "Professional Information");

                ComboBox<String> specialization = new ComboBox<>();

                specialization.getItems().addAll(
                                "Obstetrician & Gynecologist",
                                "Gynecologist",
                                "Pediatrician",
                                "General Physician",
                                "Dermatologist",
                                "Cardiologist",
                                "Other");

                specialization.setPromptText(
                                "Select specialization");

                styleComboBox(specialization);

                TextField qualification = textField(
                                "e.g. MBBS, MD");

                TextField experience = textField(
                                "Years of experience");

                TextField medicalLicense = textField(
                                "Enter medical license number");

                GridPane professionalGrid = new GridPane();

                professionalGrid.setHgap(20);
                professionalGrid.setVgap(8);

                professionalGrid.getColumnConstraints()
                                .addAll(col1, col2);

                professionalGrid.add(
                                fieldBox(
                                                "Specialization",
                                                specialization),
                                0, 0);

                professionalGrid.add(
                                fieldBox(
                                                "Qualification",
                                                qualification),
                                1, 0);

                professionalGrid.add(
                                fieldBox(
                                                "Experience",
                                                experience),
                                0, 1);

                professionalGrid.add(
                                fieldBox(
                                                "Medical License No.",
                                                medicalLicense),
                                1, 1);

                // =====================================================
                // CLINIC INFORMATION
                // =====================================================

                Label clinicTitle = sectionTitle(
                                "Clinic Information");

                TextField clinicName = textField(
                                "Enter clinic / hospital name");

                TextField clinicAddress = textField(
                                "Enter clinic address");

                GridPane clinicGrid = new GridPane();

                clinicGrid.setHgap(20);
                clinicGrid.setVgap(8);

                clinicGrid.getColumnConstraints()
                                .addAll(col1, col2);

                clinicGrid.add(
                                fieldBox(
                                                "Clinic / Hospital Name",
                                                clinicName),
                                0, 0);

                clinicGrid.add(
                                fieldBox(
                                                "Clinic Address",
                                                clinicAddress),
                                1, 0);

                // =====================================================
                // BUTTONS
                // =====================================================

                Button skip = new Button("Skip for Now");

                skip.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                12));

                skip.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " +
                                                DoctorTheme.SECONDARY_TEXT + ";" +
                                                "-fx-cursor: hand;");

                Button save = DoctorTheme.primaryButton(
                                "Save & Continue");

                HBox buttons = new HBox(15);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                buttons.getChildren().addAll(
                                skip,
                                save);

                // =====================================================
                // SAVE BUTTON
                // =====================================================

                save.setOnAction(e -> {

                        if (firstName.getText().isEmpty()
                                        || lastName.getText().isEmpty()
                                        || phone.getText().isEmpty()
                                        || email.getText().isEmpty()
                                        || specialization.getValue() == null
                                        || qualification.getText().isEmpty()
                                        || medicalLicense.getText().isEmpty()) {

                                Alert alert = new Alert(
                                                Alert.AlertType.WARNING);

                                alert.setTitle(
                                                "Incomplete Information");

                                alert.setHeaderText(null);

                                alert.setContentText(
                                                "Please fill all required fields.");

                                alert.showAndWait();

                                return;
                        }

                        Alert alert = new Alert(
                                        Alert.AlertType.INFORMATION);

                        alert.setTitle(
                                        "Profile Completed");

                        alert.setHeaderText(
                                        "Welcome to MaaCare AI!");

                        alert.setContentText(
                                        "Doctor information saved successfully.");

                        alert.showAndWait();

                        System.out.println(
                                        "Doctor Profile Saved");

                        System.out.println(
                                        "Name: "
                                                        + firstName.getText()
                                                        + " "
                                                        + lastName.getText());

                        System.out.println(
                                        "Gender: "
                                                        + gender.getValue());

                        System.out.println(
                                        "DOB: "
                                                        + dob.getValue());

                        System.out.println(
                                        "Phone: "
                                                        + phone.getText());

                        System.out.println(
                                        "Email: "
                                                        + email.getText());

                        System.out.println(
                                        "Specialization: "
                                                        + specialization.getValue());

                        System.out.println(
                                        "Qualification: "
                                                        + qualification.getText());

                        System.out.println(
                                        "Experience: "
                                                        + experience.getText());

                        System.out.println(
                                        "License: "
                                                        + medicalLicense.getText());

                        System.out.println(
                                        "Clinic: "
                                                        + clinicName.getText());

                        System.out.println(
                                        "Clinic Address: "
                                                        + clinicAddress.getText());
                });

                // =====================================================
                // SKIP BUTTON
                // =====================================================

                skip.setOnAction(e -> {

                        Alert alert = new Alert(
                                        Alert.AlertType.INFORMATION);

                        alert.setTitle(
                                        "Profile");

                        alert.setHeaderText(null);

                        alert.setContentText(
                                        "You can complete your profile later.");

                        alert.showAndWait();
                });

                // =====================================================
                // ADD ALL CONTENT
                // =====================================================

                container.getChildren().addAll(

                                heading,

                                new Separator(),

                                personalTitle,
                                personalGrid,

                                contactTitle,
                                contactGrid,

                                professionalTitle,
                                professionalGrid,

                                clinicTitle,
                                clinicGrid,

                                buttons);

                // =====================================================
                // CENTER
                // =====================================================

                StackPane center = new StackPane(container);

                center.setAlignment(Pos.TOP_CENTER);
                center.setPadding(new Insets(5));
                center.setMinWidth(0);
                center.setMaxWidth(Double.MAX_VALUE);

                ScrollPane scroll = new ScrollPane(center);
                scroll.setFitToWidth(true);
                scroll.setFitToHeight(false);
                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scroll.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                root.setCenter(scroll);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                root,
                                scenesettings.rectanguler2d.getWidth(),
                                scenesettings.rectanguler2d.getHeight());

                stage.setScene(scene);

                // =====================================================
                // STAGE SIZE
                // =====================================================

                double screenWidth = scenesettings.rectanguler2d.getWidth();

                double screenHeight = scenesettings.rectanguler2d.getHeight();

                stage.setWidth(screenWidth);
                stage.setHeight(screenHeight);

                stage.setMinWidth(
                                Math.min(1000, screenWidth));
                stage.setMinHeight(
                                Math.min(700, screenHeight));

                stage.setResizable(true);
                stage.centerOnScreen();
                stage.show();
        }

        // =====================================================
        // SECTION TITLE
        // =====================================================

        private static Label sectionTitle(
                        String text) {

                Label label = new Label(text);

                label.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                15));

                label.setTextFill(
                                Color.web(DoctorTheme.PRIMARY));

                return label;
        }

        // =====================================================
        // TEXT FIELD
        // =====================================================

        private static TextField textField(
                        String prompt) {

                TextField field = new TextField();

                field.setPromptText(prompt);

                field.setPrefHeight(40);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                return field;
        }

        // =====================================================
        // FIELD BOX
        // =====================================================

        private static VBox fieldBox(
                        String labelText,
                        Control control) {

                VBox box = new VBox(5);

                Label label = new Label(labelText);

                label.setFont(
                                javafx.scene.text.Font.font(
                                                DoctorTheme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                11));

                label.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                box.getChildren().addAll(
                                label,
                                control);

                return box;
        }

        // =====================================================
        // COMBO BOX STYLE
        // =====================================================

        private static void styleComboBox(
                        ComboBox<String> combo) {

                combo.setMaxWidth(
                                Double.MAX_VALUE);

                combo.setPrefHeight(40);
        }

        // =====================================================
        // MAIN
        // =====================================================

        public static void main(String[] args) {

                launch(args);
        }
}