package com.sigma.view.adminpages;

import com.sigma.view.scenesettings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminAnalytics {

    private Scene analyticsScene;

    public Scene getAnalyticsScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #FAF8FD;");

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(22);

        content.setPadding(
                new Insets(30, 40, 40, 40));

        // ====================================================
        // GO BACK TO DASHBOARD BUTTON
        // ====================================================
        Button backButton = new Button(
                "←   Back to Dashboard");

        backButton.setPrefHeight(42);

        backButton.setPadding(
                new Insets(0, 20, 0, 20));

        backButton.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-border-color: #713CC3;" +
                        "-fx-border-width: 1.5px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;");

        backButton.setOnMouseEntered(e -> {

            backButton.setStyle(
                    "-fx-background-color: #713CC3;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-color: #713CC3;" +
                            "-fx-border-width: 1.5px;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;");
        });

        backButton.setOnMouseExited(e -> {

            backButton.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: #713CC3;" +
                            "-fx-border-color: #713CC3;" +
                            "-fx-border-width: 1.5px;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;");
        });

        backButton.setOnAction(e -> {

            System.out.println(
                    "[ADMIN] Back to Dashboard clicked");

            // Add AdminDashboard navigation here later
        });

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label("Analytics");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32));

        title.setTextFill(
                Color.web("#24234F"));

        Label subtitle = new Label(
                "Monitor MaaCare AI platform activity and verification statistics.");

        subtitle.setStyle(
                "-fx-text-fill: #77778D;" +
                        "-fx-font-size: 14px;");

        VBox titleBox = new VBox(
                5,
                backButton,
                title,
                subtitle);

        // =====================================================
        // FILTER BAR
        // =====================================================

        HBox filterBar = new HBox(10);

        filterBar.setAlignment(
                Pos.CENTER_LEFT);

        filterBar.setPadding(
                new Insets(15));

        filterBar.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 14px;" +
                        "-fx-border-color: #E8E2EF;" +
                        "-fx-border-radius: 14px;");

        Label filter = new Label("View:");

        filter.setStyle(
                "-fx-text-fill: #24234F;" +
                        "-fx-font-weight: bold;");

        Button monthly = new Button("Monthly");

        Button quarterly = new Button("Quarterly");

        Button yearly = new Button("Yearly");

        monthly.setStyle(
                "-fx-background-color: #713CC3;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 9px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;");

        quarterly.setStyle(
                "-fx-background-color: #F1E8FF;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-background-radius: 9px;" +
                        "-fx-cursor: hand;");

        yearly.setStyle(
                "-fx-background-color: #F1E8FF;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-background-radius: 9px;" +
                        "-fx-cursor: hand;");

        monthly.setOnAction(e -> System.out.println(
                "[ANALYTICS] Monthly selected"));

        quarterly.setOnAction(e -> System.out.println(
                "[ANALYTICS] Quarterly selected"));

        yearly.setOnAction(e -> System.out.println(
                "[ANALYTICS] Yearly selected"));

        filterBar.getChildren().addAll(
                filter,
                monthly,
                quarterly,
                yearly);

        // =====================================================
        // STATISTICS CARDS
        // =====================================================

        HBox stats = new HBox(15);

        VBox totalUsers = new VBox(
                new Label("Total Users"),
                new Label("12,450"),
                new Label("+12.5%"));

        VBox doctors = new VBox(
                new Label("Doctors"),
                new Label("184"),
                new Label("+8.3%"));

        VBox hospitals = new VBox(
                new Label("Hospitals"),
                new Label("42"),
                new Label("+5.2%"));

        VBox workers = new VBox(
                new Label("ASHA Workers"),
                new Label("156"),
                new Label("+10.4%"));

        VBox[] cards = {
                totalUsers,
                doctors,
                hospitals,
                workers
        };

        for (VBox card : cards) {

            card.setSpacing(7);

            card.setPadding(
                    new Insets(18));

            card.setPrefHeight(120);

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-border-color: #E7E1EF;" +
                            "-fx-border-radius: 15px;");

            HBox.setHgrow(
                    card,
                    Priority.ALWAYS);

            Label cardTitle = (Label) card.getChildren().get(0);

            cardTitle.setStyle(
                    "-fx-text-fill: #77778D;" +
                            "-fx-font-size: 13px;");

            Label value = (Label) card.getChildren().get(1);

            value.setStyle(
                    "-fx-text-fill: #24234F;" +
                            "-fx-font-size: 27px;" +
                            "-fx-font-weight: bold;");

            Label change = (Label) card.getChildren().get(2);

            change.setStyle(
                    "-fx-text-fill: #20A56A;" +
                            "-fx-font-size: 11px;" +
                            "-fx-font-weight: bold;");

            card.setOnMouseEntered(e -> card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-border-color: #BFA1E8;" +
                            "-fx-border-width: 1.5px;" +
                            "-fx-border-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(113,60,195,0.15), 12, 0, 0, 4);"));

            card.setOnMouseExited(e -> card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-border-color: #E7E1EF;" +
                            "-fx-border-radius: 15px;"));
        }

        stats.getChildren().addAll(
                cards);

        // =====================================================
        // USER GROWTH CHART
        // =====================================================

        VBox growthBox = new VBox(15);

        growthBox.setPadding(
                new Insets(20));

        growthBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #E7E1EF;" +
                        "-fx-border-radius: 15px;");

        Label growthTitle = new Label("User Growth");

        growthTitle.setStyle(
                "-fx-text-fill: #24234F;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        CategoryAxis growthX = new CategoryAxis();

        growthX.setLabel(
                "Month");

        NumberAxis growthY = new NumberAxis();

        growthY.setLabel(
                "Users");

        LineChart<String, Number> growthChart = new LineChart<>(
                growthX,
                growthY);

        growthChart.setAnimated(false);

        growthChart.setLegendVisible(false);

        growthChart.setCreateSymbols(true);

        growthChart.setPrefHeight(320);

        XYChart.Series<String, Number> growth = new XYChart.Series<>();

        growth.getData().add(
                new XYChart.Data<>(
                        "Mar",
                        7200));

        growth.getData().add(
                new XYChart.Data<>(
                        "Apr",
                        8100));

        growth.getData().add(
                new XYChart.Data<>(
                        "May",
                        8950));

        growth.getData().add(
                new XYChart.Data<>(
                        "Jun",
                        10100));

        growth.getData().add(
                new XYChart.Data<>(
                        "Jul",
                        11200));

        growth.getData().add(
                new XYChart.Data<>(
                        "Aug",
                        12450));

        growthChart.getData().add(
                growth);

        growthBox.getChildren().addAll(
                growthTitle,
                growthChart);

        // =====================================================
        // USER DISTRIBUTION
        // =====================================================

        VBox distributionBox = new VBox(15);

        distributionBox.setPadding(
                new Insets(20));

        distributionBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #E7E1EF;" +
                        "-fx-border-radius: 15px;");

        Label distributionTitle = new Label(
                "User Distribution");

        distributionTitle.setStyle(
                "-fx-text-fill: #24234F;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        PieChart pieChart = new PieChart();

        pieChart.setPrefHeight(320);

        pieChart.setLabelsVisible(true);

        pieChart.setLegendVisible(true);

        pieChart.getData().addAll(

                new PieChart.Data(
                        "Mother / Family",
                        8450),

                new PieChart.Data(
                        "Doctors",
                        184),

                new PieChart.Data(
                        "Hospitals",
                        42),

                new PieChart.Data(
                        "ASHA Workers",
                        156));

        distributionBox.getChildren().addAll(
                distributionTitle,
                pieChart);

        // =====================================================
        // CHART ROW
        // =====================================================

        HBox chartRow = new HBox(20);

        HBox.setHgrow(
                growthBox,
                Priority.ALWAYS);

        HBox.setHgrow(
                distributionBox,
                Priority.ALWAYS);

        chartRow.getChildren().addAll(
                growthBox,
                distributionBox);

        // =====================================================
        // MONTHLY REGISTRATION CHART
        // =====================================================

        VBox registrationBox = new VBox(15);

        registrationBox.setPadding(
                new Insets(20));

        registrationBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #E7E1EF;" +
                        "-fx-border-radius: 15px;");

        Label registrationTitle = new Label(
                "Monthly Registrations");

        registrationTitle.setStyle(
                "-fx-text-fill: #24234F;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        CategoryAxis registrationX = new CategoryAxis();

        registrationX.setLabel(
                "Month");

        NumberAxis registrationY = new NumberAxis();

        registrationY.setLabel(
                "Registrations");

        BarChart<String, Number> registrationChart = new BarChart<>(
                registrationX,
                registrationY);

        registrationChart.setAnimated(false);

        registrationChart.setLegendVisible(false);

        registrationChart.setPrefHeight(320);

        XYChart.Series<String, Number> registrations = new XYChart.Series<>();

        registrations.getData().add(
                new XYChart.Data<>(
                        "Mar",
                        850));

        registrations.getData().add(
                new XYChart.Data<>(
                        "Apr",
                        1040));

        registrations.getData().add(
                new XYChart.Data<>(
                        "May",
                        1250));

        registrations.getData().add(
                new XYChart.Data<>(
                        "Jun",
                        1430));

        registrations.getData().add(
                new XYChart.Data<>(
                        "Jul",
                        1680));

        registrations.getData().add(
                new XYChart.Data<>(
                        "Aug",
                        1920));

        registrationChart.getData().add(
                registrations);

        registrationBox.getChildren().addAll(
                registrationTitle,
                registrationChart);

        // =====================================================
        // VERIFICATION STATISTICS
        // =====================================================

        VBox verificationBox = new VBox(15);

        verificationBox.setPadding(
                new Insets(20));

        verificationBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #E7E1EF;" +
                        "-fx-border-radius: 15px;");

        Label verificationTitle = new Label(
                "Verification Statistics");

        verificationTitle.setStyle(
                "-fx-text-fill: #24234F;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        HBox verificationStats = new HBox(15);

        String[][] data = {
                { "Approved", "1,842", "#20A56A" },
                { "Pending", "27", "#C67A00" },
                { "Rejected", "143", "#E53935" },
                { "Approval Rate", "92.8%", "#713CC3" }
        };

        for (String[] item : data) {

            VBox card = new VBox(5);

            card.setPadding(
                    new Insets(18));

            card.setStyle(
                    "-fx-background-color: #FAF8FD;" +
                            "-fx-background-radius: 12px;");

            Label name = new Label(
                    item[0]);

            name.setStyle(
                    "-fx-text-fill: #77778D;" +
                            "-fx-font-size: 12px;");

            Label value = new Label(
                    item[1]);

            value.setStyle(
                    "-fx-text-fill: "
                            + item[2]
                            + ";" +
                            "-fx-font-size: 24px;" +
                            "-fx-font-weight: bold;");

            card.getChildren().addAll(
                    name,
                    value);

            HBox.setHgrow(
                    card,
                    Priority.ALWAYS);

            card.setOnMouseEntered(e -> card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 12px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(113,60,195,0.12), 10, 0, 0, 3);"));

            card.setOnMouseExited(e -> card.setStyle(
                    "-fx-background-color: #FAF8FD;" +
                            "-fx-background-radius: 12px;"));

            verificationStats.getChildren().add(
                    card);
        }

        verificationBox.getChildren().addAll(
                verificationTitle,
                verificationStats);

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
                titleBox,
                filterBar,
                stats,
                chartRow,
                registrationBox,
                verificationBox);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(
                content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: #FAF8FD;");

        root.setCenter(
                scrollPane);

        // =====================================================
        // SCENE
        // =====================================================

        analyticsScene = new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight());

        return analyticsScene;
    }
}