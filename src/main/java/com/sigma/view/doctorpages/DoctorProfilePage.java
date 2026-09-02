package com.sigma.view.doctorpages;

import com.google.cloud.firestore.Firestore;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.controller.doctorController.DoctorProfileController;
import com.sigma.model.DoctorModel.DoctorProfileModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorProfilePage {

        // =========================================================
        // STAGE
        // =========================================================

        private final Stage dashboardStage;

        // =========================================================
        // FIREBASE
        // =========================================================

        private final Firestore db;
        private final String doctorUid;
        private final DoctorProfileController controller;

        // =========================================================
        // COLORS - LIGHT PURPLE THEME
        // =========================================================

        private static final String PINK = "#E84A87";
        private static final String DARK_PINK = "#D93678";

        private static final String PURPLE = "#9B4DCC";
        private static final String LIGHT_PURPLE = "#F3ECFF";

        private static final String DARK_TEXT = "#24234F";
        private static final String SECONDARY_TEXT = "#77778D";

        private static final String BORDER = "#E7DCE8";
        private static final String WHITE = "#FFFFFF";

        // =========================================================
        // TEXT FIELDS
        // =========================================================

        private TextField firstNameField;
        private TextField lastNameField;
        private TextField genderField;
        private TextField dobField;

        private TextField phoneField;
        private TextField emailField;
        private TextField addressField;

        private TextField specializationField;
        private TextField qualificationField;
        private TextField experienceField;
        private TextField medicalLicenseField;

        private TextField clinicNameField;
        private TextField clinicAddressField;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public DoctorProfilePage(
                        Stage dashboardStage,
                        String doctorUid) {

                this.dashboardStage = dashboardStage;
                this.doctorUid = doctorUid;

                this.db = FirebaseConfig.getFirestore();

                this.controller = new DoctorProfileController(
                                db,
                                doctorUid);
        }

        // =========================================================
        // SHOW PROFILE PAGE
        // =========================================================

        public void show() {

                // ---------------------------------------------------------
                // CHECK STAGE
                // ---------------------------------------------------------

                if (dashboardStage == null) {

                        showError(
                                        "Dashboard stage is not available.\n"
                                                        + "Please open Doctor Dashboard again.");

                        return;
                }

                // ---------------------------------------------------------
                // CHECK UID
                // ---------------------------------------------------------

                if (doctorUid == null ||
                                doctorUid.trim().isEmpty()) {

                        showError(
                                        "Doctor UID is not available.\n\n"
                                                        + "Please login again.");

                        return;
                }

                // ---------------------------------------------------------
                // SAVE CURRENT STAGE STATE
                // ---------------------------------------------------------

                boolean wasMaximized = dashboardStage.isMaximized();

                double currentWidth = dashboardStage.getWidth();

                double currentHeight = dashboardStage.getHeight();

                double currentX = dashboardStage.getX();

                double currentY = dashboardStage.getY();

                // =========================================================
                // ROOT
                // =========================================================

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";");

                // =========================================================
                // HEADER
                // =========================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setSpacing(18);

                header.setPadding(
                                new Insets(
                                                18,
                                                30,
                                                18,
                                                30));

                header.setStyle(
                                "-fx-background-color: " +
                                                WHITE + ";" +

                                                "-fx-border-color: " +
                                                BORDER + ";" +

                                                "-fx-border-width: 0 0 1 0;");

                // ---------------------------------------------------------
                // BACK BUTTON
                // ---------------------------------------------------------

                Button backButton = new Button(
                                "←  Back to Dashboard");

                backButton.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";" +

                                                "-fx-text-fill: " +
                                                PURPLE + ";" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-font-weight: bold;" +

                                                "-fx-background-radius: 10;" +

                                                "-fx-padding: 10 18 10 18;" +

                                                "-fx-cursor: hand;");

                backButton.setOnAction(
                                e -> DoctorDashboard.showDashboard());

                // ---------------------------------------------------------
                // TITLE
                // ---------------------------------------------------------

                VBox titleBox = new VBox(4);

                Label title = new Label(
                                "Doctor Profile");

                title.setStyle(
                                "-fx-text-fill: " +
                                                DARK_TEXT + ";" +

                                                "-fx-font-size: 24px;" +

                                                "-fx-font-weight: bold;");

                Label subtitle = new Label(
                                "View and manage your professional information");

                subtitle.setStyle(
                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";" +

                                                "-fx-font-size: 13px;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                header.getChildren().addAll(
                                backButton,
                                titleBox);

                root.setTop(header);

                // =========================================================
                // MAIN CONTENT
                // =========================================================

                VBox mainContent = new VBox(20);

                mainContent.setAlignment(
                                Pos.TOP_CENTER);

                mainContent.setPadding(
                                new Insets(
                                                30,
                                                50,
                                                40,
                                                50));

                // =========================================================
                // PROFILE CARD
                // =========================================================

                VBox profileCard = new VBox(25);

                profileCard.setMaxWidth(1050);

                profileCard.setPadding(
                                new Insets(30));

                profileCard.setStyle(
                                "-fx-background-color: " +
                                                WHITE + ";" +

                                                "-fx-background-radius: 18;" +

                                                "-fx-border-color: " +
                                                BORDER + ";" +

                                                "-fx-border-radius: 18;" +

                                                "-fx-border-width: 1;");

                // =========================================================
                // PROFILE HEADER
                // =========================================================

                HBox profileHeader = new HBox(15);

                profileHeader.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane avatar = new StackPane();

                avatar.setPrefSize(
                                70,
                                70);

                avatar.setMinSize(
                                70,
                                70);

                avatar.setMaxSize(
                                70,
                                70);

                avatar.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";" +

                                                "-fx-background-radius: 50;");

                Label doctorIcon = new Label("👨‍⚕️");

                doctorIcon.setStyle(
                                "-fx-font-size: 34px;");

                avatar.getChildren().add(
                                doctorIcon);

                VBox profileTitleBox = new VBox(5);

                Label profileTitle = new Label(
                                "Professional Profile");

                profileTitle.setStyle(
                                "-fx-text-fill: " +
                                                DARK_TEXT + ";" +

                                                "-fx-font-size: 20px;" +

                                                "-fx-font-weight: bold;");

                Label profileDescription = new Label(
                                "Your personal, professional and clinic information");

                profileDescription.setStyle(
                                "-fx-text-fill: " +
                                                SECONDARY_TEXT + ";" +

                                                "-fx-font-size: 13px;");

                profileTitleBox.getChildren().addAll(
                                profileTitle,
                                profileDescription);

                profileHeader.getChildren().addAll(
                                avatar,
                                profileTitleBox);

                // =========================================================
                // PERSONAL INFORMATION
                // =========================================================

                Label personalTitle = sectionTitle(
                                "Personal Information");

                GridPane personalGrid = createGrid();

                firstNameField = createTextField();

                lastNameField = createTextField();

                genderField = createTextField();

                dobField = createTextField();

                addField(
                                personalGrid,
                                "First Name",
                                firstNameField,
                                0,
                                0);

                addField(
                                personalGrid,
                                "Last Name",
                                lastNameField,
                                1,
                                0);

                addField(
                                personalGrid,
                                "Gender",
                                genderField,
                                0,
                                1);

                addField(
                                personalGrid,
                                "Date of Birth",
                                dobField,
                                1,
                                1);

                // =========================================================
                // CONTACT INFORMATION
                // =========================================================

                Label contactTitle = sectionTitle(
                                "Contact Information");

                GridPane contactGrid = createGrid();

                phoneField = createTextField();

                emailField = createTextField();

                addressField = createTextField();

                addField(
                                contactGrid,
                                "Phone Number",
                                phoneField,
                                0,
                                0);

                addField(
                                contactGrid,
                                "Email",
                                emailField,
                                1,
                                0);

                addField(
                                contactGrid,
                                "Address",
                                addressField,
                                0,
                                1);

                GridPane.setColumnSpan(
                                addressField.getParent(),
                                1);

                // =========================================================
                // PROFESSIONAL INFORMATION
                // =========================================================

                Label professionalTitle = sectionTitle(
                                "Professional Information");

                GridPane professionalGrid = createGrid();

                specializationField = createTextField();

                qualificationField = createTextField();

                experienceField = createTextField();

                medicalLicenseField = createTextField();

                addField(
                                professionalGrid,
                                "Specialization",
                                specializationField,
                                0,
                                0);

                addField(
                                professionalGrid,
                                "Qualification",
                                qualificationField,
                                1,
                                0);

                addField(
                                professionalGrid,
                                "Experience",
                                experienceField,
                                0,
                                1);

                addField(
                                professionalGrid,
                                "Medical License",
                                medicalLicenseField,
                                1,
                                1);

                // =========================================================
                // CLINIC INFORMATION
                // =========================================================

                Label clinicTitle = sectionTitle(
                                "Clinic Information");

                GridPane clinicGrid = createGrid();

                clinicNameField = createTextField();

                clinicAddressField = createTextField();

                addField(
                                clinicGrid,
                                "Clinic / Hospital Name",
                                clinicNameField,
                                0,
                                0);

                addField(
                                clinicGrid,
                                "Clinic Address",
                                clinicAddressField,
                                1,
                                0);

                // =========================================================
                // BUTTONS
                // =========================================================

                HBox buttonBox = new HBox(12);

                buttonBox.setAlignment(
                                Pos.CENTER_RIGHT);

                Button cancelButton = new Button(
                                "Cancel");

                cancelButton.setStyle(
                                "-fx-background-color: #F8F7FB;" +

                                                "-fx-text-fill: " +
                                                DARK_TEXT + ";" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-font-weight: bold;" +

                                                "-fx-padding: 11 25 11 25;" +

                                                "-fx-background-radius: 10;" +

                                                "-fx-border-color: " +
                                                BORDER + ";" +

                                                "-fx-border-radius: 10;" +

                                                "-fx-cursor: hand;");

                cancelButton.setOnAction(
                                e -> loadProfile());

                Button saveButton = new Button(
                                "Save Changes");

                saveButton.setStyle(
                                "-fx-background-color: " +
                                                PINK + ";" +

                                                "-fx-text-fill: white;" +

                                                "-fx-font-size: 14px;" +

                                                "-fx-font-weight: bold;" +

                                                "-fx-padding: 11 25 11 25;" +

                                                "-fx-background-radius: 10;" +

                                                "-fx-cursor: hand;");

                saveButton.setOnMouseEntered(
                                e -> saveButton.setStyle(
                                                "-fx-background-color: " +
                                                                DARK_PINK + ";" +

                                                                "-fx-text-fill: white;" +

                                                                "-fx-font-size: 14px;" +

                                                                "-fx-font-weight: bold;" +

                                                                "-fx-padding: 11 25 11 25;" +

                                                                "-fx-background-radius: 10;" +

                                                                "-fx-cursor: hand;"));

                saveButton.setOnMouseExited(
                                e -> saveButton.setStyle(
                                                "-fx-background-color: " +
                                                                PINK + ";" +

                                                                "-fx-text-fill: white;" +

                                                                "-fx-font-size: 14px;" +

                                                                "-fx-font-weight: bold;" +

                                                                "-fx-padding: 11 25 11 25;" +

                                                                "-fx-background-radius: 10;" +

                                                                "-fx-cursor: hand;"));

                saveButton.setOnAction(
                                e -> saveProfile());

                buttonBox.getChildren().addAll(
                                cancelButton,
                                saveButton);

                // =========================================================
                // ADD EVERYTHING TO CARD
                // =========================================================

                profileCard.getChildren().addAll(

                                profileHeader,

                                new Separator(),

                                personalTitle,
                                personalGrid,

                                contactTitle,
                                contactGrid,

                                professionalTitle,
                                professionalGrid,

                                clinicTitle,
                                clinicGrid,

                                new Separator(),

                                buttonBox);

                mainContent.getChildren().add(
                                profileCard);

                // =========================================================
                // SCROLL PANE
                // =========================================================

                ScrollPane scrollPane = new ScrollPane(
                                mainContent);

                scrollPane.setFitToWidth(
                                true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                root.setCenter(
                                scrollPane);

                // =========================================================
                // SCENE
                // =========================================================

                Scene scene = new Scene(root);

                // =========================================================
                // SAME STAGE
                // =========================================================

                dashboardStage.setScene(
                                scene);

                dashboardStage.setTitle(
                                "MaaCare AI - Doctor Profile");

                // =========================================================
                // RESTORE STAGE SIZE
                // =========================================================

                if (!wasMaximized) {

                        if (currentWidth > 0) {
                                dashboardStage.setWidth(
                                                currentWidth);
                        }

                        if (currentHeight > 0) {
                                dashboardStage.setHeight(
                                                currentHeight);
                        }

                        if (currentX >= 0) {
                                dashboardStage.setX(
                                                currentX);
                        }

                        if (currentY >= 0) {
                                dashboardStage.setY(
                                                currentY);
                        }
                }

                dashboardStage.setMaximized(
                                wasMaximized);

                dashboardStage.show();

                // =========================================================
                // LOAD FIRESTORE DATA
                // =========================================================

                loadProfile();
        }

        // =========================================================
        // CREATE GRID
        // =========================================================

        private GridPane createGrid() {

                GridPane grid = new GridPane();

                grid.setHgap(25);

                grid.setVgap(18);

                ColumnConstraints col1 = new ColumnConstraints();

                col1.setPercentWidth(50);

                ColumnConstraints col2 = new ColumnConstraints();

                col2.setPercentWidth(50);

                grid.getColumnConstraints().addAll(
                                col1,
                                col2);

                return grid;
        }

        // =========================================================
        // CREATE TEXT FIELD
        // =========================================================

        private TextField createTextField() {

                TextField field = new TextField();

                field.setPrefHeight(
                                42);

                field.setStyle(
                                "-fx-background-color: #FCFAFF;" +

                                                "-fx-background-radius: 9;" +

                                                "-fx-border-color: " +
                                                BORDER + ";" +

                                                "-fx-border-radius: 9;" +

                                                "-fx-border-width: 1;" +

                                                "-fx-text-fill: " +
                                                DARK_TEXT + ";" +

                                                "-fx-font-size: 13px;" +

                                                "-fx-padding: 0 12 0 12;");

                return field;
        }

        // =========================================================
        // ADD FIELD
        // =========================================================

        private void addField(
                        GridPane grid,
                        String labelText,
                        TextField field,
                        int column,
                        int row) {

                VBox box = new VBox(7);

                Label label = new Label(
                                labelText);

                label.setStyle(
                                "-fx-text-fill: " +
                                                DARK_TEXT + ";" +

                                                "-fx-font-size: 13px;" +

                                                "-fx-font-weight: bold;");

                box.getChildren().addAll(
                                label,
                                field);

                grid.add(
                                box,
                                column,
                                row);

                GridPane.setHgrow(
                                box,
                                Priority.ALWAYS);

                GridPane.setFillWidth(
                                box,
                                true);
        }

        // =========================================================
        // SECTION TITLE
        // =========================================================

        private Label sectionTitle(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-text-fill: " +
                                                PURPLE + ";" +

                                                "-fx-font-size: 16px;" +

                                                "-fx-font-weight: bold;");

                return label;
        }

        // =========================================================
        // LOAD PROFILE
        // =========================================================

        private void loadProfile() {

                try {

                        System.out.println(
                                        "[DOCTOR PROFILE] Loading profile for UID: "
                                                        + doctorUid);

                        DoctorProfileModel doctor = controller.getDoctorInformation();

                        if (doctor == null) {

                                showError(
                                                "Doctor profile could not be loaded.");

                                return;
                        }

                        // -----------------------------------------------------
                        // PERSONAL
                        // -----------------------------------------------------

                        firstNameField.setText(
                                        safe(doctor.getFirstName()));

                        lastNameField.setText(
                                        safe(doctor.getLastName()));

                        genderField.setText(
                                        safe(doctor.getGender()));

                        dobField.setText(
                                        safe(doctor.getDob()));

                        // -----------------------------------------------------
                        // CONTACT
                        // -----------------------------------------------------

                        phoneField.setText(
                                        safe(doctor.getPhone()));

                        emailField.setText(
                                        safe(doctor.getEmail()));

                        addressField.setText(
                                        safe(doctor.getAddress()));

                        // -----------------------------------------------------
                        // PROFESSIONAL
                        // -----------------------------------------------------

                        specializationField.setText(
                                        safe(doctor.getSpecialization()));

                        qualificationField.setText(
                                        safe(doctor.getQualification()));

                        experienceField.setText(
                                        safe(doctor.getExperience()));

                        medicalLicenseField.setText(
                                        safe(doctor.getMedicalLicense()));

                        // -----------------------------------------------------
                        // CLINIC
                        // -----------------------------------------------------

                        clinicNameField.setText(
                                        safe(doctor.getClinicName()));

                        clinicAddressField.setText(
                                        safe(doctor.getClinicAddress()));

                        System.out.println(
                                        "[DOCTOR PROFILE] Profile displayed successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Error while displaying profile.");

                        e.printStackTrace();

                        showError(
                                        "Unable to load Doctor Profile.\n\n"
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // SAVE PROFILE
        // =========================================================

        private void saveProfile() {

                try {

                        boolean success = controller.updateProfile(

                                        firstNameField
                                                        .getText()
                                                        .trim(),

                                        lastNameField
                                                        .getText()
                                                        .trim(),

                                        genderField
                                                        .getText()
                                                        .trim(),

                                        dobField
                                                        .getText()
                                                        .trim(),

                                        phoneField
                                                        .getText()
                                                        .trim(),

                                        emailField
                                                        .getText()
                                                        .trim(),

                                        addressField
                                                        .getText()
                                                        .trim(),

                                        specializationField
                                                        .getText()
                                                        .trim(),

                                        qualificationField
                                                        .getText()
                                                        .trim(),

                                        experienceField
                                                        .getText()
                                                        .trim(),

                                        medicalLicenseField
                                                        .getText()
                                                        .trim(),

                                        clinicNameField
                                                        .getText()
                                                        .trim(),

                                        clinicAddressField
                                                        .getText()
                                                        .trim());

                        // -----------------------------------------------------
                        // SUCCESS
                        // -----------------------------------------------------

                        if (success) {

                                Alert alert = new Alert(
                                                Alert.AlertType.INFORMATION);

                                alert.setTitle(
                                                "Profile Updated");

                                alert.setHeaderText(
                                                null);

                                alert.setContentText(
                                                "Doctor profile updated successfully.");

                                alert.showAndWait();

                        } else {

                                showError(
                                                "Profile could not be updated.\n\n"
                                                                + "Please check the Doctor UID and Firebase connection.");
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "[DOCTOR PROFILE] Error while saving profile.");

                        e.printStackTrace();

                        showError(
                                        "Unable to save Doctor Profile.\n\n"
                                                        + e.getMessage());
                }
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safe(String value) {

                return value == null
                                ? ""
                                : value;
        }

        // =========================================================
        // ERROR ALERT
        // =========================================================

        private void showError(
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                "Doctor Profile");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }
}