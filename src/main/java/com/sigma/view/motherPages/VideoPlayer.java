package com.sigma.view.motherPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;

public class VideoPlayer {

    private static final String PINK = "#E84A87";
    private static final String DARK = "#24234F";
    private static final String PURPLE = "#9B4DCC";
    private static final String TEXT_GRAY = "#77778D";

    private final VideoModel video;

    public VideoPlayer(VideoModel video) {
        this.video = video;
    }

    public void show() {

        Stage stage = new Stage();

        stage.setTitle(
                "MaaCare AI - " + video.getTitle()
        );

        stage.initModality(
                Modality.APPLICATION_MODAL
        );

        stage.setResizable(false);

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: white;"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox(14);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(18, 22, 18, 22)
        );

        header.setStyle(
                "-fx-background-color: linear-gradient(to right, #FFEAF3, #F3ECFF);"
                + "-fx-border-color: #F5C7DB;"
                + "-fx-border-width: 0 0 1 0;"
        );

        FontAwesomeIconView filmIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.FILM
                );

        filmIcon.setGlyphSize(28);

        filmIcon.setFill(
                Color.web(PINK)
        );

        VBox titleBox = new VBox(4);

        Label title =
                new Label(
                        video.getTitle()
                );

        title.setStyle(
                "-fx-font-size: 18px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + DARK + ";"
        );

        Label category =
                new Label(
                        video.getCategory()
                        + "  •  "
                        + video.getQuality()
                );

        category.setStyle(
                "-fx-font-size: 12px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + PURPLE + ";"
        );

        titleBox.getChildren().addAll(
                title,
                category
        );

        header.getChildren().addAll(
                filmIcon,
                titleBox
        );

        root.setTop(header);

        // =====================================================
        // CENTER
        // =====================================================

        VBox content = new VBox(18);

        content.setAlignment(
                Pos.CENTER
        );

        content.setPadding(
                new Insets(45, 50, 45, 50)
        );

        content.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFF8FC, #F5EEFF);"
        );

        FontAwesomeIconView playIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.PLAY
                );

        playIcon.setGlyphSize(60);

        playIcon.setFill(
                Color.web(PINK)
        );

        Label watchTitle =
                new Label(
                        "Watch this video"
                );

        watchTitle.setStyle(
                "-fx-font-size: 23px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + DARK + ";"
        );

        Label description =
                new Label(
                        "The video will open securely in YouTube."
                );

        description.setStyle(
                "-fx-font-size: 14px;"
                + "-fx-text-fill: " + TEXT_GRAY + ";"
        );

        Label quality =
                new Label(
                        "Available quality: "
                        + video.getQuality()
                );

        quality.setStyle(
                "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: " + PURPLE + ";"
        );

        // =====================================================
        // WATCH BUTTON
        // =====================================================

        Button watchButton =
                new Button(
                        "Watch on YouTube"
                );

        FontAwesomeIconView youtubeIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.YOUTUBE_PLAY
                );

        youtubeIcon.setGlyphSize(13);

        youtubeIcon.setFill(
                Color.WHITE
        );

        watchButton.setGraphic(
                youtubeIcon
        );

        watchButton.setTextFill(
                Color.WHITE
        );

        watchButton.setStyle(
                "-fx-background-color: linear-gradient(to right, "
                + PINK + ", " + PURPLE + ");"
                + "-fx-background-radius: 22;"
                + "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 11 25 11 25;"
                + "-fx-cursor: hand;"
        );

        watchButton.setOnMouseEntered(e -> {

            watchButton.setStyle(
                    "-fx-background-color: " + DARK + ";"
                    + "-fx-background-radius: 22;"
                    + "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-padding: 11 25 11 25;"
                    + "-fx-cursor: hand;"
            );
        });

        watchButton.setOnMouseExited(e -> {

            watchButton.setStyle(
                    "-fx-background-color: linear-gradient(to right, "
                    + PINK + ", " + PURPLE + ");"
                    + "-fx-background-radius: 22;"
                    + "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-padding: 11 25 11 25;"
                    + "-fx-cursor: hand;"
            );
        });

        watchButton.setOnAction(e -> {

            openYouTube();

        });

        content.getChildren().addAll(
                playIcon,
                watchTitle,
                description,
                quality,
                watchButton
        );

        root.setCenter(content);

        // =====================================================
        // BOTTOM BAR
        // =====================================================

        HBox bottomBar =
                new HBox();

        bottomBar.setAlignment(
                Pos.CENTER_RIGHT
        );

        bottomBar.setPadding(
                new Insets(12, 20, 12, 20)
        );

        bottomBar.setStyle(
                "-fx-background-color: white;"
                + "-fx-border-color: #E7DCE8;"
                + "-fx-border-width: 1 0 0 0;"
        );

        Button closeButton =
                new Button(
                        "Close"
                );

        FontAwesomeIconView closeIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.CLOSE
                );

        closeIcon.setGlyphSize(11);

        closeButton.setGraphic(
                closeIcon
        );

        closeButton.setTextFill(
                Color.WHITE
        );

        closeButton.setStyle(
                "-fx-background-color: linear-gradient(to right, "
                + PINK + ", " + PURPLE + ");"
                + "-fx-background-radius: 20;"
                + "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 8 18 8 18;"
                + "-fx-cursor: hand;"
        );

        closeButton.setOnAction(
                e -> stage.close()
        );

        bottomBar.getChildren().add(
                closeButton
        );

        root.setBottom(
                bottomBar
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        700,
                        500
                );

        stage.setScene(scene);

        stage.show();
    }

    // =========================================================
    // OPEN YOUTUBE
    // =========================================================

    private void openYouTube() {

        try {

            String url =
                    video.getYouTubeUrl();

            if (Desktop.isDesktopSupported()) {

                Desktop desktop =
                        Desktop.getDesktop();

                if (desktop.isSupported(
                        Desktop.Action.BROWSE
                )) {

                    desktop.browse(
                            new URI(url)
                    );
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}