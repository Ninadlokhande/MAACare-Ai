
package com.sigma.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Loginpage {
    private Scene loginpagScene;

    public Scene gotologinpage() {

        Text t1 = new Text("This is login page");

        StackPane root = new StackPane();

        root.setAlignment(Pos.CENTER);

        root.getChildren().add(t1);

        loginpagScene = new Scene(root);
        


        return loginpagScene;
    }
}