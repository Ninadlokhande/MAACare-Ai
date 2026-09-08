
package com.sigma.view.doctorpages;

import java.io.File;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.controller.doctorController.DoctorProfileController;
import com.sigma.controller.doctorController.ImageUploadController;
import com.sigma.model.DoctorModel.DoctorProfileModel;
import com.sigma.view.Loginpage;
import com.sigma.view.Welcomepage;
import com.sigma.view.scenesettings;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

// =============================================================
// DOCTOR PROFILE PAGE
// =============================================================

public class DoctorProfilePage {

        // =========================================================
        // MOTHER DASHBOARD COLORS
        // ONLY PAGE / BACKGROUND COLORS UPDATED
        // =========================================================

        private static final String PINK = "#E84A87";
        private static final String DARK = "#24234F";
        private static final String PURPLE = "#9B4DCC";
        private static final String TEXT_GRAY = "#77778D";
        private static final String LIGHT_PINK = "#FFF3F8";
        private static final String BORDER = "#E7DCE8";

        // MotherDashboard page background
        private static final String BACKGROUND = "#FFF8FC";

        // =========================================================
        // STAGE / FIREBASE
        // =========================================================

        private Stage dashboardStage;

        private String doctorUid;

        private Firestore db;

        private DoctorProfileController controller;

        private ImageUploadController imageUploadController;

        // =========================================================
        // PHOTO
        // =========================================================

        private ImageView doctorPhotoView;

        private Label doctorIcon;

        private Timeline photoRefreshTimeline;

        private String lastPhotoUrl = "";

        // =========================================================
        // PERSONAL INFORMATION
        // =========================================================

        private TextField firstNameField;

        private TextField lastNameField;

        private ComboBox<String> genderCombo;

        private DatePicker dobPicker;

        private TextField phoneField;

        private TextField emailField;

        private TextField addressField;

        // =========================================================
        // PROFESSIONAL INFORMATION
        // =========================================================

        private TextField specializationField;

        private TextField qualificationField;

        private TextField experienceField;

        private TextField medicalLicenseField;

        // =========================================================
        // CLINIC INFORMATION
        // =========================================================

        private TextField clinicNameField;

        private TextField clinicAddressField;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public DoctorProfilePage(
                        Stage dashboardStage,
                        String doctorUid) {

                this.dashboardStage = dashboardStage != null
                                ? dashboardStage
                                : DoctorDashboard.dashboardStage;

                this.doctorUid = doctorUid == null
                                ? ""
                                : doctorUid.trim();

                this.db = FirebaseConfig.getFirestore();

                this.controller = new DoctorProfileController(
                                db,
                                this.doctorUid);

                this.imageUploadController = new ImageUploadController();

                System.out.println(
                                "[DOCTOR PROFILE PAGE] UID = "
                                                + this.doctorUid);
        }

        // =========================================================
        // SHOW PAGE
        // =========================================================

        public void show() {

                BorderPane root = new BorderPane();

                // MotherDashboard background
                root.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");

                // =====================================================
                // HEADER
                // =====================================================

                root.setTop(
                                createHeader());

                // =====================================================
                // SIDEBAR
                // =====================================================

                root.setLeft(
                                DoctorDashboard.createSidebar(
                                                "Settings"));

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox();

                content.setSpacing(20);

                content.setPadding(
                                new Insets(
                                                25,
                                                30,
                                                40,
                                                30));

                content.setFillWidth(true);

                // MotherDashboard background
                content.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");

                content.getChildren().addAll(

                                createProfileHeaderCard(),

                                createPersonalInformationCard(),

                                createProfessionalInformationCard(),

                                createClinicInformationCard(),

                                createSaveSection());

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                content);

                scrollPane.setFitToWidth(true);

                scrollPane.setPannable(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                // MotherDashboard background
                scrollPane.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";"
                                                + "-fx-background: " + BACKGROUND + ";"
                                                + "-fx-border-color: transparent;");

                root.setCenter(
                                scrollPane);

                // =====================================================
                // SCENE SIZE
                // =====================================================

                Rectangle2D rect = scenesettings.rectanguler2d;

                Scene scene = new Scene(
                                root,
                                rect.getWidth(),
                                rect.getHeight());

                // =====================================================
                // SAME DASHBOARD SCENE
                // =====================================================

                DoctorDashboard.changeScene(
                                scene);

                if (dashboardStage != null) {

                        dashboardStage.setScene(
                                        scene);

                        dashboardStage.setResizable(
                                        true);

                        dashboardStage.setMaximized(
                                        true);

                        dashboardStage.show();

                        dashboardStage.toFront();
                }

                // =====================================================
                // LOAD PROFILE
                // =====================================================

                loadProfile();

                loadDoctorPhoto();

                startPhotoRefresh();
        }

        // =========================================================
        // HEADER
        // =========================================================

        private HBox createHeader() {

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setSpacing(15);

                header.setPadding(
                                new Insets(
                                                15,
                                                25,
                                                15,
                                                25));

                header.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: " + BORDER + ";"
                                                + "-fx-border-width: 0 0 1 0;");

                VBox titleBox = new VBox();

                titleBox.setSpacing(3);

                Label title = new Label(
                                "Doctor Profile");

                title.setStyle(
                                "-fx-font-size: 24px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: " + DARK + ";"
                                                + "-fx-font-family: Arial;");

                Label id = new Label(
                                "Doctor ID: " + doctorUid);

                id.setStyle(
                                "-fx-font-size: 12px;"
                                                + "-fx-text-fill: " + TEXT_GRAY + ";"
                                                + "-fx-font-family: Arial;");

                titleBox.getChildren().addAll(
                                title,
                                id);

                HBox spacer = new HBox();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =====================================================
                // LOGOUT BUTTON
                // =====================================================

                Button logout = createGradientButton("Logout");

                logout.setOnAction(
                                e -> logout());

                header.getChildren().addAll(
                                titleBox,
                                spacer,
                                logout);

                return header;
        }

        // =========================================================
        // PROFILE HEADER CARD
        // =========================================================

        private VBox createProfileHeaderCard() {

                VBox card = createWhiteCard();

                HBox main = new HBox();

                main.setSpacing(25);

                main.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // PHOTO
                // =====================================================

                StackPane photoContainer = new StackPane();

                photoContainer.setPrefSize(
                                125,
                                125);

                photoContainer.setMinSize(
                                125,
                                125);

                photoContainer.setMaxSize(
                                125,
                                125);

                photoContainer.setStyle(
                                "-fx-background-color: linear-gradient("
                                                + "to bottom right, "
                                                + "#FFF0F6, "
                                                + "#F4EDFF"
                                                + ");"
                                                + "-fx-background-radius: 100;"
                                                + "-fx-border-color: " + PINK + ";"
                                                + "-fx-border-width: 2;"
                                                + "-fx-border-radius: 100;");

                doctorPhotoView = new ImageView();

                doctorPhotoView.setFitWidth(
                                121);

                doctorPhotoView.setFitHeight(
                                121);

                doctorPhotoView.setPreserveRatio(
                                false);

                Circle clip = new Circle(
                                60.5,
                                60.5,
                                60.5);

                doctorPhotoView.setClip(
                                clip);

                doctorIcon = new Label("👨‍⚕️");

                doctorIcon.setStyle(
                                "-fx-font-size: 50px;");

                photoContainer.getChildren().addAll(
                                doctorPhotoView,
                                doctorIcon);

                // =====================================================
                // INFORMATION
                // =====================================================

                VBox information = new VBox();

                information.setSpacing(8);

                Label heading = new Label(
                                "Doctor Profile");

                heading.setStyle(
                                "-fx-font-size: 21px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: " + DARK + ";"
                                                + "-fx-font-family: Arial;");

                Label subtitle = new Label(
                                "Manage your personal and professional information.");

                subtitle.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-text-fill: " + TEXT_GRAY + ";"
                                                + "-fx-font-family: Arial;");

                subtitle.setWrapText(true);

                // =====================================================
                // PHOTO BUTTONS
                // =====================================================

                HBox buttons = new HBox();

                buttons.setSpacing(10);

                buttons.setAlignment(
                                Pos.CENTER_LEFT);

                Button changePhoto = createGradientButton(
                                "Change Photo");

                changePhoto.setOnAction(
                                e -> chooseAndUploadPhoto());

                Button removePhoto = createOutlinePinkButton(
                                "Remove Photo");

                removePhoto.setOnAction(
                                e -> removePhoto());

                buttons.getChildren().addAll(
                                changePhoto,
                                removePhoto);

                information.getChildren().addAll(
                                heading,
                                subtitle,
                                buttons);

                main.getChildren().addAll(
                                photoContainer,
                                information);

                card.getChildren().add(
                                main);

                return card;
        }

        // =========================================================
        // PERSONAL INFORMATION
        // =========================================================

        private VBox createPersonalInformationCard() {

                VBox card = createWhiteCard();

                Label title = createSectionTitle(
                                "Personal Information");

                GridPane grid = createGrid();

                firstNameField = createTextField();

                lastNameField = createTextField();

                phoneField = createTextField();

                emailField = createTextField();

                addressField = createTextField();

                genderCombo = new ComboBox<>();

                genderCombo.getItems().addAll(
                                "Male",
                                "Female",
                                "Other");

                genderCombo.setPromptText(
                                "Select Gender");

                styleComboBox(
                                genderCombo);

                dobPicker = new DatePicker();

                dobPicker.setPrefHeight(
                                40);

                dobPicker.setMaxWidth(
                                Double.MAX_VALUE);

                dobPicker.setStyle(
                                "-fx-font-family: Arial;"
                                                + "-fx-font-size: 13px;");

                addField(
                                grid,
                                "First Name",
                                firstNameField,
                                0,
                                0);

                addField(
                                grid,
                                "Last Name",
                                lastNameField,
                                1,
                                0);

                addField(
                                grid,
                                "Gender",
                                genderCombo,
                                0,
                                1);

                addField(
                                grid,
                                "Date of Birth",
                                dobPicker,
                                1,
                                1);

                addField(
                                grid,
                                "Phone",
                                phoneField,
                                0,
                                2);

                addField(
                                grid,
                                "Email",
                                emailField,
                                1,
                                2);

                addField(
                                grid,
                                "Address",
                                addressField,
                                0,
                                3);

                card.getChildren().addAll(
                                title,
                                grid);

                return card;
        }

        // =========================================================
        // PROFESSIONAL INFORMATION
        // =========================================================

        private VBox createProfessionalInformationCard() {

                VBox card = createWhiteCard();

                Label title = createSectionTitle(
                                "Professional Information");

                GridPane grid = createGrid();

                specializationField = createTextField();

                qualificationField = createTextField();

                experienceField = createTextField();

                medicalLicenseField = createTextField();

                addField(
                                grid,
                                "Specialization",
                                specializationField,
                                0,
                                0);

                addField(
                                grid,
                                "Qualification",
                                qualificationField,
                                1,
                                0);

                addField(
                                grid,
                                "Experience",
                                experienceField,
                                0,
                                1);

                addField(
                                grid,
                                "Medical License",
                                medicalLicenseField,
                                1,
                                1);

                card.getChildren().addAll(
                                title,
                                grid);

                return card;
        }

        // =========================================================
        // CLINIC INFORMATION
        // =========================================================

        private VBox createClinicInformationCard() {

                VBox card = createWhiteCard();

                Label title = createSectionTitle(
                                "Clinic Information");

                GridPane grid = createGrid();

                clinicNameField = createTextField();

                clinicAddressField = createTextField();

                addField(
                                grid,
                                "Clinic Name",
                                clinicNameField,
                                0,
                                0);

                addField(
                                grid,
                                "Clinic Address",
                                clinicAddressField,
                                1,
                                0);

                card.getChildren().addAll(
                                title,
                                grid);

                return card;
        }

        // =========================================================
        // SAVE SECTION
        // =========================================================

        private HBox createSaveSection() {

                HBox box = new HBox();

                box.setAlignment(
                                Pos.CENTER_RIGHT);

                box.setPadding(
                                new Insets(
                                                0,
                                                0,
                                                10,
                                                0));

                Button save = createGradientButton(
                                "Save Profile");

                save.setOnAction(
                                e -> saveProfile());

                box.getChildren().add(
                                save);

                return box;
        }

        // =========================================================
        // WHITE CARD
        // =========================================================

        private VBox createWhiteCard() {

                VBox card = new VBox();

                card.setSpacing(18);

                card.setPadding(
                                new Insets(20));

                card.setFillWidth(true);

                card.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 18;"
                                                + "-fx-border-color: " + BORDER + ";"
                                                + "-fx-border-radius: 18;"
                                                + "-fx-border-width: 1;");

                return card;
        }

        // =========================================================
        // SECTION TITLE
        // =========================================================

        private Label createSectionTitle(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-font-size: 18px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: " + DARK + ";"
                                                + "-fx-font-family: Arial;");

                return label;
        }

        // =========================================================
        // GRID
        // =========================================================

        private GridPane createGrid() {

                GridPane grid = new GridPane();

                grid.setHgap(20);

                grid.setVgap(15);

                ColumnConstraints first = new ColumnConstraints();

                first.setPercentWidth(50);

                ColumnConstraints second = new ColumnConstraints();

                second.setPercentWidth(50);

                grid.getColumnConstraints().addAll(
                                first,
                                second);

                return grid;
        }

        // =========================================================
        // ADD FIELD
        // =========================================================

        private void addField(
                        GridPane grid,
                        String labelText,
                        javafx.scene.control.Control control,
                        int column,
                        int row) {

                VBox box = new VBox();

                box.setSpacing(6);

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 12px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-text-fill: " + DARK + ";"
                                                + "-fx-font-family: Arial;");

                control.setMaxWidth(
                                Double.MAX_VALUE);

                box.getChildren().addAll(
                                label,
                                control);

                grid.add(
                                box,
                                column,
                                row);
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private TextField createTextField() {

                TextField field = new TextField();

                field.setPrefHeight(
                                42);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: #E4DCE7;"
                                                + "-fx-border-radius: 10;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-font-size: 12px;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-text-fill: " + DARK + ";"
                                                + "-fx-padding: 0 12px;");

                return field;
        }

        // =========================================================
        // COMBO BOX
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> combo) {

                combo.setPrefHeight(
                                42);

                combo.setMaxWidth(
                                Double.MAX_VALUE);

                combo.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: #E4DCE7;"
                                                + "-fx-border-radius: 10;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-font-size: 12px;"
                                                + "-fx-font-family: Arial;");
        }

        // =========================================================
        // SAVE PROFILE
        // =========================================================

        private void saveProfile() {

                try {

                        String dob = dobPicker.getValue() == null
                                        ? ""
                                        : dobPicker
                                                        .getValue()
                                                        .toString();

                        Task<Boolean> task = new Task<Boolean>() {

                                @Override
                                protected Boolean call()
                                                throws Exception {

                                        return controller.updateProfile(

                                                        firstNameField
                                                                        .getText()
                                                                        .trim(),

                                                        lastNameField
                                                                        .getText()
                                                                        .trim(),

                                                        genderCombo.getValue() == null
                                                                        ? ""
                                                                        : genderCombo
                                                                                        .getValue(),

                                                        dob,

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
                                }
                        };

                        task.setOnSucceeded(
                                        e -> {

                                                if (Boolean.TRUE.equals(
                                                                task.getValue())) {

                                                        showStyledAlert(
                                                                        Alert.AlertType.INFORMATION,
                                                                        "Success",
                                                                        "Profile updated successfully.");

                                                } else {

                                                        showStyledAlert(
                                                                        Alert.AlertType.ERROR,
                                                                        "Error",
                                                                        "Unable to update profile.");
                                                }
                                        });

                        task.setOnFailed(
                                        e -> {

                                                if (task.getException() != null) {

                                                        task.getException()
                                                                        .printStackTrace();
                                                }

                                                showStyledAlert(
                                                                Alert.AlertType.ERROR,
                                                                "Error",
                                                                "Something went wrong while updating profile.");
                                        });

                        Thread thread = new Thread(task);

                        thread.setDaemon(true);

                        thread.start();

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showStyledAlert(
                                        Alert.AlertType.ERROR,
                                        "Error",
                                        "Unable to save profile.");
                }
        }

        // =========================================================
        // LOAD PROFILE
        // =========================================================

        private void loadProfile() {

                Task<DoctorProfileModel> task = new Task<DoctorProfileModel>() {

                        @Override
                        protected DoctorProfileModel call()
                                        throws Exception {

                                return controller
                                                .getDoctorInformation();
                        }
                };

                task.setOnSucceeded(
                                e -> {

                                        DoctorProfileModel profile = task.getValue();

                                        if (profile != null) {

                                                populateProfile(
                                                                profile);
                                        }
                                });

                task.setOnFailed(
                                e -> {

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
        // POPULATE PROFILE
        // =========================================================

        private void populateProfile(
                        DoctorProfileModel profile) {

                Platform.runLater(() -> {

                        try {

                                firstNameField.setText(
                                                safe(
                                                                profile.getFirstName()));

                                lastNameField.setText(
                                                safe(
                                                                profile.getLastName()));

                                String gender = safe(
                                                profile.getGender());

                                if (!gender.isEmpty()
                                                && genderCombo
                                                                .getItems()
                                                                .contains(gender)) {

                                        genderCombo.setValue(
                                                        gender);
                                }

                                String dob = safe(
                                                profile.getDob());

                                if (!dob.isEmpty()) {

                                        try {

                                                dobPicker.setValue(
                                                                java.time.LocalDate
                                                                                .parse(dob));

                                        } catch (Exception ignored) {
                                        }
                                }

                                phoneField.setText(
                                                safe(
                                                                profile.getPhone()));

                                emailField.setText(
                                                safe(
                                                                profile.getEmail()));

                                addressField.setText(
                                                safe(
                                                                profile.getAddress()));

                                specializationField.setText(
                                                safe(
                                                                profile
                                                                                .getSpecialization()));

                                qualificationField.setText(
                                                safe(
                                                                profile
                                                                                .getQualification()));

                                experienceField.setText(
                                                safe(
                                                                profile
                                                                                .getExperience()));

                                medicalLicenseField.setText(
                                                safe(
                                                                profile
                                                                                .getMedicalLicense()));

                                clinicNameField.setText(
                                                safe(
                                                                profile
                                                                                .getClinicName()));

                                clinicAddressField.setText(
                                                safe(
                                                                profile
                                                                                .getClinicAddress()));

                        } catch (Exception ex) {

                                ex.printStackTrace();
                        }
                });
        }

        // =========================================================
        // SAFE
        // =========================================================

        private String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }

        // =========================================================
        // PHOTO CHOOSER
        // =========================================================

        private void chooseAndUploadPhoto() {

                FileChooser chooser = new FileChooser();

                chooser.setTitle(
                                "Choose Doctor Profile Photo");

                chooser.getExtensionFilters()
                                .add(
                                                new FileChooser.ExtensionFilter(
                                                                "Image Files",
                                                                "*.png",
                                                                "*.jpg",
                                                                "*.jpeg",
                                                                "*.webp"));

                File file = chooser.showOpenDialog(
                                dashboardStage);

                if (file == null) {

                        return;
                }

                uploadPhoto(file);
        }

        // =========================================================
        // UPLOAD PHOTO
        // =========================================================

        private void uploadPhoto(
                        File file) {

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                return imageUploadController
                                                .imageUpload(file);
                        }
                };

                task.setOnSucceeded(
                                e -> {

                                        String photoUrl = task.getValue();

                                        if (photoUrl != null
                                                        && !photoUrl.isBlank()) {

                                                controller.saveDoctorPhotoUrl(
                                                                photoUrl);

                                                lastPhotoUrl = photoUrl;

                                                loadImageFromUrl(
                                                                photoUrl);

                                                showStyledAlert(
                                                                Alert.AlertType.INFORMATION,
                                                                "Success",
                                                                "Profile photo updated successfully.");
                                        }
                                });

                task.setOnFailed(
                                e -> {

                                        if (task.getException() != null) {

                                                task.getException()
                                                                .printStackTrace();
                                        }

                                        showStyledAlert(
                                                        Alert.AlertType.ERROR,
                                                        "Upload Failed",
                                                        "Unable to upload profile photo.");
                                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // LOAD DOCTOR PHOTO
        // =========================================================

        private void loadDoctorPhoto() {

                if (doctorUid == null
                                || doctorUid.isBlank()) {

                        return;
                }

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                DocumentSnapshot document = db.collection("doctors")
                                                .document(doctorUid)
                                                .get()
                                                .get();

                                if (!document.exists()) {

                                        return "";
                                }

                                String[] fields = {

                                                "photoUrl",

                                                "profilePhotoUrl",

                                                "photoURL",

                                                "cloudinaryUrl"
                                };

                                for (String field : fields) {

                                        String url = document
                                                        .getString(field);

                                        if (url != null
                                                        && !url.isBlank()) {

                                                return url;
                                        }
                                }

                                return "";
                        }
                };

                task.setOnSucceeded(
                                e -> {

                                        String url = task.getValue();

                                        if (url != null
                                                        && !url.isBlank()) {

                                                lastPhotoUrl = url;

                                                loadImageFromUrl(
                                                                url);
                                        }
                                });

                task.setOnFailed(
                                e -> {

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
        // LOAD IMAGE
        // =========================================================

        private void loadImageFromUrl(
                        String url) {

                if (url == null
                                || url.isBlank()
                                || doctorPhotoView == null) {

                        return;
                }

                try {

                        Image image = new Image(
                                        url,
                                        121,
                                        121,
                                        false,
                                        true,
                                        true);

                        doctorPhotoView.setImage(
                                        image);

                        if (doctorIcon != null) {

                                doctorIcon.setVisible(
                                                false);
                        }

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // =========================================================
        // PHOTO REFRESH
        // =========================================================

        private void startPhotoRefresh() {

                stopPhotoRefresh();

                photoRefreshTimeline = new Timeline(
                                new KeyFrame(
                                                Duration.seconds(10),
                                                e -> refreshPhoto()));

                photoRefreshTimeline.setCycleCount(
                                Timeline.INDEFINITE);

                photoRefreshTimeline.play();
        }

        // =========================================================
        // REFRESH PHOTO
        // =========================================================

        private void refreshPhoto() {

                if (doctorUid == null
                                || doctorUid.isBlank()) {

                        return;
                }

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                DocumentSnapshot document = db.collection("doctors")
                                                .document(doctorUid)
                                                .get()
                                                .get();

                                if (!document.exists()) {

                                        return "";
                                }

                                String[] fields = {

                                                "photoUrl",

                                                "profilePhotoUrl",

                                                "photoURL",

                                                "cloudinaryUrl"
                                };

                                for (String field : fields) {

                                        String url = document
                                                        .getString(field);

                                        if (url != null
                                                        && !url.isBlank()) {

                                                return url;
                                        }
                                }

                                return "";
                        }
                };

                task.setOnSucceeded(
                                e -> {

                                        String url = task.getValue();

                                        if (url != null
                                                        && !url.isBlank()
                                                        && !url.equals(
                                                                        lastPhotoUrl)) {

                                                lastPhotoUrl = url;

                                                loadImageFromUrl(
                                                                url);
                                        }
                                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // REMOVE PHOTO
        // =========================================================

        private void removePhoto() {

                Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION);

                alert.setTitle(
                                "Remove Photo");

                alert.setHeaderText(
                                "Remove profile photo?");

                alert.setContentText(
                                "Are you sure you want to remove your profile photo?");

                styleAlertButtons(
                                alert);

                alert.showAndWait()
                                .ifPresent(result -> {

                                        if (result == ButtonType.OK) {

                                                try {

                                                        controller
                                                                        .removeDoctorPhoto();

                                                        lastPhotoUrl = "";

                                                        if (doctorPhotoView != null) {

                                                                doctorPhotoView
                                                                                .setImage(null);
                                                        }

                                                        if (doctorIcon != null) {

                                                                doctorIcon
                                                                                .setVisible(true);
                                                        }

                                                        showStyledAlert(
                                                                        Alert.AlertType.INFORMATION,
                                                                        "Success",
                                                                        "Profile photo removed successfully.");

                                                } catch (Exception ex) {

                                                        ex.printStackTrace();

                                                        showStyledAlert(
                                                                        Alert.AlertType.ERROR,
                                                                        "Error",
                                                                        "Unable to remove profile photo.");
                                                }
                                        }
                                });
        }

        // =========================================================
        // LOGOUT
        // =========================================================

        private void logout() {

                try {

                        // Stop profile photo refresh
                        stopPhotoRefresh();

                        // =================================================
                        // CLEAR CURRENT DOCTOR UID
                        // =================================================

                        DoctorDashboard.setCurrentDoctorUid(null);

                        // =================================================
                        // CREATE LOGIN PAGE
                        // =================================================

                        Loginpage loginPage = new Loginpage();

                        Scene loginScene = loginPage.gotologinpage();

                        // =================================================
                        // USE SAME COMMON STAGE
                        // =================================================

                        if (dashboardStage != null) {

                                dashboardStage.setScene(
                                                loginScene);

                                dashboardStage.setResizable(
                                                true);

                                dashboardStage.setMaximized(
                                                true);

                                dashboardStage.show();

                                dashboardStage.toFront();

                        } else if (Welcomepage.stage != null) {

                                Welcomepage.stage.setScene(
                                                loginScene);

                                Welcomepage.stage.setResizable(
                                                true);

                                Welcomepage.stage.setMaximized(
                                                true);

                                Welcomepage.stage.show();

                                Welcomepage.stage.toFront();
                        }

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // =========================================================
        // MAIN GRADIENT BUTTON
        // =========================================================

        private Button createGradientButton(
                        String text) {

                Button button = new Button(text);

                button.setAlignment(
                                Pos.CENTER);

                button.setPrefHeight(
                                40);

                button.setPadding(
                                new Insets(
                                                9,
                                                18,
                                                9,
                                                18));

                setGradientButtonNormal(
                                button);

                button.setOnMouseEntered(
                                e -> setGradientButtonHover(
                                                button));

                button.setOnMouseExited(
                                e -> setGradientButtonNormal(
                                                button));

                return button;
        }

        // =========================================================
        // GRADIENT NORMAL
        // =========================================================

        private void setGradientButtonNormal(
                        Button button) {

                button.setStyle(
                                "-fx-background-color: linear-gradient("
                                                + "to right, "
                                                + "#F54B87, "
                                                + "#9B4DCC"
                                                + ");"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-size: 12px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-background-radius: 20;"
                                                + "-fx-border-radius: 20;"
                                                + "-fx-padding: 9px 18px;"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // GRADIENT HOVER
        // =========================================================

        private void setGradientButtonHover(
                        Button button) {

                button.setStyle(
                                "-fx-background-color: linear-gradient("
                                                + "to right, "
                                                + "#9B4DCC, "
                                                + "#F54B87"
                                                + ");"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-size: 12px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-font-family: Arial;"
                                                + "-fx-background-radius: 20;"
                                                + "-fx-border-radius: 20;"
                                                + "-fx-padding: 9px 18px;"
                                                + "-fx-cursor: hand;");
        }

        // =========================================================
        // OUTLINE PINK BUTTON
        // =========================================================

        private Button createOutlinePinkButton(
                        String text) {

                Button button = new Button(text);

                button.setAlignment(
                                Pos.CENTER);

                button.setPrefHeight(
                                40);

                button.setPadding(
                                new Insets(
                                                9,
                                                18,
                                                9,
                                                18));

                setGradientButtonNormal(
                                button);

                button.setOnMouseEntered(
                                e -> setGradientButtonHover(
                                                button));

                button.setOnMouseExited(
                                e -> setGradientButtonNormal(
                                                button));

                return button;
        }

        // =========================================================
        // OUTLINE NORMAL
        // =========================================================

        private void setOutlineButtonNormal(
                        Button button) {

                setGradientButtonNormal(button);
        }

        // =========================================================
        // OUTLINE HOVER
        // =========================================================

        private void setOutlineButtonHover(
                        Button button) {

                setGradientButtonHover(button);
        }

        // =========================================================
        // ALERT BUTTON STYLE
        // =========================================================

        private void styleAlertButtons(
                        Alert alert) {

                alert.setOnShown(
                                e -> {

                                        Button okButton = (Button) alert
                                                        .getDialogPane()
                                                        .lookupButton(
                                                                        ButtonType.OK);

                                        Button cancelButton = (Button) alert
                                                        .getDialogPane()
                                                        .lookupButton(
                                                                        ButtonType.CANCEL);

                                        if (okButton != null) {

                                                setGradientButtonNormal(
                                                                okButton);

                                                okButton.setOnMouseEntered(
                                                                event -> setGradientButtonHover(
                                                                                okButton));

                                                okButton.setOnMouseExited(
                                                                event -> setGradientButtonNormal(
                                                                                okButton));
                                        }

                                        if (cancelButton != null) {

                                                setOutlineButtonNormal(
                                                                cancelButton);

                                                cancelButton.setOnMouseEntered(
                                                                event -> setOutlineButtonHover(
                                                                                cancelButton));

                                                cancelButton.setOnMouseExited(
                                                                event -> setOutlineButtonNormal(
                                                                                cancelButton));
                                        }
                                });
        }

        // =========================================================
        // STYLED ALERT
        // =========================================================

        private void showStyledAlert(
                        Alert.AlertType type,
                        String title,
                        String message) {

                Platform.runLater(() -> {

                        Alert alert = new Alert(type);

                        alert.setTitle(
                                        title);

                        alert.setHeaderText(
                                        null);

                        alert.setContentText(
                                        message);

                        styleAlertButtons(
                                        alert);

                        alert.showAndWait();
                });
        }

        // =========================================================
        // STOP PHOTO REFRESH
        // =========================================================

        private void stopPhotoRefresh() {

                if (photoRefreshTimeline != null) {

                        photoRefreshTimeline.stop();

                        photoRefreshTimeline = null;
                }
        }
}

