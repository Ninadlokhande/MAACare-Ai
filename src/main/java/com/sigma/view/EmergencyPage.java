package com.sigma.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EmergencyPage {

    // =========================================================
    // COLORS
    // =========================================================

    //private static final String BG = "#F7E6F3";
    private static final String BG="#F8F7FC";
   


    private static final String WHITE = "#FFFFFF";
    private static final String NAVY = "#17184F";

    private static final String PINK = "#E83E83";
    private static final String LIGHT_PINK = "#FFF0F7";

    private static final String PURPLE = "#8056C5";
    private static final String GREEN = "#67C98F";
    private static final String BLUE = "#5578D6";
    private static final String ORANGE = "#F2A33A";

    //private static final String BORDER = "#E9E6EF";
        private static final String BORDER="#E8DEFA";

  
  private static final String GREY="#777775";

    private BorderPane root;
public EmergencyPage(){ 
        createView();
}
public BorderPane getView(){ 
        return root;
}




    // =========================================================
    // MAIN SHOW METHOD
    // =========================================================

   

   private void createView(){

      //  BorderPane root = new BorderPane();
root=new BorderPane();
        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        // =====================================================
        // TOP HEADER
        // =====================================================

        HBox header = new HBox();

        header.setPadding(
                new Insets(18, 35, 12, 35)
        );

        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);


          header.getChildren().addAll(
             //  menuBtn,
              //ss  headerSpacer
               // search,
               // notification
              //  adminIcon,
              //  admin
        );

        root.setTop(header);


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(25);

        content.setPadding(
                new Insets(5, 38, 35, 38)
        );


        // =====================================================
        // TITLE + BACK BUTTON
        // =====================================================

        HBox titleRow = new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox titleBox = new VBox(5);


        Label title = new Label("Emergency");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        36
                )
        );

        title.setTextFill(
                Color.web(NAVY)
        );


      /*   Label subtitle = new Label(
                "Monitor and manage all emergency cases"
        );*/ 

        Label subtitle = new Label(
        "24/7 Emergency Support and Assistance"
);

        subtitle.setFont(
                Font.font("Arial", 16)
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );


        titleBox.getChildren().addAll(
                title,
                subtitle
        );


        Region titleSpacer = new Region();

        HBox.setHgrow(
                titleSpacer,
                Priority.ALWAYS
        );


        


        titleRow.getChildren().addAll(
                titleBox,
                titleSpacer
              //  backBtn
        );


        // =====================================================
        // AMBULANCE HERO SECTION
        // =====================================================

        HBox ambulanceBox = new HBox();

        ambulanceBox.setAlignment(
                Pos.CENTER_LEFT
        );

        ambulanceBox.setPadding(
                new Insets(10, 25, 10, 25)
        );

        ambulanceBox.setMinHeight(190);

        ambulanceBox.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;"
        );


        // Left text

        VBox ambulanceText = new VBox(6);
        ambulanceText.setPrefWidth(500);

        Label ambulanceTitle =
                new Label(
                        "Emergency Response Center"
                );

        ambulanceTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        ambulanceTitle.setTextFill(
                Color.web(PINK)
        );




        Label ambulanceSubtitle =                                                                                                                                                                                                                                                                                                                                                      
                new Label(                        "Fast • Reliable • Always Ready"
                );

        ambulanceSubtitle.setFont(
                Font.font("Arial", 14)                                                                                                  
        );

        ambulanceSubtitle.setTextFill(
                Color.web(GREY)
        );


        ambulanceText.getChildren().addAll(
                ambulanceTitle,
                ambulanceSubtitle
        );


        // Spacer

        Region ambulanceSpacer = new Region();
ambulanceSpacer.setMinWidth(60);
        HBox.setHgrow(
                ambulanceSpacer,
                Priority.ALWAYS
        );


        // =====================================================
        // ACTUAL AMBULANCE IMAGE
        // =====================================================

        Image ambulanceImage = new Image("assets\\images\\logo\\ambulanceImage.png");



        ImageView ambulanceView =
                new ImageView(ambulanceImage);


        ambulanceView.setFitWidth(430);
        ambulanceView.setFitHeight(180);

        ambulanceView.setPreserveRatio(true);
        ambulanceView.setSmooth(true);   



// =====================================================
        // EMERGENCY HOTLINE
        // =====================================================

        VBox hotline =
                new VBox(5);    


        hotline.setAlignment(
                Pos.CENTER_LEFT
        ); 
        hotline.setPrefWidth(300);
        hotline.setMaxWidth(300);



        hotline.setPadding(
                new Insets(
                        15,
                        22,
                        15,
                        22
                )
        );


        hotline.setStyle(
                "-fx-background-color: " + PINK + ";" +
                "-fx-background-radius: 15;"
        ); 
      //  hotline.setPrefWidth(400);
       // hotline.setMaxWidth(400);


        Label hotlineTitle =
                new Label(
                        "☎  Emergency Helpline"
                );

        hotlineTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        hotlineTitle.setTextFill(
                Color.WHITE
        );


        Label hotlineNumber =
                new Label("108");

        hotlineNumber.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        35
                )
        );

        hotlineNumber.setTextFill(
                Color.WHITE
        );


        Label hotlineSub =
                new Label(
                        "24/7 Emergency Support"
                );

        hotlineSub.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        hotlineSub.setTextFill(
                Color.WHITE
        );


        hotline.getChildren().addAll(
                hotlineTitle,
                hotlineNumber,
                hotlineSub
        );





        ambulanceBox.getChildren().addAll(
                ambulanceText,
                hotline,
                ambulanceSpacer,
                ambulanceView
        );


        // =====================================================
        // STAT CARDS
        // =====================================================

     /*   HBox stats = new HBox(15);

        stats.setAlignment(
                Pos.CENTER
        );


        VBox totalCard =
                createStatCard(
                        "🚑",
                        "Total Emergencies",
                        "48",
                        "This Month",
                        PINK
                );


        VBox criticalCard =
                createStatCard(
                        "🔔",
                        "Critical Cases",
                        "8",
                        "Require Immediate Attention",
                        ORANGE
                );


        VBox stabilizedCard =
                createStatCard(
                        "♥",
                        "Stabilized",
                        "28",
                        "This Month",
                        GREEN
                );


        VBox admittedCard =
                createStatCard(
                        "🛏",
                        "Admitted",
                        "32",
                        "This Month",
                        PURPLE
                );


        VBox dischargedCard =
                createStatCard(
                        "✓",
                        "Discharged",
                        "16",
                        "This Month",
                        BLUE
                );


        stats.getChildren().addAll(
                totalCard,
                criticalCard,
                stabilizedCard,
                admittedCard,
                dischargedCard
        );*/   

// =====================================================
// EMERGENCY INFORMATION CARDS
// =====================================================

HBox stats = new HBox(30);

stats.setAlignment(
        Pos.CENTER
);

/*VBox helplineCard =
        createStatCard(
                "📞",
                "Emergency Helpline",
                "108",
                "Available 24/7",
                PINK
        );

VBox ambulanceCard =
        createStatCard(
                "🚑",
                "Ambulance Support",
                "Available",
                "Emergency Transport",
                ORANGE
        );

VBox emergencyCareCard =
        createStatCard(
                "🏥",
                "Emergency Care",
                "24/7",
                "Hospital Emergency Services",
                PURPLE
        );

stats.getChildren().addAll(
        helplineCard,
        ambulanceCard,
        emergencyCareCard
);*/ 


VBox ambulanceCard =
        createStatCard(
                "🚑",
                "Ambulance Support",
                "Available",
                "Emergency Transport",
                ORANGE
        );

VBox emergencyCareCard =
        createStatCard(
                "🏥",
                "Emergency Care",
                "24/7",
                "Hospital Emergency Services",
                PURPLE
        );

VBox emergencyTeamCard =
        createStatCard(
             //   "🩺",
                             "👥",

                "Emergency Team",
                "Ready",
                "Immediate Assistance",
                GREEN
        ); 

        stats.getChildren().addAll(
        ambulanceCard,
        emergencyCareCard,
        emergencyTeamCard
);




        // =====================================================
        // STATUS OVERVIEW
        // =====================================================

     /*    VBox statusMain =
                new VBox(15);

        Label statusTitle =
                new Label(
                        "Emergency Status Overview"
                );

        statusTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        statusTitle.setTextFill(
                Color.web(NAVY)
        );


        HBox statusCards =
                new HBox(15);


        statusCards.getChildren().addAll(

                createStatusCard(
                        "🔔",
                        "Critical",
                        "8",
                        "Needs Immediate\nAttention",
                        PINK
                ),

                createStatusCard(
                        "⌛",
                        "High Priority",
                        "12",
                        "Short Response\nTime",
                        ORANGE
                ),

                createStatusCard(
                        "〽",
                        "Under Observation",
                        "18",
                        "Monitoring\nPatients",
                        BLUE
                ),

                createStatusCard(
                        "✓",
                        "Stable",
                        "28",
                        "Condition\nStable",
                        GREEN
                )
        );


        statusMain.getChildren().addAll(
                statusTitle,
                statusCards
        );


        statusMain.setPadding(
                new Insets(22)
        );


        statusMain.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );*/


        // =====================================================
        // EMERGENCY SUMMARY CHART
        // =====================================================

    /*   VBox chartBox =
                new VBox(10);


        Label chartTitle =
                new Label(
                        "Emergency Summary"
                );

        chartTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        chartTitle.setTextFill(
                Color.web(NAVY)
        );


        PieChart chart =
                new PieChart();


        chart.getData().add(
                new PieChart.Data(
                        "Critical",
                        8
                )
        );


        chart.getData().add(
                new PieChart.Data(
                        "High Priority",
                        12
                )
        );


        chart.getData().add(
                new PieChart.Data(
                        "Under Observation",
                        18
                )
        );


        chart.getData().add(
                new PieChart.Data(
                        "Stable",
                        28
                )
        );


        chart.setLegendVisible(true);

        chart.setLabelsVisible(false);

        chart.setPrefSize(
                450,
                250
        );


        chartBox.getChildren().addAll(
                chartTitle,
                chart
        );


        chartBox.setPadding(
                new Insets(22)
        );


        chartBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );*/


        // =====================================================
        // SECOND ROW
        // =====================================================

     /*   HBox secondRow =
                new HBox(18);


        HBox.setHgrow(
                statusMain,
                Priority.ALWAYS
        );


        HBox.setHgrow(
                chartBox,
                Priority.ALWAYS
        );


        secondRow.getChildren().addAll(
                statusMain,
                chartBox
        );*/


        // =====================================================
        // BOTTOM AMBULANCE MESSAGE
        // =====================================================

        HBox bottom =
                new HBox(); 

                bottom.setPrefHeight(150);


        bottom.setAlignment(
                Pos.CENTER_LEFT
        );


        bottom.setPadding(
                new Insets(
                     //   12,
                     20,
                        25,
                      //  12,
                      20,
                        25
                )
        );





        bottom.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;"
        );


        // =====================================================
        // ACTUAL BOTTOM AMBULANCE IMAGE
        // =====================================================

     /*   Image bottomAmbulanceImage =
                new Image("assets\\images\\logo\\ambulanceImage.png");


        ImageView bottomAmbulance =
                new ImageView(
                        bottomAmbulanceImage
                );


        bottomAmbulance.setFitWidth(220);
        bottomAmbulance.setFitHeight(120);

        bottomAmbulance.setPreserveRatio(true);
        bottomAmbulance.setSmooth(true);*/


        // Bottom text

        VBox bottomText =
                new VBox(8); 

                bottomText.setAlignment(Pos.CENTER_LEFT);


        Label everySecond =
                new Label(
                        "Every Second Counts"
                );

        everySecond.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        everySecond.setTextFill(
                Color.web(PINK)
        );


        Label ready =
                new Label(
                        "Our emergency team is always ready to help.\n" +
                        "Fast. Reliable. Always Here."
                );

        ready.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        ready.setTextFill(
                Color.web(NAVY)
        );


        bottomText.getChildren().addAll(
                everySecond,
                ready
        );


        bottom.getChildren().addAll(
              //  bottomAmbulance,
              //  new Label("     "),
                bottomText
        );


        

      
        // =====================================================
        // BOTTOM ROW
        // =====================================================

        HBox bottomRow =
                new HBox(18); 

                VBox.setMargin(
        bottomRow,
        new Insets(25, 0, 0, 0)
);


        HBox.setHgrow(
                bottom,
                Priority.ALWAYS
        );


        HBox.setHgrow(
                hotline,
                Priority.ALWAYS
        );


        bottomRow.getChildren().addAll(
                bottom
              //  hotline
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
                titleRow,
                ambulanceBox,
                stats,
             //   secondRow,
                bottomRow
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(content);


        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );


        root.setCenter(
                scrollPane
        ); 
}


        


    // =========================================================
    // STAT CARD METHOD
    // =========================================================

    private static VBox createStatCard(
            String icon,
            String title,
            String number,
            String subText,
            String color) {


        VBox card =
                new VBox(8);


        card.setPadding(
                new Insets(18)
        );


        card.setPrefWidth(220);
        card.setPrefHeight(135);


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 15;" +
                "-fx-background-radius: 15;"
        );


        HBox top =
                new HBox(12);


        top.setAlignment(
                Pos.CENTER_LEFT
        );


        Label iconLabel =
                new Label(icon);


        iconLabel.setFont(
                Font.font(28)
        );


        Label titleLabel =
                new Label(title);


        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        titleLabel.setTextFill(
                Color.web(NAVY)
        );


        top.getChildren().addAll(
                iconLabel,
                titleLabel
        );


        Label numberLabel =
                new Label(number);


        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );


        numberLabel.setTextFill(
                Color.web(NAVY)
        );


        Label subLabel =
                new Label(subText);


        subLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        subLabel.setTextFill(
                Color.web(color)
        );


        card.getChildren().addAll(
                top,
                numberLabel,
                subLabel
        );


        return card;
    }


    // =========================================================
    // STATUS CARD METHOD
    // =========================================================

  /* private static VBox createStatusCard(
            String icon,
            String title,
            String number,
            String description,
            String color) {


        VBox card =
                new VBox(7);


        card.setAlignment(
                Pos.CENTER
        );


        card.setPadding(
                new Insets(15)
        );


        card.setPrefWidth(145);
        card.setPrefHeight(190);


        card.setStyle(
                "-fx-background-color: " + LIGHT_PINK + ";" +
                "-fx-background-radius: 12;"
        );


        Label iconLabel =
                new Label(icon);


        iconLabel.setFont(
                Font.font(28)
        );


        Label titleLabel =
                new Label(title);


        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        titleLabel.setTextFill(
                Color.web(NAVY)
        );


        Label numberLabel =
                new Label(number);


        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );


        numberLabel.setTextFill(
                Color.web(NAVY)
        );


        Label descriptionLabel =
                new Label(description);


        descriptionLabel.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        descriptionLabel.setTextFill(
                Color.web(color)
        );


        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                numberLabel,
                descriptionLabel
        );


        return card;
    }*/
} 
