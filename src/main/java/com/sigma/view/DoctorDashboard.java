package com.sigma.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DoctorDashboard extends Application {
        public static Stage DoctorDashboardStage;

        private Scene DoctorDashboardScene;

        @Override
        public void start(Stage stage) {
                DoctorDashboardStage = stage;

                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color: #F8F7FC;");

                VBox sidebar = new VBox(15);
                sidebar.setPrefWidth(245);
                sidebar.setPadding(new Insets(25, 15, 20, 15));

                sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #d30d91, #d30d91);"
                                + "-fx-background-radius: 0 18 18 0;");

                Image logo = new Image("/assets/images/logo/logo.png");
                ImageView logoImageView = new ImageView(logo);
                logoImageView.setFitHeight(200);
                logoImageView.setFitWidth(200);

                Button dashboardBtn = createSideButton("⌂", "Dashboard", true);
                Button appointmentsBtn = createSideButton("▣", "Appointments", false);
                Button patientsBtn = createSideButton("♙", "Patients", false);
                Button reportsBtn = createSideButton("▦", "Reports", false);
                Button settingsBtn = createSideButton("⚙", "Settings", false);
                Button logoutBtn = createSideButton("⇥", "Logout", false);

                appointmentsBtn.setOnAction(event -> {
                        System.out.println("Go TO Appointment Page");
                        DoctorAppointments appointments = new DoctorAppointments();
                        Runnable callBackToDashboard = () -> {
                                DoctorDashboardStage.setScene(DoctorDashboardScene);

                        };

                        DoctorDashboardStage.setScene(appointments.getAppointmentPageScene(callBackToDashboard));
                });

                Region spacer = new Region();
                VBox.setVgrow(spacer, Priority.ALWAYS);

                VBox doctorBox = new VBox(3);

                Label doctorName = new Label("👩🏻‍⚕️  Dr. Priya Sharma");
                doctorName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                doctorName.setTextFill(Color.WHITE);

                Label specialization = new Label("Gynecologist");
                specialization.setTextFill(Color.WHITE);

                Label online = new Label(" 🟢 Online");
                online.setTextFill(Color.WHITE);

                doctorBox.getChildren().addAll(doctorName, specialization, online);

                doctorBox.setPadding(new Insets(15));
                doctorBox.setStyle("-fx-background-color: rgba(255,255,255,0.12);" + "-fx-background-radius: 15;");

                sidebar.getChildren().addAll(logoImageView, dashboardBtn, appointmentsBtn, patientsBtn, reportsBtn,
                                settingsBtn,
                                logoutBtn, spacer, doctorBox);

                root.setLeft(sidebar);

                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(30));

                BorderPane header = new BorderPane();

                VBox welcomeBox = new VBox(5);

                Label welcome = new Label("Welcome back, Dr. Priya Sharma 👋");
                welcome.setFont(Font.font("Arial", FontWeight.BOLD, 27));
                welcome.setTextFill(Color.web("#222222"));

                Label subtitle = new Label("Here's what's happening with your patients today.");
                subtitle.setFont(Font.font("Arial", 14));
                subtitle.setTextFill(Color.GRAY);

                welcomeBox.getChildren().addAll(welcome, subtitle);

                VBox dateBox = new VBox(3);
                dateBox.setAlignment(Pos.CENTER_RIGHT);

                Label notification = new Label("🔔  3");
                notification.setFont(Font.font(18));

                Label date = new Label("12 May 2025");
                date.setFont(Font.font("Arial", FontWeight.BOLD, 13));

                Label day = new Label("Monday");
                day.setTextFill(Color.GRAY);

                dateBox.getChildren().addAll(notification, date, day);

                header.setLeft(welcomeBox);
                header.setRight(dateBox);

                HBox cards = new HBox(18);

                VBox appointmentsCard = createCard("📅", "Today's Appointments", "12", "#c66044");

                VBox patientsCard = createCard("👥", "Total Patients", "248", "#14b665");

                VBox reportsCard = createCard("📄", "Pending Reports", "7", "#d09d11");

                cards.getChildren().addAll(appointmentsCard, patientsCard, reportsCard);

                for (Node card : cards.getChildren()) {
                        HBox.setHgrow(card, Priority.ALWAYS);
                }

                HBox lowerSection = new HBox(20);

                VBox scheduleBox = createSchedule();

                HBox.setHgrow(scheduleBox, Priority.ALWAYS);

                lowerSection.getChildren().addAll(scheduleBox);

                mainContent.getChildren().addAll(header, cards, lowerSection);

                VBox.setVgrow(lowerSection, Priority.ALWAYS);

                root.setCenter(mainContent);

                DoctorDashboardScene = new Scene(root, 1400, 850);

                DoctorDashboardStage.setTitle("MaaCareAI - Doctor Dashboard");
                DoctorDashboardStage.setScene(DoctorDashboardScene);
                DoctorDashboardStage.show();
        }

        private Button createSideButton(String icon, String text, boolean active) {

                Button button = new Button(icon + "    " + text);

                button.setMaxWidth(Double.MAX_VALUE);
                button.setAlignment(Pos.CENTER_LEFT);

                button.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                if (active) {

                        button.setStyle("-fx-background-color: #754CE0; -fx-background-radius: 10; -fx-text-fill: white; -fx-padding: 13 15;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 13 15;");

                        button.setOnMouseClicked(e -> button.setStyle(
                                        "-fx-background-color: #7046D2; -fx-background-radius: 10; -fx-text-fill: white; -fx-padding: 13 15;"));

                        button.setOnMouseExited(e -> button.setStyle(
                                        "-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 13 15;"));
                }

                return button;
        }

        private VBox createCard(
                        String icon,
                        String title,
                        String value,
                        String background) {

                VBox card = new VBox(12);

                card.setPadding(new Insets(20));
                card.setPrefHeight(145);

                card.setStyle(
                                "-fx-background-color: " + background + "; -fx-background-radius: 15;");

                Label iconLabel = new Label(icon);
                iconLabel.setFont(Font.font(25));

                Label titleLabel = new Label(title);
                titleLabel.setFont(
                                Font.font("Arial", FontWeight.BOLD, 14));

                Label valueLabel = new Label(value);
                valueLabel.setFont(
                                Font.font("Arial", FontWeight.BOLD, 30));

                Label viewAll = new Label("View all");
                viewAll.setTextFill(Color.web("#4B2BB8"));
                viewAll.setFont(Font.font("Arial", FontWeight.BOLD, 13));

                card.getChildren().addAll(iconLabel, titleLabel, valueLabel, viewAll);

                return card;
        }

        private VBox createSchedule() {

                VBox box = new VBox(15);

                box.setPadding(new Insets(20));

                box.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: #E5E2EC; -fx-border-radius: 15;");

                HBox heading = new HBox();

                Label title = new Label("Today's Schedule");

                title.setFont(Font.font("Arial", FontWeight.BOLD, 17));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label calendar = new Label("View Calendar");

                calendar.setTextFill(Color.web("#4B2BB8"));
                calendar.setFont(
                                Font.font("Arial", FontWeight.BOLD, 13));

                heading.getChildren().addAll(
                                title,
                                spacer,
                                calendar);

                box.getChildren().add(heading);

                box.getChildren().add(
                                scheduleRow("09:30 AM", "Anjali Patil", "Regular Checkup"));

                box.getChildren().add(
                                scheduleRow("10:15 AM", "Sneha Kulkarni",
                                                "Pregnancy Consultation"));

                box.getChildren().add(
                                scheduleRow("11:00 AM", "Pooja Deshmukh", "Follow-up"));

                box.getChildren().add(
                                scheduleRow("12:00 PM", "Neha Jadhav",
                                                "Ultrasound Report"));

                box.getChildren().add(
                                scheduleRow("01:00 PM", "Lunch Break", ""));

                return box;
        }

        private HBox scheduleRow(
                        String time,
                        String name,
                        String purpose) {

                HBox row = new HBox(15);

                row.setPadding(new Insets(10, 5, 10, 5));
                row.setAlignment(Pos.CENTER_LEFT);

                Label timeLabel = new Label(time);
                timeLabel.setPrefWidth(90);
                timeLabel.setFont(
                                Font.font("Arial", FontWeight.BOLD, 13));

                VBox patient = new VBox(3);

                Label nameLabel = new Label(name);
                nameLabel.setFont(
                                Font.font("Arial", FontWeight.BOLD, 13));

                Label purposeLabel = new Label(purpose);
                purposeLabel.setTextFill(Color.GRAY);

                patient.getChildren().addAll(nameLabel, purposeLabel);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label status = new Label(name.equals("Lunch Break") ? "Break" : "Upcoming");

                status.setStyle(
                                "-fx-background-color: #EEF5FF; -fx-background-radius: 15; -fx-padding: 5 10; -fx-text-fill: #4285D4;");

                row.getChildren().addAll(timeLabel, patient, spacer, status);

                return row;
        }

        public static void main(String[] args) {
                launch(args);
        }
}