
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientReportController;
import com.sigma.model.DoctorModel.PatientReport;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

public class PatientReportsPage {

    // ============================================================
    // COLORS - SAME AS APPOINTMENT PAGE
    // ============================================================

    private static final String PINK = "#E84A87";
    private static final String DARK_PINK = "#D93678";

    private static final String LIGHT_PINK = "#FFEAF3";
    private static final String LIGHT_PURPLE = "#F3ECFF";
    private static final String PURPLE = "#9B4DCC";

    private static final String DARK_TEXT = "#24234F";
    private static final String SECONDARY_TEXT = "#77778D";

    private static final String BORDER = "#E7DCE8";

    private static final String PAGE_BACKGROUND = "#FFF9FC";

    private static final String BUTTON_GRADIENT =
            "linear-gradient(to right, #F54B87, #9B4DCC)";

    private static final String BUTTON_GRADIENT_HOVER =
            "linear-gradient(to right, #9B4DCC, #F54B87)";

    // ============================================================
    // CONTROLS
    // ============================================================

    private static TableView<PatientReport> table;

    private static TextField searchField;

    private static ComboBox<String> statusCombo;

    private static PatientReportController controller;

    // ============================================================
    // SHOW
    // ============================================================

    public static void show() {

        Stage stage =
                DoctorDashboard.dashboardStage;

        if (stage == null) {

            stage = new Stage();

            DoctorDashboard.dashboardStage =
                    stage;
        }

        // ========================================================
        // CONTROLLER
        // ========================================================

        controller =
                DoctorDashboard.getReportController();

        if (controller == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Patient report controller is not available."
            );

            return;
        }

        // ========================================================
        // REFRESH
        // ========================================================

        try {

            controller.refreshReports();

        } catch (Exception e) {

            System.out.println(
                    "[REPORT] Error refreshing reports."
            );

            e.printStackTrace();
        }

        // ========================================================
        // ROOT
        // ========================================================

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: "
                        + PAGE_BACKGROUND
                        + ";"
        );

        // ========================================================
        // HEADER
        // ========================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(
                        22,
                        30,
                        22,
                        30
                )
        );

        header.setSpacing(20);

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Patient Reports"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        title.setTextFill(
                Color.web(DARK_TEXT)
        );

        Label subtitle =
                new Label(
                        "View and manage reports uploaded for your patients"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // ========================================================
        // UPLOAD BUTTON
        // ========================================================

        Button uploadButton =
                new Button(
                        "＋ Upload Report"
                );

        uploadButton.setPrefHeight(42);

        uploadButton.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        stylePrimaryButton(
                uploadButton
        );

        uploadButton.setOnMouseEntered(
                e ->
                        stylePrimaryButtonHover(
                                uploadButton
                        )
        );

        uploadButton.setOnMouseExited(
                e ->
                        stylePrimaryButton(
                                uploadButton
                        )
        );

        uploadButton.setOnAction(
                e ->
                        UploadReportPage.show()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                uploadButton
        );

        // ========================================================
        // FILTER BOX
        // ========================================================

        HBox filterBox =
                new HBox(12);

        filterBox.setAlignment(
                Pos.CENTER_LEFT
        );

        filterBox.setPadding(
                new Insets(
                        0,
                        30,
                        18,
                        30
                )
        );

        // ========================================================
        // SEARCH
        // ========================================================

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search patient or report..."
        );

        searchField.setPrefWidth(300);
        searchField.setPrefHeight(40);

        searchField.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 9;"
                        + "-fx-border-color: "
                        + BORDER
                        + ";"
                        + "-fx-border-radius: 9;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-text-fill: "
                        + DARK_TEXT
                        + ";"
                        + "-fx-prompt-text-fill: "
                        + SECONDARY_TEXT
                        + ";"
                        + "-fx-padding: 0 12;"
        );

        // ========================================================
        // STATUS
        // ========================================================

        statusCombo =
                new ComboBox<>();

        statusCombo.getItems().addAll(
                "All Status",
                "Pending",
                "Reviewed"
        );

        statusCombo.setValue(
                "All Status"
        );

        statusCombo.setPrefWidth(150);
        statusCombo.setPrefHeight(40);

        styleComboBox(
                statusCombo
        );

        // ========================================================
        // CLEAR
        // ========================================================

        Button clearButton =
                new Button(
                        "Clear"
                );

        clearButton.setPrefHeight(40);
        clearButton.setMinWidth(95);

        clearButton.setPadding(
                new Insets(
                        9,
                        18,
                        9,
                        18
                )
        );

        styleClearButton(
                clearButton
        );

        clearButton.setOnMouseEntered(
                e ->
                        styleClearButtonHover(
                                clearButton
                        )
        );

        clearButton.setOnMouseExited(
                e ->
                        styleClearButton(
                                clearButton
                        )
        );

        // ========================================================
        // FILTER EVENTS
        // ========================================================

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilter()
        );

        statusCombo.setOnAction(
                e ->
                        applyFilter()
        );

        clearButton.setOnAction(
                e -> {

                    searchField.clear();

                    statusCombo.setValue(
                            "All Status"
                    );

                    if (controller != null) {

                        table.setItems(
                                controller.getReports()
                        );
                    }

                    applyFilter();
                }
        );

        filterBox.getChildren().addAll(
                searchField,
                statusCombo,
                clearButton
        );

        // ========================================================
        // TABLE
        // ========================================================

        table =
                new TableView<>();

        table.setItems(
                controller.getReports()
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

        // ========================================================
        // REPORT COLUMN
        // ========================================================

        TableColumn<PatientReport, String> reportColumn =
                new TableColumn<>(
                        "Report"
                );

        reportColumn.setCellValueFactory(
                data ->
                        data.getValue()
                                .reportNameProperty()
        );

        applyDarkTableCellStyle(
                reportColumn
        );

        // ========================================================
        // PATIENT COLUMN
        // ========================================================

        TableColumn<PatientReport, String> patientColumn =
                new TableColumn<>(
                        "Patient"
                );

        patientColumn.setCellValueFactory(
                data ->
                        data.getValue()
                                .patientNameProperty()
        );

        applyDarkTableCellStyle(
                patientColumn
        );

        // ========================================================
        // TYPE COLUMN
        // ========================================================

        TableColumn<PatientReport, String> typeColumn =
                new TableColumn<>(
                        "Type"
                );

        typeColumn.setCellValueFactory(
                data ->
                        data.getValue()
                                .reportTypeProperty()
        );

        applyDarkTableCellStyle(
                typeColumn
        );

        // ========================================================
        // DATE COLUMN
        // ========================================================

        TableColumn<PatientReport, String> dateColumn =
                new TableColumn<>(
                        "Date"
                );

        dateColumn.setCellValueFactory(
                data ->
                        data.getValue()
                                .dateProperty()
        );

        applyDarkTableCellStyle(
                dateColumn
        );

        // ========================================================
        // STATUS COLUMN
        // ========================================================

        TableColumn<PatientReport, String> statusColumn =
                new TableColumn<>(
                        "Status"
                );

        statusColumn.setCellValueFactory(
                data ->
                        data.getValue()
                                .statusProperty()
        );

        // SAME TEXT STYLE AS ALL OTHER TABLE COLUMNS
        applyDarkTableCellStyle(
                statusColumn
        );

        // ========================================================
        // ACTION COLUMN
        // ========================================================

        TableColumn<PatientReport, String> actionColumn =
                new TableColumn<>(
                        "Action"
                );

        actionColumn.setPrefWidth(210);
        actionColumn.setMinWidth(160);
        actionColumn.setResizable(true);

        actionColumn.setCellFactory(
                column ->
                        new TableCell<PatientReport, String>() {

                            private final Button viewButton =
                                    new Button("View");

                            private final Button deleteButton =
                                    new Button("Delete");

                            private final HBox box =
                                    new HBox(7);

                            {

                                box.setAlignment(
                                        Pos.CENTER
                                );

                                // =================================
                                // VIEW
                                // =================================

                                styleViewButton(
                                        viewButton
                                );

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

                                // =================================
                                // DELETE
                                // =================================

                                styleDeleteButton(
                                        deleteButton
                                );

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

                                // =================================
                                // VIEW ACTION
                                // =================================

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

                                            PatientReport report =
                                                    getTableView()
                                                            .getItems()
                                                            .get(index);

                                            openReport(
                                                    report
                                            );
                                        }
                                );

                                // =================================
                                // DELETE ACTION
                                // =================================

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

                                            PatientReport report =
                                                    getTableView()
                                                            .getItems()
                                                            .get(index);

                                            deleteReport(
                                                    report
                                            );
                                        }
                                );

                                box.getChildren().addAll(
                                        viewButton,
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

                                    setGraphic(
                                            box
                                    );

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

        // ========================================================
        // HEADER STYLE
        // ========================================================

        String tableHeaderStyle =
                "-fx-font-family: 'Arial';"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + DARK_TEXT
                        + ";";

        reportColumn.setStyle(
                tableHeaderStyle
        );

        patientColumn.setStyle(
                tableHeaderStyle
        );

        typeColumn.setStyle(
                tableHeaderStyle
        );

        dateColumn.setStyle(
                tableHeaderStyle
        );

        statusColumn.setStyle(
                tableHeaderStyle
        );

        actionColumn.setStyle(
                tableHeaderStyle
        );

        // ========================================================
        // ADD COLUMNS
        // ========================================================

        table.getColumns().addAll(
                reportColumn,
                patientColumn,
                typeColumn,
                dateColumn,
                statusColumn,
                actionColumn
        );

        // ========================================================
        // ROW HOVER
        // EXACT APPOINTMENT STYLE
        // ========================================================

        table.setRowFactory(
                tv -> {

                    TableRow<PatientReport> row =
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

        // ========================================================
        // EMPTY LABEL
        // ========================================================

        Label emptyLabel =
                new Label(
                        "No patient reports found."
                );

        emptyLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        emptyLabel.setTextFill(
                Color.web(
                        SECONDARY_TEXT
                )
        );

        table.setPlaceholder(
                emptyLabel
        );

        // ========================================================
        // CONTENT
        // ========================================================

        VBox content =
                new VBox(0);

        content.setPadding(
                new Insets(
                        0,
                        30,
                        30,
                        30
                )
        );

        VBox tableCard =
                new VBox(
                        table
                );

        tableCard.setPadding(
                new Insets(10)
        );

        tableCard.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 14;"
                        + "-fx-border-color: "
                        + BORDER
                        + ";"
                        + "-fx-border-radius: 14;"
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                tableCard,
                Priority.ALWAYS
        );

        content.getChildren().add(
                tableCard
        );

        // ========================================================
        // PAGE
        // ========================================================

        VBox page =
                new VBox();

        page.getChildren().addAll(
                header,
                filterBox,
                content
        );

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        root.setCenter(
                page
        );

        root.setLeft(
                DoctorDashboard.createSidebar(
                        "Reports"
                )
        );

        // ========================================================
        // SCENE
        // ========================================================

        Scene scene =
                new Scene(
                        root,
                        DoctorDashboard.dashboardStage.getWidth(),
                        DoctorDashboard.dashboardStage.getHeight()
                );

        DoctorDashboard.changeScene(
                scene
        );

        if (stage != null) {

            stage.setTitle(
                    "MaaCare AI - Patient Reports"
            );
        }
    }

    // ============================================================
    // COMMON TABLE CELL
    // EXACT APPOINTMENT STYLE
    // ============================================================

    private static void applyDarkTableCellStyle(
            TableColumn<PatientReport, String> column
    ) {

        column.setCellFactory(
                tableColumn ->
                        new TableCell<PatientReport, String>() {

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
                                        Color.web(
                                                DARK_TEXT
                                        )
                                );

                                setFont(
                                        Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
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
                        + "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-color: transparent;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void stylePrimaryButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + BUTTON_GRADIENT_HOVER
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-color: transparent;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // CLEAR BUTTON
    // ============================================================

    private static void styleClearButton(
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
                        + "-fx-border-radius: 20;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 9px 18px;"
                        + "-fx-cursor: hand;"
        );
    }

    private static void styleClearButtonHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: "
                        + BUTTON_GRADIENT_HOVER
                        + ";"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 9px 18px;"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // COMBO BOX
    // ============================================================

    private static void styleComboBox(
            ComboBox<String> comboBox
    ) {

        comboBox.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 9;"
                        + "-fx-border-color: "
                        + BORDER
                        + ";"
                        + "-fx-border-radius: 9;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 13px;"
                        + "-fx-text-fill: "
                        + DARK_TEXT
                        + ";"
                        + "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // FILTER
    // ============================================================

    private static void applyFilter() {

        if (controller == null ||
                table == null) {

            return;
        }

        String search =
                searchField == null
                        ? ""
                        : searchField.getText();

        String status =
                statusCombo == null
                        ? "All Status"
                        : statusCombo.getValue();

        ObservableList<PatientReport> filtered =
                controller.filterReports(
                        search,
                        status
                );

        table.setItems(
                filtered
        );
    }

    // ============================================================
    // OPEN REPORT
    // ============================================================

    private static void openReport(
            PatientReport report
    ) {

        if (report == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Report not found."
            );

            return;
        }

        String url =
                report.getReportUrl();

        if (url == null ||
                url.trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Report file URL is not available."
            );

            return;
        }

        url = url.trim();

        try {

            String lowerUrl =
                    url.toLowerCase();

            if (lowerUrl.contains(
                    "/image/upload/"
            )) {

                openInBrowser(url);

                return;
            }

            if (lowerUrl.contains(
                    "/raw/upload/"
            )
                    || lowerUrl.contains(
                            ".pdf"
                    )) {

                openInBrowser(url);

                return;
            }

            openInBrowser(url);

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to open the report.\n\n"
                            + e.getMessage()
            );
        }
    }

    // ============================================================
    // OPEN BROWSER
    // ============================================================

    private static void openInBrowser(
            String url
    ) {

        try {

            if (!Desktop.isDesktopSupported()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Desktop application is not supported."
                );

                return;
            }

            Desktop desktop =
                    Desktop.getDesktop();

            if (!desktop.isSupported(
                    Desktop.Action.BROWSE
            )) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Browser opening is not supported."
                );

                return;
            }

            desktop.browse(
                    new URI(url)
            );

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to open the report in browser.\n\n"
                            + e.getMessage()
            );
        }
    }

    // ============================================================
    // DELETE REPORT
    // ============================================================

    private static void deleteReport(
            PatientReport report
    ) {

        if (report == null) {
            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Report"
        );

        confirmation.setHeaderText(
                "Delete this patient report?"
        );

        confirmation.setContentText(
                report.getReportName()
        );

        confirmation.showAndWait()
                .ifPresent(
                        result -> {

                            if (result ==
                                    ButtonType.OK) {

                                try {

                                    controller.deleteReport(
                                            report
                                    );

                                    table.setItems(
                                            controller.getReports()
                                    );

                                    applyFilter();

                                } catch (Exception e) {

                                    e.printStackTrace();

                                    showAlert(
                                            Alert.AlertType.ERROR,
                                            "Unable to delete report."
                                    );
                                }
                            }
                        }
                );
    }

    // ============================================================
    // ALERT
    // ============================================================

    private static void showAlert(
            Alert.AlertType type,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "MaaCare AI"
        );

        alert.setHeaderText(null);

        alert.setContentText(
                message
        );

        DialogPane pane =
                alert.getDialogPane();

        pane.setStyle(
                "-fx-background-color: "
                        + PAGE_BACKGROUND
                        + ";"
        );

        pane.getButtonTypes()
                .forEach(
                        buttonType -> {

                            Button button =
                                    (Button) pane
                                            .lookupButton(
                                                    buttonType
                                            );

                            if (button == null) {
                                return;
                            }

                            if (buttonType ==
                                    ButtonType.OK) {

                                button.setStyle(
                                        "-fx-background-color: "
                                                + BUTTON_GRADIENT
                                                + ";"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 20;"
                                                + "-fx-border-radius: 20;"
                                                + "-fx-cursor: hand;"
                                );

                            } else {

                                button.setStyle(
                                        "-fx-background-color: "
                                                + LIGHT_PINK
                                                + ";"
                                                + "-fx-text-fill: "
                                                + PURPLE
                                                + ";"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 7;"
                                                + "-fx-border-color: "
                                                + BORDER
                                                + ";"
                                                + "-fx-border-radius: 7;"
                                                + "-fx-cursor: hand;"
                                );
                            }
                        }
                );

        alert.showAndWait();
    }
}