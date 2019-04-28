package com.artemchernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {

    private String expressionString = "";
    private String currentNumber = "0";

    @FXML
    private TextField answerField;

    @FXML
    private Label expression;

    /**A method changing font size so that all the symbols fit to the field*/
    private void setNeededTextSize(int lengthOfLine) {
        int size = 0;
        if (lengthOfLine == 0) {
            size = 20;
        } else {
            size = 322 / lengthOfLine;
            if (size > 20) {
                size = 20;
            }
        }

        answerField.setStyle("-fx-font-size: " + size + ";");
    }

    /**A method processing button presses*/
    public void processButton(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        String pressedButtonId = pressedButton.getId();
        switch (pressedButtonId) {
            case "numeral0":
            case "numeral1":
            case "numeral2":
            case "numeral3":
            case "numeral4":
            case "numeral5":
            case "numeral6":
            case "numeral7":
            case "numeral8":
            case "numeral9": {
                if (currentNumber.equals("0")) {
                    currentNumber = "";
                }
                currentNumber += pressedButton.getText();
                setNeededTextSize(currentNumber.length());
                answerField.setText(currentNumber);
                break;
            }
            case "point": {
                if (currentNumber.isEmpty()) {
                    currentNumber += "0";
                }
                if (!currentNumber.contains(".")) {
                    currentNumber += ".";
                }
                setNeededTextSize(currentNumber.length());
                answerField.setText(currentNumber);
                break;
            }
            case "division":
            case "multiplication":
            case "subtraction":
            case "addition": {
                if (currentNumber.isEmpty()) {
                    if (expressionString.isEmpty()) {
                        if (answerField.getText().equals("Infinity") || answerField.getText().equals("-Infinity") || answerField.getText().equals("NaN")) {
                            break;
                        } else {
                            expressionString = answerField.getText();
                            expressionString += " ";
                        }
                    } else {
                        expressionString = expressionString.substring(0, expressionString.length() - 1);
                    }
                } else {
                    String checkIfCurrentNumberIsInteger = currentNumber.replaceAll("0", "");
                    if (checkIfCurrentNumberIsInteger.endsWith(".")) {
                        for (int i = 0; i < currentNumber.length(); i++) {
                            if (currentNumber.charAt(i) == '.') {
                                currentNumber = currentNumber.substring(0, i);
                            }
                        }
                    }
                    expressionString += " " + currentNumber + " ";
                    currentNumber = "";
                }
                expressionString += pressedButton.getText();
                expression.setText(expressionString);

                String result = "" + Calculator.calculate(expressionString.substring(0, expressionString.length() - 2));
                if (result.endsWith(".0")) {
                    result = result.substring(0, result.length() - 2);
                }
                setNeededTextSize(result.length());
                answerField.setText(result);
                break;
            }
            case "backspace":
                if (!currentNumber.isEmpty()) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                    if (currentNumber.isEmpty()) {
                        currentNumber = "0";
                    }
                    setNeededTextSize(currentNumber.length());
                    answerField.setText(currentNumber);
                }
                break;
            case "clean":
                expression.setText(expressionString = "");
                //fall through
            case "cleanEntry":
                setNeededTextSize(currentNumber.length());
                answerField.setText(currentNumber = "0");
                break;
            case "getResult":
                if (currentNumber.isEmpty()) {
                    expressionString += answerField.getText();
                } else {
                    if (currentNumber.endsWith(".")) {
                        currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                    }
                    expressionString += " " + currentNumber;
                }
                currentNumber = "" + Calculator.calculate(expressionString);
                expression.setText(expressionString = "");

                if (currentNumber.endsWith(".0")) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 2);
                }
                setNeededTextSize(currentNumber.length());
                answerField.setText(currentNumber);
                currentNumber = "";
                break;
        }
    }
}
