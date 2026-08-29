
package com.sigma;

import com.sigma.view.doctorpages.DoctorDashboard;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        System.out.println("Starting Application");
        System.out.println("Welcome to Maacare Ai");
        System.out.println(
                "The Complete New World of Maternity HealthCare...");

        Application.launch(DoctorDashboard.class, args);
    }
}
