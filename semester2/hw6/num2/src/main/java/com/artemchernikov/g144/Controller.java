package com.artemchernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Map;

public class Controller {

    private final Map<String, Integer> buttonNumbers = Map.of(
            "btn0", 0,
            "btn1", 1,
            "btn2", 2,
            "btn3", 3,
            "btn4", 4,
            "btn5", 5,
            "btn6", 6,
            "btn7", 7,
            "btn8", 8
    );

    private TicTacToe ticTacToe = new TicTacToe();

    @FXML
    public Label headline;

    public void initialize() {
        headline.setText("Crosses' move");
    }

    /**A method makes move according to pressed button and updates headline*/
    public void makeMove(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        if (ticTacToe.makeMove(buttonNumbers.get(pressedButton.getId()))) {
            pressedButton.setText(ticTacToe.getCurrentMoveSymbol().equals("X") ? "O" : "X");

            if (ticTacToe.isDraw()) {
                headline.setText("Draw");
                return;
            }
            if (ticTacToe.crossesWin()) {
                headline.setText("Crosses win!");
                return;
            }
            if (ticTacToe.noughtsWin()) {
                headline.setText("Noughts win!");
                return;
            }

            headline.setText(ticTacToe.getCurrentMoveSymbol().equals("X") ? "Crosses' move" : "Noughts' move");
        }
    }

}
