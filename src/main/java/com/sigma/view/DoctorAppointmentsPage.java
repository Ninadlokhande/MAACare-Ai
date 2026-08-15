package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DoctorAppointmentsPage {

        public static void show() {

                VBox root = new VBox(20);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                Theme.applyBackground(root);

                // ==============================
                // HEADER
                // ==============================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "All Appointments",
                                "Manage and view all patient appointments.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // ==============================
                // FILTER BAR
                // ==============================

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
                                "Search patient...");

                search.setPrefWidth(230);

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "All Status",
                                "Confirmed",
                                "Pending",
                                "Cancelled");

                status.setValue("All Status");

                ComboBox<String> type = new ComboBox<>();

                type.getItems().addAll(
                                "All Appointment Types",
                                "Consultation",
                                "Follow-up");

                type.setValue(
                                "All Appointment Types");

                DatePicker date = new DatePicker();

                date.setPromptText(
                                "Select Date");

                filters.getChildren().addAll(
                                date,
                                status,
                                type,
                                search);

                // ==============================
                // TABLE
                // ==============================

                TableView<Appointment> table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<Appointment, String> time = new TableColumn<>("Time");

                TableColumn<Appointment, String> patient = new TableColumn<>("Patient");

                TableColumn<Appointment, String> appointmentType = new TableColumn<>("Type");

                TableColumn<Appointment, String> stat = new TableColumn<>("Status");

                TableColumn<Appointment, String> payment = new TableColumn<>("Payment");

                TableColumn<Appointment, String> action = new TableColumn<>("Action");

                time.setCellValueFactory(
                                data -> data.getValue().timeProperty());

                patient.setCellValueFactory(
                                data -> data.getValue().patientProperty());

                appointmentType.setCellValueFactory(
                                data -> data.getValue().typeProperty());

                stat.setCellValueFactory(
                                data -> data.getValue().statusProperty());

                payment.setCellValueFactory(
                                data -> data.getValue().paymentProperty());

                action.setCellValueFactory(
                                data -> data.getValue().actionProperty());

                table.getColumns().addAll(
                                time,
                                patient,
                                appointmentType,
                                stat,
                                payment,
                                action);

                table.getItems().addAll(
                                new Appointment(
                                                "09:30 AM",
                                                "Priya Sharma",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new Appointment(
                                                "10:15 AM",
                                                "Neha Kulkarni",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new Appointment(
                                                "11:00 AM",
                                                "Sneha Patil",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new Appointment(
                                                "12:00 PM",
                                                "Ritika Singh",
                                                "Follow-up",
                                                "Pending",
                                                "Pending",
                                                "◉"),

                                new Appointment(
                                                "02:00 PM",
                                                "Ayesha Khan",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new Appointment(
                                                "03:00 PM",
                                                "Pooja Iyer",
                                                "Consultation",
                                                "Cancelled",
                                                "Refunded",
                                                "◉"));

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

        // =====================================================
        // MODEL
        // =====================================================

        public static class Appointment {

                private final javafx.beans.property.SimpleStringProperty time;
                private final javafx.beans.property.SimpleStringProperty patient;
                private final javafx.beans.property.SimpleStringProperty type;
                private final javafx.beans.property.SimpleStringProperty status;
                private final javafx.beans.property.SimpleStringProperty payment;
                private final javafx.beans.property.SimpleStringProperty action;

                public Appointment(
                                String time,
                                String patient,
                                String type,
                                String status,
                                String payment,
                                String action) {

                        this.time = new javafx.beans.property.SimpleStringProperty(time);

                        this.patient = new javafx.beans.property.SimpleStringProperty(patient);

                        this.type = new javafx.beans.property.SimpleStringProperty(type);

                        this.status = new javafx.beans.property.SimpleStringProperty(status);

                        this.payment = new javafx.beans.property.SimpleStringProperty(payment);

                        this.action = new javafx.beans.property.SimpleStringProperty(action);
                }

                public javafx.beans.property.StringProperty timeProperty() {
                        return time;
                }

                public javafx.beans.property.StringProperty patientProperty() {
                        return patient;
                }

                public javafx.beans.property.StringProperty typeProperty() {
                        return type;
                }

                public javafx.beans.property.StringProperty statusProperty() {
                        return status;
                }

                public javafx.beans.property.StringProperty paymentProperty() {
                        return payment;
                }

                public javafx.beans.property.StringProperty actionProperty() {
                        return action;
                }
        }
}