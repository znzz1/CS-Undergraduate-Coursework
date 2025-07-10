package com.ning.breakout.controller;

import com.ning.breakout.view.GameBoard;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;


/**
 * The controller class of the debug console, extends from the abstract class
 * GameSceneController and defines the event handlers of the debug console.
 *
 * @see com.ning.breakout.controller.GameSceneController
 *
 * @author Ning ZHU - modified
 */
public class DebugConsoleController extends GameSceneController {

    @FXML
    private Button m_SkipButton;

    /**
     * The slider controls the x speed of the ball.
     */
    @FXML
    private Slider m_SpeedXSlider;

    /**
     * The slider controls the y speed of the ball.
     */
    @FXML
    private Slider m_SpeedYSlider;


    private Button getSkipButton() {
        return this.m_SkipButton;
    }

    private Slider getSpeedXSlider() {
        return this.m_SpeedXSlider;
    }

    private Slider getSpeedYSlider() {
        return this.m_SpeedYSlider;
    }


    /**
     * The class constructor specifies the GameBoard instance of the debug
     * console controller.
     *
     * @param gameBoard the GameBoard instance used to interact data between the
     *                  debug console and the game board
     */
    public DebugConsoleController(GameBoard gameBoard) {
        setGameBoard(gameBoard);
    }


    /**
     * Initializes the sliders.
     * <p>
     * Sets the value of the sliders to the current speed of the ball and adds
     * change listener (observer design pattern) to the value property of the
     * sliders to make that every time the sliders' value changed, the speed of
     * ball will also be changed correspondingly.
     */
    @FXML
    public void initialize() {
        //Sets the initial value of the sliders, loads the speed of ball
        getSpeedXSlider().setValue(
            getGameBoard().getManager().getBall().getSpeedX());
        getSpeedYSlider().setValue(
            getGameBoard().getManager().getBall().getSpeedY());

        //Binds sliders' value property with ball's speed
        getSpeedXSlider().valueProperty().addListener(
            (ob, oldVal, newValue) ->
                getGameBoard().getManager().getBall().setSpeedX(
                    newValue.intValue()));
        getSpeedYSlider().valueProperty().addListener(
            (ob, oldVal, newValue) ->
                getGameBoard().getManager().getBall().setSpeedY(
                    newValue.intValue()));
    }

    /**
     * Closes the debug console when the close button clicked.
     */
    @FXML
    public void closeDebugConsole() {
        getGameBoard().hideDebugConsole();
    }

    /**
     * Skips the current level of game when the skip level button clicked.
     * <p>
     * Notes that user cannot skip the last level, and if user tries to skip
     * the last level, user will receive an alert and the skip level button will
     * be set to invisible.
     */
    @FXML
    public void skipLevel() {
        if (!getGameBoard().getManager().hasNextLevel()) {
            //User cannot skip the last level
            Alert alert = new Alert(Alert.AlertType.ERROR,
                "This already the last level!");
            alert.showAndWait();
            getSkipButton().setVisible(false);
        } else {
            getGameBoard().getManager().nextLevel();
            //Sets the bricks' view of next level
            getGameBoard().setBricksView();
            getGameBoard().updateGameStatus();
        }
    }

    /**
     * Resets the ball count when the reset ball count button clicked.
     */
    @FXML
    public void resetBallCount() {
        getGameBoard().getManager().resetBallCount();
        getGameBoard().updateGameStatus();
    }

}
