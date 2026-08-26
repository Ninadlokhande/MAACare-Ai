package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.effect.DropShadow;

public class AdminProfile {

    // =========================================================
    // MAIN PROFILE ROOT
    // =========================================================

    public Node getProfileRoot() {

        VBox content = new VBox(25);

        content.setPadding(
            new Insets(30)
        );

        content.setStyle(
            "-fx-background-color: #F9F7FC;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        Label title =
            new Label("Admin Profile");

        title.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label subtitle =
            new Label(
                "Manage your administrator account and profile information."
            );

        subtitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #77778D;"
        );


        VBox header =
            new VBox(
                5,
                title,
                subtitle
            );


        // =====================================================
        // PROFILE CARD
        // =====================================================

        HBox profileCard =
            new HBox(25);

        profileCard.setAlignment(
            Pos.CENTER_LEFT
        );

        profileCard.setPadding(
            new Insets(25)
        );

        profileCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();

        shadow.setRadius(12);

        shadow.setOffsetY(4);

        shadow.setColor(
            Color.rgb(
                60,
                30,
                80,
                0.07
            )
        );

        profileCard.setEffect(
            shadow
        );


        // =====================================================
        // PROFILE AVATAR
        // =====================================================

        Circle avatar =
            new Circle(
                45
            );

        avatar.setFill(
            Color.web("#6C63A8")
        );


        Label avatarText =
            new Label("A");

        avatarText.setStyle(
            "-fx-text-fill: white;" +
            "-fx-font-size: 30px;" +
            "-fx-font-weight: bold;"
        );


        VBox avatarBox =
            new VBox(
                avatar,
                avatarText
            );

        avatarBox.setAlignment(
            Pos.CENTER
        );

        avatarBox.setPrefWidth(
            100
        );


        // =====================================================
        // PROFILE INFORMATION
        // =====================================================

        VBox profileInfo =
            new VBox(7);


        Label name =
            new Label(
                "MaaCare Administrator"
            );

        name.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label role =
            new Label(
                "System Administrator"
            );

        role.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #6C63A8;" +
            "-fx-font-weight: bold;"
        );


        Label email =
            new Label(
                "admin@maacareai.com"
            );

        email.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        Label status =
            new Label(
                "●  Active"
            );

        status.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #20A56A;" +
            "-fx-font-weight: bold;"
        );


        profileInfo.getChildren().addAll(

            name,

            role,

            email,

            status
        );


        HBox.setHgrow(
            profileInfo,
            Priority.ALWAYS
        );


        profileCard.getChildren().addAll(

            avatarBox,

            profileInfo
        );


        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        VBox personalCard =
            createCard(
                "Personal Information"
            );


        TextField fullName =
            createField(
                "Full Name",
                "MaaCare Administrator"
            );


        TextField emailField =
            createField(
                "Email Address",
                "admin@maacareai.com"
            );


        TextField phoneField =
            createField(
                "Phone Number",
                "+91 98765 43210"
            );


        TextField roleField =
            createField(
                "Role",
                "System Administrator"
            );

        roleField.setEditable(
            false
        );


        VBox nameBox =
            createFieldBox(
                "Full Name",
                fullName
            );


        VBox emailBox =
            createFieldBox(
                "Email Address",
                emailField
            );


        VBox phoneBox =
            createFieldBox(
                "Phone Number",
                phoneField
            );


        VBox roleBox =
            createFieldBox(
                "Role",
                roleField
            );


        HBox row1 =
            new HBox(20);

        row1.getChildren().addAll(

            nameBox,

            emailBox
        );


        HBox row2 =
            new HBox(20);

        row2.getChildren().addAll(

            phoneBox,

            roleBox
        );


        HBox.setHgrow(
            nameBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            emailBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            phoneBox,
            Priority.ALWAYS
        );

        HBox.setHgrow(
            roleBox,
            Priority.ALWAYS
        );


        personalCard.getChildren().addAll(

            row1,

            row2
        );


        // =====================================================
        // ACCOUNT INFORMATION
        // =====================================================

        VBox accountCard =
            createCard(
                "Account Information"
            );


        accountCard.getChildren().addAll(

            createInfoRow(
                "Account ID",
                "ADMIN-001"
            ),

            new Separator(),

            createInfoRow(
                "Account Type",
                "Administrator"
            ),

            new Separator(),

            createInfoRow(
                "Joined",
                "January 2026"
            ),

            new Separator(),

            createInfoRow(
                "Last Login",
                "Today, 09:42 AM"
            ),

            new Separator(),

            createInfoRow(
                "Account Status",
                "Active"
            )
        );


        // =====================================================
        // SECURITY
        // =====================================================

        VBox securityCard =
            createCard(
                "Security"
            );


        Label securityTitle =
            new Label(
                "Password & Account Security"
            );

        securityTitle.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        Label securityText =
            new Label(
                "Keep your administrator account secure by regularly updating your password."
            );

        securityText.setWrapText(
            true
        );

        securityText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        Button changePassword =
            new Button(
                "Change Password"
            );

        changePassword.setStyle(
            "-fx-background-color: #6C63A8;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 10px 18px;" +
            "-fx-cursor: hand;"
        );


        changePassword.setOnAction(e -> {

            System.out.println(
                "Change password clicked"
            );

            // Connect your password-change page here.
        });


        securityCard.getChildren().addAll(

            securityTitle,

            securityText,

            changePassword
        );


        // =====================================================
        // SAVE BUTTON
        // =====================================================

        HBox buttons =
            new HBox(12);

        buttons.setAlignment(
            Pos.CENTER_RIGHT
        );


        Button cancel =
            new Button(
                "Cancel"
            );

        cancel.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #55556A;" +
            "-fx-border-color: #D8D4E2;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;"
        );


        Button save =
            new Button(
                "Save Changes"
            );

        save.setStyle(
            "-fx-background-color: #6C63A8;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 10px 22px;" +
            "-fx-cursor: hand;"
        );


        save.setOnAction(e -> {

            name.setText(
                fullName.getText()
            );

            email.setText(
                emailField.getText()
            );

            System.out.println(
                "Admin profile updated"
            );
        });


        cancel.setOnAction(e -> {

            fullName.setText(
                "MaaCare Administrator"
            );

            emailField.setText(
                "admin@maacareai.com"
            );

            phoneField.setText(
                "+91 98765 43210"
            );
        });


        buttons.getChildren().addAll(

            cancel,

            save
        );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        content.getChildren().addAll(

            header,

            profileCard,

            personalCard,

            accountCard,

            securityCard,

            buttons
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
            new ScrollPane(
                content
            );

        scrollPane.setFitToWidth(
            true
        );

        scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: #F9F7FC;"
        );


        return scrollPane;
    }


    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard(
            String title) {

        VBox card =
            new VBox(18);

        card.setPadding(
            new Insets(22)
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #E7E1EF;" +
            "-fx-border-radius: 15px;"
        );


        DropShadow shadow =
            new DropShadow();

        shadow.setRadius(10);

        shadow.setOffsetY(3);

        shadow.setColor(
            Color.rgb(
                60,
                30,
                80,
                0.05
            )
        );


        card.setEffect(
            shadow
        );


        Label heading =
            new Label(title);

        heading.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        card.getChildren().add(
            heading
        );


        return card;
    }


    // =========================================================
    // FIELD
    // =========================================================

    private TextField createField(
            String label,
            String value) {

        TextField field =
            new TextField(value);

        field.setPrefHeight(
            42
        );

        field.setStyle(
            "-fx-background-color: #F8F7FB;" +
            "-fx-border-color: #E1DDE9;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 0 12px;" +
            "-fx-font-size: 13px;"
        );


        return field;
    }


    // =========================================================
    // FIELD BOX
    // =========================================================

    private VBox createFieldBox(
            String label,
            TextField field) {

        VBox box =
            new VBox(7);


        Label labelText =
            new Label(label);

        labelText.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #55556A;"
        );


        box.getChildren().addAll(

            labelText,

            field
        );


        HBox.setHgrow(
            box,
            Priority.ALWAYS
        );


        return box;
    }


    // =========================================================
    // INFO ROW
    // =========================================================

    private HBox createInfoRow(
            String label,
            String value) {

        Label labelText =
            new Label(label);

        labelText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #77778D;"
        );


        Label valueText =
            new Label(value);

        valueText.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #24234F;"
        );


        HBox row =
            new HBox(
                labelText,
                valueText
            );

        row.setAlignment(
            Pos.CENTER_LEFT
        );


        HBox.setHgrow(
            labelText,
            Priority.ALWAYS
        );


        return row;
    }
}