package com.artemchernikov.g144;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    private ObservableList<Character> operationList = FXCollections.observableArrayList('+', '-', '*', '/');

    @FXML
    private void initialize() {
        operation.setValue('+');
        operation.setItems(operationList);
    }

    @FXML
    private TextField result;

    @FXML
    private Spinner firstOperand;

    @FXML
    private Spinner secondOperand;

    @FXML
    private ChoiceBox<Character> operation;

    public void calculate() {
        double answer = 0;
        char operator = operation.getValue();
        double first = (Integer)firstOperand.getValue();
        double second = (Integer)secondOperand.getValue();
        switch (operator) {
            case '+':
                answer = first + second;
                break;
            case '-':
                answer = first - second;
                break;
            case '*':
                answer = first * second;
                break;
            case '/':
                answer = first / second;
                break;
        }
        result.setText(answer + "");
    }
}
