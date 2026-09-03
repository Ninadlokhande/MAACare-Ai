

   

package com.sigma.view;
import com.sigma.model.HospitalAdminProfile;
import com.sigma.controller.ImageUploadController;
import com.sigma.controller.HospitalController.HospitalAdminProfileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
public class HospitalAdminProfilePage {

    // ================= COLORS =================
   // private static final String BG = "#FCFAFD";
       private static final String BG="#F7EAF5";

    private static final String WHITE = "#FFFFFF";
    private static final String NAVY = "#17184F";
    private static final String PINK = "#E83E83";
    private static final String LIGHT_PINK = "#FFF0F7";
    private static final String PURPLE = "#8056C5";
    private static final String LIGHT_PURPLE = "#F5F0FF";
    private static final String GREEN = "#67C98F";
    private static final String LIGHT_GREEN = "#ECFAF3";
    private static final String BLUE = "#5578D6";
    private static final String LIGHT_BLUE = "#F0F5FF";
    private static final String ORANGE = "#F2A33A";
    private static final String LIGHT_ORANGE = "#FFF6E8";
    private static final String BORDER = "#E9E6EF";
    private static final String GREY = "#77758A";  

    

    // =========================================================
    // MAIN SHOW METHOD
    // =========================================================

   public static void show(Stage stage) {
HospitalAdminProfileController controller=new HospitalAdminProfileController();
HospitalAdminProfile existingprofile =controller.getHospitalAdminProfile();

if (existingprofile == null) {

    controller.addHospitalAdminProfile(
            "CarePlus Hospital",
            "+91 20 1234 5678",
            "General Hospital",
            "Pune, Maharashtra 411001",
            "info@careplus.com",

            "Hospital Admin",
            "System Administrator",
            "admin@careplus.com",
            "+91 98765 43210",
            "15 Jan 2024",
            true,

            "2015",

            "27 Aug 2026",
            "10:30 AM",

            "Active",
            "2h 45m",
            "ACTIVE",
            "All Systems Operational"
    ); 

  return;   
    
}



//public BorderPane createView(){
        // ================= ROOT =================

       BorderPane root = new BorderPane();
       //root=new BorderPane();
        root.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(BG),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // ================= HEADER =================

        HBox header = new HBox(25);
        header.setPadding(new Insets(18, 28, 18, 28));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setBackground(
                new Background(
                        new BackgroundFill(
                               Color. WHITE,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // -------- MaaCare Logo --------

       /*  Label maaCareLogo = new Label("❤");
        maaCareLogo.setFont(Font.font(30));
        maaCareLogo.setTextFill(Color.web(PINK));*/ 

        // -------- MaaCare Logo --------

Image maaCareImage = new Image(
        "assets\\images\\logo\\logo.png"
);

ImageView maaCareLogo = new ImageView(maaCareImage);

maaCareLogo.setFitWidth(75);
maaCareLogo.setFitHeight(75);
maaCareLogo.setPreserveRatio(true);
maaCareLogo.setTranslateY(-5) ;  

        Label maaCareText = new Label("MaaCare AI");
        maaCareText.setFont(Font.font("Arial", FontWeight.BOLD, 27));
        maaCareText.setTextFill(Color.web(PINK));

        Label subLogo = new Label("Smart Hospital Management");
        subLogo.setFont(Font.font("Arial", 13));
        subLogo.setTextFill(Color.web(NAVY));

        VBox logoText = new VBox(1, maaCareText, subLogo);

        HBox logoBox = new HBox(10, maaCareLogo, logoText);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPrefWidth(325);

        // -------- Vertical Line --------

        Region line = new Region();
        line.setPrefWidth(1);
        line.setPrefHeight(55);
        line.setStyle("-fx-background-color: " + BORDER + ";");

        // -------- Header Title --------

        VBox titleBox = new VBox(4);

        Label title = new Label("Hospital Admin Profile");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.web(NAVY));

        Label titleSub = new Label(
                "Manage your profile information and account settings"
        );
        titleSub.setFont(Font.font("Arial", 14));
        titleSub.setTextFill(Color.web(GREY));

        titleBox.getChildren().addAll(title, titleSub);

        // -------- Admin Header Button --------

        HBox adminHeader = new HBox(12);
        adminHeader.setPadding(new Insets(10, 15, 10, 12));
        adminHeader.setAlignment(Pos.CENTER_LEFT);

        adminHeader.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );

        // Admin circle logo
        Circle adminCircle = new Circle(22);
        adminCircle.setFill(Color.web(LIGHT_PINK));

        Label adminIcon = new Label("👤");
        adminIcon.setFont(Font.font(22));

        StackPane adminLogo = new StackPane(adminCircle, adminIcon);

        VBox adminNameBox = new VBox(2);

    //    Label adminName = new Label("Hospital Admin");
Label adminName=new Label(existingprofile.getAdminName());
        adminName.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        adminName.setTextFill(Color.web(NAVY));

    //    Label hospitalName = new Label("CarePlus Hospital");
    Label hospitalName=new Label(existingprofile.getHospitalName());
        hospitalName.setFont(Font.font("Arial", 13));
        hospitalName.setTextFill(Color.web(GREY));

        adminNameBox.getChildren().addAll(adminName, hospitalName);

       

        adminHeader.getChildren().addAll(
                adminLogo,
                adminNameBox
                //arrow
        );

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        header.getChildren().addAll(
                logoBox,
                line,
                titleBox,
                headerSpacer,
                adminHeader
        );

        root.setTop(header);

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(10, 28, 5, 28));

        // ================= BACK BUTTON =================

        Button backButton = new Button("←  Back to Dashboard");

        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setTextFill(Color.web(PINK));
        backButton.setPadding(new Insets(11, 20, 11, 20));

        backButton.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnMouseEntered(e ->
                backButton.setStyle(
                        "-fx-background-color: " + LIGHT_PINK + ";" +
                        "-fx-border-color: " + PINK + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
                )
        );

        backButton.setOnMouseExited(e ->
                backButton.setStyle(
                        "-fx-background-color: " + WHITE + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
                )
        );

        // BACK TO DASHBOARD
        backButton.setOnAction(e -> {

            // IMPORTANT:
            // Your Dashboard class should have:
            // public static void show(Stage stage)
            Dashboard dashboardPage=new Dashboard();

           dashboardPage.show(stage);
        });

        // =========================================================
        // PROFILE + HOSPITAL INFORMATION
        // =========================================================

        HBox profileArea = new HBox(22);
        profileArea.setAlignment(Pos.TOP_LEFT);

        // ================= LEFT PROFILE CARD =================

        VBox profileCard = new VBox(15);
        profileCard.setPadding(new Insets(25));
        
       profileCard.setPrefHeight(500);
       profileCard.setMinHeight(0);

        profileCard.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );

        // Pink top area

        StackPane profileImageArea = new StackPane();
      //  profileImageArea.setPrefHeight(145);
      profileImageArea.setPrefHeight(180);
      profileImageArea.setMinHeight(180);

        profileImageArea.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, "
                + "#F7B3D0, #FFF0F7);"
        );

        // Admin Logo

       /*  Circle bigAdminCircle = new Circle(68);
        bigAdminCircle.setFill(Color.web("#FFE5F0"));
        bigAdminCircle.setStroke(Color.WHITE);
        bigAdminCircle.setStrokeWidth(7);

        Label bigAdminIcon = new Label("👤");
        bigAdminIcon.setFont(Font.font(58));

        StackPane bigAdminLogo = new StackPane(
                bigAdminCircle,
                bigAdminIcon
        );

        profileImageArea.getChildren().add(bigAdminLogo);*/ 

//Circle bigAdminCircle = new Circle(68);
Circle bigAdminCircle = new Circle(78);

bigAdminCircle.setFill(
        Color.web("#FFE5F0")
);

bigAdminCircle.setStroke(Color.WHITE);
bigAdminCircle.setStrokeWidth(7);

StackPane bigAdminLogo =
        new StackPane();

String imageUrl =
        existingprofile.getProfileImageUrl();

if (imageUrl != null
        && !imageUrl.isBlank()) {

    

Image image =
        new Image(
                imageUrl,
                150,
                150,
                true,
                true
        );

ImageView imageView =
        new ImageView(image);

imageView.setFitWidth(150);
imageView.setFitHeight(150);

imageView.setPreserveRatio(true);

Circle clip =
        new Circle(75, 75, 75);

imageView.setClip(clip);


    imageView.setClip(clip);

    bigAdminLogo.getChildren().add(
            imageView
    );

} else {

    Label bigAdminIcon =
            new Label("👤");

   /*  bigAdminIcon.setFont(
            Font.font(58)
    );*/ 

bigAdminIcon.setFont(
        Font.font(62)
);


    bigAdminLogo.getChildren().addAll(
            bigAdminCircle,
            bigAdminIcon
    );
}

profileImageArea.getChildren().add(
        bigAdminLogo
);  



Button uploadPhotoButton =
        new Button("📷  Change Profile Photo");

uploadPhotoButton.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                13
        )
);

uploadPhotoButton.setTextFill(
        Color.web(PINK)
);

uploadPhotoButton.setStyle(
        "-fx-background-color: " + LIGHT_PINK + ";" +
        "-fx-background-radius: 10;" +
        "-fx-border-color: " + PINK + ";" +
        "-fx-border-radius: 10;" +
        "-fx-cursor: hand;"
);

uploadPhotoButton.setOnAction(e -> {

    FileChooser fileChooser =
            new FileChooser();

    fileChooser.setTitle(
            "Select Profile Photo"
    );

    fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                    "Image Files",
                    "*.png",
                    "*.jpg",
                    "*.jpeg"
            )
    );

    File file =
            fileChooser.showOpenDialog(stage);

    if (file == null) {
        return;
    }

    ImageUploadController imageController =
            new ImageUploadController();

  

String uploadedImageUrl =
        imageController.imageUpload(file);

if (uploadedImageUrl == null) {

    showMessage(
            "Upload Failed",
            "Profile photo could not be uploaded."
    );

    return;
}

controller.updateProfileImage(
        uploadedImageUrl
);

existingprofile.setProfileImageUrl(
        uploadedImageUrl
);  




    showMessage(
            "Profile Photo Updated",
            "Profile photo uploaded successfully."
    );

    HospitalAdminProfilePage.show(stage);
});






        // Admin Name

      //  Label profileName = new Label("Hospital Admin");
      Label profileName=new Label(existingprofile.getAdminName());
        profileName.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        profileName.setTextFill(Color.web(NAVY));

        profileName.setAlignment(Pos.CENTER);

        Label role = new Label("System Administrator");
        role.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        role.setTextFill(Color.web(PINK));

        // Verified

      //  Label verified = new Label("🛡  Verified");
      Label verified = new Label(
        existingprofile.isVerified()
                ? "🛡  Verified"
                : "⚠  Not Verified"
);
        verified.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        verified.setTextFill(Color.web("#20945B"));

        verified.setPadding(new Insets(7, 14, 7, 14));

        verified.setStyle(
                "-fx-background-color: " + LIGHT_GREEN + ";" +
                "-fx-background-radius: 15;"
        );

        HBox verifiedBox = new HBox(verified);
        verifiedBox.setAlignment(Pos.CENTER);

        // Separator

        Separator separator = new Separator();
        separator.setStyle(
                "-fx-background-color: " + BORDER + ";"
        );

        // Profile details

        VBox details = new VBox(18);

        details.getChildren().addAll(

                createInfoRow(
                        "🏥",
                       // "CarePlus Hospital",
                       existingprofile.getHospitalName(),
                        "Hospital Name",
                        PINK,
                        LIGHT_PINK
                ),

                createInfoRow(
                        "✉",
                        //"admin@careplus.com",
                        existingprofile.getEmail(),
                        "Email Address",
                        PINK,
                        LIGHT_PINK
                ),

                createInfoRow(
                        "☎",
                       // "+91 98765 43210",
                       existingprofile.getContactNumber(),
                        "Contact Number",
                        PINK,
                        LIGHT_PINK
                )

           /*  createInfoRow(
                        "▣",
                        //"Joined on 15 Jan 2024",
                        "joined on" + existingprofile.getMemberSince(),
                        "Member Since",
                        PINK,
                        LIGHT_PINK
                )*/
        );

        profileCard.getChildren().addAll(
                profileImageArea,
                uploadPhotoButton,
                profileName,
                role,
                verifiedBox,
                separator,
                details
        );

        // =========================================================
        // RIGHT SIDE
        // =========================================================

        VBox rightSide = new VBox(18);
        HBox.setHgrow(rightSide, Priority.ALWAYS);

        // ================= HOSPITAL INFORMATION =================

        VBox hospitalInfoCard = new VBox(18);
        hospitalInfoCard.setPadding(new Insets(22));

        hospitalInfoCard.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );

        Label hospitalInfoTitle =
                new Label("🏥  Hospital Information");

        hospitalInfoTitle.setFont(
                Font.font("Arial", FontWeight.BOLD, 20)
        );

        hospitalInfoTitle.setTextFill(Color.web(PINK));

        GridPane hospitalGrid = new GridPane();

        hospitalGrid.setHgap(30);
        hospitalGrid.setVgap(20);

        hospitalGrid.add(
                createInfoBox(
                        "🏥",
                       // "CarePlus Hospital",
                       existingprofile.getHospitalName(),
                        "Hospital Name",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                0, 0
        );

        hospitalGrid.add(
                createInfoBox(
                        "☎",
                       // "+91 20 1234 5678",
                       existingprofile.getPhoneNumber(),
                        "Phone Number",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                1, 0
        );

        hospitalGrid.add(
                createInfoBox(
                        "🛡",
                       // "General Hospital",
                       existingprofile.getHospitalType(),
                        "Hospital Type",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                2, 0
        );

        hospitalGrid.add(
                createInfoBox(
                        "📍",
                        //"Pune, Maharashtra 411001",
                        existingprofile.getAddress(),
                        "Address",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                0, 1
        );

        hospitalGrid.add(
                createInfoBox(
                        "✉",
                        //"info@careplus.com",
                        existingprofile.getEmail(),
                        "Email Address",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                1, 1
        );

        hospitalGrid.add(
                createInfoBox(
                        "▣",
                       // "Established 2015",
                       "Established " + existingprofile.getEstablishedYear(),
                        "Established Year",
                        PURPLE,
                        LIGHT_PURPLE
                ),
                2, 1
        );

        hospitalInfoCard.getChildren().addAll(
                hospitalInfoTitle,
                hospitalGrid
        );

        // =========================================================
        // QUICK ACTIONS
        // =========================================================

        VBox quickActionsCard = new VBox(18);
        quickActionsCard.setPadding(new Insets(22));

        quickActionsCard.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );

        Label quickTitle =
                new Label("⚡  Quick Actions");

        quickTitle.setFont(
                Font.font("Arial", FontWeight.BOLD, 20)
        );

        quickTitle.setTextFill(Color.web(PINK));

        HBox actionRow = new HBox(18);
        actionRow.setAlignment(Pos.CENTER);

        

       

        // EDIT PROFILE BUTTON

        Button editProfile =
                createActionButton(
                        "👤",
                        "Edit Profile",
                        "Update profile information",
                        PURPLE,
                        LIGHT_PURPLE

                ); 
editProfile.setPrefWidth(190);
editProfile.setMinWidth(190);
editProfile.setMaxWidth(190);



        editProfile.setOnAction(e -> {

        /*    showMessage(
                    "Edit Profile",
                    "Edit profile page will open here."
            );*/


       showEditProfileDialog( 
                stage,
                profileName,
                adminName,
                hospitalName,
                controller,
                existingprofile

            );   

   });   



 actionRow.getChildren().addAll(
              
                editProfile
              
        );

        quickActionsCard.getChildren().addAll(
                quickTitle,
                actionRow
        );

        // =========================================================
        // ACCOUNT ACTIVITY
        // =========================================================

        VBox activityCard = new VBox(18);
        activityCard.setPadding(new Insets(22));

        activityCard.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );

        Label activityTitle =
                new Label("📈  Account Activity");

        activityTitle.setFont(
                Font.font("Arial", FontWeight.BOLD, 20)
        );

        activityTitle.setTextFill(Color.web(PINK));

        HBox activityRow = new HBox(30);
        activityRow.setAlignment(Pos.CENTER);

        activityRow.getChildren().addAll(

            
    createActivityBox(
                        "●",
                        "Active Session",
                      //  "2h 45m",
                      existingprofile.getActiveSession(),
                        "Duration",
                        GREEN,
                        LIGHT_GREEN
                ),

              
createActivityBox(
        "🛡",
        "Account Status",
        existingprofile.getAccountStatus(),
        existingprofile.getSystemStatus(),
        ORANGE,
        LIGHT_ORANGE
)


        );

        activityCard.getChildren().addAll(
                activityTitle,
                activityRow
        );

        rightSide.getChildren().addAll(
                hospitalInfoCard,
                quickActionsCard,
                activityCard
        );

        profileArea.getChildren().addAll(
                profileCard,
                rightSide
        );    




        // =========================================================
        // LOGOUT BUTTON
        // =========================================================

        Button logoutButton =
                new Button("⇥ Logout");

        logoutButton.setPrefWidth(180);

        logoutButton.setPrefHeight(35);

        logoutButton.setFont(
                Font.font("Arial", FontWeight.BOLD, 17)
            
        );

        logoutButton.setTextFill(Color.WHITE);

        logoutButton.setStyle(
                "-fx-background-color: " + PINK + ";" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );   
        logoutButton.setTranslateX(630);

        logoutButton.setOnMouseEntered(e ->
                logoutButton.setStyle(
                        "-fx-background-color: #D92F70;" +
                        "-fx-background-radius: 12;" +
                        "-fx-cursor: hand;"
                )
        );

        logoutButton.setOnMouseExited(e ->
                logoutButton.setStyle(
                        "-fx-background-color: " + PINK + ";" +
                        "-fx-background-radius: 12;" +
                        "-fx-cursor: hand;"
                )
        );

      


logoutButton.setOnAction(e -> {

    Alert alert = new Alert(
            Alert.AlertType.CONFIRMATION
    );

    alert.setTitle("Logout");
    alert.setHeaderText("Logout from MaaCare AI?");
    alert.setContentText(
            "Are you sure you want to logout?"
    );

    ButtonType yes =
            new ButtonType("Logout");

    ButtonType cancel =
            new ButtonType(
                    "Cancel",
                    ButtonBar.ButtonData.CANCEL_CLOSE
            );

    alert.getButtonTypes().setAll(
            yes,
            cancel
    );

    alert.showAndWait().ifPresent(result -> {

        if (result == yes) {

            System.out.println(
                    "Admin logged out successfully."
            );

            // =========================================
            // GO TO LOGIN PAGE
            // =========================================

            Loginpage loginPage = new Loginpage();

            Scene loginScene =
                    loginPage.gotologinpage();

            stage.setScene(loginScene);

            stage.setMaximized(true);

            stage.show();

            System.out.println(
                    "Login page opened successfully."
            );
        }
    });
});
     mainContent.getChildren().addAll(
             backButton,
              profileArea,logoutButton
              
                
       );

        root.setCenter(mainContent);  


     // ================= SCENE =================

        Scene scene = new Scene(
                root,
                1500,
                800
        );

        stage.setScene(scene);
        stage.setTitle("MaaCare AI - Hospital Admin Profile");
        stage.show();
    }  

   // =========================================================
    // INFO ROW
    // =========================================================

    private static HBox createInfoRow(
            String icon,
            String value,
            String label,
            String color,
            String lightColor
    ) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(22));

        StackPane iconBox = new StackPane(iconLabel);

        iconBox.setPrefSize(48, 48);

        iconBox.setStyle(
                "-fx-background-color: " + lightColor + ";" +
                "-fx-background-radius: 10;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 15)
        );
        valueLabel.setTextFill(Color.web(NAVY));

        Label labelText = new Label(label);
        labelText.setFont(Font.font("Arial", 13));
        labelText.setTextFill(Color.web(GREY));

        VBox text = new VBox(3, valueLabel, labelText);

        HBox row = new HBox(
                15,
                iconBox,
                text
        );

        row.setAlignment(Pos.CENTER_LEFT);

        return row;
    }

    // =========================================================
    // HOSPITAL INFO BOX
    // =========================================================

    private static VBox createInfoBox(
            String icon,
            String value,
            String label,
            String color,
            String lightColor
    ) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(22));

        StackPane iconBox =
                new StackPane(iconLabel);

        iconBox.setPrefSize(50, 50);

        iconBox.setStyle(
                "-fx-background-color: " + lightColor + ";" +
                "-fx-background-radius: 10;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        valueLabel.setTextFill(Color.web(NAVY));

        Label labelText =
                new Label(label);

        labelText.setFont(
                Font.font("Arial", 12)
        );

        labelText.setTextFill(Color.web(GREY));

        VBox text =
                new VBox(3, valueLabel, labelText);

        HBox row =
                new HBox(12, iconBox, text);

        row.setAlignment(Pos.CENTER_LEFT);

        VBox box =
                new VBox(row);

        box.setPrefWidth(250);

        return box;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private static Button createActionButton(
            String icon,
            String title,
            String subtitle,
            String color,
            String lightColor
    ) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(Font.font(24));

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        titleLabel.setTextFill(Color.web(color));

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setFont(
                Font.font("Arial", 12)
        );

        subtitleLabel.setTextFill(Color.web(NAVY));

        VBox content =
                new VBox(
                        8,
                        iconLabel,
                        titleLabel,
                        subtitleLabel
                );

        content.setAlignment(Pos.CENTER);

        Button button =
                new Button();

        button.setGraphic(content);

        button.setPrefHeight(115);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setStyle(
                "-fx-background-color: " + lightColor + ";" +
                "-fx-border-color: " + color + "33;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: " + lightColor + ";" +
                        "-fx-border-color: " + color + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: " + lightColor + ";" +
                        "-fx-border-color: " + color + "33;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    // =========================================================
    // ACCOUNT ACTIVITY BOX
    // =========================================================

    private static VBox createActivityBox(
            String icon,
            String title,
            String value,
            String subtitle,
            String color,
            String lightColor
    ) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(Font.font(22));

        StackPane iconBox =
                new StackPane(iconLabel);

        iconBox.setPrefSize(45, 45);

        iconBox.setStyle(
                "-fx-background-color: " + lightColor + ";" +
                "-fx-background-radius: 10;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );

        titleLabel.setTextFill(Color.web(NAVY));

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        valueLabel.setTextFill(Color.web(NAVY));

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setFont(
                Font.font("Arial", 11)
        );

        subtitleLabel.setTextFill(Color.web(GREY));

        VBox box =
                new VBox(
                        6,
                        iconBox,
                        titleLabel,
                        valueLabel,
                        subtitleLabel
                );

        box.setAlignment(Pos.CENTER_LEFT);

        return box;
    }   

// =========================================================
// EDIT PROFILE DIALOG
// =========================================================

private static void showEditProfileDialog(
        Stage stage,
        Label profileName,
        Label adminName,
        Label hospitalName,
        HospitalAdminProfileController controller,
        HospitalAdminProfile profile
) {

    Dialog<ButtonType> dialog = new Dialog<>();

    dialog.setTitle("Edit Profile");
    dialog.setHeaderText("Update Hospital Admin Profile");

    ButtonType saveButton =
            new ButtonType(
                    "Save Changes",
                    ButtonBar.ButtonData.OK_DONE
            );

    ButtonType cancelButton =
            new ButtonType(
                    "Cancel",
                    ButtonBar.ButtonData.CANCEL_CLOSE
            );

    dialog.getDialogPane().getButtonTypes().addAll(
            saveButton,
            cancelButton
    );

    VBox content = new VBox(14);
    content.setPadding(new Insets(20));
    content.setPrefWidth(500);

    // =====================================================
    // ADMIN NAME
    // =====================================================

    Label nameLabel = new Label("Admin Name");

    TextField nameField =
            new TextField(profile.getAdminName());

    // =====================================================
    // HOSPITAL NAME
    // =====================================================

    Label hospitalNameLabel =
            new Label("Hospital Name");

    TextField hospitalNameField =
            new TextField(profile.getHospitalName());

    // =====================================================
    // EMAIL
    // =====================================================

    Label emailLabel =
            new Label("Admin Email Address");

    TextField emailField =
            new TextField(profile.getEmail());

    // =====================================================
    // CONTACT NUMBER
    // =====================================================

    Label contactLabel =
            new Label("Admin Mobile Number");

    TextField contactField =
            new TextField(profile.getContactNumber());

    // =====================================================
    // PHONE NUMBER
    // =====================================================

    Label phoneLabel =
            new Label("Hospital Phone Number");

    TextField phoneField =
            new TextField(profile.getPhoneNumber());

    // =====================================================
    // HOSPITAL TYPE
    // =====================================================

    Label typeLabel =
            new Label("Hospital Type");

    TextField typeField =
            new TextField(profile.getHospitalType());

    // =====================================================
    // ADDRESS
    // =====================================================

    Label addressLabel =
            new Label("Hospital Address");

    TextField addressField =
            new TextField(profile.getAddress());

    // =====================================================
    // ESTABLISHED YEAR
    // =====================================================

    Label yearLabel =
            new Label("Established Year");

    TextField yearField =
            new TextField(
                    profile.getEstablishedYear()
            );

    // =====================================================
    // ADD ALL FIELDS
    // =====================================================

    content.getChildren().addAll(

            nameLabel,
            nameField,

            hospitalNameLabel,
            hospitalNameField,

            emailLabel,
            emailField,

            contactLabel,
            contactField,

            phoneLabel,
            phoneField,

            typeLabel,
            typeField,

            addressLabel,
            addressField,

            yearLabel,
            yearField
    );

    dialog.getDialogPane().setContent(content);

    // =====================================================
    // SAVE
    // =====================================================

    dialog.showAndWait().ifPresent(result -> {

        if (result == saveButton) {

            // =================================================
            // VALIDATION
            // =================================================

            if (nameField.getText().isBlank()
                    || hospitalNameField.getText().isBlank()
                    || emailField.getText().isBlank()
                    || contactField.getText().isBlank()
                    || phoneField.getText().isBlank()
                    || typeField.getText().isBlank()
                    || addressField.getText().isBlank()
                    || yearField.getText().isBlank()) {

                showMessage(
                        "Invalid Details",
                        "Please fill all fields."
                );

                return;
            }

            // =================================================
            // UPDATE FIREBASE
            // =================================================

            controller.updateHospitalAdminProfile(

                    // Hospital Information
                    hospitalNameField.getText(),
                    phoneField.getText(),
                    typeField.getText(),
                    addressField.getText(),

                    // Hospital Email
                    profile.getHospitalEmail(),

                    // Admin Information
                    nameField.getText(),
                    profile.getRole(),
                    emailField.getText(),
                    contactField.getText(),

                    // Other Information
                    profile.getMemberSince(),
                    profile.isVerified(),

                    yearField.getText(),

                    // Activity
                    profile.getLastLoginDate(),
                    profile.getLastLoginTime(),
                    profile.getActiveSession(),
                    profile.getSessionDuration(),
                    profile.getAccountStatus(),
                    profile.getSystemStatus()
            );  

            // =================================================
            // RELOAD PROFILE PAGE
            // =================================================

            HospitalAdminProfilePage.show(stage);

            showMessage(
                    "Profile Updated",
                    "Profile information updated successfully."
            );
        } 
    });  
}
  // =========================================================
    // MESSAGE BOX
    // =========================================================

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}