package com.sigma.view;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.sigma.dao.AshaMessageDAO;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sigma.dao.Ashabeneficiariesdao;
import com.sigma.dao.Ashavisit;
import com.sigma.dao.AshaProfileDAO;
import com.sigma.model.AshaBeneficiary;
import com.sigma.model.AshaWorkerVisitModel;
import com.sigma.model.AshaProfileModel;
import com.sigma.dao.AshaMessageDAO;

public class Asha_workerdashboard {
        private final AshaMessageDAO messageDAO = new AshaMessageDAO();

    // =========================================================
    // COLORS
    // =========================================================

    /*private static final String COLOR_BG = "#FEF9FC";
    private static final String COLOR_PRIMARY_PINK = "#E91E63";
    private static final String COLOR_LIGHT_PINK = "#FCE4EC";
    private static final String COLOR_TEXT_DARK = "#111827";
    private static final String COLOR_TEXT_MUTED = "#374151";
    private static final String COLOR_WHITE = "#FFFFFF";
    private static final String COLOR_BORDER = "#D1D5DB";
    private static final String COLOR_GREEN = "#059669";*/
// =========================================================
// COLORS - MotherBabyCare Theme
// =========================================================

private static final String COLOR_BG = "#FFF7FB";

private static final String COLOR_PRIMARY_PINK = "#E84A87";

private static final String COLOR_LIGHT_PINK = "#FFF5F9";

private static final String COLOR_TEXT_DARK = "#24234F";

private static final String COLOR_TEXT_MUTED = "#77778D";

private static final String COLOR_WHITE = "#FFFFFF";

private static final String COLOR_BORDER = "#E7DCE8";

private static final String COLOR_GREEN = "#3C9A68";

private static final String COLOR_PURPLE = "#9B4DCC";

private static final String COLOR_LIGHT_PURPLE = "#F4EDFF";

private static final String COLOR_GOLD = "#C7821B";

private static final String COLOR_LIGHT_GOLD = "#FFFDF4";

private static final String COLOR_BLUE = "#6B7FD7";

private static final String COLOR_LIGHT_BLUE = "#F0F4FF";

private static final String COLOR_BABY_PINK = "#FFEAF3";
    // =========================================================
    // STAGE / SCENE
    // =========================================================

    public static Stage homepageStage;

    private Scene homepageScene;

    // =========================================================
    // COMMON MAIN AREA
    // =========================================================

    private BorderPane mainRoot;

    private ScrollPane centerScrollPane;

    private VBox centerContent;
    private String ashaId;
    // =========================================================
// CONSTRUCTOR
// =========================================================

public Asha_workerdashboard() {

    //this.ashaId = ashaId;

    System.out.println(
            "[DASHBOARD] ASHA ID received: " + ashaId
    );
}

    // =========================================================
    // FIREBASE DAO
    // =========================================================

    private final Ashabeneficiariesdao beneficiaryDAO =
            new Ashabeneficiariesdao();

    private final Ashavisit visitDAO =
            new Ashavisit();

    private final AshaProfileDAO profileDAO =
            new AshaProfileDAO();

    // =========================================================
    // DASHBOARD FIREBASE DATA
    // =========================================================

    private int totalBeneficiaries = 0;
    private int totalVisits = 0;

    private int pregnantWomen = 0;
    private int children = 0;
    private int immunizations = 0;
    private int womenTestedThisMonth = 0;
private int womenTestNormal = 0;
private int womenTestRisky = 0;
private int womenCheckupBaaki = 0;

    private String ashaName = "ASHA Worker";
   // ashaName.setTranslateY(2);
    private String villageName = " ";

    private List<AshaWorkerVisitModel> firebaseVisits =
            new ArrayList<>();
            private List<AshaBeneficiary> firebaseBeneficiaries =
        new ArrayList<>();

    // =========================================================
    // SET STAGE
    // =========================================================

    public void setStage(Stage stage) {
        homepageStage = stage;
    }

    // =========================================================
    // RUN
    // =========================================================

    public Scene run() {

        homepageScene = createDashboardScene();

        return homepageScene;
    }

    // =========================================================
    // DASHBOARD SCENE
    // =========================================================

    private Scene createDashboardScene() {

        // -----------------------------------------------------
        // COMMON ROOT
        // -----------------------------------------------------

        mainRoot = new BorderPane();

        mainRoot.setStyle(
                "-fx-background-color: linear-gradient(" +
        "to bottom right, " +
        "#FFFFFF 0%, " +
        "#FFF7FB 55%, " +
        "#F4EDFF 100%);"
        );

        mainRoot.setPrefSize(
                1300,
                700
        );

        // -----------------------------------------------------
        // SIDEBAR
        // -----------------------------------------------------

        VBox sidebar = createSidebar();

        // -----------------------------------------------------
        // COMMON HEADER
        // -----------------------------------------------------

        HBox header = createHeader();

        // -----------------------------------------------------
        // CENTER CONTENT
        // -----------------------------------------------------

        centerContent = new VBox(22);

        centerContent.setPadding(
                new Insets(22, 28, 22, 28)
        );

        centerContent.setFillWidth(true);

        // -----------------------------------------------------
        // SCROLL PANE
        // -----------------------------------------------------

        centerScrollPane =
                new ScrollPane(centerContent);

        centerScrollPane.setFitToWidth(true);

        centerScrollPane.setFitToHeight(false);

        centerScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        centerScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        centerScrollPane.setStyle(
                "-fx-background-color: " + COLOR_BG + ";" +
                "-fx-background: " + COLOR_BG + ";" +
                "-fx-border-color: transparent;"
        );

        // -----------------------------------------------------
        // CENTER WRAPPER
        // -----------------------------------------------------

        BorderPane centerPane =
                new BorderPane();

        centerPane.setStyle(
                "-fx-background-color: #FFF7FB;"
        );

        centerPane.setTop(header);

        centerPane.setCenter(centerScrollPane);

        // -----------------------------------------------------
        // ROOT
        // -----------------------------------------------------

        mainRoot.setLeft(sidebar);

        mainRoot.setCenter(centerPane);

        // -----------------------------------------------------
        // FIRST PAGE
        // -----------------------------------------------------

        showDashboardContent();

        // -----------------------------------------------------
        // LOAD FIREBASE
        // -----------------------------------------------------

        loadDashboardData();

        return new Scene(
                mainRoot,
                1300,
                700
        );
    }
    // =========================================================
// SEND MESSAGE
// =========================================================
// =========================================================
// SEND MESSAGE
// =========================================================

/*private void showSendMessageDialog() {

    javafx.scene.control.Dialog<Void> dialog =
            new javafx.scene.control.Dialog<>();

    dialog.setTitle("Send Message");
    dialog.setHeaderText(
            "Send Message to Beneficiary"
    );


    javafx.scene.control.ButtonType sendButton =
            new javafx.scene.control.ButtonType(
                    "Send",
                    javafx.scene.control.ButtonBar.ButtonData.OK_DONE
            );

    javafx.scene.control.ButtonType cancelButton =
            javafx.scene.control.ButtonType.CANCEL;


    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    sendButton,
                    cancelButton
            );


    // =====================================================
    // CONTENT
    // =====================================================

    VBox content =
            new VBox(12);

    content.setPadding(
            new Insets(15)
    );


    Label nameLabel =
            new Label(
                    "Beneficiary Name"
            );

    nameLabel.setFont(
            Font.font(
                    "System",
                    FontWeight.BOLD,
                    13
            )
    );


    javafx.scene.control.TextField receiver =
            new javafx.scene.control.TextField();

    receiver.setPromptText(
            "Enter beneficiary name"
    );

    receiver.setPrefWidth(350);


    Label messageLabel =
            new Label(
                    "Message"
            );

    messageLabel.setFont(
            Font.font(
                    "System",
                    FontWeight.BOLD,
                    13
            )
    );


    javafx.scene.control.TextArea message =
            new javafx.scene.control.TextArea();

    message.setPromptText(
            "Enter your message"
    );

    message.setPrefRowCount(5);

    message.setWrapText(true);


    content.getChildren().addAll(
            nameLabel,
            receiver,
            messageLabel,
            message
    );


    dialog.getDialogPane()
            .setContent(content);


    // =====================================================
    // SEND BUTTON ACTION
    // =====================================================

    javafx.scene.Node sendButtonNode =
            dialog.getDialogPane()
                    .lookupButton(sendButton);


    sendButtonNode.setOnMouseClicked(e -> {

        String beneficiaryName =
                receiver.getText().trim();

        String msg =
                message.getText().trim();


        // -------------------------------------------------
        // VALIDATION
        // -------------------------------------------------

        if (beneficiaryName.isEmpty()) {

            showAlert(
                    javafx.scene.control.Alert.AlertType.WARNING,
                    "Please enter beneficiary name."
            );

            e.consume();

            return;
        }


        if (msg.isEmpty()) {

            showAlert(
                    javafx.scene.control.Alert.AlertType.WARNING,
                    "Please enter a message."
            );

            e.consume();

            return;
        }


        // -------------------------------------------------
        // SAVE TO FIREBASE
        // -------------------------------------------------

        boolean saved =
                messageDAO.saveMessage(
                        beneficiaryName,
                        msg
                );


        if (saved) {

            showAlert(
                    javafx.scene.control.Alert.AlertType.INFORMATION,
                    "Message sent and saved successfully!"
            );

        } else {

            showAlert(
                    javafx.scene.control.Alert.AlertType.ERROR,
                    "Message could not be saved."
            );

            e.consume();
        }

    });


    dialog.showAndWait();
}*/
// =========================================================
// SEND MESSAGE DIALOG
// =========================================================

private void showSendMessageDialog() {

    javafx.scene.control.Dialog<Boolean> dialog =
            new javafx.scene.control.Dialog<>();

    dialog.setTitle("Send Message");

    dialog.setHeaderText(
            "Send Message to Beneficiary"
    );


    // =====================================================
    // BUTTONS
    // =====================================================

    javafx.scene.control.ButtonType sendButton =
            new javafx.scene.control.ButtonType(
                    "Send",
                    javafx.scene.control.ButtonBar.ButtonData.OK_DONE
            );

    javafx.scene.control.ButtonType cancelButton =
            javafx.scene.control.ButtonType.CANCEL;


    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    sendButton,
                    cancelButton
            );
            VBox historyBox = new VBox(8);

Label historyTitle =
        new Label("Previous Messages");

historyTitle.setFont(
        Font.font(
                "System",
                FontWeight.BOLD,
                14
        )
);

historyBox.getChildren().add(historyTitle);

List<Map<String, Object>> messages =
        messageDAO.getMessages();

for (Map<String, Object> data : messages) {

    String beneficiary =
            String.valueOf(
                    data.get("beneficiaryName")
            );

    String msg =
            String.valueOf(
                    data.get("message")
            );

    Label messageLabel =
            new Label(
                    beneficiary + " : " + msg
            );

    messageLabel.setWrapText(true);

    historyBox.getChildren().add(
            messageLabel
    );
}


    // =====================================================
    // CONTENT
    // =====================================================

    VBox content =
            new VBox(12);

    content.setPadding(
            new Insets(20)
    );


    Label nameLabel =
            new Label(
                    "Beneficiary Name"
            );

    nameLabel.setFont(
            Font.font(
                    "System",
                    FontWeight.BOLD,
                    13
            )
    );


    javafx.scene.control.TextField nameField =
            new javafx.scene.control.TextField();

    nameField.setPromptText(
            "Enter beneficiary name"
    );

    nameField.setPrefWidth(350);


    Label messageLabel =
            new Label(
                    "Message"
            );

    messageLabel.setFont(
            Font.font(
                    "System",
                    FontWeight.BOLD,
                    13
            )
    );


    javafx.scene.control.TextArea messageField =
            new javafx.scene.control.TextArea();

    messageField.setPromptText(
            "Enter message"
    );

    messageField.setPrefRowCount(5);

    messageField.setWrapText(true);


    content.getChildren().addAll(
            nameLabel,
            nameField,
            messageLabel,
            messageField,
            historyBox
    );


    dialog.getDialogPane()
            .setContent(content);


    // =====================================================
    // RESULT CONVERTER
    // =====================================================

    dialog.setResultConverter(
            button -> {

                if (button == sendButton) {

                    String beneficiaryName =
                            nameField.getText().trim();

                    String message =
                            messageField.getText().trim();


                    // -----------------------------------------
                    // VALIDATION
                    // -----------------------------------------

                    if (beneficiaryName.isEmpty()) {

                        showAlert(
                                javafx.scene.control.Alert.AlertType.WARNING,
                                "Please enter beneficiary name."
                        );

                        return false;
                    }


                    if (message.isEmpty()) {

                        showAlert(
                                javafx.scene.control.Alert.AlertType.WARNING,
                                "Please enter message."
                        );

                        return false;
                    }


                    // -----------------------------------------
                    // FIREBASE SAVE
                    // -----------------------------------------

                    System.out.println(
                            "Trying to save message..."
                    );


                    boolean saved =
                            messageDAO.saveMessage(
                                    beneficiaryName,
                                    message
                            );


                    if (saved) {

                        showAlert(
                                javafx.scene.control.Alert.AlertType.INFORMATION,
                                "Message saved successfully in Firebase!"
                        );

                        return true;

                    } else {

                        showAlert(
                                javafx.scene.control.Alert.AlertType.ERROR,
                                "Message could not be saved in Firebase."
                        );

                        return false;
                    }
                }

                return null;
            }
    );


    dialog.showAndWait();
}

// =========================================================
// ALERT
// =========================================================

private void showAlert(
        javafx.scene.control.Alert.AlertType type,
        String message) {

    javafx.scene.control.Alert alert =
            new javafx.scene.control.Alert(type);

    alert.setTitle("MaaCare AI");

    alert.setHeaderText(null);

    alert.setContentText(message);

    alert.showAndWait();
}

    // =========================================================
    // LOAD DASHBOARD DATA FROM FIREBASE
    // =========================================================

    private void loadDashboardData() {

        Task<Void> firebaseTask =
                new Task<>() {

                    @Override
                    protected Void call() {

                        try {

                            System.out.println(
                                    "================================="
                            );

                            System.out.println(
                                    "Loading ASHA Dashboard Data..."
                            );

                            // ---------------------------------
                            // BENEFICIARIES
                            // ---------------------------------

                            List<AshaBeneficiary> beneficiaries =
                                    beneficiaryDAO
                                            .getAshaBeneficiaries();

                            if (beneficiaries != null) {
                                firebaseBeneficiaries = beneficiaries;

                                totalBeneficiaries =
                                        beneficiaries.size();

                                calculateBeneficiaryStats(
                                        beneficiaries
                                );
                            }

                            // ---------------------------------
                            // VISITS
                            // ---------------------------------

                            firebaseVisits =
                                    visitDAO.getVisits();

                            if (firebaseVisits != null) {

                                totalVisits =
                                        firebaseVisits.size();
                            }

                            // ---------------------------------
                            // PROFILE
                            // ---------------------------------

                            List<AshaProfileModel> profiles =
                                    profileDAO.getAshaProfiles();

                            if (profiles != null &&
                                    !profiles.isEmpty()) {

                                AshaProfileModel profile =
                                        profiles.get(0);

                                loadProfileData(profile);
                            }

                            System.out.println(
                                    "Total Beneficiaries = "
                                            + totalBeneficiaries
                            );

                            System.out.println(
                                    "Total Visits = "
                                            + totalVisits
                            );

                            System.out.println(
                                    "Pregnant Women = "
                                            + pregnantWomen
                            );

                            System.out.println(
                                    "Children = "
                                            + children
                            );

                            System.out.println(
                                    "Immunizations = "
                                            + immunizations
                            );

                            System.out.println(
                                    "================================="
                            );

                        } catch (Exception e) {

                            System.out.println(
                                    "ERROR LOADING DASHBOARD DATA"
                            );

                            e.printStackTrace();
                        }

                        return null;
                    }
                };

        firebaseTask.setOnSucceeded(
                e -> {

                    Platform.runLater(() -> {

                        updateHeaderProfile();

                        showDashboardContent();

                    });
                }
        );

        firebaseTask.setOnFailed(
                e -> {

                    System.out.println(
                            "Firebase Dashboard Task Failed"
                    );

                    if (firebaseTask.getException() != null) {

                        firebaseTask
                                .getException()
                                .printStackTrace();
                    }
                }
        );

        Thread thread =
                new Thread(firebaseTask);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // BENEFICIARY STATISTICS
    // =========================================================

    private void calculateBeneficiaryStats(
        List<AshaBeneficiary> beneficiaries) {

    pregnantWomen = 0;
    children = 0;
    immunizations = 0;

    womenTestedThisMonth = 0;
    womenTestNormal = 0;
    womenTestRisky = 0;
    womenCheckupBaaki = 0;

    if (beneficiaries == null) {
        return;
    }

    LocalDate today = LocalDate.now();

    for (AshaBeneficiary beneficiary : beneficiaries) {

        if (beneficiary == null) {
            continue;
        }

        String category =
                beneficiary.getCategory() == null
                        ? ""
                        : beneficiary.getCategory().trim().toLowerCase();

        String status =
                beneficiary.getStatus() == null
                        ? ""
                        : beneficiary.getStatus().trim().toLowerCase();

        // =====================================================
        // PREGNANT WOMEN
        // =====================================================

        if (category.contains("pregnant")) {

            pregnantWomen++;

            // -----------------------------------------------
            // WOMEN TESTED THIS MONTH
            // -----------------------------------------------

            String lastVisit = beneficiary.getLastVisit();

            if (lastVisit != null &&
                    !lastVisit.trim().isEmpty()) {

                LocalDate visitDate =
                        parseDate(lastVisit);

                if (visitDate != null &&
                        visitDate.getMonth() == today.getMonth() &&
                        visitDate.getYear() == today.getYear()) {

                    womenTestedThisMonth++;
                }
            }

            // -----------------------------------------------
            // NORMAL
            // -----------------------------------------------

            if (status.equals("normal")) {

                womenTestNormal++;
            }

            // -----------------------------------------------
            // RISKY
            // -----------------------------------------------

            if (status.equals("high risk")) {

                womenTestRisky++;
            }

            // -----------------------------------------------
            // CHECKUP BAAKI
            // -----------------------------------------------

            if (status.equals("follow-up due") ||
                    status.equals("follow up due") ||
                    status.equals("pending")) {

                womenCheckupBaaki++;
            }
        }

        // =====================================================
        // CHILDREN
        // =====================================================

        if (category.contains("child")) {

            children++;
        }

        // =====================================================
        // IMMUNIZED
        // =====================================================

        if (status.equals("immunized")) {

            immunizations++;
        }
    }
    System.out.println();

    System.out.println(
            "Women Tested This Month = " +
                    womenTestedThisMonth
    );

    System.out.println(
            "Women Test Normal = " +
                    womenTestNormal
    );

    System.out.println(
            "Women Test Risky = " +
                    womenTestRisky
    );

    System.out.println(
            "Women Checkup Baaki = " +
                    womenCheckupBaaki
    );
    System.out.println();
}
private LocalDate parseDate(String dateText) {

    if (dateText == null || dateText.trim().isEmpty()) {
        return null;
    }

    String text = dateText.trim();

    String[] formats = {
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "MM/dd/yyyy",
            "dd MMM yyyy",
            "d MMM yyyy",
            "dd MMMM yyyy",
            "d MMMM yyyy"
    };

    for (String format : formats) {

        try {

            return LocalDate.parse(
                    text,
                    DateTimeFormatter.ofPattern(format)
            );

        } catch (Exception ignored) {
        }
    }

    System.out.println(
            "Unable to parse visit date: " + text
    );

    return null;
}

    // =========================================================
    // PROFILE DATA
    // =========================================================

    private void loadProfileData(
            AshaProfileModel profile) {

        if (profile == null) {
            return;
        }

        try {

            /*
             * Common getter names ke according profile data.
             *
             * Agar tumhare model me exact getter available hai
             * to usko use kar sakte ho.
             */

            String name =
                    profile.getName();

            if (name != null &&
                    !name.trim().isEmpty()) {

                ashaName = name;
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to read ASHA profile name."
            );
        }

        try {

            String village =
                    profile.getAddress();

            if (village != null &&
                    !village.trim().isEmpty()) {

                villageName = village;
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to read village."
            );
        }
    }

    // =========================================================
    // UPDATE HEADER PROFILE
    // =========================================================

    private void updateHeaderProfile() {

        /*
         * Header ko recreate karne se navigation/UI structure
         * disturb nahi hota.
         *
         * Current header Firebase profile values use karega.
         */

        if (mainRoot == null) {
            return;
        }

        Node center =
                mainRoot.getCenter();

        if (!(center instanceof BorderPane)) {
            return;
        }

        BorderPane centerPane =
                (BorderPane) center;

        centerPane.setTop(
                createHeader()
        );
    }

    // =========================================================
    // SHOW DASHBOARD
    // =========================================================

    private void showDashboardContent() {

        if (centerContent == null) {
            return;
        }

        centerContent.getChildren().clear();

        HBox statsRow =
                createStatsRow();

        HBox middleRow =
                createMiddleRow();

        HBox bottomRow =
                createBottomRow();

        centerContent.getChildren().addAll(
                statsRow,
                middleRow,
                bottomRow
        );

        centerScrollPane.setVvalue(0);
    }

    // =========================================================
    // COMMON PAGE CONTENT
    // =========================================================

    private void setCenterContent(Node content) {

        if (centerContent == null ||
                content == null) {

            return;
        }

        centerContent.getChildren().clear();

        if (content instanceof Region) {

            Region region =
                    (Region) content;

            region.setMaxWidth(
                    Double.MAX_VALUE
            );

            region.setPrefWidth(
                    centerScrollPane
                            .getViewportBounds()
                            .getWidth()
            );
        }

        centerContent.getChildren().add(
                content
        );

        centerScrollPane.setVvalue(0);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar =
                new VBox(14);

        sidebar.setPadding(
                new Insets(18, 16, 12, 16)
        );

        sidebar.setPrefWidth(260);

        sidebar.setMinWidth(260);

        sidebar.setStyle(
                "-fx-background-color: " +
                COLOR_BG + ";" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        // =====================================================
        // LOGO
        // =====================================================

        ImageView logoView =
                createImageViewHolder(
                        "assets\\images\\logo1.jpeg",
                        290,
                        190
                );

        // =====================================================
        // NAVIGATION
        // =====================================================

        VBox navList =
                new VBox(8);

        // -----------------------------------------------------
        // DASHBOARD
        // -----------------------------------------------------

        Button dashboardButton =
                createNavItem(
                        "Dashboard",
                        false
                );

        dashboardButton.setOnAction(
                e -> {

                    showDashboardContent();

                    // Refresh Firebase data
                    loadDashboardData();
                }
        );

        // -----------------------------------------------------
        // BENEFICIARIES
        // -----------------------------------------------------

        Button beneficiariesButton =
                createNavItem(
                        "Beneficiaries",
                        false
                );

        beneficiariesButton.setOnAction(
                e -> openBeneficiaries()
        );

        // -----------------------------------------------------
        // HEALTH VISIT
        // -----------------------------------------------------

        Button healthVisitButton =
                createNavItem(
                        "Health Visit",
                        false
                );

        healthVisitButton.setOnAction(
                e -> openHealthVisit()
        );

        // -----------------------------------------------------
        // PROFILE
        // -----------------------------------------------------

        Button profileButton =
                createNavItem(
                        "Profile",
                        false
                );

        profileButton.setOnAction(
                e -> openProfile()
        );

        navList.getChildren().addAll(
                dashboardButton,
                beneficiariesButton,
                healthVisitButton,
                profileButton
        );

        // =====================================================
        // BOTTOM WORKER IMAGE
        // =====================================================

        ImageView bottomImgView =
                createImageViewHolder(
                        "assets\\images\\worker2.jpeg",
                        290,
                        190
                );

        bottomImgView.setTranslateY(-7);

        Label txtSlogan =
                new Label(
                        "Swasth Maa, Swasth Parivaar,\n" +
                        "Swasth Samaaj."
                );

        txtSlogan.setTranslateY(-14);

        txtSlogan.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16
                )
        );

        txtSlogan.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        txtSlogan.setAlignment(
                Pos.CENTER
        );

        VBox bottomBox =
                new VBox(
                        6,
                        bottomImgView,
                        txtSlogan
                );

        bottomBox.setAlignment(
                Pos.CENTER
        );

        VBox.setMargin(
                bottomBox,
                new Insets(-50, 0, 0, 0)
        );

        bottomBox.setCursor(
                Cursor.HAND
        );

        bottomBox.setOnMouseClicked(
                e -> openProfile()
        );

        // =====================================================
        // ADD SIDEBAR
        // =====================================================

        sidebar.getChildren().addAll(
                logoView,
                navList,
                new Spacer(),
                bottomBox
        );

        return sidebar;
    }

    // =========================================================
    // BENEFICIARIES
    // =========================================================

    private void openBeneficiaries() {

        try {

            Asha_beneficiaries beneficiaries =
                    new Asha_beneficiaries();

            Node content =
                    beneficiaries
                            .getBeneficiariesContent();

            setCenterContent(content);

        } catch (Exception ex) {

            ex.printStackTrace();

            System.out.println(
                    "Error opening Beneficiaries page."
            );
        }
    }

    // =========================================================
    // HEALTH VISIT
    // =========================================================

    private void openHealthVisit() {

        try {

            Asha_workervisit visit =
                    new Asha_workervisit();

            Node content =
                    visit.getHealthVisitContent();

            setCenterContent(content);

        } catch (Exception ex) {

            ex.printStackTrace();

            System.out.println(
                    "Error opening Health Visit page."
            );
        }
    }

    // =========================================================
    // PROFILE
    // =========================================================

    private void openProfile() {

        try {

           Asha_profilepage profile = new Asha_profilepage();
            Node content =
                    profile.getProfileContent();

            setCenterContent(content);

        } catch (Exception ex) {

            ex.printStackTrace();

            System.out.println(
                    "Error opening Profile page."
            );
        }
    }

    // =========================================================
    // BACK TO DASHBOARD
    // =========================================================

    public void backToDashboard() {

        showDashboardContent();
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private Button createNavItem(
            String title,
            boolean active
    ) {

        Button button =
                new Button();

        HBox content =
                new HBox(14);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle iconDot =
                new Circle(
                        6,
                        active
                                ? Color.web(
                                        COLOR_PRIMARY_PINK
                                )
                                : Color.web(
                                        COLOR_TEXT_MUTED
                                )
                );

        Label lbl =
                new Label(title);

        lbl.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        lbl.setStyle(
                "-fx-text-fill: " +
                (
                        active
                                ? COLOR_PRIMARY_PINK
                                : COLOR_TEXT_MUTED
                ) +
                ";"
        );

        content.getChildren().addAll(
                iconDot,
                lbl
        );

        button.setGraphic(
                content
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(48);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        String baseStyle =
                active
                        ?
                        "-fx-background-color: " +
                        COLOR_LIGHT_PINK +
                        ";" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: transparent;" +
                        "-fx-cursor: hand;"
                        :
                        "-fx-background-color: transparent;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: transparent;" +
                        "-fx-cursor: hand;";

        button.setStyle(
                baseStyle
        );

        button.setOnMouseEntered(
                e -> {

                    if (!active) {

                        button.setStyle(
                                "-fx-background-color: #F3F4F6;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-color: transparent;" +
                                "-fx-cursor: hand;"
                        );
                    }
                }
        );

        button.setOnMouseExited(
                e -> button.setStyle(
                        baseStyle
                )
        );

        return button;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(22, 28, 0, 28)
        );

        VBox titleBox =
                new VBox(4);

        Label welcome =
                new Label(
                        "Namaste, ASHA Worker! 👋"
                );

        welcome.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24
                )
        );

        welcome.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label sub =
                new Label(
                        "Thank you for your dedication towards a healthier community."
                );

        sub.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13.5
                )
        );

        sub.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_MUTED + ";"
        );

        titleBox.getChildren().addAll(
                welcome,
                sub
        );

        HBox rightControls =
                new HBox(14);

        rightControls.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // DATE PICKER
        // =====================================================

        DatePicker calendarPicker =
                new DatePicker(
                        LocalDate.now()
                );

        calendarPicker.setPrefWidth(
                150
        );

        calendarPicker.setStyle(
                "-fx-background-color: " +
                COLOR_WHITE + ";" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        calendarPicker.setOnAction(
                e -> System.out.println(
                        "Selected Date: " +
                        calendarPicker.getValue()
                )
        );

        // =====================================================
        // PROFILE IMAGE
        // =====================================================

        ImageView avatar =
                createImageViewHolder(
                        "assets\\images\\profilelogo.jpeg",
                        42,
                        42
                );

        Circle clip =
                new Circle(
                        21,
                        21,
                        21
                );

        avatar.setClip(clip);

        VBox userDetails =
                new VBox(2);

        Label name =
                new Label(
                        ashaName
                );

        name.setTranslateY(6);

        name.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        name.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label location =
                new Label(
                        villageName
                );

        location.setTranslateY(6);

        location.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        11.5
                )
        );

        location.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_MUTED + ";"
        );

        userDetails.getChildren().addAll(
                name,
                location
        );

        // =====================================================
        // PROFILE HEADER CLICK
        // =====================================================

        HBox profileContent =
                new HBox(10);

        profileContent.setAlignment(
                Pos.CENTER_LEFT
        );

        profileContent.getChildren().addAll(
                avatar,
                userDetails
        );

        Button profileHeaderButton =
                new Button();

        profileHeaderButton.setGraphic(
                profileContent
        );

        profileHeaderButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        profileHeaderButton.setOnAction(
                e -> openProfile()
        );

        rightControls.getChildren().addAll(
                calendarPicker,
                profileHeaderButton
        );

        header.getChildren().addAll(
                titleBox,
                new Spacer(),
                rightControls
        );

        return header;
    }

    // =========================================================
    // STATS ROW
    // =========================================================

    private HBox createStatsRow() {

        HBox row =
                new HBox(16);

        /*
         * Firebase:
         *
         * Pregnant Women -> available model data
         * Children       -> available model data
         * Home Visits    -> Ashavisit.getVisits()
         * Immunizations  -> available model data
         */

        VBox c1 =
                createStatCard(
                        "Pregnant Women",
                        String.valueOf(
                                pregnantWomen
                        ),
                        "Firebase Data",
                        COLOR_LIGHT_PINK
                );

        VBox c2 =
                createStatCard(
                        "Children (0–5 yrs)",
                        String.valueOf(
                                children
                        ),
                        "Firebase Data",
                        "#E0E7FF"
                );

        VBox c3 =
                createStatCard(
                        "Home Visits",
                        String.valueOf(
                                totalVisits
                        ),
                        "Firebase Data",
                        "#E0F2FE"
                );

        VBox c4 =
                createStatCard(
                        "Immunizations",
                        String.valueOf(
                                immunizations
                        ),
                        "Firebase Data",
                        "#FFEDD5"
                );

        HBox.setHgrow(
                c1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                c2,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                c3,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                c4,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                c1,
                c2,
                c3,
                c4
        );

        return row;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            String val,
            String trend,
            String iconBg
    ) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                "-fx-background-color: " +
                COLOR_WHITE + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox top =
                new HBox(14);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle iconCircle =
                new Circle(
                        22,
                        Color.web(iconBg)
                );

        VBox txt =
                new VBox(2);

        Label t =
                new Label(title);

        t.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13.5
                )
        );

        t.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_MUTED + ";"
        );

        Label v =
                new Label(val);

        v.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        28
                )
        );

        v.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        txt.getChildren().addAll(
                t,
                v
        );

        top.getChildren().addAll(
                iconCircle,
                txt
        );

        Label tr =
                new Label(trend);

        tr.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12.5
                )
        );

        tr.setStyle(
                "-fx-text-fill: " +
                COLOR_GREEN + ";"
        );

        card.getChildren().addAll(
                top,
                tr
        );

        return card;
    }

    // =========================================================
    // MIDDLE ROW
    // =========================================================

    private HBox createMiddleRow() {

        HBox row =
                new HBox(16);

        VBox chartBox =
                new VBox(14);

        chartBox.setPadding(
                new Insets(18)
        );

        chartBox.setStyle(
                "-fx-background-color: " +
                COLOR_WHITE + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox.setHgrow(
                chartBox,
                Priority.ALWAYS
        );

        Label chartTitle =
                new Label(
                        "♥ Health Activities Overview for only pregant women"
                );

        chartTitle.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16
                )
        );

        chartTitle.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        StackPane donutContainer =
                createSegmentedDonutChart();

        VBox legend =
                new VBox(10);

        legend.setAlignment(
                Pos.CENTER_LEFT
        );

        int totalActivities =
                totalBeneficiaries +
                totalVisits;

        legend.getChildren().addAll(

                createLegendRow(
                        COLOR_PRIMARY_PINK,
                        "Women Tested This Month",
                        String.valueOf(
                                womenTestedThisMonth
                        )
                ),

                createLegendRow(
                        "#9B4DCC",
                        "Women Test Normal",
                        String.valueOf(
                                womenTestNormal
                        )
                ),

                createLegendRow(
                        "#C7821B",
                        "Women Test Risky",
                        String.valueOf(womenTestRisky)
                ),

                createLegendRow(
                        "#6B7FD7",
                        "Women Checkup Baaki",
                        String.valueOf(
                                womenCheckupBaaki
                        )
                )
        );

        HBox chartBody =
                new HBox(
                        30,
                        donutContainer,
                        legend
                );

        chartBody.setAlignment(
                Pos.CENTER_LEFT
        );

        chartBox.getChildren().addAll(
                chartTitle,
                chartBody
        );

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        VBox actionsBox =
                new VBox(14);

        actionsBox.setPrefWidth(
                310
        );

        actionsBox.setPadding(
                new Insets(18)
        );

        actionsBox.setStyle(
                "-fx-background-color: " +
                COLOR_WHITE + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        Label actTitle =
                new Label(
                        "⚡ Quick Actions"
                );

        actTitle.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16
                )
        );

        actTitle.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );
        VBox btn1 =
        createActionButton(
                "Add Beneficiary",
                COLOR_LIGHT_PINK
        );

VBox btn2 =
        createActionButton(
                "Send Message",
                "#FFF9FC"
        );

// =====================================================
// ADD BENEFICIARY CLICK
// =====================================================

btn1.setOnMouseClicked(e -> {
    openBeneficiaries();
});

// =====================================================
// SEND MESSAGE CLICK
// =====================================================

btn2.setOnMouseClicked(e -> {
    showSendMessageDialog();
});

       /*  VBox btn1 =
                createActionButton(
                        "Add Beneficiary",
                        COLOR_LIGHT_PINK
                );

        VBox btn2 =
                createActionButton(
                        "Send Message",
                        "#E0E7FF"
                );*/

        
         // Existing UI remains same.
        // *
         // No navigation change is made here.
         

        actionsBox.getChildren().addAll(
                actTitle,
                btn1,
                btn2
        );

        row.getChildren().addAll(
                chartBox,
                actionsBox
        );

        return row;
    }

    // =========================================================
    // DONUT
    // =========================================================

    private StackPane createSegmentedDonutChart() {

        StackPane donutView =
                new StackPane();

        double size = 140;

        Canvas canvas =
                new Canvas(
                        size,
                        size
                );

        GraphicsContext gc =
                canvas.getGraphicsContext2D();

        gc.setLineWidth(16);
        gc.setStroke(Color.web("#E7DCE8"));
        gc.strokeArc(12, 12,size - 24, size -24, 0, 360, javafx.scene.shape.ArcType.OPEN);

        gc.setLineCap(
                StrokeLineCap.ROUND
        );
        int total =
            womenTestedThisMonth +
            womenTestNormal +
            womenTestRisky +
            womenCheckupBaaki;

    // =====================================================
    // AVOID DIVIDE BY ZERO
    // =====================================================

    if (total > 0) {

        double testedAngle =
                (womenTestedThisMonth * 360.0) / total;

        double normalAngle =
                (womenTestNormal * 360.0) / total;

        double riskyAngle =
                (womenTestRisky * 360.0) / total;

        double pendingAngle =
                (womenCheckupBaaki * 360.0) / total;
              double startAngle = 0;

    // Women Tested
    if (womenTestedThisMonth > 0) {

        drawArc(
                gc,
                startAngle,
                testedAngle,
                COLOR_PRIMARY_PINK,
                size
        );

        startAngle += testedAngle;
    }

    // Women Normal
    if (womenTestNormal > 0) {

        drawArc(
                gc,
                startAngle,
                normalAngle,
                COLOR_PURPLE,
                size
        );

        startAngle += normalAngle;
    }

    // Women Risky
    if (womenTestRisky > 0) {

        drawArc(
                gc,
                startAngle,
                riskyAngle,
                 COLOR_GOLD,
                size
        );

        startAngle += riskyAngle;
    }

    // Women Checkup Pending
    if (womenCheckupBaaki > 0) {
        drawArc(gc,
                startAngle,
                pendingAngle,



                 COLOR_BLUE,
                size
        );
    }
}  

       
        
               
        

        VBox centerTxt =
                new VBox(-2);

        centerTxt.setAlignment(
                Pos.CENTER
        );

       

        Label num =
                new Label(
                        String.valueOf(
                                total
                        )
                );

        num.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24
                )
        );

        num.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label lbl =
                new Label(
                        "Total Activities"
                );

        lbl.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        9.5
                )
        );

        lbl.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_MUTED + ";"
        );

        centerTxt.getChildren().addAll(
                num,
                lbl
        );

        donutView.getChildren().addAll(
                canvas,
                centerTxt
        );

        
    
    return donutView;
}


    // =========================================================
    // DRAW ARC
    // =========================================================

    private void drawArc(
            GraphicsContext gc,
            double startAngle,
            double arcExtent,
            String colorHex,
            double size
    ) {

        gc.setStroke(
                Color.web(colorHex)
        );

        gc.strokeArc(
                12,
                12,
                size - 24,
                size - 24,
                startAngle,
                arcExtent,
                javafx.scene.shape.ArcType.OPEN
        );
    }

    // =========================================================
    // LEGEND
    // =========================================================

    private HBox createLegendRow(
            String colorHex,
            String text,
            String val
    ) {

        HBox h =
                new HBox(12);

        h.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle c =
                new Circle(
                        7,
                        Color.web(colorHex)
                );

        Label t =
                new Label(text);

        t.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        t.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_MUTED + ";"
        );

        Label v =
                new Label(val);

        v.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        v.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        h.getChildren().addAll(
                c,
                t,
                new Spacer(),
                v
        );

        return h;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private VBox createActionButton(
            String label,
            String bgColor
    ) {

        VBox box =
                new VBox();

        box.setPadding(
                new Insets(14)
        );

        box.setStyle(
                "-fx-background-color: #F3F4F6;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-cursor: hand;"
        );

        HBox inner =
                new HBox(14);

        inner.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle c =
                new Circle(
                        16,
                        Color.web(bgColor)
                );

        Label l =
                new Label(label);

        l.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        l.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label arrow =
                new Label(">");

        arrow.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        arrow.setStyle(
                "-fx-text-fill: " +
                COLOR_PRIMARY_PINK + ";"
        );

        inner.getChildren().addAll(
                c,
                l,
                new Spacer(),
                arrow
        );

        box.getChildren().add(
                inner
        );

        return box;
    }

    // =========================================================
    // BOTTOM ROW
    // =========================================================

    private HBox createBottomRow() {

        HBox row =
                new HBox(16);

        VBox actCard =
                createListContainer(
                        "Recent Activities"
                );

        VBox actList =
                (VBox) actCard.getChildren().get(2);

        // =====================================================
        // FIREBASE VISITS
        // =====================================================

     /* if (firebaseVisits != null &&
        !firebaseVisits.isEmpty()) {

    int count = 0;

    for (AshaWorkerVisitModel visit : firebaseVisits) {

        if (visit == null) {
            continue;
        }

        if (count >= 3) {
            break;
        }

        String visitName = "Health visit completed";
        String visitDate = "Firebase";

        try {
            if (visit.getName() != null &&
                    !visit.getName().trim().isEmpty()) {

                visitName = "Visit: " + visit.getName();
            }
        } catch (Exception ignored) {
        }

        try {
            if (visit.getDate() != null) {
                visitDate = String.valueOf(visit.getDate());
            }
        } catch (Exception ignored) {
        }

        actList.getChildren().add(
                createListItem(
                        visitName,
                        visitDate,
                        COLOR_TEXT_MUTED
                )
        );

        count++;
    }
}*/
if (firebaseVisits != null &&
        !firebaseVisits.isEmpty()) {

    int count = 0;

    LocalDate today = LocalDate.now();

    for (AshaWorkerVisitModel visit : firebaseVisits) {

        if (visit == null) {
            continue;
        }

        if (count >= 3) {
            break;
        }

        String visitName = "Health visit completed";
        String visitDateText = "";

        // -----------------------------------------
        // NAME
        // -----------------------------------------

        try {

            if (visit.getName() != null &&
                    !visit.getName().trim().isEmpty()) {

                visitName =
                        "Visit: " +
                        visit.getName().trim();
            }

        } catch (Exception ignored) {
        }

        // -----------------------------------------
        // DATE
        // -----------------------------------------

        try {

            if (visit.getDate() != null) {

                visitDateText =
                        visit.getDate().trim();
            }

        } catch (Exception ignored) {
        }

        // -----------------------------------------
        // PARSE DATE
        // -----------------------------------------

        LocalDate visitDate =
                parseDate(visitDateText);

        System.out.println(
                "Dashboard Visit: " +
                visitName +
                " | Date: " +
                visitDateText +
                " | Parsed: " +
                visitDate
        );

        // -----------------------------------------
        // RECENT ACTIVITY
        // -----------------------------------------

        if (visitDate != null &&
                !visitDate.isAfter(today)) {

            actList.getChildren().add(
                    createListItem(
                            visitName,
                            visitDateText,
                            COLOR_TEXT_MUTED
                    )
            );

            count++;
        }
    }
}
        // =====================================================
        // UPCOMING REMINDERS
        // =====================================================

        VBox remCard =
                createListContainer(
                        "Upcoming Reminders"
                );

        VBox remList =
                (VBox) remCard.getChildren().get(2);

        // =====================================================
// DYNAMIC UPCOMING REMINDERS
// =====================================================

if (firebaseBeneficiaries != null &&
        !firebaseBeneficiaries.isEmpty()) {

    int reminderCount = 0;

    for (AshaBeneficiary beneficiary :
            firebaseBeneficiaries) {

        if (beneficiary == null) {
            continue;
        }

        if (reminderCount >= 3) {
            break;
        }

        String status =
                beneficiary.getStatus() == null
                        ? ""
                        : beneficiary.getStatus()
                                .trim()
                                .toLowerCase();

        String name =
                beneficiary.getName() == null
                        ? "Beneficiary"
                        : beneficiary.getName().trim();

        // =================================================
        // HIGH RISK REMINDER
        // =================================================

        if (status.equals("high risk")) {

            remList.getChildren().add(
                    createListItem(
                            "High Risk: " + name,
                            "Checkup required",
                            COLOR_PRIMARY_PINK
                    )
            );

            reminderCount++;
        }

        // =================================================
        // FOLLOW-UP DUE
        // =================================================

        else if (status.equals("follow-up due") ||
                status.equals("follow up due")) {

            remList.getChildren().add(
                    createListItem(
                            "Follow-up due: " + name,
                            "Visit required",
                            COLOR_PRIMARY_PINK
                    )
            );

            reminderCount++;
        }

        // =================================================
        // PENDING
        // =================================================

        else if (status.equals("pending")) {

            remList.getChildren().add(
                    createListItem(
                            "Checkup pending: " + name,
                            "Action required",
                            COLOR_PRIMARY_PINK
                    )
            );

            reminderCount++;
        }
    }
}

// =====================================================
// NO REMINDERS
// =====================================================

if (remList.getChildren().isEmpty()) {

    remList.getChildren().add(
            createListItem(
                    "No upcoming reminders",
                    "All up to date",
                    COLOR_GREEN
            )
    );
}

        HBox.setHgrow(
                actCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                remCard,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                actCard,
                remCard
        );

        return row;
    }

    // =========================================================
    // LIST CONTAINER
    // =========================================================

    private VBox createListContainer(
            String title
    ) {

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(18)
        );

        box.setStyle(
                "-fx-background-color: " +
                COLOR_WHITE + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                COLOR_BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox header =
                new HBox();

        Label t =
                new Label(title);

        t.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        t.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label viewAll =
                new Label(
                        "View All >"
                );

        viewAll.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        viewAll.setStyle(
                "-fx-text-fill: " +
                COLOR_PRIMARY_PINK +
                "; -fx-cursor: hand;"
        );
        viewAll.setOnMouseClicked(e -> {

        if (title.equals("Recent Activities") ||
                title.equals("Upcoming Reminders")) {

            openHealthVisit();
        }
    });

    header.getChildren().addAll(
            t,
            new Spacer(),
            viewAll

        
        );

        Line line =
                new Line(
                        0,
                        0,
                        420,
                        0
                );

        line.setStroke(
                Color.web(COLOR_BORDER)
        );

        VBox list =
                new VBox(10);

        box.getChildren().addAll(
                header,
                line,
                list
        );

        return box;
    }

    // =========================================================
    // LIST ITEM
    // =========================================================

    private HBox createListItem(
            String desc,
            String time,
            String timeColor
    ) {

        HBox item =
                new HBox(12);

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle dot =
                new Circle(
                        4.5,
                        Color.web(
                                COLOR_PRIMARY_PINK
                        )
                );

        Label d =
                new Label(desc);

        d.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12.5
                )
        );

        d.setStyle(
                "-fx-text-fill: " +
                COLOR_TEXT_DARK + ";"
        );

        Label tm =
                new Label(time);

        tm.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12
                )
        );

        tm.setStyle(
                "-fx-text-fill: " +
                timeColor + ";"
        );

        item.getChildren().addAll(
                dot,
                d,
                new Spacer(),
                tm
        );

        return item;
    }

    // =========================================================
    // IMAGE HOLDER
    // =========================================================

    private ImageView createImageViewHolder(
            String imagePath,
            double width,
            double height
    ) {

        ImageView imgView =
                new ImageView();

        try {

            Image img =
                    new Image(
                            imagePath,
                            true
                    );

            imgView.setImage(img);

        } catch (Exception e) {

            System.out.println(
                    "Image not found: " +
                    imagePath
            );
        }

        imgView.setFitWidth(
                width
        );

        imgView.setFitHeight(
                height
        );

        imgView.setPreserveRatio(
                true
        );

        return imgView;
    }

    // =========================================================
    // SPACER
    // =========================================================

    private static class Spacer
            extends Pane {

        public Spacer() {

            HBox.setHgrow(
                    this,
                    Priority.ALWAYS
            );

            VBox.setVgrow(
                    this,
                    Priority.ALWAYS
            );
        }
    }

    // =========================================================
    // SHOW
    // =========================================================

    public void show(Stage stage) {

        Scene dashboardScene =
                createDashboardScene();

        stage.setScene(
                dashboardScene
        );

        stage.setMaximized(true);

        stage.show();
    }
}