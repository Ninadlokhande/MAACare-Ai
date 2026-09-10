package com.sigma.view.motherPages;

import com.sigma.ai.AIAssistant;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MotherAiAssistant {

    private final String PINK = "#E84A87";
    private final String DARK = "#24234F";
    private final String PURPLE = "#9B4DCC";
    private final String TEXT_GRAY = "#77778D";

    // =========================================================
    // AI
    // =========================================================

    private final AIAssistant assistant = new AIAssistant();

    // =========================================================
    // PAGE CONTROLS
    // =========================================================

    private ComboBox<String> subjectBox;
    private ComboBox<String> queryTypeBox;
    private ComboBox<String> motherStageBox;
    private ComboBox<String> trimesterBox;
    private ComboBox<String> childAgeBox;
    private ComboBox<String> concernBox;

    private TextArea input;
    private Button sendButton;
    private Label selectionError;

    private final List<Button> quickButtons = new ArrayList<>();

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
        titleBox.getChildren().addAll(title, subtitle);

        // =====================================================
        // AI WELCOME CARD
        // =====================================================

        HBox welcomeCard = new HBox(18);

        welcomeCard.setAlignment(Pos.CENTER_LEFT);
        welcomeCard.setPadding(new Insets(20));

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

        HBox.setHgrow(welcomeText, Priority.ALWAYS);

        welcomeCard.getChildren().addAll(
            aiIcon,
            welcomeText
        );

        // =====================================================
        // PERSONALIZE / DROPDOWNS
        // =====================================================

        VBox contextCard = new VBox(12);

        contextCard.setPadding(new Insets(18));

        contextCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #E7DCE8;" +
            "-fx-border-radius: 18;"
        );

        Label contextTitle = new Label(
            "Personalize Your AI Assistant"
        );

        contextTitle.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        Label contextSubtitle = new Label(
            "Select your role first. Other details are optional and help AI give more relevant answers."
        );

        contextSubtitle.setWrapText(true);

        contextSubtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: " + TEXT_GRAY + ";"
        );

        GridPane parameters = new GridPane();

        parameters.setHgap(12);
        parameters.setVgap(10);

        subjectBox = new ComboBox<>();
        subjectBox.getItems().addAll(
            "Mother",
            "Child",
            "Mother & Child"
        );
        subjectBox.setPromptText("Select role *");
        styleCombo(subjectBox);

        queryTypeBox = new ComboBox<>();
        queryTypeBox.getItems().addAll(
            "Pregnancy & Mother Health",
            "Baby / Child Health",
            "Nutrition & Diet",
            "Symptoms & General Health",
            "Medication & Safety",
            "Exercise & Lifestyle",
            "Postpartum Care",
            "Baby Development",
            "Other Healthcare Question"
        );
        queryTypeBox.setPromptText("Select query type");
        styleCombo(queryTypeBox);

        motherStageBox = new ComboBox<>();
        motherStageBox.getItems().addAll(
            "Not applicable",
            "Trying to conceive",
            "Pregnant",
            "Postpartum",
            "Breastfeeding"
        );
        motherStageBox.setValue("Not applicable");
        styleCombo(motherStageBox);

        trimesterBox = new ComboBox<>();
        trimesterBox.getItems().addAll(
            "Not applicable",
            "1st Trimester",
            "2nd Trimester",
            "3rd Trimester"
        );
        trimesterBox.setValue("Not applicable");
        styleCombo(trimesterBox);

        childAgeBox = new ComboBox<>();
        childAgeBox.getItems().addAll(
            "Not applicable",
            "Newborn (0-28 days)",
            "Infant (1-12 months)",
            "Toddler (1-3 years)",
            "Preschool (3-5 years)",
            "School Age (6-12 years)"
        );
        childAgeBox.setValue("Not applicable");
        styleCombo(childAgeBox);

        concernBox = new ComboBox<>();
        concernBox.getItems().addAll(
            "General information",
            "Nutrition",
            "Symptoms",
            "Growth / Development",
            "Routine care",
            "Warning signs",
            "Medication / Supplement",
            "Lifestyle"
        );
        concernBox.setValue("General information");
        styleCombo(concernBox);

        parameters.add(createFieldLabel("👤 Role *"), 0, 0);
        parameters.add(subjectBox, 1, 0);

        parameters.add(createFieldLabel("📚 Topic"), 2, 0);
        parameters.add(queryTypeBox, 3, 0);

        parameters.add(createFieldLabel("🤰 Mother Stage"), 0, 1);
        parameters.add(motherStageBox, 1, 1);

        parameters.add(createFieldLabel("🗓 Trimester"), 2, 1);
        parameters.add(trimesterBox, 3, 1);

        parameters.add(createFieldLabel("👶 Child Age"), 0, 2);
        parameters.add(childAgeBox, 1, 2);

        parameters.add(createFieldLabel("❤️ Concern"), 2, 2);
        parameters.add(concernBox, 3, 2);

        for (int i = 1; i < 4; i += 2) {
            GridPane.setHgrow(parameters.getChildren().get(i), Priority.ALWAYS);
        }

        selectionError = new Label(
            "Please select your role before asking a question."
        );

        selectionError.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #C43D67;" +
            "-fx-font-weight: bold;"
        );

        selectionError.setVisible(false);
        selectionError.setManaged(false);

        contextCard.getChildren().addAll(
            contextTitle,
            contextSubtitle,
            parameters,
            selectionError
        );

        subjectBox.setOnAction(e -> {
            selectionError.setVisible(false);
            selectionError.setManaged(false);
        });

        // =====================================================
        // QUICK QUESTIONS
        // =====================================================

        Label quickTitle = new Label("Ask About");

        quickTitle.setStyle(
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        HBox quickQuestions = new HBox(12);

        Button pregnancy = createQuickButton("🤰 Pregnancy");
        Button nutrition = createQuickButton("🥗 Nutrition");
        Button baby = createQuickButton("👶 Baby Care");
        Button medicines = createQuickButton("💊 Medicines");
        Button symptoms = createQuickButton("❤️ Symptoms");

        quickButtons.add(pregnancy);
        quickButtons.add(nutrition);
        quickButtons.add(baby);
        quickButtons.add(medicines);
        quickButtons.add(symptoms);

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

        chatCard.setPadding(new Insets(20));

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

        VBox messages = new VBox(12);

        messages.setPadding(new Insets(5));

        ScrollPane chatScroll = new ScrollPane(messages);

        chatScroll.setFitToWidth(true);
        chatScroll.setPrefHeight(300);

        chatScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        chatScroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;"
        );

        // Keep the first hardcoded AI message.
        messages.getChildren().add(
            createAIMessage(
                "Hello! 👋 How can I help you today?"
            )
        );

        // =====================================================
        // INPUT AREA
        // =====================================================

        HBox inputArea = new HBox(10);

        input = new TextArea();

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

        HBox.setHgrow(input, Priority.ALWAYS);

        sendButton = new Button("Send  ➤");

        sendButton.setPrefWidth(100);

        styleSendButton(sendButton);

        sendButton.setOnAction(e ->
            askAIQuestion(
                input.getText(),
                messages,
                chatScroll
            )
        );

        // Ctrl/Enter or Enter with a normal one-line-style input can be
        // handled without preventing multiline questions.
        input.setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode().toString().equals("ENTER")) {
                askAIQuestion(
                    input.getText(),
                    messages,
                    chatScroll
                );
                e.consume();
            }
        });

        inputArea.getChildren().addAll(
            input,
            sendButton
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
                () -> askAIQuestion(
                    "What should I eat during pregnancy?",
                    messages,
                    chatScroll
                )
            ),

            createQuestionCard(
                "👶",
                "How can I take care of my baby?",
                () -> askAIQuestion(
                    "How can I take care of my baby?",
                    messages,
                    chatScroll
                )
            ),

            createQuestionCard(
                "💊",
                "How should I remember my medicines?",
                () -> askAIQuestion(
                    "How should I remember my medicines?",
                    messages,
                    chatScroll
                )
            ),

            createQuestionCard(
                "❤️",
                "What pregnancy symptoms should I discuss with my doctor?",
                () -> askAIQuestion(
                    "What pregnancy symptoms should I discuss with my doctor?",
                    messages,
                    chatScroll
                )
            )
        );

        // =====================================================
        // SAFETY NOTE
        // =====================================================

        VBox safety = new VBox(7);

        safety.setPadding(new Insets(16));

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
            "AI provides general information and should not replace " +
            "advice, diagnosis or treatment from a qualified doctor. " +
            "For severe or emergency symptoms, seek immediate medical care."
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
            contextCard,
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

        ScrollPane scrollPane = new ScrollPane(content);

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

        page.getChildren().add(scrollPane);

        VBox.setVgrow(
            scrollPane,
            Priority.ALWAYS
        );

        // =====================================================
        // QUICK BUTTON ACTIONS
        // =====================================================

        pregnancy.setOnAction(e ->
            askAIQuestion(
                "Tell me about pregnancy care.",
                messages,
                chatScroll
            )
        );

        nutrition.setOnAction(e ->
            askAIQuestion(
                "What should I know about nutrition during pregnancy?",
                messages,
                chatScroll
            )
        );

        baby.setOnAction(e ->
            askAIQuestion(
                "Give me baby care guidance.",
                messages,
                chatScroll
            )
        );

        medicines.setOnAction(e ->
            askAIQuestion(
                "How can I manage my medicines safely?",
                messages,
                chatScroll
            )
        );

        symptoms.setOnAction(e ->
            askAIQuestion(
                "What pregnancy symptoms should I discuss with my doctor?",
                messages,
                chatScroll
            )
        );

        return page;
    }

    // =========================================================
    // ASK AI
    // =========================================================

    private void askAIQuestion(
            String question,
            VBox messages,
            ScrollPane scroll) {

        if (question == null || question.trim().isEmpty()) {
            input.requestFocus();
            return;
        }

        // Role is mandatory.
        String role = subjectBox.getValue();

        if (role == null || role.trim().isEmpty()) {
            selectionError.setText(
                "⚠ Please select your role before asking a question."
            );
            selectionError.setVisible(true);
            selectionError.setManaged(true);
            subjectBox.requestFocus();
            return;
        }

        String cleanQuestion = question.trim();

        messages.getChildren().add(
            createUserMessage(cleanQuestion)
        );

        HBox thinkingMessage = createThinkingMessage();

        messages.getChildren().add(thinkingMessage);

        scroll.setVvalue(1.0);

        setLoading(true);

        String prompt = buildPrompt(cleanQuestion);

        Task<String> task = new Task<>() {

            @Override
            protected String call() {

                return assistant.ask(prompt);
            }
        };

        task.setOnSucceeded(e -> {

            messages.getChildren().remove(thinkingMessage);

            String response = task.getValue();

            if (response == null || response.trim().isEmpty()) {

                messages.getChildren().add(
                    createAIMessage(
                        "I couldn't generate a response right now. " +
                        "Please try asking the question again."
                    )
                );

            } else {

                String cleanedResponse =
                    cleanAIResponse(response);

                messages.getChildren().add(
                    createAIMessage(cleanedResponse)
                );
            }

            if (input != null) {
                input.clear();
            }

            setLoading(false);

            scroll.setVvalue(1.0);
        });

        task.setOnFailed(e -> {

            messages.getChildren().remove(thinkingMessage);

            Throwable error = task.getException();

            String errorMessage =
                error == null || error.getMessage() == null
                    ? "Unable to connect to MaaCare AI. Please try again."
                    : "Unable to get AI response. Please try again.";

            messages.getChildren().add(
                createAIMessage(errorMessage)
            );

            setLoading(false);

            scroll.setVvalue(1.0);
        });

        Thread thread =
            new Thread(
                task,
                "MaaCare-AI-Mother-Chat-Thread"
            );

        thread.setDaemon(true);
        thread.start();
    }

    // =========================================================
    // BUILD AI PROMPT
    // =========================================================

    private String buildPrompt(String question) {

        String role = value(subjectBox);
        String queryType = value(queryTypeBox);
        String motherStage = value(motherStageBox);
        String trimester = value(trimesterBox);
        String childAge = value(childAgeBox);
        String concern = value(concernBox);

        StringBuilder prompt = new StringBuilder();

        prompt.append(
            "You are MaaCare AI, a healthcare information assistant " +
            "for mother and child healthcare.\n\n"
        );

        prompt.append(
            "USER CONTEXT:\n"
        );

        prompt.append(
            "Role / subject: "
        ).append(role).append("\n");

        prompt.append(
            "Query type: "
        ).append(queryType).append("\n");

        prompt.append(
            "Mother stage: "
        ).append(motherStage).append("\n");

        prompt.append(
            "Trimester: "
        ).append(trimester).append("\n");

        prompt.append(
            "Child age: "
        ).append(childAge).append("\n");

        prompt.append(
            "Concern: "
        ).append(concern).append("\n\n");

        prompt.append(
            "USER QUESTION:\n"
        );

        prompt.append(question).append("\n\n");

        prompt.append(
            "RESPONSE REQUIREMENTS:\n" +
            "1. Answer the user's actual question directly.\n" +
            "2. Use the selected context when it is relevant.\n" +
            "3. Give clear, practical, easy-to-understand healthcare information.\n" +
            "4. Do not invent personal medical history or test results.\n" +
            "5. Do not claim to diagnose the user.\n" +
            "6. Do not prescribe or change prescription medicines.\n" +
            "7. If the question involves medicines, supplements, dosage, " +
            "pregnancy complications, or serious symptoms, recommend " +
            "consulting an appropriate qualified healthcare professional.\n" +
            "8. Clearly mention urgent warning signs when relevant.\n" +
            "9. Do not include image Markdown, HTML, code blocks, " +
            "embedded media, tracking content, or unnecessary URLs.\n" +
            "10. Keep the response structured with short headings and " +
            "bullet points when useful.\n" +
            "11. Do not repeat the entire user context in the answer.\n"
        );

        return prompt.toString();
    }

    // =========================================================
    // CLEAN AI RESPONSE
    // =========================================================

    private String cleanAIResponse(String response) {

        String text = response;

        // Remove Markdown images.
        text = text.replaceAll(
            "!\\[([^\\]]*)\\]\\([^)]*\\)",
            "$1"
        );

        // Convert Markdown links to visible text.
        text = text.replaceAll(
            "\\[([^\\]]+)\\]\\((https?://[^)]+)\\)",
            "$1"
        );

        // Remove bare URLs.
        text = text.replaceAll(
            "(?i)https?://\\S+",
            ""
        );

        // Remove HTML tags.
        text = text.replaceAll(
            "<[^>]*>",
            ""
        );

        // Remove code fences.
        text = text.replace(
            "```markdown",
            ""
        );

        text = text.replace(
            "```text",
            ""
        );

        text = text.replace(
            "```",
            ""
        );

        // Remove common invisible/technical response markers.
        text = text.replaceAll(
            "(?i)\\b(system message|developer message|tool call|tool output)\\b\\s*:?",
            ""
        );

        // Remove excessive Markdown heading markers while preserving text.
        text = text.replaceAll(
            "(?m)^\\s*#{1,6}\\s*",
            ""
        );

        // Make Markdown bullets clean and consistent.
        text = text.replaceAll(
            "(?m)^\\s*[-*]\\s+",
            "• "
        );

        // Remove bold/italic Markdown markers.
        text = text.replace("**", "");
        text = text.replace("__", "");
        text = text.replaceAll("(?<!\\*)\\*(?!\\*)", "");

        // Remove Markdown blockquote marker.
        text = text.replaceAll(
            "(?m)^\\s*>\\s?",
            ""
        );

        // Remove repeated blank lines.
        text = text.replaceAll(
            "\\n\\s*\\n\\s*\\n+",
            "\n\n"
        );

        // Remove leading/trailing whitespace.
        text = text.trim();

        if (text.isEmpty()) {
            return "I couldn't generate a readable response. Please try again.";
        }

        return text;
    }

    // =========================================================
    // LOADING STATE
    // =========================================================

    private void setLoading(boolean loading) {

        if (sendButton != null) {
            sendButton.setDisable(loading);
        }

        if (input != null) {
            input.setDisable(loading);
        }

        if (subjectBox != null) {
            subjectBox.setDisable(loading);
        }

        if (queryTypeBox != null) {
            queryTypeBox.setDisable(loading);
        }

        if (motherStageBox != null) {
            motherStageBox.setDisable(loading);
        }

        if (trimesterBox != null) {
            trimesterBox.setDisable(loading);
        }

        if (childAgeBox != null) {
            childAgeBox.setDisable(loading);
        }

        if (concernBox != null) {
            concernBox.setDisable(loading);
        }

        for (Button button : quickButtons) {
            button.setDisable(loading);
        }
    }

    // =========================================================
    // THINKING MESSAGE
    // =========================================================

    private HBox createThinkingMessage() {

        HBox box = new HBox(10);

        box.setAlignment(Pos.CENTER_LEFT);

        box.setPadding(new Insets(12));

        box.setStyle(
            "-fx-background-color: #F7F0FF;" +
            "-fx-background-radius: 14;"
        );

        ProgressIndicator progress =
            new ProgressIndicator();

        progress.setProgress(-1);
        progress.setPrefSize(25, 25);
        progress.setMaxSize(25, 25);

        Label text =
            new Label(
                "MaaCare AI is thinking..."
            );

        text.setStyle(
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;"
        );

        box.getChildren().addAll(
            progress,
            text
        );

        return box;
    }

    // =========================================================
    // QUICK BUTTON
    // =========================================================

    private Button createQuickButton(String text) {

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
            Runnable action) {

        HBox card = new HBox(12);

        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(14));

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
            e -> action.run()
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

    private HBox createAIMessage(String text) {

        HBox box = new HBox();

        box.setAlignment(Pos.CENTER_LEFT);

        Label message =
            new Label(text);

        message.setWrapText(true);
        message.setMaxWidth(650);

        message.setPadding(new Insets(12));

        message.setStyle(
            "-fx-background-color: #F7F0FF;" +
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 14;" +
            "-fx-line-spacing: 2px;"
        );

        box.getChildren().add(message);

        return box;
    }

    // =========================================================
    // USER MESSAGE
    // =========================================================

    private HBox createUserMessage(String text) {

        HBox box = new HBox();

        box.setAlignment(Pos.CENTER_RIGHT);

        Label message =
            new Label(text);

        message.setWrapText(true);
        message.setMaxWidth(650);

        message.setPadding(new Insets(12));

        message.setStyle(
            "-fx-background-color: #FFEAF3;" +
            "-fx-text-fill: " + DARK + ";" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 14;"
        );

        box.getChildren().add(message);

        return box;
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleCombo(ComboBox<String> combo) {

        combo.setPrefHeight(38);
        combo.setMaxWidth(Double.MAX_VALUE);

        combo.setStyle(
            "-fx-background-color: #FBF8FC;" +
            "-fx-border-color: #E0D6E4;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-font-size: 13px;" +
            "-fx-text-fill: " + DARK + ";"
        );
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private Label createFieldLabel(String text) {

        Label label =
            new Label(text);

        label.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + DARK + ";"
        );

        return label;
    }

    // =========================================================
    // SEND BUTTON STYLE
    // =========================================================

    private void styleSendButton(Button button) {

        button.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 12;"
        );
    }

    // =========================================================
    // SAFE COMBO VALUE
    // =========================================================

    private String value(ComboBox<String> box) {

        if (box == null || box.getValue() == null) {
            return "Not selected";
        }

        return box.getValue();
    }
}
