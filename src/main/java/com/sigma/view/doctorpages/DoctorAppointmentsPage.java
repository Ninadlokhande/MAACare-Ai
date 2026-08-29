
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class DoctorAppointmentsPage {

        private static TableView<DoctorAppointment> table;

        private static final DoctorAppointmentController controller = new DoctorAppointmentController();

        // =====================================================
        // SHOW
        // =====================================================

        public static void show() {

                VBox root = new VBox(20);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                Theme.applyBackground(root);

                // =====================================================
                // HEADER
                // =====================================================

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

                back.setOnAction(
                                e -> DoctorDashboard.showDashboard());

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // =====================================================
                // FILTER BOX
                // =====================================================

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

                // =====================================================
                // DATE
                // =====================================================

                DatePicker date = new DatePicker();

                date.setPromptText(
                                "Select Date");

                date.setPrefWidth(145);

                // =====================================================
                // STATUS
                // =====================================================

                ComboBox<String> status = new ComboBox<>();

                status.getItems().addAll(
                                "All Status",
                                "Confirmed",
                                "Pending",
                                "Cancelled");

                status.setValue(
                                "All Status");

                status.setPrefWidth(145);

                // =====================================================
                // TYPE
                // =====================================================

                ComboBox<String> type = new ComboBox<>();

                type.getItems().addAll(
                                "All Appointment Types",
                                "Consultation",
                                "Follow-up",
                                "Routine Checkup",
                                "First Consultation",
                                "Ultrasound Follow-up",
                                "Pregnancy Checkup");

                type.setValue(
                                "All Appointment Types");

                type.setPrefWidth(190);

                // =====================================================
                // SEARCH
                // =====================================================

                TextField search = new TextField();

                search.setPromptText(
                                "Search patient...");

                search.setPrefWidth(
                                230);

                // =====================================================
                // CLEAR
                // =====================================================

                Button clear = new Button("Clear");

                clear.setStyle(
                                "-fx-background-color: #F3ECFF;" +
                                                "-fx-text-fill: " +
                                                Theme.PRIMARY + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 9 16;" +
                                                "-fx-cursor: hand;");

                Region filterSpacer = new Region();

                HBox.setHgrow(
                                filterSpacer,
                                Priority.ALWAYS);

                filters.getChildren().addAll(
                                date,
                                status,
                                type,
                                search,
                                filterSpacer,
                                clear);

                // =====================================================
                // TABLE
                // =====================================================

                table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                table.setPlaceholder(
                                new Label(
                                                "No appointments found."));

                // =====================================================
                // COLUMNS
                // =====================================================

                TableColumn<DoctorAppointment, String> dateColumn = new TableColumn<>("Date");

                TableColumn<DoctorAppointment, String> time = new TableColumn<>("Time");

                TableColumn<DoctorAppointment, String> patient = new TableColumn<>("Patient");

                TableColumn<DoctorAppointment, String> appointmentType = new TableColumn<>("Type");

                TableColumn<DoctorAppointment, String> stat = new TableColumn<>("Status");

                TableColumn<DoctorAppointment, String> payment = new TableColumn<>("Payment");

                TableColumn<DoctorAppointment, String> action = new TableColumn<>("Action");

                // =====================================================
                // CELL VALUE FACTORIES
                // =====================================================

                dateColumn.setCellValueFactory(
                                data -> data.getValue()
                                                .dateProperty());

                time.setCellValueFactory(
                                data -> data.getValue()
                                                .timeProperty());

                patient.setCellValueFactory(
                                data -> data.getValue()
                                                .patientProperty());

                appointmentType.setCellValueFactory(
                                data -> data.getValue()
                                                .typeProperty());

                stat.setCellValueFactory(
                                data -> data.getValue()
                                                .statusProperty());

                payment.setCellValueFactory(
                                data -> data.getValue()
                                                .paymentProperty());

                // =====================================================
                // STATUS CELL
                // =====================================================

                stat.setCellFactory(
                                column -> new TableCell<DoctorAppointment, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty ||
                                                                item == null) {

                                                        setText(null);
                                                        setStyle("");

                                                        return;
                                                }

                                                setText(item);

                                                setAlignment(
                                                                Pos.CENTER);

                                                if (item.equalsIgnoreCase(
                                                                "Confirmed")) {

                                                        setStyle(
                                                                        "-fx-text-fill: "
                                                                                        + Theme.GREEN
                                                                                        + ";" +
                                                                                        "-fx-font-weight: bold;");

                                                } else if (item.equalsIgnoreCase(
                                                                "Pending")) {

                                                        setStyle(
                                                                        "-fx-text-fill: "
                                                                                        + Theme.ORANGE
                                                                                        + ";" +
                                                                                        "-fx-font-weight: bold;");

                                                } else {

                                                        setStyle(
                                                                        "-fx-text-fill: #EF4444;" +
                                                                                        "-fx-font-weight: bold;");
                                                }
                                        }
                                });

                // =====================================================
                // PAYMENT CELL
                // =====================================================

                payment.setCellFactory(
                                column -> new TableCell<DoctorAppointment, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty ||
                                                                item == null) {

                                                        setText(null);
                                                        setStyle("");

                                                        return;
                                                }

                                                setText(item);

                                                setAlignment(
                                                                Pos.CENTER);

                                                if (item.equalsIgnoreCase(
                                                                "Paid")) {

                                                        setStyle(
                                                                        "-fx-text-fill: "
                                                                                        + Theme.GREEN
                                                                                        + ";" +
                                                                                        "-fx-font-weight: bold;");

                                                } else if (item.equalsIgnoreCase(
                                                                "Pending")) {

                                                        setStyle(
                                                                        "-fx-text-fill: "
                                                                                        + Theme.ORANGE
                                                                                        + ";" +
                                                                                        "-fx-font-weight: bold;");

                                                } else {

                                                        setStyle(
                                                                        "-fx-text-fill: #EF4444;" +
                                                                                        "-fx-font-weight: bold;");
                                                }
                                        }
                                });

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                action.setCellFactory(
                                column -> new TableCell<DoctorAppointment, String>() {

                                        private final Button viewButton = new Button("👁");

                                        private final Button editButton = new Button("✏");

                                        private final Button deleteButton = new Button("🗑");

                                        private final HBox buttons = new HBox(6);

                                        {

                                                // ---------------------------------
                                                // VIEW
                                                // ---------------------------------

                                                viewButton.setStyle(
                                                                "-fx-background-color: #E0F2FE;" +
                                                                                "-fx-text-fill: #0284C7;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                viewButton.setTooltip(
                                                                new Tooltip(
                                                                                "View Appointment"));

                                                // ---------------------------------
                                                // EDIT
                                                // ---------------------------------

                                                editButton.setStyle(
                                                                "-fx-background-color: #FFF4DE;" +
                                                                                "-fx-text-fill: #F59E0B;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                editButton.setTooltip(
                                                                new Tooltip(
                                                                                "Edit Appointment"));

                                                // ---------------------------------
                                                // DELETE
                                                // ---------------------------------

                                                deleteButton.setStyle(
                                                                "-fx-background-color: #FEE2E2;" +
                                                                                "-fx-text-fill: #DC2626;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                deleteButton.setTooltip(
                                                                new Tooltip(
                                                                                "Delete Appointment"));

                                                // ---------------------------------
                                                // VIEW ACTION
                                                // ---------------------------------

                                                viewButton.setOnAction(e -> {

                                                        DoctorAppointment appointment = getSelectedAppointment();

                                                        if (appointment != null) {

                                                                controller.viewAppointment(
                                                                                appointment);
                                                        }
                                                });

                                                // ---------------------------------
                                                // EDIT ACTION
                                                // ---------------------------------

                                                editButton.setOnAction(e -> {

                                                        DoctorAppointment appointment = getSelectedAppointment();

                                                        if (appointment != null) {

                                                                controller.editAppointment(
                                                                                appointment);
                                                        }
                                                });

                                                // ---------------------------------
                                                // DELETE ACTION
                                                // ---------------------------------

                                                deleteButton.setOnAction(e -> {

                                                        DoctorAppointment appointment = getSelectedAppointment();

                                                        if (appointment == null) {
                                                                return;
                                                        }

                                                        Alert confirm = new Alert(
                                                                        Alert.AlertType.CONFIRMATION);

                                                        confirm.setTitle(
                                                                        "Delete Appointment");

                                                        confirm.setHeaderText(
                                                                        "Delete this appointment?");

                                                        confirm.setContentText(
                                                                        "Patient: "
                                                                                        + appointment
                                                                                                        .getPatient()
                                                                                        + "\nDate: "
                                                                                        + appointment
                                                                                                        .getDate()
                                                                                        + "\nTime: "
                                                                                        + appointment
                                                                                                        .getTime());

                                                        confirm.showAndWait()
                                                                        .ifPresent(
                                                                                        result -> {

                                                                                                if (result == ButtonType.OK) {

                                                                                                        boolean deleted = controller
                                                                                                                        .deleteAppointment(
                                                                                                                                        appointment);

                                                                                                        if (deleted) {

                                                                                                                applyFilters(
                                                                                                                                search,
                                                                                                                                status,
                                                                                                                                type,
                                                                                                                                date);
                                                                                                        }
                                                                                                }
                                                                                        });
                                                });

                                                buttons.setAlignment(
                                                                Pos.CENTER);

                                                buttons.getChildren()
                                                                .addAll(
                                                                                viewButton,
                                                                                editButton,
                                                                                deleteButton);
                                        }

                                        private DoctorAppointment getSelectedAppointment() {

                                                if (getIndex() < 0 ||
                                                                getIndex() >= getTableView()
                                                                                .getItems()
                                                                                .size()) {

                                                        return null;
                                                }

                                                return getTableView()
                                                                .getItems()
                                                                .get(getIndex());
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

                // =====================================================
                // ADD COLUMNS
                // =====================================================

                table.getColumns().addAll(
                                dateColumn,
                                time,
                                patient,
                                appointmentType,
                                stat,
                                payment,
                                action);

                // =====================================================
                // INITIAL DATA
                // =====================================================

                table.setItems(
                                FXCollections.observableArrayList(
                                                controller.getAppointments()));

                // =====================================================
                // SEARCH LISTENER
                // =====================================================

                search.textProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters(
                                                search,
                                                status,
                                                type,
                                                date));

                // =====================================================
                // STATUS LISTENER
                // =====================================================

                status.valueProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters(
                                                search,
                                                status,
                                                type,
                                                date));

                // =====================================================
                // TYPE LISTENER
                // =====================================================

                type.valueProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters(
                                                search,
                                                status,
                                                type,
                                                date));

                // =====================================================
                // DATE LISTENER
                // =====================================================

                date.valueProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters(
                                                search,
                                                status,
                                                type,
                                                date));

                // =====================================================
                // CLEAR
                // =====================================================

                clear.setOnAction(e -> {

                        search.clear();

                        status.setValue(
                                        "All Status");

                        type.setValue(
                                        "All Appointment Types");

                        date.setValue(null);

                        table.setItems(
                                        FXCollections.observableArrayList(
                                                        controller.getAppointments()));
                });

                // =====================================================
                // TABLE GROW
                // =====================================================

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                // =====================================================
                // ROOT CONTENT
                // =====================================================

                root.getChildren().addAll(
                                header,
                                filters,
                                table);

                // =====================================================
                // SCENE
                // =====================================================

                Scene appointmentsScene = new Scene(root);

                DoctorDashboard.changeScene(
                                appointmentsScene);
        }

        // =====================================================
        // APPLY ALL FILTERS
        // =====================================================

        private static void applyFilters(
                        TextField search,
                        ComboBox<String> status,
                        ComboBox<String> type,
                        DatePicker date) {

                String searchText = search.getText() == null
                                ? ""
                                : search.getText()
                                                .trim()
                                                .toLowerCase();

                String selectedStatus = status.getValue();

                String selectedType = type.getValue();

                LocalDate selectedDate = date.getValue();

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                for (DoctorAppointment appointment : controller.getAppointments()) {

                        // ---------------------------------------------
                        // SEARCH
                        // ---------------------------------------------

                        boolean matchesSearch = searchText.isEmpty()
                                        ||
                                        safeContains(
                                                        appointment.getPatient(),
                                                        searchText)
                                        ||
                                        safeContains(
                                                        appointment.getType(),
                                                        searchText)
                                        ||
                                        safeContains(
                                                        appointment.getTime(),
                                                        searchText)
                                        ||
                                        safeContains(
                                                        appointment.getStatus(),
                                                        searchText)
                                        ||
                                        safeContains(
                                                        appointment.getPayment(),
                                                        searchText)
                                        ||
                                        safeContains(
                                                        appointment.getDate(),
                                                        searchText);

                        // ---------------------------------------------
                        // STATUS
                        // ---------------------------------------------

                        boolean matchesStatus = selectedStatus == null
                                        ||
                                        selectedStatus.equals(
                                                        "All Status")
                                        ||
                                        appointment.getStatus()
                                                        .equalsIgnoreCase(
                                                                        selectedStatus);

                        // ---------------------------------------------
                        // TYPE
                        // ---------------------------------------------

                        boolean matchesType = selectedType == null
                                        ||
                                        selectedType.equals(
                                                        "All Appointment Types")
                                        ||
                                        appointment.getType()
                                                        .equalsIgnoreCase(
                                                                        selectedType);

                        // ---------------------------------------------
                        // DATE
                        // ---------------------------------------------

                        boolean matchesDate = true;

                        if (selectedDate != null) {

                                String selectedDateString = selectedDate.toString();

                                matchesDate = appointment.getDate()
                                                .equals(
                                                                selectedDateString);
                        }

                        // ---------------------------------------------
                        // ADD
                        // ---------------------------------------------

                        if (matchesSearch
                                        && matchesStatus
                                        && matchesType
                                        && matchesDate) {

                                filtered.add(
                                                appointment);
                        }
                }

                table.setItems(
                                filtered);
        }

        // =====================================================
        // SAFE CONTAINS
        // =====================================================

        private static boolean safeContains(
                        String value,
                        String search) {

                return value != null
                                &&
                                value.toLowerCase()
                                                .contains(search);
        }
}
