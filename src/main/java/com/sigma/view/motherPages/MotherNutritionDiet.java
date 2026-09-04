package com.sigma.view.motherPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.ButtonType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// =============================================================
// MOTHER NUTRITION / DIET PAGE
// =============================================================

public class MotherNutritionDiet {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";

    // Used for tab scrolling
    private ScrollPane mainScrollPane;

    private VBox monthlySection;
    private VBox childSection;
    private VBox avoidSection;

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

        // =====================================================
        // PAGE TITLE
        // =====================================================

        VBox titleBox = new VBox();
        titleBox.setSpacing(3);

        Label title =
            new Label("🥗 Nutrition & Diet");

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

        monthlySection = createMonthlyDietCard();
        childSection = createChildDietCard();
        avoidSection = createWhatToAvoidCard();

        leftContent.getChildren().addAll(
            monthlySection,
            childSection,
            avoidSection
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

        mainScrollPane =
            new ScrollPane(content);

        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setPannable(true);

        mainScrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        mainScrollPane.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        mainScrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;" +
            "-fx-border-color: transparent;"
        );

        page.getChildren().add(
            mainScrollPane
        );

        VBox.setVgrow(
            mainScrollPane,
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

        // =====================================================
        // TAB ACTIONS
        // =====================================================

        overview.setOnAction(e -> {

            scrollToTop();

            setActiveTab(
                overview,
                monthly,
                child,
                avoid
            );
        });

        monthly.setOnAction(e -> {

            scrollToSection(monthlySection);

            setActiveTab(
                monthly,
                overview,
                child,
                avoid
            );
        });

        child.setOnAction(e -> {

            scrollToSection(childSection);

            setActiveTab(
                child,
                overview,
                monthly,
                avoid
            );
        });

        avoid.setOnAction(e -> {

            scrollToSection(avoidSection);

            setActiveTab(
                avoid,
                overview,
                monthly,
                child
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

        return button;
    }

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

    private void setActiveTab(
            Button active,
            Button other1,
            Button other2,
            Button other3) {

        applyTabStyle(active, true);
        applyTabStyle(other1, false);
        applyTabStyle(other2, false);
        applyTabStyle(other3, false);
    }

    // =========================================================
    // SCROLL TO TOP
    // =========================================================

    private void scrollToTop() {

        if (mainScrollPane != null) {
            mainScrollPane.setVvalue(0);
        }
    }

    // =========================================================
    // SCROLL TO SECTION
    // =========================================================

    private void scrollToSection(
            VBox section) {

        if (mainScrollPane == null ||
            section == null) {
            return;
        }

        javafx.application.Platform.runLater(() -> {

            double contentHeight =
                mainScrollPane.getContent()
                    .getBoundsInLocal()
                    .getHeight();

            double viewportHeight =
                mainScrollPane.getViewportBounds()
                    .getHeight();

            double sectionY =
                section.getBoundsInParent()
                    .getMinY();

            double maxScroll =
                contentHeight - viewportHeight;

            if (maxScroll > 0) {

                mainScrollPane.setVvalue(
                    Math.min(
                        1,
                        Math.max(
                            0,
                            sectionY / maxScroll
                        )
                    )
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
        meals.setAlignment(Pos.CENTER);

        setSecondTrimesterMeals(
            meals
        );

        // =====================================================
        // TRIMESTER CHANGE
        // =====================================================

        trimester.setOnAction(e -> {

            String selected =
                trimester.getValue();

            if (selected == null) {
                return;
            }

            if (selected.startsWith("1st")) {

                setFirstTrimesterMeals(
                    meals
                );

            } else if (
                selected.startsWith("2nd")
            ) {

                setSecondTrimesterMeals(
                    meals
                );

            } else {

                setThirdTrimesterMeals(
                    meals
                );
            }
        });

        // =====================================================
        // FULL PLAN BUTTON
        // =====================================================

        Button fullPlan =
            createGradientButton(
                "View Full Monthly Diet Plan  →"
            );

        fullPlan.setOnAction(e ->
            showMonthlyDietDialog()
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
                "motherBreakfast.png",
                "☀️",
                "Poha / Upma",
                "1 Banana",
                "5-6 Almonds"
            ),

            createMealCard(
                "Lunch",
                "Balanced & nutritious",
                "motherLunch.png",
                "🍱",
                "2 Phulka",
                "1 Cup Dal",
                "Seasonal Vegetables"
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
    // 2ND TRIMESTER
    // =========================================================

    private void setSecondTrimesterMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createMealCard(
                "Breakfast",
                "Kickstart your day",
                "motherBreakfast.png",
                "☀️",
                "Oats / Poha / Upma",
                "1 Banana",
                "5-6 Almonds"
            ),

            createMealCard(
                "Lunch",
                "Stay energetic",
                "motherLunch.png",
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
                "motherBreakfast.png",
                "☀️",
                "Paratha / Poha",
                "1 Fruit",
                "Almonds & Walnuts"
            ),

            createMealCard(
                "Lunch",
                "Protein rich meal",
                "motherLunch.png",
                "🍛",
                "2 Phulka",
                "Dal",
                "Rice"
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
        meals.setAlignment(Pos.CENTER);

        setSixToNineMonthsMeals(
            meals
        );

        // =====================================================
        // AGE CHANGE
        // =====================================================

        age.setOnAction(e -> {

            String selectedAge =
                age.getValue();

            if (selectedAge == null) {
                return;
            }

            if (
                selectedAge.equals(
                    "6-9 Months"
                )
            ) {

                setSixToNineMonthsMeals(
                    meals
                );

            } else if (
                selectedAge.equals(
                    "9-12 Months"
                )
            ) {

                setNineToTwelveMonthsMeals(
                    meals
                );

            } else {

                setOneToTwoYearsMeals(
                    meals
                );
            }
        });

        // =====================================================
        // TIP
        // =====================================================

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
            showChildDietDialog()
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
                "childBreakfast.png",
                "🥣",
                "Rice Cereal / Dal Water"
            ),

            createChildMeal(
                "Lunch",
                "babylunch.png",
                "🍚",
                "Mashed Dal + Rice / Khichdi"
            ),

            createChildMeal(
                "Evening Snack",
                "childSnack.png",
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
                "childBreakfast.png",
                "🥣",
                "Soft Idli / Upma / Porridge"
            ),

            createChildMeal(
                "Lunch",
                "babylunch.png",
                "🍛",
                "Soft Rice + Dal + Vegetables"
            ),

            createChildMeal(
                "Evening Snack",
                "childSnack.png",
                "🍎",
                "Banana / Seasonal Fruit"
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
    // 1-2 YEARS
    // =========================================================

    private void setOneToTwoYearsMeals(
            HBox meals) {

        meals.getChildren().setAll(

            createChildMeal(
                "Breakfast",
                "childBreakfast.png",
                "🥞",
                "Poha / Upma / Dosa"
            ),

            createChildMeal(
                "Lunch",
                "babylunch.png",
                "🍱",
                "Rice + Dal + Vegetables + Curd"
            ),

            createChildMeal(
                "Evening Snack",
                "childSnack.png",
                "🍎",
                "Fruit Bowl / Homemade Snack"
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
        box.setPadding(new Insets(8));

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
        items.setAlignment(Pos.CENTER);

        items.getChildren().addAll(

            createAvoidCard(
                "junkfood.png",
                "🍔",
                "Junk Food",
                "High in calories,\nlow in nutrients"
            ),

            createAvoidCard(
                "dairy.png",
                "🥛",
                "Unpasteurized Dairy",
                "May contain harmful\nbacteria"
            ),

            createAvoidCard(
                "rawMeetEgg.png",
                "🥩",
                "Raw or Undercooked",
                "Risk of food\ninfection"
            ),

            createAvoidCard(
                "cafenine.png",
                "☕",
                "High Caffeine",
                "Limit caffeine\nintake"
            ),

            createAvoidCard(
                "alcoholSmocking.png",
                "🚫",
                "Alcohol & Smoking",
                "Highly harmful for\nbaby's development"
            ),

            createAvoidCard(
                "exccesSugar.png",
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
            showAvoidDialog()
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
        name.setAlignment(Pos.CENTER);

        name.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #D14A78;"
        );

        Label desc =
            new Label(description);

        desc.setWrapText(true);
        desc.setAlignment(Pos.CENTER);

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
        box.setAlignment(Pos.CENTER_LEFT);

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
            showTipsDialog()
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
        box.setAlignment(Pos.CENTER_LEFT);

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
                "Get a personalized diet plan\n" +
                "based on your health profile."
            );

        description.setWrapText(true);

        description.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        Button consult =
            createOutlineButton(
                "Consult Nutritionist  →"
            );

        consult.setOnAction(e ->
            showNutritionistDialog()
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

        if (imageName != null) {

            var resource =
                getClass().getResource(
                    "/assets/images/" + imageName
                );

            if (resource != null) {

                javafx.scene.image.Image image =
                    new javafx.scene.image.Image(
                        resource.toExternalForm()
                    );

                javafx.scene.image.ImageView imageView =
                    new javafx.scene.image.ImageView(
                        image
                    );

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
            "-fx-padding: 9px 20px;" +
            "-fx-cursor: hand;"
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
            "-fx-padding: 8px 14px;" +
            "-fx-cursor: hand;"
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

    // =============================================================
    // =============================================================
    // DIALOG SECTION
    // =============================================================
    // =============================================================

    // =========================================================
    // COMMON DIALOG
    // =========================================================

    private Dialog<Void> createInfoDialog(
        String titleText,
        String subtitleText) {

    Dialog<Void> dialog =
        new Dialog<>();

    dialog.setTitle(titleText);
    dialog.setHeaderText(null);

    VBox root =
        new VBox();

    root.setSpacing(15);
    root.setPadding(
        new Insets(22)
    );

    root.setPrefWidth(620);

    root.setStyle(
        "-fx-background-color: white;"
    );

    // =====================================================
    // TITLE
    // =====================================================

    HBox heading =
        new HBox();

    heading.setAlignment(
        Pos.CENTER_LEFT
    );

    heading.setSpacing(10);

    Label title =
        new Label(titleText);

    title.setStyle(
        "-fx-font-size: 23px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: #24234F;"
    );

    heading.getChildren().add(
        title
    );

    // =====================================================
    // SUBTITLE
    // =====================================================

    Label subtitle =
        new Label(subtitleText);

    subtitle.setWrapText(true);

    subtitle.setStyle(
        "-fx-font-size: 14px;" +
        "-fx-text-fill: #77778D;"
    );

    root.getChildren().addAll(
        heading,
        subtitle
    );

    // =====================================================
    // DIALOG CONTENT
    // =====================================================

    dialog.getDialogPane()
        .setContent(root);

    // =====================================================
    // NATIVE CLOSE BUTTON
    // =====================================================

    dialog.getDialogPane()
        .getButtonTypes()
        .add(ButtonType.CLOSE);

    // =====================================================
    // CLOSE BUTTON STYLE
    // =====================================================

    Button closeButton =
        (Button) dialog.getDialogPane()
            .lookupButton(ButtonType.CLOSE);

    closeButton.setText("Close");

    closeButton.setStyle(
        "-fx-background-color: #E84A87;" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 10;" +
        "-fx-padding: 8px 24px;" +
        "-fx-cursor: hand;"
    );

    return dialog;
}

    // =========================================================
    // MONTHLY DIET DIALOG
    // =========================================================

    private void showMonthlyDietDialog() {

        Dialog<Void> dialog =
            createInfoDialog(
                "🍃 Full Monthly Diet Plan",
                "A simple trimester-wise nutrition guide for pregnancy."
            );

        VBox root =
            (VBox) dialog.getDialogPane()
                .getContent();

        VBox details =
            new VBox();

        details.setSpacing(14);

        ScrollPane scroll =
            new ScrollPane(details);

        scroll.setFitToWidth(true);
        scroll.setPrefHeight(470);

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        // =====================================================
        // FIRST TRIMESTER
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🌸 1st Trimester — Months 1 to 3",
                "Focus on balanced meals, folate-rich foods, hydration and foods that are easy to tolerate."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Poha / Upma / Oats + Banana + Almonds"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍱 Lunch",
                "2 Phulka + Dal + Seasonal Vegetables + Curd"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☕ Evening Snack",
                "Fruit Bowl / Sprouts + Coconut Water"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Vegetable Khichdi / Dal Rice + Vegetables"
            )
        );

        // =====================================================
        // SECOND TRIMESTER
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🌷 2nd Trimester — Months 4 to 6",
                "Include a variety of vegetables, fruits, whole grains, protein sources and calcium-rich foods."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Oats / Poha / Upma + Banana + Nuts"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍱 Lunch",
                "2 Phulka + Dal + Rice + Vegetables + Curd"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☕ Evening Snack",
                "Sprouts / Fruit Bowl + Buttermilk"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Veg Khichdi / Soup + Curd + Steamed Vegetables"
            )
        );

        // =====================================================
        // THIRD TRIMESTER
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🌺 3rd Trimester — Months 7 to 9",
                "Keep meals balanced and comfortable. Include protein, iron, calcium, fibre and adequate fluids."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Poha / Paratha + Fruit + Almonds & Walnuts"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍱 Lunch",
                "2 Phulka + Dal + Rice + Seasonal Vegetables"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☕ Evening Snack",
                "Fruit Bowl / Sprouts + Buttermilk"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Light Khichdi / Soup + Curd + Vegetables"
            )
        );

        details.getChildren().add(
            createDialogSection(
                "💧 Everyday Essentials",
                "Stay hydrated, eat a variety of nutritious foods, follow your healthcare professional's supplement advice, and choose safe, properly prepared foods."
            )
        );

        root.getChildren().add(
            2,
            scroll
        );

        dialog.showAndWait();
    }

    // =========================================================
    // CHILD DIET DIALOG
    // =========================================================

    private void showChildDietDialog() {

        Dialog<Void> dialog =
            createInfoDialog(
                "👶 Full Child Diet Plan",
                "Age-wise complementary feeding information for children from 6 months to 2 years."
            );

        VBox root =
            (VBox) dialog.getDialogPane()
                .getContent();

        VBox details =
            new VBox();

        details.setSpacing(14);

        ScrollPane scroll =
            new ScrollPane(details);

        scroll.setFitToWidth(true);
        scroll.setPrefHeight(470);

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        // =====================================================
        // 6-9 MONTHS
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🥣 6–9 Months",
                "Start complementary foods in suitable texture while continuing breastfeeding as recommended by a healthcare professional."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Rice Cereal / Soft Porridge / Dal Water"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍚 Lunch",
                "Mashed Dal + Rice / Soft Khichdi"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍌 Snack",
                "Mashed Banana / Suitable Fruit Puree"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Vegetable Puree / Suji Porridge"
            )
        );

        // =====================================================
        // 9-12 MONTHS
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🍎 9–12 Months",
                "Gradually introduce a wider variety of soft foods and textures appropriate for the child's developmental stage."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Soft Idli / Upma / Porridge"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍛 Lunch",
                "Soft Rice + Dal + Vegetables"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍎 Snack",
                "Banana / Soft Seasonal Fruit"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Vegetable Puree / Soft Porridge"
            )
        );

        // =====================================================
        // 1-2 YEARS
        // =====================================================

        details.getChildren().add(
            createDialogSection(
                "🥗 1–2 Years",
                "Offer a varied family diet with age-appropriate portions and textures."
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "☀️ Breakfast",
                "Poha / Upma / Dosa"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍱 Lunch",
                "Rice + Dal + Vegetables + Curd"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🍎 Snack",
                "Fruit Bowl / Homemade Nutritious Snack"
            )
        );

        details.getChildren().add(
            createDetailMealBox(
                "🌙 Dinner",
                "Soft Vegetable Meal / Khichdi"
            )
        );

        details.getChildren().add(
            createDialogSection(
                "🧼 Important",
                "Maintain hand hygiene, food hygiene and age-appropriate food texture. Avoid foods that may create a choking risk and consult a pediatric healthcare professional when introducing new foods or if the child has feeding concerns."
            )
        );

        root.getChildren().add(
            2,
            scroll
        );

        dialog.showAndWait();
    }

    // =========================================================
    // WHAT TO AVOID DIALOG
    // =========================================================

    private void showAvoidDialog() {

        Dialog<Void> dialog =
            createInfoDialog(
                "🚫 Detailed Foods & Habits to Avoid",
                "Important pregnancy food-safety and healthy-lifestyle reminders."
            );

        VBox root =
            (VBox) dialog.getDialogPane()
                .getContent();

        VBox details =
            new VBox();

        details.setSpacing(12);

        ScrollPane scroll =
            new ScrollPane(details);

        scroll.setFitToWidth(true);
        scroll.setPrefHeight(470);

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        details.getChildren().add(
            createAvoidDetail(
                "🍔",
                "Junk & Highly Processed Food",
                "Limit foods that are high in added sugar, salt or unhealthy fats and low in useful nutrients."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🥛",
                "Unpasteurized Dairy",
                "Choose pasteurized milk and dairy products to reduce food-safety risks."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🥩",
                "Raw or Undercooked Food",
                "Avoid raw or undercooked meat, eggs and other foods that may carry harmful microorganisms."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🐟",
                "High-Mercury Fish",
                "Choose fish varieties that are considered lower in mercury and follow local healthcare guidance."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "☕",
                "Excess Caffeine",
                "Keep caffeine intake within the limit recommended by your healthcare professional."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🚫",
                "Alcohol & Smoking",
                "Avoid alcohol and tobacco exposure during pregnancy."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🍩",
                "Excess Added Sugar",
                "Limit sugary drinks, sweets and highly sugary snacks and prefer nutrient-rich foods."
            )
        );

        details.getChildren().add(
            createAvoidDetail(
                "🧴",
                "Unverified Supplements",
                "Do not start supplements, herbal products or medicines without guidance from a qualified healthcare professional."
            )
        );

        root.getChildren().add(
            2,
            scroll
        );

        dialog.showAndWait();
    }

    // =========================================================
    // DAILY TIPS DIALOG
    // =========================================================

    private void showTipsDialog() {

        Dialog<Void> dialog =
            createInfoDialog(
                "💡 All Nutrition Tips",
                "Simple everyday reminders for maintaining a balanced pregnancy diet."
            );

        VBox root =
            (VBox) dialog.getDialogPane()
                .getContent();

        VBox tips =
            new VBox();

        tips.setSpacing(11);

        tips.getChildren().addAll(

            createLargeTip(
                "🍽️",
                "Eat balanced meals",
                "Include a variety of grains, vegetables, fruits and protein-rich foods."
            ),

            createLargeTip(
                "💧",
                "Stay hydrated",
                "Drink water regularly throughout the day and follow your healthcare professional's advice."
            ),

            createLargeTip(
                "🥦",
                "Choose fresh foods",
                "Include seasonal fruits, vegetables and other nutrient-rich foods."
            ),

            createLargeTip(
                "🩸",
                "Focus on important nutrients",
                "Include sources of iron, folate, calcium and protein in your meals."
            ),

            createLargeTip(
                "💊",
                "Follow supplement advice",
                "Take pregnancy supplements only as prescribed or recommended by your healthcare professional."
            ),

            createLargeTip(
                "🧘‍♀️",
                "Stay safely active",
                "Follow activity and exercise guidance that is appropriate for your pregnancy."
            ),

            createLargeTip(
                "🧼",
                "Maintain food hygiene",
                "Wash hands, use clean utensils and prepare food safely."
            ),

            createLargeTip(
                "👩‍⚕️",
                "Attend regular check-ups",
                "Discuss your diet, symptoms and nutritional needs with your healthcare professional."
            )
        );

        ScrollPane scroll =
            new ScrollPane(tips);

        scroll.setFitToWidth(true);
        scroll.setPrefHeight(450);

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        root.getChildren().add(
            2,
            scroll
        );

        dialog.showAndWait();
    }

    // =========================================================
    // NUTRITIONIST DIALOG
    // =========================================================

    private void showNutritionistDialog() {

        Dialog<Void> dialog =
            createInfoDialog(
                "👩‍⚕️ Consult Nutritionist",
                "Get guidance for a personalized nutrition plan based on your pregnancy and health profile."
            );

        VBox root =
            (VBox) dialog.getDialogPane()
                .getContent();

        VBox details =
            new VBox();

        details.setSpacing(14);

        details.getChildren().add(
            createNutritionistInfo(
                "👩‍⚕️",
                "Personalized Nutrition Guidance",
                "A nutrition professional can help you plan balanced meals according to your nutritional needs, food preferences and healthcare advice."
            )
        );

        details.getChildren().add(
            createNutritionistInfo(
                "📋",
                "What to Discuss",
                "Pregnancy stage, current diet, food preferences, allergies, supplements and any nutrition-related concerns."
            )
        );

        details.getChildren().add(
            createNutritionistInfo(
                "🥗",
                "Personal Diet Plan",
                "Your plan can include meal timing, food variety, healthy snack options and nutrient-focused choices."
            )
        );

        details.getChildren().add(
            createNutritionistInfo(
                "💗",
                "Important",
                "For pregnancy-specific medical or nutritional concerns, always follow advice from your doctor or qualified healthcare professional."
            )
        );

        Button request =
            createGradientButton(
                "Request Nutrition Consultation"
            );

        request.setOnAction(e -> {

            request.setText(
                "✓ Consultation Request Sent"
            );

            request.setDisable(true);
        });

        details.getChildren().add(
            request
        );

        ScrollPane scroll =
            new ScrollPane(details);

        scroll.setFitToWidth(true);
        scroll.setPrefHeight(430);

        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        root.getChildren().add(
            2,
            scroll
        );

        dialog.showAndWait();
    }

    // =========================================================
    // DIALOG SECTION
    // =========================================================

    private VBox createDialogSection(
            String title,
            String description) {

        VBox box =
            new VBox();

        box.setSpacing(5);

        box.setPadding(
            new Insets(12)
        );

        box.setStyle(
            "-fx-background-color: #FFF5FA;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F3D8E3;" +
            "-fx-border-radius: 12;"
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #E84A87;"
        );

        Label descriptionLabel =
            new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666680;"
        );

        box.getChildren().addAll(
            titleLabel,
            descriptionLabel
        );

        return box;
    }

    // =========================================================
    // DETAIL MEAL BOX
    // =========================================================

    private HBox createDetailMealBox(
            String title,
            String food) {

        HBox box =
            new HBox();

        box.setSpacing(10);
        box.setAlignment(
            Pos.CENTER_LEFT
        );

        box.setPadding(
            new Insets(11)
        );

        box.setStyle(
            "-fx-background-color: #FAF8FF;" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: #E7DDF2;" +
            "-fx-border-radius: 10;"
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setPrefWidth(120);

        titleLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #7041A5;"
        );

        Label foodLabel =
            new Label(food);

        foodLabel.setWrapText(true);

        foodLabel.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #24234F;"
        );

        HBox.setHgrow(
            foodLabel,
            Priority.ALWAYS
        );

        box.getChildren().addAll(
            titleLabel,
            foodLabel
        );

        return box;
    }

    // =========================================================
    // AVOID DETAIL
    // =========================================================

    private HBox createAvoidDetail(
            String emoji,
            String title,
            String description) {

        HBox box =
            new HBox();

        box.setSpacing(12);
        box.setAlignment(
            Pos.TOP_LEFT
        );

        box.setPadding(
            new Insets(12)
        );

        box.setStyle(
            "-fx-background-color: #FFF8FA;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F3D8E3;" +
            "-fx-border-radius: 12;"
        );

        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 25px;"
        );

        VBox text =
            new VBox();

        text.setSpacing(3);

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #D14A78;"
        );

        Label descLabel =
            new Label(description);

        descLabel.setWrapText(true);

        descLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        text.getChildren().addAll(
            titleLabel,
            descLabel
        );

        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );

        box.getChildren().addAll(
            icon,
            text
        );

        return box;
    }

    // =========================================================
    // LARGE TIP
    // =========================================================

    private HBox createLargeTip(
            String emoji,
            String title,
            String description) {

        HBox box =
            new HBox();

        box.setSpacing(12);
        box.setAlignment(
            Pos.TOP_LEFT
        );

        box.setPadding(
            new Insets(11)
        );

        box.setStyle(
            "-fx-background-color: #FFFDF4;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #F1E7BE;" +
            "-fx-border-radius: 12;"
        );

        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 24px;"
        );

        VBox text =
            new VBox();

        text.setSpacing(3);

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #C7821B;"
        );

        Label desc =
            new Label(description);

        desc.setWrapText(true);

        desc.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        text.getChildren().addAll(
            titleLabel,
            desc
        );

        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );

        box.getChildren().addAll(
            icon,
            text
        );

        return box;
    }

    // =========================================================
    // NUTRITIONIST INFO
    // =========================================================

    private HBox createNutritionistInfo(
            String emoji,
            String title,
            String description) {

        HBox box =
            new HBox();

        box.setSpacing(12);
        box.setAlignment(
            Pos.TOP_LEFT
        );

        box.setPadding(
            new Insets(12)
        );

        box.setStyle(
            "-fx-background-color: #FAF5FF;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #E5D8F1;" +
            "-fx-border-radius: 12;"
        );

        Label icon =
            new Label(emoji);

        icon.setStyle(
            "-fx-font-size: 27px;"
        );

        VBox text =
            new VBox();

        text.setSpacing(4);

        Label titleLabel =
            new Label(title);

        titleLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #7041A5;"
        );

        Label desc =
            new Label(description);

        desc.setWrapText(true);

        desc.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #666680;"
        );

        text.getChildren().addAll(
            titleLabel,
            desc
        );

        HBox.setHgrow(
            text,
            Priority.ALWAYS
        );

        box.getChildren().addAll(
            icon,
            text
        );

        return box;
    }
}