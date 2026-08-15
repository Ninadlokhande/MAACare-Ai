package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PatientReportsPage {

        public static void show() {

                VBox root = new VBox(20);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                Theme.applyBackground(root);

                // HEADER

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

                HBox right = new HBox(10);

                right.getChildren().addAll(
                                Theme.backButton(),
                                upload);

                header.getChildren().addAll(
                                heading,
                                spacer,
                                right);

                // FILTERS

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

                time.setValue("All Time");

                filters.getChildren().addAll(
                                search,
                                reportType,
                                time);

                // TABLE

                TableView<Report> table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<Report, String> report = new TableColumn<>("Report");

                TableColumn<Report, String> patient = new TableColumn<>("Patient");

                TableColumn<Report, String> date = new TableColumn<>("Date");

                TableColumn<Report, String> source = new TableColumn<>("Source");

                TableColumn<Report, String> status = new TableColumn<>("Status");

                TableColumn<Report, String> action = new TableColumn<>("Action");

                report.setCellValueFactory(
                                d -> d.getValue().reportProperty());

                patient.setCellValueFactory(
                                d -> d.getValue().patientProperty());

                date.setCellValueFactory(
                                d -> d.getValue().dateProperty());

                source.setCellValueFactory(
                                d -> d.getValue().sourceProperty());

                status.setCellValueFactory(
                                d -> d.getValue().statusProperty());

                action.setCellValueFactory(
                                d -> d.getValue().actionProperty());

                table.getColumns().addAll(
                                report,
                                patient,
                                date,
                                source,
                                status,
                                action);

                table.getItems().addAll(
                                new Report(
                                                "Blood Test",
                                                "Priya Sharma",
                                                "07 May 2024\n10:30 AM",
                                                "ThyroCare Lab",
                                                "Normal",
                                                "◉  ↓"),

                                new Report(
                                                "Ultrasound",
                                                "Neha Kulkarni",
                                                "05 May 2024\n09:15 AM",
                                                "CarePlus Imaging",
                                                "Normal",
                                                "◉  ↓"),

                                new Report(
                                                "Urine Test",
                                                "Sneha Patil",
                                                "04 May 2024\n11:20 AM",
                                                "Metropolis Lab",
                                                "Normal",
                                                "◉  ↓"),

                                new Report(
                                                "Thyroid Profile",
                                                "Ayesha Khan",
                                                "03 May 2024\n09:45 AM",
                                                "ThyroCare Lab",
                                                "Low",
                                                "◉  ↓"),

                                new Report(
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

                Scene scene = new Scene(
                                root,
                                Theme.WIDTH,
                                Theme.HEIGHT);

                DoctorDashboard.dashboardStage.setScene(scene);

                DoctorDashboard.dashboardStage.setWidth(
                                Theme.WIDTH);

                DoctorDashboard.dashboardStage.setHeight(
                                Theme.HEIGHT);

                DoctorDashboard.dashboardStage.centerOnScreen();
        }

        public static class Report {

                private final javafx.beans.property.SimpleStringProperty report;
                private final javafx.beans.property.SimpleStringProperty patient;
                private final javafx.beans.property.SimpleStringProperty date;
                private final javafx.beans.property.SimpleStringProperty source;
                private final javafx.beans.property.SimpleStringProperty status;
                private final javafx.beans.property.SimpleStringProperty action;

                public Report(
                                String report,
                                String patient,
                                String date,
                                String source,
                                String status,
                                String action) {

                        this.report = new javafx.beans.property.SimpleStringProperty(report);

                        this.patient = new javafx.beans.property.SimpleStringProperty(patient);

                        this.date = new javafx.beans.property.SimpleStringProperty(date);

                        this.source = new javafx.beans.property.SimpleStringProperty(source);

                        this.status = new javafx.beans.property.SimpleStringProperty(status);

                        this.action = new javafx.beans.property.SimpleStringProperty(action);
                }

                public javafx.beans.property.StringProperty reportProperty() {
                        return report;
                }

                public javafx.beans.property.StringProperty patientProperty() {
                        return patient;
                }

                public javafx.beans.property.StringProperty dateProperty() {
                        return date;
                }

                public javafx.beans.property.StringProperty sourceProperty() {
                        return source;
                }

                public javafx.beans.property.StringProperty statusProperty() {
                        return status;
                }

                public javafx.beans.property.StringProperty actionProperty() {
                        return action;
                }
        }
}