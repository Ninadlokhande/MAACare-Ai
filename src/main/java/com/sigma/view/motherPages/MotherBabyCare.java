package com.sigma.view.motherPages;

import com.sigma.model.BabyModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

// =============================================================
// BABY CARE PAGE
// =============================================================
// Baby data is stored in BabyModel.
// Profile can be filled/edited from UI.
// All changes are immediately stored in BabyModel.
//
// Later:
// Firebase -> BabyModel -> UI
//
// No Firebase code is used here.
// =============================================================

public class MotherBabyCare {

    // =========================================================
    // COLORS
    // =========================================================

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";
    private final String GREEN = "#3C9A68";
    private final String LIGHT_PINK = "#FFF5F9";
    private final String BORDER = "#E7DCE8";
    private final String GOLD = "#C7821B";

    // =========================================================
    // BABY MODEL
    // =========================================================

    private BabyModel baby;

    // =========================================================
    // UI REFERENCES
    // =========================================================
    // These are kept so the overview can refresh after profile save.
    // =========================================================

    private Label babyNameLabel;
    private Label babyAgeLabel;
    private Label babyGenderLabel;
    private Label careStageLabel;
    private Label careStageTextLabel;

    private Label currentWeightLabel;
    private Label currentHeightLabel;
    private Label headCircumferenceLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherBabyCare() {

        baby = new BabyModel();

        // Temporary sample data.
        // This can later come from Firebase.

        baby.setBabyId("BABY001");
        baby.setMotherId("MOTHER001");
        baby.setBabyName("My Baby");

        baby.setDateOfBirth(
                LocalDate.now().minusMonths(4)
        );

        baby.setGender("Not specified");
        baby.setBloodGroup("Not specified");

        baby.setBirthWeight(3.1);
        baby.setBirthHeight(50);

        baby.setCurrentWeight(6.4);
        baby.setCurrentHeight(63);
        baby.setHeadCircumference(41);

        // Sample growth history
        baby.addGrowthRecord(
                LocalDate.now().minusMonths(1),
                6.1,
                61.5,
                40.5
        );

        baby.addGrowthRecord(
                LocalDate.now(),
                6.4,
                63,
                41
        );
    }

    // =========================================================
    // CONSTRUCTOR WITH MODEL
    // =========================================================

    public MotherBabyCare(BabyModel baby) {

        this.baby = baby;

        if (this.baby == null) {
            this.baby = new BabyModel();
        }
    }

    // =========================================================
    // SET BABY
    // =========================================================

    public void setBaby(BabyModel baby) {

        if (baby != null) {

            this.baby = baby;

            refreshBabyOverview();
        }
    }

    // =========================================================
    // GET BABY
    // =========================================================

    public BabyModel getBaby() {

        return baby;
    }

    // =============================================================
    // MAIN PAGE
    // =============================================================

    public VBox createBabyCarePage() {

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

        content.setSpacing(22);

        content.setPadding(
            new Insets(
                25,
                30,
                40,
                30
            )
        );

        // =====================================================
        // PAGE TITLE
        // =====================================================

        VBox pageTitle = new VBox();

        pageTitle.setSpacing(5);

        Label title = new Label(
            "Baby Care 👶"
        );

        title.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle = new Label(
            "Everything you need for your baby's healthy growth and development."
        );

        subtitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #77778D;"
        );

        pageTitle.getChildren().addAll(
            title,
            subtitle
        );

        content.getChildren().add(pageTitle);

        // =====================================================
        // BABY OVERVIEW
        // =====================================================

        VBox overviewCard = createBabyOverviewCard();

        content.getChildren().add(
            overviewCard
        );

        // =====================================================
        // CARE CATEGORIES
        // =====================================================

        Label careTitle = createSectionTitle(
            "Baby Care Essentials"
        );

        GridPane careGrid = createCareGrid();

        content.getChildren().addAll(
            careTitle,
            careGrid
        );

        // =====================================================
        // GROWTH TRACKER
        // =====================================================

        VBox growthCard = createGrowthTrackerCard();

        content.getChildren().add(
            growthCard
        );

        // =====================================================
        // DEVELOPMENT
        // =====================================================

        VBox developmentCard = createDevelopmentCard();

        content.getChildren().add(
            developmentCard
        );

        // =====================================================
        // IMPORTANT TIPS
        // =====================================================

        VBox tipsCard = createImportantTipsCard();

        content.getChildren().add(
            tipsCard
        );

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        VBox quickActions = createQuickActions();

        content.getChildren().add(
            quickActions
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(content);

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

        page.getChildren().add(
            scrollPane
        );

        VBox.setVgrow(
            scrollPane,
            Priority.ALWAYS
        );

        return page;
    }

    // =============================================================
    // BABY OVERVIEW
    // =============================================================

    private VBox createBabyOverviewCard() {

        VBox card = createWhiteCard();

        HBox main = new HBox();

        main.setSpacing(20);

        main.setAlignment(
            Pos.CENTER_LEFT
        );

        // =====================================================
        // BABY ICON
        // =====================================================

        Label babyIcon = new Label("👶");

        babyIcon.setAlignment(
            Pos.CENTER
        );

        babyIcon.setPrefSize(
            90,
            90
        );

        babyIcon.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-background-radius: 50%;" +
            "-fx-font-size: 48px;"
        );

        // =====================================================
        // BABY INFORMATION
        // =====================================================

        VBox info = new VBox();

        info.setSpacing(6);

        babyNameLabel = new Label(
            displayBabyName()
        );

        babyNameLabel.setStyle(
            "-fx-font-size: 21px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        babyAgeLabel = new Label(
            "Age: " + baby.getFormattedAge()
        );

        babyAgeLabel.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );

        babyGenderLabel = new Label(
            "Gender: " +
            safeValue(baby.getGender())
        );

        babyGenderLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        Label bloodGroup = new Label(
            "Blood Group: " +
            safeValue(baby.getBloodGroup())
        );

        bloodGroup.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        Label message = new Label(
            "Keep track of feeding, sleep, growth " +
            "and your baby's development."
        );

        message.setWrapText(true);

        message.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        info.getChildren().addAll(
            babyNameLabel,
            babyAgeLabel,
            babyGenderLabel,
            bloodGroup,
            message
        );

        HBox.setHgrow(
            info,
            Priority.ALWAYS
        );

        // =====================================================
        // CARE STAGE
        // =====================================================

        VBox status = new VBox();

        status.setAlignment(
            Pos.CENTER
        );

        status.setSpacing(5);

        status.setPadding(
            new Insets(12)
        );

        status.setStyle(
            "-fx-background-color: #F3FFF8;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #D4EFDF;" +
            "-fx-border-radius: 14;"
        );

        Label statusTitle = new Label(
            "Care Stage"
        );

        statusTitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #35835B;"
        );

        careStageLabel = new Label(
            getCareStage()
        );

        careStageLabel.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #3C9A68;"
        );

        careStageTextLabel = new Label(
            getCareStageText()
        );

        careStageTextLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #666680;"
        );

        status.getChildren().addAll(
            statusTitle,
            careStageLabel,
            careStageTextLabel
        );

        // =====================================================
        // EDIT PROFILE BUTTON
        // =====================================================

        Button editProfile = createPinkButton(
            "✏ Edit / Fill Profile"
        );

        editProfile.setOnAction(
            e -> showBabyProfileDialog()
        );

        VBox right = new VBox();

        right.setAlignment(
            Pos.CENTER
        );

        right.setSpacing(12);

        right.getChildren().addAll(
            status,
            editProfile
        );

        main.getChildren().addAll(
            babyIcon,
            info,
            right
        );

        card.getChildren().add(main);

        return card;
    }

    // =============================================================
    // REFRESH OVERVIEW
    // =============================================================

    private void refreshBabyOverview() {

        if (babyNameLabel == null) {
            return;
        }

        babyNameLabel.setText(
            displayBabyName()
        );

        babyAgeLabel.setText(
            "Age: " + baby.getFormattedAge()
        );

        babyGenderLabel.setText(
            "Gender: " +
            safeValue(baby.getGender())
        );

        careStageLabel.setText(
            getCareStage()
        );

        careStageTextLabel.setText(
            getCareStageText()
        );

        if (currentWeightLabel != null) {

            currentWeightLabel.setText(
                formatNumber(
                    baby.getCurrentWeight()
                ) + " kg"
            );
        }

        if (currentHeightLabel != null) {

            currentHeightLabel.setText(
                formatNumber(
                    baby.getCurrentHeight()
                ) + " cm"
            );
        }

        if (headCircumferenceLabel != null) {

            headCircumferenceLabel.setText(
                formatNumber(
                    baby.getHeadCircumference()
                ) + " cm"
            );
        }
    }

    // =============================================================
    // BABY PROFILE DIALOG
    // =============================================================

    private void showBabyProfileDialog() {

        Dialog<ButtonType> dialog =
            new Dialog<>();

        dialog.setTitle(
            "Baby Profile"
        );

        dialog.initModality(
            Modality.APPLICATION_MODAL
        );

        // =====================================================
        // HEADER
        // =====================================================

        Label heading = new Label(
            "👶 Baby Profile"
        );

        heading.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Label subHeading = new Label(
            "Fill or edit your baby's information"
        );

        subHeading.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: white;"
        );

        VBox header = new VBox();

        header.setSpacing(3);

        header.setPadding(
            new Insets(18)
        );

        header.setStyle(
            "-fx-background-color: #E84A87;" +
            "-fx-background-radius: 14 14 0 0;"
        );

        header.getChildren().addAll(
            heading,
            subHeading
        );

        // =====================================================
        // FORM
        // =====================================================

        GridPane form = new GridPane();

        form.setHgap(12);

        form.setVgap(13);

        form.setPadding(
            new Insets(20)
        );

        TextField nameField =
            createTextField(
                baby.getBabyName()
            );

        DatePicker dobPicker =
            new DatePicker(
                baby.getDateOfBirth()
            );

        ComboBox<String> genderBox =
            new ComboBox<>();

        genderBox.getItems().addAll(
            "Male",
            "Female",
            "Other",
            "Not specified"
        );

        genderBox.setValue(
            safeValue(baby.getGender())
        );

        ComboBox<String> bloodBox =
            new ComboBox<>();

        bloodBox.getItems().addAll(
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-",
            "Not specified"
        );

        bloodBox.setValue(
            safeValue(baby.getBloodGroup())
        );

        TextField birthWeight =
            createTextField(
                String.valueOf(
                    baby.getBirthWeight()
                )
            );

        TextField birthHeight =
            createTextField(
                String.valueOf(
                    baby.getBirthHeight()
                )
            );

        TextField currentWeight =
            createTextField(
                String.valueOf(
                    baby.getCurrentWeight()
                )
            );

        TextField currentHeight =
            createTextField(
                String.valueOf(
                    baby.getCurrentHeight()
                )
            );

        TextField head =
            createTextField(
                String.valueOf(
                    baby.getHeadCircumference()
                )
            );

        addFormRow(
            form,
            0,
            "Baby Name",
            nameField
        );

        addFormRow(
            form,
            1,
            "Date of Birth",
            dobPicker
        );

        addFormRow(
            form,
            2,
            "Gender",
            genderBox
        );

        addFormRow(
            form,
            3,
            "Blood Group",
            bloodBox
        );

        addFormRow(
            form,
            4,
            "Birth Weight (kg)",
            birthWeight
        );

        addFormRow(
            form,
            5,
            "Birth Height (cm)",
            birthHeight
        );

        addFormRow(
            form,
            6,
            "Current Weight (kg)",
            currentWeight
        );

        addFormRow(
            form,
            7,
            "Current Height (cm)",
            currentHeight
        );

        addFormRow(
            form,
            8,
            "Head Circumference (cm)",
            head
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button save =
            createPinkButton(
                "Save Profile"
            );

        Button cancel =
            new Button(
                "Cancel"
            );

        cancel.setStyle(
            "-fx-background-color: #F5F1F5;" +
            "-fx-text-fill: #55556D;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 9px 18px;"
        );

        HBox buttons = new HBox();

        buttons.setSpacing(10);

        buttons.setAlignment(
            Pos.CENTER_RIGHT
        );

        buttons.setPadding(
            new Insets(
                0,
                20,
                20,
                20
            )
        );

        buttons.getChildren().addAll(
            cancel,
            save
        );

        VBox root = new VBox();

        root.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;"
        );

        root.getChildren().addAll(
            header,
            form,
            buttons
        );

        ScrollPane scroll =
            new ScrollPane(root);

        scroll.setFitToWidth(true);

        scroll.setPrefViewportHeight(620);

        scroll.setStyle(
            "-fx-background-color: white;"
        );

        save.setOnAction(e -> {

            try {

                if (nameField.getText()
                        .trim().isEmpty()) {

                    showPinkAlert(
                        "Profile",
                        "Please enter baby's name."
                    );

                    return;
                }

                if (dobPicker.getValue() == null) {

                    showPinkAlert(
                        "Profile",
                        "Please select baby's date of birth."
                    );

                    return;
                }

                if (dobPicker.getValue()
                        .isAfter(LocalDate.now())) {

                    showPinkAlert(
                        "Profile",
                        "Date of birth cannot be in the future."
                    );

                    return;
                }

                // =================================================
                // STORE DATA IN BABY MODEL
                // =================================================

                baby.setBabyName(
                    nameField.getText().trim()
                );

                baby.setDateOfBirth(
                    dobPicker.getValue()
                );

                baby.setGender(
                    genderBox.getValue()
                );

                baby.setBloodGroup(
                    bloodBox.getValue()
                );

                baby.setBirthWeight(
                    parseDouble(
                        birthWeight.getText()
                    )
                );

                baby.setBirthHeight(
                    parseDouble(
                        birthHeight.getText()
                    )
                );

                baby.setCurrentWeight(
                    parseDouble(
                        currentWeight.getText()
                    )
                );

                baby.setCurrentHeight(
                    parseDouble(
                        currentHeight.getText()
                    )
                );

                baby.setHeadCircumference(
                    parseDouble(
                        head.getText()
                    )
                );

                // =================================================
                // ADD CURRENT DATA TO GROWTH HISTORY
                // =================================================

                baby.addGrowthRecord(
                    LocalDate.now(),
                    baby.getCurrentWeight(),
                    baby.getCurrentHeight(),
                    baby.getHeadCircumference()
                );

                // =================================================
                // REFRESH PAGE
                // =================================================

                refreshBabyOverview();

                // Close dialog
                dialog.setResult(
                    ButtonType.OK
                );

                dialog.close();

                showPinkAlert(
                    "Profile Saved",
                    "Baby profile has been saved successfully.\n\n" +
                    "All information is now stored in BabyModel."
                );

            } catch (NumberFormatException ex) {

                showPinkAlert(
                    "Invalid Information",
                    "Please enter valid numbers for weight, height " +
                    "and head circumference."
                );
            }
        });

        cancel.setOnAction(
            e -> dialog.close()
        );

        dialog.getDialogPane().setContent(
            scroll
        );

        dialog.getDialogPane().setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;"
        );

        dialog.showAndWait();
    }

    // =============================================================
    // FORM ROW
    // =============================================================

    private void addFormRow(
            GridPane grid,
            int row,
            String labelText,
            Control control) {

        Label label =
            new Label(labelText);

        label.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        control.setPrefWidth(300);

        if (control instanceof TextInputControl) {

            control.setStyle(
                "-fx-background-color: #FFF7FA;" +
                "-fx-border-color: #E7C5D5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
            );
        }

        if (control instanceof DatePicker) {

            control.setStyle(
                "-fx-background-color: #FFF7FA;" +
                "-fx-border-color: #E7C5D5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
            );
        }

        if (control instanceof ComboBox) {

            control.setStyle(
                "-fx-background-color: #FFF7FA;" +
                "-fx-border-color: #E7C5D5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
            );
        }

        grid.add(
            label,
            0,
            row
        );

        grid.add(
            control,
            1,
            row
        );
    }

    // =============================================================
    // CARE GRID
    // =============================================================

    private GridPane createCareGrid() {

        GridPane grid =
            new GridPane();

        grid.setHgap(15);

        grid.setVgap(15);

        grid.add(
            createCareCard(
                "🍼",
                "Feeding Guide",
                "Age-wise feeding & nutrition",
                PINK,
                getFeedingDetails()
            ),
            0,
            0
        );

        grid.add(
            createCareCard(
                "😴",
                "Sleep & Routine",
                "Healthy sleep habits for baby",
                PURPLE,
                getSleepDetails()
            ),
            1,
            0
        );

        grid.add(
            createCareCard(
                "🧼",
                "Hygiene & Care",
                "Bathing, skin & hygiene tips",
                GREEN,
                getHygieneDetails()
            ),
            2,
            0
        );

        grid.add(
            createCareCard(
                "📈",
                "Growth Tracker",
                "Track weight & height",
                GOLD,
                getGrowthDetails()
            ),
            0,
            1
        );

        grid.add(
            createCareCard(
                "🧠",
                "Baby Development",
                "Milestones month by month",
                "#6C63C9",
                getDevelopmentMessage()
            ),
            1,
            1
        );

        grid.add(
            createCareCard(
                "❤️",
                "Mother & Baby Bond",
                "Build a healthy emotional bond",
                "#D14A78",
                getBondingDetails()
            ),
            2,
            1
        );

        return grid;
    }

    // =============================================================
    // CARE CARD
    // =============================================================

    private VBox createCareCard(
            String icon,
            String title,
            String description,
            String color,
            String details) {

        VBox card =
            new VBox();

        card.setSpacing(8);

        card.setPadding(
            new Insets(18)
        );

        card.setPrefWidth(260);

        card.setMinHeight(145);

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 16;"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 32px;"
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + color + ";"
        );

        Label descriptionLabel =
            new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        Button view =
            new Button(
                "View Details  →"
            );

        view.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: " + color + ";" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 3px 0;"
        );

        view.setOnAction(
            e -> showPinkAlert(
                title,
                details
            )
        );

        card.getChildren().addAll(
            iconLabel,
            titleLabel,
            descriptionLabel,
            view
        );

        return card;
    }

    // =============================================================
    // GROWTH TRACKER
    // =============================================================

    private VBox createGrowthTrackerCard() {

        VBox card =
            createWhiteCard();

        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        VBox titleBox =
            new VBox();

        titleBox.setSpacing(4);

        Label title =
            new Label(
                "Baby Growth Tracker"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Keep track of your baby's growth"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        titleBox.getChildren().addAll(
            title,
            subtitle
        );

        HBox.setHgrow(
            titleBox,
            Priority.ALWAYS
        );

        Button history =
            createOutlineButton(
                "View History  →"
            );

        history.setOnAction(
            e -> showGrowthHistory()
        );

        heading.getChildren().addAll(
            titleBox,
            history
        );

        // =====================================================
        // VALUES
        // =====================================================

        HBox values =
            new HBox();

        values.setSpacing(15);

        VBox weight =
            createGrowthBox(
                "⚖",
                "Weight",
                formatNumber(
                    baby.getCurrentWeight()
                ) + " kg",
                "Current record"
            );

        VBox height =
            createGrowthBox(
                "📏",
                "Height",
                formatNumber(
                    baby.getCurrentHeight()
                ) + " cm",
                "Current record"
            );

        VBox head =
            createGrowthBox(
                "🧠",
                "Head Circumference",
                formatNumber(
                    baby.getHeadCircumference()
                ) + " cm",
                "Current record"
            );

        currentWeightLabel =
            (Label) weight.getProperties()
                .get("valueLabel");

        currentHeightLabel =
            (Label) height.getProperties()
                .get("valueLabel");

        headCircumferenceLabel =
            (Label) head.getProperties()
                .get("valueLabel");

        values.getChildren().addAll(
            weight,
            height,
            head
        );

        // =====================================================
        // TIP
        // =====================================================

        HBox tip =
            new HBox();

        tip.setSpacing(10);

        tip.setAlignment(
            Pos.CENTER_LEFT
        );

        tip.setPadding(
            new Insets(12)
        );

        tip.setStyle(
            "-fx-background-color: #FFF7FA;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F3D8E3;" +
            "-fx-border-radius: 12;"
        );

        Label tipIcon =
            new Label("💡");

        tipIcon.setStyle(
            "-fx-font-size: 20px;"
        );

        Label tipText =
            new Label(
                "Tip: Record your baby's weight and height regularly " +
                "and discuss unusual changes with your doctor."
            );

        tipText.setWrapText(true);

        tipText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        tip.getChildren().addAll(
            tipIcon,
            tipText
        );

        card.getChildren().addAll(
            heading,
            values,
            tip
        );

        return card;
    }

    // =============================================================
    // GROWTH HISTORY
    // =============================================================

    private void showGrowthHistory() {

        StringBuilder history =
            new StringBuilder();

        history.append(
            "Growth records for "
        );

        history.append(
            displayBabyName()
        );

        history.append(
            "\n\n"
        );

        if (baby.getGrowthHistory() == null ||
            baby.getGrowthHistory().isEmpty()) {

            history.append(
                "No growth records available."
            );

        } else {

            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                    "dd MMM yyyy"
                );

            for (
                BabyModel.GrowthRecord record
                : baby.getGrowthHistory()
            ) {

                history.append(
                    "📅 "
                );

                history.append(
                    record.getDate() != null
                        ? record.getDate().format(formatter)
                        : "Date not available"
                );

                history.append(
                    "\n"
                );

                history.append(
                    "⚖ Weight: "
                );

                history.append(
                    formatNumber(
                        record.getWeight()
                    )
                );

                history.append(
                    " kg"
                );

                history.append(
                    "\n📏 Height: "
                );

                history.append(
                    formatNumber(
                        record.getHeight()
                    )
                );

                history.append(
                    " cm"
                );

                history.append(
                    "\n🧠 Head: "
                );

                history.append(
                    formatNumber(
                        record.getHeadCircumference()
                    )
                );

                history.append(
                    " cm"
                );

                history.append(
                    "\n\n"
                );
            }
        }

        showPinkAlert(
            "Growth History",
            history.toString()
        );
    }

    // =============================================================
    // GROWTH BOX
    // =============================================================

    private VBox createGrowthBox(
            String icon,
            String title,
            String value,
            String status) {

        VBox box =
            new VBox();

        box.setSpacing(6);

        box.setAlignment(
            Pos.CENTER_LEFT
        );

        box.setPadding(
            new Insets(15)
        );

        box.setPrefWidth(220);

        box.setStyle(
            "-fx-background-color: #FAF8FF;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #E5D8F1;" +
            "-fx-border-radius: 14;"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 25px;"
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        Label valueLabel =
            new Label(value);

        valueLabel.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label statusLabel =
            new Label(status);

        statusLabel.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #3C9A68;" +
            "-fx-font-weight: bold;"
        );

        // Store reference for refresh
        box.getProperties().put(
            "valueLabel",
            valueLabel
        );

        box.getChildren().addAll(
            iconLabel,
            titleLabel,
            valueLabel,
            statusLabel
        );

        return box;
    }

    // =============================================================
    // DEVELOPMENT CARD
    // =============================================================

    private VBox createDevelopmentCard() {

        VBox card =
            createWhiteCard();

        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        VBox titleBox =
            new VBox();

        titleBox.setSpacing(4);

        Label title =
            new Label(
                "Baby Development Milestones"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "See what your baby may learn at each stage"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        titleBox.getChildren().addAll(
            title,
            subtitle
        );

        HBox.setHgrow(
            titleBox,
            Priority.ALWAYS
        );

        Button viewAll =
            createOutlineButton(
                "View All Milestones  →"
            );

        viewAll.setOnAction(
            e -> showPinkAlert(
                "Baby Development",
                getDevelopmentMessage()
            )
        );

        heading.getChildren().addAll(
            titleBox,
            viewAll
        );

        HBox milestones =
            new HBox();

        milestones.setSpacing(12);

        milestones.getChildren().addAll(

            createMilestone(
                "1–3 Months",
                "👀",
                "Looks at faces",
                "Begins smiling"
            ),

            createMilestone(
                "4–6 Months",
                "😊",
                "Recognizes familiar people",
                "Reaches for objects"
            ),

            createMilestone(
                "7–9 Months",
                "🧸",
                "Sits with support",
                "Responds to sounds"
            ),

            createMilestone(
                "10–12 Months",
                "👣",
                "Pulls to stand",
                "Uses simple sounds"
            )
        );

        card.getChildren().addAll(
            heading,
            milestones
        );

        return card;
    }

    // =============================================================
    // DEVELOPMENT MESSAGE
    // =============================================================

    private String getDevelopmentMessage() {

        int age =
            baby.getAgeInMonths();

        if (age <= 3) {

            return
                "Your baby is in the 1–3 month stage.\n\n" +
                "Common areas of development include:\n\n" +
                "✓ Looking at faces\n" +
                "✓ Responding to sounds\n" +
                "✓ Beginning to smile\n\n" +
                "Every baby develops at their own pace.";

        } else if (age <= 6) {

            return
                "Your baby is in the 4–6 month stage.\n\n" +
                "Common areas of development include:\n\n" +
                "✓ Recognizing familiar people\n" +
                "✓ Reaching for objects\n" +
                "✓ Increasing interaction\n\n" +
                "Every baby develops at their own pace.";

        } else if (age <= 9) {

            return
                "Your baby is in the 7–9 month stage.\n\n" +
                "Common areas of development include:\n\n" +
                "✓ Sitting with support\n" +
                "✓ Responding to sounds\n" +
                "✓ Exploring objects\n\n" +
                "Every baby develops at their own pace.";

        } else if (age <= 12) {

            return
                "Your baby is in the 10–12 month stage.\n\n" +
                "Common areas of development include:\n\n" +
                "✓ Pulling to stand\n" +
                "✓ Using simple sounds\n" +
                "✓ Exploring surroundings\n\n" +
                "Every baby develops at their own pace.";
        }

        return
            "Your baby has completed the first year.\n\n" +
            "The current Baby Care module focuses on the first 12 months.";
    }

    // =============================================================
    // MILESTONE
    // =============================================================

    private VBox createMilestone(
            String age,
            String icon,
            String point1,
            String point2) {

        VBox box =
            new VBox();

        box.setSpacing(7);

        box.setPadding(
            new Insets(14)
        );

        box.setPrefWidth(210);

        box.setMinHeight(135);

        box.setStyle(
            "-fx-background-color: #FFF9FC;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #F1DDE7;" +
            "-fx-border-radius: 14;"
        );

        Label ageLabel =
            new Label(age);

        ageLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 25px;"
        );

        Label first =
            new Label(
                "✓ " + point1
            );

        first.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #55556D;"
        );

        Label second =
            new Label(
                "✓ " + point2
            );

        second.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #55556D;"
        );

        Button details =
            new Button(
                "View Details"
            );

        details.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #E84A87;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );

        details.setOnAction(
            e -> showPinkAlert(
                age,
                "Development focus:\n\n" +
                "✓ " + point1 + "\n" +
                "✓ " + point2 +
                "\n\nEvery baby develops at their own pace."
            )
        );

        box.getChildren().addAll(
            ageLabel,
            iconLabel,
            first,
            second,
            details
        );

        return box;
    }

    // =============================================================
    // IMPORTANT TIPS
    // =============================================================

    private VBox createImportantTipsCard() {

        VBox card =
            createWhiteCard();

        card.setStyle(
            "-fx-background-color: #FFFDF4;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F1E7BE;" +
            "-fx-border-radius: 18;"
        );

        Label title =
            new Label(
                "Important Baby Care Tips"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #C7821B;"
        );

        GridPane tips =
            new GridPane();

        tips.setHgap(30);

        tips.setVgap(12);

        tips.add(
            createTip(
                "Wash your hands before handling your baby."
            ),
            0,
            0
        );

        tips.add(
            createTip(
                "Follow safe sleeping practices."
            ),
            1,
            0
        );

        tips.add(
            createTip(
                "Keep feeding bottles and utensils clean."
            ),
            0,
            1
        );

        tips.add(
            createTip(
                "Attend regular pediatric check-ups."
            ),
            1,
            1
        );

        tips.add(
            createTip(
                "Keep vaccinations up to date."
            ),
            0,
            2
        );

        tips.add(
            createTip(
                "Give your baby plenty of love and attention."
            ),
            1,
            2
        );

        card.getChildren().addAll(
            title,
            tips
        );

        return card;
    }

    // =============================================================
    // QUICK ACTIONS
    // =============================================================

    private VBox createQuickActions() {

        VBox card =
            createWhiteCard();

        Label title =
            new Label(
                "Quick Actions"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        HBox actions =
            new HBox();

        actions.setSpacing(15);

        Button vaccination =
            createActionButton(
                "💉",
                "Vaccination",
                PINK
            );

        Button medicine =
            createActionButton(
                "💊",
                "Medicine Reminder",
                PURPLE
            );

        Button doctor =
            createActionButton(
                "👨‍⚕️",
                "Consult Doctor",
                GREEN
            );

        Button appointment =
            createActionButton(
                "📅",
                "Book Appointment",
                GOLD
            );

        vaccination.setOnAction(
            e -> showPinkAlert(
                "Vaccination",
                getVaccinationDetails()
            )
        );

        medicine.setOnAction(
            e -> showPinkAlert(
                "Medicine Reminder",
                "Medicine reminders can be added here.\n\n" +
                "Always follow the dosage and instructions " +
                "provided by your healthcare professional."
            )
        );

        doctor.setOnAction(
            e -> showPinkAlert(
                "Consult Doctor",
                "Doctor consultation options can be connected here.\n\n" +
                "You can later connect this button to your Doctor module."
            )
        );

        appointment.setOnAction(
            e -> showPinkAlert(
                "Book Appointment",
                "Choose a doctor or hospital to book an appointment.\n\n" +
                "This can later be connected to the appointment module."
            )
        );

        actions.getChildren().addAll(
            vaccination,
            medicine,
            doctor,
            appointment
        );

        card.getChildren().addAll(
            title,
            actions
        );

        return card;
    }

    // =============================================================
    // ACTION BUTTON
    // =============================================================

    private Button createActionButton(
            String icon,
            String text,
            String color) {

        Button button =
            new Button();

        button.setPrefWidth(220);

        button.setPrefHeight(65);

        VBox content =
            new VBox();

        content.setAlignment(
            Pos.CENTER
        );

        content.setSpacing(4);

        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 23px;"
        );

        Label textLabel =
            new Label(text);

        textLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + color + ";"
        );

        content.getChildren().addAll(
            iconLabel,
            textLabel
        );

        button.setGraphic(
            content
        );

        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + color + ";" +
            "-fx-border-radius: 12;" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 8px;"
        );

        return button;
    }

    // =============================================================
    // COMMON TIP
    // =============================================================

    private HBox createTip(
            String text) {

        HBox box =
            new HBox();

        box.setSpacing(8);

        box.setAlignment(
            Pos.CENTER_LEFT
        );

        Label icon =
            new Label("✓");

        icon.setStyle(
            "-fx-text-fill: #3C9A68;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        Label label =
            new Label(text);

        label.setWrapText(true);

        label.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #55556D;"
        );

        box.getChildren().addAll(
            icon,
            label
        );

        return box;
    }

    // =============================================================
    // SECTION TITLE
    // =============================================================

    private Label createSectionTitle(
            String text) {

        Label title =
            new Label(text);

        title.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        return title;
    }

    // =============================================================
    // WHITE CARD
    // =============================================================

    private VBox createWhiteCard() {

        VBox card =
            new VBox();

        card.setSpacing(14);

        card.setPadding(
            new Insets(20)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );

        return card;
    }

    // =============================================================
    // OUTLINE BUTTON
    // =============================================================

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

    // =============================================================
    // PINK BUTTON
    // =============================================================

    private Button createPinkButton(
            String text) {

        Button button =
            new Button(text);

        button.setStyle(
            "-fx-background-color: #E84A87;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 9px 18px;"
        );

        return button;
    }

    // =============================================================
    // PINK INFORMATION POPUP
    // =============================================================

    private void showPinkAlert(
            String title,
            String message) {

        Stage stage =
            new Stage();

        stage.initModality(
            Modality.APPLICATION_MODAL
        );

        stage.setTitle(title);

        // =====================================================
        // HEADER
        // =====================================================

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Label icon =
            new Label("💗");

        icon.setStyle(
            "-fx-font-size: 25px;"
        );

        HBox header =
            new HBox();

        header.setSpacing(10);

        header.setAlignment(
            Pos.CENTER_LEFT
        );

        header.setPadding(
            new Insets(18)
        );

        header.setStyle(
            "-fx-background-color: #E84A87;" +
            "-fx-background-radius: 15 15 0 0;"
        );

        header.getChildren().addAll(
            icon,
            titleLabel
        );

        // =====================================================
        // MESSAGE
        // =====================================================

        Label messageLabel =
            new Label(message);

        messageLabel.setWrapText(true);

        messageLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #55556D;" +
            "-fx-line-spacing: 4px;"
        );

        ScrollPane scroll =
            new ScrollPane(
                messageLabel
            );

        scroll.setFitToWidth(true);

        scroll.setPrefViewportHeight(280);

        scroll.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: transparent;"
        );

        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        Button close =
            createPinkButton(
                "Close"
            );

        close.setOnAction(
            e -> stage.close()
        );

        HBox buttonBox =
            new HBox(close);

        buttonBox.setAlignment(
            Pos.CENTER_RIGHT
        );

        buttonBox.setPadding(
            new Insets(
                10,
                18,
                18,
                18
            )
        );

        VBox root =
            new VBox();

        root.setPrefWidth(480);

        root.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15;" +
            "-fx-border-color: #F2B8CF;" +
            "-fx-border-radius: 15;"
        );

        root.getChildren().addAll(
            header,
            scroll,
            buttonBox
        );

        Scene scene =
            new Scene(root);

        stage.setScene(scene);

        stage.setResizable(false);

        stage.showAndWait();
    }

    // =============================================================
    // TEXT FIELD
    // =============================================================

    private TextField createTextField(
            String value) {

        TextField field =
            new TextField();

        field.setText(
            value == null
                ? ""
                : value
        );

        return field;
    }

    // =============================================================
    // DETAILS
    // =============================================================

    private String getFeedingDetails() {

        return
            "Feeding Guide\n\n" +
            "Your baby's feeding needs change as they grow.\n\n" +
            "✓ Follow age-appropriate feeding guidance.\n" +
            "✓ Keep feeding utensils clean.\n" +
            "✓ Observe baby's hunger and fullness cues.\n" +
            "✓ For babies under 6 months, feeding guidance " +
            "should be followed according to healthcare advice.\n\n" +
            "Always consult a pediatrician for individual feeding advice.";
    }

    private String getSleepDetails() {

        return
            "Sleep & Routine\n\n" +
            "A consistent routine can help your baby feel comfortable.\n\n" +
            "✓ Keep a regular bedtime routine.\n" +
            "✓ Keep the sleep environment calm.\n" +
            "✓ Follow safe-sleep recommendations.\n" +
            "✓ Keep track of sleep patterns.\n\n" +
            "If you have concerns about your baby's sleep, " +
            "discuss them with a pediatrician.";
    }

    private String getHygieneDetails() {

        return
            "Hygiene & Care\n\n" +
            "✓ Wash hands before handling your baby.\n" +
            "✓ Keep feeding items clean.\n" +
            "✓ Keep baby's skin clean and dry.\n" +
            "✓ Change diapers regularly.\n" +
            "✓ Keep the baby's surroundings clean.\n\n" +
            "For persistent skin problems or other concerns, " +
            "consult a healthcare professional.";
    }

    private String getGrowthDetails() {

        return
            "Growth Tracker\n\n" +
            "Current information:\n\n" +
            "👶 Name: " +
            displayBabyName() +
            "\n" +
            "🎂 Age: " +
            baby.getFormattedAge() +
            "\n" +
            "⚖ Weight: " +
            formatNumber(
                baby.getCurrentWeight()
            ) +
            " kg\n" +
            "📏 Height: " +
            formatNumber(
                baby.getCurrentHeight()
            ) +
            " cm\n" +
            "🧠 Head Circumference: " +
            formatNumber(
                baby.getHeadCircumference()
            ) +
            " cm\n\n" +
            "Growth records are stored in BabyModel.";
    }

    private String getBondingDetails() {

        return
            "Mother & Baby Bond\n\n" +
            "✓ Spend time talking and interacting with your baby.\n" +
            "✓ Respond to your baby's sounds and expressions.\n" +
            "✓ Use gentle play appropriate for your baby's age.\n" +
            "✓ Create a calm and loving routine.\n\n" +
            "Positive interaction helps support your baby's development.";
    }

    private String getVaccinationDetails() {

        return
            "Vaccination\n\n" +
            "Vaccination schedules depend on the baby's age " +
            "and local healthcare recommendations.\n\n" +
            "✓ Keep vaccination records updated.\n" +
            "✓ Follow the schedule recommended by your pediatrician.\n" +
            "✓ Keep your baby's vaccination card safely.\n\n" +
            "The vaccination module can later be connected to Firebase.";
    }

    // =============================================================
    // CARE STAGE
    // =============================================================

    private String getCareStage() {

        int months =
            baby.getAgeInMonths();

        if (months <= 3) {
            return "0–3";
        }

        if (months <= 6) {
            return "4–6";
        }

        if (months <= 9) {
            return "7–9";
        }

        if (months <= 12) {
            return "10–12";
        }

        return "12+";
    }

    private String getCareStageText() {

        return baby.isUnderOneYear()
            ? "Months"
            : "First year completed";
    }

    // =============================================================
    // SAFE VALUE
    // =============================================================

    private String safeValue(
            String value) {

        if (value == null ||
            value.trim().isEmpty()) {

            return "Not specified";
        }

        return value;
    }

    // =============================================================
    // DISPLAY BABY NAME
    // =============================================================

    private String displayBabyName() {

        if (baby == null ||
            baby.getBabyName() == null ||
            baby.getBabyName().trim().isEmpty()) {

            return "My Baby";
        }

        return baby.getBabyName();
    }

    // =============================================================
    // NUMBER FORMAT
    // =============================================================

    private String formatNumber(
            double value) {

        if (value == (long) value) {

            return String.valueOf(
                (long) value
            );
        }

        return String.format(
            "%.1f",
            value
        );
    }

    // =============================================================
    // PARSE NUMBER
    // =============================================================

    private double parseDouble(
            String text) {

        if (text == null ||
            text.trim().isEmpty()) {

            return 0;
        }

        return Double.parseDouble(
            text.trim()
        );
    }
}