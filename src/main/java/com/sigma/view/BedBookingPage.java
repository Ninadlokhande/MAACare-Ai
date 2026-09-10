package com.sigma.view;

import com.google.cloud.firestore.ListenerRegistration;
import com.sigma.controller.HospitalController.BedBookingController;
import com.sigma.model.BedBooking;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BedBookingPage {

    // =========================================================
    // COLOUR PALETTE
    // =========================================================

    private static final String BG = "#F7EAF5";
    private static final String WHITE = "#FFFFFF";
    private static final String NAVY = "#17184F";

    private static final String PINK = "#E83E83";
    private static final String LIGHT_PINK = "#FFF0F7";

    private static final String PURPLE = "#8056C5";
    private static final String GREEN = "#67C98F";
    private static final String BLUE = "#5578D6";
    private static final String ORANGE = "#F2A33A";

    private static final String BORDER = "#E9E6EF";
    private static final String GREY = "#77758A";

    // =========================================================
    // CONTROLLER
    // =========================================================

    private final BedBookingController controller =
            new BedBookingController();

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<BedBooking> data =
            FXCollections.observableArrayList();

    private ListenerRegistration bookingListener;

    // =========================================================
    // STAT LABELS
    // =========================================================

    private Label totalBookingsLabel;
    private Label pendingBookingsLabel;
    private Label bookedBookingsLabel;
    private Label rejectedBookingsLabel;

    // =========================================================
    // ROOT
    // =========================================================

    private BorderPane root;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BedBookingPage() {

        createView();

        startRealtimeListener();
    }

    // =========================================================
    // CREATE VIEW
    // =========================================================

    private void createView() {

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent = new VBox(18);

        mainContent.setPadding(
                new Insets(28, 35, 30, 35)
        );

        // =====================================================
        // HEADER
        // =====================================================

        BorderPane header = new BorderPane();

        header.setPadding(
                new Insets(0, 0, 8, 0)
        );

        VBox titleBox = new VBox(5);

        Label title = new Label("Bed Booking");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(NAVY)
        );

        Label subtitle = new Label(
                "View and manage hospital bed bookings"
        );

        subtitle.setFont(
                Font.font("Arial", 15)
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        header.setLeft(titleBox);

        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox cards = new HBox(18);

        cards.setAlignment(Pos.CENTER);

        VBox totalBookings =
                createStatCard(
                        "0",
                        "🛌",
                        "Total Bookings",
                        "All Bed Requests",
                        BLUE
                );

        VBox pendingBookings =
                createStatCard(
                        "0",
                        "⏳",
                        "Pending",
                        "Awaiting Confirmation",
                        ORANGE
                );

        VBox bookedBookings =
                createStatCard(
                        "0",
                        "✅",
                        "Booked",
                        "Confirmed Bookings",
                        GREEN
                );

        VBox rejectedBookings =
                createStatCard(
                        "0",
                        "✕",
                        "Rejected",
                        "Rejected Requests",
                        PINK
                );

        cards.getChildren().addAll(
                totalBookings,
                pendingBookings,
                bookedBookings,
                rejectedBookings
        );

        // =====================================================
        // ADD BUTTON
        // =====================================================

        Button addBedButton =
                new Button("+ Add Bed Booking");

        addBedButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        addBedButton.setCursor(Cursor.HAND);

        addBedButton.setStyle(
                "-fx-background-color: " + PINK + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;" +
                "-fx-padding: 10 16;"
        );

        HBox addButtonBox =
                new HBox();

        addButtonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        addButtonBox.getChildren().add(
                addBedButton
        );

        // =====================================================
        // SEARCH + FILTER
        // =====================================================

        HBox filterBox =
                new HBox(18);

        filterBox.setPadding(
                new Insets(18)
        );

        filterBox.setAlignment(
                Pos.CENTER_LEFT
        );

        filterBox.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search patient or booking ID..."
        );

        searchField.setPrefWidth(430);
        searchField.setPrefHeight(45);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 15;" +
                "-fx-font-size: 14;"
        );

        // =====================================================
        // DEPARTMENT
        // =====================================================

        ComboBox<String> departmentBox =
                new ComboBox<>();

        departmentBox.getItems().addAll(
                "All Departments",
                "General Ward",
                "ICU",
                "Emergency",
                "Pediatric",
                "Maternity"
        );

        departmentBox.setValue(
                "All Departments"
        );

        departmentBox.setPrefWidth(190);
        departmentBox.setPrefHeight(45);

        // =====================================================
        // BED TYPE
        // =====================================================

        ComboBox<String> bedTypeBox =
                new ComboBox<>();

        bedTypeBox.getItems().addAll(
                "All Bed Types",
                "General",
                "ICU",
                "Private",
                "Semi-Private"
        );

        bedTypeBox.setValue(
                "All Bed Types"
        );

        bedTypeBox.setPrefWidth(180);
        bedTypeBox.setPrefHeight(45);

        // =====================================================
        // STATUS
        // =====================================================

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "All Status",
                "Pending",
                "Booked",
                "Rejected"
        );

        statusBox.setValue(
                "All Status"
        );

        statusBox.setPrefWidth(160);
        statusBox.setPrefHeight(45);

        filterBox.getChildren().addAll(
                searchField,
                departmentBox,
                bedTypeBox,
                statusBox
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<BedBooking> table =
                new TableView<>();

        table.setPrefHeight(450);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        // =====================================================
        // COLUMNS
        // =====================================================

        TableColumn<BedBooking, String> numberColumn =
                new TableColumn<>("No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        TableColumn<BedBooking, String> bookingIdColumn =
                new TableColumn<>("Booking ID");

        bookingIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("bookingID")
        );

        TableColumn<BedBooking, String> patientColumn =
                new TableColumn<>("Patient Name");

        patientColumn.setCellValueFactory(
                new PropertyValueFactory<>("patientName")
        );

        TableColumn<BedBooking, String> hospitalColumn =
                new TableColumn<>("Hospital");

        hospitalColumn.setCellValueFactory(
                new PropertyValueFactory<>("hospitalName")
        );

        TableColumn<BedBooking, String> departmentColumn =
                new TableColumn<>("Department");

        departmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        TableColumn<BedBooking, String> bedNoColumn =
                new TableColumn<>("Bed No.");

        bedNoColumn.setCellValueFactory(
                new PropertyValueFactory<>("bedNo")
        );

        TableColumn<BedBooking, String> bedTypeColumn =
                new TableColumn<>("Bed Type");

        bedTypeColumn.setCellValueFactory(
                new PropertyValueFactory<>("bedType")
        );

        TableColumn<BedBooking, String> checkInColumn =
                new TableColumn<>("Check-in Date");

        checkInColumn.setCellValueFactory(
                new PropertyValueFactory<>("checkinDate")
        );

        TableColumn<BedBooking, String> checkOutColumn =
                new TableColumn<>("Expected Check-out");

        checkOutColumn.setCellValueFactory(
                new PropertyValueFactory<>("expectedCheckout")
        );

        TableColumn<BedBooking, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        // =====================================================
        // ACTION COLUMN
        // =====================================================

        TableColumn<BedBooking, Void> actionColumn =
                new TableColumn<>("Action");

        actionColumn.setCellFactory(column ->
                new TableCell<BedBooking, Void>() {

                    private final Button view =
                            new Button("View");

                    private final Button accept =
                            new Button("Accept");

                    private final Button reject =
                            new Button("Reject");

                    private final Button edit =
                            new Button("Edit");

                    private final Button delete =
                            new Button("Delete");

                    private final HBox box =
                            new HBox(
                                    5,
                                    view,
                                    accept,
                                    reject,
                                    edit,
                                    delete
                            );

                    {
                        box.setAlignment(
                                Pos.CENTER
                        );

                        view.setCursor(
                                Cursor.HAND
                        );

                        accept.setCursor(
                                Cursor.HAND
                        );

                        reject.setCursor(
                                Cursor.HAND
                        );

                        edit.setCursor(
                                Cursor.HAND
                        );

                        delete.setCursor(
                                Cursor.HAND
                        );

                        view.setStyle(
                                "-fx-background-color: #FFF0F7;" +
                                "-fx-text-fill: " + PINK + ";" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                        );

                        accept.setStyle(
                                "-fx-background-color: #E8F8F0;" +
                                "-fx-text-fill: #35A56B;" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                        );

                        reject.setStyle(
                                "-fx-background-color: #FFF0F0;" +
                                "-fx-text-fill: #D94A5A;" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                        );

                        edit.setStyle(
                                "-fx-background-color: #EEF5FF;" +
                                "-fx-text-fill: #3274C6;" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                        );

                        delete.setStyle(
                                "-fx-background-color: #FFF0F0;" +
                                "-fx-text-fill: #D94A5A;" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                        );

                        // =============================================
                        // VIEW
                        // =============================================

                        view.setOnAction(e -> {

                            BedBooking record =
                                    getSelectedRecord();

                            if (record == null) {
                                return;
                            }

                            showBookingDetails(
                                    record
                            );
                        });

                        // =============================================
                        // ACCEPT
                        // =============================================

                        accept.setOnAction(e -> {

                            BedBooking record =
                                    getSelectedRecord();

                            if (record == null) {
                                return;
                            }

                            acceptBooking(
                                    record
                            );
                        });

                        // =============================================
                        // REJECT
                        // =============================================

                        reject.setOnAction(e -> {

                            BedBooking record =
                                    getSelectedRecord();

                            if (record == null) {
                                return;
                            }

                            rejectBooking(
                                    record
                            );
                        });

                        // =============================================
                        // EDIT
                        // =============================================

                        edit.setOnAction(e -> {

                            BedBooking record =
                                    getSelectedRecord();

                            if (record == null) {
                                return;
                            }

                            editBooking(
                                    record
                            );
                        });

                        // =============================================
                        // DELETE
                        // =============================================

                        delete.setOnAction(e -> {

                            BedBooking record =
                                    getSelectedRecord();

                            if (record == null) {
                                return;
                            }

                            deleteBooking(
                                    record
                            );
                        });
                    }

                    private BedBooking getSelectedRecord() {

                        if (getIndex() < 0) {
                            return null;
                        }

                        if (getIndex() >=
                                getTableView()
                                        .getItems()
                                        .size()) {

                            return null;
                        }

                        return getTableView()
                                .getItems()
                                .get(getIndex());
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty
                    ) {

                        super.updateItem(
                                item,
                                empty
                        );

                        if (empty) {

                            setGraphic(null);

                            return;
                        }

                        BedBooking record =
                                getSelectedRecord();

                        if (record == null) {

                            setGraphic(null);

                            return;
                        }

                        String status =
                                record.getStatus();

                        boolean pending =
                                status != null
                                        &&
                                        status.equalsIgnoreCase(
                                                "Pending"
                                        );

                        accept.setDisable(
                                !pending
                        );

                        reject.setDisable(
                                !pending
                        );

                        setGraphic(box);
                    }
                }
        );

        // =====================================================
        // ADD COLUMNS
        // =====================================================

        table.getColumns().addAll(
                numberColumn,
                bookingIdColumn,
                patientColumn,
                hospitalColumn,
                departmentColumn,
                bedNoColumn,
                bedTypeColumn,
                checkInColumn,
                checkOutColumn,
                statusColumn,
                actionColumn
        );

        table.setItems(
                data
        );

        // =====================================================
        // ADD BOOKING
        // =====================================================

        addBedButton.setOnAction(
                e -> showAddBedBookingDialog()
        );

        // =====================================================
        // STATUS CELL
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<BedBooking, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (empty ||
                                        item == null) {

                                    setText(null);
                                    setStyle("");

                                    return;
                                }

                                setText(item);

                                setAlignment(
                                        Pos.CENTER
                                );

                                if (item.equalsIgnoreCase(
                                        "Booked")) {

                                    setStyle(
                                            "-fx-background-color: #E8F8F0;" +
                                            "-fx-text-fill: #35A56B;" +
                                            "-fx-font-weight: bold;"
                                    );

                                } else if (
                                        item.equalsIgnoreCase(
                                                "Pending"
                                        )
                                ) {

                                    setStyle(
                                            "-fx-background-color: #FFF4E5;" +
                                            "-fx-text-fill: " +
                                            ORANGE + ";" +
                                            "-fx-font-weight: bold;"
                                    );

                                } else if (
                                        item.equalsIgnoreCase(
                                                "Rejected"
                                        )
                                ) {

                                    setStyle(
                                            "-fx-background-color: #FFF0F0;" +
                                            "-fx-text-fill: #D94A5A;" +
                                            "-fx-font-weight: bold;"
                                    );

                                } else {

                                    setStyle(
                                            "-fx-background-color: #EEF3FF;" +
                                            "-fx-text-fill: " +
                                            BLUE + ";" +
                                            "-fx-font-weight: bold;"
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // ROW HOVER
        // =====================================================

        table.setRowFactory(tv -> {

            TableRow<BedBooking> row =
                    new TableRow<>();

            row.setOnMouseEntered(e -> {

                if (!row.isEmpty()) {

                    row.setStyle(
                            "-fx-background-color: "
                                    + LIGHT_PINK + ";"
                    );
                }
            });

            row.setOnMouseExited(e -> {

                row.setStyle("");
            });

            return row;
        });

        // =====================================================
        // SEARCH
        // =====================================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilter(
                            table,
                            newValue,
                            departmentBox.getValue(),
                            bedTypeBox.getValue(),
                            statusBox.getValue()
                    );
                }
        );

        // =====================================================
        // DEPARTMENT
        // =====================================================

        departmentBox.setOnAction(e -> {

            applyFilter(
                    table,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // BED TYPE
        // =====================================================

        bedTypeBox.setOnAction(e -> {

            applyFilter(
                    table,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // STATUS
        // =====================================================

        statusBox.setOnAction(e -> {

            applyFilter(
                    table,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // ADD TO ROOT
        // =====================================================

        mainContent.getChildren().addAll(
                header,
                cards,
                addButtonBox,
                filterBox,
                table
        );

        root.setCenter(
                mainContent
        );
    }

    // =========================================================
    // REALTIME LISTENER
    // =========================================================

    private void startRealtimeListener() {

        bookingListener =
                controller.listenToBedBookings(
                        firebaseBookings -> {

                            Platform.runLater(() -> {

                                data.clear();

                                if (firebaseBookings != null) {

                                    data.addAll(
                                            firebaseBookings
                                    );
                                }

                                updateStatCards();
                            });
                        }
                );
    }

    // =========================================================
    // VIEW BOOKING
    // =========================================================

    private void showBookingDetails(
            BedBooking record
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Bed Booking Details"
        );

        alert.setHeaderText(
                "Bed Booking Information"
        );

        alert.setContentText(

                "Booking ID: "
                        + safe(
                                record.getBookingID()
                        )

                        + "\n\nPatient: "
                        + safe(
                                record.getPatientName()
                        )

                        + "\n\nHospital: "
                        + safe(
                                record.getHospitalName()
                        )

                        + "\n\nDepartment: "
                        + safe(
                                record.getDepartment()
                        )

                        + "\n\nBed No.: "
                        + safe(
                                record.getBedNo()
                        )

                        + "\n\nBed Type: "
                        + safe(
                                record.getBedType()
                        )

                        + "\n\nCheck-in Date: "
                        + safe(
                                record.getCheckinDate()
                        )

                        + "\n\nExpected Check-out: "
                        + safe(
                                record.getExpectedCheckout()
                        )

                        + "\n\nStatus: "
                        + safe(
                                record.getStatus()
                        )
        );

        alert.showAndWait();
    }

    // =========================================================
    // ACCEPT BOOKING
    // =========================================================

    private void acceptBooking(
            BedBooking booking
    ) {

        if (booking == null) {
            return;
        }

        if (booking.getStatus() != null
                &&
                booking.getStatus().equalsIgnoreCase(
                        "Booked"
                )) {

            showInformation(
                    "Already Booked",
                    "This booking is already confirmed."
            );

            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Confirm Bed Booking"
        );

        confirmation.setHeaderText(
                "Accept this bed request?"
        );

        String currentBed =
                booking.getBedNo();

        if (currentBed == null ||
                currentBed.trim().isEmpty() ||
                currentBed.equalsIgnoreCase(
                        "Not Assigned"
                )) {

            currentBed =
                    "Assigned";
        }

        confirmation.setContentText(

                "Patient: "
                        + safe(
                                booking.getPatientName()
                        )

                        + "\nHospital: "
                        + safe(
                                booking.getHospitalName()
                        )

                        + "\nDepartment: "
                        + safe(
                                booking.getDepartment()
                        )

                        + "\nBed Type: "
                        + safe(
                                booking.getBedType()
                        )

                        + "\n\nConfirm booking?"
        );

        Optional<ButtonType> response =
                confirmation.showAndWait();

        if (!response.isPresent()
                ||
                response.get() != ButtonType.OK) {

            return;
        }

        // =====================================================
        // UPDATE STATUS
        // =====================================================

        booking.setStatus(
                "Booked"
        );

        if (booking.getBedNo() == null ||
                booking.getBedNo().trim().isEmpty() ||
                booking.getBedNo().equalsIgnoreCase(
                        "Not Assigned"
                )) {

            booking.setBedNo(
                    currentBed
            );
        }

        controller.updateBedBooking(

                booking.getNumber(),

                booking.getBookingID(),

                booking.getPatientName(),

                booking.getHospitalName(),

                booking.getDepartment(),

                booking.getBedNo(),

                booking.getBedType(),

                booking.getCheckinDate(),

                booking.getExpectedCheckout(),

                booking.getStatus()
        );

        showInformation(
                "Booking Accepted",
                "The bed booking request has been confirmed."
        );
    }

    // =========================================================
    // REJECT BOOKING
    // =========================================================

    private void rejectBooking(
            BedBooking booking
    ) {

        if (booking == null) {
            return;
        }

        if (booking.getStatus() != null
                &&
                booking.getStatus().equalsIgnoreCase(
                        "Booked"
                )) {

            showWarning(
                    "Cannot Reject",
                    "A booked request cannot be rejected."
            );

            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Reject Bed Booking"
        );

        confirmation.setHeaderText(
                "Reject this bed request?"
        );

        confirmation.setContentText(
                "Patient: "
                        + safe(
                                booking.getPatientName()
                        )
        );

        Optional<ButtonType> response =
                confirmation.showAndWait();

        if (!response.isPresent()
                ||
                response.get() != ButtonType.OK) {

            return;
        }

        booking.setStatus(
                "Rejected"
        );

        booking.setBedNo(
                "Not Assigned"
        );

        controller.updateBedBooking(

                booking.getNumber(),

                booking.getBookingID(),

                booking.getPatientName(),

                booking.getHospitalName(),

                booking.getDepartment(),

                booking.getBedNo(),

                booking.getBedType(),

                booking.getCheckinDate(),

                booking.getExpectedCheckout(),

                booking.getStatus()
        );

        showInformation(
                "Booking Rejected",
                "The bed booking request has been rejected."
        );
    }

    // =========================================================
    // EDIT BOOKING
    // =========================================================

    private void editBooking(
            BedBooking record
    ) {

        if (record == null) {
            return;
        }

        String currentStatus =
                record.getStatus();

        if (currentStatus == null ||
                currentStatus.trim().isEmpty()) {

            currentStatus =
                    "Pending";
        }

        ChoiceDialog<String> dialog =
                new ChoiceDialog<>(
                        currentStatus,
                        "Pending",
                        "Booked",
                        "Rejected"
                );

        dialog.setTitle(
                "Edit Bed Booking"
        );

        dialog.setHeaderText(
                "Change Booking Status"
        );

        dialog.setContentText(
                "Select Status:"
        );

        dialog.showAndWait()
                .ifPresent(newStatus -> {

                    // =============================================
                    // BOOKED
                    // =============================================

                    if (newStatus.equalsIgnoreCase(
                            "Booked"
                    )) {

                        acceptBooking(
                                record
                        );

                        return;
                    }

                    // =============================================
                    // REJECTED
                    // =============================================

                    if (newStatus.equalsIgnoreCase(
                            "Rejected"
                    )) {

                        record.setStatus(
                                "Rejected"
                        );

                        record.setBedNo(
                                "Not Assigned"
                        );

                    } else {

                        // =========================================
                        // PENDING
                        // =========================================

                        record.setStatus(
                                "Pending"
                        );

                        if (record.getBedNo() == null ||
                                record.getBedNo()
                                        .trim()
                                        .isEmpty() ||
                                record.getBedNo()
                                        .equalsIgnoreCase(
                                                "Assigned"
                                        )) {

                            record.setBedNo(
                                    "Not Assigned"
                            );
                        }
                    }

                    controller.updateBedBooking(

                            record.getNumber(),

                            record.getBookingID(),

                            record.getPatientName(),

                            record.getHospitalName(),

                            record.getDepartment(),

                            record.getBedNo(),

                            record.getBedType(),

                            record.getCheckinDate(),

                            record.getExpectedCheckout(),

                            record.getStatus()
                    );
                });
    }

    // =========================================================
    // DELETE BOOKING
    // =========================================================

    private void deleteBooking(
            BedBooking record
    ) {

        if (record == null) {
            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Bed Booking"
        );

        confirmation.setHeaderText(
                "Delete Bed Booking?"
        );

        confirmation.setContentText(

                "Are you sure you want to delete the booking of "
                        + safe(
                                record.getPatientName()
                        )
                        + "?"
        );

        Optional<ButtonType> response =
                confirmation.showAndWait();

        if (!response.isPresent()
                ||
                response.get() != ButtonType.OK) {

            return;
        }

        controller.deleteBedBooking(
                record.getBookingID()
        );

        data.remove(
                record
        );
    }

    // =========================================================
    // ADD BED BOOKING DIALOG
    // =========================================================

    private void showAddBedBookingDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Bed Booking"
        );

        dialog.setHeaderText(
                "Enter Bed Booking Details"
        );

        GridPane form =
                new GridPane();

        form.setHgap(12);
        form.setVgap(12);
        form.setPadding(
                new Insets(20)
        );

        // =====================================================
        // BOOKING ID
        // =====================================================

        TextField bookingIdField =
                new TextField();

        bookingIdField.setPromptText(
                "Booking ID"
        );

        // =====================================================
        // PATIENT
        // =====================================================

        TextField patientField =
                new TextField();

        patientField.setPromptText(
                "Patient Name"
        );

        // =====================================================
        // HOSPITAL
        // =====================================================

        ComboBox<String> hospitalField =
                new ComboBox<>();

        hospitalField.getItems().addAll(
                "CarePlus Women Hospital",
                "MotherCare Multispeciality",
                "LifeSpring Women & Child"
        );

        hospitalField.setPromptText(
                "Select Hospital"
        );

        hospitalField.setPrefWidth(
                250
        );

        // =====================================================
        // DEPARTMENT
        // =====================================================

        ComboBox<String> departmentField =
                new ComboBox<>();

        departmentField.getItems().addAll(
                "General Ward",
                "ICU",
                "Emergency",
                "Pediatric",
                "Maternity"
        );

        departmentField.setPromptText(
                "Select Department"
        );

        departmentField.setPrefWidth(
                250
        );

        // =====================================================
        // BED TYPE
        // =====================================================

        ComboBox<String> bedTypeField =
                new ComboBox<>();

        bedTypeField.getItems().addAll(
                "General",
                "ICU",
                "Private",
                "Semi-Private"
        );

        bedTypeField.setPromptText(
                "Select Bed Type"
        );

        bedTypeField.setPrefWidth(
                250
        );

        // =====================================================
        // BED NUMBER
        // =====================================================

        TextField bedNoField =
                new TextField();

        bedNoField.setPromptText(
                "Optional - e.g. B-101"
        );

        // =====================================================
        // CHECK-IN
        // =====================================================

        TextField checkInField =
                new TextField();

        checkInField.setPromptText(
                "e.g. 25 Aug 2026"
        );

        // =====================================================
        // CHECK-OUT
        // =====================================================

        TextField checkOutField =
                new TextField();

        checkOutField.setPromptText(
                "e.g. 30 Aug 2026"
        );

        // =====================================================
        // FORM
        // =====================================================

        form.add(
                new Label("Booking ID:"),
                0,
                0
        );

        form.add(
                bookingIdField,
                1,
                0
        );

        form.add(
                new Label("Patient Name:"),
                0,
                1
        );

        form.add(
                patientField,
                1,
                1
        );

        form.add(
                new Label("Hospital:"),
                0,
                2
        );

        form.add(
                hospitalField,
                1,
                2
        );

        form.add(
                new Label("Department:"),
                0,
                3
        );

        form.add(
                departmentField,
                1,
                3
        );

        form.add(
                new Label("Bed Type:"),
                0,
                4
        );

        form.add(
                bedTypeField,
                1,
                4
        );

        form.add(
                new Label("Bed No.:"),
                0,
                5
        );

        form.add(
                bedNoField,
                1,
                5
        );

        form.add(
                new Label("Check-in Date:"),
                0,
                6
        );

        form.add(
                checkInField,
                1,
                6
        );

        form.add(
                new Label("Expected Check-out:"),
                0,
                7
        );

        form.add(
                checkOutField,
                1,
                7
        );

        dialog.getDialogPane()
                .setContent(
                        form
                );

        ButtonType addButton =
                new ButtonType(
                        "Add Booking",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        addButton,
                        ButtonType.CANCEL
                );

        // =====================================================
        // RESULT
        // =====================================================

        dialog.setResultConverter(button -> {

            if (button != addButton) {
                return null;
            }

            // =================================================
            // VALIDATION
            // =================================================

            if (
                    bookingIdField.getText()
                            .trim()
                            .isEmpty()

                            ||

                    patientField.getText()
                            .trim()
                            .isEmpty()

                            ||

                    hospitalField.getValue()
                            == null

                            ||

                    departmentField.getValue()
                            == null

                            ||

                    bedTypeField.getValue()
                            == null

                            ||

                    checkInField.getText()
                            .trim()
                            .isEmpty()

                            ||

                    checkOutField.getText()
                            .trim()
                            .isEmpty()
            ) {

                showWarning(
                        "Missing Information",
                        "Please fill all required fields."
                );

                return null;
            }

            // =================================================
            // DUPLICATE BOOKING ID
            // =================================================

            BedBooking existing =
                    controller.getBedBooking(
                            bookingIdField
                                    .getText()
                                    .trim()
                    );

            if (existing != null) {

                showWarning(
                        "Duplicate Booking ID",
                        "This Booking ID already exists."
                );

                return null;
            }

            // =================================================
            // GENERATE NUMBER
            // =================================================

            int maxNumber = 0;

            for (BedBooking booking : data) {

                if (booking == null) {
                    continue;
                }

                try {

                    int currentNumber =
                            Integer.parseInt(
                                    booking.getNumber()
                            );

                    if (currentNumber >
                            maxNumber) {

                        maxNumber =
                                currentNumber;
                    }

                } catch (
                        NumberFormatException ignored
                ) {
                }
            }

            String newNumber =
                    String.valueOf(
                            maxNumber + 1
                    );

            // =================================================
            // BED NUMBER
            // =================================================

            String bedNo =
                    bedNoField
                            .getText()
                            .trim();

            if (bedNo.isEmpty()) {

                bedNo =
                        "Not Assigned";
            }

            // =================================================
            // CREATE BOOKING
            // =================================================

            BedBooking newBooking =
                    new BedBooking(

                            newNumber,

                            bookingIdField
                                    .getText()
                                    .trim(),

                            patientField
                                    .getText()
                                    .trim(),

                            hospitalField
                                    .getValue(),

                            departmentField
                                    .getValue(),

                            bedNo,

                            bedTypeField
                                    .getValue(),

                            checkInField
                                    .getText()
                                    .trim(),

                            checkOutField
                                    .getText()
                                    .trim(),

                            "Pending"
                    );

            // =================================================
            // SAVE FIREBASE
            // =================================================

            controller.addBedBooking(

                    newBooking.getNumber(),

                    newBooking.getBookingID(),

                    newBooking.getPatientName(),

                    newBooking.getHospitalName(),

                    newBooking.getDepartment(),

                    newBooking.getBedNo(),

                    newBooking.getBedType(),

                    newBooking.getCheckinDate(),

                    newBooking.getExpectedCheckout(),

                    newBooking.getStatus()
            );

            showInformation(
                    "Booking Added",
                    "Bed booking request added successfully."
            );

            return button;
        });

        dialog.showAndWait();
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void applyFilter(
            TableView<BedBooking> table,
            String searchText,
            String selectedDepartment,
            String selectedBedType,
            String selectedStatus
    ) {

        String search =
                searchText == null
                        ? ""
                        : searchText
                                .trim()
                                .toLowerCase();

        ObservableList<BedBooking> filtered =
                FXCollections.observableArrayList();

        for (BedBooking record : data) {

            if (record == null) {
                continue;
            }

            String patientName =
                    record.getPatientName() == null
                            ? ""
                            : record.getPatientName();

            String bookingId =
                    record.getBookingID() == null
                            ? ""
                            : record.getBookingID();

            String department =
                    record.getDepartment() == null
                            ? ""
                            : record.getDepartment();

            String bedType =
                    record.getBedType() == null
                            ? ""
                            : record.getBedType();

            String status =
                    record.getStatus() == null
                            ? ""
                            : record.getStatus();

            boolean searchMatch =
                    patientName
                            .toLowerCase()
                            .contains(search)

                            ||

                    bookingId
                            .toLowerCase()
                            .contains(search);

            boolean departmentMatch =
                    selectedDepartment == null
                            ||
                    "All Departments".equals(
                            selectedDepartment
                    )
                            ||
                    department.equalsIgnoreCase(
                            selectedDepartment
                    );

            boolean bedTypeMatch =
                    selectedBedType == null
                            ||
                    "All Bed Types".equals(
                            selectedBedType
                    )
                            ||
                    bedType.equalsIgnoreCase(
                            selectedBedType
                    );

            boolean statusMatch =
                    selectedStatus == null
                            ||
                    "All Status".equals(
                            selectedStatus
                    )
                            ||
                    status.equalsIgnoreCase(
                            selectedStatus
                    );

            if (
                    searchMatch
                            &&
                    departmentMatch
                            &&
                    bedTypeMatch
                            &&
                    statusMatch
            ) {

                filtered.add(
                        record
                );
            }
        }

        table.setItems(
                filtered
        );
    }

    // =========================================================
    // UPDATE STAT CARDS
    // =========================================================

    private void updateStatCards() {

        int total = data.size();

        int pending = 0;
        int booked = 0;
        int rejected = 0;

        for (BedBooking booking : data) {

            if (booking == null ||
                    booking.getStatus() == null) {

                continue;
            }

            String status =
                    booking.getStatus();

            if (status.equalsIgnoreCase(
                    "Pending"
            )) {

                pending++;

            } else if (
                    status.equalsIgnoreCase(
                            "Booked"
                    )
            ) {

                booked++;

            } else if (
                    status.equalsIgnoreCase(
                            "Rejected"
                    )
            ) {

                rejected++;
            }
        }

        if (totalBookingsLabel != null) {

            totalBookingsLabel.setText(
                    String.valueOf(total)
            );
        }

        if (pendingBookingsLabel != null) {

            pendingBookingsLabel.setText(
                    String.valueOf(pending)
            );
        }

        if (bookedBookingsLabel != null) {

            bookedBookingsLabel.setText(
                    String.valueOf(booked)
            );
        }

        if (rejectedBookingsLabel != null) {

            rejectedBookingsLabel.setText(
                    String.valueOf(rejected)
            );
        }
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String number,
            String symbol,
            String title,
            String bottomText,
            String color
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(20)
        );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPrefHeight(
                145
        );

        card.setPrefWidth(
                260
        );

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );

        // =====================================================
        // SYMBOL
        // =====================================================

        Label symbolLabel =
                new Label(symbol);

        symbolLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        // =====================================================
        // NUMBER
        // =====================================================

        Label numberLabel =
                new Label(number);

        if (title.equals(
                "Total Bookings"
        )) {

            totalBookingsLabel =
                    numberLabel;

        } else if (title.equals(
                "Pending"
        )) {

            pendingBookingsLabel =
                    numberLabel;

        } else if (title.equals(
                "Booked"
        )) {

            bookedBookingsLabel =
                    numberLabel;

        } else if (title.equals(
                "Rejected"
        )) {

            rejectedBookingsLabel =
                    numberLabel;
        }

        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        numberLabel.setTextFill(
                Color.web(NAVY)
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        titleLabel.setTextFill(
                Color.web(NAVY)
        );

        // =====================================================
        // BOTTOM TEXT
        // =====================================================

        Label bottomLabel =
                new Label(bottomText);

        bottomLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        bottomLabel.setTextFill(
                Color.web(color)
        );

        card.getChildren().addAll(
                symbolLabel,
                numberLabel,
                titleLabel,
                bottomLabel
        );

        return card;
    }

    // =========================================================
    // INFORMATION
    // =========================================================

    private void showInformation(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // =========================================================
    // WARNING
    // =========================================================

    private void showWarning(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        return value == null
                ? "N/A"
                : value;
    }

    // =========================================================
    // DISPOSE LISTENER
    // =========================================================

    public void dispose() {

        if (bookingListener != null) {

            bookingListener.remove();

            bookingListener = null;
        }
    }

    // =========================================================
    // GET VIEW
    // =========================================================

    public BorderPane getView() {

        return root;
    }
}