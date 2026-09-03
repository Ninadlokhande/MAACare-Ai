package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.ImageUploadController;
import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadReportPage {

        // ============================================================
        // COLORS
        // ============================================================

        private static final String PURPLE = "#E84A87";
        private static final String LIGHT_PURPLE = "#FFE3EE";

        private static final String DARK_TEXT = "#3B2140";
        private static final String SECONDARY_TEXT = "#806A78";
        private static final String BORDER = "#F0D8E3";

        private static final String PAGE_BACKGROUND = "#FFF9FB";

        // ============================================================
        // SHOW
        // ============================================================

        public static void show() {

                Stage stage = DoctorDashboard.dashboardStage;

                if (stage == null) {
                        stage = new Stage();
                        DoctorDashboard.dashboardStage = stage;
                }

                final Stage dialogStage = stage;

                PatientReportController reportController = DoctorDashboard.getReportController();

                // ========================================================
                // ROOT
                // ========================================================

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " +
                                                PAGE_BACKGROUND + ";");

                // ========================================================
                // HEADER
                // ========================================================

                HBox header = new HBox();

                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(
                                new Insets(25, 35, 20, 35));

                VBox titleBox = new VBox(5);

                Label title = new Label(
                                "Upload Patient Report");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                title.setTextFill(
                                Color.web(DARK_TEXT));

                Label subtitle = new Label(
                                "Upload a medical report for a patient");

                subtitle.setFont(
                                Font.font("Arial", 14));

                subtitle.setTextFill(
                                Color.web(SECONDARY_TEXT));

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                header.getChildren().addAll(
                                titleBox,
                                spacer);

                // ========================================================
                // FORM CARD
                // ========================================================

                VBox card = new VBox(18);

                card.setMaxWidth(760);

                card.setPadding(
                                new Insets(30));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 18;");

                // ========================================================
                // PATIENT NAME
                // ========================================================

                Label patientLabel = createLabel("Patient Name");

                TextField patient = createTextField(
                                "Enter patient name");

                // ========================================================
                // REPORT NAME
                // ========================================================

                Label reportNameLabel = createLabel("Report Name");

                TextField reportName = createTextField(
                                "Enter report name");

                // ========================================================
                // REPORT TYPE
                // ========================================================

                Label reportTypeLabel = createLabel("Report Type");

                ComboBox<String> reportType = new ComboBox<>();

                reportType.getItems().addAll(
                                "Blood Test",
                                "Urine Test",
                                "Ultrasound",
                                "X-Ray",
                                "MRI",
                                "CT Scan",
                                "Prescription",
                                "Pregnancy Report",
                                "Other");

                reportType.setPromptText(
                                "Select report type");

                reportType.setPrefWidth(700);
                reportType.setPrefHeight(42);

                reportType.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;");

                // ========================================================
                // FILE
                // ========================================================

                Label fileLabel = createLabel("Report File");

                TextField fileField = createTextField(
                                "No file selected");

                fileField.setEditable(false);

                Button chooseButton = new Button("Choose File");

                chooseButton.setPrefHeight(42);

                chooseButton.setPadding(
                                new Insets(0, 18, 0, 18));

                chooseButton.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";" +
                                                "-fx-text-fill: " +
                                                PURPLE + ";" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 9;");

                HBox fileBox = new HBox(10);

                HBox.setHgrow(
                                fileField,
                                Priority.ALWAYS);

                fileBox.getChildren().addAll(
                                fileField,
                                chooseButton);

                final File[] selectedFile = new File[1];

                chooseButton.setOnAction(e -> {

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

                        File file = chooser.showOpenDialog(dialogStage);

                        if (file != null) {

                                selectedFile[0] = file;

                                fileField.setText(
                                                file.getAbsolutePath());
                        }
                });

                // ========================================================
                // STATUS
                // ========================================================

                Label statusLabel = createLabel("Status");

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "Pending",
                                "Reviewed");

                status.setValue("Pending");

                status.setPrefWidth(700);
                status.setPrefHeight(42);

                status.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;");

                // ========================================================
                // BUTTONS
                // ========================================================

                HBox buttonBox = new HBox(12);

                buttonBox.setAlignment(
                                Pos.CENTER_RIGHT);

                Button cancelButton = new Button("Cancel");

                cancelButton.setPrefHeight(44);

                cancelButton.setPadding(
                                new Insets(0, 22, 0, 22));

                cancelButton.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";" +
                                                "-fx-text-fill: " +
                                                PURPLE + ";" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 10;");

                cancelButton.setOnAction(
                                e -> PatientReportsPage.show());

                Button uploadButton = new Button("Upload Report");

                uploadButton.setPrefHeight(44);

                uploadButton.setPadding(
                                new Insets(0, 25, 0, 25));

                uploadButton.setStyle(
                                "-fx-background-color: " +
                                                PURPLE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;");

                buttonBox.getChildren().addAll(
                                cancelButton,
                                uploadButton);

                // ========================================================
                // UPLOAD ACTION
                // ========================================================

                uploadButton.setOnAction(e -> {

                        String patientName = patient.getText() == null
                                        ? ""
                                        : patient.getText().trim();

                        String reportNameText = reportName.getText() == null
                                        ? ""
                                        : reportName.getText().trim();

                        String selectedType = reportType.getValue();

                        String selectedStatus = status.getValue();

                        // ----------------------------------------------------
                        // VALIDATION
                        // ----------------------------------------------------

                        if (patientName.isEmpty()) {

                                showAlert(
                                                Alert.AlertType.WARNING,
                                                "Please enter patient name.");

                                patient.requestFocus();
                                return;
                        }

                        if (reportNameText.isEmpty()) {

                                showAlert(
                                                Alert.AlertType.WARNING,
                                                "Please enter report name.");

                                reportName.requestFocus();
                                return;
                        }

                        if (selectedType == null ||
                                        selectedType.trim().isEmpty()) {

                                showAlert(
                                                Alert.AlertType.WARNING,
                                                "Please select report type.");

                                return;
                        }

                        if (selectedFile[0] == null) {

                                showAlert(
                                                Alert.AlertType.WARNING,
                                                "Please select a report file.");

                                return;
                        }

                        // ----------------------------------------------------
                        // CLOUDINARY UPLOAD
                        // ----------------------------------------------------

                        uploadButton.setDisable(true);

                        uploadButton.setText(
                                        "Uploading...");

                        try {

                                ImageUploadController uploader = new ImageUploadController();

                                String uploadedFileUrl = uploader.imageUpload(
                                                selectedFile[0]);

                                if (uploadedFileUrl == null ||
                                                uploadedFileUrl.trim().isEmpty()) {

                                        uploadButton.setDisable(false);
                                        uploadButton.setText(
                                                        "Upload Report");

                                        showAlert(
                                                        Alert.AlertType.ERROR,
                                                        "File upload failed. " +
                                                                        "Please try again.");

                                        return;
                                }

                                // ------------------------------------------------
                                // CREATE REPORT
                                // ------------------------------------------------

                                PatientReport report = new PatientReport(

                                                reportNameText,

                                                patientName,

                                                selectedType,

                                                java.time.LocalDate.now()
                                                                .toString(),

                                                selectedStatus,

                                                "View",

                                                uploadedFileUrl.trim());

                                // ------------------------------------------------
                                // SAVE FIRESTORE
                                // ------------------------------------------------

                                reportController.addReport(
                                                report);

                                uploadButton.setDisable(false);
                                uploadButton.setText(
                                                "Upload Report");

                                showAlert(
                                                Alert.AlertType.INFORMATION,
                                                "Patient report uploaded successfully.");

                                PatientReportsPage.show();

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                uploadButton.setDisable(false);
                                uploadButton.setText(
                                                "Upload Report");

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Unable to upload report.\n\n"
                                                                + ex.getMessage());
                        }
                });

                // ========================================================
                // ADD FORM
                // ========================================================

                card.getChildren().addAll(

                                patientLabel,
                                patient,

                                reportNameLabel,
                                reportName,

                                reportTypeLabel,
                                reportType,

                                fileLabel,
                                fileBox,

                                statusLabel,
                                status,

                                buttonBox);

                // ========================================================
                // CENTER
                // ========================================================

                StackPane center = new StackPane(card);

                center.setPadding(
                                new Insets(10, 35, 35, 35));

                root.setTop(header);
                root.setLeft(DoctorDashboard.createSidebar("Reports"));
                root.setCenter(center);

                stage.setScene(
                                new Scene(root,
                                                DoctorTheme.WIDTH,
                                                DoctorTheme.HEIGHT));

                DoctorDashboard.setStandardWindowSize();

                stage.show();
        }

        private static Label createLabel(String text) {

                Label label = new Label(text);

                label.setFont(
                                Font.font("Arial", FontWeight.BOLD, 14));

                label.setTextFill(
                                Color.web(DARK_TEXT));

                return label;
        }

        private static TextField createTextField(String promptText) {

                TextField textField = new TextField();

                textField.setPromptText(promptText);
                textField.setPrefWidth(700);
                textField.setPrefHeight(42);
                textField.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;");

                return textField;
        }

        private static void showAlert(
                        Alert.AlertType type,
                        String message) {

                Alert alert = new Alert(type);

                alert.setTitle(
                                "MaaCare AI");

                alert.setHeaderText(null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }
}