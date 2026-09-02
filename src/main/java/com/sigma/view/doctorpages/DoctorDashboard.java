
package com.sigma.view.doctorpages;

import com.sigma.config.DoctorModule.FirebaseConfig;

import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.controller.doctorController.PatientController;
import com.sigma.controller.doctorController.PatientReportController;

import com.sigma.model.DoctorModel.DoctorAppointment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorDashboard extends Application {

        // =========================================================
        // COMMON DASHBOARD INSTANCE / STAGE
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

        // =========================================================
        // SAVED STAGE STATE
        // =========================================================

        private static double dashboardWidth = Theme.WIDTH;
        private static double dashboardHeight = Theme.HEIGHT;

        private static double dashboardX = -1;
        private static double dashboardY = -1;

        private static boolean dashboardWasMaximized = false;

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
        // GET CURRENT DOCTOR UID
        // =========================================================

        public static String getCurrentDoctorUid() {

                if (currentDoctorUid != null &&
                                !currentDoctorUid.trim().isEmpty()) {

                        return currentDoctorUid;
                }

                return FirebaseConfig.getCurrentDoctorUid();
        }

        // =========================================================
        // SET CURRENT DOCTOR UID
        // =========================================================

        public static void setCurrentDoctorUid(String doctorUid) {

                currentDoctorUid = doctorUid;

                if (doctorUid != null &&
                                !doctorUid.trim().isEmpty()) {

                        FirebaseConfig.setCurrentDoctorUid(doctorUid);
                }
        }

        // =========================================================
        // START
        // =========================================================

        @Override
        public void start(Stage stage) {

                instance = this;

                dashboardStage = stage;

                dashboardStage.setTitle(
                                "MaaCare AI - Doctor Dashboard");

                dashboardStage.setResizable(true);

                initializeControllers();

                dashboardScene = createDashboardScene();

                dashboardStage.setScene(
                                dashboardScene);

                dashboardStage.setWidth(
                                Theme.WIDTH);

                dashboardStage.setHeight(
                                Theme.HEIGHT);

                dashboardStage.centerOnScreen();

                dashboardStage.show();

                dashboardStage.toFront();

                dashboardStage.requestFocus();
        }

        // =========================================================
        // INITIALIZE CONTROLLERS
        // =========================================================

        private static void initializeControllers() {

                try {

                        if (appointmentController == null) {

                                appointmentController = new DoctorAppointmentController();

                        } else {

                                appointmentController.refreshAppointments();
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[DASHBOARD] Appointment controller error: "
                                                        + e.getMessage());
                }

                try {

                        if (patientController == null) {

                                patientController = new PatientController();

                        } else {

                                patientController.refreshPatients();
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[DASHBOARD] Patient controller error: "
                                                        + e.getMessage());
                }

                try {

                        if (reportController == null) {

                                reportController = new PatientReportController();

                        } else {

                                reportController.refreshReports();
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[DASHBOARD] Report controller error: "
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // SHOW DASHBOARD WITH DOCTOR UID
        // =========================================================

        public static void showDashboard(
                        String doctorUid) {

                try {

                        System.out.println(
                                        "========================================");

                        System.out.println(
                                        "[DOCTOR DASHBOARD] Opening dashboard");

                        System.out.println(
                                        "[DOCTOR DASHBOARD] UID : "
                                                        + doctorUid);

                        // -----------------------------------------------------
                        // SAVE UID
                        // -----------------------------------------------------

                        setCurrentDoctorUid(
                                        doctorUid);

                        // -----------------------------------------------------
                        // INITIALIZE INSTANCE
                        // -----------------------------------------------------

                        DoctorDashboard dashboard = getInstance();

                        // -----------------------------------------------------
                        // CREATE COMMON STAGE
                        // -----------------------------------------------------

                        if (dashboardStage == null) {

                                dashboardStage = new Stage();

                                dashboard.start(
                                                dashboardStage);

                                return;
                        }

                        // -----------------------------------------------------
                        // INITIALIZE CONTROLLERS
                        // -----------------------------------------------------

                        initializeControllers();

                        // -----------------------------------------------------
                        // CREATE DASHBOARD SCENE
                        // -----------------------------------------------------

                        dashboardScene = createDashboardScene();

                        // -----------------------------------------------------
                        // SET SCENE
                        // -----------------------------------------------------

                        dashboardStage.setScene(
                                        dashboardScene);

                        // -----------------------------------------------------
                        // SHOW
                        // -----------------------------------------------------

                        dashboardStage.show();

                        dashboardStage.toFront();

                        dashboardStage.requestFocus();

                        System.out.println(
                                        "[DOCTOR DASHBOARD] Dashboard opened successfully");

                        System.out.println(
                                        "========================================");

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[DOCTOR DASHBOARD ERROR] "
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // SHOW DASHBOARD WITHOUT UID
        // =========================================================

        public static void showDashboard() {

                try {

                        String uid = getCurrentDoctorUid();

                        if (uid != null &&
                                        !uid.trim().isEmpty()) {

                                showDashboard(uid);

                                return;
                        }

                        // -----------------------------------------------------
                        // NO UID - STILL SHOW DASHBOARD
                        // -----------------------------------------------------

                        DoctorDashboard dashboard = getInstance();

                        if (dashboardStage == null) {

                                dashboardStage = new Stage();

                                dashboard.start(
                                                dashboardStage);

                        } else {

                                initializeControllers();

                                dashboardScene = createDashboardScene();

                                dashboardStage.setScene(
                                                dashboardScene);

                                dashboardStage.show();

                                dashboardStage.toFront();

                                dashboardStage.requestFocus();
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[DOCTOR DASHBOARD ERROR] "
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // CHANGE SCENE
        // =========================================================

        public static void changeScene(
                        Scene newScene) {

                if (dashboardStage == null) {

                        System.out.println(
                                        "[NAVIGATION] Dashboard stage is null.");

                        return;
                }

                if (newScene == null) {

                        System.out.println(
                                        "[NAVIGATION] New scene is null.");

                        return;
                }

                try {

                        // -----------------------------------------------------
                        // SAVE CURRENT STAGE STATE
                        // -----------------------------------------------------

                        if (!dashboardStage.isMaximized()) {

                                dashboardWidth = dashboardStage.getWidth();

                                dashboardHeight = dashboardStage.getHeight();

                                dashboardX = dashboardStage.getX();

                                dashboardY = dashboardStage.getY();
                        }

                        dashboardWasMaximized = dashboardStage.isMaximized();

                        // -----------------------------------------------------
                        // CHANGE SCENE
                        // -----------------------------------------------------

                        dashboardStage.setScene(
                                        newScene);

                        // -----------------------------------------------------
                        // RESTORE STAGE STATE
                        // -----------------------------------------------------

                        if (dashboardWasMaximized) {

                                dashboardStage.setMaximized(
                                                true);

                        } else {

                                dashboardStage.setMaximized(
                                                false);

                                dashboardStage.setWidth(
                                                dashboardWidth);

                                dashboardStage.setHeight(
                                                dashboardHeight);

                                if (dashboardX >= 0 &&
                                                dashboardY >= 0) {

                                        dashboardStage.setX(
                                                        dashboardX);

                                        dashboardStage.setY(
                                                        dashboardY);
                                }
                        }

                        dashboardStage.show();

                        dashboardStage.toFront();

                        dashboardStage.requestFocus();

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "[NAVIGATION ERROR] "
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // GET APPOINTMENT CONTROLLER
        // =========================================================

        public static DoctorAppointmentController getAppointmentController() {

                if (appointmentController == null) {

                        appointmentController = new DoctorAppointmentController();
                }

                return appointmentController;
        }

        // =========================================================
        // GET PATIENT CONTROLLER
        // =========================================================

        public static PatientController getPatientController() {

                if (patientController == null) {

                        patientController = new PatientController();
                }

                return patientController;
        }

        // =========================================================
        // GET REPORT CONTROLLER
        // =========================================================

        public static PatientReportController getReportController() {

                if (reportController == null) {

                        reportController = new PatientReportController();
                }

                return reportController;
        }

        // =========================================================
        // OPEN DOCTOR PROFILE
        // =========================================================

        private static void openDoctorProfile() {

                try {

                        if (dashboardStage == null) {

                                showError(
                                                "Dashboard stage is not available.\n"
                                                                + "Please open the dashboard again.");

                                return;
                        }

                        String doctorUid = getCurrentDoctorUid();

                        if (doctorUid == null ||
                                        doctorUid.trim().isEmpty()) {

                                showError(
                                                "Doctor UID is not available.\n\n"
                                                                + "Please login again.");

                                return;
                        }

                        System.out.println(
                                        "[PROFILE] Opening profile for UID: "
                                                        + doctorUid);

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
        // ERROR ALERT
        // =========================================================

        private static void showError(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Doctor Dashboard");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // =========================================================
        // DASHBOARD SCENE
        // =========================================================

        private static Scene createDashboardScene() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(
                                root);

                // -----------------------------------------------------
                // SIDEBAR
                // -----------------------------------------------------

                VBox sidebar = createSidebar();

                root.setLeft(
                                sidebar);

                // -----------------------------------------------------
                // MAIN CONTENT
                // -----------------------------------------------------

                VBox content = createDashboardContent();

                root.setCenter(
                                content);

                return new Scene(
                                root,
                                Theme.WIDTH,
                                Theme.HEIGHT);
        }

        // =========================================================
        // SIDEBAR
        // =========================================================

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(
                                205);

                sidebar.setPadding(
                                new Insets(
                                                22,
                                                18,
                                                18,
                                                18));

                sidebar.setSpacing(
                                8);

                sidebar.setStyle(
                                "-fx-background-color: #EEE7FF;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER + ";"
                                                + "-fx-border-width: 0 1 0 0;");

                sidebar.getChildren()
                                .add(
                                                Theme.logo());

                // =====================================================
                // DOCTOR INFORMATION
                // =====================================================

                VBox doctorBox = new VBox(2);

                doctorBox.setPadding(
                                new Insets(
                                                18,
                                                5,
                                                12,
                                                5));

                Label doctor = new Label(
                                "Dr. Anjali Mehta");

                doctor.setStyle(
                                "-fx-font-weight: BOLD;"
                                                + "-fx-font-size: 14px");

                doctor.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                Label specialist = new Label(
                                "Obstetrician & Gynecologist");

                specialist.setStyle(
                                "-fx-font-weight: BOLD;"
                                                + "-fx-font-size: 11px");

                specialist.setTextFill(
                                Color.web(
                                                Theme.SECONDARY_TEXT));

                Label online = new Label(
                                "●  Online");

                online.setStyle(
                                "-fx-font-weight: BOLD;"
                                                + "-fx-font-size: 11px");

                online.setTextFill(
                                Color.web(
                                                Theme.GREEN));

                doctorBox.getChildren()
                                .addAll(
                                                doctor,
                                                specialist,
                                                online);

                sidebar.getChildren()
                                .add(
                                                doctorBox);

                // =====================================================
                // MENU BUTTONS
                // =====================================================

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
                                "Patient Reports",
                                false);

                Button profile = createMenuButton(
                                "👨🏻‍⚕️",
                                "Doctor Profile",
                                false);

                Button settings = createMenuButton(
                                "⚙️",
                                "Settings",
                                false);

                // =====================================================
                // NAVIGATION
                // =====================================================

                appointments.setOnAction(
                                e -> DoctorAppointmentsPage.show());

                patients.setOnAction(
                                e -> PatientsPage.show());

                reports.setOnAction(
                                e -> PatientReportsPage.show());

                profile.setOnAction(
                                e -> openDoctorProfile());

                settings.setOnAction(
                                e -> SettingsPage.display());

                dashboard.setOnAction(
                                e -> showDashboard());

                // =====================================================
                // ADD MENU
                // =====================================================

                sidebar.getChildren()
                                .addAll(
                                                dashboard,
                                                appointments,
                                                patients,
                                                reports,
                                                profile,
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

                button.setAlignment(
                                Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);

                iconLabel.setPrefWidth(
                                35);

                iconLabel.setMinWidth(
                                35);

                iconLabel.setMaxWidth(
                                35);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setStyle(
                                "-fx-font-family: 'Segoe UI Emoji';"
                                                + "-fx-font-size: 18px;");

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-family: '"
                                                + Theme.FONT
                                                + "';"
                                                + "-fx-font-size: 15px;"
                                                + "-fx-font-weight: BOLD;");

                HBox content = new HBox(8);

                content.setAlignment(
                                Pos.CENTER_LEFT);

                content.getChildren()
                                .addAll(
                                                iconLabel,
                                                textLabel);

                button.setGraphic(
                                content);

                button.setText("");

                if (active) {

                        button.setStyle(
                                        "-fx-background-color:"
                                                        + Theme.PRIMARY_LIGHT + ";"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding:"
                                                        + "10 12 10 12;");

                        iconLabel.setTextFill(
                                        Color.web(
                                                        Theme.PRIMARY));

                        textLabel.setTextFill(
                                        Color.web(
                                                        Theme.PRIMARY));

                } else {

                        button.setStyle(
                                        "-fx-background-color:"
                                                        + "transparent;"
                                                        + "-fx-background-radius: 8;"
                                                        + "-fx-padding:"
                                                        + "10 12 10 12;"
                                                        + "-fx-cursor: hand;");

                        iconLabel.setTextFill(
                                        Color.web(
                                                        Theme.TEXT));

                        textLabel.setTextFill(
                                        Color.web(
                                                        Theme.TEXT));
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
                                                25,
                                                30,
                                                25,
                                                30));

                Theme.applyBackground(
                                content);

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox welcome = new VBox(5);

                Label title = new Label(
                                "Welcome back, Dr. Anjali! 👋");

                title.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                Label sub = Theme.subtitle(
                                "Here's what's happening "
                                                + "in your clinic today.");

                welcome.getChildren()
                                .addAll(
                                                title,
                                                sub);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                String currentDate = LocalDate.now()
                                .format(
                                                DateTimeFormatter.ofPattern(
                                                                "dd MMMM yyyy"));

                Label date = new Label(
                                currentDate
                                                + "   📅");

                date.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.NORMAL,
                                                11));

                date.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                date.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + Theme.BORDER + ";"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 10 14;");

                header.getChildren()
                                .addAll(
                                                welcome,
                                                spacer,
                                                date);

                // =====================================================
                // DYNAMIC COUNTS
                // =====================================================

                int todayAppointmentCount = 0;

                int patientCount = 0;

                int reportCount = 0;

                if (appointmentController != null) {

                        todayAppointmentCount = appointmentController
                                        .getTodayAppointmentCount();
                }

                if (patientController != null) {

                        patientCount = patientController
                                        .getPatientCount();
                }

                if (reportController != null) {

                        try {

                                reportCount = reportController
                                                .getReports()
                                                .size();

                        } catch (Exception e) {

                                reportCount = 0;
                        }
                }

                // =====================================================
                // STAT CARDS
                // =====================================================

                HBox stats = new HBox(15);

                stats.getChildren()
                                .addAll(

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
                                                                "4.8",
                                                                "Average Rating",
                                                                "This Month"));

                // =====================================================
                // MIDDLE
                // =====================================================

                HBox middle = new HBox(18);

                // =====================================================
                // TODAY'S APPOINTMENTS
                // =====================================================

                VBox appointments = Theme.card();

                HBox.setHgrow(
                                appointments,
                                Priority.ALWAYS);

                appointments.setPrefHeight(
                                330);

                appointments.setMinHeight(
                                330);

                Label appointmentTitle = new Label(
                                "Today's Appointments");

                appointmentTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                appointmentTitle.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                appointments.getChildren()
                                .add(
                                                appointmentTitle);

                if (appointmentController != null) {

                        List<DoctorAppointment> todayAppointments = appointmentController
                                        .getTodayAppointments();

                        if (todayAppointments.isEmpty()) {

                                Label noAppointment = new Label(
                                                "No appointments for today.");

                                noAppointment.setFont(
                                                javafx.scene.text.Font.font(
                                                                Theme.FONT,
                                                                javafx.scene.text.FontWeight.NORMAL,
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

                                appointments.getChildren()
                                                .add(
                                                                noAppointment);

                        } else {

                                for (DoctorAppointment appointment : todayAppointments) {

                                        String details = appointment.getType();

                                        if (details == null ||
                                                        details.trim().isEmpty()) {

                                                details = "Consultation";
                                        }

                                        appointments.getChildren()
                                                        .add(
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

                        appointments.getChildren()
                                        .add(
                                                        loading);
                }

                // =====================================================
                // TODAY'S SCHEDULE
                // =====================================================

                VBox schedule = Theme.card();

                schedule.setPrefWidth(
                                330);

                schedule.setPrefHeight(
                                330);

                schedule.setMinHeight(
                                330);

                Label scheduleTitle = new Label(
                                "Today's Schedule");

                scheduleTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                scheduleTitle.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                schedule.getChildren()
                                .add(
                                                scheduleTitle);

                if (appointmentController != null) {

                        List<DoctorAppointment> todayAppointments = appointmentController
                                        .getTodayAppointments();

                        if (todayAppointments.isEmpty()) {

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

                                schedule.getChildren()
                                                .add(
                                                                noSchedule);

                        } else {

                                for (DoctorAppointment appointment : todayAppointments) {

                                        String type = appointment.getType();

                                        if (type == null ||
                                                        type.trim().isEmpty()) {

                                                type = "Consultation";
                                        }

                                        schedule.getChildren()
                                                        .add(
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

                        schedule.getChildren()
                                        .add(
                                                        loading);
                }

                middle.getChildren()
                                .addAll(
                                                appointments,
                                                schedule);

                // =====================================================
                // PATIENT FEEDBACK
                // =====================================================

                HBox bottom = new HBox(20);

                VBox feedback = Theme.card();

                HBox.setHgrow(
                                feedback,
                                Priority.ALWAYS);

                Label feedbackTitle = new Label(
                                "Patient Feedback");

                feedbackTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                feedbackTitle.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                Label rating = new Label(
                                "4.8  ★★★★★");

                rating.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                18));

                rating.setTextFill(
                                Color.web(
                                                Theme.PRIMARY));

                Label based = Theme.subtitle(
                                "Based on 156 reviews");

                feedback.getChildren()
                                .addAll(
                                                feedbackTitle,
                                                rating,
                                                based);

                bottom.getChildren()
                                .add(
                                                feedback);

                // =====================================================
                // FINAL CONTENT
                // =====================================================

                content.getChildren()
                                .addAll(
                                                header,
                                                stats,
                                                middle,
                                                bottom);

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

                HBox.setHgrow(
                                box,
                                Priority.ALWAYS);

                HBox row = new HBox(12);

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                30));

                iconLabel.setTextFill(
                                Color.web(
                                                Theme.PRIMARY));

                VBox text = new VBox(2);

                Label num = new Label(number);

                num.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));

                num.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                text.getChildren()
                                .addAll(
                                                num,
                                                Theme.subtitle(line1),
                                                Theme.subtitle(line2));

                row.getChildren()
                                .addAll(
                                                iconLabel,
                                                text);

                box.getChildren()
                                .add(
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

                t.setPrefWidth(
                                65);

                t.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                12));

                t.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                VBox patientBox = new VBox(2);

                Label p = new Label(
                                patient == null
                                                ? ""
                                                : patient);

                p.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                13));

                p.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                patientBox.getChildren()
                                .addAll(
                                                p,
                                                Theme.subtitle(
                                                                details == null
                                                                                ? ""
                                                                                : details));

                HBox.setHgrow(
                                patientBox,
                                Priority.ALWAYS);

                String actualStatus = status == null ||
                                status.trim().isEmpty()
                                                ? "Pending"
                                                : status;

                Label s = new Label(
                                actualStatus);

                s.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
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
                                        Color.web(
                                                        Theme.ORANGE));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + Theme.ORANGE_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 12;");

                } else {

                        s.setTextFill(
                                        Color.web(
                                                        Theme.GREEN));

                        s.setStyle(
                                        "-fx-background-color: "
                                                        + Theme.GREEN_LIGHT
                                                        + ";"
                                                        + "-fx-background-radius: 12;");
                }

                row.getChildren()
                                .addAll(
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

                row.setPadding(
                                new Insets(
                                                12,
                                                0,
                                                12,
                                                0));

                VBox details = new VBox(3);

                details.getChildren()
                                .add(
                                                Theme.subtitle(
                                                                time == null
                                                                                ? ""
                                                                                : time));

                Label n = new Label(
                                name == null
                                                ? ""
                                                : name);

                n.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                11));

                n.setTextFill(
                                Color.web(
                                                Theme.TEXT));

                details.getChildren()
                                .add(n);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                row.getChildren()
                                .addAll(
                                                details,
                                                spacer,
                                                Theme.subtitle(
                                                                type == null
                                                                                ? ""
                                                                                : type));

                return row;
        }

        // =========================================================
        // START DASHBOARD
        // =========================================================

        public static void startDashboard(
                        Stage stage) {

                DoctorDashboard dashboard = getInstance();

                dashboard.start(stage);
        }
}
