package com.sigma.view.motherPages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


// =============================================================
// MOTHER EMERGENCY PAGE
// =============================================================

public class MotherEmergency {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";
    private final String RED = "#E53935";
    private final String GREEN = "#3C9A68";


    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createEmergencyPage() {

        VBox page = new VBox();

        page.setFillWidth(true);

        page.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to bottom right, " +
            "#FFFFFF 0%, " +
            "#FFF7FB 55%, " +
            "#F4EDFF 100%);"
        );


        // =====================================================
        // CONTENT
        // =====================================================

        VBox content = new VBox();

        content.setSpacing(20);

        content.setPadding(
            new Insets(
                10,
                30,
                40,
                30
            )
        );


        // =====================================================
        // EMERGENCY BANNER
        // =====================================================

        VBox emergencyBanner =
            createEmergencyBanner();


        // =====================================================
        // QUICK HELP
        // =====================================================

        HBox quickHelp =
            createQuickHelp();


        // =====================================================
        // TWO COLUMN CONTENT
        // =====================================================

        HBox mainLayout =
            new HBox();

        mainLayout.setSpacing(20);


        VBox leftContent =
            new VBox();

        leftContent.setSpacing(20);

        HBox.setHgrow(
            leftContent,
            Priority.ALWAYS
        );


        VBox rightContent =
            new VBox();

        rightContent.setSpacing(20);

        rightContent.setPrefWidth(360);

        rightContent.setMinWidth(330);


        // =====================================================
        // LEFT CARDS
        // =====================================================

        VBox emergencyContacts =
            createEmergencyContactsCard();

        VBox nearbyHospitals =
            createNearbyHospitalsCard();

        VBox emergencySituations =
            createEmergencySituationsCard();


        leftContent.getChildren().addAll(
            emergencyContacts,
            nearbyHospitals,
            emergencySituations
        );


        // =====================================================
        // RIGHT CARDS
        // =====================================================

        VBox ambulance =
            createAmbulanceCard();

        VBox doctorHelp =
            createDoctorHelpCard();

        VBox bloodBank =
            createBloodBankCard();

        VBox emergencyTips =
            createEmergencyTipsCard();


        rightContent.getChildren().addAll(
            ambulance,
            doctorHelp,
            bloodBank,
            emergencyTips
        );


        mainLayout.getChildren().addAll(
            leftContent,
            rightContent
        );


        content.getChildren().addAll(
            emergencyBanner,
            quickHelp,
            mainLayout
        );


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;" +
            "-fx-border-color: transparent;"
        );


        page.getChildren().add(
            scrollPane
        );

        VBox.setVgrow(
            scrollPane,
            Priority.ALWAYS
        );


        return page;
    }


    // =========================================================
    // EMERGENCY BANNER
    // =========================================================

    private VBox createEmergencyBanner() {

        VBox banner =
            new VBox();

        banner.setSpacing(10);

        banner.setPadding(
            new Insets(22)
        );

        banner.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #FFF0F1, #FFF8FA);" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F3C8CC;" +
            "-fx-border-radius: 18;"
        );


        HBox top =
            new HBox();

        top.setAlignment(
            Pos.CENTER_LEFT
        );

        top.setSpacing(15);


        Label icon =
            new Label("🚨");

        icon.setStyle(
            "-fx-font-size: 42px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(5);


        Label title =
            new Label(
                "Emergency Assistance"
            );

        title.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #C62828;"
        );


        Label subtitle =
            new Label(
                "If you or your baby needs urgent medical attention, " +
                "get help immediately."
            );

        subtitle.setWrapText(true);

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #666680;"
        );


        text.getChildren().addAll(
            title,
            subtitle
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        Button emergency =
            createRedButton(
                "🚨  Emergency - 112"
            );


        emergency.setOnAction(e -> {

            showMessage(
                "Emergency Number",
                "Emergency Helpline: 112\n\n" +
                "Please contact emergency services immediately " +
                "if urgent medical help is required."
            );

        });


        top.getChildren().addAll(
            icon,
            text,
            emergency
        );


        Label note =
            new Label(
                "⚠ For serious symptoms, do not wait. " +
                "Seek professional medical help immediately."
            );

        note.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #B34B4B;" +
            "-fx-font-weight: bold;"
        );


        banner.getChildren().addAll(
            top,
            note
        );


        return banner;
    }


    // =========================================================
    // QUICK HELP
    // =========================================================

    private HBox createQuickHelp() {

        HBox box =
            new HBox();

        box.setSpacing(15);


        box.getChildren().addAll(

            createQuickCard(
                "🚑",
                "Ambulance",
                "Emergency transport",
                RED,
                "108"
            ),

            createQuickCard(
                "🏥",
                "Emergency",
                "Emergency services",
                PURPLE,
                "112"
            ),

            createQuickCard(
                "👮",
                "Police",
                "Safety assistance",
                PINK,
                "112"
            ),

            createQuickCard(
                "🔥",
                "Fire & Rescue",
                "Fire emergency",
                GREEN,
                "101"
            )
        );


        return box;
    }


    // =========================================================
    // QUICK CARD
    // =========================================================

    private VBox createQuickCard(
            String emoji,
            String title,
            String description,
            String color,
            String number) {

        VBox card =
            new VBox();

        card.setSpacing(7);

        card.setAlignment(
            Pos.CENTER
        );

        card.setPadding(
            new Insets(15)
        );

        card.setPrefHeight(125);

        HBox.setHgrow(
            card,
            Priority.ALWAYS
        );


        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 16;"
        );


        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 30px;"
        );


        Label name =
            new Label(title);

        name.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + color + ";"
        );


        Label desc =
            new Label(description);

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        Button help =
            new Button(
                "Call " + number
            );


        help.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 15;" +
            "-fx-padding: 5px 12px;"
        );


        help.setOnAction(e -> {

            showMessage(
                title,
                title + " Helpline: " + number +
                "\n\nPlease use this number for emergency assistance."
            );

        });


        card.getChildren().addAll(
            icon,
            name,
            desc,
            help
        );


        return card;
    }


    // =========================================================
    // EMERGENCY CONTACTS
    // =========================================================

    private VBox createEmergencyContactsCard() {

        VBox card =
            createWhiteCard();


        HBox heading =
            createCardHeading(
                "Emergency Contacts",
                FontAwesomeIcon.PHONE
            );


        Label subtitle =
            new Label(
                "Important emergency numbers to keep handy"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        VBox contacts =
            new VBox();

        contacts.setSpacing(10);


        contacts.getChildren().addAll(

            createContactRow(
                "🚑",
                "Ambulance",
                "Emergency medical transport",
                "108"
            ),

            createContactRow(
                "🆘",
                "Emergency",
                "National emergency helpline",
                "112"
            ),

            createContactRow(
                "👮",
                "Police",
                "Immediate safety assistance",
                "112"
            ),

            createContactRow(
                "🔥",
                "Fire & Rescue",
                "Fire or rescue emergency",
                "101"
            ),

            createFamilyContactRow()
        );


        card.getChildren().addAll(
            heading,
            subtitle,
            contacts
        );


        return card;
    }


    // =========================================================
    // CONTACT ROW
    // =========================================================

    private HBox createContactRow(
            String emoji,
            String title,
            String description,
            String number) {

        HBox row =
            new HBox();

        row.setSpacing(12);

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setPadding(
            new Insets(10)
        );

        row.setStyle(
            "-fx-background-color: #FFF9FB;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F1E1E7;" +
            "-fx-border-radius: 12;"
        );


        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 26px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(3);


        Label name =
            new Label(title);

        name.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label desc =
            new Label(
                description + " • " + number
            );

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        text.getChildren().addAll(
            name,
            desc
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        Button call =
            createSmallRedButton(
                "Call " + number
            );


        call.setOnAction(e -> {

            showMessage(
                title,
                "Helpline Number: " + number +
                "\n\nPlease contact the service for assistance."
            );

        });


        row.getChildren().addAll(
            icon,
            text,
            call
        );


        return row;
    }


    // =========================================================
    // FAMILY EMERGENCY CONTACT
    // =========================================================

    private HBox createFamilyContactRow() {

        HBox row =
            new HBox();

        row.setSpacing(12);

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setPadding(
            new Insets(10)
        );

        row.setStyle(
            "-fx-background-color: #FFF9FB;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F1E1E7;" +
            "-fx-border-radius: 12;"
        );


        Label icon =
            new Label("👨‍👩‍👧");

        icon.setStyle(
            "-fx-font-size: 26px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(3);


        Label name =
            new Label(
                "Family Emergency Contact"
            );

        name.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label desc =
            new Label(
                "Add a trusted family member"
            );

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        text.getChildren().addAll(
            name,
            desc
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        Button add =
            createOutlineButton(
                "Add Contact"
            );


        add.setOnAction(e -> {

            showMessage(
                "Emergency Contact",
                "You can add a trusted family member's " +
                "name and phone number here.\n\n" +
                "This can be connected to Firebase later."
            );

        });


        row.getChildren().addAll(
            icon,
            text,
            add
        );


        return row;
    }


    // =========================================================
    // NEARBY HOSPITALS
    // =========================================================

    private VBox createNearbyHospitalsCard() {

        VBox card =
            createWhiteCard();


        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );


        VBox titleBox =
            new VBox();

        titleBox.setSpacing(3);


        Label title =
            new Label(
                "Nearby Hospitals"
            );

        title.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
            new Label(
                "Hospitals that may help during an emergency"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        titleBox.getChildren().addAll(
            title,
            subtitle
        );


        HBox.setHgrow(
            titleBox,
            Priority.ALWAYS
        );


        Button search =
            createOutlineButton(
                "🔍 Search Hospitals"
            );


        search.setOnAction(e -> {

            showMessage(
                "Hospital Search",
                "Nearby hospital search can be connected " +
                "to a location service later."
            );

        });


        heading.getChildren().addAll(
            titleBox,
            search
        );


        VBox hospitals =
            new VBox();

        hospitals.setSpacing(10);


        hospitals.getChildren().addAll(

            createHospitalRow(
                "🏥",
                "Nearby Emergency Hospital",
                "Emergency • 24×7",
                "Available"
            ),

            createHospitalRow(
                "🏥",
                "City General Hospital",
                "Multi-speciality • Emergency",
                "Open"
            ),

            createHospitalRow(
                "🏥",
                "Women's & Child Care Hospital",
                "Maternity • Pediatric",
                "Open"
            )
        );


        card.getChildren().addAll(
            heading,
            hospitals
        );


        return card;
    }


    // =========================================================
    // HOSPITAL ROW
    // =========================================================

    private HBox createHospitalRow(
            String emoji,
            String name,
            String description,
            String status) {

        HBox row =
            new HBox();

        row.setSpacing(12);

        row.setAlignment(
            Pos.CENTER_LEFT
        );

        row.setPadding(
            new Insets(12)
        );

        row.setStyle(
            "-fx-background-color: #FAF7FF;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #E7DDF0;" +
            "-fx-border-radius: 12;"
        );


        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 30px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(4);


        Label hospitalName =
            new Label(name);

        hospitalName.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label hospitalDescription =
            new Label(description);

        hospitalDescription.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        Label available =
            new Label(
                "● " + status
            );

        available.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #3C9A68;" +
            "-fx-font-weight: bold;"
        );


        text.getChildren().addAll(
            hospitalName,
            hospitalDescription,
            available
        );


        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );


        Button directions =
            createOutlineButton(
                "Directions"
            );


            directions.setOnAction(e -> {

                try {
            
                    String query =
                            name + " Pune";
            
                    String url =
                            "https://www.google.com/maps/search/?api=1&query="
                            + java.net.URLEncoder.encode(
                                    query,
                                    java.nio.charset.StandardCharsets.UTF_8
                              );
            
                    java.awt.Desktop
                            .getDesktop()
                            .browse(
                                new java.net.URI(url)
                            );
            
                } catch (Exception ex) {
            
                    showMessage(
                            "Directions",
                            "Unable to open Google Maps."
                    );
                }
            });
        row.getChildren().addAll(
            icon,
            text,
            directions
        );


        return row;
    }


    // =========================================================
    // EMERGENCY SITUATIONS
    // =========================================================

    private VBox createEmergencySituationsCard() {

        VBox card =
            createWhiteCard();


        Label title =
            new Label(
                "When Should You Seek Emergency Help?"
            );

        title.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
            new Label(
                "Seek immediate professional care if you experience:"
            );

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        GridPane grid =
            new GridPane();

        grid.setHgap(12);

        grid.setVgap(12);


        grid.add(
            createSituation(
                "🩸",
                "Heavy bleeding",
                "Unusual or heavy bleeding"
            ),
            0,
            0
        );


        grid.add(
            createSituation(
                "💔",
                "Severe pain",
                "Sudden or severe pain"
            ),
            1,
            0
        );


        grid.add(
            createSituation(
                "😵",
                "Fainting",
                "Loss of consciousness"
            ),
            0,
            1
        );


        grid.add(
            createSituation(
                "🫁",
                "Breathing difficulty",
                "Serious difficulty breathing"
            ),
            1,
            1
        );


        grid.add(
            createSituation(
                "🤕",
                "Severe headache",
                "Sudden severe headache"
            ),
            0,
            2
        );


        grid.add(
            createSituation(
                "👶",
                "Baby concern",
                "Any serious concern about baby"
            ),
            1,
            2
        );


        card.getChildren().addAll(
            title,
            subtitle,
            grid
        );


        return card;
    }


    // =========================================================
    // SITUATION
    // =========================================================

    private VBox createSituation(
            String emoji,
            String title,
            String description) {

        VBox box =
            new VBox();

        box.setSpacing(5);

        box.setPadding(
            new Insets(12)
        );

        box.setPrefWidth(250);

        box.setStyle(
            "-fx-background-color: #FFF9FA;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F2DDE3;" +
            "-fx-border-radius: 12;"
        );


        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 25px;"
        );


        Label name =
            new Label(title);

        name.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #C94B5A;"
        );


        Label desc =
            new Label(description);

        desc.setWrapText(true);

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        box.getChildren().addAll(
            icon,
            name,
            desc
        );


        return box;
    }


    // =========================================================
    // AMBULANCE CARD
    // =========================================================

    private VBox createAmbulanceCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #FFF4F4;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F3D0D0;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Ambulance Assistance",
                FontAwesomeIcon.AMBULANCE
            );


        Label ambulanceIcon =
            new Label("🚑");

        ambulanceIcon.setStyle(
            "-fx-font-size: 45px;"
        );


        Label text =
            new Label(
                "Need urgent transportation?\n" +
                "Call the ambulance emergency number: 108."
            );

        text.setWrapText(true);

        text.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;" +
            "-fx-line-spacing: 4px;"
        );


        Button request =
            createRedButton(
                "🚑  Call Ambulance - 108"
            );


        request.setOnAction(e -> {

            showMessage(
                "Ambulance Assistance",
                "Ambulance Emergency Number: 108\n\n" +
                "Please contact emergency medical transport immediately."
            );

        });


        card.getChildren().addAll(
            heading,
            ambulanceIcon,
            text,
            request
        );


        return card;
    }


    // =========================================================
    // DOCTOR HELP
    // =========================================================

    private VBox createDoctorHelpCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #FFF7FB;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F0D7E3;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Emergency Doctor Help",
                FontAwesomeIcon.USER_MD
            );


        HBox content =
            new HBox();

        content.setSpacing(12);

        content.setAlignment(
            Pos.CENTER_LEFT
        );


        Label doctorIcon =
            new Label("👨‍⚕️");

        doctorIcon.setStyle(
            "-fx-font-size: 42px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(5);


        Label title =
            new Label(
                "Talk to a Doctor"
            );

        title.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label description =
            new Label(
                "Get medical guidance for urgent concerns."
            );

        description.setWrapText(true);

        description.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        Button consult =
            createPinkButton(
                "Consult Doctor"
            );


        consult.setOnAction(e -> {

            showMessage(
                "Doctor Consultation",
                "Doctor consultation option selected.\n\n" +
                "You can connect this button to the doctor " +
                "module later."
            );

        });


        text.getChildren().addAll(
            title,
            description,
            consult
        );


        content.getChildren().addAll(
            doctorIcon,
            text
        );


        card.getChildren().addAll(
            heading,
            content
        );


        return card;
    }


    // =========================================================
    // BLOOD BANK
    // =========================================================

    private VBox createBloodBankCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #FFF7F7;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F1D4D4;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Blood Bank Support",
                FontAwesomeIcon.TINT
            );


        HBox content =
            new HBox();

        content.setSpacing(12);

        content.setAlignment(
            Pos.CENTER_LEFT
        );


        Label blood =
            new Label("🩸");

        blood.setStyle(
            "-fx-font-size: 42px;"
        );


        VBox text =
            new VBox();

        text.setSpacing(5);


        Label title =
            new Label(
                "Find Blood Support"
            );

        title.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label desc =
            new Label(
                "Find nearby blood banks and support centers."
            );

        desc.setWrapText(true);

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #77778D;"
        );


        Button find =
            createSmallRedButton(
                "Find Blood Bank"
            );


        find.setOnAction(e -> {

            showMessage(
                "Blood Bank",
                "Nearby blood bank search can be connected " +
                "to a location service later."
            );

        });


        text.getChildren().addAll(
            title,
            desc,
            find
        );


        content.getChildren().addAll(
            blood,
            text
        );


        card.getChildren().addAll(
            heading,
            content
        );


        return card;
    }


    // =========================================================
    // EMERGENCY TIPS
    // =========================================================

    private VBox createEmergencyTipsCard() {

        VBox card =
            createWhiteCard();


        card.setStyle(
            "-fx-background-color: #F8F1FF;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E4D4F3;" +
            "-fx-border-radius: 18;"
        );


        HBox heading =
            createCardHeading(
                "Emergency Safety Tips",
                FontAwesomeIcon.INFO_CIRCLE
            );


        VBox tips =
            new VBox();

        tips.setSpacing(9);


        tips.getChildren().addAll(

            createTip(
                "Stay calm and ask someone nearby for help."
            ),

            createTip(
                "Keep important medical information accessible."
            ),

            createTip(
                "Do not delay professional medical care."
            ),

            createTip(
                "Keep emergency contacts saved in your phone."
            ),

            createTip(
                "Follow your doctor's emergency instructions."
            )
        );


        card.getChildren().addAll(
            heading,
            tips
        );


        return card;
    }


    // =========================================================
    // TIP
    // =========================================================

    private HBox createTip(
            String text) {

        HBox box =
            new HBox();

        box.setSpacing(8);

        box.setAlignment(
            Pos.CENTER_LEFT
        );


        Label icon =
            new Label("✓");

        icon.setStyle(
            "-fx-text-fill: #7041A5;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );


        Label label =
            new Label(text);

        label.setWrapText(true);

        label.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #55556D;"
        );


        box.getChildren().addAll(
            icon,
            label
        );


        return box;
    }


    // =========================================================
    // CARD HEADING
    // =========================================================

    private HBox createCardHeading(
            String text,
            FontAwesomeIcon iconType) {

        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        heading.setSpacing(10);


        FontAwesomeIconView icon =
            new FontAwesomeIconView(
                iconType
            );

        icon.setSize("18");

        icon.setFill(
            Color.web(PURPLE)
        );


        Label title =
            new Label(text);

        title.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        heading.getChildren().addAll(
            icon,
            title
        );


        return heading;
    }


    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
            new VBox();

        card.setSpacing(12);

        card.setPadding(
            new Insets(18)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );


        return card;
    }


    // =========================================================
    // RED BUTTON
    // =========================================================

    private Button createRedButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: #E53935;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 9px 18px;"
        );


        return button;
    }


    // =========================================================
    // SMALL RED BUTTON
    // =========================================================

    private Button createSmallRedButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: #E53935;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 15;" +
            "-fx-padding: 7px 13px;"
        );


        return button;
    }


    // =========================================================
    // PINK BUTTON
    // =========================================================

    private Button createPinkButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: #E84A87;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 15;" +
            "-fx-padding: 7px 13px;"
        );


        return button;
    }


    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private Button createOutlineButton(
            String text) {

        Button button =
            new Button(text);


        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #7041A5;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #DCC9EC;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 8px 13px;"
        );


        return button;
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message) {

        javafx.scene.control.Alert alert =
            new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.INFORMATION
            );

        alert.setTitle(title);

        alert.setHeaderText(title);

        alert.setContentText(message);

        alert.showAndWait();
    }
}