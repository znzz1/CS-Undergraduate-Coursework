package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;


/**
 * The controller class of the main menu, extends from the abstract class
 * NormalSceneController and defines the event handlers of the main menu.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 *
 * @author Ning ZHU
 */
public class MainMenuController extends NormalSceneController {

    private MediaPlayer m_WelcomeMusic;

    private MediaPlayer m_ClickButtonSound;

    /**
     * The music icon to show and control the play status of the welcome music.
     */
    @FXML
    private ImageView m_MusicIcon;


    private MediaPlayer getWelcomeMusic() {
        return this.m_WelcomeMusic;
    }

    private MediaPlayer getClickButtonSound() {
        return this.m_ClickButtonSound;
    }

    private ImageView getMusicIcon() {
        return this.m_MusicIcon;
    }


    private void setWelcomeMusic(MediaPlayer welcomeMusic) {
        this.m_WelcomeMusic = welcomeMusic;
    }

    private void setClickButtonSound(MediaPlayer clickButtonSound) {
        this.m_ClickButtonSound = clickButtonSound;
    }


    /**
     * The class constructor specifies the MediaPlayers to play the welcome
     * music and the sound for clicking button, and sets the StartGame instance
     * of the main menu controller to switches scenes.
     *
     * @param welcomeMusic the MediaPlayer to play the welcome music when user
     *                     enters the main menu
     * @param clickButtonSound the MediaPlayer to play the sound for clicking
     *                         buttons in the main menu
     * @param startGame the StartGame instance which used to switch scenes
     * @see javafx.scene.media.MediaPlayer
     */
    public MainMenuController(MediaPlayer welcomeMusic,
                              MediaPlayer clickButtonSound,
                              StartGame startGame) {
        setWelcomeMusic(welcomeMusic);
        setClickButtonSound(clickButtonSound);
        setStartGame(startGame);
    }


    /**
     * Plays the welcome music when the main menu initialized.
     */
    @FXML
    public void initialize() {
        getWelcomeMusic().play();
    }

    /**
     * Starts the game when the play button clicked.
     *
     * @see StartGame#resetGameBoard()
     * @see StartGame#switchToGameBoard()
     */
    @FXML
    public void playGame() {
        getWelcomeMusic().stop();
        getClickButtonSound().play();
        getClickButtonSound().seek(getClickButtonSound().getStartTime());
        getStartGame().resetGameBoard();
        getStartGame().switchToGameBoard();
    }

    /**
     * Switches to the preference scene to choose the color theme of the game
     * board when the preferences button clicked.
     *
     * @see StartGame#switchToPreferences()
     */
    @FXML
    public void setPreference() {
        getWelcomeMusic().stop();
        getClickButtonSound().play();
        getClickButtonSound().seek(getClickButtonSound().getStartTime());
        getStartGame().switchToPreferences();
    }

    /**
     * Switches to the game guidance scene when the guidance button clicked.
     *
     * @see StartGame#switchToGuidance()
     */
    @FXML
    public void openGuidance() {
        getWelcomeMusic().stop();
        getClickButtonSound().play();
        getClickButtonSound().seek(getClickButtonSound().getStartTime());
        getStartGame().switchToGuidance();
    }

    /**
     * Exits the game when the exit button clicked.
     */
    @FXML
    public void exitGame() {
        getClickButtonSound().play();
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    /**
     * Mutes the welcome music when the music icon clicked.
     * <p>
     * The MediaPlayer of the welcome music will be muted by clicking the music
     * icon and unmuted by clicking the music icon again. And at the same time,
     * the image source of the music icon will switch between play-icon and
     * mute-icon.
     */
    @FXML
    public void muteWelcomeMusic() {
        if (getWelcomeMusic().isMute()) {
            getWelcomeMusic().setMute(false);
            getMusicIcon().setImage(new Image(Objects.requireNonNull(
                StartGame.class.getResource(
                    "image/icons/play-icon.png")).toExternalForm()));
        } else {
            getWelcomeMusic().setMute(true);
            getMusicIcon().setImage(new Image(Objects.requireNonNull(
                StartGame.class.getResource(
                    "image/icons/mute-icon.png")).toExternalForm()));
        }
    }

}
