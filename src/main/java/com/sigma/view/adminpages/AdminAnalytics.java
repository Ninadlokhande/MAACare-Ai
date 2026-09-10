package com.sigma.view.adminpages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class AdminAnalytics {
    private static final String DARK="#24234F", GREY="#77778D", BG="#FFF8FC";

    public Node getAnalyticsRoot() {
        List<AdminDashboard.MemberRecord> members = AdminDashboard.loadAllMembers();
        long mothers=count(members,"Mother"), doctors=count(members,"Doctor"), hospitals=count(members,"Hospital"), asha=count(members,"ASHA Worker");
        long total=mothers+doctors+hospitals+asha;

        VBox content=new VBox(20); content.setPadding(new Insets(28)); content.setStyle("-fx-background-color:"+BG+";");
        Label title=new Label("Analytics"); title.setStyle("-fx-text-fill:"+DARK+";-fx-font-size:30px;-fx-font-weight:bold;");
        Label subtitle=new Label("Live member statistics loaded from Firebase."); subtitle.setStyle("-fx-text-fill:"+GREY+";-fx-font-size:14px;");
        content.getChildren().add(new VBox(5,title,subtitle));

        HBox stats=new HBox(15,card("Total Members",total),card("Mothers",mothers),card("Doctors",doctors),card("Hospitals",hospitals),card("ASHA Workers",asha));
        for(Node n:stats.getChildren()) HBox.setHgrow(n,Priority.ALWAYS);
        content.getChildren().add(stats);

        HBox charts=new HBox(18);
        VBox distribution=chartBox("Current Role Distribution",pie(mothers,doctors,hospitals,asha));
        VBox counts=chartBox("Members by Role",bar(mothers,doctors,hospitals,asha));
        HBox.setHgrow(distribution,Priority.ALWAYS); HBox.setHgrow(counts,Priority.ALWAYS); charts.getChildren().addAll(distribution,counts); content.getChildren().add(charts);

        VBox summary=box("At a Glance");
        summary.getChildren().addAll(row("Mother / Family",mothers),row("Doctors",doctors),row("Hospitals",hospitals),row("ASHA Workers",asha),row("Total Members",total));
        content.getChildren().add(summary);

        ScrollPane scroll=new ScrollPane(content); scroll.setFitToWidth(true); scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); scroll.setStyle("-fx-background-color:transparent;-fx-background:"+BG+";"); return scroll;
    }

    private long count(List<AdminDashboard.MemberRecord> l,String role){return l.stream().filter(x->role.equals(x.role)).count();}
    private VBox card(String title,long value){VBox b=box("");Label t=new Label(title),v=new Label(String.valueOf(value));t.setTextFill(Color.web(GREY));v.setTextFill(Color.web(DARK));v.setFont(Font.font("Arial",FontWeight.BOLD,24));b.getChildren().addAll(t,v);return b;}
    private VBox box(String title){VBox b=new VBox(10);b.setPadding(new Insets(18));b.setStyle("-fx-background-color:white;-fx-background-radius:15px;-fx-border-color:#F0DCE8;-fx-border-radius:15px;");if(!title.isEmpty()){Label l=new Label(title);l.setFont(Font.font("Arial",FontWeight.BOLD,18));l.setTextFill(Color.web(DARK));b.getChildren().add(l);}return b;}
    private VBox chartBox(String title,Node chart){VBox b=box(title);b.getChildren().add(chart);VBox.setVgrow(chart,Priority.ALWAYS);return b;}
    private PieChart pie(long m,long d,long h,long a){PieChart p=new PieChart();if(m>0)p.getData().add(new PieChart.Data("Mothers",m));if(d>0)p.getData().add(new PieChart.Data("Doctors",d));if(h>0)p.getData().add(new PieChart.Data("Hospitals",h));if(a>0)p.getData().add(new PieChart.Data("ASHA Workers",a));p.setLegendVisible(true);return p;}
    private BarChart<String,Number> bar(long m,long d,long h,long a){CategoryAxis x=new CategoryAxis();NumberAxis y=new NumberAxis();x.setLabel("Role");y.setLabel("Members");BarChart<String,Number> c=new BarChart<>(x,y);XYChart.Series<String,Number>s=new XYChart.Series<>();s.getData().add(new XYChart.Data<>("Mothers",m));s.getData().add(new XYChart.Data<>("Doctors",d));s.getData().add(new XYChart.Data<>("Hospitals",h));s.getData().add(new XYChart.Data<>("ASHA",a));c.getData().add(s);c.setLegendVisible(false);return c;}
    private HBox row(String name,long value){Label n=new Label(name),v=new Label(String.valueOf(value));n.setTextFill(Color.web(GREY));v.setTextFill(Color.web(DARK));v.setFont(Font.font("Arial",FontWeight.BOLD,14));RegionSpacer s=new RegionSpacer();HBox.setHgrow(s,Priority.ALWAYS);return new HBox(10,n,s,v);}
    private static class RegionSpacer extends javafx.scene.layout.Region {}
}
