package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

public class PatientReportsPage {

        // ============================================================
        // COLORS
        // ============================================================

        private static final String DARK_PINK = "#C5306D";

        private static final String LIGHT_PURPLE = "#FFE3EE";
        private static final String PURPLE = "#E84A87";

        private static final String DARK_TEXT = "#3B2140";
        private static final String SECONDARY_TEXT = "#806A78";
        private static final String BORDER = "#F0D8E3";
        private static final String PAGE_BACKGROUND = "#FFF9FB";

        private static TableView<PatientReport> table;
        private static TextField searchField;
        private static ComboBox<String> statusCombo;

        private static PatientReportController controller;

        // ============================================================
        // SHOW PAGE
        // ============================================================

        public static void show() {

                Stage stage = DoctorDashboard.dashboardStage;

                if (stage == null) {
                        stage = new Stage();
                        DoctorDashboard.dashboardStage = stage;
                }

                controller = DoctorDashboard.getReportController();

                try {
                        controller.refreshReports();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color: " + PAGE_BACKGROUND + ";");

                // ========================================================
                // HEADER
                // ========================================================

                HBox header = new HBox();
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(22, 30, 22, 30));
                header.setSpacing(20);

                VBox titleBox = new VBox(4);

                Label title = new Label("Patient Reports");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
                title.setTextFill(Color.web(DARK_TEXT));

                Label subtitle = new Label(
                                "View and manage reports uploaded for your patients");
                subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                subtitle.setTextFill(Color.web(SECONDARY_TEXT));

                titleBox.getChildren().addAll(title, subtitle);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button uploadButton = new Button("＋ Upload Report");
                uploadButton.setPrefHeight(42);
                uploadButton.setPadding(new Insets(0, 20, 0, 20));
                uploadButton.setStyle(
                                "-fx-background-color: " + PURPLE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;");

                uploadButton.setOnMouseEntered(e -> uploadButton.setStyle(
                                "-fx-background-color: " + DARK_PINK + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;"));

                uploadButton.setOnMouseExited(e -> uploadButton.setStyle(
                                "-fx-background-color: " + PURPLE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;"));

                uploadButton.setOnAction(e -> UploadReportPage.show());

                header.getChildren().addAll(
                                titleBox,
                                spacer,
                                uploadButton);

                // ========================================================
                // FILTER AREA
                // ========================================================

                HBox filterBox = new HBox(12);
                filterBox.setAlignment(Pos.CENTER_LEFT);
                filterBox.setPadding(new Insets(0, 30, 18, 30));

                searchField = new TextField();
                searchField.setPromptText("Search patient or report...");
                searchField.setPrefWidth(300);
                searchField.setPrefHeight(40);

                searchField.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-padding: 0 12;");

                statusCombo = new ComboBox<>();
                statusCombo.getItems().addAll(
                                "All Status",
                                "Pending",
                                "Reviewed");
                statusCombo.setValue("All Status");
                statusCombo.setPrefWidth(150);
                statusCombo.setPrefHeight(40);

                Button clearButton = new Button("Clear");

                clearButton.setPrefHeight(40);
                clearButton.setPadding(new Insets(0, 18, 0, 18));

                clearButton.setStyle(
                                "-fx-background-color: " + LIGHT_PURPLE + ";" +
                                                "-fx-text-fill: " + PURPLE + ";" +
                                                "-fx-font-family: Arial;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9;");

                searchField.textProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilter());

                statusCombo.setOnAction(e -> applyFilter());

                clearButton.setOnAction(e -> {
                        searchField.clear();
                        statusCombo.setValue("All Status");
                        table.setItems(controller.getReports());
                        applyFilter();
                });

                filterBox.getChildren().addAll(
                                searchField,
                                statusCombo,
                                clearButton);

                // ========================================================
                // TABLE
                // ========================================================

                table = new TableView<>();
                table.setItems(controller.getReports());

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

                table.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                // --------------------------------------------------------
                // Report Name
                // --------------------------------------------------------

                TableColumn<PatientReport, String> reportColumn = new TableColumn<>("Report");

                reportColumn.setCellValueFactory(
                                data -> data.getValue().reportNameProperty());

                // --------------------------------------------------------
                // Patient
                // --------------------------------------------------------

                TableColumn<PatientReport, String> patientColumn = new TableColumn<>("Patient");

                patientColumn.setCellValueFactory(
                                data -> data.getValue().patientNameProperty());

                // --------------------------------------------------------
                // Type
                // --------------------------------------------------------

                TableColumn<PatientReport, String> typeColumn = new TableColumn<>("Type");

                typeColumn.setCellValueFactory(
                                data -> data.getValue().reportTypeProperty());

                // --------------------------------------------------------
                // Date
                // --------------------------------------------------------

                TableColumn<PatientReport, String> dateColumn = new TableColumn<>("Date");

                dateColumn.setCellValueFactory(
                                data -> data.getValue().dateProperty());

                // --------------------------------------------------------
                // Status
                // --------------------------------------------------------

                TableColumn<PatientReport, String> statusColumn = new TableColumn<>("Status");

                statusColumn.setCellValueFactory(
                                data -> data.getValue().statusProperty());

                statusColumn.setCellFactory(column -> new TableCell<PatientReport, String>() {

                        @Override
                        protected void updateItem(
                                        String status,
                                        boolean empty) {

                                super.updateItem(status, empty);

                                if (empty || status == null) {
                                        setText(null);
                                        setGraphic(null);
                                        return;
                                }

                                Label label = new Label(status);

                                label.setPadding(
                                                new Insets(5, 12, 5, 12));

                                if ("Reviewed".equalsIgnoreCase(status)) {

                                        label.setStyle(
                                                        "-fx-background-color: #E8F8EF;" +
                                                                        "-fx-text-fill: #21874A;" +
                                                                        "-fx-background-radius: 15;" +
                                                                        "-fx-font-weight: bold;");

                                } else {

                                        label.setStyle(
                                                        "-fx-background-color: #FFF4D9;" +
                                                                        "-fx-text-fill: #A36B00;" +
                                                                        "-fx-background-radius: 15;" +
                                                                        "-fx-font-weight: bold;");
                                }

                                setGraphic(label);
                                setText(null);
                                setAlignment(Pos.CENTER);
                        }
                });

                // --------------------------------------------------------
                // Action
                // --------------------------------------------------------

                TableColumn<PatientReport, String> actionColumn = new TableColumn<>("Action");

                actionColumn.setCellFactory(column -> new TableCell<PatientReport, String>() {

                        private final Button viewButton = new Button("View");

                        private final Button deleteButton = new Button("Delete");

                        private final HBox box = new HBox(8);

                        {
                                box.setAlignment(Pos.CENTER);

                                viewButton.setStyle(
                                                "-fx-background-color: " +
                                                                LIGHT_PURPLE + ";" +
                                                                "-fx-text-fill: " + PURPLE + ";" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 8;");

                                deleteButton.setStyle(
                                                "-fx-background-color: #FFF0F4;" +
                                                                "-fx-text-fill: " + DARK_PINK + ";" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 8;");

                                viewButton.setOnAction(e -> {

                                        PatientReport report = getTableView().getItems()
                                                        .get(getIndex());

                                        openReport(report);
                                });

                                deleteButton.setOnAction(e -> {

                                        PatientReport report = getTableView().getItems()
                                                        .get(getIndex());

                                        deleteReport(report);
                                });

                                box.getChildren().addAll(
                                                viewButton,
                                                deleteButton);
                        }

                        @Override
                        protected void updateItem(
                                        String item,
                                        boolean empty) {

                                super.updateItem(item, empty);

                                if (empty) {
                                        setGraphic(null);
                                } else {
                                        setGraphic(box);
                                }
                        }
                });

                table.getColumns().addAll(
                                reportColumn,
                                patientColumn,
                                typeColumn,
                                dateColumn,
                                statusColumn,
                                actionColumn);

                // ========================================================
                // EMPTY PLACEHOLDER
                // ========================================================

                Label emptyLabel = new Label(
                                "No patient reports found.");

                emptyLabel.setFont(
                                Font.font("Arial", FontWeight.NORMAL, 15));

                emptyLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                table.setPlaceholder(emptyLabel);

                // ========================================================
                // MAIN CONTAINER
                // ========================================================

                VBox content = new VBox(0);
                content.setPadding(new Insets(0, 30, 30, 30));

                VBox tableCard = new VBox(table);
                tableCard.setPadding(new Insets(10));
                tableCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 14;");

                VBox.setVgrow(table, Priority.ALWAYS);
                VBox.setVgrow(tableCard, Priority.ALWAYS);

                content.getChildren().add(tableCard);

                VBox page = new VBox();
                page.getChildren().addAll(
                                header,
                                filterBox,
                                content);

                VBox.setVgrow(content, Priority.ALWAYS);

                root.setCenter(page);
                root.setLeft(DoctorDashboard.createSidebar("Reports"));

                Scene scene = new Scene(
                                root,
                                DoctorDashboard.dashboardStage.getWidth(),
                                DoctorDashboard.dashboardStage.getHeight());

                DoctorDashboard.changeScene(scene);

                if (stage != null) {
                        stage.setTitle("MaaCare AI - Patient Reports");
                }
        }

        // ============================================================
        // FILTER
        // ============================================================

        private static void applyFilter() {

                if (controller == null || table == null) {
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

                table.setItems(filtered);
        }

        // ============================================================
        // OPEN REPORT
        // ============================================================

        private static void openReport(PatientReport report) {

                if (report == null) {
                        return;
                }

                String url = report.getReportUrl();

                if (url == null || url.trim().isEmpty()) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Report URL is not available.");

                        return;
                }

                try {

                        Desktop.getDesktop().browse(
                                        new URI(url.trim()));

                } catch (Exception e) {

                        e.printStackTrace();

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Unable to open the report.");
                }
        }

        // ============================================================
        // DELETE REPORT
        // ============================================================

        private static void deleteReport(
                        PatientReport report) {

                if (report == null) {
                        return;
                }

                Alert confirmation = new Alert(
                                Alert.AlertType.CONFIRMATION);

                confirmation.setTitle("Delete Report");
                confirmation.setHeaderText(
                                "Delete this patient report?");
                confirmation.setContentText(
                                report.getReportName());

                confirmation.showAndWait().ifPresent(result -> {

                        if (result == ButtonType.OK) {

                                try {

                                        controller.deleteReport(report);

                                        table.setItems(
                                                        controller.getReports());

                                        applyFilter();

                                } catch (Exception e) {

                                        e.printStackTrace();

                                        showAlert(
                                                        Alert.AlertType.ERROR,
                                                        "Unable to delete report.");
                                }
                        }
                });
        }

        // ============================================================
        // ALERT
        // ============================================================

        private static void showAlert(
                        Alert.AlertType type,
                        String message) {

                Alert alert = new Alert(type);
                alert.setTitle("MaaCare AI");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }
}
