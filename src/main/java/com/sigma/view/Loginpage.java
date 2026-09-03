package com.sigma.view;

import com.sigma.controller.Controller;
import com.sigma.view.adminpages.AdminDashboard;
import com.sigma.view.doctorpages.DoctorDashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Loginpage {

    // =========================================================
    // FIELDS
    // =========================================================

    private Scene loginpagScene;

    private String role = "";

    private final Controller controller = new Controller();

    // Admin security code
    private static final String ADMIN_SECURITY_CODE = "12345";

    // =========================================================
    // LOGIN PAGE
    // =========================================================

    public Scene gotologinpage() {

        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "to bottom right, " +
                        "#FFFFFF 0%, " +
                        "#FFF4F8 50%, " +
                        "#F1E8FF 100%" +
                        ");"
        );

        // =====================================================
        // LEFT SIDE
        // =====================================================

        VBox leftSide = new VBox(18);

        leftSide.setAlignment(Pos.CENTER);

        leftSide.setPadding(
                new Insets(40, 50, 40, 60)
        );

        leftSide.setPrefWidth(550);

        // =====================================================
        // LOGO
        // =====================================================

        String IMAGE_PATH =
                "file:Maacare-Ai\\src\\main\\resources\\assets\\images\\logo\\logo.png";

        Image logoImage = new Image(IMAGE_PATH);

        ImageView logoView =
                new ImageView(logoImage);

        logoView.setPreserveRatio(true);
        logoView.setSmooth(true);

        logoView.fitWidthProperty().bind(
                leftSide.widthProperty().multiply(0.75)
        );

        // =====================================================
        // STATUS
        // =====================================================

        Text loginStatus = new Text("");

        loginStatus.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;"
        );

        loginStatus.setWrappingWidth(430);

        // =====================================================
        // BRAND
        // =====================================================

        Text brandName =
                new Text("MaaCare AI");

        brandName.setStyle(
                "-fx-fill: #9B4DCC;" +
                        "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;"
        );

        Text tagline =
                new Text(
                        "Care for Mom. Care for Baby. Care for Life."
                );

        tagline.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Text description1 =
                new Text(
                        "Your AI-Powered Companion for"
                );

        description1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Text description2 =
                new Text(
                        " Mother & Child"
                );

        description2.setStyle(
                "-fx-fill: #E84A87;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Text description3 =
                new Text(
                        " Healthcare"
                );

        description3.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        HBox description =
                new HBox(
                        description1,
                        description2,
                        description3
                );

        description.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // INFO
        // =====================================================

        Text info =
                new Text(
                        "Track. Monitor. Get AI Guidance."
                );

        info.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 16px;"
        );

        Text smallInfo =
                new Text(
                        "Because every mom and baby deserves the best care."
                );

        smallInfo.setStyle(
                "-fx-fill: #77778D;" +
                        "-fx-font-size: 14px;"
        );

        // =====================================================
        // FEATURES
        // =====================================================

        HBox features =
                new HBox(25);

        features.setAlignment(
                Pos.CENTER
        );

        features.setPadding(
                new Insets(20, 0, 0, 0)
        );

        features.getChildren().addAll(

                createFeature(
                        "♥",
                        "Pregnancy",
                        "Tracking"
                ),

                createFeature(
                        "✚",
                        "Health",
                        "Records"
                ),

                createFeature(
                        "✦",
                        "AI Health",
                        "Assistant"
                ),

                createFeature(
                        "▣",
                        "Doctor &",
                        "Hospital"
                )
        );

        leftSide.getChildren().addAll(
                logoView,
                brandName,
                tagline,
                description,
                info,
                smallInfo,
                features,
                loginStatus
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        StackPane rightSide =
                new StackPane();

        rightSide.setPadding(
                new Insets(
                        40,
                        70,
                        40,
                        40
                )
        );

        // =====================================================
        // LOGIN CARD
        // =====================================================

        VBox loginCard =
                new VBox(18);

        loginCard.setAlignment(
                Pos.TOP_CENTER
        );

        loginCard.setPadding(
                new Insets(
                        35,
                        40,
                        30,
                        40
                )
        );

        loginCard.setMaxWidth(800);
        loginCard.setMaxHeight(850);

        loginCard.setStyle(
                "-fx-background-color: rgba(255,255,255,0.94);" +
                        "-fx-background-radius: 25px;" +
                        "-fx-border-color: rgba(220,200,230,0.45);" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 25px;"
        );

        // =====================================================
        // HEADING
        // =====================================================

        Text welcome =
                new Text(
                        "Welcome Back!"
                );

        welcome.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;"
        );

        Text loginInfo =
                new Text(
                        "Login to your MaaCare AI account"
                );

        loginInfo.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 16px;"
        );

        // =====================================================
        // ROLE TITLE
        // =====================================================

        Text roleTitle =
                new Text(
                        "Select Your Role"
                );

        roleTitle.setStyle(
                "-fx-fill: #7B3FC6;" +
                        "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;"
        );

        Text selectedRole =
                new Text("");

        selectedRole.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // =====================================================
        // ROLE BUTTONS
        // =====================================================

        HBox roles =
                new HBox(12);

        roles.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // EMAIL
        // =====================================================

        TextField email =
                new TextField();

        email.setPromptText(
                "Email / Phone Number"
        );

        email.setPrefHeight(55);

        email.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #DDD9E6;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 0 18px;"
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Password"
        );

        password.setPrefHeight(55);

        password.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #DDD9E6;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 0 18px;"
        );

        // =====================================================
        // ADMIN SECURITY CODE
        // =====================================================

        PasswordField securityCode =
                new PasswordField();

        securityCode.setPromptText(
                "Admin Security Code"
        );

        securityCode.setPrefHeight(55);

        securityCode.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #DDD9E6;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 0 18px;"
        );

        // Hidden until Admin is selected
        securityCode.setVisible(false);
        securityCode.setManaged(false);

        // =====================================================
        // REMEMBER ME
        // =====================================================

        CheckBox remember =
                new CheckBox(
                        "Remember me"
                );

        remember.setStyle(
                "-fx-text-fill: #666680;" +
                        "-fx-font-size: 14px;"
        );

        HBox options =
                new HBox();

        options.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                remember,
                Priority.ALWAYS
        );

        options.getChildren().add(
                remember
        );

        // =====================================================
        // MOTHER
        // =====================================================

        Button mother =
                createRoleButton(
                        "🤱\nMother\nFamily"
                );

        mother.setOnAction(e -> {

            role = "mother";

            selectedRole.setText(
                    "Role : Mother"
            );

            securityCode.clear();
            securityCode.setVisible(false);
            securityCode.setManaged(false);
        });

        // =====================================================
        // DOCTOR
        // =====================================================

        Button doctor =
                createRoleButton(
                        "🧑‍⚕️\nDoctor"
                );

        doctor.setOnAction(e -> {

            role = "doctor";

            selectedRole.setText(
                    "Role : Doctor"
            );

            securityCode.clear();
            securityCode.setVisible(false);
            securityCode.setManaged(false);
        });

        // =====================================================
        // HOSPITAL
        // =====================================================

        Button hospital =
                createRoleButton(
                        "🏥\nHospital"
                );

        hospital.setOnAction(e -> {

            role = "hospital";

            selectedRole.setText(
                    "Role : Hospital"
            );

            securityCode.clear();
            securityCode.setVisible(false);
            securityCode.setManaged(false);
        });

        // =====================================================
        // ASHA WORKER
        // =====================================================

        Button asha =
                createRoleButton(
                        "👩\nASHA Worker"
                );

        asha.setOnAction(e -> {

            role = "asha";

            selectedRole.setText(
                    "Role : ASHA Worker"
            );

            securityCode.clear();
            securityCode.setVisible(false);
            securityCode.setManaged(false);
        });

        // =====================================================
        // ADMIN
        // =====================================================

        Button admin =
                createRoleButton(
                        "🧑‍💻\nAdmin"
                );

        admin.setOnAction(e -> {

            role = "admin";

            selectedRole.setText(
                    "Role : Admin"
            );

            securityCode.setVisible(true);
            securityCode.setManaged(true);
        });

        // =====================================================
        // ADD ROLE BUTTONS
        // =====================================================

        roles.getChildren().addAll(
                mother,
                doctor,
                hospital,
                asha,
                admin
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button login =
                new Button(
                        "🔒   Login"
                );

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(55);

        login.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "to right, #F54B87, #9B4DCC" +
                        ");" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-cursor: hand;"
        );

        // =====================================================
        // LOGIN ACTION
        // =====================================================

        login.setOnAction(e -> {

            loginStatus.setText("");

            String enteredEmail =
                    email.getText().trim();

            String enteredPassword =
                    password.getText();

            // =================================================
            // ROLE VALIDATION
            // =================================================

            if (role == null ||
                    role.isBlank()) {

                loginStatus.setText(
                        "Please select your role"
                );

                return;
            }

            // =================================================
            // EMAIL VALIDATION
            // =================================================

            if (enteredEmail.isBlank()) {

                loginStatus.setText(
                        "Please enter your email"
                );

                email.requestFocus();

                return;
            }

            // =================================================
            // PASSWORD VALIDATION
            // =================================================

            if (enteredPassword.isBlank()) {

                loginStatus.setText(
                        "Please enter your password"
                );

                password.requestFocus();

                return;
            }

            // =================================================
            // ADMIN SECURITY CODE
            // =================================================

            if (role.equals("admin")) {

                if (securityCode.getText().isBlank()) {

                    loginStatus.setText(
                            "Please enter Admin Security Code"
                    );

                    securityCode.requestFocus();

                    return;
                }

                if (!securityCode.getText().equals(
                        ADMIN_SECURITY_CODE
                )) {

                    loginStatus.setText(
                            "Invalid Admin Security Code"
                    );

                    securityCode.clear();

                    securityCode.requestFocus();

                    return;
                }
            }

            // =================================================
            // FIREBASE AUTHENTICATION
            // =================================================

            System.out.println(
                    "[LOGIN] Authenticating..."
            );

            boolean authenticated;

            try {

                authenticated =
                        controller.signin(
                                enteredEmail,
                                enteredPassword
                        );

            } catch (Exception ex) {

                ex.printStackTrace();

                loginStatus.setText(
                        "Authentication error. Please try again."
                );

                return;
            }

            // =================================================
            // AUTHENTICATION FAILED
            // =================================================

            if (!authenticated) {

                System.out.println(
                        "[LOGIN] Authentication failed"
                );

                loginStatus.setText(
                        "Invalid email or password."
                );

                password.clear();

                return;
            }

            // =================================================
            // AUTHENTICATION SUCCESS
            // =================================================

            System.out.println(
                    "[LOGIN] Authentication successful"
            );

            try {

                // =================================================
                // ADMIN
                // =================================================

                if (role.equals("admin")) {

                    System.out.println(
                            "[LOGIN] Opening Admin Dashboard..."
                    );

                    AdminDashboard adminDashboard =
                            new AdminDashboard();

                    Scene adminScene =
                            adminDashboard.gotoAdminDashboard();

                    Welcomepage.stage.setScene(
                            adminScene
                    );

                    Welcomepage.stage.setMaximized(
                            true
                    );

                    System.out.println(
                            "[LOGIN] Admin Dashboard opened"
                    );
                }

                // =================================================
                // DOCTOR
                // =================================================

                else if (role.equals("doctor")) {

                    System.out.println(
                            "[LOGIN] Opening Doctor Dashboard..."
                    );

                    DoctorDashboard doctorDashboard =
                            new DoctorDashboard();

                    Scene doctorScene =
                            doctorDashboard.gotoDoctorDashboard();

                    Welcomepage.stage.setScene(
                            doctorScene
                    );

                    Welcomepage.stage.setMaximized(
                            true
                    );

                    System.out.println(
                            "[LOGIN] Doctor Dashboard opened"
                    );
                }

                // =================================================
                // HOSPITAL
                // =================================================

                else if (role.equals("hospital")) {

                    System.out.println(
                            "[LOGIN] Opening Hospital Dashboard..."
                    );

                    Dashboard dashboard =
                            new Dashboard();

                    dashboard.show(
                            Welcomepage.stage
                    );

                    Welcomepage.stage.setMaximized(
                            true
                    );

                    System.out.println(
                            "[LOGIN] Hospital Dashboard opened"
                    );
                }

                // =================================================
                // ASHA WORKER
                // =================================================

                else if (role.equals("asha")) {

                    System.out.println(
                            "[LOGIN] Opening ASHA Dashboard..."
                    );

                    Asha_workerdashboard ashaDashboard =
                            new Asha_workerdashboard();

                    Scene ashaScene =
                            ashaDashboard.run();

                    Welcomepage.stage.setScene(
                            ashaScene
                    );

                    Welcomepage.stage.setMaximized(
                            true
                    );

                    System.out.println(
                            "[LOGIN] ASHA Dashboard opened"
                    );
                }

                // =================================================
                // MOTHER
                // =================================================

                else if (role.equals("mother")) {

                    /*
                     * Mother Dashboard has not yet been created.
                     */

                    loginStatus.setText(
                            "Mother Dashboard is not connected yet."
                    );

                    System.out.println(
                            "[LOGIN] Mother Dashboard not available"
                    );

                    return;
                }

                // =================================================
                // UNKNOWN ROLE
                // =================================================

                else {

                    loginStatus.setText(
                            "Invalid role selected."
                    );

                    return;
                }

                // =================================================
                // CLEAR LOGIN FIELDS
                // =================================================

                email.clear();
                password.clear();
                securityCode.clear();

            } catch (Exception ex) {

                ex.printStackTrace();

                loginStatus.setText(
                        "Unable to open dashboard."
                );

                System.out.println(
                        "[LOGIN] Dashboard opening failed"
                );
            }
        });

        // =====================================================
        // OR
        // =====================================================

        Text or =
                new Text("OR");

        or.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // =====================================================
        // SIGN UP
        // =====================================================

        Button signUp =
                new Button("Sign Up");

        signUp.setMaxWidth(
                Double.MAX_VALUE
        );

        signUp.setPrefHeight(52);

        signUp.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #49308C;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-color: #9B4DCC;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-cursor: hand;"
        );

        signUp.setOnAction(e -> {

            if (role == null ||
                    role.isBlank()) {

                loginStatus.setText(
                        "Please select your role first."
                );

                return;
            }

            String enteredEmail =
                    email.getText().trim();

            String enteredPassword =
                    password.getText();

            if (enteredEmail.isBlank()) {

                loginStatus.setText(
                        "Please enter your email."
                );

                email.requestFocus();

                return;
            }

            if (enteredPassword.isBlank()) {

                loginStatus.setText(
                        "Please enter your password."
                );

                password.requestFocus();

                return;
            }

            try {

                controller.signup(
                        enteredEmail,
                        enteredPassword
                );

                if (controller.status_code == 200) {

                    loginStatus.setText(
                            "Sign up successful. Login to continue."
                    );

                    password.clear();

                } else if (
                        controller.status_code == 400) {

                    loginStatus.setText(
                            "Email already exists. Please login."
                    );

                } else {

                    loginStatus.setText(
                            "Sign up failed."
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                loginStatus.setText(
                        "Unable to create account."
                );
            }
        });

        // =====================================================
        // CREATE ACCOUNT
        // =====================================================

        HBox createAccount =
                new HBox(6);

        createAccount.setAlignment(
                Pos.CENTER
        );

        Text accountText =
                new Text(
                        "Don't have an account?"
                );

        accountText.setStyle(
                "-fx-fill: #77778D;" +
                        "-fx-font-size: 14px;"
        );

        Text createText =
                new Text(
                        " Sign up"
                );

        createText.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        createAccount.getChildren().addAll(
                accountText,
                createText
        );

        // =====================================================
        // LOGIN CARD CONTENT
        // =====================================================

        loginCard.getChildren().addAll(

                welcome,

                loginInfo,

                roleTitle,

                roles,

                selectedRole,

                email,

                password,

                securityCode,

                options,

                login,

                or,

                signUp,

                createAccount
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        rightSide.getChildren().add(
                loginCard
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.setLeft(
                leftSide
        );

        root.setCenter(
                rightSide
        );

        BorderPane.setAlignment(
                leftSide,
                Pos.CENTER
        );

        // =====================================================
        // SCENE
        // =====================================================

        loginpagScene =
                new Scene(
                        root,
                        scenesettings.rectanguler2d.getWidth(),
                        scenesettings.rectanguler2d.getHeight()
                );

        return loginpagScene;
    }

    // =========================================================
    // FEATURE
    // =========================================================

    private VBox createFeature(
            String symbol,
            String line1,
            String line2) {

        Text icon =
                new Text(symbol);

        icon.setStyle(
                "-fx-fill: #E84A87;" +
                        "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;"
        );

        Text text1 =
                new Text(line1);

        text1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );

        Text text2 =
                new Text(line2);

        text2.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );

        VBox box =
                new VBox(2);

        box.setAlignment(
                Pos.CENTER
        );

        box.getChildren().addAll(
                icon,
                text1,
                text2
        );

        return box;
    }

    // =========================================================
    // ROLE BUTTON
    // =========================================================

    private Button createRoleButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefWidth(105);

        button.setPrefHeight(100);

        button.setWrapText(true);

        button.setTextAlignment(
                TextAlignment.CENTER
        );

        button.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #24234F;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: #E2DFEA;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-border-width: 1px;" +
                        "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> {

            button.setStyle(
                    "-fx-background-color: #FFF0F6;" +
                            "-fx-text-fill: #C92F78;" +
                            "-fx-font-size: 13px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 12px;" +
                            "-fx-border-color: #F54B87;" +
                            "-fx-border-radius: 12px;" +
                            "-fx-border-width: 2px;" +
                            "-fx-cursor: hand;"
            );
        });

        button.setOnMouseExited(e -> {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: #24234F;" +
                            "-fx-font-size: 13px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 12px;" +
                            "-fx-border-color: #E2DFEA;" +
                            "-fx-border-radius: 12px;" +
                            "-fx-border-width: 1px;" +
                            "-fx-cursor: hand;"
            );
        });

        return button;
    }
}