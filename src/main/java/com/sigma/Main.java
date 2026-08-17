package com.sigma;

import com.sigma.view.DoctorBasicInformationPage;
import com.sigma.view.DoctorDashboard;
import com.sigma.view.Welcomepage;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome");
        Application.launch(Welcomepage.class, args);
    }
}