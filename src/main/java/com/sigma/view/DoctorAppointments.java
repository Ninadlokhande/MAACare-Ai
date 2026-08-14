package com.sigma.view;

import com.sigma.controller.DoctorAppointmentsController;
import com.sigma.model.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DoctorAppointments {

        private Scene appointmentPageScene;

        // =========================================================
        // COLORS
        // =========================================================

        private static final String PINK = "#D94A91";
        private static final String LIGHT_PINK = "#FCEAF3";
        private static final String PURPLE = "#754CE0";
        private static final String DARK_TEXT = "#172554";
        private static final String SECONDARY = "#64748B";
        private static final String BORDER = "#E8E8F0";

        // =========================================================
        // CONTROLLER
        // =========================================================

        private final DoctorAppointmentsController controller = new DoctorAppointmentsController();

        // =========================================================
        // GET SCENE
        // =========================================================

        public Scene getAppointmentPageScene(
                        Runnable callBackToDashboard) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: #FAF9FC;");

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox mainContent = new VBox(18);

                mainContent.setPadding(
                                new Insets(25, 35, 25, 35));

                // =====================================================
                // HEADER
                // =====================================================

                BorderPane header = new BorderPane();

                VBox leftHeader = new VBox(6);

                // Breadcrumb

                HBox breadcrumb = new HBox(8);

                Label dashboardCrumb = new Label("Dashboard");

                dashboardCrumb.setFont(
                                Font.font("Arial", 13));

                dashboardCrumb.setTextFill(
                                Color.web("#7B8190"));

                Label arrow = new Label("›");

                arrow.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                arrow.setTextFill(
                                Color.web("#A0A4AE"));

                Label appointmentCrumb = new Label("Appointments");

                appointmentCrumb.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                appointmentCrumb.setTextFill(
                                Color.web(PINK));

                breadcrumb.getChildren().addAll(
                                dashboardCrumb,
                                arrow,
                                appointmentCrumb);

                // Title

                Label title = new Label("Appointments");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                title.setTextFill(
                                Color.web(DARK_TEXT));

                // Subtitle

                Label subtitle = new Label(
                                "Manage and view all patient appointments.");

                subtitle.setFont(
                                Font.font("Arial", 14));

                subtitle.setTextFill(
                                Color.web(SECONDARY));

                leftHeader.getChildren().addAll(
                                breadcrumb,
                                title,
                                subtitle);

                // =====================================================
                // RIGHT HEADER
                // =====================================================

                HBox rightHeader = new HBox(15);

                rightHeader.setAlignment(
                                Pos.TOP_RIGHT);

                Button notification = new Button("🔔");

                notification.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-cursor: hand;");

                Button message = new Button("💬");

                message.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-cursor: hand;");

                DatePicker headerDate = new DatePicker();

                headerDate.setValue(
                                java.time.LocalDate.now());

                headerDate.setPrefSize(
                                180,
                                42);

                headerDate.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E5E1EA;" +
                                                "-fx-border-radius: 10;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                rightHeader.getChildren().addAll(
                                notification,
                                message,
                                headerDate);

                header.setLeft(leftHeader);
                header.setRight(rightHeader);

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button("←  Dashboard");

                backButton.setPrefSize(
                                135,
                                40);

                setBackButtonStyle(
                                backButton,
                                false);

                backButton.setOnMouseEntered(
                                e -> setBackButtonStyle(
                                                backButton,
                                                true));

                backButton.setOnMouseExited(
                                e -> setBackButtonStyle(
                                                backButton,
                                                false));

                backButton.setOnAction(e -> {

                        if (callBackToDashboard != null) {
                                callBackToDashboard.run();
                        }
                });

                VBox headerArea = new VBox(
                                12,
                                header,
                                backButton);

                mainContent.getChildren().add(
                                headerArea);

                // =====================================================
                // TABS
                // =====================================================

                HBox tabs = new HBox(35);

                tabs.setPadding(
                                new Insets(18, 20, 0, 20));

                Label all = createTab(
                                "All Appointments",
                                true);

                Label today = createTab(
                                "Today",
                                false);

                Label upcoming = createTab(
                                "Upcoming",
                                false);

                Label completed = createTab(
                                "Completed",
                                false);

                Label cancelled = createTab(
                                "Cancelled",
                                false);

                tabs.getChildren().addAll(
                                all,
                                today,
                                upcoming,
                                completed,
                                cancelled);

                VBox tabContainer = new VBox();

                tabContainer.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16 16 0 0;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 1 1 0 1;" +
                                                "-fx-border-radius: 16 16 0 0;");

                Region activeLine = new Region();

                activeLine.setPrefHeight(2);
                activeLine.setMaxWidth(155);

                activeLine.setStyle(
                                "-fx-background-color: " + PINK + ";");

                tabContainer.getChildren().addAll(
                                tabs,
                                activeLine);

                // =====================================================
                // FILTER BAR
                // =====================================================

                HBox filterBar = new HBox(12);

                filterBar.setAlignment(
                                Pos.CENTER_LEFT);

                filterBar.setPadding(
                                new Insets(18, 20, 18, 20));

                filterBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 1 1;");

                DatePicker selectDate = new DatePicker();

                selectDate.setPromptText(
                                "Select Date");

                selectDate.setPrefSize(
                                175,
                                42);

                ComboBox<String> statusCombo = new ComboBox<>();

                statusCombo.getItems().addAll(
                                "All Status",
                                "Confirmed",
                                "Pending",
                                "Cancelled");

                statusCombo.setValue(
                                "All Status");

                statusCombo.setPrefSize(
                                170,
                                42);

                ComboBox<String> typeCombo = new ComboBox<>();

                typeCombo.getItems().addAll(
                                "All Appointment Types",
                                "Consultation",
                                "Follow-up",
                                "Ultrasound",
                                "Routine Checkup");

                typeCombo.setValue(
                                "All Appointment Types");

                typeCombo.setPrefSize(
                                205,
                                42);

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "🔍  Search patient...");

                searchField.setPrefSize(
                                250,
                                42);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button clearButton = new Button("Clear");

                clearButton.setPrefSize(
                                70,
                                42);

                clearButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #DED9EA;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-text-fill: #64748B;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-cursor: hand;");

                filterBar.getChildren().addAll(
                                selectDate,
                                statusCombo,
                                typeCombo,
                                searchField,
                                spacer,
                                clearButton);

                // =====================================================
                // TABLE
                // =====================================================

                TableView<Appointment> table = new TableView<>();

                table.setPrefHeight(560);
                table.setMinHeight(450);

                table.setFixedCellSize(75);

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                table.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 1 1;" +
                                                "-fx-background-radius: 0 0 16 16;" +
                                                "-fx-border-radius: 0 0 16 16;" +
                                                "-fx-table-cell-border-color: #F0EDF5;");

                // =====================================================
                // COLUMNS
                // =====================================================

                TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");

                TableColumn<Appointment, String> patientColumn = new TableColumn<>("Patient");

                TableColumn<Appointment, String> typeColumn = new TableColumn<>("Type");

                TableColumn<Appointment, String> statusColumn = new TableColumn<>("Status");

                TableColumn<Appointment, String> paymentColumn = new TableColumn<>("Payment");

                TableColumn<Appointment, Void> actionColumn = new TableColumn<>("Action");

                timeColumn.setCellValueFactory(
                                data -> data.getValue().timeProperty());

                patientColumn.setCellValueFactory(
                                data -> data.getValue().patientProperty());

                typeColumn.setCellValueFactory(
                                data -> data.getValue().typeProperty());

                statusColumn.setCellValueFactory(
                                data -> data.getValue().statusProperty());

                paymentColumn.setCellValueFactory(
                                data -> data.getValue().paymentProperty());

                // =====================================================
                // COLUMN WIDTH
                // =====================================================

                timeColumn.setPrefWidth(120);
                patientColumn.setPrefWidth(300);
                typeColumn.setPrefWidth(170);
                statusColumn.setPrefWidth(150);
                paymentColumn.setPrefWidth(150);
                actionColumn.setPrefWidth(130);

                // =====================================================
                // DATA
                // =====================================================

                table.setItems(
                                controller.getAppointments());

                // =====================================================
                // PATIENT CELL
                // =====================================================

                patientColumn.setCellFactory(
                                column -> new TableCell<Appointment, String>() {

                                        private final HBox box = new HBox(12);

                                        private final Label avatar = new Label("👩🏻");

                                        private final VBox details = new VBox(3);

                                        private final Label name = new Label();

                                        private final Label info = new Label();

                                        {
                                                box.setAlignment(
                                                                Pos.CENTER_LEFT);

                                                avatar.setPrefSize(
                                                                42,
                                                                42);

                                                avatar.setAlignment(
                                                                Pos.CENTER);

                                                avatar.setStyle(
                                                                "-fx-background-color: #FCEAF3;" +
                                                                                "-fx-background-radius: 50;" +
                                                                                "-fx-font-size: 19px;");

                                                name.setFont(
                                                                Font.font(
                                                                                "Arial",
                                                                                FontWeight.BOLD,
                                                                                13));

                                                name.setTextFill(
                                                                Color.web(DARK_TEXT));

                                                info.setFont(
                                                                Font.font(
                                                                                "Arial",
                                                                                11));

                                                info.setTextFill(
                                                                Color.web(SECONDARY));

                                                details.getChildren().addAll(
                                                                name,
                                                                info);

                                                box.getChildren().addAll(
                                                                avatar,
                                                                details);
                                        }

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty || item == null) {

                                                        setGraphic(null);

                                                } else {

                                                        name.setText(item);

                                                        Appointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        info.setText(
                                                                        appointment.getDetails());

                                                        setGraphic(box);
                                                }
                                        }
                                });

                // =====================================================
                // STATUS
                // =====================================================

                statusColumn.setCellFactory(
                                column -> new TableCell<Appointment, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty || item == null) {

                                                        setGraphic(null);

                                                } else {

                                                        Label label = new Label(item);

                                                        label.setFont(
                                                                        Font.font(
                                                                                        "Arial",
                                                                                        FontWeight.BOLD,
                                                                                        11));

                                                        label.setPadding(
                                                                        new Insets(
                                                                                        7,
                                                                                        13,
                                                                                        7,
                                                                                        13));

                                                        if (item.equalsIgnoreCase(
                                                                        "Confirmed")) {

                                                                label.setStyle(
                                                                                "-fx-background-color: #E6F7EF;" +
                                                                                                "-fx-text-fill: #20965F;"
                                                                                                +
                                                                                                "-fx-background-radius: 20;");

                                                        } else if (item.equalsIgnoreCase(
                                                                        "Pending")) {

                                                                label.setStyle(
                                                                                "-fx-background-color: #FFF4E5;" +
                                                                                                "-fx-text-fill: #D68A20;"
                                                                                                +
                                                                                                "-fx-background-radius: 20;");

                                                        } else {

                                                                label.setStyle(
                                                                                "-fx-background-color: #FFE8E8;" +
                                                                                                "-fx-text-fill: #D64545;"
                                                                                                +
                                                                                                "-fx-background-radius: 20;");
                                                        }

                                                        setGraphic(label);

                                                        setAlignment(
                                                                        Pos.CENTER_LEFT);
                                                }
                                        }
                                });

                // =====================================================
                // PAYMENT
                // =====================================================

                paymentColumn.setCellFactory(
                                column -> new TableCell<Appointment, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty || item == null) {

                                                        setGraphic(null);

                                                } else {

                                                        Label label = new Label(item);

                                                        label.setFont(
                                                                        Font.font(
                                                                                        "Arial",
                                                                                        FontWeight.BOLD,
                                                                                        11));

                                                        label.setPadding(
                                                                        new Insets(
                                                                                        7,
                                                                                        13,
                                                                                        7,
                                                                                        13));

                                                        if (item.equalsIgnoreCase(
                                                                        "Paid")) {

                                                                label.setStyle(
                                                                                "-fx-background-color: #E6F7EF;" +
                                                                                                "-fx-text-fill: #20965F;"
                                                                                                +
                                                                                                "-fx-background-radius: 20;");

                                                        } else {

                                                                label.setStyle(
                                                                                "-fx-background-color: #FFF4E5;" +
                                                                                                "-fx-text-fill: #D68A20;"
                                                                                                +
                                                                                                "-fx-background-radius: 20;");
                                                        }

                                                        setGraphic(label);

                                                        setAlignment(
                                                                        Pos.CENTER_LEFT);
                                                }
                                        }
                                });

                // =====================================================
                // ACTION
                // =====================================================

                actionColumn.setCellFactory(
                                column -> new TableCell<Appointment, Void>() {

                                        private final Button view = new Button("View");

                                        {
                                                view.setPrefSize(
                                                                65,
                                                                34);

                                                view.setStyle(
                                                                "-fx-background-color: #F8F8FC;" +
                                                                                "-fx-border-color: #E7E5EE;" +
                                                                                "-fx-border-radius: 8;" +
                                                                                "-fx-background-radius: 8;" +
                                                                                "-fx-text-fill: #64748B;" +
                                                                                "-fx-font-size: 12px;" +
                                                                                "-fx-cursor: hand;");

                                                view.setOnAction(e -> {

                                                        Appointment selected = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showAppointmentDetails(
                                                                        selected);
                                                });
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

                                                        setGraphic(view);

                                                        setAlignment(
                                                                        Pos.CENTER);
                                                }
                                        }
                                });

                table.getColumns().addAll(
                                timeColumn,
                                patientColumn,
                                typeColumn,
                                statusColumn,
                                paymentColumn,
                                actionColumn);

                // =====================================================
                // FILTER LISTENERS
                // =====================================================

                searchField.textProperty().addListener(
                                (obs, oldValue, newValue) -> filterTable(
                                                table,
                                                searchField,
                                                statusCombo,
                                                typeCombo));

                statusCombo.valueProperty().addListener(
                                (obs, oldValue, newValue) -> filterTable(
                                                table,
                                                searchField,
                                                statusCombo,
                                                typeCombo));

                typeCombo.valueProperty().addListener(
                                (obs, oldValue, newValue) -> filterTable(
                                                table,
                                                searchField,
                                                statusCombo,
                                                typeCombo));

                clearButton.setOnAction(e -> {

                        searchField.clear();

                        statusCombo.setValue(
                                        "All Status");

                        typeCombo.setValue(
                                        "All Appointment Types");

                        selectDate.setValue(null);

                        table.setItems(
                                        controller.getAppointments());
                });

                // =====================================================
                // TAB ACTIONS
                // =====================================================

                all.setOnMouseClicked(
                                e -> resetTabs(
                                                all,
                                                today,
                                                upcoming,
                                                completed,
                                                cancelled));

                today.setOnMouseClicked(
                                e -> filterByStatus(
                                                table,
                                                "today"));

                upcoming.setOnMouseClicked(
                                e -> filterByStatus(
                                                table,
                                                "upcoming"));

                completed.setOnMouseClicked(
                                e -> filterByStatus(
                                                table,
                                                "completed"));

                cancelled.setOnMouseClicked(
                                e -> filterByStatus(
                                                table,
                                                "cancelled"));

                // =====================================================
                // ADD CONTENT
                // =====================================================

                mainContent.getChildren().addAll(
                                tabContainer,
                                filterBar,
                                table);

                root.setCenter(
                                mainContent);

                // =====================================================
                // SCENE
                // =====================================================

                appointmentPageScene = new Scene(
                                root,
                                1400,
                                850);

                return appointmentPageScene;
        }

        // =========================================================
        // BACK BUTTON STYLE
        // =========================================================

        private void setBackButtonStyle(
                        Button button,
                        boolean hover) {

                if (hover) {

                        button.setStyle(
                                        "-fx-background-color: " + PURPLE + ";" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-color: " + PURPLE + ";" +
                                                        "-fx-border-width: 1.5;" +
                                                        "-fx-border-radius: 9;" +
                                                        "-fx-background-radius: 9;" +
                                                        "-fx-cursor: hand;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: " + PURPLE + ";" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-color: " + PURPLE + ";" +
                                                        "-fx-border-width: 1.5;" +
                                                        "-fx-border-radius: 9;" +
                                                        "-fx-background-radius: 9;" +
                                                        "-fx-cursor: hand;");
                }
        }

        // =========================================================
        // TAB
        // =========================================================

        private Label createTab(
                        String text,
                        boolean active) {

                Label tab = new Label(text);

                tab.setFont(
                                Font.font(
                                                "Arial",
                                                active
                                                                ? FontWeight.BOLD
                                                                : FontWeight.NORMAL,
                                                13));

                tab.setTextFill(
                                Color.web(
                                                active
                                                                ? PINK
                                                                : SECONDARY));

                tab.setPadding(
                                new Insets(
                                                0,
                                                0,
                                                12,
                                                0));

                tab.setCursor(
                                javafx.scene.Cursor.HAND);

                return tab;
        }

        // =========================================================
        // RESET TABS
        // =========================================================

        private void resetTabs(
                        Label all,
                        Label today,
                        Label upcoming,
                        Label completed,
                        Label cancelled) {

                Label[] tabs = {
                                all,
                                today,
                                upcoming,
                                completed,
                                cancelled
                };

                for (Label tab : tabs) {

                        tab.setTextFill(
                                        Color.web(SECONDARY));

                        tab.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.NORMAL,
                                                        13));
                }

                all.setTextFill(
                                Color.web(PINK));

                all.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
        }

        // =========================================================
        // FILTER TABLE
        // =========================================================

        private void filterTable(
                        TableView<Appointment> table,
                        TextField searchField,
                        ComboBox<String> statusCombo,
                        ComboBox<String> typeCombo) {

                String search = searchField.getText()
                                .trim()
                                .toLowerCase();

                String status = statusCombo.getValue();

                String type = typeCombo.getValue();

                FilteredList<Appointment> filtered = new FilteredList<>(
                                controller.getAppointments());

                filtered.setPredicate(
                                appointment -> {

                                        boolean searchMatch = search.isEmpty()
                                                        ||
                                                        appointment.getPatient()
                                                                        .toLowerCase()
                                                                        .contains(search);

                                        boolean statusMatch = status == null
                                                        ||
                                                        status.equals("All Status")
                                                        ||
                                                        appointment.getStatus()
                                                                        .equalsIgnoreCase(status);

                                        boolean typeMatch = type == null
                                                        ||
                                                        type.equals(
                                                                        "All Appointment Types")
                                                        ||
                                                        appointment.getType()
                                                                        .equalsIgnoreCase(type);

                                        return searchMatch
                                                        && statusMatch
                                                        && typeMatch;
                                });

                table.setItems(filtered);
        }

        // =========================================================
        // TAB FILTER
        // =========================================================

        private void filterByStatus(
                        TableView<Appointment> table,
                        String filter) {

                ObservableList<Appointment> result = FXCollections.observableArrayList();

                for (Appointment appointment : controller.getAppointments()) {

                        if (filter.equals("completed")
                                        &&
                                        appointment.getStatus()
                                                        .equalsIgnoreCase("Completed")) {

                                result.add(appointment);

                        } else if (filter.equals("cancelled")
                                        &&
                                        appointment.getStatus()
                                                        .equalsIgnoreCase("Cancelled")) {

                                result.add(appointment);

                        } else if (filter.equals("upcoming")
                                        &&
                                        !appointment.getStatus()
                                                        .equalsIgnoreCase(
                                                                        "Completed")
                                        &&
                                        !appointment.getStatus()
                                                        .equalsIgnoreCase(
                                                                        "Cancelled")) {

                                result.add(appointment);

                        } else if (filter.equals("today")) {

                                result.add(appointment);
                        }
                }

                table.setItems(result);
        }

        // =========================================================
        // DETAILS
        // =========================================================

        private void showAppointmentDetails(
                        Appointment appointment) {

                if (appointment == null) {
                        return;
                }

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Appointment Details");

                alert.setHeaderText(
                                appointment.getPatient());

                alert.setContentText(
                                "Time: "
                                                + appointment.getTime()
                                                + "\n\n"
                                                + "Details: "
                                                + appointment.getDetails()
                                                + "\n\n"
                                                + "Type: "
                                                + appointment.getType()
                                                + "\n\n"
                                                + "Status: "
                                                + appointment.getStatus()
                                                + "\n\n"
                                                + "Payment: "
                                                + appointment.getPayment());

                alert.showAndWait();
        }
}