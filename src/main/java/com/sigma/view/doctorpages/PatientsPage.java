
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

    private static final double SCREEN_WIDTH =
            scenesettings.rectanguler2d.getWidth();

    private static final double SCREEN_HEIGHT =
            scenesettings.rectanguler2d.getHeight();

    // ============================================================
    // COLORS - SAME AS APPOINTMENT PAGE
    // ============================================================

    private static final String PINK = "#E84A87";
    private static final String DARK_PINK = "#D93678";

    private static final String LIGHT_PINK = "#FFEAF3";
    private static final String LIGHT_PURPLE = "#F3ECFF";
    private static final String PURPLE = "#9B4DCC";

    private static final String DARK_TEXT = "#24234F";
    private static final String SECONDARY_TEXT = "#666680";

    private static final String BORDER = "#E7DCE8";

    private static final String PAGE_BACKGROUND = "#FFF8FC";

    private static final String BUTTON_GRADIENT =
            "linear-gradient(to right, #F54B87, #9B4DCC)";

    // ============================================================
    // CONTROLLER
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
                    "Patient controller is not available."
            );

            return;
        }

        try {

            patientController.refreshPatients();

            // ====================================================
            // MAIN ROOT
            // ====================================================

            VBox root = new VBox(20);

            root.setPadding(
                    new Insets(28, 35, 28, 35)
            );

            root.setFillWidth(true);

            root.setMinWidth(0);
            root.setMaxWidth(Double.MAX_VALUE);

            root.setStyle(
                    "-fx-background-color: "
                            + PAGE_BACKGROUND
                            + ";"
            );

            // ====================================================
            // HEADER
            // ====================================================

            HBox header = new HBox();

            header.setAlignment(
                    Pos.CENTER_LEFT
            );

            Label heading =
                    new Label("Patients");

            heading.setStyle(
                    "-fx-font-family: 'Arial';"
                            + "-fx-font-size: 24px;"
                            + "-fx-font-weight: bold;"
                            + "-fx-text-fill: "
                            + DARK_TEXT
                            + ";"
            );

            Label subtitle =
                    new Label(
                            "Manage and view patient information"
                    );

            subtitle.setStyle(
                    "-fx-font-family: 'Arial';"
                            + "-fx-font-size: 13px;"
                            + "-fx-text-fill: "
                            + SECONDARY_TEXT
                            + ";"
            );

            VBox headingBox =
                    new VBox(4);

            headingBox.getChildren().addAll(
                    heading,
                    subtitle
            );

            Region headerSpacer =
                    new Region();

            HBox.setHgrow(
                    headerSpacer,
                    Priority.ALWAYS
            );

            header.getChildren().addAll(
                    headingBox,
                    headerSpacer
            );

            // ====================================================
            // FILTER BOX
            // ====================================================

            HBox filter =
                    new HBox(10);

            filter.setAlignment(
                    Pos.CENTER_LEFT
            );

            filter.setPadding(
                    new Insets(14)
            );

            filter.setMaxWidth(
                    Double.MAX_VALUE
            );

            filter.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-background-radius: 18;"
                            + "-fx-border-color: "
                            + BORDER
                            + ";"
                            + "-fx-border-radius: 18;"
                            + "-fx-border-width: 1;"
            );

            // ====================================================
            // SEARCH
            // ====================================================

            TextField search =
                    new TextField();

            search.setPromptText(
                    "Search patient name or contact..."
            );

            search.setPrefWidth(280);
            search.setPrefHeight(40);

            search.setMaxWidth(
                    Double.MAX_VALUE
            );

            search.setStyle(
                    "-fx-background-color: #FFFFFF;"
                            + "-fx-border-color: "
                            + BORDER
                            + ";"
                            + "-fx-border-radius: 10;"
                            + "-fx-background-radius: 10;"
                            + "-fx-padding: 0 12;"
                            + "-fx-font-family: Arial;"
                            + "-fx-font-size: 13px;"
                            + "-fx-text-fill: "
                            + DARK_TEXT
                            + ";"
            );

            HBox.setHgrow(
                    search,
                    Priority.ALWAYS
            );

            // ====================================================
            // GENDER
            // ====================================================

            ComboBox<String> gender =
                    new ComboBox<>();

            gender.getItems().addAll(
                    "All",
                    "Female",
                    "Male",
                    "Other"
            );

            gender.setValue("All");

            gender.setPrefHeight(40);

            gender.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: "
                            + BORDER
                            + ";"
                            + "-fx-border-radius: 10;"
                            + "-fx-background-radius: 10;"
                            + "-fx-font-family: Arial;"
                            + "-fx-font-size: 13px;"
                            + "-fx-text-fill: "
                            + DARK_TEXT
                            + ";"
                            + "-fx-cursor: hand;"
            );

            // ====================================================
            // ADD PATIENT
            // ====================================================

            Button add =
                    new Button("+  Add Patient");

            add.setPrefHeight(40);
            add.setMinWidth(135);

            stylePrimaryButton(add);

            add.setOnMouseEntered(
                    e -> stylePrimaryButtonHover(add)
            );

            add.setOnMouseExited(
                    e -> stylePrimaryButton(add)
            );

            add.setOnAction(
                    e -> AddPatientPage.show()
            );

            Region filterSpacer =
                    new Region();

            HBox.setHgrow(
                    filterSpacer,
                    Priority.ALWAYS
            );

            filter.getChildren().addAll(
                    search,
                    gender,
                    filterSpacer,
                    add
            );

            // ====================================================
            // TABLE
            // ====================================================

            table =
                    new TableView<>();

            table.setMaxWidth(
                    Double.MAX_VALUE
            );

            table.setMaxHeight(
                    Double.MAX_VALUE
            );

            // SAME AS APPOINTMENT PAGE
            table.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
            );

            table.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-control-inner-background: white;"
                            + "-fx-border-color: "
                            + BORDER
                            + ";"
                            + "-fx-border-radius: 14;"
                            + "-fx-background-radius: 14;"
                            + "-fx-table-cell-border-color: #F0E8F1;"
                            + "-fx-font-family: Arial;"
            );

            // ====================================================
            // COLUMNS
            // ====================================================

            TableColumn<Patient, String> name =
                    new TableColumn<>("Patient");

            TableColumn<Patient, String> age =
                    new TableColumn<>("Age / Gender");

            TableColumn<Patient, String> contact =
                    new TableColumn<>("Contact");

            TableColumn<Patient, String> lastVisit =
                    new TableColumn<>("Last Visit");

            TableColumn<Patient, String> nextVisit =
                    new TableColumn<>("Next Visit");

            TableColumn<Patient, String> action =
                    new TableColumn<>("Action");

            // ====================================================
            // VALUE FACTORIES
            // ====================================================

            name.setCellValueFactory(
                    data ->
                            data.getValue()
                                    .nameProperty()
            );

            age.setCellValueFactory(
                    data ->
                            new javafx.beans.property.SimpleStringProperty(
                                    safe(data.getValue().getAge())
                                            + " Y / "
                                            + safe(data.getValue().getGender())
                            )
            );

            contact.setCellValueFactory(
                    data ->
                            data.getValue()
                                    .contactProperty()
            );

            lastVisit.setCellValueFactory(
                    data ->
                            data.getValue()
                                    .lastVisitProperty()
            );

            nextVisit.setCellValueFactory(
                    data ->
                            data.getValue()
                                    .nextVisitProperty()
            );

            // ====================================================
            // SAME TABLE TEXT STYLE AS APPOINTMENT
            // ====================================================

            applyDarkTableCellStyle(name);
            applyDarkTableCellStyle(age);
            applyDarkTableCellStyle(contact);
            applyDarkTableCellStyle(lastVisit);
            applyDarkTableCellStyle(nextVisit);

            // ====================================================
            // HEADER STYLE
            // ====================================================

            String tableHeaderStyle =
                    "-fx-font-family: 'Arial';"
                            + "-fx-font-size: 13px;"
                            + "-fx-font-weight: bold;"
                            + "-fx-text-fill: "
                            + DARK_TEXT
                            + ";";

            name.setStyle(tableHeaderStyle);
            age.setStyle(tableHeaderStyle);
            contact.setStyle(tableHeaderStyle);
            lastVisit.setStyle(tableHeaderStyle);
            nextVisit.setStyle(tableHeaderStyle);
            action.setStyle(tableHeaderStyle);

            // ====================================================
            // ACTION COLUMN WIDTH
            // ====================================================

            action.setPrefWidth(210);
            action.setMinWidth(190);
            action.setResizable(true);

            // ====================================================
            // ACTION COLUMN
            // SAME AS APPOINTMENT PAGE
            // ====================================================

            action.setCellFactory(
                    column ->
                            new TableCell<Patient, String>() {

                                private final Button viewButton =
                                        new Button("View");

                                private final Button editButton =
                                        new Button("Edit");

                                private final Button deleteButton =
                                        new Button("Delete");

                                private final HBox buttons =
                                        new HBox(7);

                                {

                                    buttons.setAlignment(
                                            Pos.CENTER
                                    );

                                    // ====================================
                                    // VIEW
                                    // ====================================

                                    styleViewButton(viewButton);

                                    viewButton.setOnMouseEntered(
                                            e ->
                                                    styleViewButtonHover(
                                                            viewButton
                                                    )
                                    );

                                    viewButton.setOnMouseExited(
                                            e ->
                                                    styleViewButton(
                                                            viewButton
                                                    )
                                    );

                                    // ====================================
                                    // EDIT
                                    // ====================================

                                    styleEditButton(editButton);

                                    editButton.setOnMouseEntered(
                                            e ->
                                                    styleEditButtonHover(
                                                            editButton
                                                    )
                                    );

                                    editButton.setOnMouseExited(
                                            e ->
                                                    styleEditButton(
                                                            editButton
                                                    )
                                    );

                                    // ====================================
                                    // DELETE
                                    // ====================================

                                    styleDeleteButton(deleteButton);

                                    deleteButton.setOnMouseEntered(
                                            e ->
                                                    styleDeleteButtonHover(
                                                            deleteButton
                                                    )
                                    );

                                    deleteButton.setOnMouseExited(
                                            e ->
                                                    styleDeleteButton(
                                                            deleteButton
                                                    )
                                    );

                                    // ====================================
                                    // VIEW ACTION
                                    // ====================================

                                    viewButton.setOnAction(
                                            e -> {

                                                int index =
                                                        getIndex();

                                                if (index < 0 ||
                                                        index >=
                                                                getTableView()
                                                                        .getItems()
                                                                        .size()) {

                                                    return;
                                                }

                                                Patient patient =
                                                        getTableView()
                                                                .getItems()
                                                                .get(index);

                                                showPatientDetails(
                                                        patient
                                                );
                                            }
                                    );

                                    // ====================================
                                    // EDIT ACTION
                                    // ====================================

                                    editButton.setOnAction(
                                            e -> {

                                                int index =
                                                        getIndex();

                                                if (index < 0 ||
                                                        index >=
                                                                getTableView()
                                                                        .getItems()
                                                                        .size()) {

                                                    return;
                                                }

                                                Patient patient =
                                                        getTableView()
                                                                .getItems()
                                                                .get(index);

                                                showEditPatientDialog(
                                                        patientController,
                                                        patient
                                                );
                                            }
                                    );

                                    // ====================================
                                    // DELETE ACTION
                                    // ====================================

                                    deleteButton.setOnAction(
                                            e -> {

                                                int index =
                                                        getIndex();

                                                if (index < 0 ||
                                                        index >=
                                                                getTableView()
                                                                        .getItems()
                                                                        .size()) {

                                                    return;
                                                }

                                                Patient patient =
                                                        getTableView()
                                                                .getItems()
                                                                .get(index);

                                                deletePatient(
                                                        patientController,
                                                        patient
                                                );
                                            }
                                    );

                                    buttons.getChildren().addAll(
                                            viewButton,
                                            editButton,
                                            deleteButton
                                    );
                                }

                                @Override
                                protected void updateItem(
                                        String item,
                                        boolean empty
                                ) {

                                    super.updateItem(
                                            item,
                                            empty
                                    );

                                    if (empty) {

                                        setText(null);
                                        setGraphic(null);

                                        setStyle(
                                                "-fx-background-color: transparent;"
                                                        + "-fx-text-fill: "
                                                        + DARK_TEXT
                                                        + ";"
                                                        + "-fx-font-family: Arial;"
                                                        + "-fx-font-size: 13px;"
                                        );

                                    } else {

                                        setText(null);
                                        setGraphic(buttons);

                                        setAlignment(
                                                Pos.CENTER
                                        );

                                        setStyle(
                                                "-fx-background-color: transparent;"
                                                        + "-fx-text-fill: "
                                                        + DARK_TEXT
                                                        + ";"
                                                        + "-fx-font-family: Arial;"
                                                        + "-fx-font-size: 13px;"
                                        );
                                    }
                                }
                            }
            );

            // ====================================================
            // ROW HOVER
            // EXACT APPOINTMENT STYLE
            // ====================================================

            table.setRowFactory(
                    tv -> {

                        TableRow<Patient> row =
                                new TableRow<>();

                        row.setStyle(
                                "-fx-background-color: white;"
                                        + "-fx-text-fill: "
                                        + DARK_TEXT
                                        + ";"
                        );

                        row.setOnMouseEntered(
                                e -> {

                                    if (!row.isEmpty()) {

                                        row.setStyle(
                                                "-fx-background-color: "
                                                        + LIGHT_PINK
                                                        + ";"
                                                        + "-fx-text-fill: "
                                                        + DARK_TEXT
                                                        + ";"
                                        );
                                    }
                                }
                        );

                        row.setOnMouseExited(
                                e -> {

                                    if (!row.isEmpty()) {

                                        row.setStyle(
                                                "-fx-background-color: white;"
                                                        + "-fx-text-fill: "
                                                        + DARK_TEXT
                                                        + ";"
                                        );
                                    }
                                }
                        );

                        return row;
                    }
            );

            // ====================================================
            // COLUMNS
            // ====================================================

            table.getColumns().addAll(
                    name,
                    age,
                    contact,
                    lastVisit,
                    nextVisit,
                    action
            );

            table.setItems(
                    patientController.getPatients()
            );

            // ====================================================
            // PLACEHOLDER
            // ====================================================

            Label emptyLabel =
                    new Label(
                            "No patients found."
                    );

            emptyLabel.setStyle(
                    "-fx-font-family: Arial;"
                            + "-fx-font-size: 15px;"
                            + "-fx-text-fill: "
                            + SECONDARY_TEXT
                            + ";"
            );

            table.setPlaceholder(
                    emptyLabel
            );

            // ====================================================
            // FILTER FUNCTION
            // ====================================================

            Runnable applyFilters =
                    () -> {

                        String searchText =
                                search.getText() == null
                                        ? ""
                                        : search.getText()
                                                .trim()
                                                .toLowerCase();

                        String selectedGender =
                                gender.getValue();

                        ObservableList<Patient> filtered =
                                FXCollections.observableArrayList();

                        for (Patient patient :
                                patientController.getPatients()) {

                            String patientName =
                                    safe(
                                            patient.getName()
                                    ).toLowerCase();

                            String patientContact =
                                    safe(
                                            patient.getContact()
                                    ).toLowerCase();

                            String patientGender =
                                    safe(
                                            patient.getGender()
                                    );

                            boolean matchesSearch =
                                    searchText.isEmpty()
                                            || patientName.contains(
                                                    searchText
                                            )
                                            || patientContact.contains(
                                                    searchText
                                            );

                            boolean matchesGender =
                                    selectedGender == null
                                            || selectedGender.equalsIgnoreCase(
                                                    "All"
                                            )
                                            || patientGender.equalsIgnoreCase(
                                                    selectedGender
                                            );

                            if (matchesSearch &&
                                    matchesGender) {

                                filtered.add(patient);
                            }
                        }

                        table.setItems(filtered);
                    };

            search.textProperty().addListener(
                    (obs, oldValue, newValue) ->
                            applyFilters.run()
            );

            gender.valueProperty().addListener(
                    (obs, oldValue, newValue) ->
                            applyFilters.run()
            );

            // ====================================================
            // GROW
            // ====================================================

            VBox.setVgrow(
                    table,
                    Priority.ALWAYS
            );

            // ====================================================
            // ROOT CONTENT
            // ====================================================

            root.getChildren().addAll(
                    header,
                    filter,
                    table
            );

            VBox.setVgrow(
                    table,
                    Priority.ALWAYS
            );

            // ====================================================
            // PAGE
            // ====================================================

            BorderPane page =
                    new BorderPane();

            page.setLeft(
                    DoctorDashboard.createSidebar(
                            "Patients"
                    )
            );

            page.setCenter(root);

            // ====================================================
            // SCENE
            // ====================================================

            Scene patientsScene =
                    new Scene(
                            page,
                            SCREEN_WIDTH,
                            SCREEN_HEIGHT
                    );

            DoctorDashboard.changeScene(
                    patientsScene
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Patients Page Error",
                    "Unable to open Patients page."
            );
        }
    }

    // ============================================================
    // SAME TABLE CELL STYLE AS APPOINTMENT
    // ============================================================

    private static void applyDarkTableCellStyle(
            TableColumn<Patient, String> column
    ) {

        column.setCellFactory(
                tableColumn ->
                        new TableCell<Patient, String>() {

                            @Override
                            protected void updateItem(
                                    String value,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        value,
                                        empty
                                );

                                if (empty) {

                                    setText(null);
                                    setGraphic(null);

                                    setStyle(
                                            "-fx-background-color: transparent;"
                                                    + "-fx-text-fill: "
                                                    + DARK_TEXT
                                                    + ";"
                                                    + "-fx-font-family: Arial;"
                                                    + "-fx-font-size: 13px;"
                                    );

                                    return;
                                }

                                setText(
                                        value == null
                                                ? ""
                                                : value
                                );

                                setTextFill(
                                        javafx.scene.paint.Color.web(
                                                DARK_TEXT
                                        )
                                );

                                setFont(
                                        javafx.scene.text.Font.font(
                                                "Arial",
                                                javafx.scene.text.FontWeight.NORMAL,
                                                13
                                        )
                                );

                                setAlignment(
                                        Pos.CENTER_LEFT
                                );

                                setStyle(
                                        "-fx-background-color: transparent;"
                                                + "-fx-text-fill: "
                                                + DARK_TEXT
                                                + ";"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-size: 13px;"
                                );
                            }
                        }
        );
    }

    // ============================================================
    // VIEW BUTTON
    // EXACT APPOINTMENT STYLE
    // ============================================================

    private static void styleViewButton(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + LIGHT_PINK
                        + ";"
                        + "-fx-text-fill: "
                        + PINK
                        + ";"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void styleViewButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + PINK
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // EDIT BUTTON
    // EXACT APPOINTMENT STYLE
    // ============================================================

    private static void styleEditButton(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + LIGHT_PURPLE
                        + ";"
                        + "-fx-text-fill: "
                        + PURPLE
                        + ";"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void styleEditButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + PURPLE
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // DELETE BUTTON
    // EXACT APPOINTMENT STYLE
    // ============================================================

    private static void styleDeleteButton(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + LIGHT_PINK
                        + ";"
                        + "-fx-text-fill: "
                        + DARK_PINK
                        + ";"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: #F3C5D8;"
                        + "-fx-border-radius: 7;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void styleDeleteButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + DARK_PINK
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 12px;"
                        + "-fx-background-radius: 7;"
                        + "-fx-border-color: transparent;"
                        + "-fx-border-radius: 7;"
                        + "-fx-padding: 7 10 7 10;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // PRIMARY BUTTON
    // ============================================================

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + BUTTON_GRADIENT
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20;"
                        + "-fx-padding: 0 18;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void stylePrimaryButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + "linear-gradient(to right, #9B4DCC, #E84A87)"
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20;"
                        + "-fx-padding: 0 18;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // PATIENT DETAILS
    // ============================================================

    private static void showPatientDetails(
            Patient patient
    ) {

        if (patient == null) {
            return;
        }

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Patient Details"
        );

        alert.setHeaderText(
                safe(patient.getName())
        );

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
                        + safe(patient.getNextVisit())
        );

        alert.showAndWait();
    }

    // ============================================================
    // EDIT PATIENT
    // ============================================================

    private static void showEditPatientDialog(
            PatientController patientController,
            Patient patient
    ) {

        if (patient == null ||
                patientController == null) {

            return;
        }

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Patient"
        );

        dialog.setHeaderText(
                "Update patient information"
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(12);
        grid.setVgap(10);

        grid.setPadding(
                new Insets(20)
        );

        TextField name =
                new TextField(
                        safe(patient.getName())
                );

        TextField age =
                new TextField(
                        safe(patient.getAge())
                );

        ComboBox<String> gender =
                new ComboBox<>();

        gender.getItems().addAll(
                "Female",
                "Male",
                "Other"
        );

        gender.setValue(
                safe(patient.getGender())
        );

        TextField contact =
                new TextField(
                        safe(patient.getContact())
                );

        TextField lastVisit =
                new TextField(
                        safe(patient.getLastVisit())
                );

        TextField nextVisit =
                new TextField(
                        safe(patient.getNextVisit())
                );

        Label nameLabel =
                new Label("Name:");

        Label ageLabel =
                new Label("Age:");

        Label genderLabel =
                new Label("Gender:");

        Label contactLabel =
                new Label("Contact:");

        Label lastVisitLabel =
                new Label("Last Visit:");

        Label nextVisitLabel =
                new Label("Next Visit:");

        String labelStyle =
                "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + DARK_TEXT
                        + ";";

        nameLabel.setStyle(labelStyle);
        ageLabel.setStyle(labelStyle);
        genderLabel.setStyle(labelStyle);
        contactLabel.setStyle(labelStyle);
        lastVisitLabel.setStyle(labelStyle);
        nextVisitLabel.setStyle(labelStyle);

        grid.addRow(0, nameLabel, name);
        grid.addRow(1, ageLabel, age);
        grid.addRow(2, genderLabel, gender);
        grid.addRow(3, contactLabel, contact);
        grid.addRow(4, lastVisitLabel, lastVisit);
        grid.addRow(5, nextVisitLabel, nextVisit);

        dialog.getDialogPane().setContent(
                grid
        );

        ButtonType save =
                new ButtonType(
                        "Save",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        save,
                        ButtonType.CANCEL
                );

        dialog.getDialogPane().setStyle(
                "-fx-background-color: "
                        + PAGE_BACKGROUND
                        + ";"
        );

        Button saveButton =
                (Button) dialog.getDialogPane()
                        .lookupButton(save);

        if (saveButton != null) {

            saveButton.setStyle(
                    "-fx-background-color: "
                            + BUTTON_GRADIENT
                            + ";"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;"
                            + "-fx-background-radius: 18;"
                            + "-fx-cursor: hand;"
            );
        }

        dialog.setResultConverter(
                button -> {

                    if (button != save) {
                        return button;
                    }

                    if (name.getText().trim().isEmpty()
                            || age.getText().trim().isEmpty()
                            || contact.getText().trim().isEmpty()
                            || gender.getValue() == null) {

                        showError(
                                "Incomplete Information",
                                "Please fill all required fields."
                        );

                        return null;
                    }

                    try {

                        int enteredAge =
                                Integer.parseInt(
                                        age.getText().trim()
                                );

                        if (enteredAge < 0 ||
                                enteredAge > 120) {

                            showError(
                                    "Invalid Age",
                                    "Age must be between 0 and 120."
                            );

                            return null;
                        }

                    } catch (NumberFormatException ex) {

                        showError(
                                "Invalid Age",
                                "Age must contain numbers only."
                        );

                        return null;
                    }

                    boolean updated =
                            patientController.updatePatient(
                                    patient,
                                    name.getText().trim(),
                                    age.getText().trim(),
                                    gender.getValue(),
                                    contact.getText().trim(),
                                    lastVisit.getText().trim(),
                                    nextVisit.getText().trim()
                            );

                    if (updated) {

                        table.refresh();

                        showInfo(
                                "Patient Updated",
                                "Patient information updated successfully."
                        );

                    } else {

                        showError(
                                "Update Failed",
                                "Unable to update patient information."
                        );
                    }

                    return button;
                }
        );

        dialog.showAndWait();
    }

    // ============================================================
    // DELETE PATIENT
    // ============================================================

    private static void deletePatient(
            PatientController patientController,
            Patient patient
    ) {

        if (patient == null ||
                patientController == null) {

            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Patient"
        );

        confirmation.setHeaderText(
                "Delete this patient?"
        );

        confirmation.setContentText(
                safe(patient.getName())
        );

        confirmation.showAndWait()
                .ifPresent(result -> {

                    if (result == ButtonType.OK) {

                        try {

                            // Use the controller's existing delete method
                            // if available in your PatientController.

                            patientController.getPatients()
                                    .remove(patient);

                            table.setItems(
                                    patientController.getPatients()
                            );

                            table.refresh();

                            showInfo(
                                    "Patient Deleted",
                                    "Patient removed successfully."
                            );

                        } catch (Exception e) {

                            e.printStackTrace();

                            showError(
                                    "Delete Failed",
                                    "Unable to delete patient."
                            );
                        }
                    }
                });
    }

    // ============================================================
    // ADD PATIENT
    // ============================================================

    public static void addPatient(
            Patient patient
    ) {

        if (patient == null) {
            return;
        }

        PatientController patientController =
                getController();

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
                    patientController.getPatients()
            );

            table.refresh();
        }
    }

    // ============================================================
    // SAFE
    // ============================================================

    private static String safe(
            String value
    ) {

        return value == null
                ? ""
                : value;
    }

    // ============================================================
    // ERROR
    // ============================================================

    private static void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // ============================================================
    // INFO
    // ============================================================

    private static void showInfo(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

