package com.ning.breakout.controller;

import com.ning.breakout.view.GameBoard;


/**
 * Abstract class GameSceneController is the base class for those controller
 * classes whose scene is the sub-scene of the game board.
 * <p>
 * By keeping the instance of class GameBoard, controllers can call the methods
 * in class GameBoard to access/modify the fields of the game board, which
 * realizes the data interaction between the sub-scene of the game board and
 * the game board.
 *
 * @see GameBoard
 *
 * @author Ning ZHU
 */
abstract public class GameSceneController {

    private GameBoard m_GameBoard;


    /**
     * @return the GameBoard instance which used to interact data between the
     * scene of the controller and the game board.
     */
    public GameBoard getGameBoard() {
        return this.m_GameBoard;
    }


    /**
     * @param gameBoard the GameBoard instance used to interact data between the
     * scene of the controller and the game board.
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.m_GameBoard = gameBoard;
    }

    /**
     * Sole constructor. (For invocation by subclass constructors, typically
     * implicit.)
     */
    public GameSceneController() { }

}
