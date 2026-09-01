package com.sigma.view;

import com.sigma.controller.Controller;
import com.sigma.view.adminpages.AdminDashboard;

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

    // =============================================================
    // FIELDS
    // =============================================================

    private Scene loginpagScene;

    public String role = "";

    Controller controller = new Controller();


    /*
     * =============================================================
     * FUTURE ROLE PAGE REFERENCES
     * =============================================================
     *
     * Keep these commented for now.
     *
     * When the respective page/dashboard is created, uncomment
     * the required field and use it after successful login.
     *
     * Example:
     *
     * private MotherDashboard motherDashboard;
     * private DoctorDashboard doctorDashboard;
     * private HospitalDashboard hospitalDashboard;
     * private AshaDashboard ashaDashboard;
     * private AmbulanceDashboard ambulanceDashboard;
     *
     * Loginpage itself does NOT need to keep AdminDashboard.
     * AdminDashboard is created once after successful admin login.
     */


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
        // LEFT SIDE - BRANDING
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

        String IMAGE_PATH =
                "file:Maacare-Ai\\src\\main\\resources\\assets\\images\\logo\\logo.png";

        Image logoImage =
                new Image(IMAGE_PATH);

        ImageView logoView =
                new ImageView(logoImage);

        logoView.setPreserveRatio(true);

        logoView.setSmooth(true);

        logoView.fitWidthProperty().bind(
                leftSide.widthProperty().multiply(0.75)
        );


        // =========================================================
        // LOGIN STATUS
        // =========================================================

        Text logininfo =
                new Text("");

        logininfo.setStyle(
                "-fx-fill: #13cee3;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================================================
        // TITLE
        // =========================================================

        Text brandName =
                new Text("MaaCare AI");

        brandName.setStyle(
                "-fx-fill: linear-gradient(to right, #E84A87, #9B4DCC);" +
                        "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================================================
        // TAGLINE
        // =========================================================

        Text tagline1 =
                new Text(
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

        Text description1 =
                new Text(
                        "Your AI-Powered Companion for"
                );

        description1.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );


        Text descriptionPink =
                new Text(
                        " Mother & Child"
                );

        descriptionPink.setStyle(
                "-fx-fill: #E84A87;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );


        Text description2 =
                new Text(
                        " Healthcare"
                );

        description2.setStyle(
                "-fx-fill: #24234F;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );


        HBox description =
                new HBox(
                        description1,
                        descriptionPink,
                        description2
                );

        description.setAlignment(
                Pos.CENTER
        );


        // =========================================================
        // INFO
        // =========================================================

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


        // =========================================================
        // FEATURES
        // =========================================================

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
                ),

                createFeature(
                        "✚",
                        "Emergency",
                        "Support"
                )
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


        // =========================================================
        // LOGIN CARD
        // =========================================================

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


        // =========================================================
        // LOGIN HEADING
        // =========================================================

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


        // =========================================================
        // ROLE SELECTION
        // =========================================================

        Text roleTitle =
                new Text(
                        "Select Your Role"
                );

        roleTitle.setStyle(
                "-fx-fill: #7B3FC6;" +
                        "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;"
        );


        Text forgot =
                new Text("");

        forgot.setStyle(
                "-fx-fill: #D82F82;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );


        HBox roles =
                new HBox(12);

        roles.setAlignment(
                Pos.CENTER
        );


        // =========================================================
        // MOTHER
        // =========================================================

        Button mother =
                createRoleButton(
                        "🤱\nMother\nFamily"
                );

        mother.setOnAction(e -> {

            role = "mother";

            forgot.setText(
                    "Role : Mother"
            );
        });


        // =========================================================
        // DOCTOR
        // =========================================================

        Button doctor =
                createRoleButton(
                        "🧑‍⚕️\nDoctor"
                );

        doctor.setOnAction(e -> {

            role = "doctor";

            forgot.setText(
                    "Role : Doctor"
            );
        });


        // =========================================================
        // HOSPITAL
        // =========================================================

        Button hospital =
                createRoleButton(
                        "🏥\nHospital"
                );

        hospital.setOnAction(e -> {

            role = "hospital";

            forgot.setText(
                    "Role : Hospital"
            );
        });


        // =========================================================
        // ASHA
        // =========================================================

        Button asha =
                createRoleButton(
                        "👩\nASHA Worker"
                );

        asha.setOnAction(e -> {

            role = "asha";

            forgot.setText(
                    "Role : ASHA WORKER"
            );
        });


        // =========================================================
        // AMBULANCE
        // =========================================================

        Button ambulance =
                createRoleButton(
                        "🚑\nAmbulance"
                );

        ambulance.setOnAction(e -> {

            role = "ambulance";

            forgot.setText(
                    "Role : Ambulance / Hospital"
            );
        });


        // =========================================================
        // ADMIN
        // =========================================================

        Button admin =
                createRoleButton(
                        "🧑‍💻\nAdmin"
                );

        admin.setOnAction(e -> {

            role = "admin";

            forgot.setText(
                    "Role : ADMIN"
            );
        });


        roles.getChildren().addAll(
                mother,
                doctor,
                hospital,
                asha,
                ambulance,
                admin
        );


        // =========================================================
        // EMAIL
        // =========================================================

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


        // =========================================================
        // PASSWORD
        // =========================================================

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


        // =========================================================
        // REMEMBER ME
        // =========================================================

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


        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button login =
                new Button(
                        "🔒   Login"
                );


        login.setOnAction(e -> {

            System.out.println(
                    "Login button pressed"
            );

            System.out.println(
                    "Selected role : " + role
            );

            System.out.println(
                    "Email : " + email.getText()
            );


            // =====================================================
            // VALIDATION
            // =====================================================

            if (role.isBlank()) {

                logininfo.setText(
                        "Please select your role"
                );

                System.out.println(
                        "[LOGIN] No role selected"
                );

                return;
            }


            if (email.getText().isBlank()) {

                logininfo.setText(
                        "Please enter your email"
                );

                System.out.println(
                        "[LOGIN] Email is empty"
                );

                return;
            }


            if (password.getText().isBlank()) {

                logininfo.setText(
                        "Please enter your password"
                );

                System.out.println(
                        "[LOGIN] Password is empty"
                );

                return;
            }


            // =====================================================
            // AUTHENTICATION
            // =====================================================

            System.out.println(
                    "[LOGIN] Authenticating user..."
            );


            boolean flag =
                    controller.signin(
                            email.getText(),
                            password.getText()
                    );


            // =====================================================
            // AUTHENTICATION SUCCESS
            // =====================================================

            if (flag) {

                System.out.println(
                        "[LOGIN] Authentication successful"
                );

                logininfo.setText(
                        "Login successful"
                );


                // =================================================
                // ADMIN
                // =================================================

                if (role.equals("admin")) {

                    System.out.println(
                            "[LOGIN] Admin role verified"
                    );

                    System.out.println(
                            "[LOGIN] Opening Admin Dashboard..."
                    );


                    /*
                     * Loginpage creates AdminDashboard here.
                     *
                     * This is intentional because Loginpage is
                     * only responsible for login/navigation.
                     *
                     * AdminDashboard itself is responsible for
                     * keeping its internal pages alive.
                     */

                    AdminDashboard adminDashboard =
                            new AdminDashboard();


                    /*
                     * Get AdminDashboard scene.
                     */

                    Scene adminDashboardScene =
                            adminDashboard.gotoAdminDashboard();


                    /*
                     * Replace login scene.
                     */

                    Welcomepage.stage.setScene(
                            adminDashboardScene
                    );


                    Welcomepage.stage.setMaximized(
                            true
                    );


                    System.out.println(
                            "[LOGIN] Admin Dashboard opened"
                    );


                    email.clear();

                    password.clear();


                    return;
                }


                // =================================================
                // MOTHER
                // =================================================

                if (role.equals("mother")) {

                    System.out.println(
                            "[LOGIN] Mother role verified"
                    );


                    /*
                     * =================================================
                     * TODO - MOTHER NAVIGATION
                     * =================================================
                     *
                     * When Mother Dashboard is ready:
                     *
                     * MotherDashboard motherDashboard =
                     *         new MotherDashboard();
                     *
                     * Scene motherScene =
                     *         motherDashboard.gotoMotherDashboard();
                     *
                     * Welcomepage.stage.setScene(motherScene);
                     *
                     * Welcomepage.stage.setMaximized(true);
                     */


                    logininfo.setText(
                            "Mother page not connected yet"
                    );

                    System.out.println(
                            "[LOGIN] Mother page not connected yet"
                    );

                    return;
                }


                // =================================================
                // DOCTOR
                // =================================================

                if (role.equals("doctor")) {

                    System.out.println(
                            "[LOGIN] Doctor role verified"
                    );


                    /*
                     * =================================================
                     * TODO - DOCTOR NAVIGATION
                     * =================================================
                     *
                     * When Doctor Dashboard is ready:
                     *
                     * DoctorDashboard doctorDashboard =
                     *         new DoctorDashboard();
                     *
                     * Scene doctorScene =
                     *         doctorDashboard.gotoDoctorDashboard();
                     *
                     * Welcomepage.stage.setScene(doctorScene);
                     *
                     * Welcomepage.stage.setMaximized(true);
                     */


                    logininfo.setText(
                            "Doctor page not connected yet"
                    );

                    System.out.println(
                            "[LOGIN] Doctor page not connected yet"
                    );

                    return;
                }


                // =================================================
                // HOSPITAL
                // =================================================

                if (role.equals("hospital")) {

                    System.out.println(
                            "[LOGIN] Hospital role verified"
                    );


                    /*
                     * =================================================
                     * TODO - HOSPITAL NAVIGATION
                     * =================================================
                     *
                     * When Hospital Dashboard is ready:
                     *
                     * HospitalDashboard hospitalDashboard =
                     *         new HospitalDashboard();
                     *
                     * Scene hospitalScene =
                     *         hospitalDashboard.gotoHospitalDashboard();
                     *
                     * Welcomepage.stage.setScene(hospitalScene);
                     *
                     * Welcomepage.stage.setMaximized(true);
                     */


                    logininfo.setText(
                            "Hospital page not connected yet"
                    );

                    System.out.println(
                            "[LOGIN] Hospital page not connected yet"
                    );

                    return;
                }


                // =================================================
                // ASHA WORKER
                // =================================================
               // =================================================
// ASHA WORKER
// =================================================

if (role.equals("asha")) {

    System.out.println(
            "[LOGIN] ASHA Worker role verified"
    );

    System.out.println(
            "[LOGIN] Opening ASHA Worker Dashboard..."
    );

    try {

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
                "[LOGIN] ASHA Worker Dashboard opened"
        );

        email.clear();
        password.clear();

    } catch (Exception ex) {

        ex.printStackTrace();

        logininfo.setText(
                "Unable to open ASHA Worker Dashboard"
        );

        System.out.println(
                "[LOGIN] Error opening ASHA Dashboard"
        );
    }

    return;
}

              // ==========================================
                     
                            


                // =================================================
                // AMBULANCE
                // =================================================

                if (role.equals("ambulance")) {

                    System.out.println(
                            "[LOGIN] Ambulance role verified"
                    );


                    /*
                     * =================================================
                     * TODO - AMBULANCE NAVIGATION
                     * =================================================
                     *
                     * When Ambulance Dashboard is ready:
                     *
                     * AmbulanceDashboard ambulanceDashboard =
                     *         new AmbulanceDashboard();
                     *
                     * Scene ambulanceScene =
                     *         ambulanceDashboard.gotoAmbulanceDashboard();
                     *
                     * Welcomepage.stage.setScene(ambulanceScene);
                     *
                     * Welcomepage.stage.setMaximized(true);
                     */


                    logininfo.setText(
                            "Ambulance page not connected yet"
                    );

                    System.out.println(
                            "[LOGIN] Ambulance page not connected yet"
                    );

                    return;
                }


                // =================================================
                // UNKNOWN ROLE
                // =================================================

                System.out.println(
                        "[LOGIN] Unknown role: " + role
                );

                logininfo.setText(
                        "Invalid role selected"
                );


            } else {

                // =================================================
                // AUTHENTICATION FAILED
                // =================================================

                System.out.println(
                        "[LOGIN] Authentication failed"
                );

                logininfo.setText(
                        "Invalid credentials. Try again."
                );

                email.clear();

                password.clear();
            }
        });


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


        // =========================================================
        // OR
        // =========================================================

        Text or =
                new Text("OR");

        or.setStyle(
                "-fx-fill: #666680;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );


        // =========================================================
        // SIGN UP BUTTON
        // =========================================================

        Button google =
                new Button("Sign Up");


        google.setOnAction(e -> {

            System.out.println(
                    role
            );

            System.out.println(
                    "sign up button pressed"
            );

            System.out.println(
                    email
            );

            System.out.println(
                    password
            );


            if (!role.isBlank()
                    && !email.getText().isBlank()
                    && !password.getText().isBlank()) {


                controller.signup(
                        email.getText(),
                        password.getText()
                );


                if (controller.status_code == 200) {

                    logininfo.setText(
                            "Sign up successful. Login to continue"
                    );

                    email.clear();

                    password.clear();


                } else if (controller.status_code == 400) {

                    logininfo.setText(
                            "Email exists. Sign up with another email / login"
                    );
                }

            } else {

                logininfo.setText(
                        "Select role and enter email/password"
                );
            }
        });


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


        // =========================================================
        // CREATE ACCOUNT
        // =========================================================

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


        // =========================================================
        // ADD EVERYTHING TO LOGIN CARD
        // =========================================================

        loginCard.getChildren().addAll(
                welcome,
                loginInfo,

                roleTitle,
                roles,

                forgot,

                email,
                password,

                options,

                login,

                or,

                google,

                createAccount
        );


        rightSide.getChildren().add(
                loginCard
        );


        // =========================================================
        // ROOT LAYOUT
        // =========================================================

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


        // =========================================================
        // SCENE
        // =========================================================

        loginpagScene =
                new Scene(
                        root,
                        scenesettings.rectanguler2d.getWidth(),
                        scenesettings.rectanguler2d.getHeight()
                );


        return loginpagScene;
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


        button.setWrapText(true);

        button.setTextAlignment(
                TextAlignment.CENTER
        );


        return button;
    }
}