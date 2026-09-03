package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientController;
import com.sigma.model.DoctorModel.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PatientsPage {

        private static TableView<Patient> table;

        private static PatientController controller;

        // =====================================================
        // GET SHARED CONTROLLER
        // =====================================================

        private static PatientController getController() {

                if (controller == null) {
                        controller = DoctorDashboard.getPatientController();
                }

                return controller;
        }

        // =====================================================
        // SHOW PAGE
        // =====================================================

        public static void show() {

                PatientController patientController = getController();

                if (patientController == null) {

                        showError(
                                        "Patient Controller Error",
                                        "Patient controller is not available.");

                        return;
                }

                try {

                        // =================================================
                        // REFRESH FIRESTORE DATA
                        // =================================================

                        patientController.refreshPatients();

                        // =================================================
                        // ROOT
                        // =================================================

                        VBox root = new VBox(20);

                        root.setPadding(
                                        new Insets(
                                                        28,
                                                        35,
                                                        28,
                                                        35));

                        root.setFillWidth(true);

                        root.setMinWidth(0);

                        root.setMaxWidth(Double.MAX_VALUE);

                        DoctorTheme.applyBackground(root);

                        // =================================================
                        // HEADER
                        // =================================================

                        HBox header = new HBox();

                        header.setAlignment(
                                        Pos.CENTER_LEFT);

                        VBox heading = DoctorTheme.pageHeader(
                                        "My Patients",
                                        "View and manage all your patients.");

                        Region spacer = new Region();

                        HBox.setHgrow(
                                        spacer,
                                        Priority.ALWAYS);

                        header.getChildren().addAll(
                                        heading,
                                        spacer);

                        // =================================================
                        // FILTER
                        // =================================================

                        HBox filter = new HBox(10);

                        filter.setAlignment(
                                        Pos.CENTER_LEFT);

                        filter.setPadding(
                                        new Insets(14));

                        filter.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-border-color: " + DoctorTheme.BORDER + ";" +
                                                        "-fx-border-radius: 10;");

                        TextField search = new TextField();

                        search.setPromptText(
                                        "Search patient name or contact...");

                        search.setPrefWidth(280);

                        search.setMaxWidth(
                                        Double.MAX_VALUE);

                        HBox.setHgrow(
                                        search,
                                        Priority.ALWAYS);

                        ComboBox<String> gender = new ComboBox<>();

                        gender.getItems().addAll(
                                        "All",
                                        "Female",
                                        "Male",
                                        "Other");

                        gender.setValue("All");

                        gender.setPrefHeight(40);

                        Button add = DoctorTheme.primaryButton(
                                        "+  Add Patient");

                        Region filterSpacer = new Region();

                        HBox.setHgrow(
                                        filterSpacer,
                                        Priority.ALWAYS);

                        filter.getChildren().addAll(
                                        search,
                                        gender,
                                        filterSpacer,
                                        add);

                        // =================================================
                        // TABLE
                        // =================================================

                        table = new TableView<>();

                        table.setMaxWidth(
                                        Double.MAX_VALUE);

                        table.setMaxHeight(
                                        Double.MAX_VALUE);

                        table.setColumnResizePolicy(
                                        TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

                        table.setPlaceholder(
                                        new Label("No patients found."));

                        // =================================================
                        // COLUMNS
                        // =================================================

                        TableColumn<Patient, String> name = new TableColumn<>("Patient");

                        TableColumn<Patient, String> age = new TableColumn<>("Age / Gender");

                        TableColumn<Patient, String> contact = new TableColumn<>("Contact");

                        TableColumn<Patient, String> lastVisit = new TableColumn<>("Last Visit");

                        TableColumn<Patient, String> nextVisit = new TableColumn<>("Next Visit");

                        TableColumn<Patient, String> action = new TableColumn<>("Action");

                        // =================================================
                        // NAME
                        // =================================================

                        name.setCellValueFactory(
                                        data -> data.getValue()
                                                        .nameProperty());

                        // =================================================
                        // AGE / GENDER
                        // =================================================

                        age.setCellValueFactory(
                                        data -> new javafx.beans.property.SimpleStringProperty(
                                                        safe(data.getValue().getAge())
                                                                        + " Y / "
                                                                        + safe(data.getValue().getGender())));

                        // =================================================
                        // CONTACT
                        // =================================================

                        contact.setCellValueFactory(
                                        data -> data.getValue()
                                                        .contactProperty());

                        // =================================================
                        // LAST VISIT
                        // =================================================

                        lastVisit.setCellValueFactory(
                                        data -> data.getValue()
                                                        .lastVisitProperty());

                        // =================================================
                        // NEXT VISIT
                        // =================================================

                        nextVisit.setCellValueFactory(
                                        data -> data.getValue()
                                                        .nextVisitProperty());

                        // =================================================
                        // ACTION COLUMN
                        // =================================================

                        action.setCellFactory(
                                        column -> new TableCell<Patient, String>() {

                                                private final Button viewButton = new Button("👁");

                                                private final Button editButton = new Button("✏");

                                                private final HBox buttons = new HBox(6);

                                                {

                                                        viewButton.setStyle(
                                                                        "-fx-background-color: #E0F2FE;" +
                                                                                        "-fx-text-fill: #0284C7;" +
                                                                                        "-fx-font-size: 14px;" +
                                                                                        "-fx-background-radius: 7;" +
                                                                                        "-fx-padding: 5 9;" +
                                                                                        "-fx-cursor: hand;");

                                                        editButton.setStyle(
                                                                        "-fx-background-color: #FFF4DE;" +
                                                                                        "-fx-text-fill: #F59E0B;" +
                                                                                        "-fx-font-size: 14px;" +
                                                                                        "-fx-background-radius: 7;" +
                                                                                        "-fx-padding: 5 9;" +
                                                                                        "-fx-cursor: hand;");

                                                        viewButton.setTooltip(
                                                                        new Tooltip(
                                                                                        "View Patient"));

                                                        editButton.setTooltip(
                                                                        new Tooltip(
                                                                                        "Edit Patient"));

                                                        // =================================================
                                                        // VIEW
                                                        // =================================================

                                                        viewButton.setOnAction(e -> {

                                                                int index = getIndex();

                                                                if (index < 0 ||
                                                                                index >= getTableView()
                                                                                                .getItems()
                                                                                                .size()) {

                                                                        return;
                                                                }

                                                                Patient patient = getTableView()
                                                                                .getItems()
                                                                                .get(index);

                                                                showPatientDetails(patient);
                                                        });

                                                        // =================================================
                                                        // EDIT
                                                        // =================================================

                                                        editButton.setOnAction(e -> {

                                                                int index = getIndex();

                                                                if (index < 0 ||
                                                                                index >= getTableView()
                                                                                                .getItems()
                                                                                                .size()) {

                                                                        return;
                                                                }

                                                                Patient patient = getTableView()
                                                                                .getItems()
                                                                                .get(index);

                                                                showEditPatientDialog(patientController, patient);
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

                        // =================================================
                        // ADD COLUMNS
                        // =================================================

                        table.getColumns().addAll(
                                        name,
                                        age,
                                        contact,
                                        lastVisit,
                                        nextVisit,
                                        action);

                        // =================================================
                        // LOAD PATIENTS
                        // =================================================

                        table.setItems(
                                        patientController.getPatients());

                        // =================================================
                        // FILTER FUNCTION
                        // =================================================

                        Runnable applyFilters = () -> {

                                String searchText = search.getText() == null
                                                ? ""
                                                : search.getText()
                                                                .trim()
                                                                .toLowerCase();

                                String selectedGender = gender.getValue();

                                ObservableList<Patient> filtered = FXCollections.observableArrayList();

                                for (Patient patient : patientController.getPatients()) {

                                        String patientName = safe(patient.getName())
                                                        .toLowerCase();

                                        String patientContact = safe(patient.getContact())
                                                        .toLowerCase();

                                        String patientGender = safe(patient.getGender());

                                        boolean matchesSearch = searchText.isEmpty()
                                                        || patientName.contains(
                                                                        searchText)
                                                        || patientContact.contains(
                                                                        searchText);

                                        boolean matchesGender = selectedGender == null
                                                        || selectedGender.equalsIgnoreCase(
                                                                        "All")
                                                        || patientGender.equalsIgnoreCase(
                                                                        selectedGender);

                                        if (matchesSearch &&
                                                        matchesGender) {

                                                filtered.add(patient);
                                        }
                                }

                                table.setItems(filtered);
                        };

                        search.textProperty()
                                        .addListener(
                                                        (obs, oldValue, newValue) -> applyFilters.run());

                        gender.valueProperty()
                                        .addListener(
                                                        (obs, oldValue, newValue) -> applyFilters.run());

                        // =================================================
                        // ADD PATIENT
                        // =================================================

                        add.setOnAction(
                                        e -> AddPatientPage.show());

                        // =================================================
                        // TABLE GROW
                        // =================================================

                        VBox.setVgrow(
                                        table,
                                        Priority.ALWAYS);

                        root.getChildren().addAll(
                                        header,
                                        filter,
                                        table);

                        // =================================================
                        // SAME COMMON STAGE
                        // =================================================

                        BorderPane page = new BorderPane();
                        page.setLeft(DoctorDashboard.createSidebar("Patients"));
                        page.setCenter(root);

                        DoctorDashboard.changeScene(
                                        new Scene(page));

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Patients Page Error",
                                        "Unable to open Patients page.");
                }
        }

        // =====================================================
        // ADD PATIENT
        // =====================================================

        private static void showPatientDetails(Patient patient) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Patient Details");
                alert.setHeaderText(patient.getName());
                alert.setContentText(
                                "Patient ID: " + safe(patient.getPatientId())
                                                + "\nAge: " + safe(patient.getAge())
                                                + "\nGender: " + safe(patient.getGender())
                                                + "\nContact: " + safe(patient.getContact())
                                                + "\nLast Visit: " + safe(patient.getLastVisit())
                                                + "\nNext Visit: " + safe(patient.getNextVisit()));
                alert.showAndWait();
        }

        private static void showEditPatientDialog(
                        PatientController patientController,
                        Patient patient) {

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Edit Patient");
                dialog.setHeaderText("Update patient information");

                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(10);
                grid.setPadding(new Insets(20));

                TextField name = new TextField(patient.getName());
                TextField age = new TextField(patient.getAge());
                ComboBox<String> gender = new ComboBox<>();
                gender.getItems().addAll("Female", "Male", "Other");
                gender.setValue(patient.getGender());
                TextField contact = new TextField(patient.getContact());
                TextField lastVisit = new TextField(patient.getLastVisit());
                TextField nextVisit = new TextField(patient.getNextVisit());

                grid.addRow(0, new Label("Name:"), name);
                grid.addRow(1, new Label("Age:"), age);
                grid.addRow(2, new Label("Gender:"), gender);
                grid.addRow(3, new Label("Contact:"), contact);
                grid.addRow(4, new Label("Last Visit:"), lastVisit);
                grid.addRow(5, new Label("Next Visit:"), nextVisit);

                dialog.getDialogPane().setContent(grid);
                ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(save, ButtonType.CANCEL);

                dialog.setResultConverter(button -> {

                        if (button != save) {
                                return button;
                        }

                        if (name.getText().trim().isEmpty()
                                        || age.getText().trim().isEmpty()
                                        || contact.getText().trim().isEmpty()
                                        || gender.getValue() == null) {

                                showError("Incomplete Information", "Please fill all required fields.");
                                return null;
                        }

                        try {
                                int enteredAge = Integer.parseInt(age.getText().trim());
                                if (enteredAge < 0 || enteredAge > 120) {
                                        showError("Invalid Age", "Age must be between 0 and 120.");
                                        return null;
                                }
                        } catch (NumberFormatException ex) {
                                showError("Invalid Age", "Age must contain numbers only.");
                                return null;
                        }

                        boolean updated = patientController.updatePatient(
                                        patient,
                                        name.getText().trim(),
                                        age.getText().trim(),
                                        gender.getValue(),
                                        contact.getText().trim(),
                                        lastVisit.getText().trim(),
                                        nextVisit.getText().trim());

                        if (updated) {
                                table.refresh();
                                showInfo("Patient Updated", "Patient information updated successfully.");
                        } else {
                                showError("Update Failed", "Unable to update patient information.");
                        }

                        return button;
                });

                dialog.showAndWait();
        }

        public static void addPatient(
                        Patient patient) {

                if (patient == null) {
                        return;
                }

                PatientController patientController = getController();

                if (patientController == null) {
                        return;
                }

                if (!patientController
                                .getPatients()
                                .contains(patient)) {

                        patientController
                                        .getPatients()
                                        .add(patient);
                }

                if (table != null) {

                        table.setItems(
                                        patientController
                                                        .getPatients());

                        table.refresh();
                }
        }

        // =====================================================
        // SAFE STRING
        // =====================================================

        private static String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }

        // =====================================================
        // ERROR
        // =====================================================

        private static void showError(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        private static void showInfo(String title, String message) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }
}