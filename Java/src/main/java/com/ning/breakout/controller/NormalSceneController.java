package com.ning.breakout.controller;


import com.ning.breakout.StartGame;

/**
 * Abstract class NormalSceneController is the base class for those controllers
 * whose scene is at the same level of the game board.
 * <p>
 * By keeping the instance of class StartGame (Main class), controllers can call
 * the methods in class StartGame to change the root of the scene of the stage,
 * which realizes the function of switching scenes.
 * <p>
 * Additionally, because class StartGame also keeps the instance of class
 * GameBoard, we can get the GameBoard instance by calling the public getter
 * method and use it to access/modify the fields of the game board to do some
 * simple data interaction between scenes.
 *
 * @see StartGame
 *
 * @author Ning ZHU
 */
abstract public class NormalSceneController {

    private StartGame m_StartGame;


    /**
     * @return the StartGame instance which used to switch scenes and interact
     * data
     */
    public StartGame getStartGame() {
        return this.m_StartGame;
    }


    /**
     * @param startGame the StartGame instance which used to switch scenes and
     *                  interact data
     */
    public void setStartGame(StartGame startGame) {
        this.m_StartGame = startGame;
    }


    /**
     * Sole constructor. (For invocation by subclass constructors, typically
     * implicit.)
     */
    public NormalSceneController() { }

}
