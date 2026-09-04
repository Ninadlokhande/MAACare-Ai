package com.sigma.view.motherPages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MotherAiAssistant {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";

    // =========================================================
    // MAIN PAGE
    // =========================================================
    
    public VBox createAIAssistantPage() {

        VBox page = new VBox();
        page.setFillWidth(true);

        page.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to bottom right, " +
            "#FFFFFF 0%, " +
            "#FFF7FB 55%, " +
            "#F4EDFF 100%);"
        );

        VBox content = new VBox(20);

        content.setPadding(
            new Insets(25, 30, 40, 30)
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
            "AI Pregnancy Assistant"
        );

        title.setStyle(
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        Label subtitle = new Label(
            "Your friendly AI companion for pregnancy and baby care"
        );

        subtitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: " + TEXT_GRAY + ";"
        );

        VBox titleBox = new VBox(5);

        titleBox.getChildren().addAll(
            title,
            subtitle
        );

        // =====================================================
        // AI WELCOME CARD
        // =====================================================

        HBox welcomeCard = new HBox(18);

        welcomeCard.setAlignment(
            Pos.CENTER_LEFT
        );

        welcomeCard.setPadding(
            new Insets(20)
        );

        welcomeCard.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #FFF0F6, #F5EEFF);" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E9D9E8;" +
            "-fx-border-radius: 18;"
        );

        Label aiIcon = new Label("🤖");

        aiIcon.setAlignment(Pos.CENTER);

        aiIcon.setPrefSize(65, 65);

        aiIcon.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-font-size: 34px;"
        );

        VBox welcomeText = new VBox(6);

        Label welcomeTitle = new Label(
            "Hello! I'm your AI Care Assistant 👋"
        );

        welcomeTitle.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        Label welcomeMessage = new Label(
            "Ask me about pregnancy, nutrition, baby care, " +
            "medicines, symptoms or your daily health routine."
        );

        welcomeMessage.setWrapText(true);

        welcomeMessage.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: " + TEXT_GRAY + ";"
        );

        welcomeText.getChildren().addAll(
            welcomeTitle,
            welcomeMessage
        );

        HBox.setHgrow(
            welcomeText,
            Priority.ALWAYS
        );

        welcomeCard.getChildren().addAll(
            aiIcon,
            welcomeText
        );

        // =====================================================
        // QUICK QUESTIONS
        // =====================================================

        Label quickTitle = new Label(
            "Ask About"
        );

        quickTitle.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        HBox quickQuestions = new HBox(12);

        Button pregnancy =
            createQuickButton(
                "🤰 Pregnancy"
            );

        Button nutrition =
            createQuickButton(
                "🥗 Nutrition"
            );

        Button baby =
            createQuickButton(
                "👶 Baby Care"
            );

        Button medicines =
            createQuickButton(
                "💊 Medicines"
            );

        Button symptoms =
            createQuickButton(
                "❤️ Symptoms"
            );

        quickQuestions.getChildren().addAll(
            pregnancy,
            nutrition,
            baby,
            medicines,
            symptoms
        );

        // =====================================================
        // CHAT AREA
        // =====================================================

        VBox chatCard = new VBox(15);

        chatCard.setPadding(
            new Insets(20)
        );

        chatCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );

        Label chatTitle = new Label(
            "Chat with AI Assistant"
        );

        chatTitle.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        // =====================================================
        // CHAT MESSAGES
        // =====================================================

        VBox messages = new VBox(12);

        messages.setPadding(
            new Insets(5)
        );

        ScrollPane chatScroll =
            new ScrollPane(messages);

        chatScroll.setFitToWidth(true);

        chatScroll.setPrefHeight(250);

        chatScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        chatScroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        // Initial AI message

        messages.getChildren().add(
            createAIMessage(
                "Hello! 👋 How can I help you today?"
            )
        );

        // =====================================================
        // INPUT AREA
        // =====================================================

        HBox inputArea = new HBox(10);

        TextArea input = new TextArea();

        input.setPromptText(
            "Type your question here..."
        );

        input.setPrefRowCount(2);

        input.setWrapText(true);

        input.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: " + DARK + ";" +
            "-fx-border-color: #E0D6E4;" +
            "-fx-border-radius: 12;" +
            "-fx-background-radius: 12;"
        );

        HBox.setHgrow(
            input,
            Priority.ALWAYS
        );

        Button send = new Button(
            "Send  ➤"
        );

        send.setPrefWidth(100);

        send.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 12;"
        );

        send.setOnAction(e -> {

            String question =
                input.getText().trim();

            if (!question.isEmpty()) {

                messages.getChildren().add(
                    createUserMessage(question)
                );

                messages.getChildren().add(
                    createAIMessage(
                        "I'm here to help. For medical concerns, " +
                        "please also consult your doctor or healthcare professional."
                    )
                );

                input.clear();

                chatScroll.setVvalue(1.0);
            }
        });

        inputArea.getChildren().addAll(
            input,
            send
        );

        chatCard.getChildren().addAll(
            chatTitle,
            chatScroll,
            inputArea
        );

        // =====================================================
        // COMMON QUESTIONS
        // =====================================================

        Label commonTitle = new Label(
            "Common Questions"
        );

        commonTitle.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        VBox commonQuestions = new VBox(10);

        commonQuestions.getChildren().addAll(

            createQuestionCard(
                "🤰",
                "What should I eat during pregnancy?",
                nutrition
            ),

            createQuestionCard(
                "👶",
                "How can I take care of my baby?",
                baby
            ),

            createQuestionCard(
                "💊",
                "How should I remember my medicines?",
                medicines
            ),

            createQuestionCard(
                "❤️",
                "What pregnancy symptoms should I discuss with my doctor?",
                symptoms
            )
        );

        // =====================================================
        // SAFETY NOTE
        // =====================================================

        VBox safety = new VBox(7);

        safety.setPadding(
            new Insets(16)
        );

        safety.setStyle(
            "-fx-background-color: #FFF9EE;" +
            "-fx-border-color: #F0DFB6;" +
            "-fx-border-radius: 14;" +
            "-fx-background-radius: 14;"
        );

        Label safetyTitle = new Label(
            "⚠ Medical Safety"
        );

        safetyTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #A87518;"
        );

        Label safetyText = new Label(
            "AI provides general information and should not " +
            "replace advice, diagnosis or treatment from a qualified doctor."
        );

        safetyText.setWrapText(true);

        safetyText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );

        safety.getChildren().addAll(
            safetyTitle,
            safetyText
        );

        content.getChildren().addAll(
            titleBox,
            welcomeCard,
            quickTitle,
            quickQuestions,
            chatCard,
            commonTitle,
            commonQuestions,
            safety
        );

        // =====================================================
        // MAIN SCROLL
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

        // =====================================================
        // QUICK BUTTON ACTIONS
        // =====================================================

        pregnancy.setOnAction(e ->
            inputQuestion(
                messages,
                chatScroll,
                "Tell me about pregnancy care."
            )
        );

        nutrition.setOnAction(e ->
            inputQuestion(
                messages,
                chatScroll,
                "What should I know about nutrition?"
            )
        );

        baby.setOnAction(e ->
            inputQuestion(
                messages,
                chatScroll,
                "Give me baby care guidance."
            )
        );

        medicines.setOnAction(e ->
            inputQuestion(
                messages,
                chatScroll,
                "How can I manage my medicines?"
            )
        );

        symptoms.setOnAction(e ->
            inputQuestion(
                messages,
                chatScroll,
                "What symptoms should I discuss with my doctor?"
            )
        );

        return page;
    }

    // =========================================================
    // QUICK BUTTON
    // =========================================================

    private Button createQuickButton(
            String text) {

        Button button =
            new Button(text);

        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #DCC9EC;" +
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 9px 15px;"
        );

        return button;
    }

    // =========================================================
    // QUESTION CARD
    // =========================================================

    private HBox createQuestionCard(
            String icon,
            String question,
            Button actionButton) {

        HBox card = new HBox(12);

        card.setAlignment(
            Pos.CENTER_LEFT
        );

        card.setPadding(
            new Insets(14)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 14;" +
            "-fx-background-radius: 14;"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setStyle(
            "-fx-font-size: 24px;"
        );

        Label questionLabel =
            new Label(question);

        questionLabel.setWrapText(true);

        questionLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: " + DARK + ";"
        );

        HBox.setHgrow(
            questionLabel,
            Priority.ALWAYS
        );

        Button askButton =
            new Button("Ask");

        askButton.setStyle(
            "-fx-background-color: #FFF0F6;" +
            "-fx-text-fill: " + PINK + ";" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;"
        );

        askButton.setOnAction(
            e -> actionButton.fire()
        );

        card.getChildren().addAll(
            iconLabel,
            questionLabel,
            askButton
        );

        return card;
    }

    // =========================================================
    // AI MESSAGE
    // =========================================================

    private HBox createAIMessage(
            String text) {

        HBox box = new HBox();

        box.setAlignment(
            Pos.CENTER_LEFT
        );

        Label message =
            new Label(text);

        message.setWrapText(true);

        message.setMaxWidth(600);

        message.setPadding(
            new Insets(12)
        );

        message.setStyle(
            "-fx-background-color: #F7F0FF;" +
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 14;"
        );

        box.getChildren().add(
            message
        );

        return box;
    }

    // =========================================================
    // USER MESSAGE
    // =========================================================

    private HBox createUserMessage(
            String text) {

        HBox box = new HBox();

        box.setAlignment(
            Pos.CENTER_RIGHT
        );

        Label message =
            new Label(text);

        message.setWrapText(true);

        message.setMaxWidth(600);

        message.setPadding(
            new Insets(12)
        );

        message.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 14;"
        );

        box.getChildren().add(
            message
        );

        return box;
    }

    // =========================================================
    // QUICK QUESTION
    // =========================================================

    private void inputQuestion(
            VBox messages,
            ScrollPane scroll,
            String question) {

        messages.getChildren().add(
            createUserMessage(question)
        );

        messages.getChildren().add(
            createAIMessage(
                "I can help you with general information about this topic. " +
                "For personal medical advice, please consult your doctor."
            )
        );

        scroll.setVvalue(1.0);
    }
}

