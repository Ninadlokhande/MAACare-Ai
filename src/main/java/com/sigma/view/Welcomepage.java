package com.sigma.view;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Welcomepage extends Application {

    // =========================================================
    // COMMON STAGE
    // =========================================================

    public static Stage stage;

    private Scene scene;
    private MediaPlayer mediaPlayer;

    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        primaryStage.setTitle("MaaCare AI");

        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(650);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);

        // =========================================================
        // ROOT
        // =========================================================

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "to bottom right, " +
                        "#FFFFFF 0%, " +
                        "#FFF4F8 50%, " +
                        "#F0E7FF 100%" +
                        ");"
        );

        // =========================================================
        // VIDEO
        // =========================================================

        /*
         * Put welcomevideo.mp4 here:
         *
         * src/main/resources/assets/videos/welcomevideo.mp4
         *
         * Because it is inside resources, JavaFX can load it
         * directly using getResource().
         */

        var videoResource = getClass().getResource(
                "/assets/videos/welcomevideo.mp4"
        );

        if (videoResource == null) {

            System.out.println(
                    "[WELCOME] ERROR: welcomevideo.mp4 not found!"
            );

            showVideoError(root);
            return;
        }

        Media media;

        try {

            media = new Media(
                    videoResource.toExternalForm()
            );

        } catch (Exception ex) {

            System.out.println(
                    "[WELCOME] ERROR: Unable to load welcome video."
            );

            ex.printStackTrace();

            showVideoError(root);
            return;
        }

        mediaPlayer = new MediaPlayer(media);

        MediaView mediaView = new MediaView(mediaPlayer);

        // =========================================================
        // VIDEO SIZE
        // =========================================================

        /*
         * The uploaded video is 1280 x 720.
         *
         * The MediaView automatically follows the application
         * window while preserving the video's aspect ratio.
         */

        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        mediaView.fitWidthProperty().bind(
                root.widthProperty()
        );

        mediaView.fitHeightProperty().bind(
                root.heightProperty()
        );

        // =========================================================
        // BLACK BACKGROUND BEHIND VIDEO
        // =========================================================

        StackPane videoContainer = new StackPane();

        videoContainer.setStyle(
                "-fx-background-color: black;"
        );

        videoContainer.getChildren().add(
                mediaView
        );

        // =========================================================
        // OPTIONAL LOADING TEXT
        // =========================================================

        Label loadingLabel = new Label(
                "Loading MaaCare AI..."
        );

        loadingLabel.setTextFill(Color.WHITE);

        loadingLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        loadingLabel.setStyle(
                "-fx-background-color: rgba(0,0,0,0.35);" +
                        "-fx-background-radius: 20px;" +
                        "-fx-padding: 10px 20px;"
        );

        StackPane.setAlignment(
                loadingLabel,
                Pos.CENTER
        );

        root.getChildren().addAll(
                videoContainer,
                loadingLabel
        );

        // =========================================================
        // MEDIA READY
        // =========================================================

        mediaPlayer.setOnReady(() -> {

            System.out.println(
                    "[WELCOME] Welcome video loaded."
            );

            loadingLabel.setVisible(false);

            /*
             * Start the video automatically.
             */

            mediaPlayer.play();
        });

        // =========================================================
        // VIDEO END
        // =========================================================

        mediaPlayer.setOnEndOfMedia(() -> {

            System.out.println(
                    "[WELCOME] Video finished."
            );

            openLoginPage(primaryStage);
        });

        // =========================================================
        // VIDEO ERROR
        // =========================================================

        mediaPlayer.setOnError(() -> {

            System.out.println(
                    "[WELCOME] Video playback error."
            );

            if (mediaPlayer.getError() != null) {
                mediaPlayer.getError().printStackTrace();
            }

            /*
             * If the video cannot play, do not leave the user
             * stuck on the Welcome page.
             */

            javafx.application.Platform.runLater(() ->
                    openLoginPage(primaryStage)
            );
        });

        // =========================================================
        // SCENE
        // =========================================================

        scene = new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight()
        );

        primaryStage.setScene(scene);

        primaryStage.setTitle(
                "MaaCare AI"
        );

        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(650);
        primaryStage.setMaximized(true);

        primaryStage.show();

        System.out.println(
                "[WELCOME] Welcome page opened."
        );
    }

    // =============================================================
    // OPEN LOGIN PAGE
    // =============================================================

    private void openLoginPage(Stage primaryStage) {

        try {

            /*
             * Fade the Welcome page out first.
             * The Login page is opened only after the fade finishes.
             */

            if (scene != null && scene.getRoot() != null) {

                FadeTransition fadeOut =
                        new FadeTransition(
                                Duration.millis(400),
                                scene.getRoot()
                        );

                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setInterpolator(
                        Interpolator.EASE_BOTH
                );

                fadeOut.setOnFinished(event ->
                        switchToLoginPage(primaryStage)
                );

                fadeOut.play();

            } else {

                switchToLoginPage(primaryStage);
            }

        } catch (Exception ex) {

            System.out.println(
                    "[WELCOME] Failed during transition to Login page."
            );

            ex.printStackTrace();

            switchToLoginPage(primaryStage);
        }
    }

    // =============================================================
    // SWITCH TO LOGIN PAGE WITH FADE + ZOOM
    // =============================================================

    private void switchToLoginPage(Stage primaryStage) {

        try {

            /*
             * Stop and release the video before changing scenes.
             */

            if (mediaPlayer != null) {

                try {
                    mediaPlayer.stop();
                } catch (Exception ignored) {
                }

                try {
                    mediaPlayer.dispose();
                } catch (Exception ignored) {
                }

                mediaPlayer = null;
            }

            System.out.println(
                    "[WELCOME] Opening Login page..."
            );

            Loginpage loginpage =
                    new Loginpage();

            Scene loginScene =
                    loginpage.gotologinpage();

            if (loginScene != null) {

                /*
                 * Start the Login page slightly smaller and invisible.
                 */

                loginScene.getRoot().setOpacity(0.0);
                loginScene.getRoot().setScaleX(0.98);
                loginScene.getRoot().setScaleY(0.98);

                primaryStage.setScene(loginScene);
                primaryStage.setMaximized(true);
                primaryStage.show();

                /*
                 * Fade Login page in.
                 */

                FadeTransition fadeIn =
                        new FadeTransition(
                                Duration.millis(500),
                                loginScene.getRoot()
                        );

                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.setInterpolator(
                        Interpolator.EASE_BOTH
                );

                /*
                 * Subtle zoom-in effect.
                 */

                ScaleTransition scaleIn =
                        new ScaleTransition(
                                Duration.millis(800),
                                loginScene.getRoot()
                        );

                scaleIn.setFromX(0.98);
                scaleIn.setFromY(0.98);
                scaleIn.setToX(1.0);
                scaleIn.setToY(1.0);
                scaleIn.setInterpolator(
                        Interpolator.EASE_OUT
                );

                ParallelTransition transition =
                        new ParallelTransition(
                                fadeIn,
                                scaleIn
                        );

                transition.setOnFinished(event -> {

                    loginScene.getRoot().setOpacity(1.0);
                    loginScene.getRoot().setScaleX(1.0);
                    loginScene.getRoot().setScaleY(1.0);

                    System.out.println(
                            "[WELCOME] Login page opened successfully."
                    );
                });

                transition.play();
            }

        } catch (Exception ex) {

            System.out.println(
                    "[WELCOME] Failed to open Login page."
            );

            ex.printStackTrace();
        }
    }

    // =============================================================
    // SHOW WELCOME PAGE
    // =============================================================

    public static void show() {

        try {

            if (stage != null) {

                Welcomepage welcomePage =
                        new Welcomepage();

                welcomePage.start(stage);

                stage.setTitle(
                        "MaaCare AI"
                );

                stage.setMaximized(
                        true
                );

                stage.show();

                return;
            }

            Stage newStage = new Stage();

            Welcomepage welcomePage =
                    new Welcomepage();

            welcomePage.start(
                    newStage
            );

        } catch (Exception ex) {

            System.out.println(
                    "[WELCOME] Failed to show Welcome Page."
            );

            ex.printStackTrace();
        }
    }

    // =============================================================
    // VIDEO LOAD ERROR SCREEN
    // =============================================================

    private void showVideoError(StackPane root) {

        Label errorLabel = new Label(
                "MaaCare AI\n\nUnable to load the welcome video."
        );

        errorLabel.setTextFill(
                Color.web("#24234F")
        );

        errorLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        errorLabel.setAlignment(
                Pos.CENTER
        );

        errorLabel.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-padding: 35px;"
        );

        root.getChildren().add(
                errorLabel
        );

        scene = new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight()
        );

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        /*
         * Give the application a short moment to display the
         * error and then continue to Login.
         */

        javafx.animation.PauseTransition pause =
                new javafx.animation.PauseTransition(
                        Duration.seconds(1.5)
                );

        pause.setOnFinished(
                event -> openLoginPage(stage)
        );

        pause.play();
    }

    // =============================================================
    // STOP VIDEO WHEN APPLICATION CLOSES
    // =============================================================

    @Override
    public void stop() {

        if (mediaPlayer != null) {

            try {
                mediaPlayer.stop();
            } catch (Exception ignored) {
            }

            try {
                mediaPlayer.dispose();
            } catch (Exception ignored) {
            }

            mediaPlayer = null;
        }
    }
}
