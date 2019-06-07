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

        final double fromSymbolWidthToFontSizeCoefficient = 1.5;
        final int maxLengthOfLine = 15;
        final int maxSize = (int)(answerField.getWidth() * fromSymbolWidthToFontSizeCoefficient / maxLengthOfLine);

        if (lengthOfLine == 0) {
            size = maxSize;
        } else {
            size = (int)(answerField.getWidth() * fromSymbolWidthToFontSizeCoefficient / lengthOfLine);
            if (size > maxSize) {
                size = maxSize;
            }
        }

        answerField.setStyle("-fx-font-size: " + size + ";");
    }

    /**A method processing pressing on button with numeral or point*/
    public void processNumeral(ActionEvent actionEvent) {
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
            case "numeral9":
                if (currentNumber.equals("0")) {
                    currentNumber = "";
                }
                currentNumber += pressedButton.getText();
                answerField.setText(currentNumber);
                break;
            case "point":
                if (currentNumber.isEmpty()) {
                    currentNumber += "0";
                }
                if (!currentNumber.contains(".")) {
                    currentNumber += ".";
                }
                answerField.setText(currentNumber);
                break;
        }
        setNeededTextSize(answerField.getText().length());
    }

    /**A method processing pressing on button with operator*/
    public void processOperator(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        String pressedButtonId = pressedButton.getId();
        switch (pressedButtonId) {
            case "division":
            case "multiplication":
            case "subtraction":
            case "addition":
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
                answerField.setText(result);
                break;
        }
        setNeededTextSize(answerField.getText().length());
    }

    /**A method processing pressing on button with data clearing*/
    public void processTextEditingButton(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        String pressedButtonId = pressedButton.getId();
        switch (pressedButtonId) {
            case "backspace":
                if (!currentNumber.isEmpty()) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                    if (currentNumber.isEmpty()) {
                        currentNumber = "0";
                    }
                    answerField.setText(currentNumber);
                }
                break;
            case "clean":
                expression.setText(expressionString = "");
                //fall through
            case "cleanEntry":
                answerField.setText(currentNumber = "0");
                break;
        }
        setNeededTextSize(answerField.getText().length());
    }

    /**A method processing pressing on button with equal sign*/
    public void processGettingAnswer() {
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
    }
}
