package com.artemchernikov.g144;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Calculator");
            primaryStage.setMinWidth(244);
            primaryStage.setMinHeight(279);
            primaryStage.setMaxWidth(244);
            primaryStage.setMaxHeight(279);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
