package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import javafx.fxml.FXML;

/**
 * The controller class of the guidance scene, extends from the abstract class
 * NormalSceneController and defines the event handlers of the guidance scene.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 *
 * @author Ning ZHU
 */
public class GuidanceController extends NormalSceneController {

    /**
     * Switches to the main menu when the back button clicked.
     *
     * @see StartGame#switchToMainMenu()
     */
    @FXML
    public void backToMain() {
        getStartGame().switchToMainMenu();
    }

}
