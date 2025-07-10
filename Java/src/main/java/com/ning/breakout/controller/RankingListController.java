package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.model.RankingListModel;
import com.ning.breakout.view.GameBoard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

/**
 * The controller class of the ranking list, extends from the abstract class
 * NormalSceneController and defines the event handlers of the ranking list.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 * @see RankingListModel
 *
 * @author Ning ZHU
 */
public class RankingListController extends NormalSceneController {

    /**
     * Background music plays when the ranking list loads.
     */
    private MediaPlayer m_InspiringMusic;

    @FXML
    private Label nameOfFirst;
    @FXML
    private Label scoreOfFirst;
    @FXML
    private Label nameOfSecond;
    @FXML
    private Label scoreOfSecond;
    @FXML
    private Label nameOfThird;
    @FXML
    private Label scoreOfThird;
    @FXML
    private Label nameOfFourth;
    @FXML
    private Label scoreOfFourth;
    @FXML
    private Label nameOfFifth;
    @FXML
    private Label scoreOfFifth;


    private MediaPlayer getInspiringMusic() {
        return this.m_InspiringMusic;
    }


    private void setInspiringMusic(MediaPlayer mediaPlayer) {
        this.m_InspiringMusic = mediaPlayer;
    }


    /**
     * The class constructor specifies the background music of the ranking list
     * and sets the StartGame instance of the ranking list controller.
     *
     * @param mediaPlayer the MediaPlayer to play the background music when user
     *                   enters the ranking list
     * @param startGame the StartGame instance used to switch scenes and
     *                  interact data with the game board
     * @see MediaPlayer
     */
    public RankingListController(MediaPlayer mediaPlayer, StartGame startGame) {
        setInspiringMusic(mediaPlayer);
        setStartGame(startGame);
    }


    /**
     * Reads data from the ranking list model and displays the history top
     * <b>5</b> records.
     *
     * @see RankingListModel
     */
    @FXML
    public void initialize() {
        RankingListModel model = new RankingListModel();
        model.readIn();
        model.writeBack();
        //Reads sorted records
        ArrayList<String> records = model.getRankingList();

        int FIRST = 0;
        int SECOND = 1;
        int THIRD = 2;
        int FOURTH = 3;
        int FIFTH = 4;

        int SCORE = 0;
        int NAME = 1;

        scoreOfFirst.setText(records.get(FIRST).split(",")[SCORE]);
        scoreOfSecond.setText(records.get(SECOND).split(",")[SCORE]);
        scoreOfThird.setText(records.get(THIRD).split(",")[SCORE]);
        scoreOfFourth.setText(records.get(FOURTH).split(",")[SCORE]);
        scoreOfFifth.setText(records.get(FIFTH).split(",")[SCORE]);

        nameOfFirst.setText(records.get(FIRST).split(",")[NAME]);
        nameOfSecond.setText(records.get(SECOND).split(",")[NAME]);
        nameOfThird.setText(records.get(THIRD).split(",")[NAME]);
        nameOfFourth.setText(records.get(FOURTH).split(",")[NAME]);
        nameOfFifth.setText(records.get(FIFTH).split(",")[NAME]);

        getInspiringMusic().play();
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
        getInspiringMusic().stop();
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
        getInspiringMusic().stop();
        getStartGame().switchToMainMenu();
    }

}
