package com.artemchernikov.g144;

import javafx.event.ActionEvent;

/**A class describing controller of crosses' side*/
public class CrossesController extends Controller {

    public void initialize() {
        super.initialize();
    }

    public void makeMove(ActionEvent actionEvent) {
        super.makeMove(actionEvent, "X");
    }

}
