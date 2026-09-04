package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientController;
import com.sigma.model.DoctorModel.Patient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AddPatientPage {

        public static void show() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Add Patient",
                                "Enter patient information to add a new patient.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // =====================================================
                // FORM CARD
                // =====================================================

                VBox form = Theme.card();

                form.setMaxWidth(850);

                form.setPadding(
                                new Insets(28));

                form.setSpacing(15);

                // =====================================================
                // PERSONAL INFORMATION
                // =====================================================

                Label personalTitle = new Label("Personal Information");

                personalTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + Theme.TEXT + ";");

                TextField name = textField("Full Name");

                TextField age = textField("Age");

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "Female",
                                "Male",
                                "Other");

                gender.setPromptText(
                                "Select Gender");

                gender.setMaxWidth(
                                Double.MAX_VALUE);

                gender.setPrefHeight(40);

                TextField phone = textField("Contact Number");

                TextField address = textField("Address");

                // =====================================================
                // VISIT INFORMATION
                // =====================================================

                Label visitTitle = new Label("Visit Information");

                visitTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + Theme.TEXT + ";");

                TextField lastVisit = textField("Last Visit");

                TextField nextVisit = textField("Next Visit");

                // =====================================================
                // GRID
                // =====================================================

                GridPane grid = new GridPane();

                grid.setHgap(20);
                grid.setVgap(15);

                ColumnConstraints col1 = new ColumnConstraints();

                col1.setPercentWidth(50);

                ColumnConstraints col2 = new ColumnConstraints();

                col2.setPercentWidth(50);

                grid.getColumnConstraints().addAll(
                                col1,
                                col2);

                grid.add(
                                fieldBox("Full Name", name),
                                0,
                                0);

                grid.add(
                                fieldBox("Age", age),
                                1,
                                0);

                grid.add(
                                fieldBox("Gender", gender),
                                0,
                                1);

                grid.add(
                                fieldBox("Contact Number", phone),
                                1,
                                1);

                grid.add(
                                fieldBox("Address", address),
                                0,
                                2);

                grid.add(
                                fieldBox("Last Visit", lastVisit),
                                1,
                                2);

                grid.add(
                                fieldBox("Next Visit", nextVisit),
                                0,
                                3);

                // =====================================================
                // BUTTONS
                // =====================================================

                Button cancel = new Button("Cancel");

                cancel.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                Button save = Theme.primaryButton(
                                "Save Patient");

                HBox buttons = new HBox(15);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                buttons.getChildren().addAll(
                                cancel,
                                save);

                // =====================================================
                // BACK / CANCEL
                // =====================================================

                back.setOnAction(e -> PatientsPage.show());

                cancel.setOnAction(e -> PatientsPage.show());

                // =====================================================
                // SAVE PATIENT
                // =====================================================

                save.setOnAction(e -> {

                        // -----------------------------------------------
                        // VALIDATION
                        // -----------------------------------------------

                        if (name.getText().trim().isEmpty()
                                        || age.getText().trim().isEmpty()
                                        || gender.getValue() == null
                                        || phone.getText().trim().isEmpty()) {

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

                        // -----------------------------------------------
                        // GET SHARED PATIENT CONTROLLER
                        // -----------------------------------------------

                        PatientController controller = DoctorDashboard.getPatientController();

                        if (controller == null) {

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "Patient Controller Error");

                                alert.setHeaderText(null);

                                alert.setContentText(
                                                "Patient controller is not available.");

                                alert.showAndWait();

                                return;
                        }

                        // -----------------------------------------------
                        // SAVE TO FIRESTORE
                        // -----------------------------------------------

                        Patient savedPatient = controller.addPatient(
                                        name.getText().trim(),
                                        age.getText().trim(),
                                        gender.getValue(),
                                        phone.getText().trim(),
                                        lastVisit.getText().trim(),
                                        nextVisit.getText().trim());

                        // -----------------------------------------------
                        // SAVE FAILED
                        // -----------------------------------------------

                        if (savedPatient == null) {

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "Save Error");

                                alert.setHeaderText(
                                                "Unable to add patient");

                                alert.setContentText(
                                                "Patient could not be saved to Firebase. "
                                                                + "Please check your Firebase connection.");

                                alert.showAndWait();

                                return;
                        }

                        // -----------------------------------------------
                        // SUCCESS
                        // -----------------------------------------------

                        Alert alert = new Alert(
                                        Alert.AlertType.INFORMATION);

                        alert.setTitle(
                                        "Patient Added");

                        alert.setHeaderText(
                                        "Patient Added Successfully");

                        alert.setContentText(
                                        name.getText().trim()
                                                        + " has been added successfully.");

                        alert.showAndWait();

                        // -----------------------------------------------
                        // GO BACK TO PATIENT PAGE
                        // -----------------------------------------------

                        PatientsPage.show();
                });

                // =====================================================
                // ADD FORM CONTENT
                // =====================================================

                form.getChildren().addAll(
                                personalTitle,
                                grid,
                                visitTitle,
                                buttons);

                // =====================================================
                // CENTER
                // =====================================================

                StackPane center = new StackPane(form);

                center.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scroll = new ScrollPane(center);

                scroll.setFitToWidth(true);

                scroll.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                // =====================================================
                // ROOT
                // =====================================================

                root.setTop(header);

                BorderPane.setMargin(
                                header,
                                new Insets(0, 0, 20, 0));

                root.setCenter(scroll);

                // =====================================================
                // SAME DASHBOARD STAGE
                // =====================================================

                Scene scene = new Scene(root);

                DoctorDashboard.changeScene(scene);
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private static TextField textField(
                        String prompt) {

                TextField field = new TextField();

                field.setPromptText(prompt);

                field.setPrefHeight(40);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                return field;
        }

        // =========================================================
        // FIELD BOX
        // =========================================================

        private static VBox fieldBox(
                        String labelText,
                        Control control) {

                VBox box = new VBox(5);

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + Theme.TEXT + ";");

                box.getChildren().addAll(
                                label,
                                control);

                return box;
        }
}