package com.sigma.view.doctorpages;

import com.sigma.model.DoctorModel.PatientReport;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PatientReportsPage {

        public static void show() {

                VBox root = new VBox(20);

                root.setPadding(
                                new Insets(
                                                28,
                                                35,
                                                28,
                                                35));

                Theme.applyBackground(root);

                // =================================================
                // HEADER
                // =================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Patient Reports",
                                "View and manage patient reports.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button upload = Theme.primaryButton(
                                "↥  Upload Report");

                Button back = Theme.backButton();

                HBox right = new HBox(10);

                right.getChildren().addAll(
                                back,
                                upload);

                header.getChildren().addAll(
                                heading,
                                spacer,
                                right);

                // =================================================
                // FILTERS
                // =================================================

                HBox filters = new HBox(10);

                filters.setPadding(
                                new Insets(14));

                filters.setAlignment(
                                Pos.CENTER_LEFT);

                filters.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 10;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search patient name...");

                search.setPrefWidth(270);

                ComboBox<String> reportType = new ComboBox<>();

                reportType.getItems().addAll(
                                "All Report Types",
                                "Blood Test",
                                "Ultrasound",
                                "Urine Test",
                                "Thyroid Profile",
                                "Vitamin D Test");

                reportType.setValue(
                                "All Report Types");

                ComboBox<String> time = new ComboBox<>();

                time.getItems().addAll(
                                "All Time",
                                "Today",
                                "This Week",
                                "This Month");

                time.setValue(
                                "All Time");

                filters.getChildren().addAll(
                                search,
                                reportType,
                                time);

                // =================================================
                // TABLE
                // =================================================

                TableView<PatientReport> table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<PatientReport, String> report = new TableColumn<>("Report");

                TableColumn<PatientReport, String> patient = new TableColumn<>("Patient");

                TableColumn<PatientReport, String> date = new TableColumn<>("Date");

                TableColumn<PatientReport, String> source = new TableColumn<>("Source");

                TableColumn<PatientReport, String> status = new TableColumn<>("Status");

                TableColumn<PatientReport, String> action = new TableColumn<>("Action");

                report.setCellValueFactory(
                                d -> d.getValue().reportNameProperty());

                patient.setCellValueFactory(
                                d -> d.getValue().patientNameProperty());

                date.setCellValueFactory(
                                d -> d.getValue().dateProperty());

                source.setCellValueFactory(
                                d -> d.getValue().reportTypeProperty());

                status.setCellValueFactory(
                                d -> d.getValue().statusProperty());

                // =================================================
                // ACTION COLUMN
                // =================================================

                action.setCellFactory(
                                column -> new TableCell<PatientReport, String>() {

                                        private final Button viewButton = new Button("👁");

                                        private final Button downloadButton = new Button("⬇");

                                        private final HBox buttons = new HBox(6);

                                        {

                                                viewButton.setStyle(
                                                                "-fx-background-color: #E0F2FE;" +
                                                                                "-fx-text-fill: #0284C7;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                viewButton.setTooltip(
                                                                new Tooltip("View Report"));

                                                downloadButton.setStyle(
                                                                "-fx-background-color: #F3ECFF;" +
                                                                                "-fx-text-fill: #8B5CF6;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                downloadButton.setTooltip(
                                                                new Tooltip("Download Report"));

                                                // ==============================
                                                // VIEW
                                                // ==============================

                                                viewButton.setOnAction(e -> {

                                                        PatientReport report = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        System.out.println(
                                                                        "Viewing Report: " +
                                                                                        report.reportNameProperty()
                                                                                                        .get()
                                                                                        +
                                                                                        " | Patient: " +
                                                                                        report.patientNameProperty()
                                                                                                        .get());
                                                });

                                                // ==============================
                                                // DOWNLOAD
                                                // ==============================

                                                downloadButton.setOnAction(e -> {

                                                        PatientReport report = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        System.out.println(
                                                                        "Downloading Report: " +
                                                                                        report.reportNameProperty()
                                                                                                        .get()
                                                                                        +
                                                                                        " | Patient: " +
                                                                                        report.patientNameProperty()
                                                                                                        .get());
                                                });

                                                buttons.setAlignment(
                                                                Pos.CENTER);

                                                buttons.getChildren().addAll(
                                                                viewButton,
                                                                downloadButton);
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
                                report,
                                patient,
                                date,
                                source,
                                status,
                                action);

                // =================================================
                // SAMPLE DATA
                // =================================================

                table.getItems().addAll(

                                new PatientReport(
                                                "Blood Test",
                                                "Priya Sharma",
                                                "07 May 2024\n10:30 AM",
                                                "ThyroCare Lab",
                                                "Normal",
                                                "◉  ↓"),

                                new PatientReport(
                                                "Ultrasound",
                                                "Neha Kulkarni",
                                                "05 May 2024\n09:15 AM",
                                                "CarePlus Imaging",
                                                "Normal",
                                                "◉  ↓"),

                                new PatientReport(
                                                "Urine Test",
                                                "Sneha Patil",
                                                "04 May 2024\n11:20 AM",
                                                "Metropolis Lab",
                                                "Normal",
                                                "◉  ↓"),

                                new PatientReport(
                                                "Thyroid Profile",
                                                "Ayesha Khan",
                                                "03 May 2024\n09:45 AM",
                                                "ThyroCare Lab",
                                                "Low",
                                                "◉  ↓"),

                                new PatientReport(
                                                "Vitamin D Test",
                                                "Pooja Iyer",
                                                "02 May 2024\n02:30 PM",
                                                "Redcliffe Labs",
                                                "Low",
                                                "◉  ↓"));

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                root.getChildren().addAll(
                                header,
                                filters,
                                table);

                // =================================================
                // SHOW REPORT PAGE ON SAME DASHBOARD STAGE
                // =================================================

                Scene reportsScene = new Scene(root);

                DoctorDashboard.changeScene(
                                reportsScene);
        }
}