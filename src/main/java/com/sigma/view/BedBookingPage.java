package com.sigma.view;

import com.sigma.model.BedBooking;
//import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.sigma.controller.HospitalController.BedBookingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.List;


public class BedBookingPage {

    // =========================================================
    // COLOUR PALETTE - SAME AS MAACARE AI
    // =========================================================


    /*  private static final String BG="#F7EAF5";


    private static final String WHITE = "#FFFFFF";
    private static final String NAVY = "#17184F";

    private static final String PINK = "#E83E83";
    private static final String LIGHT_PINK = "#FFF0F7";

    private static final String PURPLE = "#8056C5";
    private static final String GREEN = "#67C98F";
    private static final String BLUE = "#5578D6";
    private static final String ORANGE = "#F2A33A";

    private static final String BORDER = "#E9E6EF";
    private static final String GREY = "#77758A"; */ 

private static final String BG = "#FFFFFF";
private static final String WHITE = "#FFFFFF";

private static final String NAVY = "#24234F";

private static final String PINK = "#E84A87";
private static final String LIGHT_PINK = "#FFEAF3";

private static final String PURPLE = "#9B4DCC";

private static final String GREEN = "#67C98F";
private static final String BLUE = "#5578D6";
private static final String ORANGE = "#F2A33A";

private static final String BORDER = "#E7DCE8";
private static final String GREY = "#77778D";



    private BedBookingController controller=new BedBookingController();
    private Label totalBedsLabel;
private Label availableBedsLabel;
private Label occupiedBedsLabel;
private Label pendingBedsLabel;
private ObservableList<BedBooking> data =
        FXCollections.observableArrayList();

private BorderPane root;



    // =========================================================
    // SHOW PAGE
    // =========================================================

  
public BedBookingPage(){ 
        createView();
        
}



private void createView(){
root=new BorderPane();

        /*root.setStyle(
                "-fx-background-color: " + BG + ";"
        );*/ 

        root.setStyle(
        "-fx-background-color: linear-gradient(" +
        "to bottom right, " +
        "#FFFFFF 0%, " +
        "#FFF6FA 55%, " +
        "#F3ECFF 100%);"
);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent = new VBox(18);

        mainContent.setPadding(
                new Insets(28, 35, 30, 35)
        );

        // =====================================================
        // HEADER
        // =====================================================

        BorderPane header = new BorderPane();

        header.setPadding(
                new Insets(0, 0, 8, 0)
        );

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        VBox titleBox = new VBox(5);

        Label title = new Label("Bed Booking");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(NAVY)
        );

        Label subtitle = new Label(
                "View and manage hospital bed bookings"
        );

        subtitle.setFont(
                Font.font("Arial", 15)
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        
// =====================================================
// ADD BED BOOKING BUTTON
// =====================================================

Button addBedButton =
        new Button("+ Add Bed Booking");

addBedButton.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                13
        )
); 
addBedButton.setPrefWidth(170);
addBedButton.setPrefHeight(38);


addBedButton.setCursor(Cursor.HAND);

/*addBedButton.setStyle(
        "-fx-background-color: " + PINK + ";" +
        "-fx-text-fill: white;" +
        "-fx-background-radius: 8;" +
        "-fx-border-radius: 8;" +
        "-fx-padding: 10 16;"
);*/



addBedButton.setStyle(
        "-fx-background-color: linear-gradient(" +
        "to right, #F54B87, #9B4DCC);" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 13px;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 20;" +
        "-fx-padding: 8px 20px;" +
        "-fx-border-radius: 20;"
);


addBedButton.setOnMouseEntered(e -> {
    addBedButton.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #E84A87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 8px 20px;" +
            "-fx-border-radius: 20;"
    );
});

addBedButton.setOnMouseExited(e -> {
    addBedButton.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to right, #F54B87, #9B4DCC);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 8px 20px;" +
            "-fx-border-radius: 20;"
    );
});




        header.setLeft(titleBox);
       

        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox cards = new HBox(18);

        cards.setAlignment(Pos.CENTER);

        VBox totalBeds =
                createStatCard(
                        "100",
                         "🛌",
                         "Total Beds",
                        "All Hospital Beds",
                        BLUE
                );

        VBox availableBeds =
                createStatCard(
                        "0",
                        "✅",
                       "Available Beds",
                        "Ready for Booking",
                        GREEN
                );

        VBox occupiedBeds =
                createStatCard(
                        "0",
                        "🛌",
                        "Occupied Beds",
                        "Currently Occupied",
                        PINK
                );

        VBox pendingBeds =
                createStatCard(
                        "0",
                         "⏳",
                     "Pending Booking",
                        "Awaiting Confirmation",
                        ORANGE
                );

        cards.getChildren().addAll(
                totalBeds,
                availableBeds,
                occupiedBeds,
                pendingBeds
        );   


// =====================================================
// ADD BED BOOKING BUTTON BOX
// =====================================================

HBox addButtonBox = new HBox();

addButtonBox.setAlignment(
        Pos.CENTER_RIGHT
);

addButtonBox.getChildren().add(
        addBedButton
);



        // =====================================================
        // SEARCH + FILTER
        // =====================================================

        HBox filterBox = new HBox(18);

        filterBox.setPadding(
                new Insets(18)
        );

        filterBox.setAlignment(
                Pos.CENTER_LEFT
        );

        filterBox.setStyle(
                "-fx-background-color: " + WHITE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        // =====================================================
        // SEARCH
        // =====================================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search patient or booking ID..."
        );

        searchField.setPrefWidth(430);
        searchField.setPrefHeight(45);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 15;" +
                "-fx-font-size: 14;"
        );

        // =====================================================
        // DEPARTMENT
        // =====================================================

        ComboBox<String> departmentBox =
                new ComboBox<>();

        departmentBox.getItems().addAll(
                "All Departments",
                "General Ward",
                "ICU",
                "Emergency",
                "Pediatric",
                "Maternity"
        );

        departmentBox.setValue(
                "All Departments"
        );

        departmentBox.setPrefWidth(200);
        departmentBox.setPrefHeight(45);  


departmentBox.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: " + BORDER + ";" +
        "-fx-border-radius: 8;" +
        "-fx-background-radius: 8;" +
        "-fx-font-size: 14px;"
);


        // =====================================================
        // BED TYPE
        // =====================================================

        ComboBox<String> bedTypeBox =
                new ComboBox<>();

        bedTypeBox.getItems().addAll(
                "All Bed Types",
                "General",
                "ICU",
                "Private",
                "Semi-Private"
        );

        bedTypeBox.setValue(
                "All Bed Types"
        );

        bedTypeBox.setPrefWidth(200);
        bedTypeBox.setPrefHeight(45);

        bedTypeBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 14px;"
        );

        // =====================================================
        // STATUS
        // =====================================================

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "All Status",
         "Booked",
              
                "Available",
                "Pending"
        );

        statusBox.setValue(
                "All Status"
        );

        statusBox.setPrefWidth(170);
        statusBox.setPrefHeight(45); 

        statusBox.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: " + BORDER + ";" +
        "-fx-border-radius: 8;" +
        "-fx-background-radius: 8;" +
        "-fx-font-size: 14px;"
);

        filterBox.getChildren().addAll(
                searchField,
                departmentBox,
                bedTypeBox,
                statusBox
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<BedBooking> table =
                new TableView<>();

        table.setPrefHeight(420);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        // =====================================================
        // TABLE COLUMNS
        // =====================================================

        TableColumn<BedBooking, String> numberColumn =
                new TableColumn<>("No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> departmentColumn =
                new TableColumn<>("Department");

        departmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> bookingIdColumn =
                new TableColumn<>("bookingID");

        bookingIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("bookingID")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> patientColumn =
                new TableColumn<>("Patient Name");

        patientColumn.setCellValueFactory(
                new PropertyValueFactory<>("patientName")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> bedNoColumn =
                new TableColumn<>("Bed No.");

        bedNoColumn.setCellValueFactory(
                new PropertyValueFactory<>("bedNo")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> bedTypeColumn =
                new TableColumn<>("Bed Type");

        bedTypeColumn.setCellValueFactory(
                new PropertyValueFactory<>("bedType")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> checkInColumn =
                new TableColumn<>("Check-in Date");

        checkInColumn.setCellValueFactory(
                new PropertyValueFactory<>("checkinDate")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> checkOutColumn =
                new TableColumn<>("Expected Check-out");

        checkOutColumn.setCellValueFactory(
                new PropertyValueFactory<>("expectedCheckout")
        );

        // -----------------------------------------------------

        TableColumn<BedBooking, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );  

//Action Column 
// =====================================================
// ACTION COLUMN
// =====================================================

TableColumn<BedBooking, Void> actionColumn =
        new TableColumn<>("Action");

actionColumn.setCellFactory(column ->
        new TableCell<BedBooking, Void>() {

            private final Button view =
                    new Button("◉");

            private final Button edit =
                    new Button("✎");

            private final Button delete =
                    new Button("▢");

            private final HBox box =
                    new HBox(5, view, edit, delete);

            {
                box.setAlignment(Pos.CENTER);

                view.setCursor(Cursor.HAND);
                edit.setCursor(Cursor.HAND);
                delete.setCursor(Cursor.HAND);

                // =================================================
                // VIEW STYLE
                // =================================================

                view.setStyle(
                       // "-fx-background-color: #FFF0F7;" +
                       "-fx-background-color " + LIGHT_PINK + ";" +
                        "-fx-text-fill: " + PINK + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );

                // =================================================
                // EDIT STYLE
                // =================================================

              /*   edit.setStyle(
                        "-fx-background-color: #EEF5FF;" +
                        "-fx-text-fill: #3274C6;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );*/ 

edit.setStyle(
        "-fx-background-color: #F3ECFF;" +
        "-fx-text-fill: " + PURPLE + ";" +
        "-fx-border-color: " + BORDER + ";" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;"
);


                // =================================================
                // DELETE STYLE
                // =================================================

                delete.setStyle(
                        "-fx-background-color: #FFF0F0;" +
                        "-fx-text-fill: #D94A5A;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );

                // =================================================
                // VIEW
                // =================================================

                view.setOnAction(e -> {

                    BedBooking record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.INFORMATION
                            );

                    alert.setTitle(
                            "Bed Booking Details"
                    );

                    alert.setHeaderText(
                            "Bed Booking Information"
                    );

                    alert.setContentText(
                            "Booking ID: "
                            + record.getBookingID()

                            + "\n\nPatient: "
                            + record.getPatientName()

                            + "\n\nDepartment: "
                            + record.getDepartment()

                            + "\n\nBed No.: "
                            + record.getBedNo()

                            + "\n\nBed Type: "
                            + record.getBedType()

                            + "\n\nCheck-in Date: "
                            + record.getCheckinDate()

                            + "\n\nExpected Check-out: "
                            + record.getExpectedCheckout()

                            + "\n\nStatus: "
                            + record.getStatus()
                    );

                    alert.showAndWait();
                });

                // =================================================
                // EDIT
                // =================================================

                edit.setOnAction(e -> {

                    BedBooking record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    ChoiceDialog<String> dialog =
                            new ChoiceDialog<>(
                                    record.getStatus(),
                                    "Booked",
                                    "Available",
                                    "Pending"
                            );

                    dialog.setTitle(
                            "Edit Bed Booking"
                    );

                    dialog.setHeaderText(
                            "Change Booking Status"
                    );

                    dialog.setContentText(
                            "Select Status:"
                    );

                    dialog.showAndWait()
                            .ifPresent(newStatus -> {

                              

record.setStatus(
        newStatus
);

// Firebase madhe update
controller.updateBedBooking(
        record.getNumber(),
        record.getBookingID(),
        record.getPatientName(),
        record.getDepartment(),
        record.getBedNo(),
        record.getBedType(),
        record.getCheckinDate(),
        record.getExpectedCheckout(),
        record.getStatus()
);

getTableView().refresh();

updateStatCards();




                            });
                });

                // =================================================
                // DELETE
                // =================================================

                delete.setOnAction(e -> {

                    BedBooking record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    Alert confirmation =
                            new Alert(
                                    Alert.AlertType.CONFIRMATION
                            );

                    confirmation.setTitle(
                            "Delete Bed Booking"
                    );

                    confirmation.setHeaderText(
                            "Delete Bed Booking?"
                    );

                    confirmation.setContentText(
                            "Are you sure you want to delete the booking of "
                            + record.getPatientName()
                            + "?"
                    );

                    confirmation.showAndWait()
                            .ifPresent(response -> {

                            

if (response == ButtonType.OK) {

    // Firebase madhun delete
    controller.deleteBedBooking(
            record.getBookingID()
    );

data.remove(record);

table.setItems(data);
table.refresh();

updateStatCards();


    // Table madhun delete
    getTableView()
            .getItems()
            .remove(record);

            

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





        // =====================================================
        // ADD COLUMNS
        // =====================================================

       table.getColumns().addAll(
              numberColumn,
              
              departmentColumn,
                bookingIdColumn,
               patientColumn,
               bedNoColumn,
              bedTypeColumn,
               checkInColumn,
               checkOutColumn,
               statusColumn,
               actionColumn
       );

        // =====================================================
        // DATA
        // =====================================================

      


      
List<BedBooking> firebaseData =
        controller.getAllBedBookings();

data.clear();
data.addAll(firebaseData);

table.setItems(data);

updateStatCards();

        
// =====================================================
// ADD BED BOOKING ACTION
// =====================================================

addBedButton.setOnAction(e -> {

    Dialog<ButtonType> dialog =
            new Dialog<>();

    dialog.setTitle("Add Bed Booking");
    dialog.setHeaderText("Enter Bed Booking Details");

    GridPane form =
            new GridPane();

    form.setHgap(12);
    form.setVgap(12);
    form.setPadding(new Insets(20));

    TextField bookingIdField = new TextField();
    bookingIdField.setPromptText("Booking ID");

    TextField patientField = new TextField();
    patientField.setPromptText("Patient Name");

    ComboBox<String> departmentField =
            new ComboBox<>();

    departmentField.getItems().addAll(
            "General Ward",
            "ICU",
            "Emergency",
            "Pediatric",
            "Maternity"
    );

    departmentField.setPromptText("Select Department");

    TextField bedNoField = new TextField();
    bedNoField.setPromptText("Bed No.");

    ComboBox<String> bedTypeField =
            new ComboBox<>();

    bedTypeField.getItems().addAll(
            "General",
            "ICU",
            "Private",
            "Semi-Private"
    );

    bedTypeField.setPromptText("Select Bed Type");

    TextField checkInField = new TextField();
    checkInField.setPromptText("e.g. 25 Aug 2026");

    TextField checkOutField = new TextField();
    checkOutField.setPromptText("e.g. 30 Aug 2026");

    ComboBox<String> statusField =
            new ComboBox<>();

    statusField.getItems().addAll(
            "Booked",
           //"Occupied",
            "Available",
            "Pending"
    );

   statusField.setValue("Pending");
   //statusField.setValue("Occupied");

    form.add(new Label("Booking ID:"), 0, 0);
    form.add(bookingIdField, 1, 0);

    form.add(new Label("Patient Name:"), 0, 1);
    form.add(patientField, 1, 1);

    form.add(new Label("Department:"), 0, 2);
    form.add(departmentField, 1, 2);

    form.add(new Label("Bed No.:"), 0, 3);
    form.add(bedNoField, 1, 3);

    form.add(new Label("Bed Type:"), 0, 4);
    form.add(bedTypeField, 1, 4);

    form.add(new Label("Check-in Date:"), 0, 5);
    form.add(checkInField, 1, 5);

    form.add(new Label("Expected Check-out:"), 0, 6);
    form.add(checkOutField, 1, 6);

    form.add(new Label("Status:"), 0, 7);
    form.add(statusField, 1, 7);

    dialog.getDialogPane()
            .setContent(form);

    ButtonType addButton =
            new ButtonType(
                    "Add Booking",
                    ButtonBar.ButtonData.OK_DONE
            );

    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    addButton,
                    ButtonType.CANCEL
            );

    dialog.setResultConverter(button -> {

        if (button == addButton) {

            if (
                    bookingIdField.getText().trim().isEmpty()
                    ||
                    patientField.getText().trim().isEmpty()
                    ||
                    departmentField.getValue() == null
                    ||
                    bedNoField.getText().trim().isEmpty()
                    ||
                    bedTypeField.getValue() == null
                    ||
                    checkInField.getText().trim().isEmpty()
                    ||
                    checkOutField.getText().trim().isEmpty()
            ) {

                Alert warning =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                warning.setTitle(
                        "Missing Information"
                );

                warning.setHeaderText(
                        "Please fill all fields"
                );

                warning.showAndWait();

                return null;
            }

          /*  String newNumber =
                    String.valueOf(
                            data.size() + 1
                    );*/ 
/*String newNumber = String.valueOf(
        controller.getAllBedBookings().size() + 1
);*/

int maxNumber = 0;

for (BedBooking booking : data) {
    try {
        int currentNumber = Integer.parseInt(booking.getNumber());

        if (currentNumber > maxNumber) {
            maxNumber = currentNumber;
        }
    } catch (NumberFormatException ex) {

    }
}

String newNumber = String.valueOf(maxNumber + 1);

            // IMPORTANT:
            // Constructor order same as your existing data

            BedBooking newBooking =
                    new BedBooking(

                            newNumber,

                           // departmentField.getValue(),

                            bookingIdField
                                    .getText()
                                    .trim(),
                                    

                            patientField
                                    .getText()
                                    .trim(),

                                    departmentField.getValue(),

                            bedNoField
                                    .getText()
                                    .trim(),

                            bedTypeField
                                    .getValue(),

                            checkInField
                                    .getText()
                                    .trim(),

                            checkOutField
                                    .getText()
                                    .trim(),

                            statusField
                                    .getValue()
                    );

            // ADD NEW DATA
           // data.add(newBooking);

            // REFRESH TABLE
          //  table.setItems(data);
          //  table.refresh();  

controller.addBedBooking(
        newBooking.getNumber(),
        newBooking.getBookingID(),
        newBooking.getPatientName(),
        newBooking.getDepartment(),
        newBooking.getBedNo(),
        newBooking.getBedType(),
        newBooking.getCheckinDate(),
        newBooking.getExpectedCheckout(),
        newBooking.getStatus()
);

data.add(newBooking);
//data.clear();
//data.addAll(controller.getAllBedBookings());
table.setItems(data);
table.refresh(); 
updateStatCards();






            return button;
        }

        return null;
    });

    dialog.showAndWait();
});

       // =====================================================
        // STATUS CELL STYLE
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<BedBooking, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (empty ||
                                        item == null) {

                                    setText(null);
                                    setStyle("");

                                    return;
                                }

                                setText(item);

                                setAlignment(
                                        Pos.CENTER
                                );

                                if (item.equals("Booked")) {

                                    setStyle(
                                            "-fx-background-color: #E8F8F0;" +
                                            "-fx-text-fill: #35A56B;" +
                                            "-fx-font-weight: bold;"
                                    );

                                } else if (
                                        item.equals("Pending")) {

                                    setStyle(
                                            "-fx-background-color: #FFF4E5;" +
                                            "-fx-text-fill: " +
                                            ORANGE + ";" +
                                            "-fx-font-weight: bold;"
                                    );

                                } else {

                                    setStyle(
                                            "-fx-background-color: #EEF3FF;" +
                                            "-fx-text-fill: " +
                                            BLUE + ";" +
                                            "-fx-font-weight: bold;"
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // ROW HOVER
        // =====================================================

        table.setRowFactory(tv -> {

            TableRow<BedBooking> row =
                    new TableRow<>();

            row.setOnMouseEntered(e -> {

                if (!row.isEmpty()) {

                    row.setStyle(
                            "-fx-background-color: "
                                    + LIGHT_PINK + ";"
                    );
                }
            });

            row.setOnMouseExited(e -> {

                row.setStyle("");
            });

            return row;
        });

        // =====================================================
        // SEARCH FILTER
        // =====================================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilter(
                            table,
                            data,
                            newValue,
                            departmentBox.getValue(),
                            bedTypeBox.getValue(),
                            statusBox.getValue()
                    );
                }
        );

        // =====================================================
        // DEPARTMENT FILTER
        // =====================================================

        departmentBox.setOnAction(e -> {

            applyFilter(
                    table,
                    data,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // BED TYPE FILTER
        // =====================================================

        bedTypeBox.setOnAction(e -> {

            applyFilter(
                    table,
                    data,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // STATUS FILTER
        // =====================================================

        statusBox.setOnAction(e -> {

            applyFilter(
                    table,
                    data,
                    searchField.getText(),
                    departmentBox.getValue(),
                    bedTypeBox.getValue(),
                    statusBox.getValue()
            );
        });

        // =====================================================
        // ADD CONTENT
        // =====================================================

        mainContent.getChildren().addAll(
                header,
                cards,
                addButtonBox,
                filterBox,
                table
        );

        root.setCenter(mainContent);

        // =====================================================
        // SCENE
        // =====================================================

       
    }

    // =========================================================
    // FILTER METHOD
    // =========================================================

    private void applyFilter(
            TableView<BedBooking> table,
            ObservableList<BedBooking> data,
            String searchText,
            String selectedDepartment,
            String selectedBedType,
            String selectedStatus
    ) {

        String search =
                searchText == null
                        ? ""
                        : searchText.toLowerCase();

        ObservableList<BedBooking> filtered =
                FXCollections.observableArrayList();

        for (BedBooking record : data) {

            boolean searchMatch =
                    record.getPatientName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    record.getBookingID()
                            .toLowerCase()
                            .contains(search);

            boolean departmentMatch =
                    selectedDepartment.equals(
                            "All Departments"
                    )
                    ||
                    record.getDepartment()
                            .equals(selectedDepartment);

            boolean bedTypeMatch =
                    selectedBedType.equals(
                            "All Bed Types"
                    )
                    ||
                    record.getBedType()
                            .equals(selectedBedType);

            boolean statusMatch =
                    selectedStatus.equals(
                            "All Status"
                    )
                    ||
                    record.getStatus()
                            .equals(selectedStatus);

            if (
                    searchMatch &&
                    departmentMatch &&
                    bedTypeMatch &&
                    statusMatch
            ) {

                filtered.add(record);
            }
        }

        table.setItems(filtered);
    }   

// =========================================================
// UPDATE BED BOOKING STAT CARDS
// =========================================================

private void updateStatCards() {

    // Total beds hardcoded
    int totalBeds = 100;

    // Firebase
    List<BedBooking> bookings =
            controller.getAllBedBookings();

    int booked = 0;
    int pending = 0;

    for (BedBooking booking : bookings) {

        if (booking.getStatus() != null) {

            if (booking.getStatus()
                    .equalsIgnoreCase("Booked")) {

                booked++;
            }

            else if (booking.getStatus()
                    .equalsIgnoreCase("Pending")) {

                pending++;
            }
        }
    }

    // Available = Total - Booked
    int available = totalBeds - booked;

    // Safety
    if (available < 0) {
        available = 0;
    }

    // =====================================================
    // UPDATE UI
    // =====================================================

    totalBedsLabel.setText(
            String.valueOf(totalBeds)
    );

    availableBedsLabel.setText(
            String.valueOf(available)
    );

    occupiedBedsLabel.setText(
            String.valueOf(booked)
    );

    pendingBedsLabel.setText(
            String.valueOf(pending)
    );
}




    // =========================================================
    // STAT CARD
    // =========================================================

   
private VBox createStatCard(
        String number,
        String symbol,
        String title,
        String bottomText,
        String color
) {

    VBox card =
            new VBox(8);

    card.setPadding(
            new Insets(20)
    );

    card.setAlignment(
            Pos.CENTER_LEFT
    );

    card.setPrefHeight(145);
    card.setPrefWidth(260);

    card.setStyle(
            "-fx-background-color: " + WHITE + ";" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 14;" +
            "-fx-background-radius: 14;"
    );

    // =====================================================
    // SYMBOL
    // =====================================================

    Label symbolLabel =
            new Label(symbol);

    symbolLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    25
            )
    );

    // =====================================================
    // NUMBER
    // =====================================================

    Label numberLabel =
            new Label(number);
            if (title.equals("Total Beds")) {
    totalBedsLabel = numberLabel;
}
else if (title.equals("Available Beds")) {
    availableBedsLabel = numberLabel;
}
else if (title.equals("Occupied Beds")) {
    occupiedBedsLabel = numberLabel;
}
else if (title.equals("Pending Booking")) {
    pendingBedsLabel = numberLabel;
}

    numberLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    30
            )
    );

    numberLabel.setTextFill(
            Color.web(NAVY)
    );

    // =====================================================
    // TITLE
    // =====================================================

    Label titleLabel =
            new Label(title);

    titleLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    15
            )
    );

    titleLabel.setTextFill(
            Color.web(NAVY)
    );

    // =====================================================
    // BOTTOM TEXT
    // =====================================================

    Label bottomLabel =
            new Label(bottomText);

    bottomLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    12
            )
    );

    bottomLabel.setTextFill(
            Color.web(color)
    );

    card.getChildren().addAll(
            symbolLabel,
            numberLabel,
            titleLabel,
            bottomLabel
    );

    return card;
}

public BorderPane getView(){ 
        return root;
}


}