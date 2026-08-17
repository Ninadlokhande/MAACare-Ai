package com.sigma.view.adminpages;

import com.sigma.view.scenesettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NewMembers {

    private Scene newMembersScene;

    public Scene getNewMembersScene() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #FAF8FD;");

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(20, 35, 20, 35));
        topBar.setStyle("-fx-background-color: white;-fx-border-color: #E8E2EF;-fx-border-width: 0 0 1 0;");

        HBox topLeft = new HBox(20);
        topLeft.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("←  Back to Dashboard");
        backButton.setStyle("-fx-background-color: #F1E8FF;-fx-text-fill: #713CC3;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;");

        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;"));

        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #F1E8FF;-fx-text-fill: #713CC3;-fx-background-radius: 10px;-fx-border-radius: 10px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;"));

        VBox headingBox = new VBox(4);

        Label title = new Label("New Members");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("#24234F"));

        Label subtitle = new Label("Review and manage newly registered members");
        subtitle.setStyle("-fx-text-fill: #77778D;-fx-font-size: 14px;");

        headingBox.getChildren().addAll(title, subtitle);
        topLeft.getChildren().addAll(backButton, headingBox);

        HBox topRight = new HBox(15);
        topRight.setAlignment(Pos.CENTER_RIGHT);

        Button notification = new Button("🔔  3");
        notification.setStyle("-fx-background-color: #F5EEFF;-fx-text-fill: #713CC3;-fx-background-radius: 12px;-fx-font-size: 14px;-fx-font-weight: bold;-fx-cursor: hand;");

        Label admin = new Label("Admin\nSuper Administrator");
        admin.setStyle("-fx-text-fill: #24234F;-fx-font-size: 13px;-fx-font-weight: bold;");

        topRight.getChildren().addAll(notification, admin);

        topBar.setLeft(topLeft);
        topBar.setRight(topRight);

        VBox content = new VBox(20);
        content.setPadding(new Insets(30, 40, 40, 40));

        Label breadcrumb = new Label("Dashboard   ›   Member Management   ›   New Members");
        breadcrumb.setStyle("-fx-text-fill: #713CC3;-fx-font-size: 13px;");

        HBox statistics = new HBox(15);

        statistics.getChildren().addAll(
            createStatCard("24", "New Requests", "♙", "#F0E5FF", "#713CC3"),
            createStatCard("18", "Pending Review", "◷", "#FFF2DD", "#E99A20"),
            createStatCard("6", "Approved Today", "✓", "#E4F8EF", "#20A66A"),
            createStatCard("0", "Rejected Today", "✕", "#FFE7E7", "#E04747")
        );

        HBox.setHgrow(statistics, Priority.ALWAYS);

        VBox membersCard = new VBox(18);
        membersCard.setPadding(new Insets(25));
        membersCard.setStyle("-fx-background-color: white;-fx-background-radius: 18px;-fx-border-color: #E8E2EF;-fx-border-radius: 18px;-fx-effect: dropshadow(gaussian, rgba(80,50,120,0.07), 15, 0, 0, 3);");

        HBox filterBar = new HBox(12);
        filterBar.setAlignment(Pos.CENTER_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name, email or role...");
        searchField.setPrefHeight(42);
        searchField.setPrefWidth(320);
        searchField.setStyle("-fx-background-color: white;-fx-border-color: #DCD6E8;-fx-border-radius: 9px;-fx-background-radius: 9px;-fx-font-size: 13px;");

        ComboBox<String> roleFilter = new ComboBox<>();
        roleFilter.getItems().addAll("All Roles", "Doctor", "Hospital", "ASHA Worker", "Admin");
        roleFilter.setValue("All Roles");
        roleFilter.setPrefHeight(42);
        roleFilter.setPrefWidth(150);
        roleFilter.setStyle("-fx-background-color: white;-fx-border-color: #DCD6E8;-fx-border-radius: 9px;-fx-background-radius: 9px;");

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.getItems().addAll("All Status", "Pending", "Approved", "Rejected");
        statusFilter.setValue("All Status");
        statusFilter.setPrefHeight(42);
        statusFilter.setPrefWidth(150);
        statusFilter.setStyle("-fx-background-color: white;-fx-border-color: #DCD6E8;-fx-border-radius: 9px;-fx-background-radius: 9px;");

        Button refreshButton = new Button("⟳  Refresh");
        refreshButton.setPrefHeight(42);
        refreshButton.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 9px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;");

        Button exportButton = new Button("⇩  Export");
        exportButton.setPrefHeight(42);
        exportButton.setStyle("-fx-background-color: white;-fx-text-fill: #713CC3;-fx-border-color: #713CC3;-fx-border-radius: 9px;-fx-background-radius: 9px;-fx-font-size: 13px;-fx-font-weight: bold;-fx-cursor: hand;");

        HBox.setHgrow(searchField, Priority.ALWAYS);

        filterBar.getChildren().addAll(searchField, roleFilter, statusFilter, exportButton, refreshButton);

        VBox table = new VBox();

        table.getChildren().add(createTableHeader());

        table.getChildren().addAll(
            createMemberRow("1", "Dr. Priya Sharma", "priya.sharma@example.com", "Doctor", "15 Aug 2026, 10:30 AM"),
            createMemberRow("2", "Dr. Rahul Verma", "rahul.verma@example.com", "Doctor", "15 Aug 2026, 10:25 AM"),
            createMemberRow("3", "City Care Hospital", "info@citycarehospital.com", "Hospital", "15 Aug 2026, 09:45 AM"),
            createMemberRow("4", "Sunita Devi", "sunita.devi@example.com", "ASHA Worker", "15 Aug 2026, 09:20 AM"),
            createMemberRow("5", "Kavita Patel", "kavita.patel@example.com", "ASHA Worker", "15 Aug 2026, 09:10 AM"),
            createMemberRow("6", "Dr. Amit Kumar", "amit.kumar@example.com", "Doctor", "15 Aug 2026, 08:55 AM"),
            createMemberRow("7", "Green Valley Hospital", "contact@greenvalley.com", "Hospital", "15 Aug 2026, 08:40 AM"),
            createMemberRow("8", "Meena Kumari", "meena.kumari@example.com", "ASHA Worker", "15 Aug 2026, 08:30 AM")
        );

        membersCard.getChildren().addAll(filterBar, table);

        HBox pagination = new HBox(15);
        pagination.setAlignment(Pos.CENTER_LEFT);

        Label showing = new Label("Showing 1 to 8 of 24 entries");
        showing.setStyle("-fx-text-fill: #555570;-fx-font-size: 12px;");

        HBox pages = new HBox(7);
        pages.setAlignment(Pos.CENTER);

        Button previous = createPageButton("‹");
        Button page1 = createPageButton("1");
        Button page2 = createPageButton("2");
        Button page3 = createPageButton("3");
        Button next = createPageButton("›");

        page1.setStyle("-fx-background-color: #713CC3;-fx-text-fill: white;-fx-background-radius: 7px;-fx-font-weight: bold;");

        pages.getChildren().addAll(previous, page1, page2, page3, next);

        HBox.setHgrow(pages, Priority.ALWAYS);

        ComboBox<String> pageSize = new ComboBox<>();
        pageSize.getItems().addAll("8 per page", "16 per page", "24 per page");
        pageSize.setValue("8 per page");
        pageSize.setPrefHeight(35);

        pagination.getChildren().addAll(showing, pages, pageSize);

        content.getChildren().addAll(breadcrumb, statistics, membersCard, pagination);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent;-fx-background: #FAF8FD;");

        root.setTop(topBar);
        root.setCenter(scrollPane);

        newMembersScene = new Scene(root, scenesettings.rectanguler2d.getWidth(), scenesettings.rectanguler2d.getHeight());

        return newMembersScene;
    }

    private VBox createStatCard(String value, String title, String icon, String background, String iconColor) {

        VBox card = new VBox(7);
        card.setPadding(new Insets(18));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPrefHeight(100);
        card.setStyle("-fx-background-color: white;-fx-background-radius: 15px;-fx-border-color: #E8E2EF;-fx-border-radius: 15px;-fx-effect: dropshadow(gaussian, rgba(80,50,120,0.05), 10, 0, 0, 2);");

        Label iconLabel = new Label(icon);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setPrefSize(42, 42);
        iconLabel.setStyle("-fx-background-color: " + background + ";-fx-text-fill: " + iconColor + ";-fx-background-radius: 25px;-fx-font-size: 18px;-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 22px;-fx-font-weight: bold;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;");

        HBox.setHgrow(card, Priority.ALWAYS);
        card.getChildren().addAll(iconLabel, valueLabel, titleLabel);

        return card;
    }

    private HBox createTableHeader() {

        HBox header = new HBox();
        header.setPadding(new Insets(14, 10, 14, 10));
        header.setStyle("-fx-background-color: #F8F4FF;-fx-background-radius: 8px 8px 0 0;");

        header.getChildren().addAll(
            createTableLabel("#", 35),
            createTableLabel("Name", 190),
            createTableLabel("Email", 250),
            createTableLabel("Role", 140),
            createTableLabel("Registered On", 175),
            createTableLabel("Status", 110),
            createTableLabel("Actions", 210)
        );

        return header;
    }

    private HBox createMemberRow(String number, String name, String email, String role, String date) {

        HBox row = new HBox();
        row.setPadding(new Insets(14, 10, 14, 10));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: white;-fx-border-color: #EEEAF4;-fx-border-width: 0 0 1 0;");

        Label numberLabel = createTableLabel(number, 35);

        Label nameLabel = createTableLabel(name, 190);
        nameLabel.setStyle("-fx-text-fill: #24234F;-fx-font-size: 13px;-fx-font-weight: bold;");

        Label emailLabel = createTableLabel(email, 250);

        Label roleLabel = createTableLabel(role, 140);
        roleLabel.setStyle("-fx-text-fill: #555570;-fx-font-size: 13px;-fx-font-weight: bold;");

        Label dateLabel = createTableLabel(date, 175);
        dateLabel.setStyle("-fx-text-fill: #77778D;-fx-font-size: 12px;");

        Label status = new Label("Pending");
        status.setAlignment(Pos.CENTER);
        status.setPrefWidth(75);
        status.setStyle("-fx-background-color: #FFF2DD;-fx-text-fill: #D88912;-fx-background-radius: 15px;-fx-padding: 5 10 5 10;-fx-font-size: 11px;-fx-font-weight: bold;");

        HBox statusBox = new HBox(status);
        statusBox.setPrefWidth(110);
        statusBox.setAlignment(Pos.CENTER_LEFT);

        Button approve = new Button("✓  Approve");
        approve.setPrefHeight(34);
        approve.setStyle("-fx-background-color: white;-fx-text-fill: #20A66A;-fx-border-color: #20A66A;-fx-border-radius: 7px;-fx-background-radius: 7px;-fx-font-size: 12px;-fx-font-weight: bold;-fx-cursor: hand;");

        Button reject = new Button("✕  Reject");
        reject.setPrefHeight(34);
        reject.setStyle("-fx-background-color: white;-fx-text-fill: #E04747;-fx-border-color: #E04747;-fx-border-radius: 7px;-fx-background-radius: 7px;-fx-font-size: 12px;-fx-font-weight: bold;-fx-cursor: hand;");

        approve.setOnAction(e -> {
            status.setText("Approved");
            status.setStyle("-fx-background-color: #E4F8EF;-fx-text-fill: #20A66A;-fx-background-radius: 15px;-fx-padding: 5 10 5 10;-fx-font-size: 11px;-fx-font-weight: bold;");
            System.out.println("Approved new member: " + name);
        });

        reject.setOnAction(e -> {
            status.setText("Rejected");
            status.setStyle("-fx-background-color: #FFE7E7;-fx-text-fill: #E04747;-fx-background-radius: 15px;-fx-padding: 5 10 5 10;-fx-font-size: 11px;-fx-font-weight: bold;");
            System.out.println("Rejected new member: " + name);
        });

        HBox actions = new HBox(8, approve, reject);
        actions.setPrefWidth(210);
        actions.setAlignment(Pos.CENTER_LEFT);

        row.getChildren().addAll(numberLabel, nameLabel, emailLabel, roleLabel, dateLabel, statusBox, actions);

        row.setOnMouseEntered(e -> row.setStyle("-fx-background-color: #FCF9FF;-fx-border-color: #E4DCF2;-fx-border-width: 0 0 1 0;"));
        row.setOnMouseExited(e -> row.setStyle("-fx-background-color: white;-fx-border-color: #EEEAF4;-fx-border-width: 0 0 1 0;"));

        return row;
    }

    private Label createTableLabel(String text, double width) {

        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-text-fill: #555570;-fx-font-size: 12px;");

        return label;
    }

    private Button createPageButton(String text) {

        Button button = new Button(text);
        button.setPrefSize(38, 35);
        button.setStyle("-fx-background-color: white;-fx-text-fill: #555570;-fx-border-color: #DED9E8;-fx-border-radius: 7px;-fx-background-radius: 7px;-fx-font-size: 13px;-fx-cursor: hand;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #F1E8FF;-fx-text-fill: #713CC3;-fx-border-color: #BFA4E8;-fx-border-radius: 7px;-fx-background-radius: 7px;-fx-font-size: 13px;-fx-cursor: hand;"));

        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: white;-fx-text-fill: #555570;-fx-border-color: #DED9E8;-fx-border-radius: 7px;-fx-background-radius: 7px;-fx-font-size: 13px;-fx-cursor: hand;"));

        return button;
    }
}