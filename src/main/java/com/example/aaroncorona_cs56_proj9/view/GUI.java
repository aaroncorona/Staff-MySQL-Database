package com.example.aaroncorona_cs56_proj9.view;

import com.example.aaroncorona_cs56_proj9.model.Staff;
import com.example.aaroncorona_cs56_proj9.repository.StaffService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// GUI to request database CRUD Services
public final class GUI extends Application implements Runnable {

    // Text fields with global state for continual updating and reading from them
    private TextField tfId;
    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfMi;
    private TextField tfAddress;
    private TextField tfCity;
    private TextField tfPhone;
    private TextField tfEmail;
    private Label labelResults;
    private String labelResultsText;

    @Override
    public void start(Stage stage) {

        // Labels for each Staff variable
        final Label labelTitle = new Label("Create and Edit Staff Members");
        final Label labelId = new Label("Id");
        final Label labelFirstName = new Label("FirstName");
        final Label labelLastName = new Label("LastName");
        final Label labelMi = new Label("Mi");
        final Label labelAddress = new Label("Address");
        final Label labelCity = new Label("City");
        final Label labelPhone = new Label("Phone");
        final Label labelEmail = new Label("Email");

        // Set Label styles
        labelTitle.setPrefWidth(470);
        labelTitle.setTextFill(Color.web("#0076a3"));
        labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labelId.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelFirstName.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelLastName.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelMi.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelAddress.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelCity.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelPhone.setFont(Font.font("Serif", FontWeight.BOLD, 12));
        labelEmail.setFont(Font.font("Serif", FontWeight.BOLD, 12));

        // Text Fields for each Staff variable
        tfId = new TextField();
        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfMi = new TextField();
        tfAddress = new TextField();
        tfCity = new TextField();
        tfPhone = new TextField();
        tfEmail = new TextField();

        // Set preferred TF sizes
        tfId.setPrefColumnCount(35);
        tfFirstName.setPrefColumnCount(12);
        tfLastName.setPrefColumnCount(12);
        tfMi.setPrefColumnCount(1);
        tfAddress.setPrefColumnCount(12);
        tfCity.setPrefColumnCount(12);
        tfPhone.setPrefColumnCount(12);
        tfEmail.setPrefColumnCount(12);

        // Add Labels and Text Fields to an FlowPane
        final FlowPane inputs = new FlowPane();
        inputs.setPadding(new Insets(10,10,10,10));
        inputs.setHgap(10);
        inputs.setVgap(10);
        inputs.setAlignment(Pos.CENTER);
        inputs.getChildren().addAll(
                labelTitle,
                labelId, tfId,
                labelFirstName, tfFirstName,
                labelLastName, tfLastName,
                labelMi, tfMi,
                labelAddress, tfAddress,
                labelCity, tfCity,
                labelPhone, tfPhone,
                labelEmail, tfEmail
        );

        // Add Buttons for CRUD actions
        final Button btnView = new Button("View");
        final Button btnInsert = new Button("Insert");
        final Button btnUpdate = new Button("Update");
        final Button btnClear = new Button("Clear");
        final HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.getChildren().addAll(btnView, btnInsert, btnUpdate, btnClear);

        // Set button actions to call the CRUD Service methods
        btnView.setOnAction(event -> {
            // Extract the ID field and lookup the Staff record
            try{
                int id = Integer.parseInt(tfId.getText());
                Staff staffOutput = StaffService.getStaffRecordByID(id);
                if(staffOutput != null) {
                    addResultText(" Staff Return Successful:  " + staffOutput.toString());
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid ID");
            }
        });
        // Insert Staff record
        btnInsert.setOnAction(event -> {
            Staff staffInput = buildStaffObjFromTextFields();
            Staff staffOutput = StaffService.insertStaffRecord(staffInput);
            if(staffOutput != null) {
                addResultText(" Staff Insert Successful:  " + staffOutput.toString());
            }
        });
        // Update Staff record
        btnUpdate.setOnAction(event -> {
            Staff staffInput = buildStaffObjFromTextFields();
            Staff staffOutput = StaffService.updateStaffRecord(staffInput);
            if(staffOutput != null) {
                addResultText(" Staff Update Successful:  " + staffOutput.toString());
            }
        });
        // Clear all text fields
        btnClear.setOnAction(event -> {
            clearTextFields();
        });

        // Label title for the Staff results
        final Label labelResultsTitle = new Label("Staff Display");
        labelResultsTitle.setPrefWidth(480);
        labelResultsTitle.setTextFill(Color.web("#0076a3"));
        labelResultsTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labelResultsText = "";
        final HBox resultsTitle = new HBox();
        resultsTitle.getChildren().addAll(labelResultsTitle);

        // Label to show View results that display Staff info
        labelResults = new Label();
        final HBox results = new HBox();
        results.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        results.getChildren().addAll(labelResults);

        // Create an outer container (vertical box)
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(inputs, buttons, resultsTitle, results);
        vbox.setSpacing(10);

        // Wrap everything in a scrollbar
        final ScrollPane root = new ScrollPane();
        root.setFitToWidth(true);
        root.setContent(vbox);

        // Scene
        final Scene scene = new Scene(root, 500,400);
        stage.setTitle("Staff Manager");
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to intake the text field data to create a Staff object
    private Staff buildStaffObjFromTextFields() {
        // Make sure that the minimum 3 fields are populated
        if(tfId.getText().equals("")
                || tfFirstName.getText().equals("")
                || tfLastName.getText().equals("")) {
            System.out.println("Error - Please populate an ID, First Name, and Last Name");
            return null;
        }
        // Extract Int fields from the text boxes
        int id = 0;
        int phone = 0;
        try{
            id = Integer.parseInt(tfId.getText());
            phone = Integer.parseInt(tfPhone.getText());
        } catch(NumberFormatException e) {}
        // Build the Staff object
        Staff staff = new Staff(
                id,
                tfFirstName.getText(),
                tfLastName.getText(),
                tfMi.getText(),
                tfAddress.getText(),
                tfCity.getText(),
                phone,
                tfEmail.getText()
        );
        return staff;
    }

    // Helper method to empty text fields
    private void clearTextFields() {
        // Clear all text fields
        tfId.setText("");
        tfFirstName.setText("");
        tfLastName.setText("");
        tfMi.setText("");
        tfAddress.setText("");
        tfCity.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
    }

    // Helper method to update the result label
    private void addResultText(String newText) {
        labelResultsText += newText + "\n\n";
        labelResults.setText(labelResultsText);
        System.out.println(newText);
    }

    @Override
    public void run() {
        launch();
    }
}