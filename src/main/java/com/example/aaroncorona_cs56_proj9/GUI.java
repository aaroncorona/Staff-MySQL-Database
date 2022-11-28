/***************************************************************************
 Author: Aaron Corona
 Date: November 29, 2022
 CS56 Project #9 - Database GUI
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj9;

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
import javafx.stage.Stage;

// GUI for a Client to request Bank actions via Proxy
public final class GUI extends Application {

    private TextField tfAcctNum;
    private TextField tfAmount;
    private Label labelStatus;
    private String labelStatusText;

    @Override
    public void start(Stage stage) {

        // Add a Label and Text fields for "Account Number" in a Hbox
        final Label labelAcctNum = new Label("Enter Account Number:  ");
        tfAcctNum = new TextField();
        tfAcctNum.setPromptText("Account Num");
        tfAcctNum.setPrefColumnCount(10);
        final HBox acctNumRow = new HBox();
        acctNumRow.setPadding(new Insets(15, 15, 15, 15));
        acctNumRow.setAlignment(Pos.CENTER);
        acctNumRow.getChildren().addAll(labelAcctNum, tfAcctNum);

        // Add a Label and Text field for "Amount" in a Hbox
        final Label labelAmt = new Label("Enter Amount (USD):  ");
        tfAmount = new TextField();
        tfAmount.setPromptText("Amount ($)");
        tfAmount.setPrefColumnCount(10);
        final HBox amtRow = new HBox();
        amtRow.setPadding(new Insets(5, 15, 15, 15));
        amtRow.setAlignment(Pos.CENTER);
        amtRow.getChildren().addAll(labelAmt, tfAmount);

        // Add Buttons for bank actions in an HBox container
        final Button btnBalance = new Button("Balance");
        final Button btnDeposit = new Button("Deposit");
        final Button btnWithdraw = new Button("Withdraw");
        final Button btnQuit = new Button("Quit");
        final HBox buttonRow = new HBox();
        buttonRow.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        buttonRow.getChildren().addAll(btnBalance, btnDeposit, btnWithdraw, btnQuit);

        // Title label for the status results
        final Label labelStatusTitle = new Label("Client Request Results: ");

        // Add a Label that returns a message from the Bank proxy
        labelStatus = new Label();
        labelStatusText = "";
        labelStatus.setText(labelStatusText);

        // Set button actions to call the Bank proxy
        btnBalance.setOnAction(event -> {
        });
        btnDeposit.setOnAction(event -> {
        });
        btnWithdraw.setOnAction(event -> {
        });
        btnQuit.setOnAction(event -> {
        });

        // Create an outer container (vertical box) for the rows
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(acctNumRow, amtRow, buttonRow, labelStatusTitle, labelStatus);
        vbox.setSpacing(10);

        // Wrap everything in a scrollbar
        final ScrollPane root = new ScrollPane();
        root.setFitToWidth(true);
        root.setContent(vbox);

        // Scene
        final Scene scene = new Scene(root, 400,500);
        stage.setTitle("Your Account");
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to get the account number
    private int getAcctNum() {
        int acctNum = 0;
        try {
            acctNum = Integer.parseInt(tfAcctNum.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return acctNum;
    }

    // Helper method to get the amount
    private int getAmount() {
        int amount = 0;
        try {
            amount = Integer.parseInt(tfAmount.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return amount;
    }

    // Helper method to update the response label
    private void addStatusText(String newText) {
        labelStatusText += newText + "\n\n";
        labelStatus.setText(labelStatusText);
        System.out.println(newText);
    }
}