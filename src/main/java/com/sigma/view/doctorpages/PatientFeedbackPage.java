package com.sigma.view.doctorpages;

import com.google.cloud.firestore.Firestore;
import com.sigma.config.FirebaseConfig;
import com.sigma.controller.doctorController.FeedbackController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientFeedbackPage {

        // =========================================================
        // COLORS
        // =========================================================

        private static final String PRIMARY = "#E84A87";

        private static final String LIGHT_PURPLE = "#FFE3EE";

        private static final String TEXT = "#3B2140";

        private static final String SECONDARY = "#806A78";

        private static final String BORDER = "#F0D8E3";

        // =========================================================
        // DATA
        // =========================================================

        private final Stage stage;

        private final String doctorUid;

        private final String patientUid;

        private final String patientName;

        private FeedbackController feedbackController;

        // =========================================================
        // UI
        // =========================================================

        private ToggleButton star1;
        private ToggleButton star2;
        private ToggleButton star3;
        private ToggleButton star4;
        private ToggleButton star5;

        private TextArea feedbackText;

        private Label selectedRatingLabel;

        private Button submitButton;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public PatientFeedbackPage(
                        Stage stage,
                        String doctorUid,
                        String patientUid,
                        String patientName) {

                this.stage = stage;

                this.doctorUid = doctorUid == null
                                ? ""
                                : doctorUid.trim();

                this.patientUid = patientUid == null
                                ? ""
                                : patientUid.trim();

                this.patientName = patientName == null ||
                                patientName.trim().isEmpty()
                                                ? "Patient"
                                                : patientName.trim();

                try {

                        Firestore db = FirebaseConfig.getFirestore();

                        feedbackController = new FeedbackController(db);

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // =========================================================
        // SHOW
        // =========================================================

        public void show() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: #FFF9FB;");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                20,
                                                30));

                Label title = new Label(
                                "Give Feedback");

                title.setStyle(
                                "-fx-font-size: 22px;"
                                                + "-fx-font-weight: bold;");

                title.setTextFill(
                                Color.web(
                                                TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button backButton = new Button(
                                "← Back");

                backButton.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PURPLE + ";"
                                                + "-fx-text-fill: "
                                                + PRIMARY + ";"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 9 16;"
                                                + "-fx-cursor: hand;");

                backButton.setOnAction(
                                e -> {

                                        if (stage != null) {

                                                stage.close();
                                        }
                                });

                header.getChildren()
                                .addAll(
                                                title,
                                                spacer,
                                                backButton);

                root.setTop(
                                header);

                // =====================================================
                // MAIN CARD
                // =====================================================

                VBox card = new VBox(18);

                card.setMaxWidth(
                                650);

                card.setPadding(
                                new Insets(
                                                30));

                card.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-background-radius: 18;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-radius: 18;");

                // =====================================================
                // PATIENT
                // =====================================================

                Label patientLabel = new Label(
                                "Patient: "
                                                + patientName);

                patientLabel.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;");

                patientLabel.setTextFill(
                                Color.web(
                                                TEXT));

                // =====================================================
                // DOCTOR
                // =====================================================

                Label doctorLabel = new Label(
                                "Please share your experience with your doctor.");

                doctorLabel.setStyle(
                                "-fx-font-size: 14px;");

                doctorLabel.setTextFill(
                                Color.web(
                                                SECONDARY));

                // =====================================================
                // RATING TITLE
                // =====================================================

                Label ratingTitle = new Label(
                                "How would you rate your consultation?");

                ratingTitle.setStyle(
                                "-fx-font-size: 15px;"
                                                + "-fx-font-weight: bold;");

                ratingTitle.setTextFill(
                                Color.web(
                                                TEXT));

                // =====================================================
                // STAR BUTTONS
                // =====================================================

                HBox stars = new HBox(8);

                stars.setAlignment(
                                Pos.CENTER_LEFT);

                star1 = createStarButton(1);

                star2 = createStarButton(2);

                star3 = createStarButton(3);

                star4 = createStarButton(4);

                star5 = createStarButton(5);

                stars.getChildren()
                                .addAll(
                                                star1,
                                                star2,
                                                star3,
                                                star4,
                                                star5);

                // =====================================================
                // SELECTED RATING
                // =====================================================

                selectedRatingLabel = new Label(
                                "Please select a rating");

                selectedRatingLabel.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;");

                selectedRatingLabel.setTextFill(
                                Color.web(
                                                PRIMARY));

                // =====================================================
                // COMMENT TITLE
                // =====================================================

                Label commentTitle = new Label(
                                "Your Feedback");

                commentTitle.setStyle(
                                "-fx-font-size: 15px;"
                                                + "-fx-font-weight: bold;");

                commentTitle.setTextFill(
                                Color.web(
                                                TEXT));

                // =====================================================
                // TEXT AREA
                // =====================================================

                feedbackText = new TextArea();

                feedbackText.setPromptText(
                                "Write your feedback here...");

                feedbackText.setWrapText(
                                true);

                feedbackText.setPrefRowCount(
                                6);

                feedbackText.setStyle(
                                "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-radius: 10;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-font-size: 13px;");

                // =====================================================
                // SUBMIT BUTTON
                // =====================================================

                submitButton = new Button(
                                "Submit Feedback");

                submitButton.setMaxWidth(
                                Double.MAX_VALUE);

                submitButton.setStyle(
                                "-fx-background-color: "
                                                + PRIMARY + ";"
                                                + "-fx-text-fill: white;"
                                                + "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-background-radius: 10;"
                                                + "-fx-padding: 12;"
                                                + "-fx-cursor: hand;");

                submitButton.setOnAction(
                                e -> submitFeedback());

                card.getChildren()
                                .addAll(
                                                patientLabel,
                                                doctorLabel,
                                                ratingTitle,
                                                stars,
                                                selectedRatingLabel,
                                                commentTitle,
                                                feedbackText,
                                                submitButton);

                StackPane center = new StackPane(card);

                center.setPadding(
                                new Insets(
                                                30));

                root.setCenter(
                                center);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                root,
                                800,
                                650);

                stage.setTitle(
                                "MaaCare AI - Patient Feedback");

                stage.setScene(
                                scene);

                stage.show();

                stage.toFront();
        }

        // =========================================================
        // CREATE STAR
        // =========================================================

        private ToggleButton createStarButton(
                        int starNumber) {

                ToggleButton button = new ToggleButton(
                                "★");

                button.setPrefSize(
                                52,
                                52);

                button.setStyle(
                                "-fx-background-color: "
                                                + LIGHT_PURPLE + ";"
                                                + "-fx-text-fill: "
                                                + SECONDARY + ";"
                                                + "-fx-font-size: 27px;"
                                                + "-fx-background-radius: 12;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-radius: 12;"
                                                + "-fx-cursor: hand;");

                button.setOnAction(
                                e -> selectRating(
                                                starNumber));

                return button;
        }

        // =========================================================
        // SELECT RATING
        // =========================================================

        private void selectRating(
                        int rating) {

                star1.setSelected(false);
                star2.setSelected(false);
                star3.setSelected(false);
                star4.setSelected(false);
                star5.setSelected(false);

                ToggleButton[] buttons = {
                                star1,
                                star2,
                                star3,
                                star4,
                                star5
                };

                for (int i = 0; i < rating; i++) {

                        buttons[i].setSelected(
                                        true);

                        buttons[i].setStyle(
                                        "-fx-background-color: "
                                                        + PRIMARY + ";"
                                                        + "-fx-text-fill: white;"
                                                        + "-fx-font-size: 27px;"
                                                        + "-fx-background-radius: 12;"
                                                        + "-fx-border-color: "
                                                        + PRIMARY + ";"
                                                        + "-fx-border-radius: 12;"
                                                        + "-fx-cursor: hand;");
                }

                for (int i = rating; i < 5; i++) {

                        buttons[i].setStyle(
                                        "-fx-background-color: "
                                                        + LIGHT_PURPLE + ";"
                                                        + "-fx-text-fill: "
                                                        + SECONDARY + ";"
                                                        + "-fx-font-size: 27px;"
                                                        + "-fx-background-radius: 12;"
                                                        + "-fx-border-color: "
                                                        + BORDER + ";"
                                                        + "-fx-border-radius: 12;"
                                                        + "-fx-cursor: hand;");
                }

                selectedRatingLabel.setText(
                                rating
                                                + " out of 5 stars selected");

                selectedRatingLabel.setUserData(
                                rating);
        }

        // =========================================================
        // GET SELECTED RATING
        // =========================================================

        private int getSelectedRating() {

                if (selectedRatingLabel == null ||
                                selectedRatingLabel.getUserData() == null) {

                        return 0;
                }

                return (int) selectedRatingLabel.getUserData();
        }

        // =========================================================
        // SUBMIT FEEDBACK
        // =========================================================

        private void submitFeedback() {

                int rating = getSelectedRating();

                String comment = feedbackText.getText();

                // -----------------------------------------------------
                // VALIDATE DOCTOR UID
                // -----------------------------------------------------

                if (doctorUid.isEmpty()) {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Doctor UID is missing.",
                                        "Please open feedback from the doctor's appointment.");

                        return;
                }

                // -----------------------------------------------------
                // VALIDATE RATING
                // -----------------------------------------------------

                if (rating < 1 ||
                                rating > 5) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Rating Required",
                                        "Please select a rating from 1 to 5 stars.");

                        return;
                }

                // -----------------------------------------------------
                // VALIDATE COMMENT
                // -----------------------------------------------------

                if (comment == null ||
                                comment.trim().isEmpty()) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Feedback Required",
                                        "Please write your feedback.");

                        return;
                }

                // -----------------------------------------------------
                // DISABLE BUTTON
                // -----------------------------------------------------

                submitButton.setDisable(
                                true);

                submitButton.setText(
                                "Submitting...");

                // -----------------------------------------------------
                // FIRESTORE BACKGROUND TASK
                // -----------------------------------------------------

                Thread thread = new Thread(
                                () -> {

                                        boolean success = feedbackController.addFeedback(
                                                        doctorUid,
                                                        patientUid,
                                                        patientName,
                                                        rating,
                                                        comment);

                                        Platform.runLater(
                                                        () -> {

                                                                submitButton.setDisable(
                                                                                false);

                                                                submitButton.setText(
                                                                                "Submit Feedback");

                                                                if (success) {

                                                                        showAlert(
                                                                                        Alert.AlertType.INFORMATION,
                                                                                        "Feedback Submitted",
                                                                                        "Thank you! Your feedback has been submitted successfully.");

                                                                        feedbackText.clear();

                                                                        selectedRatingLabel
                                                                                        .setUserData(
                                                                                                        null);

                                                                        selectedRatingLabel
                                                                                        .setText(
                                                                                                        "Please select a rating");

                                                                        resetStars();

                                                                } else {

                                                                        showAlert(
                                                                                        Alert.AlertType.ERROR,
                                                                                        "Submission Failed",
                                                                                        "Unable to submit feedback. Please try again.");
                                                                }
                                                        });
                                });

                thread.setDaemon(
                                true);

                thread.start();
        }

        // =========================================================
        // RESET STARS
        // =========================================================

        private void resetStars() {

                ToggleButton[] buttons = {
                                star1,
                                star2,
                                star3,
                                star4,
                                star5
                };

                for (ToggleButton button : buttons) {

                        button.setSelected(
                                        false);

                        button.setStyle(
                                        "-fx-background-color: "
                                                        + LIGHT_PURPLE + ";"
                                                        + "-fx-text-fill: "
                                                        + SECONDARY + ";"
                                                        + "-fx-font-size: 27px;"
                                                        + "-fx-background-radius: 12;"
                                                        + "-fx-border-color: "
                                                        + BORDER + ";"
                                                        + "-fx-border-radius: 12;"
                                                        + "-fx-cursor: hand;");
                }
        }

        // =========================================================
        // ALERT
        // =========================================================

        private void showAlert(
                        Alert.AlertType type,
                        String title,
                        String message) {

                Alert alert = new Alert(type);

                alert.setTitle(
                                title);

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }
}