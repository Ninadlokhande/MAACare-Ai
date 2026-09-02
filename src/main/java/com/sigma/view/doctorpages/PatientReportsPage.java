package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.List;

public class PatientReportsPage {

        // =====================================================
        // CONTROLLER
        // =====================================================

        private static PatientReportController controller;

        // =====================================================
        // TABLE
        // =====================================================

        private static TableView<PatientReport> table;

        // =====================================================
        // SEARCH / STATUS
        // =====================================================

        private static TextField searchField;

        private static ComboBox<String> statusCombo;

        // =====================================================
        // REALTIME REFRESH
        // =====================================================

        private static Timeline realtimeRefreshTimeline;

        // =====================================================
        // CURRENT FILTER VALUES
        // =====================================================

        private static String currentSearchText = "";

        private static String currentStatus = "All Status";

        // =====================================================
        // GET SHARED CONTROLLER
        // =====================================================

        private static PatientReportController getController() {

                if (controller == null) {

                        controller = DoctorDashboard.getReportController();
                }

                return controller;
        }

        // =====================================================
        // SHOW PAGE
        // =====================================================

        public static void show() {

                controller = getController();

                // =================================================
                // INITIAL FIRESTORE LOAD
                // =================================================

                try {

                        controller.refreshReports();

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[PATIENT REPORTS] Initial report load failed.");
                }

                // =================================================
                // ROOT
                // =================================================

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setPadding(
                                new Insets(
                                                28,
                                                35,
                                                28,
                                                35));

                // =================================================
                // HEADER
                // =================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Patient Reports",
                                "View and manage patient medical reports.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                back.setOnAction(
                                e -> {

                                        stopRealtimeRefresh();

                                        DoctorDashboard.showDashboard();
                                });

                header.getChildren()
                                .addAll(
                                                heading,
                                                spacer,
                                                back);

                // =================================================
                // FILTER
                // =================================================

                HBox filter = new HBox(10);

                filter.setAlignment(
                                Pos.CENTER_LEFT);

                filter.setPadding(
                                new Insets(14));

                filter.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER + ";"
                                                + "-fx-border-radius: 10;");

                // =================================================
                // SEARCH
                // =================================================

                searchField = new TextField();

                searchField.setPromptText(
                                "Search report or patient...");

                searchField.setPrefWidth(
                                280);

                // =================================================
                // STATUS
                // =================================================

                statusCombo = new ComboBox<>();

                statusCombo.getItems()
                                .addAll(
                                                "All Status",
                                                "Normal",
                                                "Abnormal",
                                                "Low",
                                                "High",
                                                "Pending");

                statusCombo.setValue(
                                "All Status");

                // =================================================
                // UPLOAD BUTTON
                // =================================================

                Button upload = Theme.primaryButton(
                                "+  Upload Report");

                Region filterSpacer = new Region();

                HBox.setHgrow(
                                filterSpacer,
                                Priority.ALWAYS);

                filter.getChildren()
                                .addAll(
                                                searchField,
                                                statusCombo,
                                                filterSpacer,
                                                upload);

                // =================================================
                // TABLE
                // =================================================

                table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                table.setPlaceholder(
                                new Label(
                                                "No reports found."));

                // =================================================
                // COLUMNS
                // =================================================

                TableColumn<PatientReport, String> reportName = new TableColumn<>(
                                "Report");

                TableColumn<PatientReport, String> patientName = new TableColumn<>(
                                "Patient");

                TableColumn<PatientReport, String> reportType = new TableColumn<>(
                                "Type");

                TableColumn<PatientReport, String> date = new TableColumn<>(
                                "Date");

                TableColumn<PatientReport, String> reportStatus = new TableColumn<>(
                                "Status");

                TableColumn<PatientReport, String> action = new TableColumn<>(
                                "Action");

                // =================================================
                // CELL VALUE FACTORIES
                // =================================================

                reportName.setCellValueFactory(
                                d -> d.getValue()
                                                .reportNameProperty());

                patientName.setCellValueFactory(
                                d -> d.getValue()
                                                .patientNameProperty());

                reportType.setCellValueFactory(
                                d -> d.getValue()
                                                .reportTypeProperty());

                date.setCellValueFactory(
                                d -> d.getValue()
                                                .dateProperty());

                reportStatus.setCellValueFactory(
                                d -> d.getValue()
                                                .statusProperty());

                // =================================================
                // ACTION COLUMN
                // =================================================

                action.setCellFactory(
                                column -> new TableCell<PatientReport, String>() {

                                        private final Button view = new Button("👁");

                                        private final Button delete = new Button("🗑");

                                        private final HBox buttons = new HBox(6);

                                        {

                                                // =================================
                                                // VIEW STYLE
                                                // =================================

                                                view.setStyle(
                                                                "-fx-background-color: #E0F2FE;"
                                                                                + "-fx-text-fill: #0284C7;"
                                                                                + "-fx-font-size: 14px;"
                                                                                + "-fx-background-radius: 7;"
                                                                                + "-fx-padding: 5 9;"
                                                                                + "-fx-cursor: hand;");

                                                // =================================
                                                // DELETE STYLE
                                                // =================================

                                                delete.setStyle(
                                                                "-fx-background-color: #FEE2E2;"
                                                                                + "-fx-text-fill: #DC2626;"
                                                                                + "-fx-font-size: 14px;"
                                                                                + "-fx-background-radius: 7;"
                                                                                + "-fx-padding: 5 9;"
                                                                                + "-fx-cursor: hand;");

                                                // =================================
                                                // TOOLTIPS
                                                // =================================

                                                view.setTooltip(
                                                                new Tooltip(
                                                                                "View Report"));

                                                delete.setTooltip(
                                                                new Tooltip(
                                                                                "Delete Report"));

                                                // =================================
                                                // VIEW
                                                // =================================

                                                view.setOnAction(
                                                                e -> {

                                                                        if (getIndex() < 0 ||
                                                                                        getIndex() >= getTableView()
                                                                                                        .getItems()
                                                                                                        .size()) {

                                                                                return;
                                                                        }

                                                                        PatientReport report = getTableView()
                                                                                        .getItems()
                                                                                        .get(
                                                                                                        getIndex());

                                                                        showReportDetails(
                                                                                        report);
                                                                });

                                                // =================================
                                                // DELETE
                                                // =================================

                                                delete.setOnAction(
                                                                e -> {

                                                                        if (getIndex() < 0 ||
                                                                                        getIndex() >= getTableView()
                                                                                                        .getItems()
                                                                                                        .size()) {

                                                                                return;
                                                                        }

                                                                        PatientReport report = getTableView()
                                                                                        .getItems()
                                                                                        .get(
                                                                                                        getIndex());

                                                                        deleteReport(
                                                                                        report);
                                                                });

                                                buttons.setAlignment(
                                                                Pos.CENTER);

                                                buttons.getChildren()
                                                                .addAll(
                                                                                view,
                                                                                delete);
                                        }

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty) {

                                                        setGraphic(null);

                                                } else {

                                                        setGraphic(
                                                                        buttons);

                                                        setAlignment(
                                                                        Pos.CENTER);
                                                }
                                        }
                                });

                // =================================================
                // ADD COLUMNS
                // =================================================

                table.getColumns()
                                .addAll(
                                                reportName,
                                                patientName,
                                                reportType,
                                                date,
                                                reportStatus,
                                                action);

                // =================================================
                // INITIAL TABLE DATA
                // =================================================

                refreshTableFromFirestore();

                // =================================================
                // SEARCH LISTENER
                // =================================================

                searchField.textProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> {

                                                        currentSearchText = newValue == null
                                                                        ? ""
                                                                        : newValue.trim();

                                                        refreshTableUsingFilters();
                                                });

                // =================================================
                // STATUS LISTENER
                // =================================================

                statusCombo.setOnAction(
                                e -> {

                                        currentStatus = statusCombo.getValue();

                                        refreshTableUsingFilters();
                                });

                // =================================================
                // UPLOAD
                // =================================================

                upload.setOnAction(
                                e -> UploadReportPage.show());

                // =================================================
                // CONTENT
                // =================================================

                VBox content = new VBox(
                                15,
                                filter,
                                table);

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                // =================================================
                // ROOT LAYOUT
                // =================================================

                root.setTop(
                                header);

                BorderPane.setMargin(
                                header,
                                new Insets(
                                                0,
                                                0,
                                                20,
                                                0));

                root.setCenter(
                                content);

                // =================================================
                // SAME DASHBOARD STAGE
                // =================================================

                Scene scene = new Scene(root);

                DoctorDashboard.changeScene(
                                scene);

                // =================================================
                // START REALTIME REFRESH
                // =================================================

                startRealtimeRefresh();
        }

        // =====================================================
        // REFRESH TABLE FROM FIRESTORE
        // =====================================================

        private static void refreshTableFromFirestore() {

                try {

                        controller.refreshReports();

                        refreshTableUsingFilters();

                        System.out.println(
                                        "[PATIENT REPORTS] Firestore data refreshed.");

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[PATIENT REPORTS] Refresh failed: "
                                                        + e.getMessage());
                }
        }

        // =====================================================
        // APPLY CURRENT FILTERS
        // =====================================================

        private static void refreshTableUsingFilters() {

                if (controller == null ||
                                table == null) {

                        return;
                }

                try {

                        List<PatientReport> reports;

                        // =============================================
                        // SEARCH + STATUS
                        // =============================================

                        if (currentSearchText != null &&
                                        !currentSearchText.isEmpty()) {

                                reports = controller.searchReports(
                                                currentSearchText);

                        } else {

                                reports = controller.getReports();
                        }

                        // =============================================
                        // STATUS FILTER
                        // =============================================

                        if (currentStatus != null &&
                                        !currentStatus.equals(
                                                        "All Status")) {

                                List<PatientReport> statusReports = controller.filterByStatus(
                                                currentStatus);

                                // -----------------------------------------
                                // If search is also active, find matching
                                // reports between search and status results
                                // -----------------------------------------

                                if (currentSearchText != null &&
                                                !currentSearchText.isEmpty()) {

                                        reports.retainAll(
                                                        statusReports);

                                } else {

                                        reports = statusReports;
                                }
                        }

                        table.getItems()
                                        .setAll(
                                                        reports);

                        table.refresh();

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[PATIENT REPORTS] "
                                                        + "Unable to apply filters: "
                                                        + e.getMessage());
                }
        }

        // =====================================================
        // REALTIME AUTO REFRESH
        // =====================================================

        private static void startRealtimeRefresh() {

                stopRealtimeRefresh();

                realtimeRefreshTimeline = new Timeline(
                                new KeyFrame(
                                                Duration.seconds(3),
                                                e -> {

                                                        refreshTableFromFirestore();

                                                }));

                realtimeRefreshTimeline.setCycleCount(
                                Timeline.INDEFINITE);

                realtimeRefreshTimeline.play();

                System.out.println(
                                "[PATIENT REPORTS] "
                                                + "Realtime refresh started.");
        }

        // =====================================================
        // STOP REALTIME REFRESH
        // =====================================================

        private static void stopRealtimeRefresh() {

                if (realtimeRefreshTimeline != null) {

                        realtimeRefreshTimeline.stop();

                        realtimeRefreshTimeline = null;

                        System.out.println(
                                        "[PATIENT REPORTS] "
                                                        + "Realtime refresh stopped.");
                }
        }

        // =====================================================
        // ADD REPORT
        // =====================================================

        public static void addReport(
                        PatientReport report) {

                if (report == null) {
                        return;
                }

                try {

                        PatientReportController reportController = getController();

                        reportController.addReport(
                                        report);

                        // =============================================
                        // FIRESTORE REFRESH
                        // =============================================

                        reportController.refreshReports();

                        if (table != null) {

                                refreshTableUsingFilters();
                        }

                        System.out.println(
                                        "[PATIENT REPORTS] "
                                                        + "Report added successfully.");

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Unable to add patient report.\n\n"
                                                        + e.getMessage());
                }
        }

        // =====================================================
        // VIEW REPORT
        // =====================================================

        private static void showReportDetails(
                        PatientReport report) {

                if (report == null) {
                        return;
                }

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Patient Report");

                alert.setHeaderText(
                                safe(
                                                report.getReportName()));

                alert.setContentText(
                                "Patient: "
                                                + safe(
                                                                report.getPatientName())

                                                + "\n\nReport Type: "
                                                + safe(
                                                                report.getReportType())

                                                + "\n\nDate: "
                                                + safe(
                                                                report.getDate())

                                                + "\n\nStatus: "
                                                + safe(
                                                                report.getStatus())

                                                + "\n\nFile: "
                                                + safe(
                                                                report.getAction()));

                alert.showAndWait();
        }

        // =====================================================
        // DELETE REPORT
        // =====================================================

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
                                "Delete this report?");

                confirmation.setContentText(
                                safe(
                                                report.getReportName()));

                confirmation.showAndWait()
                                .ifPresent(
                                                response -> {

                                                        if (response == ButtonType.OK) {

                                                                try {

                                                                        controller.deleteReport(
                                                                                        report);

                                                                        // =================================
                                                                        // REFRESH FIRESTORE DATA
                                                                        // =================================

                                                                        controller.refreshReports();

                                                                        refreshTableUsingFilters();

                                                                        System.out.println(
                                                                                        "[PATIENT REPORTS] "
                                                                                                        + "Report deleted.");

                                                                } catch (Exception ex) {

                                                                        ex.printStackTrace();

                                                                        Alert error = new Alert(
                                                                                        Alert.AlertType.ERROR);

                                                                        error.setTitle(
                                                                                        "Delete Error");

                                                                        error.setHeaderText(
                                                                                        "Unable to delete report");

                                                                        error.setContentText(
                                                                                        "Please check your Firebase connection.");

                                                                        error.showAndWait();
                                                                }
                                                        }
                                                });
        }

        // =====================================================
        // ERROR ALERT
        // =====================================================

        private static void showError(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Patient Reports");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // =====================================================
        // SAFE STRING
        // =====================================================

        private static String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }
}