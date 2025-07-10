package com.ning.breakout.controller;

import com.ning.breakout.view.GameBoard;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * The controller class defines the key event listener for the whole game
 * (designed for all scenes in the game, but only the listener for game board is
 * implemented).
 *
 * @see GameBoard
 * @see com.ning.breakout.StartGame
 *
 * @author Ning ZHU - modified
 */
public class KeyInputListener {

    private Scene m_Scene;

    private GameBoard m_GameBoard;

    private boolean m_IsPlaying;


    private Scene getScene() {
        return this.m_Scene;
    }

    private GameBoard getGameBoard() {
        return this.m_GameBoard;
    }

    private boolean getIsPlaying() {
        return this.m_IsPlaying;
    }


    private void setScene(Scene scene) {
        this.m_Scene = scene;
    }

    /**
     * @param gameBoard GameBoard instance used to access/modify the fields of
     *                  the game board when key event happens.
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.m_GameBoard = gameBoard;
    }

    /**
     * Sets to true only when user plays in the game board.
     * <p>
     * User can not open debug console, pause menu and move the paddle if user
     * is not playing in the game board.
     *
     * @param isPlaying whether user plays in the game board
     */
    public void setIsPlaying(boolean isPlaying) {
        this.m_IsPlaying = isPlaying;
    }


    /**
     * The class constructor sets the key event listener of specified scene.
     *
     * @param scene the scene of the stage
     */
    public KeyInputListener(Scene scene) {
        setScene(scene);
        addKeyListener();
    }


    private void changeAnimationRunningStatus() {
        if (getGameBoard().getGameTimer().getStatus() ==
                Animation.Status.RUNNING) {
            getGameBoard().getGameTimer().stop();
        } else {
            getGameBoard().getGameTimer().play();
        }
    }

    private void openPauseMenuOrCloseDebugConsole() {
        if (getGameBoard().getShowDebugConsole()) {
            getGameBoard().hideDebugConsole();
        } else {
            getGameBoard().showPauseMenu();
        }
    }

    /**
     * Adds key event listener for the scene.
     */
    private void addKeyListener() {
        getScene().setOnKeyPressed(KeyEvent -> {
            KeyCode code = KeyEvent.getCode();
            if (getIsPlaying()) {
                //User can only play the game in the game board
                if (code == KeyCode.LEFT) {
                    getGameBoard().getManager().getPlayer().moveLeft();
                }
                if (code == KeyCode.RIGHT) {
                    getGameBoard().getManager().getPlayer().moveRight();
                }
                if (code == KeyCode.SPACE) {
                    if (!getGameBoard().getShowPauseMenu() &&
                            !getGameBoard().getShowDebugConsole()) {
                        changeAnimationRunningStatus();
                    }
                }
                if (code == KeyCode.ESCAPE) {
                    getGameBoard().getGameTimer().stop();
                    if (getGameBoard().getShowPauseMenu()) {
                        getGameBoard().hidePauseMenu();
                    } else {
                        openPauseMenuOrCloseDebugConsole();
                    }
                }
                if (code == KeyCode.F1 && KeyEvent.isAltDown()
                        && KeyEvent.isShiftDown()) {
                    if (!getGameBoard().getShowDebugConsole() &&
                            !getGameBoard().getShowPauseMenu()) {
                        getGameBoard().getGameTimer().stop();
                        getGameBoard().showDebugConsole();
                    }
                }
            }
        });
        getScene().setOnKeyReleased(keyEvent -> {
            if (getIsPlaying()) {
                //Paddle can only move when key pressed
                getGameBoard().getManager().getPlayer().stop();
            }
        });
    }

}
