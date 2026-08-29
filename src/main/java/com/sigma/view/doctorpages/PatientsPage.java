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

        private static final PatientController controller = new PatientController();

        // =====================================================
        // SHOW PATIENT PAGE
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

                // =====================================================
                // FILTER
                // =====================================================

                HBox filter = new HBox(10);

                filter.setAlignment(
                                Pos.CENTER_LEFT);

                filter.setPadding(
                                new Insets(14));

                filter.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 10;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search patient name or contact...");

                search.setPrefWidth(280);

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

                // =====================================================
                // TABLE
                // =====================================================

                table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

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

                name.setCellValueFactory(
                                data -> data.getValue()
                                                .nameProperty());

                age.setCellValueFactory(
                                data -> data.getValue()
                                                .ageProperty());

                contact.setCellValueFactory(
                                data -> data.getValue()
                                                .contactProperty());

                lastVisit.setCellValueFactory(
                                data -> data.getValue()
                                                .lastVisitProperty());

                nextVisit.setCellValueFactory(
                                data -> data.getValue()
                                                .nextVisitProperty());

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                action.setCellFactory(
                                column -> new TableCell<Patient, String>() {

                                        private final Button viewButton = new Button("👁");

                                        private final Button editButton = new Button("✏");

                                        private final HBox buttons = new HBox(6);

                                        {

                                                // =================================================
                                                // VIEW
                                                // =================================================

                                                viewButton.setStyle(
                                                                "-fx-background-color: #E0F2FE;" +
                                                                                "-fx-text-fill: #0284C7;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                viewButton.setTooltip(
                                                                new Tooltip(
                                                                                "View Patient"));

                                                // =================================================
                                                // EDIT
                                                // =================================================

                                                editButton.setStyle(
                                                                "-fx-background-color: #FFF4DE;" +
                                                                                "-fx-text-fill: #F59E0B;" +
                                                                                "-fx-font-size: 14px;" +
                                                                                "-fx-background-radius: 7;" +
                                                                                "-fx-padding: 5 9;" +
                                                                                "-fx-cursor: hand;");

                                                editButton.setTooltip(
                                                                new Tooltip(
                                                                                "Edit Patient"));

                                                // =================================================
                                                // VIEW ACTION
                                                // =================================================

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

                                                        controller.viewPatient(
                                                                        patient);
                                                });

                                                // =================================================
                                                // EDIT ACTION
                                                // =================================================

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

                                                        controller.editPatient(
                                                                        patient);
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

                table.getColumns().addAll(
                                name,
                                age,
                                contact,
                                lastVisit,
                                nextVisit,
                                action);

                // =====================================================
                // LOAD PATIENTS FROM CONTROLLER
                // =====================================================

                table.setItems(
                                FXCollections.observableArrayList(
                                                controller.getPatients()));

                // =====================================================
                // FILTER
                // =====================================================

                Runnable applyFilters = () -> {

                        String searchText = search.getText() == null
                                        ? ""
                                        : search.getText()
                                                        .trim()
                                                        .toLowerCase();

                        String selectedGender = gender.getValue();

                        ObservableList<Patient> filtered = FXCollections.observableArrayList();

                        for (Patient patient : controller.getPatients()) {

                                boolean matchesSearch = searchText.isEmpty()
                                                || patient.getName()
                                                                .toLowerCase()
                                                                .contains(searchText)
                                                || patient.getContact()
                                                                .contains(searchText);

                                boolean matchesGender = selectedGender == null
                                                || selectedGender.equals("All")
                                                || patient.getAge()
                                                                .toLowerCase()
                                                                .contains(
                                                                                selectedGender
                                                                                                .toLowerCase());

                                if (matchesSearch &&
                                                matchesGender) {

                                        filtered.add(patient);
                                }
                        }

                        table.setItems(filtered);
                };

                // =====================================================
                // SEARCH
                // =====================================================

                search.textProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters.run());

                // =====================================================
                // GENDER
                // =====================================================

                gender.valueProperty().addListener(
                                (obs, oldValue, newValue) -> applyFilters.run());

                // =====================================================
                // ADD PATIENT
                // =====================================================

                add.setOnAction(e -> {

                        AddPatientPage.show();

                });

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
                // SCENE
                // =====================================================

                Scene patientsScene = new Scene(root);

                DoctorDashboard.changeScene(
                                patientsScene);
        }

        // =====================================================
        // ADD NEW PATIENT
        // =====================================================

        public static void addPatient(
                        Patient patient) {

                if (patient == null) {
                        return;
                }

                controller.getPatients()
                                .add(patient);

                if (table != null) {

                        table.setItems(
                                        FXCollections.observableArrayList(
                                                        controller.getPatients()));

                        table.refresh();
                }
        }
}