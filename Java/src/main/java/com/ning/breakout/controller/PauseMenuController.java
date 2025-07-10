package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.GameBoardManager;
import javafx.fxml.FXML;

/**
 * The controller class of the pause menu, extends from the abstract class
 * GameSceneController and defines the event handlers of the pause menu.
 *
 * @see com.ning.breakout.controller.GameSceneController
 * @see StartGame
 *
 * @author Ning ZHU - modified
 */
public class PauseMenuController extends GameSceneController {

    private StartGame m_StartGame;


    private StartGame getStartGame() {
        return this.m_StartGame;
    }


    /**
     * Sets the StartGame instance of the pause menu controller which used to
     * switch back to the main menu.
     *
     * @param startGame the StartGame instance used to switch back to the
     *                  main menu
     */
    public void setStartGame(StartGame startGame) {
        this.m_StartGame = startGame;
    }


    /**
     * Continues to play the game when the continue button clicked. (The pause
     * menu will be closed.)
     */
    @FXML
    public void continueToPlay() {
        getGameBoard().hidePauseMenu();
    }

    /**
     * Restarts the current level of the game when the restart button clicked.
     * (The pause menu will be closed.)
     *
     * @see GameBoardManager#restartLevel()
     */
    @FXML
    public void restartLevel() {
        getGameBoard().getManager().restartLevel();
        getGameBoard().updateGameStatus();

        getGameBoard().hidePauseMenu();
    }

    /**
     * Switches to the main menu when the back to main button clicked.
     *
     * @see StartGame#switchToMainMenu()
     */
    @FXML
    public void backToMain() {
        getGameBoard().hidePauseMenu();
        getStartGame().getListener().setIsPlaying(false);
        getStartGame().switchToMainMenu();
    }

}
