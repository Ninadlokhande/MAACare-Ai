package com.sigma;

import com.sigma.view.doctorpages.DoctorBasicInformationPage;
import com.sigma.view.doctorpages.DoctorDashboard;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("In main !");
        Application.launch(DoctorDashboard.class, args);
    }
}