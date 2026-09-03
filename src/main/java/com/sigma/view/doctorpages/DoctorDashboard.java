package com.sigma.view.doctorpages;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.sigma.config.DoctorModule.FirebaseConfig;

import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.controller.doctorController.FeedbackController;
import com.sigma.controller.doctorController.PatientController;
import com.sigma.controller.doctorController.PatientReportController;

import com.sigma.model.DoctorModel.DoctorAppointment;
import com.sigma.model.DoctorModel.FeedbackModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DoctorDashboard extends Application {

        // =========================================================
        // SINGLETON + STAGE
        // =========================================================

        private static DoctorDashboard instance;

        public static Stage dashboardStage;

        private static Scene dashboardScene;

        private static String currentDoctorUid;

        // =========================================================
        // CONTROLLERS
        // =========================================================

        private static DoctorAppointmentController appointmentController;

        private static PatientController patientController;

        private static PatientReportController reportController;

        private static FeedbackController feedbackController;

        // =========================================================
        // STAGE SIZE MEMORY
        // =========================================================

        // =========================================================
        // PROFILE PHOTO
        // =========================================================

        private static ImageView doctorProfileImageView;

        private static Label doctorProfileIcon;

        private static String lastDashboardPhotoUrl = "";

        private static Timeline profilePhotoRefreshTimeline;

        // =========================================================
        // FEEDBACK UI
        // =========================================================

        private static Label dashboardAverageRating;

        private static Label dashboardReviewCount;

        private static VBox dashboardFeedbackList;

        // =========================================================
        // GET INSTANCE
        // =========================================================

        public static DoctorDashboard getInstance() {

                if (instance == null) {
                        instance = new DoctorDashboard();
                }

                return instance;
        }

        // =========================================================
        // CURRENT DOCTOR UID
        // =========================================================

        public static String getCurrentDoctorUid() {

                if (currentDoctorUid != null
                                && !currentDoctorUid.trim().isEmpty()) {

                        return currentDoctorUid;
                }

                return FirebaseConfig.getCurrentDoctorUid();
        }

        // =========================================================
        // SET CURRENT DOCTOR UID
        // =========================================================

        public static void setCurrentDoctorUid(String doctorUid) {

                currentDoctorUid = doctorUid;

                if (doctorUid != null
                                && !doctorUid.trim().isEmpty()) {

                        FirebaseConfig.setCurrentDoctorUid(doctorUid);
                }
        }

        // =========================================================
        // START
        // =========================================================

        @Override
        public void start(Stage stage) throws Exception {

                instance = this;

                dashboardStage = stage;

                dashboardStage.setResizable(true);

                initializeControllers();

                dashboardScene = createDashboardScene();

                dashboardStage.setScene(dashboardScene);

                /*
                 * IMPORTANT:
                 * SceneSettings / scenesettings removed.
                 * DoctorTheme.WIDTH and DoctorTheme.HEIGHT are used.
                 */
                dashboardStage.setWidth(DoctorTheme.WIDTH);

                dashboardStage.setHeight(DoctorTheme.HEIGHT);

                dashboardStage.centerOnScreen();

                dashboardStage.show();

                dashboardStage.toFront();

                dashboardStage.requestFocus();

                loadDashboardDoctorPhoto();

                startProfilePhotoRefresh();

                startRealtimePatientFeedback();
        }

        // =========================================================
        // INITIALIZE CONTROLLERS
        // =========================================================

        private static void initializeControllers() {

                // -----------------------------------------------------
                // APPOINTMENTS
                // -----------------------------------------------------

                try {

                        refreshDashboardAppointments();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // -----------------------------------------------------
                // PATIENTS
                // -----------------------------------------------------

                try {

                        if (patientController == null) {

                                patientController = new PatientController();

                        } else {

                                patientController.refreshPatients();
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // -----------------------------------------------------
                // REPORTS
                // -----------------------------------------------------

                try {

                        if (reportController == null) {

                                reportController = new PatientReportController();

                        } else {

                                reportController.refreshReports();
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // -----------------------------------------------------
                // FEEDBACK
                // -----------------------------------------------------

                try {

                        if (feedbackController == null) {

                                feedbackController = new FeedbackController(
                                                FirebaseConfig.getFirestore());
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================================
        // REFRESH APPOINTMENTS
        // =========================================================

        private static void refreshDashboardAppointments() {

                try {

                        if (appointmentController == null) {

                                appointmentController = new DoctorAppointmentController();

                        } else {

                                appointmentController.refreshAppointments();
                        }

                        System.out.println(
                                        "[DASHBOARD] Latest appointments loaded from Firestore.");

                } catch (Exception e) {

                        System.out.println(
                                        "[DASHBOARD ERROR] Appointment refresh failed.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // SHOW DASHBOARD WITH UID
        // =========================================================

        public static void showDashboard(Stage stage, String doctorUid) {

                dashboardStage = stage;
                showDashboard(doctorUid);
        }

        public static void showDashboard(String doctorUid) {

                try {

                        setCurrentDoctorUid(doctorUid);

                        DoctorDashboard dashboard = getInstance();

                        if (dashboardStage == null) {

                                dashboardStage = new Stage();

                                dashboard.start(dashboardStage);

                                return;
                        }

                        refreshDashboardAppointments();

                        try {

                                if (patientController == null) {

                                        patientController = new PatientController();

                                } else {

                                        patientController.refreshPatients();
                                }

                        } catch (Exception e) {

                                e.printStackTrace();
                        }

                        try {

                                if (reportController == null) {

                                        reportController = new PatientReportController();

                                } else {

                                        reportController.refreshReports();
                                }

                        } catch (Exception e) {

                                e.printStackTrace();
                        }

                        dashboardScene = createDashboardScene();

                        dashboardStage.setScene(dashboardScene);

                        dashboardStage.show();

                        dashboardStage.toFront();

                        dashboardStage.requestFocus();

                        loadDashboardDoctorPhoto();

                        startRealtimePatientFeedback();

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================================
        // SHOW DASHBOARD WITHOUT UID
        // =========================================================

        public static void showDashboard() {

                try {

                        String uid = getCurrentDoctorUid();

                        if (uid != null
                                        && !uid.trim().isEmpty()) {

                                showDashboard(uid);

                                return;
                        }

                        DoctorDashboard dashboard = getInstance();

                        if (dashboardStage == null) {

                                dashboardStage = new Stage();

                                dashboard.start(dashboardStage);

                        } else {

                                refreshDashboardAppointments();

                                dashboardScene = createDashboardScene();

                                dashboardStage.setScene(dashboardScene);

                                dashboardStage.show();

                                dashboardStage.toFront();

                                dashboardStage.requestFocus();

                                loadDashboardDoctorPhoto();

                                startRealtimePatientFeedback();
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================================
        // CHANGE SCENE - SAME STAGE
        // =========================================================

        public static void changeScene(Scene newScene) {

                if (dashboardStage == null
                                || newScene == null) {

                        return;
                }

                try {

                        dashboardStage.setScene(newScene);

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        public static void setStandardWindowSize() {

                if (dashboardStage == null) {
                        return;
                }

                dashboardStage.setMaximized(false);
                dashboardStage.setWidth(DoctorTheme.WIDTH);
                dashboardStage.setHeight(DoctorTheme.HEIGHT);
                dashboardStage.centerOnScreen();
        }

        // =========================================================
        // CONTROLLER GETTERS
        // =========================================================

        public static DoctorAppointmentController getAppointmentController() {

                if (appointmentController == null) {

                        appointmentController = new DoctorAppointmentController();
                }

                return appointmentController;
        }

        public static PatientController getPatientController() {

                if (patientController == null) {

                        patientController = new PatientController();
                }

                return patientController;
        }

        public static PatientReportController getReportController() {

                if (reportController == null) {

                        reportController = new PatientReportController();
                }

                return reportController;
        }

        public static FeedbackController getFeedbackController() {

                if (feedbackController == null) {

                        feedbackController = new FeedbackController(
                                        FirebaseConfig.getFirestore());
                }

                return feedbackController;
        }

        // =========================================================
        // CREATE DASHBOARD SCENE
        // =========================================================

        private static Scene createDashboardScene() {

                BorderPane root = new BorderPane();

                DoctorTheme.applyBackground(root);

                root.setLeft(
                                createSidebar());

                root.setCenter(
                                createDashboardContent());

                /*
                 * IMPORTANT:
                 * SceneSettings completely removed.
                 */
                return new Scene(
                                root,
                                DoctorTheme.WIDTH,
                                DoctorTheme.HEIGHT);
        }

        // =========================================================
        // SIDEBAR
        // =========================================================

        public static VBox createSidebar() {

                return createSidebar("Dashboard");
        }

        public static VBox createSidebar(String activePage) {

                VBox sidebar = new VBox(8);

                sidebar.setPrefWidth(235);

                sidebar.setMinWidth(235);

                sidebar.setMaxWidth(235);

                sidebar.setPadding(
                                new Insets(
                                                22,
                                                18,
                                                18,
                                                18));

                sidebar.setStyle(
                                "-fx-background-color: #FFF0F6;"
                                                + "-fx-border-color: "
                                                + DoctorTheme.BORDER
                                                + ";"
                                                + "-fx-border-width: 0 1 0 0;");

                Image logoImage = new Image(
                                DoctorDashboard.class.getResource(
                                                "/assets/images/logo/logo.png")
                                                .toExternalForm());

                ImageView logoView = new ImageView(logoImage);

                logoView.setFitWidth(200);
                logoView.setFitHeight(100);
                logoView.setPreserveRatio(true);

                StackPane logoBox = new StackPane(logoView);
                logoBox.setPrefHeight(112);
                logoBox.setMaxWidth(Double.MAX_VALUE);

                // -----------------------------------------------------
                // DOCTOR INFO
                // -----------------------------------------------------

                VBox doctorBox = new VBox(2);

                doctorBox.setPadding(
                                new Insets(
                                                18,
                                                5,
                                                12,
                                                5));

                Label doctor = new Label(
                                "Doctor");

                doctor.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                16));

                doctor.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label specialist = new Label(
                                "Obstetrician & Gynecologist");

                specialist.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                11));

                specialist.setTextFill(
                                Color.web(DoctorTheme.SECONDARY_TEXT));

                Label online = new Label(
                                "● Online");

                online.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                11));

                online.setTextFill(
                                Color.web(DoctorTheme.GREEN));

                doctorBox.getChildren().addAll(
                                doctor,
                                specialist,
                                online);

                // -----------------------------------------------------
                // SEPARATOR
                // -----------------------------------------------------

                Separator separator = new Separator();

                separator.setOpacity(0.5);

                // -----------------------------------------------------
                // MENU BUTTONS
                // -----------------------------------------------------

                Button dashboard = createMenuButton(
                                "🏠",
                                "Dashboard",
                                "Dashboard".equalsIgnoreCase(activePage));

                Button appointments = createMenuButton(
                                "📅",
                                "Appointments",
                                "Appointments".equalsIgnoreCase(activePage));

                Button patients = createMenuButton(
                                "👩‍⚕️",
                                "Patients",
                                "Patients".equalsIgnoreCase(activePage));

                Button reports = createMenuButton(
                                "📋",
                                "Reports",
                                "Reports".equalsIgnoreCase(activePage));

                Button settings = createMenuButton(
                                "⚙️",
                                "Settings",
                                "Settings".equalsIgnoreCase(activePage));

                // -----------------------------------------------------
                // ACTIONS
                // -----------------------------------------------------

                dashboard.setOnAction(
                                e -> showDashboard());

                appointments.setOnAction(
                                e -> DoctorAppointmentsPage.show());

                patients.setOnAction(
                                e -> PatientsPage.show());

                reports.setOnAction(
                                e -> PatientReportsPage.show());

                settings.setOnAction(
                                e -> SettingsPage.display());

                sidebar.getChildren().addAll(
                                logoBox,
                                doctorBox,
                                separator,
                                dashboard,
                                appointments,
                                patients,
                                reports,
                                settings);

                return sidebar;
        }

        // =========================================================
        // MENU BUTTON
        // =========================================================

        private static Button createMenuButton(
                        String icon,
                        String text,
                        boolean active) {

                Button button = new Button();

                button.setPrefHeight(44);
                button.setMinHeight(44);
                button.setMaxHeight(44);
                button.setMaxWidth(
                                Double.MAX_VALUE);

                HBox content = new HBox(12);

                content.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';"
                                                + "-fx-font-size: 22px;"
                                                + "-fx-effect: dropshadow(gaussian, rgba(139,92,246,0.18), 4, 0.2, 0, 1);");
                iconLabel.setPrefWidth(34);
                iconLabel.setMinWidth(34);
                iconLabel.setAlignment(Pos.CENTER);

                Label textLabel = new Label(text);

                textLabel.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                15));

                content.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(content);

                button.setText("");

                if (active) {

                        button.setStyle(
                                        "-fx-background-color: "
                                                        + DoctorTheme.PRIMARY_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding: 10 12 10 12;"
                                                        + "-fx-cursor: hand;");

                        iconLabel.setTextFill(
                                        Color.web(DoctorTheme.PRIMARY));

                        textLabel.setTextFill(
                                        Color.web(DoctorTheme.PRIMARY));

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding: 10 12 10 12;"
                                                        + "-fx-cursor: hand;");

                        iconLabel.setTextFill(
                                        Color.web(DoctorTheme.TEXT));

                        textLabel.setTextFill(
                                        Color.web(DoctorTheme.TEXT));
                }

                return button;
        }

        // =========================================================
        // DASHBOARD CONTENT
        // =========================================================

        private static VBox createDashboardContent() {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                24,
                                                28,
                                                24,
                                                28));

                content.setFillWidth(true);

                content.setMaxWidth(
                                Double.MAX_VALUE);

                DoctorTheme.applyBackground(content);

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox welcome = new VBox(5);

                Label title = new Label(
                                "Welcome, Doctor!");

                title.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label sub = DoctorTheme.subtitle(
                                "Here's what's happening in your clinic today.");

                welcome.getChildren().addAll(
                                title,
                                sub);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // -----------------------------------------------------
                // DATE
                // -----------------------------------------------------

                String currentDate = LocalDate.now().format(
                                DateTimeFormatter.ofPattern(
                                                "dd MMMM yyyy"));

                Label date = new Label(
                                currentDate + "   📅");

                date.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                11));

                date.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                date.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + DoctorTheme.BORDER
                                                + ";"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 10 14;");

                // -----------------------------------------------------
                // PROFILE
                // -----------------------------------------------------

                StackPane profileAvatar = createDashboardProfileAvatar();

                HBox.setMargin(
                                profileAvatar,
                                new Insets(
                                                0,
                                                0,
                                                0,
                                                12));

                header.getChildren().addAll(
                                welcome,
                                spacer,
                                date,
                                profileAvatar);

                // =====================================================
                // STATISTICS
                // =====================================================

                int todayAppointmentCount = 0;

                int patientCount = 0;

                int reportCount = 0;

                if (appointmentController != null) {

                        try {

                                todayAppointmentCount = appointmentController
                                                .getTodayAppointmentCount();

                        } catch (Exception e) {

                                todayAppointmentCount = 0;
                        }
                }

                if (patientController != null) {

                        try {

                                patientCount = patientController
                                                .getPatientCount();

                        } catch (Exception e) {

                                patientCount = 0;
                        }
                }

                if (reportController != null) {

                        try {

                                if (reportController.getReports() != null) {

                                        reportCount = reportController
                                                        .getReports()
                                                        .size();
                                }

                        } catch (Exception e) {

                                reportCount = 0;
                        }
                }

                HBox stats = new HBox(15);

                stats.setFillHeight(true);

                stats.setMaxWidth(
                                Double.MAX_VALUE);

                HBox.setHgrow(
                                stats,
                                Priority.ALWAYS);

                stats.getChildren().addAll(

                                statCard(
                                                "🗓",
                                                String.valueOf(
                                                                todayAppointmentCount),
                                                "Today's",
                                                "Appointments"),

                                statCard(
                                                "👤",
                                                String.valueOf(
                                                                patientCount),
                                                "Patients",
                                                "Registered"),

                                statCard(
                                                "▤",
                                                String.valueOf(
                                                                reportCount),
                                                "Reports",
                                                "Available"),

                                statCard(
                                                "★",
                                                "Loading...",
                                                "Average Rating",
                                                "Realtime"));

                // =====================================================
                // MIDDLE
                // =====================================================

                HBox middle = new HBox(18);

                middle.setFillHeight(true);

                middle.setMaxWidth(
                                Double.MAX_VALUE);

                // =====================================================
                // TODAY APPOINTMENTS
                // =====================================================

                VBox appointments = DoctorTheme.card();

                HBox.setHgrow(
                                appointments,
                                Priority.ALWAYS);

                appointments.setPrefHeight(330);

                appointments.setMinHeight(330);

                appointments.setMaxWidth(
                                Double.MAX_VALUE);

                Label appointmentTitle = new Label(
                                "Today's Appointments");

                appointmentTitle.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                16));

                appointmentTitle.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                appointments.getChildren().add(
                                appointmentTitle);

                // -----------------------------------------------------
                // APPOINTMENT DATA
                // -----------------------------------------------------

                if (appointmentController != null) {

                        List<DoctorAppointment> todayAppointments = appointmentController
                                        .getTodayAppointments();

                        if (todayAppointments == null
                                        || todayAppointments.isEmpty()) {

                                Label noAppointment = new Label(
                                                "No appointments for today.");

                                noAppointment.setFont(
                                                Font.font(
                                                                DoctorTheme.FONT,
                                                                FontWeight.NORMAL,
                                                                13));

                                noAppointment.setTextFill(
                                                Color.web(
                                                                DoctorTheme.SECONDARY_TEXT));

                                noAppointment.setPadding(
                                                new Insets(
                                                                20,
                                                                0,
                                                                0,
                                                                0));

                                appointments.getChildren().add(
                                                noAppointment);

                        } else {

                                for (DoctorAppointment appointment : todayAppointments) {

                                        String details = appointment.getType();

                                        if (details == null
                                                        || details.trim().isEmpty()) {

                                                details = "Consultation";
                                        }

                                        appointments.getChildren().add(
                                                        appointmentRow(
                                                                        appointment.getTime(),
                                                                        appointment.getPatient(),
                                                                        details,
                                                                        appointment.getStatus()));
                                }
                        }

                } else {

                        Label loading = new Label(
                                        "Appointments loading...");

                        loading.setTextFill(
                                        Color.web(
                                                        DoctorTheme.SECONDARY_TEXT));

                        appointments.getChildren().add(
                                        loading);
                }

                // =====================================================
                // TODAY SCHEDULE
                // =====================================================

                VBox schedule = DoctorTheme.card();

                HBox.setHgrow(
                                schedule,
                                Priority.ALWAYS);

                schedule.setMaxWidth(
                                Double.MAX_VALUE);

                schedule.setPrefHeight(330);

                schedule.setMinHeight(330);

                Label scheduleTitle = new Label(
                                "Today's Schedule");

                scheduleTitle.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                16));

                scheduleTitle.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                schedule.getChildren().add(
                                scheduleTitle);

                if (appointmentController != null) {

                        List<DoctorAppointment> todayAppointments = appointmentController
                                        .getTodayAppointments();

                        if (todayAppointments == null
                                        || todayAppointments.isEmpty()) {

                                Label noSchedule = new Label(
                                                "No schedule for today.");

                                noSchedule.setTextFill(
                                                Color.web(
                                                                DoctorTheme.SECONDARY_TEXT));

                                noSchedule.setPadding(
                                                new Insets(
                                                                20,
                                                                0,
                                                                0,
                                                                0));

                                schedule.getChildren().add(
                                                noSchedule);

                        } else {

                                for (DoctorAppointment appointment : todayAppointments) {

                                        String type = appointment.getType();

                                        if (type == null
                                                        || type.trim().isEmpty()) {

                                                type = "Consultation";
                                        }

                                        schedule.getChildren().add(
                                                        scheduleRow(
                                                                        appointment.getTime(),
                                                                        appointment.getPatient(),
                                                                        type));
                                }
                        }

                } else {

                        Label loading = new Label(
                                        "Schedule loading...");

                        loading.setTextFill(
                                        Color.web(
                                                        DoctorTheme.SECONDARY_TEXT));

                        schedule.getChildren().add(
                                        loading);
                }

                middle.getChildren().addAll(
                                appointments,
                                schedule);

                // =====================================================
                // FEEDBACK
                // =====================================================

                VBox feedback = createFeedbackSection();

                HBox.setHgrow(
                                feedback,
                                Priority.ALWAYS);

                // =====================================================
                // ADD CONTENT
                // =====================================================

                content.getChildren().addAll(
                                header,
                                stats,
                                createAppointmentOverviewCard(),
                                middle,
                                feedback);

                return content;
        }

        private static VBox createAppointmentOverviewCard() {

                VBox card = DoctorTheme.card();
                card.setPrefHeight(230);
                card.setMinHeight(230);
                card.setMaxWidth(Double.MAX_VALUE);

                HBox heading = new HBox();
                heading.setAlignment(Pos.CENTER_LEFT);

                Label title = new Label("Appointment Overview");
                title.setFont(Font.font(DoctorTheme.FONT, FontWeight.BOLD, 16));
                title.setTextFill(Color.web(DoctorTheme.TEXT));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label caption = DoctorTheme.subtitle("By current status");
                heading.getChildren().addAll(title, spacer, caption);

                PieChart chart = new PieChart();
                chart.setLegendVisible(true);
                chart.setLabelsVisible(true);
                chart.setStartAngle(90);
                chart.setClockwise(true);
                chart.setAnimated(false);
                chart.setPrefSize(560, 175);
                chart.setMinHeight(175);

                int pending = 0;
                int confirmed = 0;
                int completed = 0;
                int cancelled = 0;

                if (appointmentController != null) {
                        for (DoctorAppointment appointment : appointmentController.getAppointments()) {
                                String status = appointment.getStatus() == null
                                                ? "Pending"
                                                : appointment.getStatus();

                                if (status.equalsIgnoreCase("Confirmed")) {
                                        confirmed++;
                                } else if (status.equalsIgnoreCase("Completed")) {
                                        completed++;
                                } else if (status.equalsIgnoreCase("Cancelled")) {
                                        cancelled++;
                                } else {
                                        pending++;
                                }
                        }
                }

                if (pending + confirmed + completed + cancelled == 0) {
                        chart.setData(FXCollections.observableArrayList(
                                        new PieChart.Data("No appointments", 1)));
                } else {
                        chart.setData(FXCollections.observableArrayList(
                                        new PieChart.Data("Pending", pending),
                                        new PieChart.Data("Confirmed", confirmed),
                                        new PieChart.Data("Completed", completed),
                                        new PieChart.Data("Cancelled", cancelled)));
                }

                chart.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 12px;"
                                + "-fx-background-color: transparent;");
                card.getChildren().addAll(heading, chart);
                return card;
        }

        // =========================================================
        // STAT CARD
        // =========================================================

        private static VBox statCard(
                        String icon,
                        String number,
                        String line1,
                        String line2) {

                VBox box = DoctorTheme.card();

                box.setPrefHeight(104);

                box.setMinHeight(104);

                box.setMaxWidth(
                                Double.MAX_VALUE);

                HBox.setHgrow(
                                box,
                                Priority.ALWAYS);

                HBox row = new HBox(12);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';"
                                                + "-fx-font-size: 27px;"
                                                + "-fx-text-fill: #E84A87;");
                iconLabel.setAlignment(Pos.CENTER);

                StackPane iconBadge = new StackPane(iconLabel);
                iconBadge.setMinSize(52, 52);
                iconBadge.setPrefSize(52, 52);
                iconBadge.setMaxSize(52, 52);
                iconBadge.setStyle("-fx-background-color: #FFF0F6;"
                                + "-fx-background-radius: 26;"
                                + "-fx-border-color: #F7C7D8;"
                                + "-fx-border-radius: 26;"
                                + "-fx-border-width: 1;");

                VBox text = new VBox(2);

                Label num = new Label(number);

                num.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                20));

                num.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label firstLine = DoctorTheme.subtitle(line1);

                Label secondLine = DoctorTheme.subtitle(line2);

                text.getChildren().addAll(
                                num,
                                firstLine,
                                secondLine);

                row.getChildren().addAll(
                                iconBadge,
                                text);

                box.getChildren().add(
                                row);

                return box;
        }

        // =========================================================
        // APPOINTMENT ROW
        // =========================================================

        private static HBox appointmentRow(
                        String time,
                        String patient,
                        String details,
                        String status) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                12,
                                                0,
                                                12,
                                                0));

                Label t = new Label(
                                time == null
                                                ? ""
                                                : time);

                t.setPrefWidth(75);

                t.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                12));

                t.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                VBox patientBox = new VBox(2);

                Label p = new Label(
                                patient == null
                                                ? ""
                                                : patient);

                p.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                13));

                p.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                patientBox.getChildren().addAll(
                                p,
                                DoctorTheme.subtitle(
                                                details == null
                                                                ? ""
                                                                : details));

                HBox.setHgrow(
                                patientBox,
                                Priority.ALWAYS);

                String actualStatus = status == null
                                || status.trim().isEmpty()
                                                ? "Pending"
                                                : status;

                Label s = new Label(actualStatus);

                s.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                9));

                s.setPadding(
                                new Insets(
                                                5,
                                                9,
                                                5,
                                                9));

                if (actualStatus.equalsIgnoreCase(
                                "Pending")) {

                        s.setTextFill(
                                        Color.web(DoctorTheme.ORANGE));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + DoctorTheme.ORANGE_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 12;");

                } else if (actualStatus.equalsIgnoreCase(
                                "Cancelled")) {

                        s.setTextFill(
                                        Color.web("#D93636"));

                        s.setStyle(
                                        "-fx-background-color: #FFF0F0;"
                                                        + "-fx-background-radius: 12;");

                } else {

                        s.setTextFill(
                                        Color.web(DoctorTheme.GREEN));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + DoctorTheme.GREEN_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 12;");
                }

                row.getChildren().addAll(
                                t,
                                patientBox,
                                s);

                return row;
        }

        // =========================================================
        // SCHEDULE ROW
        // =========================================================

        private static HBox scheduleRow(
                        String time,
                        String name,
                        String type) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                12,
                                                0,
                                                12,
                                                0));

                VBox details = new VBox(3);

                details.getChildren().add(
                                DoctorTheme.subtitle(
                                                time == null
                                                                ? ""
                                                                : time));

                Label n = new Label(
                                name == null
                                                ? ""
                                                : name);

                n.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                11));

                n.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                details.getChildren().add(n);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                row.getChildren().addAll(
                                details,
                                spacer,
                                DoctorTheme.subtitle(
                                                type == null
                                                                ? ""
                                                                : type));

                return row;
        }

        // =========================================================
        // FEEDBACK SECTION
        // =========================================================

        private static VBox createFeedbackSection() {

                VBox feedbackCard = new VBox(12);

                feedbackCard.setPadding(
                                new Insets(
                                                18,
                                                20,
                                                18,
                                                20));

                feedbackCard.setPrefHeight(240);

                feedbackCard.setMinHeight(240);

                feedbackCard.setMaxWidth(
                                Double.MAX_VALUE);

                feedbackCard.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 16;"
                                                + "-fx-border-color: "
                                                + DoctorTheme.BORDER
                                                + ";"
                                                + "-fx-border-radius: 16;"
                                                + "-fx-border-width: 1;");

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titleBox = new VBox(3);

                Label title = new Label(
                                "Patient Feedback");

                title.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                17));

                title.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Label subtitle = new Label(
                                "What your patients are saying");

                subtitle.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                10));

                subtitle.setTextFill(
                                Color.web(
                                                DoctorTheme.SECONDARY_TEXT));

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                VBox ratingBox = new VBox(1);

                ratingBox.setAlignment(
                                Pos.CENTER_RIGHT);

                dashboardAverageRating = new Label(
                                "0.0  ☆☆☆☆☆");

                dashboardAverageRating.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                17));

                dashboardAverageRating.setTextFill(
                                Color.web(DoctorTheme.PRIMARY));

                dashboardReviewCount = new Label(
                                "Based on 0 reviews");

                dashboardReviewCount.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                9));

                dashboardReviewCount.setTextFill(
                                Color.web(
                                                DoctorTheme.SECONDARY_TEXT));

                ratingBox.getChildren().addAll(
                                dashboardAverageRating,
                                dashboardReviewCount);

                header.getChildren().addAll(
                                titleBox,
                                spacer,
                                ratingBox);

                Separator separator = new Separator();

                separator.setOpacity(0.5);

                dashboardFeedbackList = new VBox(7);

                dashboardFeedbackList.setPadding(
                                new Insets(
                                                2,
                                                0,
                                                0,
                                                0));

                Label loading = new Label(
                                "Loading patient feedback...");

                loading.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                11));

                loading.setTextFill(
                                Color.web(
                                                DoctorTheme.SECONDARY_TEXT));

                dashboardFeedbackList.getChildren().add(
                                loading);

                feedbackCard.getChildren().addAll(
                                header,
                                separator,
                                dashboardFeedbackList);

                return feedbackCard;
        }

        // =========================================================
        // FEEDBACK ITEM
        // =========================================================

        private static HBox createFeedbackItem(
                        FeedbackModel feedback) {

                HBox card = new HBox(12);

                card.setAlignment(
                                Pos.TOP_LEFT);

                card.setPadding(
                                new Insets(
                                                5,
                                                10,
                                                5,
                                                10));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: #FAF8FF;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-border-color: #EEE8F8;"
                                                + "-fx-border-radius: 10;");

                StackPane avatar = createPatientAvatar(
                                feedback.getPatientName());

                VBox details = new VBox(2);

                HBox.setHgrow(
                                details,
                                Priority.ALWAYS);

                HBox nameDateRow = new HBox();

                nameDateRow.setAlignment(
                                Pos.CENTER_LEFT);

                String patientName = feedback.getPatientName();

                if (patientName == null
                                || patientName.trim().isEmpty()) {

                        patientName = "Patient";
                }

                Label name = new Label(patientName);

                name.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                11));

                name.setTextFill(
                                Color.web(DoctorTheme.TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label date = new Label(
                                formatFeedbackDate(
                                                feedback.getTimestamp()));

                date.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                9));

                date.setTextFill(
                                Color.web(
                                                DoctorTheme.SECONDARY_TEXT));

                nameDateRow.getChildren().addAll(
                                name,
                                spacer,
                                date);

                HBox stars = createStarRating(
                                feedback.getRating());

                String comment = feedback.getComment();

                if (comment == null
                                || comment.trim().isEmpty()) {

                        comment = "No comment provided.";
                }

                Label commentLabel = new Label(
                                "\"" + comment + "\"");

                commentLabel.setWrapText(true);

                commentLabel.setMaxWidth(
                                Double.MAX_VALUE);

                commentLabel.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.NORMAL,
                                                9.5));

                commentLabel.setTextFill(
                                Color.web(
                                                DoctorTheme.SECONDARY_TEXT));

                details.getChildren().addAll(
                                nameDateRow,
                                stars,
                                commentLabel);

                card.getChildren().addAll(
                                avatar,
                                details);

                return card;
        }

        // =========================================================
        // PATIENT AVATAR
        // =========================================================

        private static StackPane createPatientAvatar(
                        String patientName) {

                StackPane avatar = new StackPane();

                avatar.setPrefSize(
                                34,
                                34);

                avatar.setMinSize(
                                34,
                                34);

                avatar.setMaxSize(
                                38,
                                38);

                Circle circle = new Circle(19);

                circle.setFill(
                                Color.web(
                                                DoctorTheme.PRIMARY_LIGHT));

                Label initialsLabel = new Label(
                                getInitials(patientName));

                initialsLabel.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                12));

                initialsLabel.setTextFill(
                                Color.web(DoctorTheme.PRIMARY));

                avatar.getChildren().addAll(
                                circle,
                                initialsLabel);

                return avatar;
        }

        // =========================================================
        // INITIALS
        // =========================================================

        private static String getInitials(
                        String name) {

                if (name == null
                                || name.trim().isEmpty()) {

                        return "P";
                }

                String[] parts = name.trim().split("\\s+");

                if (parts.length == 1) {

                        return parts[0]
                                        .substring(0, 1)
                                        .toUpperCase();
                }

                return (parts[0].substring(0, 1)
                                + parts[parts.length - 1]
                                                .substring(0, 1))
                                .toUpperCase();
        }

        // =========================================================
        // STAR RATING
        // =========================================================

        private static HBox createStarRating(
                        double rating) {

                HBox stars = new HBox(2);

                stars.setAlignment(
                                Pos.CENTER_LEFT);

                int rounded = (int) Math.round(rating);

                if (rounded < 0) {
                        rounded = 0;
                }

                if (rounded > 5) {
                        rounded = 5;
                }

                for (int i = 0; i < 5; i++) {

                        Label star = new Label(
                                        i < rounded
                                                        ? "★"
                                                        : "☆");

                        star.setFont(
                                        Font.font(
                                                        "Segoe UI Symbol",
                                                        FontWeight.BOLD,
                                                        12));

                        star.setTextFill(
                                        Color.web("#F4B400"));

                        stars.getChildren().add(
                                        star);
                }

                Label ratingLabel = new Label(
                                String.format(
                                                "%.1f",
                                                rating));

                ratingLabel.setFont(
                                Font.font(
                                                DoctorTheme.FONT,
                                                FontWeight.BOLD,
                                                9));

                ratingLabel.setTextFill(
                                Color.web(DoctorTheme.PRIMARY));

                stars.getChildren().add(
                                ratingLabel);

                return stars;
        }

        // =========================================================
        // FEEDBACK DATE
        // =========================================================

        private static String formatFeedbackDate(
                        Date date) {

                if (date == null) {
                        return "";
                }

                try {

                        SimpleDateFormat formatter = new SimpleDateFormat(
                                        "dd MMM yyyy");

                        return formatter.format(date);

                } catch (Exception e) {

                        return "";
                }
        }

        // =========================================================
        // START REALTIME FEEDBACK
        // =========================================================

        private static void startRealtimePatientFeedback() {

                String doctorUid = getCurrentDoctorUid();

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        Platform.runLater(
                                        () -> updateDashboardFeedback(
                                                        Collections.emptyList()));
                        return;
                }

                try {

                        if (feedbackController == null) {

                                feedbackController = new FeedbackController(
                                                FirebaseConfig.getFirestore());
                        }

                        FeedbackController currentFeedbackController = feedbackController;

                        Thread initialFeedbackThread = new Thread(() -> {

                                try {
                                        List<FeedbackModel> feedbackList = currentFeedbackController
                                                        .getDoctorFeedback(doctorUid);

                                        Platform.runLater(
                                                        () -> updateDashboardFeedback(feedbackList));
                                } catch (Exception exception) {
                                        System.out.println(
                                                        "[FEEDBACK] Initial rating load failed: "
                                                                        + exception.getMessage());
                                        Platform.runLater(
                                                        () -> updateDashboardFeedback(
                                                                        Collections.emptyList()));
                                }
                        });

                        initialFeedbackThread.setDaemon(true);
                        initialFeedbackThread.setName("Doctor-Feedback-Initial-Load");
                        initialFeedbackThread.start();

                        feedbackController
                                        .startRealtimeFeedbackListener(
                                                        doctorUid,
                                                        feedbackList -> Platform.runLater(
                                                                        () -> updateDashboardFeedback(
                                                                                        feedbackList)));

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================================
        // UPDATE FEEDBACK
        // =========================================================

        private static void updateDashboardFeedback(
                        List<FeedbackModel> feedbackList) {

                if (dashboardAverageRating == null
                                || dashboardReviewCount == null) {

                        return;
                }

                if (feedbackList == null
                                || feedbackList.isEmpty()) {

                        dashboardAverageRating.setText(
                                        "0.0  ☆☆☆☆☆");

                        dashboardReviewCount.setText(
                                        "Based on 0 reviews");

                        if (dashboardFeedbackList != null) {

                                dashboardFeedbackList
                                                .getChildren()
                                                .clear();

                                Label noFeedback = new Label(
                                                "No patient feedback yet.");

                                noFeedback.setFont(
                                                Font.font(
                                                                DoctorTheme.FONT,
                                                                FontWeight.NORMAL,
                                                                11));

                                noFeedback.setTextFill(
                                                Color.web(
                                                                DoctorTheme.SECONDARY_TEXT));

                                dashboardFeedbackList
                                                .getChildren()
                                                .add(
                                                                noFeedback);
                        }

                        return;
                }

                double totalRating = 0;

                int validRatings = 0;

                for (FeedbackModel feedback : feedbackList) {

                        double rating = feedback.getRating();

                        if (rating > 0
                                        && rating <= 5) {

                                totalRating += rating;

                                validRatings++;
                        }
                }

                double averageRating = validRatings > 0
                                ? totalRating / validRatings
                                : 0;

                averageRating = Math.round(
                                averageRating * 10.0) / 10.0;

                dashboardAverageRating.setText(
                                String.format(
                                                "%.1f  %s",
                                                averageRating,
                                                createStarString(
                                                                averageRating)));

                dashboardReviewCount.setText(
                                "Based on "
                                                + feedbackList.size()
                                                + " reviews");

                if (dashboardFeedbackList != null) {

                        dashboardFeedbackList
                                        .getChildren()
                                        .clear();

                        int count = 0;

                        for (FeedbackModel feedback : feedbackList) {

                                if (count >= 3) {
                                        break;
                                }

                                dashboardFeedbackList
                                                .getChildren()
                                                .add(
                                                                createFeedbackItem(
                                                                                feedback));

                                count++;
                        }
                }
        }

        // =========================================================
        // STAR STRING
        // =========================================================

        private static String createStarString(
                        double rating) {

                int rounded = (int) Math.round(rating);

                if (rounded < 0) {
                        rounded = 0;
                }

                if (rounded > 5) {
                        rounded = 5;
                }

                StringBuilder stars = new StringBuilder();

                for (int i = 0; i < 5; i++) {

                        if (i < rounded) {

                                stars.append("★");

                        } else {

                                stars.append("☆");
                        }
                }

                return stars.toString();
        }

        // =========================================================
        // PROFILE AVATAR
        // =========================================================

        private static StackPane createDashboardProfileAvatar() {

                StackPane avatar = new StackPane();

                avatar.setPrefSize(
                                52,
                                52);

                avatar.setMinSize(
                                52,
                                52);

                avatar.setMaxSize(
                                52,
                                52);

                avatar.setStyle(
                                "-fx-background-color: "
                                                + DoctorTheme.PRIMARY_LIGHT
                                                + ";"
                                                + "-fx-background-radius: 50%;"
                                                + "-fx-cursor: hand;");

                doctorProfileIcon = new Label("👨‍⚕️");

                doctorProfileIcon.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';"
                                                + "-fx-font-size: 27px;");

                doctorProfileImageView = new ImageView();

                doctorProfileImageView.setFitWidth(52);

                doctorProfileImageView.setFitHeight(52);

                doctorProfileImageView.setPreserveRatio(false);

                doctorProfileImageView.setVisible(false);

                Circle clip = new Circle(
                                26,
                                26,
                                26);

                doctorProfileImageView.setClip(
                                clip);

                avatar.getChildren().addAll(
                                doctorProfileIcon,
                                doctorProfileImageView);

                avatar.setOnMouseClicked(
                                e -> openDoctorProfile());

                Tooltip.install(
                                avatar,
                                new Tooltip(
                                                "Open Doctor Profile"));

                return avatar;
        }

        // =========================================================
        // OPEN DOCTOR PROFILE
        // =========================================================

        private static void openDoctorProfile() {

                try {

                        if (dashboardStage == null) {

                                showError(
                                                "Dashboard stage is not available.");

                                return;
                        }

                        String doctorUid = getCurrentDoctorUid();

                        if (doctorUid == null
                                        || doctorUid.trim().isEmpty()) {

                                showError(
                                                "Doctor UID is not available.\n\n"
                                                                + "Please login again.");

                                return;
                        }

                        DoctorProfilePage profilePage = new DoctorProfilePage(
                                        dashboardStage,
                                        doctorUid);

                        profilePage.show();

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Unable to open Doctor Profile.\n\n"
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // ERROR
        // =========================================================

        private static void showError(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Doctor Dashboard");

                alert.setHeaderText(null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // =========================================================
        // LOAD DOCTOR PHOTO
        // =========================================================

        private static void loadDashboardDoctorPhoto() {

                String doctorUid = getCurrentDoctorUid();

                if (doctorUid == null
                                || doctorUid.trim().isEmpty()) {

                        clearDashboardDoctorPhoto();

                        return;
                }

                if (doctorProfileImageView == null) {
                        return;
                }

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                Firestore db = FirebaseConfig
                                                .getFirestore();

                                DocumentSnapshot document = db.collection("doctors")
                                                .document(doctorUid)
                                                .get()
                                                .get(
                                                                10,
                                                                TimeUnit.SECONDS);

                                if (!document.exists()) {

                                        return "";
                                }

                                String url = document.getString(
                                                "photoUrl");

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "profilePhotoUrl");
                                }

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "photoURL");
                                }

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "cloudinaryUrl");
                                }

                                return url == null
                                                ? ""
                                                : url.trim();
                        }
                };

                task.setOnSucceeded(
                                e -> {

                                        String photoUrl = task.getValue();

                                        if (photoUrl == null
                                                        || photoUrl.trim().isEmpty()) {

                                                clearDashboardDoctorPhoto();

                                                return;
                                        }

                                        if (!photoUrl.equals(
                                                        lastDashboardPhotoUrl)) {

                                                lastDashboardPhotoUrl = photoUrl;

                                                displayDashboardDoctorPhoto(
                                                                photoUrl);
                                        }
                                });

                task.setOnFailed(
                                e -> {

                                        System.out.println(
                                                        "[DASHBOARD PHOTO] "
                                                                        + "Failed to load doctor photo.");

                                        if (task.getException() != null) {

                                                task.getException()
                                                                .printStackTrace();
                                        }
                                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // DISPLAY PHOTO
        // =========================================================

        private static void displayDashboardDoctorPhoto(
                        String photoUrl) {

                if (photoUrl == null
                                || photoUrl.trim().isEmpty()) {

                        clearDashboardDoctorPhoto();

                        return;
                }

                Task<Image> imageTask = new Task<Image>() {

                        @Override
                        protected Image call()
                                        throws Exception {

                                Image image = new Image(
                                                photoUrl,
                                                52,
                                                52,
                                                false,
                                                true,
                                                false);

                                if (image.isError()) {

                                        throw new Exception(
                                                        "Unable to load doctor profile image.");
                                }

                                return image;
                        }
                };

                imageTask.setOnSucceeded(
                                e -> {

                                        if (doctorProfileImageView == null
                                                        || doctorProfileIcon == null) {

                                                return;
                                        }

                                        Image image = imageTask.getValue();

                                        doctorProfileImageView
                                                        .setImage(image);

                                        doctorProfileImageView
                                                        .setVisible(true);

                                        doctorProfileIcon
                                                        .setVisible(false);
                                });

                imageTask.setOnFailed(
                                e -> clearDashboardDoctorPhoto());

                Thread thread = new Thread(imageTask);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // CLEAR PHOTO
        // =========================================================

        private static void clearDashboardDoctorPhoto() {

                Platform.runLater(
                                () -> {

                                        if (doctorProfileImageView != null) {

                                                doctorProfileImageView
                                                                .setImage(null);

                                                doctorProfileImageView
                                                                .setVisible(false);
                                        }

                                        if (doctorProfileIcon != null) {

                                                doctorProfileIcon
                                                                .setVisible(true);
                                        }

                                        lastDashboardPhotoUrl = "";
                                });
        }

        // =========================================================
        // PROFILE PHOTO REFRESH
        // =========================================================

        private static void startProfilePhotoRefresh() {

                stopProfilePhotoRefresh();

                profilePhotoRefreshTimeline = new Timeline(
                                new KeyFrame(
                                                Duration.seconds(3),
                                                e -> loadDashboardDoctorPhoto()));

                profilePhotoRefreshTimeline
                                .setCycleCount(
                                                Timeline.INDEFINITE);

                profilePhotoRefreshTimeline.play();
        }

        // =========================================================
        // STOP PHOTO REFRESH
        // =========================================================

        private static void stopProfilePhotoRefresh() {

                if (profilePhotoRefreshTimeline != null) {

                        profilePhotoRefreshTimeline.stop();

                        profilePhotoRefreshTimeline = null;
                }
        }

        // =========================================================
        // STOP DASHBOARD SERVICES
        // =========================================================

        public static void stopDashboardRealtimeServices() {

                stopProfilePhotoRefresh();

                if (feedbackController != null) {

                        feedbackController
                                        .stopRealtimeFeedbackListener();
                }
        }

        // =========================================================
        // START DASHBOARD
        // =========================================================

        public static void startDashboard(
                        Stage stage) {

                DoctorDashboard dashboard = getInstance();

                try {
                        dashboard.start(stage);
                } catch (Exception e) {
                        throw new RuntimeException("Failed to start doctor dashboard", e);
                }
        }
}

final class DoctorTheme {

        private DoctorTheme() {
        }

        public static final String BACKGROUND = "linear-gradient(to bottom right, #FFF9FB, #FFEFF5)";
        public static final String WHITE = "#FFFFFF";
        public static final String PRIMARY = "#E84A87";
        public static final String PRIMARY_LIGHT = "#FFE3EE";
        public static final String TEXT = "#3B2140";
        public static final String SECONDARY_TEXT = "#806A78";
        public static final String BORDER = "#F0D8E3";
        public static final String GREEN = "#21A366";
        public static final String GREEN_LIGHT = "#E7F8EF";
        public static final String ORANGE = "#F59E0B";
        public static final String ORANGE_LIGHT = "#FFF4DE";
        public static final String RED = "#E74C78";
        public static final String RED_LIGHT = "#FDEAF1";
        public static final String PURPLE = "#C5306D";
        public static final String FONT = "Arial";

        public static final double WIDTH = 1200;
        public static final double HEIGHT = 750;

        public static void applyBackground(Region region) {
                region.setStyle("-fx-background-color: " + BACKGROUND + ";");
        }

        public static Label title(String text) {
                Label label = new Label(text);
                label.setFont(Font.font(FONT, FontWeight.BOLD, 26));
                label.setTextFill(Color.web(TEXT));
                return label;
        }

        public static Label subtitle(String text) {
                Label label = new Label(text);
                label.setFont(Font.font(FONT, FontWeight.NORMAL, 12));
                label.setTextFill(Color.web(SECONDARY_TEXT));
                return label;
        }

        public static VBox card() {
                VBox box = new VBox();
                box.setPadding(new Insets(18));
                box.setStyle("-fx-background-color: #FFFFFF;"
                                + "-fx-background-radius: 18;"
                                + "-fx-border-color: " + BORDER + ";"
                                + "-fx-border-radius: 18;"
                                + "-fx-border-width: 1;"
                                + "-fx-effect: dropshadow(gaussian, rgba(154,139,194,0.12),"
                                + "18, 0.2, 0, 4);");
                return box;
        }

        public static Button primaryButton(String text) {
                Button button = new Button(text);
                button.setFont(Font.font(FONT, FontWeight.BOLD, 12));
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-background-color: linear-gradient(to right, "
                                + PRIMARY + ", " + PURPLE + ");"
                                + "-fx-background-radius: 12;"
                                + "-fx-padding: 11 20 11 20;"
                                + "-fx-cursor: hand;"
                                + "-fx-effect: dropshadow(gaussian, rgba(217,106,158,0.25),"
                                + "10, 0.2, 0, 3);");
                return button;
        }

        public static Button backButton() {
                Button button = new Button("←  Back to Dashboard");
                button.setFont(Font.font(FONT, FontWeight.BOLD, 12));
                button.setTextFill(Color.web(PRIMARY));
                button.setStyle("-fx-background-color: " + PRIMARY_LIGHT + ";"
                                + "-fx-background-radius: 8;"
                                + "-fx-padding: 10 16 10 16;"
                                + "-fx-cursor: hand;");
                button.setOnAction(e -> DoctorDashboard.showDashboard());
                return button;
        }

        public static VBox pageHeader(String title, String description) {
                VBox box = new VBox(5);
                box.getChildren().addAll(title(title), subtitle(description));
                return box;
        }
}