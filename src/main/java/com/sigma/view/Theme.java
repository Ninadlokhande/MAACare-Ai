package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Theme {

        // ==============================
        // COMMON COLORS
        // ==============================

        public static final String BACKGROUND = "linear-gradient(to bottom right, #FFF9FC, #FFF1F8)";

        public static final String WHITE = "#FFFFFF";
        public static final String PRIMARY = "#E92B83";
        public static final String PRIMARY_LIGHT = "#FCE6F1";
        public static final String TEXT = "#18213D";
        public static final String SECONDARY_TEXT = "#6B7280";
        public static final String BORDER = "#F0E7ED";
        public static final String GREEN = "#21A366";
        public static final String GREEN_LIGHT = "#E7F8EF";
        public static final String ORANGE = "#F59E0B";
        public static final String ORANGE_LIGHT = "#FFF4DE";
        public static final String RED = "#E74C78";
        public static final String RED_LIGHT = "#FDEAF1";

        public static final String FONT = "Arial";

        public static final double WIDTH = 1200;
        public static final double HEIGHT = 750;

        // ==============================
        // MAIN BACKGROUND
        // ==============================

        public static void applyBackground(Region region) {
                region.setStyle(
                                "-fx-background-color: " + BACKGROUND + ";");
        }

        // ==============================
        // TITLE
        // ==============================

        public static Label title(String text) {

                Label label = new Label(text);

                label.setFont(javafx.scene.text.Font.font(
                                FONT,
                                javafx.scene.text.FontWeight.BOLD,
                                22));

                label.setTextFill(Color.web(TEXT));

                return label;
        }

        // ==============================
        // SUB TITLE
        // ==============================

        public static Label subtitle(String text) {

                Label label = new Label(text);

                label.setFont(javafx.scene.text.Font.font(
                                FONT,
                                javafx.scene.text.FontWeight.NORMAL,
                                12));

                label.setTextFill(Color.web(SECONDARY_TEXT));

                return label;
        }

        // ==============================
        // CARD
        // ==============================

        public static VBox card() {

                VBox box = new VBox();

                box.setPadding(new Insets(18));

                box.setStyle(
                                "-fx-background-color: #ecf1f7;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-border-width: 1;");

                return box;
        }

        // ==============================
        // PRIMARY BUTTON
        // ==============================

        public static Button primaryButton(String text) {

                Button button = new Button(text);

                button.setFont(javafx.scene.text.Font.font(
                                FONT,
                                javafx.scene.text.FontWeight.BOLD,
                                12));

                button.setTextFill(Color.WHITE);

                button.setStyle(
                                "-fx-background-color: " + PRIMARY + ";" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 18 10 18;" +
                                                "-fx-cursor: hand;");

                return button;
        }

        // ==============================
        // BACK BUTTON
        // ==============================

        public static Button backButton() {

                Button button = new Button("←  Back to Dashboard");

                button.setFont(javafx.scene.text.Font.font(
                                FONT,
                                javafx.scene.text.FontWeight.BOLD,
                                12));

                button.setTextFill(Color.web(PRIMARY));

                button.setStyle(
                                "-fx-background-color: " + PRIMARY_LIGHT + ";" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 10 16 10 16;" +
                                                "-fx-cursor: hand;");

                button.setOnAction(e -> DoctorDashboard.showDashboard());

                return button;
        }

        // ==============================
        // PAGE HEADER
        // ==============================

        public static VBox pageHeader(
                        String title,
                        String description) {

                VBox box = new VBox(5);

                Label t = title(title);
                Label s = subtitle(description);

                box.getChildren().addAll(t, s);

                return box;
        }

        // ==============================
        // LOGO
        // ==============================

        public static VBox logo() {

                VBox box = new VBox(2);

                box.setAlignment(Pos.CENTER);

                Image logoImage = new Image(
                                "assets/images/logo/logo.png");
                ImageView logoImageView = new ImageView(logoImage);
                logoImageView.setFitHeight(200);
                logoImageView.setFitWidth(200);

                box.getChildren().addAll(logoImageView);

                return box;
        }
}