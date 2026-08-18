package com.sigma.view.doctorpages;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DoctorDashboard extends Application {

        public static Stage DoctorDashboardStage;

        private Scene DoctorDashboardScene;

        private static final String PINK = "#D94A91";
        private static final String LIGHT_PINK = "#FCEAF3";
        private static final String PURPLE = "#8B5CF6";

        private static final String DARK_TEXT = "#172554";
        private static final String SECONDARY_TEXT = "#64748B";

        private static final String BORDER = "#E8E8F0";
        private static final String WHITE = "#FFFFFF";

        private static final String FONT_FAMILY = "Arial";

        private SVGPath wave1;
        private SVGPath wave2;

        @Override
        public void start(Stage stage) {

                DoctorDashboardStage = stage;

                StackPane root = new StackPane();

                root.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to bottom right," +
                                                "#FFFFFF 0%," +
                                                "#FFF9FC 55%," +
                                                "#F8F4FF 100%);");

                Circle glow1 = new Circle();

                glow1.radiusProperty().bind(root.heightProperty().multiply(0.25));

                glow1.setFill(new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#FFD8E9", 0.25)),
                                new Stop(1, Color.TRANSPARENT)));

                StackPane.setAlignment(
                                glow1,
                                Pos.TOP_LEFT);

                Circle glow2 = new Circle();

                glow2.radiusProperty().bind(
                                root.heightProperty().multiply(0.22));

                glow2.setFill(new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#E5D7FF", 0.20)),
                                new Stop(1, Color.TRANSPARENT)));

                StackPane.setAlignment(glow2, Pos.BOTTOM_RIGHT);

                wave1 = new SVGPath();

                wave1.setFill(new LinearGradient(0, 0, 1, 0, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(
                                                0,
                                                Color.web("#F54B87", 0.10)),

                                new Stop(
                                                0.5,
                                                Color.web("#E78BC0", 0.06)),

                                new Stop(
                                                1,
                                                Color.web("#9B4DCC", 0.10))));

                wave2 = new SVGPath();

                wave2.setFill(
                                new LinearGradient(
                                                0,
                                                0,
                                                1,
                                                0,
                                                true,
                                                CycleMethod.NO_CYCLE,

                                                new Stop(
                                                                0,
                                                                Color.web("#FFB5D0", 0.08)),

                                                new Stop(
                                                                0.5,
                                                                Color.web("#E4B8F0", 0.06)),

                                                new Stop(
                                                                1,
                                                                Color.web("#B99BEA", 0.09))));

                BorderPane borderPane = new BorderPane();

                borderPane.setPadding(
                                new Insets(
                                                18,
                                                25,
                                                20,
                                                20));

                VBox sidebar = new VBox(8);

                sidebar.setPrefWidth(245);

                sidebar.setPadding(
                                new Insets(
                                                20,
                                                15,
                                                20,
                                                15));

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 0 22 22 0;" +
                                                "-fx-border-color: #F0E9EF;" +
                                                "-fx-border-width: 0 1 0 0;");

                Image logo = new Image("/assets/images/logo/logo.png");

                ImageView logoImageView = new ImageView(logo);

                logoImageView.setFitWidth(175);
                logoImageView.setFitHeight(125);

                logoImageView.setPreserveRatio(true);
                logoImageView.setSmooth(true);

                VBox logoBox = new VBox(logoImageView);

                logoBox.setAlignment(Pos.CENTER);

                logoBox.setPadding(new Insets(0, 0, 15, 0));

                Button dashboardBtn = createSideButton(
                                "⌂",
                                "Dashboard",
                                true);

                Button appointmentsBtn = createSideButton(
                                "▣",
                                "Appointments",
                                false);

                Button patientsBtn = createSideButton(
                                "♙",
                                "Patients",
                                false);

                Button reportsBtn = createSideButton(
                                "▦",
                                "Patient Reports",
                                false);

                /*
                 * Button prescriptionsBtn = createSideButton(
                 * "✎",
                 * "Prescriptions",
                 * false);
                 * 
                 * Button messagesBtn = createSideButton(
                 * "✉",
                 * "Messages",
                 * false);
                 * 
                 * Button calendarBtn = createSideButton(
                 * "▣",
                 * "Calendar",
                 * false);
                 */
                Button profileBtn = createSideButton(
                                "♙",
                                "Profile",
                                false);

                Button settingsBtn = createSideButton(
                                "⚙",
                                "Settings",
                                false);

                // =====================================================
                // APPOINTMENTS NAVIGATION
                // =====================================================

                appointmentsBtn.setOnAction(event -> {

                        try {

                                DoctorAppointments appointmentPage = new DoctorAppointments();

                                Scene appointmentScene = appointmentPage.getAppointmentPageScene(() -> {

                                        // Back to Dashboard
                                        DoctorDashboardStage.setScene(
                                                        DoctorDashboardScene);

                                        DoctorDashboardStage.show();
                                });

                                // Open Appointment Page
                                DoctorDashboardStage.setScene(appointmentScene);
                                DoctorDashboardStage.show();

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                javafx.scene.control.Alert errorAlert = new javafx.scene.control.Alert(
                                                javafx.scene.control.Alert.AlertType.ERROR);

                                errorAlert.setTitle("Navigation Error");
                                errorAlert.setHeaderText("Appointment Page could not be opened");
                                errorAlert.setContentText(
                                                "Error: " + ex.getMessage());
                                errorAlert.showAndWait();
                        }
                });

                Region sidebarSpacer = new Region();

                VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

                VBox doctorBox = new VBox(4);

                Label doctorName = new Label("👩🏻‍⚕  Dr. Priya Sharma");

                doctorName.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 14));

                doctorName.setTextFill(Color.web(DARK_TEXT));

                Label specialization = new Label("Gynecologist");

                specialization.setFont(Font.font(FONT_FAMILY, 12));

                specialization.setTextFill(Color.web(SECONDARY_TEXT));

                Label online = new Label("●  Online");

                online.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 12));

                online.setTextFill(Color.web("#22A06B"));

                doctorBox.getChildren().addAll(doctorName, specialization, online);

                doctorBox.setPadding(new Insets(13));

                doctorBox.setStyle(
                                "-fx-background-color: #FFF5F9; -fx-background-radius: 14; -fx-border-color: #F6DCE9; -fx-border-radius: 14;");

                sidebar.getChildren().addAll(logoBox, dashboardBtn, appointmentsBtn, patientsBtn, reportsBtn,
                                profileBtn, settingsBtn, sidebarSpacer, doctorBox);

                borderPane.setLeft(sidebar);

                VBox mainContent = new VBox(18);

                mainContent.setPadding(new Insets(5, 10, 5, 25));

                BorderPane header = new BorderPane();

                VBox welcomeBox = new VBox(5);

                Label welcome = new Label("Welcome back, Dr. Priya Sharma 👋");

                welcome.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 27));

                welcome.setTextFill(Color.web(DARK_TEXT));

                Label subtitle = new Label("Here's what's happening in your clinic today.");

                subtitle.setFont(Font.font(FONT_FAMILY, 14));

                subtitle.setTextFill(Color.web(SECONDARY_TEXT));

                welcomeBox.getChildren().addAll(welcome, subtitle);

                HBox rightHeader = new HBox(15);

                rightHeader.setAlignment(Pos.CENTER_RIGHT);

                Label date = new Label("13 August 2026, Thursday");

                date.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));

                date.setTextFill(Color.web(DARK_TEXT));

                Button notification = new Button("🔔");

                notification.setStyle(
                                "-fx-background-color: white; -fx-border-color: #E8E3EA;-fx-border-radius: 10;-fx-background-radius: 10;-fx-padding: 9 12;");

                rightHeader.getChildren().addAll(date, notification);

                header.setLeft(welcomeBox);

                header.setRight(rightHeader);

                HBox cards = new HBox(15);

                VBox appointmentCard = createStatCard(
                                "📅",
                                "18",
                                "Today's\nAppointments",
                                "#FFF0F6",
                                PINK);

                VBox patientCard = createStatCard(
                                "👥",
                                "6",
                                "New Patients\nThis Week",
                                "#F4EEFF",
                                PURPLE);

                VBox reportsCard = createStatCard(
                                "📄",
                                "32",
                                "Reports\nThis Week",
                                "#EEF5FF",
                                "#4285D4");

                VBox ratingCard = createStatCard(
                                "★",
                                "4.8",
                                "Average Rating\nThis Month",
                                "#EEFAF4",
                                "#25A66A");

                cards.getChildren().addAll(
                                appointmentCard,
                                patientCard,
                                reportsCard,
                                ratingCard);

                for (Node card : cards.getChildren()) {

                        HBox.setHgrow(
                                        card,
                                        Priority.ALWAYS);
                }

                HBox middleSection = new HBox(18);

                VBox appointmentsBox = createAppointmentsSection();

                VBox scheduleBox = createSchedule();

                HBox.setHgrow(appointmentsBox, Priority.SOMETIMES);

                HBox.setHgrow(scheduleBox, Priority.ALWAYS);

                middleSection.getChildren().addAll(
                                appointmentsBox,
                                scheduleBox);

                VBox quickActions = createQuickActions();

                mainContent.getChildren().addAll(
                                header,
                                cards,
                                middleSection,
                                quickActions);

                VBox.setVgrow(
                                middleSection,
                                Priority.ALWAYS);

                borderPane.setCenter(
                                mainContent);

                // IMPORTANT:
                // Background first, waves second, UI last.
                // त्यामुळे waves UI च्या मागे राहतील.

                root.getChildren().addAll(
                                glow1,
                                glow2,
                                wave1,
                                wave2,
                                borderPane);

                root.widthProperty().addListener((obs, oldValue, newValue) -> {
                        updateWaves(newValue.doubleValue(), root.getHeight());
                });

                root.heightProperty().addListener(
                                (obs, oldValue, newValue) -> {

                                        updateWaves(
                                                        root.getWidth(),
                                                        newValue.doubleValue());
                                });

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(root, 1400, 850);

                DoctorDashboardScene = scene;

                DoctorDashboardStage.setTitle("MaaCare AI - Doctor Dashboard");

                DoctorDashboardStage.setScene(DoctorDashboardScene);

                DoctorDashboardStage.setMinWidth(1100);

                DoctorDashboardStage.setMinHeight(700);

                DoctorDashboardStage.setMaximized(true);

                DoctorDashboardStage.show();

                updateWaves(
                                root.getWidth(),
                                root.getHeight());
        }

        // =====================================================
        // SIDEBAR BUTTON
        // =====================================================

        private Button createSideButton(
                        String icon,
                        String text,
                        boolean active) {

                Button button = new Button(
                                icon + "    " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                13));

                button.setPadding(
                                new Insets(
                                                12,
                                                15,
                                                12,
                                                15));

                if (active) {

                        button.setStyle(
                                        "-fx-background-color: #FCE8F2;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-text-fill: " +
                                                        PINK +
                                                        ";" +
                                                        "-fx-cursor: hand;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " +
                                                        DARK_TEXT +
                                                        ";" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-cursor: hand;");

                        button.setOnMouseEntered(e -> {

                                button.setStyle(
                                                "-fx-background-color: #FFF1F7;" +
                                                                "-fx-background-radius: 10;" +
                                                                "-fx-text-fill: " +
                                                                PINK +
                                                                ";" +
                                                                "-fx-cursor: hand;");
                        });

                        button.setOnMouseExited(e -> {

                                button.setStyle(
                                                "-fx-background-color: transparent;" +
                                                                "-fx-text-fill: " +
                                                                DARK_TEXT +
                                                                ";" +
                                                                "-fx-background-radius: 10;" +
                                                                "-fx-cursor: hand;");
                        });
                }

                return button;
        }

        // =====================================================
        // STAT CARD
        // =====================================================

        private VBox createStatCard(
                        String icon,
                        String value,
                        String title,
                        String iconBackground,
                        String iconColor) {

                VBox card = new VBox(7);

                card.setPadding(
                                new Insets(17));

                card.setPrefHeight(
                                125);

                card.setMinHeight(
                                115);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-radius: 16;");

                HBox top = new HBox();

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                22));

                iconLabel.setTextFill(
                                Color.web(iconColor));

                StackPane iconBox = new StackPane(
                                iconLabel);

                iconBox.setPrefSize(
                                48,
                                48);

                iconBox.setStyle(
                                "-fx-background-color: " +
                                                iconBackground +
                                                ";" +
                                                "-fx-background-radius: 13;");

                top.getChildren().add(
                                iconBox);

                Label valueLabel = new Label(value);

                valueLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                27));

                valueLabel.setTextFill(
                                Color.web(DARK_TEXT));

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                12));

                titleLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                card.getChildren().addAll(
                                top,
                                valueLabel,
                                titleLabel);

                return card;
        }

        // =====================================================
        // APPOINTMENTS SECTION
        // =====================================================

        private VBox createAppointmentsSection() {

                VBox box = new VBox(12);

                box.setPadding(
                                new Insets(17));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-radius: 16;");

                HBox heading = new HBox();

                Label title = new Label(
                                "Today's Appointments");

                title.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                16));

                title.setTextFill(
                                Color.web(DARK_TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label viewAll = new Label(
                                "View All →");

                viewAll.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                12));

                viewAll.setTextFill(
                                Color.web(PURPLE));

                heading.getChildren().addAll(
                                title,
                                spacer,
                                viewAll);

                box.getChildren().add(
                                heading);

                box.getChildren().addAll(

                                appointmentRow(
                                                "09:30 AM",
                                                "Priya Sharma",
                                                "28 Y | 24 Weeks Pregnant",
                                                "Confirmed"),

                                appointmentRow(
                                                "10:15 AM",
                                                "Neha Kulkarni",
                                                "32 Y | Routine Checkup",
                                                "Confirmed"),

                                appointmentRow(
                                                "11:00 AM",
                                                "Sneha Patil",
                                                "26 Y | First Consultation",
                                                "Confirmed"),

                                appointmentRow(
                                                "12:00 PM",
                                                "Ritika Singh",
                                                "30 Y | Ultrasound Follow-up",
                                                "Pending"));

                return box;
        }

        // =====================================================
        // APPOINTMENT ROW
        // =====================================================

        private HBox appointmentRow(
                        String time,
                        String name,
                        String details,
                        String statusText) {

                HBox row = new HBox(12);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                10,
                                                5,
                                                10,
                                                5));

                Label timeLabel = new Label(time);

                timeLabel.setPrefWidth(
                                75);

                timeLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                12));

                timeLabel.setTextFill(
                                Color.web(DARK_TEXT));

                Circle patientCircle = new Circle(
                                20,
                                Color.web("#FCE8F2"));

                Label patientIcon = new Label("👩");

                StackPane avatar = new StackPane(
                                patientCircle,
                                patientIcon);

                VBox patient = new VBox(3);

                Label nameLabel = new Label(name);

                nameLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                12));

                nameLabel.setTextFill(
                                Color.web(DARK_TEXT));

                Label detailsLabel = new Label(details);

                detailsLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                11));

                detailsLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                patient.getChildren().addAll(
                                nameLabel,
                                detailsLabel);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label status = new Label(
                                statusText);

                if (statusText.equals("Confirmed")) {

                        status.setStyle(
                                        "-fx-background-color: #E6F7EF;" +
                                                        "-fx-text-fill: #20965F;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 6 12;" +
                                                        "-fx-font-weight: bold;");

                } else {

                        status.setStyle(
                                        "-fx-background-color: #FFF4E5;" +
                                                        "-fx-text-fill: #D68A20;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 6 12;" +
                                                        "-fx-font-weight: bold;");
                }

                Label arrow = new Label("›");

                arrow.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                22));

                arrow.setTextFill(
                                Color.web(SECONDARY_TEXT));

                row.getChildren().addAll(
                                timeLabel,
                                avatar,
                                patient,
                                spacer,
                                status,
                                arrow);

                row.setStyle(
                                "-fx-border-color: #F0EDF2;" +
                                                "-fx-border-width: 0 0 1 0;");

                return row;
        }

        // =====================================================
        // SCHEDULE
        // =====================================================

        private VBox createSchedule() {

                VBox box = new VBox(12);

                box.setPadding(
                                new Insets(17));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-radius: 16;");

                HBox heading = new HBox();

                Label title = new Label(
                                "Today's Schedule");

                title.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                16));

                title.setTextFill(
                                Color.web(DARK_TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label calendar = new Label(
                                "View Calendar");

                calendar.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                12));

                calendar.setTextFill(
                                Color.web(PURPLE));

                heading.getChildren().addAll(
                                title,
                                spacer,
                                calendar);

                box.getChildren().add(
                                heading);

                box.getChildren().addAll(

                                scheduleRow(
                                                "09:30 AM - 10:00 AM",
                                                "Priya Sharma",
                                                "Consultation",
                                                PINK),

                                scheduleRow(
                                                "10:15 AM - 10:45 AM",
                                                "Neha Kulkarni",
                                                "Consultation",
                                                "#4285D4"),

                                scheduleRow(
                                                "11:00 AM - 11:30 AM",
                                                "Sneha Patil",
                                                "Consultation",
                                                "#36A269"),

                                scheduleRow(
                                                "12:00 PM - 12:30 PM",
                                                "Ritika Singh",
                                                "Follow-up",
                                                "#D89028"));

                return box;
        }

        // =====================================================
        // SCHEDULE ROW
        // =====================================================

        private HBox scheduleRow(
                        String time,
                        String name,
                        String purpose,
                        String accent) {

                HBox row = new HBox(12);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                10,
                                                5,
                                                10,
                                                8));

                VBox timePatient = new VBox(4);

                Label timeLabel = new Label(time);

                timeLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                11));

                timeLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Label nameLabel = new Label(name);

                nameLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                12));

                nameLabel.setTextFill(
                                Color.web(DARK_TEXT));

                timePatient.getChildren().addAll(
                                timeLabel,
                                nameLabel);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label purposeLabel = new Label(purpose);

                purposeLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                12));

                purposeLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Region accentLine = new Region();

                accentLine.setPrefWidth(3);
                accentLine.setPrefHeight(45);

                accentLine.setStyle(
                                "-fx-background-color: " +
                                                accent +
                                                ";" +
                                                "-fx-background-radius: 5;");

                row.getChildren().addAll(
                                accentLine,
                                timePatient,
                                spacer,
                                purposeLabel);

                row.setStyle(
                                "-fx-border-color: #F0EDF2;" +
                                                "-fx-border-width: 0 0 1 0;");

                return row;
        }

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        private VBox createQuickActions() {

                VBox box = new VBox(10);

                box.setPadding(
                                new Insets(15));

                box.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right," +
                                                "#FFF5FA," +
                                                "#FAF6FF" +
                                                ");" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: #F0E4F0;" +
                                                "-fx-border-radius: 16;");

                Label title = new Label(
                                "Quick Actions");

                title.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                16));

                title.setTextFill(
                                Color.web(DARK_TEXT));

                HBox actions = new HBox(15);

                actions.getChildren().addAll(

                                createActionButton(
                                                "📅",
                                                "Add Appointment"),

                                createActionButton(
                                                "👤",
                                                "Add Patient"),

                                createActionButton(
                                                "📄",
                                                "Write Prescription"),

                                createActionButton(
                                                "⬆",
                                                "Upload Report"),

                                createActionButton(
                                                "💬",
                                                "Send Message"));

                box.getChildren().addAll(
                                title,
                                actions);

                return box;
        }

        // =====================================================
        // QUICK ACTION BUTTON
        // =====================================================

        private VBox createActionButton(
                        String icon,
                        String text) {

                VBox button = new VBox(7);

                button.setAlignment(
                                Pos.CENTER);

                button.setPrefWidth(
                                145);

                button.setPrefHeight(
                                70);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-border-color: #EEE5F0;" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-cursor: hand;");

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                20));

                iconLabel.setTextFill(
                                Color.web(PURPLE));

                Label textLabel = new Label(text);

                textLabel.setFont(
                                Font.font(
                                                FONT_FAMILY,
                                                FontWeight.BOLD,
                                                11));

                textLabel.setTextFill(
                                Color.web(DARK_TEXT));

                button.getChildren().addAll(
                                iconLabel,
                                textLabel);

                return button;
        }

        // =====================================================
        // RESPONSIVE WAVES
        // =====================================================

        private void updateWaves(
                        double width,
                        double height) {

                if (width <= 0 || height <= 0) {
                        return;
                }

                // =================================================
                // WAVE 1
                // =================================================

                double startY1 = height * 0.88;

                wave1.setContent(

                                "M 0 " + startY1 +

                                                " C " +
                                                (width * 0.16) +
                                                " " +
                                                (height * 0.82) +

                                                ", " +
                                                (width * 0.32) +
                                                " " +
                                                (height * 0.94) +

                                                ", " +
                                                (width * 0.50) +
                                                " " +
                                                (height * 0.87) +

                                                " C " +
                                                (width * 0.68) +
                                                " " +
                                                (height * 0.80) +

                                                ", " +
                                                (width * 0.85) +
                                                " " +
                                                (height * 0.93) +

                                                ", " +
                                                width +
                                                " " +
                                                (height * 0.85) +

                                                " L " +
                                                width +
                                                " " +
                                                height +

                                                " L 0 " +
                                                height +

                                                " Z");

                // =================================================
                // WAVE 2
                // =================================================

                double startY2 = height * 0.92;

                wave2.setContent(

                                "M 0 " + startY2 +

                                                " C " +
                                                (width * 0.20) +
                                                " " +
                                                (height * 0.86) +

                                                ", " +
                                                (width * 0.38) +
                                                " " +
                                                (height * 0.97) +

                                                ", " +
                                                (width * 0.55) +
                                                " " +
                                                (height * 0.91) +

                                                " C " +
                                                (width * 0.72) +
                                                " " +
                                                (height * 0.85) +

                                                ", " +
                                                (width * 0.88) +
                                                " " +
                                                (height * 0.96) +

                                                ", " +
                                                width +
                                                " " +
                                                (height * 0.90) +

                                                " L " +
                                                width +
                                                " " +
                                                height +

                                                " L 0 " +
                                                height +

                                                " Z");
        }

        // =====================================================
        // MAIN
        // =====================================================

     
}