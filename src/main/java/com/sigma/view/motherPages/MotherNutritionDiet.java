package com.sigma.view.motherPages;

import com.sigma.ai.AIAssistant;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

// =============================================================
// MOTHER NUTRITION / DIET PAGE
// =============================================================

public class MotherNutritionDiet {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";

    // =========================================================
    // AI ASSISTANT
    // =========================================================

    private final AIAssistant assistant =
            new AIAssistant();

    // =========================================================
    // TAB NAVIGATION REFERENCES
    // =========================================================

    private ScrollPane dietScrollPane;

    private VBox monthlyDietSection;
    private VBox childDietSection;
    private VBox whatToAvoidSection;

    private VBox dietContent;

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public VBox createDietNutritionPage() {

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

        content.setSpacing(18);

        content.setPadding(
            new Insets(18, 25, 35, 25)
        );

        dietContent = content;

        // =====================================================
        // PAGE TITLE
        // =====================================================

        VBox titleBox = new VBox();

        titleBox.setSpacing(3);

        Label title =
        new Label("Nutrition & Diet");

        title.setStyle(
            "-fx-font-size: 27px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Healthy nutrition for you and your baby's growth. 💗"
            );

        subtitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #77778D;"
        );

        titleBox.getChildren().addAll(
            title,
            subtitle
        );

        content.getChildren().add(titleBox);

        // =====================================================
        // TABS
        // =====================================================

        content.getChildren().add(
            createTabs()
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        HBox contentLayout = new HBox();

        contentLayout.setSpacing(18);

        VBox leftContent = new VBox();

        leftContent.setSpacing(18);

        HBox.setHgrow(
            leftContent,
            Priority.ALWAYS
        );

        VBox rightContent = new VBox();

        rightContent.setSpacing(18);

        rightContent.setPrefWidth(340);

        rightContent.setMinWidth(320);

        // =====================================================
        // LEFT CONTENT
        // =====================================================

        monthlyDietSection =
            createMonthlyDietCard();

        childDietSection =
            createChildDietCard();

        whatToAvoidSection =
            createWhatToAvoidCard();

        leftContent.getChildren().addAll(
            monthlyDietSection,
            childDietSection,
            whatToAvoidSection
        );

        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        rightContent.getChildren().addAll(
            createNutritionGlanceCard(),
            createDailyTipsCard(),
            createHealthyReminderCard(),
            createPersonalDietCard()
        );

        contentLayout.getChildren().addAll(
            leftContent,
            rightContent
        );

        content.getChildren().add(
            contentLayout
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(content);

        this.dietScrollPane =
            scrollPane;

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
    // TABS
    // =========================================================

    private HBox createTabs() {

        HBox tabs = new HBox();

        tabs.setSpacing(32);

        tabs.setPadding(
            new Insets(0, 0, 3, 0)
        );

        Button overview =
            createTabButton(
                "Overview",
                true
            );

        Button monthly =
            createTabButton(
                "Monthly Diet (Mom)",
                false
            );

        Button child =
            createTabButton(
                "Child Diet",
                false
            );

        Button avoid =
            createTabButton(
                "What to Avoid",
                false
            );

        overview.setOnAction(e -> {

            setActiveTab(
                overview,
                monthly,
                child,
                avoid
            );

            scrollToTop();
        });

        monthly.setOnAction(e -> {

            setActiveTab(
                monthly,
                overview,
                child,
                avoid
            );

            scrollToSection(
                monthlyDietSection
            );
        });

        child.setOnAction(e -> {

            setActiveTab(
                child,
                overview,
                monthly,
                avoid
            );

            scrollToSection(
                childDietSection
            );
        });

        avoid.setOnAction(e -> {

            setActiveTab(
                avoid,
                overview,
                monthly,
                child
            );

            scrollToSection(
                whatToAvoidSection
            );
        });

        tabs.getChildren().addAll(
            overview,
            monthly,
            child,
            avoid
        );

        return tabs;
    }

    // =========================================================
    // TAB BUTTON
    // =========================================================

    private Button createTabButton(
            String text,
            boolean active) {

        Button button =
            new Button(text);

        applyTabStyle(
            button,
            active
        );

        button.setCursor(
            Cursor.HAND
        );

        return button;
    }

    // =========================================================
    // APPLY TAB STYLE
    // =========================================================

    private void applyTabStyle(
            Button button,
            boolean active) {

        button.setStyle(
            active
            ?
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #E84A87;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: transparent transparent #E84A87 transparent;" +
            "-fx-border-width: 0 0 3 0;" +
            "-fx-padding: 8px 12px;" +
            "-fx-cursor: hand;"
            :
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #666680;" +
            "-fx-font-size: 14px;" +
            "-fx-border-color: transparent;" +
            "-fx-padding: 8px 12px;" +
            "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // ACTIVE TAB
    // =========================================================

    private void setActiveTab(
            Button active,
            Button... otherTabs) {

        applyTabStyle(
            active,
            true
        );

        for (Button button : otherTabs) {

            applyTabStyle(
                button,
                false
            );
        }
    }

    // =========================================================
    // SCROLL TO TOP
    // =========================================================

    private void scrollToTop() {

        if (dietScrollPane == null) {
            return;
        }

        Platform.runLater(() ->
            dietScrollPane.setVvalue(0)
        );
    }

    // =========================================================
    // SCROLL TO SECTION
    // =========================================================

    private void scrollToSection(
            Node target) {

        if (dietScrollPane == null ||
            target == null) {

            return;
        }

        Platform.runLater(() -> {

            try {

                Node content =
                    dietScrollPane.getContent();

                if (content == null) {
                    return;
                }

                Bounds targetInScene =
                    target.localToScene(
                        target.getBoundsInLocal()
                    );

                Bounds targetInContent =
                    content.sceneToLocal(
                        targetInScene
                    );

                double targetY =
                    targetInContent.getMinY();

                double contentHeight =
                    content.getBoundsInLocal()
                        .getHeight();

                double viewportHeight =
                    dietScrollPane
                        .getViewportBounds()
                        .getHeight();

                double scrollableHeight =
                    contentHeight -
                    viewportHeight;

                if (scrollableHeight <= 0) {

                    dietScrollPane.setVvalue(0);

                    return;
                }

                double newValue =
                    targetY /
                    scrollableHeight;

                newValue =
                    Math.max(
                        0,
                        Math.min(
                            1,
                            newValue - 0.02
                        )
                    );

                dietScrollPane.setVvalue(
                    newValue
                );

            } catch (Exception ex) {

                System.out.println(
                    "TAB SCROLL ERROR: " +
                    ex.getMessage()
                );
            }
        });
    }

    // =========================================================
    // MONTHLY DIET CARD
    // =========================================================

    private VBox createMonthlyDietCard() {

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
                "🍃 Monthly Diet (Mom)"
            );

        title.setStyle(
            "-fx-font-size: 21px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Balanced diet for a healthy pregnancy"
            );

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
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

        ComboBox<String> trimester =
            new ComboBox<>();

        trimester.getItems().addAll(
            "1st Trimester (1-3 Months)",
            "2nd Trimester (4-6 Months)",
            "3rd Trimester (7-9 Months)"
        );

        trimester.setValue(
            "2nd Trimester (4-6 Months)"
        );

        trimester.setPrefWidth(215);

        trimester.setStyle(
            "-fx-font-size: 13px;"
        );

        heading.getChildren().addAll(
            titleBox,
            trimester
        );

        HBox meals =
            new HBox();

        meals.setSpacing(12);

        meals.setAlignment(
            Pos.CENTER
        );

        setSecondTrimesterMeals(
            meals
        );

        trimester.setOnAction(e -> {

            String selected =
                trimester.getValue();

            if (selected.startsWith("1st")) {

                setFirstTrimesterMeals(meals);

            } else if (
                selected.startsWith("2nd")
            ) {

                setSecondTrimesterMeals(meals);

            } else {

                setThirdTrimesterMeals(meals);
            }
        });

        Button fullPlan =
            createGradientButton(
                "View Full Monthly Diet Plan  →"
            );

        fullPlan.setOnAction(e ->
            showMonthlyDietPlan()
        );

        card.getChildren().addAll(
            heading,
            meals,
            fullPlan
        );

        return card;
    }

    // =========================================================
    // 1ST TRIMESTER
    // =========================================================

    private void setFirstTrimesterMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createMealCard(
                "Breakfast",
                "Start your day healthy",
                "logo/motherBreakfast.png",
                "☀️",
                "Poha / Upma",
                "1 Banana",
                "5-6 Almonds"
            ),

            createMealCard(
                "Lunch",
                "Balanced & nutritious",
                "logo/motherLunch.png",
                "🍱",
                "2 Phulka",
                "1 Cup Dal",
                "Seasonal Vegetables"
            ),

            createMealCard(
                "Evening Snack",
                "Light & healthy",
                "logo/snack.png",
                "☕",
                "Fruit Bowl",
                "Coconut Water",
                "Roasted Chana"
            ),

            createMealCard(
                "Dinner",
                "Light dinner",
                "logo/dinner.png",
                "🍱",
                "Veg Khichdi",
                "1 Bowl Curd",
                "Green Vegetables"
            )
        );
    }

    // =========================================================
    // 2ND TRIMESTER
    // =========================================================

    private void setSecondTrimesterMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createMealCard(
                "Breakfast",
                "Kickstart your day",
                "logo/motherBreakfast.png",
                "☀️",
                "Oats / Poha / Upma",
                "1 Banana",
                "5-6 Almonds"
            ),

            createMealCard(
                "Lunch",
                "Stay energetic",
                "logo/motherLunch.png",
                "🍱",
                "2 Phulka",
                "1 Cup Dal",
                "1 Cup Rice"
            ),

            createMealCard(
                "Evening Snack",
                "Healthy snacking",
                "logo/snack.png",
                "☕",
                "Sprouts / Fruit Bowl",
                "Coconut Water",
                "Buttermilk"
            ),

            createMealCard(
                "Dinner",
                "Light & easy to digest",
                "logo/dinner.png",
                "🍱",
                "Veg Khichdi / Soup",
                "1 Bowl Curd",
                "Steamed Vegetables"
            )
        );
    }

    // =========================================================
    // 3RD TRIMESTER
    // =========================================================

    private void setThirdTrimesterMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createMealCard(
                "Breakfast",
                "Energy rich breakfast",
                "logo/motherBreakfast.png",
                "☀️",
                "Paratha / Poha",
                "1 Fruit",
                "Almonds & Walnuts"
            ),

            createMealCard(
                "Lunch",
                "Protein rich meal",
                "logo/motherLunch.png",
                "🍛",
                "2 Phulka",
                "Dal",
                "Rice"
            ),

            createMealCard(
                "Evening Snack",
                "Healthy snack",
                "logo/snack.png",
                "☕",
                "Fruit Bowl",
                "Coconut Water",
                "Roasted Chana"
            ),

            createMealCard(
                "Dinner",
                "Easy to digest",
                "logo/dinner.png",
                "🍱",
                "Khichdi / Dal Rice",
                "Curd",
                "Vegetable Soup"
            )
        );
    }

    // =========================================================
    // MOM MEAL CARD
    // =========================================================

    private VBox createMealCard(
            String title,
            String subtitle,
            String imageName,
            String emoji,
            String item1,
            String item2,
            String item3) {

        VBox card =
            new VBox();

        card.setPrefWidth(205);

        card.setMinWidth(190);

        card.setSpacing(7);

        card.setPadding(
            new Insets(10)
        );

        card.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #E8E0EA;" +
            "-fx-border-radius: 14;" +
            "-fx-background-radius: 14;"
        );

        HBox heading =
            new HBox();

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        heading.setSpacing(8);

        Label iconLabel =
            new Label(emoji);

        iconLabel.setStyle(
            "-fx-font-size: 23px;"
        );

        VBox headingText =
            new VBox();

        headingText.setSpacing(1);

        Label mealTitle =
            new Label(title);

        mealTitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );

        Label mealSubtitle =
            new Label(subtitle);

        mealSubtitle.setWrapText(true);

        mealSubtitle.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #77778D;"
        );

        headingText.getChildren().addAll(
            mealTitle,
            mealSubtitle
        );

        heading.getChildren().addAll(
            iconLabel,
            headingText
        );

        StackPane imageBox =
            createFoodImage(
                imageName,
                emoji,
                185,
                105
            );

        Label items =
            new Label(
                "• " + item1 + "\n" +
                "• " + item2 + "\n" +
                "• " + item3
            );

        items.setWrapText(true);

        items.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #24234F;" +
            "-fx-line-spacing: 4px;"
        );

        card.getChildren().addAll(
            heading,
            imageBox,
            items
        );

        return card;
    }

    // =========================================================
    // CHILD DIET
    // =========================================================

    private VBox createChildDietCard() {

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
                "👶 Child Diet (6 Months – 2 Years)"
            );

        title.setStyle(
            "-fx-font-size: 21px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Nutrition for your baby's healthy growth"
            );

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
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

        ComboBox<String> age =
            new ComboBox<>();

        age.getItems().addAll(
            "6-9 Months",
            "9-12 Months",
            "1-2 Years"
        );

        age.setValue(
            "6-9 Months"
        );

        age.setPrefWidth(150);

        age.setStyle(
            "-fx-font-size: 13px;"
        );

        heading.getChildren().addAll(
            titleBox,
            age
        );

        HBox meals =
            new HBox();

        meals.setSpacing(12);

        meals.setAlignment(
            Pos.CENTER
        );

        setSixToNineMonthsMeals(meals);

        age.setOnAction(e -> {

            String selectedAge =
                age.getValue();

            if (
                selectedAge.equals(
                    "6-9 Months"
                )
            ) {

                setSixToNineMonthsMeals(meals);

            } else if (
                selectedAge.equals(
                    "9-12 Months"
                )
            ) {

                setNineToTwelveMonthsMeals(meals);

            } else {

                setOneToTwoYearsMeals(meals);
            }
        });

        HBox tip =
            new HBox();

        tip.setAlignment(
            Pos.CENTER_LEFT
        );

        tip.setSpacing(9);

        tip.setPadding(
            new Insets(10)
        );

        tip.setStyle(
            "-fx-background-color: #F0FFF7;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #D5F0E0;" +
            "-fx-border-radius: 12;"
        );

        Label tipIcon =
            new Label("💡");

        tipIcon.setStyle(
            "-fx-font-size: 16px;"
        );

        Label tipText =
            new Label(
                "Tip: Start with age-appropriate foods and " +
                "gradually introduce variety. Ensure proper " +
                "texture and hygiene."
            );

        tipText.setWrapText(true);

        tipText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #35835B;"
        );

        HBox.setHgrow(
            tipText,
            Priority.ALWAYS
        );

        tip.getChildren().addAll(
            tipIcon,
            tipText
        );

        Button fullChildPlan =
            createGreenButton(
                "View Full Child Diet Plan  →"
            );

        fullChildPlan.setOnAction(e ->
            showChildDietPlan()
        );

        HBox bottom =
            new HBox();

        bottom.setSpacing(10);

        HBox.setHgrow(
            tip,
            Priority.ALWAYS
        );

        bottom.getChildren().addAll(
            tip,
            fullChildPlan
        );

        card.getChildren().addAll(
            heading,
            meals,
            bottom
        );

        return card;
    }

    // =========================================================
    // 6-9 MONTHS
    // =========================================================

    private void setSixToNineMonthsMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createChildMeal(
                "Breakfast",
                "logo/childBreakfast.png",
                "🥣",
                "Rice Cereal / Dal Water"
            ),

            createChildMeal(
                "Lunch",
                "logo/babylunch.png",
                "🍚",
                "Mashed Dal + Rice / Khichdi"
            ),

            createChildMeal(
                "Evening Snack",
                "logo/childSnack.png",
                "🍌",
                "Banana / Fruit Puree"
            ),

            createChildMeal(
                "Dinner",
                "logo/childdinner.png",
                "🍱",
                "Veg Puree / Suji Porridge"
            )
        );
    }

    // =========================================================
    // 9-12 MONTHS
    // =========================================================

    private void setNineToTwelveMonthsMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createChildMeal(
                "Breakfast",
                "logo/childBreakfast.png",
                "🥣",
                "Soft Idli / Upma / Porridge"
            ),

            createChildMeal(
                "Lunch",
                "logo/babylunch.png",
                "🍛",
                "Soft Rice + Dal + Vegetables"
            ),

            createChildMeal(
                "Evening Snack",
                "logo/childSnack.png",
                "🍎",
                "Banana / Seasonal Fruit"
            ),

            createChildMeal(
                "Dinner",
                "logo/childdinner.png",
                "🍱",
                "Khichdi / Soft Chapati + Dal"
            )
        );
    }

    // =========================================================
    // 1-2 YEARS
    // =========================================================

    private void setOneToTwoYearsMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createChildMeal(
                "Breakfast",
                "logo/childBreakfast.png",
                "🥞",
                "Poha / Upma / Dosa"
            ),

            createChildMeal(
                "Lunch",
                "logo/babylunch.png",
                "🍱",
                "Rice + Dal + Vegetables + Curd"
            ),

            createChildMeal(
                "Evening Snack",
                "logo/childSnack.png",
                "🍎",
                "Fruit Bowl / Homemade Snack"
            ),

            createChildMeal(
                "Dinner",
                "logo/childdinner.png",
                "🍱",
                "Chapati + Dal + Vegetables"
            )
        );
    }

    // =========================================================
    // CHILD MEAL CARD
    // =========================================================

    private VBox createChildMeal(
            String title,
            String imageName,
            String emoji,
            String food) {

        VBox box =
            new VBox();

        box.setAlignment(
            Pos.CENTER_LEFT
        );

        box.setSpacing(7);

        box.setPadding(
            new Insets(8)
        );

        box.setPrefWidth(205);

        box.setMinWidth(190);

        box.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #E8E0EA;" +
            "-fx-border-radius: 12;" +
            "-fx-background-radius: 12;"
        );

        StackPane imageBox =
            createFoodImage(
                imageName,
                emoji,
                185,
                100
            );

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #4B9B65;"
        );

        Label foodLabel =
            new Label(food);

        foodLabel.setWrapText(true);

        foodLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #24234F;"
        );

        box.getChildren().addAll(
            imageBox,
            titleLabel,
            foodLabel
        );

        return box;
    }

    // =========================================================
    // WHAT TO AVOID
    // =========================================================

    private VBox createWhatToAvoidCard() {

        VBox card =
            createWhiteCard();

        Label title =
            new Label(
                "🚫 What to Avoid"
            );

        title.setStyle(
            "-fx-font-size: 21px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Foods & habits to avoid during pregnancy"
            );

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #77778D;"
        );

        HBox items =
            new HBox();

        items.setSpacing(10);

        items.setAlignment(
            Pos.CENTER
        );

        items.getChildren().addAll(

            createAvoidCard(
                "logo/junkfood.png",
                "🍔",
                "Junk Food",
                "High in calories,\nlow in nutrients"
            ),

            createAvoidCard(
                "logo/dairyProduct.png",
                "🥛",
                "Unpasteurized Dairy",
                "May contain harmful\nbacteria"
            ),

            createAvoidCard(
                "logo/rawMeetEgg.png",
                "🥩",
                "Raw or Undercooked",
                "Risk of food\ninfection"
            ),

            createAvoidCard(
                "logo/cafenine.png",
                "☕",
                "High Caffeine",
                "Limit caffeine\nintake"
            ),

            createAvoidCard(
                "logo/alcoholSmocking.png",
                "🚫",
                "Alcohol & Smoking",
                "Highly harmful for\nbaby's development"
            ),

            createAvoidCard(
                "logo/exccesSugar.png",
                "🍩",
                "Excess Sugar",
                "May lead to excess\nsugar intake"
            )
        );

        Button detailed =
            createSmallOutlineButton(
                "View Detailed List  →"
            );

        detailed.setOnAction(e ->
            showWhatToAvoid()
        );

        card.getChildren().addAll(
            title,
            subtitle,
            items,
            detailed
        );

        return card;
    }

    // =========================================================
    // AVOID CARD
    // =========================================================

    private VBox createAvoidCard(
            String imageName,
            String emoji,
            String title,
            String description) {

        VBox box =
            new VBox();

        box.setAlignment(
            Pos.CENTER
        );

        box.setSpacing(7);

        box.setPrefWidth(130);

        box.setMinWidth(120);

        box.setPadding(
            new Insets(9)
        );

        box.setStyle(
            "-fx-background-color: #FFF8FA;" +
            "-fx-border-color: #F3D8E3;" +
            "-fx-border-radius: 12;" +
            "-fx-background-radius: 12;"
        );

        StackPane imageBox =
            createFoodImage(
                imageName,
                emoji,
                90,
                70
            );

        Label name =
            new Label(title);

        name.setWrapText(true);

        name.setAlignment(
            Pos.CENTER
        );

        name.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #D14A78;"
        );

        Label desc =
            new Label(description);

        desc.setWrapText(true);

        desc.setAlignment(
            Pos.CENTER
        );

        desc.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-text-fill: #666680;"
        );

        box.getChildren().addAll(
            imageBox,
            name,
            desc
        );

        return box;
    }

    // =========================================================
    // NUTRITION AT A GLANCE
    // =========================================================

    private VBox createNutritionGlanceCard() {

        VBox card =
            createWhiteCard();

        Label title =
            new Label(
                "🌿 Nutrition at a Glance"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        GridPane grid =
            new GridPane();

        grid.setHgap(15);

        grid.setVgap(12);

        grid.add(
            createNutritionItem(
                "🍃",
                "Folate",
                "For baby's brain\ndevelopment"
            ),
            0, 0
        );

        grid.add(
            createNutritionItem(
                "🩸",
                "Iron",
                "For more energy\n& blood volume"
            ),
            1, 0
        );

        grid.add(
            createNutritionItem(
                "🦴",
                "Calcium",
                "For strong bones\n& teeth"
            ),
            0, 1
        );

        grid.add(
            createNutritionItem(
                "🥚",
                "Protein",
                "For baby's growth\n& tissue repair"
            ),
            1, 1
        );

        card.getChildren().addAll(
            title,
            grid
        );

        return card;
    }

    // =========================================================
    // NUTRITION ITEM
    // =========================================================

    private HBox createNutritionItem(
            String emoji,
            String title,
            String description) {

        HBox box =
            new HBox();

        box.setSpacing(9);

        box.setAlignment(
            Pos.CENTER_LEFT
        );

        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 28px;"
        );

        VBox text =
            new VBox();

        text.setSpacing(1);

        Label name =
            new Label(title);

        name.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #4B9B65;"
        );

        Label desc =
            new Label(description);

        desc.setWrapText(true);

        desc.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        text.getChildren().addAll(
            name,
            desc
        );

        box.getChildren().addAll(
            icon,
            text
        );

        return box;
    }

    // =========================================================
    // DAILY TIPS
    // =========================================================

    private VBox createDailyTipsCard() {

        VBox card =
            createWhiteCard();

        card.setStyle(
            "-fx-background-color: #FFFDF4;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #F1E7BE;" +
            "-fx-border-radius: 18;"
        );

        Label title =
            new Label(
                "💡 Daily Nutrition Tips"
            );

        title.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #C7821B;"
        );

        VBox tips =
            new VBox();

        tips.setSpacing(10);

        tips.getChildren().addAll(

            createTip(
                "Eat small & frequent meals 🍽️"
            ),

            createTip(
                "Drink water regularly throughout the day 💧"
            ),

            createTip(
                "Include seasonal fruits & green vegetables 🥦"
            ),

            createTip(
                "Take your prescribed supplements on time 💊"
            ),

            createTip(
                "Stay active with safe exercises & yoga 🧘‍♀️"
            )
        );

        Button viewTips =
            createOutlineButton(
                "View All Tips  →"
            );

        viewTips.setOnAction(e ->
            showAllNutritionTips()
        );

        card.getChildren().addAll(
            title,
            tips,
            viewTips
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
            "-fx-text-fill: #3C9A68;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        Label label =
            new Label(text);

        label.setWrapText(true);

        label.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #55556D;"
        );

        box.getChildren().addAll(
            icon,
            label
        );

        return box;
    }

    // =========================================================
    // HEALTHY REMINDER
    // =========================================================

    private VBox createHealthyReminderCard() {

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
                "Healthy Reminder 💗",
                FontAwesomeIcon.HEART
            );

        HBox content =
            new HBox();

        content.setSpacing(10);

        VBox text =
            new VBox();

        text.setSpacing(8);

        Label message =
            new Label(
                "A balanced diet today builds a\n" +
                "healthy tomorrow for you and your baby."
            );

        message.setWrapText(true);

        message.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #55556D;" +
            "-fx-line-spacing: 4px;"
        );

        StackPane image =
            createFoodImage(
                "healthyMother.png",
                "🤰",
                130,
                115
            );

        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );

        text.getChildren().add(
            message
        );

        content.getChildren().addAll(
            text,
            image
        );

        card.getChildren().addAll(
            heading,
            content
        );

        return card;
    }

    // =========================================================
    // PERSONAL DIET
    // =========================================================

    private VBox createPersonalDietCard() {

        VBox card =
            createWhiteCard();

        card.setStyle(
            "-fx-background-color: #FAF5FF;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E5D8F1;" +
            "-fx-border-radius: 18;"
        );

        HBox content =
            new HBox();

        content.setSpacing(12);

        StackPane doctorImage =
            createFoodImage(
                "nutritionist.png",
                "👩‍⚕️",
                100,
                100
            );

        VBox text =
            new VBox();

        text.setSpacing(8);

        Label title =
            new Label(
                "Need Personal Diet Plan?"
            );

        title.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #7041A5;"
        );

        Label description =
            new Label(
                "Get AI-powered nutrition guidance\n" +
                "based on your pregnancy needs."
            );

        description.setWrapText(true);

        description.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        Button consult =
            createOutlineButton(
                "🤖 Consult Nutritionist  →"
            );

        consult.setOnAction(e ->
            showNutritionistAI()
        );

        text.getChildren().addAll(
            title,
            description,
            consult
        );

        content.getChildren().addAll(
            doctorImage,
            text
        );

        card.getChildren().add(
            content
        );

        return card;
    }

    // =========================================================
    // IMAGE LOADER
    // =========================================================

    private StackPane createFoodImage(
            String imageName,
            String emoji,
            double width,
            double height) {

        StackPane holder =
            new StackPane();

        holder.setPrefSize(
            width,
            height
        );

        holder.setMinSize(
            width,
            height
        );

        holder.setMaxSize(
            width,
            height
        );

        holder.setAlignment(
            Pos.CENTER
        );

        holder.setStyle(
            "-fx-background-color: #FFF7FA;" +
            "-fx-background-radius: 12;"
        );

        if (imageName != null &&
            !imageName.trim().isEmpty()) {

            String cleanName =
                imageName.trim();

            String resourcePath;

            if (cleanName.startsWith("/")) {

                resourcePath =
                    cleanName;

            } else if (
                cleanName.startsWith("assets/")
            ) {

                resourcePath =
                    "/" + cleanName;

            } else if (
                cleanName.startsWith("images/")
            ) {

                resourcePath =
                    "/assets/" + cleanName;

            } else if (
                cleanName.startsWith("logo/")
            ) {

                resourcePath =
                    "/assets/images/" +
                    cleanName;

            } else {

                resourcePath =
                    "/assets/images/logo/" +
                    cleanName;
            }

            try {

                var resource =
                    getClass().getResource(
                        resourcePath
                    );

                if (resource != null) {

                    Image image =
                        new Image(
                            resource.toExternalForm(),
                            false
                        );

                    if (!image.isError()) {

                        ImageView imageView =
                            new ImageView(image);

                        imageView.setFitWidth(
                            width - 6
                        );

                        imageView.setFitHeight(
                            height - 6
                        );

                        imageView.setPreserveRatio(
                            true
                        );

                        imageView.setSmooth(
                            true
                        );

                        holder.getChildren().add(
                            imageView
                        );

                        return holder;
                    }

                    System.out.println(
                        "IMAGE LOAD ERROR: " +
                        resourcePath
                    );

                } else {

                    System.out.println(
                        "IMAGE NOT FOUND: " +
                        resourcePath
                    );
                }

            } catch (Exception ex) {

                System.out.println(
                    "IMAGE ERROR: " +
                    resourcePath
                );

                System.out.println(
                    ex.getMessage()
                );
            }
        }

        Label fallback =
            new Label(emoji);

        fallback.setStyle(
            "-fx-font-size: 42px;"
        );

        holder.getChildren().add(
            fallback
        );

        return holder;
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
            "-fx-font-size: 18px;" +
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
            new Insets(16)
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

        button.setAlignment(
            Pos.CENTER
        );

        button.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 9px 20px;"
        );

        button.setCursor(
            Cursor.HAND
        );

        return button;
    }

    // =========================================================
    // GREEN BUTTON
    // =========================================================

    private Button createGreenButton(
            String text) {

        Button button =
            new Button(text);

        button.setStyle(
            "-fx-background-color: #3C9A68;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8px 14px;"
        );

        button.setCursor(
            Cursor.HAND
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
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #DCC9EC;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 9px 16px;" +
            "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================================
    // SMALL OUTLINE BUTTON
    // =========================================================

    private Button createSmallOutlineButton(
            String text) {

        Button button =
            createOutlineButton(text);

        button.setTextFill(
            Color.web(PINK)
        );

        return button;
    }

    // =========================================================
    // GENERIC POPUP
    // =========================================================

    private void showPopup(
            String title,
            String subtitle,
            VBox popupContent) {

        Dialog<Void> dialog =
            new Dialog<>();

        dialog.setTitle(title);

        DialogPane dialogPane =
            dialog.getDialogPane();

        dialogPane.setHeaderText(null);

        dialogPane.setStyle(
            "-fx-background-color: #FFFDFE;"
        );

        VBox main =
            new VBox();

        main.setSpacing(12);

        main.setPadding(
            new Insets(18)
        );

        Label heading =
            new Label(title);

        heading.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label sub =
            new Label(subtitle);

        sub.setWrapText(true);

        sub.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        Separator separator =
            new Separator();

        main.getChildren().addAll(
            heading,
            sub,
            separator,
            popupContent
        );

        ScrollPane popupScroll =
            new ScrollPane(main);

        popupScroll.setFitToWidth(true);

        popupScroll.setPannable(true);

        popupScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        popupScroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        popupScroll.setPrefViewportWidth(620);

        popupScroll.setPrefViewportHeight(500);

        popupScroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        dialogPane.setContent(
            popupScroll
        );

        dialogPane.getButtonTypes().add(
            ButtonType.CLOSE
        );

        dialog.showAndWait();
    }

    // =========================================================
    // MONTHLY DIET FULL PLAN
    // =========================================================

    private void showMonthlyDietPlan() {

        VBox content =
            new VBox();

        content.setSpacing(14);

        content.getChildren().addAll(

            createPopupSection(
                "🌅 Breakfast",
                "Start your day with a nutritious meal.",
                "• Poha / Upma / Oats\n" +
                "• One seasonal fruit\n" +
                "• 5-6 almonds or other nuts\n" +
                "• Milk or another suitable calcium-rich food"
            ),

            createPopupSection(
                "🍱 Lunch",
                "Keep lunch balanced with carbohydrates, protein and vegetables.",
                "• 2 Phulka / Chapati\n" +
                "• Dal or another protein source\n" +
                "• Seasonal vegetables\n" +
                "• Rice as required\n" +
                "• Curd / suitable dairy option"
            ),

            createPopupSection(
                "☕ Evening Snack",
                "Choose a light and nutritious snack.",
                "• Fruit bowl\n" +
                "• Sprouts\n" +
                "• Roasted chana\n" +
                "• Coconut water\n" +
                "• Buttermilk"
            ),

            createPopupSection(
                "🌙 Dinner",
                "Prefer a balanced and easy-to-digest dinner.",
                "• Veg Khichdi\n" +
                "• Dal + Rice\n" +
                "• Chapati + vegetables\n" +
                "• Vegetable soup\n" +
                "• Curd"
            ),

            createPopupSection(
                "💧 Hydration",
                "Drink fluids regularly throughout the day.",
                "• Prefer water as the main drink\n" +
                "• Keep yourself hydrated throughout the day\n" +
                "• Coconut water or buttermilk can be included when suitable"
            ),

            createPopupSection(
                "💊 Supplements",
                "Take pregnancy supplements only as prescribed.",
                "• Follow the prescribed dose and timing\n" +
                "• Do not start or stop supplements on your own\n" +
                "• Ask your doctor if you have questions"
            )
        );

        showPopup(
            "🍃 Full Monthly Diet Plan",
            "A general pregnancy nutrition guide. Your healthcare professional can personalize it for you.",
            content
        );
    }

    // =========================================================
    // CHILD FULL DIET PLAN
    // =========================================================

    private void showChildDietPlan() {

        VBox content =
            new VBox();

        content.setSpacing(14);

        content.getChildren().addAll(

            createPopupSection(
                "🥣 6-9 Months",
                "Introduce age-appropriate complementary foods gradually.",
                "• Rice cereal / dal water\n" +
                "• Soft khichdi\n" +
                "• Mashed vegetables\n" +
                "• Banana or suitable fruit puree\n" +
                "• Soft porridge\n" +
                "• Continue breast milk or formula as appropriate"
            ),

            createPopupSection(
                "🍚 9-12 Months",
                "Gradually introduce more textures and variety.",
                "• Soft idli / upma / porridge\n" +
                "• Soft rice + dal + vegetables\n" +
                "• Mashed or soft seasonal fruits\n" +
                "• Khichdi\n" +
                "• Soft chapati with dal"
            ),

            createPopupSection(
                "🍱 1-2 Years",
                "Offer a varied family-style diet with suitable textures.",
                "• Poha / Upma / Dosa\n" +
                "• Rice + Dal + Vegetables\n" +
                "• Chapati + Dal + Vegetables\n" +
                "• Seasonal fruits\n" +
                "• Curd / suitable dairy foods\n" +
                "• Homemade nutritious snacks"
            ),

            createPopupSection(
                "💧 Hydration",
                "Offer appropriate fluids regularly.",
                "• Water\n" +
                "• Breast milk / formula as appropriate for age\n" +
                "• Avoid making sugary drinks a regular part of the diet"
            ),

            createPopupSection(
                "🧼 Food Safety",
                "Safe preparation is especially important for young children.",
                "• Wash hands before preparing food\n" +
                "• Use clean utensils\n" +
                "• Use safe ingredients\n" +
                "• Introduce new foods gradually\n" +
                "• Use age-appropriate textures"
            )
        );

        showPopup(
            "👶 Full Child Diet Plan",
            "General complementary-feeding information. Follow your pediatrician's advice for your child's individual needs.",
            content
        );
    }

    // =========================================================
    // WHAT TO AVOID - FULL LIST
    // =========================================================

    private void showWhatToAvoid() {

        VBox content =
            new VBox();

        content.setSpacing(14);

        content.getChildren().addAll(

            createPopupSection(
                "🍔 Junk Food",
                "Limit foods that provide lots of calories but relatively few nutrients.",
                "Examples include highly processed snacks, fried foods and sugary packaged foods."
            ),

            createPopupSection(
                "🥛 Unpasteurized Dairy",
                "Choose pasteurized dairy products.",
                "Unpasteurized milk and dairy products can carry harmful bacteria."
            ),

            createPopupSection(
                "🥩 Raw or Undercooked Foods",
                "Food should be prepared safely and cooked appropriately.",
                "Avoid raw or undercooked meat, eggs and other foods when they may pose an infection risk."
            ),

            createPopupSection(
                "☕ High Caffeine",
                "Keep caffeine intake within the limit recommended by your healthcare professional.",
                "Caffeine can be present in coffee, tea, cola, energy drinks and chocolate."
            ),

            createPopupSection(
                "🚫 Alcohol & Smoking",
                "Avoid alcohol and tobacco exposure during pregnancy.",
                "If you need help stopping tobacco use, speak with a healthcare professional."
            ),

            createPopupSection(
                "🍩 Excess Sugar",
                "Limit foods and drinks high in added sugar.",
                "Prefer whole fruits and balanced meals instead of frequently consuming sugary snacks and drinks."
            ),

            createPopupSection(
                "🥤 Sugary Drinks",
                "Limit drinks with high amounts of added sugar.",
                "Choose water as the main drink and include other suitable fluids when needed."
            ),

            createPopupSection(
                "🍟 Excess Fried Food",
                "Avoid making deep-fried foods a regular part of your diet.",
                "Prefer balanced meals with vegetables, whole grains and suitable protein sources."
            ),

            createPopupSection(
                "⚠️ Unwashed Foods",
                "Food hygiene is important during pregnancy.",
                "Wash fruits and vegetables properly and maintain clean food preparation practices."
            )
        );

        showPopup(
            "🚫 What to Avoid",
            "Food-safety and healthy-eating reminders during pregnancy.",
            content
        );
    }

    // =========================================================
    // ALL DAILY NUTRITION TIPS
    // =========================================================

    private void showAllNutritionTips() {

        VBox content =
            new VBox();

        content.setSpacing(14);

        content.getChildren().addAll(

            createPopupSection(
                "🍽️ Eat Small & Frequent Meals",
                "Smaller meals may be easier to manage for some people.",
                "Choose balanced meals and snacks throughout the day according to your appetite and healthcare advice."
            ),

            createPopupSection(
                "💧 Stay Hydrated",
                "Drink water regularly throughout the day.",
                "Keep water available and drink regularly according to your needs."
            ),

            createPopupSection(
                "🥦 Eat Fruits & Vegetables",
                "Include a variety of seasonal produce.",
                "Try to include different types of fruits and vegetables across your meals."
            ),

            createPopupSection(
                "🌾 Choose Nutritious Foods",
                "Build meals around nutritious food choices.",
                "Include whole grains, pulses, vegetables, fruits, nuts and suitable protein sources."
            ),

            createPopupSection(
                "🥚 Include Protein",
                "Protein supports normal growth and tissue maintenance.",
                "Include suitable sources such as dal, beans, dairy, eggs or other protein foods according to your dietary preferences."
            ),

            createPopupSection(
                "💊 Take Prescribed Supplements",
                "Follow your healthcare professional's instructions.",
                "Do not change the dose or add supplements without discussing them with your doctor."
            ),

            createPopupSection(
                "🧘‍♀️ Stay Active Safely",
                "Movement can be part of a healthy pregnancy when medically appropriate.",
                "Choose activities approved for you by your healthcare professional."
            ),

            createPopupSection(
                "😴 Prioritize Rest",
                "Good nutrition works together with adequate rest.",
                "Maintain a regular sleep and rest routine as much as possible."
            ),

            createPopupSection(
                "👩‍⚕️ Regular Checkups",
                "Keep your scheduled prenatal appointments.",
                "Discuss nutrition, supplements and any pregnancy-related concerns with your healthcare professional."
            ),

            createPopupSection(
                "🧼 Food Safety",
                "Safe food handling is important.",
                "Wash hands, use clean utensils, store food safely and avoid foods that may carry infection risks."
            )
        );

        showPopup(
            "💡 All Daily Nutrition Tips",
            "Simple nutrition and healthy-lifestyle reminders for pregnancy.",
            content
        );
    }

    // =========================================================
    // OLD CONSULTATION INFORMATION
    // =========================================================

    private void showNutritionistInfo() {

        VBox content =
            new VBox();

        content.setSpacing(14);

        content.getChildren().addAll(

            createPopupSection(
                "👩‍⚕️ Personalized Diet Consultation",
                "Get nutrition guidance based on your individual health profile.",
                "A nutritionist or registered dietitian can help plan meals according to your pregnancy stage, food preferences, nutritional requirements and medical advice."
            ),

            createPopupSection(
                "📋 What to Discuss",
                "Keep your relevant information ready for the consultation.",
                "• Current pregnancy week\n" +
                "• Usual eating pattern\n" +
                "• Food preferences and restrictions\n" +
                "• Doctor-advised dietary restrictions\n" +
                "• Current supplements or medicines"
            ),

            createPopupSection(
                "🥗 Personalized Plan",
                "Your diet plan can be adjusted to your individual requirements.",
                "The nutrition professional can suggest suitable meal timing, food choices, portions and alternatives based on your needs."
            ),

            createPopupSection(
                "🩺 Health Conditions",
                "Some conditions require specific dietary guidance.",
                "For conditions such as gestational diabetes, anemia, food allergies or other health concerns, follow your doctor's or qualified nutrition professional's specific advice."
            ),

            createPopupSection(
                "💗 Before Your Consultation",
                "Prepare your questions in advance.",
                "Write down any food-related concerns, symptoms, allergies, dietary preferences or questions about supplements that you want to discuss."
            )
        );

        showPopup(
            "👩‍⚕️ Consult Nutritionist",
            "Personalized nutrition support for a healthier pregnancy.",
            content
        );
    }

    // =========================================================
    // AI NUTRITIONIST
    // =========================================================

    private void showNutritionistAI() {

        Dialog<Void> dialog =
            new Dialog<>();

        dialog.setTitle(
            "🤖 MaaCare AI Nutritionist"
        );

        DialogPane dialogPane =
            dialog.getDialogPane();

        dialogPane.setHeaderText(null);

        dialogPane.setStyle(
            "-fx-background-color: #FFFDFE;"
        );

        VBox main =
            new VBox();

        main.setSpacing(12);

        main.setPadding(
            new Insets(18)
        );

        Label heading =
            new Label(
                "👩‍⚕️ Consult Nutritionist"
            );

        heading.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );

        Label subtitle =
            new Label(
                "Get AI-powered nutrition guidance from MaaCare AI."
            );

        subtitle.setWrapText(true);

        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        Separator separator =
            new Separator();

        // =====================================================
        // AI CONTENT
        // =====================================================

        VBox aiTipsBox =
            new VBox();

        aiTipsBox.setSpacing(12);

        aiTipsBox.setPadding(
            new Insets(8)
        );

        Label initial =
            new Label(
                "Preparing your personalized nutrition guidance..."
            );

        initial.setWrapText(true);

        initial.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        aiTipsBox.getChildren().add(
            initial
        );

        ScrollPane aiScroll =
            new ScrollPane(aiTipsBox);

        aiScroll.setFitToWidth(true);

        aiScroll.setPannable(true);

        aiScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        aiScroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        aiScroll.setPrefViewportHeight(
            360
        );

        aiScroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        // =====================================================
        // LOADING OVERLAY
        // =====================================================

        StackPane aiPane =
            new StackPane();

        ProgressIndicator progress =
            new ProgressIndicator();

        progress.setPrefSize(
            42,
            42
        );

        VBox loadingBox =
            new VBox();

        loadingBox.setAlignment(
            Pos.CENTER
        );

        loadingBox.setSpacing(8);

        Label loadingText =
            new Label(
                "MaaCare AI is thinking..."
            );

        loadingText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #7041A5;" +
            "-fx-font-weight: bold;"
        );

        loadingBox.getChildren().addAll(
            progress,
            loadingText
        );

        StackPane loadingOverlay =
            new StackPane(
                loadingBox
            );

        loadingOverlay.setStyle(
            "-fx-background-color: rgba(255,255,255,0.90);" +
            "-fx-background-radius: 12;"
        );

        aiPane.getChildren().addAll(
            aiScroll,
            loadingOverlay
        );

        // =====================================================
        // REQUEST BUTTON
        // =====================================================

        Button requestButton =
            createGradientButton(
                "📋 Request Nutrition Consultation"
            );

        requestButton.setOnAction(e -> {

            requestButton.setText(
                "✓ Consultation Request Sent"
            );

            requestButton.setDisable(true);
        });

        // =====================================================
        // DETAILS
        // =====================================================

        VBox details =
            new VBox();

        details.setSpacing(12);

        details.getChildren().addAll(
            heading,
            subtitle,
            separator,
            aiPane,
            requestButton
        );

        VBox.setVgrow(
            aiPane,
            Priority.ALWAYS
        );

        ScrollPane detailsScroll =
            new ScrollPane(details);

        detailsScroll.setFitToWidth(true);

        detailsScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        detailsScroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        detailsScroll.setPrefViewportWidth(
            650
        );

        detailsScroll.setPrefViewportHeight(
            560
        );

        detailsScroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        dialogPane.setContent(
            detailsScroll
        );

        dialogPane.getButtonTypes().add(
            ButtonType.CLOSE
        );

        // =====================================================
        // AI PROMPT
        // =====================================================

        String prompt =
            """
            You are MaaCare AI, a pregnancy nutrition specialist.

            Generate exactly 5 practical and safe nutrition tips
            for a pregnant woman.

            The tips should cover:
            - Balanced nutrition
            - Protein
            - Iron and folate
            - Calcium
            - Fruits and vegetables
            - Hydration
            - Healthy meals and snacks

            Safety rules:
            - Do not diagnose any condition.
            - Do not prescribe medicines.
            - Do not tell the user to stop medicines or supplements.
            - Do not provide dangerous or extreme dietary advice.
            - Recommend a qualified healthcare professional for
              individualized medical or nutrition concerns.

            STRICT OUTPUT FORMAT:

            TIP|emoji|title|description

            Return exactly 5 lines.

            Example format only:
            TIP|🥦|Eat More Vegetables|Include a variety of vegetables in balanced meals.

            Important:
            - No Markdown.
            - No headings.
            - No tables.
            - No HTML.
            - No image URLs.
            - No images.
            - No introduction.
            - No conclusion.
            - Do not use the | character inside title or description.
            """;

        // =====================================================
        // AI TASK
        // =====================================================

        Task<String> task =
            new Task<>() {

                @Override
                protected String call() {

                    return assistant.ask(
                        prompt
                    );
                }
            };

        // =====================================================
        // SUCCESS
        // =====================================================

        task.setOnSucceeded(e -> {

            loadingOverlay.setVisible(
                false
            );

            aiTipsBox.getChildren().clear();

            String response =
                task.getValue();

            if (
                response == null ||
                response.trim().isEmpty()
            ) {

                Label empty =
                    new Label(
                        "MaaCare AI could not generate tips right now."
                    );

                empty.setWrapText(true);

                empty.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: #D14A78;"
                );

                aiTipsBox.getChildren().add(
                    empty
                );

                return;
            }

            displayNutritionAITips(
                response,
                aiTipsBox
            );
        });

        // =====================================================
        // FAILURE
        // =====================================================

        task.setOnFailed(e -> {

            loadingOverlay.setVisible(
                false
            );

            aiTipsBox.getChildren().clear();

            Throwable error =
                task.getException();

            String message =
                error != null
                ? error.getMessage()
                : "Unknown AI error.";

            message =
                cleanAIResponse(message);

            Label errorLabel =
                new Label(
                    "⚠️ MaaCare AI is currently unavailable.\n\n" +
                    message
                );

            errorLabel.setWrapText(true);

            errorLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #C84A6F;"
            );

            aiTipsBox.getChildren().add(
                errorLabel
            );
        });

        // =====================================================
        // START AI THREAD
        // =====================================================

        Thread aiThread =
            new Thread(
                task,
                "MaaCare-Nutrition-AI-Thread"
            );

        aiThread.setDaemon(true);

        aiThread.start();

        dialog.showAndWait();
    }

    // =========================================================
    // DISPLAY AI TIPS
    // =========================================================

    private void displayNutritionAITips(
            String response,
            VBox container) {

        container.getChildren().clear();

        String cleanedResponse =
            response
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim();

        String[] lines =
            cleanedResponse.split("\n");

        int tipCount = 0;

        for (String rawLine : lines) {

            if (tipCount >= 5) {
                break;
            }

            if (
                rawLine == null ||
                rawLine.trim().isEmpty()
            ) {
                continue;
            }

            String line =
                cleanAIText(
                    rawLine
                ).trim();

            if (
                line.isEmpty() ||
                line.equals("```")
            ) {
                continue;
            }

            String[] parts =
                line.split(
                    "\\|",
                    4
                );

            if (
                parts.length != 4
            ) {
                continue;
            }

            if (
                !parts[0]
                    .trim()
                    .equalsIgnoreCase("TIP")
            ) {
                continue;
            }

            String emoji =
                cleanAIText(
                    parts[1]
                ).trim();

            String title =
                cleanAIText(
                    parts[2]
                ).trim();

            String description =
                cleanAIText(
                    parts[3]
                ).trim();

            if (
                title.isEmpty() ||
                description.isEmpty()
            ) {
                continue;
            }

            container.getChildren().add(
                createNutritionistInfo(
                    emoji,
                    title,
                    description
                )
            );

            tipCount++;
        }

        // =====================================================
        // FALLBACK
        // =====================================================

        if (tipCount == 0) {

            Label fallback =
                new Label(
                    "MaaCare AI returned an unexpected response.\n" +
                    "Please try the consultation again."
                );

            fallback.setWrapText(true);

            fallback.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #C84A6F;"
            );

            container.getChildren().add(
                fallback
            );
        }
    }

    // =========================================================
    // AI TEXT CLEANER
    // =========================================================

    private String cleanAIText(
            String text) {

        if (text == null) {
            return "";
        }

        String cleaned =
            text.trim();

        // Remove Markdown images
        cleaned =
            cleaned.replaceAll(
                "!\\[([^\\]]*)\\]\\([^)]*\\)",
                "$1"
            );

        // Remove Markdown links but keep visible text
        cleaned =
            cleaned.replaceAll(
                "\\[([^\\]]+)\\]\\([^)]*\\)",
                "$1"
            );

        // Remove HTML tags
        cleaned =
            cleaned.replaceAll(
                "<[^>]*>",
                ""
            );

        // Remove emphasis
        cleaned =
            cleaned.replace(
                "**",
                ""
            );

        cleaned =
            cleaned.replace(
                "__",
                ""
            );

        // Remove inline code markers
        cleaned =
            cleaned.replace(
                "`",
                ""
            );

        // Remove code fences
        cleaned =
            cleaned.replace(
                "```",
                ""
            );

        return cleaned.trim();
    }

    // =========================================================
    // AI RESPONSE CLEANER
    // =========================================================

    private String cleanAIResponse(
            String response) {

        if (response == null) {
            return "";
        }

        return cleanAIText(
            response
        );
    }

    // =========================================================
    // AI TIP CARD
    // =========================================================

    private VBox createNutritionistInfo(
            String emoji,
            String title,
            String description) {

        VBox card =
            new VBox();

        card.setSpacing(7);

        card.setPadding(
            new Insets(12)
        );

        card.setStyle(
            "-fx-background-color: #FAF5FF;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #E3D2F0;" +
            "-fx-border-radius: 14;"
        );

        HBox heading =
            new HBox();

        heading.setSpacing(9);

        heading.setAlignment(
            Pos.CENTER_LEFT
        );

        Label emojiLabel =
            new Label(
                emoji
            );

        emojiLabel.setStyle(
            "-fx-font-size: 25px;"
        );

        Label titleLabel =
            new Label(
                title
            );

        titleLabel.setWrapText(true);

        titleLabel.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #7041A5;"
        );

        heading.getChildren().addAll(
            emojiLabel,
            titleLabel
        );

        Text descriptionText =
            new Text(
                description
            );

        descriptionText.setWrappingWidth(
            570
        );

        descriptionText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-fill: #55556D;"
        );

        card.getChildren().addAll(
            heading,
            descriptionText
        );

        return card;
    }

    // =========================================================
    // POPUP SECTION
    // =========================================================

    private VBox createPopupSection(
            String title,
            String subtitle,
            String details) {

        VBox box =
            new VBox();

        box.setSpacing(5);

        box.setPadding(
            new Insets(12)
        );

        box.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #E8E0EA;" +
            "-fx-border-radius: 12;"
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setWrapText(true);

        titleLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );

        Label subtitleLabel =
            new Label(subtitle);

        subtitleLabel.setWrapText(true);

        subtitleLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #77778D;"
        );

        Label detailsLabel =
            new Label(details);

        detailsLabel.setWrapText(true);

        detailsLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #24234F;" +
            "-fx-line-spacing: 4px;"
        );

        box.getChildren().addAll(
            titleLabel,
            subtitleLabel,
            detailsLabel
        );

        return box;
    }
}