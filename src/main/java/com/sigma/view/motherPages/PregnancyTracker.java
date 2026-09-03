package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sigma.controller.PregnancyWeekController;
import com.sigma.model.MotherWlcModel;
import com.sigma.model.PregnancyWeekModel;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
    // PREGNANCY WEEK CONTROLLER
    // =========================================================

    private final PregnancyWeekController pregnancyWeekController =
            new PregnancyWeekController();


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

    private final Map<Integer, PregnancyWeekModel> weekData =
            new HashMap<>();


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public PregnancyTracker() {

        currentWeek = 20;

        loadWeekDataFromFirebase();
    }


    // =========================================================
    // MODEL CONSTRUCTOR
    // =========================================================

    public PregnancyTracker(
            MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        calculateCurrentWeekFromLMP();

        loadWeekDataFromFirebase();
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
    // LOAD WEEK DATA FROM FIREBASE
    // =========================================================

    private void loadWeekDataFromFirebase() {

        new Thread(() -> {

            try {

                List<PregnancyWeekModel> weeks =
                        pregnancyWeekController.getAllWeeks();

                weekData.clear();

                for (PregnancyWeekModel week : weeks) {

                    weekData.put(
                            week.getWeek(),
                            week
                    );
                }

                Platform.runLater(() -> {

                    if (weekCombo != null) {

                        weekCombo.setValue(currentWeek);
                    }

                    updateWeekContent();
                });

            } catch (Exception e) {

                e.printStackTrace();

                Platform.runLater(() -> {

                    if (motherDetailsBox != null) {

                        motherDetailsBox
                                .getChildren()
                                .clear();

                        Label errorLabel =
                                new Label(
                                        "Unable to load pregnancy data."
                                );

                        errorLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-text-fill: #E84A87;"
                        );

                        motherDetailsBox
                                .getChildren()
                                .add(
                                        errorLabel
                                );
                    }
                });
            }
        }).start();
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


        Platform.runLater(() -> {

            Node line =
                    progressSeries.getNode();

            if (line != null) {

                line.setStyle(
                        "-fx-stroke: #F3A6C2;" +
                        "-fx-stroke-width: 3px;"
                );
            }


            for (
                    XYChart.Data<String, Number> data :
                    progressSeries.getData()
            ) {

                Node symbol =
                        data.getNode();

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

            Node line =
                    progressSeries.getNode();

            if (line != null) {

                line.setStyle(
                        "-fx-stroke: #F3A6C2;" +
                        "-fx-stroke-width: 3px;"
                );
            }


            for (
                    XYChart.Data<String, Number> data :
                    progressSeries.getData()
            ) {

                Node symbol =
                        data.getNode();

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


        PregnancyWeekModel data =
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
                                data.getMotherChanges(),
                                PINK
                        ),

                        createInfoBox(
                                "Common Feelings",
                                data.getSymptoms(),
                                PURPLE
                        ),

                        createInfoBox(
                                "Care Focus",
                                data.getTips(),
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


        PregnancyWeekModel data =
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
                                data.getBabyDevelopment(),
                                PURPLE
                        ),

                        createInfoBox(
                                "Baby Size",
                                data.getBabySize(),
                                PINK
                        ),

                        createInfoBox(
                                "This Week's Milestone",
                                data.getMilestone(),
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
                new Label(
                        description != null
                                ? description
                                : ""
                );


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


        PregnancyWeekModel data =
                weekData.get(currentWeek);


        milestoneLabel =
                new Label(
                        data != null
                                ? data.getMilestone()
                                : "Loading..."
                );


        milestoneLabel.setWrapText(true);


        milestoneLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #55556D;" +
                "-fx-line-spacing: 5px;"
        );


        milestoneDevelopmentLabel =
                new Label(
                        data != null
                                ? "👶 " + data.getBabyDevelopment()
                                : "👶 Loading..."
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


        PregnancyWeekModel currentData =
                weekData.get(currentWeek);


        if (currentData != null) {

            if (milestoneWeekLabel != null) {

                milestoneWeekLabel.setText(
                        "Week " +
                        currentWeek +
                        " Development"
                );
            }


            if (milestoneLabel != null) {

                milestoneLabel.setText(
                        currentData.getMilestone()
                );
            }


            if (milestoneDevelopmentLabel != null) {

                milestoneDevelopmentLabel.setText(
                        "👶 " +
                        currentData.getBabyDevelopment()
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
}