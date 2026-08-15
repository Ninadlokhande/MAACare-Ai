package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.sigma.view.scenesettings;

public class AdminRequestApproval {

    private Scene adminapprovalScene;


    public Scene getAdminrequestApprovalScene() {

        // =====================================================
        // MAIN ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #FAF8FD;"
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(20);

        content.setPadding(
            new Insets(30, 45, 40, 45)
        );


        // =====================================================
        // BACK TO DASHBOARD BUTTON
        // =====================================================

        Button backButton =
            new Button(
                "←   Back to Dashboard"
            );

        backButton.setPrefHeight(42);

        backButton.setPadding(
            new Insets(0, 20, 0, 20)
        );

        backButton.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #713CC3;" +
            "-fx-border-color: #713CC3;" +
            "-fx-border-width: 1.5px;" +
            "-fx-border-radius: 10px;" +
            "-fx-background-radius: 10px;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );


        backButton.setOnMouseEntered(e -> {

            backButton.setStyle(
                "-fx-background-color: #713CC3;" +
                "-fx-text-fill: white;" +
                "-fx-border-color: #713CC3;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            );
        });


        backButton.setOnMouseExited(e -> {

            backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #713CC3;" +
                "-fx-border-color: #713CC3;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            );
        });


        backButton.setOnAction(e -> {

            System.out.println(
                "[ADMIN] Back to Dashboard clicked"
            );

            // Add AdminDashboard navigation here later
        });


        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label title =
            new Label(
                "Approval Requests"
            );

        title.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                32
            )
        );

        title.setTextFill(
            Color.web("#24234F")
        );


        Label subtitle =
            new Label(
                "Review and verify healthcare provider registration requests."
            );

        subtitle.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 15px;"
        );


        VBox titleBox =
            new VBox(
                5,
                title,
                subtitle
            );


        // =====================================================
        // PENDING HEADER
        // =====================================================

        HBox pendingHeader =
            new HBox();

        pendingHeader.setAlignment(
            Pos.CENTER_LEFT
        );

        pendingHeader.setPadding(
            new Insets(18, 22, 18, 22)
        );

        pendingHeader.setStyle(
            "-fx-background-color: #F3ECFF;" +
            "-fx-background-radius: 15px 15px 0 0;" +
            "-fx-border-color: #E7DCF8;" +
            "-fx-border-radius: 15px 15px 0 0;"
        );


        Label pendingIcon =
            new Label("◷");

        pendingIcon.setStyle(
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;"
        );


        Label pendingTitle =
            new Label(
                "Pending Verification Requests"
            );

        pendingTitle.setStyle(
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );


        HBox pendingLeft =
            new HBox(
                12,
                pendingIcon,
                pendingTitle
            );

        pendingLeft.setAlignment(
            Pos.CENTER_LEFT
        );


        Label pendingCount =
            new Label(
                "4 Pending"
            );

        pendingCount.setStyle(
            "-fx-background-color: #713CC3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10px;" +
            "-fx-padding: 7px 13px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
        );


        HBox.setHgrow(
            pendingLeft,
            Priority.ALWAYS
        );


        pendingHeader.getChildren().addAll(
            pendingLeft,
            pendingCount
        );


        // =====================================================
        // REQUEST LIST
        // =====================================================

        VBox requestList =
            new VBox(12);

        requestList.setPadding(
            new Insets(18)
        );

        requestList.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 0 0 15px 15px;" +
            "-fx-border-color: #E7DCF8;" +
            "-fx-border-radius: 0 0 15px 15px;"
        );


        // =====================================================
        // DUMMY REQUEST 1 - DOCTOR
        // =====================================================

        VBox doctorCard =
            createRequestCard(
                "Dr. Rahul Sharma",
                "Doctor",
                "MBBS",
                "Medical Registration: DOC-1024",
                "4 Documents",
                "DR"
            );


        // =====================================================
        // DUMMY REQUEST 2 - HOSPITAL
        // =====================================================

        VBox hospitalCard =
            createRequestCard(
                "City Care Hospital",
                "Hospital",
                "Registered Healthcare Facility",
                "Hospital Registration: HOS-2041",
                "6 Documents",
                "CH"
            );


        // =====================================================
        // DUMMY REQUEST 3 - ASHA WORKER
        // =====================================================

        VBox ashaCard =
            createRequestCard(
                "Kavita Patil",
                "ASHA Worker",
                "12th Pass",
                "ASHA Worker ID: ASHA-3088",
                "3 Documents",
                "KP"
            );


        // =====================================================
        // DUMMY REQUEST 4 - AMBULANCE
        // =====================================================

        VBox ambulanceCard =
            createRequestCard(
                "LifeCare Ambulance",
                "Ambulance Provider",
                "Emergency Medical Transport",
                "Provider Registration: AMB-4022",
                "5 Documents",
                "LA"
            );


        requestList.getChildren().addAll(
            doctorCard,
            hospitalCard,
            ashaCard,
            ambulanceCard
        );


        // =====================================================
        // REQUEST CONTAINER
        // =====================================================

        VBox requestContainer =
            new VBox(
                pendingHeader,
                requestList
            );


        // =====================================================
        // FOOTER
        // =====================================================

        Label footer =
            new Label(
                "Showing 1 to 4 of 4 pending requests"
            );

        footer.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 13px;"
        );

        footer.setAlignment(
            Pos.CENTER
        );


        HBox footerBox =
            new HBox(footer);

        footerBox.setAlignment(
            Pos.CENTER
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
            backButton,
            titleBox,
            requestContainer,
            footerBox
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(
                content
            );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #FAF8FD;"
        );


        // =====================================================
        // ROOT
        // =====================================================

        root.setCenter(
            scrollPane
        );


        // =====================================================
        // SCENE
        // =====================================================

        adminapprovalScene =
            new Scene(
                root,
                scenesettings.rectanguler2d.getWidth(),
                scenesettings.rectanguler2d.getHeight()
            );


        return adminapprovalScene;
    }


    // =========================================================
    // CREATE REQUEST CARD
    // =========================================================

    private VBox createRequestCard(
            String name,
            String role,
            String qualification,
            String registration,
            String documents,
            String initials) {


        VBox card =
            new VBox();

        card.setSpacing(12);

        card.setPadding(
            new Insets(20)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14px;" +
            "-fx-border-color: #E8E2EF;" +
            "-fx-border-radius: 14px;"
        );


        // =====================================================
        // TOP SECTION
        // =====================================================

        HBox mainRow =
            new HBox(20);

        mainRow.setAlignment(
            Pos.CENTER_LEFT
        );


        // =====================================================
        // PROFILE CIRCLE
        // =====================================================

        Circle profileCircle =
            new Circle(
                42
            );

        profileCircle.setFill(
            Color.web("#F1E8FF")
        );


        Label initialsLabel =
            new Label(
                initials
            );

        initialsLabel.setStyle(
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );


        VBox profile =
            new VBox(
                initialsLabel
            );

        profile.setAlignment(
            Pos.CENTER
        );

        profile.setMinWidth(84);


        // =====================================================
        // NAME + ROLE
        // =====================================================

        Label nameLabel =
            new Label(
                name
            );

        nameLabel.setStyle(
            "-fx-text-fill: #24234F;" +
            "-fx-font-size: 19px;" +
            "-fx-font-weight: bold;"
        );


        Label roleLabel =
            new Label(
                "●  " + role
            );

        roleLabel.setStyle(
            "-fx-background-color: #F1E8FF;" +
            "-fx-text-fill: #713CC3;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 5px 10px;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
        );


        Label qualificationLabel =
            new Label(
                "🎓  " + qualification
            );

        qualificationLabel.setStyle(
            "-fx-text-fill: #5F6078;" +
            "-fx-font-size: 13px;"
        );


        Label registrationLabel =
            new Label(
                "▣  " + registration
            );

        registrationLabel.setStyle(
            "-fx-text-fill: #5F6078;" +
            "-fx-font-size: 13px;"
        );


        VBox information =
            new VBox(
                7,
                nameLabel,
                roleLabel,
                qualificationLabel,
                registrationLabel
            );


        // =====================================================
        // DOCUMENT COUNT
        // =====================================================

        VBox documentBox =
            new VBox(5);

        documentBox.setAlignment(
            Pos.CENTER
        );

        documentBox.setPadding(
            new Insets(10, 25, 10, 25)
        );

        documentBox.setStyle(
            "-fx-background-color: #FAF7FF;" +
            "-fx-background-radius: 12px;"
        );


        Label documentIcon =
            new Label("▤");

        documentIcon.setStyle(
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 25px;"
        );


        Label documentLabel =
            new Label(
                documents
            );

        documentLabel.setStyle(
            "-fx-text-fill: #713CC3;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );


        Label submitted =
            new Label(
                "Submitted"
            );

        submitted.setStyle(
            "-fx-text-fill: #77778D;" +
            "-fx-font-size: 11px;"
        );


        documentBox.getChildren().addAll(
            documentIcon,
            documentLabel,
            submitted
        );


        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        Button seeDocuments =
            new Button(
                "◉  See Documents"
            );

        styleSeeDocumentsButton(
            seeDocuments
        );


        Button approve =
            new Button(
                "✓  Approve"
            );

        styleApproveButton(
            approve
        );


        Button reject =
            new Button(
                "✕  Reject"
            );

        styleRejectButton(
            reject
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        seeDocuments.setOnAction(e -> {

            System.out.println(
                "[ADMIN] See Documents clicked for: "
                + name
            );

            System.out.println(
                "[ADMIN] Role: " + role
            );

            System.out.println(
                "[ADMIN] Documents: " + documents
            );

            // Add document page navigation here later
        });


        approve.setOnAction(e -> {

            System.out.println(
                "[ADMIN] APPROVE clicked for: "
                + name
            );

            System.out.println(
                "[ADMIN] Role: " + role
            );

            System.out.println(
                "[ADMIN] Verification request approved."
            );

            // Add database update here later
        });


        reject.setOnAction(e -> {

            System.out.println(
                "[ADMIN] REJECT clicked for: "
                + name
            );

            System.out.println(
                "[ADMIN] Role: " + role
            );

            System.out.println(
                "[ADMIN] Verification request rejected."
            );

            // Add database update here later
        });


        HBox buttons =
            new HBox(
                10,
                seeDocuments,
                approve,
                reject
            );

        buttons.setAlignment(
            Pos.CENTER_RIGHT
        );


        // =====================================================
        // RESPONSIVE SPACING
        // =====================================================

        HBox.setHgrow(
            information,
            Priority.ALWAYS
        );


        mainRow.getChildren().addAll(
            profile,
            information,
            documentBox,
            buttons
        );


        card.getChildren().add(
            mainRow
        );


        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(e -> {

            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #C9A8F5;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 14px;" +
                "-fx-effect: dropshadow(gaussian, rgba(113,60,195,0.12), 12, 0, 0, 4);"
            );
        });


        card.setOnMouseExited(e -> {

            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #E8E2EF;" +
                "-fx-border-radius: 14px;"
            );
        });


        return card;
    }


    // =========================================================
    // SEE DOCUMENTS BUTTON STYLE
    // =========================================================

    private void styleSeeDocumentsButton(
            Button button) {

        String normalStyle =
            "-fx-background-color: white;" +
            "-fx-text-fill: #713CC3;" +
            "-fx-border-color: #713CC3;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        String hoverStyle =
            "-fx-background-color: #713CC3;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: #713CC3;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        button.setStyle(
            normalStyle
        );


        button.setOnMouseEntered(e ->
            button.setStyle(
                hoverStyle
            )
        );


        button.setOnMouseExited(e ->
            button.setStyle(
                normalStyle
            )
        );
    }


    // =========================================================
    // APPROVE BUTTON STYLE
    // =========================================================

    private void styleApproveButton(
            Button button) {

        String normalStyle =
            "-fx-background-color: white;" +
            "-fx-text-fill: #159447;" +
            "-fx-border-color: #38B66D;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        String hoverStyle =
            "-fx-background-color: #159447;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: #159447;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        button.setStyle(
            normalStyle
        );


        button.setOnMouseEntered(e ->
            button.setStyle(
                hoverStyle
            )
        );


        button.setOnMouseExited(e ->
            button.setStyle(
                normalStyle
            )
        );
    }


    // =========================================================
    // REJECT BUTTON STYLE
    // =========================================================

    private void styleRejectButton(
            Button button) {

        String normalStyle =
            "-fx-background-color: white;" +
            "-fx-text-fill: #E53935;" +
            "-fx-border-color: #EF5350;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        String hoverStyle =
            "-fx-background-color: #E53935;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: #E53935;" +
            "-fx-border-width: 1.2px;" +
            "-fx-border-radius: 9px;" +
            "-fx-background-radius: 9px;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 9px 15px;";


        button.setStyle(
            normalStyle
        );


        button.setOnMouseEntered(e ->
            button.setStyle(
                hoverStyle
            )
        );


        button.setOnMouseExited(e ->
            button.setStyle(
                normalStyle
            )
        );
    }
}