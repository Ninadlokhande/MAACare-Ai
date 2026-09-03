
package com.sigma.view;

import com.sigma.view.adminpages.AdminDashboard;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Welcomepage extends Application {

    // =========================================================
    // COMMON STAGE
    // =========================================================

    public static Stage stage;

    private Scene scene;

    private SVGPath wave1;
    private SVGPath wave2;

    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        primaryStage.setTitle("MaaCare AI");

        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(650);
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
                        ");");

        // =========================================================
        // BACKGROUND GLOW 1
        // =========================================================

        Circle glow1 = new Circle();

        glow1.radiusProperty().bind(
                root.heightProperty().multiply(0.30));

        glow1.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#FFD1E3", 0.55)),
                        new Stop(
                                1,
                                Color.TRANSPARENT)));

        StackPane.setAlignment(
                glow1,
                Pos.TOP_LEFT);

        // =========================================================
        // BACKGROUND GLOW 2
        // =========================================================

        Circle glow2 = new Circle();

        glow2.radiusProperty().bind(
                root.heightProperty().multiply(0.25));

        glow2.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#DCCBFF", 0.50)),
                        new Stop(
                                1,
                                Color.TRANSPARENT)));

        StackPane.setAlignment(
                glow2,
                Pos.BOTTOM_RIGHT);

        // =========================================================
        // WAVE 1
        // =========================================================

        wave1 = new SVGPath();

        wave1.setFill(
                new LinearGradient(
                        0,
                        0,
                        1,
                        0,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#F54B87", 0.40)),
                        new Stop(
                                0.5,
                                Color.web("#E78BC0", 0.30)),
                        new Stop(
                                1,
                                Color.web("#9B4DCC", 0.40))));

        // =========================================================
        // WAVE 2
        // =========================================================

        wave2 = new SVGPath();

        wave2.setFill(
                new LinearGradient(
                        0,
                        0,
                        1,
                        0,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#FFB5D0", 0.30)),
                        new Stop(
                                0.5,
                                Color.web("#E4B8F0", 0.25)),
                        new Stop(
                                1,
                                Color.web("#B99BEA", 0.30))));

        // =========================================================
        // MAIN BORDERPANE
        // =========================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(
                new Insets(
                        30,
                        60,
                        30,
                        60));

        // =========================================================
        // LOGO
        // =========================================================

        var logoResource = getClass().getResource(
                "/assets/images/logo/logo.png");

        if (logoResource == null) {

            System.out.println(
                    "ERROR: logo.png not found!");

            return;
        }

        Image logoImage = new Image(
                logoResource.toExternalForm());

        ImageView logoView = new ImageView(logoImage);

        logoView.setPreserveRatio(true);
        logoView.setSmooth(true);

        logoView.fitHeightProperty().bind(
                root.heightProperty().multiply(0.30));

        // =========================================================
        // GET STARTED BUTTON
        // =========================================================

        Button getStartedBtn = new Button(" Get Started");

        getStartedBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #F54B87, #9B4DCC);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 17px;" +
                        "-fx-padding: 14px 45px;" +
                        "-fx-background-radius: 30px;" +
                        "-fx-border-radius: 30px;" +
                        "-fx-cursor: hand;");

        // =========================================================
        // BUTTON HOVER
        // =========================================================

        getStartedBtn.setOnMouseEntered(e -> getStartedBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #E83F7C, #8B42BD);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 17px;" +
                        "-fx-padding: 14px 45px;" +
                        "-fx-background-radius: 30px;" +
                        "-fx-border-radius: 30px;" +
                        "-fx-cursor: hand;"));

        getStartedBtn.setOnMouseExited(e -> getStartedBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #F54B87, #9B4DCC);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 17px;" +
                        "-fx-padding: 14px 45px;" +
                        "-fx-background-radius: 30px;" +
                        "-fx-border-radius: 30px;" +
                        "-fx-cursor: hand;"));

        // =========================================================
        // GET STARTED ACTION
        // =========================================================

        getStartedBtn.setOnAction(e -> {

            try {

                System.out.println(
                        "[WELCOME] Opening Login page...");

                Loginpage loginpage = new Loginpage();

                Scene loginScene = loginpage.gotologinpage();

                if (loginScene != null) {

                    primaryStage.setScene(
                            loginScene);

                    primaryStage.setMaximized(
                            true);

                    primaryStage.show();

                    System.out.println(
                            "[WELCOME] Login page opened successfully.");
                }

            } catch (Exception ex) {

                System.out.println(
                        "[WELCOME] Failed to open Login page.");

                ex.printStackTrace();
            }
        });

        // =========================================================
        // LOGO SHADOW
        // =========================================================

        DropShadow logoShadow = new DropShadow();

        logoShadow.setRadius(25);
        logoShadow.setSpread(0.08);
        logoShadow.setOffsetY(8);

        logoShadow.setColor(
                Color.web(
                        "#C94C91",
                        0.22));

        logoView.setEffect(
                logoShadow);

        // =========================================================
        // MAIN TEXT
        // =========================================================

        Text text1 = new Text("");

        text1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;");

        Text pinkText = new Text(
                "Smart Care for Every Mother & Baby");

        pinkText.setStyle(
                "-fx-fill: #E84A87;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 36px;");

        Text text2 = new Text("");

        text2.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;");

        TextFlow annotation = new TextFlow(
                text1,
                pinkText,
                text2);

        annotation.setTextAlignment(
                TextAlignment.CENTER);

        annotation.setMaxWidth(
                700);

        // =========================================================
        // EXTRA INFORMATION
        // =========================================================

        Text extraInfo = new Text(
                "Track Pregnancy . AI Health Assistant . Hospital Booking .");

        extraInfo.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: normal;");

        Text smallInfo = new Text(
                "Mother Care . Baby Care . Government Schemes");

        smallInfo.setStyle(
                "-fx-fill: #77778D;" +
                        "-fx-font-size: 20px;");

        // =========================================================
        // CONTENT BOX
        // =========================================================

        VBox logoSideBox = new VBox(
                14,
                logoView,
                annotation,
                extraInfo,
                smallInfo,
                getStartedBtn);

        logoSideBox.setAlignment(
                Pos.CENTER);

        logoSideBox.setPadding(
                new Insets(20));

        logoSideBox.setMaxWidth(
                750);

        logoSideBox.setMaxHeight(
                Double.MAX_VALUE);

        // =========================================================
        // RESPONSIVE SPACING
        // =========================================================

        logoSideBox.spacingProperty().bind(
                root.heightProperty().multiply(0.018));

        // =========================================================
        // CENTER CONTENT
        // =========================================================

        borderPane.setCenter(
                logoSideBox);

        // =========================================================
        // ADD BACKGROUND + CONTENT
        // =========================================================

        root.getChildren().addAll(
                glow1,
                glow2,
                wave1,
                wave2,
                borderPane);

        // =========================================================
        // RESPONSIVE WAVES
        // =========================================================

        root.widthProperty().addListener(
                (observable, oldValue, newValue) -> {

                    updateWaves(
                            newValue.doubleValue(),
                            root.getHeight());
                });

        root.heightProperty().addListener(
                (observable, oldValue, newValue) -> {

                    updateWaves(
                            root.getWidth(),
                            newValue.doubleValue());
                });

        // =========================================================
        // LOGO INTRO ANIMATION
        // =========================================================

        logoView.setOpacity(0);

        logoView.setScaleX(0.75);
        logoView.setScaleY(0.75);

        FadeTransition logoFade = new FadeTransition(
                Duration.seconds(1.3),
                logoView);

        logoFade.setFromValue(0);
        logoFade.setToValue(1);

        ScaleTransition logoScale = new ScaleTransition(
                Duration.seconds(1.3),
                logoView);

        logoScale.setFromX(0.75);
        logoScale.setFromY(0.75);

        logoScale.setToX(1);
        logoScale.setToY(1);

        logoScale.setInterpolator(
                Interpolator.EASE_OUT);

        ParallelTransition logoIntro = new ParallelTransition(
                logoFade,
                logoScale);

        // =========================================================
        // FLOATING LOGO
        // =========================================================

        TranslateTransition floating = new TranslateTransition(
                Duration.seconds(3),
                logoView);

        floating.setFromY(0);
        floating.setToY(-10);

        floating.setAutoReverse(true);

        floating.setCycleCount(
                TranslateTransition.INDEFINITE);

        floating.setInterpolator(
                Interpolator.EASE_BOTH);

        logoIntro.setOnFinished(
                event -> floating.play());

        logoIntro.play();

        // =========================================================
        // TEXT ANIMATIONS
        // =========================================================

        annotation.setOpacity(0);
        extraInfo.setOpacity(0);
        smallInfo.setOpacity(0);

        FadeTransition annotationFade = new FadeTransition(
                Duration.seconds(0.9),
                annotation);

        annotationFade.setFromValue(0);
        annotationFade.setToValue(1);

        FadeTransition infoFade = new FadeTransition(
                Duration.seconds(0.9),
                extraInfo);

        infoFade.setFromValue(0);
        infoFade.setToValue(1);

        FadeTransition smallFade = new FadeTransition(
                Duration.seconds(0.9),
                smallInfo);

        smallFade.setFromValue(0);
        smallFade.setToValue(1);

        // =========================================================
        // TEXT DELAY
        // =========================================================

        javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                Duration.seconds(0.8));

        delay.setOnFinished(
                event -> {

                    annotationFade.play();

                    javafx.animation.PauseTransition delay2 = new javafx.animation.PauseTransition(
                            Duration.seconds(0.25));

                    delay2.setOnFinished(
                            event2 -> {

                                infoFade.play();
                                smallFade.play();
                            });

                    delay2.play();
                });

        delay.play();

        // =========================================================
        // WELCOME PAGE SCENE
        // =========================================================

        Scene sc = new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight());

        scene = sc;

        stage.setScene(sc);

        stage.setTitle(
                "MaaCare AI");

        // =========================================================
        // WINDOW SETTINGS
        // =========================================================

        stage.setMinWidth(1500);
        stage.setMinHeight(800);
        stage.setMaximized(true);

        stage.show();

        // =========================================================
        // INITIAL WAVE UPDATE
        // =========================================================

        updateWaves(
                root.getWidth(),
                root.getHeight());
    }

    // =============================================================
    // SHOW WELCOME PAGE
    // =============================================================

    public static void show() {

        try {

            // -----------------------------------------------------
            // If common stage already exists
            // -----------------------------------------------------

            if (stage != null) {

                /*
                 * New WelcomePage scene तयार करण्यासाठी
                 * start() पुन्हा call केला आहे.
                 *
                 * त्यामुळे:
                 * Doctor Dashboard
                 * ↓
                 * Logout
                 * ↓
                 * Welcome Page
                 */

                Welcomepage welcomePage = new Welcomepage();

                welcomePage.start(stage);

                stage.setTitle(
                        "MaaCare AI");

                stage.setMaximized(true);

                stage.show();

                return;
            }

            // -----------------------------------------------------
            // If stage does not exist
            // -----------------------------------------------------

            Stage newStage = new Stage();

            Welcomepage welcomePage = new Welcomepage();

            welcomePage.start(newStage);

        } catch (Exception ex) {

            System.out.println(
                    "[WELCOME] Failed to show Welcome Page.");

            ex.printStackTrace();
        }
    }

    // =============================================================
    // RESPONSIVE WAVE METHOD
    // =============================================================

    private void updateWaves(
            double width,
            double height) {

        if (width <= 0 ||
                height <= 0) {
            return;
        }

        // =========================================================
        // WAVE 1
        // =========================================================

        double startY1 = height * 0.77;

        wave1.setContent(
                "M 0 " +
                        startY1 +

                        " C " +
                        (width * 0.16) +
                        " " +
                        (height * 0.65) +

                        ", " +
                        (width * 0.30) +
                        " " +
                        (height * 0.88) +

                        ", " +
                        (width * 0.50) +
                        " " +
                        (height * 0.73) +

                        " C " +
                        (width * 0.67) +
                        " " +
                        (height * 0.60) +

                        ", " +
                        (width * 0.83) +
                        " " +
                        (height * 0.84) +

                        ", " +
                        width +
                        " " +
                        (height * 0.69) +

                        " L " +
                        width +
                        " " +
                        height +

                        " L 0 " +
                        height +

                        " Z");

        // =========================================================
        // WAVE 2
        // =========================================================

        double startY2 = height * 0.82;

        wave2.setContent(
                "M 0 " +
                        startY2 +

                        " C " +
                        (width * 0.18) +
                        " " +
                        (height * 0.72) +

                        ", " +
                        (width * 0.34) +
                        " " +
                        (height * 0.91) +

                        ", " +
                        (width * 0.53) +
                        " " +
                        (height * 0.78) +

                        " C " +
                        (width * 0.70) +
                        " " +
                        (height * 0.66) +

                        ", " +
                        (width * 0.86) +
                        " " +
                        (height * 0.88) +

                        ", " +
                        width +
                        " " +
                        (height * 0.75) +

                        " L " +
                        width +
                        " " +
                        height +

                        " L 0 " +
                        height +

                        " Z");
    }
}
