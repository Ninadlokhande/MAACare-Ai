package com.sigma.view;

import com.sigma.controller.Controller;
import com.sigma.model.MotherWlcModel;
import com.sigma.view.adminpages.AdminDashboard;
import com.sigma.view.doctorpages.DoctorDashboard;
import com.sigma.view.motherPages.MotherWelcome;
import com.sigma.view.motherPages.MotherDashBoard;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

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

        VBox leftSide = new VBox(14);

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
                leftSide.widthProperty().multiply(0.75)
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
                createFeature("🤰", "Pregnancy", "Tracking"),
                createFeature("❤️", "Health", "Records"),
                createFeature("🤖", "AI Health", "Assistant"),
                createFeature("🏥", "Doctor &", "Hospital")
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

      loginCard.setEffect(
        new javafx.scene.effect.DropShadow(
                28,
                0,
                10,
                javafx.scene.paint.Color.rgb(0, 0, 0, 0.16)
        )
);

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
                "👋  Welcome Back!"
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
                "🎯  Select Your Role"
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
                "📧  Email / Phone Number"
        );

        email.setPrefHeight(55);

        addFieldMotion(email);

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

        password.setPromptText("🔒  Password");
        visiblePassword.setPromptText("🔒  Password");

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
        addFieldMotion(password);
        addFieldMotion(visiblePassword);

        visiblePassword.setVisible(false);
        visiblePassword.setManaged(false);

        Button showPassword = new Button("👁  Show");

        showPassword.setPrefHeight(45);
        showPassword.setMinWidth(65);

        showPassword.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        addButtonMotion(showPassword, 1.08);

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

                showPassword.setText("👁  Show");

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

                showPassword.setText("🙈  Hide");

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
                "🛡️  Admin Security Code"
        );

        securityCode.setPrefHeight(55);

        addFieldMotion(securityCode);

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
        // FORGOT PASSWORD
        // =========================================================

        Button forgotPassword =
                new Button("🔑  Forgot Password?");

        forgotPassword.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #713CC3;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
        );

        addButtonMotion(forgotPassword, 1.04);

        forgotPassword.setOnAction(e ->
                logininfo.setText(
                        "Password reset is handled through Firebase."
                )
        );

        // =========================================================
        // REMEMBER ME
        // =========================================================

        CheckBox remember =
                new CheckBox("☑  Remember me");

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
            animateRoleSelection(mother);

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
            animateRoleSelection(doctor);

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
            animateRoleSelection(hospital);

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
            animateRoleSelection(asha);

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
            animateRoleSelection(admin);

            selectedRole.setText(
                    "Role : Admin"
            );

            securityCode.setVisible(true);
            securityCode.setManaged(true);

            // Admin accounts are login-only.
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

        Text loginStatus =
                new Text("");

        loginStatus.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );

        loginStatus.setWrappingWidth(650);

        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button login =
                new Button("🔐  Login");

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

        addButtonMotion(login, 1.025);

        // =========================================================
        // LOGIN ACTION
        // =========================================================

        login.setOnAction(e -> {

            loginStatus.setText("");
            animateStatusMessage(loginStatus);

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
                        "⚠  Please select your role."
                );

                return;
            }

            // =====================================================
            // EMAIL VALIDATION
            // =====================================================

            if (enteredEmail.isBlank()) {

                loginStatus.setText(
                        "📧  Please enter your email."
                );

                email.requestFocus();

                return;
            }

            // =====================================================
            // PASSWORD VALIDATION
            // =====================================================

            if (enteredPassword.isBlank()) {

                loginStatus.setText(
                        "🔒  Please enter your password."
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
                            "❌  Invalid Admin Security Code."
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
                            "❌  Invalid email or password."
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
                    // ROLE-BASED LOGIN NAVIGATION
                    // =================================================
                    // Existing Mother -> Mother Dashboard
                    // All other roles -> their assigned dashboard/page.
                    // =================================================

                    openDashboardForLogin(role);

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
                new Button("📝  Sign Up");

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

        addButtonMotion(signUp, 1.025);

        signUp.setOnAction(e -> {

            if (role == null ||
                    role.isBlank()) {

                loginStatus.setText(
                        "Please select your role first."
                );

                return;
            }

            // Admin does not have a public sign-up option.
            if (role.equals("admin")) {

                loginStatus.setText(
                        "Admin accounts cannot be created here."
                );

                return;
            }

            String enteredEmail =
                    email.getText().trim();

            String enteredPassword =
                    visiblePassword.isVisible()
                            ? visiblePassword.getText()
                            : password.getText();

            if (enteredEmail.isBlank()) {

                loginStatus.setText(
                        "📧  Please enter your email."
                );

                email.requestFocus();

                return;
            }

            if (enteredPassword.isBlank()) {

                loginStatus.setText(
                        "🔒  Please enter your password."
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

                    System.out.println(
                            "[SIGNUP] Registration successful for role: " + role
                    );

                    try {

                        setLoginLoading(true, loginStatus);

                        // New Mother -> Mother Welcome / onboarding.
                        // New users of every other role -> their assigned page.
                        openDashboardAfterSignup(role);

                        return;

                    } catch (Exception navigationException) {

                        navigationException.printStackTrace();

                        loginStatus.setText(
                                "Sign up successful, but unable to open the next page."
                        );

                        setLoginLoading(false, loginStatus);
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

        showSignUpControls(true);

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
        // LIVE HEALTHCARE STATUS PANEL
        // =========================================================

        VBox livePanel = createLiveStatusPanel();

        // =========================================================
        // LOGIN CARD CONTENT
        // =========================================================

        loginCard.getChildren().addAll(
                welcome,
                loginInfo,
                livePanel,
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
                or,
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
        // LIVE AMBIENT ANIMATION LAYER
        // =========================================================

        Pane ambientLayer = createAmbientAnimationLayer();

        // Keep the animation layer visual-only so it never blocks clicks.
        ambientLayer.setMouseTransparent(true);

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
                        ambientLayer,
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

        // =========================================================
        // START PAGE ANIMATIONS
        // =========================================================

        playPageEntranceAnimation(
                leftSide,
                loginCard,
                logoView,
                welcome,
                loginInfo,
                roleTitle,
                roles,
                email,
                passwordBox,
                login,
                features
        );

        return loginpagScene;
    }

    // =========================================================
    // ROLE-BASED NAVIGATION
    // =========================================================

    private void openDashboardForLogin(String selectedRole)
            throws Exception {

        if ("admin".equals(selectedRole)) {

            System.out.println("[LOGIN] Opening Admin Dashboard...");

            AdminDashboard adminDashboard =
                    new AdminDashboard();

            Scene adminScene =
                    adminDashboard.gotoAdminDashboard();

            Welcomepage.stage.setScene(adminScene);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        if ("doctor".equals(selectedRole)) {

            System.out.println(
                    "[SIGNUP] Doctor selected."
            );

            /*
             * =========================================================
             * DOCTOR SIGNUP - WRITE / KEEP YOUR DOCTOR NAVIGATION HERE
             * =========================================================
             *
             * Put the existing Doctor signup-success destination here.
             *
             * Example:
             *
             * DoctorDashboard doctorDashboard =
             *         new DoctorDashboard();
             *
             * Scene doctorScene =
             *         doctorDashboard.YOUR_EXISTING_METHOD();
             *
             * Welcomepage.stage.setScene(doctorScene);
             * Welcomepage.stage.setMaximized(true);
             *
             * This space is intentionally left for your existing
             * Doctor page/dashboard code.
             * =========================================================
             */

            return;
        }

        if ("hospital".equals(selectedRole)) {

            System.out.println("[LOGIN] Opening Hospital Dashboard...");

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(Welcomepage.stage);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        if ("asha".equals(selectedRole)) {

            System.out.println("[LOGIN] Opening ASHA Dashboard...");

            Asha_workerdashboard ashaDashboard =
                    new Asha_workerdashboard();

            Scene ashaScene =
                    ashaDashboard.run();

            Welcomepage.stage.setScene(ashaScene);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        if ("mother".equals(selectedRole)) {

            System.out.println(
                    "[LOGIN] Existing Mother -> Opening Mother Dashboard..."
            );

            MotherWlcModel motherModel =
                    new MotherWlcModel();

            MotherDashBoard motherDashboard =
                    new MotherDashBoard(motherModel);

            Scene dashboardScene =
                    motherDashboard.getmotherDashboardScene();

            Welcomepage.stage.setScene(dashboardScene);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        throw new IllegalArgumentException(
                "Invalid role selected: " + selectedRole
        );
    }


    private void openDashboardAfterSignup(String selectedRole)
            throws Exception {

        // =========================================================
        // NEW MOTHER
        // =========================================================
        // Signup is different from login:
        // a newly registered Mother goes to MotherWelcome first.
        // =========================================================

        if ("mother".equals(selectedRole)) {

            System.out.println(
                    "[SIGNUP] New Mother -> Opening Mother Welcome..."
            );

            MotherWelcome motherWelcome =
                    new MotherWelcome();

            Scene motherWelcomeScene =
                    motherWelcome.getMotherWelcomeScene();

            Welcomepage.stage.setScene(motherWelcomeScene);
            Welcomepage.stage.setMaximized(true);

            System.out.println(
                    "[SIGNUP] Mother Welcome opened"
            );

            return;
        }

        // =========================================================
        // ALL OTHER SIGNUP ROLES
        // =========================================================
        // Use the exact same destination as login.
        // =========================================================

        if ("doctor".equals(selectedRole)) {

            System.out.println(
                    "[SIGNUP] Doctor -> Opening Doctor Dashboard..."
            );

            // =========================================================
            // DOCTOR LOGIN DESTINATION
            // =========================================================
            // WRITE YOUR EXISTING DOCTOR DASHBOARD NAVIGATION HERE.
            //
            // Example:
            // Scene doctorScene = doctorDashboard.<your_existing_method>();
            // Welcomepage.stage.setScene(doctorScene);
            // Welcomepage.stage.setMaximized(true);
            // =========================================================
            return;
        }

        if ("hospital".equals(selectedRole)) {

            System.out.println(
                    "[SIGNUP] Hospital -> Opening Hospital Dashboard..."
            );

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(Welcomepage.stage);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        if ("asha".equals(selectedRole)) {

            System.out.println(
                    "[SIGNUP] ASHA -> Opening ASHA Dashboard..."
            );

            Asha_workerdashboard ashaDashboard =
                    new Asha_workerdashboard();

            Scene ashaScene =
                    ashaDashboard.run();

            Welcomepage.stage.setScene(ashaScene);
            Welcomepage.stage.setMaximized(true);
            return;
        }

        if ("admin".equals(selectedRole)) {

            throw new IllegalArgumentException(
                    "Admin accounts cannot be created here."
            );
        }

        throw new IllegalArgumentException(
                "Invalid role selected: " + selectedRole
        );
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
                        "Opening your MaaCare AI account..."
                );

        loadingTitle.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        Text loadingText =
                new Text(
                        "Preparing your dashboard. Please wait..."
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

        if (loading) {
            loadingOverlay.setManaged(true);
            loadingOverlay.setVisible(true);
            loginRootForLoading.setDisable(true);
            loadingStatusPulse(loadingOverlay);
            loginStatus.setText("");
        } else {
            loginRootForLoading.setDisable(false);

            FadeTransition fadeOut =
                    new FadeTransition(Duration.millis(220), loadingOverlay);

            fadeOut.setFromValue(loadingOverlay.getOpacity());
            fadeOut.setToValue(0);

            fadeOut.setOnFinished(e -> {
                loadingOverlay.setVisible(false);
                loadingOverlay.setManaged(false);
                loadingOverlay.setOpacity(1);
            });

            fadeOut.play();
        }
    }

    // =============================================================
    // ANIMATION HELPERS
    // =============================================================

    private Pane createAmbientAnimationLayer() {

        Pane layer = new Pane();
        layer.setMouseTransparent(true);

        Circle glow1 = createGlowCircle(150, 0.10);
        Circle glow2 = createGlowCircle(105, 0.08);
        Circle glow3 = createGlowCircle(75, 0.07);
        Circle glow4 = createGlowCircle(120, 0.06);

        // Starting positions create a soft "living" background.
        glow1.setTranslateX(-300);
        glow1.setTranslateY(-230);

        glow2.setTranslateX(370);
        glow2.setTranslateY(-180);

        glow3.setTranslateX(430);
        glow3.setTranslateY(260);

        glow4.setTranslateX(-390);
        glow4.setTranslateY(300);

        layer.getChildren().addAll(glow1, glow2, glow3, glow4);

        animateAmbientCircle(glow1, 1500, 55, 35);
        animateAmbientCircle(glow2, 1900, -45, 45);
        animateAmbientCircle(glow3, 1700, -35, -50);
        animateAmbientCircle(glow4, 2200, 50, -35);

        return layer;
    }

    private Circle createGlowCircle(double radius, double opacity) {

        Circle circle = new Circle(radius);
        circle.setOpacity(opacity);
        circle.setStyle("-fx-fill: #E84A87;");
        circle.setEffect(new GaussianBlur(28));

        return circle;
    }

    private void animateAmbientCircle(
            Circle circle,
            double millis,
            double x,
            double y) {

        TranslateTransition move =
                new TranslateTransition(
                        Duration.millis(millis),
                        circle
                );

        move.setByX(x);
        move.setByY(y);
        move.setInterpolator(Interpolator.EASE_BOTH);
        move.setAutoReverse(true);
        move.setCycleCount(TranslateTransition.INDEFINITE);
        move.play();

        RotateTransition rotate =
                new RotateTransition(
                        Duration.millis(millis * 1.7),
                        circle
                );

        rotate.setByAngle(360);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.play();
    }

    private void playPageEntranceAnimation(
            VBox leftSide,
            VBox loginCard,
            ImageView logoView,
            Text welcome,
            Text loginInfo,
            Text roleTitle,
            HBox roles,
            TextField email,
            HBox passwordBox,
            Button login,
            HBox features) {

        leftSide.setOpacity(0);
        leftSide.setTranslateX(-45);

        loginCard.setOpacity(0);
        loginCard.setTranslateX(55);

        // Left panel slides in.
        FadeTransition leftFade =
                new FadeTransition(Duration.millis(700), leftSide);
        leftFade.setFromValue(0);
        leftFade.setToValue(1);

        TranslateTransition leftSlide =
                new TranslateTransition(Duration.millis(700), leftSide);
        leftSlide.setFromX(-45);
        leftSlide.setToX(0);
        leftSlide.setInterpolator(Interpolator.EASE_OUT);

        new ParallelTransition(leftFade, leftSlide).play();

        // Login card arrives slightly after the branding panel.
        FadeTransition cardFade =
                new FadeTransition(Duration.millis(750), loginCard);
        cardFade.setFromValue(0);
        cardFade.setToValue(1);

        TranslateTransition cardSlide =
                new TranslateTransition(Duration.millis(750), loginCard);
        cardSlide.setFromX(55);
        cardSlide.setToX(0);
        cardSlide.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition cardEntrance =
                new ParallelTransition(cardFade, cardSlide);

        cardEntrance.setDelay(Duration.millis(120));
        cardEntrance.play();

        // Small staggered reveal makes the form feel active rather than static.
        animateReveal(welcome, 220, 0, -12);
        animateReveal(loginInfo, 300, 0, -10);
        animateReveal(roleTitle, 380, 0, -10);
        animateReveal(roles, 460, 0, -12);
        animateReveal(email, 540, 0, -10);
        animateReveal(passwordBox, 600, 0, -10);
        animateReveal(login, 700, 0, -8);
        animateReveal(features, 650, 0, 10);

        // Logo continuously floats very slightly.
        TranslateTransition logoFloat =
                new TranslateTransition(
                        Duration.seconds(2.4),
                        logoView
                );
        logoFloat.setByY(-8);
        logoFloat.setAutoReverse(true);
        logoFloat.setCycleCount(TranslateTransition.INDEFINITE);
        logoFloat.setInterpolator(Interpolator.EASE_BOTH);
        logoFloat.play();

        // Feature icons gently pulse.
        for (javafx.scene.Node node : features.getChildren()) {
            ScaleTransition pulse =
                    new ScaleTransition(
                            Duration.seconds(2.2),
                            node
                    );
            pulse.setFromX(1.0);
            pulse.setFromY(1.0);
            pulse.setToX(1.035);
            pulse.setToY(1.035);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(ScaleTransition.INDEFINITE);
            pulse.setInterpolator(Interpolator.EASE_BOTH);
            pulse.play();
        }
    }

    private void animateReveal(
            javafx.scene.Node node,
            double delayMillis,
            double fromX,
            double fromY) {

        node.setOpacity(0);
        node.setTranslateX(fromX);
        node.setTranslateY(fromY);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(420),
                        node
                );
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide =
                new TranslateTransition(
                        Duration.millis(420),
                        node
                );
        slide.setFromX(fromX);
        slide.setFromY(fromY);
        slide.setToX(0);
        slide.setToY(0);
        slide.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition transition =
                new ParallelTransition(fade, slide);

        transition.setDelay(Duration.millis(delayMillis));
        transition.play();
    }

    private void animateRoleSelection(Button selectedButton) {

        ScaleTransition pop =
                new ScaleTransition(
                        Duration.millis(150),
                        selectedButton
                );

        pop.setFromX(1.0);
        pop.setFromY(1.0);
        pop.setToX(1.10);
        pop.setToY(1.10);
        pop.setAutoReverse(true);
        pop.setCycleCount(2);
        pop.setInterpolator(Interpolator.EASE_OUT);
        pop.play();
    }

    private void animateScale(
            javafx.scene.Node node,
            double scale,
            double millis) {

        ScaleTransition transition =
                new ScaleTransition(
                        Duration.millis(millis),
                        node
                );

        transition.setToX(scale);
        transition.setToY(scale);
        transition.setInterpolator(Interpolator.EASE_OUT);
        transition.play();
    }

    private void addButtonMotion(
            Button button,
            double hoverScale) {

        button.setOnMouseEntered(e ->
                animateScale(button, hoverScale, 120)
        );

        button.setOnMouseExited(e ->
                animateScale(button, 1.0, 120)
        );

        button.setOnMousePressed(e ->
                animateScale(button, 0.97, 70)
        );

        button.setOnMouseReleased(e ->
                animateScale(button, hoverScale, 90)
        );
    }

    private void loadingStatusPulse(
            StackPane overlay) {

        overlay.setOpacity(0);

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.millis(250),
                        overlay
                );

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    // =============================================================
    // ADDITIONAL LIVE UI / MOTION
    // =============================================================

    private VBox createLiveStatusPanel() {

        VBox panel = new VBox(8);
        panel.setAlignment(Pos.CENTER_LEFT);
        panel.setPadding(new Insets(12, 16, 12, 16));
        panel.setMaxWidth(Double.MAX_VALUE);
        panel.setStyle(
                "-fx-background-color: rgba(248,244,255,0.90);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(155,77,204,0.18);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;"
        );

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Circle dot = new Circle(5);
        dot.setStyle("-fx-fill: #35C98B;");

        Text live = new Text("🟢  LIVE CARE NETWORK");
        live.setStyle(
                "-fx-fill: #49308C;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Text status = new Text("All systems operational");
        status.setStyle(
                "-fx-fill: #35A979;" +
                "-fx-font-size: 11px;"
        );

        HBox.setHgrow(status, Priority.ALWAYS);
        header.getChildren().addAll(dot, live, status);

        ProgressBar activity = new ProgressBar(0.72);
        activity.setMaxWidth(Double.MAX_VALUE);
        activity.setPrefHeight(5);
        activity.setStyle(
                "-fx-accent: #E84A87;" +
                "-fx-control-inner-background: #EEEAF5;"
        );

        Text activityText = new Text(
                "AI monitoring • Secure connection • Real-time assistance"
        );
        activityText.setStyle(
                "-fx-fill: #77778D;" +
                "-fx-font-size: 10px;"
        );

        panel.getChildren().addAll(header, activity, activityText);

        // Breathing live indicator.
        ScaleTransition dotPulse =
                new ScaleTransition(Duration.seconds(0.9), dot);
        dotPulse.setFromX(0.75);
        dotPulse.setFromY(0.75);
        dotPulse.setToX(1.35);
        dotPulse.setToY(1.35);
        dotPulse.setAutoReverse(true);
        dotPulse.setCycleCount(ScaleTransition.INDEFINITE);
        dotPulse.setInterpolator(Interpolator.EASE_BOTH);
        dotPulse.play();

        // Continuously moving activity bar.
        Timeline activityMotion = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(activity.progressProperty(), 0.55)),
                new KeyFrame(Duration.seconds(2.0),
                        new KeyValue(activity.progressProperty(), 0.88))
        );
        activityMotion.setAutoReverse(true);
        activityMotion.setCycleCount(Timeline.INDEFINITE);
        activityMotion.play();

        return panel;
    }

    private void animateStatusMessage(Text node) {

        node.setOpacity(0);
        node.setTranslateY(8);

        FadeTransition fade =
                new FadeTransition(Duration.millis(280), node);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide =
                new TranslateTransition(Duration.millis(280), node);
        slide.setFromY(8);
        slide.setToY(0);
        slide.setInterpolator(Interpolator.EASE_OUT);

        new ParallelTransition(fade, slide).play();
    }

    private void addFieldMotion(javafx.scene.control.Control field) {

        field.setOnMouseEntered(e -> {
            ScaleTransition s =
                    new ScaleTransition(Duration.millis(120), field);
            s.setToX(1.012);
            s.setToY(1.012);
            s.play();
        });

        field.setOnMouseExited(e -> {
            ScaleTransition s =
                    new ScaleTransition(Duration.millis(120), field);
            s.setToX(1);
            s.setToY(1);
            s.play();
        });
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
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: #E2DFEA;" +
                        "-fx-border-radius: 16px;" +
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
            animateScale(button, 1.06, 120);
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
            animateScale(button, 1.0, 120);
        });

        button.setOnMousePressed(e -> animateScale(button, 0.96, 70));
        button.setOnMouseReleased(e -> animateScale(button, 1.06, 90));

        return button;
    }

    public BorderPane getRoot() {
        return loginroot;
    }
}
