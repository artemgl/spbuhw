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

    /**A method calculating meaning of received expression*/
    public static double calculateExpression(double firstOperand, double secondOperand, String operator) {
        double answer = 0;
        switch (operator) {
            case "+":
                answer = firstOperand + secondOperand;
                break;
            case "-":
                answer = firstOperand - secondOperand;
                break;
            case "*":
                answer = firstOperand * secondOperand;
                break;
            case "/":
                answer = firstOperand / secondOperand;
                break;
        }
        return answer;
    }

    /**A method sets calculated value to answer field*/
    public void calculate() {
        result.setText("" +
                calculateExpression(
                (Integer)firstOperand.getValue(),
                (Integer)secondOperand.getValue(),
                operation.getValue()));
    }
}
