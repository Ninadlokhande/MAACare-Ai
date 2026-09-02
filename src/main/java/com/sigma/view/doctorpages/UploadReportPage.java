package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.ImageUploadController;
import com.sigma.controller.doctorController.PatientReportController;
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

        // =====================================================
        // SHOW PAGE
        // =====================================================

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
                                "Upload Patient Report",
                                "Upload a new medical report for a patient.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                back.setOnAction(e -> PatientReportsPage.show());

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // =====================================================
                // FORM
                // =====================================================

                VBox form = Theme.card();

                form.setMaxWidth(700);

                form.setPadding(new Insets(30));

                form.setSpacing(15);

                // =====================================================
                // PATIENT NAME
                // =====================================================

                TextField patientName = new TextField();

                patientName.setPromptText(
                                "Enter patient name");

                patientName.setPrefHeight(40);

                patientName.setMaxWidth(
                                Double.MAX_VALUE);

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
                // SOURCE / LABORATORY
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

                Button chooseFile = new Button(
                                "Choose File");

                chooseFile.setPrefHeight(40);

                HBox fileBox = new HBox(10);

                HBox.setHgrow(
                                filePath,
                                Priority.ALWAYS);

                fileBox.getChildren().addAll(
                                filePath,
                                chooseFile);

                // =====================================================
                // STORE SELECTED FILE
                // =====================================================

                final File[] selectedFile = new File[1];

                // =====================================================
                // CHOOSE FILE
                // =====================================================

                chooseFile.setOnAction(e -> {

                        FileChooser chooser = new FileChooser();

                        chooser.setTitle(
                                        "Select Patient Report");

                        chooser.getExtensionFilters().addAll(

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

                        File file = chooser.showOpenDialog(null);

                        if (file != null) {

                                selectedFile[0] = file;

                                filePath.setText(
                                                file.getAbsolutePath());

                                System.out.println(
                                                "[REPORT] Selected file: "
                                                                + file.getAbsolutePath());
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

                status.setValue("Normal");

                status.setMaxWidth(
                                Double.MAX_VALUE);

                status.setPrefHeight(40);

                // =====================================================
                // BUTTONS
                // =====================================================

                Button cancel = new Button(
                                "Cancel");

                cancel.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: "
                                                + Theme.SECONDARY_TEXT + ";" +
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

                cancel.setOnAction(
                                e -> PatientReportsPage.show());

                // =====================================================
                // UPLOAD REPORT
                // =====================================================

                upload.setOnAction(e -> {

                        // =================================================
                        // GET PATIENT NAME
                        // =================================================

                        String enteredPatientName = patientName.getText().trim();

                        // =================================================
                        // VALIDATION
                        // =================================================

                        if (enteredPatientName.isEmpty()
                                        || reportType.getValue() == null
                                        || reportName.getText()
                                                        .trim()
                                                        .isEmpty()
                                        || selectedFile[0] == null) {

                                Alert alert = new Alert(
                                                Alert.AlertType.WARNING);

                                alert.setTitle(
                                                "Incomplete Information");

                                alert.setHeaderText(null);

                                alert.setContentText(
                                                "Please enter patient name, "
                                                                + "select report type, "
                                                                + "enter report name and "
                                                                + "select report file.");

                                alert.showAndWait();

                                return;
                        }

                        // =================================================
                        // GET SELECTED FILE
                        // =================================================

                        File file = selectedFile[0];

                        if (!file.exists() || !file.isFile()) {

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "File Error");

                                alert.setHeaderText(
                                                "Selected file not found");

                                alert.setContentText(
                                                "Please select the report file again.");

                                alert.showAndWait();

                                return;
                        }

                        // =================================================
                        // DISABLE BUTTON DURING UPLOAD
                        // =================================================

                        upload.setDisable(true);

                        chooseFile.setDisable(true);

                        upload.setText(
                                        "Uploading...");

                        // =================================================
                        // UPLOAD TO CLOUDINARY
                        // =================================================

                        String uploadedFileUrl;

                        try {

                                ImageUploadController imageUploadController = new ImageUploadController();

                                uploadedFileUrl = imageUploadController.imageUpload(file);

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                upload.setDisable(false);

                                chooseFile.setDisable(false);

                                upload.setText(
                                                "Upload Report");

                                Alert error = new Alert(
                                                Alert.AlertType.ERROR);

                                error.setTitle(
                                                "Cloudinary Upload Error");

                                error.setHeaderText(
                                                "Unable to upload report file");

                                error.setContentText(
                                                "The report file could not be uploaded "
                                                                + "to Cloudinary.\n\n"
                                                                + "Please check your Cloudinary configuration.");

                                error.showAndWait();

                                return;
                        }

                        // =================================================
                        // CHECK CLOUDINARY URL
                        // =================================================

                        if (uploadedFileUrl == null
                                        || uploadedFileUrl.trim().isEmpty()
                                        || uploadedFileUrl.equalsIgnoreCase("null")) {

                                upload.setDisable(false);

                                chooseFile.setDisable(false);

                                upload.setText(
                                                "Upload Report");

                                Alert error = new Alert(
                                                Alert.AlertType.ERROR);

                                error.setTitle(
                                                "Upload Failed");

                                error.setHeaderText(
                                                "Cloudinary did not return a file URL");

                                error.setContentText(
                                                "The report was not saved because "
                                                                + "the uploaded file URL is empty.");

                                error.showAndWait();

                                return;
                        }

                        System.out.println(
                                        "[REPORT UPLOAD] Cloudinary URL:");

                        System.out.println(
                                        uploadedFileUrl);

                        // =================================================
                        // DATE
                        // =================================================

                        String date = LocalDateTime.now()
                                        .format(
                                                        DateTimeFormatter.ofPattern(
                                                                        "dd MMM yyyy hh:mm a"));

                        // =================================================
                        // CREATE REPORT OBJECT
                        // =================================================

                        PatientReport report = new PatientReport(

                                        // Report Name
                                        reportName.getText()
                                                        .trim(),

                                        // Patient Name
                                        enteredPatientName,

                                        // Report Type
                                        reportType.getValue(),

                                        // Date
                                        date,

                                        // Status
                                        status.getValue(),

                                        // Action
                                        "View",

                                        // Cloudinary URL
                                        uploadedFileUrl.trim());

                        // =================================================
                        // SAVE REPORT TO FIRESTORE
                        // =================================================

                        try {

                                PatientReportController reportController = DoctorDashboard.getReportController();

                                reportController.addReport(
                                                report);

                                System.out.println(
                                                "[UPLOAD REPORT] Report saved to Firestore.");

                                System.out.println(
                                                "[UPLOAD REPORT] Patient: "
                                                                + enteredPatientName);

                                System.out.println(
                                                "[UPLOAD REPORT] URL saved: "
                                                                + report.getReportUrl());

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                upload.setDisable(false);

                                chooseFile.setDisable(false);

                                upload.setText(
                                                "Upload Report");

                                Alert error = new Alert(
                                                Alert.AlertType.ERROR);

                                error.setTitle(
                                                "Firestore Error");

                                error.setHeaderText(
                                                "Unable to save report");

                                error.setContentText(
                                                "The file was uploaded to Cloudinary, "
                                                                + "but the report information could not "
                                                                + "be saved to Firestore.\n\n"
                                                                + "Please check your Firebase connection.");

                                error.showAndWait();

                                return;
                        }

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
                                        "Report for "
                                                        + enteredPatientName
                                                        + " has been uploaded successfully.");

                        alert.showAndWait();

                        // =================================================
                        // GO TO REPORTS PAGE
                        // =================================================

                        PatientReportsPage.show();
                });

                // =====================================================
                // FORM FIELDS
                // =====================================================

                form.getChildren().addAll(

                                fieldBox(
                                                "Patient Name",
                                                patientName),

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

                // SAME DASHBOARD STAGE
                DoctorDashboard.changeScene(scene);
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
                                                "-fx-text-fill: "
                                                + Theme.TEXT + ";");

                box.getChildren().addAll(
                                label,
                                control);

                return box;
        }
}