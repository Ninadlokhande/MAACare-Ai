package com.sigma.view.adminpages;

import com.sigma.view.scenesettings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;

public class AdminDashboard {

    private Scene adminDashboardScene;

    public Scene gotoAdminDashboard() {

        // =====================================================
        // MAIN BORDERPANE
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        // =====================================================
        // LEFT SIDEBAR
        // =====================================================

        VBox sidebar = new VBox(8);

        sidebar.setPrefWidth(230);

        sidebar.setPadding(
            new Insets(25, 15, 20, 15)
        );

        sidebar.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E8E2EF;" +
            "-fx-border-width: 0 1 0 0;"
        );


        // =====================================================
        // LOGO
        // =====================================================

        ImageView logoView = null;

        var logoResource =
            getClass().getResource(
                "/assets/images/logo/logo.png"
            );

        if (logoResource != null) {

            Image logoImage =
                new Image(
                    logoResource.toExternalForm()
                );

            logoView =
                new ImageView(logoImage);

            logoView.setPreserveRatio(true);
            logoView.setSmooth(true);

            // Responsive logo size
            logoView.fitHeightProperty().bind(
                root.heightProperty().multiply(0.12)
            );

            logoView.fitWidthProperty().bind(
                sidebar.widthProperty().multiply(0.75)
            );
        }


        Label adminText =
            new Label("ADMIN DASHBOARD");

        adminText.setStyle(
            "-fx-text-fill: #8A8899;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );


        VBox logoBox =
            new VBox(3);

        logoBox.setAlignment(
            Pos.CENTER
        );

        logoBox.setPadding(
            new Insets(0, 0, 25, 10)
        );


        if (logoView != null) {

            logoBox.getChildren().add(
                logoView
            );

        } else {

            Label logo =
                new Label("MaaCare AI");

            logo.setFont(
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    25
                )
            );

            logo.setTextFill(
                Color.web("#D83F82")
            );

            logoBox.getChildren().add(
                logo
            );
        }


        logoBox.getChildren().add(
            adminText
        );


        // =====================================================
        // SIDEBAR BUTTONS
        // =====================================================

        Button dashboard =
            createSideButton(
                "⌂",
                "Dashboard",
                true
            );


        Button newMember =
            createSideButton(
                "＋",
                "New Member",
                false
            );


        Button approvals =
            createSideButton(
                "✓",
                "Approval Requests",
                false
            );


        Button analytics =
            createSideButton(
                "▥",
                "Analytics",
                false
            );


        Button profile =
            createSideButton(
                "♙",
                "My Profile",
                false
            );


        Button settings =
            createSideButton(
                "⚙",
                "Settings",
                false
            );


        // =====================================================
        // ALL SIDEBAR BUTTONS
        // =====================================================

        Button[] sidebarButtons = {
            dashboard,
            newMember,
            approvals,
            analytics,
            profile,
            settings
        };


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        dashboard.setOnAction(e -> {

            setSelectedButton(
                dashboard,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] Dashboard button pressed"
            );
        });


        newMember.setOnAction(e -> {

            setSelectedButton(
                newMember,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] New Member button pressed"
            );
        });


        approvals.setOnAction(e -> {

            setSelectedButton(
                approvals,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] Approval Requests button pressed"
            );
        });


        analytics.setOnAction(e -> {

            setSelectedButton(
                analytics,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] Analytics button pressed"
            );
        });


        profile.setOnAction(e -> {

            setSelectedButton(
                profile,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] My Profile button pressed"
            );
        });


        settings.setOnAction(e -> {

            setSelectedButton(
                settings,
                sidebarButtons
            );

            System.out.println(
                "[ADMIN] Settings button pressed"
            );
        });


        // =====================================================
        // ADD BUTTONS TO SIDEBAR
        // =====================================================

        sidebar.getChildren().addAll(
            logoBox,
            dashboard,
            newMember,
            approvals,
            analytics,
            profile,
            settings
        );


        // =====================================================
        // TOP BAR
        // =====================================================

        BorderPane topBar =
            new BorderPane();

        topBar.setPadding(
            new Insets(18, 30, 18, 30)
        );

        topBar.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E8E2EF;" +
            "-fx-border-width: 0 0 1 0;"
        );


        Label pageTitle =
            new Label("Dashboard");

        pageTitle.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;"
        );


        Label admin =
            new Label(
                "🔔   Admin"
            );

        admin.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );


        topBar.setLeft(pageTitle);

        topBar.setRight(admin);


        // =====================================================
        // CENTER CONTENT
        // =====================================================

        VBox centerContent =
            new VBox(20);

        centerContent.setPadding(
            new Insets(30)
        );

        centerContent.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        ScrollPane scrollPane1 =
            new ScrollPane(
                centerContent
            );

        scrollPane1.setFitToWidth(true);

        scrollPane1.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane1.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #F9F7FC;"
        );


        // =====================================================
        // WELCOME
        // =====================================================

        Label welcome =
            new Label(
                "Welcome back, Admin!"
            );

        welcome.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;"
        );


        Label subtitle =
            new Label(
                "Here's what's happening with MaaCare AI today."
            );

        subtitle.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 14px;"
        );


        VBox heading =
            new VBox(
                5,
                welcome,
                subtitle
            );


        // =====================================================
        // STATISTICS
        // =====================================================

        HBox stats =
            new HBox(15);


        VBox usersCard =
            createStatCard(
                "Total Users",
                "12,450",
                "+12.5% this month"
            );


        VBox doctorsCard =
            createStatCard(
                "Doctors",
                "184",
                "+8.3% this month"
            );


        VBox hospitalsCard =
            createStatCard(
                "Hospitals",
                "42",
                "+5.2% this month"
            );


        VBox approvalsCard =
            createStatCard(
                "Pending Approvals",
                "27",
                "5 new today"
            );


        HBox.setHgrow(
            usersCard,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            doctorsCard,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            hospitalsCard,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            approvalsCard,
            Priority.ALWAYS
        );


        stats.getChildren().addAll(
            usersCard,
            doctorsCard,
            hospitalsCard,
            approvalsCard
        );


        // =====================================================
        // CHARTS
        // =====================================================

        HBox charts =
            new HBox(20);


        VBox userGrowth =
            createUserGrowthChart();


        VBox userDistribution =
            createUserDistributionChart();


        HBox.setHgrow(
            userGrowth,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            userDistribution,
            Priority.ALWAYS
        );


        charts.getChildren().addAll(
            userGrowth,
            userDistribution
        );


        // =====================================================
        // MIDDLE SECTION
        // =====================================================

        HBox middle =
            new HBox(20);


        // =====================================================
        // PENDING APPROVALS
        // =====================================================

        VBox approvalsBox =
            createDashboardBox(
                "Pending Approvals"
            );


        Label approvalInfo =
            new Label(
                "Healthcare providers waiting for verification"
            );

        approvalInfo.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 11px;"
        );


        approvalsBox.getChildren().add(
            approvalInfo
        );


        approvalsBox.getChildren().addAll(

            createApprovalRow(
                "Dr. Rahul Sharma",
                "Doctor",
                "Pune",
                "Pending"
            ),

            createApprovalRow(
                "City Care Hospital",
                "Hospital",
                "Pune",
                "Pending"
            ),

            createApprovalRow(
                "Kavita Patil",
                "ASHA Worker",
                "Pune",
                "Pending"
            ),

            createApprovalRow(
                "Dr. Priya Mehta",
                "Doctor",
                "Mumbai",
                "Pending"
            )
        );


        Button viewApprovals =
            new Button(
                "View All Approvals →"
            );

        viewApprovals.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #713CC3;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );


        viewApprovals.setOnAction(e -> {

            System.out.println(
                "[ADMIN] View All Approvals pressed"
            );
        });


        approvalsBox.getChildren().add(
            viewApprovals
        );


        // =====================================================
        // RECENT ACTIVITY
        // =====================================================

        VBox activityBox =
            createDashboardBox(
                "Recent Activity"
            );


        activityBox.getChildren().addAll(

            createActivityRow(
                "New user registered",
                "Priya Singh joined MaaCare AI",
                "10:30 AM"
            ),

            createActivityRow(
                "Doctor registration",
                "Dr. Rahul Sharma submitted documents",
                "10:15 AM"
            ),

            createActivityRow(
                "Hospital registration",
                "City Care Hospital submitted verification",
                "09:50 AM"
            ),

            createActivityRow(
                "Complaint received",
                "User reported a platform issue",
                "09:20 AM"
            ),

            createActivityRow(
                "Doctor profile updated",
                "Dr. Neha Joshi updated profile",
                "08:55 AM"
            )
        );


        HBox.setHgrow(
            approvalsBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            activityBox,
            Priority.ALWAYS
        );


        middle.getChildren().addAll(
            approvalsBox,
            activityBox
        );


        // =====================================================
        // LOWER SECTION
        // =====================================================

        HBox lower =
            new HBox(20);


        VBox registrationBox =
            createRegistrationChart();


        VBox summaryBox =
            createDashboardBox(
                "System Summary"
            );


        summaryBox.getChildren().addAll(

            createSummaryRow(
                "Mother / Family",
                "8,450"
            ),

            createSummaryRow(
                "Doctors",
                "184"
            ),

            createSummaryRow(
                "Hospitals",
                "42"
            ),

            createSummaryRow(
                "ASHA Workers",
                "156"
            ),

            createSummaryRow(
                "Active Users",
                "10,842"
            ),

            createSummaryRow(
                "Active Complaints",
                "14"
            )
        );


        // =====================================================
        // SYSTEM STATUS
        // =====================================================

        VBox systemStatus =
            new VBox(8);

        systemStatus.setPadding(
            new Insets(15)
        );

        systemStatus.setStyle(
            "-fx-background-color: #F7F1FF;" +
            "-fx-background-radius: 12px;"
        );


        Label statusTitle =
            new Label(
                "System Status"
            );

        statusTitle.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );


        Label serverStatus =
            new Label(
                "●  All systems operational"
            );

        serverStatus.setStyle(
            "-fx-text-fill: #20A56A;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );


        Label databaseStatus =
            new Label(
                "●  Database connected"
            );

        databaseStatus.setStyle(
            "-fx-text-fill: #20A56A;" +
            "-fx-font-size: 11px;"
        );


        Label storageStatus =
            new Label(
                "●  Storage available"
            );

        storageStatus.setStyle(
            "-fx-text-fill: #20A56A;" +
            "-fx-font-size: 11px;"
        );


        systemStatus.getChildren().addAll(
            statusTitle,
            serverStatus,
            databaseStatus,
            storageStatus
        );


        summaryBox.getChildren().add(
            systemStatus
        );


        HBox.setHgrow(
            registrationBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            summaryBox,
            Priority.ALWAYS
        );


        lower.getChildren().addAll(
            registrationBox,
            summaryBox
        );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        centerContent.getChildren().addAll(
            heading,
            stats,
            charts,
            middle,
            lower
        );


        // =====================================================
        // SET BORDERPANE
        // =====================================================

        root.setLeft(sidebar);

        root.setTop(topBar);

        root.setCenter(scrollPane1);


        // =====================================================
        // SCENE
        // =====================================================

        adminDashboardScene =
            new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight()
            );


        return adminDashboardScene;
    }


    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private Button createSideButton(
            String icon,
            String text,
            boolean selected) {

        Button button =
            new Button(
                icon + "    " + text
            );

        button.setMaxWidth(
            Double.MAX_VALUE
        );

        button.setPrefHeight(45);

        button.setAlignment(
            Pos.CENTER_LEFT
        );

        button.setStyle(
            getSidebarButtonStyle(
                selected
            )
        );

        return button;
    }


    // =========================================================
    // SET SELECTED BUTTON
    // =========================================================

    private void setSelectedButton(
            Button selectedButton,
            Button[] allButtons) {

        for (Button button :
                allButtons) {

            if (button == selectedButton) {

                button.setStyle(
                    getSidebarButtonStyle(
                        true
                    )
                );

            } else {

                button.setStyle(
                    getSidebarButtonStyle(
                        false
                    )
                );
            }
        }
    }


    // =========================================================
    // SIDEBAR BUTTON STYLE
    // =========================================================

    private String getSidebarButtonStyle(
            boolean selected) {

        if (selected) {

            return
                "-fx-background-color: #F1E8FF;" +
                "-fx-text-fill: #713CC3;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 15 0 15;";

        } else {

            return
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #24234F;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: normal;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 15 0 15;";
        }
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

        card.setPrefHeight(125);

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();

        shadow.setRadius(12);

        shadow.setOffsetY(4);

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
            new Label(change);

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
    // COMMON DASHBOARD BOX
    // =========================================================

    private VBox createDashboardBox(
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

        shadow.setRadius(12);

        shadow.setOffsetY(4);

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
    // USER GROWTH CHART
    // =========================================================

    private VBox createUserGrowthChart() {

        VBox box =
            createDashboardBox(
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
            270
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
    // USER DISTRIBUTION
    // =========================================================

    private VBox createUserDistributionChart() {

        VBox box =
            createDashboardBox(
                "User Distribution"
            );


        PieChart pieChart =
            new PieChart();


        pieChart.setPrefHeight(
            270
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
    // REGISTRATION BAR CHART
    // =========================================================

    private VBox createRegistrationChart() {

        VBox box =
            createDashboardBox(
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
            270
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
    // APPROVAL ROW
    // =========================================================

    private HBox createApprovalRow(
            String name,
            String role,
            String location,
            String status) {

        VBox person =
            new VBox(3);


        Label nameLabel =
            new Label(name);


        nameLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-weight: bold;"
        );


        Label roleLabel =
            new Label(
                role + " • " + location
            );


        roleLabel.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 11px;"
        );


        person.getChildren().addAll(
            nameLabel,
            roleLabel
        );


        Label statusLabel =
            new Label(status);


        statusLabel.setStyle(
            "-fx-background-color: #FFF0D8;" +
            "-fx-text-fill: #C67A00;" +
            "-fx-padding: 5px 10px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;"
        );


        HBox row =
            new HBox(
                15,
                person,
                statusLabel
            );


        row.setAlignment(
            Pos.CENTER_LEFT
        );


        HBox.setHgrow(
            person,
            Priority.ALWAYS
        );


        return row;
    }


    // =========================================================
    // ACTIVITY ROW
    // =========================================================

    private VBox createActivityRow(
            String title,
            String description,
            String time) {

        Label titleLabel =
            new Label(title);


        titleLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-weight: bold;"
        );


        Label descriptionLabel =
            new Label(description);


        descriptionLabel.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 11px;"
        );


        Label timeLabel =
            new Label(time);


        timeLabel.setStyle(
            "-fx-text-fill: #9999AA;" +
            "-fx-font-size: 10px;"
        );


        VBox row =
            new VBox(
                3,
                titleLabel,
                descriptionLabel,
                timeLabel
            );


        return row;
    }


    // =========================================================
    // SUMMARY ROW
    // =========================================================

    private HBox createSummaryRow(
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


        HBox.setHgrow(
            titleLabel,
            Priority.ALWAYS
        );


        return row;
    }
}