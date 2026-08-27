package com.sigma.view.doctorpages;

import com.sigma.model.DoctorModel.PatientReport;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UploadReportPage {

        public static void show() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setPadding(
                                new Insets(
                                                28,
                                                35,
                                                28,
                                                35));

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Upload Patient Report",
                                "Upload a new medical report for a patient.");

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
                // FORM
                // =====================================================

                VBox form = Theme.card();

                form.setMaxWidth(700);

                form.setPadding(
                                new Insets(30));

                form.setSpacing(15);

                // =====================================================
                // PATIENT
                // =====================================================

                ComboBox<String> patient = new ComboBox<>();

                patient.getItems().addAll(
                                "Priya Sharma",
                                "Neha Kulkarni",
                                "Sneha Patil",
                                "Ayesha Khan",
                                "Ritika Singh",
                                "Pooja Iyer");

                patient.setPromptText(
                                "Select patient");

                patient.setMaxWidth(
                                Double.MAX_VALUE);

                patient.setPrefHeight(40);

                // =====================================================
                // REPORT TYPE
                // =====================================================

                ComboBox<String> reportType = new ComboBox<>();

                reportType.getItems().addAll(
                                "Blood Test",
                                "Ultrasound",
                                "Urine Test",
                                "Thyroid Profile",
                                "Vitamin D Test",
                                "Other");

                reportType.setPromptText(
                                "Select report type");

                reportType.setMaxWidth(
                                Double.MAX_VALUE);

                reportType.setPrefHeight(40);

                // =====================================================
                // REPORT NAME
                // =====================================================

                TextField reportName = new TextField();

                reportName.setPromptText(
                                "Enter report name");

                reportName.setPrefHeight(40);

                // =====================================================
                // SOURCE
                // =====================================================

                TextField source = new TextField();

                source.setPromptText(
                                "e.g. ThyroCare Lab");

                source.setPrefHeight(40);

                // =====================================================
                // FILE
                // =====================================================

                TextField filePath = new TextField();

                filePath.setPromptText(
                                "Select report file");

                filePath.setEditable(false);

                filePath.setPrefHeight(40);

                Button chooseFile = new Button("Choose File");

                chooseFile.setPrefHeight(40);

                HBox fileBox = new HBox(10);

                HBox.setHgrow(
                                filePath,
                                Priority.ALWAYS);

                fileBox.getChildren().addAll(
                                filePath,
                                chooseFile);

                // =====================================================
                // CHOOSE FILE
                // =====================================================

                chooseFile.setOnAction(e -> {

                        FileChooser chooser = new FileChooser();

                        chooser.setTitle(
                                        "Select Patient Report");

                        chooser.getExtensionFilters()
                                        .addAll(

                                                        new FileChooser.ExtensionFilter(
                                                                        "PDF Files",
                                                                        "*.pdf"),

                                                        new FileChooser.ExtensionFilter(
                                                                        "Image Files",
                                                                        "*.png",
                                                                        "*.jpg",
                                                                        "*.jpeg"),

                                                        new FileChooser.ExtensionFilter(
                                                                        "All Files",
                                                                        "*.*"));

                        File file = chooser.showOpenDialog(
                                        null);

                        if (file != null) {

                                filePath.setText(
                                                file.getAbsolutePath());
                        }
                });

                // =====================================================
                // STATUS
                // =====================================================

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "Normal",
                                "Abnormal",
                                "Low",
                                "High",
                                "Pending");

                status.setValue(
                                "Normal");

                status.setMaxWidth(
                                Double.MAX_VALUE);

                status.setPrefHeight(40);

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

                Button upload = Theme.primaryButton(
                                "Upload Report");

                HBox buttons = new HBox(15);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                buttons.getChildren().addAll(
                                cancel,
                                upload);

                // =====================================================
                // CANCEL
                // =====================================================

                cancel.setOnAction(e -> {

                        PatientReportsPage.show();
                });

                // =====================================================
                // UPLOAD
                // =====================================================

                upload.setOnAction(e -> {

                        if (patient.getValue() == null
                                        || reportType.getValue() == null
                                        || reportName.getText()
                                                        .trim()
                                                        .isEmpty()
                                        || filePath.getText()
                                                        .trim()
                                                        .isEmpty()) {

                                Alert alert = new Alert(
                                                Alert.AlertType.WARNING);

                                alert.setTitle(
                                                "Incomplete Information");

                                alert.setHeaderText(null);

                                alert.setContentText(
                                                "Please select patient, " +
                                                                "report type, report name " +
                                                                "and report file.");

                                alert.showAndWait();

                                return;
                        }

                        // =================================================
                        // DATE
                        // =================================================

                        String date = LocalDateTime.now()
                                        .format(
                                                        DateTimeFormatter.ofPattern(
                                                                        "dd MMM yyyy hh:mm a"));

                        // =================================================
                        // CREATE REPORT
                        // =================================================

                        PatientReport report = new PatientReport(

                                        reportName.getText()
                                                        .trim(),

                                        patient.getValue(),

                                        reportType.getValue(),

                                        date,

                                        status.getValue(),

                                        filePath.getText());

                        // =================================================
                        // ADD REPORT
                        // =================================================

                        PatientReportsPage.addReport(
                                        report);

                        // =================================================
                        // SUCCESS
                        // =================================================

                        Alert alert = new Alert(
                                        Alert.AlertType.INFORMATION);

                        alert.setTitle(
                                        "Report Uploaded");

                        alert.setHeaderText(
                                        "Report Uploaded Successfully");

                        alert.setContentText(
                                        "The report has been added " +
                                                        "to the patient reports list.");

                        alert.showAndWait();

                        PatientReportsPage.show();
                });

                // =====================================================
                // FORM
                // =====================================================

                form.getChildren().addAll(

                                fieldBox(
                                                "Patient",
                                                patient),

                                fieldBox(
                                                "Report Type",
                                                reportType),

                                fieldBox(
                                                "Report Name",
                                                reportName),

                                fieldBox(
                                                "Source / Laboratory",
                                                source),

                                fieldBox(
                                                "Report File",
                                                fileBox),

                                fieldBox(
                                                "Status",
                                                status),

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
                                new Insets(
                                                0,
                                                0,
                                                20,
                                                0));

                root.setCenter(scroll);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(root);

                DoctorDashboard.changeScene(
                                scene);
        }

        // =====================================================
        // FIELD BOX
        // =====================================================

        private static VBox fieldBox(
                        String labelText,
                        javafx.scene.Node control) {

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