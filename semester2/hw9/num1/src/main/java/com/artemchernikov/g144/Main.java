package com.artemchernikov.g144;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends Application {

    private String ip;
    private int port = 8189;

    private TicTacToeConnection serverConnection;
    private TicTacToeConnection clientConnection;

    private Server createServer(Stage stage) {
        return new Server(port, stage);
    }

    private Client createClient(String ip, Stage stage) {
        return new Client(ip, port, stage);
    }

    private Parent createContentForServer() {
        AnchorPane waitPane = new AnchorPane();
        waitPane.setPrefWidth(200);
        waitPane.setPrefHeight(150);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressIndicator.setLayoutX(75);
        progressIndicator.setLayoutY(75);
        waitPane.getChildren().add(progressIndicator);

        Label headline = new Label("Connection waiting...");
        headline.setPrefSize(200, 15);
        headline.setLayoutX(0);
        headline.setLayoutY(20);
        headline.setTextAlignment(TextAlignment.CENTER);
        headline.setAlignment(Pos.CENTER);
        waitPane.getChildren().add(headline);

        String ipAddress = "";
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Label message = new Label("Your IP-address: " + ipAddress);
        message.setPrefSize(200, 15);
        message.setLayoutX(0);
        message.setLayoutY(40);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setAlignment(Pos.CENTER);
        waitPane.getChildren().add(message);

        try {
            serverConnection.startConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return waitPane;
    }

    private Parent createContentForClient(Stage stage, Stage primaryStage) {
        AnchorPane connectPane = new AnchorPane();
        connectPane.setPrefWidth(200);
        connectPane.setPrefHeight(150);

        Label info = new Label("Enter ip-address and press \"Connect\"");
        info.setPrefSize(200, 15);
        info.setLayoutX(0);
        info.setLayoutY(10);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setAlignment(Pos.CENTER);
        connectPane.getChildren().add(info);

        TextField textField = new TextField();
        textField.setPrefSize(150, 20);
        textField.setLayoutX(25);
        textField.setLayoutY(40);
        connectPane.getChildren().add(textField);

        Button connectBtn = new Button("Connect");
        connectBtn.setPrefSize(80, 10);
        connectBtn.setLayoutX(60);
        connectBtn.setLayoutY(80);

        connectBtn.setOnAction(event -> {
            ip = textField.getText();
            clientConnection = createClient(ip, (Stage)connectBtn.getScene().getWindow());
            try {
                clientConnection.startConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.close();
        });
        connectPane.getChildren().add(connectBtn);

        return connectPane;
    }

    @Override
    public void start(Stage primaryStage) {

        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(110);

        Button createServerBtn = new Button("Create server");
        createServerBtn.setPrefSize(120, 20);
        createServerBtn.setLayoutX(40);
        createServerBtn.setLayoutY(20);
        createServerBtn.setOnAction(event -> {
            primaryStage.close();
            Stage stage = new Stage();
            serverConnection = createServer(stage);
            stage.setScene(new Scene(createContentForServer(), 200, 150));
            stage.setTitle("Waiting");
            stage.show();
        });
        pane.getChildren().add(createServerBtn);

        Button joinServerBtn = new Button("Join to server");
        joinServerBtn.setPrefSize(120, 20);
        joinServerBtn.setLayoutX(40);
        joinServerBtn.setLayoutY(60);
        joinServerBtn.setOnAction(event -> {
            Stage stage = new Stage();
            Scene scene = new Scene(createContentForClient(stage, primaryStage), 200, 135);
            stage.setTitle("Joining");
            stage.setScene(scene);
            stage.show();
        });
        pane.getChildren().add(joinServerBtn);

        Scene scene = new Scene(pane, 200, 110);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
