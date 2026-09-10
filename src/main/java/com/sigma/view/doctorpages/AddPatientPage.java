
package com.sigma.view.doctorpages;

import com.sigma.controller.doctorController.PatientController;
import com.sigma.model.DoctorModel.Patient;
import com.sigma.view.scenesettings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPatientPage {

    // =========================================================
    // MOTHER DASHBOARD THEME COLORS
    // =========================================================

    private static final String PINK = "#E84A87";
    private static final String DARK_PINK = "#D93678";

    private static final String PURPLE = "#9B4DCC";

    private static final String LIGHT_PINK = "#FFEAF3";
    private static final String LIGHT_PURPLE = "#F3ECFF";

    private static final String TEXT = "#24234F";
    private static final String SECONDARY_TEXT = "#666680";

    private static final String BORDER = "#E7DCE8";

    private static final String PAGE_BACKGROUND = "#FFF8FC";

    private static final String BACKGROUND_GRADIENT =
            "linear-gradient(to bottom right, "
            + "#FFFFFF 0%, "
            + "#FFF6FA 55%, "
            + "#F3ECFF 100%)";

    // =========================================================
    // SHOW PAGE
    // =========================================================

    public static void show() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND_GRADIENT + ";"
        );

        root.setPadding(
                new Insets(28, 35, 28, 35)
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox heading = DoctorTheme.pageHeader(
                "Add Patient",
                "Enter patient information to add a new patient."
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                heading,
                spacer
        );

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox form = DoctorTheme.card();

        form.setStyle(
                "-fx-background-color: #FFFFFF;"
                + "-fx-background-radius: 18px;"
                + "-fx-border-color: " + BORDER + ";"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 18px;"
                + "-fx-effect: dropshadow(gaussian, rgba(155,77,204,0.10), 18, 0.15, 0, 5);"
        );

        form.setMaxWidth(850);

        form.setPadding(
                new Insets(28)
        );

        form.setSpacing(18);

        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        Label personalTitle = new Label(
                "Personal Information"
        );

        personalTitle.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 18px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + TEXT + ";"
        );

        // =====================================================
        // PATIENT FIELDS
        // =====================================================

        TextField name = createTextField(
                "Full Name"
        );

        TextField age = createTextField(
                "Age"
        );

        ComboBox<String> gender = new ComboBox<>();

        gender.getItems().addAll(
                "Female",
                "Male",
                "Other"
        );

        gender.setPromptText(
                "Select Gender"
        );

        gender.setPrefHeight(40);

        gender.setMaxWidth(
                Double.MAX_VALUE
        );

        gender.setStyle(
                "-fx-background-color: #FFFFFF;"
                + "-fx-border-color: " + BORDER + ";"
                + "-fx-border-radius: 8px;"
                + "-fx-background-radius: 8px;"
                + "-fx-font-family: Arial;"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: " + TEXT + ";"
        );

        TextField phone = createTextField(
                "Contact Number"
        );

        TextField address = createTextField(
                "Address"
        );

        // =====================================================
        // ROW 1
        // =====================================================

        HBox row1 = new HBox(20);

        VBox nameBox = fieldBox(
                "Full Name *",
                name
        );

        VBox ageBox = fieldBox(
                "Age *",
                age
        );

        HBox.setHgrow(
                nameBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                ageBox,
                Priority.ALWAYS
        );

        row1.getChildren().addAll(
                nameBox,
                ageBox
        );

        // =====================================================
        // ROW 2
        // =====================================================

        HBox row2 = new HBox(20);

        VBox genderBox = fieldBox(
                "Gender *",
                gender
        );

        VBox phoneBox = fieldBox(
                "Contact Number *",
                phone
        );

        HBox.setHgrow(
                genderBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                phoneBox,
                Priority.ALWAYS
        );

        row2.getChildren().addAll(
                genderBox,
                phoneBox
        );

        // =====================================================
        // ROW 3 - ADDRESS
        // =====================================================

        HBox row3 = new HBox();

        VBox addressBox = fieldBox(
                "Address",
                address
        );

        HBox.setHgrow(
                addressBox,
                Priority.ALWAYS
        );

        row3.getChildren().add(
                addressBox
        );

        // =====================================================
        // VISIT INFORMATION
        // =====================================================

        Label visitTitle = new Label(
                "Visit Information"
        );

        visitTitle.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 18px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + TEXT + ";"
        );

        TextField lastVisit = createTextField(
                "Last Visit"
        );

        TextField nextVisit = createTextField(
                "Next Visit"
        );

        // =====================================================
        // VISIT ROW
        // =====================================================

        HBox visitRow = new HBox(20);

        VBox lastVisitBox = fieldBox(
                "Last Visit",
                lastVisit
        );

        VBox nextVisitBox = fieldBox(
                "Next Visit",
                nextVisit
        );

        HBox.setHgrow(
                lastVisitBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                nextVisitBox,
                Priority.ALWAYS
        );

        visitRow.getChildren().addAll(
                lastVisitBox,
                nextVisitBox
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button cancel = new Button(
                "Cancel"
        );

        cancel.setPrefHeight(42);

        cancel.setMinWidth(110);

        /*
         * CANCEL BUTTON SAME AS PROFILE PAGE SAVE BUTTON
         */
        styleGradientButton(cancel);

        Button save = new Button(
                "Save Patient"
        );

        save.setPrefHeight(42);

        save.setMinWidth(145);

        styleGradientButton(save);

        HBox buttons = new HBox(15);

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        buttons.getChildren().addAll(
                cancel,
                save
        );

        // =====================================================
        // CANCEL BUTTON ACTION
        // =====================================================

        cancel.setOnAction(
                e -> PatientsPage.show()
        );

        // =====================================================
        // SAVE PATIENT
        // =====================================================

        save.setOnAction(e -> {

            if (name.getText().trim().isEmpty()
                    || age.getText().trim().isEmpty()
                    || gender.getValue() == null
                    || phone.getText().trim().isEmpty()) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle(
                        "Incomplete Information"
                );

                alert.setHeaderText(null);

                alert.setContentText(
                        "Please fill all required fields."
                );

                styleAlertButtons(alert);

                alert.showAndWait();

                return;
            }

            // =================================================
            // AGE VALIDATION
            // =================================================

            try {

                int enteredAge = Integer.parseInt(
                        age.getText().trim()
                );

                if (enteredAge < 0
                        || enteredAge > 120) {

                    Alert alert = new Alert(
                            Alert.AlertType.WARNING
                    );

                    alert.setTitle(
                            "Invalid Age"
                    );

                    alert.setHeaderText(null);

                    alert.setContentText(
                            "Please enter a valid age between 0 and 120."
                    );

                    styleAlertButtons(alert);

                    alert.showAndWait();

                    return;
                }

            } catch (NumberFormatException ex) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle(
                        "Invalid Age"
                );

                alert.setHeaderText(null);

                alert.setContentText(
                        "Age must contain numbers only."
                );

                styleAlertButtons(alert);

                alert.showAndWait();

                return;
            }

            // =================================================
            // GET PATIENT CONTROLLER
            // =================================================

            PatientController controller =
                    DoctorDashboard.getPatientController();

            if (controller == null) {

                Alert alert = new Alert(
                        Alert.AlertType.ERROR
                );

                alert.setTitle(
                        "Controller Error"
                );

                alert.setHeaderText(
                        "Patient Controller Error"
                );

                alert.setContentText(
                        "Patient controller is not available."
                );

                styleAlertButtons(alert);

                alert.showAndWait();

                return;
            }

            // =================================================
            // SAVE TO FIRESTORE
            // =================================================

            try {

                Patient savedPatient =
                        controller.addPatient(
                                name.getText().trim(),
                                age.getText().trim(),
                                gender.getValue(),
                                phone.getText().trim(),
                                lastVisit.getText().trim(),
                                nextVisit.getText().trim()
                        );

                if (savedPatient == null) {

                    Alert alert = new Alert(
                            Alert.AlertType.ERROR
                    );

                    alert.setTitle(
                            "Save Error"
                    );

                    alert.setHeaderText(
                            "Unable to add patient"
                    );

                    alert.setContentText(
                            "Patient could not be saved."
                    );

                    styleAlertButtons(alert);

                    alert.showAndWait();

                    return;
                }

                // =================================================
                // SUCCESS
                // =================================================

                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION
                );

                alert.setTitle(
                        "Patient Added"
                );

                alert.setHeaderText(
                        "Patient added successfully"
                );

                alert.setContentText(
                        name.getText().trim()
                                + " has been added successfully."
                );

                styleAlertButtons(alert);

                alert.showAndWait();

                PatientsPage.show();

            } catch (Exception ex) {

                ex.printStackTrace();

                Alert alert = new Alert(
                        Alert.AlertType.ERROR
                );

                alert.setTitle(
                        "Save Error"
                );

                alert.setHeaderText(
                        "Unable to add patient"
                );

                alert.setContentText(
                        "An error occurred while saving the patient:\n"
                                + ex.getMessage()
                );

                styleAlertButtons(alert);

                alert.showAndWait();
            }
        });

        // =====================================================
        // FORM CONTENT
        // =====================================================

        form.getChildren().addAll(
                personalTitle,
                row1,
                row2,
                row3,
                visitTitle,
                visitRow,
                buttons
        );

        // =====================================================
        // CENTER
        // =====================================================

        StackPane center = new StackPane();

        center.setAlignment(
                Pos.CENTER
        );

        center.setPadding(
                new Insets(10)
        );

        center.getChildren().add(
                form
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scroll = new ScrollPane();

        scroll.setContent(
                center
        );

        scroll.setFitToWidth(true);

        scroll.setFitToHeight(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scroll.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-background: transparent;"
                + "-fx-border-color: transparent;"
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.setTop(
                header
        );

        root.setLeft(
                DoctorDashboard.createSidebar(
                        "Patients"
                )
        );

        BorderPane.setMargin(
                header,
                new Insets(0, 0, 20, 0)
        );

        root.setCenter(
                scroll
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight()
        );

        // =====================================================
        // COMMON DASHBOARD STAGE
        // =====================================================

        DoctorDashboard.getInstance();

        DoctorDashboard.changeScene(
                scene
        );

        Stage dashboardStage =
                DoctorDashboard.dashboardStage;

        if (dashboardStage != null) {

            dashboardStage.setResizable(true);

            dashboardStage.setMaximized(true);

            dashboardStage.show();
        }
    }

    // =========================================================
    // CREATE TEXT FIELD
    // =========================================================

    private static TextField createTextField(
            String prompt) {

        TextField field = new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(40);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color: #FFFFFF;"
                + "-fx-border-color: " + BORDER + ";"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 8px;"
                + "-fx-background-radius: 8px;"
                + "-fx-font-family: Arial;"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: " + TEXT + ";"
                + "-fx-prompt-text-fill: " + SECONDARY_TEXT + ";"
                + "-fx-padding: 0 12px 0 12px;"
        );

        return field;
    }

    // =========================================================
    // FIELD BOX
    // =========================================================

    private static VBox fieldBox(
            String labelText,
            Node field) {

        Label label = new Label(
                labelText
        );

        label.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + TEXT + ";"
        );

        VBox box = new VBox(7);

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.getChildren().addAll(
                label,
                field
        );

        return box;
    }

    // =========================================================
    // GRADIENT BUTTON
    // SAME AS PROFILE PAGE SAVE BUTTON
    // =========================================================

    private static void styleGradientButton(
            Button button) {

        button.setStyle(
                "-fx-background-color: linear-gradient("
                        + "to right, #F54B87, #9B4DCC);"
                        + "-fx-text-fill: white;"
                        + "-fx-font-family: Arial;"
                        + "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-background-radius: 20px;"
                        + "-fx-border-radius: 20px;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 10px 20px;"
                        + "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> {

            if (!button.isDisabled()) {

                button.setStyle(
                        "-fx-background-color: linear-gradient("
                                + "to right, #9B4DCC, #F54B87);"
                                + "-fx-text-fill: white;"
                                + "-fx-font-family: Arial;"
                                + "-fx-font-size: 14px;"
                                + "-fx-font-weight: bold;"
                                + "-fx-background-radius: 20px;"
                                + "-fx-border-radius: 20px;"
                                + "-fx-border-color: transparent;"
                                + "-fx-padding: 10px 20px;"
                                + "-fx-cursor: hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {

            if (!button.isDisabled()) {

                button.setStyle(
                        "-fx-background-color: linear-gradient("
                                + "to right, #F54B87, #9B4DCC);"
                                + "-fx-text-fill: white;"
                                + "-fx-font-family: Arial;"
                                + "-fx-font-size: 14px;"
                                + "-fx-font-weight: bold;"
                                + "-fx-background-radius: 20px;"
                                + "-fx-border-radius: 20px;"
                                + "-fx-border-color: transparent;"
                                + "-fx-padding: 10px 20px;"
                                + "-fx-cursor: hand;"
                );
            }
        });
    }

    // =========================================================
    // ALERT BUTTON STYLE
    // =========================================================

    private static void styleAlertButtons(
            Alert alert) {

        alert.setOnShown(e -> {

            Button okButton =
                    (Button) alert.getDialogPane()
                            .lookupButton(
                                    ButtonType.OK
                            );

            Button cancelButton =
                    (Button) alert.getDialogPane()
                            .lookupButton(
                                    ButtonType.CANCEL
                            );

            if (okButton != null) {

                styleGradientButton(
                        okButton
                );
            }

            if (cancelButton != null) {

                styleGradientButton(
                        cancelButton
                );
            }
        });
    }
}