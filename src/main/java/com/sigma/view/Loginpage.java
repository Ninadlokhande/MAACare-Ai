package com.sigma.view;

import com.sigma.controller.Controller;
import com.sigma.model.MotherWlcModel;
import com.sigma.view.adminpages.AdminDashboard;
import com.sigma.view.doctorpages.DoctorDashboard;
import com.sigma.view.doctorpages.DoctorInformationPage;
import com.sigma.view.motherPages.MotherWelcome;
import com.sigma.view.motherPages.MotherDashBoard;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import com.sigma.view.HospitalInfoPage;

import java.util.prefs.Preferences;

public class Loginpage {

        public BorderPane loginroot;
        private Scene loginpagScene;
        public String role = "";

        private final Controller controller = new Controller();

        private Button signUpButton;
        private Text orText;
        private HBox createAccountBox;

        private StackPane loadingOverlay;
        private BorderPane loginRootForLoading;

        private static final Preferences LOGIN_PREFS = Preferences.userNodeForPackage(Loginpage.class);

        private static final String PREF_REMEMBER = "remember_login";
        private static final String PREF_EMAIL = "login_email";
        private static final String PREF_PASSWORD = "login_password";
        private static final String PREF_ROLE = "login_role";

        private static final String ADMIN_SECURITY_CODE = "12345";

        public Scene gotologinpage() {

                BorderPane root = new BorderPane();

                // Screenshot-inspired soft white / pink / lavender background
                root.setStyle(
                                "-fx-background-color: linear-gradient(to bottom right, " +
                                                "#FFFFFF 0%, #FFF7FA 42%, #F7F0FF 72%, #EEE7FF 100%);");

                // =========================================================
                // LEFT SIDE
                // =========================================================

                VBox leftSide = new VBox(14);
                leftSide.setAlignment(Pos.CENTER);
                leftSide.setPadding(new Insets(40, 50, 40, 60));
                leftSide.setPrefWidth(550);

                String imagePath = "assets/images/logo/logo.png";
                Image logoImage;

                try {
                        logoImage = new Image(imagePath);
                } catch (Exception ex) {
                        logoImage = null;
                }

                ImageView logoView = new ImageView();
                if (logoImage != null) {
                        logoView.setImage(logoImage);
                }

                logoView.setPreserveRatio(true);
                logoView.setSmooth(true);
                logoView.fitWidthProperty().bind(
                                leftSide.widthProperty().multiply(0.75));

                Text logininfo = new Text("");
                logininfo.setStyle(
                                "-fx-fill: #13CEE3;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;");

                Text brandName = new Text("MaaCare AI");
                brandName.setStyle(
                                "-fx-fill: linear-gradient(to right, #E84A87, #9B4DCC);" +
                                                "-fx-font-size: 48px;" +
                                                "-fx-font-weight: bold;");

                Text tagline1 = new Text(
                                "Care for Mom. Care for Baby. Care for Life.");
                tagline1.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;");

                Text description1 = new Text(
                                "Your AI-Powered Companion for");
                description1.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;");

                Text descriptionPink = new Text(" Mother & Child");
                descriptionPink.setStyle(
                                "-fx-fill: #E84A87;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;");

                Text description2 = new Text(" Healthcare");
                description2.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;");

                HBox description = new HBox(
                                description1, descriptionPink, description2);
                description.setAlignment(Pos.CENTER);

                Text info = new Text(
                                "Track. Monitor. Get AI Guidance.");
                info.setStyle(
                                "-fx-fill: #666680;" +
                                                "-fx-font-size: 16px;");

                Text smallInfo = new Text(
                                "Because every mom and baby deserves the best care.");
                smallInfo.setStyle(
                                "-fx-fill: #77778D;" +
                                                "-fx-font-size: 14px;");

                HBox features = new HBox(25);
                features.setAlignment(Pos.CENTER);
                features.setPadding(new Insets(20, 0, 0, 0));

                features.getChildren().addAll(
                                createFeature("🤰", "Pregnancy", "Tracking"),
                                createFeature("❤️", "Health", "Records"),
                                createFeature("🤖", "AI Health", "Assistant"),
                                createFeature("🏥", "Doctor &", "Hospital"));

                leftSide.getChildren().addAll(
                                logoView,
                                brandName,
                                tagline1,
                                description,
                                info,
                                smallInfo,
                                features,
                                logininfo);

                // =========================================================
                // RIGHT SIDE
                // =========================================================

                StackPane rightSide = new StackPane();
                rightSide.setPadding(new Insets(35, 60, 35, 35));

                VBox loginCard = new VBox(10);
                loginCard.setAlignment(Pos.TOP_CENTER);
                loginCard.setPadding(new Insets(32, 38, 28, 38));
                loginCard.setMaxWidth(800);
                loginCard.setMaxHeight(850);

                loginCard.setEffect(new javafx.scene.effect.DropShadow(
                                28, 0, 10, Color.rgb(70, 35, 90, 0.16)));

                loginCard.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.96);" +
                                                "-fx-background-radius: 25px;" +
                                                "-fx-border-color: rgba(220,200,230,0.55);" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 25px;");

                Text welcome = new Text("👋  Welcome Back!");
                welcome.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 32px;" +
                                                "-fx-font-weight: bold;");

                Text loginInfo = new Text(
                                "Login to your MaaCare AI account");
                loginInfo.setStyle(
                                "-fx-fill: #666680;" +
                                                "-fx-font-size: 16px;");

                Text roleTitle = new Text("🎯  Select Your Role");
                roleTitle.setStyle(
                                "-fx-fill: #7B3FC6;" +
                                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;");

                Text selectedRole = new Text("No role selected");
                selectedRole.setStyle(
                                "-fx-fill: #D82F82;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;");

                HBox roles = new HBox(10);
                roles.setAlignment(Pos.CENTER);

                Button mother = createRoleButton(
                                "Mother\nFamily",
                                "/assets/images/mothericon.jpeg");

                Button doctor = createRoleButton(
                                "Doctor",
                                "/assets/images/doctoricon.jpeg");

                Button hospital = createRoleButton(
                                "Hospital",
                                "/assets/images/hospitalicon.jpeg");

                Button asha = createRoleButton(
                                "ASHA Worker",
                                "/assets/images/ashaworkericon.jpeg");

                Button admin = createRoleButton(
                                "Admin",
                                "/assets/images/adminicon.jpeg");

                TextField email = new TextField();
                email.setPromptText("📧  Email / Phone Number");
                email.setPrefHeight(55);
                addFieldMotion(email);
                email.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #DDD9E6;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 12px;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-padding: 0 18px;");

                PasswordField password = new PasswordField();
                TextField visiblePassword = new TextField();

                password.setPromptText("🔒  Password");
                visiblePassword.setPromptText("🔒  Password");
                password.setPrefHeight(55);
                visiblePassword.setPrefHeight(55);

                String passwordStyle = "-fx-background-color: white;" +
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
                showPassword.setMinWidth(70);
                showPassword.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #713CC3;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                HBox passwordBox = new HBox(5);
                passwordBox.setAlignment(Pos.CENTER);
                HBox.setHgrow(password, Priority.ALWAYS);
                HBox.setHgrow(visiblePassword, Priority.ALWAYS);
                passwordBox.getChildren().addAll(
                                password, visiblePassword, showPassword);

                final boolean[] syncingPassword = { false };

                password.textProperty().addListener((obs, oldValue, newValue) -> {
                        if (!syncingPassword[0] && !visiblePassword.isVisible()) {
                                syncingPassword[0] = true;
                                visiblePassword.setText(newValue);
                                syncingPassword[0] = false;
                        }
                });

                visiblePassword.textProperty().addListener(
                                (obs, oldValue, newValue) -> {
                                        if (!syncingPassword[0] &&
                                                        visiblePassword.isVisible()) {
                                                syncingPassword[0] = true;
                                                password.setText(newValue);
                                                syncingPassword[0] = false;
                                        }
                                });

                showPassword.setOnAction(e -> {
                        if (!visiblePassword.isVisible()) {
                                visiblePassword.setText(password.getText());
                                password.setVisible(false);
                                password.setManaged(false);
                                visiblePassword.setVisible(true);
                                visiblePassword.setManaged(true);
                                visiblePassword.requestFocus();
                                showPassword.setText("🙈  Hide");
                        } else {
                                password.setText(visiblePassword.getText());
                                visiblePassword.setVisible(false);
                                visiblePassword.setManaged(false);
                                password.setVisible(true);
                                password.setManaged(true);
                                password.requestFocus();
                                showPassword.setText("👁  Show");
                        }
                });

                addButtonMotion(showPassword, 1.08);

                // =========================================================
                // ADMIN SECURITY CODE
                // =========================================================

                PasswordField securityCode = new PasswordField();
                securityCode.setPromptText("🛡️  Admin Security Code");
                securityCode.setPrefHeight(50);
                securityCode.setVisible(false);
                securityCode.setManaged(false);
                securityCode.setStyle(passwordStyle);
                addFieldMotion(securityCode);

                // =========================================================
                // FORGOT PASSWORD
                // =========================================================

                Button forgotPassword = new Button("🔑  Forgot Password?");
                forgotPassword.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #713CC3;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                forgotPassword.setOnAction(e -> showStatus(
                                null,
                                "Password reset is handled through Firebase."));

                CheckBox remember = new CheckBox("Remember me");
                remember.setStyle(
                                "-fx-text-fill: #666680;" +
                                                "-fx-font-size: 14px;");

                HBox options = new HBox(10, remember, forgotPassword);
                options.setAlignment(Pos.CENTER_LEFT);
                HBox.setHgrow(remember, Priority.ALWAYS);

                remember.setOnAction(e -> {
                        if (!remember.isSelected()) {
                                clearRememberedLogin();
                        }
                });

                // =========================================================
                // ROLE ACTIONS
                // =========================================================

                mother.setOnAction(e -> {
                        role = "mother";
                        animateRoleSelection(mother);
                        selectedRole.setText("Role : Mother");
                        securityCode.clear();
                        securityCode.setVisible(false);
                        securityCode.setManaged(false);
                        showSignUpControls(true);
                });

                doctor.setOnAction(e -> {
                        role = "doctor";
                        animateRoleSelection(doctor);
                        selectedRole.setText("Role : Doctor");
                        securityCode.clear();
                        securityCode.setVisible(false);
                        securityCode.setManaged(false);
                        showSignUpControls(true);
                });

                hospital.setOnAction(e -> {
                        role = "hospital";
                        animateRoleSelection(hospital);
                        selectedRole.setText("Role : Hospital");
                        securityCode.clear();
                        securityCode.setVisible(false);
                        securityCode.setManaged(false);
                        showSignUpControls(true);
                });

                asha.setOnAction(e -> {
                        role = "asha";
                        animateRoleSelection(asha);
                        selectedRole.setText("Role : ASHA Worker");
                        securityCode.clear();
                        securityCode.setVisible(false);
                        securityCode.setManaged(false);
                        showSignUpControls(true);
                });

                admin.setOnAction(e -> {
                        role = "admin";
                        animateRoleSelection(admin);
                        selectedRole.setText("Role : Admin");
                        securityCode.setVisible(true);
                        securityCode.setManaged(true);
                        showSignUpControls(false);
                });

                roles.getChildren().addAll(
                                mother, doctor, hospital, asha, admin);

                Text loginStatus = new Text("");
                loginStatus.setStyle(
                                "-fx-fill: #D82F82;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");
                loginStatus.setWrappingWidth(650);

                Button login = new Button("🔐  Login");
                login.setMaxWidth(Double.MAX_VALUE);
                login.setPrefHeight(52);
                login.setStyle(
                                "-fx-background-color: linear-gradient(to right, #E84A87, #9B4DCC);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-cursor: hand;");

                addButtonMotion(login, 1.025);

                login.setOnAction(e -> {
                        loginStatus.setText("");
                        animateStatusMessage(loginStatus);

                        String enteredEmail = email.getText().trim();
                        String enteredPassword = visiblePassword.isVisible()
                                        ? visiblePassword.getText()
                                        : password.getText();

                        if (role == null || role.isBlank()) {
                                loginStatus.setText("⚠  Please select your role.");
                                return;
                        }

                        if (enteredEmail.isBlank()) {
                                loginStatus.setText("📧  Please enter your email.");
                                email.requestFocus();
                                return;
                        }

                        if (enteredPassword.isBlank()) {
                                loginStatus.setText("🔒  Please enter your password.");
                                password.requestFocus();
                                return;
                        }

                        if ("admin".equals(role)) {
                                String enteredSecurityCode = securityCode.getText().trim();

                                if (enteredSecurityCode.isBlank()) {
                                        loginStatus.setText(
                                                        "Please enter Admin Security Code.");
                                        securityCode.requestFocus();
                                        return;
                                }

                                if (!enteredSecurityCode.equals(
                                                ADMIN_SECURITY_CODE)) {
                                        loginStatus.setText(
                                                        "❌  Invalid Admin Security Code.");
                                        securityCode.clear();
                                        securityCode.requestFocus();
                                        return;
                                }
                        }

                        setLoginLoading(true, loginStatus);

                        Task<Boolean> loginTask = new Task<>() {
                                @Override
                                protected Boolean call() throws Exception {
                                        return controller.signin(
                                                        enteredEmail, enteredPassword);
                                }
                        };

                        loginTask.setOnSucceeded(event -> {
                                boolean authenticated = loginTask.getValue();

                                if (!authenticated) {
                                        loginStatus.setText(
                                                        "❌  Invalid email or password.");
                                        password.clear();
                                        visiblePassword.clear();
                                        setLoginLoading(false, loginStatus);
                                        return;
                                }

                                saveRememberedLogin(
                                                remember.isSelected(),
                                                enteredEmail,
                                                enteredPassword,
                                                role);

                                try {
                                        openDashboardForLogin(role);

                                        email.clear();
                                        password.clear();
                                        visiblePassword.clear();
                                        securityCode.clear();

                                        setLoginLoading(false, loginStatus);

                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                        loginStatus.setText(
                                                        "Unable to open dashboard.");
                                        setLoginLoading(false, loginStatus);
                                }
                        });

                        loginTask.setOnFailed(event -> {
                                Throwable ex = loginTask.getException();
                                if (ex != null) {
                                        ex.printStackTrace();
                                }

                                loginStatus.setText(
                                                "Authentication error. Please try again.");
                                setLoginLoading(false, loginStatus);
                        });

                        Thread loginThread = new Thread(
                                        loginTask, "Firebase-Login-Thread");
                        loginThread.setDaemon(true);
                        loginThread.start();
                });

                Text or = new Text("OR");
                orText = or;
                or.setStyle(
                                "-fx-fill: #666680;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;");

                Button signUp = new Button("📝  Sign Up");
                signUpButton = signUp;
                signUp.setMaxWidth(Double.MAX_VALUE);
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
                                                "-fx-cursor: hand;");

                addButtonMotion(signUp, 1.025);

                signUp.setOnAction(e -> {
                        if (role == null || role.isBlank()) {
                                loginStatus.setText(
                                                "Please select your role first.");
                                return;
                        }

                        if ("admin".equals(role)) {
                                loginStatus.setText(
                                                "Admin accounts cannot be created here.");
                                return;
                        }

                        if ("doctor".equals(role)) {
                                new DoctorInformationPage().show(
                                                Welcomepage.stage);
                                return;
                        }

                        if ("asha".equals(role)) {
                                new Ashaworkerinformationpage().show(
                                                Welcomepage.stage);
                                return;
                        }

                        if ("hospital".equals(role)) {
                                HospitalInfoPage.show(Welcomepage.stage);
                                return;
                        }

                        if (!"mother".equals(role)) {
                                loginStatus.setText("Invalid role selected.");
                                return;
                        }

                        String enteredEmail = email.getText().trim();
                        String enteredPassword = visiblePassword.isVisible()
                                        ? visiblePassword.getText()
                                        : password.getText();

                        if (enteredEmail.isBlank()) {
                                loginStatus.setText(
                                                "📧  Please enter your email.");
                                email.requestFocus();
                                return;
                        }

                        if (enteredPassword.isBlank()) {
                                loginStatus.setText(
                                                "🔒  Please enter your password.");
                                password.requestFocus();
                                return;
                        }

                        try {
                                controller.signup(
                                                enteredEmail, enteredPassword);

                                if (controller.status_code == 200) {
                                        try {
                                                setLoginLoading(true, loginStatus);
                                                openDashboardAfterSignup(role);
                                                return;
                                        } catch (Exception navigationException) {
                                                navigationException.printStackTrace();
                                                loginStatus.setText(
                                                                "Sign up successful, but unable to open the next page.");
                                                setLoginLoading(false, loginStatus);
                                        }
                                } else {
                                        String error = controller.getLastError();
                                        loginStatus.setText(
                                                        error == null || error.isBlank()
                                                                        ? "Sign up failed."
                                                                        : error);
                                }

                        } catch (Exception ex) {
                                ex.printStackTrace();
                                loginStatus.setText(
                                                "Sign up failed. Please try again.");
                        }
                });

                HBox createAccount = new HBox(6);
                createAccountBox = createAccount;
                createAccount.setAlignment(Pos.CENTER);

                Text accountText = new Text(
                                "Don't have an account?");
                accountText.setStyle(
                                "-fx-fill: #77778D;" +
                                                "-fx-font-size: 14px;");

                Text createText = new Text(" Sign up");
                createText.setStyle(
                                "-fx-fill: #D82F82;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;");

                createAccount.getChildren().addAll(
                                accountText, createText);

                showSignUpControls(true);

                loadRememberedLogin(
                                email,
                                password,
                                visiblePassword,
                                remember,
                                mother,
                                doctor,
                                hospital,
                                asha,
                                admin);

                VBox livePanel = createLiveStatusPanel();

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
                                options,
                                login,
                                loginStatus,
                                or,
                                signUp,
                                createAccount);

                rightSide.getChildren().add(loginCard);

                root.setLeft(leftSide);
                root.setCenter(rightSide);
                BorderPane.setAlignment(leftSide, Pos.CENTER);

                Pane ambientLayer = createAmbientAnimationLayer();
                ambientLayer.setMouseTransparent(true);

                StackPane loadingOverlay = createLoadingOverlay();
                loadingOverlay.setVisible(false);
                loadingOverlay.setManaged(false);

                StackPane sceneRoot = new StackPane(
                                root, ambientLayer, loadingOverlay);

                loginroot = root;

                loginpagScene = new Scene(
                                sceneRoot,
                                scenesettings.rectanguler2d.getWidth(),
                                scenesettings.rectanguler2d.getHeight());

                this.loadingOverlay = loadingOverlay;
                this.loginRootForLoading = root;

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
                                features);

                return loginpagScene;
        }

        private void openDashboardForLogin(
                        String selectedRole) throws Exception {

                if ("admin".equals(selectedRole)) {
                        AdminDashboard adminDashboard = new AdminDashboard();

                        Scene adminScene = adminDashboard.gotoAdminDashboard();

                        Welcomepage.stage.setScene(adminScene);
                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                if ("doctor".equals(selectedRole)) {
                        String firebaseUid = controller.getFirebaseUid();

                        if (firebaseUid == null ||
                                        firebaseUid.trim().isEmpty()) {
                                throw new IllegalStateException(
                                                "Firebase UID was not generated for Doctor login.");
                        }

                        DoctorDashboard.setCurrentDoctorUid(
                                        firebaseUid);

                        DoctorDashboard.showDashboard(
                                        Welcomepage.stage, firebaseUid);

                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                if ("hospital".equals(selectedRole)) {
                        Dashboard dashboard = new Dashboard();
                        dashboard.show(Welcomepage.stage);

                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                if ("asha".equals(selectedRole)) {
                        Asha_workerdashboard ashaDashboard = new Asha_workerdashboard();

                        Scene ashaScene = ashaDashboard.run();

                        Welcomepage.stage.setScene(ashaScene);
                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                if ("mother".equals(selectedRole)) {
                        MotherWlcModel motherModel = new MotherWlcModel();

                        MotherDashBoard motherDashboard = new MotherDashBoard(motherModel);

                        Scene dashboardScene = motherDashboard.getmotherDashboardScene();

                        Welcomepage.stage.setScene(dashboardScene);
                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                throw new IllegalArgumentException(
                                "Invalid role selected: " + selectedRole);
        }

        private void openDashboardAfterSignup(
                        String selectedRole) throws Exception {

                if ("mother".equals(selectedRole)) {
                        MotherWelcome motherWelcome = new MotherWelcome();

                        Scene motherWelcomeScene = motherWelcome.getMotherWelcomeScene();

                        Welcomepage.stage.setScene(
                                        motherWelcomeScene);
                        Welcomepage.stage.setMaximized(true);
                        Welcomepage.stage.show();
                        Welcomepage.stage.toFront();
                        return;
                }

                if ("doctor".equals(selectedRole)) {
                        new DoctorInformationPage().show(
                                        Welcomepage.stage);
                        return;
                }

                if ("hospital".equals(selectedRole)) {
                        HospitalInfoPage.show(
                                        Welcomepage.stage);
                        return;
                }

                if ("asha".equals(selectedRole)) {
                        new Ashaworkerinformationpage().show(
                                        Welcomepage.stage);
                        return;
                }

                if ("admin".equals(selectedRole)) {
                        throw new IllegalArgumentException(
                                        "Admin accounts cannot be created here.");
                }

                throw new IllegalArgumentException(
                                "Invalid role selected: " + selectedRole);
        }

        private void showSignUpControls(boolean visible) {
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

        private void saveRememberedLogin(
                        boolean rememberMe,
                        String email,
                        String password,
                        String selectedRole) {

                if (!rememberMe) {
                        clearRememberedLogin();
                        return;
                }

                if (email == null || email.isBlank() ||
                                password == null || password.isBlank() ||
                                selectedRole == null || selectedRole.isBlank()) {
                        return;
                }

                LOGIN_PREFS.putBoolean(PREF_REMEMBER, true);
                LOGIN_PREFS.put(PREF_EMAIL, email);
                LOGIN_PREFS.put(PREF_PASSWORD, password);
                LOGIN_PREFS.put(PREF_ROLE, selectedRole);

                try {
                        LOGIN_PREFS.flush();
                } catch (Exception ignored) {
                }
        }

        private void clearRememberedLogin() {
                LOGIN_PREFS.remove(PREF_REMEMBER);
                LOGIN_PREFS.remove(PREF_EMAIL);
                LOGIN_PREFS.remove(PREF_PASSWORD);
                LOGIN_PREFS.remove(PREF_ROLE);

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
                                PREF_REMEMBER, false)) {
                        return;
                }

                String savedEmail = LOGIN_PREFS.get(PREF_EMAIL, "");

                String savedPassword = LOGIN_PREFS.get(PREF_PASSWORD, "");

                String savedRole = LOGIN_PREFS.get(PREF_ROLE, "");

                if (savedEmail.isBlank() ||
                                savedPassword.isBlank() ||
                                savedRole.isBlank()) {
                        clearRememberedLogin();
                        return;
                }

                email.setText(savedEmail);
                password.setText(savedPassword);
                visiblePassword.setText(savedPassword);
                remember.setSelected(true);

                switch (savedRole) {
                        case "mother":
                                mother.fire();
                                break;
                        case "doctor":
                                doctor.fire();
                                break;
                        case "hospital":
                                hospital.fire();
                                break;
                        case "asha":
                                asha.fire();
                                break;
                        case "admin":
                                admin.fire();
                                break;
                        default:
                                clearRememberedLogin();
                                break;
                }
        }

        private StackPane createLoadingOverlay() {
                StackPane overlay = new StackPane();

                overlay.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.96);");

                VBox loadingBox = new VBox(15);
                loadingBox.setAlignment(Pos.CENTER);

                ProgressIndicator progress = new ProgressIndicator(-1);

                progress.setPrefSize(65, 65);

                Text loadingTitle = new Text(
                                "Opening your MaaCare AI account...");
                loadingTitle.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;");

                Text loadingText = new Text(
                                "Preparing your dashboard. Please wait...");
                loadingText.setStyle(
                                "-fx-fill: #77778D;" +
                                                "-fx-font-size: 14px;");

                loadingBox.getChildren().addAll(
                                progress, loadingTitle, loadingText);

                overlay.getChildren().add(loadingBox);
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
                        loadingRootPulse();
                        loginRootForLoading.setDisable(true);
                        loadingStatusPulse(loadingOverlay);
                        loginStatus.setText("");
                } else {
                        loginRootForLoading.setDisable(false);

                        FadeTransition fadeOut = new FadeTransition(
                                        Duration.millis(220),
                                        loadingOverlay);

                        fadeOut.setFromValue(
                                        loadingOverlay.getOpacity());
                        fadeOut.setToValue(0);

                        fadeOut.setOnFinished(e -> {
                                loadingOverlay.setVisible(false);
                                loadingOverlay.setManaged(false);
                                loadingOverlay.setOpacity(1);
                        });

                        fadeOut.play();
                }
        }

        private void loadingRootPulse() {
                // Intentionally lightweight: avoids blocking the JavaFX thread.
        }

        private void showStatus(Text node, String message) {
                if (node != null) {
                        node.setText(message);
                        animateStatusMessage(node);
                }
        }

        private Pane createAmbientAnimationLayer() {
                Pane layer = new Pane();

                Circle glow1 = createGlowCircle(150, 0.10, "#E84A87");
                Circle glow2 = createGlowCircle(105, 0.08, "#9B4DCC");
                Circle glow3 = createGlowCircle(75, 0.07, "#E84A87");
                Circle glow4 = createGlowCircle(120, 0.06, "#9B4DCC");

                glow1.setTranslateX(-300);
                glow1.setTranslateY(-230);

                glow2.setTranslateX(370);
                glow2.setTranslateY(-180);

                glow3.setTranslateX(430);
                glow3.setTranslateY(260);

                glow4.setTranslateX(-390);
                glow4.setTranslateY(300);

                layer.getChildren().addAll(
                                glow1, glow2, glow3, glow4);

                animateAmbientCircle(
                                glow1, 1500, 55, 35);
                animateAmbientCircle(
                                glow2, 1900, -45, 45);
                animateAmbientCircle(
                                glow3, 1700, -35, -50);
                animateAmbientCircle(
                                glow4, 2200, 50, -35);

                return layer;
        }

        private Circle createGlowCircle(
                        double radius,
                        double opacity,
                        String color) {

                Circle circle = new Circle(radius);
                circle.setOpacity(opacity);
                circle.setFill(Color.web(color));
                circle.setEffect(new GaussianBlur(28));
                return circle;
        }

        private void animateAmbientCircle(
                        Circle circle,
                        double millis,
                        double x,
                        double y) {

                TranslateTransition move = new TranslateTransition(
                                Duration.millis(millis),
                                circle);

                move.setByX(x);
                move.setByY(y);
                move.setInterpolator(
                                Interpolator.EASE_BOTH);
                move.setAutoReverse(true);
                move.setCycleCount(
                                TranslateTransition.INDEFINITE);
                move.play();

                RotateTransition rotate = new RotateTransition(
                                Duration.millis(millis * 1.7),
                                circle);

                rotate.setByAngle(360);
                rotate.setInterpolator(
                                Interpolator.LINEAR);
                rotate.setCycleCount(
                                RotateTransition.INDEFINITE);
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

                FadeTransition leftFade = new FadeTransition(
                                Duration.millis(700),
                                leftSide);

                leftFade.setFromValue(0);
                leftFade.setToValue(1);

                TranslateTransition leftSlide = new TranslateTransition(
                                Duration.millis(700),
                                leftSide);

                leftSlide.setFromX(-45);
                leftSlide.setToX(0);
                leftSlide.setInterpolator(
                                Interpolator.EASE_OUT);

                new ParallelTransition(
                                leftFade, leftSlide).play();

                FadeTransition cardFade = new FadeTransition(
                                Duration.millis(750),
                                loginCard);

                cardFade.setFromValue(0);
                cardFade.setToValue(1);

                TranslateTransition cardSlide = new TranslateTransition(
                                Duration.millis(750),
                                loginCard);

                cardSlide.setFromX(55);
                cardSlide.setToX(0);
                cardSlide.setInterpolator(
                                Interpolator.EASE_OUT);

                ParallelTransition cardEntrance = new ParallelTransition(
                                cardFade, cardSlide);

                cardEntrance.setDelay(
                                Duration.millis(120));
                cardEntrance.play();

                animateReveal(welcome, 220, 0, -12);
                animateReveal(loginInfo, 300, 0, -10);
                animateReveal(roleTitle, 380, 0, -10);
                animateReveal(roles, 460, 0, -12);
                animateReveal(email, 540, 0, -10);
                animateReveal(passwordBox, 600, 0, -10);
                animateReveal(login, 700, 0, -8);
                animateReveal(features, 650, 0, 10);

                TranslateTransition logoFloat = new TranslateTransition(
                                Duration.seconds(2.4),
                                logoView);

                logoFloat.setByY(-8);
                logoFloat.setAutoReverse(true);
                logoFloat.setCycleCount(
                                TranslateTransition.INDEFINITE);
                logoFloat.setInterpolator(
                                Interpolator.EASE_BOTH);
                logoFloat.play();

                for (Node node : features.getChildren()) {
                        ScaleTransition pulse = new ScaleTransition(
                                        Duration.seconds(2.2),
                                        node);

                        pulse.setFromX(1.0);
                        pulse.setFromY(1.0);
                        pulse.setToX(1.035);
                        pulse.setToY(1.035);
                        pulse.setAutoReverse(true);
                        pulse.setCycleCount(
                                        ScaleTransition.INDEFINITE);
                        pulse.setInterpolator(
                                        Interpolator.EASE_BOTH);
                        pulse.play();
                }
        }

        private void animateReveal(
                        Node node,
                        double delayMillis,
                        double fromX,
                        double fromY) {

                node.setOpacity(0);
                node.setTranslateX(fromX);
                node.setTranslateY(fromY);

                FadeTransition fade = new FadeTransition(
                                Duration.millis(420),
                                node);

                fade.setFromValue(0);
                fade.setToValue(1);

                TranslateTransition slide = new TranslateTransition(
                                Duration.millis(420),
                                node);

                slide.setFromX(fromX);
                slide.setFromY(fromY);
                slide.setToX(0);
                slide.setToY(0);
                slide.setInterpolator(
                                Interpolator.EASE_OUT);

                ParallelTransition transition = new ParallelTransition(
                                fade, slide);

                transition.setDelay(
                                Duration.millis(delayMillis));
                transition.play();
        }

        private void animateRoleSelection(
                        Button selectedButton) {

                ScaleTransition pop = new ScaleTransition(
                                Duration.millis(150),
                                selectedButton);

                pop.setFromX(1.0);
                pop.setFromY(1.0);
                pop.setToX(1.10);
                pop.setToY(1.10);
                pop.setAutoReverse(true);
                pop.setCycleCount(2);
                pop.setInterpolator(
                                Interpolator.EASE_OUT);
                pop.play();
        }

        private void addButtonMotion(
                        Button button,
                        double hoverScale) {

                button.setOnMouseEntered(e -> animateScale(
                                button, hoverScale, 120));

                button.setOnMouseExited(e -> animateScale(
                                button, 1.0, 120));

                button.setOnMousePressed(e -> animateScale(
                                button, 0.97, 70));

                button.setOnMouseReleased(e -> animateScale(
                                button, hoverScale, 90));
        }

        private void loadingStatusPulse(
                        StackPane overlay) {

                overlay.setOpacity(0);

                FadeTransition fadeIn = new FadeTransition(
                                Duration.millis(250),
                                overlay);

                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.play();
        }

        private VBox createLiveStatusPanel() {
                VBox panel = new VBox(8);

                panel.setAlignment(Pos.CENTER_LEFT);
                panel.setPadding(
                                new Insets(12, 16, 12, 16));
                panel.setMaxWidth(
                                Double.MAX_VALUE);

                panel.setStyle(
                                "-fx-background-color: rgba(248,244,255,0.90);" +
                                                "-fx-background-radius: 16px;" +
                                                "-fx-border-color: rgba(155,77,204,0.18);" +
                                                "-fx-border-radius: 16px;" +
                                                "-fx-border-width: 1px;");

                HBox header = new HBox(8);
                header.setAlignment(Pos.CENTER_LEFT);

                Circle dot = new Circle(5);
                dot.setFill(Color.web("#35C98B"));

                Text live = new Text(
                                "🟢  LIVE CARE NETWORK");
                live.setStyle(
                                "-fx-fill: #49308C;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;");

                Text status = new Text(
                                "All systems operational");
                status.setStyle(
                                "-fx-fill: #35A979;" +
                                                "-fx-font-size: 11px;");

                HBox.setHgrow(status, Priority.ALWAYS);

                header.getChildren().addAll(
                                dot, live, status);

                ProgressBar activity = new ProgressBar(0.72);

                activity.setMaxWidth(
                                Double.MAX_VALUE);
                activity.setPrefHeight(5);

                activity.setStyle(
                                "-fx-accent: #E84A87;" +
                                                "-fx-control-inner-background: #EEEAF5;");

                Text activityText = new Text(
                                "AI monitoring • Secure connection • Real-time assistance");

                activityText.setStyle(
                                "-fx-fill: #77778D;" +
                                                "-fx-font-size: 10px;");

                panel.getChildren().addAll(
                                header, activity, activityText);

                ScaleTransition dotPulse = new ScaleTransition(
                                Duration.seconds(0.9),
                                dot);

                dotPulse.setFromX(0.75);
                dotPulse.setFromY(0.75);
                dotPulse.setToX(1.35);
                dotPulse.setToY(1.35);
                dotPulse.setAutoReverse(true);
                dotPulse.setCycleCount(
                                ScaleTransition.INDEFINITE);
                dotPulse.setInterpolator(
                                Interpolator.EASE_BOTH);
                dotPulse.play();

                Timeline activityMotion = new Timeline(
                                new KeyFrame(
                                                Duration.ZERO,
                                                new KeyValue(
                                                                activity.progressProperty(),
                                                                0.55)),
                                new KeyFrame(
                                                Duration.seconds(2.0),
                                                new KeyValue(
                                                                activity.progressProperty(),
                                                                0.88)));

                activityMotion.setAutoReverse(true);
                activityMotion.setCycleCount(
                                Timeline.INDEFINITE);
                activityMotion.play();

                return panel;
        }

        private void animateStatusMessage(
                        Text node) {

                node.setOpacity(0);
                node.setTranslateY(8);

                FadeTransition fade = new FadeTransition(
                                Duration.millis(280),
                                node);

                fade.setFromValue(0);
                fade.setToValue(1);

                TranslateTransition slide = new TranslateTransition(
                                Duration.millis(280),
                                node);

                slide.setFromY(8);
                slide.setToY(0);
                slide.setInterpolator(
                                Interpolator.EASE_OUT);

                new ParallelTransition(
                                fade, slide).play();
        }

        private void addFieldMotion(Control field) {
                field.setOnMouseEntered(e -> {
                        ScaleTransition s = new ScaleTransition(
                                        Duration.millis(120),
                                        field);
                        s.setToX(1.012);
                        s.setToY(1.012);
                        s.play();
                });

                field.setOnMouseExited(e -> {
                        ScaleTransition s = new ScaleTransition(
                                        Duration.millis(120),
                                        field);
                        s.setToX(1);
                        s.setToY(1);
                        s.play();
                });
        }

        private VBox createFeature(
                        String symbol,
                        String line1,
                        String line2) {

                Text icon = new Text(symbol);
                icon.setStyle(
                                "-fx-fill: #E84A87;" +
                                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;");

                Text text1 = new Text(line1);
                text1.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                Text text2 = new Text(line2);
                text2.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                VBox box = new VBox(2);
                box.setAlignment(Pos.CENTER);
                box.getChildren().addAll(
                                icon, text1, text2);

                return box;
        }

        private Button createRoleButton(
                        String text,
                        String imagePath) {

                Button button = new Button();

                button.setPrefWidth(105);
                button.setPrefHeight(115);
                button.setMinWidth(105);
                button.setMinHeight(115);

                Image image = null;

                try {
                        image = new Image(
                                        imagePath, 52, 52, true, true);
                } catch (Exception e) {
                        System.out.println(
                                        "[LOGIN] Unable to load role image: "
                                                        + imagePath);
                }

                ImageView imageView = new ImageView();

                if (image != null) {
                        imageView.setImage(image);
                }

                imageView.setFitWidth(52);
                imageView.setFitHeight(52);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);

                Text buttonText = new Text(text);
                buttonText.setTextAlignment(
                                TextAlignment.CENTER);
                buttonText.setStyle(
                                "-fx-fill: #24234F;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;");

                VBox content = new VBox(6);
                content.setAlignment(Pos.CENTER);
                content.getChildren().addAll(
                                imageView, buttonText);

                button.setGraphic(content);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16px;" +
                                                "-fx-border-color: #E2DFEA;" +
                                                "-fx-border-radius: 16px;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 8px;");

                button.setOnMouseEntered(e -> {
                        button.setStyle(
                                        "-fx-background-color: #FFF0F6;" +
                                                        "-fx-background-radius: 16px;" +
                                                        "-fx-border-color: #F54B87;" +
                                                        "-fx-border-radius: 16px;" +
                                                        "-fx-border-width: 2px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 8px;");

                        buttonText.setStyle(
                                        "-fx-fill: #C92F78;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;");

                        animateScale(button, 1.06, 120);
                });

                button.setOnMouseExited(e -> {
                        button.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-background-radius: 16px;" +
                                                        "-fx-border-color: #E2DFEA;" +
                                                        "-fx-border-radius: 16px;" +
                                                        "-fx-border-width: 1px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 8px;");

                        buttonText.setStyle(
                                        "-fx-fill: #24234F;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;");

                        animateScale(button, 1.0, 120);
                });

                button.setOnMousePressed(e -> animateScale(button, 0.96, 70));

                button.setOnMouseReleased(e -> animateScale(button, 1.06, 90));

                return button;
        }

        private void animateScale(
                        Node node,
                        double scale,
                        double millis) {

                ScaleTransition transition = new ScaleTransition(
                                Duration.millis(millis),
                                node);

                transition.setToX(scale);
                transition.setToY(scale);
                transition.setInterpolator(
                                Interpolator.EASE_OUT);
                transition.play();
        }

        public BorderPane getRoot() {
                return loginroot;
        }
}
