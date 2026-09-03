package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class DoctorAppointmentsPage {

        // =========================================================
        // COLORS
        // =========================================================

        private static final String PINK = "#E84A87";
        private static final String LIGHT_PINK = "#FFF0F6";
        private static final String PURPLE = "#C5306D";
        private static final String LIGHT_PURPLE = "#FFE3EE";
        private static final String DARK_TEXT = "#3B2140";
        private static final String SECONDARY = "#806A78";
        private static final String BORDER = "#F0D8E3";
        private static final String PAGE_BACKGROUND = "#FFF9FB";

        // =========================================================
        // TABLE
        // =========================================================

        private static TableView<DoctorAppointment> table;

        // =========================================================
        // FILTERS
        // =========================================================

        private static TextField searchField;
        private static ComboBox<String> statusCombo;
        private static ComboBox<String> typeCombo;

        // =========================================================
        // REFRESH BUTTON
        // =========================================================

        private static Button refreshButton;

        // =========================================================
        // CONTROLLER
        // =========================================================

        private static final DoctorAppointmentController controller = new DoctorAppointmentController();

        // =========================================================
        // SHOW PAGE
        // =========================================================

        public static void show() {

                try {
                        controller.refreshAppointments();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + PAGE_BACKGROUND + ";");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox(18);

                header.setPadding(
                                new Insets(20, 25, 20, 25));

                header.setAlignment(Pos.CENTER_LEFT);

                header.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: " + BORDER + ";"
                                                + "-fx-border-width: 0 0 1 0;");

                VBox titleBox = new VBox(4);

                Label title = new Label("Appointments");

                title.setStyle(
                                "-fx-font-size: 26px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: " + DARK_TEXT + ";");

                Label subtitle = new Label(
                                "Manage and view all patient appointments");

                subtitle.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-text-fill: " + SECONDARY + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =====================================================
                // REFRESH
                // =====================================================

                refreshButton = new Button("↻ Refresh");

                styleRefreshButton();

                refreshButton.setOnAction(
                                e -> refreshAppointmentsFromFirebase());

                // =====================================================
                // ADD
                // =====================================================

                Button add = new Button("+ Add Appointment");

                add.setStyle(
                                "-fx-background-color: " + PINK + ";"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 10 18 10 18;"
                                                + "-fx-cursor: hand;");

                add.setOnAction(
                                e -> showAddAppointmentDialog());

                // =====================================================
                // BACK
                // =====================================================

                header.getChildren().addAll(
                                titleBox,
                                spacer,
                                refreshButton,
                                add);

                root.setTop(header);
                root.setLeft(DoctorDashboard.createSidebar("Appointments"));

                // =====================================================
                // FILTER BOX
                // =====================================================

                HBox filterBox = new HBox(14);

                filterBox.setPadding(
                                new Insets(20, 25, 10, 25));

                filterBox.setAlignment(Pos.CENTER_LEFT);

                // =====================================================
                // SEARCH
                // =====================================================

                searchField = new TextField();

                searchField.setPromptText(
                                "Search patient, type, date...");

                searchField.setPrefWidth(300);

                searchField.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: " + BORDER + ";"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 10;");

                // =====================================================
                // STATUS
                // =====================================================

                statusCombo = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "All",
                                                "Confirmed",
                                                "Pending",
                                                "Completed",
                                                "Cancelled"));

                statusCombo.setValue("All");
                statusCombo.setPrefWidth(150);

                // =====================================================
                // TYPE
                // =====================================================

                typeCombo = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "All",
                                                "Consultation",
                                                "Follow-up",
                                                "Emergency",
                                                "Check-up"));

                typeCombo.setValue("All");
                typeCombo.setPrefWidth(150);

                filterBox.getChildren().addAll(
                                searchField,
                                statusCombo,
                                typeCombo);

                // =====================================================
                // TABLE
                // =====================================================

                table = new TableView<>();

                table.setMaxWidth(Double.MAX_VALUE);
                table.setMaxHeight(Double.MAX_VALUE);

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

                table.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: " + BORDER + ";"
                                                + "-fx-border-radius: 10;"
                                                + "-fx-background-radius: 10;");

                // =====================================================
                // TIME
                // =====================================================

                TableColumn<DoctorAppointment, String> timeCol = new TableColumn<>("Time");

                timeCol.setCellValueFactory(
                                new PropertyValueFactory<>("time"));

                // =====================================================
                // PATIENT
                // =====================================================

                TableColumn<DoctorAppointment, String> patientCol = new TableColumn<>("Patient");

                patientCol.setCellValueFactory(
                                new PropertyValueFactory<>("patient"));

                // =====================================================
                // TYPE
                // =====================================================

                TableColumn<DoctorAppointment, String> typeCol = new TableColumn<>("Type");

                typeCol.setCellValueFactory(
                                new PropertyValueFactory<>("type"));

                // =====================================================
                // STATUS
                // =====================================================

                TableColumn<DoctorAppointment, String> statusCol = new TableColumn<>("Status");

                statusCol.setCellValueFactory(
                                new PropertyValueFactory<>("status"));

                // =====================================================
                // PAYMENT
                // =====================================================

                TableColumn<DoctorAppointment, String> paymentCol = new TableColumn<>("Payment");

                paymentCol.setCellValueFactory(
                                new PropertyValueFactory<>("payment"));

                // =====================================================
                // ACTION
                // =====================================================

                TableColumn<DoctorAppointment, Void> actionCol = new TableColumn<>("Action");

                actionCol.setPrefWidth(200);

                actionCol.setCellFactory(
                                column -> new TableCell<>() {

                                        private final Button view = new Button("View");

                                        private final Button edit = new Button("Edit");

                                        private final Button delete = new Button("Delete");

                                        private final HBox buttons = new HBox(7);

                                        {
                                                buttons.setAlignment(Pos.CENTER);

                                                buttons.getChildren().addAll(
                                                                view,
                                                                edit,
                                                                delete);

                                                view.setStyle(
                                                                "-fx-background-color: " + LIGHT_PINK + ";"
                                                                                + "-fx-text-fill: " + PURPLE + ";"
                                                                                + "-fx-font-weight: bold;"
                                                                                + "-fx-background-radius: 6;"
                                                                                + "-fx-cursor: hand;");

                                                edit.setStyle(
                                                                "-fx-background-color: " + LIGHT_PURPLE + ";"
                                                                                + "-fx-text-fill: " + PURPLE + ";"
                                                                                + "-fx-font-weight: bold;"
                                                                                + "-fx-background-radius: 6;"
                                                                                + "-fx-cursor: hand;");

                                                delete.setStyle(
                                                                "-fx-background-color: #FFF0F0;"
                                                                                + "-fx-text-fill: #D93636;"
                                                                                + "-fx-font-weight: bold;"
                                                                                + "-fx-background-radius: 6;"
                                                                                + "-fx-cursor: hand;");

                                                // =========================================
                                                // VIEW
                                                // =========================================

                                                view.setOnAction(e -> {

                                                        if (getIndex() < 0 ||
                                                                        getIndex() >= getTableView()
                                                                                        .getItems().size()) {
                                                                return;
                                                        }

                                                        DoctorAppointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showAppointmentDetails(
                                                                        appointment);
                                                });

                                                // =========================================
                                                // EDIT
                                                // =========================================

                                                edit.setOnAction(e -> {

                                                        if (getIndex() < 0 ||
                                                                        getIndex() >= getTableView()
                                                                                        .getItems().size()) {
                                                                return;
                                                        }

                                                        DoctorAppointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showEditAppointmentDialog(
                                                                        appointment);
                                                });

                                                // =========================================
                                                // DELETE
                                                // =========================================

                                                delete.setOnAction(e -> {

                                                        if (getIndex() < 0 ||
                                                                        getIndex() >= getTableView()
                                                                                        .getItems().size()) {
                                                                return;
                                                        }

                                                        DoctorAppointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        Alert confirm = new Alert(
                                                                        Alert.AlertType.CONFIRMATION);

                                                        confirm.setTitle(
                                                                        "Delete Appointment");

                                                        confirm.setHeaderText(
                                                                        "Delete this appointment?");

                                                        confirm.setContentText(
                                                                        "Patient: "
                                                                                        + appointment.getPatient());

                                                        confirm.showAndWait()
                                                                        .ifPresent(result -> {

                                                                                if (result == ButtonType.OK) {

                                                                                        try {

                                                                                                boolean deleted = controller
                                                                                                                .deleteAppointment(
                                                                                                                                appointment);

                                                                                                if (deleted) {

                                                                                                        refreshAppointmentTable();

                                                                                                        showSuccess(
                                                                                                                        "Appointment Deleted",
                                                                                                                        "Appointment deleted successfully.");

                                                                                                } else {

                                                                                                        showError(
                                                                                                                        "Unable to delete appointment.");
                                                                                                }

                                                                                        } catch (Exception ex) {

                                                                                                ex.printStackTrace();

                                                                                                showError(
                                                                                                                "Delete failed.\n\n"
                                                                                                                                + ex.getMessage());
                                                                                        }
                                                                                }
                                                                        });
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
                                                        setGraphic(buttons);
                                                }
                                        }
                                });

                table.getColumns().addAll(
                                timeCol,
                                patientCol,
                                typeCol,
                                statusCol,
                                paymentCol,
                                actionCol);

                // =====================================================
                // FILTER LISTENERS
                // =====================================================

                searchField.textProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter());

                statusCombo.valueProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter());

                typeCombo.valueProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter());

                // =====================================================
                // INITIAL DATA
                // =====================================================

                applyFilter();

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox(10);

                content.setPadding(
                                new Insets(0, 25, 25, 25));

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                content.getChildren().addAll(
                                filterBox,
                                table);

                root.setCenter(content);

                // =====================================================
                // SCENE
                // =====================================================

                Scene appointmentsScene = new Scene(
                                root,
                                1100,
                                700);

                DoctorDashboard.changeScene(
                                appointmentsScene);
        }

        // =========================================================
        // REFRESH APPOINTMENT TABLE
        // =========================================================

        private static void refreshAppointmentTable() {

                try {

                        controller.refreshAppointments();

                        applyFilter();

                        if (table != null) {
                                table.refresh();
                        }

                        System.out.println(
                                        "[APPOINTMENT PAGE] Latest Firestore data loaded.");

                } catch (Exception e) {

                        System.out.println(
                                        "[APPOINTMENT PAGE ERROR] Refresh failed.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // REFRESH BUTTON STYLE
        // =========================================================

        private static void styleRefreshButton() {

                if (refreshButton == null) {
                        return;
                }

                refreshButton.setStyle(
                                "-fx-background-color: " + LIGHT_PINK + ";"
                                                + "-fx-text-fill: " + PINK + ";"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 10 16 10 16;"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // REFRESH FROM FIREBASE
        // =========================================================

        private static void refreshAppointmentsFromFirebase() {

                if (refreshButton == null) {
                        return;
                }

                refreshButton.setDisable(true);
                refreshButton.setText("↻ Refreshing...");

                Task<Void> refreshTask = new Task<>() {

                        @Override
                        protected Void call() {

                                controller.refreshAppointments();

                                return null;
                        }
                };

                refreshTask.setOnSucceeded(e -> {

                        applyFilter();

                        if (table != null) {
                                table.refresh();
                        }

                        refreshButton.setText("↻ Refresh");
                        refreshButton.setDisable(false);

                        styleRefreshButton();
                });

                refreshTask.setOnFailed(e -> {

                        Throwable exception = refreshTask.getException();

                        if (exception != null) {
                                exception.printStackTrace();
                        }

                        Platform.runLater(() -> {

                                refreshButton.setText("↻ Refresh");
                                refreshButton.setDisable(false);

                                styleRefreshButton();

                                showError(
                                                "Unable to refresh appointments.\n\n"
                                                                + "Please check your Firebase connection.");
                        });
                });

                Thread thread = new Thread(refreshTask);

                thread.setDaemon(true);
                thread.setName("Appointment-Refresh-Thread");
                thread.start();
        }

        // =========================================================
        // APPLY FILTER
        // =========================================================

        private static void applyFilter() {

                if (table == null) {
                        return;
                }

                String search = searchField == null ||
                                searchField.getText() == null
                                                ? ""
                                                : searchField.getText()
                                                                .trim()
                                                                .toLowerCase();

                String selectedStatus = statusCombo == null
                                ? "All"
                                : statusCombo.getValue();

                String selectedType = typeCombo == null
                                ? "All"
                                : typeCombo.getValue();

                ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                for (DoctorAppointment appointment : controller.getAppointments()) {

                        if (appointment == null) {
                                continue;
                        }

                        String patient = safeLower(appointment.getPatient());

                        String patientId = safeLower(appointment.getPatientId());

                        String type = safeLower(appointment.getType());

                        String status = safeLower(appointment.getStatus());

                        String date = safeLower(appointment.getDate());

                        String time = safeLower(appointment.getTime());

                        boolean searchMatch = search.isEmpty()
                                        || patient.contains(search)
                                        || patientId.contains(search)
                                        || type.contains(search)
                                        || status.contains(search)
                                        || date.contains(search)
                                        || time.contains(search);

                        boolean statusMatch = selectedStatus == null
                                        || selectedStatus.equals("All")
                                        || selectedStatus.equalsIgnoreCase(
                                                        appointment.getStatus());

                        boolean typeMatch = selectedType == null
                                        || selectedType.equals("All")
                                        || selectedType.equalsIgnoreCase(
                                                        appointment.getType());

                        if (searchMatch &&
                                        statusMatch &&
                                        typeMatch) {

                                filtered.add(appointment);
                        }
                }

                table.setItems(filtered);
                table.refresh();
        }

        // =========================================================
        // SAFE LOWER
        // =========================================================

        private static String safeLower(String value) {

                if (value == null) {
                        return "";
                }

                return value.trim().toLowerCase();
        }

        // =========================================================
        // ERROR
        // =========================================================

        private static void showError(String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Appointment Error");

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // SUCCESS
        // =========================================================

        private static void showSuccess(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);
                alert.setHeaderText(title);
                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // VIEW DETAILS
        // =========================================================

        private static void showAppointmentDetails(
                        DoctorAppointment appointment) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Appointment Details");

                alert.setHeaderText(
                                appointment.getPatient());

                alert.setContentText(

                                "Appointment ID: "
                                                + appointment.getAppointmentId()

                                                + "\n\nPatient ID: "
                                                + appointment.getPatientId()

                                                + "\n\nDate: "
                                                + appointment.getDate()

                                                + "\n\nTime: "
                                                + appointment.getTime()

                                                + "\n\nType: "
                                                + appointment.getType()

                                                + "\n\nStatus: "
                                                + appointment.getStatus()

                                                + "\n\nPayment: "
                                                + appointment.getPayment());

                alert.showAndWait();
        }

        // =========================================================
        // EDIT APPOINTMENT
        // =========================================================

        private static void showEditAppointmentDialog(
                        DoctorAppointment appointment) {

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Edit Appointment");

                dialog.setHeaderText(
                                "Edit Appointment Details");

                GridPane grid = new GridPane();

                grid.setHgap(12);
                grid.setVgap(12);
                grid.setPadding(new Insets(20));

                // =====================================================
                // PATIENT ID
                // =====================================================

                TextField patientId = new TextField(
                                appointment.getPatientId());

                patientId.setPrefWidth(260);

                // =====================================================
                // PATIENT NAME
                // =====================================================

                TextField patient = new TextField(
                                appointment.getPatient());

                patient.setPrefWidth(260);

                // =====================================================
                // DATE
                // =====================================================

                DatePicker date = new DatePicker();

                try {

                        if (appointment.getDate() != null &&
                                        !appointment.getDate()
                                                        .trim()
                                                        .isEmpty()) {

                                date.setValue(
                                                LocalDate.parse(
                                                                appointment.getDate()
                                                                                .trim()));
                        }

                } catch (Exception ex) {

                        System.out.println(
                                        "[EDIT] Date parse failed: "
                                                        + appointment.getDate());
                }

                // =====================================================
                // TIME
                // =====================================================

                TextField time = new TextField(
                                appointment.getTime());

                time.setPrefWidth(260);

                // =====================================================
                // TYPE
                // =====================================================

                ComboBox<String> type = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Consultation",
                                                "Follow-up",
                                                "Emergency",
                                                "Check-up"));

                if (appointment.getType() != null &&
                                !appointment.getType()
                                                .trim()
                                                .isEmpty()) {

                        type.setValue(
                                        appointment.getType());

                } else {

                        type.setValue(
                                        "Consultation");
                }

                type.setPrefWidth(260);

                // =====================================================
                // STATUS
                // =====================================================

                ComboBox<String> status = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Pending",
                                                "Confirmed",
                                                "Completed",
                                                "Cancelled"));

                if (appointment.getStatus() != null &&
                                !appointment.getStatus()
                                                .trim()
                                                .isEmpty()) {

                        status.setValue(
                                        appointment.getStatus());

                } else {

                        status.setValue(
                                        "Pending");
                }

                status.setPrefWidth(260);

                // =====================================================
                // PAYMENT
                // =====================================================

                ComboBox<String> payment = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Paid",
                                                "Unpaid",
                                                "Pending"));

                if (appointment.getPayment() != null &&
                                !appointment.getPayment()
                                                .trim()
                                                .isEmpty()) {

                        payment.setValue(
                                        appointment.getPayment());

                } else {

                        payment.setValue(
                                        "Unpaid");
                }

                payment.setPrefWidth(260);

                // =====================================================
                // GRID
                // =====================================================

                grid.add(
                                new Label("Patient ID:"),
                                0,
                                0);

                grid.add(
                                patientId,
                                1,
                                0);

                grid.add(
                                new Label("Patient Name:"),
                                0,
                                1);

                grid.add(
                                patient,
                                1,
                                1);

                grid.add(
                                new Label("Date:"),
                                0,
                                2);

                grid.add(
                                date,
                                1,
                                2);

                grid.add(
                                new Label("Time:"),
                                0,
                                3);

                grid.add(
                                time,
                                1,
                                3);

                grid.add(
                                new Label("Type:"),
                                0,
                                4);

                grid.add(
                                type,
                                1,
                                4);

                grid.add(
                                new Label("Status:"),
                                0,
                                5);

                grid.add(
                                status,
                                1,
                                5);

                grid.add(
                                new Label("Payment:"),
                                0,
                                6);

                grid.add(
                                payment,
                                1,
                                6);

                dialog.getDialogPane()
                                .setContent(grid);

                ButtonType saveButton = new ButtonType(
                                "Save Changes",
                                ButtonBar.ButtonData.OK_DONE);

                ButtonType cancelButton = new ButtonType(
                                "Cancel",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                cancelButton);

                // =====================================================
                // SAVE
                // =====================================================

                dialog.setResultConverter(button -> {

                        if (button != saveButton) {
                                return button;
                        }

                        String newPatientId = patientId.getText().trim();

                        String newPatientName = patient.getText().trim();

                        String newTime = time.getText().trim();

                        if (newPatientName.isEmpty()) {

                                showError(
                                                "Patient name cannot be empty.");

                                return null;
                        }

                        if (newPatientId.isEmpty()) {

                                showError(
                                                "Patient ID cannot be empty.");

                                return null;
                        }

                        if (date.getValue() == null) {

                                showError(
                                                "Please select appointment date.");

                                return null;
                        }

                        if (newTime.isEmpty()) {

                                showError(
                                                "Appointment time cannot be empty.");

                                return null;
                        }

                        if (type.getValue() == null) {

                                showError(
                                                "Please select appointment type.");

                                return null;
                        }

                        if (status.getValue() == null) {

                                showError(
                                                "Please select appointment status.");

                                return null;
                        }

                        if (payment.getValue() == null) {

                                showError(
                                                "Please select payment status.");

                                return null;
                        }

                        String newDate = date.getValue().toString();

                        String newType = type.getValue();

                        String newStatus = status.getValue();

                        String newPayment = payment.getValue();

                        try {

                                System.out.println(
                                                "[EDIT APPOINTMENT] Updating Firestore...");

                                boolean updated = controller.updateAppointment(

                                                appointment.getAppointmentId(),

                                                newPatientId,

                                                newDate,

                                                newTime,

                                                newPatientName,

                                                newType,

                                                newStatus,

                                                newPayment);

                                if (updated) {

                                        // =========================================
                                        // VERY IMPORTANT
                                        // RELOAD LATEST DATA
                                        // =========================================

                                        refreshAppointmentTable();

                                        showSuccess(
                                                        "Appointment Updated",
                                                        "Updated appointment details are now visible.");

                                } else {

                                        showError(
                                                        "Appointment could not be updated in Firebase.");
                                }

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                showError(
                                                "Unable to update appointment.\n\n"
                                                                + ex.getMessage());
                        }

                        return button;
                });

                dialog.showAndWait();
        }

        // =========================================================
        // ADD APPOINTMENT
        // =========================================================

        private static void showAddAppointmentDialog() {

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Add Appointment");

                dialog.setHeaderText(
                                "Create New Appointment");

                GridPane grid = new GridPane();

                grid.setHgap(12);
                grid.setVgap(12);
                grid.setPadding(new Insets(20));

                TextField doctorId = new TextField(
                                controller.getDefaultDoctorId());

                doctorId.setDisable(true);

                TextField patientId = new TextField();

                patientId.setPromptText(
                                "Enter patient ID");

                TextField patientName = new TextField();

                patientName.setPromptText(
                                "Enter patient name");

                DatePicker appointmentDate = new DatePicker();

                appointmentDate.setValue(
                                LocalDate.now());

                TextField time = new TextField();

                time.setPromptText(
                                "e.g. 10:30 AM");

                ComboBox<String> type = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Consultation",
                                                "Follow-up",
                                                "Emergency",
                                                "Check-up"));

                type.setValue(
                                "Consultation");

                ComboBox<String> status = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Pending",
                                                "Confirmed",
                                                "Completed",
                                                "Cancelled"));

                status.setValue(
                                "Pending");

                ComboBox<String> payment = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Paid",
                                                "Unpaid",
                                                "Pending"));

                payment.setValue(
                                "Unpaid");

                grid.add(new Label("Doctor ID:"), 0, 0);
                grid.add(doctorId, 1, 0);

                grid.add(new Label("Patient ID:"), 0, 1);
                grid.add(patientId, 1, 1);

                grid.add(new Label("Patient Name:"), 0, 2);
                grid.add(patientName, 1, 2);

                grid.add(new Label("Date:"), 0, 3);
                grid.add(appointmentDate, 1, 3);

                grid.add(new Label("Time:"), 0, 4);
                grid.add(time, 1, 4);

                grid.add(new Label("Type:"), 0, 5);
                grid.add(type, 1, 5);

                grid.add(new Label("Status:"), 0, 6);
                grid.add(status, 1, 6);

                grid.add(new Label("Payment:"), 0, 7);
                grid.add(payment, 1, 7);

                dialog.getDialogPane()
                                .setContent(grid);

                ButtonType saveButton = new ButtonType(
                                "Save",
                                ButtonBar.ButtonData.OK_DONE);

                ButtonType cancelButton = new ButtonType(
                                "Cancel",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                cancelButton);

                dialog.setResultConverter(button -> {

                        if (button != saveButton) {
                                return button;
                        }

                        String selectedDate = appointmentDate.getValue() == null
                                        ? ""
                                        : appointmentDate
                                                        .getValue()
                                                        .toString();

                        String selectedPatientId = patientId.getText().trim();

                        String selectedPatientName = patientName.getText().trim();

                        String selectedTime = time.getText().trim();

                        if (selectedPatientId.isEmpty()) {

                                showError(
                                                "Patient ID cannot be empty.");

                                return null;
                        }

                        if (selectedPatientName.isEmpty()) {

                                showError(
                                                "Patient name cannot be empty.");

                                return null;
                        }

                        if (selectedDate.isEmpty()) {

                                showError(
                                                "Please select appointment date.");

                                return null;
                        }

                        if (selectedTime.isEmpty()) {

                                showError(
                                                "Appointment time cannot be empty.");

                                return null;
                        }

                        DoctorAppointment appointment = controller.addAppointment(

                                        doctorId.getText(),

                                        selectedPatientId,

                                        selectedDate,

                                        selectedTime,

                                        selectedPatientName,

                                        type.getValue(),

                                        status.getValue(),

                                        payment.getValue());

                        if (appointment != null) {

                                refreshAppointmentTable();

                                showSuccess(
                                                "Appointment Added",
                                                "Appointment has been saved to Firebase.");

                        } else {

                                showError(
                                                "Unable to add appointment.");
                        }

                        return button;
                });

                dialog.showAndWait();
        }
}