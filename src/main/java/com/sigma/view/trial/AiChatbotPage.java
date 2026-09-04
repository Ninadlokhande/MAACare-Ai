package com.sigma.view.trial;

import com.sigma.ai.AIAssistant;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Interactive MaaCare AI healthcare chatbot page.
 *
 * The selected context and the user's question are combined into one
 * structured prompt and sent to AIAssistant.ask(...).
 */
public class AiChatbotPage {

    private final AIAssistant assistant = new AIAssistant();

    private BorderPane root;
    private Scene scene;

    private ComboBox<String> queryTypeBox;
    private ComboBox<String> subjectBox;
    private ComboBox<String> motherStageBox;
    private ComboBox<String> trimesterBox;
    private ComboBox<String> childAgeBox;
    private ComboBox<String> concernBox;

    private TextArea queryArea;
    private TextArea answerArea;
    private VBox formattedAnswer;

    private Button askButton;
    private Button clearButton;

    private ProgressIndicator progress;
    private Text loadingText;

    public Scene getScene() {
        if (scene != null) {
            return scene;
        }

        root = new BorderPane();
        root.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, " +
            "#FFF7FA 0%, #F7F2FF 50%, #F2F8FF 100%);"
        );

        // HEADER
        VBox header = new VBox(5);
        header.setPadding(new Insets(28, 42, 24, 42));
        header.setStyle(
            "-fx-background-color: rgba(255,255,255,0.94);" +
            "-fx-border-color: #E5DDED;" +
            "-fx-border-width: 0 0 1px 0;"
        );

        Text title = new Text("MaaCare AI");
        title.setFont(Font.font("System", FontWeight.BOLD, 30));
        title.setFill(Color.web("#8B3FBF"));

        Text subtitle = new Text(
            "Your AI-powered companion for mother and child healthcare"
        );
        subtitle.setFont(Font.font("System", 15));
        subtitle.setFill(Color.web("#666680"));

        header.getChildren().addAll(title, subtitle);
        root.setTop(header);

        // MAIN
        HBox content = new HBox(24);
        content.setPadding(new Insets(28, 42, 34, 42));
        content.setAlignment(Pos.TOP_CENTER);

        VBox questionCard = createCard(520);
        VBox answerCard = createCard(680);

        // LEFT CARD
        Text questionTitle = heading("Ask MaaCare AI");
        Text questionSubtitle = normalText(
            "Select the context and ask your healthcare question."
        );

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

        subjectBox = new ComboBox<>();
        subjectBox.getItems().addAll(
            "Mother",
            "Child",
            "Mother & Child"
        );
        subjectBox.setPromptText("Select who this is for");
        styleCombo(subjectBox);
        subjectBox.setOnAction(e -> updateParameterState());

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

        GridPane parameters = new GridPane();
        parameters.setHgap(12);
        parameters.setVgap(10);

        parameters.add(label("Mother Stage"), 0, 0);
        parameters.add(motherStageBox, 1, 0);

        parameters.add(label("Trimester"), 0, 1);
        parameters.add(trimesterBox, 1, 1);

        parameters.add(label("Child Age"), 0, 2);
        parameters.add(childAgeBox, 1, 2);

        parameters.add(label("Main Concern"), 0, 3);
        parameters.add(concernBox, 1, 3);

        GridPane.setHgrow(motherStageBox, Priority.ALWAYS);
        GridPane.setHgrow(trimesterBox, Priority.ALWAYS);
        GridPane.setHgrow(childAgeBox, Priority.ALWAYS);
        GridPane.setHgrow(concernBox, Priority.ALWAYS);

        queryArea = new TextArea();
        queryArea.setWrapText(true);
        queryArea.setPrefRowCount(7);
        queryArea.setPromptText(
            "Example: What foods can help me meet my iron needs?"
        );
        queryArea.setStyle(textAreaStyle());

        askButton = new Button("Ask MaaCare AI");
        askButton.setMaxWidth(Double.MAX_VALUE);
        askButton.setPrefHeight(50);
        askButton.setStyle(primaryButtonStyle());
        askButton.setOnAction(e -> askAI());

        clearButton = new Button("Clear");
        clearButton.setPrefHeight(50);
        clearButton.setMinWidth(90);
        clearButton.setStyle(secondaryButtonStyle());
        clearButton.setOnAction(e -> clearPage());

        HBox buttons = new HBox(10, askButton, clearButton);
        HBox.setHgrow(askButton, Priority.ALWAYS);

        questionCard.getChildren().addAll(
            questionTitle,
            questionSubtitle,
            spacer(8),
            label("Query Type"),
            queryTypeBox,
            label("For Whom?"),
            subjectBox,
            spacer(4),
            parameters,
            spacer(4),
            label("Your Question"),
            queryArea,
            spacer(4),
            buttons
        );

        // RIGHT CARD
        Text answerTitle = heading("AI Response");
        Text answerSubtitle = normalText(
            "Your MaaCare AI answer will appear below."
        );

        // Formatted AI response area.
        answerArea = new TextArea();
        answerArea.setEditable(false);
        answerArea.setVisible(false);
        answerArea.setManaged(false);

        formattedAnswer = new VBox(5);
        formattedAnswer.setPadding(new Insets(16));
        formattedAnswer.setFillWidth(true);
        formattedAnswer.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12px;"
        );

        Label responsePlaceholder = new Label(
            "Ask a question to receive an AI response..."
        );
        responsePlaceholder.setWrapText(true);
        responsePlaceholder.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #888899;"
        );
        formattedAnswer.getChildren().add(responsePlaceholder);

        ScrollPane responseScroll = new ScrollPane(formattedAnswer);
        responseScroll.setFitToWidth(true);
        responseScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        responseScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        responseScroll.setStyle(
            "-fx-background-color: white;" +
            "-fx-background: white;" +
            "-fx-border-color: #DDD9E6;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;"
        );

        StackPane answerPane = new StackPane(responseScroll);

        progress = new ProgressIndicator();
        progress.setProgress(-1);          // indeterminate circular spinner
        progress.setPrefSize(58, 58);
        progress.setMinSize(58, 58);
        progress.setMaxSize(58, 58);
        progress.setStyle(
            "-fx-progress-color: #9B4DCC;"
        );
        progress.setVisible(false);
        progress.setManaged(false);

        loadingText = new Text("MaaCare AI is thinking...");
        loadingText.setFont(Font.font("System", FontWeight.BOLD, 14));
        loadingText.setFill(Color.web("#713CC3"));
        loadingText.setVisible(false);
        loadingText.setManaged(false);

        VBox loadingBox = new VBox(10, progress, loadingText);
        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setPadding(new Insets(22, 30, 22, 30));
        loadingBox.setMaxWidth(250);
        loadingBox.setMaxHeight(145);
        loadingBox.setStyle(
            "-fx-background-color: rgba(255,255,255,0.97);" +
            "-fx-background-radius: 18px;" +
            "-fx-border-color: #E5DDED;" +
            "-fx-border-radius: 18px;" +
            "-fx-effect: dropshadow(gaussian, rgba(70,50,90,0.20), 18, 0.18, 0, 5);"
        );
        loadingBox.setVisible(false);
        loadingBox.setManaged(false);

        StackPane.setAlignment(loadingBox, Pos.CENTER);
        answerPane.getChildren().add(loadingBox);
        VBox.setVgrow(answerPane, Priority.ALWAYS);

        Button clearConversation = new Button("Clear AI Conversation");
        clearConversation.setStyle(secondaryButtonStyle());
        clearConversation.setOnAction(e -> {
            assistant.clearConversation();
            resetResponseArea();
        });

        HBox footer = new HBox(clearConversation);
        footer.setAlignment(Pos.CENTER_RIGHT);

        answerCard.getChildren().addAll(
            answerTitle,
            answerSubtitle,
            spacer(8),
            answerPane,
            footer
        );

        content.getChildren().addAll(questionCard, answerCard);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;"
        );

        root.setCenter(scroll);

        updateParameterState();

        scene = new Scene(root, 1250, 820);
        return scene;
    }

    private void askAI() {

        String question = queryArea.getText() == null
            ? ""
            : queryArea.getText().trim();

        if (queryTypeBox.getValue() == null) {
            showResponseError("Please select a query type.");
            queryTypeBox.requestFocus();
            return;
        }

        if (subjectBox.getValue() == null) {
            showResponseError("Please select who the question is for.");
            subjectBox.requestFocus();
            return;
        }

        if (question.isBlank()) {
            showResponseError("Please enter your question.");
            queryArea.requestFocus();
            return;
        }

        String prompt = buildPrompt(question);

        setLoading(true);

        Task<String> task = new Task<>() {
            @Override
            protected String call() {
                return assistant.ask(prompt);
            }
        };

        task.setOnSucceeded(e -> {
            String response = task.getValue();

            if (response == null || response.isBlank()) {
                showResponseError(
                    "MaaCare AI returned an empty response. Please try again."
                );
            } else {
                displayFormattedResponse(response);
            }

            setLoading(false);
        });

        task.setOnFailed(e -> {
            Throwable error = task.getException();

            showResponseError(
                error == null || error.getMessage() == null
                    ? "Unable to connect to MaaCare AI. Please try again."
                    : "Unable to get AI response:\n" + error.getMessage()
            );

            setLoading(false);
        });

        Thread thread = new Thread(task, "MaaCare-AI-Chat-Thread");
        thread.setDaemon(true);
        thread.start();
    }

    private String buildPrompt(String question) {

        StringBuilder prompt = new StringBuilder();

        prompt.append(
            "The user is asking MaaCare AI a healthcare question.\n\n"
        );

        prompt.append("QUERY TYPE: ")
              .append(value(queryTypeBox))
              .append("\n");

        prompt.append("SUBJECT: ")
              .append(value(subjectBox))
              .append("\n");

        prompt.append("MOTHER STAGE: ")
              .append(value(motherStageBox))
              .append("\n");

        prompt.append("TRIMESTER: ")
              .append(value(trimesterBox))
              .append("\n");

        prompt.append("CHILD AGE: ")
              .append(value(childAgeBox))
              .append("\n");

        prompt.append("MAIN CONCERN: ")
              .append(value(concernBox))
              .append("\n\n");

        prompt.append("USER QUESTION:\n")
              .append(question)
              .append("\n\n");

        prompt.append(
            "Use all applicable information above together with the user's "
            + "question. Answer the user's actual question directly. "
            + "Do not assume information marked Not applicable. "
            + "If important information is missing, say what is needed. "
            + "Keep the response understandable and healthcare-focused.\n\n"
            + "FORMAT THE RESPONSE FOR A JAVAFX HEALTHCARE APP:\n"
            + "- Use # for the main title when useful.\n"
            + "- Use ## or ### for section headings.\n"
            + "- Use **text** for important terms or labels.\n"
            + "- Use bullet points beginning with '-'.\n"
            + "- Use numbered lists such as '1.' when appropriate.\n"
            + "- Put blank lines between paragraphs and sections.\n"
            + "- Use '---' only between major sections when useful.\n"
            + "- Do not use HTML.\n"
            + "- Do not use tables.\n"
            + "- Keep paragraphs short and easy to read.\n"
            + "- Do not put the whole response in one large paragraph."
        );

        return prompt.toString();
    }

    /**
     * Converts the AI's Markdown-like response into readable JavaFX controls.
     * Supported:
     *  - # / ## / ### headings
     *  - **bold text**
     *  - - / * / • bullet points
     *  - 1. / 1) numbered points
     *  - --- separators
     *  - normal paragraphs
     */
    private void displayFormattedResponse(String response) {

        formattedAnswer.getChildren().clear();

        if (response == null || response.isBlank()) {
            showResponseError("No response received.");
            return;
        }

        String[] lines = response
            .replace("\r\n", "\n")
            .replace('\r', '\n')
            .split("\n", -1);

        boolean previousWasContent = false;

        for (String rawLine : lines) {

            String line = rawLine.trim();

            if (line.isEmpty()) {
                if (previousWasContent) {
                    Region spacer = new Region();
                    spacer.setMinHeight(7);
                    spacer.setPrefHeight(7);
                    spacer.setMaxHeight(7);
                    formattedAnswer.getChildren().add(spacer);
                    previousWasContent = false;
                }
                continue;
            }

            // Remove accidental Markdown image syntax.
            line = line.replaceAll("!\\[([^]]*)\\]\\([^)]*\\)", "$1");

            // Horizontal separator.
            if (line.matches("^(-{3,}|\\*{3,}|_{3,})$")) {
                Separator separator = new Separator();
                separator.setMaxWidth(Double.MAX_VALUE);
                separator.setPadding(new Insets(5, 0, 5, 0));
                formattedAnswer.getChildren().add(separator);
                previousWasContent = false;
                continue;
            }

            // Main heading.
            if (line.matches("^#\\s+.+")) {
                String text = line.replaceFirst("^#\\s+", "");
                TextFlow heading = createFormattedTextFlow(text, 24, true);
                heading.setPadding(new Insets(8, 0, 7, 0));
                formattedAnswer.getChildren().add(heading);
                previousWasContent = true;
                continue;
            }

            // Second-level heading.
            if (line.matches("^##\\s+.+")) {
                String text = line.replaceFirst("^##\\s+", "");
                TextFlow heading = createFormattedTextFlow(text, 20, true);
                heading.setPadding(new Insets(7, 0, 5, 0));
                formattedAnswer.getChildren().add(heading);
                previousWasContent = true;
                continue;
            }

            // Third-level heading.
            if (line.matches("^###\\s+.+")) {
                String text = line.replaceFirst("^###\\s+", "");
                TextFlow heading = createFormattedTextFlow(text, 17, true);
                heading.setPadding(new Insets(6, 0, 4, 0));
                formattedAnswer.getChildren().add(heading);
                previousWasContent = true;
                continue;
            }

            // Bullet point.
            if (line.matches("^[-*•]\\s+.+")) {
                String text = line.replaceFirst("^[-*•]\\s+", "");

                TextFlow bullet = createFormattedTextFlow(
                    "•  " + text,
                    15,
                    false
                );

                bullet.setPadding(new Insets(2, 5, 2, 8));
                formattedAnswer.getChildren().add(bullet);
                previousWasContent = true;
                continue;
            }

            // Numbered point.
            if (line.matches("^\\d+[.)]\\s+.+")) {
                String number = line.replaceFirst(
                    "^(\\d+)[.)]\\s+.*$",
                    "$1"
                );

                String text = line.replaceFirst(
                    "^\\d+[.)]\\s+",
                    ""
                );

                TextFlow numbered = createFormattedTextFlow(
                    number + ".  " + text,
                    15,
                    false
                );

                numbered.setPadding(new Insets(2, 5, 2, 8));
                formattedAnswer.getChildren().add(numbered);
                previousWasContent = true;
                continue;
            }

            // Normal paragraph.
            TextFlow paragraph = createFormattedTextFlow(
                line,
                15,
                false
            );

            paragraph.setPadding(new Insets(2, 0, 4, 0));
            formattedAnswer.getChildren().add(paragraph);
            previousWasContent = true;
        }
    }

    /**
     * Creates a TextFlow and renders **bold** sections as actual bold text.
     */
    private TextFlow createFormattedTextFlow(
        String text,
        double fontSize,
        boolean heading
    ) {

        TextFlow flow = new TextFlow();
        flow.setLineSpacing(3);
        flow.setMaxWidth(Double.MAX_VALUE);

        Pattern boldPattern = Pattern.compile(
            "\\*\\*(.+?)\\*\\*|__(.+?)__"
        );

        Matcher matcher = boldPattern.matcher(text);

        int lastEnd = 0;

        while (matcher.find()) {

            if (matcher.start() > lastEnd) {
                addNormalText(
                    flow,
                    text.substring(lastEnd, matcher.start()),
                    fontSize
                );
            }

            String boldText = matcher.group(1) != null
                ? matcher.group(1)
                : matcher.group(2);

            Text bold = new Text(boldText);
            bold.setFont(
                Font.font(
                    "System",
                    FontWeight.BOLD,
                    fontSize
                )
            );
            bold.setFill(
                Color.web(heading ? "#292653" : "#222222")
            );

            flow.getChildren().add(bold);

            lastEnd = matcher.end();
        }

        if (lastEnd < text.length()) {
            addNormalText(
                flow,
                text.substring(lastEnd),
                fontSize
            );
        }

        return flow;
    }

    private void addNormalText(
        TextFlow flow,
        String text,
        double fontSize
    ) {

        if (text.isEmpty()) {
            return;
        }

        Text normal = new Text(text);
        normal.setFont(Font.font("System", fontSize));
        normal.setFill(Color.web("#333333"));

        flow.getChildren().add(normal);
    }

    private void showResponseError(String message) {

        formattedAnswer.getChildren().clear();

        TextFlow errorFlow = new TextFlow();

        Text errorText = new Text(message);
        errorText.setFont(Font.font("System", 15));
        errorText.setFill(Color.web("#B42318"));

        errorFlow.getChildren().add(errorText);

        formattedAnswer.getChildren().add(errorFlow);
    }

    private void resetResponseArea() {

        formattedAnswer.getChildren().clear();

        Label placeholder = new Label(
            "Ask a question to receive an AI response..."
        );

        placeholder.setWrapText(true);
        placeholder.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-text-fill: #888899;"
        );

        formattedAnswer.getChildren().add(placeholder);
    }

    private void updateParameterState() {

        String subject = subjectBox.getValue();

        boolean mother =
            "Mother".equals(subject) ||
            "Mother & Child".equals(subject);

        boolean child =
            "Child".equals(subject) ||
            "Mother & Child".equals(subject);

        motherStageBox.setDisable(!mother);
        trimesterBox.setDisable(!mother);
        childAgeBox.setDisable(!child);

        if (!mother) {
            motherStageBox.setValue("Not applicable");
            trimesterBox.setValue("Not applicable");
        }

        if (!child) {
            childAgeBox.setValue("Not applicable");
        }
    }

    private void setLoading(boolean loading) {

        askButton.setDisable(loading);
        clearButton.setDisable(loading);
        queryArea.setDisable(loading);
        queryTypeBox.setDisable(loading);
        subjectBox.setDisable(loading);
        motherStageBox.setDisable(loading);
        trimesterBox.setDisable(loading);
        childAgeBox.setDisable(loading);
        concernBox.setDisable(loading);

        progress.setVisible(loading);
        progress.setManaged(loading);
        loadingText.setVisible(loading);
        loadingText.setManaged(loading);

        if (loading && progress.getParent() != null) {
            progress.getParent().toFront();
            progress.requestFocus();
        }
    }

    private void clearPage() {

        queryArea.clear();
        resetResponseArea();

        queryTypeBox.setValue(null);
        subjectBox.setValue(null);
        motherStageBox.setValue("Not applicable");
        trimesterBox.setValue("Not applicable");
        childAgeBox.setValue("Not applicable");
        concernBox.setValue("General information");

        assistant.clearConversation();

        updateParameterState();
    }

    private String value(ComboBox<String> box) {

        String value = box.getValue();

        return value == null || value.isBlank()
            ? "Not specified"
            : value;
    }

    private VBox createCard(double width) {

        VBox card = new VBox(9);

        card.setPrefWidth(width);
        card.setMaxWidth(width);
        card.setPadding(new Insets(26));

        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.96);" +
            "-fx-background-radius: 22px;" +
            "-fx-border-color: rgba(220,210,235,0.8);" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 22px;" +
            "-fx-effect: dropshadow(gaussian, rgba(70,50,90,0.10), 18, 0.15, 0, 6);"
        );

        return card;
    }

    private Label label(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("System", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#4B4770"));

        return label;
    }

    private Text heading(String text) {

        Text heading = new Text(text);
        heading.setFont(Font.font("System", FontWeight.BOLD, 23));
        heading.setFill(Color.web("#292653"));

        return heading;
    }

    private Text normalText(String text) {

        Text t = new Text(text);
        t.setFont(Font.font("System", 13));
        t.setFill(Color.web("#77778D"));

        return t;
    }

    private javafx.scene.layout.Region spacer(double height) {

        javafx.scene.layout.Region region =
            new javafx.scene.layout.Region();

        region.setMinHeight(height);
        region.setPrefHeight(height);
        region.setMaxHeight(height);

        return region;
    }

    private void styleCombo(ComboBox<String> box) {

        box.setPrefHeight(46);
        box.setMaxWidth(Double.MAX_VALUE);

        box.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #DDD9E6;" +
            "-fx-border-radius: 11px;" +
            "-fx-background-radius: 11px;" +
            "-fx-font-size: 14px;"
        );
    }

    private String textAreaStyle() {

        return
            "-fx-background-color: white;" +
            "-fx-control-inner-background: white;" +
            "-fx-border-color: #DDD9E6;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 12px;";
    }

    private String primaryButtonStyle() {

        return
            "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 12px;" +
            "-fx-cursor: hand;";
    }

    private String secondaryButtonStyle() {

        return
            "-fx-background-color: white;" +
            "-fx-text-fill: #633A9B;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #9B4DCC;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 11px;" +
            "-fx-background-radius: 11px;" +
            "-fx-cursor: hand;";
    }

    public BorderPane getRoot() {
        return root;
    }

    public Scene getChatScene() {
        return getScene();
    }
}
