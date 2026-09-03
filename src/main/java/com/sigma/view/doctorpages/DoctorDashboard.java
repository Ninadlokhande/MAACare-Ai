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
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
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

        private static double dashboardWidth = Theme.WIDTH;

        private static double dashboardHeight = Theme.HEIGHT;

        private static double dashboardX = -1;

        private static double dashboardY = -1;

        private static boolean dashboardWasMaximized = false;

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
                 * Theme.WIDTH and Theme.HEIGHT are used.
                 */
                dashboardStage.setWidth(Theme.WIDTH);

                dashboardStage.setHeight(Theme.HEIGHT);

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

        public static void showDashboard(String doctorUid) {

                try {

                        setCurrentDoctorUid(doctorUid);

                        DoctorDashboard dashboard = getInstance();

                        if (dashboardStage == null) {

                                dashboardStage = new Stage();

                                dashboard.start(dashboardStage);

                                return;
                        }

                        saveStageState();

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

                        restoreStageState();

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

                                saveStageState();

                                refreshDashboardAppointments();

                                dashboardScene = createDashboardScene();

                                dashboardStage.setScene(dashboardScene);

                                restoreStageState();

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
        // SAVE STAGE STATE
        // =========================================================

        private static void saveStageState() {

                if (dashboardStage == null) {
                        return;
                }

                dashboardWasMaximized = dashboardStage.isMaximized();

                if (!dashboardWasMaximized) {

                        dashboardWidth = dashboardStage.getWidth();

                        dashboardHeight = dashboardStage.getHeight();

                        dashboardX = dashboardStage.getX();

                        dashboardY = dashboardStage.getY();
                }
        }

        // =========================================================
        // RESTORE STAGE STATE
        // =========================================================

        private static void restoreStageState() {

                if (dashboardStage == null) {
                        return;
                }

                try {

                        if (dashboardWasMaximized) {

                                dashboardStage.setMaximized(true);

                        } else {

                                dashboardStage.setMaximized(false);

                                dashboardStage.setWidth(
                                                dashboardWidth > 0
                                                                ? dashboardWidth
                                                                : Theme.WIDTH);

                                dashboardStage.setHeight(
                                                dashboardHeight > 0
                                                                ? dashboardHeight
                                                                : Theme.HEIGHT);

                                if (dashboardX >= 0) {

                                        dashboardStage.setX(
                                                        dashboardX);
                                }

                                if (dashboardY >= 0) {

                                        dashboardStage.setY(
                                                        dashboardY);
                                }
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

                        saveStageState();

                        dashboardStage.setScene(newScene);

                        restoreStageState();

                        dashboardStage.show();

                        dashboardStage.toFront();

                        dashboardStage.requestFocus();

                } catch (Exception e) {

                        e.printStackTrace();
                }
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

                Theme.applyBackground(root);

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
                                Theme.WIDTH,
                                Theme.HEIGHT);
        }

        // =========================================================
        // SIDEBAR
        // =========================================================

        private static VBox createSidebar() {

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
                                "-fx-background-color: #EEE7FF;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER
                                                + ";"
                                                + "-fx-border-width: 0 1 0 0;");

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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                14));

                doctor.setTextFill(
                                Color.web(Theme.TEXT));

                Label specialist = new Label(
                                "Obstetrician & Gynecologist");

                specialist.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                10));

                specialist.setTextFill(
                                Color.web(Theme.SECONDARY_TEXT));

                Label online = new Label(
                                "● Online");

                online.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                10));

                online.setTextFill(
                                Color.web(Theme.GREEN));

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
                                true);

                Button appointments = createMenuButton(
                                "📅",
                                "Appointments",
                                false);

                Button patients = createMenuButton(
                                "👩‍⚕️",
                                "Patients",
                                false);

                Button reports = createMenuButton(
                                "📋",
                                "Reports",
                                false);

                Button settings = createMenuButton(
                                "⚙️",
                                "Settings",
                                false);

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

                button.setMaxWidth(
                                Double.MAX_VALUE);

                HBox content = new HBox(12);

                content.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';"
                                                + "-fx-font-size: 17px;");

                Label textLabel = new Label(text);

                textLabel.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                13));

                content.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(content);

                button.setText("");

                if (active) {

                        button.setStyle(
                                        "-fx-background-color: "
                                                        + Theme.PRIMARY_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding: 10 12 10 12;"
                                                        + "-fx-cursor: hand;");

                        iconLabel.setTextFill(
                                        Color.web(Theme.PRIMARY));

                        textLabel.setTextFill(
                                        Color.web(Theme.PRIMARY));

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding: 10 12 10 12;"
                                                        + "-fx-cursor: hand;");

                        iconLabel.setTextFill(
                                        Color.web(Theme.TEXT));

                        textLabel.setTextFill(
                                        Color.web(Theme.TEXT));
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

                Theme.applyBackground(content);

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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(Theme.TEXT));

                Label sub = Theme.subtitle(
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
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                11));

                date.setTextFill(
                                Color.web(Theme.TEXT));

                date.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER
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
                                                "📅",
                                                String.valueOf(
                                                                todayAppointmentCount),
                                                "Today's",
                                                "Appointments"),

                                statCard(
                                                "👥",
                                                String.valueOf(
                                                                patientCount),
                                                "Patients",
                                                "Registered"),

                                statCard(
                                                "📋",
                                                String.valueOf(
                                                                reportCount),
                                                "Reports",
                                                "Available"),

                                statCard(
                                                "⭐",
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

                VBox appointments = Theme.card();

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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                16));

                appointmentTitle.setTextFill(
                                Color.web(Theme.TEXT));

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
                                                                Theme.FONT,
                                                                FontWeight.NORMAL,
                                                                13));

                                noAppointment.setTextFill(
                                                Color.web(
                                                                Theme.SECONDARY_TEXT));

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
                                                        Theme.SECONDARY_TEXT));

                        appointments.getChildren().add(
                                        loading);
                }

                // =====================================================
                // TODAY SCHEDULE
                // =====================================================

                VBox schedule = Theme.card();

                schedule.setPrefWidth(330);

                schedule.setPrefHeight(330);

                schedule.setMinHeight(330);

                Label scheduleTitle = new Label(
                                "Today's Schedule");

                scheduleTitle.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                16));

                scheduleTitle.setTextFill(
                                Color.web(Theme.TEXT));

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
                                                                Theme.SECONDARY_TEXT));

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
                                                        Theme.SECONDARY_TEXT));

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
                                middle,
                                feedback);

                return content;
        }

        // =========================================================
        // STAT CARD
        // =========================================================

        private static VBox statCard(
                        String icon,
                        String number,
                        String line1,
                        String line2) {

                VBox box = Theme.card();

                box.setPrefHeight(90);

                box.setMinHeight(90);

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
                                                + "-fx-font-size: 27px;");

                VBox text = new VBox(2);

                Label num = new Label(number);

                num.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                20));

                num.setTextFill(
                                Color.web(Theme.TEXT));

                Label firstLine = Theme.subtitle(line1);

                Label secondLine = Theme.subtitle(line2);

                text.getChildren().addAll(
                                num,
                                firstLine,
                                secondLine);

                row.getChildren().addAll(
                                iconLabel,
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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                12));

                t.setTextFill(
                                Color.web(Theme.TEXT));

                VBox patientBox = new VBox(2);

                Label p = new Label(
                                patient == null
                                                ? ""
                                                : patient);

                p.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                13));

                p.setTextFill(
                                Color.web(Theme.TEXT));

                patientBox.getChildren().addAll(
                                p,
                                Theme.subtitle(
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
                                                Theme.FONT,
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
                                        Color.web(Theme.ORANGE));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + Theme.ORANGE_LIGHT
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
                                        Color.web(Theme.GREEN));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + Theme.GREEN_LIGHT
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
                                Theme.subtitle(
                                                time == null
                                                                ? ""
                                                                : time));

                Label n = new Label(
                                name == null
                                                ? ""
                                                : name);

                n.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                11));

                n.setTextFill(
                                Color.web(Theme.TEXT));

                details.getChildren().add(n);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                row.getChildren().addAll(
                                details,
                                spacer,
                                Theme.subtitle(
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

                feedbackCard.setPrefHeight(230);

                feedbackCard.setMinHeight(230);

                feedbackCard.setMaxWidth(
                                Double.MAX_VALUE);

                feedbackCard.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 16;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER
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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                17));

                title.setTextFill(
                                Color.web(Theme.TEXT));

                Label subtitle = new Label(
                                "What your patients are saying");

                subtitle.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                10));

                subtitle.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                17));

                dashboardAverageRating.setTextFill(
                                Color.web(Theme.PRIMARY));

                dashboardReviewCount = new Label(
                                "Based on 0 reviews");

                dashboardReviewCount.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                9));

                dashboardReviewCount.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

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
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                11));

                loading.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

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
                                                8,
                                                10,
                                                8,
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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                11));

                name.setTextFill(
                                Color.web(Theme.TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label date = new Label(
                                formatFeedbackDate(
                                                feedback.getTimestamp()));

                date.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                9));

                date.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

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
                                                Theme.FONT,
                                                FontWeight.NORMAL,
                                                9.5));

                commentLabel.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

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
                                38,
                                38);

                avatar.setMinSize(
                                38,
                                38);

                avatar.setMaxSize(
                                38,
                                38);

                Circle circle = new Circle(19);

                circle.setFill(
                                Color.web(
                                                Theme.PRIMARY_LIGHT));

                Label initialsLabel = new Label(
                                getInitials(patientName));

                initialsLabel.setFont(
                                Font.font(
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                12));

                initialsLabel.setTextFill(
                                Color.web(Theme.PRIMARY));

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
                                                Theme.FONT,
                                                FontWeight.BOLD,
                                                9));

                ratingLabel.setTextFill(
                                Color.web(Theme.PRIMARY));

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

                        return;
                }

                try {

                        if (feedbackController == null) {

                                feedbackController = new FeedbackController(
                                                FirebaseConfig.getFirestore());
                        }

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
                                                                Theme.FONT,
                                                                FontWeight.NORMAL,
                                                                11));

                                noFeedback.setTextFill(
                                                Color.web(
                                                                Theme.SECONDARY_TEXT));

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
                                                + Theme.PRIMARY_LIGHT
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