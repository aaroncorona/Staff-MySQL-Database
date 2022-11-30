package com.example.aaroncorona_cs56_proj9.view;

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
        final TextField tfId = new TextField();
        final TextField tfFirstName = new TextField();
        final TextField tfLastName = new TextField();
        final TextField tfMi = new TextField();
        final TextField tfAddress = new TextField();
        final TextField tfCity = new TextField();
        final TextField tfPhone = new TextField();
        final TextField tfEmail = new TextField();

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
//        btnBalance.setOnAction(event -> {
//        });
//        btnDeposit.setOnAction(event -> {
//        });
//        btnWithdraw.setOnAction(event -> {
//        });
//        btnQuit.setOnAction(event -> {
//        });

        // Label title for the Staff results
        final Label labelResultsTitle = new Label("Staff Display");
        labelResultsTitle.setPrefWidth(480);
        labelResultsTitle.setTextFill(Color.web("#0076a3"));
        labelResultsTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        final HBox resultsTitle = new HBox();
        resultsTitle.getChildren().addAll(labelResultsTitle);

        // Label to show View results that display Staff info
        final Label labelResults = new Label("hi\nhi");
        final HBox results = new HBox();
        results.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        results.setAlignment(Pos.CENTER);
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

    @Override
    public void run() {
        launch();
    }
}