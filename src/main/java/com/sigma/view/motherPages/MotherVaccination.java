package com.sigma.view.motherPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sigma.controller.MotherVaccinationController;
import com.sigma.model.MotherVaccinationmodel;
import com.sigma.model.MotherWlcModel;

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
    // FIREBASE
    // =========================================================

    private final MotherVaccinationController controller;

    private MotherWlcModel motherModel;

    private String motherId;


    // =========================================================
    // VACCINATION DATA
    // =========================================================

    private final List<MotherVaccinationmodel> vaccinations =
            new ArrayList<>();


    // =========================================================
    // CONSTRUCTOR - EXISTING COMPATIBILITY
    // =========================================================

    public MotherVaccination() {

        this.motherModel = null;

        this.motherId = null;

        this.controller =
                new MotherVaccinationController();
    }


    // =========================================================
    // CONSTRUCTOR - FIREBASE
    // =========================================================

    public MotherVaccination(
            MotherWlcModel motherModel) {

        this.motherModel = motherModel;

        this.motherId =
                motherModel != null
                        ? motherModel.getMotherId()
                        : null;

        this.controller =
                new MotherVaccinationController();
    }


    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createVaccinationPage() {

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
    // LOAD FIREBASE DATA
    // =========================================================

    private void loadVaccinationData() {

        vaccinations.clear();

        try {

            if (motherId != null &&
                !motherId.trim().isEmpty()) {

                List<MotherVaccinationmodel> firebaseData =
                        controller.getVaccinationsByMotherId(
                                motherId
                        );

                if (firebaseData != null &&
                    !firebaseData.isEmpty()) {

                    vaccinations.addAll(
                            firebaseData
                    );

                    System.out.println(
                            "💉 Vaccinations loaded from Firebase: "
                            + vaccinations.size()
                    );

                    return;
                }
            }

            System.out.println(
                    "ℹ️ No vaccination data found in Firebase."
            );

            System.out.println(
                    "📋 Loading demo vaccination data..."
            );

            loadDemoVaccinationData();

        } catch (Exception e) {

            System.out.println(
                    "❌ Error loading vaccination data."
            );

            e.printStackTrace();

            loadDemoVaccinationData();
        }
    }


    // =========================================================
    // DEMO / FALLBACK DATA
    // =========================================================

    private void loadDemoVaccinationData() {

        String demoMotherId =
                motherId != null &&
                !motherId.trim().isEmpty()
                        ? motherId
                        : "DEMO_MOTHER_001";


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_001",
                demoMotherId,
                "Baby",
                "BCG",
                "Birth Dose",
                "02 Sep 2026",
                "02 Sep 2026",
                "Completed",
                "Tuberculosis protection"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_002",
                demoMotherId,
                "Baby",
                "OPV",
                "Birth Dose",
                "02 Sep 2026",
                "02 Sep 2026",
                "Completed",
                "Polio protection"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_003",
                demoMotherId,
                "Baby",
                "Pentavalent",
                "Dose 1",
                "15 Sep 2026",
                null,
                "Upcoming",
                "Protection against diphtheria, tetanus, pertussis and other infections"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_004",
                demoMotherId,
                "Baby",
                "Rotavirus",
                "Dose 1",
                "15 Sep 2026",
                null,
                "Upcoming",
                "Protection against rotavirus infection"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_005",
                demoMotherId,
                "Baby",
                "PCV",
                "Dose 1",
                "20 Sep 2026",
                null,
                "Upcoming",
                "Protection against pneumococcal disease"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_006",
                demoMotherId,
                "Mother",
                "Td / Tdap",
                "Recommended Dose",
                "12 Sep 2026",
                null,
                "Upcoming",
                "Protects mother and baby"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_007",
                demoMotherId,
                "Mother",
                "Influenza",
                "Seasonal",
                "25 Sep 2026",
                null,
                "Upcoming",
                "Seasonal flu protection"
            )
        );


        vaccinations.add(
            new MotherVaccinationmodel(
                "DEMO_VACC_008",
                demoMotherId,
                "Mother",
                "COVID-19",
                "As Recommended",
                null,
                null,
                "Consult Doctor",
                "Vaccination according to doctor's recommendation"
            )
        );


        System.out.println(
                "✅ Demo vaccinations loaded: "
                + vaccinations.size()
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


        for (MotherVaccinationmodel vaccination :
                vaccinations) {

            String status =
                    vaccination.getStatus();

            if ("Completed".equalsIgnoreCase(status)) {

                completed++;

            } else if ("Due Soon".equalsIgnoreCase(status)) {

                dueSoon++;

            } else if ("Upcoming".equalsIgnoreCase(status)) {

                upcoming++;
            }
        }


        int total =
                vaccinations.size();


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


        for (MotherVaccinationmodel vaccination :
                vaccinations) {

            String status =
                    vaccination.getStatus();

            if (!"Completed".equalsIgnoreCase(status)) {

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
            MotherVaccinationmodel vaccination) {

        String person =
                vaccination.getPerson();

        String color =
                "Mother".equalsIgnoreCase(person)
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


       FontAwesomeIconView icon =
        new FontAwesomeIconView(
                "Mother".equalsIgnoreCase(person)
                        ? FontAwesomeIcon.USER
                        : FontAwesomeIcon.CHILD
        );

icon.setSize("25");
icon.setFill(Color.web(color));

        icon.setStyle(
                "-fx-font-size: 25px;"
        );


        VBox personBox =
                new VBox();


        Label personLabel =
                new Label(
                        person == null
                                ? "Unknown"
                                : person
                );

        personLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + color + ";" +
                "-fx-font-weight: bold;"
        );


        Label vaccineLabel =
                new Label(
                        vaccination.getVaccineName() == null
                                ? "Vaccination"
                                : vaccination.getVaccineName()
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
                        vaccination.getDose() == null
                                ? "Dose not available"
                                : vaccination.getDose()
                );

        doseLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #55556D;"
        );


        Label dateLabel =
                new Label(
                        vaccination.getDueDate() == null ||
                        vaccination.getDueDate().isEmpty()
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
                        vaccination.getStatus() == null
                                ? "Unknown"
                                : vaccination.getStatus()
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


        Button complete =
                createSmallButton(
                        "Complete"
                );


        if ("Completed".equalsIgnoreCase(
                vaccination.getStatus())) {

            complete.setDisable(true);
        }


        complete.setOnAction(e ->
                completeVaccination(
                        vaccination
                )
        );


        box.getChildren().addAll(
                top,
                doseLabel,
                dateLabel,
                statusLabel,
                view,
                complete
        );


        return box;
    }


    // =========================================================
    // COMPLETE VACCINATION
    // =========================================================

    private void completeVaccination(
            MotherVaccinationmodel vaccination) {

        if (vaccination == null) {
            return;
        }


        String id =
                vaccination.getId();


        // -----------------------------------------------------
        // DEMO DATA
        // -----------------------------------------------------

        if (id != null &&
            id.startsWith("DEMO_")) {

            vaccination.setStatus(
                    "Completed"
            );

            vaccination.setCompletedDate(
                    LocalDate.now().format(
                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy"
                            )
                    )
            );


            showInfo(
                    "Vaccination Completed",
                    vaccination.getVaccineName()
                            + " marked as completed."
            );


            refreshPage();

            return;
        }


        // -----------------------------------------------------
        // FIREBASE DATA
        // -----------------------------------------------------

        try {

            String completedDate =
                    LocalDate.now().format(
                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy"
                            )
                    );


            boolean success =
                    controller.markAsCompleted(
                            id,
                            completedDate
                    );


            if (success) {

                vaccination.setStatus(
                        "Completed"
                );

                vaccination.setCompletedDate(
                        completedDate
                );


                showInfo(
                        "Vaccination Completed",
                        vaccination.getVaccineName()
                                + " marked as completed and saved to Firebase."
                );


                refreshPage();

            } else {

                showInfo(
                        "Update Failed",
                        "Unable to update vaccination in Firebase."
                );
            }


        } catch (Exception e) {

            e.printStackTrace();

            showInfo(
                    "Error",
                    "Something went wrong while updating vaccination."
            );
        }
    }


    // =========================================================
    // REFRESH PAGE
    // =========================================================

    private void refreshPage() {

        if (motherModel != null) {

            motherId =
                    motherModel.getMotherId();
        }

        loadVaccinationData();
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


        for (MotherVaccinationmodel vaccination :
                vaccinations) {

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
                        "Complete vaccination schedule is loaded from your baby's vaccination data."
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


        for (MotherVaccinationmodel vaccination :
                vaccinations) {

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
            MotherVaccinationmodel vaccination) {

        String vaccineName =
                vaccination.getVaccineName();


        String color =
                "COVID-19".equalsIgnoreCase(vaccineName)
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


        FontAwesomeIconView icon =
        new FontAwesomeIconView(
                FontAwesomeIcon.MEDKIT);

icon.setSize("25");
icon.setFill(Color.web(PURPLE));

icon.setStyle(
        "-fx-font-size: 25px;"
);

        icon.setStyle(
                "-fx-font-size: 25px;"
        );


        Label title =
                new Label(
                        vaccineName == null
                                ? "Vaccination"
                                : vaccineName
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
                        vaccination.getDescription() == null
                                ? "No description available."
                                : vaccination.getDescription()
                );


        desc.setWrapText(true);


        desc.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #666680;"
        );


        Label status =
                new Label(
                        vaccination.getStatus() == null
                                ? "Unknown"
                                : vaccination.getStatus()
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
            MotherVaccinationmodel vaccination) {

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

FontAwesomeIconView icon =
        new FontAwesomeIconView(
                FontAwesomeIcon.MEDKIT
);

icon.setSize("22");
icon.setFill(Color.web(PURPLE));

icon.setStyle(
        "-fx-font-size: 22px;"
);

        icon.setStyle(
                "-fx-font-size: 22px;"
        );


        VBox text =
                new VBox(3);


        Label name =
                new Label(
                        vaccination.getVaccineName() == null
                                ? "Vaccination"
                                : vaccination.getVaccineName()
                );


        name.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + DARK + ";"
        );


        Label desc =
                new Label(
                        vaccination.getDescription() == null
                                ? "No description available."
                                : vaccination.getDescription()
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


        String status =
                vaccination.getStatus() == null
                        ? "Unknown"
                        : vaccination.getStatus();


        String statusColor =
                getStatusColor(status);


        Label statusLabel =
                new Label(status);


        statusLabel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + statusColor + ";" +
                "-fx-text-fill: " + statusColor + ";" +
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


        FontAwesomeIconView icon =
        new FontAwesomeIconView(
                FontAwesomeIcon.BELL
        );

icon.setSize("30");
icon.setFill(Color.web("#C7821B"));

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
                        "Vaccination reminder will be connected with your vaccination data."
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

FontAwesomeIconView icon =
        new FontAwesomeIconView(
                FontAwesomeIcon.USER_MD
        );

icon.setSize("38");
icon.setFill(Color.web(PURPLE));

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
            MotherVaccinationmodel vaccination) {

        String dueDate =
                vaccination.getDueDate() == null ||
                vaccination.getDueDate().isEmpty()
                        ? "Not available yet"
                        : vaccination.getDueDate();


        String completedDate =
                vaccination.getCompletedDate() == null ||
                vaccination.getCompletedDate().isEmpty()
                        ? "Not completed"
                        : vaccination.getCompletedDate();


        String person =
                vaccination.getPerson() == null
                        ? "Unknown"
                        : vaccination.getPerson();


        String vaccineName =
                vaccination.getVaccineName() == null
                        ? "Vaccination"
                        : vaccination.getVaccineName();


        String dose =
                vaccination.getDose() == null
                        ? "Not available"
                        : vaccination.getDose();


        String status =
                vaccination.getStatus() == null
                        ? "Unknown"
                        : vaccination.getStatus();


        String description =
                vaccination.getDescription() == null
                        ? "No description available."
                        : vaccination.getDescription();


        String message =
                "For: " + person +
                "\n\n" +
                "Vaccine: " + vaccineName +
                "\n" +
                "Dose: " + dose +
                "\n" +
                "Due Date: " + dueDate +
                "\n" +
                "Completed Date: " + completedDate +
                "\n" +
                "Status: " + status +
                "\n\n" +
                description;


        showInfo(
                vaccineName,
                message
        );
    }


    // =========================================================
    // INFO ALERT
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