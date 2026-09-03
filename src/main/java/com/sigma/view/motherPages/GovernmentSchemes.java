package com.sigma.view.motherPages;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// =============================================================
// GOVERNMENT SCHEMES PAGE
// =============================================================

public class GovernmentSchemes {


private final String PINK = "#E84A87";
private final String DARK = "#24234F";
private final String PURPLE = "#9B4DCC";
private final String TEXT_GRAY = "#77778D";
private final String GREEN = "#3C9A68";

private VBox schemesContainer;

private TextField searchField;

private String selectedCategory = "All";

private final List<SchemeData> schemes =
        new ArrayList<>();


// =========================================================
// CONSTRUCTOR
// =========================================================

public GovernmentSchemes() {

    loadSchemes();
}


// =========================================================
// SCHEME DATA
// =========================================================

private void loadSchemes() {

    schemes.add(

        new SchemeData(

            "🤰",

            "Pradhan Mantri Matru Vandana Yojana",

            "PMMVY",

            "Pregnancy",

            "Financial assistance for eligible pregnant and lactating mothers.",

            "PMMVY is a maternity benefit programme that supports eligible mothers during pregnancy and childbirth. Benefits and eligibility should always be verified through official government sources.",

            "Pregnant Women • Lactating Mothers • Eligible Beneficiaries",

            "https://www.india.gov.in/category/welfare-of-families/subcategory/family-welfare/details/pradhan-mantri-matru-vandana-yojana"
        )
    );


    schemes.add(

        new SchemeData(

            "🏥",

            "Janani Suraksha Yojana",

            "JSY",

            "Pregnancy",

            "Supports safe motherhood and encourages institutional delivery.",

            "Janani Suraksha Yojana focuses on reducing maternal and newborn health risks by encouraging institutional deliveries and improving access to maternal healthcare services.",

            "Pregnant Women • Eligibility depends on applicable guidelines",

            "https://nhm.gov.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "👩‍⚕️",

            "Janani Shishu Suraksha Karyakram",

            "JSSK",

            "Mother",

            "Free healthcare support for pregnant women and newborn babies at eligible public health facilities.",

            "JSSK aims to reduce financial barriers for maternity and newborn healthcare services. Available services depend on official programme guidelines and public healthcare facilities.",

            "Pregnant Women • Newborn Babies",

            "https://nhm.gov.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "🥗",

            "POSHAN Abhiyaan",

            "Nutrition",

            "Mother",

            "Nutrition awareness and support for pregnant women, mothers and children.",

            "POSHAN Abhiyaan focuses on improving nutrition outcomes for children, pregnant women and lactating mothers through awareness, monitoring and coordinated nutrition programmes.",

            "Pregnant Women • Lactating Mothers • Children",

            "https://www.womenchild.maharashtra.gov.in/en/poshan-abhiyaan"
        )
    );


    schemes.add(

        new SchemeData(

            "🧒",

            "Saksham Anganwadi and POSHAN 2.0",

            "Nutrition Support",

            "Baby",

            "Integrated nutrition support for children and mothers.",

            "Saksham Anganwadi and POSHAN 2.0 supports nutrition, health and wellness initiatives for children, adolescent girls, pregnant women and lactating mothers.",

            "Children • Pregnant Women • Lactating Mothers",

            "https://womenchild.maharashtra.gov.in/en/anganwadi-corner"
        )
    );


    schemes.add(

        new SchemeData(

            "👶",

            "Mission Indradhanush",

            "Immunization",

            "Baby",

            "Supports vaccination coverage for children and pregnant women.",

            "Mission Indradhanush focuses on improving immunization coverage and reaching beneficiaries who may have missed recommended vaccines.",

            "Children • Pregnant Women",

            "https://www.mohfw.gov.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "🩺",

            "Ayushman Bharat",

            "Healthcare",

            "Financial Help",

            "Health protection and access to eligible healthcare services.",

            "Ayushman Bharat includes healthcare initiatives designed to improve access to health services. Coverage and eligibility depend on the applicable scheme and beneficiary criteria.",

            "Eligible Families • As per official criteria",

            "https://pmjay.gov.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "💰",

            "Mukhyamantri Majhi Ladki Bahin Yojana",

            "Maharashtra",

            "Financial Help",

            "Financial support scheme for eligible women in Maharashtra.",

            "This Maharashtra Government scheme provides financial assistance to eligible women subject to current government eligibility rules and programme guidelines.",

            "Eligible Women of Maharashtra",

            "https://womenchild.maharashtra.gov.in/en/"
        )
    );


    schemes.add(

        new SchemeData(

            "👧",

            "Beti Bachao Beti Padhao",

            "Girl Child",

            "Baby",

            "Promotes the welfare, protection and empowerment of the girl child.",

            "Beti Bachao Beti Padhao focuses on improving awareness and support for the survival, protection, education and empowerment of girls.",

            "Girl Children • Families • Communities",

            "https://wcd.nic.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "🏦",

            "Sukanya Samriddhi Yojana",

            "Savings",

            "Financial Help",

            "Savings scheme designed to support the future needs of the girl child.",

            "Sukanya Samriddhi Yojana is a government-backed savings scheme for eligible girl children. Account opening rules and financial terms should be verified through official sources.",

            "Eligible Girl Child",

            "https://www.nsiindia.gov.in/"
        )
    );


    schemes.add(

        new SchemeData(

            "👩",

            "Mission Shakti",

            "Women Support",

            "Mother",

            "Support for women's safety, security and empowerment.",

            "Mission Shakti brings together initiatives focused on women's safety, support and empowerment. It includes different components and services under official government guidelines.",

            "Women • Eligible Beneficiaries",

            "https://womenchild.maharashtra.gov.in/en/women-corner"
        )
    );
}


// =========================================================
// MAIN PAGE
// =========================================================

public VBox createGovernmentSchemesPage() {

    VBox page = new VBox();

    page.setFillWidth(true);

    page.setStyle(

        "-fx-background-color: linear-gradient(" +
        "to bottom right, " +
        "#FFFFFF 0%, " +
        "#FFF7FB 55%, " +
        "#F4EDFF 100%);"
    );


    VBox content = new VBox(22);

    content.setPadding(
        new Insets(25, 30, 40, 30)
    );


    // =====================================================
    // TITLE
    // =====================================================

    HBox titleSection =
            createTitleSection();


    // =====================================================
    // SEARCH
    // =====================================================

    HBox searchBox =
            createSearchBox();


    // =====================================================
    // CATEGORY SECTION
    // =====================================================

    VBox categorySection =
            createCategorySection();


    // =====================================================
    // SCHEMES HEADER
    // =====================================================

    HBox schemesHeader =
            createSchemesHeader();


    // =====================================================
    // SCHEMES CONTAINER
    // =====================================================

    schemesContainer = new VBox(15);

    showSchemes();


    // =====================================================
    // IMPORTANT NOTE
    // =====================================================

    VBox importantNote =
            createImportantNote();


    content.getChildren().addAll(

        titleSection,

        searchBox,

        categorySection,

        schemesHeader,

        schemesContainer,

        importantNote
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
// TITLE SECTION
// =========================================================

private HBox createTitleSection() {

    HBox box = new HBox();

    box.setAlignment(Pos.CENTER_LEFT);


    VBox text = new VBox(6);


    Label title = new Label(
        "Government Schemes"
    );

    title.setStyle(

        "-fx-font-size: 27px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    Label subtitle = new Label(

        "Explore useful government schemes for pregnancy, " +
        "motherhood, child care and financial support."
    );

    subtitle.setStyle(

        "-fx-font-size: 14px;" +
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


    Label icon =
            new Label("🏛️");

    icon.setStyle(

        "-fx-font-size: 42px;" +
        "-fx-background-color: #FFF0F6;" +
        "-fx-background-radius: 18;" +
        "-fx-padding: 12px;"
    );


    box.getChildren().addAll(
        text,
        icon
    );


    return box;
}


// =========================================================
// SEARCH BOX
// =========================================================

private HBox createSearchBox() {

    HBox searchBox = new HBox(10);

    searchBox.setAlignment(
        Pos.CENTER_LEFT
    );

    searchBox.setPadding(
        new Insets(4, 15, 4, 15)
    );


    searchBox.setStyle(

        "-fx-background-color: white;" +
        "-fx-border-color: #E7DCE8;" +
        "-fx-border-radius: 14;" +
        "-fx-background-radius: 14;"
    );


    Label icon =
            new Label("🔍");

    icon.setStyle(
        "-fx-font-size: 18px;"
    );


    searchField =
            new TextField();

    searchField.setPromptText(

        "Search schemes, benefits or categories..."
    );


    searchField.setStyle(

        "-fx-background-color: transparent;" +
        "-fx-border-color: transparent;" +
        "-fx-font-size: 14px;" +
        "-fx-padding: 10px;"
    );


    HBox.setHgrow(
        searchField,
        Priority.ALWAYS
    );


    searchField.textProperty()
            .addListener(
                (obs, oldValue, newValue) -> {

                    showSchemes();
                }
            );


    searchBox.getChildren().addAll(
        icon,
        searchField
    );


    return searchBox;
}


// =========================================================
// CATEGORY SECTION
// =========================================================

private VBox createCategorySection() {

    VBox section = new VBox(10);


    Label title =
            new Label("Browse by Category");

    title.setStyle(

        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    FlowPane categories =
            new FlowPane();

    categories.setHgap(10);

    categories.setVgap(10);


    categories.getChildren().addAll(

        createCategoryButton(
            "✨ All",
            "All"
        ),

        createCategoryButton(
            "🤰 Pregnancy",
            "Pregnancy"
        ),

        createCategoryButton(
            "👩 Mother",
            "Mother"
        ),

        createCategoryButton(
            "👶 Baby",
            "Baby"
        ),

        createCategoryButton(
            "💰 Financial Help",
            "Financial Help"
        )
    );


    section.getChildren().addAll(
        title,
        categories
    );


    return section;
}


// =========================================================
// CATEGORY BUTTON
// =========================================================

private Button createCategoryButton(

        String text,
        String category) {

    Button button =
            new Button(text);


    button.setStyle(

        "-fx-background-color: white;" +
        "-fx-text-fill: " + PURPLE + ";" +
        "-fx-font-size: 13px;" +
        "-fx-font-weight: bold;" +
        "-fx-border-color: #DCC9EC;" +
        "-fx-border-radius: 20;" +
        "-fx-background-radius: 20;" +
        "-fx-padding: 9px 17px;"
    );


    button.setOnAction(e -> {

        selectedCategory = category;

        showSchemes();
    });


    return button;
}


// =========================================================
// SCHEMES HEADER
// =========================================================

private HBox createSchemesHeader() {

    HBox box = new HBox();

    box.setAlignment(
        Pos.CENTER_LEFT
    );


    Label title =
            new Label("Available Schemes");

    title.setStyle(

        "-fx-font-size: 20px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    box.getChildren().add(
        title
    );


    return box;
}


// =========================================================
// SHOW SCHEMES
// =========================================================

private void showSchemes() {

    if (schemesContainer == null) {

        return;
    }


    schemesContainer.getChildren().clear();


    String searchText = "";

    if (searchField != null) {

        searchText =
                searchField.getText()
                        .toLowerCase()
                        .trim();
    }


    boolean found = false;


    for (SchemeData scheme : schemes) {

        boolean categoryMatch =

            selectedCategory.equals("All") ||

            scheme.category.equalsIgnoreCase(
                selectedCategory
            );


        boolean searchMatch =

            searchText.isEmpty() ||

            scheme.name.toLowerCase()
                .contains(searchText) ||

            scheme.shortName.toLowerCase()
                .contains(searchText) ||

            scheme.category.toLowerCase()
                .contains(searchText) ||

            scheme.shortDescription
                .toLowerCase()
                .contains(searchText) ||

            scheme.details
                .toLowerCase()
                .contains(searchText);


        if (categoryMatch && searchMatch) {

            schemesContainer.getChildren().add(

                createSchemeCard(
                    scheme
                )
            );

            found = true;
        }
    }


    if (!found) {

        Label noResult =
                new Label(

                    "No schemes found for your search."
                );


        noResult.setStyle(

            "-fx-font-size: 15px;" +
            "-fx-text-fill: " + TEXT_GRAY + ";" +
            "-fx-padding: 25px;"
        );


        schemesContainer.getChildren().add(
            noResult
        );
    }
}


// =========================================================
// SCHEME CARD
// =========================================================

private VBox createSchemeCard(
        SchemeData scheme) {

    VBox card = new VBox(12);

    card.setPadding(
        new Insets(18)
    );


    card.setStyle(

        "-fx-background-color: white;" +
        "-fx-background-radius: 18;" +
        "-fx-border-color: #E7DCE8;" +
        "-fx-border-radius: 18;"
    );


    // =====================================================
    // TOP
    // =====================================================

    HBox top = new HBox(15);

    top.setAlignment(
        Pos.CENTER_LEFT
    );


    Label icon =
            new Label(scheme.icon);

    icon.setAlignment(
        Pos.CENTER
    );

    icon.setPrefSize(
        60,
        60
    );


    icon.setStyle(

        "-fx-background-color: #FFF0F6;" +
        "-fx-background-radius: 16;" +
        "-fx-font-size: 30px;"
    );


    VBox text = new VBox(5);


    Label name =
            new Label(scheme.name);

    name.setWrapText(true);


    name.setStyle(

        "-fx-font-size: 17px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    Label category =
            new Label(

                scheme.shortName +
                " • " +
                scheme.category
            );


    category.setStyle(

        "-fx-font-size: 12px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + PINK + ";"
    );


    text.getChildren().addAll(
        name,
        category
    );


    HBox.setHgrow(
        text,
        Priority.ALWAYS
    );


    top.getChildren().addAll(
        icon,
        text
    );


    // =====================================================
    // DESCRIPTION
    // =====================================================

    Label description =
            new Label(
                scheme.shortDescription
            );

    description.setWrapText(true);


    description.setStyle(

        "-fx-font-size: 13px;" +
        "-fx-text-fill: " + TEXT_GRAY + ";" +
        "-fx-line-spacing: 3px;"
    );


    // =====================================================
    // BOTTOM
    // =====================================================

    HBox bottom = new HBox();

    bottom.setAlignment(
        Pos.CENTER_RIGHT
    );


    Button learnMore =
            createGradientButton(
                "Learn More  →"
            );


    learnMore.setOnAction(e ->

        showSchemeDetails(
            scheme
        )
    );


    bottom.getChildren().add(
        learnMore
    );


    card.getChildren().addAll(

        top,

        description,

        bottom
    );


    return card;
}


// =========================================================
// SCHEME DETAILS POPUP
// =========================================================

private void showSchemeDetails(
        SchemeData scheme) {

    Dialog<Void> dialog =
            new Dialog<>();


    dialog.setTitle(
        scheme.name
    );


    dialog.setHeaderText(null);


    VBox content =
            new VBox(15);

    content.setPadding(
        new Insets(20)
    );

    content.setPrefWidth(
        520
    );


    Label title =
            new Label(
                scheme.icon +
                " " +
                scheme.name
            );


    title.setWrapText(true);


    title.setStyle(

        "-fx-font-size: 20px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    Label category =
            new Label(

                scheme.shortName +
                " • " +
                scheme.category
            );


    category.setStyle(

        "-fx-background-color: #FFF0F6;" +
        "-fx-text-fill: " + PINK + ";" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 15;" +
        "-fx-padding: 6px 12px;"
    );


    Label aboutTitle =
            new Label("About this Scheme");

    aboutTitle.setStyle(

        "-fx-font-size: 15px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    Label details =
            new Label(
                scheme.details
            );

    details.setWrapText(true);


    details.setStyle(

        "-fx-font-size: 13px;" +
        "-fx-text-fill: " + TEXT_GRAY + ";" +
        "-fx-line-spacing: 4px;"
    );


    Label eligibleTitle =
            new Label(
                "Who may benefit?"
            );


    eligibleTitle.setStyle(

        "-fx-font-size: 15px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + DARK + ";"
    );


    Label eligibility =
            new Label(
                "✓ " +
                scheme.eligibility
            );


    eligibility.setWrapText(true);


    eligibility.setStyle(

        "-fx-background-color: #F2FBF6;" +
        "-fx-text-fill: " + GREEN + ";" +
        "-fx-background-radius: 12;" +
        "-fx-padding: 12px;" +
        "-fx-font-size: 13px;"
    );


    Label note =
            new Label(

                "⚠ Eligibility, benefits and application " +
                "procedures may change. Please verify the " +
                "latest information on the official website."
            );


    note.setWrapText(true);


    note.setStyle(

        "-fx-background-color: #FFF9E8;" +
        "-fx-text-fill: #9A6A12;" +
        "-fx-background-radius: 12;" +
        "-fx-padding: 12px;" +
        "-fx-font-size: 12px;"
    );


    Button website =
            createGradientButton(
                "Open Official Website  ↗"
            );


    website.setOnAction(e ->

        openWebsite(
            scheme.website
        )
    );


    HBox buttonBox =
            new HBox();

    buttonBox.setAlignment(
        Pos.CENTER_RIGHT
    );

    buttonBox.getChildren().add(
        website
    );


    content.getChildren().addAll(

        title,

        category,

        aboutTitle,

        details,

        eligibleTitle,

        eligibility,

        note,

        buttonBox
    );


    dialog.getDialogPane()
            .setContent(content);


    dialog.getDialogPane()
            .getButtonTypes()
            .add(

                javafx.scene.control.ButtonType.CLOSE
            );


    dialog.showAndWait();
}


// =========================================================
// OPEN WEBSITE
// =========================================================

private void openWebsite(
        String website) {

    try {

        if (
            Desktop.isDesktopSupported()
        ) {

            Desktop.getDesktop()
                    .browse(

                        new URI(
                            website
                        )
                    );
        }

    } catch (Exception e) {

        showMessage(

            "Unable to Open Website",

            "Please check your internet connection and try again."
        );
    }
}


// =========================================================
// IMPORTANT NOTE
// =========================================================

private VBox createImportantNote() {

    VBox note = new VBox(8);

    note.setPadding(
        new Insets(18)
    );


    note.setStyle(

        "-fx-background-color: #FFF7FA;" +
        "-fx-border-color: #F3D8E3;" +
        "-fx-border-radius: 16;" +
        "-fx-background-radius: 16;"
    );


    Label title =
            new Label(
                "💡 Important Information"
            );


    title.setStyle(

        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: " + PINK + ";"
    );


    Label text =
            new Label(

                "Government scheme eligibility, benefits and application " +
                "procedures can change. Always verify the latest details " +
                "through official government websites before applying."
            );


    text.setWrapText(true);


    text.setStyle(

        "-fx-font-size: 13px;" +
        "-fx-text-fill: " + TEXT_GRAY + ";"
    );


    note.getChildren().addAll(
        title,
        text
    );


    return note;
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
// MESSAGE
// =========================================================

private void showMessage(

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


// =========================================================
// SCHEME DATA CLASS
// =========================================================

private static class SchemeData {

    String icon;

    String name;

    String shortName;

    String category;

    String shortDescription;

    String details;

    String eligibility;

    String website;


    SchemeData(

            String icon,

            String name,

            String shortName,

            String category,

            String shortDescription,

            String details,

            String eligibility,

            String website) {


        this.icon =
                icon;

        this.name =
                name;

        this.shortName =
                shortName;

        this.category =
                category;

        this.shortDescription =
                shortDescription;

        this.details =
                details;

        this.eligibility =
                eligibility;

        this.website =
                website;
    }
}

}
