package com.sigma.view;


import com.sigma.config.FirebaseConfig;
import com.sigma.model.Appointment;

 import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.transformation.FilteredList;
import com.sigma.controller.HospitalController.AppointmentController;





public class AppointmentPage{

        private final String PINK="#E91E63";
        private final String LIGHT_PINK="#FFF0F6";
        private final String BORDER="#E8E8EF";
        private final String TEXT="#25253A";
        private final String MUTED="#777789";
         
       
       private static final String BG="#F7EAF5";
       

         

         private final AppointmentController controller=new AppointmentController();
         private ObservableList<Appointment> data=FXCollections.observableArrayList();



        private VBox Content;

        private Label totalValueLabel;
private Label todayValueLabel;
private Label confirmedValueLabel;
private Label pendingValueLabel;

        // Constructor 

             


    public AppointmentPage(){ 
        createAppointmentPage();
    }

        // GET VIEW 


        public VBox getView(){ 
                return Content;
        }



//Create AppointmentPage



private void createAppointmentPage() { 


        Content = new VBox(0);
                Content.setBackground( 
                       new Background(
                        new BackgroundFill(
                                Color.web(BG),
                                 
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                                
                        )
                )
                );


        // =====================================================
        // TOP HEADER
        // =====================================================

        VBox header = new VBox(5);

        header.setPadding(
                new Insets(20, 25, 10, 25)
        );

        // BACK BUTTON


        // TITLE
        Label title = new Label("Appointments");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );

        Label subtitle = new Label(
                "Manage and view all patient appointments"
        );

        subtitle.setFont(
                Font.font("Arial", 11)
        );

        subtitle.setTextFill(
                Color.web(MUTED)
        );

        VBox titleBox = new VBox(
                2,
                title,
                subtitle
        );

        // SEARCH
        TextField search = new TextField();

        search.setPromptText(
                "⌕   Search appointments, patients, doctors..."
        );

        search.setPrefWidth(300);
        search.setPrefHeight(38);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E5E5EC;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 12;"
        );

        // NOTIFICATION
        Label notification = new Label("♧");

        notification.setFont(
                Font.font("Arial", 20)
        );

        notification.setTextFill(
                Color.web(PINK)
        );

        // USER
       // Circle userCircle = new Circle(17);

      //  userCircle.setFill(
             //   Color.web("#F7C5D9")
     //   );

       // Label admin = new Label(
          //      "Hospital Admin"
       // );

      //  admin.setFont(
           //     Font.font(
             //           "Arial",
              //          FontWeight.BOLD,
                 //       11
              //  )
      //  );

      //  admin.setTextFill(
            //    Color.web(TEXT)
        //);

        HBox userBox = new HBox(
              //  8,
              //  userCircle
              //  admin
        );

        userBox.setAlignment(
                Pos.CENTER
        );

        Region headerSpace = new Region();

        HBox.setHgrow(
                headerSpace,
                Priority.ALWAYS
        );

        HBox topRow = new HBox(
                15,
              //  backButton,
                titleBox,
                headerSpace,
                search,
                notification,
                userBox
        );

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        header.getChildren().add(
                topRow
        );

        // =====================================================
        // STATISTICS CARDS
        // =====================================================

        HBox cards = new HBox(15);

        cards.setPadding(
                new Insets(10, 25, 15, 25)
        );

        VBox totalCard = createStatCard(
                "▣",
                "Total Appointments",
                "24",
                "This Month",
                "#FDE8F1"
        );

     
VBox todayCard = createStatCard(
        "◷",
        "Today's Appointments",
        "0",
        java.time.LocalDate.now()
                .format(
                    java.time.format.DateTimeFormatter
                        .ofPattern("dd MMM yyyy")
                ),
        "#F0ECFF"
);



        VBox confirmedCard = createStatCard(
                "✓",
                "Confirmed",
                "0",
                "This Month",
                "#E8F8EF"
        );

        VBox pendingCard = createStatCard(
                "◷",
                "Pending",
                "8",
                "This Month",
                "#FFF4E5"
        );

        cards.getChildren().addAll(
                totalCard,
                todayCard,
                confirmedCard,
                pendingCard
        );  
        

        // =====================================================
        // FILTERS
        // =====================================================

        ComboBox<String> statusCombo =
                new ComboBox<>();

        statusCombo.getItems().addAll(
                "All Status",
                "Confirmed",
                "Pending",
                "Cancelled"
        );

        statusCombo.setValue(
                "All Status"
        );

        statusCombo.setPrefWidth(105);
        statusCombo.setPrefHeight(35);

        DatePicker datePicker =
                new DatePicker();

                datePicker.setValue(java.time.LocalDate.now());


datePicker.valueProperty().addListener(
        (observable, oldDate, newDate) -> {

            updateTodayAppointments(newDate);

        }
);


        datePicker.setPromptText(
                "Select Date"
        );

        datePicker.setPrefWidth(105);
        datePicker.setPrefHeight(35);

        Button newAppointment =
                new Button("+  New Appointment");

        newAppointment.setPrefHeight(38);
        newAppointment.setPrefWidth(130);

        newAppointment.setTextFill(
                Color.WHITE
        );

        newAppointment.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        newAppointment.setCursor(
                Cursor.HAND
        );

        newAppointment.setStyle(
                "-fx-background-color: " + PINK + ";" +
                "-fx-background-radius: 7;" +
                "-fx-border-radius: 7;"
        );

        Region filterSpace = new Region();

        HBox.setHgrow(
                filterSpace,
                Priority.ALWAYS
        );

        HBox filters = new HBox(
                10,
                statusCombo,
                datePicker,
                filterSpace,
                newAppointment
        );

        filters.setPadding(
                new Insets(0, 25, 15, 25)
        );

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<Appointment> table =
                new TableView<>();

        table.setPrefHeight(500);

        table.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY
        );
        

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;"
        );

        // NUMBER
      

TableColumn<Appointment, String> noCol =
        new TableColumn<>("#");

noCol.setCellFactory(column ->
        new TableCell<Appointment, String>() {

            @Override
            protected void updateItem(
                    String item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(
                            String.valueOf(
                                    getIndex() + 1
                            )
                    );
                }
            }
        }
);



        // PATIENT
        TableColumn<Appointment, String> patientCol =
                new TableColumn<>("Patient Name");

        patientCol.setCellValueFactory(
                new PropertyValueFactory<>("patient")
        );

        // DOCTOR
        TableColumn<Appointment, String> doctorCol =
                new TableColumn<>("Doctor");

       doctorCol.setCellValueFactory(
                new PropertyValueFactory<>("doctor")
        );
        

        // DATE
        TableColumn<Appointment, String> dateCol =
                new TableColumn<>("Date");

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        // TIME
        TableColumn<Appointment, String> timeCol =
                new TableColumn<>("Time");

        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );

        // DEPARTMENT
        TableColumn<Appointment, String> departmentCol =
                new TableColumn<>("Department");

        departmentCol.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        // STATUS
        TableColumn<Appointment, String> statusCol =
                new TableColumn<>("Status");

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        // =====================================================
        // STATUS CELL
        // =====================================================

        statusCol.setCellFactory(column ->
                new TableCell<Appointment, String>() {

                    @Override
                    protected void updateItem(
                            String status,
                            boolean empty
                    ) {

                        super.updateItem(
                                status,
                                empty
                        );

                        if (empty || status == null) {

                            setGraphic(null);
                            setText(null);

                            return;
                        }

                        Label label =
                                new Label(status);

                        label.setFont(
                                Font.font(
                                        "Arial",
                                        FontWeight.BOLD,
                                        9
                                )
                        );

                        label.setPadding(
                                new Insets(
                                        5, 9, 5, 9
                                )
                        );

                        if (status.equals("Confirmed")) {

                            label.setTextFill(
                                    Color.web("#2E9D68")
                            );

                            label.setStyle(
                                    "-fx-background-color: #E5F7EE;" +
                                    "-fx-background-radius: 10;"
                            );

                        } else if (
                                status.equals("Pending")
                        ) {

                            label.setTextFill(
                                    Color.web("#D99120")
                            );

                            label.setStyle(
                                    "-fx-background-color: #FFF2DE;" +
                                    "-fx-background-radius: 10;"
                            );

                        } else {

                            label.setTextFill(
                                    Color.web("#D94A5A")
                            );

                            label.setStyle(
                                    "-fx-background-color: #FFE7EA;" +
                                    "-fx-background-radius: 10;"
                            );
                        }

                        setGraphic(label);
                    }
                }
        );

        
// =====================================================
// ACTION COLUMN
// =====================================================

TableColumn<Appointment, Void> actionCol =
        new TableColumn<>("Action");

actionCol.setCellFactory(column ->
        new TableCell<Appointment, Void>() {

            private final Button view =
                    new Button("◉");

            private final Button edit =
                    new Button("✎");

            private final Button delete =
                    new Button("▢");

            private final HBox box =
                    new HBox(
                            5,
                            view,
                            edit,
                            delete
                    );

            {

                box.setAlignment(
                        Pos.CENTER
                );

                // =================================================
                // BUTTON CURSOR
                // =================================================

                view.setCursor(Cursor.HAND);
                edit.setCursor(Cursor.HAND);
                delete.setCursor(Cursor.HAND);

                // =================================================
                // BUTTON STYLE
                // =================================================

                styleActionButton(
                        view,
                        "#FFF0F6",
                        PINK
                );

                styleActionButton(
                        edit,
                        "#EEF5FF",
                        "#3274C6"
                );

                styleActionButton(
                        delete,
                        "#FFF0F0",
                        "#D94A5A"
                );

                // =================================================
                // VIEW BUTTON
                // =================================================

                view.setOnAction(e -> {

                    Appointment appointment =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.INFORMATION
                            );

                    alert.setTitle(
                            "Appointment Details"
                    );

                    alert.setHeaderText(
                            "Appointment Information"
                    );

                    alert.setContentText(
                            "Patient: "
                            + appointment.getPatient()
                            + "\n\nDoctor: "
                            + appointment.getDoctor()
                            + "\n\nDate: "
                            + appointment.getDate()
                            + "\n\nTime: "
                            + appointment.getTime()
                            + "\n\nDepartment: "
                            + appointment.getDepartment()
                            + "\n\nStatus: "
                            + appointment.getStatus()
                    );

                    alert.showAndWait();
                });

                // =================================================
                // EDIT BUTTON
                // =================================================

                edit.setOnAction(e -> {

                    Appointment appointment =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    TextInputDialog dialog =
                            new TextInputDialog(
                                    appointment.getStatus()
                            );

                    dialog.setTitle(
                            "Edit Appointment"
                    );

                    dialog.setHeaderText(
                            "Edit Appointment Status"
                    );

                    dialog.setContentText(
                            "Enter Status:"
                    );

                    dialog.showAndWait()
                            .ifPresent(newStatus -> {

                                if (newStatus.trim().isEmpty()) {
                                    return;
                                }

                

                                String newStatusValue = newStatus.trim();

controller.updateAppointment(
        appointment.getNumber(),
        appointment.getPatient(),
        appointment.getDoctor(),
        appointment.getDate(),
        appointment.getTime(),
        appointment.getDepartment(),
        newStatusValue
);

appointment.setStatus(newStatusValue);

updateStatCards();

getTableView().refresh();


                            });
                });

                // =================================================
                // DELETE BUTTON
                // =================================================

                delete.setOnAction(e -> {

                    Appointment appointment =
                          getTableView()
                                    .getItems()
                                    .get(getIndex());

                        
     

                    Alert confirmation =
                            new Alert(
                                    Alert.AlertType.CONFIRMATION
                            );

                    confirmation.setTitle(
                            "Delete Appointment"
                    );

                    confirmation.setHeaderText(
                            "Delete Appointment?"
                    );

                    confirmation.setContentText(
                            "Are you sure you want to delete the appointment of "
                            + appointment.getPatient()
                            + "?"
                    );

                    confirmation.showAndWait()
                            .ifPresent(response -> {

                                if (response ==
                                        ButtonType.OK) {  
controller.deleteAppointment(appointment.getNumber());
// Reload latest data from Firebase 

data.setAll(

controller.getAllAppointments()
                                        );

                  // refresh table 

                  getTableView().refresh();

                  // Update all statistics 


                  updateStatCards();

                  // update today's appointment 

                  updateTodayAppointments(datePicker.getValue());



                                          
                                }
                            });
                });
            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(
                        item,
                        empty
                );

                if (empty) {

                    setGraphic(null);

                } else {

                    setGraphic(box);
                }
            }
        }
); 






        table.getColumns().addAll(
           noCol,
               patientCol,
               doctorCol,
             dateCol,
               timeCol,
              departmentCol,
              statusCol,
               actionCol
        );

        // =====================================================
        // TABLE DATA
        // =====================================================

     




data.setAll(controller.getAllAppointments());
updateStatCards();

updateStatCards();

updateTodayAppointments(
        datePicker.getValue()
);

      //  table.setItems(data);     



FilteredList<Appointment> filteredData =
        new FilteredList<>(data, appointment -> true);

Runnable updateFilter = () -> {

    String selectedStatus = statusCombo.getValue();
    String searchText = search.getText().trim().toLowerCase();

    filteredData.setPredicate(appointment -> {

        // STATUS FILTER
        boolean statusMatch =
                selectedStatus == null ||
                selectedStatus.equals("All Status") ||
                appointment.getStatus()
                        .equalsIgnoreCase(selectedStatus);

        // SEARCH FILTER
        boolean searchMatch =
                searchText.isEmpty()
                || appointment.getPatient().toLowerCase().contains(searchText)
                || appointment.getDoctor().toLowerCase().contains(searchText)
                || appointment.getDate().toLowerCase().contains(searchText)
                || appointment.getTime().toLowerCase().contains(searchText)
                || appointment.getDepartment().toLowerCase().contains(searchText);

        return statusMatch && searchMatch;
    });
};

// All Status / Confirmed / Pending / Cancelled
statusCombo.setOnAction(e -> updateFilter.run());

// Search
search.textProperty().addListener((observable, oldValue, newValue) -> {
    updateFilter.run();
});

table.setItems(filteredData);




// =====================================================
// NEW APPOINTMENT BUTTON ACTION
// =====================================================

newAppointment.setOnAction(e -> {

    Dialog<ButtonType> dialog = new Dialog<>();

    dialog.setTitle("New Appointment");
    dialog.setHeaderText("Create New Appointment");

    ButtonType saveButton =
            new ButtonType(
                    "Save Appointment",
                    ButtonBar.ButtonData.OK_DONE
            );

    ButtonType cancelButton =
            new ButtonType(
                    "Cancel",
                    ButtonBar.ButtonData.CANCEL_CLOSE
            );

    dialog.getDialogPane().getButtonTypes().addAll(
            saveButton,
            cancelButton
    ); 
    

        VBox form=new VBox(12);
    form.setPadding(new Insets(20));
    form.setPrefWidth(400);

    TextField patientField =
            new TextField();
    patientField.setPromptText("Enter patient name");

    TextField doctorField =
            new TextField();
    doctorField.setPromptText("Enter doctor name");

    TextField dateField =
            new TextField();
    dateField.setPromptText("Example: 24 Aug 2026");

    TextField timeField =
            new TextField();
    timeField.setPromptText("Example: 10:30 AM");

   TextField departmentField=new TextField();

   departmentField.setPromptText("Enter department");




    ComboBox<String> statusField =
            new ComboBox<>();

    statusField.getItems().addAll(
            "Confirmed",
            "Pending",
            "Cancelled"
    );

    statusField.setValue("Pending");

    form.getChildren().addAll(

            new Label("Patient Name"),
            patientField,

            new Label("Doctor"),
            doctorField,

            new Label("Date"),
            dateField,

            new Label("Time"),
            timeField,

            new Label("Department"),
            departmentField,

            new Label("Status"),
            statusField
    );

    dialog.getDialogPane().setContent(form);

    dialog.showAndWait().ifPresent(result -> {

        if (result == saveButton) {

            if (patientField.getText().isBlank()
                    || doctorField.getText().isBlank()
                    || dateField.getText().isBlank()
                    || timeField.getText().isBlank()
                    || departmentField.getText().isBlank()) {

                Alert alert =
                        new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Please fill all appointment details."
                );

                alert.showAndWait();

                return;
            }

            // Generate next appointment number

        //    int nextNumber = data.size() + 1; 


int nextNumber = 1;

for (Appointment appointment : data) {

    try {

        int currentNumber =
                Integer.parseInt(
                        appointment.getNumber()
                );

        if (currentNumber >= nextNumber) {
            nextNumber = currentNumber + 1;
        }

    } catch (NumberFormatException ex) {
        // Ignore invalid number
    }
}


          Appointment appointment =
                    new Appointment(
                            String.valueOf(nextNumber),
                            patientField.getText(),
                            doctorField.getText(),
                            dateField.getText(),
                            timeField.getText(),
                            departmentField.getText(),
                            statusField.getValue()
                    );

         //   data.add(appointment);

           // table.refresh();   


//String number = String.valueOf(data.size() + 1);
String number=String.valueOf(nextNumber);

controller.addAppointment(
        number,
        patientField.getText(),
        doctorField.getText(),
        dateField.getText(),
        timeField.getText(),
        departmentField.getText(),
        statusField.getValue()
);

data.setAll(controller.getAllAppointments());
updateStatCards();
updateTodayAppointments(java.time.LocalDate.now());
//table.refresh();






            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Appointment Added");
            alert.setHeaderText(null);
            alert.setContentText(
                    "New appointment added successfully."
            );

            alert.showAndWait();
        }
    });
});







        // =====================================================
        // BOTTOM
        // =====================================================

        Label showing =
                new Label(
                        "Showing 1 to 8 of 24 entries"
                );

        showing.setFont(
                Font.font("Arial", 10)
        );

        showing.setTextFill(
                Color.web(MUTED)
        );

// =====================================================
// PAGINATION - NEXT BUTTON
// ====================================================



        Region bottomSpace =
                new Region();

        HBox.setHgrow(
                bottomSpace,
                Priority.ALWAYS
        );

        HBox bottom =
                new HBox(
                        10,
                        showing,
                        bottomSpace
                      //  pagination
                );

        bottom.setPadding(
                new Insets(
                        10, 25, 15, 25
                )
        );

        bottom.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        Content.getChildren().addAll(
                header,
                cards,
                filters,
                table,
                bottom
        );
}
    

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String icon,
            String title,
            String value,
            String bottomText,
            String iconBackground
    ) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font("Arial", 19)
        );

        iconLabel.setTextFill(
                Color.web(PINK)
        );

        StackPane iconBox =
                new StackPane(iconLabel);

        iconBox.setPrefSize(
                42,
                42
        );

        iconBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(iconBackground),
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        10
                )
        );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        Label valueLabel =
                new Label(value);




        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        valueLabel.setTextFill(
                Color.web(TEXT)
        );  
if (title.equals("Total Appointments")) {
    totalValueLabel = valueLabel;
} else if (title.equals("Today's Appointments")) {
    todayValueLabel = valueLabel;
} else if (title.equals("Confirmed")) {
    confirmedValueLabel = valueLabel;
} else if (title.equals("Pending")) {
    pendingValueLabel = valueLabel;
}



        Label bottomLabel =
                new Label(bottomText);

        bottomLabel.setFont(
                Font.font("Arial", 9)
        );

        bottomLabel.setTextFill(
                Color.web(PINK)
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        valueLabel,
                        bottomLabel
                );

        HBox cardContent =
                new HBox(
                        12,
                        iconBox,
                        text
                );

        cardContent.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox card =
                new VBox(cardContent);

        card.setPadding(
                new Insets(
                        14, 16, 14, 16
                )
        );

        card.setPrefHeight(82);

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E8E8EF;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        return card;
    }

    // =========================================================
    // ACTION BUTTON STYLE
    // =========================================================

    private void styleActionButton(
            Button button,
            String background,
            String textColor
    ) {

        button.setPrefSize(
                25,
                25
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        button.setTextFill(
                Color.web(textColor)
        );

        button.setStyle(
                "-fx-background-color: " +
                background + ";" +
                "-fx-border-color: #E8E8EF;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );
    }  

private void updateStatCards() {

    int total = data.size();

    int confirmed = 0;
    int pending = 0;

    for (Appointment appointment : data) {

        if (appointment.getStatus() == null) {
            continue;
        }

        if (appointment.getStatus()
                .equalsIgnoreCase("Confirmed")) {

            confirmed++;

        } else if (appointment.getStatus()
                .equalsIgnoreCase("Pending")) {

            pending++;
        }
    }

    if (totalValueLabel != null) {
        totalValueLabel.setText(
                String.valueOf(total)
        );
    }

    if (confirmedValueLabel != null) {
        confirmedValueLabel.setText(
                String.valueOf(confirmed)
        );
    }

    if (pendingValueLabel != null) {
        pendingValueLabel.setText(
                String.valueOf(pending)
        );
    }
} 

 


private void updateTodayAppointments(
        java.time.LocalDate selectedDate
) {

    if (selectedDate == null) {
        todayValueLabel.setText("0");
        return;
    }

    int count = 0;

    java.time.format.DateTimeFormatter formatter =
            java.time.format.DateTimeFormatter.ofPattern(
                    "dd MMM yyyy",
                    java.util.Locale.ENGLISH
            );

    for (Appointment appointment : data) {

        try {

            String dateText = appointment.getDate();

            if (dateText == null || dateText.trim().isEmpty()) {
                continue;
            }

            java.time.LocalDate appointmentDate =
                    java.time.LocalDate.parse(
                            dateText.trim(),
                            formatter
                    );

            if (appointmentDate.equals(selectedDate)) {
                count++;
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid appointment date: ["
                    + appointment.getDate()
                    + "]"
            );
        }
    }

    todayValueLabel.setText(
            String.valueOf(count)
    );
}
}






    
        
    

