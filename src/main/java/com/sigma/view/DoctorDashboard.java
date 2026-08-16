package com.sigma.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorDashboard extends Application {

        // =====================================================
        // COMMON PUBLIC STAGE
        // =====================================================

        public static Stage dashboardStage;

        // =====================================================
        // DASHBOARD SCENE
        // =====================================================

        private static Scene dashboardScene;

        @Override
        public void start(Stage stage) {

                dashboardStage = stage;

                dashboardStage.setTitle("MaaCare AI - Doctor Dashboard");

                dashboardScene = createDashboardScene();

                dashboardStage.setScene(dashboardScene);

                dashboardStage.setWidth(1200);
                dashboardStage.setHeight(750);

                dashboardStage.setResizable(true);

                dashboardStage.show();
        }

        // =====================================================
        // CREATE DASHBOARD SCENE
        // =====================================================

        private static Scene createDashboardScene() {

                BorderPane root = new BorderPane();

                Theme.applyBackground(root);

                // LEFT SIDEBAR
                VBox sidebar = createSidebar();

                root.setLeft(sidebar);

                // RIGHT CONTENT
                VBox content = createDashboardContent();

                root.setCenter(content);

                return new Scene(
                                root,
                                Theme.WIDTH,
                                Theme.HEIGHT);
        }

        public static void changeScene(Scene scene) {
                dashboardStage.setScene(scene);
        }

        // =====================================================
        // SIDEBAR
        // =====================================================

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(205);

                sidebar.setPadding(
                                new Insets(22, 18, 18, 18));

                sidebar.setSpacing(8);

                sidebar.setStyle(
                                "-fx-background-color: #F2E1EA;" +
                                                "-fx-border-color: " + Theme.BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");
                // LOGO
                sidebar.getChildren().add(
                                Theme.logo());

                // DOCTOR
                VBox doctorBox = new VBox(2);

                doctorBox.setPadding(
                                new Insets(18, 5, 12, 5));

                Label doctor = new Label("Dr. Anjali Mehta");

                doctor.setStyle("-fx-font-weight: BOLD; -fx-font-size: 13px");

                doctor.setTextFill(Color.web(Theme.TEXT));

                Label specialist = new Label("Obstetrician & Gynecologist");

                specialist.setStyle("-fx-font-weight: BOLD; -fx-font-size: 10px");

                specialist.setTextFill(
                                Color.web(Theme.SECONDARY_TEXT));

                Label online = new Label("●  Online");

                online.setStyle("-fx-font-weight: BOLD; -fx-font-size: 10px");
                online.setTextFill(
                                Color.web(Theme.GREEN));

                doctorBox.getChildren().addAll(
                                doctor,
                                specialist,
                                online);

                sidebar.getChildren().add(doctorBox);

                // MENU
                Button dashboard = createMenuButton("⌂", "Dashboard", true);
                Button appointments = createMenuButton("▣", "Appointments", false);
                Button patients = createMenuButton("♙", "Patients", false);
                Button reports = createMenuButton("▤", "Patient Reports", false);
                Button prescriptions = createMenuButton("♢", "Prescriptions", false);
                Button messages = createMenuButton("▱", "Messages", false);
                Button calendar = createMenuButton("□", "Calendar", false);
                Button profile = createMenuButton("♙", "Profile", false);
                Button settings = createMenuButton("⚙", "Settings", false);
                // NAVIGATION
                appointments.setOnAction(e -> DoctorAppointmentsPage.show());

                patients.setOnAction(e -> PatientsPage.show());

                reports.setOnAction(e -> PatientReportsPage.show());

                settings.setOnAction(e -> SettingsPage.show());

                sidebar.getChildren().addAll(
                                dashboard,
                                appointments,
                                patients,
                                reports,
                                prescriptions,
                                messages,
                                calendar,
                                profile,
                                settings);

                // PUSH LOGOUT TO BOTTOM
                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                sidebar.getChildren().add(spacer);

                Button logout = createMenuButton("↪", "Logout", false);
                sidebar.getChildren().add(logout);

                return sidebar;
        }

        // =====================================================
        // MENU BUTTON
        // =====================================================

        private static Button createMenuButton(
                        String text,
                        boolean active) {

                return createMenuButton("", text, active);
        }

        private static Button createMenuButton(
                        String icon,
                        String text,
                        boolean active) {

                Button button = new Button();

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                // =================================================
                // ICON
                // =================================================

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-family: '" + Theme.FONT + "';" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: BOLD;");

                // =================================================
                // TEXT
                // =================================================

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-family: '" + Theme.FONT + "';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: BOLD;");

                // =================================================
                // ICON + TEXT
                // =================================================

                HBox content = new HBox(10);

                content.setAlignment(
                                Pos.CENTER_LEFT);

                content.getChildren().addAll(
                                iconLabel,
                                textLabel);

                button.setGraphic(content);

                button.setText("");

                // =================================================
                // ACTIVE BUTTON
                // =================================================

                if (active) {

                        button.setStyle(
                                        "-fx-background-color:" + Theme.PRIMARY_LIGHT + ";" +
                                                        "-fx-background-radius: 8;" +
                                                        "-fx-padding: 10 12 10 12;");

                        iconLabel.setTextFill(
                                        Color.web(Theme.PRIMARY));

                        textLabel.setTextFill(
                                        Color.web(Theme.PRIMARY));

                        // =================================================
                        // NORMAL BUTTON
                        // =================================================

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-background-radius: 8;" +
                                                        "-fx-padding: 10 12 10 12;" +
                                                        "-fx-cursor: hand;");

                        iconLabel.setTextFill(Color.web("#18213D"));
                        textLabel.setTextFill(Color.web("#18213D"));

                        // iconLabel.setTextFill(
                        // Color.web(Theme.TEXT));

                        // textLabel.setTextFill(
                        // Color.web(Theme.TEXT));
                }

                return button;
        }

        // =====================================================
        // DASHBOARD CONTENT
        // =====================================================

        private static VBox createDashboardContent() {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(25, 30, 25, 30));

                Theme.applyBackground(content);

                // HEADER
                HBox header = new HBox();

                header.setAlignment(Pos.CENTER_LEFT);

                VBox welcome = new VBox(5);

                Label title = new Label("Welcome back, Dr. Anjali! 👋");

                title.setFont(javafx.scene.text.Font.font(
                                Theme.FONT,
                                javafx.scene.text.FontWeight.BOLD,
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

                Label date = new Label("08 May 2024   📅");

                date.setFont(javafx.scene.text.Font.font(
                                Theme.FONT,
                                javafx.scene.text.FontWeight.NORMAL,
                                11));

                date.setTextFill(
                                Color.web(Theme.TEXT));

                date.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + Theme.BORDER + ";" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 14;");

                header.getChildren().addAll(
                                welcome,
                                spacer,
                                date);

                // =================================================
                // STAT CARDS
                // =================================================

                HBox stats = new HBox(15);

                stats.getChildren().addAll(
                                statCard(
                                                "▣",
                                                "18",
                                                "Today's",
                                                "Appointments"),

                                statCard(
                                                "♙",
                                                "6",
                                                "New Patients",
                                                "This Week"),

                                statCard(
                                                "▤",
                                                "32",
                                                "Reports",
                                                "This Week"),

                                statCard(
                                                "★",
                                                "4.8",
                                                "Average Rating",
                                                "This Month"));

                // =================================================
                // TODAY APPOINTMENTS
                // =================================================

                HBox middle = new HBox(18);

                VBox appointments = Theme.card();
                middle.setPrefHeight(400);

                HBox.setHgrow(
                                appointments,
                                Priority.ALWAYS);

                Label appointmentTitle = new Label("Today's Appointments");

                appointmentTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                appointmentTitle.setTextFill(
                                Color.web(Theme.TEXT));

                appointments.getChildren().add(
                                appointmentTitle);

                appointments.getChildren().add(
                                appointmentRow(
                                                "09:30 AM",
                                                "Priya Sharma",
                                                "28 Y | 24 Weeks Pregnant",
                                                "Confirmed"));

                appointments.getChildren().add(
                                appointmentRow(
                                                "10:15 AM",
                                                "Neha Kulkarni",
                                                "32 Y | Routine Checkup",
                                                "Confirmed"));

                appointments.getChildren().add(
                                appointmentRow(
                                                "11:00 AM",
                                                "Sneha Patil",
                                                "26 Y | First Consultation",
                                                "Confirmed"));

                appointments.getChildren().add(
                                appointmentRow(
                                                "12:00 PM",
                                                "Ritika Singh",
                                                "30 Y | Ultrasound Follow-up",
                                                "Pending"));

                VBox schedule = Theme.card();

                schedule.setPrefWidth(330);
                schedule.setPrefHeight(400);

                Label scheduleTitle = new Label("Today's Schedule");

                scheduleTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                scheduleTitle.setTextFill(
                                Color.web(Theme.TEXT));

                schedule.getChildren().add(scheduleTitle);

                schedule.getChildren().addAll(
                                scheduleRow(
                                                "09:30 AM - 10:00 AM",
                                                "Priya Sharma",
                                                "Consultation"),

                                scheduleRow(
                                                "10:15 AM - 10:45 AM",
                                                "Neha Kulkarni",
                                                "Consultation"),

                                scheduleRow(
                                                "11:00 AM - 11:30 AM",
                                                "Sneha Patil",
                                                "Consultation"),

                                scheduleRow(
                                                "12:00 PM - 12:30 PM",
                                                "Ritika Singh",
                                                "Follow-up"));

                middle.getChildren().addAll(
                                appointments,
                                schedule);

                // =================================================
                // QUICK ACTIONS
                // =================================================

                HBox bottom = new HBox(20);

                VBox quickActions = Theme.card();
                // quickActions.setPrefHeight(50);

                HBox.setHgrow(
                                quickActions,
                                Priority.ALWAYS);

                Label quickTitle = new Label("Quick Actions");

                quickTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                quickTitle.setTextFill(
                                Color.web(Theme.TEXT));

                HBox actions = new HBox(20);

                actions.getChildren().addAll(
                                quickAction("▣", "Add Appointment"),
                                quickAction("♙", "Add Patient"),
                                quickAction("▤", "Write Prescription"),
                                quickAction("↥", "Upload Report"),
                                quickAction("▱", "Send Message"));

                quickActions.getChildren().addAll(
                                quickTitle,
                                actions);

                VBox feedback = Theme.card();

                feedback.setPrefWidth(220);

                Label feedbackTitle = new Label("Patient Feedback");

                feedbackTitle.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                16));

                feedbackTitle.setTextFill(
                                Color.web(Theme.TEXT));

                Label rating = new Label("4.8  ★★★★★");

                rating.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                18));

                rating.setTextFill(
                                Color.web(Theme.PRIMARY));

                Label based = Theme.subtitle(
                                "Based on 156 reviews");

                feedback.getChildren().addAll(
                                feedbackTitle,
                                rating,
                                based);

                bottom.getChildren().addAll(
                                quickActions,
                                feedback);

                content.getChildren().addAll(
                                header,
                                stats,
                                middle,
                                bottom);

                return content;
        }

        // =====================================================
        // STAT CARD
        // =====================================================

        private static VBox statCard(
                        String icon,
                        String number,
                        String line1,
                        String line2) {

                VBox box = Theme.card();

                box.setPrefHeight(95);

                HBox.setHgrow(
                                box,
                                Priority.ALWAYS);

                HBox row = new HBox(12);

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                23));

                iconLabel.setTextFill(
                                Color.web(Theme.PRIMARY));

                VBox text = new VBox(2);

                Label num = new Label(number);

                num.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                20));

                num.setTextFill(
                                Color.web(Theme.TEXT));

                Label l1 = Theme.subtitle(line1);
                Label l2 = Theme.subtitle(line2);

                text.getChildren().addAll(
                                num,
                                l1,
                                l2);

                row.getChildren().addAll(
                                iconLabel,
                                text);

                box.getChildren().add(row);

                return box;
        }

        // =====================================================
        // APPOINTMENT ROW
        // =====================================================

        private static HBox appointmentRow(
                        String time,
                        String patient,
                        String details,
                        String status) {

                HBox row = new HBox(10);

                row.setAlignment(Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(12, 0, 12, 0));

                Label t = new Label(time);

                t.setPrefWidth(65);

                t.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                10));

                t.setTextFill(
                                Color.web(Theme.TEXT));

                VBox patientBox = new VBox(2);

                Label p = new Label(patient);

                p.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                11));

                p.setTextFill(
                                Color.web(Theme.TEXT));

                Label d = Theme.subtitle(details);

                patientBox.getChildren().addAll(
                                p,
                                d);

                HBox.setHgrow(
                                patientBox,
                                Priority.ALWAYS);

                Label s = new Label(status);

                s.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                9));

                s.setPadding(
                                new Insets(5, 9, 5, 9));

                if (status.equals("Pending")) {

                        s.setTextFill(
                                        Color.web(Theme.ORANGE));

                        s.setStyle(
                                        "-fx-background-color: " +
                                                        Theme.ORANGE_LIGHT + ";" +
                                                        "-fx-background-radius: 12;");

                } else {

                        s.setTextFill(
                                        Color.web(Theme.GREEN));

                        s.setStyle(
                                        "-fx-background-color: " +
                                                        Theme.GREEN_LIGHT + ";" +
                                                        "-fx-background-radius: 12;");
                }

                row.getChildren().addAll(
                                t,
                                patientBox,
                                s);

                return row;
        }

        // =====================================================
        // SCHEDULE ROW
        // =====================================================

        private static HBox scheduleRow(
                        String time,
                        String name,
                        String type) {

                HBox row = new HBox(10);

                row.setPadding(
                                new Insets(12, 0, 12, 0));

                VBox details = new VBox(3);

                Label t = Theme.subtitle(time);

                Label n = new Label(name);

                n.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                10));

                n.setTextFill(
                                Color.web(Theme.TEXT));

                details.getChildren().addAll(
                                t,
                                n);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label typeLabel = Theme.subtitle(type);

                row.getChildren().addAll(
                                details,
                                spacer,
                                typeLabel);

                return row;
        }

        // =====================================================
        // QUICK ACTION
        // =====================================================

        private static VBox quickAction(
                        String icon,
                        String text) {

                VBox box = new VBox(7);

                box.setAlignment(Pos.CENTER);

                box.setPrefWidth(115);

                box.setPadding(
                                new Insets(10));

                box.setStyle(
                                "-fx-background-color: #ffffff;" +
                                                "-fx-border-color: " + Theme.BORDER + ";" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;");

                Label i = new Label(icon);

                i.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                22));

                i.setTextFill(
                                Color.web(Theme.PRIMARY));

                Label l = new Label(text);

                l.setWrapText(true);

                l.setAlignment(Pos.CENTER);

                l.setFont(
                                javafx.scene.text.Font.font(
                                                Theme.FONT,
                                                javafx.scene.text.FontWeight.BOLD,
                                                13));

                l.setTextFill(
                                Color.web(Theme.TEXT));

                box.getChildren().addAll(
                                i,
                                l);

                return box;
        }

        // =====================================================
        // SHOW DASHBOARD
        // =====================================================

        public static void showDashboard() {

                if (dashboardStage != null && dashboardScene != null) {

                        changeScene(dashboardScene);

                }
        }

        // =====================================================
        // MAIN
        // =====================================================

        public static void main(String[] args) {

                launch(args);
        }
}