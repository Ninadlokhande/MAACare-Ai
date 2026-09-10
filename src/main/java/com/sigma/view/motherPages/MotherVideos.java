package com.sigma.view.motherPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class MotherVideos {

    private static final String PINK = "#E84A87";
    private static final String DARK = "#24234F";
    private static final String PURPLE = "#9B4DCC";
    private static final String TEXT_GRAY = "#77778D";

    private final VideoLibrary videoLibrary;

    public MotherVideos() {
        videoLibrary = new VideoLibrary();
    }

    public VBox createVideoPage() {

        VBox page =
                new VBox(22);

        page.setPadding(
                new Insets(
                        5,
                        30,
                        30,
                        30
                )
        );

        page.setFillWidth(true);

        // =====================================================
        // HEADER
        // =====================================================

        VBox heading =
                new VBox(4);

        Label title =
                new Label("Video Library");

        title.setStyle(
                "-fx-font-size:27px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:" + DARK + ";"
        );

        Label subtitle =
                new Label(
                        "Helpful videos for pregnancy, motherhood "
                        + "and baby care"
                );

        subtitle.setStyle(
                "-fx-font-size:15px;"
                + "-fx-text-fill:" + TEXT_GRAY + ";"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // =====================================================
        // INTRO CARD
        // =====================================================

        HBox introCard =
                new HBox(18);

        introCard.setAlignment(
                Pos.CENTER_LEFT
        );

        introCard.setPadding(
                new Insets(20)
        );

        introCard.setPrefHeight(92);

        introCard.setStyle(
                "-fx-background-color:"
                + "linear-gradient(to right,#FFEAF3,#F3ECFF);"
                + "-fx-background-radius:18;"
                + "-fx-border-color:#F5C7DB;"
                + "-fx-border-radius:18;"
        );

        FontAwesomeIconView filmIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.FILM
                );

        filmIcon.setGlyphSize(40);

        filmIcon.setFill(
                Color.web(PINK)
        );

        VBox introText =
                new VBox(5);

        Label introTitle =
                new Label(
                        "Learn. Understand. Care."
                );

        introTitle.setStyle(
                "-fx-font-size:18px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:" + DARK + ";"
        );

        Label introDescription =
                new Label(
                        "Explore carefully selected educational "
                        + "videos for every stage of motherhood."
                );

        introDescription.setWrapText(true);

        introDescription.setStyle(
                "-fx-font-size:13px;"
                + "-fx-text-fill:" + TEXT_GRAY + ";"
        );

        introText.getChildren().addAll(
                introTitle,
                introDescription
        );

        introCard.getChildren().addAll(
                filmIcon,
                introText
        );

        page.getChildren().addAll(
                heading,
                introCard
        );

        // =====================================================
        // CATEGORIES
        // =====================================================

        for (String category :
                videoLibrary.getCategories()) {

            page.getChildren().add(
                    createCategorySection(category)
            );
        }

        return page;
    }

    private VBox createCategorySection(
            String category
    ) {

        VBox section =
                new VBox(12);

        Label categoryTitle =
                new Label(category);

        categoryTitle.setStyle(
                "-fx-font-size:19px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:" + DARK + ";"
        );

        HBox videoRow =
                new HBox(16);

        videoRow.setAlignment(
                Pos.CENTER_LEFT
        );

        videoRow.setPadding(
                new Insets(
                        2,
                        4,
                        10,
                        4
                )
        );

        List<VideoModel> videos =
                videoLibrary.getVideosByCategory(
                        category
                );

        for (VideoModel video : videos) {

            videoRow.getChildren().add(
                    createVideoCard(video)
            );
        }

        ScrollPane scrollPane =
                new ScrollPane(videoRow);

        scrollPane.setPannable(true);

        scrollPane.setFitToHeight(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPrefHeight(300);

        scrollPane.setStyle(
                "-fx-background-color:transparent;"
                + "-fx-background:transparent;"
                + "-fx-border-color:transparent;"
        );

        section.getChildren().addAll(
                categoryTitle,
                scrollPane
        );

        return section;
    }

    private VBox createVideoCard(
            VideoModel video
    ) {

        VBox card =
                new VBox(9);

        card.setPrefWidth(270);
        card.setMinWidth(270);
        card.setMaxWidth(270);

        card.setPrefHeight(275);

        card.setPadding(
                new Insets(10)
        );

        card.setStyle(
                "-fx-background-color:white;"
                + "-fx-background-radius:18;"
                + "-fx-border-color:#E7DCE8;"
                + "-fx-border-radius:18;"
        );

        // =====================================================
        // THUMBNAIL
        // =====================================================

        StackPane thumbnailPane =
                new StackPane();

        thumbnailPane.setPrefSize(
                250,
                140
        );

        thumbnailPane.setMinSize(
                250,
                140
        );

        thumbnailPane.setMaxSize(
                250,
                140
        );

        ImageView thumbnail =
                new ImageView();

        thumbnail.setFitWidth(250);
        thumbnail.setFitHeight(140);

        thumbnail.setPreserveRatio(false);

        thumbnail.setSmooth(true);

        try {

            Image image =
                    new Image(
                            video.getThumbnailUrl(),
                            250,
                            140,
                            false,
                            true,
                            true
                    );

            thumbnail.setImage(image);

        } catch (Exception ignored) {
        }

        thumbnailPane.getChildren().add(
                thumbnail
        );

        // =====================================================
        // PLAY BUTTON
        // =====================================================

        Circle playCircle =
                new Circle(25);

        playCircle.setFill(
                Color.web(PINK)
        );

        FontAwesomeIconView playIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.PLAY
                );

        playIcon.setGlyphSize(16);

        playIcon.setFill(
                Color.WHITE
        );

        thumbnailPane.getChildren().addAll(
                playCircle,
                playIcon
        );

        // =====================================================
        // QUALITY
        // =====================================================

        Label quality =
                new Label(
                        video.getQuality()
                );

        quality.setStyle(
                "-fx-background-color:rgba(36,35,79,0.90);"
                + "-fx-background-radius:8;"
                + "-fx-text-fill:white;"
                + "-fx-font-size:10px;"
                + "-fx-font-weight:bold;"
                + "-fx-padding:4 7 4 7;"
        );

        StackPane.setAlignment(
                quality,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                quality,
                new Insets(8)
        );

        thumbnailPane.getChildren().add(
                quality
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        video.getTitle()
                );

        title.setWrapText(true);

        title.setMaxWidth(250);

        title.setMinHeight(34);

        title.setStyle(
                "-fx-font-size:14px;"
                + "-fx-font-weight:bold;"
                + "-fx-text-fill:" + DARK + ";"
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label description =
                new Label(
                        video.getDescription()
                );

        description.setWrapText(true);

        description.setMaxWidth(250);

        description.setMinHeight(34);

        description.setMaxHeight(40);

        description.setStyle(
                "-fx-font-size:12px;"
                + "-fx-text-fill:" + TEXT_GRAY + ";"
        );

        // =====================================================
        // WATCH BUTTON
        // =====================================================

        Button watchButton =
                new Button(
                        "Watch Video"
                );

        FontAwesomeIconView buttonIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.PLAY
                );

        buttonIcon.setGlyphSize(10);

        buttonIcon.setFill(
                Color.WHITE
        );

        watchButton.setGraphic(
                buttonIcon
        );

        watchButton.setTextFill(
                Color.WHITE
        );

        watchButton.setStyle(
                "-fx-background-color:"
                + "linear-gradient(to right,"
                + PINK + "," + PURPLE + ");"
                + "-fx-background-radius:20;"
                + "-fx-font-size:13px;"
                + "-fx-font-weight:bold;"
                + "-fx-padding:8 16 8 16;"
                + "-fx-cursor:hand;"
        );

        watchButton.setOnAction(
                e -> openVideo(video)
        );

        // =====================================================
        // CARD CLICK
        // =====================================================

        card.setOnMouseClicked(e -> {

            if (e.getClickCount() == 1) {
                openVideo(video);
            }
        });

        // =====================================================
        // HOVER
        // =====================================================

        card.setOnMouseEntered(e -> {

            card.setStyle(
                    "-fx-background-color:#FFF8FC;"
                    + "-fx-background-radius:18;"
                    + "-fx-border-color:" + PINK + ";"
                    + "-fx-border-radius:18;"
            );
        });

        card.setOnMouseExited(e -> {

            card.setStyle(
                    "-fx-background-color:white;"
                    + "-fx-background-radius:18;"
                    + "-fx-border-color:#E7DCE8;"
                    + "-fx-border-radius:18;"
            );
        });

        card.getChildren().addAll(
                thumbnailPane,
                title,
                description,
                watchButton
        );

        return card;
    }

    private void openVideo(
            VideoModel video
    ) {

        VideoPlayer player =
                new VideoPlayer(video);

        player.show();
    }
}