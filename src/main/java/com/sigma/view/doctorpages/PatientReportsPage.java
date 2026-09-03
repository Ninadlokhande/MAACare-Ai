package com.sigma.view.doctorpages;

import com.sigma.view.scenesettings;
import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PatientReportsPage {

        private static PatientReportController controller;

        private static TableView<PatientReport> table;

        // =====================================================
        // SHOW PAGE
        // =====================================================

        public static void show() {

                if (controller == null) {
                        controller = new PatientReportController();
                }

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                root.setMinWidth(0);
                root.setMinHeight(0);

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
                                "Patient Reports",
                                "View and manage patient medical reports.");

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
                // FILTER
                // =====================================================

                HBox filter = new HBox(10);

                filter.setAlignment(
                                Pos.CENTER_LEFT);

                filter.setPadding(
                                new Insets(14));

                filter.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 10;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search report or patient...");

                search.setPrefWidth(280);
                search.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(search, Priority.ALWAYS);

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "All Status",
                                "Normal",
                                "Abnormal",
                                "Low",
                                "High",
                                "Pending");

                status.setValue(
                                "All Status");

                Button upload = Theme.primaryButton(
                                "+  Upload Report");

                Region filterSpacer = new Region();

                HBox.setHgrow(
                                filterSpacer,
                                Priority.ALWAYS);

                filter.getChildren().addAll(
                                search,
                                status,
                                filterSpacer,
                                upload);

                // =====================================================
                // TABLE
                // =====================================================

                table = new TableView<>();

                table.setMaxWidth(Double.MAX_VALUE);
                table.setMaxHeight(Double.MAX_VALUE);

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<PatientReport, String> reportName = new TableColumn<>("Report");

                TableColumn<PatientReport, String> patientName = new TableColumn<>("Patient");

                TableColumn<PatientReport, String> reportType = new TableColumn<>("Type");

                TableColumn<PatientReport, String> date = new TableColumn<>("Date");

                TableColumn<PatientReport, String> reportStatus = new TableColumn<>("Status");

                TableColumn<PatientReport, String> action = new TableColumn<>("Action");

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

                // =====================================================
                // ACTION COLUMN
                // =====================================================

                action.setCellFactory(
                                column -> new TableCell<PatientReport, String>() {

                                        private final Button view = new Button("👁");

                                        private final Button delete = new Button("🗑");

                                        private final HBox buttons = new HBox(6);

                                        {

                                                view.setStyle(
                                                                "-fx-background-color: #E0F2FE;" +
                                                                                "-fx-text-fill: #0284C7;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                delete.setStyle(
                                                                "-fx-background-color: #FEE2E2;" +
                                                                                "-fx-text-fill: #DC2626;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                view.setTooltip(
                                                                new Tooltip(
                                                                                "View Report"));

                                                delete.setTooltip(
                                                                new Tooltip(
                                                                                "Delete Report"));

                                                view.setOnAction(e -> {

                                                        PatientReport report = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showReportDetails(
                                                                        report);
                                                });

                                                delete.setOnAction(e -> {

                                                        PatientReport report = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        deleteReport(report);
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

                                                        setGraphic(buttons);

                                                        setAlignment(
                                                                        Pos.CENTER);
                                                }
                                        }
                                });

                table.getColumns().addAll(
                                reportName,
                                patientName,
                                reportType,
                                date,
                                reportStatus,
                                action);

                table.setItems(
                                controller.getReports());

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                // =====================================================
                // SEARCH
                // =====================================================

                search.textProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> {

                                                        table.setItems(
                                                                        controller.searchReports(
                                                                                        newValue));
                                                });

                // =====================================================
                // STATUS FILTER
                // =====================================================

                status.setOnAction(e -> {

                        table.setItems(
                                        controller.filterByStatus(
                                                        status.getValue()));
                });

                // =====================================================
                // UPLOAD
                // =====================================================

                upload.setOnAction(e -> {

                        UploadReportPage.show();
                });

                // =====================================================
                // ROOT
                // =====================================================

                VBox content = new VBox(
                                15,
                                filter,
                                table);

                content.setFillWidth(true);
                content.setMinWidth(0);
                content.setMaxWidth(Double.MAX_VALUE);
                content.setMaxHeight(Double.MAX_VALUE);

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                root.setTop(header);

                BorderPane.setMargin(
                                header,
                                new Insets(
                                                0,
                                                0,
                                                20,
                                                0));

                VBox.setVgrow(
                                content,
                                Priority.ALWAYS);

                root.setCenter(content);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                root,
                                scenesettings.rectanguler2d.getWidth(),
                                scenesettings.rectanguler2d.getHeight()
                );

                DoctorDashboard.changeScene(
                                scene);
        }

        // =====================================================
        // ADD REPORT
        // =====================================================

        public static void addReport(
                        PatientReport report) {

                if (controller == null) {

                        controller = new PatientReportController();
                }

                controller.addReport(
                                report);
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
                                report.getReportName());

                alert.setContentText(

                                "Patient: " +
                                                report.getPatientName()

                                                + "\n\nReport Type: " +
                                                report.getReportType()

                                                + "\n\nDate: " +
                                                report.getDate()

                                                + "\n\nStatus: " +
                                                report.getStatus()

                                                + "\n\nFile: " +
                                                report.getAction());

                alert.showAndWait();
        }

        // =====================================================
        // DELETE REPORT
        // =====================================================

        private static void deleteReport(
                        PatientReport report) {

                Alert confirmation = new Alert(
                                Alert.AlertType.CONFIRMATION);

                confirmation.setTitle(
                                "Delete Report");

                confirmation.setHeaderText(
                                "Delete this report?");

                confirmation.setContentText(
                                report.getReportName());

                confirmation.showAndWait()
                                .ifPresent(response -> {

                                        if (response == ButtonType.OK) {

                                                controller.deleteReport(
                                                                report);

                                                table.setItems(
                                                                controller.getReports());
                                        }
                                });
        }
}