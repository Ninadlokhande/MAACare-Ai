package com.sigma.view.motherPages;

import java.util.ArrayList;
import java.util.List;

import com.sigma.controller.MedicineReminderController;
import com.sigma.model.MedicineReminderModel;
import com.sigma.model.MotherWlcModel;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


// =============================================================
// MOTHER MEDICINE REMINDER PAGE
// =============================================================

public class MotherMedicineReminder {

    private final String PINK = "#E84A87";
    private final String PURPLE = "#9B4DCC";
    private final String GREEN = "#3C9A68";


    // =========================================================
    // MOTHER MODEL
    // =========================================================

    private final MotherWlcModel motherModel;


    // =========================================================
    // CONTROLLER
    // =========================================================

    private final MedicineReminderController controller;


    // =========================================================
    // MEDICINE MODEL LIST
    // =========================================================

    private final List<MedicineReminderModel> medicines =
        new ArrayList<>();


    // =========================================================
    // DYNAMIC UI REFERENCES
    // =========================================================

    private VBox medicineListContainer;

    private HBox summaryContainer;

    private VBox upcomingRemindersContainer;

    private VBox prescriptionMedicineList;

    private Label prescriptionDoctorLabel;

    private Label prescriptionDateLabel;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherMedicineReminder(
            MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        this.controller =
                new MedicineReminderController();

        loadMedicinesFromFirebase();
    }


    // =========================================================
    // LOAD MEDICINES FROM FIREBASE
    // =========================================================

    private void loadMedicinesFromFirebase() {

        medicines.clear();

        if (motherModel == null) {

            System.out.println(
                "Mother model is null."
            );

            loadDemoMedicines();

            return;
        }


        String motherId =
            motherModel.getMotherId();


        if (motherId == null ||
            motherId.trim().isEmpty()) {

            System.out.println(
                "Mother ID not available."
            );

            loadDemoMedicines();

            return;
        }


        try {

            List<MedicineReminderModel> firebaseMedicines =
                controller.getMedicinesByMotherId(
                    motherId
                );


            if (firebaseMedicines != null &&
                !firebaseMedicines.isEmpty()) {

                medicines.addAll(
                    firebaseMedicines
                );

                System.out.println(
                    "Firebase medicines loaded: "
                    + medicines.size()
                );

            } else {

                System.out.println(
                    "No medicines found in Firebase."
                );

                loadDemoMedicines();
            }


        } catch (Exception e) {

            System.out.println(
                "Error loading medicines from Firebase."
            );

            e.printStackTrace();

            // Firebase error झाल्यास UI blank ठेवायचा नाही
            loadDemoMedicines();
        }
    }


    // =========================================================
    // DEMO MEDICINES
    // =========================================================

    private void loadDemoMedicines() {

        medicines.clear();


        // =====================================================
        // DEMO MEDICINE 1
        // =====================================================

        MedicineReminderModel iron =
            new MedicineReminderModel(

                "Iron + Folic Acid",

                "Ferrous Ascorbate",

                "Morning",

                "8:00 AM",

                "After Breakfast",

                "1 Tablet",

                "Once daily",

                "Dr. Priya Sharma",

                "10 Aug 2026",

                true,

                true,

                true,

                true
            );


        // Special ID to identify demo data
        iron.setMedicineId(
            "DEMO_MEDICINE_001"
        );


        if (motherModel != null) {

            iron.setMotherId(
                motherModel.getMotherId()
            );
        }


        // =====================================================
        // DEMO MEDICINE 2
        // =====================================================

        MedicineReminderModel calcium =
            new MedicineReminderModel(

                "Calcium Tablet",

                "Calcium + Vitamin D3",

                "Afternoon",

                "2:00 PM",

                "After Lunch",

                "1 Tablet",

                "Once daily",

                "Dr. Priya Sharma",

                "10 Aug 2026",

                true,

                true,

                true,

                true
            );


        calcium.setMedicineId(
            "DEMO_MEDICINE_002"
        );


        if (motherModel != null) {

            calcium.setMotherId(
                motherModel.getMotherId()
            );
        }


        medicines.add(iron);

        medicines.add(calcium);


        System.out.println(
            "Demo medicines loaded."
        );
    }


    // =========================================================
    // CHECK DEMO MEDICINE
    // =========================================================

    private boolean isDemoMedicine(
            MedicineReminderModel medicine) {

        if (medicine == null ||
            medicine.getMedicineId() == null) {

            return false;
        }


        return medicine.getMedicineId()
                .startsWith("DEMO_");
    }


    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createMedicineReminderPage() {

        VBox page = new VBox();

        page.setFillWidth(true);

        page.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to bottom right, " +
            "#FFFFFF 0%, " +
            "#FFF7FB 55%, " +
            "#F4EDFF 100%);"
        );


        VBox content = new VBox();

        content.setSpacing(20);

        content.setPadding(
            new Insets(25, 30, 40, 30)
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        VBox titleBox = new VBox();

        titleBox.setSpacing(5);


        Label title = new Label(
            "Medicine Reminder 💊"
        );

        title.setStyle(
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle = new Label(
            "Never miss your medicines. Stay healthy throughout your pregnancy."
        );

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #77778D;"
        );


        titleBox.getChildren().addAll(
            title,
            subtitle
        );


        content.getChildren().add(titleBox);


        // =====================================================
        // SUMMARY
        // =====================================================

        HBox summary =
            createSummarySection();

        content.getChildren().add(summary);


        // =====================================================
        // MAIN LAYOUT
        // =====================================================

        HBox mainLayout = new HBox();

        mainLayout.setSpacing(20);


        VBox leftContent = new VBox();

        leftContent.setSpacing(18);

        HBox.setHgrow(
            leftContent,
            Priority.ALWAYS
        );


        VBox rightContent = new VBox();

        rightContent.setSpacing(18);

        rightContent.setPrefWidth(350);

        rightContent.setMinWidth(320);


        // =====================================================
        // LEFT SIDE
        // =====================================================

        VBox medicinesCard =
            createTodaysMedicinesCard();


        VBox upcomingCard =
            createUpcomingRemindersCard();


        leftContent.getChildren().addAll(
            medicinesCard,
            upcomingCard
        );


        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox reminderCard =
            createReminderSettingsCard();


        VBox prescriptionCard =
            createPrescriptionCard();


        VBox safetyCard =
            createMedicineSafetyCard();


        rightContent.getChildren().addAll(
            reminderCard,
            prescriptionCard,
            safetyCard
        );


        mainLayout.getChildren().addAll(
            leftContent,
            rightContent
        );


        content.getChildren().add(mainLayout);


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;" +
            "-fx-border-color: transparent;"
        );


        page.getChildren().add(scrollPane);

        VBox.setVgrow(
            scrollPane,
            Priority.ALWAYS
        );


        return page;
    }


    // =========================================================
    // SUMMARY SECTION
    // =========================================================

    private HBox createSummarySection() {

        int totalMedicines =
            medicines.size();

        int takenMedicines =
            getTakenMedicineCount();

        int remainingMedicines =
            totalMedicines - takenMedicines;

        String nextReminder =
            getNextReminderTime();


        HBox summary = new HBox();

        summary.setSpacing(15);


        summary.getChildren().addAll(

            createSummaryCard(
                "💊",
                "Today's Medicines",
                String.valueOf(totalMedicines),
                PINK
            ),

            createSummaryCard(
                "✓",
                "Taken",
                String.valueOf(takenMedicines),
                GREEN
            ),

            createSummaryCard(
                "⏰",
                "Remaining",
                String.valueOf(remainingMedicines),
                PURPLE
            ),

            createSummaryCard(
                "📅",
                "Next Reminder",
                nextReminder,
                "#C7821B"
            )
        );


        summaryContainer = summary;


        return summary;
    }


    // =========================================================
    // REFRESH SUMMARY
    // =========================================================

    private void refreshSummarySection() {

        if (summaryContainer == null) {
            return;
        }


        int totalMedicines =
            medicines.size();

        int takenMedicines =
            getTakenMedicineCount();

        int remainingMedicines =
            totalMedicines - takenMedicines;

        String nextReminder =
            getNextReminderTime();


        summaryContainer.getChildren().clear();


        summaryContainer.getChildren().addAll(

            createSummaryCard(
                "💊",
                "Today's Medicines",
                String.valueOf(totalMedicines),
                PINK
            ),

            createSummaryCard(
                "✓",
                "Taken",
                String.valueOf(takenMedicines),
                GREEN
            ),

            createSummaryCard(
                "⏰",
                "Remaining",
                String.valueOf(remainingMedicines),
                PURPLE
            ),

            createSummaryCard(
                "📅",
                "Next Reminder",
                nextReminder,
                "#C7821B"
            )
        );
    }


    // =========================================================
    // GET TAKEN COUNT
    // =========================================================

    private int getTakenMedicineCount() {

        int count = 0;

        for (
            MedicineReminderModel medicine :
            medicines
        ) {

            if (medicine.isTaken()) {
                count++;
            }
        }

        return count;
    }


    // =========================================================
    // GET NEXT REMINDER
    // =========================================================

    private String getNextReminderTime() {

        for (
            MedicineReminderModel medicine :
            medicines
        ) {

            if (!medicine.isTaken()) {

                return medicine.getTime();
            }
        }

        return "All Done";
    }


    // =========================================================
    // SUMMARY CARD
    // =========================================================

    private VBox createSummaryCard(
            String icon,
            String title,
            String value,
            String color) {

        VBox card = new VBox();

        card.setSpacing(6);

        card.setPadding(
            new Insets(15)
        );

        card.setPrefWidth(190);

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 16;"
        );


        HBox top = new HBox();

        top.setAlignment(
            Pos.CENTER_LEFT
        );


        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 25px;"
        );


        HBox.setHgrow(
            iconLabel,
            Priority.ALWAYS
        );


        Label valueLabel =
            new Label(value);

        valueLabel.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + color + ";"
        );


        top.getChildren().addAll(
            iconLabel,
            valueLabel
        );


        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );


        card.getChildren().addAll(
            top,
            titleLabel
        );


        return card;
    }


    // =========================================================
    // TODAY'S MEDICINES
    // =========================================================

    private VBox createTodaysMedicinesCard() {

        VBox card =
            createWhiteCard();


        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );


        VBox headingText =
            new VBox();

        headingText.setSpacing(4);


        Label title =
            new Label(
                "Today's Medicines"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
            new Label(
                "Take your medicines according to the prescribed schedule."
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        headingText.getChildren().addAll(
            title,
            subtitle
        );


        HBox.setHgrow(
            headingText,
            Priority.ALWAYS
        );


        Button addMedicine =
            createGradientButton(
                "+ Add Medicine"
            );


        addMedicine.setOnAction(
            e -> showAddMedicineDialog()
        );


        heading.getChildren().addAll(
            headingText,
            addMedicine
        );


        // =====================================================
        // DYNAMIC MEDICINE LIST
        // =====================================================

        medicineListContainer =
            new VBox();

        medicineListContainer.setSpacing(12);


        refreshMedicineList();


        card.getChildren().addAll(
            heading,
            medicineListContainer
        );


        return card;
    }


    // =========================================================
    // REFRESH MEDICINE LIST
    // =========================================================

    private void refreshMedicineList() {

        if (medicineListContainer == null) {
            return;
        }


        medicineListContainer.getChildren().clear();


        if (medicines.isEmpty()) {

            Label empty =
                new Label(
                    "No medicines added yet."
                );

            empty.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
            );

            medicineListContainer.getChildren().add(
                empty
            );

            return;
        }


        for (
            MedicineReminderModel medicine :
            medicines
        ) {

            medicineListContainer.getChildren().add(
                createMedicineRow(medicine)
            );
        }
    }


    // =========================================================
    // MEDICINE ROW
    // =========================================================

    private HBox createMedicineRow(
            MedicineReminderModel medicine) {

        HBox row =
            new HBox();

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setSpacing(15);

        row.setPadding(
            new Insets(13)
        );


        row.setStyle(
            "-fx-background-color: #FCF9FC;" +
            "-fx-background-radius: 13;" +
            "-fx-border-color: #EEE5EF;" +
            "-fx-border-radius: 13;"
        );


        StackPane iconBox =
            new StackPane();

        iconBox.setPrefSize(
            52,
            52
        );

        iconBox.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-background-radius: 14;"
        );


        Label iconLabel =
            new Label("💊");

        iconLabel.setStyle(
            "-fx-font-size: 25px;"
        );


        iconBox.getChildren().add(
            iconLabel
        );


        VBox details =
            new VBox();

        details.setSpacing(4);


        Label medicineName =
            new Label(
                medicine.getMedicineName()
            );

        medicineName.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label medicineType =
            new Label(
                medicine.getMedicineType()
            );

        medicineType.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #77778D;"
        );


        Label instructionLabel =
            new Label(
                medicine.getInstruction()
            );

        instructionLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #8A7890;"
        );


        details.getChildren().addAll(
            medicineName,
            medicineType,
            instructionLabel
        );


        HBox.setHgrow(
            details,
            Priority.ALWAYS
        );


        VBox timeBox =
            new VBox();

        timeBox.setAlignment(
            Pos.CENTER_RIGHT
        );

        timeBox.setSpacing(4);


        Label periodLabel =
            new Label(
                medicine.getPeriod()
            );

        periodLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        Label timeLabel =
            new Label(
                medicine.getTime()
            );

        timeLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #7041A5;"
        );


        timeBox.getChildren().addAll(
            periodLabel,
            timeLabel
        );


        Button status =
            new Button(
                medicine.isTaken()
                    ? "✓ Taken"
                    : "Take Now"
            );


        updateStatusButton(
            status,
            medicine.isTaken()
        );


        if (!medicine.isTaken()) {

            status.setOnAction(
                e -> markMedicineAsTaken(
                    medicine
                )
            );
        }


        row.getChildren().addAll(
            iconBox,
            details,
            timeBox,
            status
        );


        return row;
    }


    // =========================================================
    // MARK MEDICINE AS TAKEN
    // =========================================================

    private void markMedicineAsTaken(
            MedicineReminderModel medicine) {

        // =====================================================
        // DEMO MEDICINE
        // =====================================================

        if (isDemoMedicine(medicine)) {

            medicine.setTaken(true);

            refreshMedicineList();

            refreshSummarySection();

            refreshUpcomingReminders();

            showMessage(
                "Medicine Taken",
                medicine.getMedicineName() +
                " marked as taken successfully."
            );

            return;
        }


        // =====================================================
        // FIREBASE MEDICINE
        // =====================================================

        String medicineId =
            medicine.getMedicineId();


        if (medicineId == null ||
            medicineId.trim().isEmpty()) {

            showMessage(
                "Error",
                "Medicine ID is missing."
            );

            return;
        }


        try {

            boolean success =
                controller.markAsTaken(
                    medicineId
                );


            if (success) {

                medicine.setTaken(true);

                refreshMedicineList();

                refreshSummarySection();

                refreshUpcomingReminders();

                showMessage(
                    "Medicine Taken",
                    medicine.getMedicineName() +
                    " marked as taken successfully."
                );

            } else {

                showMessage(
                    "Error",
                    "Unable to update medicine status."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showMessage(
                "Firebase Error",
                "Unable to update medicine status."
            );
        }
    }


    // =========================================================
    // STATUS BUTTON STYLE
    // =========================================================

    private void updateStatusButton(
            Button status,
            boolean taken) {

        if (taken) {

            status.setStyle(
                "-fx-background-color: #E8F7EF;" +
                "-fx-text-fill: #3C9A68;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 7px 12px;"
            );

        } else {

            status.setStyle(
                "-fx-background-color: #E84A87;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 7px 12px;"
            );
        }
    }


    // =========================================================
    // UPCOMING REMINDERS
    // =========================================================

    private VBox createUpcomingRemindersCard() {

        VBox card =
            createWhiteCard();


        Label title =
            new Label(
                "Upcoming Reminders"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
            new Label(
                "Your next scheduled medicine reminders"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        upcomingRemindersContainer =
            new VBox();

        upcomingRemindersContainer.setSpacing(10);


        refreshUpcomingReminders();


        card.getChildren().addAll(
            title,
            subtitle,
            upcomingRemindersContainer
        );


        return card;
    }


    // =========================================================
    // REFRESH UPCOMING
    // =========================================================

    private void refreshUpcomingReminders() {

        if (upcomingRemindersContainer == null) {
            return;
        }


        upcomingRemindersContainer
            .getChildren()
            .clear();


        for (
            MedicineReminderModel medicine :
            medicines
        ) {

            if (!medicine.isTaken()) {

                upcomingRemindersContainer
                    .getChildren()
                    .add(
                        createUpcomingRow(medicine)
                    );
            }
        }


        if (
            upcomingRemindersContainer
                .getChildren()
                .isEmpty()
        ) {

            Label allDone =
                new Label(
                    "✓ All medicines for today are completed."
                );

            allDone.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #3C9A68;" +
                "-fx-font-weight: bold;"
            );

            upcomingRemindersContainer
                .getChildren()
                .add(allDone);
        }
    }


    // =========================================================
    // UPCOMING ROW
    // =========================================================

    private HBox createUpcomingRow(
            MedicineReminderModel medicine) {

        HBox row =
            new HBox();

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setSpacing(12);

        row.setPadding(
            new Insets(9)
        );


        Label iconLabel =
            new Label("💊");

        iconLabel.setStyle(
            "-fx-font-size: 22px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(3);


        Label medicineLabel =
            new Label(
                medicine.getMedicineName()
            );

        medicineLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label dayLabel =
            new Label("Today");

        dayLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        text.getChildren().addAll(
            medicineLabel,
            dayLabel
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        Label timeLabel =
            new Label(
                medicine.getTime()
            );

        timeLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );


        row.getChildren().addAll(
            iconLabel,
            text,
            timeLabel
        );


        return row;
    }


    // =========================================================
    // REMINDER SETTINGS CARD
    // =========================================================

    private VBox createReminderSettingsCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #FFF5F9;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F1D7E3;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Reminder Settings",
                FontAwesomeIcon.BELL
            );


        Label description =
            new Label(
                "Get notified when it is time to take your medicine."
            );

        description.setWrapText(true);

        description.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;" +
            "-fx-line-spacing: 3px;"
        );


        VBox options =
            new VBox();

        options.setSpacing(10);


        options.getChildren().addAll(

            createReminderOption(
                "🔔",
                "Medicine notifications",
                "Receive reminders on time",
                1
            ),

            createReminderOption(
                "⏰",
                "Early reminder",
                "Notify me 15 minutes before",
                2
            ),

            createReminderOption(
                "📋",
                "Daily summary",
                "Show today's medicine schedule",
                3
            )
        );


        Button settings =
            createOutlineButton(
                "Manage Reminder Settings"
            );


        settings.setOnAction(
            e -> openMotherSettings()
        );


        card.getChildren().addAll(
            heading,
            description,
            options,
            settings
        );


        return card;
    }


    // =========================================================
    // REMINDER OPTION
    // =========================================================

    private HBox createReminderOption(
            String icon,
            String title,
            String description,
            int optionType) {

        HBox row =
            new HBox();

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setSpacing(10);


        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 20px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(2);


        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label descLabel =
            new Label(description);

        descLabel.setStyle(
            "-fx-font-size: 10px;" +
            "-fx-text-fill: #77778D;"
        );


        text.getChildren().addAll(
            titleLabel,
            descLabel
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        boolean enabled = true;


        if (optionType == 1) {

            enabled =
                medicines.isEmpty()
                    ? true
                    : medicines.get(0)
                        .isNotificationsEnabled();
        }

        else if (optionType == 2) {

            enabled =
                medicines.isEmpty()
                    ? true
                    : medicines.get(0)
                        .isEarlyReminderEnabled();
        }

        else if (optionType == 3) {

            enabled =
                medicines.isEmpty()
                    ? true
                    : medicines.get(0)
                        .isDailySummaryEnabled();
        }


        Button toggle =
            new Button(
                enabled ? "ON" : "OFF"
            );


        updateToggleButton(
            toggle,
            enabled
        );


        toggle.setOnAction(
            e -> updateReminderSetting(
                optionType,
                toggle
            )
        );


        row.getChildren().addAll(
            iconLabel,
            text,
            toggle
        );


        return row;
    }


    // =========================================================
    // UPDATE REMINDER SETTING
    // =========================================================

    private void updateReminderSetting(
            int optionType,
            Button toggle) {

        boolean newValue =
            toggle.getText().equals("OFF");


        if (medicines.isEmpty()) {

            updateToggleButton(
                toggle,
                newValue
            );

            return;
        }


        try {

            boolean success = true;


            for (
                MedicineReminderModel medicine :
                medicines
            ) {

                // =============================================
                // UPDATE LOCAL MODEL
                // =============================================

                if (optionType == 1) {

                    medicine.setNotificationsEnabled(
                        newValue
                    );

                }

                else if (optionType == 2) {

                    medicine.setEarlyReminderEnabled(
                        newValue
                    );

                }

                else if (optionType == 3) {

                    medicine.setDailySummaryEnabled(
                        newValue
                    );
                }


                // =============================================
                // DEMO MEDICINE
                // =============================================

                if (isDemoMedicine(medicine)) {
                    continue;
                }


                // =============================================
                // SAVE FIREBASE MEDICINE
                // =============================================

                boolean updated =
                    controller.updateMedicine(
                        medicine
                    );


                if (!updated) {

                    success = false;
                }
            }


            if (success) {

                updateToggleButton(
                    toggle,
                    newValue
                );

                showMessage(
                    "Reminder Settings",
                    "Reminder setting updated successfully."
                );

            } else {

                showMessage(
                    "Error",
                    "Unable to save reminder setting."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showMessage(
                "Firebase Error",
                "Unable to save reminder setting."
            );
        }
    }


    // =========================================================
    // TOGGLE STYLE
    // =========================================================

    private void updateToggleButton(
            Button toggle,
            boolean enabled) {

        toggle.setText(
            enabled ? "ON" : "OFF"
        );


        if (enabled) {

            toggle.setStyle(
                "-fx-background-color: #E84A87;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 5px 10px;"
            );

        } else {

            toggle.setStyle(
                "-fx-background-color: #D9D6DC;" +
                "-fx-text-fill: #666680;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 5px 10px;"
            );
        }
    }


    // =========================================================
    // PRESCRIPTION CARD
    // =========================================================

    private VBox createPrescriptionCard() {

        VBox card =
            createWhiteCard();


        HBox heading =
            createCardHeading(
                "Prescription",
                FontAwesomeIcon.FILE_TEXT
            );


        String doctorName =
            medicines.isEmpty()
                ? "Doctor information unavailable"
                : medicines.get(0)
                    .getPrescribedBy();


        String prescribedDate =
            medicines.isEmpty()
                ? "Date unavailable"
                : medicines.get(0)
                    .getPrescribedDate();


        prescriptionDoctorLabel =
            new Label(
                "👨‍⚕️ " + doctorName
            );

        prescriptionDoctorLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        prescriptionDateLabel =
            new Label(
                "Prescribed on: " +
                prescribedDate
            );

        prescriptionDateLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        prescriptionMedicineList =
            new VBox();

        prescriptionMedicineList.setSpacing(6);


        refreshPrescriptionList();


        Button view =
            createOutlineButton(
                "View Full Prescription  →"
            );


        view.setOnAction(
            e -> showFullPrescription()
        );


        card.getChildren().addAll(
            heading,
            prescriptionDoctorLabel,
            prescriptionDateLabel,
            prescriptionMedicineList,
            view
        );


        return card;
    }


    // =========================================================
    // REFRESH PRESCRIPTION
    // =========================================================

    private void refreshPrescriptionList() {

        if (prescriptionMedicineList == null) {
            return;
        }


        prescriptionMedicineList
            .getChildren()
            .clear();


        if (medicines.isEmpty()) {

            Label empty =
                new Label(
                    "No prescription details available."
                );

            empty.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #77778D;"
            );

            prescriptionMedicineList
                .getChildren()
                .add(empty);

        } else {

            for (
                MedicineReminderModel medicine :
                medicines
            ) {

                prescriptionMedicineList
                    .getChildren()
                    .add(
                        createPrescriptionItem(medicine)
                    );
            }
        }


        // =====================================================
        // UPDATE DOCTOR + DATE
        // =====================================================

        if (prescriptionDoctorLabel != null) {

            String doctorName =
                medicines.isEmpty()
                    ? "Doctor information unavailable"
                    : medicines.get(0)
                        .getPrescribedBy();


            prescriptionDoctorLabel.setText(
                "👨‍⚕️ " + doctorName
            );
        }


        if (prescriptionDateLabel != null) {

            String prescribedDate =
                medicines.isEmpty()
                    ? "Date unavailable"
                    : medicines.get(0)
                        .getPrescribedDate();


            prescriptionDateLabel.setText(
                "Prescribed on: " +
                prescribedDate
            );
        }
    }


    // =========================================================
    // PRESCRIPTION ITEM
    // =========================================================

    private HBox createPrescriptionItem(
            MedicineReminderModel medicine) {

        HBox row =
            new HBox();

        row.setSpacing(8);

        row.setAlignment(
            Pos.CENTER_LEFT
        );


        Label tick =
            new Label("✓");

        tick.setStyle(
            "-fx-text-fill: #3C9A68;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );


        Label medicineLabel =
            new Label(
                medicine.getMedicineName() +
                "  •  " +
                medicine.getFrequency()
            );

        medicineLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #55556D;"
        );


        row.getChildren().addAll(
            tick,
            medicineLabel
        );


        return row;
    }


    // =========================================================
    // FULL PRESCRIPTION
    // =========================================================

    private void showFullPrescription() {

        Dialog<ButtonType> dialog =
            new Dialog<>();


        dialog.setTitle(
            "Full Prescription"
        );

        dialog.setHeaderText(
            "Complete Prescription Details"
        );


        VBox content =
            new VBox();

        content.setSpacing(14);

        content.setPadding(
            new Insets(15)
        );


        if (medicines.isEmpty()) {

            content.getChildren().add(
                new Label(
                    "No prescription details available."
                )
            );

        } else {

            MedicineReminderModel first =
                medicines.get(0);


            Label doctor =
                new Label(
                    "👨‍⚕️ Prescribed By: " +
                    first.getPrescribedBy()
                );

            doctor.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
            );


            Label date =
                new Label(
                    "📅 Prescribed Date: " +
                    first.getPrescribedDate()
                );

            date.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #77778D;"
            );


            content.getChildren().addAll(
                doctor,
                date
            );


            for (
                MedicineReminderModel medicine :
                medicines
            ) {

                VBox medicineBox =
                    new VBox();

                medicineBox.setSpacing(5);

                medicineBox.setPadding(
                    new Insets(12)
                );

                medicineBox.setStyle(
                    "-fx-background-color: #FFF5F9;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #F1D7E3;" +
                    "-fx-border-radius: 12;"
                );


                Label name =
                    new Label(
                        "💊 " +
                        medicine.getMedicineName()
                    );

                name.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #24234F;"
                );


                Label type =
                    new Label(
                        "Type: " +
                        medicine.getMedicineType()
                    );


                Label dosage =
                    new Label(
                        "Dosage: " +
                        medicine.getDosage()
                    );


                Label frequency =
                    new Label(
                        "Frequency: " +
                        medicine.getFrequency()
                    );


                Label time =
                    new Label(
                        "Time: " +
                        medicine.getTime()
                    );


                Label instruction =
                    new Label(
                        "Instruction: " +
                        medicine.getInstruction()
                    );


                type.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #666680;"
                );

                dosage.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #666680;"
                );

                frequency.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #666680;"
                );

                time.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #666680;"
                );

                instruction.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #666680;"
                );


                medicineBox.getChildren().addAll(
                    name,
                    type,
                    dosage,
                    frequency,
                    time,
                    instruction
                );


                content.getChildren().add(
                    medicineBox
                );
            }
        }


        ScrollPane scroll =
            new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setPrefHeight(450);

        scroll.setPrefWidth(480);

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );


        dialog.getDialogPane()
            .setContent(scroll);


        dialog.getDialogPane()
            .getButtonTypes()
            .add(
                new ButtonType(
                    "Close",
                    ButtonBar.ButtonData.CANCEL_CLOSE
                )
            );


        dialog.showAndWait();
    }


    // =========================================================
    // MEDICINE SAFETY CARD
    // =========================================================

    private VBox createMedicineSafetyCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #F8F1FF;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E4D4F3;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Medicine Safety",
                FontAwesomeIcon.HEART
            );


        VBox points =
            new VBox();

        points.setSpacing(8);


        points.getChildren().addAll(

            createSafetyPoint(
                "Take medicines only as prescribed."
            ),

            createSafetyPoint(
                "Do not change the dose without consulting your doctor."
            ),

            createSafetyPoint(
                "Keep medicines away from children."
            ),

            createSafetyPoint(
                "Inform your doctor about any unusual reaction."
            )
        );


        Button learnMore =
            createOutlineButton(
                "Learn More  →"
            );


        learnMore.setOnAction(
            e -> showMedicineSafetyDetails()
        );


        card.getChildren().addAll(
            heading,
            points,
            learnMore
        );


        return card;
    }


    // =========================================================
    // SAFETY POINT
    // =========================================================

    private HBox createSafetyPoint(
            String text) {

        HBox row =
            new HBox();

        row.setSpacing(8);

        row.setAlignment(
            Pos.TOP_LEFT
        );


        Label icon =
            new Label("✓");

        icon.setStyle(
            "-fx-text-fill: #7041A5;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );


        Label label =
            new Label(text);

        label.setWrapText(true);

        label.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #55556D;"
        );


        row.getChildren().addAll(
            icon,
            label
        );


        return row;
    }


    // =========================================================
    // MEDICINE SAFETY DETAILS
    // =========================================================

    private void showMedicineSafetyDetails() {

        Dialog<ButtonType> dialog =
            new Dialog<>();


        dialog.setTitle(
            "Medicine Safety"
        );

        dialog.setHeaderText(
            "Important Medicine Safety Guidelines"
        );


        VBox content =
            new VBox();

        content.setSpacing(12);

        content.setPadding(
            new Insets(15)
        );


        content.getChildren().addAll(

            createSafetyDetail(
                "1. Follow the prescription",
                "Take the medicine, dose and frequency exactly as prescribed by your healthcare provider."
            ),

            createSafetyDetail(
                "2. Do not change the dose yourself",
                "Do not increase, decrease or stop a prescribed medicine without consulting your healthcare provider."
            ),

            createSafetyDetail(
                "3. Follow the correct timing",
                "Try to take medicines at the prescribed time and follow instructions such as before or after food."
            ),

            createSafetyDetail(
                "4. Keep medicines safely",
                "Store medicines properly and keep them away from children."
            ),

            createSafetyDetail(
                "5. Report unusual reactions",
                "If you notice an unexpected reaction after taking a medicine, contact your healthcare provider."
            ),

            createSafetyDetail(
                "6. Keep your prescription updated",
                "Whenever your doctor changes a medicine, make sure the medicine reminder information is updated."
            )
        );


        ScrollPane scroll =
            new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setPrefWidth(480);

        scroll.setPrefHeight(420);

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );


        dialog.getDialogPane()
            .setContent(scroll);


        dialog.getDialogPane()
            .getButtonTypes()
            .add(
                new ButtonType(
                    "Close",
                    ButtonBar.ButtonData.CANCEL_CLOSE
                )
            );


        dialog.showAndWait();
    }


    // =========================================================
    // SAFETY DETAIL
    // =========================================================

    private VBox createSafetyDetail(
            String title,
            String description) {

        VBox box =
            new VBox();

        box.setSpacing(4);

        box.setPadding(
            new Insets(10)
        );

        box.setStyle(
            "-fx-background-color: #F8F1FF;" +
            "-fx-background-radius: 10;"
        );


        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label descriptionLabel =
            new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #55556D;"
        );


        box.getChildren().addAll(
            titleLabel,
            descriptionLabel
        );


        return box;
    }


    // =========================================================
    // ADD MEDICINE DIALOG
    // =========================================================

    private void showAddMedicineDialog() {

        Dialog<ButtonType> dialog =
            new Dialog<>();


        dialog.setTitle(
            "Add Medicine"
        );

        dialog.setHeaderText(
            "Add a new medicine to your schedule"
        );


        VBox form =
            new VBox();

        form.setSpacing(10);

        form.setPadding(
            new Insets(10)
        );


        Label nameLabel =
            createFormLabel("Medicine Name");

        TextField nameField =
            new TextField();

        nameField.setPromptText(
            "e.g. Iron + Folic Acid"
        );


        Label typeLabel =
            createFormLabel("Medicine Type");

        TextField typeField =
            new TextField();

        typeField.setPromptText(
            "e.g. Tablet / Capsule / Syrup"
        );


        Label periodLabel =
            createFormLabel("Period");

        ComboBox<String> periodBox =
            new ComboBox<>();

        periodBox.getItems().addAll(
            "Morning",
            "Afternoon",
            "Evening",
            "Night"
        );

        periodBox.setPromptText(
            "Select period"
        );

        periodBox.setMaxWidth(
            Double.MAX_VALUE
        );


        Label timeLabel =
            createFormLabel("Time");

        TextField timeField =
            new TextField();

        timeField.setPromptText(
            "e.g. 8:00 AM"
        );


        Label instructionLabel =
            createFormLabel("Instruction");

        TextField instructionField =
            new TextField();

        instructionField.setPromptText(
            "e.g. After Breakfast"
        );


        Label dosageLabel =
            createFormLabel("Dosage");

        TextField dosageField =
            new TextField();

        dosageField.setPromptText(
            "e.g. 1 Tablet"
        );


        Label frequencyLabel =
            createFormLabel("Frequency");

        TextField frequencyField =
            new TextField();

        frequencyField.setPromptText(
            "e.g. Once daily"
        );


        Label doctorLabel =
            createFormLabel("Prescribed By");

        TextField doctorField =
            new TextField();

        doctorField.setPromptText(
            "e.g. Dr. Priya Sharma"
        );


        Label dateLabel =
            createFormLabel("Prescribed Date");

        TextField dateField =
            new TextField();

        dateField.setPromptText(
            "e.g. 30 Aug 2026"
        );


        form.getChildren().addAll(

            nameLabel,
            nameField,

            typeLabel,
            typeField,

            periodLabel,
            periodBox,

            timeLabel,
            timeField,

            instructionLabel,
            instructionField,

            dosageLabel,
            dosageField,

            frequencyLabel,
            frequencyField,

            doctorLabel,
            doctorField,

            dateLabel,
            dateField
        );


        ScrollPane scroll =
            new ScrollPane(form);

        scroll.setFitToWidth(true);

        scroll.setPrefHeight(500);

        scroll.setPrefWidth(500);

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );


        dialog.getDialogPane()
            .setContent(scroll);


        ButtonType addButton =
            new ButtonType(
                "Add Medicine",
                ButtonBar.ButtonData.OK_DONE
            );


        ButtonType cancelButton =
            new ButtonType(
                "Cancel",
                ButtonBar.ButtonData.CANCEL_CLOSE
            );


        dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                addButton,
                cancelButton
            );


        dialog.setResultConverter(
            button -> {

                if (button == addButton) {

                    if (
                        nameField.getText()
                            .trim().isEmpty() ||

                        typeField.getText()
                            .trim().isEmpty() ||

                        periodBox.getValue() == null ||

                        timeField.getText()
                            .trim().isEmpty() ||

                        instructionField.getText()
                            .trim().isEmpty() ||

                        dosageField.getText()
                            .trim().isEmpty() ||

                        frequencyField.getText()
                            .trim().isEmpty() ||

                        doctorField.getText()
                            .trim().isEmpty() ||

                        dateField.getText()
                            .trim().isEmpty()
                    ) {

                        showMessage(
                            "Missing Information",
                            "Please fill all medicine details."
                        );

                        return null;
                    }


                    // =================================================
                    // CREATE MODEL
                    // =================================================

                    MedicineReminderModel newMedicine =
                        new MedicineReminderModel(

                            nameField.getText()
                                .trim(),

                            typeField.getText()
                                .trim(),

                            periodBox.getValue(),

                            timeField.getText()
                                .trim(),

                            instructionField.getText()
                                .trim(),

                            dosageField.getText()
                                .trim(),

                            frequencyField.getText()
                                .trim(),

                            doctorField.getText()
                                .trim(),

                            dateField.getText()
                                .trim(),

                            false,

                            true,

                            true,

                            true
                        );


                    // =================================================
                    // SET MOTHER ID
                    // =================================================

                    if (
                        motherModel == null ||
                        motherModel.getMotherId() == null ||
                        motherModel.getMotherId()
                            .trim().isEmpty()
                    ) {

                        showMessage(
                            "Error",
                            "Mother ID is not available."
                        );

                        return null;
                    }


                    newMedicine.setMotherId(
                        motherModel.getMotherId()
                    );


                    // =================================================
                    // SAVE TO FIREBASE
                    // =================================================

                    try {

                        boolean saved =
                            controller.saveMedicine(
                                newMedicine
                            );


                        if (!saved) {

                            showMessage(
                                "Save Failed",
                                "Unable to save medicine to Firebase."
                            );

                            return null;
                        }


                        // =============================================
                        // ADD ONLY AFTER FIREBASE SAVE SUCCESS
                        // =============================================

                        medicines.add(
                            newMedicine
                        );


                        refreshMedicineList();

                        refreshSummarySection();

                        refreshUpcomingReminders();

                        refreshPrescriptionList();


                        showMessage(
                            "Medicine Added",
                            newMedicine.getMedicineName() +
                            " has been added successfully."
                        );


                        return addButton;


                    } catch (Exception ex) {

                        ex.printStackTrace();

                        showMessage(
                            "Firebase Error",
                            "Unable to save medicine."
                        );

                        return null;
                    }
                }


                return null;
            }
        );


        dialog.showAndWait();
    }


    // =========================================================
    // FORM LABEL
    // =========================================================

    private Label createFormLabel(
            String text) {

        Label label =
            new Label(text);

        label.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        return label;
    }


    // =========================================================
    // MOTHER SETTINGS
    // =========================================================

    private void openMotherSettings() {

        // Mother Settings navigation नंतर add करू.
    }


    // =========================================================
    // CARD HEADING
    // =========================================================

    private HBox createCardHeading(
            String text,
            FontAwesomeIcon iconType) {

        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        heading.setSpacing(10);


        FontAwesomeIconView icon =
            new FontAwesomeIconView(
                iconType
            );

        icon.setSize("18");

        icon.setFill(
            Color.web(PURPLE)
        );


        Label title =
            new Label(text);

        title.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        heading.getChildren().addAll(
            icon,
            title
        );


        return heading;
    }


    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
            new VBox();

        card.setSpacing(12);

        card.setPadding(
            new Insets(18)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );


        return card;
    }


    // =========================================================
    // GRADIENT BUTTON
    // =========================================================

    private Button createGradientButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 9px 17px;"
        );


        return button;
    }


    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private Button createOutlineButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #7041A5;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #DCC9EC;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 8px 15px;"
        );


        return button;
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message) {

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