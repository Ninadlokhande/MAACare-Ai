package com.sigma.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Aboutus extends Application {

    // ================= COLORS =================

    private final String BG = "#FFF7FA";
    private final String WHITE = "#FFFFFF";
    private final String PRIMARY_PINK = "#E91E63";
    private final String DARK_PINK = "#C2185B";
    private final String LIGHT_PINK = "#FCE4EC";
    private final String TEXT = "#1F2937";
    private final String MUTED = "#6B7280";
    private final String BORDER = "#F8BBD0";

    private ImageView core2webImage;

    // ================= START =================

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        VBox main = new VBox(10);

        main.setPadding(
                new Insets(18, 25, 15, 25)
        );

        // ================= HEADER =================

        Label title = new Label("About Us");

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Maa Care AI  •  Supporting better maternal healthcare"
        );

        subtitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox header = new VBox(2);

        header.getChildren().addAll(
                title,
                subtitle
        );

        // ================= SPECIAL THANKS =================

        VBox specialThanks = createSpecialThanks();

        // ================= PROJECT DESCRIPTION =================

        VBox projectDescription = createInfoCard(
                "Project Description",
                "Maa Care AI is a healthcare support system designed " +
                "to help ASHA workers manage and monitor pregnant women " +
                "effectively. It helps maintain beneficiary information, " +
                "health visits and important pregnancy-related records " +
                "in one place."
        );

        // ================= TEAM INFORMATION =================

        VBox teamInformation = createInfoCard(
                "Team Information",
                "Maa Care AI is developed with the goal of using " +
                "technology to support maternal healthcare and make " +
                "the work of ASHA workers easier and more organized."
        );

        HBox row1 = new HBox(10);

        row1.getChildren().addAll(
                projectDescription,
                teamInformation
        );

        HBox.setHgrow(
                projectDescription,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                teamInformation,
                Priority.ALWAYS
        );

        // ================= INSTRUCTORS =================

        VBox instructors = createNamesCard(
                "Thanks to Instructors",
                "Sachin Sir",
                "Pramod Sir",
                "Akshay Sir"
        );

        // ================= SUPER MENTORS =================

        VBox superMentors = createNamesCard(
                "Thanks to Super Mentors",
                "Shiv Sir",
                "Subodh Sir"
        );

        HBox row2 = new HBox(10);

        row2.getChildren().addAll(
                instructors,
                superMentors
        );

        HBox.setHgrow(
                instructors,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                superMentors,
                Priority.ALWAYS
        );

        // ================= OUR TEAM =================

        VBox ourTeam = createNamesCard(
                "Our Team",
                "Ninad",
                "Priyanka",
                "Sakshi",
                "Shivani",
                "Sneha"
        );

        // ================= MENTORS =================

        VBox mentors = createNamesCard(
                "Mentors & Team Leads",
                "Mentors",
                "Team Leads"
        );

        HBox row3 = new HBox(10);

        row3.getChildren().addAll(
                ourTeam,
                mentors
        );

        HBox.setHgrow(
                ourTeam,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                mentors,
                Priority.ALWAYS
        );

        // ================= ADD ALL =================

        main.getChildren().addAll(
                header,
                specialThanks,
                row1,
                row2,
                row3
        );

        root.setCenter(main);

        // ================= SCENE =================

        Scene scene = new Scene(
                root,
                1200,
                680
        );

        stage.setTitle(
                "Maa Care AI - About Us"
        );

        stage.setScene(scene);

        stage.show();
    }

    // =========================================================
    // SPECIAL THANKS CARD
    // =========================================================

    private VBox createSpecialThanks() {

        VBox card = new VBox(7);

        card.setPadding(
                new Insets(12, 18, 12, 18)
        );

        card.setPrefHeight(145);
        card.setMinHeight(145);
        card.setMaxHeight(145);

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;"
        );

        Label heading = new Label(
                "Special Thanks"
        );

        heading.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_PINK + ";"
        );

        // ================= LEFT SIDE =================

        Label shashiSir = new Label(
                "Shashi Sir"
        );

        shashiSir.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label core2web = new Label(
                "Core2Web"
        );

        core2web.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_PINK + ";"
        );

        Button uploadButton = new Button(
                "Upload Core2Web Photo"
        );

        uploadButton.setStyle(
                "-fx-background-color: " + PRIMARY_PINK + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 14;" +
                "-fx-cursor: hand;"
        );

        uploadButton.setOnAction(
                event -> uploadPhoto()
        );

        VBox left = new VBox(
                5,
                shashiSir,
                core2web,
                uploadButton
        );

        left.setAlignment(
                Pos.CENTER_LEFT
        );

        // ================= PHOTO AREA =================

        core2webImage = new ImageView();

        core2webImage.setFitWidth(240);
        core2webImage.setFitHeight(105);

        core2webImage.setPreserveRatio(true);

        StackPane photoBox = new StackPane();

        photoBox.setPrefWidth(285);
        photoBox.setPrefHeight(115);

        photoBox.setMinWidth(285);
        photoBox.setMinHeight(115);

        photoBox.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 12;"
        );

        Label photoLabel = new Label(
                "Core2Web Photo"
        );

        photoLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_PINK + ";"
        );

        photoBox.getChildren().add(
                photoLabel
        );

        // Image initially above the label
        photoBox.getChildren().add(
                core2webImage
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        HBox content = new HBox(
                15,
                left,
                space,
                photoBox
        );

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        card.getChildren().addAll(
                heading,
                content
        );

        return card;
    }

    // =========================================================
    // UPLOAD PHOTO
    // =========================================================

    private void uploadPhoto() {

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Select Core2Web Photo"
        );

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File file =
                fileChooser.showOpenDialog(
                        core2webImage
                                .getScene()
                                .getWindow()
                );

        if (file != null) {

            Image image = new Image(
                    file.toURI().toString()
            );

            core2webImage.setImage(
                    image
            );
        }
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private VBox createInfoCard(
            String headingText,
            String description
    ) {

        VBox card = new VBox(6);

        card.setPadding(
                new Insets(11, 15, 11, 15)
        );

        card.setPrefHeight(100);
        card.setMinHeight(100);
        card.setMaxHeight(100);

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-background-radius: 13;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 13;"
        );

        Label heading = new Label(
                headingText
        );

        heading.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_PINK + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        card.getChildren().addAll(
                heading,
                descriptionLabel
        );

        return card;
    }

    // =========================================================
    // NAMES CARD
    // =========================================================

    private VBox createNamesCard(
            String headingText,
            String... names
    ) {

        VBox card = new VBox(8);

        card.setPadding(
                new Insets(11, 15, 11, 15)
        );

        card.setPrefHeight(88);
        card.setMinHeight(88);
        card.setMaxHeight(88);

        card.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-background-radius: 13;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 13;"
        );

        Label heading = new Label(
                headingText
        );

        heading.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK_PINK + ";"
        );

        HBox namesBox = new HBox(18);

        namesBox.setAlignment(
                Pos.CENTER_LEFT
        );

        for (String name : names) {

            Label nameLabel =
                    new Label("• " + name);

            nameLabel.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + TEXT + ";"
            );

            namesBox.getChildren().add(
                    nameLabel
            );
        }

        card.getChildren().addAll(
                heading,
                namesBox
        );

        return card;
    }

    // =========================================================
    // MAIN
    // =========================================================

    
}