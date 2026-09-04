package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientController;
import com.sigma.model.DoctorModel.Patient;
import com.sigma.view.scenesettings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * ============================================================
 * PatientsPage
 * ============================================================
 *
 * Uses:
 * - Common DoctorDashboard Stage
 * - scenesettings.rectanguler2d for common screen size
 * - DoctorDashboard common sidebar
 * - Shared PatientController
 *
 * ============================================================
 */
public class PatientsPage {

        private static TableView<Patient> table;

        private static PatientController controller;

        // ============================================================
        // COMMON SCREEN SIZE
        // ============================================================

        private static final double SCREEN_WIDTH = scenesettings.rectanguler2d.getWidth();

        private static final double SCREEN_HEIGHT = scenesettings.rectanguler2d.getHeight();

        // ============================================================
        // GET SHARED CONTROLLER
        // ============================================================

        private static PatientController getController() {

                if (controller == null) {
                        controller = DoctorDashboard.getPatientController();
                }

                return controller;
        }

        // ============================================================
        // SHOW PAGE
        // ============================================================

        public static void show() {

                PatientController patientController = getController();

                if (patientController == null) {

                        showError(
                                        "Patient Controller Error",
                                        "Patient controller is not available.");

                        return;
                }

                try {

                        // =====================================================
                        // REFRESH FIRESTORE DATA
                        // =====================================================

                        patientController.refreshPatients();

                        // =====================================================
                        // ROOT CONTENT
                        // =====================================================

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

                        // =====================================================
                        // HEADER
                        // =====================================================

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

                        // =====================================================
                        // FILTER SECTION
                        // =====================================================

                        HBox filter = new HBox(10);

                        filter.setAlignment(
                                        Pos.CENTER_LEFT);

                        filter.setPadding(
                                        new Insets(14));

                        filter.setMaxWidth(
                                        Double.MAX_VALUE);

                        filter.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-border-color: " +
                                                        DoctorTheme.BORDER + ";" +
                                                        "-fx-border-radius: 10;");

                        // =====================================================
                        // SEARCH
                        // =====================================================

                        TextField search = new TextField();

                        search.setPromptText(
                                        "Search patient name or contact...");

                        search.setPrefWidth(280);

                        search.setPrefHeight(40);

                        search.setMaxWidth(
                                        Double.MAX_VALUE);

                        HBox.setHgrow(
                                        search,
                                        Priority.ALWAYS);

                        // =====================================================
                        // GENDER FILTER
                        // =====================================================

                        ComboBox<String> gender = new ComboBox<>();

                        gender.getItems().addAll(
                                        "All",
                                        "Female",
                                        "Male",
                                        "Other");

                        gender.setValue("All");

                        gender.setPrefHeight(40);

                        // =====================================================
                        // ADD PATIENT BUTTON
                        // =====================================================

                        Button add = DoctorTheme.primaryButton(
                                        "+  Add Patient");

                        add.setPrefHeight(40);

                        // =====================================================
                        // FILTER SPACER
                        // =====================================================

                        Region filterSpacer = new Region();

                        HBox.setHgrow(
                                        filterSpacer,
                                        Priority.ALWAYS);

                        filter.getChildren().addAll(
                                        search,
                                        gender,
                                        filterSpacer,
                                        add);

                        // =====================================================
                        // TABLE
                        // =====================================================

                        table = new TableView<>();

                        table.setMaxWidth(
                                        Double.MAX_VALUE);

                        table.setMaxHeight(
                                        Double.MAX_VALUE);

                        table.setColumnResizePolicy(
                                        TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

                        table.setPlaceholder(
                                        new Label("No patients found."));

                        // =====================================================
                        // COLUMNS
                        // =====================================================

                        TableColumn<Patient, String> name = new TableColumn<>("Patient");

                        TableColumn<Patient, String> age = new TableColumn<>("Age / Gender");

                        TableColumn<Patient, String> contact = new TableColumn<>("Contact");

                        TableColumn<Patient, String> lastVisit = new TableColumn<>("Last Visit");

                        TableColumn<Patient, String> nextVisit = new TableColumn<>("Next Visit");

                        TableColumn<Patient, String> action = new TableColumn<>("Action");

                        // =====================================================
                        // NAME COLUMN
                        // =====================================================

                        name.setCellValueFactory(
                                        data -> data.getValue()
                                                        .nameProperty());

                        // =====================================================
                        // AGE / GENDER COLUMN
                        // =====================================================

                        age.setCellValueFactory(
                                        data -> new javafx.beans.property.SimpleStringProperty(
                                                        safe(data.getValue().getAge())
                                                                        + " Y / "
                                                                        + safe(data.getValue().getGender())));

                        // =====================================================
                        // CONTACT COLUMN
                        // =====================================================

                        contact.setCellValueFactory(
                                        data -> data.getValue()
                                                        .contactProperty());

                        // =====================================================
                        // LAST VISIT COLUMN
                        // =====================================================

                        lastVisit.setCellValueFactory(
                                        data -> data.getValue()
                                                        .lastVisitProperty());

                        // =====================================================
                        // NEXT VISIT COLUMN
                        // =====================================================

                        nextVisit.setCellValueFactory(
                                        data -> data.getValue()
                                                        .nextVisitProperty());

                        // =====================================================
                        // ACTION COLUMN
                        // =====================================================

                        action.setCellFactory(
                                        column -> new TableCell<Patient, String>() {

                                                private final Button viewButton = new Button("👁");

                                                private final Button editButton = new Button("✏");

                                                private final HBox buttons = new HBox(6);

                                                {

                                                        // =================================
                                                        // VIEW BUTTON STYLE
                                                        // =================================

                                                        viewButton.setStyle(
                                                                        "-fx-background-color: #E0F2FE;" +
                                                                                        "-fx-text-fill: #0284C7;" +
                                                                                        "-fx-font-size: 14px;" +
                                                                                        "-fx-background-radius: 7;" +
                                                                                        "-fx-padding: 5 9;" +
                                                                                        "-fx-cursor: hand;");

                                                        // =================================
                                                        // EDIT BUTTON STYLE
                                                        // =================================

                                                        editButton.setStyle(
                                                                        "-fx-background-color: #FFF4DE;" +
                                                                                        "-fx-text-fill: #F59E0B;" +
                                                                                        "-fx-font-size: 14px;" +
                                                                                        "-fx-background-radius: 7;" +
                                                                                        "-fx-padding: 5 9;" +
                                                                                        "-fx-cursor: hand;");

                                                        // =================================
                                                        // TOOLTIPS
                                                        // =================================

                                                        viewButton.setTooltip(
                                                                        new Tooltip(
                                                                                        "View Patient"));

                                                        editButton.setTooltip(
                                                                        new Tooltip(
                                                                                        "Edit Patient"));

                                                        // =================================
                                                        // VIEW PATIENT
                                                        // =================================

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

                                                        // =================================
                                                        // EDIT PATIENT
                                                        // =================================

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

                                                                showEditPatientDialog(
                                                                                patientController,
                                                                                patient);
                                                        });

                                                        // =================================
                                                        // BUTTON CONTAINER
                                                        // =================================

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

                        // =====================================================
                        // ADD COLUMNS
                        // =====================================================

                        table.getColumns().addAll(
                                        name,
                                        age,
                                        contact,
                                        lastVisit,
                                        nextVisit,
                                        action);

                        // =====================================================
                        // LOAD PATIENTS
                        // =====================================================

                        table.setItems(
                                        patientController.getPatients());

                        // =====================================================
                        // FILTER FUNCTION
                        // =====================================================

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

                        // =====================================================
                        // SEARCH LISTENER
                        // =====================================================

                        search.textProperty()
                                        .addListener(
                                                        (obs, oldValue, newValue) -> applyFilters.run());

                        // =====================================================
                        // GENDER LISTENER
                        // =====================================================

                        gender.valueProperty()
                                        .addListener(
                                                        (obs, oldValue, newValue) -> applyFilters.run());

                        // =====================================================
                        // ADD PATIENT
                        // =====================================================

                        add.setOnAction(
                                        e -> AddPatientPage.show());

                        // =====================================================
                        // TABLE GROW
                        // =====================================================

                        VBox.setVgrow(
                                        table,
                                        Priority.ALWAYS);

                        // =====================================================
                        // ADD CONTENT
                        // =====================================================

                        root.getChildren().addAll(
                                        header,
                                        filter,
                                        table);

                        // =====================================================
                        // COMMON PAGE CONTAINER
                        // =====================================================

                        BorderPane page = new BorderPane();

                        // =====================================================
                        // COMMON SIDEBAR
                        // =====================================================

                        page.setLeft(
                                        DoctorDashboard.createSidebar(
                                                        "Patients"));

                        // =====================================================
                        // CENTER CONTENT
                        // =====================================================

                        page.setCenter(root);

                        // =====================================================
                        // COMMON SCREEN SIZE
                        // =====================================================

                        Scene patientsScene = new Scene(
                                        page,
                                        SCREEN_WIDTH,
                                        SCREEN_HEIGHT);

                        // =====================================================
                        // USE COMMON DASHBOARD STAGE
                        // =====================================================

                        DoctorDashboard.changeScene(
                                        patientsScene);

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Patients Page Error",
                                        "Unable to open Patients page.");
                }
        }

        // ============================================================
        // SHOW PATIENT DETAILS
        // ============================================================

        private static void showPatientDetails(
                        Patient patient) {

                if (patient == null) {
                        return;
                }

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Patient Details");

                alert.setHeaderText(
                                safe(patient.getName()));

                alert.setContentText(
                                "Patient ID: "
                                                + safe(patient.getPatientId())

                                                + "\nAge: "
                                                + safe(patient.getAge())

                                                + "\nGender: "
                                                + safe(patient.getGender())

                                                + "\nContact: "
                                                + safe(patient.getContact())

                                                + "\nLast Visit: "
                                                + safe(patient.getLastVisit())

                                                + "\nNext Visit: "
                                                + safe(patient.getNextVisit()));

                alert.showAndWait();
        }

        // ============================================================
        // EDIT PATIENT DIALOG
        // ============================================================

        private static void showEditPatientDialog(
                        PatientController patientController,
                        Patient patient) {

                if (patient == null ||
                                patientController == null) {

                        return;
                }

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Edit Patient");

                dialog.setHeaderText(
                                "Update patient information");

                GridPane grid = new GridPane();

                grid.setHgap(12);

                grid.setVgap(10);

                grid.setPadding(
                                new Insets(20));

                // ========================================================
                // FIELDS
                // ========================================================

                TextField name = new TextField(
                                safe(patient.getName()));

                TextField age = new TextField(
                                safe(patient.getAge()));

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "Female",
                                "Male",
                                "Other");

                gender.setValue(
                                safe(patient.getGender()));

                TextField contact = new TextField(
                                safe(patient.getContact()));

                TextField lastVisit = new TextField(
                                safe(patient.getLastVisit()));

                TextField nextVisit = new TextField(
                                safe(patient.getNextVisit()));

                // ========================================================
                // GRID
                // ========================================================

                grid.addRow(
                                0,
                                new Label("Name:"),
                                name);

                grid.addRow(
                                1,
                                new Label("Age:"),
                                age);

                grid.addRow(
                                2,
                                new Label("Gender:"),
                                gender);

                grid.addRow(
                                3,
                                new Label("Contact:"),
                                contact);

                grid.addRow(
                                4,
                                new Label("Last Visit:"),
                                lastVisit);

                grid.addRow(
                                5,
                                new Label("Next Visit:"),
                                nextVisit);

                dialog.getDialogPane()
                                .setContent(grid);

                // ========================================================
                // BUTTONS
                // ========================================================

                ButtonType save = new ButtonType(
                                "Save",
                                ButtonBar.ButtonData.OK_DONE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                save,
                                                ButtonType.CANCEL);

                // ========================================================
                // RESULT CONVERTER
                // ========================================================

                dialog.setResultConverter(
                                button -> {

                                        if (button != save) {
                                                return button;
                                        }

                                        // =================================================
                                        // REQUIRED FIELD VALIDATION
                                        // =================================================

                                        if (name.getText()
                                                        .trim()
                                                        .isEmpty()
                                                        || age.getText()
                                                                        .trim()
                                                                        .isEmpty()
                                                        || contact.getText()
                                                                        .trim()
                                                                        .isEmpty()
                                                        || gender.getValue() == null) {

                                                showError(
                                                                "Incomplete Information",
                                                                "Please fill all required fields.");

                                                return null;
                                        }

                                        // =================================================
                                        // AGE VALIDATION
                                        // =================================================

                                        try {

                                                int enteredAge = Integer.parseInt(
                                                                age.getText()
                                                                                .trim());

                                                if (enteredAge < 0 ||
                                                                enteredAge > 120) {

                                                        showError(
                                                                        "Invalid Age",
                                                                        "Age must be between 0 and 120.");

                                                        return null;
                                                }

                                        } catch (NumberFormatException ex) {

                                                showError(
                                                                "Invalid Age",
                                                                "Age must contain numbers only.");

                                                return null;
                                        }

                                        // =================================================
                                        // UPDATE PATIENT
                                        // =================================================

                                        boolean updated = patientController.updatePatient(
                                                        patient,
                                                        name.getText().trim(),
                                                        age.getText().trim(),
                                                        gender.getValue(),
                                                        contact.getText().trim(),
                                                        lastVisit.getText().trim(),
                                                        nextVisit.getText().trim());

                                        // =================================================
                                        // UPDATE SUCCESS
                                        // =================================================

                                        if (updated) {

                                                table.refresh();

                                                showInfo(
                                                                "Patient Updated",
                                                                "Patient information updated successfully.");

                                        } else {

                                                showError(
                                                                "Update Failed",
                                                                "Unable to update patient information.");
                                        }

                                        return button;
                                });

                dialog.showAndWait();
        }

        // ============================================================
        // ADD PATIENT
        // ============================================================

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

        // ============================================================
        // SAFE STRING
        // ============================================================

        private static String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }

        // ============================================================
        // ERROR
        // ============================================================

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

        // ============================================================
        // INFORMATION
        // ============================================================

        private static void showInfo(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }
}