package com.sigma.view.doctorpages;

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

        header.setAlignment(
                Pos.CENTER_LEFT);

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
                        "-fx-text-fill: " +
                        Theme.TEXT + ";");

        // =====================================================
        // NAME
        // =====================================================

        TextField name = textField("Full Name");

        // =====================================================
        // AGE
        // =====================================================

        TextField age = textField("Age");

        // =====================================================
        // GENDER
        // =====================================================

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

        // =====================================================
        // CONTACT
        // =====================================================

        TextField phone = textField("Contact Number");

        // =====================================================
        // ADDRESS
        // =====================================================

        TextField address = textField("Address");

        // =====================================================
        // VISIT INFORMATION
        // =====================================================

        Label visitTitle = new Label("Visit Information");

        visitTitle.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        Theme.TEXT + ";");

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

        grid.getColumnConstraints()
                .addAll(col1, col2);

        grid.add(
                fieldBox("Full Name", name),
                0, 0);

        grid.add(
                fieldBox("Age", age),
                1, 0);

        grid.add(
                fieldBox("Gender", gender),
                0, 1);

        grid.add(
                fieldBox("Contact Number", phone),
                1, 1);

        grid.add(
                fieldBox("Address", address),
                0, 2);

        grid.add(
                fieldBox("Last Visit", lastVisit),
                1, 2);

        grid.add(
                fieldBox("Next Visit", nextVisit),
                0, 3);

        // =====================================================
        // BUTTONS
        // =====================================================

        Button cancel = new Button("Cancel");

        cancel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: " +
                        Theme.SECONDARY_TEXT + ";" +
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
        // CANCEL
        // =====================================================

        cancel.setOnAction(e -> {

            PatientsPage.show();

        });

        // =====================================================
        // SAVE
        // =====================================================

        save.setOnAction(e -> {

            // -----------------------------------------
            // VALIDATION
            // -----------------------------------------

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

            // -----------------------------------------
            // AGE + GENDER
            // -----------------------------------------

            String ageGender = age.getText().trim()
                    + " Y / "
                    + gender.getValue();

            // -----------------------------------------
            // CREATE PATIENT
            // -----------------------------------------

            Patient patient = new Patient(

                    name.getText().trim(),

                    ageGender,

                    phone.getText().trim(),

                    lastVisit.getText().trim(),

                    nextVisit.getText().trim(),

                    "");

            // -----------------------------------------
            // ADD TO PATIENT LIST
            // -----------------------------------------

            PatientsPage.addPatient(
                    patient);

            // -----------------------------------------
            // SUCCESS MESSAGE
            // -----------------------------------------

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION);

            alert.setTitle(
                    "Patient Added");

            alert.setHeaderText(
                    "Patient Added Successfully");

            alert.setContentText(
                    name.getText().trim()
                            + " has been added to your patient list.");

            alert.showAndWait();

            // -----------------------------------------
            // BACK TO PATIENT PAGE
            // -----------------------------------------

            PatientsPage.show();
        });

        // =====================================================
        // FORM CONTENT
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

        ScrollPane scroll = new ScrollPane(center);

        scroll.setFitToWidth(true);

        scroll.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;");

        root.setTop(header);

        BorderPane.setMargin(
                header,
                new Insets(0, 0, 20, 0));

        root.setCenter(scroll);

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(root);

        DoctorDashboard.changeScene(
                scene);
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

        label.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        Theme.TEXT + ";");

        box.getChildren().addAll(
                label,
                control);

        return box;
    }
}