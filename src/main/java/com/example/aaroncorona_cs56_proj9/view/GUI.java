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
        Label labelTitle = new Label("Enter Staff Information");
        Label labelId = new Label("Id");
        Label labelFirstName = new Label("FirstName");
        Label labelLastName = new Label("LastName");
        Label labelMi = new Label("Mi");
        Label labelAddress = new Label("Address");
        Label labelCity = new Label("City");
        Label labelPhone = new Label("Phone");
        Label labelEmail = new Label("Email");

        // Set Label styles
        labelTitle.setPrefWidth(480);
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
        TextField tfId = new TextField();
        TextField tfFirstName = new TextField();
        TextField tfLastName = new TextField();
        TextField tfMi = new TextField();
        TextField tfAddress = new TextField();
        TextField tfCity = new TextField();
        TextField tfPhone = new TextField();
        TextField tfEmail = new TextField();

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

        // Add Buttons for bank actions in an HBox container
//        final Button btnBalance = new Button("Balance");
//        final Button btnDeposit = new Button("Deposit");
//        final Button btnWithdraw = new Button("Withdraw");
//        final Button btnQuit = new Button("Quit");
        final HBox buttons = new HBox();
        buttons.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
//        buttons.getChildren().addAll(btnBalance, btnDeposit, btnWithdraw, btnQuit);

        // Set button actions to call the Bank proxy
//        btnBalance.setOnAction(event -> {
//        });
//        btnDeposit.setOnAction(event -> {
//        });
//        btnWithdraw.setOnAction(event -> {
//        });
//        btnQuit.setOnAction(event -> {
//        });

        // Create an outer container (vertical box) for the Hboxes
        final VBox root = new VBox();
        root.getChildren().addAll(inputs, buttons);
        root.setSpacing(10);

        // Scene
        final Scene scene = new Scene(root, 500,300);
        stage.setTitle("Staff Manager");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void run() {
        launch();
    }
}