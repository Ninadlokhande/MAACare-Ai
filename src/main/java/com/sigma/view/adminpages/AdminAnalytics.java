package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.effect.DropShadow;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class AdminAnalytics {

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public Node getAnalyticsRoot() {

        VBox mainContent =
            new VBox(20);

        mainContent.setPadding(
            new Insets(30)
        );

        mainContent.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        Label title =
            new Label(
                "Analytics"
            );

        title.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;"
        );


        Label subtitle =
            new Label(
                "Monitor MaaCare AI platform activity, users and registrations."
            );

        subtitle.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 14px;"
        );


        VBox heading =
            new VBox(
                5,
                title,
                subtitle
            );


        // =====================================================
        // STATISTICS
        // =====================================================

        HBox statistics =
            new HBox(15);


        VBox totalUsers =
            createStatCard(
                "Total Users",
                "12,450",
                "+12.5%"
            );


        VBox activeUsers =
            createStatCard(
                "Active Users",
                "10,842",
                "+9.7%"
            );


        VBox doctors =
            createStatCard(
                "Doctors",
                "184",
                "+8.3%"
            );


        VBox hospitals =
            createStatCard(
                "Hospitals",
                "42",
                "+5.2%"
            );


        HBox.setHgrow(
            totalUsers,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            activeUsers,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            doctors,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            hospitals,
            Priority.ALWAYS
        );


        statistics.getChildren().addAll(

            totalUsers,
            activeUsers,
            doctors,
            hospitals
        );


        // =====================================================
        // FIRST CHART ROW
        // =====================================================

        HBox firstCharts =
            new HBox(20);


        VBox userGrowth =
            createUserGrowthChart();


        VBox registrationChart =
            createRegistrationChart();


        HBox.setHgrow(
            userGrowth,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            registrationChart,
            Priority.ALWAYS
        );


        firstCharts.getChildren().addAll(

            userGrowth,

            registrationChart
        );


        // =====================================================
        // SECOND CHART ROW
        // =====================================================

        HBox secondCharts =
            new HBox(20);


        VBox distribution =
            createUserDistributionChart();


        VBox roleDistribution =
            createRoleDistributionChart();


        HBox.setHgrow(
            distribution,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            roleDistribution,
            Priority.ALWAYS
        );


        secondCharts.getChildren().addAll(

            distribution,

            roleDistribution
        );


        // =====================================================
        // SYSTEM ANALYTICS
        // =====================================================

        HBox systemRow =
            new HBox(20);


        VBox engagementBox =
            createAnalyticsBox(
                "Platform Engagement"
            );


        engagementBox.getChildren().addAll(

            createMetricRow(
                "Daily Active Users",
                "4,285"
            ),

            createMetricRow(
                "Weekly Active Users",
                "8,920"
            ),

            createMetricRow(
                "Monthly Active Users",
                "10,842"
            ),

            createMetricRow(
                "Average Session",
                "18 min"
            ),

            createMetricRow(
                "AI Queries Today",
                "3,842"
            )
        );


        VBox registrationSummary =
            createAnalyticsBox(
                "Registration Summary"
            );


        registrationSummary.getChildren().addAll(

            createMetricRow(
                "Mother / Family",
                "8,450"
            ),

            createMetricRow(
                "Doctors",
                "184"
            ),

            createMetricRow(
                "Hospitals",
                "42"
            ),

            createMetricRow(
                "ASHA Workers",
                "156"
            ),

            createMetricRow(
                "Pending Verification",
                "27"
            )
        );


        HBox.setHgrow(
            engagementBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            registrationSummary,
            Priority.ALWAYS
        );


        systemRow.getChildren().addAll(

            engagementBox,

            registrationSummary
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainContent.getChildren().addAll(

            heading,

            statistics,

            firstCharts,

            secondCharts,

            systemRow
        );


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(
                mainContent
            );

        scrollPane.setFitToWidth(
            true
        );

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #F9F7FC;"
        );


        return scrollPane;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            String value,
            String change) {

        VBox card =
            new VBox(8);

        card.setPadding(
            new Insets(18)
        );

        card.setPrefHeight(
            125
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();

        shadow.setRadius(
            12
        );

        shadow.setOffsetY(
            4
        );

        shadow.setColor(
            Color.rgb(
                60,
                30,
                80,
                0.08
            )
        );


        card.setEffect(
            shadow
        );


        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 13px;"
        );


        Label valueLabel =
            new Label(value);

        valueLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;"
        );


        Label changeLabel =
            new Label(
                change +
                " this month"
            );

        changeLabel.setStyle(
            "-fx-text-fill: #20A56A;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );


        card.getChildren().addAll(

            titleLabel,

            valueLabel,

            changeLabel
        );


        return card;
    }


    // =========================================================
    // USER GROWTH
    // =========================================================

    private VBox createUserGrowthChart() {

        VBox box =
            createAnalyticsBox(
                "User Growth"
            );


        CategoryAxis xAxis =
            new CategoryAxis();

        xAxis.setLabel(
            "Month"
        );


        NumberAxis yAxis =
            new NumberAxis();

        yAxis.setLabel(
            "Users"
        );


        LineChart<String, Number> chart =
            new LineChart<>(
                xAxis,
                yAxis
            );


        chart.setLegendVisible(
            false
        );

        chart.setAnimated(
            false
        );

        chart.setCreateSymbols(
            true
        );

        chart.setPrefHeight(
            300
        );


        XYChart.Series<String, Number> series =
            new XYChart.Series<>();


        series.getData().add(
            new XYChart.Data<>(
                "Mar",
                7200
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Apr",
                8100
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "May",
                8950
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Jun",
                10100
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Jul",
                11200
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Aug",
                12450
            )
        );


        chart.getData().add(
            series
        );


        box.getChildren().add(
            chart
        );


        return box;
    }


    // =========================================================
    // MONTHLY REGISTRATIONS
    // =========================================================

    private VBox createRegistrationChart() {

        VBox box =
            createAnalyticsBox(
                "Monthly Registrations"
            );


        CategoryAxis xAxis =
            new CategoryAxis();

        xAxis.setLabel(
            "Month"
        );


        NumberAxis yAxis =
            new NumberAxis();

        yAxis.setLabel(
            "Registrations"
        );


        BarChart<String, Number> chart =
            new BarChart<>(
                xAxis,
                yAxis
            );


        chart.setLegendVisible(
            false
        );

        chart.setAnimated(
            false
        );

        chart.setPrefHeight(
            300
        );


        XYChart.Series<String, Number> series =
            new XYChart.Series<>();


        series.getData().add(
            new XYChart.Data<>(
                "Mar",
                850
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Apr",
                1040
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "May",
                1250
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Jun",
                1430
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Jul",
                1680
            )
        );

        series.getData().add(
            new XYChart.Data<>(
                "Aug",
                1920
            )
        );


        chart.getData().add(
            series
        );


        box.getChildren().add(
            chart
        );


        return box;
    }


    // =========================================================
    // USER DISTRIBUTION
    // =========================================================

    private VBox createUserDistributionChart() {

        VBox box =
            createAnalyticsBox(
                "User Distribution"
            );


        PieChart pieChart =
            new PieChart();


        pieChart.setPrefHeight(
            300
        );


        pieChart.setLabelsVisible(
            true
        );


        pieChart.getData().addAll(

            new PieChart.Data(
                "Mother / Family",
                8450
            ),

            new PieChart.Data(
                "Doctors",
                184
            ),

            new PieChart.Data(
                "Hospitals",
                42
            ),

            new PieChart.Data(
                "ASHA Workers",
                156
            )
        );


        box.getChildren().add(
            pieChart
        );


        return box;
    }


    // =========================================================
    // ROLE DISTRIBUTION
    // =========================================================

    private VBox createRoleDistributionChart() {

        VBox box =
            createAnalyticsBox(
                "Healthcare Provider Distribution"
            );


        PieChart pieChart =
            new PieChart();


        pieChart.setPrefHeight(
            300
        );


        pieChart.setLabelsVisible(
            true
        );


        pieChart.getData().addAll(

            new PieChart.Data(
                "Doctors",
                184
            ),

            new PieChart.Data(
                "Hospitals",
                42
            ),

            new PieChart.Data(
                "ASHA Workers",
                156
            )
        );


        box.getChildren().add(
            pieChart
        );


        return box;
    }


    // =========================================================
    // COMMON ANALYTICS BOX
    // =========================================================

    private VBox createAnalyticsBox(
            String title) {

        VBox box =
            new VBox(12);

        box.setPadding(
            new Insets(20)
        );

        box.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();

        shadow.setRadius(
            12
        );

        shadow.setOffsetY(
            4
        );

        shadow.setColor(
            Color.rgb(
                60,
                30,
                80,
                0.06
            )
        );


        box.setEffect(
            shadow
        );


        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;"
        );


        box.getChildren().add(
            titleLabel
        );


        return box;
    }


    // =========================================================
    // METRIC ROW
    // =========================================================

    private HBox createMetricRow(
            String title,
            String value) {

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 12px;"
        );


        Label valueLabel =
            new Label(value);

        valueLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );


        HBox row =
            new HBox(
                titleLabel,
                valueLabel
            );


        row.setAlignment(
            Pos.CENTER_LEFT
        );


        row.setPadding(
            new Insets(
                8,
                0,
                8,
                0
            )
        );


        HBox.setHgrow(
            titleLabel,
            Priority.ALWAYS
        );


        return row;
    }
}