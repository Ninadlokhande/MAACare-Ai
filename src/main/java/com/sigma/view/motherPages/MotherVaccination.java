package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sigma.model.Vaccination;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


// =============================================================
// MOTHER + BABY VACCINATION PAGE
// =============================================================

public class MotherVaccination {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";
    private final String GREEN = "#3C9A68";

    // =========================================================
    // VACCINATION DATA
    // =========================================================

    private final List<Vaccination> vaccinations =
            new ArrayList<>();


    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createVaccinationPage() {

        // Load current vaccination data
        loadVaccinationData();

        VBox page = new VBox();

        page.setFillWidth(true);

        page.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to bottom right, " +
            "#FFFFFF 0%, " +
            "#FFF7FB 55%, " +
            "#F4EDFF 100%);"
        );


        VBox content = new VBox();

        content.setSpacing(20);

        content.setPadding(
            new Insets(
                22,
                30,
                40,
                30
            )
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        content.getChildren().add(
            createPageTitle()
        );


        // =====================================================
        // SUMMARY
        // =====================================================

        content.getChildren().add(
            createSummaryCards()
        );


        // =====================================================
        // UPCOMING
        // =====================================================

        content.getChildren().add(
            createUpcomingVaccination()
        );


        // =====================================================
        // BABY VACCINATION
        // =====================================================

        content.getChildren().add(
            createBabyVaccination()
        );


        // =====================================================
        // MOTHER VACCINATION
        // =====================================================

        content.getChildren().add(
            createMotherVaccination()
        );


        // =====================================================
        // REMINDER
        // =====================================================

        content.getChildren().add(
            createReminder()
        );


        // =====================================================
        // CONSULTATION
        // =====================================================

        content.getChildren().add(
            createConsultation()
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
    // LOAD VACCINATION DATA
    // =========================================================

    private void loadVaccinationData() {

        vaccinations.clear();

        /*
         * IMPORTANT:
         *
         * Actual vaccination data will come from Firebase later.
         *
         * Do NOT put actual due dates here.
         *
         * These are only vaccine definitions for the UI.
         */


        vaccinations.add(
            new Vaccination(
                "baby_bcg",
                "Baby",
                "BCG",
                "Birth Dose",
                null,
                null,
                "Completed",
                "Tuberculosis protection"
            )
        );


        vaccinations.add(
            new Vaccination(
                "baby_opv",
                "Baby",
                "OPV",
                "Birth Dose",
                null,
                null,
                "Completed",
                "Polio protection"
            )
        );


        vaccinations.add(
            new Vaccination(
                "baby_penta",
                "Baby",
                "Pentavalent",
                "Dose 1",
                null,
                null,
                "Upcoming",
                "Protection against diphtheria, tetanus, pertussis and other infections"
            )
        );


        vaccinations.add(
            new Vaccination(
                "baby_rotavirus",
                "Baby",
                "Rotavirus",
                "Dose 1",
                null,
                null,
                "Upcoming",
                "Protection against rotavirus infection"
            )
        );


        vaccinations.add(
            new Vaccination(
                "baby_pcv",
                "Baby",
                "PCV",
                "Dose 1",
                null,
                null,
                "Upcoming",
                "Protection against pneumococcal disease"
            )
        );


        vaccinations.add(
            new Vaccination(
                "mother_td",
                "Mother",
                "Td / Tdap",
                "Recommended Dose",
                null,
                null,
                "Upcoming",
                "Protects mother and baby"
            )
        );


        vaccinations.add(
            new Vaccination(
                "mother_flu",
                "Mother",
                "Influenza",
                "Seasonal",
                null,
                null,
                "Upcoming",
                "Seasonal flu protection"
            )
        );


        vaccinations.add(
            new Vaccination(
                "mother_covid",
                "Mother",
                "COVID-19",
                "As Recommended",
                null,
                null,
                "Consult Doctor",
                "Vaccination according to doctor's recommendation"
            )
        );
    }


    // =========================================================
    // PAGE TITLE
    // =========================================================

    private HBox createPageTitle() {

        HBox box = new HBox();

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setSpacing(15);


        VBox text = new VBox(5);


        Label title =
                new Label(
                        "Vaccination & Immunization"
                );

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        Label subtitle =
                new Label(
                        "Keep track of important vaccinations for you and your baby."
                );

        subtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: " + TEXT_GRAY + ";"
        );


        text.getChildren().addAll(
                title,
                subtitle
        );


        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );


        HBox date = new HBox();

        date.setAlignment(
                Pos.CENTER
        );

        date.setSpacing(7);

        date.setPadding(
                new Insets(10, 15, 10, 15)
        );

        date.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #E7DCE8;" +
                "-fx-border-radius: 12;"
        );


        FontAwesomeIconView calendar =
                new FontAwesomeIconView(
                        FontAwesomeIcon.CALENDAR
                );

        calendar.setSize("17");

        calendar.setFill(
                Color.web(PURPLE)
        );


        Label dateLabel =
                new Label(
                        LocalDate.now().format(
                                DateTimeFormatter.ofPattern(
                                        "dd MMM yyyy"
                                )
                        )
                );

        dateLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        date.getChildren().addAll(
                calendar,
                dateLabel
        );


        box.getChildren().addAll(
                text,
                date
        );


        return box;
    }


    // =========================================================
    // SUMMARY CARDS
    // =========================================================

    private HBox createSummaryCards() {

        HBox row = new HBox(15);


        int upcoming = 0;
        int completed = 0;
        int dueSoon = 0;


        for (Vaccination vaccination : vaccinations) {

            if ("Completed".equalsIgnoreCase(
                    vaccination.getStatus())) {

                completed++;

            } else if ("Due Soon".equalsIgnoreCase(
                    vaccination.getStatus())) {

                dueSoon++;

            } else if ("Upcoming".equalsIgnoreCase(
                    vaccination.getStatus())) {

                upcoming++;
            }
        }


        int total = vaccinations.size();


        row.getChildren().addAll(

                createSummaryCard(
                        "💉",
                        String.valueOf(upcoming),
                        "Upcoming",
                        PINK
                ),

                createSummaryCard(
                        "✓",
                        String.valueOf(completed),
                        "Completed",
                        GREEN
                ),

                createSummaryCard(
                        "⏰",
                        String.valueOf(dueSoon),
                        "Due Soon",
                        "#C7821B"
                ),

                createSummaryCard(
                        "🛡",
                        String.valueOf(total),
                        "Total Vaccines",
                        PURPLE
                )
        );


        return row;
    }


    private VBox createSummaryCard(
            String icon,
            String number,
            String title,
            String color) {

        VBox card = new VBox(5);

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPadding(
                new Insets(16)
        );

        card.setPrefWidth(210);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #E7DCE8;" +
                "-fx-border-radius: 16;"
        );


        HBox top = new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-font-size: 25px;"
        );


        Label numberLabel =
                new Label(number);

        numberLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + color + ";"
        );


        top.getChildren().addAll(
                iconLabel,
                numberLabel
        );


        Label name =
                new Label(title);

        name.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT_GRAY + ";"
        );


        card.getChildren().addAll(
                top,
                name
        );


        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );


        return card;
    }


    // =========================================================
    // UPCOMING VACCINATION
    // =========================================================

    private VBox createUpcomingVaccination() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createCardHeading(
                        "Upcoming Vaccinations",
                        FontAwesomeIcon.CALENDAR
                );


        Label subtitle =
                new Label(
                        "Your next important vaccination appointments"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT_GRAY + ";"
        );


        HBox vaccines =
                new HBox(15);


        for (Vaccination vaccination : vaccinations) {

            if (!"Completed".equalsIgnoreCase(
                    vaccination.getStatus())) {

                vaccines.getChildren().add(
                        createUpcomingCard(
                                vaccination
                        )
                );
            }
        }


        card.getChildren().addAll(
                heading,
                subtitle,
                vaccines
        );


        return card;
    }


    private VBox createUpcomingCard(
            Vaccination vaccination) {

        String color =
                vaccination.getPerson().equalsIgnoreCase("Mother")
                        ? PURPLE
                        : PINK;


        VBox box = new VBox(8);

        box.setPadding(
                new Insets(14)
        );

        box.setPrefWidth(250);

        box.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E8E0EA;" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );


        HBox top = new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label icon =
                new Label(
                        vaccination.getPerson()
                                .equalsIgnoreCase("Mother")
                                ? "🤰"
                                : "👶"
                );

        icon.setStyle(
                "-fx-font-size: 25px;"
        );


        VBox personBox = new VBox();


        Label personLabel =
                new Label(
                        vaccination.getPerson()
                );

        personLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + color + ";" +
                "-fx-font-weight: bold;"
        );


        Label vaccineLabel =
                new Label(
                        vaccination.getVaccineName()
                );

        vaccineLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        personBox.getChildren().addAll(
                personLabel,
                vaccineLabel
        );


        top.getChildren().addAll(
                icon,
                personBox
        );


        Label doseLabel =
                new Label(
                        vaccination.getDose()
                );

        doseLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #55556D;"
        );


        Label dateLabel =
                new Label(
                        vaccination.getDueDate() == null
                                ? "Date will be updated"
                                : "Due: " +
                                  vaccination.getDueDate()
                );

        dateLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #55556D;"
        );


        Label statusLabel =
                new Label(
                        vaccination.getStatus()
                );

        statusLabel.setStyle(
                "-fx-background-color: #FFF0F5;" +
                "-fx-text-fill: " + color + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5px 9px;"
        );


        Button view =
                createSmallButton(
                        "View Details"
                );


        view.setOnAction(e ->
                showVaccinationInfo(
                        vaccination
                )
        );


        box.getChildren().addAll(
                top,
                doseLabel,
                dateLabel,
                statusLabel,
                view
        );


        return box;
    }


    // =========================================================
    // BABY VACCINATION
    // =========================================================

    private VBox createBabyVaccination() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createCardHeading(
                        "👶 Baby Vaccination Schedule",
                        FontAwesomeIcon.HEART
                );


        ComboBox<String> age =
                new ComboBox<>();

        age.getItems().addAll(
                "Birth",
                "6 Weeks",
                "10 Weeks",
                "14 Weeks",
                "6 Months",
                "9 Months",
                "12 Months"
        );

        age.setValue(
                "6 Weeks"
        );

        age.setPrefWidth(145);


        HBox headingRow =
                new HBox();

        headingRow.setAlignment(
                Pos.CENTER_LEFT
        );


        HBox.setHgrow(
                heading,
                Priority.ALWAYS
        );


        headingRow.getChildren().addAll(
                heading,
                age
        );


        VBox vaccineList =
                new VBox(10);


        for (Vaccination vaccination : vaccinations) {

            if ("Baby".equalsIgnoreCase(
                    vaccination.getPerson())) {

                vaccineList.getChildren().add(
                        createVaccineRow(
                                vaccination
                        )
                );
            }
        }


        Button schedule =
                createGradientButton(
                        "View Complete Baby Vaccination Schedule  →"
                );


        schedule.setOnAction(e ->
                showInfo(
                        "Baby Vaccination Schedule",
                        "Complete vaccination schedule will be loaded from your baby's vaccination data."
                )
        );


        card.getChildren().addAll(
                headingRow,
                vaccineList,
                schedule
        );


        return card;
    }


    // =========================================================
    // MOTHER VACCINATION
    // =========================================================

    private VBox createMotherVaccination() {

        VBox card =
                createWhiteCard();


        HBox heading =
                createCardHeading(
                        "🤰 Mother Vaccination",
                        FontAwesomeIcon.HEART
                );


        Label subtitle =
                new Label(
                        "Important vaccines recommended during pregnancy"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT_GRAY + ";"
        );


        GridPane grid =
                new GridPane();

        grid.setHgap(15);

        grid.setVgap(15);


        int column = 0;
        int row = 0;


        for (Vaccination vaccination : vaccinations) {

            if ("Mother".equalsIgnoreCase(
                    vaccination.getPerson())) {

                grid.add(
                        createMotherVaccineCard(
                                vaccination
                        ),
                        column,
                        row
                );


                column++;


                if (column == 2) {
                    column = 0;
                    row++;
                }
            }
        }


        card.getChildren().addAll(
                heading,
                subtitle,
                grid
        );


        return card;
    }


    private VBox createMotherVaccineCard(
            Vaccination vaccination) {

        String color =
                vaccination.getVaccineName()
                        .equalsIgnoreCase("COVID-19")
                        ? GREEN
                        : PINK;


        VBox box =
                new VBox(7);

        box.setPadding(
                new Insets(14)
        );

        box.setStyle(
                "-fx-background-color: #FFF9FC;" +
                "-fx-border-color: #F1DCE6;" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );


        HBox top =
                new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label icon =
                new Label("💉");

        icon.setStyle(
                "-fx-font-size: 25px;"
        );


        Label title =
                new Label(
                        vaccination.getVaccineName()
                );

        title.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        top.getChildren().addAll(
                icon,
                title
        );


        Label desc =
                new Label(
                        vaccination.getDescription()
                );

        desc.setWrapText(true);

        desc.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #666680;"
        );


        Label status =
                new Label(
                        vaccination.getStatus()
                );

        status.setStyle(
                "-fx-text-fill: " + color + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );


        Button consult =
                createOutlineButton(
                        "Check Recommendation"
                );


        consult.setOnAction(e ->
                showVaccinationInfo(
                        vaccination
                )
        );


        box.getChildren().addAll(
                top,
                desc,
                status,
                consult
        );


        return box;
    }


    // =========================================================
    // VACCINE ROW
    // =========================================================

    private HBox createVaccineRow(
            Vaccination vaccination) {

        HBox row =
                new HBox(15);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(12)
        );

        row.setStyle(
                "-fx-background-color: #FAF8FB;" +
                "-fx-background-radius: 12;"
        );


        Label icon =
                new Label("💉");

        icon.setStyle(
                "-fx-font-size: 22px;"
        );


        VBox text =
                new VBox(3);


        Label name =
                new Label(
                        vaccination.getVaccineName()
                );

        name.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        Label desc =
                new Label(
                        vaccination.getDescription()
                );

        desc.setWrapText(true);

        desc.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + TEXT_GRAY + ";"
        );


        text.getChildren().addAll(
                name,
                desc
        );


        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );


        Label statusLabel =
                new Label(
                        vaccination.getStatus()
                );

        statusLabel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + getStatusColor(
                        vaccination.getStatus()
                ) + ";" +
                "-fx-text-fill: " + getStatusColor(
                        vaccination.getStatus()
                ) + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 6px 10px;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );


        Button details =
                createSmallButton(
                        "Details"
                );


        details.setOnAction(e ->
                showVaccinationInfo(
                        vaccination
                )
        );


        row.getChildren().addAll(
                icon,
                text,
                statusLabel,
                details
        );


        return row;
    }


    // =========================================================
    // STATUS COLOR
    // =========================================================

    private String getStatusColor(
            String status) {

        if ("Completed".equalsIgnoreCase(status)) {
            return GREEN;
        }

        if ("Due Soon".equalsIgnoreCase(status)) {
            return "#C7821B";
        }

        if ("Consult Doctor".equalsIgnoreCase(status)) {
            return PURPLE;
        }

        return PINK;
    }


    // =========================================================
    // REMINDER
    // =========================================================

    private HBox createReminder() {

        HBox box =
                new HBox(15);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(18)
        );

        box.setStyle(
                "-fx-background-color: #FFFDF4;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #F1E7BE;" +
                "-fx-border-radius: 16;"
        );


        Label icon =
                new Label("🔔");

        icon.setStyle(
                "-fx-font-size: 30px;"
        );


        VBox text =
                new VBox(4);


        Label title =
                new Label(
                        "Vaccination Reminder"
                );

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C7821B;"
        );


        Label message =
                new Label(
                        "Keep your vaccination record updated and follow the recommended schedule."
                );

        message.setWrapText(true);

        message.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #666680;"
        );


        text.getChildren().addAll(
                title,
                message
        );


        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );


        Button reminder =
                createGradientButton(
                        "Set Reminder"
                );


        reminder.setOnAction(e ->
                showInfo(
                        "Reminder",
                        "Reminder will be connected with your vaccination data."
                )
        );


        box.getChildren().addAll(
                icon,
                text,
                reminder
        );


        return box;
    }


    // =========================================================
    // CONSULTATION
    // =========================================================

    private VBox createConsultation() {

        VBox card =
                createWhiteCard();


        card.setStyle(
                "-fx-background-color: #F8F2FF;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E2D2F2;" +
                "-fx-border-radius: 18;"
        );


        HBox top =
                new HBox(15);

        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label icon =
                new Label("👩‍⚕️");

        icon.setStyle(
                "-fx-font-size: 38px;"
        );


        VBox text =
                new VBox(5);


        Label title =
                new Label(
                        "Need help with vaccination?"
                );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #7041A5;"
        );


        Label description =
                new Label(
                        "Consult a doctor to understand which vaccines are suitable for you and your baby."
                );

        description.setWrapText(true);

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #666680;"
        );


        text.getChildren().addAll(
                title,
                description
        );


        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );


        Button doctor =
                createOutlineButton(
                        "Consult Doctor  →"
                );


        doctor.setOnAction(e ->
                showInfo(
                        "Doctor Consultation",
                        "Doctor consultation page will open here."
                )
        );


        top.getChildren().addAll(
                icon,
                text,
                doctor
        );


        card.getChildren().add(
                top
        );


        return card;
    }


    // =========================================================
    // CARD HEADING
    // =========================================================

    private HBox createCardHeading(
            String text,
            FontAwesomeIcon iconType) {

        HBox heading =
                new HBox(10);

        heading.setAlignment(
                Pos.CENTER_LEFT
        );


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
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
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
    // GRADIENT BUTTON
    // =========================================================

    private Button createGradientButton(
            String text) {

        Button button =
                new Button(text);

        button.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right, #F54B87, #9B4DCC);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 9px 17px;"
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
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #DCC9EC;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8px 15px;"
        );


        return button;
    }


    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private Button createSmallButton(
            String text) {

        Button button =
                new Button(text);

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: " + PINK + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #F1C9D9;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 6px 11px;"
        );


        return button;
    }


    // =========================================================
    // VACCINATION INFO
    // =========================================================

    private void showVaccinationInfo(
            Vaccination vaccination) {

        String dueDate =
                vaccination.getDueDate() == null
                        ? "Not available yet"
                        : vaccination.getDueDate();


        String completedDate =
                vaccination.getCompletedDate() == null ||
                vaccination.getCompletedDate().isEmpty()
                        ? "Not completed"
                        : vaccination.getCompletedDate();


        String message =
                "For: " + vaccination.getPerson() +
                "\n\n" +
                "Vaccine: " + vaccination.getVaccineName() +
                "\n" +
                "Dose: " + vaccination.getDose() +
                "\n" +
                "Due Date: " + dueDate +
                "\n" +
                "Completed Date: " + completedDate +
                "\n" +
                "Status: " + vaccination.getStatus() +
                "\n\n" +
                vaccination.getDescription();


        showInfo(
                vaccination.getVaccineName(),
                message
        );
    }


    // =========================================================
    // INFO
    // =========================================================

    private void showInfo(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}