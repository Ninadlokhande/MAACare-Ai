package com.sigma.view;


//import javafx.application.Application;
import com.sigma.controller.HospitalController.BedBookingController;
import com.sigma.controller.HospitalController.AppointmentController;
import com.sigma.model.BedBooking;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.sigma.controller.HospitalController.LabrecordsController;
import com.sigma.model.Labrecords;
import com.sigma.controller.HospitalController.HospitalAdminProfileController;
import com.sigma.model.HospitalAdminProfile;

import com.sigma.controller.HospitalController.NotificationController;
import com.sigma.model.Notification;
public class Dashboard  {
        

    // =====================================================
    // MAACARE AI COLOR PALETTE
    // =====================================================
       private static final String BG="#F7EAF5";


    private static final String WHITE = "#FFFFFF";

    private static final String NAVY = "#17184F";
    private static final String PINK = "#E83E83";
      private static final String LIGHT_PINK = "#E6D6F5";
     private static final String PURPLE = "#8056C5";
    private static final String GREEN = "#67C98F";
    private static final String BLUE = "#5578D6";
    private static final String ORANGE = "#F2A33A";
  private static final String BORDER = "#B8B3C2";
  private static final String GREY = "#77758A";  

    // =====================================================
    // START
    // =====================================================  

    private BorderPane root;
    private javafx.scene.Node dashboardContent;



    public void show(Stage stage) {  

    

       //   BorderPane root = new BorderPane(); 
       root=new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =================================================
        // SIDEBAR
        // =================================================

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(240);

        sidebar.setPadding(
                new Insets(20, 15, 20, 15)
        );

        sidebar.setSpacing(12);

        sidebar.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-background-radius: 0 25 25 0;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 0 25 25 0;"
        );

        // =================================================
        // LOGO
        // =================================================

        VBox logoBox = new VBox();

        logoBox.setAlignment(Pos.CENTER);

        logoBox.setPadding(
                new Insets(5, 0, 20, 0)
        );

        Image logoImage = new Image("assets\\images\\logo\\logo.png");
        

        ImageView logoView =
                new ImageView(logoImage);

        logoView.setFitWidth(195);
        logoView.setFitHeight(195);
        logoView.setPreserveRatio(true);

        logoBox.getChildren().add(logoView);

        // =================================================
        // SIDEBAR MENU
        // =================================================

        Button dashboardBtn =
                menuButton("⌂", "Dashboard", true);

              
dashboardBtn.setOnAction(e -> {

    Stage currentStage =
            (Stage) dashboardBtn.getScene().getWindow();

    show(currentStage);
});



        Button appointmentBtn =
                menuButton("▣", "Appointments", true);

        // =================================================
        // APPOINTMENTS NAVIGATION ONLY
        // =================================================

       


appointmentBtn.setOnAction(e -> {

    AppointmentPage appointments =
            new AppointmentPage();

    root.setCenter(
            appointments.getView()
    );
});



        // =================================================
        // OTHER BUTTONS
        // NO NAVIGATION FOR THESE
        // =================================================

        Button labBtn =
                menuButton(
                        "⚗",
                        "Lab Records",
                        true
                ); 

      
labBtn.setOnAction(e -> {
      
      javafx.scene.Node dashboardContent=root.getCenter();
    

LabrecordsPage labPage =
        new LabrecordsPage() ;

            root.setLeft(sidebar);
            root.setCenter(labPage.getView());
        


});

     Button bedBtn =
                menuButton(
                        "▤",
                        "Bed Booking",
                        true
                );
             
bedBtn.setOnAction(e->{ 
        BedBookingPage bedpage=new BedBookingPage();
        root.setLeft(sidebar);
        root.setCenter(bedpage.getView());
});







        Button emergencyBtn =
                menuButton(
                        "✚",
                        "Emergency",
                        true
                ); 
              

emergencyBtn.setOnAction(e->{ 
        EmergencyPage emergencyPage=new EmergencyPage();
        root.setLeft(sidebar);
        root.setCenter(emergencyPage.getView());
});

        

        VBox menu = new VBox(18);

        menu.getChildren().addAll(
                dashboardBtn,
                appointmentBtn,
                labBtn,
                bedBtn,
                emergencyBtn
        );

        sidebar.getChildren().addAll(
                logoBox,
                menu
        );

        root.setLeft(sidebar);

        // =================================================
        // MAIN CONTENT
        // =================================================

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(28, 35, 30, 35)
        );

        // =================================================
        // HEADER
        // =================================================

        HBox header =
                createHeader(stage);

        // =================================================
        // STAT CARDS
        // =================================================

        HBox statCards =
                new HBox(18); 
LabrecordsController labController =
        new LabrecordsController();

int labReportCount =
        labController.getAllLabrecords().size();

AppointmentController appointmentController =
        new AppointmentController();

int appointmentCount =
        appointmentController.getAllAppointments().size(); 

        BedBookingController bedBookingController =
        new BedBookingController();

int bedBookingCount =
        bedBookingController.getAllBedBookings().size();

       // System.out.println("Firebase Bed Booking Count = " + bedBookingCount);



        statCards.setAlignment(
                Pos.CENTER
        ); 
    




        statCards.getChildren().addAll( 
                createStatCard(
                                "📅",

                        "Total Appointments",
                       // "24",
                       String.valueOf(appointmentCount),
                        PINK),

                       createStatCard( 
                                    //   "🧪",
                               "⚗",         


                        "Lab Reports",
                       // "18",
                       String.valueOf(labReportCount),
                        PURPLE),  

                        createStatCard(
                        
                                        
                   "🛌",


                        "BedBooking",
                       
                       String.valueOf(bedBookingCount),
                        PINK), 

                        createStatCard(
                        
                                        "🚑",

                        "Emergency Cases",
                      "48",
                    //  String.valueOf(bedBookingCount),
                        PINK
                ),

               // appointmentCard,

               // labCard,

               // bedbookingCard,

               // emergencyCasesCard,

               createStatCard(
                        
                        "👥",

                        "Total Patients",
                       "150",
                       BLUE
               )
        );  

          // =================================================
        // MIDDLE
        // =================================================

        HBox middle =
                new HBox(20);

        VBox appointmentPanel =
                createAppointmentPanel();

        VBox bedPanel =
                createBedPanel();

        HBox.setHgrow(
                appointmentPanel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                bedPanel,
                Priority.ALWAYS
        );

        middle.getChildren().addAll(
                appointmentPanel,
                bedPanel
        );

        // =================================================
        // BOTTOM
        // =================================================

        HBox bottom =
                new HBox(20);

        VBox labPanel =
                createLabPanel();

        VBox emergencyPanel =
                createEmergencyPanel();

        HBox.setHgrow(
                labPanel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                emergencyPanel,
                Priority.ALWAYS
        );

        bottom.getChildren().addAll(
                labPanel,
                emergencyPanel
        );

        // =================================================
        // ADD CONTENT
        // =================================================

        content.getChildren().addAll(
                header,
                statCards,
                middle,
                bottom
        );

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-background: " + BG + ";"
        );

        root.setCenter(scrollPane);
        dashboardContent=scrollPane;

        // =================================================
        // SCENE
        // =================================================

        Scene scene =
                new Scene(
                        root,
                        1500,
                        800
                );

        stage.setTitle(
                "MaaCare AI - Hospital Dashboard"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    } 

    // =====================================================
    // HEADER
    // =====================================================

    private HBox createHeader(Stage stage) {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox welcome =
                new VBox(5);

HospitalAdminProfileController profileController =
        new HospitalAdminProfileController();

HospitalAdminProfile profile =
        profileController.getHospitalAdminProfile();

String hospitalName = "Hospital";

if (profile != null &&
        profile.getHospitalName() != null &&
        !profile.getHospitalName().trim().isEmpty()) {

    hospitalName = profile.getHospitalName();
}

Label title =
        new Label(
                "Good Morning, " + hospitalName + "! 👋"
        );


        title.setTextFill(
                Color.web(NAVY)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        Label subtitle =
                new Label(
                        "Here's what's happening in your hospital today."
                );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        welcome.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        

           


// =================================================
// NOTIFICATION
// =================================================

Label bell =
        new Label("🔔");

bell.setFont(
        Font.font(25)
);

bell.setTextFill(
        Color.web(NAVY)
);

StackPane notification =
        new StackPane();

notification.setPrefSize(
        50,
        45
);

notification.setCursor(
        Cursor.HAND
);

notification.getChildren().add(
        bell
);


// =================================================
// GET UNREAD NOTIFICATION COUNT
// =================================================

NotificationController notificationController =
        new NotificationController();

List<Notification> unreadNotifications =
        notificationController.getUnreadNotifications();

int unreadCount =
        unreadNotifications.size();
       // System.out.println("Unread Notification Count = " + unreadCount);


// =================================================
// NOTIFICATION BADGE
// =================================================


final StackPane badge =
        new StackPane();

if (unreadCount > 0) {

    Circle badgeCircle =
            new Circle(10);

    badgeCircle.setFill(
            Color.web(PINK)
    );

    Label badgeText =
            new Label(
                    String.valueOf(unreadCount)
            );

    badgeText.setTextFill(
            Color.WHITE
    );

    badgeText.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    10
            )
    );

    badge.getChildren().addAll(
            badgeCircle,
            badgeText
    );

    StackPane.setAlignment(
            badge,
            Pos.TOP_RIGHT
    );

    notification.getChildren().add(
            badge
    );
}


// =================================================
// NOTIFICATION CLICK
// =================================================

notification.setOnMouseClicked(e -> {

    showNotificationPopup(
            notificationController
    );
//Remove notification count after reading

notification.getChildren().remove(badge);

});

// =================================================
// ADMIN
// =================================================

Circle avatar =
        new Circle(22);

avatar.setFill(
        Color.web("#F4B7CC")
);

Label adminIcon =
        new Label("👤");

adminIcon.setFont(
        Font.font(22)
);

StackPane adminLogo =
        new StackPane(
                avatar,
                adminIcon
);

adminLogo.setPrefSize(
        44,
        44
);

Label admin =
        new Label(
                "Hospital Admin"
);

admin.setTextFill(
        Color.web(NAVY)
);

admin.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                13
        )
);

Label arrow =
        new Label("⌄");

arrow.setTextFill(
        Color.web(NAVY)
);

HBox adminBox =
        new HBox(
                8,
                adminLogo,
                admin,
                arrow
);

adminBox.setAlignment(
        Pos.CENTER
);

adminBox.setOnMouseClicked(e->{ 
HospitalAdminProfilePage.show(stage);
}); 
adminBox.setStyle("-fx-cursor: hand");

    header.getChildren().addAll(
                welcome,
                spacer,
               // search,
                notification,
                adminBox
        );

        return header;
    } 


private void showNotificationPopup(
        NotificationController controller) {

    List<Notification> notifications =
            controller.getAllNotifications();

    Dialog<Void> dialog = new Dialog<>();

    dialog.setTitle("Notifications");
    dialog.setHeaderText("Hospital Notifications");

    // =================================================
    // NOTIFICATION LIST
    // =================================================

    VBox notificationBox =
            new VBox(12);

    notificationBox.setPadding(
            new Insets(10)
    );

    if (notifications.isEmpty()) {

        Label emptyLabel =
                new Label("No notifications available.");

        emptyLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        emptyLabel.setTextFill(
                Color.web(NAVY)
        );

        notificationBox.getChildren().add(
                emptyLabel
        );

    } else {

        for (Notification notification :
                notifications) {

            // -----------------------------------------
            // Notification text
            // -----------------------------------------

            Label message =
                    new Label(
                            "• " + notification.getMessage()
                    );

            message.setWrapText(true);

            message.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            13
                    )
            );

            message.setTextFill(
                    Color.web(NAVY)
            );

            Label type =
                    new Label(
                            "Type: " +
                            notification.getType()
                    );

            type.setFont(
                    Font.font(
                            "Arial",
                            11
                    )
            );

        /*    type.setTextFill(
                    Color.web(GREY)
            );*/ 

type.setTextFill(
        Color.web("#555568")
);

type.setStyle(
        "-fx-font-size: 12px;"
);


            VBox textBox =
                    new VBox(
                            4,
                            message,
                            type
                    );

            HBox.setHgrow(
                    textBox,
                    Priority.ALWAYS
            );

            // -----------------------------------------
            // DELETE BUTTON
            // -----------------------------------------

            Button deleteButton =
                    new Button("Delete");

            deleteButton.setStyle(
                    "-fx-background-color: #E83E83;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
            );
            deleteButton.setPrefWidth(75);
deleteButton.setPrefHeight(35);

            deleteButton.setOnAction(e -> {

                if (notification.getId() != null) {

                    controller.deleteNotification(
                            notification.getId()
                    );

                    notificationBox.getChildren()
                            .remove(
                                    deleteButton.getParent()
                            );
                }
            });

            // -----------------------------------------
            // Notification row
            // -----------------------------------------

            HBox row =
                    new HBox(
                            10,
                            textBox,
                            deleteButton
                    );

            row.setAlignment(
                    Pos.CENTER_LEFT
            );

            row.setPadding(
                    new Insets(10)
            );

           /*  row.setStyle(
                    "-fx-background-color: #FFF0F7;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #D1D5DB;" +
                    "-fx-border-radius: 10;"
            );*/ 
row.setStyle(
        "-fx-background-color: #F8F5FF;" +
        "-fx-background-radius: 12;" +
        "-fx-border-color: #D8D1E8;" +
        "-fx-border-radius: 12;" +
        "-fx-padding: 12;"
);



            notificationBox.getChildren().add(
                    row
            );

            // Mark notification as read
            if (notification.getId() != null) {

                controller.markAsRead(
                        notification.getId()
                );
            }
        }
    }

    // =================================================
    // SCROLL PANE
    // =================================================

    ScrollPane scrollPane =
            new ScrollPane(notificationBox);

    scrollPane.setFitToWidth(true);

    scrollPane.setPrefViewportHeight(450);
    scrollPane.setPrefViewportWidth(520);

    scrollPane.setMaxHeight(450);

  

    scrollPane.setStyle(
        "-fx-background-color: #FFFFFF;" +
        "-fx-background: #FFFFFF;"
);

    // =================================================
    // DIALOG CONTENT
    // =================================================

    VBox content =
            new VBox(
                    10,
                    scrollPane
            );

    content.setPadding(
            new Insets(5)
    );
dialog.getDialogPane().setStyle(
        "-fx-background-color: #FFFFFF;"
);


    dialog.getDialogPane()
            .setContent(content);

    // =================================================
    // CLOSE BUTTON
    // =================================================

    ButtonType closeButton =
            new ButtonType(
                    "Close",
                    ButtonBar.ButtonData.CANCEL_CLOSE
            );

    dialog.getDialogPane()
            .getButtonTypes()
            .add(closeButton);

    dialog.showAndWait();
}






    // =====================================================
    // MENU BUTTON
    // =====================================================

    private Button menuButton(
            String icon,
            String text,
            boolean active
    ) {

        Button button =
                new Button();

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(23)
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        HBox content =
                new HBox(
                        15,
                        iconLabel,
                        textLabel
                );

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphic(
                content
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                55
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    LIGHT_PINK + ";" +
                    "-fx-background-radius: 12;" +
                    "-fx-padding: 0 15;"
            );

            iconLabel.setTextFill(
                    Color.web(PINK)
            );

        /*    textLabel.setTextFill(
                    Color.web(PINK)
            );*/
            textLabel.setTextFill(
                Color.web(NAVY)
            );

        } 
 else {

    button.setStyle(
            "-fx-background-color: " + LIGHT_PINK + ";" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 0 15;" +
            "-fx-cursor: hand;"
    );

    iconLabel.setTextFill(
            Color.web(NAVY)
    );

    textLabel.setTextFill(
            Color.web(NAVY)
    );

    // Mouse Hover
    button.setOnMouseEntered(e -> {

        button.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 0 15;" +
                "-fx-cursor: hand;"
        );

        iconLabel.setTextFill(
                Color.web(NAVY)
        );

        textLabel.setTextFill(
                Color.web(NAVY)
        );
    });


         


        }

        return button;
    }   
    //click / PRESS Effect 



    // =====================================================
    // STAT CARD
    // =====================================================

    private VBox createStatCard(
            String icon,
            String title,
            String number,
            String iconColor
    ) {

        VBox card =
                new VBox(8);

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(15)
        );

        card.setPrefHeight(
                160
        );

        card.setPrefWidth(
                180
        );

        card.setStyle(
                "-fx-background-color: " +
                WHITE + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 15;"
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setTextFill(
                Color.web(iconColor)
        );

        iconLabel.setFont(
                Font.font(32)
        );

        Label heading =
                new Label(title);

        heading.setTextFill(
                Color.web(NAVY)
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        heading.setWrapText(
                true
        );

        heading.setAlignment(
                Pos.CENTER
        );

        Label value =
                new Label(number);

        value.setTextFill(
                Color.web(NAVY)
        );

        value.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

     

        card.getChildren().addAll(
                iconLabel,
                heading,
                value
              //  view
        );

        return card;
    }

    // =====================================================
    // PANEL
    // =====================================================

    private VBox createPanel() {

        VBox panel =
                new VBox(15);

        panel.setPadding(
                new Insets(18)
        );

        panel.setStyle(
                "-fx-background-color: " +
                WHITE + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 15;"
        );

        return panel;
    }

    // =====================================================
    // PANEL HEADING
    // =====================================================

    private HBox panelHeading(
            String text
    ) {

        Label title =
                new Label(text);

        title.setTextFill(
                Color.web(NAVY)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label view =
                new Label("View All");

        view.setTextFill(
                Color.web("#C03BA0")
        );

        view.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        HBox heading =
                new HBox(
                        title,
                        spacer,
                        view
                );

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        return heading;
    }

    // =====================================================
    // APPOINTMENT TABLE
    // =====================================================

    private VBox createAppointmentPanel() {

        VBox panel =
                createPanel();

        panel.getChildren().add(
                panelHeading(
                        "Today's Appointments"
                )
        );

        TableView<Appointment> table =
                new TableView<>();

        TableColumn<Appointment, String> patient =
                new TableColumn<>("Patient");

        TableColumn<Appointment, String> doctor =
                new TableColumn<>("Doctor");

        TableColumn<Appointment, String> time =
                new TableColumn<>("Time");

        TableColumn<Appointment, String> status =
                new TableColumn<>("Status");

        patient.setCellValueFactory(
                data -> data.getValue().patient
        );

        doctor.setCellValueFactory(
                data -> data.getValue().doctor
        );

        time.setCellValueFactory(
                data -> data.getValue().time
        );

        status.setCellValueFactory(
                data -> data.getValue().status
        );

        patient.setPrefWidth(140);
        doctor.setPrefWidth(130);
        time.setPrefWidth(100);
        status.setPrefWidth(110);

       table.getColumns().addAll(
               patient,
               doctor,
              time,
               status
        );

       
AppointmentController appointmentController =
        new AppointmentController();

List<com.sigma.model.Appointment> appointments =
        appointmentController.getAllAppointments();

for (com.sigma.model.Appointment appointment : appointments) {

    table.getItems().add(
            new Appointment(
                    appointment.getPatient(),
                    appointment.getDoctor(),
                    appointment.getTime(),
                    appointment.getStatus()
            )
    );
}




        table.setPrefHeight(
                255
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-control-inner-background: white;" +
                "-fx-table-cell-border-color: " +
                BORDER + ";" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 10;"
        );

        table.setRowFactory(
                tv -> {

                    TableRow<Appointment> row =
                            new TableRow<>();

                    row.setStyle(
                            "-fx-background-color: white;"
                    );

                    row.hoverProperty().addListener(
                            (obs, oldValue, newValue) -> {

                                if (newValue) {

                                    row.setStyle(
                                            "-fx-background-color: " +
                                            LIGHT_PINK + ";"
                                    );

                                } else {

                                    row.setStyle(
                                            "-fx-background-color: white;"
                                    );
                                }
                            }
                    );

                    return row;
                }
        );

        status.setCellFactory(
                column ->
                        new TableCell<
                                Appointment,
                                String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (empty ||
                                        item == null) {

                                    setText(null);

                                    setStyle(
                                            "-fx-background-color: white;"
                                    );

                                } else {

                                    setText(item);

                                    setAlignment(
                                            Pos.CENTER
                                    );

                                    if (item.equals(
                                            "Confirmed")) {

                                        setStyle(
                                                "-fx-background-color: #E8F8F0;" +
                                                "-fx-text-fill: #35A56B;" +
                                                "-fx-font-weight: bold;"
                                        );

                                    } else if (
                                            item.equals(
                                                    "Waiting")) {

                                        setStyle(
                                                "-fx-background-color: #FFF4E5;" +
                                                "-fx-text-fill: " +
                                                ORANGE + ";" +
                                                "-fx-font-weight: bold;"
                                        );

                                    } else {

                                        setStyle(
                                                "-fx-background-color: #EEF3FF;" +
                                                "-fx-text-fill: " +
                                                BLUE + ";" +
                                                "-fx-font-weight: bold;"
                                        );
                                    }
                                }
                            }
                        }
        );

        panel.getChildren().add(
                table
        );

        return panel;
    }

    // =====================================================
    // BED PIE CHART
    // =====================================================

    private VBox createBedPanel() {

        VBox panel =
                createPanel();

        panel.getChildren().add(
                panelHeading(
                        "Bed Overview"
                )
        );

  

// Get Bed Booking data from Firebase
BedBookingController controller =
        new BedBookingController();

List<BedBooking> bookings =
        controller.getAllBedBookings();

// Total beds in hospital
   

int totalBedCount = 100;


int bookedCount = 0;

for (BedBooking booking : bookings) {

    if ("Booked".equalsIgnoreCase(booking.getStatus())) {
        bookedCount++;
    }
}


int availableCount = totalBedCount - bookedCount;






// Percentages
double availablePercentage =
        totalBedCount == 0
                ? 0
                : (availableCount * 100.0)
                / totalBedCount;

double bookedPercentage =
        totalBedCount == 0
                ? 0
                : (bookedCount * 100.0)
                / totalBedCount;






// Pie Chart
PieChart chart =
        new PieChart();

PieChart.Data available =
        new PieChart.Data(
                "Available",
                availableCount
        );

PieChart.Data booked =
        new PieChart.Data(
                "Booked",
                bookedCount
        );




        chart.getData().addAll(
                available,
                booked
        );

        chart.setLegendVisible(
                false
        );

        chart.setLabelsVisible(
                false
        );

        chart.setStartAngle(
                90
        );

        chart.setPrefSize(
                250,
                250
        );

        Platform.runLater(() -> {

            if (available.getNode() != null) {

                available.getNode().setStyle(
                        "-fx-pie-color: " +
                        PINK + ";"
                );
            }

            if (booked.getNode() != null) {

                booked.getNode().setStyle(
                        "-fx-pie-color: " +
                        GREEN + ";"
                );
            }
        });

      /*  Label total =
                new Label("100");*/ 
                Label total =
        new Label(String.valueOf(totalBedCount));

        total.setTextFill(
                Color.web(NAVY)
        );

        total.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        Label totalBeds =
                new Label("Total Beds");

        totalBeds.setTextFill(
                Color.web(NAVY)
        );

        totalBeds.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        VBox center =
                new VBox(
                        0,
                        total,
                        totalBeds
                );

        center.setAlignment(
                Pos.CENTER
        );

        StackPane chartPane =
                new StackPane(
                        chart,
                        center
                );

        VBox availableBox =
                legendBox(
                        GREEN,
                        "Available",
                        //"32 (32%)"
                         availableCount + " (" +
        Math.round(availablePercentage) + "%)"
                );

       
VBox bookedBox =
        legendBox(
                PINK,
                "Occupied",
                bookedCount + " (" +
                Math.round(bookedPercentage) + "%)"
        );


        VBox legend =
                new VBox(
                        25,
                        availableBox,
                        bookedBox
                );

        legend.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox area =
                new HBox(
                        20,
                        chartPane,
                        legend
                );

        area.setAlignment(
                Pos.CENTER
        );

        panel.getChildren().add(
                area
        );

        return panel;
    }

    // =====================================================
    // PIE LEGEND
    // =====================================================

    private VBox legendBox(
            String color,
            String title,
            String value
    ) {

        Label dot =
                new Label("●");

        dot.setTextFill(
                Color.web(color)
        );

        dot.setFont(
                Font.font(17)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(NAVY)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        HBox first =
                new HBox(
                        8,
                        dot,
                        titleLabel
                );

        first.setAlignment(
                Pos.CENTER_LEFT
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                Color.web(NAVY)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        return new VBox(
                4,
                first,
                valueLabel
        );
    }

    // =====================================================
    // LAB TABLE
    // =====================================================

    private VBox createLabPanel() {

        VBox panel =
                createPanel();

        panel.getChildren().add(
                panelHeading(
                        "Recent Lab Reports"
                )
        );

        TableView<LabReport> table =
                new TableView<>();

        TableColumn<LabReport, String> patient =
                new TableColumn<>("Patient");

        TableColumn<LabReport, String> test =
                new TableColumn<>("Test");

        TableColumn<LabReport, String> date =
                new TableColumn<>("Date");

        TableColumn<LabReport, String> status =
                new TableColumn<>("Status");

        patient.setCellValueFactory(
                data -> data.getValue().patient
        );

        test.setCellValueFactory(
                data -> data.getValue().test
        );

        date.setCellValueFactory(
                data -> data.getValue().date
        );

        status.setCellValueFactory(
                data -> data.getValue().status
        );

       table.getColumns().addAll(
             patient,
              test,
              date,
             status
        );

      

LabrecordsController controller =
        new LabrecordsController();

List<Labrecords> labrecords =
        controller.getAllLabrecords();

for (Labrecords record : labrecords) {

    table.getItems().add(
            new LabReport(
                    record.getPatientName(),
                    record.getTestName(),
                    record.getDate(),
                    record.getStatus()
            )
    );
}






        table.setPrefHeight(
                210
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-control-inner-background: white;" +
                "-fx-table-cell-border-color: " +
                BORDER + ";" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 10;"
        );

        table.setRowFactory(
                tv -> {

                    TableRow<LabReport> row =
                            new TableRow<>();

                    row.setStyle(
                            "-fx-background-color: white;"
                    );

                    row.hoverProperty().addListener(
                            (obs, oldValue, newValue) -> {

                                row.setStyle(
                                        newValue
                                                ? "-fx-background-color: " +
                                                  LIGHT_PINK + ";"
                                                : "-fx-background-color: white;"
                                );
                            }
                    );

                    return row;
                }
        );

        status.setCellFactory(
                column ->
                        new TableCell<
                                LabReport,
                                String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (empty ||
                                        item == null) {

                                    setText(null);

                                } else {

                                    setText(item);

                                    setAlignment(
                                            Pos.CENTER
                                    );

                                    if (item.equals(
                                            "Completed")) {

                                        setStyle(
                                                "-fx-background-color: #E8F8F0;" +
                                                "-fx-text-fill: #35A56B;" +
                                                "-fx-font-weight: bold;"
                                        );

                                    } else {

                                        setStyle(
                                                "-fx-background-color: #FFF4E5;" +
                                                "-fx-text-fill: " +
                                                ORANGE + ";" +
                                                "-fx-font-weight: bold;"
                                        );
                                    }
                                }
                            }
                        }
        );

        panel.getChildren().add(table);
        return panel;
    }

    // =====================================================
    // EMERGENCY PANEL
    // =====================================================

    private VBox createEmergencyPanel() {

        VBox panel =
                createPanel();

        panel.getChildren().add(
                panelHeading(
                        "Emergency Overview"
                )
        );

        HBox cards =
                new HBox(15);

        cards.getChildren().addAll(

                emergencyCard(
                        "Critical",
                        "2",
                        PINK,
                        "#FFF0F7"
                ),

                emergencyCard(
                        "High Priority",
                        "3",
                        ORANGE,
                        "#FFF7EA"
                ),

                emergencyCard(
                        "Normal",
                        "5",
                        BLUE,
                        "#EEF3FF"
                )
        );

        panel.getChildren().add(
                cards
        );

        return panel;
    }

    // =====================================================
    // EMERGENCY CARD
    // =====================================================

    private VBox emergencyCard(
            String title,
            String number,
            String color,
            String background
    ) {

        VBox card =
                new VBox(12);

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefWidth(
                130
        );

        card.setPrefHeight(
                125
        );

        card.setStyle(
                "-fx-background-color: " +
                background + ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 12;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(color)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Label numberLabel =
                new Label(number);

        numberLabel.setTextFill(
                Color.web(color)
        );

        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        card.getChildren().addAll(
                titleLabel,
                numberLabel
        );

        return card;
    }

    // =====================================================
    // APPOINTMENT MODEL
    // =====================================================

    static class Appointment {

        SimpleStringProperty patient;
        SimpleStringProperty doctor;
        SimpleStringProperty time;
        SimpleStringProperty status;

        Appointment(
                String patient,
                String doctor,
                String time,
                String status
        ) {

            this.patient =
                    new SimpleStringProperty(
                            patient
                    );

            this.doctor =
                    new SimpleStringProperty(
                            doctor
                    );

            this.time =
                    new SimpleStringProperty(
                            time
                    );

            this.status =
                    new SimpleStringProperty(
                            status
                    );
        }
    }

    // =====================================================
    // LAB REPORT MODEL
    // =====================================================

    static class LabReport {

        SimpleStringProperty patient;
        SimpleStringProperty test;
        SimpleStringProperty date;
        SimpleStringProperty status;

        LabReport(
                String patient,
                String test,
                String date,
                String status
        ) {

            this.patient =
                    new SimpleStringProperty(
                            patient
                    );

            this.test =
                    new SimpleStringProperty(
                            test
                    );

            this.date =
                    new SimpleStringProperty(
                            date
                    );

            this.status =
                    new SimpleStringProperty(
                            status
                    );
        }
    }     
 
    }
