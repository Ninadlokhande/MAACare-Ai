package com.sigma.view.doctorpages;

import com.sigma.controller.MedicalReportMotherController;
import com.sigma.controller.doctorController.ImageUploadController;
import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;
import com.sigma.model.MedicalReportMother;
import com.sigma.view.scenesettings;

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
import java.time.LocalDate;
import java.util.UUID;

public class UploadReportPage {

        private static final String PINK = "#E84A87";
        private static final String DARK_PINK = "#D93678";
        private static final String LIGHT_PINK = "#FFEAF3";

        private static final String PURPLE = "#9B4DCC";
        private static final String DARK_PURPLE = "#7B35A8";
        private static final String LIGHT_PURPLE = "#F3ECFF";

        private static final String DARK_TEXT = "#24234F";
        private static final String SECONDARY_TEXT = "#666680";
        private static final String BORDER = "#E7DCE8";

        private static final String PAGE_BACKGROUND = "#FFF8FC";

        private static final String PAGE_BACKGROUND_GRADIENT = "linear-gradient(to bottom right, "
                        + "#FFFFFF 0%, "
                        + "#FFF6FA 55%, "
                        + "#F3ECFF 100%)";

        private static final String BUTTON_GRADIENT = "linear-gradient(to right, #F54B87, #9B4DCC)";

        private static final String BUTTON_GRADIENT_HOVER = "linear-gradient(to right, #9B4DCC, #F54B87)";

        // =========================================================
        // SHOW
        // =========================================================

        public static void show() {

                Stage stage = DoctorDashboard.dashboardStage;

                if (stage == null) {

                        stage = new Stage();

                        DoctorDashboard.dashboardStage = stage;
                }

                final Stage dialogStage = stage;

                PatientReportController reportController = DoctorDashboard.getReportController();

                if (reportController == null) {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Patient report controller is not available.");

                        return;
                }

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND_GRADIENT
                                                + ";");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setPadding(
                                new Insets(
                                                25,
                                                35,
                                                20,
                                                35));

                header.setSpacing(20);

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
                                "Send a medical report directly to the selected mother");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                14));

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

                // =====================================================
                // CARD
                // =====================================================

                VBox card = new VBox(18);

                card.setMaxWidth(760);
                card.setPrefWidth(760);

                card.setPadding(
                                new Insets(30));

                card.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.96);"
                                                + "-fx-background-radius: 18;"
                                                + "-fx-border-color: "
                                                + BORDER
                                                + ";"
                                                + "-fx-border-radius: 18;"
                                                + "-fx-border-width: 1;");

                // =====================================================
                // MOTHER ID
                // =====================================================

                Label motherIdLabel = createLabel("Mother ID");

                TextField motherId = createTextField(
                                "Enter Mother ID");

                // =====================================================
                // PATIENT NAME
                // =====================================================

                Label patientLabel = createLabel("Patient Name");

                TextField patient = createTextField(
                                "Enter patient name");

                // =====================================================
                // REPORT NAME
                // =====================================================

                Label reportNameLabel = createLabel("Report Name");

                TextField reportName = createTextField(
                                "Enter report name");

                // =====================================================
                // REPORT TYPE
                // =====================================================

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

                reportType.setMaxWidth(
                                Double.MAX_VALUE);

                reportType.setPrefHeight(42);

                styleComboBox(reportType);

                // =====================================================
                // FILE
                // =====================================================

                Label fileLabel = createLabel("Report File");

                TextField fileField = createTextField(
                                "No file selected");

                fileField.setEditable(false);

                Button chooseButton = new Button("Choose File");

                chooseButton.setPrefHeight(42);
                chooseButton.setMinWidth(120);

                styleGradientButton(
                                chooseButton,
                                13,
                                "10px 18px");

                HBox fileBox = new HBox(10);

                fileBox.setAlignment(
                                Pos.CENTER_LEFT);

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

                        File file = chooser.showOpenDialog(
                                        dialogStage);

                        if (file != null) {

                                selectedFile[0] = file;

                                fileField.setText(
                                                file.getAbsolutePath());
                        }
                });

                // =====================================================
                // STATUS
                // =====================================================

                Label statusLabel = createLabel("Status");

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "Pending",
                                "Reviewed");

                status.setValue(
                                "Pending");

                status.setMaxWidth(
                                Double.MAX_VALUE);

                status.setPrefHeight(42);

                styleComboBox(status);

                // =====================================================
                // BUTTONS
                // =====================================================

                HBox buttonBox = new HBox(12);

                buttonBox.setAlignment(
                                Pos.CENTER_RIGHT);

                Button cancelButton = new Button("Cancel");

                cancelButton.setPrefHeight(44);
                cancelButton.setMinWidth(110);

                styleGradientButton(
                                cancelButton,
                                14,
                                "10px 22px");

                cancelButton.setOnAction(
                                e -> PatientReportsPage.show());

                Button uploadButton = new Button("Upload Report");

                uploadButton.setPrefHeight(44);
                uploadButton.setMinWidth(145);

                styleGradientButton(
                                uploadButton,
                                14,
                                "10px 25px");

                buttonBox.getChildren().addAll(
                                cancelButton,
                                uploadButton);

                // =====================================================
                // UPLOAD ACTION
                // =====================================================

                uploadButton.setOnAction(e -> {

                        String motherIdText = motherId.getText() == null
                                        ? ""
                                        : motherId.getText().trim();

                        String patientName = patient.getText() == null
                                        ? ""
                                        : patient.getText().trim();

                        String reportNameText = reportName.getText() == null
                                        ? ""
                                        : reportName.getText().trim();

                        String selectedType = reportType.getValue();

                        String selectedStatus = status.getValue();

                        // =================================================
                        // VALIDATION
                        // =================================================

                        if (motherIdText.isEmpty()) {

                                showAlert(
                                                Alert.AlertType.WARNING,
                                                "Please enter Mother ID.");

                                motherId.requestFocus();

                                return;
                        }

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

                        // =================================================
                        // UPLOADING
                        // =================================================

                        uploadButton.setDisable(true);

                        uploadButton.setText(
                                        "Uploading...");

                        try {

                                // =================================================
                                // 1. UPLOAD FILE ONLY ONCE
                                // =================================================

                                ImageUploadController uploader = new ImageUploadController();

                                String uploadedFileUrl = uploader.imageUpload(
                                                selectedFile[0]);

                                if (uploadedFileUrl == null ||
                                                uploadedFileUrl.trim().isEmpty()) {

                                        throw new Exception(
                                                        "Cloudinary did not return a file URL.");
                                }

                                uploadedFileUrl = uploadedFileUrl.trim();

                                // =================================================
                                // 2. CREATE UNIQUE REPORT ID
                                // =================================================

                                String reportId = UUID.randomUUID()
                                                .toString();

                                String today = LocalDate.now()
                                                .toString();

                                // =================================================
                                // 3. SAVE DOCTOR REPORT
                                // =================================================

                                PatientReport report = new PatientReport(

                                                reportId,

                                                motherIdText,

                                                reportNameText,

                                                patientName,

                                                selectedType,

                                                today,

                                                selectedStatus,

                                                "View",

                                                uploadedFileUrl);

                                reportController.addReport(
                                                report);

                                // =================================================
                                // 4. SAVE SAME REPORT TO MOTHER
                                // =================================================

                                MedicalReportMotherController motherController = new MedicalReportMotherController();

                                MedicalReportMother motherReport = new MedicalReportMother(

                                                reportId,

                                                motherIdText,

                                                reportNameText,

                                                today,

                                                "",

                                                "Doctor",

                                                uploadedFileUrl);

                                boolean saved = motherController
                                                .saveReport(
                                                                motherReport);

                                if (!saved) {

                                        throw new Exception(
                                                        "Report was uploaded, but Mother report could not be saved.");
                                }

                                // =================================================
                                // SUCCESS
                                // =================================================

                                uploadButton.setDisable(
                                                false);

                                uploadButton.setText(
                                                "Upload Report");

                                showAlert(
                                                Alert.AlertType.INFORMATION,
                                                "Report uploaded successfully.\n\n"
                                                                + "The report has been sent to the selected Mother.");

                                PatientReportsPage.show();

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                uploadButton.setDisable(
                                                false);

                                uploadButton.setText(
                                                "Upload Report");

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Unable to upload report.\n\n"
                                                                + ex.getMessage());
                        }
                });

                // =====================================================
                // CARD CONTENT
                // =====================================================

                card.getChildren().addAll(

                                motherIdLabel,
                                motherId,

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

                // =====================================================
                // CENTER
                // =====================================================

                StackPane center = new StackPane(card);

                center.setAlignment(
                                Pos.TOP_CENTER);

                center.setPadding(
                                new Insets(
                                                10,
                                                35,
                                                35,
                                                35));

                root.setTop(header);

                root.setLeft(
                                DoctorDashboard.createSidebar(
                                                "Reports"));

                root.setCenter(center);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                root,
                                scenesettings.rectanguler2d
                                                .getWidth(),
                                scenesettings.rectanguler2d
                                                .getHeight());

                DoctorDashboard.changeScene(
                                scene);

                Stage dashboardStage = DoctorDashboard.dashboardStage;

                if (dashboardStage != null) {

                        dashboardStage.setResizable(
                                        true);

                        dashboardStage.setMaximized(
                                        true);

                        dashboardStage.show();
                }
        }

        // =========================================================
        // LABEL
        // =========================================================

        private static Label createLabel(
                        String text) {

                Label label = new Label(text);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                label.setTextFill(
                                Color.web(DARK_TEXT));

                return label;
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private static TextField createTextField(
                        String promptText) {

                TextField textField = new TextField();

                textField.setPromptText(
                                promptText);

                textField.setPrefWidth(700);

                textField.setPrefHeight(42);

                textField.setMaxWidth(
                                Double.MAX_VALUE);

                textField.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + BORDER
                                                + ";"
                                                + "-fx-border-radius: 9;"
                                                + "-fx-background-radius: 9;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 14px;"
                                                + "-fx-text-fill: "
                                                + DARK_TEXT
                                                + ";"
                                                + "-fx-prompt-text-fill: "
                                                + SECONDARY_TEXT
                                                + ";"
                                                + "-fx-padding: 0 12;");

                return textField;
        }

        // =========================================================
        // COMBOBOX
        // =========================================================

        private static void styleComboBox(
                        ComboBox<String> comboBox) {

                comboBox.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + BORDER
                                                + ";"
                                                + "-fx-border-radius: 9;"
                                                + "-fx-background-radius: 9;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 14px;"
                                                + "-fx-text-fill: "
                                                + DARK_TEXT
                                                + ";"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // BUTTON
        // =========================================================

        private static void styleGradientButton(
                        Button button,
                        int fontSize,
                        String padding) {

                button.setStyle(
                                "-fx-background-color: "
                                                + BUTTON_GRADIENT
                                                + ";"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: "
                                                + fontSize
                                                + "px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 20;"
                                                + "-fx-border-radius: 20;"
                                                + "-fx-border-color: transparent;"
                                                + "-fx-padding: "
                                                + padding
                                                + ";"
                                                + "-fx-cursor: hand;");

                button.setOnMouseEntered(e -> {

                        if (!button.isDisabled()) {

                                button.setStyle(
                                                "-fx-background-color: "
                                                                + BUTTON_GRADIENT_HOVER
                                                                + ";"
                                                                + "-fx-text-fill: white;"
                                                                + "-fx-font-family: Arial;"
                                                                + "-fx-font-size: "
                                                                + fontSize
                                                                + "px;"
                                                                + "-fx-font-weight: bold;"
                                                                + "-fx-background-radius: 20;"
                                                                + "-fx-border-radius: 20;"
                                                                + "-fx-border-color: transparent;"
                                                                + "-fx-padding: "
                                                                + padding
                                                                + ";"
                                                                + "-fx-cursor: hand;");
                        }
                });

                button.setOnMouseExited(e -> {

                        if (!button.isDisabled()) {

                                button.setStyle(
                                                "-fx-background-color: "
                                                                + BUTTON_GRADIENT
                                                                + ";"
                                                                + "-fx-text-fill: white;"
                                                                + "-fx-font-family: Arial;"
                                                                + "-fx-font-size: "
                                                                + fontSize
                                                                + "px;"
                                                                + "-fx-font-weight: bold;"
                                                                + "-fx-background-radius: 20;"
                                                                + "-fx-border-radius: 20;"
                                                                + "-fx-border-color: transparent;"
                                                                + "-fx-padding: "
                                                                + padding
                                                                + ";"
                                                                + "-fx-cursor: hand;");
                        }
                });
        }

        // =========================================================
        // ALERT
        // =========================================================

        private static void showAlert(
                        Alert.AlertType type,
                        String message) {

                Alert alert = new Alert(type);

                alert.setTitle(
                                "MaaCare AI");

                alert.setHeaderText(null);

                alert.setContentText(
                                message);

                DialogPane pane = alert.getDialogPane();

                pane.setStyle(
                                "-fx-background-color: "
                                                + PAGE_BACKGROUND
                                                + ";");

                alert.setOnShown(e -> {

                        Button okButton = (Button) alert
                                        .getDialogPane()
                                        .lookupButton(
                                                        ButtonType.OK);

                        if (okButton != null) {

                                styleGradientButton(
                                                okButton,
                                                13,
                                                "9px 18px");
                        }
                });

                alert.showAndWait();
        }
}