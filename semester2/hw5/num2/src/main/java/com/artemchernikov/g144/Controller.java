package com.artemchernikov.g144;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField result;

    @FXML
    private Spinner firstOperand;

    @FXML
    private Spinner secondOperand;

    @FXML
    private ChoiceBox<String> operation;

    /**A method calculating value of the expression composed by user*/
    public void calculate() {
        double answer = 0;
        String operator = operation.getValue();
        double first = (Integer)firstOperand.getValue();
        double second = (Integer)secondOperand.getValue();
        switch (operator) {
            case "+":
                answer = first + second;
                break;
            case "-":
                answer = first - second;
                break;
            case "*":
                answer = first * second;
                break;
            case "/":
                answer = first / second;
                break;
        }
        result.setText(answer + "");
    }
}
