package com.sigma.view;

import com.sigma.model.Labrecords;
import com.sigma.controller.HospitalController.LabrecordsController;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LabrecordsPage {

    // =========================================================
    // COLORS
    // =========================================================

    
       private static final String BG="#F7EAF5";
      private static final String WHITE = "#FFFFFF";
    private static final String NAVY = "#17184F";
    private static final String PINK = "#E83E83";
    private static final String LIGHT_PINK = "#FFF0F7";
    private static final String PURPLE = "#8056C5";
    private static final String GREEN = "#67C98F";
    private static final String ORANGE = "#F2A33A";
    private static final String BORDER = "#E9E6EF";
    private static final String GREY = "#77758A";

    // =====================================================
// DYNAMIC STAT CARD LABELS
// =====================================================

private Label totalReportsLabel;
private Label pendingReportsLabel;
private Label completedReportsLabel;
private Label todayReportsLabel;


  private BorderPane root; 
  private ObservableList<Labrecords> data =
        FXCollections.observableArrayList();

  private LabrecordsController controller=new LabrecordsController();

  public LabrecordsPage(){ 
        createView();
  }
 

  private void createView(){ 
        root=new BorderPane();
        root.setStyle("-fx-background-color: " + BG + ";");
  

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
                new Insets(0, 0, 5, 0)
        );

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        VBox titleBox = new VBox(5);

        Label title = new Label("Lab Records");

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
                "View and manage all patient lab reports"
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        
// =====================================================
// ADD LAB RECORD BUTTON
// =====================================================

Button addLabButton =
        new Button("+ Add Lab Record");

addLabButton.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                13
        )
);

addLabButton.setCursor(Cursor.HAND);

addLabButton.setStyle(
        "-fx-background-color: " + PINK + ";" +
        "-fx-text-fill: white;" +
        "-fx-background-radius: 8;" +
        "-fx-border-radius: 8;" +
        "-fx-padding: 10 16;"
);



        header.setLeft(titleBox);
        //header.setRight(backButton);

        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox cards = new HBox(18);

        cards.setAlignment(Pos.CENTER);

        VBox totalCard =
                createStatCard(
                       "▣",
                        "0",
                        "Total Reports",
                        "↗  All Time",
                        PINK
                );

        VBox pendingCard =
                createStatCard(
                        "◷",
                        "0",
                        "Pending Reports",
                        "◷  Awaiting Results",
                        PURPLE
                );

        VBox completedCard =
                createStatCard(
                        "✓",
                        "0",
                        "Completed Reports",
                        "↗  This Month",
                        GREEN
                );

        VBox todayCard =
                createStatCard(
                        "▤",
                        "0",
                        "Today's Reports",
                        "▣  Generated Today",
                        ORANGE
                );

        cards.getChildren().addAll(
                totalCard,
                pendingCard,
                completedCard,
                todayCard
        );  

// ===================================================== // ADD LAB RECORD BUTTON BOX // =====================================================
 
HBox addButtonBox = new HBox();
 
addButtonBox.setAlignment( Pos.CENTER_RIGHT );
 
addButtonBox.getChildren().add( addLabButton );



        // =====================================================
        // SEARCH + FILTER AREA
        // =====================================================

        HBox filterBox = new HBox(20);

        filterBox.setPadding(
                new Insets(20)
        );

        filterBox.setAlignment(
                Pos.CENTER_LEFT
        );

        filterBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        // =====================================================
        // SEARCH FIELD
        // =====================================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search by patient name or test..."
        );

        searchField.setPrefHeight(45);
        searchField.setPrefWidth(470);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 14;" +
                "-fx-padding: 0 15;"
        );

        // =====================================================
        // DEPARTMENT FILTER
        // =====================================================

        ComboBox<String> departmentBox =
                new ComboBox<>();

        departmentBox.getItems().addAll(
                "All Departments",
                "Hematology",
                "Biochemistry",
                "Immunology",
                "Pathology"
        );

        departmentBox.setValue(
                "All Departments"
        );

        departmentBox.setPrefWidth(220);
        departmentBox.setPrefHeight(45);

        // =====================================================
        // STATUS FILTER
        // =====================================================

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "All Status",
                "Completed",
                "Pending"
        );

        statusBox.setValue(
                "All Status"
        );

        statusBox.setPrefWidth(220);
        statusBox.setPrefHeight(45);

        

        // =====================================================
        // TABLE
        // =====================================================

        TableView<Labrecords> table =
                new TableView<>();

        table.setPrefHeight(410);

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
        // NO COLUMN
        // =====================================================

        TableColumn<Labrecords, String> numberColumn =
                new TableColumn<>("No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        numberColumn.setPrefWidth(70);

        // =====================================================
        // PATIENT NAME
        // =====================================================

        TableColumn<Labrecords, String> patientColumn =
                new TableColumn<>("Patient Name");

        patientColumn.setCellValueFactory(
                new PropertyValueFactory<>("PatientName")
        );

        // =====================================================
        // TEST NAME
        // =====================================================

        TableColumn<Labrecords, String> testColumn =
                new TableColumn<>("Test Name");

        testColumn.setCellValueFactory(
                new PropertyValueFactory<>("TestName")
        );

        // =====================================================
        // DEPARTMENT
        // =====================================================

        TableColumn<Labrecords, String> departmentColumn =
                new TableColumn<>("Department");

        departmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("Department")
        );

        // =====================================================
        // DATE
        // =====================================================

        TableColumn<Labrecords, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("Date")
        );

        // =====================================================
        // STATUS
        // =====================================================

        TableColumn<Labrecords, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("Status")
        );

        // =====================================================
        // RESULTS
        // =====================================================

        TableColumn<Labrecords, String> resultsColumn =
                new TableColumn<>("Results");

        resultsColumn.setCellValueFactory(
                new PropertyValueFactory<>("Results")  

   );   
// =====================================================
// ACTION COLUMN
// =====================================================

TableColumn<Labrecords, Void> actionColumn =
        new TableColumn<>("Action");

actionColumn.setCellFactory(column ->
        new TableCell<Labrecords, Void>() {

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

                // VIEW STYLE
                view.setStyle(
                        "-fx-background-color: #FFF0F7;" +
                        "-fx-text-fill: " + PINK + ";" +
                        "-fx-border-color: #E9E6EF;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );

                // EDIT STYLE
                edit.setStyle(
                        "-fx-background-color: #EEF5FF;" +
                        "-fx-text-fill: #3274C6;" +
                        "-fx-border-color: #E9E6EF;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );

                // DELETE STYLE
                delete.setStyle(
                        "-fx-background-color: #FFF0F0;" +
                        "-fx-text-fill: #D94A5A;" +
                        "-fx-border-color: #E9E6EF;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
                );

                // =================================================
                // VIEW
                // =================================================

                view.setOnAction(e -> {

                    Labrecords record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    Alert alert =
                            new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Lab Report Details");
                    alert.setHeaderText("Lab Report Information");

                    alert.setContentText(
                            "Patient: "
                            + record.getPatientName()
                            + "\n\nTest: "
                            + record.getTestName()
                            + "\n\nDepartment: "
                            + record.getDepartment()
                            + "\n\nDate: "
                            + record.getDate()
                            + "\n\nStatus: "
                            + record.getStatus()
                            + "\n\nResults: "
                            + record.getResults()
                    );

                    alert.showAndWait();
                });

                // =================================================
                // EDIT
                // =================================================

                edit.setOnAction(e -> {

                    Labrecords record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    ChoiceDialog<String> dialog =
                            new ChoiceDialog<>(
                                    record.getStatus(),
                                    "Completed",
                                    "Pending"
                            );

                    dialog.setTitle("Edit Lab Report");
                    dialog.setHeaderText(
                            "Change Report Status"
                    );
                    dialog.setContentText(
                            "Select Status:"
                    );

                    dialog.showAndWait()
                            .ifPresent(newStatus -> {

                              //  record.setStatus(newStatus);

                             //    getTableView().refresh(); 

record.setStatus(newStatus);

// Firebase मध्ये update
controller.updateLabrecord(
        record.getNumber(),
        record.getPatientName(),
        record.getTestName(),
        record.getDepartment(),
        record.getDate(),
        record.getStatus(),
        record.getResults()
);

getTableView().refresh();
updateStatCards();

                            });
                });

                // =================================================
                // DELETE
                // =================================================

                delete.setOnAction(e -> {

                    Labrecords record =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    Alert confirmation =
                            new Alert(
                                    Alert.AlertType.CONFIRMATION
                            );

                    confirmation.setTitle(
                            "Delete Lab Report"
                    );

                    confirmation.setHeaderText(
                            "Delete Lab Report?"
                    );

                    confirmation.setContentText(
                            "Are you sure you want to delete the report of "
                            + record.getPatientName()
                            + "?"
                    );

                    confirmation.showAndWait()
                            .ifPresent(response -> {

                              /*  if (response == ButtonType.OK) {

                                  //  data.remove(record);

                                    getTableView()
                                            .getItems()
                                            .remove(record);*/
                                if (response == ButtonType.OK) {

    // Firebase madhun delete
    controller.deleteLabrecord(
            record.getNumber()
    );

    // Table madhun delete
    data.remove(record);
updateStatCards();
    table.refresh();
}
                            });
                });
            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        }
);



        // =====================================================
        // ADD TABLE COLUMNS
        // =====================================================

       table.getColumns().addAll(
              numberColumn,
             patientColumn,
             testColumn,
             departmentColumn,
               dateColumn,
               statusColumn,
               resultsColumn,
               actionColumn
      );



        

     
List<Labrecords> firebaseData =
        controller.getAllLabrecords();
data.clear();
data.addAll(firebaseData);
updateStatCards();


     //  table.setItems(data);  
// =====================================================
// FILTERED LIST
// =====================================================

FilteredList<Labrecords> filteredData =
        new FilteredList<>(
                data,
                record -> true
        );

table.setItems(filteredData);


addLabButton.setOnAction(e -> {

    Dialog<ButtonType> dialog =
            new Dialog<>();

    dialog.setTitle("Add Lab Record");
    dialog.setHeaderText(
            "Enter Lab Record Details"
    );

    GridPane form =
            new GridPane();

    form.setHgap(12);
    form.setVgap(12);
    form.setPadding(
            new Insets(20)
    );

    // =================================================
    // PATIENT NAME
    // =================================================

    TextField patientField =
            new TextField();

    patientField.setPromptText(
            "Patient Name"
    );

    // =================================================
    // TEST NAME
    // =================================================

    TextField testField =
            new TextField();

    testField.setPromptText(
            "Test Name"
    );

    // =================================================
    // DEPARTMENT
    // =================================================

    ComboBox<String> departmentField =
            new ComboBox<>();

    departmentField.getItems().addAll(
            "Hematology",
            "Biochemistry",
            "Immunology",
            "Pathology"
    );

    departmentField.setPromptText(
            "Select Department"
    );

    // =================================================
    // DATE
    // =================================================

    TextField dateField =
            new TextField();

    dateField.setPromptText(
            "e.g. 25 Aug 2026 | 10:30 AM"
    );

    // =================================================
    // STATUS
    // =================================================

    ComboBox<String> statusField =
            new ComboBox<>();

    statusField.getItems().addAll(
            "Completed",
            "Pending"
    );

    statusField.setValue(
            "Pending"
    );

    // =================================================
    // RESULTS
    // =================================================

    TextField resultsField =
            new TextField();

    resultsField.setPromptText(
            "Results"
    );

    // =================================================
    // FORM
    // =================================================

    form.add(
            new Label("Patient Name:"),
            0, 0
    );

    form.add(
            patientField,
            1, 0
    );

    form.add(
            new Label("Test Name:"),
            0, 1
    );

    form.add(
            testField,
            1, 1
    );

    form.add(
            new Label("Department:"),
            0, 2
    );

    form.add(
            departmentField,
            1, 2
    );

    form.add(
            new Label("Date:"),
            0, 3
    );

    form.add(
            dateField,
            1, 3
    );

    form.add(
            new Label("Status:"),
            0, 4
    );

    form.add(
            statusField,
            1, 4
    );

    form.add(
            new Label("Results:"),
            0, 5
    );

    form.add(
            resultsField,
            1, 5
    );

    // =================================================
    // FIELD WIDTH
    // =================================================

    patientField.setPrefWidth(280);
    testField.setPrefWidth(280);
    departmentField.setPrefWidth(280);
    dateField.setPrefWidth(280);
    statusField.setPrefWidth(280);
    resultsField.setPrefWidth(280);

    // =================================================
    // ADD BUTTON
    // =================================================

    ButtonType addButton =
            new ButtonType(
                    "Add Record",
                    ButtonBar.ButtonData.OK_DONE
            );

    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(
                    addButton,
                    ButtonType.CANCEL
            );

    dialog.getDialogPane()
            .setContent(form);

    // =================================================
    // RESULT
    // =================================================

    dialog.setResultConverter(button -> {

        if (button == addButton) {

            // =========================================
            // VALIDATION
            // =========================================

            if (
                    patientField.getText()
                            .trim()
                            .isEmpty()
                    ||
                    testField.getText()
                            .trim()
                            .isEmpty()
                    ||
                    departmentField.getValue()
                            == null
                    ||
                    dateField.getText()
                            .trim()
                            .isEmpty()
                    ||
                    resultsField.getText()
                            .trim()
                            .isEmpty()
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

            // =========================================
            // AUTOMATIC NUMBER
            // =========================================

/*String newNumber =
                    String.valueOf(
                            data.size() + 1
                    );*/ 
int maxNumber = 0;

for (Labrecords record : data) {

    try {

        int currentNumber =
                Integer.parseInt(
                        record.getNumber()
                );

        if (currentNumber > maxNumber) {
            maxNumber = currentNumber;
        }

    } catch (NumberFormatException ex) {
        // Invalid number ignore
    }
}

String newNumber =
        String.valueOf(maxNumber + 1);


            // =========================================
            // NEW LAB RECORD
            // =========================================

            Labrecords newRecord =
                    new Labrecords(

                            newNumber,

                            patientField
                                    .getText()
                                    .trim(),

                            testField
                                    .getText()
                                    .trim(),

                            departmentField
                                    .getValue(),

                            dateField
                                    .getText()
                                    .trim(),

                            statusField
                                    .getValue(),

                            resultsField
                                    .getText()
                                    .trim()
                    );
// =========================================
// SAVE TO FIREBASE
// =========================================

controller.addLabrecord(
        newRecord.getNumber(),
        newRecord.getPatientName(),
        newRecord.getTestName(),
        newRecord.getDepartment(),
        newRecord.getDate(),
        newRecord.getStatus(),
        newRecord.getResults()
);

// =========================================
// ADD TO LOCAL TABLE
// =========================================

data.add(newRecord);

updateStatCards();
filteredData.setPredicate(filteredData.getPredicate());
table.refresh();


// =========================================
// REFRESH TABLE
// =========================================

filteredData.setPredicate(filteredData.getPredicate());
table.refresh();


           // table.refresh();

            return button;
        }

        return null;
    });

    dialog.showAndWait();
});



        

        
// =====================================================
// BED BOOKING STYLE FILTERING
// =====================================================

Runnable updateFilter = () -> {

    String searchText =
            searchField.getText()
                    .trim()
                    .toLowerCase();

    String selectedDepartment =
            departmentBox.getValue();

    String selectedStatus =
            statusBox.getValue();

    filteredData.setPredicate(record -> {

        boolean searchMatch =
                searchText.isEmpty()
                ||
                record.getPatientName()
                        .toLowerCase()
                        .contains(searchText)
                ||
                record.getTestName()
                        .toLowerCase()
                        .contains(searchText);

        boolean departmentMatch =
                selectedDepartment.equals(
                        "All Departments"
                )
                ||
                record.getDepartment()
                        .equals(selectedDepartment);

        boolean statusMatch =
                selectedStatus.equals(
                        "All Status"
                )
                ||
                record.getStatus()
                        .equals(selectedStatus);

        return searchMatch
                && departmentMatch
                && statusMatch;
    });
};

searchField.textProperty().addListener(
        (observable, oldValue, newValue) ->
                updateFilter.run()
);

departmentBox.valueProperty().addListener(
        (observable, oldValue, newValue) ->
                updateFilter.run()
);

statusBox.valueProperty().addListener(
        (observable, oldValue, newValue) ->
                updateFilter.run()
);








filterBox.getChildren().addAll(
        searchField,
        departmentBox,
        statusBox
);




        // =====================================================
        // TABLE ROW HOVER
        // =====================================================

        table.setRowFactory(tv -> {

            TableRow<Labrecords> row =
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
        // ADD ALL CONTENT
        // =====================================================

        mainContent.getChildren().addAll(
                header,
                cards,
                addButtonBox,
                filterBox,
                table
        );

        //root.setCenter(mainContent);
        root.setCenter(mainContent);

        // =====================================================
        // SCENE
        // =====================================================

      /*  Scene scene =
              new Scene(
                        root,
                        1500,
                        800
                );

        stage.setScene(scene);

        stage.setTitle(
                "MaaCareAI - Lab Records"
        );*/

      //  stage.show();
    }

    // =========================================================
    // FILTER METHOD
    // =========================================================

    private void applyFilter(
            TableView<Labrecords> table,
            ObservableList<Labrecords> data,
            String searchText,
            String selectedDepartment,
            String selectedStatus
    ) {

        String search =
                searchText == null
                        ? ""
                        : searchText.toLowerCase();

        ObservableList<Labrecords> filtered =
                FXCollections.observableArrayList();

        for (Labrecords record : data) {

            boolean searchMatch =
                    record.getPatientName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    record.getTestName()
                            .toLowerCase()
                            .contains(search);

            boolean departmentMatch =
                    selectedDepartment.equals(
                            "All Departments"
                    )
                    ||
                    record.getDepartment()
                            .equals(selectedDepartment);

            boolean statusMatch =
                    selectedStatus.equals(
                            "All Status"
                    )
                    ||
                    record.getStatus()
                            .equals(selectedStatus);

            if (
                    searchMatch
                    && departmentMatch
                    && statusMatch
            ) {

                filtered.add(record);
            }
        }

        table.setItems(filtered);  
    }

    // =========================================================
    // STAT CARD METHOD
    // =========================================================

   
private VBox createStatCard(
        String symbol,
        String number,
        String title,
        String bottomText,
        String color
) {

    VBox card =
            new VBox(8);

    card.setPadding(
            new Insets(18)
    );

    card.setPrefHeight(155);
    card.setPrefWidth(260);

    card.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 14;" +
            "-fx-background-radius: 14;"
    );

    // =====================================================
    // TOP ROW - SYMBOL + NUMBER
    // =====================================================

    HBox topRow =
            new HBox(12);

    topRow.setAlignment(
            Pos.CENTER_LEFT
    );

    Label symbolLabel =
            new Label(symbol);

    symbolLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    24
            )
    );

    symbolLabel.setTextFill(
            Color.web(color)
    );

    symbolLabel.setStyle(
            "-fx-background-color: " + LIGHT_PINK + ";" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 8 12;"
    );

    Label numberLabel =
            new Label(number);
            if (title.equals("Total Reports")) {
    totalReportsLabel = numberLabel;
}
else if (title.equals("Pending Reports")) {
    pendingReportsLabel = numberLabel;
}
else if (title.equals("Completed Reports")) {
    completedReportsLabel = numberLabel;
}
else if (title.equals("Today's Reports")) {
    todayReportsLabel = numberLabel;
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

    topRow.getChildren().addAll(
            symbolLabel,
            numberLabel
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
                    13
            )
    );

    bottomLabel.setTextFill(
            Color.web(color)
    );

    card.getChildren().addAll(
            topRow,
            titleLabel,
            bottomLabel
    );

    return card;  
}  

// =========================================================
// UPDATE LAB RECORD STAT CARDS
// =========================================================

private void updateStatCards() {

    // Firebase मधून latest data घ्या
    List<Labrecords> records =
            controller.getAllLabrecords();

    int total = records.size();

    int pending = 0;
    int completed = 0;
    int today = 0;

    LocalDate todayDate = LocalDate.now();

    for (Labrecords record : records) {

        // ---------------------------------------------
        // STATUS COUNT
        // ---------------------------------------------

        if (record.getStatus() != null) {

            if (record.getStatus()
                    .equalsIgnoreCase("Pending")) {

                pending++;

            }
            else if (record.getStatus()
                    .equalsIgnoreCase("Completed")) {

                completed++;
            }
        }

        // ---------------------------------------------
        // TODAY'S REPORT COUNT
        // ---------------------------------------------

        if (record.getDate() != null) {

            String dateText =
                    record.getDate().trim();

            try {

                // Example:
                // 29 Aug 2026 | 10:30 AM

                String datePart =
                        dateText.split("\\|")[0].trim();

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern(
                                "dd MMM yyyy"
                        );

                LocalDate recordDate =
                        LocalDate.parse(
                                datePart,
                                formatter
                        );

                if (recordDate.equals(todayDate)) {
                    today++;
                }

            }
            catch (DateTimeParseException ex) {

                System.out.println(
                        "Invalid lab record date: "
                        + dateText
                );
            }
        }
    }

    // ---------------------------------------------
    // UPDATE UI
    // ---------------------------------------------

    totalReportsLabel.setText(
            String.valueOf(total)
    );

    pendingReportsLabel.setText(
            String.valueOf(pending)
    );

    completedReportsLabel.setText(
            String.valueOf(completed)
    );

    todayReportsLabel.setText(
            String.valueOf(today)
    );
}



public BorderPane getView(){ 
      //  return root;

      if(root==null){ 
        createView();
      }
      return root;
}   

}

   
    
