package com.sigma.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FontAwesomeTest extends Application {

    @Override
    public void start(Stage stage) {

        FontAwesomeIconView icon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.HEART
                );

        icon.setSize("80");
        icon.setFill(Color.PINK);

        StackPane root = new StackPane(icon);

        Scene scene =
                new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("FontAwesome Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}