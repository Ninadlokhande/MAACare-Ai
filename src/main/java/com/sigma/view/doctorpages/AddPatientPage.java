
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientController;
import com.sigma.model.DoctorModel.Patient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddPatientPage {

        // =========================================================
        // SHOW PAGE
        // =========================================================

        public static void show() {

                BorderPane root = new BorderPane();

                // Apply common theme
                DoctorTheme.applyBackground(root);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = DoctorTheme.pageHeader(
                                "Add Patient",
                                "Enter patient information to add a new patient.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                header.getChildren().addAll(
                                heading,
                                spacer);

                // =====================================================
                // FORM CARD
                // =====================================================

                VBox form = DoctorTheme.card();

                form.setMaxWidth(850);

                form.setPadding(
                                new Insets(28));

                form.setSpacing(18);

                // =====================================================
                // PERSONAL INFORMATION
                // =====================================================

                Label personalTitle = new Label(
                                "Personal Information");

                personalTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DoctorTheme.TEXT + ";");

                // =====================================================
                // PATIENT FIELDS
                // =====================================================

                TextField name = createTextField(
                                "Full Name");

                TextField age = createTextField(
                                "Age");

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "Female",
                                "Male",
                                "Other");

                gender.setPromptText(
                                "Select Gender");

                gender.setPrefHeight(40);

                gender.setMaxWidth(
                                Double.MAX_VALUE);

                TextField phone = createTextField(
                                "Contact Number");

                TextField address = createTextField(
                                "Address");

                // =====================================================
                // ROW 1
                // =====================================================

                HBox row1 = new HBox(20);

                VBox nameBox = fieldBox(
                                "Full Name *",
                                name);

                VBox ageBox = fieldBox(
                                "Age *",
                                age);

                HBox.setHgrow(
                                nameBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                ageBox,
                                Priority.ALWAYS);

                row1.getChildren().addAll(
                                nameBox,
                                ageBox);

                // =====================================================
                // ROW 2
                // =====================================================

                HBox row2 = new HBox(20);

                VBox genderBox = fieldBox(
                                "Gender *",
                                gender);

                VBox phoneBox = fieldBox(
                                "Contact Number *",
                                phone);

                HBox.setHgrow(
                                genderBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                phoneBox,
                                Priority.ALWAYS);

                row2.getChildren().addAll(
                                genderBox,
                                phoneBox);

                // =====================================================
                // ROW 3 - ADDRESS
                // =====================================================

                HBox row3 = new HBox();

                VBox addressBox = fieldBox(
                                "Address",
                                address);

                HBox.setHgrow(
                                addressBox,
                                Priority.ALWAYS);

                row3.getChildren().add(
                                addressBox);

                // =====================================================
                // VISIT INFORMATION
                // =====================================================

                Label visitTitle = new Label(
                                "Visit Information");

                visitTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DoctorTheme.TEXT + ";");

                // =====================================================
                // VISIT FIELDS
                // =====================================================

                TextField lastVisit = createTextField(
                                "Last Visit");

                TextField nextVisit = createTextField(
                                "Next Visit");

                // =====================================================
                // VISIT ROW
                // =====================================================

                HBox visitRow = new HBox(20);

                VBox lastVisitBox = fieldBox(
                                "Last Visit",
                                lastVisit);

                VBox nextVisitBox = fieldBox(
                                "Next Visit",
                                nextVisit);

                HBox.setHgrow(
                                lastVisitBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                nextVisitBox,
                                Priority.ALWAYS);

                visitRow.getChildren().addAll(
                                lastVisitBox,
                                nextVisitBox);

                // =====================================================
                // BUTTONS
                // =====================================================

                Button cancel = new Button(
                                "Cancel");

                cancel.setPrefHeight(40);

                cancel.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + DoctorTheme.SECONDARY_TEXT + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                Button save = DoctorTheme.primaryButton(
                                "Save Patient");

                HBox buttons = new HBox(15);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                buttons.getChildren().addAll(
                                cancel,
                                save);

                // =====================================================
                // CANCEL BUTTON
                // =====================================================

                cancel.setOnAction(
                                e -> PatientsPage.show());

                // =====================================================
                // SAVE PATIENT
                // =====================================================

                save.setOnAction(e -> {

                        // -------------------------------------------------
                        // VALIDATION
                        // -------------------------------------------------

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

                        // -------------------------------------------------
                        // AGE VALIDATION
                        // -------------------------------------------------

                        try {

                                int enteredAge = Integer.parseInt(
                                                age.getText().trim());

                                if (enteredAge < 0
                                                || enteredAge > 120) {

                                        Alert alert = new Alert(
                                                        Alert.AlertType.WARNING);

                                        alert.setTitle(
                                                        "Invalid Age");

                                        alert.setHeaderText(null);

                                        alert.setContentText(
                                                        "Please enter a valid age between 0 and 120.");

                                        alert.showAndWait();

                                        return;
                                }

                        } catch (NumberFormatException ex) {

                                Alert alert = new Alert(
                                                Alert.AlertType.WARNING);

                                alert.setTitle(
                                                "Invalid Age");

                                alert.setHeaderText(null);

                                alert.setContentText(
                                                "Age must contain numbers only.");

                                alert.showAndWait();

                                return;
                        }

                        // -------------------------------------------------
                        // GET PATIENT CONTROLLER
                        // -------------------------------------------------

                        PatientController controller = DoctorDashboard.getPatientController();

                        if (controller == null) {

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "Controller Error");

                                alert.setHeaderText(
                                                "Patient Controller Error");

                                alert.setContentText(
                                                "Patient controller is not available.");

                                alert.showAndWait();

                                return;
                        }

                        // -------------------------------------------------
                        // SAVE TO FIRESTORE
                        // -------------------------------------------------

                        try {

                                Patient savedPatient = controller.addPatient(
                                                name.getText().trim(),
                                                age.getText().trim(),
                                                gender.getValue(),
                                                phone.getText().trim(),
                                                lastVisit.getText().trim(),
                                                nextVisit.getText().trim());

                                // -------------------------------------------------
                                // CHECK RESULT
                                // -------------------------------------------------

                                if (savedPatient == null) {

                                        Alert alert = new Alert(
                                                        Alert.AlertType.ERROR);

                                        alert.setTitle(
                                                        "Save Error");

                                        alert.setHeaderText(
                                                        "Unable to add patient");

                                        alert.setContentText(
                                                        "Patient could not be saved.");

                                        alert.showAndWait();

                                        return;
                                }

                                // -------------------------------------------------
                                // SUCCESS
                                // -------------------------------------------------

                                Alert alert = new Alert(
                                                Alert.AlertType.INFORMATION);

                                alert.setTitle(
                                                "Patient Added");

                                alert.setHeaderText(
                                                "Patient added successfully");

                                alert.setContentText(
                                                name.getText().trim()
                                                                + " has been added successfully.");

                                alert.showAndWait();

                                // -------------------------------------------------
                                // BACK TO PATIENT PAGE
                                // -------------------------------------------------

                                PatientsPage.show();

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "Save Error");

                                alert.setHeaderText(
                                                "Unable to add patient");

                                alert.setContentText(
                                                "An error occurred while saving the patient:\n"
                                                                + ex.getMessage());

                                alert.showAndWait();
                        }
                });

                // =====================================================
                // ADD FORM CONTENT
                // =====================================================

                form.getChildren().addAll(
                                personalTitle,
                                row1,
                                row2,
                                row3,
                                visitTitle,
                                visitRow,
                                buttons);

                // =====================================================
                // CENTER
                // =====================================================

                StackPane center = new StackPane();

                center.setAlignment(
                                Pos.CENTER);

                center.getChildren().add(
                                form);

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scroll = new ScrollPane();

                scroll.setContent(
                                center);

                scroll.setFitToWidth(
                                true);

                scroll.setFitToHeight(
                                true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scroll.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                // =====================================================
                // ROOT
                // =====================================================

                root.setTop(
                                header);

                root.setLeft(DoctorDashboard.createSidebar("Patients"));

                BorderPane.setMargin(
                                header,
                                new Insets(0, 0, 20, 0));

                root.setCenter(
                                scroll);

                Scene scene = new Scene(root);
                DoctorDashboard.getInstance();
                DoctorDashboard.changeScene(scene);
        }

        // =========================================================
        // CREATE TEXT FIELD
        // =========================================================

        private static TextField createTextField(String prompt) {
                TextField field = new TextField();

                field.setPromptText(
                                prompt);

                field.setPrefHeight(
                                40);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                return field;
        }

        // =========================================================
        // FIELD BOX
        // =========================================================

        private static VBox fieldBox(
                        String labelText,
                        Node field) {

                Label label = new Label(
                                labelText);

                label.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DoctorTheme.TEXT + ";");

                VBox box = new VBox(7);

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.getChildren().addAll(
                                label,
                                field);

                return box;
        }
}
