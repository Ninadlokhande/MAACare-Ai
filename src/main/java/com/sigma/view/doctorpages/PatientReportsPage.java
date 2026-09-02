package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

/**
 * Doctor - Patient Reports Page
 *
 * Features:
 * - View patient reports
 * - Search reports
 * - Filter by status
 * - Open uploaded report
 * - Delete report
 * - Functional refresh button
 * - No Clear button
 * - Same dashboard stage
 */
public class PatientReportsPage {

        // =========================================================
        // CONTROLLER
        // =========================================================

        private static PatientReportController controller;

        // =========================================================
        // TABLE
        // =========================================================

        private static TableView<PatientReport> table;

        // =========================================================
        // FILTERS
        // =========================================================

        private static TextField searchField;
        private static ComboBox<String> statusCombo;

        // =========================================================
        // AUTO REFRESH
        // =========================================================

        private static Timeline realtimeRefreshTimeline;

        // =========================================================
        // SHOW
        // =========================================================

        public static void show() {

                try {

                        controller = getController();

                        if (controller == null) {

                                showError(
                                                "Controller Error",
                                                "Patient Report Controller is not available.");

                                return;
                        }

                        // -----------------------------------------------------
                        // LOAD FIRESTORE DATA
                        // -----------------------------------------------------

                        controller.refreshReports();

                        // -----------------------------------------------------
                        // ROOT
                        // -----------------------------------------------------

                        BorderPane root = new BorderPane();

                        Theme.applyBackground(root);

                        root.setPadding(
                                        new Insets(25, 35, 25, 35));

                        // =====================================================
                        // HEADER
                        // =====================================================

                        HBox header = new HBox();

                        header.setAlignment(Pos.CENTER_LEFT);

                        VBox heading = Theme.pageHeader(
                                        "Patient Reports",
                                        "View and manage patient medical reports.");

                        Region spacer = new Region();

                        HBox.setHgrow(
                                        spacer,
                                        Priority.ALWAYS);

                        // =====================================================
                        // REFRESH BUTTON
                        // =====================================================

                        Button refreshButton = new Button("↻ Refresh");

                        refreshButton.setPrefHeight(38);

                        refreshButton.setPrefWidth(105);

                        refreshButton.setStyle(
                                        "-fx-background-color: #F3ECFF;" +
                                                        "-fx-text-fill: #9B4DCC;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-border-color: #E7DCE8;" +
                                                        "-fx-border-radius: 10;" +
                                                        "-fx-cursor: hand;");

                        refreshButton.setOnAction(e -> {

                                refreshReportsNow(refreshButton);

                        });

                        // =====================================================
                        // UPLOAD BUTTON
                        // =====================================================

                        Button uploadButton = Theme.primaryButton("+ Upload Report");

                        uploadButton.setPrefHeight(38);

                        uploadButton.setOnAction(
                                        e -> UploadReportPage.show());

                        // =====================================================
                        // BACK BUTTON
                        // =====================================================

                        Button back = Theme.backButton();

                        back.setOnAction(e -> {

                                stopRealtimeRefresh();

                                DoctorDashboard.showDashboard();

                        });

                        // =====================================================
                        // HEADER CHILDREN
                        // =====================================================

                        header.getChildren().addAll(
                                        heading,
                                        spacer,
                                        refreshButton,
                                        uploadButton,
                                        back);

                        HBox.setMargin(
                                        refreshButton,
                                        new Insets(0, 8, 0, 0));

                        HBox.setMargin(
                                        uploadButton,
                                        new Insets(0, 10, 0, 0));

                        // =====================================================
                        // FILTER BAR
                        // =====================================================

                        HBox filterBar = new HBox(12);

                        filterBar.setAlignment(
                                        Pos.CENTER_LEFT);

                        filterBar.setPadding(
                                        new Insets(15));

                        filterBar.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-background-radius: 14;" +
                                                        "-fx-border-color: #E7DCE8;" +
                                                        "-fx-border-radius: 14;");

                        // =====================================================
                        // SEARCH
                        // =====================================================

                        searchField = new TextField();

                        searchField.setPromptText(
                                        "🔍 Search report, patient or type...");

                        searchField.setPrefHeight(40);

                        searchField.setPrefWidth(330);

                        searchField.setStyle(
                                        "-fx-background-color: #FAF8FF;" +
                                                        "-fx-border-color: #E7DCE8;" +
                                                        "-fx-border-radius: 9;" +
                                                        "-fx-background-radius: 9;" +
                                                        "-fx-padding: 0 12;");

                        // =====================================================
                        // STATUS
                        // =====================================================

                        statusCombo = new ComboBox<>();

                        statusCombo.getItems().addAll(
                                        "All Status",
                                        "Normal",
                                        "Abnormal",
                                        "Low",
                                        "High",
                                        "Pending");

                        statusCombo.setValue(
                                        "All Status");

                        statusCombo.setPrefHeight(40);

                        statusCombo.setPrefWidth(150);

                        // =====================================================
                        // FILTER BAR
                        // =====================================================

                        filterBar.getChildren().addAll(
                                        searchField,
                                        statusCombo);

                        // =====================================================
                        // SEARCH LISTENER
                        // =====================================================

                        searchField.textProperty().addListener(
                                        (obs, oldValue, newValue) -> applyFilters());

                        // =====================================================
                        // STATUS LISTENER
                        // =====================================================

                        statusCombo.valueProperty().addListener(
                                        (obs, oldValue, newValue) -> applyFilters());

                        // =====================================================
                        // TABLE
                        // =====================================================

                        table = new TableView<>();

                        table.setColumnResizePolicy(
                                        TableView.CONSTRAINED_RESIZE_POLICY);

                        table.setPlaceholder(
                                        new Label(
                                                        "📄 No patient reports found."));

                        table.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #E7DCE8;" +
                                                        "-fx-border-radius: 14;" +
                                                        "-fx-background-radius: 14;");

                        // =====================================================
                        // REPORT COLUMN
                        // =====================================================

                        TableColumn<PatientReport, String> reportColumn = new TableColumn<>("Report");

                        reportColumn.setCellValueFactory(
                                        data -> data.getValue()
                                                        .reportNameProperty());

                        // =====================================================
                        // PATIENT COLUMN
                        // =====================================================

                        TableColumn<PatientReport, String> patientColumn = new TableColumn<>("Patient");

                        patientColumn.setCellValueFactory(
                                        data -> data.getValue()
                                                        .patientNameProperty());

                        // =====================================================
                        // TYPE COLUMN
                        // =====================================================

                        TableColumn<PatientReport, String> typeColumn = new TableColumn<>("Type");

                        typeColumn.setCellValueFactory(
                                        data -> data.getValue()
                                                        .reportTypeProperty());

                        // =====================================================
                        // DATE COLUMN
                        // =====================================================

                        TableColumn<PatientReport, String> dateColumn = new TableColumn<>("Date");

                        dateColumn.setCellValueFactory(
                                        data -> data.getValue()
                                                        .dateProperty());

                        // =====================================================
                        // STATUS COLUMN
                        // =====================================================

                        TableColumn<PatientReport, String> statusColumn = new TableColumn<>("Status");

                        statusColumn.setCellValueFactory(
                                        data -> data.getValue()
                                                        .statusProperty());

                        statusColumn.setCellFactory(
                                        column -> new TableCell<PatientReport, String>() {

                                                @Override
                                                protected void updateItem(
                                                                String status,
                                                                boolean empty) {

                                                        super.updateItem(
                                                                        status,
                                                                        empty);

                                                        if (empty ||
                                                                        status == null ||
                                                                        status.trim().isEmpty()) {

                                                                setText(null);
                                                                setGraphic(null);

                                                                return;
                                                        }

                                                        Label pill = new Label(status);

                                                        pill.setPadding(
                                                                        new Insets(
                                                                                        5,
                                                                                        12,
                                                                                        5,
                                                                                        12));

                                                        pill.setStyle(
                                                                        getStatusStyle(status));

                                                        setAlignment(
                                                                        Pos.CENTER);

                                                        setGraphic(pill);
                                                        setText(null);
                                                }
                                        });

                        // =====================================================
                        // ACTION COLUMN
                        // =====================================================

                        TableColumn<PatientReport, Void> actionColumn = new TableColumn<>("Action");

                        actionColumn.setPrefWidth(190);

                        actionColumn.setCellFactory(
                                        column -> new TableCell<PatientReport, Void>() {

                                                private final Button viewButton = new Button("👁 View");

                                                private final Button deleteButton = new Button("🗑 Delete");

                                                private final HBox box = new HBox(8);

                                                {

                                                        box.setAlignment(
                                                                        Pos.CENTER);

                                                        // ---------------------------------
                                                        // VIEW
                                                        // ---------------------------------

                                                        viewButton.setPrefHeight(32);

                                                        viewButton.setStyle(
                                                                        "-fx-background-color: #F3ECFF;" +
                                                                                        "-fx-text-fill: #9B4DCC;" +
                                                                                        "-fx-font-weight: bold;" +
                                                                                        "-fx-background-radius: 8;" +
                                                                                        "-fx-cursor: hand;");

                                                        viewButton.setOnAction(event -> {

                                                                int index = getIndex();

                                                                if (index < 0 ||
                                                                                index >= getTableView()
                                                                                                .getItems()
                                                                                                .size()) {

                                                                        return;
                                                                }

                                                                PatientReport report = getTableView()
                                                                                .getItems()
                                                                                .get(index);

                                                                openReport(report);

                                                        });

                                                        // ---------------------------------
                                                        // DELETE
                                                        // ---------------------------------

                                                        deleteButton.setPrefHeight(32);

                                                        deleteButton.setStyle(
                                                                        "-fx-background-color: #FFEAF3;" +
                                                                                        "-fx-text-fill: #D93678;" +
                                                                                        "-fx-font-weight: bold;" +
                                                                                        "-fx-background-radius: 8;" +
                                                                                        "-fx-cursor: hand;");

                                                        deleteButton.setOnAction(event -> {

                                                                int index = getIndex();

                                                                if (index < 0 ||
                                                                                index >= getTableView()
                                                                                                .getItems()
                                                                                                .size()) {

                                                                        return;
                                                                }

                                                                PatientReport report = getTableView()
                                                                                .getItems()
                                                                                .get(index);

                                                                deleteReport(report);

                                                        });

                                                        box.getChildren().addAll(
                                                                        viewButton,
                                                                        deleteButton);
                                                }

                                                @Override
                                                protected void updateItem(
                                                                Void item,
                                                                boolean empty) {

                                                        super.updateItem(
                                                                        item,
                                                                        empty);

                                                        if (empty) {

                                                                setGraphic(null);

                                                        } else {

                                                                setGraphic(box);
                                                        }
                                                }
                                        });

                        // =====================================================
                        // ADD COLUMNS
                        // =====================================================

                        table.getColumns().addAll(
                                        reportColumn,
                                        patientColumn,
                                        typeColumn,
                                        dateColumn,
                                        statusColumn,
                                        actionColumn);

                        // =====================================================
                        // LOAD DATA
                        // =====================================================

                        applyFilters();

                        // =====================================================
                        // CONTENT
                        // =====================================================

                        VBox content = new VBox(15);

                        content.getChildren().addAll(
                                        filterBar,
                                        table);

                        VBox.setVgrow(
                                        table,
                                        Priority.ALWAYS);

                        // =====================================================
                        // ROOT
                        // =====================================================

                        root.setTop(header);

                        BorderPane.setMargin(
                                        header,
                                        new Insets(0, 0, 20, 0));

                        root.setCenter(content);

                        // =====================================================
                        // SCENE
                        // =====================================================

                        Scene scene = new Scene(root);

                        DoctorDashboard.changeScene(scene);

                        // =====================================================
                        // AUTO REFRESH
                        // =====================================================

                        startRealtimeRefresh();

                } catch (Exception e) {

                        System.out.println(
                                        "[REPORT PAGE ERROR] Unable to open page.");

                        e.printStackTrace();

                        showError(
                                        "Patient Reports",
                                        "Unable to open Patient Reports page.");
                }
        }

        // =========================================================
        // REFRESH REPORTS
        // =========================================================

        private static void refreshReportsNow(
                        Button refreshButton) {

                try {

                        if (controller == null) {

                                showError(
                                                "Refresh Error",
                                                "Patient Report Controller is not available.");

                                return;
                        }

                        // Disable while loading
                        refreshButton.setDisable(true);

                        refreshButton.setText(
                                        "⟳ Loading...");

                        System.out.println(
                                        "[REPORT PAGE] Refresh started...");

                        // Firestore refresh
                        controller.refreshReports();

                        // Reapply search/status filters
                        applyFilters();

                        System.out.println(
                                        "[REPORT PAGE] Refresh completed.");

                        // Small confirmation
                        refreshButton.setText(
                                        "✓ Updated");

                        Timeline resetButton = new Timeline(
                                        new KeyFrame(
                                                        Duration.seconds(1),
                                                        event -> {

                                                                refreshButton.setText(
                                                                                "↻ Refresh");

                                                                refreshButton.setDisable(
                                                                                false);
                                                        }));

                        resetButton.play();

                } catch (Exception e) {

                        e.printStackTrace();

                        refreshButton.setText(
                                        "↻ Refresh");

                        refreshButton.setDisable(
                                        false);

                        showError(
                                        "Refresh Error",
                                        "Unable to load latest patient reports.");
                }
        }

        // =========================================================
        // GET CONTROLLER
        // =========================================================

        private static PatientReportController getController() {

                try {

                        PatientReportController existing = DoctorDashboard.getReportController();

                        if (existing != null) {

                                return existing;
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return null;
        }

        // =========================================================
        // APPLY FILTERS
        // =========================================================

        private static void applyFilters() {

                if (controller == null ||
                                table == null) {

                        return;
                }

                String search = searchField == null
                                ? ""
                                : searchField.getText();

                String status = statusCombo == null
                                ? "All Status"
                                : statusCombo.getValue();

                ObservableList<PatientReport> filtered = controller.filterReports(
                                search,
                                status);

                table.setItems(
                                FXCollections.observableArrayList(
                                                filtered));
        }

        // =========================================================
        // OPEN REPORT
        // =========================================================

        private static void openReport(
                        PatientReport report) {

                if (report == null) {

                        showWarning(
                                        "Report Error",
                                        "Report information is not available.");

                        return;
                }

                String reportUrl = safe(report.getReportUrl());

                System.out.println(
                                "======================================");

                System.out.println(
                                "[VIEW REPORT]");

                System.out.println(
                                "Report Name : "
                                                + report.getReportName());

                System.out.println(
                                "Report URL  : "
                                                + reportUrl);

                System.out.println(
                                "======================================");

                // -----------------------------------------------------
                // OLD RECORD FALLBACK
                // -----------------------------------------------------

                if (reportUrl.isEmpty()) {

                        String action = safe(report.getAction());

                        if (action.startsWith("http://") ||
                                        action.startsWith("https://")) {

                                reportUrl = action;
                        }
                }

                // -----------------------------------------------------
                // URL EMPTY
                // -----------------------------------------------------

                if (reportUrl.isEmpty()) {

                        showWarning(
                                        "Report Not Found",
                                        "No uploaded report URL is available.\n\n"
                                                        + "Please upload this report again.");

                        return;
                }

                // -----------------------------------------------------
                // CLOUDINARY URL
                // -----------------------------------------------------

                if (reportUrl.startsWith("http://") ||
                                reportUrl.startsWith("https://")) {

                        try {

                                if (!Desktop.isDesktopSupported()) {

                                        showWarning(
                                                        "Cannot Open Report",
                                                        "Your computer does not support "
                                                                        + "opening browser links.");

                                        return;
                                }

                                Desktop desktop = Desktop.getDesktop();

                                if (!desktop.isSupported(
                                                Desktop.Action.BROWSE)) {

                                        showWarning(
                                                        "Cannot Open Report",
                                                        "Your system cannot open web links.");

                                        return;
                                }

                                // -------------------------------------------------
                                // OPEN IN DEFAULT BROWSER
                                // -------------------------------------------------

                                desktop.browse(
                                                new URI(reportUrl));

                                System.out.println(
                                                "[VIEW REPORT] Browser opened successfully.");

                                return;

                        } catch (Exception e) {

                                e.printStackTrace();

                                showError(
                                                "PDF Open Error",
                                                "Unable to open the uploaded report.\n\n"
                                                                + "The Cloudinary file URL may be invalid "
                                                                + "or the PDF may not be available.");

                                return;
                        }
                }

                // -----------------------------------------------------
                // LOCAL FILE
                // -----------------------------------------------------

                try {

                        File localFile = new File(reportUrl);

                        if (localFile.exists() &&
                                        localFile.isFile()) {

                                if (Desktop.isDesktopSupported() &&
                                                Desktop.getDesktop()
                                                                .isSupported(
                                                                                Desktop.Action.OPEN)) {

                                        Desktop.getDesktop()
                                                        .open(localFile);

                                        return;
                                }
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // -----------------------------------------------------
                // INVALID
                // -----------------------------------------------------

                showWarning(
                                "Report Not Found",
                                "The report file could not be opened.\n\n"
                                                + "Stored value:\n"
                                                + reportUrl);
        }

        // =========================================================
        // DELETE
        // =========================================================

        private static void deleteReport(
                        PatientReport report) {

                if (report == null) {
                        return;
                }

                Alert confirmation = new Alert(
                                Alert.AlertType.CONFIRMATION);

                confirmation.setTitle(
                                "Delete Report");

                confirmation.setHeaderText(
                                "Delete Patient Report?");

                confirmation.setContentText(
                                "Report: "
                                                + safe(report.getReportName())
                                                + "\nPatient: "
                                                + safe(report.getPatientName()));

                ButtonType deleteButton = new ButtonType(
                                "Delete",
                                ButtonBar.ButtonData.OK_DONE);

                ButtonType cancelButton = new ButtonType(
                                "Cancel",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                confirmation.getButtonTypes().setAll(
                                deleteButton,
                                cancelButton);

                confirmation.showAndWait()
                                .ifPresent(result -> {

                                        if (result == deleteButton) {

                                                try {

                                                        controller.deleteReport(
                                                                        report);

                                                        applyFilters();

                                                        showInformation(
                                                                        "Report Deleted",
                                                                        "Patient report deleted successfully.");

                                                } catch (Exception e) {

                                                        e.printStackTrace();

                                                        showError(
                                                                        "Delete Error",
                                                                        "Unable to delete patient report.");
                                                }
                                        }
                                });
        }

        // =========================================================
        // STATUS STYLE
        // =========================================================

        private static String getStatusStyle(
                        String status) {

                if (status == null) {
                        status = "";
                }

                switch (status.toLowerCase()) {

                        case "normal":

                                return "-fx-background-color: #E8F8F0;" +
                                                "-fx-text-fill: #238B5A;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";

                        case "abnormal":

                                return "-fx-background-color: #FFE8EE;" +
                                                "-fx-text-fill: #D93678;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";

                        case "high":

                                return "-fx-background-color: #FFF0E5;" +
                                                "-fx-text-fill: #D66A1F;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";

                        case "low":

                                return "-fx-background-color: #EAF3FF;" +
                                                "-fx-text-fill: #3974B8;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";

                        case "pending":

                                return "-fx-background-color: #FFF7DC;" +
                                                "-fx-text-fill: #A47700;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";

                        default:

                                return "-fx-background-color: #F3ECFF;" +
                                                "-fx-text-fill: #9B4DCC;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-font-weight: bold;";
                }
        }

        // =========================================================
        // AUTO REFRESH
        // =========================================================

        private static void startRealtimeRefresh() {

                stopRealtimeRefresh();

                realtimeRefreshTimeline = new Timeline(
                                new KeyFrame(
                                                Duration.seconds(5),
                                                event -> {

                                                        try {

                                                                if (controller == null) {
                                                                        return;
                                                                }

                                                                controller.refreshReports();

                                                                applyFilters();

                                                        } catch (Exception e) {

                                                                System.out.println(
                                                                                "[REPORT] Auto refresh failed.");
                                                        }
                                                }));

                realtimeRefreshTimeline.setCycleCount(
                                Timeline.INDEFINITE);

                realtimeRefreshTimeline.play();
        }

        // =========================================================
        // STOP AUTO REFRESH
        // =========================================================

        public static void stopRealtimeRefresh() {

                if (realtimeRefreshTimeline != null) {

                        realtimeRefreshTimeline.stop();

                        realtimeRefreshTimeline = null;
                }
        }

        // =========================================================
        // SAFE
        // =========================================================

        private static String safe(
                        String value) {

                return value == null
                                ? ""
                                : value.trim();
        }

        // =========================================================
        // WARNING
        // =========================================================

        private static void showWarning(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.WARNING);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // ERROR
        // =========================================================

        private static void showError(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // INFORMATION
        // =========================================================

        private static void showInformation(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }
}