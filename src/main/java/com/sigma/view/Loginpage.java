package com.sigma.view;

import com.sigma.controller.Controller;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Loginpage {

    private Scene loginpagScene;
    public String role = "";
    Controller controller = new Controller();

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
        leftSide.setPadding(new Insets(40, 50, 40, 60));

        leftSide.setPrefWidth(550);


        // ---------------- LOGO ----------------

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
        Text logininfo = new Text("");
        logininfo.setStyle( "-fx-fill: #13cee3;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;");


        // ---------------- TITLE ----------------

        Text brandName =
            new Text("MaaCare AI");

        brandName.setStyle(
            "-fx-fill: linear-gradient(to right, #E84A87, #9B4DCC);" +
            "-fx-font-size: 48px;" +
            "-fx-font-weight: bold;"
        );


        // ---------------- TAGLINE ----------------

        Text tagline1 =
            new Text("Care for Mom. Care for Baby. Care for Life.");

        tagline1.setStyle(
            "-fx-fill: #24234F;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );


        // ---------------- DESCRIPTION ----------------

        Text description1 =
            new Text("Your AI-Powered Companion for");

        description1.setStyle(
            "-fx-fill: #24234F;" +
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;"
        );


        Text descriptionPink =
            new Text(" Mother & Child");

        descriptionPink.setStyle(
            "-fx-fill: #E84A87;" +
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;"
        );


        Text description2 =
            new Text(" Healthcare");

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

        description.setAlignment(Pos.CENTER);


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
        // FEATURE ITEMS
        // =========================================================

        HBox features =
            new HBox(25);

        features.setAlignment(Pos.CENTER);
        features.setPadding(new Insets(20, 0, 0, 0));


        features.getChildren().addAll(
            createFeature("♥", "Pregnancy", "Tracking"),
            createFeature("✚", "Health", "Records"),
            createFeature("✦", "AI Health", "Assistant"),
            createFeature("▣", "Doctor &", "Hospital"),
            createFeature("✚", "Emergency", "Support")
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
        // RIGHT SIDE - LOGIN CARD
        // =========================================================

        StackPane rightSide =
            new StackPane();

        rightSide.setPadding(
            new Insets(40, 70, 40, 40)
        );


        VBox loginCard =
            new VBox(18);

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
        // LOGIN HEADING
        // =========================================================

        Text welcome =
            new Text("Welcome Back!");

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
            new Text("Select Your Role");

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

        roles.setAlignment(Pos.CENTER);

        Button mother =
            createRoleButton("🤱\nMother\nFamily");
            mother.setOnAction(e->{
                role = "mother";

            });

            mother.setOnMouseClicked(e->{
                forgot.setText("Role : Mother");

                
            }
               
            );

        Button doctor =
            createRoleButton("🧑‍⚕️\nDoctor");

            doctor.setOnAction(e->{
                role= "doctor";

            });

               doctor.setOnMouseClicked(e->{
               forgot.setText("Role : Doctor");
               }

                
            );

        Button hospital =
            createRoleButton("🏥\nHospital");
            hospital.setOnAction(e->{
                role = "hospital";
            });
               hospital.setOnMouseClicked(e->{ 
                    forgot.setText("Role : Hospital");

               }
               
            );

        Button asha =
            createRoleButton("👩\nASHA Worker");
            asha.setOnAction(e->{
                role ="asha";
            });

              asha.setOnMouseClicked(e->{
                    forgot.setText("Role : ASHA WORKER");

              }
               
            );

        Button ambulance =
            createRoleButton("🚑\nAmbulance");
            ambulance.setOnAction(e->{
                role = "ambulance";
            });

               ambulance.setOnMouseClicked(e->{

                forgot.setText("Role : Ambulence / Hospital \n 🚑");
               }
                
            );

        Button admin =
            createRoleButton(" 🧑‍💻\nAdmin");
            admin.setOnAction(e->{
                role="admin";
            });
            admin.setOnMouseClicked(e->{
            
                forgot.setText("Role : ADMIN");
            }


                
            );
            


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
        // REMEMBER + FORGOT
        // =========================================================

        CheckBox remember =
            new CheckBox("Remember me");

        remember.setStyle(
            "-fx-text-fill: #666680;" +
            "-fx-font-size: 14px;"
        );


       


        HBox options =
            new HBox();

        options.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(remember, Priority.ALWAYS);

        options.getChildren().addAll(
            remember
            
        );


        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button login =
            new Button("🔒   Login");
            login.setOnAction(e->{
                System.out.println(role);;
                System.out.println("login button pressed");
                System.out.println(email);
                System.out.println(password);
                if (!role.isBlank()
        && !email.getText().isBlank()
        && !password.getText().isBlank()) {
            

    controller.signin(email.getText(),password.getText());

     if(controller.status_code == 200){
            logininfo.setText("Login sucessfull redirecting");
            }
            else {
                 logininfo.setText("Invalid Credentials Try again");

                 email.clear();
               password.clear();

            }
    }});

        login.setMaxWidth(Double.MAX_VALUE);

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
        // GOOGLE BUTTON
        // =========================================================

        Button google =
            new Button("Sign Up");
        google.setOnAction(e->{
                System.out.println(role);;
                System.out.println("sign up button pressed");
                System.out.println(email);
                System.out.println(password);
                if (!role.isBlank()
        && !email.getText().isBlank()
        && !password.getText().isBlank()) {

            controller.signup(email.getText(),password.getText());

            if(controller.status_code == 200){
            logininfo.setText("sign up sucessfull login to continue");
            email.clear();
            password.clear();

            }
            else if(controller.status_code ==400 ){
                 logininfo.setText("Email exists sign up with another email / login");

            }

    
    
}
        }); 

        google.setMaxWidth(Double.MAX_VALUE);

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

        createAccount.setAlignment(Pos.CENTER);

        Text accountText =
            new Text("Don't have an account?");

        accountText.setStyle(
            "-fx-fill: #77778D;" +
            "-fx-font-size: 14px;"
        );


        Text createText =
            new Text(" Sign up");

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


            login,

            or,

            google,

            createAccount
        );


        rightSide.getChildren().add(loginCard);


        // =========================================================
        // RESPONSIVE LAYOUT
        // =========================================================

        root.setLeft(leftSide);
        root.setCenter(rightSide);


        BorderPane.setAlignment(
            leftSide,
            Pos.CENTER
        );


        // =========================================================
        // SCENE
        // =========================================================

        loginpagScene =
            new Scene(root, scenesettings.rectanguler2d.getWidth(),scenesettings.rectanguler2d.getHeight());

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

        box.setAlignment(Pos.CENTER);

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
        button.setTextAlignment(TextAlignment.CENTER);


        return button;
    }


    // =============================================================
    // SEPARATOR
    // =============================================================

    private StackPane createSeparator() {

        StackPane separator =
            new StackPane();

        separator.setPrefHeight(15);

        Text heart =
            new Text("♥");

        heart.setStyle(
            "-fx-fill: #F39ABD;" +
            "-fx-font-size: 14px;"
        );

        separator.getChildren().add(heart);

        return separator;
    }
}