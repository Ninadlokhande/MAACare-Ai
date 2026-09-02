
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class DoctorAppointmentsPage {

        private static final String PINK = "#E84A87";
        private static final String LIGHT_PINK = "#FFEAF3";
        private static final String PURPLE = "#754CE0";
        private static final String DARK_TEXT = "#172554";
        private static final String SECONDARY = "#64748B";
        private static final String BORDER = "#E8E8F0";
        private static final String PAGE_BACKGROUND = "#FAF9FC";

        private static TableView<DoctorAppointment> table;

        private static final DoctorAppointmentController controller = new DoctorAppointmentController();

        public static void show() {

                /*
                 * Load latest appointments from Firebase
                 */
                controller.refreshAppointments();

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " +
                                                PAGE_BACKGROUND + ";");

                // ================= HEADER =================

                HBox header = new HBox(18);

                header.setPadding(
                                new Insets(20, 25, 20, 25));

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                VBox titleBox = new VBox(4);

                Label title = new Label("Appointments");

                title.setStyle(
                                "-fx-font-size: 26px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                DARK_TEXT + ";");

                Label subtitle = new Label(
                                "Manage and view all patient appointments");

                subtitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " +
                                                SECONDARY + ";");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);
                // ================= REFRESH BUTTON =================
                Button refresh = new Button("↻ Refresh");

                refresh.setStyle(
                                "-fx-background-color: " + LIGHT_PINK + ";" +
                                                "-fx-text-fill: " + PINK + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 16 10 16;" +
                                                "-fx-cursor: hand;");

                refresh.setOnAction(e -> {

                        try {

                                System.out.println(
                                                "[APPOINTMENT] Refresh button clicked.");

                                // ==========================================
                                // 1. FIRESTORE मधून latest data load करा
                                // ==========================================

                                controller.refreshAppointments();

                                // ==========================================
                                // 2. TableView ला नवीन data द्या
                                // ==========================================

                                if (table != null) {

                                        table.setItems(
                                                        controller.getAppointments());

                                        table.refresh();
                                }

                                // ==========================================
                                // 3. Success message
                                // ==========================================

                                System.out.println(
                                                "[APPOINTMENT] Appointments refreshed successfully.");

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                System.out.println(
                                                "[APPOINTMENT ERROR] Refresh failed.");

                                Alert alert = new Alert(Alert.AlertType.ERROR);

                                alert.setTitle("Refresh Error");

                                alert.setHeaderText(
                                                "Unable to refresh appointments");

                                alert.setContentText(
                                                "Please check your Firebase connection and try again.");

                                alert.showAndWait();
                        }
                });

                // ================= ADD BUTTON =================

                Button add = new Button(
                                "+ Add Appointment");

                add.setStyle(
                                "-fx-background-color: " +
                                                PINK + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 18 10 18;" +
                                                "-fx-cursor: hand;");

                add.setOnAction(
                                e -> showAddAppointmentDialog());

                // ================= BACK BUTTON =================

                Button back = new Button(
                                "← Back to Dashboard");

                back.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PINK + ";" +
                                                "-fx-text-fill: " +
                                                PINK + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 16 10 16;" +
                                                "-fx-cursor: hand;");

                back.setOnAction(
                                e -> DoctorDashboard.showDashboard());

                header.getChildren().addAll(
                                titleBox,
                                spacer,
                                refresh,
                                add,
                                back);

                root.setTop(header);

                // ================= FILTER BOX =================

                HBox filterBox = new HBox(14);

                filterBox.setPadding(
                                new Insets(20, 25, 10, 25));

                filterBox.setAlignment(
                                Pos.CENTER_LEFT);

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Search patient, type, date...");

                searchField.setPrefWidth(300);

                searchField.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10;");

                ComboBox<String> statusCombo = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "All",
                                                "Confirmed",
                                                "Pending",
                                                "Completed",
                                                "Cancelled"));

                statusCombo.setValue("All");
                statusCombo.setPrefWidth(150);

                ComboBox<String> typeCombo = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "All",
                                                "Consultation",
                                                "Follow-up",
                                                "Emergency",
                                                "Check-up"));

                typeCombo.setValue("All");
                typeCombo.setPrefWidth(150);

                /*
                 * Clear button removed completely.
                 */

                filterBox.getChildren().addAll(
                                searchField,
                                statusCombo,
                                typeCombo);

                // ================= TABLE =================

                table = new TableView<>();

                table.setItems(
                                controller.getAppointments());

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                table.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 10;" +
                                                "-fx-background-radius: 10;");

                // ================= TIME =================

                TableColumn<DoctorAppointment, String> timeCol = new TableColumn<>("Time");

                timeCol.setCellValueFactory(
                                new PropertyValueFactory<>("time"));

                // ================= PATIENT =================

                TableColumn<DoctorAppointment, String> patientCol = new TableColumn<>("Patient");

                patientCol.setCellValueFactory(
                                new PropertyValueFactory<>("patient"));

                // ================= TYPE =================

                TableColumn<DoctorAppointment, String> typeCol = new TableColumn<>("Type");

                typeCol.setCellValueFactory(
                                new PropertyValueFactory<>("type"));

                // ================= STATUS =================

                TableColumn<DoctorAppointment, String> statusCol = new TableColumn<>("Status");

                statusCol.setCellValueFactory(
                                new PropertyValueFactory<>("status"));

                // ================= PAYMENT =================

                TableColumn<DoctorAppointment, String> paymentCol = new TableColumn<>("Payment");

                paymentCol.setCellValueFactory(
                                new PropertyValueFactory<>("payment"));

                // ================= ACTION =================

                TableColumn<DoctorAppointment, Void> actionCol = new TableColumn<>("Action");

                actionCol.setPrefWidth(190);

                actionCol.setCellFactory(
                                column -> new TableCell<>() {

                                        private final Button view = new Button("View");

                                        private final Button edit = new Button("Edit");

                                        private final Button delete = new Button("Delete");

                                        private final HBox buttons = new HBox(7);

                                        {
                                                buttons.setAlignment(
                                                                Pos.CENTER);

                                                buttons.getChildren().addAll(
                                                                view,
                                                                edit,
                                                                delete);

                                                // VIEW

                                                view.setStyle(
                                                                "-fx-background-color: " +
                                                                                LIGHT_PINK + ";" +
                                                                                "-fx-text-fill: " +
                                                                                PURPLE + ";" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-background-radius: 6;" +
                                                                                "-fx-cursor: hand;");

                                                // EDIT

                                                edit.setStyle(
                                                                "-fx-background-color: #F3ECFF;" +
                                                                                "-fx-text-fill: " +
                                                                                PURPLE + ";" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-background-radius: 6;" +
                                                                                "-fx-cursor: hand;");

                                                // DELETE

                                                delete.setStyle(
                                                                "-fx-background-color: #FFF0F0;" +
                                                                                "-fx-text-fill: #D93636;" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-background-radius: 6;" +
                                                                                "-fx-cursor: hand;");

                                                // VIEW ACTION

                                                view.setOnAction(e -> {

                                                        DoctorAppointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showAppointmentDetails(
                                                                        appointment);
                                                });

                                                // EDIT ACTION

                                                edit.setOnAction(e -> {

                                                        DoctorAppointment appointment = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        showEditAppointmentDialog(
                                                                        appointment);
                                                });

                                                // DELETE ACTION

                                                delete.setOnAction(e -> {

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
                                                                        "Patient: " +
                                                                                        appointment.getPatient());

                                                        confirm.showAndWait()
                                                                        .ifPresent(result -> {

                                                                                if (result == ButtonType.OK) {

                                                                                        boolean deleted = controller
                                                                                                        .deleteAppointment(
                                                                                                                        appointment);

                                                                                        if (deleted) {

                                                                                                table.setItems(
                                                                                                                controller
                                                                                                                                .getAppointments());

                                                                                                table.refresh();

                                                                                                Alert success = new Alert(
                                                                                                                Alert.AlertType.INFORMATION);

                                                                                                success.setTitle(
                                                                                                                "Deleted");

                                                                                                success.setHeaderText(
                                                                                                                "Appointment Deleted");

                                                                                                success.setContentText(
                                                                                                                "Appointment deleted successfully.");

                                                                                                success.showAndWait();
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

                // ================= FILTER LOGIC =================

                Runnable applyFilter = () -> {

                        String search = searchField.getText() == null
                                        ? ""
                                        : searchField.getText()
                                                        .trim()
                                                        .toLowerCase();

                        String selectedStatus = statusCombo.getValue();

                        String selectedType = typeCombo.getValue();

                        ObservableList<DoctorAppointment> filtered = FXCollections.observableArrayList();

                        for (DoctorAppointment appointment : controller.getAppointments()) {

                                boolean searchMatch = search.isEmpty()

                                                ||

                                                appointment.getPatient()
                                                                .toLowerCase()
                                                                .contains(search)

                                                ||

                                                appointment.getPatientId()
                                                                .toLowerCase()
                                                                .contains(search)

                                                ||

                                                appointment.getType()
                                                                .toLowerCase()
                                                                .contains(search)

                                                ||

                                                appointment.getDate()
                                                                .toLowerCase()
                                                                .contains(search)

                                                ||

                                                appointment.getTime()
                                                                .toLowerCase()
                                                                .contains(search);

                                boolean statusMatch = selectedStatus == null
                                                ||
                                                selectedStatus.equals("All")
                                                ||
                                                selectedStatus.equalsIgnoreCase(
                                                                appointment.getStatus());

                                boolean typeMatch = selectedType == null
                                                ||
                                                selectedType.equals("All")
                                                ||
                                                selectedType.equalsIgnoreCase(
                                                                appointment.getType());

                                if (searchMatch &&
                                                statusMatch &&
                                                typeMatch) {

                                        filtered.add(
                                                        appointment);
                                }
                        }

                        table.setItems(
                                        filtered);
                };

                searchField.textProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter.run());

                statusCombo.valueProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter.run());

                typeCombo.valueProperty()
                                .addListener(
                                                (obs, oldValue, newValue) -> applyFilter.run());

                // ================= CONTENT =================

                VBox content = new VBox(10);

                content.setPadding(
                                new Insets(
                                                0,
                                                25,
                                                25,
                                                25));

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                content.getChildren().addAll(
                                filterBox,
                                table);

                root.setCenter(content);

                // ================= SCENE =================

                Scene appointmentsScene = new Scene(root);

                /*
                 * Open Appointment page using
                 * the same Dashboard Stage.
                 */
                DoctorDashboard.changeScene(
                                appointmentsScene);
        }

        // =========================================================
        // APPOINTMENT DETAILS
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
                                "Appointment ID: " +
                                                appointment.getAppointmentId()
                                                + "\n\n" +

                                                "Patient ID: " +
                                                appointment.getPatientId()
                                                + "\n\n" +

                                                "Date: " +
                                                appointment.getDate()
                                                + "\n\n" +

                                                "Time: " +
                                                appointment.getTime()
                                                + "\n\n" +

                                                "Type: " +
                                                appointment.getType()
                                                + "\n\n" +

                                                "Status: " +
                                                appointment.getStatus()
                                                + "\n\n" +

                                                "Payment: " +
                                                appointment.getPayment());

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
                                "Edit Appointment");

                GridPane grid = new GridPane();

                grid.setHgap(12);
                grid.setVgap(12);

                grid.setPadding(
                                new Insets(20));

                TextField patient = new TextField(
                                appointment.getPatient());

                TextField patientId = new TextField(
                                appointment.getPatientId());

                DatePicker date = new DatePicker();

                try {

                        date.setValue(
                                        LocalDate.parse(
                                                        appointment.getDate()));

                } catch (Exception ignored) {
                }

                TextField time = new TextField(
                                appointment.getTime());

                ComboBox<String> type = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Consultation",
                                                "Follow-up",
                                                "Emergency",
                                                "Check-up"));

                type.setValue(
                                appointment.getType());

                ComboBox<String> status = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Pending",
                                                "Confirmed",
                                                "Completed",
                                                "Cancelled"));

                status.setValue(
                                appointment.getStatus());

                ComboBox<String> payment = new ComboBox<>(
                                FXCollections.observableArrayList(
                                                "Paid",
                                                "Unpaid",
                                                "Pending"));

                payment.setValue(
                                appointment.getPayment());

                grid.add(
                                new Label("Patient ID:"),
                                0, 0);

                grid.add(
                                patientId,
                                1, 0);

                grid.add(
                                new Label("Patient Name:"),
                                0, 1);

                grid.add(
                                patient,
                                1, 1);

                grid.add(
                                new Label("Date:"),
                                0, 2);

                grid.add(
                                date,
                                1, 2);

                grid.add(
                                new Label("Time:"),
                                0, 3);

                grid.add(
                                time,
                                1, 3);

                grid.add(
                                new Label("Type:"),
                                0, 4);

                grid.add(
                                type,
                                1, 4);

                grid.add(
                                new Label("Status:"),
                                0, 5);

                grid.add(
                                status,
                                1, 5);

                grid.add(
                                new Label("Payment:"),
                                0, 6);

                grid.add(
                                payment,
                                1, 6);

                dialog.getDialogPane()
                                .setContent(grid);

                ButtonType save = new ButtonType(
                                "Save",
                                ButtonBar.ButtonData.OK_DONE);

                ButtonType cancel = new ButtonType(
                                "Cancel",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                save,
                                                cancel);

                dialog.setResultConverter(
                                button -> {

                                        if (button == save) {

                                                Alert info = new Alert(
                                                                Alert.AlertType.INFORMATION);

                                                info.setTitle(
                                                                "Edit Appointment");

                                                info.setHeaderText(
                                                                "Edit functionality");

                                                info.setContentText(
                                                                "The Edit UI is ready. "
                                                                                + "Firestore update method "
                                                                                + "can be connected next.");

                                                info.showAndWait();
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

                grid.setPadding(
                                new Insets(20));

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

                grid.add(
                                new Label("Doctor ID:"),
                                0, 0);

                grid.add(
                                doctorId,
                                1, 0);

                grid.add(
                                new Label("Patient ID:"),
                                0, 1);

                grid.add(
                                patientId,
                                1, 1);

                grid.add(
                                new Label("Patient Name:"),
                                0, 2);

                grid.add(
                                patientName,
                                1, 2);

                grid.add(
                                new Label("Date:"),
                                0, 3);

                grid.add(
                                appointmentDate,
                                1, 3);

                grid.add(
                                new Label("Time:"),
                                0, 4);

                grid.add(
                                time,
                                1, 4);

                grid.add(
                                new Label("Type:"),
                                0, 5);

                grid.add(
                                type,
                                1, 5);

                grid.add(
                                new Label("Status:"),
                                0, 6);

                grid.add(
                                status,
                                1, 6);

                grid.add(
                                new Label("Payment:"),
                                0, 7);

                grid.add(
                                payment,
                                1, 7);

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

                dialog.setResultConverter(
                                button -> {

                                        if (button == saveButton) {

                                                String selectedDate = appointmentDate.getValue() == null
                                                                ? ""
                                                                : appointmentDate
                                                                                .getValue()
                                                                                .toString();

                                                DoctorAppointment appointment = controller.addAppointment(

                                                                doctorId.getText(),

                                                                patientId.getText(),

                                                                selectedDate,

                                                                time.getText(),

                                                                patientName.getText(),

                                                                type.getValue(),

                                                                status.getValue(),

                                                                payment.getValue());

                                                if (appointment != null) {

                                                        controller.refreshAppointments();

                                                        if (table != null) {

                                                                table.setItems(
                                                                                controller
                                                                                                .getAppointments());

                                                                table.refresh();
                                                        }

                                                        Alert success = new Alert(
                                                                        Alert.AlertType.INFORMATION);

                                                        success.setTitle(
                                                                        "Appointment Added");

                                                        success.setHeaderText(
                                                                        "Appointment added successfully");

                                                        success.setContentText(
                                                                        "The appointment has been saved "
                                                                                        + "to Firebase.");

                                                        success.showAndWait();

                                                } else {

                                                        Alert alert = new Alert(
                                                                        Alert.AlertType.ERROR);

                                                        alert.setTitle(
                                                                        "Appointment Error");

                                                        alert.setHeaderText(
                                                                        "Unable to add appointment");

                                                        alert.setContentText(
                                                                        "Please check the entered "
                                                                                        + "data and try again.");

                                                        alert.showAndWait();
                                                }
                                        }

                                        return button;
                                });

                dialog.showAndWait();
        }
}
