
package com.sigma.view;

import com.sigma.controller.Ashabeneficiariescontroller;
import com.sigma.controller.Ashavisitcontroller;
import com.sigma.dao.Ashavisit;
import com.sigma.model.AshaBeneficiary;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.ListenerRegistration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Asha_beneficiaries {

        // =========================================================
        // SCENE SIZE
        // =========================================================

        private static final int SCENE_WIDTH = 1300;

        // =========================================================
        // COLORS
        // =========================================================

        private static final String COLOR_BG = "#FEF9FC";
        private static final String COLOR_PRIMARY_PURPLE = "#EC4988";

        private static final String COLOR_ICON_PURPLE = "#C9B6FF";
        private static final String COLOR_ICON_PINK = "#F8D7E3";
        private static final String COLOR_ICON_BLUE = "#E0F2FE";
        private static final String COLOR_ICON_GREEN = "#D1FAE5";
        private static final String COLOR_ICON_ORANGE = "#FEF3C7";

        private static final String COLOR_TEXT_MUTED = "#718096";

        // =========================================================
        // LABELS
        // =========================================================

        private static final String LABEL_BENEFICIARIES = "Beneficiaries";

        private static final String LABEL_TRACK_MANAGE = "Track and manage all beneficiaries in your area";

        private static final String LABEL_TOTAL_BENEFICIARIES = "Total Beneficiaries";

        private static final String LABEL_PREGNANT_WOMEN = "Pregnant Women";

        private static final String LABEL_CHILDREN = "Children (0-5 Yrs)";

        private static final String LABEL_FULLY_IMMUNIZED = "Fully Immunized";

        private static final String LABEL_PENDING_VISITS = "Pending Visits";

        private static final String LABEL_ALL_BENEFICIARIES = "All Beneficiaries";

        // =========================================================
        // DATA
        // =========================================================

        private final ObservableList<AshaBeneficiary> masterData = FXCollections.observableArrayList();

        private FilteredList<AshaBeneficiary> filteredData;

        private String currentFilter = "All";

        // =========================================================
        // CONTROLLERS
        // =========================================================

        private final Ashabeneficiariescontroller controller = new Ashabeneficiariescontroller();

        private final Ashavisitcontroller visitController = new Ashavisitcontroller();

        // =========================================================
        // TABLE
        // =========================================================

        private TableView<AshaBeneficiary> beneficiaryTable;

        private ListenerRegistration beneficiaryListener;

        // =========================================================
        // TABLE COUNT
        // =========================================================

        private Label beneficiaryCountLabel;

        // =========================================================
        // METRIC LABELS
        // =========================================================

        private Label totalBeneficiariesValue;
        private Label pregnantWomenValue;
        private Label childrenValue;
        private Label fullyImmunizedValue;
        private Label pendingVisitsValue;

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        public Node getBeneficiariesContent() {

                loadFirebaseData();

                filteredData = new FilteredList<>(
                                masterData,
                                p -> true);

                startRealtimeListener();

                VBox root = new VBox(14);

                root.setMaxWidth(Double.MAX_VALUE);
                root.setFillWidth(true);

                root.setPadding(
                                new Insets(
                                                8,
                                                14,
                                                10,
                                                14));

                root.setStyle(
                                "-fx-background-color: " +
                                                COLOR_BG +
                                                ";");

                // =====================================================
                // TOP SECTION
                // =====================================================

                VBox topSection = createTopSection();

                topSection.setMinHeight(88);
                topSection.setPrefHeight(88);
                topSection.setMaxHeight(88);

                // =====================================================
                // METRICS
                // =====================================================

                GridPane metricsGrid = createMetricsGrid();

                metricsGrid.setMinHeight(125);
                metricsGrid.setPrefHeight(125);
                metricsGrid.setMaxHeight(125);

                // =====================================================
                // TABLE
                // =====================================================

                VBox tableSection = createTableSection();

                tableSection.setMinHeight(350);
                tableSection.setPrefHeight(350);
                tableSection.setMaxHeight(350);

                tableSection.setMaxWidth(
                                Double.MAX_VALUE);

                // =====================================================
                // ACTIVITY
                // =====================================================

                GridPane activityGrid = createActivityGrid();

                activityGrid.setMinHeight(100);
                activityGrid.setPrefHeight(100);
                activityGrid.setMaxHeight(100);

                activityGrid.setMaxWidth(
                                Double.MAX_VALUE);

                root.getChildren().addAll(
                                topSection,
                                metricsGrid,
                                tableSection,
                                activityGrid);

                return root;
        }

        // =========================================================
        // LOAD FIREBASE
        // =========================================================

        private void loadFirebaseData() {

                try {

                        List<AshaBeneficiary> firebaseData = controller.getAllAshaBeneficiaries();

                        if (firebaseData != null) {

                                masterData.setAll(firebaseData);

                        } else {

                                masterData.clear();
                        }

                        updateMetrics();

                } catch (Exception e) {

                        e.printStackTrace();

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Unable to load beneficiaries from Firebase.");
                }
        }

        // =========================================================
        // REALTIME LISTENER
        // =========================================================

        private void startRealtimeListener() {

                if (beneficiaryListener != null) {

                        beneficiaryListener.remove();
                        beneficiaryListener = null;
                }

                beneficiaryListener = controller.listenToAshaBeneficiaries(
                                (snapshots, error) -> {

                                        if (error != null) {

                                                error.printStackTrace();
                                                return;
                                        }

                                        if (snapshots == null) {
                                                return;
                                        }

                                        java.util.ArrayList<AshaBeneficiary> updatedData = new java.util.ArrayList<>();

                                        for (DocumentSnapshot doc : snapshots.getDocuments()) {

                                                try {

                                                        AshaBeneficiary beneficiary = doc.toObject(
                                                                        AshaBeneficiary.class);

                                                        if (beneficiary != null) {

                                                                updatedData.add(
                                                                                beneficiary);
                                                        }

                                                } catch (Exception e) {

                                                        e.printStackTrace();
                                                }
                                        }

                                        Platform.runLater(() -> {

                                                masterData.setAll(
                                                                updatedData);

                                                applyFilters("");

                                                updateMetrics();

                                                if (beneficiaryTable != null) {

                                                        beneficiaryTable.refresh();
                                                }

                                                updateBeneficiaryCount();
                                        });
                                });
        }

        // =========================================================
        // UPDATE COUNT
        // =========================================================

        private void updateBeneficiaryCount() {

                if (beneficiaryCountLabel == null) {
                        return;
                }

                beneficiaryCountLabel.setText(
                                "Total " +
                                                filteredData.size() +
                                                " Beneficiaries");
        }

        // =========================================================
        // UPDATE METRICS
        // =========================================================

        private void updateMetrics() {

                if (totalBeneficiariesValue == null) {
                        return;
                }

                totalBeneficiariesValue.setText(
                                String.valueOf(masterData.size()));

                int pregnantCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getCategory())
                                                                .toLowerCase()
                                                                .contains("pregnant"))
                                .count();

                pregnantWomenValue.setText(
                                String.valueOf(pregnantCount));

                int childrenCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getCategory())
                                                                .toLowerCase()
                                                                .contains("child"))
                                .count();

                childrenValue.setText(
                                String.valueOf(childrenCount));

                int immunizedCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getStatus())
                                                                .equalsIgnoreCase(
                                                                                "Immunized"))
                                .count();

                fullyImmunizedValue.setText(
                                String.valueOf(immunizedCount));

                int pendingCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getStatus())
                                                                .equalsIgnoreCase(
                                                                                "Follow-up Due"))
                                .count();

                pendingVisitsValue.setText(
                                String.valueOf(pendingCount));
        }

        // =========================================================
        // TOP SECTION
        // =========================================================

        private VBox createTopSection() {

                VBox main = new VBox(4);

                HBox headerBox = new HBox();

                headerBox.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titleBox = new VBox(2);

                Label titleLabel = new Label(
                                LABEL_BENEFICIARIES);

                titleLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                24));

                Label subtitleLabel = new Label(
                                LABEL_TRACK_MANAGE);

                subtitleLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.NORMAL,
                                                12));

                subtitleLabel.setTextFill(
                                Color.web(
                                                COLOR_TEXT_MUTED));

                titleBox.getChildren().addAll(
                                titleLabel,
                                subtitleLabel);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Search beneficiaries...");

                searchField.setPrefWidth(220);
                searchField.setPrefHeight(36);

                searchField.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E2E8F0;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-padding: 0 10px;");

                searchField.textProperty()
                                .addListener(
                                                (observable,
                                                                oldValue,
                                                                newValue) -> applyFilters(newValue));

                Button filterButton = new Button("Filter");

                filterButton.setPrefWidth(80);
                filterButton.setPrefHeight(36);

                filterButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #C9B6FF;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-text-fill: #6C47FF;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                filterButton.setOnAction(
                                e -> showFilterDialog());

                Button addButton = new Button(
                                "+ Add Beneficiary");

                addButton.setPrefWidth(150);
                addButton.setPrefHeight(36);

                addButton.setStyle(
                                "-fx-background-color: " +
                                                COLOR_PRIMARY_PURPLE +
                                                ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                addButton.setOnAction(
                                e -> showAddBeneficiaryDialog());

                HBox.setMargin(
                                filterButton,
                                new Insets(0, 8, 0, 8));

                headerBox.getChildren().addAll(
                                titleBox,
                                spacer,
                                searchField,
                                filterButton,
                                addButton);

                main.getChildren().add(
                                headerBox);

                return main;
        }

        // =========================================================
        // FILTER
        // =========================================================

        private void applyFilters(String searchText) {

                if (filteredData == null) {
                        return;
                }

                String search = searchText == null
                                ? ""
                                : searchText
                                                .trim()
                                                .toLowerCase();

                filteredData.setPredicate(
                                beneficiary -> {

                                        if (beneficiary == null) {
                                                return false;
                                        }

                                        boolean searchMatch = search.isEmpty()
                                                        ||
                                                        safe(beneficiary.getName())
                                                                        .toLowerCase()
                                                                        .contains(search)
                                                        ||
                                                        safe(beneficiary.getVillage())
                                                                        .toLowerCase()
                                                                        .contains(search)
                                                        ||
                                                        safe(beneficiary.getStatus())
                                                                        .toLowerCase()
                                                                        .contains(search)
                                                        ||
                                                        safe(beneficiary.getCategory())
                                                                        .toLowerCase()
                                                                        .contains(search);

                                        if (!searchMatch) {
                                                return false;
                                        }

                                        if (currentFilter.equals("All")) {
                                                return true;
                                        }

                                        return safe(beneficiary.getCategory())
                                                        .equalsIgnoreCase(currentFilter)
                                                        ||
                                                        safe(beneficiary.getStatus())
                                                                        .equalsIgnoreCase(currentFilter);
                                });

                updateBeneficiaryCount();

                if (beneficiaryTable != null) {
                        beneficiaryTable.refresh();
                }
        }

        // =========================================================
        // FILTER DIALOG
        // =========================================================

        private void showFilterDialog() {

                ChoiceDialog<String> dialog = new ChoiceDialog<>(
                                currentFilter,
                                "All",
                                "Pregnant Woman",
                                "Child",
                                "High Risk",
                                "Normal",
                                "Immunized",
                                "Follow-up Due");

                dialog.setTitle(
                                "Filter Beneficiaries");

                dialog.setHeaderText(
                                "Select beneficiary category/status");

                dialog.setContentText(
                                "Filter:");

                dialog.showAndWait()
                                .ifPresent(
                                                selected -> {

                                                        currentFilter = selected;

                                                        applyFilters("");
                                                });
        }

        // =========================================================
        // ADD BENEFICIARY
        // =========================================================

        private void showAddBeneficiaryDialog() {

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Add Beneficiary");

                dialog.setHeaderText(
                                "Enter Beneficiary Information");

                ButtonType saveButton = new ButtonType(
                                "Add",
                                ButtonBar.ButtonData.OK_DONE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                ButtonType.CANCEL);

                GridPane form = new GridPane();

                form.setHgap(10);
                form.setVgap(10);

                form.setPadding(
                                new Insets(
                                                12,
                                                12,
                                                30,
                                                12));

                TextField nameField = new TextField();

                nameField.setPromptText(
                                "Beneficiary name");

                TextField categoryField = new TextField();

                categoryField.setPromptText(
                                "Pregnant Woman / Child");

                TextField ageField = new TextField();

                ageField.setPromptText(
                                "Age");

                TextField villageField = new TextField();

                villageField.setPromptText(
                                "Village / Area");

                ComboBox<String> statusBox = new ComboBox<>();

                statusBox.getItems().addAll(
                                "Normal",
                                "High Risk",
                                "Immunized",
                                "Follow-up Due");

                statusBox.setValue(
                                "Normal");

                DatePicker visitDatePicker = new DatePicker();

                visitDatePicker.setPromptText(
                                "Select visit date");

                form.add(new Label("Name:"), 0, 0);
                form.add(nameField, 1, 0);

                form.add(new Label("Category:"), 0, 1);
                form.add(categoryField, 1, 1);

                form.add(new Label("Age:"), 0, 2);
                form.add(ageField, 1, 2);

                form.add(new Label("Village:"), 0, 3);
                form.add(villageField, 1, 3);

                form.add(new Label("Status:"), 0, 4);
                form.add(statusBox, 1, 4);

                form.add(new Label("Visit Date:"), 0, 5);
                form.add(visitDatePicker, 1, 5);

                dialog.getDialogPane()
                                .setContent(form);

                dialog.showAndWait()
                                .ifPresent(
                                                result -> {

                                                        if (result != saveButton) {
                                                                return;
                                                        }

                                                        if (nameField.getText()
                                                                        .trim()
                                                                        .isEmpty()) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please enter beneficiary name.");

                                                                return;
                                                        }

                                                        if (categoryField.getText()
                                                                        .trim()
                                                                        .isEmpty()) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please enter category.");

                                                                return;
                                                        }

                                                        int newId = 1;

                                                        if (!masterData.isEmpty()) {

                                                                newId = masterData.stream()
                                                                                .mapToInt(
                                                                                                AshaBeneficiary::getId)
                                                                                .max()
                                                                                .orElse(0)
                                                                                + 1;
                                                        }

                                                        String lastVisit = visitDatePicker.getValue() != null
                                                                        ? visitDatePicker
                                                                                        .getValue()
                                                                                        .toString()
                                                                        : "";

                                                        boolean saved = controller.addAshaBeneficiary(
                                                                        newId,
                                                                        nameField.getText().trim(),
                                                                        categoryField.getText().trim(),
                                                                        ageField.getText().trim(),
                                                                        villageField.getText().trim(),
                                                                        statusBox.getValue(),
                                                                        lastVisit);

                                                        if (!saved) {

                                                                showAlert(
                                                                                Alert.AlertType.ERROR,
                                                                                "Unable to save beneficiary to Firebase.");

                                                                return;
                                                        }

                                                        showAlert(
                                                                        Alert.AlertType.INFORMATION,
                                                                        "Beneficiary added successfully.");

                                                        loadFirebaseData();
                                                        applyFilters("");
                                                        updateMetrics();

                                                        if (beneficiaryTable != null) {
                                                                beneficiaryTable.refresh();
                                                        }
                                                });
        }

        // =========================================================
        // VIEW DETAILS
        // =========================================================

        private void showViewDetailsDialog(
                        AshaBeneficiary b) {

                if (b == null) {
                        return;
                }

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Beneficiary Details");

                alert.setHeaderText(
                                safe(b.getName()));

                String details = "Beneficiary ID : " + b.getId() +
                                "\n\nName : " + safe(b.getName()) +
                                "\n\nCategory : " + safe(b.getCategory()) +
                                "\n\nAge : " + safe(b.getAge()) +
                                "\n\nVillage / Area : " + safe(b.getVillage()) +
                                "\n\nStatus : " + safe(b.getStatus()) +
                                "\n\nLast Visit : " + safe(b.getLastVisit());

                Label label = new Label(details);

                label.setWrapText(true);

                label.setStyle(
                                "-fx-font-size: 18px;");

                alert.getDialogPane()
                                .setContent(label);

                alert.showAndWait();
        }

        // =========================================================
        // RECORD VISIT
        // =========================================================

        private void showVisitDialog(
                        AshaBeneficiary beneficiary) {

                if (beneficiary == null) {
                        return;
                }

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Record Visit");

                dialog.setHeaderText(
                                "Record Visit - " +
                                                safe(beneficiary.getName()));

                ButtonType saveButton = new ButtonType(
                                "Save Visit",
                                ButtonBar.ButtonData.OK_DONE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                ButtonType.CANCEL);

                GridPane form = new GridPane();

                form.setHgap(10);
                form.setVgap(10);

                form.setPadding(
                                new Insets(15));

                DatePicker visitDatePicker = new DatePicker();

                visitDatePicker.setValue(
                                LocalDate.now());

                TextField purposeField = new TextField();

                purposeField.setPromptText(
                                "Enter visit purpose");

                TextField timeField = new TextField();

                timeField.setPromptText(
                                "Enter visit time");

                TextArea notesArea = new TextArea();

                notesArea.setPromptText(
                                "Enter visit notes...");

                notesArea.setPrefRowCount(4);
                notesArea.setWrapText(true);

                form.add(new Label("Visit Date:"), 0, 0);
                form.add(visitDatePicker, 1, 0);

                form.add(new Label("Purpose:"), 0, 1);
                form.add(purposeField, 1, 1);

                form.add(new Label("Time:"), 0, 2);
                form.add(timeField, 1, 2);

                form.add(new Label("Notes:"), 0, 3);
                form.add(notesArea, 1, 3);

                dialog.getDialogPane()
                                .setContent(form);

                dialog.showAndWait()
                                .ifPresent(
                                                result -> {

                                                        if (result != saveButton) {
                                                                return;
                                                        }

                                                        if (visitDatePicker.getValue() == null) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please select visit date.");

                                                                return;
                                                        }

                                                        if (purposeField.getText()
                                                                        .trim()
                                                                        .isEmpty()) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please enter visit purpose.");

                                                                return;
                                                        }

                                                        LocalDate selectedDate = visitDatePicker.getValue();

                                                        String date = selectedDate.format(
                                                                        DateTimeFormatter.ofPattern(
                                                                                        "dd MMM yyyy"));

                                                        String isoDate = selectedDate.toString();

                                                        String purpose = purposeField.getText().trim();

                                                        String time = timeField.getText().trim();

                                                        String notes = notesArea.getText().trim();

                                                        boolean saved = visitController.addVisit(
                                                                        beneficiary,
                                                                        date,
                                                                        purpose,
                                                                        time);

                                                        if (!saved) {

                                                                showAlert(
                                                                                Alert.AlertType.ERROR,
                                                                                "Failed to save visit.");

                                                                return;
                                                        }

                                                        boolean beneficiaryUpdated = controller.updateAshaBeneficiary(
                                                                        beneficiary.getId(),
                                                                        beneficiary.getName(),
                                                                        beneficiary.getCategory(),
                                                                        beneficiary.getAge(),
                                                                        beneficiary.getVillage(),
                                                                        beneficiary.getStatus(),
                                                                        isoDate);

                                                        if (!beneficiaryUpdated) {

                                                                System.out.println(
                                                                                "Visit saved but Last Visit could not be updated.");
                                                        }

                                                        loadFirebaseData();
                                                        applyFilters("");
                                                        updateMetrics();

                                                        if (beneficiaryTable != null) {
                                                                beneficiaryTable.refresh();
                                                        }

                                                        updateBeneficiaryCount();

                                                        String successMessage = "Visit recorded successfully.";

                                                        if (!notes.isEmpty()) {
                                                                successMessage += "\nNotes saved for reference.";
                                                        }

                                                        showAlert(
                                                                        Alert.AlertType.INFORMATION,
                                                                        successMessage);
                                                });
        }

        // =========================================================
        // OPTIONS MENU
        // =========================================================

        private void showOptionsMenu(
                        AshaBeneficiary beneficiary,
                        Button sourceButton) {

                if (beneficiary == null ||
                                sourceButton == null) {

                        return;
                }

                ContextMenu menu = new ContextMenu();

                MenuItem viewItem = new MenuItem("View Details");

                MenuItem editItem = new MenuItem("Edit");

                MenuItem deleteItem = new MenuItem("Delete");

                viewItem.setOnAction(
                                e -> showViewDetailsDialog(beneficiary));

                editItem.setOnAction(
                                e -> showEditBeneficiaryDialog(beneficiary));

                deleteItem.setOnAction(
                                e -> deleteBeneficiary(beneficiary));

                menu.getItems().addAll(
                                viewItem,
                                editItem,
                                new SeparatorMenuItem(),
                                deleteItem);

                menu.show(
                                sourceButton,
                                javafx.geometry.Side.BOTTOM,
                                0,
                                0);
        }

        // =========================================================
        // EDIT BENEFICIARY
        // =========================================================

        private void showEditBeneficiaryDialog(
                        AshaBeneficiary old) {

                if (old == null) {
                        return;
                }

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Edit Beneficiary");

                dialog.setHeaderText(
                                "Edit Beneficiary Information");

                ButtonType saveButton = new ButtonType(
                                "Save",
                                ButtonBar.ButtonData.OK_DONE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                ButtonType.CANCEL);

                GridPane form = new GridPane();

                form.setHgap(10);
                form.setVgap(10);

                form.setPadding(
                                new Insets(12));

                TextField nameField = new TextField(
                                safe(old.getName()));

                TextField categoryField = new TextField(
                                safe(old.getCategory()));

                TextField ageField = new TextField(
                                safe(old.getAge()));

                TextField villageField = new TextField(
                                safe(old.getVillage()));

                ComboBox<String> statusBox = new ComboBox<>();

                statusBox.getItems().addAll(
                                "Normal",
                                "High Risk",
                                "Immunized",
                                "Follow-up Due");

                String oldStatus = safe(old.getStatus());

                statusBox.setValue(
                                oldStatus.isEmpty()
                                                ? "Normal"
                                                : oldStatus);

                DatePicker visitDatePicker = new DatePicker();

                String oldLastVisit = safe(old.getLastVisit()).trim();

                if (!oldLastVisit.isEmpty() &&
                                !oldLastVisit.equalsIgnoreCase(
                                                "Not Visited")) {

                        try {

                                visitDatePicker.setValue(
                                                LocalDate.parse(oldLastVisit));

                        } catch (Exception e) {

                                try {

                                        visitDatePicker.setValue(
                                                        LocalDate.parse(
                                                                        oldLastVisit,
                                                                        DateTimeFormatter.ofPattern(
                                                                                        "dd MMM yyyy")));

                                } catch (Exception ignored) {
                                }
                        }
                }

                form.add(new Label("Name:"), 0, 0);
                form.add(nameField, 1, 0);

                form.add(new Label("Category:"), 0, 1);
                form.add(categoryField, 1, 1);

                form.add(new Label("Age:"), 0, 2);
                form.add(ageField, 1, 2);

                form.add(new Label("Village:"), 0, 3);
                form.add(villageField, 1, 3);

                form.add(new Label("Status:"), 0, 4);
                form.add(statusBox, 1, 4);

                form.add(new Label("Last Visit:"), 0, 5);
                form.add(visitDatePicker, 1, 5);

                dialog.getDialogPane()
                                .setContent(form);

                dialog.showAndWait()
                                .ifPresent(
                                                result -> {

                                                        if (result != saveButton) {
                                                                return;
                                                        }

                                                        if (nameField.getText()
                                                                        .trim()
                                                                        .isEmpty()) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please enter beneficiary name.");

                                                                return;
                                                        }

                                                        if (categoryField.getText()
                                                                        .trim()
                                                                        .isEmpty()) {

                                                                showAlert(
                                                                                Alert.AlertType.WARNING,
                                                                                "Please enter category.");

                                                                return;
                                                        }

                                                        String lastVisit = visitDatePicker.getValue() != null
                                                                        ? visitDatePicker
                                                                                        .getValue()
                                                                                        .toString()
                                                                        : "";

                                                        boolean updated = controller.updateAshaBeneficiary(
                                                                        old.getId(),
                                                                        nameField.getText().trim(),
                                                                        categoryField.getText().trim(),
                                                                        ageField.getText().trim(),
                                                                        villageField.getText().trim(),
                                                                        statusBox.getValue(),
                                                                        lastVisit);

                                                        if (!updated) {

                                                                showAlert(
                                                                                Alert.AlertType.ERROR,
                                                                                "Unable to update beneficiary.");

                                                                return;
                                                        }

                                                        loadFirebaseData();
                                                        applyFilters("");
                                                        updateMetrics();

                                                        if (beneficiaryTable != null) {
                                                                beneficiaryTable.refresh();
                                                        }

                                                        updateBeneficiaryCount();

                                                        showAlert(
                                                                        Alert.AlertType.INFORMATION,
                                                                        "Beneficiary updated successfully.");
                                                });
        }

        // =========================================================
        // DELETE
        // =========================================================

        private void deleteBeneficiary(
                        AshaBeneficiary beneficiary) {

                if (beneficiary == null) {
                        return;
                }

                Alert confirmation = new Alert(
                                Alert.AlertType.CONFIRMATION);

                confirmation.setTitle(
                                "Delete Beneficiary");

                confirmation.setHeaderText(
                                "Delete " +
                                                safe(beneficiary.getName()) +
                                                "?");

                confirmation.setContentText(
                                "This beneficiary will be removed from Firebase.");

                confirmation.showAndWait()
                                .ifPresent(
                                                result -> {

                                                        if (result == ButtonType.OK) {

                                                                boolean deleted = controller.deleteAshaBeneficiary(
                                                                                beneficiary.getId());

                                                                if (!deleted) {

                                                                        showAlert(
                                                                                        Alert.AlertType.ERROR,
                                                                                        "Unable to delete beneficiary.");

                                                                        return;
                                                                }

                                                                boolean visitsDeleted = visitController
                                                                                .deleteVisitsByBeneficiaryId(
                                                                                                beneficiary.getId());

                                                                if (!visitsDeleted) {

                                                                        showAlert(
                                                                                        Alert.AlertType.WARNING,
                                                                                        "Beneficiary deleted, but health visits could not be deleted.");

                                                                        return;
                                                                }

                                                                loadFirebaseData();
                                                                applyFilters("");
                                                                updateMetrics();

                                                                if (beneficiaryTable != null) {
                                                                        beneficiaryTable.refresh();
                                                                }

                                                                updateBeneficiaryCount();

                                                                showAlert(
                                                                                Alert.AlertType.INFORMATION,
                                                                                "Beneficiary deleted successfully.");
                                                        }
                                                });
        }

        // =========================================================
        // ALERT
        // =========================================================

        private void showAlert(
                        Alert.AlertType type,
                        String message) {

                Alert alert = new Alert(type);

                alert.setHeaderText(null);

                alert.setContentText(message);

                alert.showAndWait();
        }

        // =========================================================
        // METRICS GRID
        // =========================================================

        private GridPane createMetricsGrid() {

                GridPane grid = new GridPane();

                grid.setHgap(12);
                grid.setVgap(0);

                grid.setMaxWidth(
                                Double.MAX_VALUE);

                double cardWidth = (SCENE_WIDTH -
                                28 -
                                4 * 12) / 5.0;

                totalBeneficiariesValue = new Label(
                                String.valueOf(
                                                masterData.size()));

                int pregnantCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getCategory())
                                                                .toLowerCase()
                                                                .contains("pregnant"))
                                .count();

                pregnantWomenValue = new Label(
                                String.valueOf(
                                                pregnantCount));

                int childrenCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getCategory())
                                                                .toLowerCase()
                                                                .contains("child"))
                                .count();

                childrenValue = new Label(
                                String.valueOf(
                                                childrenCount));

                int immunizedCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getStatus())
                                                                .equalsIgnoreCase(
                                                                                "Immunized"))
                                .count();

                fullyImmunizedValue = new Label(
                                String.valueOf(
                                                immunizedCount));

                int pendingCount = (int) masterData.stream()
                                .filter(
                                                b -> safe(b.getStatus())
                                                                .equalsIgnoreCase(
                                                                                "Follow-up Due"))
                                .count();

                pendingVisitsValue = new Label(
                                String.valueOf(
                                                pendingCount));

                grid.add(
                                createMetricCardWithLabel(
                                                COLOR_ICON_PURPLE,
                                                totalBeneficiariesValue,
                                                LABEL_TOTAL_BENEFICIARIES,
                                                "Firebase records",
                                                cardWidth,
                                                "people"),
                                0,
                                0);

                grid.add(
                                createMetricCardWithLabel(
                                                COLOR_ICON_PINK,
                                                pregnantWomenValue,
                                                LABEL_PREGNANT_WOMEN,
                                                "Current records",
                                                cardWidth,
                                                "pregnant"),
                                1,
                                0);

                grid.add(
                                createMetricCardWithLabel(
                                                COLOR_ICON_BLUE,
                                                childrenValue,
                                                LABEL_CHILDREN,
                                                "Current records",
                                                cardWidth,
                                                "child"),
                                2,
                                0);

                grid.add(
                                createMetricCardWithLabel(
                                                COLOR_ICON_GREEN,
                                                fullyImmunizedValue,
                                                LABEL_FULLY_IMMUNIZED,
                                                "Current records",
                                                cardWidth,
                                                "immunized"),
                                3,
                                0);

                grid.add(
                                createMetricCardWithLabel(
                                                COLOR_ICON_ORANGE,
                                                pendingVisitsValue,
                                                LABEL_PENDING_VISITS,
                                                "Follow-up required",
                                                cardWidth,
                                                "calendar"),
                                4,
                                0);

                return grid;
        }

        // =========================================================
        // UPDATED METRIC CARD
        // =========================================================

        private VBox createMetricCardWithLabel(
                        String iconColorHex,
                        Label valueLabel,
                        String description,
                        String changeLabel,
                        double width,
                        String iconType) {

                VBox card = new VBox(4);

                card.setPadding(
                                new Insets(
                                                12,
                                                14,
                                                10,
                                                14));

                card.setPrefWidth(width);
                card.setMinWidth(width);
                card.setMaxWidth(width);

                // CARD HEIGHT INCREASED
                card.setMinHeight(118);
                card.setPrefHeight(118);
                card.setMaxHeight(118);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-border-color: #E2E8F0;" +
                                                "-fx-border-radius: 12px;" +
                                                "-fx-effect: dropshadow("
                                                + "gaussian, rgba(0,0,0,0.06), 10, 0.15, 0, 2);");

                StackPane icon = createIcon(
                                iconType,
                                iconColorHex,
                                COLOR_PRIMARY_PURPLE,
                                42);

                HBox topBox = new HBox(10);

                topBox.setAlignment(
                                Pos.CENTER_LEFT);

                valueLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                24));

                topBox.getChildren().addAll(
                                icon,
                                valueLabel);

                Label descLabel = new Label(
                                description);

                descLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                14));

                Label changeDesc = new Label(
                                changeLabel);

                changeDesc.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.NORMAL,
                                                10));

                changeDesc.setTextFill(
                                Color.web(
                                                COLOR_TEXT_MUTED));

                card.getChildren().addAll(
                                topBox,
                                descLabel,
                                changeDesc);

                return card;
        }

        // =========================================================
        // TABLE SECTION
        // =========================================================

        private VBox createTableSection() {

                VBox section = new VBox(7);

                section.setPadding(
                                new Insets(10));

                section.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-border-color: #E2E8F0;" +
                                                "-fx-border-radius: 12px;");

                section.setMaxWidth(
                                Double.MAX_VALUE);

                HBox titleBox = new HBox();

                titleBox.setAlignment(
                                Pos.CENTER_LEFT);

                Label titleLabel = new Label(
                                LABEL_ALL_BENEFICIARIES);

                titleLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                17));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                beneficiaryCountLabel = new Label(
                                "Total " +
                                                filteredData.size() +
                                                " Beneficiaries");

                beneficiaryCountLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.NORMAL,
                                                11));

                beneficiaryCountLabel.setTextFill(
                                Color.web(
                                                COLOR_TEXT_MUTED));

                Button exportButton = new Button(
                                "Export");

                exportButton.setPrefHeight(30);

                exportButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #C9B6FF;" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-text-fill: #6C47FF;" +
                                                "-fx-cursor: hand;");

                exportButton.setOnAction(
                                e -> exportBeneficiaries());

                HBox.setMargin(
                                beneficiaryCountLabel,
                                new Insets(
                                                0,
                                                10,
                                                0,
                                                0));

                titleBox.getChildren().addAll(
                                titleLabel,
                                spacer,
                                beneficiaryCountLabel,
                                exportButton);

                beneficiaryTable = new TableView<>();

                beneficiaryTable.setPrefHeight(300);
                beneficiaryTable.setMinHeight(300);
                beneficiaryTable.setMaxHeight(300);

                beneficiaryTable.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<AshaBeneficiary, Integer> idCol = new TableColumn<>("#");

                idCol.setPrefWidth(45);

                idCol.setCellValueFactory(
                                new PropertyValueFactory<>("id"));

                TableColumn<AshaBeneficiary, String> nameCol = new TableColumn<>("Name");

                nameCol.setPrefWidth(160);

                nameCol.setCellValueFactory(
                                new PropertyValueFactory<>("name"));

                TableColumn<AshaBeneficiary, String> catCol = new TableColumn<>("Category");

                catCol.setPrefWidth(180);

                catCol.setCellValueFactory(
                                new PropertyValueFactory<>("category"));

                TableColumn<AshaBeneficiary, String> ageCol = new TableColumn<>("Age");

                ageCol.setPrefWidth(90);

                ageCol.setCellValueFactory(
                                new PropertyValueFactory<>("age"));

                TableColumn<AshaBeneficiary, String> villCol = new TableColumn<>("Village/Area");

                villCol.setPrefWidth(160);

                villCol.setCellValueFactory(
                                new PropertyValueFactory<>("village"));

                TableColumn<AshaBeneficiary, String> statusCol = new TableColumn<>("Status");

                statusCol.setPrefWidth(150);

                statusCol.setCellValueFactory(
                                new PropertyValueFactory<>("status"));

                statusCol.setCellFactory(
                                param -> new TableCell<AshaBeneficiary, String>() {

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
                                                        setGraphic(null);

                                                        return;
                                                }

                                                Label label = new Label(item);

                                                label.setPadding(
                                                                new Insets(
                                                                                3,
                                                                                7,
                                                                                3,
                                                                                7));

                                                String baseStyle = "-fx-border-radius: 6px;" +
                                                                "-fx-background-radius: 6px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-font-size: 10px;";

                                                if (item.contains("High Risk")) {

                                                        label.setStyle(
                                                                        baseStyle +
                                                                                        "-fx-background-color: #F8D7E3;"
                                                                                        +
                                                                                        "-fx-text-fill: #B91C1C;");

                                                } else if (item.contains("Immunized")) {

                                                        label.setStyle(
                                                                        baseStyle +
                                                                                        "-fx-background-color: " +
                                                                                        COLOR_ICON_BLUE +
                                                                                        ";" +
                                                                                        "-fx-text-fill: #0369A1;");

                                                } else if (item.contains("Normal")) {

                                                        label.setStyle(
                                                                        baseStyle +
                                                                                        "-fx-background-color: " +
                                                                                        COLOR_ICON_GREEN +
                                                                                        ";" +
                                                                                        "-fx-text-fill: #15803D;");

                                                } else {

                                                        label.setStyle(
                                                                        baseStyle +
                                                                                        "-fx-background-color: " +
                                                                                        COLOR_ICON_ORANGE +
                                                                                        ";" +
                                                                                        "-fx-text-fill: #B45309;");
                                                }

                                                setGraphic(label);
                                                setText(null);
                                        }
                                });

                TableColumn<AshaBeneficiary, String> visitCol = new TableColumn<>("Last Visit");

                visitCol.setPrefWidth(130);

                visitCol.setCellValueFactory(
                                new PropertyValueFactory<>("lastVisit"));

                TableColumn<AshaBeneficiary, Void> actionCol = new TableColumn<>("Actions");

                actionCol.setPrefWidth(160);

                actionCol.setCellFactory(
                                param -> new TableCell<AshaBeneficiary, Void>() {

                                        private final Button viewBtn = new Button("View");

                                        private final Button visitBtn = new Button("Visit");

                                        private final Button optionsBtn = new Button("...");

                                        private final HBox container = new HBox(
                                                        4,
                                                        viewBtn,
                                                        visitBtn,
                                                        optionsBtn);

                                        {

                                                viewBtn.setStyle(
                                                                "-fx-background-color: transparent;" +
                                                                                "-fx-text-fill: #6C47FF;" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-cursor: hand;");

                                                visitBtn.setStyle(
                                                                "-fx-background-color: transparent;" +
                                                                                "-fx-text-fill: #EC4988;" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-cursor: hand;");

                                                optionsBtn.setStyle(
                                                                "-fx-background-color: transparent;" +
                                                                                "-fx-text-fill: #374151;" +
                                                                                "-fx-font-weight: bold;" +
                                                                                "-fx-cursor: hand;");

                                                container.setAlignment(
                                                                Pos.CENTER);

                                                viewBtn.setOnAction(
                                                                e -> {

                                                                        int index = getIndex();

                                                                        if (index >= 0 &&
                                                                                        index < getTableView()
                                                                                                        .getItems()
                                                                                                        .size()) {

                                                                                AshaBeneficiary b = getTableView()
                                                                                                .getItems()
                                                                                                .get(index);

                                                                                showViewDetailsDialog(b);
                                                                        }
                                                                });

                                                visitBtn.setOnAction(
                                                                e -> {

                                                                        int index = getIndex();

                                                                        if (index >= 0 &&
                                                                                        index < getTableView()
                                                                                                        .getItems()
                                                                                                        .size()) {

                                                                                AshaBeneficiary b = getTableView()
                                                                                                .getItems()
                                                                                                .get(index);

                                                                                showVisitDialog(b);
                                                                        }
                                                                });

                                                optionsBtn.setOnAction(
                                                                e -> {

                                                                        int index = getIndex();

                                                                        if (index >= 0 &&
                                                                                        index < getTableView()
                                                                                                        .getItems()
                                                                                                        .size()) {

                                                                                AshaBeneficiary b = getTableView()
                                                                                                .getItems()
                                                                                                .get(index);

                                                                                showOptionsMenu(
                                                                                                b,
                                                                                                optionsBtn);
                                                                        }
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
                                                        setGraphic(container);
                                                }
                                        }
                                });

                beneficiaryTable.getColumns().addAll(
                                idCol,
                                nameCol,
                                catCol,
                                ageCol,
                                villCol,
                                statusCol,
                                visitCol,
                                actionCol);

                beneficiaryTable.setItems(
                                filteredData);

                section.getChildren().addAll(
                                titleBox,
                                beneficiaryTable);

                return section;
        }

        // =========================================================
        // EXPORT CSV
        // =========================================================

        private void exportBeneficiaries() {

                if (filteredData == null) {
                        return;
                }

                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle(
                                "Export Beneficiaries");

                fileChooser.setInitialFileName(
                                "beneficiaries.csv");

                fileChooser
                                .getExtensionFilters()
                                .add(
                                                new FileChooser.ExtensionFilter(
                                                                "CSV Files",
                                                                "*.csv"));

                File file = fileChooser.showSaveDialog(null);

                if (file == null) {
                        return;
                }

                try (
                                FileWriter writer = new FileWriter(file)) {

                        writer.write(
                                        "ID,Name,Category,Age,Village/Area,Status,Last Visit\n");

                        for (AshaBeneficiary b : filteredData) {

                                if (b == null) {
                                        continue;
                                }

                                writer.write(
                                                b.getId() + "," +
                                                                escapeCSV(b.getName()) + "," +
                                                                escapeCSV(b.getCategory()) + "," +
                                                                escapeCSV(b.getAge()) + "," +
                                                                escapeCSV(b.getVillage()) + "," +
                                                                escapeCSV(b.getStatus()) + "," +
                                                                escapeCSV(b.getLastVisit()) +
                                                                "\n");
                        }

                        showAlert(
                                        Alert.AlertType.INFORMATION,
                                        "Beneficiaries exported successfully.");

                } catch (IOException ex) {

                        ex.printStackTrace();

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Unable to export beneficiaries.");
                }
        }

        // =========================================================
        // CSV ESCAPE
        // =========================================================

        private String escapeCSV(
                        String value) {

                if (value == null) {
                        return "";
                }

                return "\"" +
                                value.replace(
                                                "\"",
                                                "\"\"")
                                +
                                "\"";
        }

        // =========================================================
        // BOTTOM ACTIVITY GRID
        // =========================================================

        private GridPane createActivityGrid() {

                GridPane grid = new GridPane();

                grid.setTranslateY(8);
                grid.setHgap(10);

                grid.setMaxWidth(
                                Double.MAX_VALUE);

                double cardWidth = (SCENE_WIDTH -
                                28 -
                                5 * 10) / 6.0;

                grid.add(
                                createActivityCard(
                                                COLOR_ICON_PURPLE,
                                                "Total Visits This Month",
                                                "40",
                                                cardWidth,
                                                "visits"),
                                0,
                                0);

                grid.add(
                                createActivityCard(
                                                COLOR_ICON_PINK,
                                                "Home Visits",
                                                "32",
                                                cardWidth,
                                                "visits"),
                                1,
                                0);

                grid.add(
                                createActivityCard(
                                                COLOR_ICON_BLUE,
                                                "Health Camps",
                                                "5",
                                                cardWidth,
                                                "camp"),
                                2,
                                0);

                grid.add(
                                createActivityCard(
                                                COLOR_ICON_GREEN,
                                                "Referrals",
                                                "3",
                                                cardWidth,
                                                "referral"),
                                3,
                                0);

                grid.add(
                                createActivityCard(
                                                COLOR_ICON_ORANGE,
                                                "IFA Distributed",
                                                "78",
                                                cardWidth,
                                                "ifa"),
                                4,
                                0);

                grid.add(
                                createActivityCard(
                                                "#FEE2E2",
                                                "Nutritional Supplements",
                                                "56",
                                                cardWidth,
                                                "nutrition"),
                                5,
                                0);

                return grid;
        }

        // =========================================================
        // UPDATED ACTIVITY CARD
        // =========================================================

        private VBox createActivityCard(
                        String iconColorHex,
                        String description,
                        String value,
                        double width,
                        String iconType) {

                VBox card = new VBox();

                card.setPadding(
                                new Insets(
                                                10,
                                                12,
                                                10,
                                                12));

                card.setPrefWidth(width);
                card.setMinWidth(width);
                card.setMaxWidth(width);

                // CARD HEIGHT INCREASED
                card.setMinHeight(92);
                card.setPrefHeight(92);
                card.setMaxHeight(92);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: #E2E8F0;" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-effect: dropshadow("
                                                + "gaussian, rgba(0,0,0,0.05), 8, 0.15, 0, 2);");

                StackPane icon = createIcon(
                                iconType,
                                iconColorHex,
                                COLOR_PRIMARY_PURPLE,
                                38);

                VBox textPart = new VBox(2);

                Label descLabel = new Label(
                                description);

                descLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                12));

                descLabel.setWrapText(true);

                Label valueLabel = new Label(value);

                valueLabel.setFont(
                                Font.font(
                                                "System",
                                                FontWeight.BOLD,
                                                19));

                textPart.getChildren().addAll(
                                descLabel,
                                valueLabel);

                HBox container = new HBox(
                                9,
                                icon,
                                textPart);

                container.setAlignment(
                                Pos.CENTER_LEFT);

                card.getChildren().add(
                                container);

                return card;
        }

        // =========================================================
        // ICON CREATOR
        // =========================================================

        private StackPane createIcon(
                        String type,
                        String backgroundColor,
                        String iconColor,
                        double size) {

                StackPane container = new StackPane();

                container.setPrefSize(
                                size,
                                size);

                container.setMinSize(
                                size,
                                size);

                container.setMaxSize(
                                size,
                                size);

                Circle background = new Circle(
                                size / 2,
                                Color.web(backgroundColor));

                Region icon = null;

                switch (type) {

                        case "people":
                                icon = createPeopleIcon(iconColor, size);
                                break;

                        case "pregnant":
                                icon = createPregnantIcon(iconColor, size);
                                break;

                        case "child":
                                icon = createChildIcon(iconColor, size);
                                break;

                        case "immunized":
                                icon = createCheckIcon(iconColor, size);
                                break;

                        case "calendar":
                                icon = createCalendarIcon(iconColor, size);
                                break;

                        case "visits":
                                icon = createHomeIcon(iconColor, size);
                                break;

                        case "camp":
                                icon = createMedicalIcon(iconColor, size);
                                break;

                        case "referral":
                                icon = createReferralIcon(iconColor, size);
                                break;

                        case "ifa":
                                icon = createMedicineIcon(iconColor, size);
                                break;

                        case "nutrition":
                                icon = createHeartIcon(iconColor, size);
                                break;

                        default:
                                break;
                }

                if (icon != null) {

                        container.getChildren().addAll(
                                        background,
                                        icon);

                } else {

                        container.getChildren().add(
                                        background);
                }

                return container;
        }

        // =========================================================
        // PEOPLE ICON
        // =========================================================

        private Region createPeopleIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                double c = size * 0.18;

                Circle head1 = new Circle(
                                size * 0.30,
                                size * 0.34,
                                c,
                                Color.web(color));

                Circle head2 = new Circle(
                                size * 0.58,
                                size * 0.34,
                                c * 0.9,
                                Color.web(color));

                Arc body1 = new Arc(
                                size * 0.30,
                                size * 0.67,
                                size * 0.22,
                                size * 0.18,
                                200,
                                140);

                body1.setType(ArcType.OPEN);
                body1.setStroke(Color.web(color));
                body1.setStrokeWidth(2);
                body1.setFill(Color.TRANSPARENT);

                Arc body2 = new Arc(
                                size * 0.60,
                                size * 0.67,
                                size * 0.20,
                                size * 0.16,
                                200,
                                140);

                body2.setType(ArcType.OPEN);
                body2.setStroke(Color.web(color));
                body2.setStrokeWidth(2);
                body2.setFill(Color.TRANSPARENT);

                pane.getChildren().addAll(
                                head1,
                                head2,
                                body1,
                                body2);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // PREGNANT ICON
        // =========================================================

        private Region createPregnantIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Circle head = new Circle(
                                size * 0.50,
                                size * 0.24,
                                size * 0.12,
                                Color.web(color));

                Arc body = new Arc(
                                size * 0.47,
                                size * 0.58,
                                size * 0.22,
                                size * 0.30,
                                190,
                                160);

                body.setType(ArcType.OPEN);
                body.setFill(Color.TRANSPARENT);
                body.setStroke(Color.web(color));
                body.setStrokeWidth(2);

                Circle belly = new Circle(
                                size * 0.58,
                                size * 0.62,
                                size * 0.13,
                                Color.TRANSPARENT);

                belly.setStroke(Color.web(color));
                belly.setStrokeWidth(2);

                Line leg1 = new Line(
                                size * 0.43,
                                size * 0.80,
                                size * 0.37,
                                size * 0.92);

                Line leg2 = new Line(
                                size * 0.57,
                                size * 0.80,
                                size * 0.64,
                                size * 0.92);

                for (Line line : new Line[] { leg1, leg2 }) {

                        line.setStroke(Color.web(color));
                        line.setStrokeWidth(2);
                        line.setStrokeLineCap(
                                        StrokeLineCap.ROUND);
                }

                pane.getChildren().addAll(
                                head,
                                body,
                                belly,
                                leg1,
                                leg2);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // CHILD ICON
        // =========================================================

        private Region createChildIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Circle head = new Circle(
                                size * 0.50,
                                size * 0.30,
                                size * 0.14,
                                Color.web(color));

                Arc body = new Arc(
                                size * 0.50,
                                size * 0.66,
                                size * 0.22,
                                size * 0.20,
                                200,
                                140);

                body.setType(ArcType.OPEN);
                body.setFill(Color.TRANSPARENT);
                body.setStroke(Color.web(color));
                body.setStrokeWidth(2);

                pane.getChildren().addAll(
                                head,
                                body);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // CHECK ICON
        // =========================================================

        private Region createCheckIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Line line1 = new Line(
                                size * 0.25,
                                size * 0.52,
                                size * 0.44,
                                size * 0.70);

                Line line2 = new Line(
                                size * 0.44,
                                size * 0.70,
                                size * 0.77,
                                size * 0.32);

                for (Line line : new Line[] { line1, line2 }) {

                        line.setStroke(Color.web(color));
                        line.setStrokeWidth(2.5);
                        line.setStrokeLineCap(
                                        StrokeLineCap.ROUND);
                }

                pane.getChildren().addAll(
                                line1,
                                line2);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // CALENDAR ICON
        // =========================================================

        private Region createCalendarIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Rectangle calendar = new Rectangle(
                                size * 0.22,
                                size * 0.28,
                                size * 0.56,
                                size * 0.50);

                calendar.setFill(Color.TRANSPARENT);
                calendar.setStroke(Color.web(color));
                calendar.setStrokeWidth(1.8);
                calendar.setArcWidth(3);
                calendar.setArcHeight(3);

                Line top = new Line(
                                size * 0.22,
                                size * 0.42,
                                size * 0.78,
                                size * 0.42);

                Line ring1 = new Line(
                                size * 0.36,
                                size * 0.20,
                                size * 0.36,
                                size * 0.35);

                Line ring2 = new Line(
                                size * 0.64,
                                size * 0.20,
                                size * 0.64,
                                size * 0.35);

                for (Line line : new Line[] { top, ring1, ring2 }) {

                        line.setStroke(Color.web(color));
                        line.setStrokeWidth(1.8);
                        line.setStrokeLineCap(
                                        StrokeLineCap.ROUND);
                }

                pane.getChildren().addAll(
                                calendar,
                                top,
                                ring1,
                                ring2);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // HOME ICON
        // =========================================================

        private Region createHomeIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Polygon roof = new Polygon(
                                size * 0.20,
                                size * 0.48,
                                size * 0.50,
                                size * 0.20,
                                size * 0.80,
                                size * 0.48);

                roof.setFill(Color.TRANSPARENT);
                roof.setStroke(Color.web(color));
                roof.setStrokeWidth(1.8);

                Rectangle house = new Rectangle(
                                size * 0.30,
                                size * 0.45,
                                size * 0.40,
                                size * 0.35);

                house.setFill(Color.TRANSPARENT);
                house.setStroke(Color.web(color));
                house.setStrokeWidth(1.8);

                Rectangle door = new Rectangle(
                                size * 0.45,
                                size * 0.60,
                                size * 0.10,
                                size * 0.20);

                door.setFill(Color.web(color));

                pane.getChildren().addAll(
                                roof,
                                house,
                                door);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // MEDICAL ICON
        // =========================================================

        private Region createMedicalIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Rectangle vertical = new Rectangle(
                                size * 0.42,
                                size * 0.20,
                                size * 0.16,
                                size * 0.60);

                Rectangle horizontal = new Rectangle(
                                size * 0.20,
                                size * 0.42,
                                size * 0.60,
                                size * 0.16);

                vertical.setFill(Color.web(color));
                horizontal.setFill(Color.web(color));

                pane.getChildren().addAll(
                                vertical,
                                horizontal);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // REFERRAL ICON
        // =========================================================

        private Region createReferralIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Line line = new Line(
                                size * 0.25,
                                size * 0.72,
                                size * 0.72,
                                size * 0.28);

                Line arrow1 = new Line(
                                size * 0.52,
                                size * 0.28,
                                size * 0.72,
                                size * 0.28);

                Line arrow2 = new Line(
                                size * 0.72,
                                size * 0.28,
                                size * 0.72,
                                size * 0.48);

                for (Line l : new Line[] {
                                line,
                                arrow1,
                                arrow2
                }) {

                        l.setStroke(Color.web(color));
                        l.setStrokeWidth(2);
                        l.setStrokeLineCap(
                                        StrokeLineCap.ROUND);
                }

                pane.getChildren().addAll(
                                line,
                                arrow1,
                                arrow2);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // MEDICINE ICON
        // =========================================================

        private Region createMedicineIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Rectangle medicine = new Rectangle(
                                size * 0.25,
                                size * 0.38,
                                size * 0.50,
                                size * 0.24);

                medicine.setFill(Color.TRANSPARENT);
                medicine.setStroke(Color.web(color));
                medicine.setStrokeWidth(1.8);
                medicine.setArcWidth(6);
                medicine.setArcHeight(6);

                Line line = new Line(
                                size * 0.50,
                                size * 0.38,
                                size * 0.50,
                                size * 0.62);

                line.setStroke(Color.web(color));
                line.setStrokeWidth(1.8);

                pane.getChildren().addAll(
                                medicine,
                                line);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // HEART ICON
        // =========================================================

        private Region createHeartIcon(
                        String color,
                        double size) {

                Pane pane = new Pane();

                Circle left = new Circle(
                                size * 0.38,
                                size * 0.40,
                                size * 0.16,
                                Color.web(color));

                Circle right = new Circle(
                                size * 0.62,
                                size * 0.40,
                                size * 0.16,
                                Color.web(color));

                Polygon bottom = new Polygon(
                                size * 0.22,
                                size * 0.43,
                                size * 0.78,
                                size * 0.43,
                                size * 0.50,
                                size * 0.80);

                bottom.setFill(
                                Color.web(color));

                pane.getChildren().addAll(
                                left,
                                right,
                                bottom);

                pane.setPrefSize(size, size);

                return pane;
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safe(String value) {

                return value == null
                                ? ""
                                : value;
        }
}
