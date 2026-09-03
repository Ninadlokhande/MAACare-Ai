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

                // =================================================
                // REFRESH FIRESTORE DATA
                // =================================================

                patientController.refreshPatients();

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

                Theme.applyBackground(root);

                // =================================================
                // HEADER
                // =================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "My Patients",
                                "View and manage all your patients.");

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
                                                "-fx-border-color: "
                                                + Theme.BORDER + ";" +
                                                "-fx-border-radius: 10;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search patient name or contact...");

                search.setPrefWidth(280);
                search.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(search, Priority.ALWAYS);

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "All",
                                "Female",
                                "Male",
                                "Other");

                gender.setValue("All");

                Button add = Theme.primaryButton(
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

                table.setMaxWidth(Double.MAX_VALUE);
                table.setMaxHeight(Double.MAX_VALUE);

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

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

                name.setCellValueFactory(
                                data -> data.getValue()
                                                .nameProperty());

                age.setCellValueFactory(
                                data -> new javafx.beans.property.SimpleStringProperty(
                                                safe(data.getValue().getAge())
                                                                + " Y / "
                                                                + safe(data.getValue().getGender())));

                contact.setCellValueFactory(
                                data -> data.getValue()
                                                .contactProperty());

                lastVisit.setCellValueFactory(
                                data -> data.getValue()
                                                .lastVisitProperty());

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

                                                // =========================
                                                // VIEW
                                                // =========================

                                                viewButton.setOnAction(e -> {

                                                        if (getIndex() < 0 ||
                                                                        getIndex() >= getTableView()
                                                                                        .getItems()
                                                                                        .size()) {

                                                                return;
                                                        }

                                                        Patient patient = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        patientController
                                                                        .viewPatient(
                                                                                        patient);
                                                });

                                                // =========================
                                                // EDIT
                                                // =========================

                                                editButton.setOnAction(e -> {

                                                        if (getIndex() < 0 ||
                                                                        getIndex() >= getTableView()
                                                                                        .getItems()
                                                                                        .size()) {

                                                                return;
                                                        }

                                                        Patient patient = getTableView()
                                                                        .getItems()
                                                                        .get(getIndex());

                                                        patientController
                                                                        .editPatient(
                                                                                        patient);
                                                });

                                                buttons.setAlignment(
                                                                Pos.CENTER);

                                                buttons.getChildren()
                                                                .addAll(
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

                table.getColumns().addAll(
                                name,
                                age,
                                contact,
                                lastVisit,
                                nextVisit,
                                action);

                // =================================================
                // LOAD FIRESTORE PATIENTS
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

                                String patientContact = safe(patient.getContact());

                                String patientGender = safe(patient.getGender());

                                boolean matchesSearch = searchText.isEmpty()
                                                || patientName
                                                                .contains(searchText)
                                                || patientContact
                                                                .contains(searchText);

                                boolean matchesGender = selectedGender == null
                                                || selectedGender
                                                                .equalsIgnoreCase("All")
                                                || patientGender
                                                                .equalsIgnoreCase(
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
                // SAME DASHBOARD STAGE
                // =================================================

                Scene patientsScene = new Scene(
                                root,
                                scenesettings.rectanguler2d.getWidth(),
                                scenesettings.rectanguler2d.getHeight());

                DoctorDashboard.changeScene(
                                patientsScene);
        }

        // =====================================================
        // ADD PATIENT
        // =====================================================

        public static void addPatient(
                        Patient patient) {

                if (patient == null) {
                        return;
                }

                PatientController patientController = getController();

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
}