package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class AdminRequestApproval {
    private BorderPane root;
    private VBox memberList;
    private Label memberCount;
    private static final String PURPLE="#9C49C8", DARK="#24234F", GREY="#77778D", BORDER="#F0DCE8", BG="#FFF8FC";

    public BorderPane getAdminrequestApprovalRoot(){
        root=new BorderPane(); root.setStyle("-fx-background-color:"+BG+";");
        VBox content=new VBox(20); content.setPadding(new Insets(30,40,40,40));
        Label title=new Label("Members"); title.setFont(Font.font("Arial",FontWeight.BOLD,30)); title.setTextFill(Color.web(DARK));
        Label subtitle=new Label("All Mother, Doctor, Hospital and ASHA Worker profiles currently available in Firebase."); subtitle.setStyle("-fx-text-fill:"+GREY+";-fx-font-size:14px;");
        VBox header=new VBox(6,title,subtitle);
        HBox countHeader=new HBox(); countHeader.setPadding(new Insets(18)); countHeader.setAlignment(Pos.CENTER_LEFT); countHeader.setStyle("-fx-background-color:white;-fx-background-radius:15px 15px 0 0;-fx-border-color:"+BORDER+";-fx-border-radius:15px 15px 0 0;");
        Label label=new Label("Registered Members"); label.setFont(Font.font("Arial",FontWeight.BOLD,18)); label.setTextFill(Color.web(DARK)); memberCount=new Label(); memberCount.setStyle("-fx-background-color:"+PURPLE+";-fx-text-fill:white;-fx-background-radius:10px;-fx-padding:7px 13px;-fx-font-size:12px;-fx-font-weight:bold;"); HBox.setHgrow(label,Priority.ALWAYS); countHeader.getChildren().addAll(label,memberCount);
        memberList=new VBox(12); memberList.setPadding(new Insets(18)); memberList.setStyle("-fx-background-color:white;-fx-background-radius:0 0 15px 15px;-fx-border-color:"+BORDER+";-fx-border-radius:0 0 15px 15px;");
        content.getChildren().addAll(header,new VBox(countHeader,memberList)); root.setCenter(new ScrollPane(content)); ((ScrollPane)root.getCenter()).setFitToWidth(true); ((ScrollPane)root.getCenter()).setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); ((ScrollPane)root.getCenter()).setStyle("-fx-background-color:transparent;-fx-background:"+BG+";"); refreshMembers(); return root;
    }

    public void refreshMembers(){
        if(memberList==null)return; memberList.getChildren().clear(); List<AdminDashboard.MemberRecord> list=AdminDashboard.loadAllMembers(); memberCount.setText(String.valueOf(list.size()));
        for(AdminDashboard.MemberRecord m:list) memberList.getChildren().add(card(m));
        if(list.isEmpty())memberList.getChildren().add(new Label("No member profiles found in Firebase."));
    }

    private VBox card(AdminDashboard.MemberRecord m){
        VBox card=new VBox(12); card.setPadding(new Insets(18)); card.setStyle("-fx-background-color:#FCFAFF;-fx-background-radius:12px;-fx-border-color:#E8E0F1;-fx-border-radius:12px;");
        HBox top=new HBox(15); top.setAlignment(Pos.CENTER_LEFT); Label avatar=new Label(initials(m.name)); avatar.setMinSize(50,50);avatar.setMaxSize(50,50);avatar.setAlignment(Pos.CENTER);avatar.setStyle("-fx-background-color:#FFF0F6;-fx-background-radius:25px;-fx-text-fill:"+PURPLE+";-fx-font-size:15px;-fx-font-weight:bold;");
        VBox info=new VBox(4);Label name=new Label(m.name);name.setFont(Font.font("Arial",FontWeight.BOLD,16));name.setTextFill(Color.web(DARK));Label role=new Label(m.role);role.setStyle("-fx-background-color:#FFF0F6;-fx-text-fill:"+PURPLE+";-fx-background-radius:8px;-fx-padding:4 8;-fx-font-size:11px;-fx-font-weight:bold;");Label email=new Label(m.email.isEmpty()?"Email not available":m.email);email.setTextFill(Color.web(GREY));info.getChildren().addAll(name,role,email);HBox.setHgrow(info,Priority.ALWAYS);
        Button view=new Button("View Details");view.setStyle("-fx-background-color:#FFF0F6;-fx-text-fill:#C53F83;-fx-font-weight:bold;-fx-background-radius:8px;-fx-padding:9 14;-fx-cursor:hand;");view.setOnAction(e->showDetails(m));top.getChildren().addAll(avatar,info,view);
        Label id=new Label("ID: "+m.id);id.setTextFill(Color.web(GREY)); Label phone=new Label("Phone: "+(m.phone.isEmpty()?"Not available":m.phone));phone.setTextFill(Color.web(GREY)); Label extra=new Label(m.extra);extra.setWrapText(true);extra.setTextFill(Color.web(GREY));card.getChildren().addAll(top,id,phone,extra);return card;
    }
    private void showDetails(AdminDashboard.MemberRecord m){Alert a=new Alert(Alert.AlertType.INFORMATION);a.setTitle("Member Details");a.setHeaderText(m.name+" — "+m.role);a.setContentText("ID: "+m.id+"\nEmail: "+m.email+"\nPhone: "+m.phone+"\nQualification/Type: "+m.qualification+"\nAddress: "+m.address+"\n"+m.extra);a.showAndWait();}
    private String initials(String n){if(n==null||n.isBlank())return "?";String[]p=n.trim().split("\\s+");return p.length==1?p[0].substring(0,Math.min(2,p[0].length())).toUpperCase():(p[0].substring(0,1)+p[p.length-1].substring(0,1)).toUpperCase();}
}
