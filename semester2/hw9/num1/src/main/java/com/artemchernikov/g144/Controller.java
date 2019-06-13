package com.artemchernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Map;

/**A class describing controller*/
public class Controller {
    private List<Button> buttons;

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

    @FXML
    public Button btn0;
    @FXML
    public Button btn1;
    @FXML
    public Button btn2;
    @FXML
    public Button btn3;
    @FXML
    public Button btn4;
    @FXML
    public Button btn5;
    @FXML
    public Button btn6;
    @FXML
    public Button btn7;
    @FXML
    public Button btn8;

    public void initialize() {
        buttons = List.of(
                btn0, btn1, btn2,
                btn3, btn4, btn5,
                btn6, btn7, btn8
        );
    }

    private void lockAllButtons() {
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }

    /**A method makes move according to pressed button and updates headline*/
    public void makeMove(ActionEvent actionEvent, String symbol) {
        Button pressedButton = (Button)actionEvent.getSource();
        int buttonNumber = buttonNumbers.get(pressedButton.getId());
        if (TicTacToeConnection.ticTacToe.makeMove(buttonNumber)) {
            pressedButton.setText(symbol);

            lockAllButtons();
            TicTacToeConnection.sender.accept(buttonNumber);
            TicTacToeConnection.gameOverChecker.accept(TicTacToeConnection.ticTacToe);
        }
    }

}
