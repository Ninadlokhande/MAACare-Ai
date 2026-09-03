package com.sigma.view.adminpages;
import com.sigma.view.Loginpage;
import com.sigma.view.Welcomepage;
import com.sigma.view.scenesettings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
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


/**
 * MaaCare AI
 *
 * ADMIN DASHBOARD
 *
 * Architecture:
 *
 * One Scene
 *      |
 *      +--- BorderPane
 *             |
 *             +--- LEFT   = Sidebar
 *             |
 *             +--- TOP    = Page title
 *             |
 *             +--- CENTER = Current page
 *
 *
 * IMPORTANT:
 *
 * All pages are created only once.
 *
 * Clicking a sidebar button only changes:
 *
 *     root.setCenter(existingPage);
 *
 * Therefore pages are NOT recreated every time.
 */
public class AdminDashboard {


    // =========================================================
    // MAIN ROOT
    // =========================================================

    private BorderPane root;

    private Scene adminDashboardScene;
    private Scene loginpageScene = new Loginpage().gotologinpage();

    // =========================================================
    // DASHBOARD CENTER
    // =========================================================

    private ScrollPane dashboardScrollPane;


    // =========================================================
    // PAGE TITLE
    // =========================================================

    private Label pageTitle;


    // =========================================================
    // PERSISTENT PAGE INSTANCES
    // =========================================================

    private AdminNewMember newMemberPage;

    private AdminRequestApproval approvalPage;

    private AdminProfile profilePage;

    private AdminSettings settingsPage;

    private Node analyticsPage;


    // =========================================================
    // PERSISTENT PAGE ROOTS
    // =========================================================

    private Node newMemberRoot;

    private Node approvalRoot;

    private Node profileRoot;

    private Node settingsRoot;
    



    // =========================================================
    // MAIN DASHBOARD
    // =========================================================

    public Scene gotoAdminDashboard() {

        // =====================================================
        // DO NOT REBUILD THE DASHBOARD
        // =====================================================

        if (adminDashboardScene != null) {

            return adminDashboardScene;
        }


        // =====================================================
        // MAIN BORDERPANE
        // =====================================================

        root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        // =====================================================
        // LEFT SIDEBAR
        // =====================================================

        VBox sidebar =
            new VBox(8);

        sidebar.setPrefWidth(230);

        sidebar.setPadding(
            new Insets(
                25,
                15,
                20,
                15
            )
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
                new ImageView(
                    logoImage
                );

            logoView.setPreserveRatio(true);

            logoView.setSmooth(true);

            logoView.fitHeightProperty()
                .bind(
                    root.heightProperty()
                        .multiply(0.12)
                );

            logoView.fitWidthProperty()
                .bind(
                    sidebar.widthProperty()
                        .multiply(0.75)
                );
        }


        Label adminText =
            new Label(
                "ADMIN DASHBOARD"
            );

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
            new Insets(
                0,
                0,
                25,
                10
            )
        );


        if (logoView != null) {

            logoBox.getChildren().add(
                logoView
            );

        } else {

            Label logo =
                new Label(
                    "MaaCare AI"
                );

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

        Button Logout =
            createSideButton(
                "↩",
                "Logout",
                true
            );

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

            settings,

            Logout
        };


        // =====================================================
        // TOP BAR
        // =====================================================

        BorderPane topBar =
            new BorderPane();

        topBar.setPadding(
            new Insets(
                18,
                30,
                18,
                30
            )
        );

        topBar.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E8E2EF;" +
            "-fx-border-width: 0 0 1 0;"
        );


        pageTitle =
            new Label(
                "Dashboard"
            );

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


        topBar.setLeft(
            pageTitle
        );

        topBar.setRight(
            admin
        );


        // =====================================================
        // DASHBOARD CONTENT
        // =====================================================

        VBox centerContent =
            createDashboardContent();


        dashboardScrollPane =
            new ScrollPane(
                centerContent
            );

        dashboardScrollPane.setFitToWidth(
            true
        );

        dashboardScrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        dashboardScrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #F9F7FC;"
        );


        // =====================================================
        // CREATE ALL PAGES ONLY ONCE
        // =====================================================

        try {

            // -------------------------------------------------
            // NEW MEMBER
            // -------------------------------------------------

            newMemberPage =
                new AdminNewMember();

            newMemberRoot =
                newMemberPage.getNewMemberRoot();


            // -------------------------------------------------
            // APPROVAL REQUESTS
            // -------------------------------------------------

            approvalPage =
                new AdminRequestApproval();

            approvalRoot =
                approvalPage
                    .getAdminrequestApprovalRoot();


            // -------------------------------------------------
            // PROFILE
            // -------------------------------------------------

            profilePage =
                new AdminProfile();

            profileRoot =
                profilePage.getProfileRoot();


            // -------------------------------------------------
            // ANALYTICS
            // -------------------------------------------------

            analyticsPage =
                createAnalyticsPage();


            // -------------------------------------------------
            // SETTINGS
            // -------------------------------------------------
            //
            // IMPORTANT:
            //
            // This calls the separate AdminSettings class
            // created earlier.
            //
            // It is created ONLY ONCE.
            // -------------------------------------------------

            settingsPage =
                new AdminSettings();

            settingsRoot =
                settingsPage.getSettingsRoot();


            System.out.println(
                "[ADMIN] Persistent pages initialized"
            );


        } catch (Exception ex) {

            ex.printStackTrace();

            throw new RuntimeException(
                "Failed to initialize Admin Dashboard pages.",
                ex
            );
        }


        // =====================================================
        // DASHBOARD BUTTON
        // =====================================================

        dashboard.setOnAction(e -> {

            setSelectedButton(
                dashboard,
                sidebarButtons
            );

            root.setCenter(
                dashboardScrollPane
            );

            pageTitle.setText(
                "Dashboard"
            );

            System.out.println(
                "[ADMIN] Dashboard opened"
            );
        });


        Logout.setOnAction(e -> {

    System.out.println("[ADMIN] Logging out...");

    // Return to the real Login Scene.
    // Do not place the login root inside the Admin Dashboard center,
    // otherwise the Admin sidebar remains visible.
    if (Welcomepage.stage != null) {

        Welcomepage.stage.setScene(loginpageScene);
        Welcomepage.stage.setMaximized(false);
        Welcomepage.stage.centerOnScreen();
    }

    System.out.println("[ADMIN] Logout successful");
});


        // =====================================================
        // NEW MEMBER
        // =====================================================

        newMember.setOnAction(e -> {

            setSelectedButton(
                newMember,
                sidebarButtons
            );

            // IMPORTANT:
            // Do NOT create AdminNewMember here.

            root.setCenter(
                newMemberRoot
            );

            pageTitle.setText(
                "New Member"
            );

            System.out.println(
                "[ADMIN] New Member opened - existing page reused"
            );
        });


        // =====================================================
        // APPROVAL REQUESTS
        // =====================================================

        approvals.setOnAction(e -> {

            setSelectedButton(
                approvals,
                sidebarButtons
            );

            // IMPORTANT:
            // Do NOT create AdminRequestApproval here.

            root.setCenter(
                approvalRoot
            );

            pageTitle.setText(
                "Approval Requests"
            );

            System.out.println(
                "[ADMIN] Approval Requests opened - existing page reused"
            );
        });


        // =====================================================
        // ANALYTICS
        // =====================================================

        analytics.setOnAction(e -> {

            setSelectedButton(
                analytics,
                sidebarButtons
            );

            // Reuse existing analytics page.

            root.setCenter(
                analyticsPage
            );

            pageTitle.setText(
                "Analytics"
            );

            System.out.println(
                "[ADMIN] Analytics opened - existing page reused"
            );
        });


        // =====================================================
        // PROFILE
        // =====================================================

        profile.setOnAction(e -> {

            setSelectedButton(
                profile,
                sidebarButtons
            );

            // Reuse existing profile page.

            root.setCenter(
                profileRoot
            );

            pageTitle.setText(
                "My Profile"
            );

            System.out.println(
                "[ADMIN] Profile opened - existing page reused"
            );
        });


        // =====================================================
        // SETTINGS
        // =====================================================

        settings.setOnAction(e -> {

            setSelectedButton(
                settings,
                sidebarButtons
            );

            // =================================================
            // IMPORTANT
            // =================================================
            //
            // We are NOT doing:
            //
            // new AdminSettings()
            //
            // here.
            //
            // The page was already created once above.
            //
            // We simply put its existing root in the
            // BorderPane center.
            // =================================================

            root.setCenter(
                settingsRoot
            );

            pageTitle.setText(
                "Settings"
            );

            System.out.println(
                "[ADMIN] Settings opened - existing page reused"
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

    settings,

    Logout
);

        // =====================================================
        // SET BORDERPANE
        // =====================================================

        root.setLeft(
            sidebar
        );

        root.setTop(
            topBar
        );

        root.setCenter(
            dashboardScrollPane
        );


        // =====================================================
        // CREATE ONLY ONE SCENE
        // =====================================================

        adminDashboardScene =
            new Scene(
                root,
                scenesettings.rectanguler2d
                    .getWidth(),
                scenesettings.rectanguler2d
                    .getHeight()
            );


        return adminDashboardScene;
    }


    // =========================================================
    // DASHBOARD CONTENT
    // =========================================================

    private VBox createDashboardContent() {

        VBox centerContent =
            new VBox(20);

        centerContent.setPadding(
            new Insets(30)
        );

        centerContent.setStyle(
            "-fx-background-color: #F9F7FC;"
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
                "124",
                "+12.5% this month"
            );


        VBox doctorsCard =
            createStatCard(
                "Doctors",
                "57",
                "+8.3% this month"
            );


        VBox hospitalsCard =
            createStatCard(
                "Hospitals",
                "35",
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
        // ADD EVERYTHING
        // =====================================================

        centerContent.getChildren().addAll(

            heading,

            stats,

            charts,

            middle,

            lower
        );


        return centerContent;
    }


    // =========================================================
    // ANALYTICS PAGE
    // =========================================================

    private VBox createAnalyticsPage() {

        VBox page =
            new VBox(20);

        page.setPadding(
            new Insets(30)
        );

        page.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


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
                "Monitor MaaCare AI platform activity and verification statistics."
            );

        subtitle.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 14px;"
        );


        HBox stats =
            new HBox(15);


        VBox users =
            createStatCard(
                "Total Users",
                "12,450",
                "+12.5%"
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


        VBox workers =
            createStatCard(
                "ASHA Workers",
                "156",
                "+10.4%"
            );


        HBox.setHgrow(
            users,
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

        HBox.setHgrow(
            workers,
            Priority.ALWAYS
        );


        stats.getChildren().addAll(

            users,

            doctors,

            hospitals,

            workers
        );


        HBox charts =
            new HBox(20);


        VBox growth =
            createUserGrowthChart();


        VBox distribution =
            createUserDistributionChart();


        HBox.setHgrow(
            growth,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            distribution,
            Priority.ALWAYS
        );


        charts.getChildren().addAll(

            growth,

            distribution
        );


        VBox registrations =
            createRegistrationChart();


        page.getChildren().addAll(

            title,

            subtitle,

            stats,

            charts,

            registrations
        );


        ScrollPane scroll =
            new ScrollPane(
                page
            );

        scroll.setFitToWidth(
            true
        );

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #F9F7FC;"
        );


        VBox wrapper =
            new VBox(
                scroll
            );

        VBox.setVgrow(
            scroll,
            Priority.ALWAYS
        );


        return wrapper;
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


        button.setPrefHeight(
            45
        );


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
    // SELECTED BUTTON
    // =========================================================

    private void setSelectedButton(
            Button selectedButton,
            Button[] allButtons) {

        for (
            Button button :
            allButtons
        ) {

            button.setStyle(
                getSidebarButtonStyle(
                    button == selectedButton
                )
            );
        }
    }


    // =========================================================
    // SIDEBAR STYLE
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
                role +
                " • " +
                location
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
            "-fx-text-fill: #D88A20;" +
            "-fx-font-size: 11px;" +
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

    private HBox createActivityRow(
            String title,
            String description,
            String time) {

        VBox content =
            new VBox(3);


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


        content.getChildren().addAll(

            titleLabel,

            descriptionLabel
        );


        Label timeLabel =
            new Label(time);

        timeLabel.setStyle(
            "-fx-text-fill: #9994A5;" +
            "-fx-font-size: 10px;"
        );


        HBox row =
            new HBox(
                15,
                content,
                timeLabel
            );


        row.setAlignment(
            Pos.CENTER_LEFT
        );


        HBox.setHgrow(
            content,
            Priority.ALWAYS
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
            "-fx-font-size: 14px;" +
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