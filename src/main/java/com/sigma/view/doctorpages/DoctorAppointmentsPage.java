package com.sigma.view.doctorpages;

import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DoctorAppointmentsPage {

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
                                "Search patient...");

                search.setPrefWidth(230);

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "All Status",
                                "Confirmed",
                                "Pending",
                                "Cancelled");

                status.setValue(
                                "All Status");

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

                // =================================================
                // TABLE
                // =================================================

                TableView<DoctorAppointment> table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<DoctorAppointment, String> time = new TableColumn<>("Time");

                TableColumn<DoctorAppointment, String> patient = new TableColumn<>("Patient");

                TableColumn<DoctorAppointment, String> appointmentType = new TableColumn<>("Type");

                TableColumn<DoctorAppointment, String> stat = new TableColumn<>("Status");

                TableColumn<DoctorAppointment, String> payment = new TableColumn<>("Payment");

                TableColumn<DoctorAppointment, String> action = new TableColumn<>("Action");

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

                action.setCellFactory(column -> new TableCell<DoctorAppointment, String>() {

                        private final Button viewButton = new Button("👁");

                        private final Button editButton = new Button("✏️");

                        private final HBox buttons = new HBox(6);

                        {
                                // ==========================
                                // VIEW BUTTON
                                // ==========================

                                viewButton.setStyle(
                                                "-fx-background-color: #E0F2FE;" +
                                                                "-fx-text-fill: #0284C7;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-background-radius: 7;" +
                                                                "-fx-padding: 5 9 5 9;" +
                                                                "-fx-cursor: hand;");
                                viewButton.setTooltip(
                                                new Tooltip("View Appointment"));

                                // ==========================
                                // EDIT BUTTON
                                // ==========================

                                editButton.setStyle(
                                                "-fx-background-color: #FFF4DE;" +
                                                                "-fx-text-fill: #8B5CF6;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-background-radius: 7;" +
                                                                "-fx-padding: 5 9 5 9;" +
                                                                "-fx-cursor: hand;");

                                editButton.setTooltip(
                                                new Tooltip("Edit Appointment"));

                                // ==========================
                                // VIEW ACTION
                                // ==========================

                                viewButton.setOnAction(e -> {

                                        DoctorAppointment appointment = getTableView()
                                                        .getItems()
                                                        .get(getIndex());

                                        System.out.println(
                                                        "Viewing: " +
                                                                        appointment.patientProperty().get());
                                });

                                // ==========================
                                // EDIT ACTION
                                // ==========================

                                editButton.setOnAction(e -> {

                                        DoctorAppointment appointment = getTableView()
                                                        .getItems()
                                                        .get(getIndex());

                                        System.out.println(
                                                        "Editing: " +
                                                                        appointment.patientProperty().get());
                                });

                                buttons.setAlignment(
                                                Pos.CENTER);

                                buttons.getChildren().addAll(
                                                viewButton,
                                                editButton);
                        }

                        @Override
                        protected void updateItem(
                                        String item,
                                        boolean empty) {

                                super.updateItem(item, empty);

                                if (empty) {
                                        setGraphic(null);
                                } else {
                                        setGraphic(buttons);
                                        setAlignment(Pos.CENTER);
                                }
                        }
                });

                table.getColumns().addAll(
                                time,
                                patient,
                                appointmentType,
                                stat,
                                payment,
                                action);

                table.getItems().addAll(

                                new DoctorAppointment(
                                                "09:30 AM",
                                                "Priya Sharma",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new DoctorAppointment(
                                                "10:15 AM",
                                                "Neha Kulkarni",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new DoctorAppointment(
                                                "11:00 AM",
                                                "Sneha Patil",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new DoctorAppointment(
                                                "12:00 PM",
                                                "Ritika Singh",
                                                "Follow-up",
                                                "Pending",
                                                "Pending",
                                                "◉"),

                                new DoctorAppointment(
                                                "02:00 PM",
                                                "Ayesha Khan",
                                                "Consultation",
                                                "Confirmed",
                                                "Paid",
                                                "◉"),

                                new DoctorAppointment(
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

                // =================================================
                // NEW SCENE
                // =================================================

                Scene appointmentsScene = new Scene(root);

                // =================================================
                // RUNNABLE
                // =================================================

                Runnable openAppointmentsPage = () -> DoctorDashboard.changeScene(
                                appointmentsScene);

                openAppointmentsPage.run();
        }
}
