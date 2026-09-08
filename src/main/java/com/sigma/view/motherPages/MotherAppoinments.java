package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// =============================================================
// MOTHER APPOINTMENTS PAGE
// =============================================================

public class MotherAppoinments {

        private final String PINK = "#E84A87";
        private final String DARK = "#24234F";
        private final String PURPLE = "#9B4DCC";
        private final String TEXT_GRAY = "#77778D";
        private final String LIGHT_PINK = "#FFF3F8";
        private final String BORDER = "#E7DCE8";

        private VBox mainContent;

        // =========================================================
        // TEMPORARY SELECTED DOCTOR
        // Later -> Doctor Controller / Firebase
        // =========================================================

        private String selectedDoctor = "Dr. Anjali Mehta";

        private String selectedSpeciality = "Obstetrician & Gynecologist";

        private String selectedImage = "doctor1.png";

        private String selectedRating = "4.8";

        private String selectedReviews = "256 Reviews";

        private String selectedClinic = "CarePlus Women Clinic, Pune";

        private String selectedFee = "₹800";

        // =========================================================
        // TEMPORARY APPOINTMENT STORAGE
        // Later -> Controller / Firebase
        // =========================================================

        private final List<AppointmentData> appointments = new ArrayList<>();

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public MotherAppoinments() {

                // Existing temporary appointments
                appointments.add(
                                new AppointmentData(
                                                "Dr. Anjali Mehta",
                                                "Obstetrician & Gynecologist",
                                                "doctor1.png",
                                                "20 May 2026",
                                                "11:00 AM",
                                                "CarePlus Women Clinic, Pune",
                                                "Confirmed"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Neha Kulkarni",
                                                "Nutritionist",
                                                "doctor2.png",
                                                "28 May 2026",
                                                "04:00 PM",
                                                "CarePlus Women Clinic, Pune",
                                                "Upcoming"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Rahul Deshmukh",
                                                "Pediatrician",
                                                "doctor3.png",
                                                "10 June 2026",
                                                "10:30 AM",
                                                "CarePlus Children Clinic, Pune",
                                                "Pending"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Anjali Mehta",
                                                "Obstetrician & Gynecologist",
                                                "doctor1.png",
                                                "10 April 2026",
                                                "11:00 AM",
                                                "CarePlus Women Clinic, Pune",
                                                "Completed"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Neha Kulkarni",
                                                "Nutritionist",
                                                "doctor2.png",
                                                "25 March 2026",
                                                "04:00 PM",
                                                "CarePlus Women Clinic, Pune",
                                                "Completed"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Rahul Deshmukh",
                                                "Pediatrician",
                                                "doctor3.png",
                                                "05 April 2026",
                                                "10:30 AM",
                                                "CarePlus Children Clinic, Pune",
                                                "Cancelled"));

                appointments.add(
                                new AppointmentData(
                                                "Dr. Pooja Iyer",
                                                "Lactation Consultant",
                                                "doctor4.png",
                                                "18 March 2026",
                                                "03:30 PM",
                                                "CarePlus Women Clinic, Pune",
                                                "Cancelled"));
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public VBox createAppointmentPage() {

                VBox page = new VBox();

                page.setFillWidth(true);

                page.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to bottom right, " +
                                                "#FFFFFF 0%, " +
                                                "#FFF7FB 55%, " +
                                                "#F4EDFF 100%);");

                mainContent = new VBox();

                mainContent.setSpacing(20);

                mainContent.setPadding(
                                new Insets(25, 30, 40, 30));

                // =====================================================
                // TITLE
                // =====================================================

                VBox titleBox = new VBox();

                titleBox.setSpacing(5);

                Label title = new Label("Appointments");

                title.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "Manage your appointments and book a new one.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #77778D;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                HBox tabs = createTabs();

                VBox appointmentContent = createMyAppointmentsContent();

                mainContent.getChildren().addAll(
                                titleBox,
                                tabs,
                                appointmentContent);

                ScrollPane scrollPane = new ScrollPane(mainContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setPannable(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                page.getChildren().add(scrollPane);

                return page;
        }

        // =========================================================
        // TABS
        // =========================================================

        private HBox createTabs() {

                HBox tabs = new HBox();

                tabs.setSpacing(35);

                tabs.setPadding(
                                new Insets(0, 0, 8, 0));

                Button myAppointments = createTabButton(
                                "My Appointments",
                                true);

                Button bookAppointment = createTabButton(
                                "Book Appointment",
                                false);

                Button hospitalBooking = createTabButton(
                                "Hospital Booking",
                                false);

                tabs.getChildren().addAll(
                                myAppointments,
                                bookAppointment,
                                hospitalBooking);

                myAppointments.setOnAction(e -> {

                        setActiveTab(
                                        myAppointments,
                                        bookAppointment,
                                        hospitalBooking);

                        replaceContent(
                                        createMyAppointmentsContent());
                });

                bookAppointment.setOnAction(e -> {

                        setActiveTab(
                                        bookAppointment,
                                        myAppointments,
                                        hospitalBooking);

                        replaceContent(
                                        createBookAppointmentContent());
                });

                hospitalBooking.setOnAction(e -> {

                        setActiveTab(
                                        hospitalBooking,
                                        myAppointments,
                                        bookAppointment);

                        replaceContent(
                                        createHospitalBookingContent());
                });

                return tabs;
        }

        // =========================================================
        // TAB BUTTON
        // =========================================================

        private Button createTabButton(
                        String text,
                        boolean active) {

                Button button = new Button(text);

                button.setStyle(
                                active
                                                ? "-fx-background-color: transparent;" +
                                                                "-fx-text-fill: #E84A87;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-border-color: transparent transparent #E84A87 transparent;"
                                                                +
                                                                "-fx-border-width: 0 0 3 0;" +
                                                                "-fx-padding: 8px 4px;"
                                                : "-fx-background-color: transparent;" +
                                                                "-fx-text-fill: #666680;" +
                                                                "-fx-font-size: 14px;" +
                                                                "-fx-border-color: transparent;" +
                                                                "-fx-padding: 8px 4px;");

                return button;
        }

        // =========================================================
        // ACTIVE TAB
        // =========================================================

        private void setActiveTab(
                        Button active,
                        Button other1,
                        Button other2) {

                active.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #E84A87;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: transparent transparent #E84A87 transparent;" +
                                                "-fx-border-width: 0 0 3 0;" +
                                                "-fx-padding: 8px 4px;");

                other1.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 8px 4px;");

                other2.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 8px 4px;");
        }

        // =========================================================
        // REPLACE CONTENT
        // =========================================================

        private void replaceContent(VBox newContent) {

                if (mainContent != null &&
                                mainContent.getChildren().size() >= 3) {

                        mainContent
                                        .getChildren()
                                        .set(2, newContent);
                }
        }

        // =========================================================
        // MY APPOINTMENTS
        // =========================================================

        private VBox createMyAppointmentsContent() {

                VBox content = new VBox();

                content.setSpacing(20);

                HBox layout = new HBox();

                layout.setSpacing(20);

                VBox left = new VBox();

                left.setSpacing(15);

                HBox.setHgrow(
                                left,
                                Priority.ALWAYS);

                VBox right = createBookQuickCard();

                right.setPrefWidth(390);

                right.setMinWidth(350);

                VBox appointmentsCard = createWhiteCard();

                HBox heading = new HBox();

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                VBox headingText = new VBox();

                Label title = new Label("My Appointments");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "Your upcoming and previous appointments");

                subtitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                headingText.getChildren().addAll(
                                title,
                                subtitle);

                HBox.setHgrow(
                                headingText,
                                Priority.ALWAYS);

                Button viewAll = createSmallPinkButton(
                                "View All");

                heading.getChildren().addAll(
                                headingText,
                                viewAll);

                HBox filters = new HBox();

                filters.setSpacing(10);

                Button upcoming = createFilterButton(
                                "Upcoming",
                                true);

                Button completed = createFilterButton(
                                "Completed",
                                false);

                Button cancelled = createFilterButton(
                                "Cancelled",
                                false);

                filters.getChildren().addAll(
                                upcoming,
                                completed,
                                cancelled);

                VBox appointmentList = new VBox();

                appointmentList.setSpacing(12);

                showAppointments(
                                appointmentList,
                                "Upcoming");

                upcoming.setOnAction(e -> {

                        setAppointmentFilterActive(
                                        upcoming,
                                        completed,
                                        cancelled);

                        appointmentList.getChildren().clear();

                        showAppointments(
                                        appointmentList,
                                        "Upcoming");
                });

                completed.setOnAction(e -> {

                        setAppointmentFilterActive(
                                        completed,
                                        upcoming,
                                        cancelled);

                        appointmentList.getChildren().clear();

                        showAppointments(
                                        appointmentList,
                                        "Completed");
                });

                cancelled.setOnAction(e -> {

                        setAppointmentFilterActive(
                                        cancelled,
                                        upcoming,
                                        completed);

                        appointmentList.getChildren().clear();

                        showAppointments(
                                        appointmentList,
                                        "Cancelled");
                });

                HBox urgent = createUrgentBooking();

                appointmentsCard.getChildren().addAll(
                                heading,
                                filters,
                                appointmentList,
                                urgent);

                left.getChildren().add(
                                appointmentsCard);

                layout.getChildren().addAll(
                                left,
                                right);

                content.getChildren().add(
                                layout);

                return content;
        }

        // =========================================================
        // SHOW APPOINTMENTS
        // =========================================================

        private void showAppointments(
                        VBox appointmentList,
                        String requiredStatus) {

                boolean found = false;

                for (AppointmentData appointment : appointments) {

                        if (requiredStatus.equals("Upcoming")) {

                                if (appointment.status.equals("Upcoming") ||
                                                appointment.status.equals("Confirmed") ||
                                                appointment.status.equals("Pending")) {

                                        appointmentList.getChildren().add(
                                                        createAppointmentCard(
                                                                        appointment));

                                        found = true;
                                }

                        } else {

                                if (appointment.status.equals(
                                                requiredStatus)) {

                                        appointmentList.getChildren().add(
                                                        createAppointmentCard(
                                                                        appointment));

                                        found = true;
                                }
                        }
                }

                if (!found) {

                        Label empty = new Label(
                                        "No " +
                                                        requiredStatus.toLowerCase() +
                                                        " appointments.");

                        empty.setStyle(
                                        "-fx-text-fill: #77778D;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-padding: 15px;");

                        appointmentList.getChildren().add(
                                        empty);
                }
        }

        // =========================================================
        // APPOINTMENT CARD
        // =========================================================

        private HBox createAppointmentCard(
                        AppointmentData appointment) {

                HBox card = new HBox();

                card.setSpacing(15);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setPadding(
                                new Insets(15));

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E8DFEA;" +
                                                "-fx-border-radius: 14;" +
                                                "-fx-background-radius: 14;");

                StackPane doctorImage = createDoctorImage(
                                appointment.imageName,
                                68,
                                68);

                VBox information = new VBox();

                information.setSpacing(5);

                Label doctorName = new Label(
                                appointment.doctor);

                doctorName.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label spec = new Label(
                                appointment.speciality);

                spec.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                Label dateLabel = new Label(
                                "📅  " +
                                                appointment.date +
                                                "   •   " +
                                                appointment.time);

                dateLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #55556D;");

                Label locationLabel = new Label(
                                "⌖  " +
                                                appointment.location);

                locationLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                information.getChildren().addAll(
                                doctorName,
                                spec,
                                dateLabel,
                                locationLabel);

                HBox.setHgrow(
                                information,
                                Priority.ALWAYS);

                VBox right = new VBox();

                right.setSpacing(8);

                right.setAlignment(
                                Pos.CENTER_RIGHT);

                Label statusLabel = new Label(
                                appointment.status);

                styleStatus(
                                statusLabel,
                                appointment.status);

                String buttonText;

                if (appointment.status.equals("Pending")) {

                        buttonText = "Reschedule";

                } else {

                        buttonText = "View Details";
                }

                Button action = createOutlinePinkButton(
                                buttonText);

                // =====================================================
                // IMPORTANT: ACTUAL CLICK ACTION
                // =====================================================

                action.setOnAction(e -> {

                        if (appointment.status.equals("Pending")) {

                                showRescheduleDialog(
                                                appointment);

                        } else {

                                showAppointmentDetails(
                                                appointment);
                        }
                });

                right.getChildren().addAll(
                                statusLabel,
                                action);

                card.getChildren().addAll(
                                doctorImage,
                                information,
                                right);

                return card;
        }

        // =========================================================
        // VIEW DETAILS DIALOG
        // =========================================================

        private void showAppointmentDetails(
                        AppointmentData appointment) {

                Dialog<Void> dialog = new Dialog<>();

                dialog.setTitle(
                                "Appointment Details");

                dialog.setHeaderText(
                                "Appointment with " +
                                                appointment.doctor);

                VBox box = new VBox();

                box.setSpacing(12);

                box.setPadding(
                                new Insets(20));

                Label doctor = new Label(
                                "Doctor: " +
                                                appointment.doctor);

                Label speciality = new Label(
                                "Specialization: " +
                                                appointment.speciality);

                Label date = new Label(
                                "Date: " +
                                                appointment.date);

                Label time = new Label(
                                "Time: " +
                                                appointment.time);

                Label clinic = new Label(
                                "Clinic: " +
                                                appointment.location);

                Label status = new Label(
                                "Status: " +
                                                appointment.status);

                Label fee = new Label(
                                "Consultation Fee: ₹800");

                styleDialogLabel(doctor);
                styleDialogLabel(speciality);
                styleDialogLabel(date);
                styleDialogLabel(time);
                styleDialogLabel(clinic);
                styleDialogLabel(status);
                styleDialogLabel(fee);

                box.getChildren().addAll(
                                doctor,
                                speciality,
                                date,
                                time,
                                clinic,
                                status,
                                fee);

                dialog.getDialogPane()
                                .setContent(box);

                ButtonType close = new ButtonType(
                                "Close");

                dialog.getDialogPane()
                                .getButtonTypes()
                                .add(close);

                dialog.showAndWait();
        }

        // =========================================================
        // RESCHEDULE DIALOG
        // =========================================================

        private void showRescheduleDialog(
                        AppointmentData appointment) {

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle(
                                "Reschedule Appointment");

                dialog.setHeaderText(
                                "Reschedule with " +
                                                appointment.doctor);

                VBox box = new VBox();

                box.setSpacing(15);

                box.setPadding(
                                new Insets(20));

                Label doctor = new Label(
                                "Doctor: " +
                                                appointment.doctor);

                doctor.setStyle(
                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label dateLabel = new Label(
                                "Select New Date");

                DatePicker datePicker = new DatePicker(
                                LocalDate.now());

                datePicker.setMaxWidth(
                                Double.MAX_VALUE);

                Label timeLabel = new Label(
                                "Select New Time");

                ComboBox<String> timeBox = new ComboBox<>();

                timeBox.getItems().addAll(
                                "09:00 AM",
                                "09:30 AM",
                                "10:00 AM",
                                "10:30 AM",
                                "11:00 AM",
                                "04:00 PM",
                                "04:30 PM",
                                "05:00 PM",
                                "05:30 PM",
                                "06:00 PM");

                timeBox.setValue(
                                "10:00 AM");

                timeBox.setMaxWidth(
                                Double.MAX_VALUE);

                box.getChildren().addAll(
                                doctor,
                                dateLabel,
                                datePicker,
                                timeLabel,
                                timeBox);

                dialog.getDialogPane()
                                .setContent(box);

                ButtonType reschedule = new ButtonType(
                                "Reschedule");

                ButtonType cancel = new ButtonType(
                                "Cancel");

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                reschedule,
                                                cancel);

                dialog.setResultConverter(
                                button -> button);

                dialog.showAndWait().ifPresent(
                                result -> {

                                        if (result == reschedule) {

                                                if (datePicker.getValue() == null) {

                                                        showAlert(
                                                                        "Please select a date.");

                                                        return;
                                                }

                                                appointment.date = datePicker
                                                                .getValue()
                                                                .format(
                                                                                DateTimeFormatter.ofPattern(
                                                                                                "dd MMM yyyy"));

                                                appointment.time = timeBox.getValue();

                                                appointment.status = "Upcoming";

                                                showAlert(
                                                                "Appointment rescheduled successfully!");

                                                replaceContent(
                                                                createMyAppointmentsContent());
                                        }
                                });
        }

        // =========================================================
        // QUICK BOOK CARD
        // =========================================================

        private VBox createBookQuickCard() {

                VBox card = createWhiteCard();

                Label title = new Label(
                                "Book an Appointment");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "Find the right doctor for you");

                subtitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                TextField search = createSearchField(
                                "Search doctors...");

                ComboBox<String> specialty = new ComboBox<>();

                specialty.getItems().addAll(
                                "All Specialties",
                                "Obstetrician & Gynecologist",
                                "Nutritionist",
                                "Pediatrician",
                                "Lactation Consultant",
                                "General Physician");

                specialty.setValue(
                                "All Specialties");

                specialty.setMaxWidth(
                                Double.MAX_VALUE);

                Button book = createGradientButton(
                                "Book Appointment  →");

                book.setMaxWidth(
                                Double.MAX_VALUE);

                book.setOnAction(e -> {

                        replaceContent(
                                        createBookAppointmentContent());
                });

                card.getChildren().addAll(
                                title,
                                subtitle,
                                search,
                                specialty,
                                book);

                return card;
        }

        // =========================================================
        // URGENT BOOKING
        // =========================================================

        private HBox createUrgentBooking() {

                HBox box = new HBox();

                box.setSpacing(15);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                box.setPadding(
                                new Insets(15));

                box.setStyle(
                                "-fx-background-color: #FFF0F6;" +
                                                "-fx-border-color: #F5D1E1;" +
                                                "-fx-border-radius: 14;" +
                                                "-fx-background-radius: 14;");

                FontAwesomeIconView icon = new FontAwesomeIconView(
                                FontAwesomeIcon.CALENDAR);

                icon.setSize("25");

                icon.setFill(
                                Color.web(PINK));

                VBox text = new VBox();

                Label title = new Label(
                                "Need to see a doctor immediately?");

                title.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label description = new Label(
                                "Book an instant consultation with available doctors.");

                description.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                text.getChildren().addAll(
                                title,
                                description);

                HBox.setHgrow(
                                text,
                                Priority.ALWAYS);

                Button book = createGradientButton(
                                "Book Now");

                book.setOnAction(e -> {

                        replaceContent(
                                        createBookAppointmentContent());
                });

                box.getChildren().addAll(
                                icon,
                                text,
                                book);

                return box;
        }

        // =========================================================
        // BOOK APPOINTMENT CONTENT
        // =========================================================

        private VBox createBookAppointmentContent() {

                VBox content = new VBox();

                content.setSpacing(20);

                HBox layout = new HBox();

                layout.setSpacing(20);

                VBox doctorsSection = createDoctorsSection();

                HBox.setHgrow(
                                doctorsSection,
                                Priority.ALWAYS);

                VBox bookingSection = createDoctorBookingSection();

                bookingSection.setPrefWidth(430);

                bookingSection.setMinWidth(400);

                layout.getChildren().addAll(
                                doctorsSection,
                                bookingSection);

                content.getChildren().add(
                                layout);

                return content;
        }

        // =========================================================
        // DOCTORS SECTION
        // =========================================================

        private VBox createDoctorsSection() {

                VBox card = createWhiteCard();

                HBox heading = new HBox();

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titleBox = new VBox();

                Label title = new Label(
                                "Find a Doctor");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "Search doctors by specialty, location and rating");

                subtitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                Button filter = createOutlinePinkButton(
                                "☷  Filter");

                heading.getChildren().addAll(
                                titleBox,
                                filter);

                HBox filters = new HBox();

                filters.setSpacing(10);

                TextField search = createSearchField(
                                "Search doctors...");

                HBox.setHgrow(
                                search,
                                Priority.ALWAYS);

                ComboBox<String> specialty = new ComboBox<>();

                specialty.getItems().addAll(
                                "Specialty",
                                "Obstetrician & Gynecologist",
                                "Nutritionist",
                                "Pediatrician",
                                "Lactation Consultant",
                                "General Physician");

                specialty.setValue(
                                "Specialty");

                specialty.setPrefWidth(170);

                ComboBox<String> location = new ComboBox<>();

                location.getItems().addAll(
                                "Location",
                                "Pune",
                                "Mumbai",
                                "Nashik",
                                "Nagpur");

                location.setValue(
                                "Location");

                location.setPrefWidth(130);

                filters.getChildren().addAll(
                                search,
                                specialty,
                                location);

                VBox doctors = new VBox();

                doctors.setSpacing(12);

                // =====================================================
                // ALL DOCTORS
                // =====================================================

                List<DoctorData> doctorList = createDoctorData();

                // =====================================================
                // REFRESH DOCTOR LIST
                // =====================================================

                Runnable refreshDoctors = () -> {

                        doctors.getChildren().clear();

                        String query = search.getText()
                                        .trim()
                                        .toLowerCase();

                        String selectedSpecialty = specialty.getValue();

                        String selectedLocation = location.getValue();

                        boolean found = false;

                        for (DoctorData doctor : doctorList) {

                                boolean nameMatch = query.isEmpty() ||
                                                doctor.name
                                                                .toLowerCase()
                                                                .contains(query)
                                                ||
                                                doctor.speciality
                                                                .toLowerCase()
                                                                .contains(query);

                                boolean specialityMatch = selectedSpecialty.equals(
                                                "Specialty") ||
                                                doctor.speciality.equals(
                                                                selectedSpecialty);

                                boolean locationMatch = selectedLocation.equals(
                                                "Location") ||
                                                doctor.location.equals(
                                                                selectedLocation);

                                if (nameMatch &&
                                                specialityMatch &&
                                                locationMatch) {

                                        doctors.getChildren().add(
                                                        createDoctorListCard(
                                                                        doctor));

                                        found = true;
                                }
                        }

                        if (!found) {

                                Label noDoctor = new Label(
                                                "No doctors found.");

                                noDoctor.setStyle(
                                                "-fx-text-fill: #77778D;" +
                                                                "-fx-padding: 20px;");

                                doctors.getChildren().add(
                                                noDoctor);
                        }
                };

                search.textProperty().addListener(
                                (obs, oldValue, newValue) -> refreshDoctors.run());

                specialty.valueProperty().addListener(
                                (obs, oldValue, newValue) -> refreshDoctors.run());

                location.valueProperty().addListener(
                                (obs, oldValue, newValue) -> refreshDoctors.run());

                refreshDoctors.run();

                Button more = createOutlinePinkButton(
                                "View More Doctors  ↓");

                more.setMaxWidth(
                                Double.MAX_VALUE);

                card.getChildren().addAll(
                                heading,
                                filters,
                                doctors,
                                more);

                return card;
        }

        // =========================================================
        // DOCTOR DATA
        // =========================================================

        private List<DoctorData> createDoctorData() {

                List<DoctorData> list = new ArrayList<>();

                list.add(
                                new DoctorData(
                                                "Dr. Anjali Mehta",
                                                "Obstetrician & Gynecologist",
                                                "doctor1.png",
                                                "4.8",
                                                "256 Reviews",
                                                "Pune"));

                list.add(
                                new DoctorData(
                                                "Dr. Neha Kulkarni",
                                                "Nutritionist",
                                                "doctor2.png",
                                                "4.7",
                                                "189 Reviews",
                                                "Pune"));

                list.add(
                                new DoctorData(
                                                "Dr. Rahul Deshmukh",
                                                "Pediatrician",
                                                "doctor3.png",
                                                "4.6",
                                                "312 Reviews",
                                                "Pune"));

                list.add(
                                new DoctorData(
                                                "Dr. Pooja Iyer",
                                                "Lactation Consultant",
                                                "doctor4.png",
                                                "4.9",
                                                "162 Reviews",
                                                "Pune"));

                list.add(
                                new DoctorData(
                                                "Dr. Simran Kaur",
                                                "General Physician",
                                                "doctor5.png",
                                                "4.5",
                                                "205 Reviews",
                                                "Pune"));

                return list;
        }

        // =========================================================
        // DOCTOR LIST CARD
        // =========================================================

        private HBox createDoctorListCard(
                        DoctorData doctor) {

                HBox card = new HBox();

                card.setSpacing(12);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setPadding(
                                new Insets(12));

                card.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E8DFEA;" +
                                                "-fx-border-radius: 13;" +
                                                "-fx-background-radius: 13;");

                StackPane image = createDoctorImage(
                                doctor.imageName,
                                58,
                                58);

                VBox text = new VBox();

                text.setSpacing(4);

                Label doctorName = new Label(
                                doctor.name);

                doctorName.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label specialityLabel = new Label(
                                doctor.speciality);

                specialityLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                Label ratingLabel = new Label(
                                "★ " +
                                                doctor.rating +
                                                "   (" +
                                                doctor.reviews +
                                                ")");

                ratingLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #E7A51A;");

                text.getChildren().addAll(
                                doctorName,
                                specialityLabel,
                                ratingLabel);

                HBox.setHgrow(
                                text,
                                Priority.ALWAYS);

                Button select = createOutlinePinkButton(
                                "Select");

                select.setOnAction(e -> {

                        selectedDoctor = doctor.name;

                        selectedSpeciality = doctor.speciality;

                        selectedImage = doctor.imageName;

                        selectedRating = doctor.rating;

                        selectedReviews = doctor.reviews;

                        selectedClinic = "CarePlus Women Clinic, " +
                                        doctor.location;

                        selectedFee = "₹800";

                        // Refresh booking section
                        replaceContent(
                                        createBookAppointmentContent());
                });

                card.getChildren().addAll(
                                image,
                                text,
                                select);

                return card;
        }

        // =========================================================
        // DOCTOR BOOKING SECTION
        // =========================================================

        private VBox createDoctorBookingSection() {

                VBox card = createWhiteCard();

                Label title = new Label(
                                "Book an Appointment");

                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                // =====================================================
                // SELECTED DOCTOR
                // =====================================================

                HBox doctor = new HBox();

                doctor.setSpacing(12);

                doctor.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane image = createDoctorImage(
                                selectedImage,
                                65,
                                65);

                VBox doctorInfo = new VBox();

                doctorInfo.setSpacing(4);

                Label doctorName = new Label(
                                selectedDoctor);

                doctorName.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label speciality = new Label(
                                selectedSpeciality);

                speciality.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                Label rating = new Label(
                                "★ " +
                                                selectedRating +
                                                "   (" +
                                                selectedReviews +
                                                ")");

                rating.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #E7A51A;");

                doctorInfo.getChildren().addAll(
                                doctorName,
                                speciality,
                                rating);

                doctor.getChildren().addAll(
                                image,
                                doctorInfo);

                Label clinic = new Label(
                                "⌖  " +
                                                selectedClinic);

                clinic.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #55556D;");

                Label fee = new Label(
                                "Consultation Fee  " +
                                                selectedFee);

                fee.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #E84A87;");

                // =====================================================
                // DATE
                // =====================================================

                Label dateTitle = new Label(
                                "Select Date");

                dateTitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                HBox dates = new HBox();

                dates.setSpacing(7);

                LocalDate today = LocalDate.now();

                final LocalDate[] selectedDate = { today };

                for (int i = 0; i < 5; i++) {

                        LocalDate date = today.plusDays(i);

                        Button dateButton = new Button(
                                        i == 0
                                                        ? "Today\n" +
                                                                        date.format(
                                                                                        DateTimeFormatter.ofPattern(
                                                                                                        "dd MMM"))
                                                        : date.format(
                                                                        DateTimeFormatter.ofPattern(
                                                                                        "EEE\n" +
                                                                                                        "dd MMM")));

                        dateButton.setPrefWidth(68);

                        dateButton.setPrefHeight(55);

                        final LocalDate currentDate = date;

                        styleDateButton(
                                        dateButton,
                                        i == 0);

                        dateButton.setOnAction(e -> {

                                selectedDate[0] = currentDate;

                                for (Node node : dates.getChildren()) {

                                        if (node instanceof Button) {

                                                styleDateButton(
                                                                (Button) node,
                                                                false);
                                        }
                                }

                                styleDateButton(
                                                dateButton,
                                                true);
                        });

                        dates.getChildren().add(
                                        dateButton);
                }

                // =====================================================
                // TIME
                // =====================================================

                Label timeTitle = new Label(
                                "Available Time Slots");

                timeTitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                GridPane times = new GridPane();

                times.setHgap(8);

                times.setVgap(8);

                String[] slots = {

                                "09:00 AM",
                                "09:30 AM",
                                "10:00 AM",
                                "10:30 AM",
                                "11:00 AM",

                                "04:00 PM",
                                "04:30 PM",
                                "05:00 PM",
                                "05:30 PM",
                                "06:00 PM"
                };

                final String[] selectedTime = { null };

                for (int i = 0; i < slots.length; i++) {

                        Button time = new Button(
                                        slots[i]);

                        time.setPrefWidth(72);

                        time.setPrefHeight(35);

                        styleTimeButton(
                                        time,
                                        false);

                        final String currentTime = slots[i];

                        time.setOnAction(e -> {

                                selectedTime[0] = currentTime;

                                for (Node node : times.getChildren()) {

                                        if (node instanceof Button) {

                                                styleTimeButton(
                                                                (Button) node,
                                                                false);
                                        }
                                }

                                styleTimeButton(
                                                time,
                                                true);
                        });

                        times.add(
                                        time,
                                        i % 5,
                                        i / 5);
                }

                // =====================================================
                // CONFIRM
                // =====================================================

                Button confirm = createGradientButton(
                                "Confirm Appointment");

                confirm.setMaxWidth(
                                Double.MAX_VALUE);

                confirm.setOnAction(e -> {

                        if (selectedDate[0] == null) {

                                showAlert(
                                                "Please select a date.");

                                return;
                        }

                        if (selectedTime[0] == null) {

                                showAlert(
                                                "Please select a time slot.");

                                return;
                        }

                        String formattedDate = selectedDate[0]
                                        .format(
                                                        DateTimeFormatter.ofPattern(
                                                                        "dd MMM yyyy"));

                        AppointmentData newAppointment = new AppointmentData(
                                        selectedDoctor,
                                        selectedSpeciality,
                                        selectedImage,
                                        formattedDate,
                                        selectedTime[0],
                                        selectedClinic,
                                        "Confirmed");

                        appointments.add(
                                        0,
                                        newAppointment);

                        showAlert(
                                        "Appointment confirmed successfully!\n\n" +
                                                        "Doctor: " +
                                                        selectedDoctor +
                                                        "\nDate: " +
                                                        formattedDate +
                                                        "\nTime: " +
                                                        selectedTime[0]);

                        replaceContent(
                                        createMyAppointmentsContent());
                });

                card.getChildren().addAll(
                                title,
                                doctor,
                                clinic,
                                fee,
                                createDivider(),
                                dateTitle,
                                dates,
                                timeTitle,
                                times,
                                confirm);

                return card;
        }

        // =========================================================
        // HOSPITAL BOOKING
        // =========================================================

        private VBox createHospitalBookingContent() {

                VBox content = new VBox();

                content.setSpacing(20);

                VBox searchCard = createWhiteCard();

                Label title = new Label(
                                "Hospital Booking");

                title.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label subtitle = new Label(
                                "Find hospitals, check facilities and book appointments.");

                subtitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #77778D;");

                HBox searchRow = new HBox();

                searchRow.setSpacing(10);

                TextField search = createSearchField(
                                "Search hospitals...");

                HBox.setHgrow(
                                search,
                                Priority.ALWAYS);

                ComboBox<String> location = new ComboBox<>();

                location.getItems().addAll(
                                "Location",
                                "Pune",
                                "Mumbai",
                                "Nashik",
                                "Nagpur");

                location.setValue(
                                "Location");

                location.setPrefWidth(150);

                ComboBox<String> department = new ComboBox<>();

                department.getItems().addAll(
                                "Department",
                                "Maternity",
                                "Gynecology",
                                "Pediatrics",
                                "General");

                department.setValue(
                                "Department");

                department.setPrefWidth(160);

                Button searchButton = createGradientButton(
                                "Search");

                searchRow.getChildren().addAll(
                                search,
                                location,
                                department,
                                searchButton);

                searchCard.getChildren().addAll(
                                title,
                                subtitle,
                                searchRow);

                HBox hospitals = new HBox();

                hospitals.setSpacing(15);

                hospitals.getChildren().addAll(

                                createHospitalCard(
                                                "CarePlus Women Hospital",
                                                "Pune",
                                                "4.8",
                                                "Maternity • Gynecology • NICU",
                                                "hospital1.png"),

                                createHospitalCard(
                                                "MotherCare Multispeciality",
                                                "Pune",
                                                "4.7",
                                                "Maternity • Pediatrics • Surgery",
                                                "hospital2.png"),

                                createHospitalCard(
                                                "LifeSpring Women & Child",
                                                "Pune",
                                                "4.6",
                                                "Gynecology • Pediatrics",
                                                "hospital3.png"));

                content.getChildren().addAll(
                                searchCard,
                                hospitals);

                return content;
        }

        // =========================================================
        // HOSPITAL CARD
        // =========================================================

        private VBox createHospitalCard(
                        String name,
                        String location,
                        String rating,
                        String facilities,
                        String imageName) {

                VBox card = createWhiteCard();

                card.setPrefWidth(300);

                card.setMinWidth(280);

                StackPane image = createHospitalImage(
                                imageName,
                                260,
                                130);

                Label hospitalName = new Label(name);

                hospitalName.setWrapText(true);

                hospitalName.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #24234F;");

                Label locationLabel = new Label(
                                "⌖  " +
                                                location);

                locationLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #77778D;");

                Label ratingLabel = new Label(
                                "★ " +
                                                rating +
                                                "   Patients Recommend");

                ratingLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #E7A51A;");

                Label facilitiesLabel = new Label(
                                facilities);

                facilitiesLabel.setWrapText(true);

                facilitiesLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #55556D;");

                Button book = createGradientButton(
                                "Book Hospital Appointment");

                book.setMaxWidth(
                                Double.MAX_VALUE);

                Button details = createOutlinePinkButton(
                                "View Hospital Details");

                details.setMaxWidth(
                                Double.MAX_VALUE);

                details.setOnAction(e -> {

                        showAlert(
                                        "Hospital Details\n\n" +
                                                        "Hospital: " +
                                                        name +
                                                        "\nLocation: " +
                                                        location +
                                                        "\nRating: " +
                                                        rating +
                                                        "\nFacilities: " +
                                                        facilities);
                });

                book.setOnAction(e -> {

                        showAlert(
                                        "Hospital appointment booking selected for:\n" +
                                                        name);
                });

                card.getChildren().addAll(
                                image,
                                hospitalName,
                                locationLabel,
                                ratingLabel,
                                facilitiesLabel,
                                details,
                                book);

                return card;
        }

        // =========================================================
        // DOCTOR IMAGE
        // =========================================================

        private StackPane createDoctorImage(
                        String imageName,
                        double width,
                        double height) {

                StackPane holder = new StackPane();

                holder.setPrefSize(
                                width,
                                height);

                holder.setMinSize(
                                width,
                                height);

                holder.setMaxSize(
                                width,
                                height);

                holder.setStyle(
                                "-fx-background-color: #FFF1F7;" +
                                                "-fx-background-radius: 50%;");

                String[] paths = {

                                "/assets/images/doctors/" +
                                                imageName,

                                "/assets/images/doctor/" +
                                                imageName,

                                "/assets/images/" +
                                                imageName
                };

                for (String path : paths) {

                        var resource = getClass()
                                        .getResource(path);

                        if (resource != null) {

                                Image image = new Image(
                                                resource.toExternalForm());

                                ImageView imageView = new ImageView(image);

                                imageView.setFitWidth(
                                                width);

                                imageView.setFitHeight(
                                                height);

                                imageView.setPreserveRatio(
                                                false);

                                Circle clip = new Circle(
                                                width / 2,
                                                height / 2,
                                                Math.min(
                                                                width,
                                                                height) / 2);

                                imageView.setClip(
                                                clip);

                                holder.getChildren().add(
                                                imageView);

                                return holder;
                        }
                }

                FontAwesomeIconView doctorIcon = new FontAwesomeIconView(
                                FontAwesomeIcon.USER_MD);

                doctorIcon.setSize(
                                String.valueOf(
                                                Math.min(
                                                                width,
                                                                height) * 0.45));

                doctorIcon.setFill(
                                Color.web(PURPLE));

                holder.getChildren().add(
                                doctorIcon);

                return holder;
        }

        // =========================================================
        // HOSPITAL IMAGE
        // =========================================================

        private StackPane createHospitalImage(
                        String imageName,
                        double width,
                        double height) {

                StackPane holder = new StackPane();

                holder.setPrefSize(
                                width,
                                height);

                holder.setStyle(
                                "-fx-background-color: #F8F1FF;" +
                                                "-fx-background-radius: 12;");

                String[] paths = {

                                "/assets/images/hospitals/" +
                                                imageName,

                                "/assets/images/hospital/" +
                                                imageName,

                                "/assets/images/" +
                                                imageName
                };

                for (String path : paths) {

                        var resource = getClass()
                                        .getResource(path);

                        if (resource != null) {

                                ImageView imageView = new ImageView(
                                                new Image(
                                                                resource.toExternalForm()));

                                imageView.setFitWidth(
                                                width);

                                imageView.setFitHeight(
                                                height);

                                imageView.setPreserveRatio(
                                                true);

                                holder.getChildren().add(
                                                imageView);

                                return holder;
                        }
                }

                FontAwesomeIconView icon = new FontAwesomeIconView(
                                FontAwesomeIcon.HOSPITAL_ALT);

                icon.setSize("45");

                icon.setFill(
                                Color.web(PURPLE));

                holder.getChildren().add(
                                icon);

                return holder;
        }

        // =========================================================
        // SEARCH FIELD
        // =========================================================

        private TextField createSearchField(
                        String prompt) {

                TextField field = new TextField();

                field.setPromptText(
                                prompt);

                field.setPrefHeight(
                                42);

                field.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E4DCE7;" +
                                                "-fx-border-radius: 10;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                return field;
        }

        // =========================================================
        // WHITE CARD
        // =========================================================

        private VBox createWhiteCard() {

                VBox card = new VBox();

                card.setSpacing(12);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: #E7DCE8;" +
                                                "-fx-border-radius: 18;");

                return card;
        }

        // =========================================================
        // DIVIDER
        // =========================================================

        private Label createDivider() {

                Label divider = new Label();

                divider.setPrefHeight(1);

                divider.setMaxWidth(
                                Double.MAX_VALUE);

                divider.setStyle(
                                "-fx-background-color: #EEE7EF;");

                return divider;
        }

        // =========================================================
        // DATE BUTTON STYLE
        // =========================================================

        private void styleDateButton(
                        Button button,
                        boolean active) {

                button.setStyle(
                                active
                                                ? "-fx-background-color: #FFF0F6;" +
                                                                "-fx-text-fill: #E84A87;" +
                                                                "-fx-border-color: #E84A87;" +
                                                                "-fx-border-radius: 10;" +
                                                                "-fx-background-radius: 10;" +
                                                                "-fx-font-size: 10px;" +
                                                                "-fx-font-weight: bold;"
                                                : "-fx-background-color: white;" +
                                                                "-fx-text-fill: #55556D;" +
                                                                "-fx-border-color: #E6DFE8;" +
                                                                "-fx-border-radius: 10;" +
                                                                "-fx-background-radius: 10;" +
                                                                "-fx-font-size: 10px;");
        }

        // =========================================================
        // TIME BUTTON STYLE
        // =========================================================

        private void styleTimeButton(
                        Button button,
                        boolean active) {

                button.setStyle(
                                active
                                                ? "-fx-background-color: #FFF0F6;" +
                                                                "-fx-text-fill: #E84A87;" +
                                                                "-fx-border-color: #E84A87;" +
                                                                "-fx-border-radius: 8;" +
                                                                "-fx-background-radius: 8;" +
                                                                "-fx-font-size: 10px;" +
                                                                "-fx-font-weight: bold;"
                                                : "-fx-background-color: white;" +
                                                                "-fx-text-fill: #55556D;" +
                                                                "-fx-border-color: #E5DFE8;" +
                                                                "-fx-border-radius: 8;" +
                                                                "-fx-background-radius: 8;" +
                                                                "-fx-font-size: 10px;");
        }

        // =========================================================
        // GRADIENT BUTTON
        // =========================================================

        private Button createGradientButton(
                        String text) {

                Button button = new Button(text);

                button.setAlignment(
                                Pos.CENTER);

                button.setStyle(
                                "-fx-background-color: linear-gradient(" +
                                                "to right, #F54B87, #9B4DCC);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-padding: 9px 18px;");

                return button;
        }

        // =========================================================
        // OUTLINE BUTTON
        // =========================================================

        private Button createOutlinePinkButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #E84A87;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: #E4B6CD;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-padding: 7px 12px;");

                return button;
        }

        // =========================================================
        // SMALL PINK BUTTON
        // =========================================================

        private Button createSmallPinkButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #9B4DCC;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: transparent;");

                return button;
        }

        // =========================================================
        // FILTER BUTTON
        // =========================================================

        private Button createFilterButton(
                        String text,
                        boolean active) {

                Button button = new Button(text);

                button.setPrefWidth(105);

                button.setStyle(
                                active
                                                ? "-fx-background-color: #FFF0F6;" +
                                                                "-fx-text-fill: #E84A87;" +
                                                                "-fx-border-color: #F0A9C8;" +
                                                                "-fx-border-radius: 9;" +
                                                                "-fx-background-radius: 9;" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-font-weight: bold;"
                                                : "-fx-background-color: white;" +
                                                                "-fx-text-fill: #666680;" +
                                                                "-fx-border-color: #E6DFE8;" +
                                                                "-fx-border-radius: 9;" +
                                                                "-fx-background-radius: 9;" +
                                                                "-fx-font-size: 11px;");

                return button;
        }

        // =========================================================
        // FILTER ACTIVE
        // =========================================================

        private void setAppointmentFilterActive(
                        Button active,
                        Button other1,
                        Button other2) {

                active.setStyle(
                                "-fx-background-color: #FFF0F6;" +
                                                "-fx-text-fill: #E84A87;" +
                                                "-fx-border-color: #F0A9C8;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;");

                other1.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-border-color: #E6DFE8;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-size: 11px;");

                other2.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #666680;" +
                                                "-fx-border-color: #E6DFE8;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-font-size: 11px;");
        }

        // =========================================================
        // STATUS STYLE
        // =========================================================

        private void styleStatus(
                        Label label,
                        String status) {

                if (status.equals("Cancelled")) {

                        label.setStyle(
                                        "-fx-background-color: #FDECEC;" +
                                                        "-fx-text-fill: #D95353;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 5px 12px;" +
                                                        "-fx-font-size: 10px;" +
                                                        "-fx-font-weight: bold;");

                } else if (status.equals("Completed")) {

                        label.setStyle(
                                        "-fx-background-color: #EAF9F1;" +
                                                        "-fx-text-fill: #29935C;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 5px 12px;" +
                                                        "-fx-font-size: 10px;" +
                                                        "-fx-font-weight: bold;");

                } else if (status.equals("Pending")) {

                        label.setStyle(
                                        "-fx-background-color: #FFF7E6;" +
                                                        "-fx-text-fill: #D99000;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 5px 12px;" +
                                                        "-fx-font-size: 10px;" +
                                                        "-fx-font-weight: bold;");

                } else {

                        label.setStyle(
                                        "-fx-background-color: #EAF9F1;" +
                                                        "-fx-text-fill: #29935C;" +
                                                        "-fx-background-radius: 15;" +
                                                        "-fx-padding: 5px 12px;" +
                                                        "-fx-font-size: 10px;" +
                                                        "-fx-font-weight: bold;");
                }
        }

        // =========================================================
        // DIALOG LABEL
        // =========================================================

        private void styleDialogLabel(
                        Label label) {

                label.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #24234F;");
        }

        // =========================================================
        // ALERT
        // =========================================================

        private void showAlert(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "MaaCare AI");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        // =========================================================
        // TEMPORARY APPOINTMENT MODEL
        // =========================================================

        private static class AppointmentData {

                String doctor;
                String speciality;
                String imageName;
                String date;
                String time;
                String location;
                String status;

                AppointmentData(
                                String doctor,
                                String speciality,
                                String imageName,
                                String date,
                                String time,
                                String location,
                                String status) {

                        this.doctor = doctor;
                        this.speciality = speciality;
                        this.imageName = imageName;
                        this.date = date;
                        this.time = time;
                        this.location = location;
                        this.status = status;
                }
        }

        // =========================================================
        // TEMPORARY DOCTOR DATA
        // =========================================================

        private static class DoctorData {

                String name;
                String speciality;
                String imageName;
                String rating;
                String reviews;
                String location;

                DoctorData(
                                String name,
                                String speciality,
                                String imageName,
                                String rating,
                                String reviews,
                                String location) {

                        this.name = name;
                        this.speciality = speciality;
                        this.imageName = imageName;
                        this.rating = rating;
                        this.reviews = reviews;
                        this.location = location;
                }
        }
}