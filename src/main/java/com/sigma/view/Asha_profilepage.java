package com.sigma.view;

import com.sigma.model.AshaProfileModel;
import com.sigma.controller.Ashaprofilecontroller;
import com.sigma.Cloudinary.CloudinaryService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import com.sigma.SessionManager;
import javafx.stage.FileChooser;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;


import java.io.File;


/**
 * ASHA WORKER PROFILE PAGE
 *
 * Dashboard navigation remains:
 *
 * Asha_profilepage profile = new Asha_profilepage();
 * Node content = profile.getProfileContent();
 *
 * ASHA ID is entered from this page only.
 */
public class Asha_profilepage {

    private AshaProfileModel profile;
    private Ashaprofilecontroller controller;
    private CloudinaryService cloudinaryService;

    private VBox rootBox;

    /*
     * ASHA ID is kept only for current profile-page session.
     *
     * It is NOT taken from Dashboard.
     */
    private String sessionAshaId = null;

    private boolean ashaIdFixed = false;


    // =========================================================
    // SVG ICONS
    // =========================================================

    private static final String USER_ICON_SVG =
            "M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z";

    private static final String PHONE_ICON_SVG =
            "M6.62 10.79c1.44 2.83 3.76 5.14 6.59 6.59l2.2-2.2c.27-.27.67-.36 1.02-.24 1.12.37 2.33.57 3.57.57.55 0 1 .45 1 1V20c0 .55-.45 1-1 1-9.39 0-17-7.61-17-17 0-.55.45-1 1-1h3.5c.55 0 1 .45 1 1 0 1.25.2 2.45.57 3.57.11.35.03.74-.25 1.02l-2.2 2.2z";

    private static final String MAIL_ICON_SVG =
            "M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z";

    private static final String LOCATION_ICON_SVG =
            "M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z";

    private static final String CALENDAR_ICON_SVG =
            "M19 3h-1V1h-2v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.11.89 2 2 2h14c1.11 0 2-.89 2-2V5c0-1.1-.89-2-2-2zm0 16H5V8h14v11z";

    private static final String LOCK_ICON_SVG =
            "M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 3.1 3.1 3.1v2z";

    private static final String LOGOUT_ICON_SVG =
            "M17 7l-1.41 1.41L18.17 11H8v2h10.17l-2.58 2.58L17 17l5-5zM4 5h8V3H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h8v-2H4V5z";

    private static final String CAMERA_ICON_SVG =
            "M9 2L7.17 4H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2h-3.17L15 2H9zm3 15c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5z";


    // =========================================================
    // COLORS
    // =========================================================

    private static final String PRIMARY_PINK = "#EC4988";
    private static final String TEXT_DARK = "#0F172A";
    private static final String TEXT_GREY = "#64748B";


    // =========================================================
    // CONSTRUCTOR
    // =========================================================
 //private String sessionAshaId;
    public Asha_profilepage() {
         

        controller = new Ashaprofilecontroller();

        cloudinaryService =
                new CloudinaryService();
                sessionAshaId = SessionManager.getAshaId();

if (sessionAshaId != null && !sessionAshaId.isBlank()) {
    ashaIdFixed = true;
}

        /*
         * IMPORTANT:
         *
         * Dashboard मधून ASHA ID येणार नाही.
         *
         * त्यामुळे profile page उघडल्यानंतर
         * user स्वतः ASHA ID enter करेल.
         */
    }


    // =========================================================
    // MAIN PROFILE CONTENT
    // =========================================================

    public Node getProfileContent() {

        rootBox = new VBox(14);

        rootBox.setPadding(
                new Insets(18, 30, 25, 30)
        );

        rootBox.setAlignment(
                Pos.TOP_CENTER
        );

        rootBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#FEF9FC"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        VBox titleBox =
                new VBox(3);

        titleBox.setAlignment(
                Pos.CENTER
        );

        Label mainTitle =
                new Label("My Profile");

        mainTitle.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1E1B4B;"
        );

        Label subTitle =
                new Label(
                        "Manage your personal information"
                );

        subTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #64748B;"
        );

        titleBox.getChildren().addAll(
                mainTitle,
                subTitle
        );


        // =====================================================
        // ASHA ID SECTION
        // =====================================================

        HBox ashaIdBox =
                createAshaIdSection();

        rootBox.getChildren().addAll(
                titleBox,
                ashaIdBox
        );


        /*
         * जर ASHA ID set केलेली असेल तर profile load करा.
         * पहिल्यांदा ID set नसेल तर खाली बाकी profile
         * placeholder स्वरूपात दाखवतो.
         */

        VBox profileContainer =
                new VBox(14);

        profileContainer.setMaxWidth(
                Double.MAX_VALUE
        );

        rootBox.getChildren().add(
                profileContainer
        );


        if (sessionAshaId != null &&
            !sessionAshaId.isBlank()) {

            loadProfileFromFirebase(
                    sessionAshaId,
                    profileContainer
            );

        } else {

            showEmptyProfileMessage(
                    profileContainer
            );
        }


        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                rootBox
        );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setStyle(
                "-fx-background-color: #FEF9FC;" +
                "-fx-border-color: transparent;"
        );

        return scrollPane;
    }


    // =========================================================
    // ASHA ID SECTION
    // =========================================================

    private HBox createAshaIdSection() {

        HBox box =
                new HBox(10);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(12, 18, 12, 18)
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #F1E5EC;" +
                "-fx-border-radius: 14px;"
        );


        Label label =
                new Label("ASHA ID");

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );


        TextField idField =
                new TextField();

        idField.setPromptText(
                "Enter ASHA ID"
        );

        idField.setPrefHeight(
                38
        );

        HBox.setHgrow(
                idField,
                Priority.ALWAYS
        );


        Button setIdButton =
                new Button("Set ASHA ID");

        setIdButton.setStyle(
                "-fx-background-color: #EC4988;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 8 18;" +
                "-fx-cursor: hand;"
        );


        /*
         * ID आधीच set असेल तर field locked.
         */

        if (sessionAshaId != null &&
            !sessionAshaId.isBlank()) {

            idField.setText(
                    sessionAshaId
            );

            idField.setDisable(true);

            setIdButton.setDisable(
                    true
            );

            ashaIdFixed = true;
        }


        setIdButton.setOnAction(e -> {

            String enteredId =
                    idField.getText().trim();


            if (enteredId.isEmpty()) {

                showError(
                        "Please enter ASHA ID."
                );

                return;
            }


            if (ashaIdFixed) {

                showError(
                        "ASHA ID cannot be changed until logout."
                );

                return;
            }


            /*
             * ID current session मध्ये fix.
             */

            sessionAshaId =
                    enteredId;

            ashaIdFixed =
                    true;
                    SessionManager.setAshaId(enteredId);


            /*
             * Firebase मधून त्या ID चा profile fetch.
             */

            AshaProfileModel firebaseProfile =
                    controller.getAshaProfile(
                            sessionAshaId
                    );


            if (firebaseProfile == null) {

                /*
                 * नवीन profile.
                 */

                profile =
                        new AshaProfileModel(
                                "ASHA Worker",
                                sessionAshaId,
                                "",
                                "",
                                "",
                                "",
                                "",
                                true
                        );

                controller.addAshaProfile(
                        profile.getName(),
                        profile.getAshaId(),
                        profile.getPhoneNumber(),
                        profile.getEmail(),
                        profile.getAddress(),
                        profile.getDateOfJoining(),
                        profile.getProfileImage(),
                        profile.isActive()
                );

            } else {

                profile =
                        firebaseProfile;
            }


            /*
             * Field lock.
             */

            idField.setDisable(
                    true
            );

            setIdButton.setDisable(
                    true
            );


            /*
             * Profile page पुन्हा build करून
             * Firebase data दाखवतो.
             */

            refreshProfilePage();


            showSuccess(
                    "ASHA ID set successfully."
            );
        });


        box.getChildren().addAll(
                label,
                idField,
                setIdButton
        );

        return box;
    }


    // =========================================================
    // LOAD PROFILE
    // =========================================================

    private void loadProfileFromFirebase(
            String ashaId,
            VBox container) {

        try {

            profile =
                    controller.getAshaProfile(
                            ashaId
                    );


            if (profile == null) {

                profile =
                        new AshaProfileModel(
                                "ASHA Worker",
                                ashaId,
                                "",
                                "",
                                "",
                                "",
                                "",
                                true
                        );
            }


            buildProfileUI(
                    container
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            showError(
                    "Unable to load profile from Firebase."
            );
        }
    }


    // =========================================================
    // EMPTY PROFILE
    // =========================================================

    private void showEmptyProfileMessage(
            VBox container) {

        VBox box =
                new VBox(8);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(40)
        );

        Label text =
                new Label(
                        "Please enter your ASHA ID above."
                );

        text.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748B;"
        );

        box.getChildren().add(
                text
        );

        container.getChildren().add(
                box
        );
    }


    // =========================================================
    // BUILD EXISTING PROFILE UI
    // =========================================================

    private void buildProfileUI(
            VBox profileContainer) {

        profileContainer.getChildren().clear();


        // =====================================================
        // PROFILE BANNER
        // =====================================================

        HBox profileBanner =
                new HBox();

        profileBanner.setAlignment(
                Pos.CENTER_LEFT
        );

        profileBanner.setPadding(
                new Insets(10, 25, 10, 25)
        );

        profileBanner.setMinHeight(
                125
        );

        profileBanner.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: #F1E5EC;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 16px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.04), 10, 0, 0, 3);"
        );


        // =====================================================
        // AVATAR
        // =====================================================

        StackPane avatarPane =
                new StackPane();

        Circle outerCircle =
                new Circle(
                        30,
                        Color.web("#F472B6")
                );

        outerCircle.setStroke(
                Color.web("#F472B6")
        );

        outerCircle.setStrokeWidth(
                2
        );


        ImageView uploadedImageView =
                new ImageView();

        uploadedImageView.setFitWidth(
                60
        );

        uploadedImageView.setFitHeight(
                60
        );

        uploadedImageView.setPreserveRatio(
                false
        );
       

        Circle imageClip =
                new Circle(
                        30,
                        30,
                        30
                );

        uploadedImageView.setClip(
                imageClip
        );


        if (profile.getProfileImage() != null &&
            profile.getProfileImage().startsWith("http")) {

            uploadedImageView.setImage(
                    new Image(
                            profile.getProfileImage(),
                            true
                    )
            );
        }


        SVGPath userAvatarIcon =
                createSvgPath(
                        USER_ICON_SVG,
                        "#FFFFFF",
                        1.5
                );


        // =====================================================
        // CAMERA
        // =====================================================

        Button cameraButton =
                new Button();

        cameraButton.setGraphic(
                createSvgPath(
                        CAMERA_ICON_SVG,
                        PRIMARY_PINK,
                        0.65
                )
        );

        cameraButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-color: #CBD5E1;" +
                "-fx-border-radius: 50%;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 4px;" +
                "-fx-cursor: hand;"
        );

        cameraButton.setPrefSize(
                25,
                25
        );

        cameraButton.setOnAction(
                e -> uploadProfileImage(
                        uploadedImageView,
                        avatarPane,
                        outerCircle,
                        userAvatarIcon
                )
        );


        StackPane.setAlignment(
                cameraButton,
                Pos.BOTTOM_RIGHT
        );

        StackPane.setMargin(
                cameraButton,
                new Insets(
                        45,
                        0,
                        0,
                        45
                )
        );


        if (uploadedImageView.getImage() != null) {

            avatarPane.getChildren().addAll(
                    uploadedImageView,
                    cameraButton
            );

        } else {

            avatarPane.getChildren().addAll(
                    outerCircle,
                    userAvatarIcon,
                    cameraButton
            );
        }


        // =====================================================
        // PROFILE DETAILS
        // =====================================================

        VBox profileDetails =
                new VBox(5);

        profileDetails.setPadding(
                new Insets(0, 0, 0, 15)
        );


        Label workerName =
                new Label(
                        profile.getName()
                );

        workerName.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT_DARK + ";"
        );
        workerName.setTranslateY(23);


        HBox idBadgeBox =
                new HBox(8);

        idBadgeBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Label ashaId =
                new Label(
                        "ASHA ID: " +
                        profile.getAshaId()
                );

        ashaId.setTranslateY(
                21
        );

        ashaId.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT_GREY + ";"
        );


        Label statusBadge =
                new Label(
                        profile.isActive()
                                ? "✔ Active"
                                : "✖ Inactive"
                );

        statusBadge.setStyle(
                "-fx-background-color: #DCFCE7;" +
                "-fx-text-fill: #166534;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 2 8;" +
                "-fx-background-radius: 10px;"
        );


        idBadgeBox.getChildren().addAll(
                ashaId,
                statusBadge
        );


        profileDetails.getChildren().addAll(
                workerName,
                idBadgeBox
        );


        Region bannerSpacer =
                new Region();

        HBox.setHgrow(
                bannerSpacer,
                Priority.ALWAYS
        );


        ImageView imageView =
                null;

        try {

            if (profile.getProfileImage() != null &&
                !profile.getProfileImage().isBlank()) {

                Image sirImage =
                        new Image(
                                profile.getProfileImage()
                        );

                imageView =
                        new ImageView(
                                sirImage
                        );

                imageView.setFitHeight(
                        100
                );

                imageView.setFitWidth(
                        220
                );

                imageView.setPreserveRatio(
                        true
                );
            }

        } catch (Exception ex) {

            System.out.println(
                    "[PROFILE] Image could not be loaded."
            );
        }


        if (imageView != null) {

            profileBanner.getChildren().addAll(
                    avatarPane,
                    profileDetails,
                    bannerSpacer,
                    imageView
            );

        } else {

            profileBanner.getChildren().addAll(
                    avatarPane,
                    profileDetails,
                    bannerSpacer
            );
        }


        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        VBox infoCard =
                new VBox(7);

        infoCard.setPadding(
                new Insets(
                        14,
                        20,
                        14,
                        20
                )
        );

        infoCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: #F1E5EC;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 16px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.04), 10, 0, 0, 3);"
        );


        HBox sectionHeader =
                new HBox(8);

        sectionHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        SVGPath infoIcon =
                createSvgPath(
                        USER_ICON_SVG,
                        PRIMARY_PINK,
                        0.8
                );


        Label sectionTitle =
                new Label(
                        "Personal Information"
                );

        sectionTitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                PRIMARY_PINK + ";"
        );


        sectionHeader.getChildren().addAll(
                infoIcon,
                sectionTitle
        );


        VBox infoList =
                new VBox(6);


        refreshInfoList(
                infoList
        );


        infoCard.getChildren().addAll(
                sectionHeader,
                infoList
        );


        // =====================================================
        // EDIT PROFILE BUTTON
        // =====================================================

        Button editProfileButton =
                new Button(
                        "Edit Profile"
                );

        editProfileButton.setStyle(
                "-fx-background-color: #EC4988;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 8 18;" +
                "-fx-cursor: hand;"
        );


        HBox editButtonBox =
                new HBox(
                        editProfileButton
                );

        editButtonBox.setAlignment(
                Pos.CENTER_RIGHT
        );


        infoCard.getChildren().add(
                editButtonBox
        );


        editProfileButton.setOnAction(
                e -> showEditProfileDialog(
                        workerName,
                        infoList
                )
        );


        // =====================================================
        // ACTION BOX
        // =====================================================

        HBox actionBox =
                new HBox(15);

        actionBox.setAlignment(
                Pos.CENTER
        );


        HBox passCard =
                createActionCard(
                        LOCK_ICON_SVG,
                        "Change Password",
                        "Update your account password",
                        "#F3E8FF",
                        PRIMARY_PINK,
                        PRIMARY_PINK
                );

        HBox.setHgrow(
                passCard,
                Priority.ALWAYS
        );


        passCard.setOnMouseClicked(
                e -> showChangePasswordDialog()
        );


        HBox logoutCard =
                createActionCard(
                        LOGOUT_ICON_SVG,
                        "Logout",
                        "Securely logout from your account",
                        "#FFE4E6",
                        PRIMARY_PINK,
                        PRIMARY_PINK
                );

        HBox.setHgrow(
                logoutCard,
                Priority.ALWAYS
        );


        logoutCard.setOnMouseClicked(
                e -> logoutUser()
        );


        actionBox.getChildren().addAll(
                passCard,
                logoutCard
        );


        // =====================================================
        // FOOTER
        // =====================================================

        Label footerText =
                new Label(
                        "Thank you for your dedication and service to the community! 💜"
                );

        footerText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #64748B;"
        );

        footerText.setAlignment(
                Pos.CENTER
        );

        footerText.setMaxWidth(
                Double.MAX_VALUE
        );


        profileContainer.getChildren().addAll(
                profileBanner,
                infoCard,
                actionBox,
                footerText
        );
    }


    // =========================================================
    // REFRESH INFO LIST
    // =========================================================

    private void refreshInfoList(
            VBox infoList) {

        infoList.getChildren().clear();

        infoList.getChildren().addAll(

                createInfoRow(
                        USER_ICON_SVG,
                        "Name",
                        profile.getName(),
                        PRIMARY_PINK
                ),

                createInfoRow(
                        PHONE_ICON_SVG,
                        "Phone Number",
                        profile.getPhoneNumber(),
                        PRIMARY_PINK
                ),

                createInfoRow(
                        MAIL_ICON_SVG,
                        "Email",
                        profile.getEmail(),
                        PRIMARY_PINK
                ),

                createInfoRow(
                        LOCATION_ICON_SVG,
                        "Address",
                        profile.getAddress(),
                        PRIMARY_PINK
                ),

                createInfoRow(
                        CALENDAR_ICON_SVG,
                        "Date of Joining",
                        profile.getDateOfJoining(),
                        PRIMARY_PINK
                )
        );
    }


    // =========================================================
    // EDIT PROFILE
    // =========================================================

    private void showEditProfileDialog(
            Label workerName,
            VBox infoList) {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Profile"
        );

        dialog.setHeaderText(
                "Update your personal information"
        );


        TextField nameField =
                new TextField(
                        profile.getName()
                );

        TextField phoneField =
                new TextField(
                        profile.getPhoneNumber()
                );

        TextField emailField =
                new TextField(
                        profile.getEmail()
                );

        TextField addressField =
                new TextField(
                        profile.getAddress()
                );

        TextField dateogjoining =
                new TextField(
                        profile.getDateOfJoining()
                );


        nameField.setPromptText(
                "Enter name"
        );

        phoneField.setPromptText(
                "Enter phone number"
        );

        emailField.setPromptText(
                "Enter email"
        );

        addressField.setPromptText(
                "Enter address"
        );

        dateogjoining.setPromptText(
                "Date of joining"
        );


        nameField.setPrefHeight(
                40
        );

        phoneField.setPrefHeight(
                40
        );

        emailField.setPrefHeight(
                40
        );

        addressField.setPrefHeight(
                40
        );

        dateogjoining.setPrefHeight(
                40
        );


        VBox content =
                new VBox(10);

        content.setPadding(
                new Insets(20)
        );

        content.setPrefWidth(
                380
        );


        content.getChildren().addAll(

                new Label("Name"),
                nameField,

                new Label("Phone Number"),
                phoneField,

                new Label("Email"),
                emailField,

                new Label("Address"),
                addressField,

                new Label("Date of joining"),
                dateogjoining
        );


        dialog.getDialogPane().setContent(
                content
        );


        ButtonType saveButton =
                new ButtonType(
                        "Save Changes"
                );

        ButtonType cancelButton =
                new ButtonType(
                        "Cancel"
                );


        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        cancelButton
                );


        Button save =
                (Button) dialog.getDialogPane()
                        .lookupButton(
                                saveButton
                        );


        save.addEventFilter(
                javafx.event.ActionEvent.ACTION,
                event -> {

                    String newName =
                            nameField.getText().trim();

                    String newPhone =
                            phoneField.getText().trim();

                    String newEmail =
                            emailField.getText().trim();

                    String newAddress =
                            addressField.getText().trim();

                    String newdate =
                            dateogjoining
                                    .getText()
                                    .trim();


                    if (newName.isEmpty()) {

                        showError(
                                "Name cannot be empty."
                        );

                        event.consume();

                        return;
                    }


                    if (newPhone.isEmpty()) {

                        showError(
                                "Phone number cannot be empty."
                        );

                        event.consume();

                        return;
                    }


                    if (newEmail.isEmpty()) {

                        showError(
                                "Email cannot be empty."
                        );

                        event.consume();

                        return;
                    }


                    if (newAddress.isEmpty()) {

                        showError(
                                "Address cannot be empty."
                        );

                        event.consume();

                        return;
                    }


                    if (newdate.isEmpty()) {

                        showError(
                                "Date of joining cannot be empty."
                        );

                        event.consume();

                        return;
                    }


                    /*
                     * IMPORTANT:
                     *
                     * ASHA ID is NOT changed here.
                     *
                     * sessionAshaId remains fixed.
                     */

                    controller.updateAshaProfile(

                            newName,

                            sessionAshaId,

                            newPhone,

                            newEmail,

                            newAddress,

                            newdate,

                            profile.getProfileImage(),

                            profile.isActive()
                    );


                    /*
                     * Firebase save झाल्यानंतर
                     * पुन्हा Firebase मधून profile fetch.
                     */

                    AshaProfileModel updatedProfile =
                            controller.getAshaProfile(
                                    sessionAshaId
                            );


                    if (updatedProfile != null) {

                        profile =
                                updatedProfile;

                    } else {

                        profile =
                                new AshaProfileModel(
                                        newName,
                                        sessionAshaId,
                                        newPhone,
                                        newEmail,
                                        newAddress,
                                        newdate,
                                        profile.getProfileImage(),
                                        profile.isActive()
                                );
                    }


                    workerName.setText(
                            profile.getName()
                    );


                    refreshInfoList(
                            infoList
                    );


                    System.out.println(
                            "[PROFILE] Firebase profile updated successfully."
                    );


                    showSuccess(
                            "Profile updated successfully."
                    );
                }
        );


        dialog.showAndWait();
    }


    // =========================================================
    // REFRESH WHOLE PROFILE
    // =========================================================

    private void refreshProfilePage() {

        if (rootBox == null) {
            return;
        }

        /*
         * Existing root मधील title + ASHA ID section
         * ठेवून profile portion refresh करणे.
         *
         * getProfileContent() पुन्हा call करण्याची गरज नाही.
         */

        Node oldNode = null;

        if (rootBox.getChildren().size() > 2) {

            oldNode =
                    rootBox.getChildren().get(2);
        }

        VBox newContainer =
                new VBox(14);

        newContainer.setMaxWidth(
                Double.MAX_VALUE
        );

        loadProfileFromFirebase(
                sessionAshaId,
                newContainer
        );


        if (oldNode != null) {

            int index =
                    rootBox.getChildren()
                            .indexOf(oldNode);

            rootBox.getChildren()
                    .set(
                            index,
                            newContainer
                    );

        } else {

            rootBox.getChildren().add(
                    newContainer
            );
        }
    }


    // =========================================================
    // IMAGE UPLOAD
    // =========================================================

    private void uploadProfileImage(
            ImageView uploadedImageView,
            StackPane avatarPane,
            Circle outerCircle,
            SVGPath userAvatarIcon) {

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Select Profile Image"
        );

        fileChooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "All Files",
                                "*.png",
                                "*.jpg",
                                "*.jpeg",
                                "*.JPEG",
                                "*.webp",
                                "*.JPG",
                                "*.WEBP",
                                "*.PNG"
                        )
                );


        File selectedFile =
                fileChooser.showOpenDialog(
                        Welcomepage.stage
                );


        if (selectedFile == null) {
            return;
        }


        try {

            String imageUrl =
                    cloudinaryService
                            .uploadProfileImage(
                                    selectedFile
                            );


            if (imageUrl == null ||
                imageUrl.isBlank()) {

                showError(
                        "Image upload failed."
                );

                return;
            }


            controller.updateAshaProfile(

                    profile.getName(),

                    sessionAshaId,

                    profile.getPhoneNumber(),

                    profile.getEmail(),

                    profile.getAddress(),

                    profile.getDateOfJoining(),

                    imageUrl,

                    profile.isActive()
            );


            profile =
                    controller.getAshaProfile(
                            sessionAshaId
                    );


            uploadedImageView.setImage(
                    new Image(
                            imageUrl,
                            true
                    )
            );
            refreshProfilePage();
            

            
            


            avatarPane.getChildren()
                    .removeAll(
                            outerCircle,
                            userAvatarIcon
                    );


            if (!avatarPane.getChildren()
                    .contains(uploadedImageView)) {

                avatarPane.getChildren()
                        .add(
                                0,
                                uploadedImageView
                        );
            }


            showSuccess(
                    "Profile image uploaded successfully!"
            );


        } catch (Exception ex) {

            ex.printStackTrace();

            showError(
                    "Image upload failed: " +
                    ex.getMessage()
            );
        }
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private void logoutUser() {

        /*
         * ASHA ID फक्त current profile-page session मध्ये होती.
         *
         * Logout झाल्यावर clear.
         */

        sessionAshaId =
                null;

        ashaIdFixed =
                false;

        profile =
                null;
                SessionManager.logout();


        try {

            Loginpage loginPage =
                    new Loginpage();

            Scene loginScene =
                    loginPage.gotologinpage();


            if (Welcomepage.stage != null) {

                Welcomepage.stage.setScene(
                        loginScene
                );

                Welcomepage.stage.setMaximized(
                        true
                );

            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }


    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void showChangePasswordDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Change Password"
        );

        dialog.setHeaderText(
                "Update your account password"
        );


        PasswordField newPassword =
                new PasswordField();

        newPassword.setPromptText(
                "Enter new password"
        );

        newPassword.setPrefHeight(
                45
        );


        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Confirm new password"
        );

        confirmPassword.setPrefHeight(
                45
        );


        Label message =
                new Label();

        message.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );


        VBox content =
                new VBox(12);

        content.setPadding(
                new Insets(20)
        );

        content.setPrefWidth(
                350
        );


        content.getChildren().addAll(

                new Label(
                        "New Password"
                ),

                newPassword,

                new Label(
                        "Confirm Password"
                ),

                confirmPassword,

                message
        );


        dialog.getDialogPane().setContent(
                content
        );


        ButtonType updateButton =
                new ButtonType(
                        "Update Password"
                );

        ButtonType cancelButton =
                new ButtonType(
                        "Cancel"
                );


        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        updateButton,
                        cancelButton
                );


        Button update =
                (Button) dialog.getDialogPane()
                        .lookupButton(
                                updateButton
                        );


        update.addEventFilter(
                javafx.event.ActionEvent.ACTION,
                event -> {

                    String newPass =
                            newPassword.getText();

                    String confirmPass =
                            confirmPassword.getText();


                    if (newPass.isBlank()) {

                        message.setText(
                                "Please enter a new password."
                        );

                        message.setStyle(
                                "-fx-text-fill: #DC2626;" +
                                "-fx-font-weight: bold;"
                        );

                        event.consume();

                        return;
                    }


                    if (newPass.length() < 6) {

                        message.setText(
                                "Password must contain at least 6 characters."
                        );

                        message.setStyle(
                                "-fx-text-fill: #DC2626;" +
                                "-fx-font-weight: bold;"
                        );

                        event.consume();

                        return;
                    }


                    if (!newPass.equals(confirmPass)) {

                        message.setText(
                                "Passwords do not match."
                        );

                        message.setStyle(
                                "-fx-text-fill: #DC2626;" +
                                "-fx-font-weight: bold;"
                        );

                        event.consume();

                        return;
                    }


                    message.setText(
                            "Password validated successfully."
                    );

                    message.setStyle(
                            "-fx-text-fill: #059669;" +
                            "-fx-font-weight: bold;"
                    );
                }
        );


        dialog.showAndWait();
    }


    // =========================================================
    // SUCCESS
    // =========================================================

    private void showSuccess(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Success"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =========================================================
    // ERROR
    // =========================================================

    private void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Invalid Information"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =========================================================
    // SVG
    // =========================================================

    private SVGPath createSvgPath(
            String pathData,
            String fillHex,
            double scale) {

        SVGPath path =
                new SVGPath();

        path.setContent(
                pathData
        );

        path.setFill(
                Color.web(fillHex)
        );

        path.setScaleX(
                scale
        );

        path.setScaleY(
                scale
        );

        return path;
    }


    // =========================================================
    // INFO ROW
    // =========================================================

    private HBox createInfoRow(
            String svgPathData,
            String labelText,
            String valueText,
            String themeColor) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setMaxWidth(
                Double.MAX_VALUE
        );

        row.setStyle(
                "-fx-background-color: #F8FAFC;" +
                "-fx-padding: 7 12;" +
                "-fx-background-radius: 10px;"
        );


        StackPane leftIconBg =
                new StackPane();

        Circle circle =
                new Circle(
                        14,
                        Color.web("#F1F5F9")
                );


        SVGPath leftIcon =
                createSvgPath(
                        svgPathData,
                        themeColor,
                        0.7
                );


        leftIconBg.getChildren().addAll(
                circle,
                leftIcon
        );


        Label title =
                new Label(
                        labelText
                );

        title.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        title.setPrefWidth(
                130
        );


        Label value =
                new Label(
                        valueText == null
                                ? ""
                                : valueText
                );

        value.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #475569;"
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        row.getChildren().addAll(
                leftIconBg,
                title,
                value,
                spacer
        );


        return row;
    }


    // =========================================================
    // ACTION CARD
    // =========================================================

    private HBox createActionCard(
            String svgPathData,
            String title,
            String subtitle,
            String bgColor,
            String iconColor,
            String textColor) {

        HBox card =
                new HBox(12);

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPadding(
                new Insets(
                        10,
                        15,
                        10,
                        15
                )
        );

        card.setMinHeight(
                65
        );

        card.setStyle(
                "-fx-background-color: " +
                bgColor +
                ";" +
                "-fx-background-radius: 14px;" +
                "-fx-cursor: hand;"
        );


        StackPane iconBox =
                new StackPane();

        iconBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 8;"
        );


        SVGPath icon =
                createSvgPath(
                        svgPathData,
                        iconColor,
                        0.85
                );


        iconBox.getChildren().add(
                icon
        );


        VBox textContainer =
                new VBox(2);


        Label mainText =
                new Label(
                        title
                );

        mainText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                textColor +
                ";"
        );


        Label subText =
                new Label(
                        subtitle
                );

        subText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #64748B;"
        );


        textContainer.getChildren().addAll(
                mainText,
                subText
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        SVGPath arrow =
                createSvgPath(
                        "M8.59 16.59L13.17 12 8.59 7.41L10 6l6 6-6 6-1.41-1.41z",
                        textColor,
                        0.85
                );


        card.getChildren().addAll(
                iconBox,
                textContainer,
                spacer,
                arrow
        );


        return card;
    }
}
