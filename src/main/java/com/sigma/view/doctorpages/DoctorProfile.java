package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.DoctorBasicInformationController;
import com.sigma.model.DoctorModel.DoctorBasicInformationModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DoctorProfile extends BorderPane {

        private final DoctorBasicInformationController controller;

        private TextField firstName;
        private TextField lastName;
        private TextField phone;
        private TextField email;
        private TextField address;
        private TextField qualification;
        private TextField experience;
        private TextField license;
        private TextField clinicName;
        private TextField clinicAddress;

        private ComboBox<String> gender;
        private ComboBox<String> specialization;

        private Button editButton;
        private Button saveButton;
        private Button cancelButton;

        public DoctorProfile() {

                controller = new DoctorBasicInformationController();

                buildUI();

                loadProfile();

                setEditable(false);
        }

        // =====================================================
        // BUILD UI
        // =====================================================

        private void buildUI() {

                Theme.applyBackground(this);

                setPadding(
                                new Insets(
                                                28,
                                                35,
                                                28,
                                                35));

                // =================================================
                // HEADER
                // =================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "Doctor Profile",
                                "View and manage your professional information.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button back = Theme.backButton();

                header.getChildren().addAll(
                                heading,
                                spacer,
                                back);

                // =================================================
                // EDIT BUTTON
                // =================================================

                editButton = Theme.primaryButton(
                                "✏ Edit Profile");

                header.getChildren().add(
                                editButton);

                editButton.setOnAction(
                                e -> enableEditing());

                // =================================================
                // FORM
                // =================================================

                VBox card = Theme.card();

                card.setPadding(
                                new Insets(25));

                card.setSpacing(18);

                // =================================================
                // PERSONAL
                // =================================================

                Label personal = sectionTitle(
                                "Personal Information");

                firstName = new TextField();

                lastName = new TextField();

                gender = new ComboBox<>();

                gender.getItems().addAll(
                                "Female",
                                "Male",
                                "Other");

                GridPane personalGrid = grid();

                personalGrid.add(
                                field(
                                                "First Name",
                                                firstName),
                                0, 0);

                personalGrid.add(
                                field(
                                                "Last Name",
                                                lastName),
                                1, 0);

                personalGrid.add(
                                field(
                                                "Gender",
                                                gender),
                                0, 1);

                // =================================================
                // CONTACT
                // =================================================

                Label contact = sectionTitle(
                                "Contact Information");

                phone = new TextField();

                email = new TextField();

                address = new TextField();

                GridPane contactGrid = grid();

                contactGrid.add(
                                field(
                                                "Phone",
                                                phone),
                                0, 0);

                contactGrid.add(
                                field(
                                                "Email",
                                                email),
                                1, 0);

                contactGrid.add(
                                field(
                                                "Address",
                                                address),
                                0, 1);

                // =================================================
                // PROFESSIONAL
                // =================================================

                Label professional = sectionTitle(
                                "Professional Information");

                specialization = new ComboBox<>();

                specialization.getItems().addAll(
                                "Obstetrician & Gynecologist",
                                "Gynecologist",
                                "Pediatrician",
                                "General Physician",
                                "Dermatologist",
                                "Cardiologist",
                                "Other");

                qualification = new TextField();

                experience = new TextField();

                license = new TextField();

                GridPane professionalGrid = grid();

                professionalGrid.add(
                                field(
                                                "Specialization",
                                                specialization),
                                0, 0);

                professionalGrid.add(
                                field(
                                                "Qualification",
                                                qualification),
                                1, 0);

                professionalGrid.add(
                                field(
                                                "Experience",
                                                experience),
                                0, 1);

                professionalGrid.add(
                                field(
                                                "Medical License",
                                                license),
                                1, 1);

                // =================================================
                // CLINIC
                // =================================================

                Label clinic = sectionTitle(
                                "Clinic Information");

                clinicName = new TextField();

                clinicAddress = new TextField();

                GridPane clinicGrid = grid();

                clinicGrid.add(
                                field(
                                                "Clinic Name",
                                                clinicName),
                                0, 0);

                clinicGrid.add(
                                field(
                                                "Clinic Address",
                                                clinicAddress),
                                1, 0);

                // =================================================
                // SAVE / CANCEL
                // =================================================

                saveButton = Theme.primaryButton(
                                "Save Changes");

                cancelButton = new Button(
                                "Cancel");

                cancelButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " +
                                                Theme.SECONDARY_TEXT + ";" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                HBox buttons = new HBox(12);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                buttons.getChildren().addAll(
                                cancelButton,
                                saveButton);

                saveButton.setVisible(false);
                cancelButton.setVisible(false);

                // =================================================
                // SAVE ACTION
                // =================================================

                saveButton.setOnAction(
                                e -> saveProfile());

                cancelButton.setOnAction(
                                e -> {

                                        loadProfile();

                                        setEditable(false);
                                });

                // =================================================
                // CARD CONTENT
                // =================================================

                card.getChildren().addAll(

                                personal,
                                personalGrid,

                                contact,
                                contactGrid,

                                professional,
                                professionalGrid,

                                clinic,
                                clinicGrid,

                                buttons);

                ScrollPane scroll = new ScrollPane(card);

                scroll.setFitToWidth(true);

                scroll.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                setTop(header);

                BorderPane.setMargin(
                                header,
                                new Insets(
                                                0,
                                                0,
                                                20,
                                                0));

                setCenter(scroll);
        }

        // =====================================================
        // LOAD PROFILE
        // =====================================================

        private void loadProfile() {

                DoctorBasicInformationModel doctor = controller.getDoctorInformation();

                firstName.setText(
                                doctor.getFirstName());

                lastName.setText(
                                doctor.getLastName());

                gender.setValue(
                                doctor.getGender());

                phone.setText(
                                doctor.getPhone());

                email.setText(
                                doctor.getEmail());

                address.setText(
                                doctor.getAddress());

                specialization.setValue(
                                doctor.getSpecialization());

                qualification.setText(
                                doctor.getQualification());

                experience.setText(
                                doctor.getExperience());

                license.setText(
                                doctor.getMedicalLicense());

                clinicName.setText(
                                doctor.getClinicName());

                clinicAddress.setText(
                                doctor.getClinicAddress());
        }

        // =====================================================
        // ENABLE EDIT
        // =====================================================

        private void enableEditing() {

                setEditable(true);

                editButton.setVisible(false);

                saveButton.setVisible(true);

                cancelButton.setVisible(true);
        }

        // =====================================================
        // SET EDITABLE
        // =====================================================

        private void setEditable(
                        boolean editable) {

                firstName.setEditable(editable);
                lastName.setEditable(editable);
                phone.setEditable(editable);
                email.setEditable(editable);
                address.setEditable(editable);
                qualification.setEditable(editable);
                experience.setEditable(editable);
                license.setEditable(editable);
                clinicName.setEditable(editable);
                clinicAddress.setEditable(editable);

                gender.setDisable(!editable);
                specialization.setDisable(!editable);
        }

        // =====================================================
        // SAVE PROFILE
        // =====================================================

        private void saveProfile() {

                if (firstName.getText()
                                .trim().isEmpty()
                                || lastName.getText()
                                                .trim().isEmpty()
                                || phone.getText()
                                                .trim().isEmpty()
                                || email.getText()
                                                .trim().isEmpty()
                                || specialization.getValue() == null
                                || qualification.getText()
                                                .trim().isEmpty()
                                || license.getText()
                                                .trim().isEmpty()) {

                        Alert alert = new Alert(
                                        Alert.AlertType.WARNING);

                        alert.setTitle(
                                        "Incomplete Information");

                        alert.setHeaderText(null);

                        alert.setContentText(
                                        "Please fill all required fields.");

                        alert.showAndWait();

                        return;
                }

                controller.updateProfile(

                                firstName.getText().trim(),

                                lastName.getText().trim(),

                                gender.getValue(),

                                "",

                                phone.getText().trim(),

                                email.getText().trim(),

                                address.getText().trim(),

                                specialization.getValue(),

                                qualification.getText().trim(),

                                experience.getText().trim(),

                                license.getText().trim(),

                                clinicName.getText().trim(),

                                clinicAddress.getText().trim());

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "Profile Updated");

                alert.setHeaderText(
                                "Success");

                alert.setContentText(
                                "Doctor profile updated successfully.");

                alert.showAndWait();

                setEditable(false);

                editButton.setVisible(true);

                saveButton.setVisible(false);

                cancelButton.setVisible(false);
        }

        // =====================================================
        // GRID
        // =====================================================

        private GridPane grid() {

                GridPane grid = new GridPane();

                grid.setHgap(20);
                grid.setVgap(12);

                ColumnConstraints c1 = new ColumnConstraints();

                c1.setPercentWidth(50);

                ColumnConstraints c2 = new ColumnConstraints();

                c2.setPercentWidth(50);

                grid.getColumnConstraints()
                                .addAll(c1, c2);

                return grid;
        }

        // =====================================================
        // FIELD
        // =====================================================

        private VBox field(
                        String title,
                        Control control) {

                VBox box = new VBox(5);

                Label label = new Label(title);

                label.setStyle(
                                "-fx-font-weight: bold;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " +
                                                Theme.TEXT + ";");

                control.setMaxWidth(
                                Double.MAX_VALUE);

                if (control instanceof TextField) {

                        ((TextField) control)
                                        .setPrefHeight(38);
                }

                if (control instanceof ComboBox) {

                        ((ComboBox<?>) control)
                                        .setPrefHeight(38);
                }

                box.getChildren().addAll(
                                label,
                                control);

                return box;
        }

        // =====================================================
        // SECTION TITLE
        // =====================================================

        private Label sectionTitle(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                Theme.PRIMARY + ";");

                return label;
        }

        // =====================================================
        // SHOW
        // =====================================================

        public static void show() {

                DoctorProfile profile = new DoctorProfile();

                Scene scene = new Scene(profile);

                DoctorDashboard.changeScene(
                                scene);
        }
}