package com.sigma.view;

import com.sigma.controller.Ashavisitcontroller;
import com.sigma.model.AshaBeneficiary;
import com.sigma.model.AshaWorkerVisitModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


// =========================================================
// ASHA WORKER VISIT PAGE
// =========================================================

public class Asha_workervisit {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String PRIMARY_PINK = "#EC4899";
    private static final String BACKGROUND = "#FEF9FC";
    private static final String TEXT_DARK = "#263238";
    private static final String TEXT_GREY = "#607D8B";
    private static final String BORDER = "#D9E2E8";


    // =========================================================
    // DATE FORMATS
    // =========================================================

    // Firebase preferred format
    private static final DateTimeFormatter FIREBASE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Existing Firebase/UI data format
    // Example: 29 Aug 2026
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy");


    // =========================================================
    // CONTROLLER
    // =========================================================

    private final Ashavisitcontroller controller =
            new Ashavisitcontroller();


    // =========================================================
    // VISIT LISTS
    // =========================================================

    private List<AshaWorkerVisitModel> recentVisits;

    private List<AshaWorkerVisitModel> upcomingVisits;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Asha_workervisit() {

        loadFirebaseData();
    }


    // =========================================================
    // LOAD FIREBASE DATA
    // =========================================================

    private void loadFirebaseData() {

        recentVisits = new ArrayList<>();

        upcomingVisits = new ArrayList<>();

        try {

            List<AshaWorkerVisitModel> allVisits =
                    controller.getAllVisits();


            if (allVisits == null) {

                System.out.println(
                        "UI received visits: 0"
                );

                return;
            }


            System.out.println(
                    "UI received visits: "
                            + allVisits.size()
            );


            LocalDate today =
                    LocalDate.now();


            // =====================================================
            // LOOP ALL FIREBASE VISITS
            // =====================================================

            for (AshaWorkerVisitModel visit :
                    allVisits) {

                if (visit == null) {

                    continue;
                }


                System.out.println(
                        "UI Visit -> Name: "
                                + safeString(
                                        visit.getName()
                                )
                                + " | Date: "
                                + safeString(
                                        visit.getDate()
                                )
                                + " | Location: "
                                + safeString(
                                        visit.getLocation()
                                )
                );


                // =================================================
                // CHECK DATE
                // =================================================

                if (visit.getDate() == null
                        || visit.getDate()
                        .trim()
                        .isEmpty()) {

                    System.out.println(
                            "Skipping visit because date is empty."
                    );

                    continue;
                }


                // =================================================
                // PARSE DATE
                // =================================================

                LocalDate visitDate =
                        parseDate(
                                visit.getDate()
                        );


                if (visitDate == null) {

                    System.out.println(
                            "Skipping visit because date could not be parsed: "
                                    + visit.getDate()
                    );

                    continue;
                }


                // =================================================
                // RECENT / TODAY
                // =================================================

                if (visitDate.isBefore(today)
                        || visitDate.isEqual(today)) {

                    recentVisits.add(
                            visit
                    );

                }

                // =================================================
                // UPCOMING
                // =================================================

                else {

                    upcomingVisits.add(
                            visit
                    );
                }
            }


            // =========================================================
            // SORT RECENT
            // NEWEST FIRST
            // =========================================================

            recentVisits.sort(
                    Comparator.comparing(
                            visit -> {

                                LocalDate date =
                                        parseDate(
                                                visit.getDate()
                                        );

                                return date != null
                                        ? date
                                        : LocalDate.MIN;
                            },
                            Comparator.reverseOrder()
                    )
            );


            // =========================================================
            // SORT UPCOMING
            // EARLIEST FIRST
            // =========================================================

            upcomingVisits.sort(
                    Comparator.comparing(
                            visit -> {

                                LocalDate date =
                                        parseDate(
                                                visit.getDate()
                                        );

                                return date != null
                                        ? date
                                        : LocalDate.MAX;
                            }
                    )
            );


            // =========================================================
            // DEBUG
            // =========================================================

            System.out.println(
                    "Recent visits: "
                            + recentVisits.size()
            );

            System.out.println(
                    "Upcoming visits: "
                            + upcomingVisits.size()
            );


        } catch (Exception e) {

            System.out.println(
                    "Error loading Firebase visits:"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // PARSE DATE
    //
    // SUPPORTS:
    //
    // 2026-08-29
    // 29 Aug 2026
    //
    // =========================================================

    private LocalDate parseDate(
            String date) {

        if (date == null
                || date.trim().isEmpty()) {

            return null;
        }


        String value =
                date.trim();


        // =========================================================
        // FORMAT 1
        // yyyy-MM-dd
        // =========================================================

        try {

            return LocalDate.parse(
                    value,
                    FIREBASE_DATE_FORMAT
            );

        } catch (Exception ignored) {
        }


        // =========================================================
        // FORMAT 2
        // dd MMM yyyy
        //
        // Example:
        // 29 Aug 2026
        // =========================================================

        try {

            return LocalDate.parse(
                    value,
                    DISPLAY_DATE_FORMAT
            );

        } catch (Exception ignored) {
        }


        // =========================================================
        // FORMAT 3
        // IF DATE CONTAINS TIME
        //
        // Example:
        // 2026-08-29 10:30:00
        // =========================================================

        try {

            if (value.length() >= 10) {

                String firstTen =
                        value.substring(
                                0,
                                10
                        );

                return LocalDate.parse(
                        firstTen,
                        FIREBASE_DATE_FORMAT
                );
            }

        } catch (Exception ignored) {
        }


        // =========================================================
        // INVALID DATE
        // =========================================================

        System.out.println(
                "Invalid date: "
                        + date
        );

        return null;
    }


    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safeString(
            String value) {

        if (value == null) {

            return "";
        }

        return value;
    }


    // =========================================================
    // MAIN CONTENT
    // =========================================================

    public Node getHealthVisitContent() {

        // Reload latest Firebase data
        loadFirebaseData();


        VBox main =
                new VBox(22);

        main.setPadding(
                new Insets(
                        25,
                        30,
                        30,
                        30
                )
        );

        main.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
        );


        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Health Visits"
                );

        title.setStyle(
                "-fx-font-size: 25px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + TEXT_DARK
                        + ";"
        );


        Label subtitle =
                new Label(
                        "Track and manage beneficiary visits"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;"
                        + "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        VBox heading =
                new VBox(4);

        heading.getChildren().addAll(
                title,
                subtitle
        );


        // =========================================================
        // OVERVIEW
        // =========================================================

        Label overviewTitle =
                new Label(
                        "Visit Overview"
                );

        overviewTitle.setStyle(
                "-fx-font-size: 21px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + TEXT_DARK
                        + ";"
        );


        // =========================================================
        // COLUMNS
        // =========================================================

        HBox columns =
                new HBox(22);

        columns.setAlignment(
                Pos.TOP_CENTER
        );


        VBox recentBox =
                createRecentVisitsBox();


        VBox upcomingBox =
                createUpcomingVisitsBox();


        HBox.setHgrow(
                recentBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                upcomingBox,
                Priority.ALWAYS
        );


        recentBox.setMaxWidth(
                Double.MAX_VALUE
        );

        upcomingBox.setMaxWidth(
                Double.MAX_VALUE
        );


        columns.getChildren().addAll(
                recentBox,
                upcomingBox
        );


        main.getChildren().addAll(
                heading,
                overviewTitle,
                columns
        );


        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane pageScroll =
                new ScrollPane();

        pageScroll.setContent(
                main
        );

        pageScroll.setFitToWidth(
                true
        );

        pageScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        pageScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        pageScroll.setPannable(
                true
        );

        pageScroll.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
                        + "-fx-border-color: transparent;"
        );


        return pageScroll;
    }


    // =========================================================
    // RECENT VISITS
    // =========================================================

    private VBox createRecentVisitsBox() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.setPrefHeight(
                455
        );

      box.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: " +
        BORDER +
        ";" +
        "-fx-border-width: 1;" +
        "-fx-border-radius: 14;" +
        "-fx-background-radius: 14;"
);


        Label title =
                new Label(
                        "◷   Recent Visits"
                );

       title.setStyle(
        "-fx-font-size: 18px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " +
        PRIMARY_PINK +
        ";"
);


        VBox content =
                new VBox(12);


        // =========================================================
        // RECENT VISITS CARDS
        // =========================================================

        for (AshaWorkerVisitModel visit :
                recentVisits) {

            int totalVisits =
                    countVisits(
                            visit.getBeneficiaryId()
                    );


            content.getChildren().add(
                    recentCard(
                            visit.getName(),
                            visit.getLocation(),
                            visit.getDate(),
                            visit.getCondition(),
                            totalVisits
                    )
            );
        }


        // =========================================================
        // EMPTY MESSAGE
        // =========================================================

        if (recentVisits.isEmpty()) {

            Label empty =
                    new Label(
                            "No recent visits found."
                    );

            empty.setStyle(
                    "-fx-text-fill: "
                            + TEXT_GREY
                            + ";"
            );

            content.getChildren().add(
                    empty
            );
        }


        // =========================================================
        // SCROLL
        // =========================================================

       box.getChildren().addAll(
        title,
        content
);


        return box;
    }


    // =========================================================
    // UPCOMING VISITS
    // =========================================================

    private VBox createUpcomingVisitsBox() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.setPrefHeight(
                455
        );

     box.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: " +
        BORDER +
        ";" +
        "-fx-border-width: 1;" +
        "-fx-border-radius: 14;" +
        "-fx-background-radius: 14;"
);


        Label title =
                new Label(
                        "▦   Upcoming Visits"
                );

      title.setStyle(
        "-fx-font-size: 18px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " +
        PRIMARY_PINK +
        ";"
);


        VBox content =
                new VBox(12);


        // =========================================================
        // UPCOMING CARDS
        // =========================================================

        for (AshaWorkerVisitModel visit :
                upcomingVisits) {

            content.getChildren().add(
                    upcomingCard(
                            visit.getDate(),
                            visit.getName(),
                            visit.getLocation(),
                            visit.getPurpose(),
                            visit.getTime()
                    )
            );
        }


        // =========================================================
        // EMPTY MESSAGE
        // =========================================================

        if (upcomingVisits.isEmpty()) {

            Label empty =
                    new Label(
                            "No upcoming visits found."
                    );

            empty.setStyle(
                    "-fx-text-fill: "
                            + TEXT_GREY
                            + ";"
            );

            content.getChildren().add(
                    empty
            );
        }


        // =========================================================
        // SCROLL
        // =========================================================

       box.getChildren().addAll(
        title,
        content
);

        return box;
    }


    // =========================================================
    // COUNT VISITS
    // =========================================================

    private int countVisits(
            int beneficiaryId) {

        try {

            return controller.getTotalVisitCount(
                    beneficiaryId
            );

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    // =========================================================
    // RECENT CARD
    // =========================================================

    private VBox recentCard(
            String name,
            String location,
            String date,
            String condition,
            int totalVisits) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(14)
        );

        card.setMinHeight(
                145
        );

        card.setPrefHeight(
                145
        );

        card.setStyle(
                "-fx-background-color: #FAFBFC;"
                        + "-fx-border-color: #E2E8EC;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
        );


        Label nameLabel =
                new Label(
                        safeString(name)
                );

        nameLabel.setStyle(
                "-fx-font-size: 16px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + PRIMARY_PINK
                        + ";"
        );


        Label locationLabel =
                new Label(
                        "📍  "
                                + safeString(location)
                );

        locationLabel.setStyle(
                "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        Label dateLabel =
                new Label(
                        "▣  "
                                + formatDate(date)
                );

        dateLabel.setStyle(
                "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        Label conditionLabel =
                new Label(
                        "●  Condition: "
                                + safeString(condition)
                );

        conditionLabel.setStyle(
                "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + PRIMARY_PINK
                        + ";"
        );


        Label totalLabel =
                new Label(
                        "✓  Total Visits: "
                                + totalVisits
                );

        totalLabel.setStyle(
                "-fx-font-weight: bold;"
                        + "-fx-text-fill: #475569;"
        );


        card.getChildren().addAll(
                nameLabel,
                locationLabel,
                dateLabel,
                conditionLabel,
                totalLabel
        );


        return card;
    }


    // =========================================================
    // UPCOMING CARD
    // =========================================================

    private VBox upcomingCard(
            String date,
            String name,
            String location,
            String purpose,
            String time) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(14)
        );

        card.setMinHeight(
                160
        );

        card.setPrefHeight(
                160
        );

        card.setStyle(
                "-fx-background-color: #FAFBFC;"
                        + "-fx-border-color: #E2E8EC;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
        );


        Label dateLabel =
                new Label(
                        "▣  "
                                + formatDate(date)
                );

        dateLabel.setStyle(
                "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + PRIMARY_PINK
                        + ";"
        );


        Label nameLabel =
                new Label(
                        safeString(name)
                );

        nameLabel.setStyle(
                "-fx-font-size: 16px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: "
                        + PRIMARY_PINK
                        + ";"
        );


        Label locationLabel =
                new Label(
                        "📍  "
                                + safeString(location)
                );

        locationLabel.setStyle(
                "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        Label purposeLabel =
                new Label(
                        "🩺  "
                                + safeString(purpose)
                );

        purposeLabel.setStyle(
                "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        Label timeLabel =
                new Label(
                        "◷  "
                                + safeString(time)
                );

        timeLabel.setStyle(
                "-fx-text-fill: "
                        + TEXT_GREY
                        + ";"
        );


        card.getChildren().addAll(
                dateLabel,
                nameLabel,
                locationLabel,
                purposeLabel,
                timeLabel
        );


        return card;
    }


    // =========================================================
    // FORMAT DATE FOR DISPLAY
    // =========================================================

    private String formatDate(
            String date) {

        if (date == null
                || date.trim().isEmpty()) {

            return "";
        }


        LocalDate localDate =
                parseDate(date);


        if (localDate == null) {

            return date;
        }


        return localDate.format(
                DISPLAY_DATE_FORMAT
        );
    }


    // =========================================================
    // SHOW VISIT DIALOG
    // THIS METHOD IS CALLED FROM BENEFICIARY PAGE
    // =========================================================

    public void showVisitDialog(
            AshaBeneficiary beneficiary) {

        if (beneficiary == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Beneficiary not found."
            );

            return;
        }


        Dialog<ButtonType> dialog =
                new Dialog<>();


        dialog.setTitle(
                "Record Visit"
        );

        dialog.setHeaderText(
                "Record Visit - "
                        + beneficiary.getName()
        );


        ButtonType saveButton =
                new ButtonType(
                        "Save Visit",
                        ButtonBar.ButtonData.OK_DONE
                );


        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        ButtonType.CANCEL
                );


        // =========================================================
        // FORM
        // =========================================================

        GridPane form =
                new GridPane();

        form.setHgap(10);

        form.setVgap(12);

        form.setPadding(
                new Insets(15)
        );


        // =========================================================
        // DATE
        // =========================================================

        DatePicker datePicker =
                new DatePicker();

        datePicker.setValue(
                LocalDate.now()
        );


        // =========================================================
        // PURPOSE
        // =========================================================

        TextField purposeField =
                new TextField();

        purposeField.setPromptText(
                "Enter visit purpose"
        );


        // =========================================================
        // TIME
        // =========================================================

        TextField timeField =
                new TextField();

        timeField.setPromptText(
                "Example: 10:30 AM"
        );


        // =========================================================
        // BENEFICIARY INFO
        // =========================================================

        Label beneficiaryLabel =
                new Label(
                        beneficiary.getName()
                                + " - "
                                + beneficiary.getVillage()
                );

        beneficiaryLabel.setStyle(
                "-fx-font-weight: bold;"
        );


        // =========================================================
        // FORM ADD
        // =========================================================

        form.add(
                new Label("Beneficiary:"),
                0,
                0
        );

        form.add(
                beneficiaryLabel,
                1,
                0
        );


        form.add(
                new Label("Visit Date:"),
                0,
                1
        );

        form.add(
                datePicker,
                1,
                1
        );


        form.add(
                new Label("Purpose:"),
                0,
                2
        );

        form.add(
                purposeField,
                1,
                2
        );


        form.add(
                new Label("Time:"),
                0,
                3
        );

        form.add(
                timeField,
                1,
                3
        );


        dialog.getDialogPane()
                .setContent(form);


        // =========================================================
        // SAVE
        // =========================================================

        dialog.showAndWait()
                .ifPresent(result -> {

                    if (result != saveButton) {

                        return;
                    }


                    // =================================================
                    // DATE VALIDATION
                    // =================================================

                    if (datePicker.getValue() == null) {

                        showAlert(
                                Alert.AlertType.WARNING,
                                "Please select visit date."
                        );

                        return;
                    }


                    // =================================================
                    // PURPOSE
                    // =================================================

                    String purpose =
                            purposeField
                                    .getText()
                                    .trim();


                    // =================================================
                    // TIME
                    // =================================================

                    String time =
                            timeField
                                    .getText()
                                    .trim();


                    if (purpose.isEmpty()) {

                        showAlert(
                                Alert.AlertType.WARNING,
                                "Please enter visit purpose."
                        );

                        return;
                    }


                    if (time.isEmpty()) {

                        showAlert(
                                Alert.AlertType.WARNING,
                                "Please enter visit time."
                        );

                        return;
                    }


                    // =================================================
                    // DATE SAVE FORMAT
                    //
                    // IMPORTANT:
                    // Firebase mein yyyy-MM-dd save hoga.
                    // =================================================

                    String date =
                            datePicker
                                    .getValue()
                                    .format(
                                            FIREBASE_DATE_FORMAT
                                    );


                    System.out.println(
                            "Saving visit:"
                                    + " Beneficiary="
                                    + beneficiary.getName()
                                    + " | Date="
                                    + date
                                    + " | Purpose="
                                    + purpose
                                    + " | Time="
                                    + time
                    );


                    // =================================================
                    // FIREBASE SAVE
                    // =================================================

                    boolean success =
                            controller.addVisit(
                                    beneficiary,
                                    date,
                                    purpose,
                                    time
                            );


                    // =================================================
                    // SUCCESS
                    // =================================================

                    if (success) {

                        showAlert(
                                Alert.AlertType.INFORMATION,
                                "Visit recorded successfully."
                        );


                        // =================================================
                        // IMPORTANT:
                        // SAVE KE BAAD FIREBASE SE DATA RELOAD
                        // =================================================

                        loadFirebaseData();

                    } else {

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Failed to save visit."
                        );
                    }
                });
    }


    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "ASHA Worker"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}
