package com.sigma.view;

import com.sigma.controller.Controller;
import com.sigma.view.adminpages.AdminDashboard;
import com.sigma.view.doctorpages.DoctorDashboard;
import com.sigma.view.motherPages.MotherWelcome;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.prefs.Preferences;

public class Loginpage {

    public BorderPane loginroot;

    private Scene loginpagScene;

    public String role = "";

    private final Controller controller = new Controller();

    // Login controls whose visibility depends on the selected role.
    private Button signUpButton;
    private Text orText;
    private HBox createAccountBox;

    // Loading overlay.
    private StackPane loadingOverlay;
    private BorderPane loginRootForLoading;

    // Remember Me storage (local to this computer).
    private static final Preferences LOGIN_PREFS =
            Preferences.userNodeForPackage(Loginpage.class);

    private static final String PREF_REMEMBER = "remember_login";
    private static final String PREF_EMAIL = "login_email";
    private static final String PREF_PASSWORD = "login_password";
    private static final String PREF_ROLE = "login_role";

    // Admin security code.
    private static final String ADMIN_SECURITY_CODE = "12345";

    // =============================================================
    // LOGIN PAGE
    // =============================================================

    public Scene gotologinpage() {

        // =========================================================
        // ROOT
        // =========================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "to bottom right, " +
                        "#FFFFFF 0%, " +
                        "#FFF4F8 50%, " +
                        "#F1E8FF 100%" +
                        ");"
        );

        // =========================================================
        // LEFT SIDE
        // =========================================================

        VBox leftSide = new VBox(18);

        leftSide.setAlignment(Pos.CENTER);

        leftSide.setPadding(
                new Insets(40, 50, 40, 60)
        );

        leftSide.setPrefWidth(550);

        // =========================================================
        // LOGO
        // =========================================================

        String imagePath =
                "file:Maacare-Ai\\src\\main\\resources\\assets\\images\\logo\\logo.png";

        Image logoImage = new Image(imagePath);

        ImageView logoView = new ImageView(logoImage);

        logoView.setPreserveRatio(true);
        logoView.setSmooth(true);

        logoView.fitWidthProperty().bind(
                leftSide.widthProperty().multiply(0.6)
        );

        // =========================================================
        // LEFT LOGIN STATUS
        // =========================================================

        Text logininfo = new Text("");

        logininfo.setStyle(
                "-fx-fill: #13CEE3;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================================================
        // TITLE
        // =========================================================

        Text brandName = new Text("MaaCare AI");

        brandName.setStyle(
                "-fx-fill: linear-gradient(to right, #E84A87, #9B4DCC);" +
                        "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================================================
        // TAGLINE
        // =========================================================

        Text tagline1 = new Text(
                "Care for Mom. Care for Baby. Care for Life."
        );

        tagline1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================================================
        // DESCRIPTION
        // =========================================================

        Text description1 = new Text(
                "Your AI-Powered Companion for"
        );

        description1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Text descriptionPink = new Text(
                " Mother & Child"
        );

        descriptionPink.setStyle(
                "-fx-fill: #E84A87;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Text description2 = new Text(
                " Healthcare"
        );

        description2.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        HBox description = new HBox(
                description1,
                descriptionPink,
                description2
        );

        description.setAlignment(Pos.CENTER);

        // =========================================================
        // INFO
        // =========================================================

        Text info = new Text(
                "Track. Monitor. Get AI Guidance."
        );

        info.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 16px;"
        );

        Text smallInfo = new Text(
                "Because every mom and baby deserves the best care."
        );

        smallInfo.setStyle(
                "-fx-fill: #77778D;" +
                        "-fx-font-size: 14px;"
        );

        // =========================================================
        // FEATURES
        // =========================================================

        HBox features = new HBox(25);

        features.setAlignment(Pos.CENTER);

        features.setPadding(
                new Insets(20, 0, 0, 0)
        );

        features.getChildren().addAll(
                createFeature("♥", "Pregnancy", "Tracking"),
                createFeature("✚", "Health", "Records"),
                createFeature("✦", "AI Health", "Assistant"),
                createFeature("▣", "Doctor &", "Hospital")
        );

        leftSide.getChildren().addAll(
                logoView,
                brandName,
                tagline1,
                description,
                info,
                smallInfo,
                features,
                logininfo
        );

        // =========================================================
        // RIGHT SIDE
        // =========================================================

        StackPane rightSide = new StackPane();

        rightSide.setPadding(
                new Insets(40, 70, 40, 40)
        );

        // =========================================================
        // LOGIN CARD
        // =========================================================

        VBox loginCard = new VBox(18);

        loginCard.setAlignment(Pos.TOP_CENTER);

        loginCard.setPadding(
                new Insets(35, 40, 30, 40)
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

        // =========================================================
        // HEADING
        // =========================================================

        Text welcome = new Text(
                "Welcome Back!"
        );

        welcome.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;"
        );

        Text loginInfo = new Text(
                "Login to your MaaCare AI account"
        );

        loginInfo.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 16px;"
        );

        // =========================================================
        // ROLE
        // =========================================================

        Text roleTitle = new Text(
                "Select Your Role"
        );

        roleTitle.setStyle(
                "-fx-fill: #7B3FC6;" +
                        "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;"
        );

        Text selectedRole = new Text(
                "No role selected"
        );

        selectedRole.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================================================
        // ROLE BUTTONS
        // =========================================================

        HBox roles = new HBox(12);

        roles.setAlignment(Pos.CENTER);

        // =========================================================
        // EMAIL
        // =========================================================

        TextField email = new TextField();

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

        // =========================================================
        // PASSWORD
        // =========================================================

        PasswordField password = new PasswordField();

        TextField visiblePassword = new TextField();

        password.setPromptText("Password");
        visiblePassword.setPromptText("Password");

        password.setPrefHeight(55);
        visiblePassword.setPrefHeight(55);

        String passwordStyle =
                "-fx-background-color: white;" +
                        "-fx-border-color: #DDD9E6;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 0 18px;";

        password.setStyle(passwordStyle);
        visiblePassword.setStyle(passwordStyle);

        visiblePassword.setVisible(false);
        visiblePassword.setManaged(false);

        Button showPassword = new Button("Show");

        showPassword.setPrefHeight(45);
        showPassword.setMinWidth(65);

        showPassword.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        HBox passwordBox = new HBox(5);

        passwordBox.setAlignment(Pos.CENTER);

        HBox.setHgrow(password, Priority.ALWAYS);
        HBox.setHgrow(visiblePassword, Priority.ALWAYS);

        passwordBox.getChildren().addAll(
                password,
                visiblePassword,
                showPassword
        );

        // Keep both password fields synchronized while typing.
        final boolean[] syncingPassword = {false};

        password.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (!syncingPassword[0] &&
                            !visiblePassword.isVisible()) {

                        syncingPassword[0] = true;
                        visiblePassword.setText(newValue);
                        syncingPassword[0] = false;
                    }
                }
        );

        visiblePassword.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (!syncingPassword[0] &&
                            visiblePassword.isVisible()) {

                        syncingPassword[0] = true;
                        password.setText(newValue);
                        syncingPassword[0] = false;
                    }
                }
        );

        showPassword.setOnAction(e -> {

            if (visiblePassword.isVisible()) {

                syncingPassword[0] = true;
                password.setText(
                        visiblePassword.getText()
                );
                syncingPassword[0] = false;

                visiblePassword.setVisible(false);
                visiblePassword.setManaged(false);

                password.setVisible(true);
                password.setManaged(true);

                showPassword.setText("Show");

            } else {

                syncingPassword[0] = true;
                visiblePassword.setText(
                        password.getText()
                );
                syncingPassword[0] = false;

                password.setVisible(false);
                password.setManaged(false);

                visiblePassword.setVisible(true);
                visiblePassword.setManaged(true);

                showPassword.setText("Hide");

                visiblePassword.requestFocus();
                visiblePassword.positionCaret(
                        visiblePassword.getText().length()
                );
            }
        });

        // =========================================================
        // ADMIN SECURITY CODE
        // =========================================================

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

        securityCode.setVisible(false);
        securityCode.setManaged(false);

        // =========================================================
        // LOGIN STATUS
        // =========================================================

        Text loginStatus =
                new Text("");

        loginStatus.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );

        loginStatus.setWrappingWidth(650);

        // =========================================================
        // FORGOT PASSWORD
        // =========================================================

        Button forgotPassword =
                new Button("Forgot Password?");

        forgotPassword.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
        );

        forgotPassword.setOnAction(e ->
                logininfo.setText(
                        "Password reset is handled through Firebase."
                )
        );

        // =========================================================
        // REMEMBER ME
        // =========================================================

        CheckBox remember =
                new CheckBox("Remember me");

        remember.setStyle(
                "-fx-text-fill: #666680;" +
                        "-fx-font-size: 14px;"
        );

        HBox options = new HBox();

        options.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(
                remember,
                Priority.ALWAYS
        );

        options.getChildren().add(
                remember
        );

        remember.setOnAction(e -> {

            if (!remember.isSelected()) {
                clearRememberedLogin();
            }
        });

        // =========================================================
        // ROLE: MOTHER
        // =========================================================

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

            showSignUpControls(true);
        });

        // =========================================================
        // ROLE: DOCTOR
        // =========================================================

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

            showSignUpControls(true);
        });

        // =========================================================
        // ROLE: HOSPITAL
        // =========================================================

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

            showSignUpControls(true);
        });

        // =========================================================
        // ROLE: ASHA WORKER
        // =========================================================

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

            showSignUpControls(true);
        });

        // =========================================================
        // ROLE: ADMIN
        // =========================================================

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

            // Admin is login-only. Hide all Sign Up controls.
            showSignUpControls(false);
        });

        // =========================================================
        // ADD ROLE BUTTONS
        // =========================================================

        // Ambulance / Emergency login has intentionally been removed.
        roles.getChildren().addAll(
                mother,
                doctor,
                hospital,
                asha,
                admin
        );

        // =========================================================
        // LOGIN STATUS
        // =========================================================

        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button login =
                new Button("Login");

        login.setMaxWidth(
                Double.MAX_VALUE
        );

        login.setPrefHeight(52);

        login.setStyle(
                "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-cursor: hand;"
        );

        // =========================================================
        // LOGIN ACTION
        // =========================================================

        login.setOnAction(e -> {

            loginStatus.setText("");

            String enteredEmail =
                    email.getText().trim();

            String enteredPassword =
                    visiblePassword.isVisible()
                            ? visiblePassword.getText()
                            : password.getText();

            // =====================================================
            // ROLE VALIDATION
            // =====================================================

            if (role == null || role.isBlank()) {

                loginStatus.setText(
                        "Please select your role."
                );

                return;
            }

            // =====================================================
            // EMAIL VALIDATION
            // =====================================================

            if (enteredEmail.isBlank()) {

                loginStatus.setText(
                        "Please enter your email."
                );

                email.requestFocus();

                return;
            }

            // =====================================================
            // PASSWORD VALIDATION
            // =====================================================

            if (enteredPassword.isBlank()) {

                loginStatus.setText(
                        "Please enter your password."
                );

                password.requestFocus();

                return;
            }

            // =====================================================
            // ADMIN SECURITY CODE
            // =====================================================

            if (role.equals("admin")) {

                String enteredSecurityCode =
                        securityCode.getText().trim();

                if (enteredSecurityCode.isBlank()) {

                    loginStatus.setText(
                            "Please enter Admin Security Code."
                    );

                    securityCode.requestFocus();

                    return;
                }

                if (!enteredSecurityCode.equals(
                        ADMIN_SECURITY_CODE)) {

                    loginStatus.setText(
                            "Invalid Admin Security Code."
                    );

                    securityCode.clear();
                    securityCode.requestFocus();

                    return;
                }
            }

            // =====================================================
            // SHOW LOADING
            // =====================================================

            setLoginLoading(
                    true,
                    loginStatus
            );

            System.out.println(
                    "[LOGIN] Authenticating..."
            );

            Task<Boolean> loginTask =
                    new Task<>() {

                        @Override
                        protected Boolean call()
                                throws Exception {

                            return controller.signin(
                                    enteredEmail,
                                    enteredPassword
                            );
                        }
                    };

            // =====================================================
            // LOGIN SUCCESS
            // =====================================================

            loginTask.setOnSucceeded(event -> {

                boolean authenticated =
                        loginTask.getValue();

                if (!authenticated) {

                    System.out.println(
                            "[LOGIN] Authentication failed"
                    );

                    loginStatus.setText(
                            "Invalid email or password."
                    );

                    password.clear();
                    visiblePassword.clear();

                    setLoginLoading(
                            false,
                            loginStatus
                    );

                    return;
                }

                System.out.println(
                        "[LOGIN] Authentication successful"
                );

                // Save credentials only after successful login.
                saveRememberedLogin(
                        remember.isSelected(),
                        enteredEmail,
                        enteredPassword,
                        role
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
            "[LOGIN] Doctor Dashboard is not connected yet."
    );

    loginStatus.setText(
            "Doctor Dashboard is not connected yet."
    );

    setLoginLoading(
            false,
            loginStatus
    );

    return;
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
                    // ASHA
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

        System.out.println(
                "[LOGIN] Opening Mother Welcome..."
        );
    
        MotherWelcome motherWelcome =
                new MotherWelcome();
    
        Scene motherScene =
                motherWelcome.getMotherWelcomeScene();
    
        Welcomepage.stage.setScene(
                motherScene
        );
    
        Welcomepage.stage.setMaximized(
                true
        );
    
        System.out.println(
                "[LOGIN] Mother Welcome opened"
        );
    }

                    else {

                        loginStatus.setText(
                                "Invalid role selected."
                        );

                        setLoginLoading(
                                false,
                                loginStatus
                        );

                        return;
                    }

                    // Clear login fields after dashboard opens.
                    email.clear();
                    password.clear();
                    visiblePassword.clear();
                    securityCode.clear();

                    setLoginLoading(
                            false,
                            loginStatus
                    );

                } catch (Exception ex) {

                    ex.printStackTrace();

                    loginStatus.setText(
                            "Unable to open dashboard."
                    );

                    System.out.println(
                            "[LOGIN] Dashboard opening failed"
                    );

                    setLoginLoading(
                            false,
                            loginStatus
                    );
                }
            });

            // =====================================================
            // LOGIN FAILED
            // =====================================================

            loginTask.setOnFailed(event -> {

                Throwable ex =
                        loginTask.getException();

                if (ex != null) {
                    ex.printStackTrace();
                }

                loginStatus.setText(
                        "Authentication error. Please try again."
                );

                setLoginLoading(
                        false,
                        loginStatus
                );
            });

            Thread loginThread =
                    new Thread(
                            loginTask,
                            "Firebase-Login-Thread"
                    );

            loginThread.setDaemon(true);
            loginThread.start();
        });

        // =========================================================
        // OR
        // =========================================================

        Text or =
                new Text("OR");

        orText = or;

        or.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // =========================================================
        // SIGN UP
        // =========================================================

        Button signUp =
                new Button("Sign Up");

        signUpButton = signUp;

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
                    visiblePassword.isVisible()
                            ? visiblePassword.getText()
                            : password.getText();

            // Admin accounts cannot be created from this page.
            if (role.equals("admin")) {
                loginStatus.setText(
                        "Admin accounts cannot be created here."
                );
                return;
            }

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

                    if (role.equals("admin")) {
                        loginStatus.setText(
                                "Admin account created successfully. Login to continue."
                        );
                    } else {
                        loginStatus.setText(
                                "Sign up successful. Login to continue."
                        );
                    }

                    password.clear();
                    visiblePassword.clear();

                    if (role.equals("admin")) {
                        securityCode.clear();
                    }

                } else {

                    String error =
                            controller.getLastError();

                    loginStatus.setText(
                            error == null ||
                                    error.isBlank()
                                    ? "Sign up failed."
                                    : error
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                loginStatus.setText(
                        "Sign up failed. Please try again."
                );
            }
        });

        // =========================================================
        // GOOGLE LOGIN
        // =========================================================

        Button google =
                new Button("Continue with Google");

        google.setMaxWidth(
                Double.MAX_VALUE
        );

        google.setPrefHeight(52);

        google.setStyle(
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

        google.setOnAction(e ->
                loginStatus.setText(
                        "Google sign-in is not configured yet."
                )
        );

        // =========================================================
        // CREATE ACCOUNT
        // =========================================================

        HBox createAccount =
                new HBox(6);

        createAccountBox =
                createAccount;

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

        // =========================================================
        // INITIAL SIGN-UP VISIBILITY
        // =========================================================

        // No role is selected initially, so keep Sign Up hidden.
        showSignUpControls(false);

        // =========================================================
        // RESTORE REMEMBERED LOGIN
        // =========================================================

        loadRememberedLogin(
                email,
                password,
                visiblePassword,
                remember,
                mother,
                doctor,
                hospital,
                asha,
                admin
        );

        // =========================================================
        // LOGIN CARD CONTENT
        // =========================================================

        loginCard.getChildren().addAll(
                welcome,
                loginInfo,
                roleTitle,
                roles,
                selectedRole,
                email,
                passwordBox,
                securityCode,
                forgotPassword,
                options,
                login,
                loginStatus,

                signUp,
                createAccount
        );

        rightSide.getChildren().add(
                loginCard
        );

        // =========================================================
        // ROOT
        // =========================================================

        root.setLeft(leftSide);
        root.setCenter(rightSide);

        BorderPane.setAlignment(
                leftSide,
                Pos.CENTER
        );

        // =========================================================
        // LOADING OVERLAY
        // =========================================================

        StackPane loadingOverlay =
                createLoadingOverlay();

        loadingOverlay.setVisible(false);
        loadingOverlay.setManaged(false);

        StackPane sceneRoot =
                new StackPane(
                        root,
                        loadingOverlay
                );

        // Keep BorderPane for getRoot() compatibility.
        loginroot = root;

        loginpagScene =
                new Scene(
                        sceneRoot,
                        scenesettings.rectanguler2d.getWidth(),
                        scenesettings.rectanguler2d.getHeight()
                );

        this.loadingOverlay =
                loadingOverlay;

        this.loginRootForLoading =
                root;

        return loginpagScene;
    }

    // =========================================================
    // SIGN-UP VISIBILITY
    // =========================================================

    private void showSignUpControls(
            boolean visible) {

        if (orText != null) {

            orText.setVisible(visible);
            orText.setManaged(visible);
        }

        if (signUpButton != null) {

            signUpButton.setVisible(visible);
            signUpButton.setManaged(visible);
        }

        if (createAccountBox != null) {

            createAccountBox.setVisible(visible);
            createAccountBox.setManaged(visible);
        }
    }

    // =========================================================
    // REMEMBER ME
    // =========================================================

    private void saveRememberedLogin(
            boolean rememberMe,
            String email,
            String password,
            String selectedRole) {

        if (!rememberMe) {

            clearRememberedLogin();

            return;
        }

        if (email == null ||
                email.isBlank() ||
                password == null ||
                password.isBlank() ||
                selectedRole == null ||
                selectedRole.isBlank()) {

            return;
        }

        LOGIN_PREFS.putBoolean(
                PREF_REMEMBER,
                true
        );

        LOGIN_PREFS.put(
                PREF_EMAIL,
                email
        );

        LOGIN_PREFS.put(
                PREF_PASSWORD,
                password
        );

        LOGIN_PREFS.put(
                PREF_ROLE,
                selectedRole
        );

        try {
            LOGIN_PREFS.flush();
        } catch (Exception ignored) {
        }
    }

    private void clearRememberedLogin() {

        LOGIN_PREFS.remove(
                PREF_REMEMBER
        );

        LOGIN_PREFS.remove(
                PREF_EMAIL
        );

        LOGIN_PREFS.remove(
                PREF_PASSWORD
        );

        LOGIN_PREFS.remove(
                PREF_ROLE
        );

        try {
            LOGIN_PREFS.flush();
        } catch (Exception ignored) {
        }
    }

    private void loadRememberedLogin(
            TextField email,
            PasswordField password,
            TextField visiblePassword,
            CheckBox remember,
            Button mother,
            Button doctor,
            Button hospital,
            Button asha,
            Button admin) {

        if (!LOGIN_PREFS.getBoolean(
                PREF_REMEMBER,
                false)) {

            return;
        }

        String savedEmail =
                LOGIN_PREFS.get(
                        PREF_EMAIL,
                        ""
                );

        String savedPassword =
                LOGIN_PREFS.get(
                        PREF_PASSWORD,
                        ""
                );

        String savedRole =
                LOGIN_PREFS.get(
                        PREF_ROLE,
                        ""
                );

        if (savedEmail.isBlank() ||
                savedPassword.isBlank() ||
                savedRole.isBlank()) {

            clearRememberedLogin();

            return;
        }

        email.setText(savedEmail);

        password.setText(
                savedPassword
        );

        visiblePassword.setText(
                savedPassword
        );

        remember.setSelected(true);

        switch (savedRole) {

            case "mother" ->
                    mother.fire();

            case "doctor" ->
                    doctor.fire();

            case "hospital" ->
                    hospital.fire();

            case "asha" ->
                    asha.fire();

            case "admin" ->
                    admin.fire();

            default ->
                    clearRememberedLogin();
        }
    }

    // =========================================================
    // LOADING STATE
    // =========================================================

    private StackPane createLoadingOverlay() {

        StackPane overlay =
                new StackPane();

        overlay.setStyle(
                "-fx-background-color: rgba(255,255,255,0.96);"
        );

        VBox loadingBox =
                new VBox(15);

        loadingBox.setAlignment(
                Pos.CENTER
        );

        ProgressIndicator progress =
                new ProgressIndicator();

        progress.setPrefSize(
                65,
                65
        );

        progress.setProgress(-1);

        Text loadingTitle =
                new Text(
                        "Signing you in..."
                );

        loadingTitle.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        Text loadingText =
                new Text(
                        "Connecting to MaaCare AI. Please wait..."
                );

        loadingText.setStyle(
                "-fx-fill: #77778D;" +
                        "-fx-font-size: 14px;"
        );

        loadingBox.getChildren().addAll(
                progress,
                loadingTitle,
                loadingText
        );

        overlay.getChildren().add(
                loadingBox
        );

        return overlay;
    }

    private void setLoginLoading(
            boolean loading,
            Text loginStatus) {

        if (loadingOverlay == null ||
                loginRootForLoading == null) {

            return;
        }

        loadingOverlay.setVisible(
                loading
        );

        loadingOverlay.setManaged(
                loading
        );

        loginRootForLoading.setDisable(
                loading
        );

        if (loading) {
            loginStatus.setText("");
        }
    }

    // =============================================================
    // FEATURE CREATION
    // =============================================================

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

    // =============================================================
    // ROLE BUTTON
    // =============================================================

    private Button createRoleButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefWidth(105);
        button.setPrefHeight(100);
        button.setWrapText(true);

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

        button.setOnMouseEntered(e ->
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
                )
        );

        button.setOnMouseExited(e ->
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
                )
        );

        return button;
    }

    public BorderPane getRoot() {
        return loginroot;
    }
}
