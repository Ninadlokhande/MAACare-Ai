package com.sigma.view.adminpages;

import com.sigma.view.scenesettings;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.stage.Stage;

public class AboutUsPage {

    private Runnable backAction;

    // ============================================================
    // COLORS
    // ============================================================

    private static final String PINK = "#E83E78";
    private static final String DARK_PINK = "#C92E68";
    private static final String LIGHT_PINK = "#FFE8F1";

    private static final String PURPLE = "#7B4DE0";
    private static final String DARK_PURPLE = "#6236C5";
    private static final String LIGHT_PURPLE = "#EEE7FF";

    private static final String DARK_TEXT = "#000000";
    private static final String SECONDARY_TEXT = "#000000";
    private static final String BORDER = "#E4D9EE";
    private static final String WHITE = "#FFFFFF";

    private static final String FONT = "Arial";

    // ============================================================
    // SHASHI SIR IMAGE PATH
    // ============================================================

    // Tu nantar fakt ha path change kar
    private final String SHASHI_SIR_IMAGE =
            "assets/images/shashi_sir.png";

    // ============================================================
    // MAIN METHOD
    // ============================================================

    public void show(Stage stage) {
        show(stage, null);
    }

    public void show(Stage stage, Runnable backAction) {

        this.backAction = backAction;

        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color: #FBF6FA;");

        // ========================================================
        // TOP HEADER
        // ========================================================

        HBox header = createHeader(stage);

        root.setTop(header);

        // ========================================================
        // MAIN CONTENT
        // ========================================================

        VBox content = new VBox(25);

        content.setAlignment(Pos.TOP_CENTER);      // Keep the pink/lavender page background visible behind every card.\n        content.setStyle("-fx-background-color: #FBF6FA;");

        content.setPadding(
                new Insets(35, 60, 50, 60)
        );

        // ========================================================
        // HERO SECTION
        // ========================================================

        VBox hero = createHeroSection();

        // ========================================================
        // PROJECT DESCRIPTION
        // ========================================================

        VBox projectSection =
                createProjectDescription();

        // ========================================================
        // SPECIAL THANKS
        // ========================================================

        VBox specialThanks =
                createSpecialThanks();

        // ========================================================
        // TEAM
        // ========================================================

        VBox teamSection =
                createTeamSection();

        // ========================================================
        // INSTRUCTORS
        // ========================================================

        VBox instructorSection =
                createPeopleSection(
                        "Thanks to Instructors",
                        "Our sincere gratitude to our instructors "
                                + "for their continuous guidance and support.",
                        new String[]{
                                "Sachin Sir",
                                "Pramod Sir",
                                "Akshay Sir"
                        },
                        PINK
                );

        // ========================================================
        // SUPER MENTORS
        // ========================================================

        VBox superMentorSection =
                createPeopleSection(
                        "Thanks to Super Mentors",
                        "We are grateful to our super mentors "
                                + "for their valuable guidance and motivation.",
                        new String[]{
                                "Shiv Sir",
                                "Subodh Sir"
                        },
                        PURPLE
                );

        // ========================================================
        // MENTORS & TEAM LEADS
        // ========================================================

        VBox mentorSection =
                createMentorSection();

        // ========================================================
        // FOOTER
        // ========================================================

        VBox footer =
                createFooter();

        // Add everything

        content.getChildren().addAll(
                hero,
                projectSection,
                specialThanks,
                teamSection,
                instructorSection,
                superMentorSection,
                mentorSection,
                footer
        );

        // ========================================================
        // SCROLL PANE
        // ========================================================

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: #FBF6FA;" +
                "-fx-background: #FBF6FA;" +
                "-fx-border-color: transparent;"
        );

        root.setCenter(scrollPane);

        // ========================================================
        // SCENE
        // ========================================================

        Rectangle2D bounds = scenesettings.rectanguler2d;

        Scene scene =
                new Scene(
                        root,
                        bounds.getWidth(),
                        bounds.getHeight()
                );

        stage.setScene(scene);

        stage.setTitle("MaaCare AI - About Us");

        stage.setResizable(true);

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.show();

        // ========================================================
        // FADE ANIMATION
        // ========================================================

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(500),
                        root
                );

        fade.setFromValue(0);
        fade.setToValue(1);

        fade.play();
    }

    // ============================================================
    // HEADER
    // ============================================================

    private HBox createHeader(Stage stage) {

        HBox header = new HBox();

        header.setAlignment(Pos.CENTER_LEFT);

        header.setPadding(
                new Insets(15, 25, 15, 25)
        );

        header.setSpacing(20);

        header.setStyle(
                "-fx-background-color: linear-gradient(to right, #FFFFFF, #FFF3F8, #F6F0FF);" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 2 0;" +
                "-fx-padding: 12px 24px;"
        );

        // --------------------------------------------------------
        // BACK BUTTON
        // --------------------------------------------------------

        Button backButton =
                new Button("←  Back");

        backButton.setPrefHeight(42);

        backButton.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12px;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnMouseEntered(e ->
                backButton.setStyle(
                        "-fx-background-color: " + PINK + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-font-family: Arial;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-cursor: hand;"
                )
        );

        backButton.setOnMouseExited(e ->
                backButton.setStyle(
                        "-fx-background-color: " + LIGHT_PINK + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-font-family: Arial;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-cursor: hand;"
                )
        );

        // --------------------------------------------------------
        // BACK ACTION
        // --------------------------------------------------------

        backButton.setOnAction(e -> {

            if (backAction != null) {
                backAction.run();
            } else if (stage.getScene() != null) {
                stage.close();
            }

        });

        // --------------------------------------------------------
        // LOGO / TITLE
        // --------------------------------------------------------

        VBox titleBox = new VBox(2);

        Label title =
                new Label("MaaCare AI");

        title.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(Color.BLACK);

        Label subtitle =
                new Label("About Us");

        subtitle.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        12
                )
        );

        subtitle.setTextFill(Color.BLACK);

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        header.getChildren().addAll(
                backButton,
                titleBox
        );

        return header;
    }

    // ============================================================
    // HERO SECTION
    // ============================================================

    private VBox createHeroSection() {

        VBox box = new VBox(8);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(30));
        box.setMaxWidth(1000);
        box.setStyle(
                "-fx-background-color: linear-gradient(to right, #FFDCE9, #EEE3FF);" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #E7C4D8;" +
                "-fx-border-radius: 20px;" +
                "-fx-border-width: 1.5px;" +
                "-fx-effect: dropshadow(gaussian, rgba(123,77,224,0.12), 18, 0, 0, 6);"
        );

        Label smallTitle = new Label("WELCOME TO");
        smallTitle.setFont(Font.font(FONT, FontWeight.BOLD, 13));
        smallTitle.setTextFill(Color.BLACK);

        Label title = new Label("MaaCare AI");
        title.setFont(Font.font(FONT, FontWeight.BOLD, 38));
        title.setTextFill(Color.BLACK);

        Label subtitle = new Label(
                "Technology with Care • Innovation with Purpose"
        );
        subtitle.setFont(Font.font(FONT, FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.BLACK);

        Label description = new Label(
                "A healthcare platform designed to connect, support and empower mothers, families and healthcare professionals."
        );
        description.setWrapText(true);
        description.setFont(Font.font(FONT, FontWeight.NORMAL, 14));
        description.setTextFill(Color.BLACK);
        description.setMaxWidth(850);

        box.getChildren().addAll(smallTitle, title, subtitle, description);
        return box;
    }

    // ============================================================
    // PROJECT DESCRIPTION
    // ============================================================

    private VBox createProjectDescription() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "About MaaCare AI",
                        PURPLE
                );

        Label description =
                new Label(
                        "MaaCare AI is a healthcare-focused application "
                                + "designed to support mothers, babies and "
                                + "healthcare professionals throughout the "
                                + "pregnancy and early childcare journey. "
                                + "The project aims to provide accessible, "
                                + "organized and user-friendly healthcare "
                                + "information and services through technology."
                );

        description.setWrapText(true);

        description.setMaxWidth(950);

        description.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        15
                )
        );

        description.setTextFill(Color.BLACK);

        description.setLineSpacing(5);

        card.getChildren().addAll(
                heading,
                description
        );

        return card;
    }

    // ============================================================
    // SPECIAL THANKS
    // ============================================================

    private VBox createSpecialThanks() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Special Thanks",
                        PINK
                );

        HBox content =
                new HBox(30);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        // --------------------------------------------------------
        // IMAGE
        // --------------------------------------------------------

        StackPane imageContainer =
                new StackPane();

        imageContainer.setPrefSize(
                150,
                150
        );

        imageContainer.setMaxSize(
                150,
                150
        );

        try {

            Image image =
                    new Image(
                            SHASHI_SIR_IMAGE,
                            130,
                            130,
                            true,
                            true
                    );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(130);
            imageView.setFitHeight(130);

            imageView.setPreserveRatio(true);

            Circle clip =
                    new Circle(
                            65,
                            65,
                            65
                    );

            imageView.setClip(clip);

            imageContainer.getChildren()
                    .add(imageView);

        } catch (Exception ex) {

            Label placeholder =
                    new Label("Photo");

            placeholder.setFont(
                    Font.font(
                            FONT,
                            FontWeight.BOLD,
                            16
                    )
            );

            placeholder.setTextFill(Color.BLACK);

            imageContainer.getChildren()
                    .add(placeholder);
        }

        imageContainer.setStyle(
                "-fx-background-color: " +
                        LIGHT_PINK + ";" +
                "-fx-background-radius: 75px;" +
                "-fx-border-color: " +
                        PINK + ";" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 75px;"
        );

        // --------------------------------------------------------
        // TEXT
        // --------------------------------------------------------

        VBox textBox =
                new VBox(8);

        Label thanks =
                new Label(
                        "A Heartfelt Thank You"
                );

        thanks.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        22
                )
        );

        thanks.setTextFill(Color.BLACK);

        Label shashi =
                new Label(
                        "Shashi Sir"
                );

        shashi.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        18
                )
        );

        shashi.setTextFill(Color.BLACK);

        Label core2web =
                new Label(
                        "Core2Web"
                );

        core2web.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        16
                )
        );

        core2web.setTextFill(Color.BLACK);

        Label message =
                new Label(
                        "Thank you for your valuable guidance, "
                                + "support, encouragement and for helping "
                                + "us turn our ideas into reality."
                );

        message.setWrapText(true);

        message.setMaxWidth(700);

        message.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        14
                )
        );

        message.setTextFill(Color.BLACK);

        textBox.getChildren().addAll(
                thanks,
                shashi,
                core2web,
                message
        );

        content.getChildren().addAll(
                imageContainer,
                textBox
        );

        card.getChildren().addAll(
                heading,
                content
        );

        return card;
    }

    // ============================================================
    // TEAM SECTION
    // ============================================================

    private VBox createTeamSection() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Our Team",
                        PURPLE
                );

        Label subtitle =
                new Label(
                        "Meet the team behind MaaCare AI"
                );

        subtitle.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(Color.BLACK);

        VBox teamList =
                new VBox(10);

        teamList.setAlignment(
                Pos.CENTER_LEFT
        );

        String[] members = {
                "Ninad Chandrakant Lokhande",
                "Priyanka Govind Gadekar",
                "Sakshi Dattatray Rajdev",
                "Sneha Namdeo Thombare",
                "Shivani Sunil Thombare"
        };

        for (int i = 0; i < members.length; i++) {

            HBox member =
                    createNameCard(
                            (i + 1) + ".",
                            members[i],
                            i % 2 == 0
                                    ? LIGHT_PINK
                                    : LIGHT_PURPLE
                    );

            teamList.getChildren()
                    .add(member);
        }

        card.getChildren().addAll(
                heading,
                subtitle,
                teamList
        );

        return card;
    }

    // ============================================================
    // INSTRUCTORS / SUPER MENTORS
    // ============================================================

    private VBox createPeopleSection(
            String title,
            String subtitleText,
            String[] names,
            String accentColor) {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        title,
                        accentColor
                );

        Label subtitle =
                new Label(subtitleText);

        subtitle.setWrapText(true);

        subtitle.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(Color.BLACK);

        FlowPane namesPane =
                new FlowPane();

        namesPane.setHgap(15);
        namesPane.setVgap(15);

        namesPane.setAlignment(
                Pos.CENTER_LEFT
        );

        for (String name : names) {

            VBox personCard =
                    new VBox();

            personCard.setAlignment(
                    Pos.CENTER
            );

            personCard.setPrefWidth(220);

            personCard.setPrefHeight(70);

            personCard.setStyle(
                    "-fx-background-color: " +
                            (accentColor.equals(PINK)
                                    ? "#FFE4EF"
                                    : "#EEE5FF") + ";" +

                    "-fx-background-radius: 14px;" +

                    "-fx-border-color: " +
                            accentColor + ";" +

                    "-fx-border-radius: 14px;" +

                    "-fx-border-width: 1.5px;" +
                    "-fx-effect: dropshadow(gaussian, rgba(80,50,100,0.08), 8, 0, 0, 2);"
            );

            Label nameLabel =
                    new Label(name);

            nameLabel.setFont(
                    Font.font(
                            FONT,
                            FontWeight.BOLD,
                            15
                    )
            );

            nameLabel.setTextFill(Color.BLACK);

            personCard.getChildren()
                    .add(nameLabel);

            namesPane.getChildren()
                    .add(personCard);
        }

        card.getChildren().addAll(
                heading,
                subtitle,
                namesPane
        );

        return card;
    }

    // ============================================================
    // MENTORS & TEAM LEADS
    // ============================================================

    private VBox createMentorSection() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Thanks to Mentors & Team Leads",
                        PINK
                );

        Label text =
                new Label(
                        "We extend our sincere gratitude to all our "
                                + "mentors and team leads who supported us "
                                + "throughout the project journey. Their "
                                + "guidance, feedback, teamwork and "
                                + "motivation helped us continuously improve "
                                + "and complete MaaCare AI successfully."
                );

        text.setWrapText(true);

        text.setMaxWidth(950);

        text.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        15
                )
        );

        text.setTextFill(Color.BLACK);

        text.setLineSpacing(5);

        card.getChildren().addAll(
                heading,
                text
        );

        return card;
    }

    // ============================================================
    // FOOTER
    // ============================================================

    private VBox createFooter() {

        VBox footer =
                new VBox(8);

        footer.setAlignment(
                Pos.CENTER
        );

        footer.setPadding(
                new Insets(25, 10, 20, 10)
        );
        footer.setStyle(
                "-fx-background-color: linear-gradient(to right, #FFF0F6, #F1E9FF);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18px;" +
                "-fx-border-width: 1px;"
        );

        Label line =
                new Label(
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
                );

        line.setTextFill(Color.BLACK);

        Label title =
                new Label(
                        "MaaCare AI"
                );

        title.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        20
                )
        );

        title.setTextFill(Color.BLACK);

        Label tagline =
                new Label(
                        "Care • Connect • Empower"
                );

        tagline.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        13
                )
        );

        tagline.setTextFill(Color.BLACK);

        Label copyright =
                new Label(
                        "Made with dedication by Team MaaCare AI"
                );

        copyright.setFont(
                Font.font(
                        FONT,
                        FontWeight.NORMAL,
                        12
                )
        );

        copyright.setTextFill(Color.BLACK);

        footer.getChildren().addAll(
                line,
                title,
                tagline,
                copyright
        );

        return footer;
    }

    // ============================================================
    // COMMON CARD
    // ============================================================

    private VBox createCard() {

        VBox card =
                new VBox(15);

        card.setMaxWidth(1000);

        card.setPadding(
                new Insets(25)
        );

        card.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFFFFF, #FFF8FC);" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #E8D7E8;" +
                "-fx-border-radius: 20px;" +
                "-fx-border-width: 1.2px;" +
                "-fx-effect: dropshadow(gaussian, rgba(80,50,100,0.12), 16, 0, 0, 5);"
        );

        return card;
    }

    // ============================================================
    // SECTION HEADING
    // ============================================================

    private Label createSectionHeading(
            String text,
            String color) {

        Label heading =
                new Label(text);

        heading.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        23
                )
        );

        heading.setTextFill(Color.BLACK);
        heading.setStyle(
                "-fx-background-color: " + (color.equals(PURPLE) ? LIGHT_PURPLE : LIGHT_PINK) + ";" +
                "-fx-border-color: " + color + ";" +
                "-fx-border-width: 0 0 0 5px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-padding: 8px 14px;"
        );

        return heading;
    }

    // ============================================================
    // TEAM NAME CARD
    // ============================================================

    private HBox createNameCard(
            String number,
            String name,
            String backgroundColor) {

        HBox box =
                new HBox(15);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(12, 18, 12, 18)
        );

        box.setMaxWidth(900);

        box.setStyle(
                "-fx-background-color: " + backgroundColor + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + (backgroundColor.equals(LIGHT_PINK) ? "#F2A5C2" : "#BCA0EE") + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1.2px;"
        );

        Label numberLabel =
                new Label(number);

        numberLabel.setMinWidth(30);

        numberLabel.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        15
                )
        );

        numberLabel.setTextFill(Color.BLACK);

        Label nameLabel =
                new Label(name);

        nameLabel.setFont(
                Font.font(
                        FONT,
                        FontWeight.BOLD,
                        15
                )
        );

        nameLabel.setTextFill(Color.BLACK);

        box.getChildren().addAll(
                numberLabel,
                nameLabel
        );

        return box;
    }
}