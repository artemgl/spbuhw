package com.artemchernikov.g144;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Game {

    private InputStream inputStream;
    private OutputStream outputStream;

    private TicTacToe ticTacToe = new TicTacToe();

    private List<Button> buttons = new LinkedList<>();

    public Game() {
    }

    public Game(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public synchronized void send(int value) {
        try {
            outputStream.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized int receive() throws IOException {
        return inputStream.read();
    }

    public void unlockAllButtons() {
        for (Button btn : buttons) {
            btn.setDisable(false);
        }
    }

    public void lockAllButtons() {
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }

    public void initialize() {
//        headline.setText("Crosses' move");

        Thread receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (inputStream.available() != 0) {
                        updateBoard();
                        sleep(1000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        receiver.setDaemon(true);
        receiver.start();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(300, 300);

        for (int i = 0; i < 9; i++) {
            Button btn = new Button();
            final int j = i;
            btn.setOnAction(event1 -> {
                ticTacToe.makeMove(j);
                btn.setText(ticTacToe.getCurrentMoveSymbol().equals("X") ? "O" : "X");
                lockAllButtons();
                send(j);
            });

            buttons.add(btn);

            btn.setPrefSize(60, 60);
            btn.setLayoutX(5 + 60 * (i % 3));
            btn.setLayoutY(5 + 60 * (int)(i / 3));

            anchorPane.getChildren().add(btn);
        }

        Stage stage = new Stage();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void updateBoard() throws IOException {
        int index = receive();

        ticTacToe.makeMove(index);

        buttons.get(index).setText(ticTacToe.getField()[index]);

//        unlockAllButtons();
    }

}
