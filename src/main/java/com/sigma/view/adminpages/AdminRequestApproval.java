package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminRequestApproval {

    private BorderPane root;

    private VBox requestList;
    private Label pendingCount;
    private Label footer;

    private int pendingRequests = 4;

    // =========================================================
    // COLORS
    // =========================================================

    private static final String PURPLE = "#713CC3";
    private static final String DARK = "#24234F";
    private static final String GREY = "#77778D";
    private static final String BORDER = "#E7DCF8";
    private static final String BACKGROUND = "#FAF8FD";

    // =========================================================
    // MAIN ROOT
    // =========================================================

    public BorderPane getAdminrequestApprovalRoot() {

        root = new BorderPane();

        root.setStyle(
            "-fx-background-color: " + BACKGROUND + ";"
        );

        VBox content = createContent();

        ScrollPane scrollPane =
            new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: " + BACKGROUND + ";"
        );

        root.setCenter(scrollPane);

        return root;
    }

    // =========================================================
    // CONTENT
    // =========================================================

    private VBox createContent() {

        VBox content =
            new VBox(20);

        content.setPadding(
            new Insets(
                30,
                40,
                40,
                40
            )
        );

        // =====================================================
        // HEADER
        // =====================================================

        Label title =
            new Label(
                "Approval Requests"
            );

        title.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                30
            )
        );

        title.setTextFill(
            Color.web(DARK)
        );

        Label subtitle =
            new Label(
                "Review and approve healthcare providers waiting for verification."
            );

        subtitle.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 14px;"
        );

        VBox header =
            new VBox(
                6,
                title,
                subtitle
            );

        // =====================================================
        // PENDING HEADER
        // =====================================================

        HBox pendingHeader =
            new HBox();

        pendingHeader.setPadding(
            new Insets(
                18
            )
        );

        pendingHeader.setAlignment(
            Pos.CENTER_LEFT
        );

        pendingHeader.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px 15px 0 0;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 15px 15px 0 0;"
        );

        Label pendingTitle =
            new Label(
                "Pending Approval Requests"
            );

        pendingTitle.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                18
            )
        );

        pendingTitle.setTextFill(
            Color.web(DARK)
        );

        pendingCount =
            new Label();

        updatePendingCount();

        pendingCount.setStyle(
            "-fx-background-color: " + PURPLE + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-padding: 7px 13px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
        );

        HBox.setHgrow(
            pendingTitle,
            Priority.ALWAYS
        );

        pendingHeader.getChildren().addAll(
            pendingTitle,
            pendingCount
        );

        // =====================================================
        // REQUEST LIST
        // =====================================================

        requestList =
            new VBox(12);

        requestList.setPadding(
            new Insets(18)
        );

        requestList.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 0 0 15px 15px;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 0 0 15px 15px;"
        );

        // =====================================================
        // REQUESTS
        // =====================================================

        addRequest(
            "Dr. Rahul Sharma",
            "Doctor",
            "MBBS",
            "Medical Registration: DOC-1024",
            "4 Documents",
            "DR"
        );

        addRequest(
            "City Care Hospital",
            "Hospital",
            "Registered Healthcare Facility",
            "Hospital Registration: HOS-2041",
            "6 Documents",
            "CH"
        );

        addRequest(
            "Kavita Patil",
            "ASHA Worker",
            "12th Pass",
            "ASHA Worker ID: ASHA-3088",
            "3 Documents",
            "KP"
        );

        addRequest(
            "LifeCare Ambulance",
            "Ambulance Provider",
            "Emergency Medical Transport",
            "Provider Registration: AMB-4022",
            "5 Documents",
            "LA"
        );

        // =====================================================
        // CONTAINER
        // =====================================================

        VBox requestContainer =
            new VBox(
                pendingHeader,
                requestList
            );

        // =====================================================
        // FOOTER
        // =====================================================

        footer =
            new Label();

        updateFooter();

        footer.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 13px;"
        );

        HBox footerBox =
            new HBox(
                footer
            );

        footerBox.setAlignment(
            Pos.CENTER
        );

        // =====================================================
        // ADD
        // =====================================================

        content.getChildren().addAll(
            header,
            requestContainer,
            footerBox
        );

        return content;
    }

    // =========================================================
    // ADD REQUEST
    // =========================================================

    private void addRequest(
            String name,
            String type,
            String qualification,
            String registration,
            String documents,
            String initials) {

        VBox card =
            createRequestCard(
                name,
                type,
                qualification,
                registration,
                documents,
                initials
            );

        requestList.getChildren().add(
            card
        );
    }

    // =========================================================
    // REQUEST CARD
    // =========================================================

    private VBox createRequestCard(
            String name,
            String type,
            String qualification,
            String registration,
            String documents,
            String initials) {

        VBox card =
            new VBox(12);

        card.setPadding(
            new Insets(18)
        );

        card.setStyle(
            "-fx-background-color: #FCFAFF;" +
            "-fx-background-radius: 12px;" +
            "-fx-border-color: #E8E0F1;" +
            "-fx-border-radius: 12px;"
        );

        // =====================================================
        // TOP
        // =====================================================

        HBox top =
            new HBox(15);

        top.setAlignment(
            Pos.CENTER_LEFT
        );

        // Avatar
        Label avatar =
            new Label(
                initials
            );

        avatar.setMinSize(
            50,
            50
        );

        avatar.setMaxSize(
            50,
            50
        );

        avatar.setAlignment(
            Pos.CENTER
        );

        avatar.setStyle(
            "-fx-background-color: #EEE4FF;" +
            "-fx-background-radius: 25px;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        // Information
        VBox information =
            new VBox(4);

        Label nameLabel =
            new Label(
                name
            );

        nameLabel.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                16
            )
        );

        nameLabel.setTextFill(
            Color.web(DARK)
        );

        Label typeLabel =
            new Label(
                type
            );

        typeLabel.setStyle(
            "-fx-background-color: #F0E7FF;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 4px 8px;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );

        HBox typeBox =
            new HBox(
                typeLabel
            );

        Label qualificationLabel =
            new Label(
                qualification
            );

        qualificationLabel.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        information.getChildren().addAll(
            nameLabel,
            typeBox,
            qualificationLabel
        );

        HBox.setHgrow(
            information,
            Priority.ALWAYS
        );

        top.getChildren().addAll(
            avatar,
            information
        );

        // =====================================================
        // DETAILS
        // =====================================================

        Label registrationLabel =
            new Label(
                registration
            );

        registrationLabel.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        Label documentsLabel =
            new Label(
                "📄  " + documents
            );

        documentsLabel.setStyle(
            "-fx-text-fill: " + GREY + ";" +
            "-fx-font-size: 12px;"
        );

        HBox details =
            new HBox(20);

        details.getChildren().addAll(
            registrationLabel,
            documentsLabel
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
            new HBox(10);

        buttons.setAlignment(
            Pos.CENTER_RIGHT
        );

        Button viewButton =
            new Button(
                "View Details"
            );

        Button rejectButton =
            new Button(
                "Reject"
            );

        Button approveButton =
            new Button(
                "Approve"
            );

        styleViewButton(
            viewButton
        );

        styleRejectButton(
            rejectButton
        );

        styleApproveButton(
            approveButton
        );

        viewButton.setOnAction(e -> {

            System.out.println(
                "[ADMIN] Viewing request: "
                + name
            );

        });

        rejectButton.setOnAction(e -> {

            System.out.println(
                "[ADMIN] Rejecting request: "
                + name
            );

            removeRequest(
                card,
                name
            );
        });

        approveButton.setOnAction(e -> {

            System.out.println(
                "[ADMIN] Approving request: "
                + name
            );

            removeRequest(
                card,
                name
            );
        });

        buttons.getChildren().addAll(
            viewButton,
            rejectButton,
            approveButton
        );

        // =====================================================
        // ADD TO CARD
        // =====================================================

        card.getChildren().addAll(
            top,
            details,
            buttons
        );

        return card;
    }

    // =========================================================
    // REMOVE REQUEST
    // =========================================================

    private void removeRequest(
            VBox card,
            String name) {

        if (!requestList.getChildren().contains(card)) {
            return;
        }

        requestList.getChildren().remove(
            card
        );

        pendingRequests--;

        if (pendingRequests < 0) {
            pendingRequests = 0;
        }

        updatePendingCount();
        updateFooter();

        System.out.println(
            "[ADMIN] Request processed: "
            + name
        );
    }

    // =========================================================
    // PENDING COUNT
    // =========================================================

    private void updatePendingCount() {

        if (pendingCount == null) {
            return;
        }

        pendingCount.setText(
            pendingRequests + " Pending"
        );
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void updateFooter() {

        if (footer == null) {
            return;
        }

        int total = 4;

        int shown =
            pendingRequests;

        if (shown == 0) {

            footer.setText(
                "No pending approval requests"
            );

        } else {

            footer.setText(
                "Showing 1 to "
                + shown
                + " of "
                + total
                + " pending requests"
            );
        }
    }

    // =========================================================
    // VIEW BUTTON
    // =========================================================

    private void styleViewButton(
            Button button) {

        String normal =
            "-fx-background-color: white;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-border-color: " + PURPLE + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 15px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        String hover =
            "-fx-background-color: #F2EAFF;" +
            "-fx-text-fill: " + PURPLE + ";" +
            "-fx-border-color: " + PURPLE + ";" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 15px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        button.setStyle(
            normal
        );

        button.setOnMouseEntered(
            e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
            e -> button.setStyle(normal)
        );
    }

    // =========================================================
    // REJECT BUTTON
    // =========================================================

    private void styleRejectButton(
            Button button) {

        String normal =
            "-fx-background-color: white;" +
            "-fx-text-fill: #D93025;" +
            "-fx-border-color: #D93025;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 15px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        String hover =
            "-fx-background-color: #FFF0EF;" +
            "-fx-text-fill: #D93025;" +
            "-fx-border-color: #D93025;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 15px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        button.setStyle(
            normal
        );

        button.setOnMouseEntered(
            e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
            e -> button.setStyle(normal)
        );
    }

    // =========================================================
    // APPROVE BUTTON
    // =========================================================

    private void styleApproveButton(
            Button button) {

        String normal =
            "-fx-background-color: " + PURPLE + ";" +
            "-fx-text-fill: white;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 17px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        String hover =
            "-fx-background-color: #5E2FA5;" +
            "-fx-text-fill: white;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 8px 17px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;";

        button.setStyle(
            normal
        );

        button.setOnMouseEntered(
            e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
            e -> button.setStyle(normal)
        );
    }
}