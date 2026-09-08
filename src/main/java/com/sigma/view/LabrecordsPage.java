package com.sigma.view;

import com.sigma.model.Labrecords;
import com.sigma.controller.HospitalController.LabrecordsController;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LabrecordsPage {

        // =========================================================
        // COLORS
        // =========================================================

        private static final String BG = "#F7EAF5";
        private static final String WHITE = "#FFFFFF";
        private static final String NAVY = "#17184F";
        private static final String PINK = "#E83E83";
        private static final String LIGHT_PINK = "#FFF0F7";
        private static final String PURPLE = "#8056C5";
        private static final String GREEN = "#67C98F";
        private static final String ORANGE = "#F2A33A";
        private static final String BORDER = "#E9E6EF";
        private static final String GREY = "#77758A";

        // =========================================================
        // STAT LABELS
        // =========================================================

        private Label totalReportsLabel;
        private Label pendingReportsLabel;
        private Label completedReportsLabel;
        private Label todayReportsLabel;

        // =========================================================
        // VARIABLES
        // =========================================================

        private BorderPane root;

        private ObservableList<Labrecords> data = FXCollections.observableArrayList();

        private LabrecordsController controller = new LabrecordsController();

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public LabrecordsPage() {
                createView();
        }

        // =========================================================
        // CREATE VIEW
        // =========================================================

        private void createView() {

                root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                VBox mainContent = new VBox(18);

                mainContent.setPadding(
                                new Insets(28, 35, 30, 35));

                // =====================================================
                // HEADER
                // =====================================================

                BorderPane header = new BorderPane();

                VBox titleBox = new VBox(5);

                Label title = new Label("Lab Records");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                title.setTextFill(
                                Color.web(NAVY));

                Label subtitle = new Label(
                                "View and manage all patient lab reports");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                15));

                subtitle.setTextFill(
                                Color.web(GREY));

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                header.setLeft(titleBox);

                // =====================================================
                // ADD BUTTON
                // =====================================================

                Button addLabButton = new Button("+ Add Lab Record");

                addLabButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                addLabButton.setCursor(Cursor.HAND);

                addLabButton.setStyle(
                                "-fx-background-color: " + PINK + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-padding: 10 16;");

                HBox addButtonBox = new HBox();

                addButtonBox.setAlignment(
                                Pos.CENTER_RIGHT);

                addButtonBox.getChildren().add(
                                addLabButton);

                // =====================================================
                // SUMMARY CARDS
                // =====================================================

                HBox cards = new HBox(18);

                cards.setAlignment(Pos.CENTER);

                VBox totalCard = createStatCard(
                                "▣",
                                "0",
                                "Total Reports",
                                "↗  All Time",
                                PINK);

                VBox pendingCard = createStatCard(
                                "◷",
                                "0",
                                "Pending Reports",
                                "◷  Awaiting Results",
                                PURPLE);

                VBox completedCard = createStatCard(
                                "✓",
                                "0",
                                "Completed Reports",
                                "↗  This Month",
                                GREEN);

                VBox todayCard = createStatCard(
                                "▤",
                                "0",
                                "Today's Reports",
                                "▣  Generated Today",
                                ORANGE);

                cards.getChildren().addAll(
                                totalCard,
                                pendingCard,
                                completedCard,
                                todayCard);

                // =====================================================
                // SEARCH AND FILTER
                // =====================================================

                HBox filterBox = new HBox(20);

                filterBox.setPadding(
                                new Insets(20));

                filterBox.setAlignment(
                                Pos.CENTER_LEFT);

                filterBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Search by patient name or test...");

                searchField.setPrefHeight(45);

                searchField.setPrefWidth(470);

                searchField.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-font-size: 14;" +
                                                "-fx-padding: 0 15;");

                ComboBox<String> departmentBox = new ComboBox<>();

                departmentBox.getItems().addAll(
                                "All Departments",
                                "Hematology",
                                "Biochemistry",
                                "Immunology",
                                "Pathology");

                departmentBox.setValue(
                                "All Departments");

                departmentBox.setPrefWidth(220);

                departmentBox.setPrefHeight(45);

                ComboBox<String> statusBox = new ComboBox<>();

                statusBox.getItems().addAll(
                                "All Status",
                                "Completed",
                                "Pending");

                statusBox.setValue(
                                "All Status");

                statusBox.setPrefWidth(220);

                statusBox.setPrefHeight(45);

                filterBox.getChildren().addAll(
                                searchField,
                                departmentBox,
                                statusBox);

                // =====================================================
                // TABLE
                // =====================================================

                TableView<Labrecords> table = new TableView<>();

                table.setPrefHeight(410);

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                table.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                // =====================================================
                // TABLE COLUMNS
                // =====================================================

                TableColumn<Labrecords, String> numberColumn = new TableColumn<>("No.");

                numberColumn.setCellValueFactory(
                                new PropertyValueFactory<>("number"));

                numberColumn.setPrefWidth(70);

                TableColumn<Labrecords, String> patientColumn = new TableColumn<>("Patient Name");

                patientColumn.setCellValueFactory(
                                new PropertyValueFactory<>("PatientName"));

                TableColumn<Labrecords, String> testColumn = new TableColumn<>("Test Name");

                testColumn.setCellValueFactory(
                                new PropertyValueFactory<>("TestName"));

                TableColumn<Labrecords, String> departmentColumn = new TableColumn<>("Department");

                departmentColumn.setCellValueFactory(
                                new PropertyValueFactory<>("Department"));

                TableColumn<Labrecords, String> dateColumn = new TableColumn<>("Date");

                dateColumn.setCellValueFactory(
                                new PropertyValueFactory<>("Date"));

                TableColumn<Labrecords, String> statusColumn = new TableColumn<>("Status");

                statusColumn.setCellValueFactory(
                                new PropertyValueFactory<>("Status"));

                TableColumn<Labrecords, String> resultsColumn = new TableColumn<>("Results");

                resultsColumn.setCellValueFactory(
                                new PropertyValueFactory<>("Results"));

                // =====================================================
                // ACTION COLUMN
                // =====================================================

                TableColumn<Labrecords, Void> actionColumn = new TableColumn<>("Action");

                actionColumn.setCellFactory(column -> new TableCell<Labrecords, Void>() {

                        private final Button view = new Button("◉");

                        private final Button edit = new Button("✎");

                        private final Button delete = new Button("▢");

                        private final HBox box = new HBox(
                                        5,
                                        view,
                                        edit,
                                        delete);

                        {
                                box.setAlignment(
                                                Pos.CENTER);

                                view.setCursor(
                                                Cursor.HAND);

                                edit.setCursor(
                                                Cursor.HAND);

                                delete.setCursor(
                                                Cursor.HAND);

                                view.setStyle(
                                                "-fx-background-color: #FFF0F7;" +
                                                                "-fx-text-fill: " + PINK + ";" +
                                                                "-fx-border-color: #E9E6EF;" +
                                                                "-fx-border-radius: 5;" +
                                                                "-fx-background-radius: 5;");

                                edit.setStyle(
                                                "-fx-background-color: #EEF5FF;" +
                                                                "-fx-text-fill: #3274C6;" +
                                                                "-fx-border-color: #E9E6EF;" +
                                                                "-fx-border-radius: 5;" +
                                                                "-fx-background-radius: 5;");

                                delete.setStyle(
                                                "-fx-background-color: #FFF0F0;" +
                                                                "-fx-text-fill: #D94A5A;" +
                                                                "-fx-border-color: #E9E6EF;" +
                                                                "-fx-border-radius: 5;" +
                                                                "-fx-background-radius: 5;");

                                // =================================================
                                // VIEW
                                // =================================================

                                view.setOnAction(e -> {

                                        Labrecords record = getTableView()
                                                        .getItems()
                                                        .get(getIndex());

                                        Dialog<ButtonType> viewDialog = new Dialog<>();

                                        viewDialog.setTitle(
                                                        "Lab Report Details");

                                        viewDialog.setHeaderText(
                                                        "Lab Report Information");

                                        VBox content = new VBox(12);

                                        content.setPadding(
                                                        new Insets(20));

                                        Label patientLabel = new Label(
                                                        "Patient: "
                                                                        + record.getPatientName());

                                        Label testLabel = new Label(
                                                        "Test: "
                                                                        + record.getTestName());

                                        Label departmentLabel = new Label(
                                                        "Department: "
                                                                        + record.getDepartment());

                                        Label dateLabel = new Label(
                                                        "Date: "
                                                                        + record.getDate());

                                        Label statusLabel = new Label(
                                                        "Status: "
                                                                        + record.getStatus());

                                        Label resultsLabel = new Label(
                                                        "Results: "
                                                                        + record.getResults());

                                        content.getChildren().addAll(
                                                        patientLabel,
                                                        testLabel,
                                                        departmentLabel,
                                                        dateLabel,
                                                        statusLabel,
                                                        resultsLabel);

                                        // =================================================
                                        // DOCUMENT BUTTON
                                        // =================================================

                                        String documentUrl = record.getDocumentUrl();

                                        Button viewDocumentButton = new Button(
                                                        "View Document");

                                        viewDocumentButton.setCursor(
                                                        Cursor.HAND);

                                        viewDocumentButton.setStyle(
                                                        "-fx-background-color: "
                                                                        + PINK + ";" +
                                                                        "-fx-text-fill: white;" +
                                                                        "-fx-font-weight: bold;" +
                                                                        "-fx-background-radius: 7;" +
                                                                        "-fx-padding: 9 15;");

                                        if (documentUrl == null
                                                        || documentUrl.trim().isEmpty()) {

                                                viewDocumentButton.setDisable(
                                                                true);

                                                viewDocumentButton.setText(
                                                                "No Document Available");

                                        } else {

                                                viewDocumentButton.setOnAction(
                                                                event -> openDocument(
                                                                                documentUrl));
                                        }

                                        content.getChildren().add(
                                                        viewDocumentButton);

                                        viewDialog.getDialogPane()
                                                        .setContent(
                                                                        content);

                                        viewDialog.getDialogPane()
                                                        .getButtonTypes()
                                                        .add(
                                                                        ButtonType.CLOSE);

                                        viewDialog.showAndWait();
                                });

                                // =================================================
                                // EDIT
                                // =================================================

                                edit.setOnAction(e -> {

                                        Labrecords record = getTableView()
                                                        .getItems()
                                                        .get(getIndex());

                                        ChoiceDialog<String> dialog = new ChoiceDialog<>(
                                                        record.getStatus(),
                                                        "Completed",
                                                        "Pending");

                                        dialog.setTitle(
                                                        "Edit Lab Report");

                                        dialog.setHeaderText(
                                                        "Change Report Status");

                                        dialog.setContentText(
                                                        "Select Status:");

                                        dialog.showAndWait()
                                                        .ifPresent(
                                                                        newStatus -> {

                                                                                record.setStatus(
                                                                                                newStatus);

                                                                                controller.updateLabrecord(
                                                                                                record.getNumber(),
                                                                                                record.getPatientName(),
                                                                                                record.getTestName(),
                                                                                                record.getDepartment(),
                                                                                                record.getDate(),
                                                                                                record.getStatus(),
                                                                                                record.getResults(),
                                                                                                record.getDocumentUrl());

                                                                                getTableView()
                                                                                                .refresh();

                                                                                updateStatCards();
                                                                        });
                                });

                                // =================================================
                                // DELETE
                                // =================================================

                                delete.setOnAction(e -> {

                                        Labrecords record = getTableView()
                                                        .getItems()
                                                        .get(getIndex());

                                        Alert confirmation = new Alert(
                                                        Alert.AlertType.CONFIRMATION);

                                        confirmation.setTitle(
                                                        "Delete Lab Report");

                                        confirmation.setHeaderText(
                                                        "Delete Lab Report?");

                                        confirmation.setContentText(
                                                        "Are you sure you want to delete the report of "
                                                                        + record.getPatientName()
                                                                        + "?");

                                        confirmation.showAndWait()
                                                        .ifPresent(
                                                                        response -> {

                                                                                if (response == ButtonType.OK) {

                                                                                        controller.deleteLabrecord(
                                                                                                        record.getNumber());

                                                                                        data.remove(
                                                                                                        record);

                                                                                        updateStatCards();

                                                                                        table.refresh();
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

                                        setGraphic(box);
                                }
                        }
                });

                table.getColumns().addAll(
                                numberColumn,
                                patientColumn,
                                testColumn,
                                departmentColumn,
                                dateColumn,
                                statusColumn,
                                resultsColumn,
                                actionColumn);

                // =====================================================
                // LOAD FIREBASE DATA
                // =====================================================

                List<Labrecords> firebaseData = controller.getAllLabrecords();

                data.clear();

                data.addAll(
                                firebaseData);

                FilteredList<Labrecords> filteredData = new FilteredList<>(
                                data,
                                record -> true);

                table.setItems(
                                filteredData);

                updateStatCards();

                // =====================================================
                // ADD LAB RECORD
                // =====================================================

                addLabButton.setOnAction(e -> {

                        Dialog<ButtonType> dialog = new Dialog<>();

                        dialog.setTitle(
                                        "Add Lab Record");

                        dialog.setHeaderText(
                                        "Enter Lab Record Details");

                        GridPane form = new GridPane();

                        form.setHgap(12);

                        form.setVgap(12);

                        form.setPadding(
                                        new Insets(20));

                        // =================================================
                        // FIELDS
                        // =================================================

                        TextField patientField = new TextField();

                        patientField.setPromptText(
                                        "Patient Name");

                        TextField testField = new TextField();

                        testField.setPromptText(
                                        "Test Name");

                        ComboBox<String> departmentField = new ComboBox<>();

                        departmentField.getItems().addAll(
                                        "Hematology",
                                        "Biochemistry",
                                        "Immunology",
                                        "Pathology");

                        departmentField.setPromptText(
                                        "Select Department");

                        TextField dateField = new TextField();

                        dateField.setPromptText(
                                        "e.g. 04 Sep 2026 | 10:30 AM");

                        ComboBox<String> statusField = new ComboBox<>();

                        statusField.getItems().addAll(
                                        "Completed",
                                        "Pending");

                        statusField.setValue(
                                        "Pending");

                        TextField resultsField = new TextField();

                        resultsField.setPromptText(
                                        "Results");

                        // =================================================
                        // DOCUMENT
                        // =================================================

                        Label documentLabel = new Label(
                                        "No document selected");

                        documentLabel.setTextFill(
                                        Color.web(GREY));

                        Button chooseDocumentButton = new Button(
                                        "Choose Document");

                        chooseDocumentButton.setCursor(
                                        Cursor.HAND);

                        chooseDocumentButton.setStyle(
                                        "-fx-background-color: #FFF0F7;" +
                                                        "-fx-text-fill: " + PINK + ";" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-border-radius: 6;" +
                                                        "-fx-background-radius: 6;" +
                                                        "-fx-padding: 8 12;");

                        final File[] selectedDocument = new File[1];

                        chooseDocumentButton.setOnAction(
                                        event -> {

                                                FileChooser fileChooser = new FileChooser();

                                                fileChooser.setTitle(
                                                                "Select Lab Report Document");

                                                fileChooser.getExtensionFilters()
                                                                .addAll(

                                                                                new FileChooser.ExtensionFilter(
                                                                                                "PDF Documents",
                                                                                                "*.pdf"),

                                                                                new FileChooser.ExtensionFilter(
                                                                                                "Word Documents",
                                                                                                "*.doc",
                                                                                                "*.docx"),

                                                                                new FileChooser.ExtensionFilter(
                                                                                                "Text Documents",
                                                                                                "*.txt"),

                                                                                new FileChooser.ExtensionFilter(
                                                                                                "All Files",
                                                                                                "*.*"));

                                                File file = fileChooser.showOpenDialog(
                                                                dialog.getOwner());

                                                if (file != null) {

                                                        selectedDocument[0] = file;

                                                        documentLabel.setText(
                                                                        file.getName());
                                                }
                                        });

                        // =================================================
                        // FORM
                        // =================================================

                        form.add(
                                        new Label("Patient Name:"),
                                        0,
                                        0);

                        form.add(
                                        patientField,
                                        1,
                                        0);

                        form.add(
                                        new Label("Test Name:"),
                                        0,
                                        1);

                        form.add(
                                        testField,
                                        1,
                                        1);

                        form.add(
                                        new Label("Department:"),
                                        0,
                                        2);

                        form.add(
                                        departmentField,
                                        1,
                                        2);

                        form.add(
                                        new Label("Date:"),
                                        0,
                                        3);

                        form.add(
                                        dateField,
                                        1,
                                        3);

                        form.add(
                                        new Label("Status:"),
                                        0,
                                        4);

                        form.add(
                                        statusField,
                                        1,
                                        4);

                        form.add(
                                        new Label("Results:"),
                                        0,
                                        5);

                        form.add(
                                        resultsField,
                                        1,
                                        5);

                        form.add(
                                        new Label("Document:"),
                                        0,
                                        6);

                        HBox documentBox = new HBox(10);

                        documentBox.setAlignment(
                                        Pos.CENTER_LEFT);

                        documentBox.getChildren().addAll(
                                        chooseDocumentButton,
                                        documentLabel);

                        form.add(
                                        documentBox,
                                        1,
                                        6);

                        patientField.setPrefWidth(280);
                        testField.setPrefWidth(280);
                        departmentField.setPrefWidth(280);
                        dateField.setPrefWidth(280);
                        statusField.setPrefWidth(280);
                        resultsField.setPrefWidth(280);

                        // =================================================
                        // DIALOG BUTTONS
                        // =================================================

                        ButtonType addButton = new ButtonType(
                                        "Add Record",
                                        ButtonBar.ButtonData.OK_DONE);

                        dialog.getDialogPane()
                                        .getButtonTypes()
                                        .addAll(
                                                        addButton,
                                                        ButtonType.CANCEL);

                        dialog.getDialogPane()
                                        .setContent(form);

                        // =================================================
                        // RESULT CONVERTER
                        // =================================================

                        dialog.setResultConverter(
                                        button -> {

                                                if (button == addButton) {

                                                        // =================================
                                                        // VALIDATION
                                                        // =================================

                                                        if (patientField.getText()
                                                                        .trim()
                                                                        .isEmpty()
                                                                        ||
                                                                        testField.getText()
                                                                                        .trim()
                                                                                        .isEmpty()
                                                                        ||
                                                                        departmentField.getValue() == null
                                                                        ||
                                                                        dateField.getText()
                                                                                        .trim()
                                                                                        .isEmpty()
                                                                        ||
                                                                        resultsField.getText()
                                                                                        .trim()
                                                                                        .isEmpty()) {

                                                                Alert warning = new Alert(
                                                                                Alert.AlertType.WARNING);

                                                                warning.setTitle(
                                                                                "Missing Information");

                                                                warning.setHeaderText(
                                                                                "Please fill all fields");

                                                                warning.showAndWait();

                                                                return null;
                                                        }

                                                        // =================================
                                                        // GENERATE NUMBER
                                                        // =================================

                                                        int maxNumber = 0;

                                                        for (Labrecords record : data) {

                                                                try {

                                                                        int currentNumber = Integer.parseInt(
                                                                                        record.getNumber());

                                                                        if (currentNumber > maxNumber) {

                                                                                maxNumber = currentNumber;
                                                                        }

                                                                } catch (NumberFormatException ex) {

                                                                        // Ignore invalid number
                                                                }
                                                        }

                                                        String newNumber = String.valueOf(
                                                                        maxNumber + 1);

                                                        // =================================
                                                        // UPLOAD DOCUMENT
                                                        // =================================

                                                        String documentUrl = "";

                                                        if (selectedDocument[0] != null) {

                                                                try {

                                                                        documentUrl = controller
                                                                                        .uploadLabReportDocument(
                                                                                                        selectedDocument[0]);

                                                                } catch (IOException ex) {

                                                                        Alert error = new Alert(
                                                                                        Alert.AlertType.ERROR);

                                                                        error.setTitle(
                                                                                        "Upload Error");

                                                                        error.setHeaderText(
                                                                                        "Document upload failed");

                                                                        error.setContentText(
                                                                                        ex.getMessage());

                                                                        error.showAndWait();

                                                                        return null;
                                                                }
                                                        }

                                                        // =================================
                                                        // CREATE RECORD
                                                        // =================================

                                                        Labrecords newRecord = new Labrecords(

                                                                        newNumber,

                                                                        patientField
                                                                                        .getText()
                                                                                        .trim(),

                                                                        testField
                                                                                        .getText()
                                                                                        .trim(),

                                                                        departmentField
                                                                                        .getValue(),

                                                                        dateField
                                                                                        .getText()
                                                                                        .trim(),

                                                                        statusField
                                                                                        .getValue(),

                                                                        resultsField
                                                                                        .getText()
                                                                                        .trim(),

                                                                        documentUrl);

                                                        // =================================
                                                        // SAVE TO FIREBASE
                                                        // =================================

                                                        controller.addLabrecord(
                                                                        newRecord.getNumber(),
                                                                        newRecord.getPatientName(),
                                                                        newRecord.getTestName(),
                                                                        newRecord.getDepartment(),
                                                                        newRecord.getDate(),
                                                                        newRecord.getStatus(),
                                                                        newRecord.getResults(),
                                                                        newRecord.getDocumentUrl());

                                                        // =================================
                                                        // ADD TO TABLE
                                                        // =================================

                                                        data.add(
                                                                        newRecord);

                                                        updateStatCards();

                                                        table.refresh();

                                                        return button;
                                                }

                                                return null;
                                        });

                        dialog.showAndWait();
                });

                // =====================================================
                // FILTERING
                // =====================================================

                Runnable updateFilter = () -> {

                        String searchText = searchField.getText()
                                        .trim()
                                        .toLowerCase();

                        String selectedDepartment = departmentBox.getValue();

                        String selectedStatus = statusBox.getValue();

                        filteredData.setPredicate(
                                        record -> {

                                                boolean searchMatch = searchText.isEmpty()

                                                                ||

                                                                (record.getPatientName() != null
                                                                                &&
                                                                                record.getPatientName()
                                                                                                .toLowerCase()
                                                                                                .contains(
                                                                                                                searchText))

                                                                ||

                                                                (record.getTestName() != null
                                                                                &&
                                                                                record.getTestName()
                                                                                                .toLowerCase()
                                                                                                .contains(
                                                                                                                searchText));

                                                boolean departmentMatch = selectedDepartment.equals(
                                                                "All Departments")
                                                                ||
                                                                (record.getDepartment() != null
                                                                                &&
                                                                                record.getDepartment()
                                                                                                .equals(
                                                                                                                selectedDepartment));

                                                boolean statusMatch = selectedStatus.equals(
                                                                "All Status")
                                                                ||
                                                                (record.getStatus() != null
                                                                                &&
                                                                                record.getStatus()
                                                                                                .equals(
                                                                                                                selectedStatus));

                                                return searchMatch
                                                                && departmentMatch
                                                                && statusMatch;
                                        });
                };

                searchField.textProperty()
                                .addListener(
                                                (observable, oldValue, newValue) -> updateFilter.run());

                departmentBox.valueProperty()
                                .addListener(
                                                (observable, oldValue, newValue) -> updateFilter.run());

                statusBox.valueProperty()
                                .addListener(
                                                (observable, oldValue, newValue) -> updateFilter.run());

                // =====================================================
                // ROW HOVER
                // =====================================================

                table.setRowFactory(tv -> {

                        TableRow<Labrecords> row = new TableRow<>();

                        row.setOnMouseEntered(e -> {

                                if (!row.isEmpty()) {

                                        row.setStyle(
                                                        "-fx-background-color: "
                                                                        + LIGHT_PINK + ";");
                                }
                        });

                        row.setOnMouseExited(e -> {

                                row.setStyle("");
                        });

                        return row;
                });

                // =====================================================
                // ADD CONTENT
                // =====================================================

                mainContent.getChildren().addAll(
                                header,
                                cards,
                                addButtonBox,
                                filterBox,
                                table);

                root.setCenter(
                                mainContent);
        }

        // =========================================================
        // OPEN DOCUMENT
        // =========================================================

        private void openDocument(
                        String documentUrl) {

                try {

                        if (documentUrl == null
                                        || documentUrl.trim().isEmpty()) {

                                Alert alert = new Alert(
                                                Alert.AlertType.WARNING);

                                alert.setTitle(
                                                "Document");

                                alert.setHeaderText(
                                                "No document available");

                                alert.showAndWait();

                                return;
                        }

                        if (Desktop.isDesktopSupported()
                                        &&
                                        Desktop.getDesktop()
                                                        .isSupported(
                                                                        Desktop.Action.BROWSE)) {

                                Desktop.getDesktop()
                                                .browse(
                                                                new URI(
                                                                                documentUrl));

                        } else {

                                Alert alert = new Alert(
                                                Alert.AlertType.ERROR);

                                alert.setTitle(
                                                "Cannot Open Document");

                                alert.setHeaderText(
                                                "Browser could not be opened");

                                alert.showAndWait();
                        }

                } catch (Exception ex) {

                        Alert alert = new Alert(
                                        Alert.AlertType.ERROR);

                        alert.setTitle(
                                        "Document Error");

                        alert.setHeaderText(
                                        "Unable to open document");

                        alert.setContentText(
                                        ex.getMessage());

                        alert.showAndWait();
                }
        }

        // =========================================================
        // STAT CARD
        // =========================================================

        private VBox createStatCard(
                        String symbol,
                        String number,
                        String title,
                        String bottomText,
                        String color) {

                VBox card = new VBox(8);

                card.setPadding(
                                new Insets(18));

                card.setPrefHeight(155);

                card.setPrefWidth(260);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 14;" +
                                                "-fx-background-radius: 14;");

                HBox topRow = new HBox(12);

                topRow.setAlignment(
                                Pos.CENTER_LEFT);

                Label symbolLabel = new Label(symbol);

                symbolLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                symbolLabel.setTextFill(
                                Color.web(color));

                symbolLabel.setStyle(
                                "-fx-background-color: " + LIGHT_PINK + ";" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-padding: 8 12;");

                Label numberLabel = new Label(number);

                if (title.equals(
                                "Total Reports")) {

                        totalReportsLabel = numberLabel;

                } else if (title.equals(
                                "Pending Reports")) {

                        pendingReportsLabel = numberLabel;

                } else if (title.equals(
                                "Completed Reports")) {

                        completedReportsLabel = numberLabel;

                } else if (title.equals(
                                "Today's Reports")) {

                        todayReportsLabel = numberLabel;
                }

                numberLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                numberLabel.setTextFill(
                                Color.web(NAVY));

                topRow.getChildren().addAll(
                                symbolLabel,
                                numberLabel);

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                titleLabel.setTextFill(
                                Color.web(NAVY));

                Label bottomLabel = new Label(bottomText);

                bottomLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                bottomLabel.setTextFill(
                                Color.web(color));

                card.getChildren().addAll(
                                topRow,
                                titleLabel,
                                bottomLabel);

                return card;
        }

        // =========================================================
        // UPDATE STAT CARDS
        // =========================================================

        private void updateStatCards() {

                List<Labrecords> records = controller.getAllLabrecords();

                int total = records.size();

                int pending = 0;
                int completed = 0;
                int today = 0;

                LocalDate todayDate = LocalDate.now();

                for (Labrecords record : records) {

                        if (record.getStatus() != null) {

                                if (record.getStatus()
                                                .equalsIgnoreCase(
                                                                "Pending")) {

                                        pending++;

                                } else if (record.getStatus()
                                                .equalsIgnoreCase(
                                                                "Completed")) {

                                        completed++;
                                }
                        }

                        if (record.getDate() != null) {

                                String dateText = record.getDate()
                                                .trim();

                                try {

                                        String datePart = dateText
                                                        .split("\\|")[0]
                                                        .trim();

                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                                        "dd MMM yyyy");

                                        LocalDate recordDate = LocalDate.parse(
                                                        datePart,
                                                        formatter);

                                        if (recordDate.equals(
                                                        todayDate)) {

                                                today++;
                                        }

                                } catch (DateTimeParseException ex) {

                                        System.out.println(
                                                        "Invalid lab record date: "
                                                                        + dateText);
                                }
                        }
                }

                totalReportsLabel.setText(
                                String.valueOf(total));

                pendingReportsLabel.setText(
                                String.valueOf(pending));

                completedReportsLabel.setText(
                                String.valueOf(completed));

                todayReportsLabel.setText(
                                String.valueOf(today));
        }

        // =========================================================
        // GET VIEW
        // =========================================================

        public BorderPane getView() {

                if (root == null) {

                        createView();
                }

                return root;
        }
}