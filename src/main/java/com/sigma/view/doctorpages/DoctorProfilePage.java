package com.sigma.view.doctorpages;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.sigma.config.DoctorModule.FirebaseConfig;
import com.sigma.controller.doctorController.DoctorProfileController;
import com.sigma.controller.doctorController.ImageUploadController;
import com.sigma.model.DoctorModel.DoctorProfileModel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DoctorProfilePage {

        // =========================================================
        // THEME
        // =========================================================

        private static final String BACKGROUND = "#FFF9FB";

        private static final String PURPLE = "#E84A87";
        private static final String LIGHT_PURPLE = "#FFE3EE";

        private static final String PINK = "#E84A87";
        private static final String LIGHT_PINK = "#FFEAF3";

        private static final String DARK_TEXT = "#3B2140";
        private static final String SECONDARY_TEXT = "#806A78";
        private static final String BORDER = "#F0D8E3";

        // =========================================================
        // STAGE / FIREBASE
        // =========================================================

        private final Stage dashboardStage;
        private final String doctorUid;

        private final Firestore db;
        private final DoctorProfileController controller;

        // =========================================================
        // PHOTO
        // =========================================================

        private final ImageUploadController imageUploadController;

        private ImageView doctorPhotoView;
        private Label doctorIcon;

        private Timeline photoRefreshTimeline;

        private String lastPhotoUrl = "";

        // =========================================================
        // PROFILE FIELDS
        // =========================================================

        private TextField firstNameField;
        private TextField lastNameField;
        private ComboBox<String> genderCombo;
        private DatePicker dobPicker;

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

        public DoctorProfilePage(Stage dashboardStage, String doctorUid) {

                this.dashboardStage = dashboardStage;
                this.doctorUid = doctorUid == null ? "" : doctorUid.trim();

                this.db = FirebaseConfig.getFirestore();

                this.controller = new DoctorProfileController(
                                db,
                                this.doctorUid);

                this.imageUploadController = new ImageUploadController();

                System.out.println(
                                "[DOCTOR PROFILE PAGE] UID = " + this.doctorUid);
        }

        // =========================================================
        // SHOW PAGE
        // =========================================================

        public void show() {

                if (doctorUid == null || doctorUid.trim().isEmpty()) {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Doctor UID is missing.");

                        return;
                }

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = createHeader();

                root.setTop(header);
                root.setLeft(DoctorDashboard.createSidebar("Settings"));

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox(25);

                content.setPadding(
                                new Insets(30, 45, 40, 45));

                content.setAlignment(Pos.TOP_CENTER);

                // =====================================================
                // PROFILE CARD
                // =====================================================

                VBox profileCard = createProfileCard();

                // =====================================================
                // PERSONAL INFORMATION
                // =====================================================

                VBox personalCard = createPersonalInformationCard();

                // =====================================================
                // PROFESSIONAL INFORMATION
                // =====================================================

                VBox professionalCard = createProfessionalInformationCard();

                // =====================================================
                // CLINIC INFORMATION
                // =====================================================

                VBox clinicCard = createClinicInformationCard();

                // =====================================================
                // SAVE BUTTON
                // =====================================================

                HBox saveBox = new HBox();

                saveBox.setAlignment(Pos.CENTER_RIGHT);

                Button saveButton = new Button(
                                "Save Profile");

                saveButton.setStyle(
                                "-fx-background-color: " + PURPLE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 12px 28px;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-cursor: hand;");

                saveButton.setOnAction(e -> saveProfile());

                saveBox.getChildren().add(saveButton);

                content.getChildren().addAll(
                                profileCard,
                                personalCard,
                                professionalCard,
                                clinicCard,
                                saveBox);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setStyle(
                                "-fx-background: transparent;" +
                                                "-fx-background-color: transparent;");

                root.setCenter(scrollPane);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(root);

                dashboardStage.setScene(scene);

                dashboardStage.setResizable(true);

                dashboardStage.show();

                // =====================================================
                // LOAD PROFILE + PHOTO
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

                header.setAlignment(Pos.CENTER_LEFT);

                header.setPadding(
                                new Insets(18, 30, 18, 30));

                header.setSpacing(20);

                header.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label title = new Label(
                                "Doctor Profile");

                title.setStyle(
                                "-fx-text-fill: " + DARK_TEXT + ";" +
                                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label uidLabel = new Label(
                                "Doctor ID: " + doctorUid);

                uidLabel.setStyle(
                                "-fx-text-fill: " + SECONDARY_TEXT + ";" +
                                                "-fx-font-size: 12px;");

                header.getChildren().addAll(
                                title,
                                spacer,
                                uidLabel);

                return header;
        }

        // =========================================================
        // PROFILE CARD
        // =========================================================

        private VBox createProfileCard() {

                VBox card = new VBox(20);

                card.setPadding(
                                new Insets(25));

                card.setAlignment(Pos.CENTER);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18px;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 18px;");

                // =====================================================
                // PHOTO
                // =====================================================

                StackPane photoContainer = new StackPane();

                doctorPhotoView = new ImageView();

                doctorPhotoView.setFitWidth(90);

                doctorPhotoView.setFitHeight(90);

                doctorPhotoView.setPreserveRatio(false);

                doctorPhotoView.setVisible(false);

                Circle clip = new Circle(
                                45,
                                45,
                                45);

                doctorPhotoView.setClip(clip);

                doctorIcon = new Label(
                                "👨‍⚕️");

                doctorIcon.setStyle(
                                "-fx-font-size: 48px;");

                StackPane avatar = new StackPane();

                avatar.setPrefSize(90, 90);

                avatar.setMaxSize(90, 90);

                avatar.setStyle(
                                "-fx-background-color: " +
                                                LIGHT_PURPLE + ";" +
                                                "-fx-background-radius: 50%;");

                avatar.getChildren().addAll(
                                doctorIcon,
                                doctorPhotoView);

                photoContainer.getChildren().add(
                                avatar);

                // =====================================================
                // NAME
                // =====================================================

                Label nameLabel = new Label(
                                "Doctor Profile");

                nameLabel.setStyle(
                                "-fx-text-fill: " + DARK_TEXT + ";" +
                                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;");

                Label photoHint = new Label(
                                "Upload your professional profile photo");

                photoHint.setStyle(
                                "-fx-text-fill: " + SECONDARY_TEXT + ";" +
                                                "-fx-font-size: 13px;");

                // =====================================================
                // CHANGE PHOTO
                // =====================================================

                Button changePhotoButton = new Button("📷  Change Photo");

                changePhotoButton.setStyle(
                                "-fx-background-color: " + PURPLE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 10px 18px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");

                changePhotoButton.setOnAction(
                                e -> chooseAndUploadPhoto());

                // =====================================================
                // REMOVE PHOTO
                // =====================================================

                Button removePhotoButton = new Button("Remove Photo");

                removePhotoButton.setStyle(
                                "-fx-background-color: " + LIGHT_PINK + ";" +
                                                "-fx-text-fill: " + PINK + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 10px 18px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");

                removePhotoButton.setOnAction(
                                e -> removeDoctorPhoto());

                HBox photoButtons = new HBox(10);

                photoButtons.setAlignment(
                                Pos.CENTER);

                photoButtons.getChildren().addAll(
                                changePhotoButton,
                                removePhotoButton);

                card.getChildren().addAll(
                                photoContainer,
                                nameLabel,
                                photoHint,
                                photoButtons);

                return card;
        }

        // =========================================================
        // PERSONAL INFORMATION
        // =========================================================

        private VBox createPersonalInformationCard() {

                VBox card = createSectionCard();

                Label title = sectionTitle(
                                "Personal Information");

                GridPane grid = createGrid();

                firstNameField = createTextField();

                lastNameField = createTextField();

                genderCombo = new ComboBox<>();

                genderCombo.getItems().addAll(
                                "Male",
                                "Female",
                                "Other");

                genderCombo.setMaxWidth(
                                Double.MAX_VALUE);

                styleComboBox(genderCombo);

                dobPicker = new DatePicker();

                dobPicker.setMaxWidth(
                                Double.MAX_VALUE);

                styleDatePicker(dobPicker);

                phoneField = createTextField();

                emailField = createTextField();

                addressField = createTextField();

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

                GridPane.setColumnSpan(
                                addressField,
                                2);

                card.getChildren().addAll(
                                title,
                                grid);

                return card;
        }

        // =========================================================
        // PROFESSIONAL INFORMATION
        // =========================================================

        private VBox createProfessionalInformationCard() {

                VBox card = createSectionCard();

                Label title = sectionTitle(
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

                VBox card = createSectionCard();

                Label title = sectionTitle(
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
        // SECTION CARD
        // =========================================================

        private VBox createSectionCard() {

                VBox box = new VBox(20);

                box.setPadding(
                                new Insets(25));

                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18px;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 18px;");

                return box;
        }

        // =========================================================
        // SECTION TITLE
        // =========================================================

        private Label sectionTitle(String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-text-fill: " + DARK_TEXT + ";" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;");

                return label;
        }

        // =========================================================
        // GRID
        // =========================================================

        private GridPane createGrid() {

                GridPane grid = new GridPane();

                grid.setHgap(20);

                grid.setVgap(16);

                ColumnConstraintsHelper.setColumns(grid);

                return grid;
        }

        // =========================================================
        // ADD FIELD
        // =========================================================

        private void addField(
                        GridPane grid,
                        String labelText,
                        javafx.scene.Node field,
                        int column,
                        int row) {

                VBox box = new VBox(7);

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-text-fill: " + DARK_TEXT + ";" +
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
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private TextField createTextField() {

                TextField field = new TextField();

                field.setPrefHeight(42);

                field.setStyle(
                                "-fx-background-color: #FFF9FB;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-padding: 0 12px;" +
                                                "-fx-text-fill: " + DARK_TEXT + ";" +
                                                "-fx-font-size: 13px;");

                return field;
        }

        // =========================================================
        // COMBOBOX
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> comboBox) {

                comboBox.setPrefHeight(42);

                comboBox.setStyle(
                                "-fx-background-color: #FFF9FB;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-font-size: 13px;");
        }

        // =========================================================
        // DATE PICKER
        // =========================================================

        private void styleDatePicker(
                        DatePicker datePicker) {

                datePicker.setPrefHeight(42);

                datePicker.setStyle(
                                "-fx-background-color: #FFF9FB;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-font-size: 13px;");
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

                task.setOnSucceeded(e -> {

                        DoctorProfileModel doctor = task.getValue();

                        if (doctor == null) {
                                return;
                        }

                        firstNameField.setText(
                                        safe(doctor.getFirstName()));

                        lastNameField.setText(
                                        safe(doctor.getLastName()));

                        genderCombo.setValue(
                                        safe(doctor.getGender()));

                        if (doctor.getDob() != null
                                        && !doctor.getDob().isEmpty()) {

                                try {

                                        dobPicker.setValue(
                                                        java.time.LocalDate.parse(
                                                                        doctor.getDob()));

                                } catch (Exception ignored) {
                                }
                        }

                        phoneField.setText(
                                        safe(doctor.getPhone()));

                        emailField.setText(
                                        safe(doctor.getEmail()));

                        addressField.setText(
                                        safe(doctor.getAddress()));

                        specializationField.setText(
                                        safe(doctor.getSpecialization()));

                        qualificationField.setText(
                                        safe(doctor.getQualification()));

                        experienceField.setText(
                                        safe(doctor.getExperience()));

                        medicalLicenseField.setText(
                                        safe(doctor.getMedicalLicense()));

                        clinicNameField.setText(
                                        safe(doctor.getClinicName()));

                        clinicAddressField.setText(
                                        safe(doctor.getClinicAddress()));
                });

                task.setOnFailed(e -> {

                        System.out.println(
                                        "[DOCTOR PROFILE] Failed to load profile.");

                        task.getException().printStackTrace();
                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // SAVE PROFILE
        // =========================================================

        private void saveProfile() {

                final String dob = dobPicker.getValue() == null
                                ? ""
                                : dobPicker.getValue().toString();

                Task<Boolean> task = new Task<Boolean>() {

                        @Override
                        protected Boolean call()
                                        throws Exception {

                                return controller.updateProfile(

                                                firstNameField.getText().trim(),

                                                lastNameField.getText().trim(),

                                                genderCombo.getValue() == null
                                                                ? ""
                                                                : genderCombo.getValue(),

                                                dob,

                                                phoneField.getText().trim(),

                                                emailField.getText().trim(),

                                                addressField.getText().trim(),

                                                specializationField.getText().trim(),

                                                qualificationField.getText().trim(),

                                                experienceField.getText().trim(),

                                                medicalLicenseField.getText().trim(),

                                                clinicNameField.getText().trim(),

                                                clinicAddressField.getText().trim());
                        }
                };

                task.setOnSucceeded(e -> {

                        if (task.getValue()) {

                                showAlert(
                                                Alert.AlertType.INFORMATION,
                                                "Profile updated successfully.");

                                loadDoctorPhoto();

                        } else {

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Failed to update profile.");
                        }
                });

                task.setOnFailed(e -> {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Error while updating profile.");

                        task.getException().printStackTrace();
                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // CHOOSE AND UPLOAD PHOTO
        // =========================================================

        private void chooseAndUploadPhoto() {

                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle(
                                "Select Doctor Profile Photo");

                fileChooser.getExtensionFilters().add(
                                new FileChooser.ExtensionFilter(
                                                "Image Files",
                                                "*.png",
                                                "*.jpg",
                                                "*.jpeg",
                                                "*.webp"));

                File file = fileChooser.showOpenDialog(
                                dashboardStage);

                if (file == null) {
                        return;
                }

                System.out.println(
                                "[DOCTOR PHOTO] Selected file: "
                                                + file.getAbsolutePath());

                // =====================================================
                // BACKGROUND UPLOAD
                // =====================================================

                Task<String> uploadTask = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                return imageUploadController
                                                .imageUpload(file);
                        }
                };

                uploadTask.setOnSucceeded(e -> {

                        String photoUrl = uploadTask.getValue();

                        if (photoUrl == null
                                        || photoUrl.trim().isEmpty()) {

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Photo upload failed.");

                                return;
                        }

                        System.out.println(
                                        "[DOCTOR PHOTO] Cloudinary URL = "
                                                        + photoUrl);

                        // =================================================
                        // SAVE URL TO FIRESTORE
                        // =================================================

                        Task<Boolean> saveTask = new Task<Boolean>() {

                                @Override
                                protected Boolean call()
                                                throws Exception {

                                        return controller
                                                        .saveDoctorPhotoUrl(
                                                                        photoUrl);
                                }
                        };

                        saveTask.setOnSucceeded(event -> {

                                if (saveTask.getValue()) {

                                        lastPhotoUrl = photoUrl;

                                        displayPhoto(photoUrl);

                                        showAlert(
                                                        Alert.AlertType.INFORMATION,
                                                        "Profile photo updated successfully.");

                                } else {

                                        showAlert(
                                                        Alert.AlertType.ERROR,
                                                        "Photo uploaded but URL could not be saved.");
                                }
                        });

                        saveTask.setOnFailed(event -> {

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Could not save photo URL to Firestore.");

                                saveTask.getException()
                                                .printStackTrace();
                        });

                        Thread saveThread = new Thread(saveTask);

                        saveThread.setDaemon(true);

                        saveThread.start();
                });

                uploadTask.setOnFailed(e -> {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Cloudinary upload failed.");

                        uploadTask.getException()
                                        .printStackTrace();
                });

                Thread uploadThread = new Thread(uploadTask);

                uploadThread.setDaemon(true);

                uploadThread.start();
        }

        // =========================================================
        // LOAD DOCTOR PHOTO
        // =========================================================

        private void loadDoctorPhoto() {

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                String profileUid = doctorUid;

                                if (profileUid == null || profileUid.isEmpty()) {
                                        return "";
                                }

                                DocumentSnapshot document = db.collection("doctors")
                                                .document(profileUid)
                                                .get()
                                                .get(
                                                                10,
                                                                TimeUnit.SECONDS);

                                if (!document.exists()) {
                                        return "";
                                }

                                String url = document.getString("photoUrl");

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "profilePhotoUrl");
                                }

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "photoURL");
                                }

                                if (url == null
                                                || url.trim().isEmpty()) {

                                        url = document.getString(
                                                        "cloudinaryUrl");
                                }

                                return url == null
                                                ? ""
                                                : url.trim();
                        }
                };

                task.setOnSucceeded(e -> {

                        String photoUrl = task.getValue();

                        if (photoUrl == null
                                        || photoUrl.isEmpty()) {

                                clearPhoto();

                                return;
                        }

                        if (!photoUrl.equals(lastPhotoUrl)) {

                                lastPhotoUrl = photoUrl;

                                displayPhoto(photoUrl);
                        }
                });

                task.setOnFailed(e -> {

                        System.out.println(
                                        "[DOCTOR PHOTO] Failed to load photo.");

                        if (task.getException() != null) {
                                task.getException().printStackTrace();
                        }
                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // DISPLAY PHOTO
        // =========================================================

        private void displayPhoto(String photoUrl) {

                if (photoUrl == null
                                || photoUrl.trim().isEmpty()) {

                        clearPhoto();

                        return;
                }

                Task<Image> imageTask = new Task<Image>() {

                        @Override
                        protected Image call()
                                        throws Exception {

                                Image image = new Image(
                                                photoUrl,
                                                90,
                                                90,
                                                false,
                                                true,
                                                false);

                                if (image.isError()) {
                                        throw new Exception(
                                                        "Unable to load image.");
                                }

                                return image;
                        }
                };

                imageTask.setOnSucceeded(e -> {

                        Image image = imageTask.getValue();

                        doctorPhotoView.setImage(image);

                        doctorPhotoView.setVisible(true);

                        doctorIcon.setVisible(false);

                        System.out.println(
                                        "[DOCTOR PHOTO] Photo displayed.");
                });

                imageTask.setOnFailed(e -> {

                        System.out.println(
                                        "[DOCTOR PHOTO] Image loading failed.");

                        clearPhoto();
                });

                Thread thread = new Thread(imageTask);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // CLEAR PHOTO
        // =========================================================

        private void clearPhoto() {

                Platform.runLater(() -> {

                        doctorPhotoView.setImage(null);

                        doctorPhotoView.setVisible(false);

                        doctorIcon.setVisible(true);

                        lastPhotoUrl = "";
                });
        }

        // =========================================================
        // REMOVE PHOTO
        // =========================================================

        private void removeDoctorPhoto() {

                Task<Boolean> task = new Task<Boolean>() {

                        @Override
                        protected Boolean call()
                                        throws Exception {

                                return controller
                                                .removeDoctorPhoto();
                        }
                };

                task.setOnSucceeded(e -> {

                        if (task.getValue()) {

                                clearPhoto();

                                showAlert(
                                                Alert.AlertType.INFORMATION,
                                                "Profile photo removed successfully.");

                        } else {

                                showAlert(
                                                Alert.AlertType.ERROR,
                                                "Could not remove profile photo.");
                        }
                });

                task.setOnFailed(e -> {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Error while removing photo.");

                        task.getException()
                                        .printStackTrace();
                });

                Thread thread = new Thread(task);

                thread.setDaemon(true);

                thread.start();
        }

        // =========================================================
        // AUTO REFRESH PHOTO
        // =========================================================

        private void startPhotoRefresh() {

                stopPhotoRefresh();

                photoRefreshTimeline = new Timeline(
                                new KeyFrame(
                                                Duration.seconds(3),
                                                e -> loadDoctorPhoto()));

                photoRefreshTimeline.setCycleCount(
                                Timeline.INDEFINITE);

                photoRefreshTimeline.play();
        }

        // =========================================================
        // STOP REFRESH
        // =========================================================

        private void stopPhotoRefresh() {

                if (photoRefreshTimeline != null) {

                        photoRefreshTimeline.stop();

                        photoRefreshTimeline = null;
                }
        }

        // =========================================================
        // ALERT
        // =========================================================

        private void showAlert(
                        Alert.AlertType type,
                        String message) {

                Platform.runLater(() -> {

                        Alert alert = new Alert(type);

                        alert.setTitle(
                                        "Doctor Profile");

                        alert.setHeaderText(null);

                        alert.setContentText(message);

                        alert.showAndWait();
                });
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
        // COLUMN HELPER
        // =========================================================

        private static class ColumnConstraintsHelper {

                static void setColumns(GridPane grid) {

                        javafx.scene.layout.ColumnConstraints c1 = new javafx.scene.layout.ColumnConstraints();

                        javafx.scene.layout.ColumnConstraints c2 = new javafx.scene.layout.ColumnConstraints();

                        c1.setPercentWidth(50);

                        c2.setPercentWidth(50);

                        grid.getColumnConstraints().addAll(
                                        c1,
                                        c2);
                }
        }
}