package com.sigma.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DoctorAppointments {

    private Scene AppointmentPageScene;

    Scene getAppointmentPageScene(Runnable callBackToDashboard) {
        Button BackToDashboardBtn = new Button("Go To Dashboard");
        BackToDashboardBtn.setStyle("-fx-padding: 20px 20px; -fx-background-color: #ffffff; ");

        BackToDashboardBtn.setOnAction(e -> {
            System.out.println("Back to dashboard clicked");
            if (callBackToDashboard != null) {
                callBackToDashboard.run();
            }
        });

        VBox vb = new VBox();
        vb.getChildren().add(BackToDashboardBtn);

        AppointmentPageScene = new Scene(vb, 1400, 850);
        return AppointmentPageScene;

    }

}
