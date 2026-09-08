package com.sigma.view.motherPages;

import com.sigma.model.MedicalReportMother;
import com.sigma.model.MotherWlcModel;
import com.sigma.view.scenesettings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import com.sigma.model.MedicalReportMother;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MotherDashBoard {

        private Scene MotherdashboardScene;

        /*
         * Firebase मधून आलेला Mother Model
         */
        private MotherWlcModel motherModel;

        private VBox mainContent;

        private final String PINK = "#E84A87";
        private final String DARK = "#24234F";
        private final String PURPLE = "#9B4DCC";

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public MotherDashBoard(MotherWlcModel motherModel) {
                this.motherModel = motherModel;
        }

        // =========================================================
        // DASHBOARD SCENE
        // =========================================================

        public Scene getmotherDashboardScene() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to bottom right, " +
                                                "#FFFFFF 0%, " +
                                                "#FFF6FA 55%, " +
                                                "#F3ECFF 100%);");

                // =====================================================
                // SIDEBAR
                // =====================================================

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(255);
                sidebar.setMinWidth(255);

                sidebar.setPadding(
                                new Insets(18, 16, 18, 16));

                sidebar.setSpacing(8);

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-width: 0 1 0 0;");

                // =====================================================
                // LOGO
                // =====================================================

                ImageView logoView = new ImageView();

                var logoResource = getClass().getResource(
                                "/assets/images/logo/logo.png");

                if (logoResource != null) {

                        Image logoImage = new Image(
                                        logoResource.toExternalForm());

                        logoView.setImage(logoImage);

                        logoView.setFitHeight(125);
                        logoView.setFitWidth(210);

                        logoView.setPreserveRatio(true);
                }

                VBox logoBox = new VBox();

                logoBox.setAlignment(Pos.CENTER);

                logoBox.setPadding(
                                new Insets(0, 0, 10, 0));

                logoBox.getChildren().add(
                                logoView);

                // =====================================================
                // SIDEBAR BUTTONS
                // =====================================================

                Button dashboardButton = createMenuButton(
                                "Dashboard",
                                FontAwesomeIcon.HOME);

                Button pregnancyButton = createMenuButton(
                                "Pregnancy Tracker",
                                FontAwesomeIcon.HEART);

                Button appointmentButton = createMenuButton(
                                "Appointments",
                                FontAwesomeIcon.CALENDAR);

                Button medicineButton = createMenuButton(
                                "Medicines & Reminders",
                                FontAwesomeIcon.MEDKIT);

                Button nutritionButton = createMenuButton(
                                "Diet & Nutrition",
                                FontAwesomeIcon.APPLE);

                Button babyButton = createMenuButton(
                                "Baby Care",
                                FontAwesomeIcon.HEART);

                Button vaccinationButton = createMenuButton(
                                "Vaccination",
                                FontAwesomeIcon.MEDKIT);

                Button reportsButton = createMenuButton(
                                "Reports",
                                FontAwesomeIcon.FILE_TEXT);

                Button emergencyButton = createMenuButton(
                                "Emergency",
                                FontAwesomeIcon.AMBULANCE);

                Button schemesButton = createMenuButton(
                                "Government Schemes",
                                FontAwesomeIcon.BUILDING);

                Button aiButton = createMenuButton(
                                "AI Assistant",
                                FontAwesomeIcon.COG);

                // =====================================================
                // SETTINGS BUTTON
                // =====================================================

                Button settingsButton = createMenuButton(
                                "Settings",
                                FontAwesomeIcon.COG);

                // =====================================================
                // AI ASSISTANT STYLE
                // =====================================================

                aiButton.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #E84A87, #9B4DCC);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-padding: 10px 12px;");

                FontAwesomeIconView aiIcon = (FontAwesomeIconView) aiButton.getGraphic();

                aiIcon.setFill(Color.WHITE);

                // =====================================================
                // SIDEBAR ACTIONS
                // =====================================================

                dashboardButton.setOnAction(e -> {
                        showDashboard();
                });

                pregnancyButton.setOnAction(e -> {

                        PregnancyTracker pregnancyTracker = new PregnancyTracker(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        pregnancyTracker.createPregnancyTrackingPage());

                });

                appointmentButton.setOnAction(e -> {

                        MotherAppoinments appointments = new MotherAppoinments();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        appointments.createAppointmentPage());

                });

                medicineButton.setOnAction(e -> {

                        MotherMedicineReminder medicinePage = new MotherMedicineReminder(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        medicinePage.createMedicineReminderPage());
                });

                nutritionButton.setOnAction(e -> {

                        MotherNutritionDiet nutritionDiet = new MotherNutritionDiet();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        nutritionDiet.createDietNutritionPage());

                });

                babyButton.setOnAction(e -> {

                        MotherBabyCare babyCare = new MotherBabyCare();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        babyCare.createBabyCarePage());

                });

                vaccinationButton.setOnAction(e -> {

                        MotherVaccination vaccination = new MotherVaccination();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        vaccination.createVaccinationPage());

                });

                reportsButton.setOnAction(e -> {

                        MotherReports reports = new MotherReports(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        reports.createReportsPage());
                });
                emergencyButton.setOnAction(e -> {

                        MotherEmergency emergency = new MotherEmergency();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        emergency.createEmergencyPage());

                });

                schemesButton.setOnAction(e -> {

                        GovernmentSchemes schemes = new GovernmentSchemes();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        schemes.createGovernmentSchemesPage());

                });

                aiButton.setOnAction(e -> {

                        MotherAiAssistant aiAssistant = new MotherAiAssistant();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        aiAssistant.createAIAssistantPage());

                });

                // =====================================================
                // SETTINGS ACTION
                // =====================================================

                settingsButton.setOnAction(e -> {

                        MotherSettings settings = new MotherSettings(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        settings.createSettingsPage());

                });

                // =====================================================
                // SIDEBAR CHILDREN
                // =====================================================

                sidebar.getChildren().addAll(

                                logoBox,

                                dashboardButton,
                                pregnancyButton,
                                appointmentButton,
                                medicineButton,
                                nutritionButton,
                                babyButton,
                                vaccinationButton,
                                reportsButton,
                                emergencyButton,
                                schemesButton,
                                aiButton,
                                settingsButton);

                root.setLeft(sidebar);

                // =====================================================
                // TOP HEADER
                // =====================================================

                HBox header = new HBox();

                header.setPadding(
                                new Insets(22, 32, 18, 32));

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setSpacing(25);

                // =====================================================
                // WELCOME
                // =====================================================

                VBox welcomeBox = new VBox();

                welcomeBox.setSpacing(5);

                String motherName = getMotherName();

                Label welcomeText = new Label(
                                "Welcome back, " + motherName + "! 👋");

                welcomeText.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "You're doing great! Let's make this pregnancy journey healthy and happy.");

                subtitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: #77778D;");

                welcomeBox.getChildren().addAll(
                                welcomeText,
                                subtitle);

                HBox.setHgrow(
                                welcomeBox,
                                Priority.ALWAYS);

                // =====================================================
                // SEARCH
                // =====================================================

                HBox searchBox = new HBox();

                searchBox.setAlignment(
                                Pos.CENTER_LEFT);

                searchBox.setSpacing(10);

                searchBox.setPrefWidth(280);
                searchBox.setPrefHeight(45);

                searchBox.setPadding(
                                new Insets(0, 15, 0, 15));

                searchBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E3DCE8;" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                FontAwesomeIconView searchIcon = new FontAwesomeIconView(
                                FontAwesomeIcon.SEARCH);

                searchIcon.setSize("17");

                searchIcon.setFill(
                                Color.web(DARK));

                Label searchText = new Label(
                                "Search anything...");

                searchText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #77778D;");

                searchBox.getChildren().addAll(
                                searchIcon,
                                searchText);

                searchBox.setOnMouseClicked(e -> showMessage("Search"));

                // =====================================================
                // DATE
                // =====================================================

                HBox dateBox = new HBox();

                dateBox.setAlignment(
                                Pos.CENTER);

                dateBox.setSpacing(7);

                FontAwesomeIconView dateIcon = new FontAwesomeIconView(
                                FontAwesomeIcon.CALENDAR);

                dateIcon.setSize("18");

                dateIcon.setFill(
                                Color.web(PURPLE));

                Label dateLabel = new Label(
                                LocalDate.now().format(
                                                DateTimeFormatter.ofPattern(
                                                                "dd MMM yyyy")));

                dateLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                dateBox.getChildren().addAll(
                                dateIcon,
                                dateLabel);

                // =====================================================
                // NOTIFICATION
                // =====================================================

                HBox notificationBox = new HBox();

                notificationBox.setAlignment(
                                Pos.CENTER);

                notificationBox.setSpacing(3);

                FontAwesomeIconView notificationIcon = new FontAwesomeIconView(
                                FontAwesomeIcon.BELL);

                notificationIcon.setSize("22");

                notificationIcon.setFill(
                                Color.web(PINK));

                Label notification = new Label("3");

                notification.setStyle(
                                "-fx-background-color: #E84A87;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-padding: 3px 6px;");

                notificationBox.getChildren().addAll(
                                notificationIcon,
                                notification);

                notificationBox.setOnMouseClicked(e -> showMessage("Notifications"));

                // =====================================================
                // PROFILE
                // =====================================================

                HBox profileBox = new HBox();

                profileBox.setAlignment(
                                Pos.CENTER);

                profileBox.setSpacing(9);

                String firstLetter = motherName.substring(0, 1).toUpperCase();

                Label profileCircle = new Label(firstLetter);

                profileCircle.setAlignment(
                                Pos.CENTER);

                profileCircle.setPrefSize(
                                40,
                                40);

                profileCircle.setStyle(
                                "-fx-background-color: #FFEAF3;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-text-fill: #E84A87;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;");

                VBox profileText = new VBox();

                Label profileName = new Label(motherName);

                profileName.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label profileRole = new Label("Patient");

                profileRole.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                profileText.getChildren().addAll(
                                profileName,
                                profileRole);

                profileBox.getChildren().addAll(
                                profileCircle,
                                profileText);

                profileBox.setOnMouseClicked(e -> showProfile());

                header.getChildren().addAll(
                                welcomeBox,
                                searchBox,
                                dateBox,
                                notificationBox,
                                profileBox);

                root.setTop(header);

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                mainContent = new VBox();

                mainContent.setSpacing(22);

                mainContent.setPadding(
                                new Insets(
                                                5,
                                                30,
                                                30,
                                                30));

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                mainContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                root.setCenter(
                                scrollPane);

                // =====================================================
                // FIRST PAGE = DASHBOARD
                // =====================================================

                showDashboard();

                // =====================================================
                // SCENE
                // =====================================================

                MotherdashboardScene = new Scene(
                                root,
                                scenesettings.rectanguler2d.getWidth(),
                                scenesettings.rectanguler2d.getHeight());

                return MotherdashboardScene;
        }

        // =========================================================
        // GET MOTHER NAME
        // =========================================================

        private String getMotherName() {

                if (motherModel == null) {
                        return "Mother";
                }

                String name = motherModel.getName();

                if (name == null || name.trim().isEmpty()) {
                        return "Mother";
                }

                return name.trim();
        }

        // =========================================================
        // PROFILE
        // =========================================================

        private void showProfile() {

                mainContent.getChildren().clear();

                MotherProfile profile = new MotherProfile(motherModel);

                mainContent.getChildren().add(
                                profile.createProfilePage());
        }

        // =========================================================
        // PREGNANCY CALCULATIONS
        // =========================================================

        private long getPregnancyDays() {

                if (motherModel == null ||
                                motherModel.getLmpDate() == null) {

                        return 0;
                }

                return ChronoUnit.DAYS.between(
                                motherModel.getLmpDate(),
                                LocalDate.now());
        }

        private int getPregnancyWeek() {

                long days = getPregnancyDays();

                if (days <= 0) {
                        return 0;
                }

                return (int) (days / 7);
        }

        private long getRemainingDays() {

                long pregnancyDays = getPregnancyDays();

                return Math.max(
                                0,
                                280 - pregnancyDays);
        }

        private double getPregnancyProgress() {

                long pregnancyDays = getPregnancyDays();

                return Math.min(
                                100,
                                Math.max(
                                                0,
                                                (pregnancyDays * 100.0) / 280));
        }

        private String getTrimester(int week) {

                if (week <= 13) {
                        return "1st Trimester";
                }

                if (week <= 27) {
                        return "2nd Trimester";
                }

                return "3rd Trimester";
        }

        // =========================================================
        // WEEK TEXT
        // =========================================================

        private String getWeekText(int week) {

                if (week <= 0) {
                        return "Current Pregnancy Week";
                }

                if (week % 100 >= 11 &&
                                week % 100 <= 13) {

                        return week + "th Week of Pregnancy";
                }

                switch (week % 10) {

                        case 1:
                                return week + "st Week of Pregnancy";

                        case 2:
                                return week + "nd Week of Pregnancy";

                        case 3:
                                return week + "rd Week of Pregnancy";

                        default:
                                return week + "th Week of Pregnancy";
                }
        }

        // =========================================================
        // SHOW DASHBOARD
        // =========================================================

        private void showDashboard() {

                mainContent.getChildren().clear();

                // =====================================================
                // LEFT COLUMN
                // =====================================================

                VBox leftColumn = new VBox();

                leftColumn.setSpacing(22);

                HBox.setHgrow(
                                leftColumn,
                                Priority.ALWAYS);

                // =====================================================
                // PREGNANCY MAIN CARD
                // =====================================================

                HBox pregnancyCard = createPregnancyMainCard();

                leftColumn.getChildren().add(
                                pregnancyCard);

                // =====================================================
                // QUICK ACTION TITLE
                // =====================================================

                Label quickTitle = new Label(
                                "Quick Actions");

                quickTitle.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                // =====================================================
                // QUICK ACTIONS
                // =====================================================

                HBox quickActions = new HBox();

                quickActions.setSpacing(15);

                Button bookButton = createActionButton(
                                "Book\nAppointment",
                                FontAwesomeIcon.CALENDAR);

                Button reportButton = createActionButton(
                                "Upload\nReports",
                                FontAwesomeIcon.UPLOAD);

                Button medicineAction = createActionButton(
                                "Medicine\nReminders",
                                FontAwesomeIcon.MEDKIT);

                Button dietButton = createActionButton(
                                "Diet\nPlan",
                                FontAwesomeIcon.APPLE);

                // =====================================================
                // QUICK ACTION NAVIGATION
                // =====================================================

                bookButton.setOnAction(e -> {

                        MotherAppoinments appointments = new MotherAppoinments();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        appointments.createAppointmentPage());

                });

                reportButton.setOnAction(e -> {

                        MotherReports reports = new MotherReports(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        reports.createReportsPage());
                });

                medicineAction.setOnAction(e -> {

                        MotherMedicineReminder medicinePage = new MotherMedicineReminder(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        medicinePage.createMedicineReminderPage());

                });

                dietButton.setOnAction(e -> {

                        MotherNutritionDiet nutrition = new MotherNutritionDiet();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        nutrition.createDietNutritionPage());

                });

                quickActions.getChildren().addAll(
                                bookButton,
                                reportButton,
                                medicineAction,
                                dietButton);

                leftColumn.getChildren().addAll(
                                quickTitle,
                                quickActions);

                // =====================================================
                // RIGHT COLUMN
                // =====================================================

                VBox rightColumn = new VBox();

                rightColumn.setSpacing(20);

                rightColumn.setPrefWidth(390);

                rightColumn.setMinWidth(360);

                VBox progressCard = createPregnancyProgressCard();

                VBox appointmentCard = createAppointmentCard();

                VBox healthTip = createHealthTipCard();

                rightColumn.getChildren().addAll(
                                progressCard,
                                appointmentCard,
                                healthTip);

                // =====================================================
                // DASHBOARD TWO COLUMNS
                // =====================================================

                HBox dashboardLayout = new HBox();

                dashboardLayout.setSpacing(22);

                dashboardLayout.getChildren().addAll(
                                leftColumn,
                                rightColumn);

                mainContent.getChildren().add(
                                dashboardLayout);
        }

        // =========================================================
        // PREGNANCY MAIN CARD
        // =========================================================

        private HBox createPregnancyMainCard() {

                HBox card = new HBox();

                card.setPadding(
                                new Insets(25));

                card.setSpacing(10);

                card.setPrefHeight(285);
                card.setMinHeight(285);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: #FFEAF3;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: #F5C7DB;" +
                                                "-fx-border-radius: 20;");

                VBox details = new VBox();

                details.setSpacing(10);

                HBox.setHgrow(
                                details,
                                Priority.ALWAYS);

                // =====================================================
                // YOU ARE IN
                // =====================================================

                Label smallText = new Label("You are in");

                smallText.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                int weekNumber = getPregnancyWeek();

                Label week = new Label(
                                getWeekText(weekNumber));

                week.setStyle(
                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                long remainingDays = getRemainingDays();

                Label trimester = new Label(
                                getTrimester(weekNumber)
                                                + "   •   "
                                                + remainingDays
                                                + " Days to Go");

                trimester.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-text-fill: #666680;");

                Label progressText = new Label(
                                "Pregnancy Progress");

                progressText.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #E84A87;");

                HBox progressBar = new HBox();

                progressBar.setPrefHeight(12);

                progressBar.setMaxWidth(430);

                progressBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;");

                HBox progressFill = new HBox();

                double progress = getPregnancyProgress();

                progressFill.setPrefWidth(
                                430 * progress / 100);

                progressFill.setPrefHeight(12);

                progressFill.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #F54B87, #E84A87);" +
                                                "-fx-background-radius: 10;");

                progressBar.getChildren().add(
                                progressFill);

                Label completed = new Label(
                                String.format(
                                                "%.0f%% Completed",
                                                progress));

                completed.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #E84A87;");

                Button detailsButton = createSmallButton(
                                "View Details  →");

                detailsButton.setOnAction(e -> {

                        PregnancyTracker pregnancyTracker = new PregnancyTracker(motherModel);

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        pregnancyTracker.createPregnancyTrackingPage());

                });

                details.getChildren().addAll(
                                smallText,
                                week,
                                trimester,
                                progressText,
                                progressBar,
                                completed,
                                detailsButton);

                // =====================================================
                // MOTHER IMAGE
                // =====================================================

                ImageView motherImage = new ImageView();

                String imagePath = "/assets/images/logo/PregnantMother.png";

                var motherResource = getClass().getResource(imagePath);

                System.out.println("Mother image resource = " + motherResource);

                if (motherResource != null) {

                        Image image = new Image(
                                        motherResource.toExternalForm());

                        System.out.println("Mother image loaded = " + !image.isError());

                        motherImage.setImage(image);

                        motherImage.setFitWidth(350);
                        motherImage.setFitHeight(350);

                        motherImage.setPreserveRatio(true);
                        motherImage.setSmooth(true);

                } else {

                        System.out.println(
                                        "❌ Mother image NOT FOUND: " + imagePath);
                }

                VBox imageBox = new VBox();

                imageBox.setAlignment(
                                Pos.CENTER_RIGHT);

                imageBox.setPrefWidth(270);
                imageBox.setMinWidth(230);
                imageBox.setPrefHeight(250);

                imageBox.getChildren().add(
                                motherImage);

                card.getChildren().addAll(
                                details,
                                imageBox);

                return card;
        }

        // =========================================================
        // PREGNANCY PROGRESS CARD
        // =========================================================

        private VBox createPregnancyProgressCard() {

                VBox card = createWhiteCard();

                card.setPrefHeight(225);

                HBox heading = createCardHeading(
                                "Pregnancy Progress",
                                FontAwesomeIcon.CALENDAR);

                HBox pregnancyInfo = new HBox();

                pregnancyInfo.setAlignment(
                                Pos.CENTER_LEFT);

                pregnancyInfo.setSpacing(12);

                VBox pregnancyDetails = new VBox();

                pregnancyDetails.setSpacing(3);

                HBox.setHgrow(
                                pregnancyDetails,
                                Priority.ALWAYS);

                int weekNumber = getPregnancyWeek();

                Label week = new Label(
                                getWeekText(weekNumber));

                week.setStyle(
                                "-fx-font-size: 24px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #E84A87;");

                Label trimester = new Label(
                                getTrimester(weekNumber));

                trimester.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: #666680;");

                Label babySize = new Label(
                                "Baby is the size of Corn 🌽");

                babySize.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #24234F;" +
                                                "-fx-font-weight: bold;");

                pregnancyDetails.getChildren().addAll(
                                week,
                                trimester,
                                babySize);

                pregnancyInfo.getChildren().add(
                                pregnancyDetails);

                javafx.scene.control.Separator separator = new javafx.scene.control.Separator();

                separator.setStyle(
                                "-fx-background-color: #E7DCE8;");

                HBox stats = new HBox();

                stats.setSpacing(12);

                stats.setAlignment(
                                Pos.CENTER);

                stats.getChildren().addAll(

                                createStat(
                                                "30.1 cm",
                                                "Baby Length"),

                                createStat(
                                                "600 g",
                                                "Baby Weight"),

                                createStat(
                                                "8-10 /day",
                                                "Kick Count"),

                                createStat(
                                                "+6.2 kg",
                                                "Your Weight"));

                card.getChildren().addAll(
                                heading,
                                pregnancyInfo,
                                separator,
                                stats);

                return card;
        }

        // =========================================================
        // STAT
        // =========================================================

        private VBox createStat(
                        String value,
                        String title) {

                VBox box = new VBox();

                box.setAlignment(
                                Pos.CENTER);

                box.setSpacing(4);

                box.setPrefWidth(75);

                Label valueLabel = new Label(value);

                valueLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label titleLabel = new Label(title);

                titleLabel.setWrapText(true);

                titleLabel.setAlignment(
                                Pos.CENTER);

                titleLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                box.getChildren().addAll(
                                valueLabel,
                                titleLabel);

                return box;
        }

        // =========================================================
        // APPOINTMENT CARD
        // =========================================================

        private VBox createAppointmentCard() {

                VBox card = createWhiteCard();

                HBox heading = createCardHeading(
                                "Upcoming Appointment",
                                FontAwesomeIcon.CALENDAR);

                Label doctor = new Label(
                                "Dr. Anjali Mehta");

                doctor.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #E84A87;");

                Label details = new Label(
                                "Obstetrician & Gynecologist\n" +
                                                "20 May 2024  •  11:00 AM\n" +
                                                "CarePlus Women Clinic, Pune");

                details.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-line-spacing: 5px;");

                Button view = createSmallButton(
                                "View Appointment  →");

                view.setMaxWidth(
                                Double.MAX_VALUE);

                view.setOnAction(e -> {

                        MotherAppoinments appointments = new MotherAppoinments();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        appointments.createAppointmentPage());

                });

                card.getChildren().addAll(
                                heading,
                                doctor,
                                details,
                                view);

                return card;
        }

        // =========================================================
        // HEALTH TIP
        // =========================================================

        private VBox createHealthTipCard() {

                VBox card = createWhiteCard();

                card.setStyle(
                                "-fx-background-color: #FFF8FC;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 18;");

                HBox heading = createCardHeading(
                                "Today's Health Tip",
                                FontAwesomeIcon.LIGHTBULB_ALT);

                Label text = new Label(
                                "Stay hydrated and follow a healthy\n" +
                                                "balanced diet. Take adequate rest\n" +
                                                "and attend your scheduled check-ups.");

                text.setWrapText(true);

                text.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-line-spacing: 4px;");

                Button explore = createSmallButton(
                                "Explore More Tips  →");

                explore.setOnAction(e -> {

                        MotherAiAssistant aiAssistant = new MotherAiAssistant();

                        mainContent.getChildren().clear();

                        mainContent.getChildren().add(
                                        aiAssistant.createAIAssistantPage());

                });

                card.getChildren().addAll(
                                heading,
                                text,
                                explore);

                return card;
        }

        // =========================================================
        // CARD HEADING
        // =========================================================

        private HBox createCardHeading(
                        String text,
                        FontAwesomeIcon iconType) {

                HBox heading = new HBox();

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                heading.setSpacing(10);

                FontAwesomeIconView icon = new FontAwesomeIconView(
                                iconType);

                icon.setSize("19");

                icon.setFill(
                                Color.web(PURPLE));

                Label title = new Label(text);

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                heading.getChildren().addAll(
                                icon,
                                title);

                return heading;
        }

        // =========================================================
        // WHITE CARD
        // =========================================================

        private VBox createWhiteCard() {

                VBox card = new VBox();

                card.setPadding(
                                new Insets(20));

                card.setSpacing(10);

                card.setPrefHeight(190);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 18;");

                return card;
        }

        // =========================================================
        // MENU BUTTON
        // =========================================================

        private Button createMenuButton(
                        String text,
                        FontAwesomeIcon iconType) {

                FontAwesomeIconView icon = new FontAwesomeIconView(
                                iconType);

                icon.setSize("17");

                icon.setFill(
                                Color.web(DARK));

                Button button = new Button(text);

                button.setGraphic(icon);

                button.setPrefWidth(220);

                button.setPrefHeight(46);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setGraphicTextGap(15);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #24234F;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-padding: 10px 12px;");

                button.setOnMouseEntered(e -> {

                        button.setStyle(
                                        "-fx-background-color: #FFEAF3;" +
                                                        "-fx-text-fill: #E84A87;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-padding: 10px 12px;");

                        icon.setFill(
                                        Color.web(PINK));
                });

                button.setOnMouseExited(e -> {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #24234F;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-background-radius: 10;" +
                                                        "-fx-padding: 10px 12px;");

                        icon.setFill(
                                        Color.web(DARK));
                });

                return button;
        }

        // =========================================================
        // QUICK ACTION BUTTON
        // =========================================================

        private Button createActionButton(
                        String text,
                        FontAwesomeIcon iconType) {

                FontAwesomeIconView icon = new FontAwesomeIconView(
                                iconType);

                icon.setSize("28");

                icon.setFill(
                                Color.web(PINK));

                Button button = new Button(text);

                button.setGraphic(icon);

                button.setContentDisplay(
                                javafx.scene.control.ContentDisplay.TOP);

                button.setGraphicTextGap(10);

                button.setPrefWidth(145);

                button.setPrefHeight(105);

                button.setWrapText(true);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #24234F;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 15;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 15;" +
                                                "-fx-padding: 12px;");

                button.setOnMouseEntered(e -> {

                        button.setStyle(
                                        "-fx-background-color: #FFEAF3;" +
                                                        "-fx-text-fill: #E84A87;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-border-color: #E84A87;" +
                                                        "-fx-border-radius: 15;" +
                                                        "-fx-padding: 12px;");

                });

                button.setOnMouseExited(e -> {

                        button.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #24234F;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-border-color: #E7DCE8;" +
                                                        "-fx-border-radius: 15;" +
                                                        "-fx-padding: 12px;");

                });

                return button;
        }

        // =========================================================
        // SMALL BUTTON
        // =========================================================

        private Button createSmallButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefHeight(38);

                button.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #F54B87, #9B4DCC);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-padding: 8px 20px;");

                return button;
        }

        // =========================================================
        // MESSAGE
        // =========================================================

        private void showMessage(
                        String pageName) {

                System.out.println(
                                "Open page: " + pageName);
        }

        // =========================================================
        // BACK DASHBOARD
        // =========================================================

        public void backDashMethod() {

                showDashboard();

        }
}