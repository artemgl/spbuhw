package com.artemchernikov.g144;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.*;

public class Controller {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Slider slider;

    /**A method updates progress of progress bar according slider value*/
    public void updateProgressBar(MouseEvent scrollEvent) {
        progressBar.setProgress(slider.getValue() / 100);
    }
}
