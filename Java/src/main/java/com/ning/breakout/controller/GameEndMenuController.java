package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.GameBoard;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

/**
 * The controller class of the game end menu, extends from the abstract class
 * NormalSceneController and defines the event handlers of the game end menu.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 *
 * @author Ning ZHU
 */
public class GameEndMenuController extends NormalSceneController {

    /**
     * The label to show the prompt message and display the score.
     */
    @FXML
    private Label m_ScoreLabel;


    private Label getScoreLabel() {
        return this.m_ScoreLabel;
    }


    /**
     * Switches to the username form to upload score when the upload score
     * button clicked.
     *
     * @see StartGame#switchToUsernameForm()
     */
    @FXML
    public void uploadScore() {
        getStartGame().switchToUsernameForm();
    }

    /**
     * Starts a new round of game from the first level when the play again
     * button clicked.
     *
     * @see GameBoard#replayGame()
     * @see StartGame#switchToGameBoard()
     */
    @FXML
    public void playAgain() {
        getStartGame().getGameBoard().replayGame();
        getStartGame().switchToGameBoard();
    }

    /**
     * Switches to the main menu when the back to main button clicked.
     *
     * @see StartGame#switchToMainMenu()
     */
    @FXML
    public void backToMain() {
        getStartGame().switchToMainMenu();
    }

    /**
     * Displays the score of this round of game when score label clicked.
     */
    @FXML
    public void showScore() {
        getScoreLabel().setText(String.format("You get %d points",
            getStartGame().getGameBoard().getManager().getGameScore()));
        getScoreLabel().setCursor(Cursor.DEFAULT);
    }

}
