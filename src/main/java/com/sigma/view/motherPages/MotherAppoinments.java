package com.sigma.view.motherPages;

import com.sigma.model.BedBooking;
import com.sigma.controller.HospitalController.BedBookingController;
import com.sigma.controller.HospitalController.AppointmentController;
import com.sigma.controller.doctorController.DoctorAppointmentController;
import com.sigma.model.DoctorModel.DoctorAppointment;
import com.sigma.model.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.ListenerRegistration;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.application.Platform;
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

public class MotherAppoinments {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";
    private final String LIGHT_PINK = "#FFF3F8";
    private final String BORDER = "#E7DCE8";

    private VBox mainContent;

    private final BedBookingController bedBookingController =
            new BedBookingController();

    private ListenerRegistration bedBookingListener;

    private final DoctorAppointmentController appointmentController =
            new DoctorAppointmentController();

    private final AppointmentController hospitalAppointmentController =
            new AppointmentController();

    private final String motherUid;
    private final String motherName;

    private String selectedDoctor = "Dr. Anjali Mehta";
    private String selectedSpeciality = "Obstetrician & Gynecologist";
    private String selectedImage = "doctor1.png";
    private String selectedRating = "4.8";
    private String selectedReviews = "256 Reviews";
    private String selectedClinic = "CarePlus Women Clinic, Pune";
    private String selectedFee = "₹800";

    private final List<AppointmentData> appointments = new ArrayList<>();

    public MotherAppoinments() {
        this(null);
    }

    public MotherAppoinments(String motherUid) {
        this.motherUid = motherUid;
        this.motherName = "Mother";

        System.out.println("[MOTHER APPOINTMENTS] Mother UID = " + motherUid);

        if (motherUid == null || motherUid.trim().isEmpty()) {
            loadDummyAppointments();
        }
    }

    private void loadDummyAppointments() {
        appointments.clear();

        appointments.add(new AppointmentData(null, "Dr. Anjali Mehta",
                "Obstetrician & Gynecologist", "doctor1.png",
                "20 May 2026", "11:00 AM",
                "CarePlus Women Clinic, Pune", "Confirmed"));

        appointments.add(new AppointmentData(null, "Dr. Neha Kulkarni",
                "Nutritionist", "doctor2.png",
                "28 May 2026", "04:00 PM",
                "CarePlus Women Clinic, Pune", "Upcoming"));

        appointments.add(new AppointmentData(null, "Dr. Rahul Deshmukh",
                "Pediatrician", "doctor3.png",
                "10 June 2026", "10:30 AM",
                "CarePlus Children Clinic, Pune", "Pending"));

        appointments.add(new AppointmentData(null, "Dr. Anjali Mehta",
                "Obstetrician & Gynecologist", "doctor1.png",
                "10 April 2026", "11:00 AM",
                "CarePlus Women Clinic, Pune", "Completed"));

        appointments.add(new AppointmentData(null, "Dr. Neha Kulkarni",
                "Nutritionist", "doctor2.png",
                "25 March 2026", "04:00 PM",
                "CarePlus Women Clinic, Pune", "Completed"));

        appointments.add(new AppointmentData(null, "Dr. Rahul Deshmukh",
                "Pediatrician", "doctor3.png",
                "05 April 2026", "10:30 AM",
                "CarePlus Children Clinic, Pune", "Cancelled"));

        appointments.add(new AppointmentData(null, "Dr. Pooja Iyer",
                "Lactation Consultant", "doctor4.png",
                "18 March 2026", "03:30 PM",
                "CarePlus Women Clinic, Pune", "Cancelled"));
    }

    public VBox createAppointmentPage() {
        VBox page = new VBox();
        page.setFillWidth(true);
        page.setStyle("-fx-background-color: linear-gradient(to bottom right, #FFFFFF 0%, #FFF7FB 55%, #F4EDFF 100%);");

        mainContent = new VBox();
        mainContent.setSpacing(20);
        mainContent.setPadding(new Insets(25, 30, 40, 30));

        VBox titleBox = new VBox();
        titleBox.setSpacing(5);

        Label title = new Label("Appointments");
        title.setStyle("-fx-font-size: 27px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label("Manage your appointments and book a new one.");
        subtitle.setStyle("-fx-font-size: 14px;-fx-text-fill: #77778D;");

        titleBox.getChildren().addAll(title, subtitle);

        HBox tabs = createTabs();
        VBox appointmentContent = createMyAppointmentsContent();

        mainContent.getChildren().addAll(titleBox, tabs, appointmentContent);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent;-fx-background: transparent;-fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        page.getChildren().add(scrollPane);
        return page;
    }

    private HBox createTabs() {
        HBox tabs = new HBox();
        tabs.setSpacing(22);
        tabs.setPadding(new Insets(0, 0, 8, 0));

        Button myAppointments = createTabButton("My Appointments", true);
        Button bookAppointment = createTabButton("Book Appointment", false);
        Button hospitalBooking = createTabButton("Hospital Booking", false);
        Button bedBooking = createTabButton("Bed Booking", false);

        tabs.getChildren().addAll(myAppointments, bookAppointment, hospitalBooking, bedBooking);

        myAppointments.setOnAction(e -> {
            setActiveTab(myAppointments, bookAppointment, hospitalBooking, bedBooking);
            replaceContent(createMyAppointmentsContent());
        });

        bookAppointment.setOnAction(e -> {
            setActiveTab(bookAppointment, myAppointments, hospitalBooking, bedBooking);
            replaceContent(createBookAppointmentContent());
        });

        hospitalBooking.setOnAction(e -> {
            setActiveTab(hospitalBooking, myAppointments, bookAppointment, bedBooking);
            replaceContent(createHospitalBookingContent());
        });

        bedBooking.setOnAction(e -> {
            setActiveTab(bedBooking, myAppointments, bookAppointment, hospitalBooking);
            replaceContent(createBedBookingContent());
        });

        return tabs;
    }

    private Button createTabButton(String text, boolean active) {
        Button button = new Button(text);
        button.setStyle(active
                ? "-fx-background-color: transparent;-fx-text-fill: #E84A87;-fx-font-size: 14px;-fx-font-weight: bold;-fx-border-color: transparent transparent #E84A87 transparent;-fx-border-width: 0 0 3 0;-fx-padding: 8px 4px;"
                : "-fx-background-color: transparent;-fx-text-fill: #666680;-fx-font-size: 14px;-fx-border-color: transparent;-fx-padding: 8px 4px;");
        return button;
    }

    private void setActiveTab(Button active, Button... others) {
        active.setStyle("-fx-background-color: transparent;-fx-text-fill: #E84A87;-fx-font-size: 14px;-fx-font-weight: bold;-fx-border-color: transparent transparent #E84A87 transparent;-fx-border-width: 0 0 3 0;-fx-padding: 8px 4px;");
        for (Button other : others) {
            other.setStyle("-fx-background-color: transparent;-fx-text-fill: #666680;-fx-font-size: 14px;-fx-border-color: transparent;-fx-padding: 8px 4px;");
        }
    }

    private void replaceContent(VBox newContent) {
        if (mainContent != null && mainContent.getChildren().size() >= 3) {
            mainContent.getChildren().set(2, newContent);
        }
    }

    private VBox createMyAppointmentsContent() {
        VBox content = new VBox();
        content.setSpacing(20);

        HBox layout = new HBox();
        layout.setSpacing(20);

        VBox left = new VBox();
        left.setSpacing(15);
        HBox.setHgrow(left, Priority.ALWAYS);

        VBox right = createBookQuickCard();
        right.setPrefWidth(390);
        right.setMinWidth(350);

        VBox appointmentsCard = createWhiteCard();

        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);

        VBox headingText = new VBox();

        Label title = new Label("My Appointments");
        title.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label("Your upcoming and previous appointments");
        subtitle.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        headingText.getChildren().addAll(title, subtitle);
        HBox.setHgrow(headingText, Priority.ALWAYS);

        Button viewAll = createSmallPinkButton("View All");
        heading.getChildren().addAll(headingText, viewAll);

        HBox filters = new HBox();
        filters.setSpacing(10);

        Button upcoming = createFilterButton("Upcoming", true);
        Button completed = createFilterButton("Completed", false);
        Button cancelled = createFilterButton("Cancelled", false);

        filters.getChildren().addAll(upcoming, completed, cancelled);

        VBox appointmentList = new VBox();
        appointmentList.setSpacing(12);

        showAppointments(appointmentList, "Upcoming");

        upcoming.setOnAction(e -> {
            setAppointmentFilterActive(upcoming, completed, cancelled);
            appointmentList.getChildren().clear();
            showAppointments(appointmentList, "Upcoming");
        });

        completed.setOnAction(e -> {
            setAppointmentFilterActive(completed, upcoming, cancelled);
            appointmentList.getChildren().clear();
            showAppointments(appointmentList, "Completed");
        });

        cancelled.setOnAction(e -> {
            setAppointmentFilterActive(cancelled, upcoming, completed);
            appointmentList.getChildren().clear();
            showAppointments(appointmentList, "Cancelled");
        });

        HBox urgent = createUrgentBooking();

        appointmentsCard.getChildren().addAll(heading, filters, appointmentList, urgent);
        left.getChildren().add(appointmentsCard);
        layout.getChildren().addAll(left, right);
        content.getChildren().add(layout);

        return content;
    }

    private void showAppointments(VBox appointmentList, String requiredStatus) {
        if (motherUid != null && !motherUid.trim().isEmpty()) {
            try {
                appointments.clear();

                List<DoctorAppointment> firebaseAppointments =
                        appointmentController.getAppointmentsForPatient(motherUid);

                if (firebaseAppointments != null) {
                    for (DoctorAppointment firebaseAppointment : firebaseAppointments) {
                        AppointmentData appointment =
                                convertFirebaseAppointment(firebaseAppointment);
                        if (appointment != null) {
                            appointments.add(appointment);
                        }
                    }
                }

                List<Appointment> hospitalAppointments =
                        hospitalAppointmentController.getAppointmentsByMother(motherUid);

                if (hospitalAppointments != null) {
                    for (Appointment firebaseAppointment : hospitalAppointments) {
                        AppointmentData appointment =
                                convertHospitalAppointment(firebaseAppointment);
                        if (appointment != null) {
                            appointments.add(appointment);
                        }
                    }
                }

            } catch (Exception ex) {
                System.out.println("[MOTHER APPOINTMENTS] Firebase load failed");
                ex.printStackTrace();
            }
        }

        boolean found = false;

        for (AppointmentData appointment : appointments) {
            String status = appointment.status == null ? "Pending" : appointment.status;

            if (requiredStatus.equals("Upcoming")) {
                if (status.equalsIgnoreCase("Upcoming")
                        || status.equalsIgnoreCase("Confirmed")
                        || status.equalsIgnoreCase("Pending")) {
                    appointmentList.getChildren().add(createAppointmentCard(appointment));
                    found = true;
                }
            } else if (requiredStatus.equals("Cancelled")) {
                if (status.equalsIgnoreCase("Cancelled")
                        || status.equalsIgnoreCase("Rejected")) {
                    appointmentList.getChildren().add(createAppointmentCard(appointment));
                    found = true;
                }
            } else if (status.equalsIgnoreCase(requiredStatus)) {
                appointmentList.getChildren().add(createAppointmentCard(appointment));
                found = true;
            }
        }

        if (!found) {
            Label empty = new Label("No " + requiredStatus.toLowerCase() + " appointments.");
            empty.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;-fx-padding: 15px;");
            appointmentList.getChildren().add(empty);
        }
    }

    private AppointmentData convertFirebaseAppointment(DoctorAppointment firebaseAppointment) {
        if (firebaseAppointment == null) return null;

        String speciality = firebaseAppointment.getType();
        String doctor = "Dr. Anjali Mehta";
        String image = "doctor1.png";
        String clinic = "CarePlus Women Clinic, Pune";

        if ("Nutritionist".equalsIgnoreCase(speciality)) {
            doctor = "Dr. Neha Kulkarni";
            image = "doctor2.png";
            clinic = "CarePlus Women Clinic, Pune";
        } else if ("Pediatrician".equalsIgnoreCase(speciality)) {
            doctor = "Dr. Rahul Deshmukh";
            image = "doctor3.png";
            clinic = "CarePlus Children Clinic, Pune";
        } else if ("Lactation Consultant".equalsIgnoreCase(speciality)) {
            doctor = "Dr. Pooja Iyer";
            image = "doctor4.png";
            clinic = "CarePlus Women Clinic, Pune";
        } else if ("General Physician".equalsIgnoreCase(speciality)) {
            doctor = "Dr. Simran Kaur";
            image = "doctor5.png";
            clinic = "CarePlus Women Clinic, Pune";
        }

        String status = firebaseAppointment.getStatus();
        if (status == null || status.trim().isEmpty()) status = "Pending";

        return new AppointmentData(
                firebaseAppointment.getAppointmentId(),
                doctor,
                speciality,
                image,
                firebaseAppointment.getDate(),
                firebaseAppointment.getTime(),
                clinic,
                status
        );
    }

    private AppointmentData convertHospitalAppointment(Appointment firebaseAppointment) {
        if (firebaseAppointment == null) return null;

        String hospital = firebaseAppointment.getHospital();
        if (hospital == null || hospital.trim().isEmpty()) hospital = "Hospital";

        String department = firebaseAppointment.getDepartment();
        if (department == null || department.trim().isEmpty()) department = "General";

        String status = firebaseAppointment.getStatus();
        if (status == null || status.trim().isEmpty()) status = "Pending";

        String image = "hospital1.png";
        if (hospital.toLowerCase().contains("mothercare")) {
            image = "hospital2.png";
        } else if (hospital.toLowerCase().contains("lifespring")) {
            image = "hospital3.png";
        }

        return new AppointmentData(
                firebaseAppointment.getNumber(),
                hospital,
                "Hospital Appointment",
                image,
                firebaseAppointment.getDate(),
                firebaseAppointment.getTime(),
                hospital,
                status,
                true,
                hospital,
                department
        );
    }

    private HBox createAppointmentCard(AppointmentData appointment) {
        HBox card = new HBox();
        card.setSpacing(15);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #E8DCEB;-fx-border-radius: 14;-fx-background-radius: 14;");

        StackPane image = appointment.hospitalAppointment
                ? createHospitalImage(appointment.imageName, 68, 68)
                : createDoctorImage(appointment.imageName, 68, 68);

        VBox information = new VBox();
        information.setSpacing(5);

        Label name = new Label(appointment.doctor);
        name.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label spec = new Label(
                appointment.hospitalAppointment
                        ? "Department: " + appointment.department
                        : appointment.speciality
        );
        spec.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        Label dateLabel = new Label("📅  " + appointment.date + "   •   " + appointment.time);
        dateLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #55556D;");

        Label locationLabel = new Label("⌖  " + appointment.location);
        locationLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #77778D;");

        information.getChildren().addAll(name, spec, dateLabel, locationLabel);
        HBox.setHgrow(information, Priority.ALWAYS);

        VBox right = new VBox();
        right.setSpacing(8);
        right.setAlignment(Pos.CENTER_RIGHT);

        Label statusLabel = new Label(appointment.status);
        styleStatus(statusLabel, appointment.status);

        String buttonText;
        if (!appointment.hospitalAppointment && appointment.status.equalsIgnoreCase("Pending")) {
            buttonText = "Reschedule";
        } else {
            buttonText = "View Details";
        }

        Button action = createOutlinePinkButton(buttonText);
        action.setOnAction(e -> {
            if (!appointment.hospitalAppointment
                    && appointment.status.equalsIgnoreCase("Pending")) {
                showRescheduleDialog(appointment);
            } else {
                showAppointmentDetails(appointment);
            }
        });

        right.getChildren().addAll(statusLabel, action);
        card.getChildren().addAll(image, information, right);

        return card;
    }

    private void showAppointmentDetails(AppointmentData appointment) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Appointment Details");
        dialog.setHeaderText(
                appointment.hospitalAppointment
                        ? "Hospital Appointment Details"
                        : "Appointment with " + appointment.doctor
        );

        VBox box = new VBox();
        box.setSpacing(12);
        box.setPadding(new Insets(20));

        if (appointment.hospitalAppointment) {
            Label hospital = new Label("Hospital: " + appointment.hospital);
            Label department = new Label("Department: " + appointment.department);
            Label patient = new Label("Patient: " + motherName);
            Label date = new Label("Date: " + appointment.date);
            Label time = new Label("Time: " + appointment.time);
            Label location = new Label("Location: " + appointment.location);
            Label status = new Label("Status: " + appointment.status);

            for (Label label : new Label[]{hospital, department, patient, date, time, location, status}) {
                styleDialogLabel(label);
            }

            box.getChildren().addAll(hospital, department, patient, date, time, location, status);
        } else {
            Label doctor = new Label("Doctor: " + appointment.doctor);
            Label speciality = new Label("Specialization: " + appointment.speciality);
            Label date = new Label("Date: " + appointment.date);
            Label time = new Label("Time: " + appointment.time);
            Label clinic = new Label("Clinic: " + appointment.location);
            Label status = new Label("Status: " + appointment.status);
            Label fee = new Label("Consultation Fee: ₹800");

            styleDialogLabel(doctor);
            styleDialogLabel(speciality);
            styleDialogLabel(date);
            styleDialogLabel(time);
            styleDialogLabel(clinic);
            styleDialogLabel(status);
            styleDialogLabel(fee);

            box.getChildren().addAll(doctor, speciality, date, time, clinic, status, fee);
        }

        dialog.getDialogPane().setContent(box);
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Close"));
        dialog.showAndWait();
    }

    private void showRescheduleDialog(AppointmentData appointment) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Reschedule Appointment");
        dialog.setHeaderText("Reschedule with " + appointment.doctor);

        VBox box = new VBox();
        box.setSpacing(15);
        box.setPadding(new Insets(20));

        Label doctor = new Label("Doctor: " + appointment.doctor);
        doctor.setStyle("-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label dateLabel = new Label("Select New Date");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setMaxWidth(Double.MAX_VALUE);

        Label timeLabel = new Label("Select New Time");
        ComboBox<String> timeBox = new ComboBox<>();
        timeBox.getItems().addAll(
                "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM",
                "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM"
        );
        timeBox.setValue("10:00 AM");
        timeBox.setMaxWidth(Double.MAX_VALUE);

        box.getChildren().addAll(doctor, dateLabel, datePicker, timeLabel, timeBox);
        dialog.getDialogPane().setContent(box);

        ButtonType reschedule = new ButtonType("Reschedule");
        ButtonType cancel = new ButtonType("Cancel");
        dialog.getDialogPane().getButtonTypes().addAll(reschedule, cancel);
        dialog.setResultConverter(button -> button);

        dialog.showAndWait().ifPresent(result -> {
            if (result == reschedule) {
                if (datePicker.getValue() == null) {
                    showAlert("Please select a date.");
                    return;
                }

                if (timeBox.getValue() == null) {
                    showAlert("Please select a time.");
                    return;
                }

                String formattedDate = datePicker.getValue().format(
                        DateTimeFormatter.ofPattern("dd MMM yyyy"));

                if (motherUid != null
                        && !motherUid.trim().isEmpty()
                        && appointment.appointmentId != null
                        && !appointment.appointmentId.trim().isEmpty()) {
                    try {
                        boolean updated = appointmentController.updateAppointment(
                                appointment.appointmentId,
                                motherUid,
                                formattedDate,
                                timeBox.getValue(),
                                motherName,
                                appointment.speciality,
                                "Pending",
                                "Pending"
                        );

                        if (!updated) {
                            showAlert("Unable to reschedule appointment.");
                            return;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showAlert("Unable to reschedule appointment.");
                        return;
                    }
                }

                appointment.date = formattedDate;
                appointment.time = timeBox.getValue();
                appointment.status = "Pending";

                showAlert(
                        "Appointment rescheduled successfully!\n\n" +
                        "Doctor: " + appointment.doctor +
                        "\nDate: " + formattedDate +
                        "\nTime: " + timeBox.getValue() +
                        "\n\nStatus: Pending"
                );

                replaceContent(createMyAppointmentsContent());
            }
        });
    }

    private VBox createBookQuickCard() {
        VBox card = createWhiteCard();

        Label title = new Label("Book an Appointment");
        title.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label("Find the right doctor for you");
        subtitle.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        TextField search = createSearchField("Search doctors...");

        ComboBox<String> specialty = new ComboBox<>();
        specialty.getItems().addAll(
                "All Specialties",
                "Obstetrician & Gynecologist",
                "Nutritionist",
                "Pediatrician",
                "Lactation Consultant",
                "General Physician"
        );
        specialty.setValue("All Specialties");
        specialty.setMaxWidth(Double.MAX_VALUE);

        Button book = createGradientButton("Book Appointment  →");
        book.setMaxWidth(Double.MAX_VALUE);
        book.setOnAction(e -> replaceContent(createBookAppointmentContent()));

        card.getChildren().addAll(title, subtitle, search, specialty, book);
        return card;
    }

    private HBox createUrgentBooking() {
        HBox box = new HBox();
        box.setSpacing(15);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #FFF0F6;-fx-border-color: #F5D1E1;-fx-border-radius: 14;-fx-background-radius: 14;");

        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
        icon.setSize("25");
        icon.setFill(Color.web(PINK));

        VBox text = new VBox();

        Label title = new Label("Need to see a doctor immediately?");
        title.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label description = new Label("Book an instant consultation with available doctors.");
        description.setStyle("-fx-font-size: 11px;-fx-text-fill: #77778D;");

        text.getChildren().addAll(title, description);
        HBox.setHgrow(text, Priority.ALWAYS);

        Button book = createGradientButton("Book Now");
        book.setOnAction(e -> replaceContent(createBookAppointmentContent()));

        box.getChildren().addAll(icon, text, book);
        return box;
    }

    private VBox createBookAppointmentContent() {
        VBox content = new VBox();
        content.setSpacing(20);

        HBox layout = new HBox();
        layout.setSpacing(20);

        VBox doctorsSection = createDoctorsSection();
        HBox.setHgrow(doctorsSection, Priority.ALWAYS);

        VBox bookingSection = createDoctorBookingSection();
        bookingSection.setPrefWidth(430);
        bookingSection.setMinWidth(400);

        layout.getChildren().addAll(doctorsSection, bookingSection);
        content.getChildren().add(layout);
        return content;
    }

    private VBox createDoctorsSection() {
        VBox card = createWhiteCard();

        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox();

        Label title = new Label("Find a Doctor");
        title.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label("Search doctors by specialty, location and rating");
        subtitle.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        titleBox.getChildren().addAll(title, subtitle);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        Button filter = createOutlinePinkButton("☷  Filter");
        heading.getChildren().addAll(titleBox, filter);

        HBox filters = new HBox();
        filters.setSpacing(10);

        TextField search = createSearchField("Search doctors...");
        HBox.setHgrow(search, Priority.ALWAYS);

        ComboBox<String> specialty = new ComboBox<>();
        specialty.getItems().addAll(
                "Specialty",
                "Obstetrician & Gynecologist",
                "Nutritionist",
                "Pediatrician",
                "Lactation Consultant",
                "General Physician"
        );
        specialty.setValue("Specialty");
        specialty.setPrefWidth(170);

        ComboBox<String> location = new ComboBox<>();
        location.getItems().addAll("Location", "Pune", "Mumbai", "Nashik", "Nagpur");
        location.setValue("Location");
        location.setPrefWidth(130);

        filters.getChildren().addAll(search, specialty, location);

        VBox doctors = new VBox();
        doctors.setSpacing(12);

        List<DoctorData> doctorList = createDoctorData();

        Runnable refreshDoctors = () -> {
            doctors.getChildren().clear();

            String query = search.getText().trim().toLowerCase();
            String selectedSpecialty = specialty.getValue();
            String selectedLocation = location.getValue();

            boolean found = false;

            for (DoctorData doctor : doctorList) {
                boolean nameMatch = query.isEmpty()
                        || doctor.name.toLowerCase().contains(query)
                        || doctor.speciality.toLowerCase().contains(query);

                boolean specialityMatch = selectedSpecialty.equals("Specialty")
                        || doctor.speciality.equals(selectedSpecialty);

                boolean locationMatch = selectedLocation.equals("Location")
                        || doctor.location.equals(selectedLocation);

                if (nameMatch && specialityMatch && locationMatch) {
                    doctors.getChildren().add(createDoctorListCard(doctor));
                    found = true;
                }
            }

            if (!found) {
                Label noDoctor = new Label("No doctors found.");
                noDoctor.setStyle("-fx-text-fill: #77778D;-fx-padding: 20px;");
                doctors.getChildren().add(noDoctor);
            }
        };

        search.textProperty().addListener((obs, oldValue, newValue) -> refreshDoctors.run());
        specialty.valueProperty().addListener((obs, oldValue, newValue) -> refreshDoctors.run());
        location.valueProperty().addListener((obs, oldValue, newValue) -> refreshDoctors.run());

        refreshDoctors.run();

        Button more = createOutlinePinkButton("View More Doctors  ↓");
        more.setMaxWidth(Double.MAX_VALUE);

        card.getChildren().addAll(heading, filters, doctors, more);
        return card;
    }

    private List<DoctorData> createDoctorData() {
        List<DoctorData> list = new ArrayList<>();

        list.add(new DoctorData("Dr. Anjali Mehta", "Obstetrician & Gynecologist",
                "doctor1.png", "4.8", "256 Reviews", "Pune"));

        list.add(new DoctorData("Dr. Neha Kulkarni", "Nutritionist",
                "doctor2.png", "4.7", "189 Reviews", "Pune"));

        list.add(new DoctorData("Dr. Rahul Deshmukh", "Pediatrician",
                "doctor3.png", "4.6", "312 Reviews", "Pune"));

        list.add(new DoctorData("Dr. Pooja Iyer", "Lactation Consultant",
                "doctor4.png", "4.9", "162 Reviews", "Pune"));

        list.add(new DoctorData("Dr. Simran Kaur", "General Physician",
                "doctor5.png", "4.5", "205 Reviews", "Pune"));

        return list;
    }

    private HBox createDoctorListCard(DoctorData doctor) {
        HBox card = new HBox();
        card.setSpacing(12);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(12));
        card.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #E8DFEA;-fx-border-radius: 13;-fx-background-radius: 13;");

        StackPane image = createDoctorImage(doctor.imageName, 58, 58);

        VBox text = new VBox();
        text.setSpacing(4);

        Label doctorName = new Label(doctor.name);
        doctorName.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label specialityLabel = new Label(doctor.speciality);
        specialityLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #77778D;");

        Label ratingLabel = new Label("★ " + doctor.rating + "   (" + doctor.reviews + ")");
        ratingLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #E7A51A;");

        text.getChildren().addAll(doctorName, specialityLabel, ratingLabel);
        HBox.setHgrow(text, Priority.ALWAYS);

        Button select = createOutlinePinkButton("Select");
        select.setOnAction(e -> {
            selectedDoctor = doctor.name;
            selectedSpeciality = doctor.speciality;
            selectedImage = doctor.imageName;
            selectedRating = doctor.rating;
            selectedReviews = doctor.reviews;

            selectedClinic = doctor.speciality.equals("Pediatrician")
                    ? "CarePlus Children Clinic, " + doctor.location
                    : "CarePlus Women Clinic, " + doctor.location;

            selectedFee = "₹800";
            replaceContent(createBookAppointmentContent());
        });

        card.getChildren().addAll(image, text, select);
        return card;
    }

    private VBox createDoctorBookingSection() {
        VBox card = createWhiteCard();

        Label title = new Label("Book an Appointment");
        title.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        HBox doctor = new HBox();
        doctor.setSpacing(12);
        doctor.setAlignment(Pos.CENTER_LEFT);

        StackPane image = createDoctorImage(selectedImage, 65, 65);

        VBox doctorInfo = new VBox();
        doctorInfo.setSpacing(4);

        Label doctorName = new Label(selectedDoctor);
        doctorName.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label speciality = new Label(selectedSpeciality);
        speciality.setStyle("-fx-font-size: 11px;-fx-text-fill: #77778D;");

        Label rating = new Label("★ " + selectedRating + "   (" + selectedReviews + ")");
        rating.setStyle("-fx-font-size: 11px;-fx-text-fill: #E7A51A;");

        doctorInfo.getChildren().addAll(doctorName, speciality, rating);
        doctor.getChildren().addAll(image, doctorInfo);

        Label clinic = new Label("⌖  " + selectedClinic);
        clinic.setStyle("-fx-font-size: 12px;-fx-text-fill: #55556D;");

        Label fee = new Label("Consultation Fee  " + selectedFee);
        fee.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #E84A87;");

        Label dateTitle = new Label("Select Date");
        dateTitle.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        HBox dates = new HBox();
        dates.setSpacing(7);

        LocalDate today = LocalDate.now();
        final LocalDate[] selectedDate = {today};

        for (int i = 0; i < 5; i++) {
            LocalDate date = today.plusDays(i);

            Button dateButton = new Button(
                    i == 0
                            ? "Today\n" + date.format(DateTimeFormatter.ofPattern("dd MMM"))
                            : date.format(DateTimeFormatter.ofPattern("EEE\ndd MMM"))
            );

            dateButton.setPrefWidth(68);
            dateButton.setPrefHeight(55);

            final LocalDate currentDate = date;
            styleDateButton(dateButton, i == 0);

            dateButton.setOnAction(e -> {
                selectedDate[0] = currentDate;

                for (Node node : dates.getChildren()) {
                    if (node instanceof Button) styleDateButton((Button) node, false);
                }

                styleDateButton(dateButton, true);
            });

            dates.getChildren().add(dateButton);
        }

        Label timeTitle = new Label("Available Time Slots");
        timeTitle.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        GridPane times = new GridPane();
        times.setHgap(8);
        times.setVgap(8);

        String[] slots = {
                "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM",
                "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM"
        };

        final String[] selectedTime = {null};

        for (int i = 0; i < slots.length; i++) {
            Button time = new Button(slots[i]);
            time.setPrefWidth(72);
            time.setPrefHeight(35);
            styleTimeButton(time, false);

            final String currentTime = slots[i];

            time.setOnAction(e -> {
                selectedTime[0] = currentTime;

                for (Node node : times.getChildren()) {
                    if (node instanceof Button) styleTimeButton((Button) node, false);
                }

                styleTimeButton(time, true);
            });

            times.add(time, i % 5, i / 5);
        }

        Button confirm = createGradientButton("Confirm Appointment");
        confirm.setMaxWidth(Double.MAX_VALUE);

        confirm.setOnAction(e -> {
            if (selectedDate[0] == null) {
                showAlert("Please select a date.");
                return;
            }

            if (selectedTime[0] == null) {
                showAlert("Please select a time slot.");
                return;
            }

            String formattedDate = selectedDate[0].format(
                    DateTimeFormatter.ofPattern("dd MMM yyyy"));

            if (motherUid != null && !motherUid.trim().isEmpty()) {
                try {
                    DoctorAppointment savedAppointment =
                            appointmentController.addAppointment(
                                    "D001",
                                    motherUid,
                                    formattedDate,
                                    selectedTime[0],
                                    motherName,
                                    selectedSpeciality,
                                    "Pending",
                                    "Pending"
                            );

                    if (savedAppointment == null) {
                        showAlert("Unable to book appointment.\nPlease try again.");
                        return;
                    }

                    AppointmentData newAppointment =
                            convertFirebaseAppointment(savedAppointment);

                    if (newAppointment != null) appointments.add(0, newAppointment);

                    showAlert(
                            "Appointment booked successfully!\n\n" +
                            "Doctor: " + selectedDoctor +
                            "\nDate: " + formattedDate +
                            "\nTime: " + selectedTime[0] +
                            "\nStatus: Pending"
                    );

                    replaceContent(createMyAppointmentsContent());
                    return;

                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert("Unable to book appointment.\n\nPlease check Firebase connection.");
                    return;
                }
            }

            AppointmentData newAppointment = new AppointmentData(
                    null,
                    selectedDoctor,
                    selectedSpeciality,
                    selectedImage,
                    formattedDate,
                    selectedTime[0],
                    selectedClinic,
                    "Pending"
            );

            appointments.add(0, newAppointment);

            showAlert(
                    "Appointment booked successfully!\n\n" +
                    "Doctor: " + selectedDoctor +
                    "\nDate: " + formattedDate +
                    "\nTime: " + selectedTime[0] +
                    "\nStatus: Pending"
            );

            replaceContent(createMyAppointmentsContent());
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
                confirm
        );

        return card;
    }

    private VBox createHospitalBookingContent() {
        VBox content = new VBox();
        content.setSpacing(20);

        VBox searchCard = createWhiteCard();

        Label title = new Label("Hospital Booking");
        title.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label(
                "Find hospitals, check facilities and book appointments."
        );
        subtitle.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        HBox searchRow = new HBox();
        searchRow.setSpacing(10);

        TextField search = createSearchField("Search hospitals...");
        HBox.setHgrow(search, Priority.ALWAYS);

        ComboBox<String> location = new ComboBox<>();
        location.getItems().addAll("Location", "Pune", "Mumbai", "Nashik", "Nagpur");
        location.setValue("Location");
        location.setPrefWidth(150);

        ComboBox<String> department = new ComboBox<>();
        department.getItems().addAll("Department", "Maternity", "Gynecology", "Pediatrics", "General");
        department.setValue("Department");
        department.setPrefWidth(160);

        Button searchButton = createGradientButton("Search");

        searchRow.getChildren().addAll(search, location, department, searchButton);
        searchCard.getChildren().addAll(title, subtitle, searchRow);

        HBox hospitals = new HBox();
        hospitals.setSpacing(15);

        hospitals.getChildren().addAll(
                createHospitalCard(
                        "CarePlus Women Hospital",
                        "Pune",
                        "4.8",
                        "Maternity • Gynecology • NICU",
                        "hospital1.png"
                ),
                createHospitalCard(
                        "MotherCare Multispeciality",
                        "Pune",
                        "4.7",
                        "Maternity • Pediatrics • Surgery",
                        "hospital2.png"
                ),
                createHospitalCard(
                        "LifeSpring Women & Child",
                        "Pune",
                        "4.6",
                        "Gynecology • Pediatrics",
                        "hospital3.png"
                )
        );

        content.getChildren().addAll(searchCard, hospitals);
        return content;
    }

    private VBox createBedBookingContent() {
        VBox content = new VBox();
        content.setSpacing(20);

        HBox layout = new HBox();
        layout.setSpacing(20);

        VBox requestCard = createWhiteCard();
        requestCard.setStyle("-fx-background-color: linear-gradient(to bottom right, #FFFFFF 0%, #FFF7FB 100%);-fx-background-radius: 18;-fx-border-color: #E8D8EE;-fx-border-radius: 18;");
        HBox.setHgrow(requestCard, Priority.ALWAYS);

        Label title = new Label("Bed Booking");
        title.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label subtitle = new Label("Request a hospital bed for your upcoming admission.");
        subtitle.setStyle("-fx-font-size: 12px;-fx-text-fill: #77778D;");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(12);

        Label patientLabel = new Label("Patient Name");
        Label hospitalLabel = new Label("Hospital");
        Label departmentLabel = new Label("Department");
        Label bedTypeLabel = new Label("Bed Type");
        Label checkinLabel = new Label("Check-in Date");
        Label checkoutLabel = new Label("Expected Checkout");

        styleDialogLabel(patientLabel);
        styleDialogLabel(hospitalLabel);
        styleDialogLabel(departmentLabel);
        styleDialogLabel(bedTypeLabel);
        styleDialogLabel(checkinLabel);
        styleDialogLabel(checkoutLabel);

        TextField patientName = createSearchField("Enter patient name");
        patientName.setPrefWidth(260);

        ComboBox<String> hospitalBox = new ComboBox<>();
        hospitalBox.getItems().addAll(
                "CarePlus Women Hospital",
                "MotherCare Multispeciality",
                "LifeSpring Women & Child"
        );
        hospitalBox.setValue("CarePlus Women Hospital");
        hospitalBox.setPrefWidth(260);
        hospitalBox.setPrefHeight(42);
        hospitalBox.setStyle("-fx-background-color: #FFF9FC;-fx-border-color: #E4D6E8;-fx-border-radius: 10;-fx-background-radius: 10;-fx-font-size: 12px;");

        ComboBox<String> departmentBox = new ComboBox<>();
        departmentBox.getItems().addAll("Maternity", "Gynecology", "Pediatrics", "General");
        departmentBox.setValue("Maternity");
        departmentBox.setPrefWidth(260);
        departmentBox.setPrefHeight(42);
        departmentBox.setStyle("-fx-background-color: #FFF9FC;-fx-border-color: #E4D6E8;-fx-border-radius: 10;-fx-background-radius: 10;-fx-font-size: 12px;");

        ComboBox<String> bedTypeBox = new ComboBox<>();
        bedTypeBox.getItems().addAll("General", "Semi-Private", "Private", "ICU");
        bedTypeBox.setValue("General");
        bedTypeBox.setPrefWidth(260);
        bedTypeBox.setPrefHeight(42);
        bedTypeBox.setStyle("-fx-background-color: #FFF9FC;-fx-border-color: #E4D6E8;-fx-border-radius: 10;-fx-background-radius: 10;-fx-font-size: 12px;");

        DatePicker checkinDate = new DatePicker(LocalDate.now());
        checkinDate.setPrefWidth(260);
        checkinDate.setPrefHeight(42);

        DatePicker checkoutDate = new DatePicker(LocalDate.now().plusDays(3));
        checkoutDate.setPrefWidth(260);
        checkoutDate.setPrefHeight(42);

        checkinDate.setStyle("-fx-background-color: #FFF9FC;-fx-border-color: #E4D6E8;-fx-border-radius: 10;-fx-background-radius: 10;");
        checkoutDate.setStyle("-fx-background-color: #FFF9FC;-fx-border-color: #E4D6E8;-fx-border-radius: 10;-fx-background-radius: 10;");

        form.add(patientLabel, 0, 0);
        form.add(patientName, 1, 0);
        form.add(hospitalLabel, 0, 1);
        form.add(hospitalBox, 1, 1);
        form.add(departmentLabel, 0, 2);
        form.add(departmentBox, 1, 2);
        form.add(bedTypeLabel, 0, 3);
        form.add(bedTypeBox, 1, 3);
        form.add(checkinLabel, 0, 4);
        form.add(checkinDate, 1, 4);
        form.add(checkoutLabel, 0, 5);
        form.add(checkoutDate, 1, 5);

        Button requestButton = createGradientButton("Request Bed Booking");
        requestButton.setMaxWidth(Double.MAX_VALUE);

        requestButton.setOnAction(e -> {
            if (patientName.getText().trim().isEmpty()) {
                showAlert("Please enter patient name.");
                return;
            }

            if (checkinDate.getValue() == null) {
                showAlert("Please select check-in date.");
                return;
            }

            if (checkoutDate.getValue() == null) {
                showAlert("Please select expected checkout date.");
                return;
            }

            if (checkoutDate.getValue().isBefore(checkinDate.getValue())) {
                showAlert("Expected checkout date cannot be before check-in date.");
                return;
            }

            String bookingId = "BED-" + System.currentTimeMillis();

            String checkin = checkinDate.getValue().format(
                    DateTimeFormatter.ofPattern("dd MMM yyyy"));

            String checkout = checkoutDate.getValue().format(
                    DateTimeFormatter.ofPattern("dd MMM yyyy"));

            BedBooking booking = new BedBooking(
                    String.valueOf(System.currentTimeMillis()),
                    bookingId,
                    patientName.getText().trim(),
                    hospitalBox.getValue(),
                    departmentBox.getValue(),
                    "Not Assigned",
                    bedTypeBox.getValue(),
                    checkin,
                    checkout,
                    "Pending"
            );

            if (motherUid != null && !motherUid.trim().isEmpty()) {
                bedBookingController.addBedBooking(
                        booking.getNumber(),
                        booking.getBookingID(),
                        motherUid,
                        booking.getPatientName(),
                        booking.getHospitalName(),
                        booking.getDepartment(),
                        booking.getBedNo(),
                        booking.getBedType(),
                        booking.getCheckinDate(),
                        booking.getExpectedCheckout(),
                        booking.getStatus()
                );
            } else {
                bedBookingController.addBedBooking(
                        booking.getNumber(),
                        booking.getBookingID(),
                        booking.getPatientName(),
                        booking.getHospitalName(),
                        booking.getDepartment(),
                        booking.getBedNo(),
                        booking.getBedType(),
                        booking.getCheckinDate(),
                        booking.getExpectedCheckout(),
                        booking.getStatus()
                );
            }

            showAlert(
                    "Bed booking request submitted successfully!\n\n" +
                    "Booking ID: " + booking.getBookingID() +
                    "\nHospital: " + hospitalBox.getValue() +
                    "\nPatient: " + booking.getPatientName() +
                    "\nDepartment: " + booking.getDepartment() +
                    "\nBed Type: " + booking.getBedType() +
                    "\nCheck-in: " + booking.getCheckinDate() +
                    "\nExpected Checkout: " + booking.getExpectedCheckout() +
                    "\nBed No: Not Assigned" +
                    "\nStatus: Pending"
            );
        });

        requestCard.getChildren().addAll(
                title, subtitle, createDivider(), form, requestButton
        );

        VBox infoCard = createWhiteCard();
        infoCard.setStyle("-fx-background-color: linear-gradient(to bottom right, #FAF5FF 0%, #FFFFFF 100%);-fx-background-radius: 18;-fx-border-color: #E3D5EC;-fx-border-radius: 18;");
        infoCard.setPrefWidth(330);
        infoCard.setMinWidth(300);

        Label infoTitle = new Label("How Bed Booking Works");
        infoTitle.setStyle("-fx-font-size: 17px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label step1 = new Label("1. Select hospital and department");
        Label step2 = new Label("2. Choose required bed type");
        Label step3 = new Label("3. Select admission dates");
        Label step4 = new Label("4. Send bed booking request");
        Label step5 = new Label("5. Hospital admin accepts or rejects the request");

        Label status = new Label("Status: Pending\nBed No: Not Assigned");

        for (Label label : new Label[]{step1, step2, step3, step4, step5}) {
            label.setWrapText(true);
            label.setStyle("-fx-font-size: 12px;-fx-text-fill: #55556D;-fx-padding: 4px 0;");
        }

        status.setStyle("-fx-background-color: #FFF7E6;-fx-text-fill: #D99000;-fx-background-radius: 12;-fx-padding: 10px;-fx-font-size: 11px;-fx-font-weight: bold;");

        infoCard.getChildren().addAll(
                infoTitle, createDivider(), step1, step2, step3,
                step4, step5, createDivider(), status
        );

        layout.getChildren().addAll(requestCard, infoCard);
        content.getChildren().add(layout);

        startBedBookingListener(status);
        return content;
    }

    private void startBedBookingListener(Label statusLabel) {
        if (bedBookingListener != null) {
            try {
                bedBookingListener.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }
            bedBookingListener = null;
        }

        if (motherUid == null || motherUid.trim().isEmpty()) {
            statusLabel.setText("Status: Pending\nBed No: Not Assigned");
            return;
        }

        try {
            bedBookingListener =
                    bedBookingController.listenToBedBookingsForMother(
                            motherUid,
                            bookings -> {

                                if (bookings == null || bookings.isEmpty()) {
                                    Platform.runLater(() -> {
                                        statusLabel.setText("Status: No Booking\nBed No: Not Assigned");
                                        statusLabel.setStyle("-fx-background-color: #FFF7E6;-fx-text-fill: #D99000;-fx-background-radius: 12;-fx-padding: 10px;-fx-font-size: 11px;-fx-font-weight: bold;");
                                    });
                                    return;
                                }

                                BedBooking latestBooking = bookings.get(0);
                                long latestNumber = Long.MIN_VALUE;

                                for (BedBooking booking : bookings) {
                                    if (booking == null) continue;

                                    try {
                                        String number = booking.getNumber();

                                        if (number != null && !number.trim().isEmpty()) {
                                            long currentNumber = Long.parseLong(number);

                                            if (currentNumber > latestNumber) {
                                                latestNumber = currentNumber;
                                                latestBooking = booking;
                                            }
                                        }
                                    } catch (Exception ignored) {
                                    }
                                }

                                if (latestBooking == null) return;

                                String bookingStatus = latestBooking.getStatus();
                                String bedNo = latestBooking.getBedNo();

                                if (bookingStatus == null || bookingStatus.trim().isEmpty()) {
                                    bookingStatus = "Pending";
                                }

                                if (bedNo == null || bedNo.trim().isEmpty()) {
                                    bedNo = "Not Assigned";
                                }

                                final String finalStatus = bookingStatus;
                                final String finalBedNo = bedNo;

                                Platform.runLater(() -> {
                                    statusLabel.setText(
                                            "Status: " + finalStatus +
                                            "\nBed No: " + finalBedNo
                                    );

                                    if (finalStatus.equalsIgnoreCase("Confirmed")) {
                                        statusLabel.setStyle("-fx-background-color: #EAF9F1;-fx-text-fill: #29935C;-fx-background-radius: 12;-fx-padding: 10px;-fx-font-size: 11px;-fx-font-weight: bold;");
                                    } else if (finalStatus.equalsIgnoreCase("Rejected")) {
                                        statusLabel.setStyle("-fx-background-color: #FDECEC;-fx-text-fill: #D95353;-fx-background-radius: 12;-fx-padding: 10px;-fx-font-size: 11px;-fx-font-weight: bold;");
                                    } else {
                                        statusLabel.setStyle("-fx-background-color: #FFF7E6;-fx-text-fill: #D99000;-fx-background-radius: 12;-fx-padding: 10px;-fx-font-size: 11px;-fx-font-weight: bold;");
                                    }

                                    System.out.println(
                                            "[MOTHER BED BOOKING] Status = " +
                                            finalStatus +
                                            " | Bed No = " + finalBedNo
                                    );
                                });
                            }
                    );
        } catch (Exception ex) {
            System.out.println("[MOTHER BED BOOKING] Listener failed");
            ex.printStackTrace();
        }
    }

    private VBox createHospitalCard(
            String name,
            String location,
            String rating,
            String facilities,
            String imageName) {

        VBox card = createWhiteCard();
        card.setPrefWidth(300);
        card.setMinWidth(280);

        StackPane image = createHospitalImage(imageName, 260, 130);

        Label hospitalName = new Label(name);
        hospitalName.setWrapText(true);
        hospitalName.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-text-fill: #24234F;");

        Label locationLabel = new Label("⌖  " + location);
        locationLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #77778D;");

        Label ratingLabel = new Label("★ " + rating + "   Patients Recommend");
        ratingLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #E7A51A;");

        Label facilitiesLabel = new Label(facilities);
        facilitiesLabel.setWrapText(true);
        facilitiesLabel.setStyle("-fx-font-size: 11px;-fx-text-fill: #55556D;");

        Button book = createGradientButton("Book Hospital Appointment");
        book.setMaxWidth(Double.MAX_VALUE);

        Button details = createOutlinePinkButton("View Hospital Details");
        details.setMaxWidth(Double.MAX_VALUE);

        details.setOnAction(e -> showAlert(
                "Hospital Details\n\n" +
                "Hospital: " + name +
                "\nLocation: " + location +
                "\nRating: " + rating +
                "\nFacilities: " + facilities
        ));

        book.setOnAction(e ->
                showHospitalBookingDialog(
                        name,
                        location,
                        facilities
                )
        );

        card.getChildren().addAll(
                image,
                hospitalName,
                locationLabel,
                ratingLabel,
                facilitiesLabel,
                details,
                book
        );

        return card;
    }

    private void showHospitalBookingDialog(
            String hospital,
            String location,
            String facilities) {

        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Hospital Appointment");
        dialog.setHeaderText("Book Appointment at " + hospital);

        VBox box = new VBox();
        box.setSpacing(12);
        box.setPadding(new Insets(20));

        Label hospitalLabel = new Label("Hospital: " + hospital);
        Label locationLabel = new Label("Location: " + location);
        Label facilitiesLabel = new Label("Facilities: " + facilities);

        styleDialogLabel(hospitalLabel);
        styleDialogLabel(locationLabel);
        styleDialogLabel(facilitiesLabel);

        Label patientLabel = new Label("Patient Name");
        styleDialogLabel(patientLabel);

        TextField patientField = createSearchField("Enter patient name");
        patientField.setText(motherName);

        Label departmentLabel = new Label("Department");
        styleDialogLabel(departmentLabel);

        ComboBox<String> departmentBox = new ComboBox<>();
        departmentBox.getItems().addAll(
                "Maternity",
                "Gynecology",
                "Pediatrics",
                "General"
        );
        departmentBox.setValue("Maternity");
        departmentBox.setMaxWidth(Double.MAX_VALUE);

        Label dateLabel = new Label("Appointment Date");
        styleDialogLabel(dateLabel);

        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setMaxWidth(Double.MAX_VALUE);

        Label timeLabel = new Label("Appointment Time");
        styleDialogLabel(timeLabel);

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
                "06:00 PM"
        );
        timeBox.setValue("10:00 AM");
        timeBox.setMaxWidth(Double.MAX_VALUE);

        box.getChildren().addAll(
                hospitalLabel,
                locationLabel,
                facilitiesLabel,
                createDivider(),
                patientLabel,
                patientField,
                departmentLabel,
                departmentBox,
                dateLabel,
                datePicker,
                timeLabel,
                timeBox
        );

        dialog.getDialogPane().setContent(box);

        ButtonType bookButton = new ButtonType("Book Appointment");
        ButtonType cancelButton = new ButtonType("Cancel");

        dialog.getDialogPane().getButtonTypes().addAll(
                bookButton,
                cancelButton
        );

        dialog.setResultConverter(button -> button);

        dialog.showAndWait().ifPresent(result -> {
            if (result != bookButton) return;

            if (motherUid == null || motherUid.trim().isEmpty()) {
                showAlert("Mother account is not connected to Firebase.");
                return;
            }

            if (patientField.getText().trim().isEmpty()) {
                showAlert("Please enter patient name.");
                return;
            }

            if (departmentBox.getValue() == null) {
                showAlert("Please select department.");
                return;
            }

            if (datePicker.getValue() == null) {
                showAlert("Please select appointment date.");
                return;
            }

            if (timeBox.getValue() == null) {
                showAlert("Please select appointment time.");
                return;
            }

            String formattedDate = datePicker.getValue().format(
                    DateTimeFormatter.ofPattern("dd MMM yyyy"));

            String appointmentNumber = "HOSP-" + System.currentTimeMillis();
            try {

                // =====================================================
                // SAVE HOSPITAL APPOINTMENT TO FIREBASE
                // =====================================================
                hospitalAppointmentController.addHospitalAppointment(
                        appointmentNumber,
                        patientField.getText().trim(),
                        hospital,
                        formattedDate,
                        timeBox.getValue(),
                        departmentBox.getValue(),
                        motherUid,
                        "Pending"
                );
            
                // =====================================================
                // ADD LOCALLY SO IT CAN BE SHOWN IMMEDIATELY
                // =====================================================
                AppointmentData newAppointment =
                        new AppointmentData(
                                appointmentNumber,
                                hospital,
                                "Hospital Appointment",
                                hospital.equalsIgnoreCase("CarePlus Women Hospital")
                                        ? "hospital1.png"
                                        : hospital.equalsIgnoreCase("MotherCare Multispeciality")
                                                ? "hospital2.png"
                                                : "hospital3.png",
                                formattedDate,
                                timeBox.getValue(),
                                hospital + ", " + location,
                                "Pending",
                                true,
                                hospital,
                                departmentBox.getValue()
                        );
            
                appointments.add(0, newAppointment);
            
                showAlert(
                        "Hospital appointment booked successfully!\n\n" +
                        "Hospital: " + hospital +
                        "\nDepartment: " + departmentBox.getValue() +
                        "\nPatient: " + patientField.getText().trim() +
                        "\nDate: " + formattedDate +
                        "\nTime: " + timeBox.getValue() +
                        "\nStatus: Pending"
                );
            
                replaceContent(createMyAppointmentsContent());
            
            } catch (Exception ex) {
            
                ex.printStackTrace();
            
                showAlert(
                        "Unable to book hospital appointment.\n\n" +
                        "Please check Firebase connection."
                );
            }
        });
}
    private StackPane createDoctorImage(
            String imageName,
            double width,
            double height) {

        StackPane holder = new StackPane();

        holder.setPrefSize(width, height);
        holder.setMinSize(width, height);
        holder.setMaxSize(width, height);

        holder.setStyle(
                "-fx-background-color: #FFF1F7;" +
                "-fx-background-radius: 50%;"
        );

        String[] paths = {
                "assets/images/doctors/" + imageName,
                "assets/images/doctor/" + imageName,
                "assets/images/" + imageName
        };

        for (String path : paths) {
            var resource = getClass().getResource(path);

            if (resource != null) {
                Image image = new Image(resource.toExternalForm());
                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
                imageView.setPreserveRatio(false);

                Circle clip = new Circle(
                        width / 2,
                        height / 2,
                        Math.min(width, height) / 2
                );

                imageView.setClip(clip);
                holder.getChildren().add(imageView);

                return holder;
            }
        }

        FontAwesomeIconView doctorIcon =
                new FontAwesomeIconView(FontAwesomeIcon.USER_MD);

        doctorIcon.setSize(String.valueOf(
                Math.min(width, height) * 0.45
        ));

        doctorIcon.setFill(Color.web(PURPLE));
        holder.getChildren().add(doctorIcon);

        return holder;
    }

    private StackPane createHospitalImage(
            String imageName,
            double width,
            double height) {

        StackPane holder = new StackPane();

        holder.setPrefSize(width, height);
        holder.setStyle(
                "-fx-background-color: #F8F1FF;" +
                "-fx-background-radius: 12;"
        );

        String[] paths = {
                "/assets/images/hospitals/" + imageName,
                "/assets/images/hospital/" + imageName,
                "/assets/images/" + imageName
        };

        for (String path : paths) {
            var resource = getClass().getResource(path);

            if (resource != null) {
                ImageView imageView = new ImageView(
                        new Image(resource.toExternalForm())
                );

                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
                imageView.setPreserveRatio(true);

                holder.getChildren().add(imageView);
                return holder;
            }
        }

        FontAwesomeIconView icon =
                new FontAwesomeIconView(FontAwesomeIcon.HOSPITAL_ALT);

        icon.setSize("45");
        icon.setFill(Color.web(PURPLE));

        holder.getChildren().add(icon);
        return holder;
    }

    private TextField createSearchField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(42);

        field.setStyle(
                "-fx-background-color: #FFF9FC;" +
                "-fx-border-color: #E5D8EA;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 12px;"
        );

        return field;
    }

    private VBox createWhiteCard() {
        VBox card = new VBox();
        card.setSpacing(12);
        card.setPadding(new Insets(18));

        card.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFFFFF 0%, #FFF9FC 100%);" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E8DCEB;" +
                "-fx-border-radius: 18;"
        );

        return card;
    }

    private Label createDivider() {
        Label divider = new Label();

        divider.setPrefHeight(1);
        divider.setMaxWidth(Double.MAX_VALUE);

        divider.setStyle("-fx-background-color: #EEE7EF;");

        return divider;
    }

    private void styleDateButton(Button button, boolean active) {
        button.setStyle(
                active
                        ? "-fx-background-color: #FFF0F6;-fx-text-fill: #E84A87;-fx-border-color: #E84A87;-fx-border-radius: 10;-fx-background-radius: 10;-fx-font-size: 10px;-fx-font-weight: bold;"
                        : "-fx-background-color: white;-fx-text-fill: #55556D;-fx-border-color: #E6DFE8;-fx-border-radius: 10;-fx-background-radius: 10;-fx-font-size: 10px;"
        );
    }

    private void styleTimeButton(Button button, boolean active) {
        button.setStyle(
                active
                        ? "-fx-background-color: #FFF0F6;-fx-text-fill: #E84A87;-fx-border-color: #E84A87;-fx-border-radius: 8;-fx-background-radius: 8;-fx-font-size: 10px;-fx-font-weight: bold;"
                        : "-fx-background-color: white;-fx-text-fill: #55556D;-fx-border-color: #E5DFE8;-fx-border-radius: 8;-fx-background-radius: 8;-fx-font-size: 10px;"
        );
    }

    private Button createGradientButton(String text) {
        Button button = new Button(text);

        button.setAlignment(Pos.CENTER);

        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 9px 18px;"
        );

        return button;
    }

    private Button createOutlinePinkButton(String text) {
        Button button = new Button(text);

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #E84A87;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #E4B6CD;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 7px 12px;"
        );

        return button;
    }

    private Button createSmallPinkButton(String text) {
        Button button = new Button(text);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #9B4DCC;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: transparent;"
        );

        return button;
    }

    private Button createFilterButton(String text, boolean active) {
        Button button = new Button(text);
        button.setPrefWidth(105);

        button.setStyle(
                active
                        ? "-fx-background-color: #FFF0F6;-fx-text-fill: #E84A87;-fx-border-color: #F0A9C8;-fx-border-radius: 9;-fx-background-radius: 9;-fx-font-size: 11px;-fx-font-weight: bold;"
                        : "-fx-background-color: white;-fx-text-fill: #666680;-fx-border-color: #E6DFE8;-fx-border-radius: 9;-fx-background-radius: 9;-fx-font-size: 11px;"
        );

        return button;
    }

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
                "-fx-font-weight: bold;"
        );

        other1.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #666680;" +
                "-fx-border-color: #E6DFE8;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 11px;"
        );

        other2.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #666680;" +
                "-fx-border-color: #E6DFE8;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 11px;"
        );
    }

    private void styleStatus(Label label, String status) {
        if (status == null) status = "Pending";

        if (status.equalsIgnoreCase("Cancelled")
                || status.equalsIgnoreCase("Rejected")) {

            label.setStyle(
                    "-fx-background-color: #FDECEC;" +
                    "-fx-text-fill: #D95353;" +
                    "-fx-background-radius: 15;" +
                    "-fx-padding: 5px 12px;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;"
            );

        } else if (status.equalsIgnoreCase("Completed")
                || status.equalsIgnoreCase("Confirmed")) {

            label.setStyle(
                    "-fx-background-color: #EAF9F1;" +
                    "-fx-text-fill: #29935C;" +
                    "-fx-background-radius: 15;" +
                    "-fx-padding: 5px 12px;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;"
            );

        } else {
            label.setStyle(
                    "-fx-background-color: #FFF7E6;" +
                    "-fx-text-fill: #D99000;" +
                    "-fx-background-radius: 15;" +
                    "-fx-padding: 5px 12px;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;"
            );
        }
    }

    private void styleDialogLabel(Label label) {
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #24234F;"
        );
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("MaaCare AI");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private static class AppointmentData {
        String appointmentId;
        String doctor;
        String speciality;
        String imageName;
        String date;
        String time;
        String location;
        String status;

        boolean hospitalAppointment;
        String hospital;
        String department;

        AppointmentData(
                String appointmentId,
                String doctor,
                String speciality,
                String imageName,
                String date,
                String time,
                String location,
                String status) {

            this(
                    appointmentId,
                    doctor,
                    speciality,
                    imageName,
                    date,
                    time,
                    location,
                    status,
                    false,
                    null,
                    null
            );
        }

        AppointmentData(
                String appointmentId,
                String doctor,
                String speciality,
                String imageName,
                String date,
                String time,
                String location,
                String status,
                boolean hospitalAppointment,
                String hospital,
                String department) {

            this.appointmentId = appointmentId;
            this.doctor = doctor;
            this.speciality = speciality;
            this.imageName = imageName;
            this.date = date;
            this.time = time;
            this.location = location;
            this.status = status;
            this.hospitalAppointment = hospitalAppointment;
            this.hospital = hospital;
            this.department = department;
        }
    }

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
