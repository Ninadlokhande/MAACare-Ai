package com.sigma.view.motherPages;

import com.sigma.model.MotherWlcModel;
import com.sigma.view.scenesettings;

import com.sigma.controller.HospitalController.AppointmentController;
import com.sigma.controller.HospitalController.BedBookingController;
import com.sigma.controller.doctorController.DoctorAppointmentController;

import com.sigma.model.Appointment;
import com.sigma.model.BedBooking;
import com.sigma.model.DoctorModel.DoctorAppointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.prefs.Preferences;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
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

    /*
     * Logged-in Mother चा Firebase Authentication UID
     */
    private String motherUid;

    private VBox mainContent;

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";


    // =========================================================
    // NOTIFICATION
    // =========================================================

    /*
     * Notification badge label
     *
     * आधी hardcoded "3" होता.
     * आता Firebase मधून actual count येईल.
     */
    private Label notificationLabel;

    /*
     * Current notifications
     */
    private final List<String> currentNotifications =
            new ArrayList<>();

    /*
     * Persistent read/unread state.
     *
     * Read state is stored locally for the logged-in Mother.
     * The key contains the notification type + message, so when an
     * appointment status changes, a new notification becomes unread.
     */
    private final Preferences notificationPreferences =
            Preferences.userNodeForPackage(MotherDashBoard.class);

    private static class NotificationItem {
        private final String key;
        private final String message;

        NotificationItem(String key, String message) {
            this.key = key;
            this.message = message;
        }
    }


    // =========================================================
    // CONTROLLERS
    // =========================================================

    /*
     * Existing controllers वापरले आहेत.
     *
     * नवीन Notification DAO / Model / Controller
     * तयार करण्याची गरज नाही.
     */
    private final DoctorAppointmentController doctorAppointmentController =
            new DoctorAppointmentController();

    private final AppointmentController hospitalAppointmentController =
            new AppointmentController();

    private final BedBookingController bedBookingController =
            new BedBookingController();


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MotherDashBoard(
            MotherWlcModel motherModel,
            String motherUid) {

        this.motherModel = motherModel;
        this.motherUid = motherUid;

        System.out.println(
                "[MOTHER DASHBOARD] Mother UID = "
                        + motherUid
        );
    }


    /*
     * Old constructor
     *
     * Existing code break होऊ नये म्हणून ठेवला आहे.
     */
    public MotherDashBoard(MotherWlcModel motherModel) {
        this(motherModel, null);
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
            "#F3ECFF 100%);"
        );


        // =====================================================
        // SIDEBAR
        // =====================================================

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(255);
        sidebar.setMinWidth(255);

        sidebar.setPadding(
                new Insets(18, 16, 18, 16)
        );

        sidebar.setSpacing(2);

        sidebar.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-width: 0 1 0 0;"
        );


        // =====================================================
        // LOGO
        // =====================================================

        ImageView logoView = new ImageView();

        var logoResource =
                getClass().getResource(
                    "/assets/images/logo/logo.png"
                );

        if (logoResource != null) {

            Image logoImage =
                    new Image(
                        logoResource.toExternalForm()
                    );

            logoView.setImage(logoImage);

            logoView.setFitHeight(125);
            logoView.setFitWidth(210);

            logoView.setPreserveRatio(true);
        }

        VBox logoBox = new VBox();

        logoBox.setAlignment(Pos.CENTER);

        logoBox.setPadding(
                new Insets(0, 0, 10, 0)
        );

        logoBox.getChildren().add(
                logoView
        );


        // =====================================================
        // SIDEBAR BUTTONS
        // =====================================================

        Button dashboardButton =
                createMenuButton(
                    "Dashboard",
                    FontAwesomeIcon.HOME
                );

        Button pregnancyButton =
                createMenuButton(
                    "Pregnancy Tracker",
                    FontAwesomeIcon.HEART
                );

        Button appointmentButton =
                createMenuButton(
                    "Appointments",
                    FontAwesomeIcon.CALENDAR
                );

        Button medicineButton =
                createMenuButton(
                    "Medicines & Reminders",
                    FontAwesomeIcon.MEDKIT
                );

        Button nutritionButton =
                createMenuButton(
                    "Diet & Nutrition",
                    FontAwesomeIcon.APPLE
                );

        Button videoLibraryButton =
                createMenuButton(
                    "Video Library",
                    FontAwesomeIcon.FILM
                );

        Button babyButton =
                createMenuButton(
                    "Baby Care",
                    FontAwesomeIcon.HEART
                );

        Button vaccinationButton =
                createMenuButton(
                    "Vaccination",
                    FontAwesomeIcon.MEDKIT
                );

        Button reportsButton =
                createMenuButton(
                    "Reports",
                    FontAwesomeIcon.FILE_TEXT
                );

        Button emergencyButton =
                createMenuButton(
                    "Emergency",
                    FontAwesomeIcon.AMBULANCE
                );

        Button schemesButton =
                createMenuButton(
                    "Government Schemes",
                    FontAwesomeIcon.BUILDING
                );

        Button aiButton =
                createMenuButton(
                    "AI Assistant",
                    FontAwesomeIcon.COG
                );


        // =====================================================
        // SETTINGS BUTTON
        // =====================================================

        Button settingsButton =
                createMenuButton(
                    "Settings",
                    FontAwesomeIcon.COG
                );


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
            "-fx-padding: 10px 12px;"
        );

        FontAwesomeIconView aiIcon =
                (FontAwesomeIconView)
                aiButton.getGraphic();

        aiIcon.setFill(Color.WHITE);


        // =====================================================
        // SIDEBAR ACTIONS
        // =====================================================

        dashboardButton.setOnAction(e -> {
            showDashboard();
        });


        pregnancyButton.setOnAction(e -> {

            PregnancyTracker pregnancyTracker =
                    new PregnancyTracker(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    pregnancyTracker.createPregnancyTrackingPage()
            );

        });


        // =====================================================
        // APPOINTMENTS
        // =====================================================

        appointmentButton.setOnAction(e -> {

            MotherAppoinments appointments =
                    new MotherAppoinments(motherUid);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    appointments.createAppointmentPage()
            );

        });


        medicineButton.setOnAction(e -> {

            MotherMedicineReminder medicinePage =
                    new MotherMedicineReminder(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    medicinePage.createMedicineReminderPage()
            );
        });


        nutritionButton.setOnAction(e -> {

            MotherNutritionDiet nutritionDiet =
                    new MotherNutritionDiet();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    nutritionDiet.createDietNutritionPage()
            );

        });


        videoLibraryButton.setOnAction(e -> {

            MotherVideos videoLibrary =
                    new MotherVideos();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    videoLibrary.createVideoPage()
            );

        });


        babyButton.setOnAction(e -> {

            MotherBabyCare babyCare =
                    new MotherBabyCare();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    babyCare.createBabyCarePage()
            );

        });


        vaccinationButton.setOnAction(e -> {

            MotherVaccination vaccination =
                    new MotherVaccination();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    vaccination.createVaccinationPage()
            );

        });


        reportsButton.setOnAction(e -> {

            MotherReports reports =
                    new MotherReports(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    reports.createReportsPage()
            );

        });


        emergencyButton.setOnAction(e -> {

            MotherEmergency emergency =
                    new MotherEmergency();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    emergency.createEmergencyPage()
            );

        });


        schemesButton.setOnAction(e -> {

            GovernmentSchemes schemes =
                    new GovernmentSchemes();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    schemes.createGovernmentSchemesPage()
            );

        });


        aiButton.setOnAction(e -> {

            MotherAiAssistant aiAssistant =
                    new MotherAiAssistant();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    aiAssistant.createAIAssistantPage()
            );

        });


        // =====================================================
        // SETTINGS ACTION
        // =====================================================

        settingsButton.setOnAction(e -> {

            MotherSettings settings =
                    new MotherSettings(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    settings.createSettingsPage()
            );

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
                videoLibraryButton,
                babyButton,
                vaccinationButton,
                reportsButton,
                emergencyButton,
                schemesButton,
                aiButton,
                settingsButton
        );

        root.setLeft(sidebar);


        // =====================================================
        // TOP HEADER
        // =====================================================

        HBox header = new HBox();

        header.setPadding(
                new Insets(22, 32, 18, 32)
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setSpacing(25);


        // =====================================================
        // WELCOME
        // =====================================================

        VBox welcomeBox = new VBox();

        welcomeBox.setSpacing(5);

        String motherName = getMotherName();


        Label welcomeText =
                new Label(
                    "Welcome back, " + motherName + "! 👋"
                );

        welcomeText.setStyle(
            "-fx-font-size: 27px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
                new Label(
                    "You're doing great! Let's make this pregnancy journey healthy and happy."
                );

        subtitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #77778D;"
        );

        welcomeBox.getChildren().addAll(
                welcomeText,
                subtitle
        );

        HBox.setHgrow(
                welcomeBox,
                Priority.ALWAYS
        );


        // =====================================================
        // SEARCH
        // =====================================================

        HBox searchBox = new HBox();

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setSpacing(10);

        searchBox.setPrefWidth(280);
        searchBox.setPrefHeight(45);

        searchBox.setPadding(
                new Insets(0, 15, 0, 15)
        );

        searchBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E3DCE8;" +
            "-fx-border-radius: 12;" +
            "-fx-background-radius: 12;"
        );

        FontAwesomeIconView searchIcon =
                new FontAwesomeIconView(
                    FontAwesomeIcon.SEARCH
                );

        searchIcon.setSize("17");

        searchIcon.setFill(
                Color.web(DARK)
        );

        Label searchText =
                new Label(
                    "Search anything..."
                );

        searchText.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #77778D;"
        );

        searchBox.getChildren().addAll(
                searchIcon,
                searchText
        );

        searchBox.setOnMouseClicked(e ->
                showMessage("Search")
        );


        // =====================================================
        // DATE
        // =====================================================

        HBox dateBox = new HBox();

        dateBox.setAlignment(
                Pos.CENTER
        );

        dateBox.setSpacing(7);

        FontAwesomeIconView dateIcon =
                new FontAwesomeIconView(
                    FontAwesomeIcon.CALENDAR
                );

        dateIcon.setSize("18");

        dateIcon.setFill(
                Color.web(PURPLE)
        );

        Label dateLabel =
                new Label(
                    LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                            "dd MMM yyyy"
                        )
                    )
                );

        dateLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        dateBox.getChildren().addAll(
                dateIcon,
                dateLabel
        );


        // =====================================================
        // NOTIFICATION
        // =====================================================

        HBox notificationBox = new HBox();

        notificationBox.setAlignment(
                Pos.CENTER
        );

        notificationBox.setSpacing(3);

        FontAwesomeIconView notificationIcon =
                new FontAwesomeIconView(
                    FontAwesomeIcon.BELL
                );

        notificationIcon.setSize("22");

        notificationIcon.setFill(
                Color.web(PINK)
        );


        /*
         * BEFORE:
         *
         * Label notification = new Label("3");
         *
         * NOW:
         * Firebase मधून actual count येईल.
         */
        notificationLabel =
                new Label("0");

        notificationLabel.setStyle(
            "-fx-background-color: #E84A87;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 3px 6px;"
        );


        notificationBox.getChildren().addAll(
                notificationIcon,
                notificationLabel
        );


        /*
         * Bell click
         */
        notificationBox.setOnMouseClicked(e -> {

            /*
             * Click केल्यावर latest Firebase data
             * पुन्हा load होईल.
             */
            loadNotifications(true);

        });


        // =====================================================
        // PROFILE
        // =====================================================

        HBox profileBox = new HBox();

        profileBox.setAlignment(
                Pos.CENTER
        );

        profileBox.setSpacing(9);

       String firstLetter =
        (motherName == null ||
         motherName.trim().isEmpty())
                ? "M"
                : motherName.trim()
                        .substring(0, 1)
                        .toUpperCase();


        Label profileCircle =
                new Label(firstLetter);

        profileCircle.setAlignment(
                Pos.CENTER
        );

        profileCircle.setPrefSize(
                40,
                40
        );

        profileCircle.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-background-radius: 50%;" +
            "-fx-text-fill: #E84A87;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );


        VBox profileText = new VBox();


        Label profileName =
                new Label(motherName);

        profileName.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label profileRole =
                new Label("Patient");

        profileRole.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #77778D;"
        );


        profileText.getChildren().addAll(
                profileName,
                profileRole
        );


        profileBox.getChildren().addAll(
                profileCircle,
                profileText
        );


        profileBox.setOnMouseClicked(e ->
                showProfile()
        );


        header.getChildren().addAll(
                welcomeBox,
                searchBox,
                dateBox,
                notificationBox,
                profileBox
        );

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
                    30
                )
        );


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                    mainContent
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;"
        );

        root.setCenter(
                scrollPane
        );


        // =====================================================
        // FIRST PAGE = DASHBOARD
        // =====================================================

        showDashboard();


        /*
         * Dashboard तयार झाल्यावर notifications
         * Firebase मधून load करा.
         */
        loadNotifications(false);


        // =====================================================
        // SCENE
        // =====================================================

        MotherdashboardScene =
                new Scene(
                    root,
                    scenesettings.rectanguler2d.getWidth(),
                    scenesettings.rectanguler2d.getHeight()
                );

        return MotherdashboardScene;
    }


    // =========================================================
    // LOAD NOTIFICATIONS
    // =========================================================

    private void loadNotifications(
            boolean showPopup) {

        /*
         * UID नसल्यास Firebase query करू नका.
         */
        if (motherUid == null
                || motherUid.trim().isEmpty()) {

            System.out.println(
                    "[NOTIFICATION] Mother UID is empty."
            );

            if (notificationLabel != null) {
                notificationLabel.setText("0");
            }

            if (showPopup) {
                showNotificationPopup(
                        new ArrayList<NotificationItem>()
                );
            }

            return;
        }

        CompletableFuture
                .supplyAsync(() -> {

                    List<NotificationItem> notifications =
                            new ArrayList<>();

                    try {

                        String uid =
                                motherUid.trim();

                        // =====================================
                        // DOCTOR APPOINTMENTS
                        // =====================================

                        List<DoctorAppointment>
                                doctorAppointments =
                                doctorAppointmentController
                                        .getAppointmentsForPatient(uid);

                        if (doctorAppointments != null) {

                            for (DoctorAppointment appointment :
                                    doctorAppointments) {

                                if (appointment == null) {
                                    continue;
                                }

                                String status =
                                        appointment.getStatus();

                                String message =
                                        createDoctorNotification(
                                                appointment,
                                                status
                                        );

                                if (message != null
                                        && !message.trim().isEmpty()) {

                                    String doctorId =
                                            appointment.getDoctorId();

                                    String key =
                                            createNotificationKey(
                                                    "DOCTOR",
                                                    safeKeyPart(doctorId)
                                                            + "|"
                                                            + message
                                            );

                                    if (!isNotificationRead(key)) {
                                        notifications.add(
                                                new NotificationItem(
                                                        key,
                                                        message
                                                )
                                        );
                                    }
                                }
                            }
                        }


                        // =====================================
                        // HOSPITAL APPOINTMENTS
                        // =====================================

                        List<Appointment>
                                hospitalAppointments =
                                hospitalAppointmentController
                                        .getAppointmentsByMother(uid);

                        if (hospitalAppointments != null) {

                            for (Appointment appointment :
                                    hospitalAppointments) {

                                if (appointment == null) {
                                    continue;
                                }

                                String type =
                                        appointment
                                                .getAppointmentType();

                                if (type == null
                                        || !"HOSPITAL"
                                                .equalsIgnoreCase(
                                                        type.trim())) {
                                    continue;
                                }

                                String status =
                                        appointment.getStatus();

                                String message =
                                        createHospitalNotification(
                                                appointment,
                                                status
                                        );

                                if (message != null
                                        && !message.trim().isEmpty()) {

                                    String patient =
                                            appointment.getPatient();

                                    String key =
                                            createNotificationKey(
                                                    "HOSPITAL",
                                                    safeKeyPart(patient)
                                                            + "|"
                                                            + message
                                            );

                                    if (!isNotificationRead(key)) {
                                        notifications.add(
                                                new NotificationItem(
                                                        key,
                                                        message
                                                )
                                        );
                                    }
                                }
                            }
                        }


                        // =====================================
                        // BED BOOKINGS
                        // =====================================

                        List<BedBooking>
                                bedBookings =
                                bedBookingController
                                        .getAllBedBookings();

                        if (bedBookings != null) {

                            for (BedBooking booking :
                                    bedBookings) {

                                if (booking == null) {
                                    continue;
                                }

                                String bookingMotherUid =
                                        booking.getMotherUid();

                                if (bookingMotherUid == null
                                        || !uid.equals(
                                                bookingMotherUid.trim())) {
                                    continue;
                                }

                                String status =
                                        booking.getStatus();

                                String message =
                                        createBedNotification(
                                                booking,
                                                status
                                        );

                                if (message != null
                                        && !message.trim().isEmpty()) {

                                    String bookingId =
                                            booking.getBookingID();

                                    String key =
                                            createNotificationKey(
                                                    "BED",
                                                    safeKeyPart(bookingId)
                                                            + "|"
                                                            + message
                                            );

                                    if (!isNotificationRead(key)) {
                                        notifications.add(
                                                new NotificationItem(
                                                        key,
                                                        message
                                                )
                                        );
                                    }
                                }
                            }
                        }

                        System.out.println(
                                "[NOTIFICATION] Unread Total = "
                                        + notifications.size()
                        );

                    } catch (Exception e) {

                        System.out.println(
                                "[NOTIFICATION] Error loading notifications"
                        );

                        e.printStackTrace();
                    }

                    return notifications;
                })

                // =============================================
                // JAVA FX THREAD
                // =============================================

                .thenAccept(notifications -> {

                    Platform.runLater(() -> {

                        currentNotifications.clear();

                        for (NotificationItem item : notifications) {
                            currentNotifications.add(item.message);
                        }

                        if (notificationLabel != null) {
                            notificationLabel.setText(
                                    String.valueOf(
                                            notifications.size()
                                    )
                            );
                        }

                        if (showPopup) {
                            showNotificationPopup(
                                    notifications
                            );
                        }
                    });
                });
    }


    // =========================================================
    // NOTIFICATION READ STATE
    // =========================================================

    private String createNotificationKey(
            String type,
            String value) {

        String raw =
                safeKeyPart(motherUid)
                        + "|"
                        + safeKeyPart(type)
                        + "|"
                        + safeKeyPart(value);

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    raw.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder result =
                    new StringBuilder();

            for (byte b : hash) {
                result.append(
                        String.format("%02x", b)
                );
            }

            return "notification_" + result;

        } catch (Exception e) {
            return "notification_"
                    + Integer.toHexString(raw.hashCode());
        }
    }


    private boolean isNotificationRead(
            String key) {

        return notificationPreferences
                .getBoolean(key, false);
    }


    private void markNotificationRead(
            String key) {

        notificationPreferences
                .putBoolean(key, true);

        try {
            notificationPreferences.flush();
        } catch (Exception e) {
            System.out.println(
                    "[NOTIFICATION] Unable to save read state"
            );
        }
    }


    private String safeKeyPart(
            String value) {

        if (value == null) {
            return "";
        }

        return value.trim();
    }


    // =========================================================
    // DOCTOR NOTIFICATION
    // =========================================================

    private String createDoctorNotification(
            DoctorAppointment appointment,
            String status) {

        if (status == null
                || status.trim().isEmpty()) {

            status = "Pending";
        }


        String doctorId =
                appointment.getDoctorId();


        if (doctorId == null
                || doctorId.trim().isEmpty()) {

            doctorId = "Doctor";
        }


        if ("Confirmed".equalsIgnoreCase(status)) {

            return "👨‍⚕️ Doctor Appointment\n"
                    + "Your doctor appointment has been confirmed."
                    + "\nDoctor: "
                    + doctorId;

        }


        if ("Rejected".equalsIgnoreCase(status)) {

            return "👨‍⚕️ Doctor Appointment\n"
                    + "Your doctor appointment request was rejected."
                    + "\nDoctor: "
                    + doctorId;

        }


        if ("Cancelled".equalsIgnoreCase(status)) {

            return "👨‍⚕️ Doctor Appointment\n"
                    + "Your doctor appointment has been cancelled."
                    + "\nDoctor: "
                    + doctorId;

        }


        if ("Completed".equalsIgnoreCase(status)) {

            return "👨‍⚕️ Doctor Appointment\n"
                    + "Your doctor appointment has been completed."
                    + "\nDoctor: "
                    + doctorId;

        }


        return "👨‍⚕️ Doctor Appointment\n"
                + "Your doctor appointment is "
                + status + "."
                + "\nDoctor: "
                + doctorId;
    }


    // =========================================================
    // HOSPITAL NOTIFICATION
    // =========================================================

    private String createHospitalNotification(
            Appointment appointment,
            String status) {

        if (status == null
                || status.trim().isEmpty()) {

            status = "Pending";
        }


        String hospital =
                appointment.getHospital();


        if (hospital == null
                || hospital.trim().isEmpty()) {

            /*
             * Hospital appointment मध्ये
             * doctor field मध्येही hospital value आहे.
             */
            hospital =
                    appointment.getDoctor();
        }


        if (hospital == null
                || hospital.trim().isEmpty()) {

            hospital = "Hospital";
        }


        // =====================================
        // CONFIRMED
        // =====================================

        if ("Confirmed".equalsIgnoreCase(status)) {

            return "🏥 Hospital Appointment\n"
                    + "Your hospital appointment has been confirmed."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // REJECTED
        // =====================================

        if ("Rejected".equalsIgnoreCase(status)) {

            return "🏥 Hospital Appointment\n"
                    + "Your hospital appointment request was rejected."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // CANCELLED
        // =====================================

        if ("Cancelled".equalsIgnoreCase(status)) {

            return "🏥 Hospital Appointment\n"
                    + "Your hospital appointment has been cancelled."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // COMPLETED
        // =====================================

        if ("Completed".equalsIgnoreCase(status)) {

            return "🏥 Hospital Appointment\n"
                    + "Your hospital appointment has been completed."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // PENDING / OTHER
        // =====================================

        return "🏥 Hospital Appointment\n"
                + "Your hospital appointment is "
                + status + "."
                + "\nHospital: "
                + hospital;
    }


    // =========================================================
    // BED BOOKING NOTIFICATION
    // =========================================================

    private String createBedNotification(
            BedBooking booking,
            String status) {

        if (status == null
                || status.trim().isEmpty()) {

            status = "Pending";
        }


        String hospital =
                booking.getHospitalName();


        if (hospital == null
                || hospital.trim().isEmpty()) {

            hospital = "Hospital";
        }


        // =====================================
        // CONFIRMED
        // =====================================

        if ("Confirmed".equalsIgnoreCase(status)) {

            return "🛏️ Bed Booking\n"
                    + "Your bed booking has been confirmed."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // REJECTED
        // =====================================

        if ("Rejected".equalsIgnoreCase(status)) {

            return "🛏️ Bed Booking\n"
                    + "Your bed booking request was rejected."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // CANCELLED
        // =====================================

        if ("Cancelled".equalsIgnoreCase(status)) {

            return "🛏️ Bed Booking\n"
                    + "Your bed booking has been cancelled."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // COMPLETED
        // =====================================

        if ("Completed".equalsIgnoreCase(status)) {

            return "🛏️ Bed Booking\n"
                    + "Your bed booking has been completed."
                    + "\nHospital: "
                    + hospital;

        }


        // =====================================
        // PENDING / OTHER
        // =====================================

        return "🛏️ Bed Booking\n"
                + "Your bed booking is "
                + status + "."
                + "\nHospital: "
                + hospital;
    }


    // =========================================================
    // SHOW NOTIFICATION POPUP
    // =========================================================

    private void showNotificationPopup(
            List<NotificationItem> notifications) {

        Dialog<Void> dialog =
                new Dialog<>();

        dialog.setTitle("Notifications");
        dialog.setHeaderText("Your Notifications");

        VBox notificationContainer =
                new VBox();

        notificationContainer.setSpacing(10);
        notificationContainer.setPadding(
                new Insets(5)
        );

        if (notifications == null
                || notifications.isEmpty()) {

            Label emptyLabel =
                    new Label("No new notifications.");

            emptyLabel.setWrapText(true);
            emptyLabel.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: #77778D;" +
                    "-fx-padding: 20px;"
            );

            notificationContainer
                    .getChildren()
                    .add(emptyLabel);

        } else {

            for (NotificationItem item : notifications) {

                HBox row = new HBox();

                row.setAlignment(
                        Pos.CENTER_LEFT
                );
                row.setSpacing(5);
                row.setPadding(
                        new Insets(10)
                );

                row.setStyle(
                        "-fx-background-color: #FFF8FB;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #F2DCE7;" +
                        "-fx-border-radius: 10;"
                );

                Label messageLabel =
                        new Label(item.message);

                messageLabel.setWrapText(true);
                messageLabel.setMaxWidth(360);
                messageLabel.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #24234F;"
                );

                Button readButton =
                        new Button("Mark as Read");

                readButton.setStyle(
                        "-fx-background-color: #E84A87;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 15;" +
                        "-fx-padding: 7px 12px;"
                );

                HBox.setHgrow(
                        messageLabel,
                        Priority.ALWAYS
                );

                readButton.setOnAction(e -> {

                    markNotificationRead(
                            item.key
                    );

                    notificationContainer
                            .getChildren()
                            .remove(row);

                    updateNotificationBadge(
                            notificationContainer
                    );
                });

                row.getChildren().addAll(
                        messageLabel,
                        readButton
                );

                notificationContainer
                        .getChildren()
                        .add(row);
            }
        }

        ScrollPane scrollPane =
                new ScrollPane(
                        notificationContainer
                );

        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportWidth(520);
        scrollPane.setPrefViewportHeight(430);
        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        dialog.getDialogPane()
                .setContent(scrollPane);

        dialog.getDialogPane()
                .getButtonTypes()
                .add(
                        javafx.scene.control.ButtonType.CLOSE
                );

        javafx.scene.Node defaultCloseButton =
                dialog.getDialogPane()
                        .lookupButton(
                                javafx.scene.control.ButtonType.CLOSE
                        );

        if (defaultCloseButton != null) {
            defaultCloseButton
                    .setStyle(
                            "-fx-background-color: #9B4DCC;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 12px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-padding: 7px 18px;"
                    );
        }

        dialog.showAndWait();
    }


    private void updateNotificationBadge(
            VBox notificationContainer) {

        long unreadCount =
                notificationContainer
                        .getChildren()
                        .stream()
                        .filter(node ->
                                node instanceof HBox
                        )
                        .count();

        if (notificationLabel != null) {
            notificationLabel.setText(
                    String.valueOf(unreadCount)
            );
        }

        currentNotifications.clear();

        for (javafx.scene.Node node :
                notificationContainer.getChildren()) {

            if (node instanceof HBox) {
                HBox row = (HBox) node;

                if (!row.getChildren().isEmpty()
                        && row.getChildren().get(0)
                                instanceof Label) {

                    currentNotifications.add(
                            ((Label) row.getChildren()
                                    .get(0)).getText()
                    );
                }
            }
        }
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

        MotherProfile profile =
                new MotherProfile(motherModel);

        mainContent.getChildren().add(
                profile.createProfilePage()
        );
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
                LocalDate.now()
        );
    }


    private int getPregnancyWeek() {

        long days = getPregnancyDays();

        if (days <= 0) {
            return 0;
        }

        return (int) (days / 7);
    }


    private long getRemainingDays() {

        long pregnancyDays =
                getPregnancyDays();

        return Math.max(
                0,
                280 - pregnancyDays
        );
    }


    private double getPregnancyProgress() {

        long pregnancyDays =
                getPregnancyDays();

        return Math.min(
                100,
                Math.max(
                        0,
                        (pregnancyDays * 100.0) / 280
                )
        );
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
                Priority.ALWAYS
        );


        // =====================================================
        // PREGNANCY MAIN CARD
        // =====================================================

        HBox pregnancyCard =
                createPregnancyMainCard();

        leftColumn.getChildren().add(
                pregnancyCard
        );


        // =====================================================
        // QUICK ACTION TITLE
        // =====================================================

        Label quickTitle =
                new Label(
                    "Quick Actions"
                );

        quickTitle.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        HBox quickActions =
                new HBox();

        quickActions.setSpacing(15);


        Button bookButton =
                createActionButton(
                    "Book\nAppointment",
                    FontAwesomeIcon.CALENDAR
                );


        Button reportButton =
                createActionButton(
                    "Upload\nReports",
                    FontAwesomeIcon.UPLOAD
                );


        Button medicineAction =
                createActionButton(
                    "Medicine\nReminders",
                    FontAwesomeIcon.MEDKIT
                );


        Button dietButton =
                createActionButton(
                    "Diet\nPlan",
                    FontAwesomeIcon.APPLE
                );


        // =====================================================
        // QUICK ACTION NAVIGATION
        // =====================================================

        bookButton.setOnAction(e -> {

                MotherAppoinments appointments =
                        new MotherAppoinments(motherUid);
            
                mainContent.getChildren().clear();
            
                mainContent.getChildren().add(
                        appointments.createAppointmentPage()
                );
            
            });


        reportButton.setOnAction(e -> {

            MotherReports reports =
                    new MotherReports(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    reports.createReportsPage()
            );

        });


        medicineAction.setOnAction(e -> {

            MotherMedicineReminder medicinePage =
                    new MotherMedicineReminder(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    medicinePage.createMedicineReminderPage()
            );

        });


        dietButton.setOnAction(e -> {

            MotherNutritionDiet nutrition =
                    new MotherNutritionDiet();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    nutrition.createDietNutritionPage()
            );

        });


        quickActions.getChildren().addAll(
                bookButton,
                reportButton,
                medicineAction,
                dietButton
        );


        leftColumn.getChildren().addAll(
                quickTitle,
                quickActions
        );


        // =====================================================
        // RIGHT COLUMN
        // =====================================================

        VBox rightColumn =
                new VBox();

        rightColumn.setSpacing(20);

        rightColumn.setPrefWidth(390);

        rightColumn.setMinWidth(360);


        VBox progressCard =
                createPregnancyProgressCard();

        VBox appointmentCard =
                createAppointmentCard();

        VBox healthTip =
                createHealthTipCard();


        rightColumn.getChildren().addAll(
                progressCard,
                appointmentCard,
                healthTip
        );


        // =====================================================
        // DASHBOARD TWO COLUMNS
        // =====================================================

        HBox dashboardLayout =
                new HBox();

        dashboardLayout.setSpacing(22);

        dashboardLayout.getChildren().addAll(
                leftColumn,
                rightColumn
        );


        mainContent.getChildren().add(
                dashboardLayout
        );
    }


    // =========================================================
    // PREGNANCY MAIN CARD
    // =========================================================

    private HBox createPregnancyMainCard() {

        HBox card = new HBox();

        card.setPadding(
                new Insets(25)
        );

        card.setSpacing(10);

        card.setPrefHeight(285);
        card.setMinHeight(285);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-background-radius: 20;" +
            "-fx-border-color: #F5C7DB;" +
            "-fx-border-radius: 20;"
        );


        VBox details = new VBox();

        details.setSpacing(10);

        HBox.setHgrow(
                details,
                Priority.ALWAYS
        );


        // =====================================================
        // YOU ARE IN
        // =====================================================

        Label smallText =
                new Label("You are in");

        smallText.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        int weekNumber =
                getPregnancyWeek();


        Label week =
                new Label(
                    getWeekText(weekNumber)
                );

        week.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        long remainingDays =
                getRemainingDays();


        Label trimester =
                new Label(
                    getTrimester(weekNumber)
                    + "   •   "
                    + remainingDays
                    + " Days to Go"
                );

        trimester.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #666680;"
        );


        Label progressText =
                new Label(
                    "Pregnancy Progress"
                );

        progressText.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );


        HBox progressBar =
                new HBox();

        progressBar.setPrefHeight(12);

        progressBar.setMaxWidth(430);

        progressBar.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;"
        );


        HBox progressFill =
                new HBox();


        double progress =
                getPregnancyProgress();


        progressFill.setPrefWidth(
                430 * progress / 100
        );

        progressFill.setPrefHeight(12);

        progressFill.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #E84A87);" +
            "-fx-background-radius: 10;"
        );


        progressBar.getChildren().add(
                progressFill
        );


        Label completed =
                new Label(
                    String.format(
                        "%.0f%% Completed",
                        progress
                    )
                );

        completed.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );


        Button detailsButton =
                createSmallButton(
                    "View Details  →"
                );


        detailsButton.setOnAction(e -> {

            PregnancyTracker pregnancyTracker =
                    new PregnancyTracker(motherModel);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    pregnancyTracker.createPregnancyTrackingPage()
            );

        });


        details.getChildren().addAll(
                smallText,
                week,
                trimester,
                progressText,
                progressBar,
                completed,
                detailsButton
        );


        // =====================================================
        // MOTHER IMAGE
        // =====================================================

        ImageView motherImage = new ImageView();

        String imagePath =
                "/assets/images/logo/PregnantMother.png";

        var motherResource =
                getClass().getResource(imagePath);

        System.out.println(
                "Mother image resource = "
                        + motherResource
        );

        if (motherResource != null) {

            Image image =
                    new Image(
                        motherResource.toExternalForm()
                    );

            System.out.println(
                    "Mother image loaded = "
                            + !image.isError()
            );

            motherImage.setImage(image);

            motherImage.setFitWidth(350);
            motherImage.setFitHeight(350);

            motherImage.setPreserveRatio(true);
            motherImage.setSmooth(true);

        } else {

            System.out.println(
                    "❌ Mother image NOT FOUND: "
                            + imagePath
            );
        }

        VBox imageBox = new VBox();

        imageBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        imageBox.setPrefWidth(270);
        imageBox.setMinWidth(230);
        imageBox.setPrefHeight(250);

        imageBox.getChildren().add(
                motherImage
        );

        card.getChildren().addAll(
                details,
                imageBox
        );

        return card;
    }


    // =========================================================
    // PREGNANCY PROGRESS CARD
    // =========================================================

    private VBox createPregnancyProgressCard() {

        VBox card =
                createWhiteCard();

        card.setPrefHeight(225);


        HBox heading =
                createCardHeading(
                    "Pregnancy Progress",
                    FontAwesomeIcon.CALENDAR
                );


        HBox pregnancyInfo =
                new HBox();

        pregnancyInfo.setAlignment(
                Pos.CENTER_LEFT
        );

        pregnancyInfo.setSpacing(12);


        VBox pregnancyDetails =
                new VBox();

        pregnancyDetails.setSpacing(3);

        HBox.setHgrow(
                pregnancyDetails,
                Priority.ALWAYS
        );


        int weekNumber =
                getPregnancyWeek();


        Label week =
                new Label(
                    getWeekText(weekNumber)
                );

        week.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );


        Label trimester =
                new Label(
                    getTrimester(weekNumber)
                );

        trimester.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #666680;"
        );


        


        pregnancyDetails.getChildren().addAll(
                week,
                trimester
                
        );


        pregnancyInfo.getChildren().add(
                pregnancyDetails
        );


        javafx.scene.control.Separator separator =
                new javafx.scene.control.Separator();


        separator.setStyle(
            "-fx-background-color: #E7DCE8;"
        );


        
        card.getChildren().addAll(
                heading,
                pregnancyInfo,
                separator
        );

        return card;
    }


    


    // =========================================================
    // APPOINTMENT CARD
    // =========================================================

    private VBox createAppointmentCard() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createCardHeading(
                    "Upcoming Appointment",
                    FontAwesomeIcon.CALENDAR
                );


        Label doctor =
                new Label(
                    "Dr. Anjali Mehta"
                );

        doctor.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );


        Label details =
                new Label(
                    "Obstetrician & Gynecologist\n" +
                    "20 May 2024  •  11:00 AM\n" +
                    "CarePlus Women Clinic, Pune"
                );

        details.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #666680;" +
            "-fx-line-spacing: 5px;"
        );


        Button view =
                createSmallButton(
                    "View Appointment  →"
                );

        view.setMaxWidth(
                Double.MAX_VALUE
        );


        view.setOnAction(e -> {

            MotherAppoinments appointments =
                    new MotherAppoinments(motherUid);

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    appointments.createAppointmentPage()
            );

        });


        card.getChildren().addAll(
                heading,
                doctor,
                details,
                view
        );

        return card;
    }


    // =========================================================
    // HEALTH TIP
    // =========================================================

    private VBox createHealthTipCard() {

        VBox card =
                createWhiteCard();


        card.setStyle(
            "-fx-background-color: #FFF8FC;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
                createCardHeading(
                    "Today's Health Tip",
                    FontAwesomeIcon.LIGHTBULB_ALT
                );


        Label text =
                new Label(
                    "Stay hydrated and follow a healthy\n" +
                    "balanced diet. Take adequate rest\n" +
                    "and attend your scheduled check-ups."
                );

        text.setWrapText(true);

        text.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #666680;" +
            "-fx-line-spacing: 4px;"
        );


        Button explore =
                createSmallButton(
                    "Explore More Tips  →"
                );


        explore.setOnAction(e -> {

            MotherAiAssistant aiAssistant =
                    new MotherAiAssistant();

            mainContent.getChildren().clear();

            mainContent.getChildren().add(
                    aiAssistant.createAIAssistantPage()
            );

        });


        card.getChildren().addAll(
                heading,
                text,
                explore
        );

        return card;
    }


    // =========================================================
    // CARD HEADING
    // =========================================================

    private HBox createCardHeading(
            String text,
            FontAwesomeIcon iconType) {

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        heading.setSpacing(10);


        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                    iconType
                );

        icon.setSize("19");

        icon.setFill(
                Color.web(PURPLE)
        );


        Label title =
                new Label(text);

        title.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        heading.getChildren().addAll(
                icon,
                title
        );

        return heading;
    }


    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
                new VBox();

        card.setPadding(
                new Insets(20)
        );

        card.setSpacing(10);

        card.setPrefHeight(190);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );

        return card;
    }


    // =========================================================
    // MENU BUTTON
    // =========================================================

    private Button createMenuButton(
            String text,
            FontAwesomeIcon iconType) {

        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                    iconType
                );

        icon.setSize("17");

        icon.setFill(
                Color.web(DARK)
        );


        Button button =
                new Button(text);

        button.setGraphic(icon);

        button.setPrefWidth(220);

        button.setPrefHeight(46);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphicTextGap(15);


        button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 500;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 10px 12px;"
        );


        button.setOnMouseEntered(e -> {

            button.setStyle(
                "-fx-background-color: #FFEAF3;" +
                "-fx-text-fill: #E84A87;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 12px;"
            );

            icon.setFill(
                    Color.web(PINK)
            );
        });


        button.setOnMouseExited(e -> {

            button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #24234F;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: 500;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10px 12px;"
            );

            icon.setFill(
                    Color.web(DARK)
            );
        });


        return button;
    }


    // =========================================================
    // QUICK ACTION BUTTON
    // =========================================================

    private Button createActionButton(
            String text,
            FontAwesomeIcon iconType) {

        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                    iconType
                );

        icon.setSize("28");

        icon.setFill(
                Color.web(PINK)
        );


        Button button =
                new Button(text);

        button.setGraphic(icon);

        button.setContentDisplay(
                javafx.scene.control.ContentDisplay.TOP
        );

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
            "-fx-padding: 12px;"
        );


        button.setOnMouseEntered(e -> {

            button.setStyle(
                "-fx-background-color: #FFEAF3;" +
                "-fx-text-fill: #E84A87;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #E84A87;" +
                "-fx-border-radius: 15;" +
                "-fx-padding: 12px;"
            );

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
                "-fx-padding: 12px;"
            );

        });


        return button;
    }


    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private Button createSmallButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(38);

        button.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 8px 20px;"
        );

        return button;
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String pageName) {

        System.out.println(
            "Open page: " + pageName
        );
    }


    // =========================================================
    // BACK DASHBOARD
    // =========================================================

    public void backDashMethod() {

        showDashboard();

    }
}