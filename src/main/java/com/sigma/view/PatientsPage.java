package com.sigma.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PatientsPage {

        public static void show() {

                VBox root = new VBox(20);

                root.setPadding(
                                new Insets(28, 35, 28, 35));

                Theme.applyBackground(root);

                // HEADER

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                VBox heading = Theme.pageHeader(
                                "My Patients",
                                "View and manage all your patients.");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                header.getChildren().addAll(
                                heading,
                                spacer,
                                Theme.backButton());

                // FILTER

                HBox filter = new HBox(10);

                filter.setAlignment(
                                Pos.CENTER_LEFT);

                filter.setPadding(
                                new Insets(14));

                filter.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-color: " +
                                                Theme.BORDER + ";" +
                                                "-fx-border-radius: 10;");

                TextField search = new TextField();

                search.setPromptText(
                                "Search patient name or ID...");

                search.setPrefWidth(280);

                ComboBox<String> gender = new ComboBox<>();

                gender.getItems().addAll(
                                "All",
                                "Female",
                                "Male");

                gender.setValue("All");

                Button add = Theme.primaryButton(
                                "+  Add Patient");

                Region filterSpacer = new Region();

                HBox.setHgrow(
                                filterSpacer,
                                Priority.ALWAYS);

                filter.getChildren().addAll(
                                search,
                                gender,
                                filterSpacer,
                                add);

                // TABLE

                TableView<Patient> table = new TableView<>();

                table.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<Patient, String> name = new TableColumn<>("Patient");

                TableColumn<Patient, String> age = new TableColumn<>("Age / Gender");

                TableColumn<Patient, String> contact = new TableColumn<>("Contact");

                TableColumn<Patient, String> lastVisit = new TableColumn<>("Last Visit");

                TableColumn<Patient, String> nextVisit = new TableColumn<>("Next Visit");

                TableColumn<Patient, String> action = new TableColumn<>("Action");

                name.setCellValueFactory(
                                d -> d.getValue().nameProperty());

                age.setCellValueFactory(
                                d -> d.getValue().ageProperty());

                contact.setCellValueFactory(
                                d -> d.getValue().contactProperty());

                lastVisit.setCellValueFactory(
                                d -> d.getValue().lastVisitProperty());

                nextVisit.setCellValueFactory(
                                d -> d.getValue().nextVisitProperty());

                action.setCellValueFactory(
                                d -> d.getValue().actionProperty());

                table.getColumns().addAll(
                                name,
                                age,
                                contact,
                                lastVisit,
                                nextVisit,
                                action);

                table.getItems().addAll(
                                new Patient(
                                                "Priya Sharma",
                                                "28 Y / Female",
                                                "9876543210",
                                                "07 May 2024",
                                                "14 May 2024",
                                                "◉  ⋮"),

                                new Patient(
                                                "Neha Kulkarni",
                                                "32 Y / Female",
                                                "9765432109",
                                                "05 May 2024",
                                                "15 May 2024",
                                                "◉  ⋮"),

                                new Patient(
                                                "Sneha Patil",
                                                "26 Y / Female",
                                                "9988776655",
                                                "04 May 2024",
                                                "18 May 2024",
                                                "◉  ⋮"),

                                new Patient(
                                                "Ayesha Khan",
                                                "29 Y / Female",
                                                "9871234567",
                                                "03 May 2024",
                                                "17 May 2024",
                                                "◉  ⋮"),

                                new Patient(
                                                "Ritika Singh",
                                                "30 Y / Female",
                                                "9812345670",
                                                "01 May 2024",
                                                "12 May 2024",
                                                "◉  ⋮"));

                VBox.setVgrow(
                                table,
                                Priority.ALWAYS);

                root.getChildren().addAll(
                                header,
                                filter,
                                table);

                Scene scene = new Scene(
                                root,
                                Theme.WIDTH,
                                Theme.HEIGHT);

                DoctorDashboard.dashboardStage.setScene(scene);

                DoctorDashboard.dashboardStage.setWidth(
                                Theme.WIDTH);

                DoctorDashboard.dashboardStage.setHeight(
                                Theme.HEIGHT);

                DoctorDashboard.dashboardStage.centerOnScreen();
        }

        public static class Patient {

                private final javafx.beans.property.SimpleStringProperty name;
                private final javafx.beans.property.SimpleStringProperty age;
                private final javafx.beans.property.SimpleStringProperty contact;
                private final javafx.beans.property.SimpleStringProperty lastVisit;
                private final javafx.beans.property.SimpleStringProperty nextVisit;
                private final javafx.beans.property.SimpleStringProperty action;

                public Patient(
                                String name,
                                String age,
                                String contact,
                                String lastVisit,
                                String nextVisit,
                                String action) {

                        this.name = new javafx.beans.property.SimpleStringProperty(name);

                        this.age = new javafx.beans.property.SimpleStringProperty(age);

                        this.contact = new javafx.beans.property.SimpleStringProperty(contact);

                        this.lastVisit = new javafx.beans.property.SimpleStringProperty(lastVisit);

                        this.nextVisit = new javafx.beans.property.SimpleStringProperty(nextVisit);

                        this.action = new javafx.beans.property.SimpleStringProperty(action);
                }

                public javafx.beans.property.StringProperty nameProperty() {
                        return name;
                }

                public javafx.beans.property.StringProperty ageProperty() {
                        return age;
                }

                public javafx.beans.property.StringProperty contactProperty() {
                        return contact;
                }

                public javafx.beans.property.StringProperty lastVisitProperty() {
                        return lastVisit;
                }

                public javafx.beans.property.StringProperty nextVisitProperty() {
                        return nextVisit;
                }

                public javafx.beans.property.StringProperty actionProperty() {
                        return action;
                }
        }
}