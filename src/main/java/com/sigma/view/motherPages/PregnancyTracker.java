package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.sigma.model.MotherWlcModel;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;


// =============================================================
// PREGNANCY TRACKING PAGE
// =============================================================

public class PregnancyTracker {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";
    private final String GREEN = "#43A66A";
    private final String LIGHT_PINK = "#FFF3F8";
    private final String LIGHT_PURPLE = "#F7F1FF";

    // =========================================================
    // MOTHER MODEL
    // =========================================================

    private MotherWlcModel motherModel;


    // =========================================================
    // CURRENT WEEK
    // =========================================================

    private int currentWeek = 20;


    // =========================================================
    // UI COMPONENTS
    // =========================================================

    private VBox motherDetailsBox;
    private VBox babyDetailsBox;

    private Label weekTitle;
    private Label weekSubtitle;

    private Label progressWeekLabel;

    private StackPane journeyImageBox;
    private StackPane motherImageBox;
    private StackPane babyImageBox;
    private StackPane milestoneImageBox;

    private ComboBox<Integer> weekCombo;

    private LineChart<String, Number> babyDevelopmentChart;

    // Dynamic UI elements
    private Label journeySubtitleLabel;
    private Label trimesterLabel;
    private Label trimesterDescriptionLabel;
    private ProgressBar pregnancyProgressBar;
    private Label motherWeekHint;
    private Label babyWeekHint;
    private Label milestoneWeekLabel;
    private Label milestoneLabel;
    private Label milestoneDevelopmentLabel;


    // =========================================================
    // WEEK DATA
    // =========================================================

    private final Map<Integer, String[]> weekData =
            new HashMap<>();


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public PregnancyTracker() {

        loadWeekData();

        currentWeek = 20;
    }


    // =========================================================
    // MODEL CONSTRUCTOR
    // =========================================================

    public PregnancyTracker(
            MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        loadWeekData();

        calculateCurrentWeekFromLMP();
    }


    // =========================================================
    // SET MODEL
    // =========================================================

    public void setMotherModel(
            MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        calculateCurrentWeekFromLMP();

        if (weekCombo != null) {

            weekCombo.setValue(currentWeek);
        }

        updateWeekContent();
    }


    // =========================================================
    // CALCULATE CURRENT WEEK FROM LMP
    // =========================================================

    private void calculateCurrentWeekFromLMP() {

        if (motherModel == null) {

            currentWeek = 20;

            return;
        }


        LocalDate lmpDate =
                motherModel.getLmpDate();


        if (lmpDate == null) {

            currentWeek = 20;

            return;
        }


        LocalDate today =
                LocalDate.now();


        if (lmpDate.isAfter(today)) {

            currentWeek = 1;

            return;
        }


        long days =
                ChronoUnit.DAYS.between(
                        lmpDate,
                        today
                );


        int calculatedWeek =
                (int) (days / 7) + 1;


        if (calculatedWeek < 1) {

            calculatedWeek = 1;
        }


        if (calculatedWeek > 40) {

            calculatedWeek = 40;
        }


        currentWeek =
                calculatedWeek;
    }


    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createPregnancyTrackingPage() {

        // Always calculate the latest week from the mother's LMP
        // before creating the UI. This ensures the page does not
        // remain on the default Week 20 when an LMP is available.
        calculateCurrentWeekFromLMP();

        VBox page =
                new VBox();

        page.setFillWidth(true);

        page.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to bottom right, " +
                "#FFFFFF 0%, " +
                "#FFF7FB 55%, " +
                "#F5EEFF 100%);"
        );


        VBox content =
                new VBox();

        content.setSpacing(20);

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

        content.getChildren().add(
                createPageTitle()
        );


        // =====================================================
        // WEEK SELECTOR
        // =====================================================

        content.getChildren().add(
                createWeekSelector()
        );


        // =====================================================
        // PREGNANCY JOURNEY
        // =====================================================

        content.getChildren().add(
                createPregnancyProgressCard()
        );


        // =====================================================
        // CURRENT WEEK
        // =====================================================

        content.getChildren().add(
                createCurrentWeekCard()
        );


        // =====================================================
        // MOTHER + BABY
        // =====================================================

        HBox detailsLayout =
                new HBox();

        detailsLayout.setSpacing(20);


        VBox motherCard =
                createMotherChangesCard();


        VBox babyCard =
                createBabyDevelopmentCard();


        HBox.setHgrow(
                motherCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                babyCard,
                Priority.ALWAYS
        );


        detailsLayout.getChildren().addAll(
                motherCard,
                babyCard
        );


        content.getChildren().add(
                detailsLayout
        );


        // =====================================================
        // WEEKLY MILESTONES
        // =====================================================

        content.getChildren().add(
                createMilestoneCard()
        );


        // =====================================================
        // CARE TIPS
        // =====================================================

        content.getChildren().add(
                createTipsCard()
        );


        // =====================================================
        // NAVIGATION
        // =====================================================

        content.getChildren().add(
                createWeekNavigation()
        );


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


        page.getChildren().add(
                scrollPane
        );


        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );


        return page;
    }


    // =========================================================
    // PAGE TITLE
    // =========================================================

    private VBox createPageTitle() {

        VBox box =
                new VBox();

        box.setSpacing(5);


        Label title =
                new Label(
                        "Pregnancy Tracking 🤰"
                );

        title.setStyle(
                "-fx-font-size: 29px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        Label subtitle =
                new Label(
                        "Follow your pregnancy journey and " +
                        "your baby's development week by week."
                );

        subtitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #77778D;"
        );


        box.getChildren().addAll(
                title,
                subtitle
        );


        return box;
    }


    // =========================================================
    // WEEK SELECTOR
    // =========================================================

    private HBox createWeekSelector() {

        HBox card =
                new HBox();

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setSpacing(15);

        card.setPadding(
                new Insets(16)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #E7DCE8;" +
                "-fx-border-radius: 16;"
        );


        FontAwesomeIconView calendarIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.CALENDAR
                );

        calendarIcon.setSize("22");

        calendarIcon.setFill(
                Color.web(PINK)
        );


        Label label =
                new Label(
                        "Select Pregnancy Week"
                );

        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        weekCombo =
                new ComboBox<>();


        for (int i = 1; i <= 40; i++) {

            weekCombo.getItems().add(i);
        }


        weekCombo.setValue(
                currentWeek
        );


        weekCombo.setPrefWidth(130);


        weekCombo.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-background-color: #FFF5F9;" +
                "-fx-border-color: #F0C6D8;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );


        Label weekLabel =
                new Label("Week");


        weekLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;"
        );


        weekCombo.setOnAction(e -> {

            if (weekCombo.getValue() != null) {

                currentWeek =
                        weekCombo.getValue();

                updateWeekContent();
            }
        });


        card.getChildren().addAll(
                calendarIcon,
                label,
                weekCombo,
                weekLabel
        );


        return card;
    }


    // =========================================================
    // PREGNANCY JOURNEY
    // =========================================================

    private VBox createPregnancyProgressCard() {

        VBox card =
                createWhiteCard();

        card.setSpacing(15);


        // =====================================================
        // HEADING
        // =====================================================

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
                        "Your Pregnancy Journey 🌸"
                );

        title.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        journeySubtitleLabel =
                new Label(
                        getTrimesterName(currentWeek) +
                        " • Week " + currentWeek + " of 40"
                );

        journeySubtitleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;"
        );


        titleBox.getChildren().addAll(
                title,
                journeySubtitleLabel
        );


        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        progressWeekLabel =
                new Label(
                        currentWeek + " / 40"
                );


        progressWeekLabel.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E84A87;"
        );


        heading.getChildren().addAll(
                titleBox,
                progressWeekLabel
        );


        // =====================================================
        // IMAGE + INFO
        // =====================================================

        HBox journey =
                new HBox();

        journey.setSpacing(20);

        journey.setAlignment(
                Pos.CENTER_LEFT
        );


        journeyImageBox =
                createTrimesterImage(
                        currentWeek,
                        300,
                        170
                );


        VBox info =
                new VBox();

        info.setSpacing(10);


        trimesterLabel =
                new Label(
                        getTrimesterName(currentWeek)
                );


        trimesterLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #7041A5;"
        );


        trimesterDescriptionLabel =
                new Label(
                        getTrimesterDescription(currentWeek)
                );


        trimesterDescriptionLabel.setWrapText(true);


        trimesterDescriptionLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #55556D;" +
                "-fx-line-spacing: 5px;"
        );


        HBox.setHgrow(
                info,
                Priority.ALWAYS
        );


        info.getChildren().addAll(
                trimesterLabel,
                trimesterDescriptionLabel
        );


        journey.getChildren().addAll(
                journeyImageBox,
                info
        );


        // =====================================================
        // PROGRESS BAR
        // =====================================================

        pregnancyProgressBar =
                new ProgressBar(
                        currentWeek / 40.0
                );


        pregnancyProgressBar.setMaxWidth(
                Double.MAX_VALUE
        );


        pregnancyProgressBar.setPrefHeight(14);


        pregnancyProgressBar.setStyle(
                "-fx-accent: #E84A87;"
        );


        HBox.setHgrow(
                pregnancyProgressBar,
                Priority.ALWAYS
        );


        HBox labels =
                new HBox();


        labels.setAlignment(
                Pos.CENTER_LEFT
        );


        Label start =
                new Label("Week 1");


        start.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        Label end =
                new Label("Week 40");


        end.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;"
        );


        HBox.setHgrow(
                start,
                Priority.ALWAYS
        );


        labels.getChildren().addAll(
                start,
                end
        );


        card.getChildren().addAll(
                heading,
                journey,
                pregnancyProgressBar,
                labels
        );


        return card;
    }


    // =========================================================
    // CURRENT WEEK CARD
    // =========================================================

    private VBox createCurrentWeekCard() {

        VBox card =
                new VBox();

        card.setSpacing(12);

        card.setPadding(
                new Insets(20)
        );


        card.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right, #FFF0F6, #F7F0FF);" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #EBD5E5;" +
                "-fx-border-radius: 18;"
        );


        HBox top =
                new HBox();


        top.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox text =
                new VBox();

        text.setSpacing(5);


        weekTitle =
                new Label(
                        "Week " + currentWeek
                );


        weekTitle.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        weekSubtitle =
                new Label(
                        getWeekSubtitle(currentWeek)
                );


        weekSubtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #77778D;"
        );


        text.getChildren().addAll(
                weekTitle,
                weekSubtitle
        );


        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );


        VBox babyIcon =
                createIconCircle(
                        FontAwesomeIcon.HEART,
                        PURPLE
                );


        top.getChildren().addAll(
                text,
                babyIcon
        );


        card.getChildren().add(
                top
        );


        return card;
    }


    // =========================================================
    // MOTHER CHANGES CARD
    // =========================================================

    private VBox createMotherChangesCard() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createHeading(
                        "Mother Changes",
                        FontAwesomeIcon.HEART,
                        PINK
                );


        motherWeekHint =
                new Label(
                        "How your body may change during Week "
                        + currentWeek
                );


        motherWeekHint.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;"
        );


        motherImageBox =
                createTrimesterImage(
                        currentWeek,
                        250,
                        150
                );


        motherDetailsBox =
                new VBox();


        motherDetailsBox.setSpacing(12);


        updateMotherDetails();


        card.getChildren().addAll(
                heading,
                motherWeekHint,
                motherImageBox,
                motherDetailsBox
        );


        return card;
    }


    // =========================================================
    // BABY DEVELOPMENT CARD
    // =========================================================

    private VBox createBabyDevelopmentCard() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createHeading(
                        "Baby Development",
                        FontAwesomeIcon.CHILD,
                        PURPLE
                );


        babyWeekHint =
                new Label(
                        "Your baby's growth during Week "
                        + currentWeek
                );


        babyWeekHint.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77778D;"
        );


        // =====================================================
        // LINE CHART
        // =====================================================

        babyDevelopmentChart =
                createBabyDevelopmentChart(
                        currentWeek
                );


        babyDetailsBox =
                new VBox();


        babyDetailsBox.setSpacing(12);


        updateBabyDetails();


        card.getChildren().addAll(
                heading,
                babyWeekHint,
                babyDevelopmentChart,
                babyDetailsBox
        );


        return card;
    }


    // =========================================================
    // BABY DEVELOPMENT LINE CHART
    // =========================================================

    private LineChart<String, Number>
            createBabyDevelopmentChart(
                    int week) {


        CategoryAxis xAxis =
                new CategoryAxis();


        NumberAxis yAxis =
                new NumberAxis();


        xAxis.setLabel(
                "Pregnancy Week"
        );


        yAxis.setLabel(
                "Development Progress (%)"
        );


        xAxis.setTickLabelFont(
                javafx.scene.text.Font.font(11)
        );


        yAxis.setTickLabelFont(
                javafx.scene.text.Font.font(11)
        );


        LineChart<String, Number> chart =
                new LineChart<>(
                        xAxis,
                        yAxis
                );


        chart.setTitle(
                "Baby Development Progress"
        );


        chart.setTitleSide(
                javafx.geometry.Side.TOP
        );


        chart.setLegendVisible(false);


        chart.setAnimated(false);


        chart.setCreateSymbols(true);


        chart.setPrefHeight(245);

        chart.setMinHeight(245);

        chart.setMaxHeight(245);


        chart.setPrefWidth(390);
        chart.setMinWidth(390);
        chart.setMaxWidth(390);


        chart.setHorizontalGridLinesVisible(true);

        chart.setVerticalGridLinesVisible(false);


        chart.setStyle(
                "-fx-background-color: #FFF9FC;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #F4D5E2;" +
                "-fx-border-radius: 14;" +
                "-fx-padding: 8;"
        );


        XYChart.Series<String, Number>
                progressSeries =
                new XYChart.Series<>();


        progressSeries.setName(
                "Development Progress"
        );


        /*
         * Show all weeks up to the current week.
         *
         * Example:
         * Week 20 selected
         * → graph displays Week 1 to Week 20
         */

        for (
                int i = 1;
                i <= week;
                i++
        ) {


            double progress =
                    (i / 40.0) * 100.0;


            progressSeries
                    .getData()
                    .add(
                            new XYChart.Data<>(
                                    "Week " + i,
                                    progress
                            )
                    );
        }


        chart.getData().add(
                progressSeries
        );

        // Light-pink line and symbols. The chart node is available
        // after CSS/layout, so apply the series style on the FX thread.
        Platform.runLater(() -> {
            Node line = progressSeries.getNode();
            if (line != null) {
                line.setStyle(
                        "-fx-stroke: #F3A6C2;" +
                        "-fx-stroke-width: 3px;"
                );
            }

            for (XYChart.Data<String, Number> data :
                    progressSeries.getData()) {
                Node symbol = data.getNode();
                if (symbol != null) {
                    symbol.setStyle(
                            "-fx-background-color: #F3A6C2, white;" +
                            "-fx-background-insets: 0, 2;" +
                            "-fx-background-radius: 7px;" +
                            "-fx-padding: 5px;"
                    );
                }
            }
        });

        return chart;
    }


    // =========================================================
    // UPDATE BABY DEVELOPMENT CHART
    // =========================================================

    private void updateBabyDevelopmentChart() {

        if (babyDevelopmentChart == null) {

            return;
        }


        babyDevelopmentChart
                .getData()
                .clear();


        XYChart.Series<String, Number>
                progressSeries =
                new XYChart.Series<>();


        progressSeries.setName(
                "Development Progress"
        );


        for (
                int i = 1;
                i <= currentWeek;
                i++
        ) {


            double progress =
                    (i / 40.0) * 100.0;


            progressSeries
                    .getData()
                    .add(
                            new XYChart.Data<>(
                                    "Week " + i,
                                    progress
                            )
                    );
        }


        babyDevelopmentChart
                .getData()
                .add(
                        progressSeries
                );

        Platform.runLater(() -> {
            Node line = progressSeries.getNode();
            if (line != null) {
                line.setStyle(
                        "-fx-stroke: #F3A6C2;" +
                        "-fx-stroke-width: 3px;"
                );
            }

            for (XYChart.Data<String, Number> data :
                    progressSeries.getData()) {
                Node symbol = data.getNode();
                if (symbol != null) {
                    symbol.setStyle(
                            "-fx-background-color: #F3A6C2, white;" +
                            "-fx-background-insets: 0, 2;" +
                            "-fx-background-radius: 7px;" +
                            "-fx-padding: 5px;"
                    );
                }
            }
        });
    }


    // =========================================================
    // MOTHER DETAILS
    // =========================================================

    private void updateMotherDetails() {

        if (motherDetailsBox == null) {

            return;
        }


        String[] data =
                weekData.get(currentWeek);


        if (data == null) {

            return;
        }


        motherDetailsBox
                .getChildren()
                .clear();


        motherDetailsBox
                .getChildren()
                .addAll(

                        createInfoBox(
                                "Body Changes",
                                data[0],
                                PINK
                        ),

                        createInfoBox(
                                "Common Feelings",
                                data[1],
                                PURPLE
                        ),

                        createInfoBox(
                                "Care Focus",
                                data[2],
                                GREEN
                        )
                );
    }


    // =========================================================
    // BABY DETAILS
    // =========================================================

    private void updateBabyDetails() {

        if (babyDetailsBox == null) {

            return;
        }


        String[] data =
                weekData.get(currentWeek);


        if (data == null) {

            return;
        }


        babyDetailsBox
                .getChildren()
                .clear();


        babyDetailsBox
                .getChildren()
                .addAll(

                        createInfoBox(
                                "Development",
                                data[3],
                                PURPLE
                        ),

                        createInfoBox(
                                "Baby Size",
                                data[4],
                                PINK
                        ),

                        createInfoBox(
                                "This Week's Milestone",
                                data[5],
                                GREEN
                        )
                );
    }


    // =========================================================
    // INFO BOX
    // =========================================================

    private VBox createInfoBox(
            String title,
            String description,
            String color) {


        VBox box =
                new VBox();


        box.setSpacing(5);


        box.setPadding(
                new Insets(12)
        );


        box.setStyle(
                "-fx-background-color: #FAF8FC;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EDE4F0;" +
                "-fx-border-radius: 12;"
        );


        Label titleLabel =
                new Label(title);


        titleLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + color + ";"
        );


        Label descriptionLabel =
                new Label(description);


        descriptionLabel.setWrapText(true);


        descriptionLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #55556D;" +
                "-fx-line-spacing: 4px;"
        );


        box.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );


        return box;
    }


    // =========================================================
    // WEEKLY MILESTONES
    // =========================================================

    private VBox createMilestoneCard() {

        VBox card =
                createWhiteCard();


        card.setSpacing(15);


        HBox heading =
                createHeading(
                        "Weekly Milestones ✨",
                        FontAwesomeIcon.STAR,
                        PINK
                );


        HBox content =
                new HBox();


        content.setSpacing(20);


        content.setAlignment(
                Pos.CENTER_LEFT
        );


        milestoneImageBox =
                createWeekImage(
                        currentWeek,
                        210,
                        145
                );


        VBox milestoneInfo =
                new VBox();


        milestoneInfo.setSpacing(10);


        milestoneWeekLabel =
                new Label(
                        "Week " + currentWeek +
                        " Development"
                );


        milestoneWeekLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #24234F;"
        );


        String[] data =
                weekData.get(currentWeek);


        milestoneLabel =
                new Label(
                        data[5]
                );


        milestoneLabel.setWrapText(true);


        milestoneLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #55556D;" +
                "-fx-line-spacing: 5px;"
        );


        milestoneDevelopmentLabel =
                new Label(
                        "👶 " + data[3]
                );


        milestoneDevelopmentLabel.setWrapText(true);


        milestoneDevelopmentLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #7041A5;" +
                "-fx-line-spacing: 4px;"
        );


        HBox.setHgrow(
                milestoneInfo,
                Priority.ALWAYS
        );


        milestoneInfo
                .getChildren()
                .addAll(
                        milestoneWeekLabel,
                        milestoneLabel,
                        milestoneDevelopmentLabel
                );


        content.getChildren()
                .addAll(
                        milestoneImageBox,
                        milestoneInfo
                );


        card.getChildren().addAll(
                heading,
                content
        );


        return card;
    }


    // =========================================================
    // TIPS
    // =========================================================

    private VBox createTipsCard() {

        VBox card =
                new VBox();


        card.setSpacing(14);


        card.setPadding(
                new Insets(18)
        );


        card.setStyle(
                "-fx-background-color: #F8F1FF;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E4D4F3;" +
                "-fx-border-radius: 18;"
        );


        HBox heading =
                createHeading(
                        "Pregnancy Care Tips",
                        FontAwesomeIcon.LIGHTBULB_ALT,
                        PURPLE
                );


        HBox tips =
                new HBox();


        tips.setSpacing(20);


        tips.getChildren().addAll(

                createTip(
                        "💧",
                        "Stay Hydrated",
                        "Drink water regularly throughout the day."
                ),

                createTip(
                        "🥗",
                        "Balanced Nutrition",
                        "Choose a variety of nutritious foods."
                ),

                createTip(
                        "😴",
                        "Rest Well",
                        "Give your body enough time to rest."
                ),

                createTip(
                        "📅",
                        "Regular Checkups",
                        "Follow your healthcare provider's appointments."
                )
        );


        card.getChildren().addAll(
                heading,
                tips
        );


        return card;
    }


    // =========================================================
    // TIP ITEM
    // =========================================================

    private VBox createTip(
            String emoji,
            String title,
            String description) {


        VBox box =
                new VBox();


        box.setSpacing(5);


        box.setPrefWidth(210);


        Label icon =
                new Label(emoji);


        icon.setStyle(
                "-fx-font-size: 25px;"
        );


        Label titleLabel =
                new Label(title);


        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #7041A5;"
        );


        Label descriptionLabel =
                new Label(description);


        descriptionLabel.setWrapText(true);


        descriptionLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77778D;" +
                "-fx-line-spacing: 3px;"
        );


        box.getChildren().addAll(
                icon,
                titleLabel,
                descriptionLabel
        );


        return box;
    }


    // =========================================================
    // WEEK NAVIGATION
    // =========================================================

    private HBox createWeekNavigation() {

        HBox navigation =
                new HBox();


        navigation.setAlignment(
                Pos.CENTER
        );


        navigation.setSpacing(15);


        Button previous =
                createOutlineButton(
                        "← Previous Week"
                );


        Button next =
                createGradientButton(
                        "Next Week →"
                );


        previous.setOnAction(e -> {

            if (currentWeek > 1) {

                currentWeek--;


                if (weekCombo != null) {

                    weekCombo.setValue(
                            currentWeek
                    );
                }


                updateWeekContent();
            }
        });


        next.setOnAction(e -> {

            if (currentWeek < 40) {

                currentWeek++;


                if (weekCombo != null) {

                    weekCombo.setValue(
                            currentWeek
                    );
                }


                updateWeekContent();
            }
        });


        navigation.getChildren().addAll(
                previous,
                next
        );


        return navigation;
    }


    // =========================================================
    // UPDATE WEEK CONTENT
    // =========================================================

    private void updateWeekContent() {


        if (weekTitle != null) {

            weekTitle.setText(
                    "Week " + currentWeek
            );
        }


        if (weekSubtitle != null) {

            weekSubtitle.setText(
                    getWeekSubtitle(currentWeek)
            );
        }


        if (progressWeekLabel != null) {

            progressWeekLabel.setText(
                    currentWeek + " / 40"
            );
        }

        if (journeySubtitleLabel != null) {
            journeySubtitleLabel.setText(
                    getTrimesterName(currentWeek) +
                    " • Week " + currentWeek + " of 40"
            );
        }

        if (trimesterLabel != null) {
            trimesterLabel.setText(
                    getTrimesterName(currentWeek)
            );
        }

        if (trimesterDescriptionLabel != null) {
            trimesterDescriptionLabel.setText(
                    getTrimesterDescription(currentWeek)
            );
        }

        if (pregnancyProgressBar != null) {
            pregnancyProgressBar.setProgress(
                    currentWeek / 40.0
            );
        }

        if (motherWeekHint != null) {
            motherWeekHint.setText(
                    "How your body may change during Week " +
                    currentWeek
            );
        }

        if (babyWeekHint != null) {
            babyWeekHint.setText(
                    "Your baby's growth during Week " +
                    currentWeek
            );
        }

        String[] currentData = weekData.get(currentWeek);

        if (currentData != null) {
            if (milestoneWeekLabel != null) {
                milestoneWeekLabel.setText(
                        "Week " + currentWeek + " Development"
                );
            }

            if (milestoneLabel != null) {
                milestoneLabel.setText(currentData[5]);
            }

            if (milestoneDevelopmentLabel != null) {
                milestoneDevelopmentLabel.setText(
                        "👶 " + currentData[3]
                );
            }
        }


        if (journeyImageBox != null) {

            updateImageBox(
                    journeyImageBox,
                    getTrimesterImage(currentWeek),
                    "🤰"
            );
        }


        if (motherImageBox != null) {

            updateImageBox(
                    motherImageBox,
                    getTrimesterImage(currentWeek),
                    "🤰"
            );
        }


        if (babyImageBox != null) {

            updateImageBox(
                    babyImageBox,
                    getWeekImagePath(currentWeek),
                    "👶"
            );
        }


        if (milestoneImageBox != null) {

            updateImageBox(
                    milestoneImageBox,
                    getWeekImagePath(currentWeek),
                    "👶"
            );
        }


        updateBabyDevelopmentChart();


        updateMotherDetails();


        updateBabyDetails();
    }


    // =========================================================
    // TRIMESTER IMAGE
    // =========================================================

    private StackPane createTrimesterImage(
            int week,
            double width,
            double height) {


        StackPane box =
                new StackPane();


        box.setPrefSize(
                width,
                height
        );


        box.setMinSize(
                width,
                height
        );


        box.setMaxSize(
                width,
                height
        );


        box.setAlignment(
                Pos.CENTER
        );


        box.setStyle(
                "-fx-background-color: #FFF7FA;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #F0D8E3;" +
                "-fx-border-radius: 15;"
        );


        updateImageBox(
                box,
                getTrimesterImage(week),
                "🤰"
        );


        return box;
    }


    // =========================================================
    // WEEK IMAGE
    // =========================================================

    private StackPane createWeekImage(
            int week,
            double width,
            double height) {


        StackPane box =
                new StackPane();


        box.setPrefSize(
                width,
                height
        );


        box.setMinSize(
                width,
                height
        );


        box.setMaxSize(
                width,
                height
        );


        box.setAlignment(
                Pos.CENTER
        );


        box.setStyle(
                "-fx-background-color: #FFF7FA;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #E9D9ED;" +
                "-fx-border-radius: 15;"
        );


        updateImageBox(
                box,
                getWeekImagePath(week),
                "👶"
        );


        return box;
    }


    // =========================================================
    // UPDATE IMAGE BOX
    // =========================================================

    private void updateImageBox(
            StackPane box,
            String imagePath,
            String emoji) {


        box.getChildren().clear();


        if (imagePath != null) {


            var resource =
                    getClass().getResource(
                            imagePath
                    );


            if (resource != null) {


                javafx.scene.image.Image image =
                        new javafx.scene.image.Image(
                                resource.toExternalForm()
                        );


                javafx.scene.image.ImageView imageView =
                        new javafx.scene.image.ImageView(
                                image
                        );


                imageView.setFitWidth(
                        box.getPrefWidth() - 8
                );


                imageView.setFitHeight(
                        box.getPrefHeight() - 8
                );


                imageView.setPreserveRatio(
                        true
                );


                imageView.setSmooth(
                        true
                );


                box.getChildren().add(
                        imageView
                );


                return;
            }
        }


        Label fallback =
                new Label(emoji);


        fallback.setStyle(
                "-fx-font-size: 55px;"
        );


        box.getChildren().add(
                fallback
        );
    }


    // =========================================================
    // TRIMESTER IMAGE PATH
    // =========================================================

    private String getTrimesterImage(
            int week) {


        if (week <= 13) {

            return "/assets/images/mother/first_trimester.png";

        } else if (week <= 27) {

            return "/assets/images/mother/second_trimester.png";

        } else {

            return "/assets/images/mother/third_trimester.png";
        }
    }


    // =========================================================
    // WEEK IMAGE PATH
    // =========================================================

    private String getWeekImagePath(
            int week) {


        return "/assets/images/week" +
                week +
                ".png";
    }


    // =========================================================
    // TRIMESTER NAME
    // =========================================================

    private String getTrimesterName(
            int week) {


        if (week <= 13) {

            return "🌱 First Trimester";

        } else if (week <= 27) {

            return "🌸 Second Trimester";

        } else {

            return "🌷 Third Trimester";
        }
    }


    // =========================================================
    // TRIMESTER DESCRIPTION
    // =========================================================

    private String getTrimesterDescription(
            int week) {


        if (week <= 13) {

            return
                    "Early pregnancy is a period of rapid " +
                    "development. Your baby's basic organs " +
                    "and body structures begin forming.";

        } else if (week <= 27) {

            return
                    "During the second trimester, your baby " +
                    "continues growing and becoming more " +
                    "active. Many body systems mature further.";

        } else {

            return
                    "The third trimester focuses on continued " +
                    "growth, brain development and preparation " +
                    "for birth.";
        }
    }


    // =========================================================
    // WEEK SUBTITLE
    // =========================================================

    private String getWeekSubtitle(
            int week) {


        if (week <= 4) {

            return "Early pregnancy development";

        } else if (week <= 8) {

            return "Baby's early development begins";

        } else if (week <= 12) {

            return "First trimester milestones";

        } else if (week <= 16) {

            return "Baby continues to grow";

        } else if (week <= 20) {

            return "Your baby is becoming more active";

        } else if (week <= 24) {

            return "Baby's senses and movement continue developing";

        } else if (week <= 28) {

            return "Baby continues preparing for the third trimester";

        } else if (week <= 32) {

            return "Baby continues gaining strength and growth";

        } else if (week <= 36) {

            return "Getting closer to meeting your baby";

        } else {

            return "Final weeks of pregnancy";
        }
    }


    // =========================================================
    // HEADING
    // =========================================================

    private HBox createHeading(
            String text,
            FontAwesomeIcon iconType,
            String color) {


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


        icon.setSize("20");


        icon.setFill(
                Color.web(color)
        );


        Label title =
                new Label(text);


        title.setStyle(
                "-fx-font-size: 19px;" +
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
    // ICON CIRCLE
    // =========================================================

    private VBox createIconCircle(
            FontAwesomeIcon iconType,
            String color) {


        VBox box =
                new VBox();


        box.setAlignment(
                Pos.CENTER
        );


        box.setPrefSize(
                58,
                58
        );


        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-color: #E8D6ED;" +
                "-fx-border-radius: 50%;"
        );


        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                        iconType
                );


        icon.setSize("24");


        icon.setFill(
                Color.web(color)
        );


        box.getChildren().add(
                icon
        );


        return box;
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
    // OUTLINE BUTTON
    // =========================================================

    private Button createOutlineButton(
            String text) {


        Button button =
                new Button(text);


        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #7041A5;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #DCC9EC;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 20px;" +
                "-fx-cursor: hand;"
        );


        return button;
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
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 10px 22px;" +
                "-fx-cursor: hand;"
        );


        return button;
    }


    // =========================================================
    // WEEK DATA
    // =========================================================

    private void loadWeekData() {

        // =====================================================
        // WEEK 1
        // =====================================================

        addWeek(
                1,
                "Pregnancy is just beginning and conception " +
                "typically occurs around this time.",
                "You may not notice pregnancy symptoms yet.",
                "Start focusing on healthy habits and prenatal care.",
                "The fertilized egg begins its early journey toward implantation.",
                "Microscopic — too small to measure meaningfully.",
                "Early cell division and preparation for implantation begin."
        );


        // =====================================================
        // WEEK 2
        // =====================================================

        addWeek(
                2,
                "Ovulation and fertilization may occur during this period.",
                "Most people still do not feel pregnancy-related changes.",
                "Track your cycle and begin pregnancy planning if appropriate.",
                "The fertilized egg continues dividing as it moves toward the uterus.",
                "Microscopic.",
                "Cell division continues and the early embryo prepares for implantation."
        );


        // =====================================================
        // WEEK 3
        // =====================================================

        addWeek(
                3,
                "Implantation may occur and early pregnancy hormones begin rising.",
                "Some people notice mild tiredness or light changes.",
                "Begin prenatal care and discuss folic acid with your healthcare provider.",
                "The embryo starts developing the foundations of the nervous system and other structures.",
                "About a tiny seed.",
                "Implantation and early embryonic development take place."
        );


        // =====================================================
        // WEEK 4
        // =====================================================

        addWeek(
                4,
                "Pregnancy hormones increase and a missed period may occur.",
                "Fatigue, breast tenderness or mild nausea may begin.",
                "Take prescribed prenatal supplements and arrange prenatal care.",
                "The embryo's basic layers begin forming and early development accelerates.",
                "About a poppy seed.",
                "Early structures that will become the baby's organs begin forming."
        );


        // =====================================================
        // WEEK 5
        // =====================================================

        addWeek(
                5,
                "Hormonal changes may make you feel more tired or sensitive.",
                "Nausea, fatigue and breast tenderness can become more noticeable.",
                "Eat regular nutritious meals and stay hydrated.",
                "The neural tube and early heart structures continue developing.",
                "About a sesame seed.",
                "Early heart and nervous-system development progresses."
        );


        // =====================================================
        // WEEK 6
        // =====================================================

        addWeek(
                6,
                "Your body is adjusting rapidly to increasing pregnancy hormones.",
                "Morning sickness, tiredness and food aversions may occur.",
                "Rest when needed and discuss persistent vomiting with your provider.",
                "The embryo develops early facial features and limb buds.",
                "About a lentil.",
                "Early heart activity may begin and the brain develops rapidly."
        );


        // =====================================================
        // WEEK 7
        // =====================================================

        addWeek(
                7,
                "Hormonal changes can make fatigue and nausea stronger.",
                "You may experience nausea, tiredness or increased urination.",
                "Choose small frequent meals and maintain hydration.",
                "The brain continues developing and limb structures become more defined.",
                "About a blueberry.",
                "Arm and leg development continues."
        );


        // =====================================================
        // WEEK 8
        // =====================================================

        addWeek(
                8,
                "Your uterus continues growing while pregnancy hormones remain high.",
                "Nausea, fatigue and breast changes may continue.",
                "Continue prenatal care, nutrition and adequate rest.",
                "Facial features and developing limbs become more recognizable.",
                "About a raspberry.",
                "The embryo's major body structures continue taking shape."
        );


        // =====================================================
        // WEEK 9
        // =====================================================

        addWeek(
                9,
                "Your body continues adapting to pregnancy hormones.",
                "Fatigue and nausea may still be noticeable.",
                "Keep meals nutritious and follow your prenatal-care schedule.",
                "The embryo is developing rapidly and the basic body plan is becoming clearer.",
                "About a cherry.",
                "Fingers and toes begin becoming more defined."
        );


        // =====================================================
        // WEEK 10
        // =====================================================

        addWeek(
                10,
                "Your uterus continues expanding and your body needs extra energy.",
                "You may feel tired, nauseated or emotionally sensitive.",
                "Continue healthy nutrition and prescribed supplements.",
                "Major organs have formed in early form and continue maturing.",
                "About a small strawberry.",
                "The embryo transitions toward the fetal stage."
        );


        // =====================================================
        // WEEK 11
        // =====================================================

        addWeek(
                11,
                "Some early pregnancy symptoms may begin changing.",
                "Nausea may continue while energy may slowly improve.",
                "Include protein, fruits, vegetables and whole grains in meals.",
                "The baby's body continues growing and the head remains proportionally large.",
                "About a lime.",
                "Hands and feet continue developing."
        );


        // =====================================================
        // WEEK 12
        // =====================================================

        addWeek(
                12,
                "Your uterus is growing and early pregnancy symptoms may start easing.",
                "Energy may begin improving, although symptoms vary.",
                "Keep prenatal appointments and maintain balanced nutrition.",
                "The baby's facial features and organs continue maturing.",
                "About a plum.",
                "Reflexive movements begin developing."
        );


        // =====================================================
        // WEEK 13
        // =====================================================

        addWeek(
                13,
                "You are reaching the end of the first trimester.",
                "Nausea may reduce and appetite or energy may improve.",
                "Continue prenatal vitamins and prepare for the second trimester.",
                "The baby's bones and muscles continue developing.",
                "About a peach.",
                "The baby can make small movements even though you may not feel them yet."
        );


        // =====================================================
        // WEEK 14
        // =====================================================

        addWeek(
                14,
                "The second trimester begins and many early symptoms may improve.",
                "You may feel more energetic than during early pregnancy.",
                "Continue balanced meals, hydration and regular checkups.",
                "The baby's facial muscles and body proportions continue developing.",
                "About a lemon.",
                "The baby continues moving and developing facial features."
        );


        // =====================================================
        // WEEK 15
        // =====================================================

        addWeek(
                15,
                "Your uterus continues growing as your abdomen gradually changes.",
                "Mild stretching sensations may occur.",
                "Maintain comfortable activity and adequate hydration.",
                "The baby's bones continue developing and becoming stronger.",
                "About an apple.",
                "The baby's skeletal development progresses."
        );


        // =====================================================
        // WEEK 16
        // =====================================================

        addWeek(
                16,
                "Your pregnancy may become more visible as your uterus grows.",
                "Some people begin noticing more energy and appetite.",
                "Follow your provider's guidance for nutrition and activity.",
                "The baby's facial muscles and movements continue developing.",
                "About an avocado.",
                "The baby can make facial movements and move the limbs."
        );


        // =====================================================
        // WEEK 17
        // =====================================================

        addWeek(
                17,
                "Your belly may become more noticeable as the uterus grows.",
                "You may experience stretching sensations and changing posture.",
                "Focus on comfortable movement, hydration and balanced meals.",
                "The baby's skeleton continues developing and body fat begins gradually increasing later in pregnancy.",
                "About a pear.",
                "The baby continues growing rapidly and becoming more active."
        );


        // =====================================================
        // WEEK 18
        // =====================================================

        addWeek(
                18,
                "Your uterus continues expanding and your center of gravity changes.",
                "Back discomfort or sleep changes may occur.",
                "Use comfortable sleeping positions and discuss significant pain with your provider.",
                "The baby's hearing structures continue developing.",
                "About a bell pepper.",
                "The baby may begin responding to sounds."
        );


        // =====================================================
        // WEEK 19
        // =====================================================

        addWeek(
                19,
                "Your growing uterus may affect posture and comfort.",
                "You may notice skin changes or occasional aches.",
                "Stay hydrated and maintain gentle, provider-approved activity.",
                "The baby's senses continue developing and movement becomes stronger.",
                "About a mango.",
                "Sensory development continues, including touch and hearing."
        );


        // =====================================================
        // WEEK 20
        // =====================================================

        addWeek(
                20,
                "You are around the halfway point of pregnancy.",
                "Your belly is more noticeable and movements may become clearer.",
                "Attend scheduled scans and continue nutritious meals.",
                "The baby's growth continues and movements may become easier to feel.",
                "About a banana.",
                "The baby's movement and sensory development continue."
        );


        // =====================================================
        // WEEK 21
        // =====================================================

        addWeek(
                21,
                "Your growing uterus may increase pressure on your back and legs.",
                "You may feel stronger baby movements.",
                "Rest when needed and keep up with hydration.",
                "The baby's digestive system and sensory development continue.",
                "About a carrot.",
                "The baby continues swallowing amniotic fluid and practicing movements."
        );


        // =====================================================
        // WEEK 22
        // =====================================================

        addWeek(
                22,
                "Your abdomen continues growing and your body needs increasing support.",
                "You may notice more movement and occasional leg discomfort.",
                "Maintain balanced nutrition and discuss persistent symptoms with your provider.",
                "The baby's facial features become more defined.",
                "About a papaya.",
                "The baby's senses continue developing."
        );


        // =====================================================
        // WEEK 23
        // =====================================================

        addWeek(
                23,
                "Your uterus continues expanding as pregnancy progresses.",
                "Backache, leg cramps or sleep changes may occur.",
                "Keep hydrated and follow safe activity recommendations.",
                "The baby's lungs continue developing even though they are not yet mature.",
                "About a large grapefruit.",
                "The baby continues practicing breathing-like movements."
        );


        // =====================================================
        // WEEK 24
        // =====================================================

        addWeek(
                24,
                "Your body is supporting rapid baby growth.",
                "You may notice increased movement and physical tiredness.",
                "Keep prenatal appointments and monitor your wellbeing.",
                "The baby's lungs and nervous system continue maturing.",
                "About an ear of corn.",
                "The baby's developing senses respond increasingly to the environment."
        );


        // =====================================================
        // WEEK 25
        // =====================================================

        addWeek(
                25,
                "Your growing uterus can affect sleep, posture and comfort.",
                "You may notice stronger kicks and occasional heartburn.",
                "Eat smaller meals if needed and stay hydrated.",
                "The baby's brain and nervous system continue developing.",
                "About a rutabaga.",
                "The baby's movements become more coordinated."
        );


        // =====================================================
        // WEEK 26
        // =====================================================

        addWeek(
                26,
                "Your pregnancy is progressing toward the third trimester.",
                "Back discomfort, leg cramps or swelling may occur.",
                "Discuss unusual swelling or symptoms with your healthcare provider.",
                "The baby's eyes and brain continue developing.",
                "About a cucumber.",
                "The baby may respond to familiar sounds."
        );


        // =====================================================
        // WEEK 27
        // =====================================================

        addWeek(
                27,
                "You are finishing the second trimester.",
                "You may experience stronger movements and increasing tiredness.",
                "Prepare for the third trimester and keep regular checkups.",
                "The baby's brain and lungs continue maturing.",
                "About a cauliflower.",
                "The baby's sleep and wake patterns become more noticeable."
        );


        // =====================================================
        // WEEK 28
        // =====================================================

        addWeek(
                28,
                "The third trimester begins and your body is preparing for later pregnancy.",
                "You may feel more tired and experience sleep changes.",
                "Prioritize rest, hydration and prenatal appointments.",
                "The baby's brain continues rapid development and the lungs mature further.",
                "About an eggplant.",
                "The baby's eyes can open and close."
        );


        // =====================================================
        // WEEK 29
        // =====================================================

        addWeek(
                29,
                "Your growing uterus may create more pressure and discomfort.",
                "You may notice stronger kicks and occasional shortness of breath.",
                "Take comfortable breaks and discuss concerning symptoms with your provider.",
                "The baby continues gaining muscle and body fat.",
                "About a butternut squash.",
                "The baby's movements become stronger and more coordinated."
        );


        // =====================================================
        // WEEK 30
        // =====================================================

        addWeek(
                30,
                "Your body is supporting rapid growth during the third trimester.",
                "Fatigue, back discomfort and sleep changes may continue.",
                "Continue prenatal care and prepare gradually for delivery.",
                "The baby's brain continues developing rapidly.",
                "About a cabbage.",
                "The baby continues gaining weight and developing brain connections."
        );


        // =====================================================
        // WEEK 31
        // =====================================================

        addWeek(
                31,
                "Your uterus continues expanding and may affect your breathing and sleep.",
                "You may experience stronger movements and more frequent urination.",
                "Rest comfortably and maintain hydration.",
                "The baby's muscles, bones and brain continue maturing.",
                "About a coconut.",
                "The baby continues practicing movements and breathing-like motions."
        );


        // =====================================================
        // WEEK 32
        // =====================================================

        addWeek(
                32,
                "Your body is preparing increasingly for birth.",
                "You may feel heavier and need more rest.",
                "Keep prenatal appointments and discuss your birth plan with your provider.",
                "The baby's bones continue hardening while the skull remains flexible.",
                "About a squash.",
                "The baby continues gaining fat and developing brain function."
        );


        // =====================================================
        // WEEK 33
        // =====================================================

        addWeek(
                33,
                "Your growing belly may affect movement and sleeping comfort.",
                "Backache, pelvic pressure and fatigue may increase.",
                "Rest regularly and report concerning symptoms promptly.",
                "The baby's immune system and brain continue maturing.",
                "About a pineapple.",
                "The baby continues gaining strength and preparing for life outside the womb."
        );


        // =====================================================
        // WEEK 34
        // =====================================================

        addWeek(
                34,
                "Your body is getting closer to full-term pregnancy.",
                "You may experience increased tiredness and pelvic pressure.",
                "Keep all prenatal appointments and follow your provider's guidance.",
                "The baby's lungs and nervous system continue maturing.",
                "About a cantaloupe.",
                "The baby continues gaining body fat and improving temperature regulation."
        );


        // =====================================================
        // WEEK 35
        // =====================================================

        addWeek(
                35,
                "Your uterus is taking up more space and movement may feel harder.",
                "Frequent urination, fatigue and pelvic pressure may occur.",
                "Rest, stay hydrated and prepare essential items for delivery.",
                "The baby's brain and lungs continue maturing.",
                "About a honeydew melon.",
                "The baby continues gaining weight and practicing coordinated movements."
        );


        // =====================================================
        // WEEK 36
        // =====================================================

        addWeek(
                36,
                "Your body is approaching the final weeks of pregnancy.",
                "You may feel increased pelvic pressure as the baby moves lower.",
                "Attend all remaining prenatal appointments and know when to contact your provider.",
                "The baby's organs are continuing to mature and the body is gaining fat.",
                "About a large melon.",
                "The baby continues preparing for birth."
        );


        // =====================================================
        // WEEK 37
        // =====================================================

        addWeek(
                37,
                "Pregnancy is now considered early term.",
                "You may notice increased pelvic pressure and changes in comfort.",
                "Keep your healthcare provider's contact information available.",
                "The baby's organs continue functioning and the body continues maturing.",
                "About a bunch of Swiss chard.",
                "The baby continues gaining weight and preparing for birth."
        );


        // =====================================================
        // WEEK 38
        // =====================================================

        addWeek(
                38,
                "Your body continues preparing for labor and birth.",
                "You may feel more pelvic pressure and tiredness.",
                "Continue monitoring your wellbeing and follow your birth plan.",
                "The baby continues gaining weight and maturing.",
                "About a leek-sized baby.",
                "The baby continues final preparation for life outside the uterus."
        );


        // =====================================================
        // WEEK 39
        // =====================================================

        addWeek(
                39,
                "You are very close to meeting your baby.",
                "You may feel increased pressure and contractions may occur.",
                "Follow your healthcare provider's instructions about signs of labor.",
                "The baby's organs are mature enough for life outside the uterus in most cases.",
                "About a small watermelon.",
                "The baby continues final growth and preparation for birth."
        );


        // =====================================================
        // WEEK 40
        // =====================================================

        addWeek(
                40,
                "This is the estimated due week for many pregnancies.",
                "You may experience increasing pressure and signs that labor is approaching.",
                "Stay in contact with your healthcare provider and follow your birth plan.",
                "Your baby has completed most major development and is ready for birth.",
                "About a small pumpkin.",
                "Final preparation for birth and transition to life outside the womb."
        );
    }


    // =========================================================
    // ADD WEEK
    // =========================================================

    private void addWeek(
            int week,
            String bodyChanges,
            String feelings,
            String careFocus,
            String development,
            String size,
            String milestone) {


        weekData.put(
                week,
                new String[] {
                        bodyChanges,
                        feelings,
                        careFocus,
                        development,
                        size,
                        milestone
                }
        );
    }
}