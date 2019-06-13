package com.artemchernikov.g144;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**A class describing connection for playing*/
public abstract class TicTacToeConnection {

    private ConnectionThread connectionThread = new ConnectionThread();
    private List<Button> buttons = new LinkedList<>();

    public static TicTacToe ticTacToe = new TicTacToe();
    public static Consumer<Integer> sender;
    public static Consumer<TicTacToe> gameOverChecker;

    private Stage stage;

    public TicTacToeConnection(Stage stage) {
        sender = (data -> {
            try {
                send(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        gameOverChecker = (ticTacToe1 -> {
            if (ticTacToe1.gameOver()) {
                String message = "";
                if (ticTacToe1.isDraw()) {
                    message = "Draw!";
                }
                if (ticTacToe1.noughtsWin()) {
                    message = "Noughts win!";
                }
                if (ticTacToe1.crossesWin()) {
                    message = "Crosses win!";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
                alert.setHeaderText(null);
                alert.showAndWait();
                System.exit(0);
            }
        });

        this.stage = stage;
        connectionThread.setDaemon(true);
    }

    private void unlockAllButtons() {
        for (Button btn : buttons) {
            btn.setDisable(false);
        }
    }

    private void lockAllButtons() {
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }

    /**A method starts to connect with other player*/
    public void startConnection() throws Exception {
        connectionThread.start();
    }

    private void createContent() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(isServer() ? "crossesGame.fxml" : "noughtsGame.fxml"));
            for (Node node : root.getChildrenUnmodifiable()) {
                if (node instanceof Button) {
                    buttons.add((Button)node);
                }
            }
            Scene scene = new Scene(root);
            stage.setTitle("Tic-tac-toe");
            stage.setMinWidth(204);
            stage.setMinHeight(253);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!isServer()) {
            lockAllButtons();
        }
    }

    private void send(Integer data) throws Exception {
        connectionThread.out.writeObject(data);
    }

    public void closeConnection() throws Exception {
        connectionThread.socket.close();
    }

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    /**A class describing thread which connects with other player*/
    private class ConnectionThread extends Thread {

        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                Platform.runLater(TicTacToeConnection.this::createContent);

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);

                while (true) {
                    Integer index = (Integer)in.readObject();
                    Platform.runLater(() -> {
                        ticTacToe.makeMove(index);
                        buttons.get(index).setText(isServer() ? "O" : "X");
                        unlockAllButtons();
                        gameOverChecker.accept(ticTacToe);
                    });
                }

            } catch (Exception exc) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Connection interrupted", ButtonType.CLOSE);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                });
            }
        }

    }
}
