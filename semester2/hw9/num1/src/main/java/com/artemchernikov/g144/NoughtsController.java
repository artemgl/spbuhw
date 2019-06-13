package com.artemchernikov.g144;

import javafx.event.ActionEvent;

/**A class describing controller of noughts' side*/
public class NoughtsController extends Controller {

    private TicTacToe ticTacToe = new TicTacToe();

    public void initialize() {
        super.initialize();
        ticTacToe.switchTurn();
    }

    public void makeMove(ActionEvent actionEvent) {
        super.makeMove(actionEvent, "O");
    }

}
