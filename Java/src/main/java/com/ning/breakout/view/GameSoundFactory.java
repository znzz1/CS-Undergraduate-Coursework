package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

/**
 * GameSoundFactory is the class provides game media players.
 * <p>
 * The media includes:
 * <ul>
 * <li>media for welcoming the user in the main menu</li>
 * <li>media for clicking buttons in the main menu</li>
 * <li>media for impacting paddle in the game board</li>
 * <li>media for breaking brick in the game board</li>
 * <li>media for losing the ball in the game board</li>
 * <li>media for passing the current level in the game board</li>
 * <li>media for failing the game in the game board</li>
 * <li>media for passing the game in the game board</li>
 * <li>media for inspiring the user in the ranking list</li>
 * </ul>
 * @see MediaPlayer
 *
 * @author Ning ZHU
 */
public class GameSoundFactory {

    private MediaPlayer m_WelcomeMusic;

    private MediaPlayer m_ClickButtonSound;

    private MediaPlayer m_ImpactPaddleSound;

    private MediaPlayer m_BreakBrickSound;

    private MediaPlayer m_BallLostSound;

    private MediaPlayer m_NextLevelSound;

    private MediaPlayer m_GameFailMusic;

    private MediaPlayer m_GamePassMusic;

    private MediaPlayer m_InspiringMusic;


    /**
     * Returns the media player to play the welcome music
     *
     * @return the media player to play the welcome music
     */
    public MediaPlayer getWelcomeMusic() {
        return this.m_WelcomeMusic;
    }

    /**
     * Returns the media player to play the sound for clicking button.
     *
     * @return the media player to play the sound for clicking button
     */
    public MediaPlayer getClickButtonSound() {
        return this.m_ClickButtonSound;
    }

    /**
     * Returns the media player to play the sound for impacting paddle.
     *
     * @return the media player to play the sound for impacting paddle
     */
    public MediaPlayer getImpactPaddleSound() {
        return this.m_ImpactPaddleSound;
    }

    /**
     * Returns the media player to play the sound for breaking paddle.
     *
     * @return the media player to play the sound for breaking paddle
     */
    public MediaPlayer getBreakBrickSound() {
        return this.m_BreakBrickSound;
    }

    /**
     * Returns the media player to play the sound for losing ball.
     *
     * @return the media player to play the sound for losing ball
     */
    public MediaPlayer getBallLostSound() {
        return this.m_BallLostSound;
    }

    /**
     * Returns the media player to play the sound for passing the current level.
     *
     * @return the media player to play the sound for passing the current level
     */
    public MediaPlayer getNextLevelSound() {
        return this.m_NextLevelSound;
    }

    /**
     * Returns the media player to play the music when game failed.
     *
     * @return the media player to play the music when game failed
     */
    public MediaPlayer getGameFailMusic() {
        return this.m_GameFailMusic;
    }

    /**
     * Returns the media player to play the music when game passed.
     *
     * @return the media player to play the music when game passed
     */
    public MediaPlayer getGamePassMusic() {
        return this.m_GamePassMusic;
    }

    /**
     * Returns the media player to play the background music for ranking list.
     *
     * @return the media player to play the background music for ranking list
     */
    public MediaPlayer getInspiringMusic() {
        return this.m_InspiringMusic;
    }


    private void setWelcomeMusic(MediaPlayer welcomeMusic) {
        this.m_WelcomeMusic = welcomeMusic;
    }

    private void setClickButtonSound(MediaPlayer clickButtonSound) {
        this.m_ClickButtonSound = clickButtonSound;
    }

    private void setImpactPaddleSound(MediaPlayer impactPaddleSound) {
        this.m_ImpactPaddleSound = impactPaddleSound;
    }

    private void setBreakBrickSound(MediaPlayer breakBrickSound) {
        this.m_BreakBrickSound = breakBrickSound;
    }

    private void setBallLostSound(MediaPlayer ballLostSound) {
        this.m_BallLostSound = ballLostSound;
    }

    private void setNextLevelSound(MediaPlayer nextLevelSound) {
        this.m_NextLevelSound = nextLevelSound;
    }

    private void setGameFailMusic(MediaPlayer gameFailMusic) {
        this.m_GameFailMusic = gameFailMusic;
    }

    private void setGamePassMusic(MediaPlayer gamePassMusic) {
        this.m_GamePassMusic = gamePassMusic;
    }

    private void setInspiringMusic(MediaPlayer inspiringMusic) {
        this.m_InspiringMusic = inspiringMusic;
    }


    /**
     * The class constructor initializes and sets the source of the media
     * players.
     */
    public GameSoundFactory() {
        makeWelcomeMusic();
        makeClickButtonSound();
        makeImpactPaddleSound();
        makeBreakBrickSound();
        makeBallLostSound();
        makeNextLevelSound();
        makeGameFailMusic();
        makeGamePassMusic();
        makeInspiringMusic();
    }


    private void makeWelcomeMusic() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/welcome-music.mp3")).toExternalForm());
        setWelcomeMusic(new MediaPlayer(media));
    }

    private void makeClickButtonSound() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/click-button-sound.mp3")).toExternalForm());
        setClickButtonSound(new MediaPlayer(media));
    }

    private void makeImpactPaddleSound() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/impact-paddle-sound.mp3")).toExternalForm());
        setImpactPaddleSound(new MediaPlayer(media));
    }

    private void makeBreakBrickSound() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/break-brick-sound.wav")).toExternalForm());
        setBreakBrickSound(new MediaPlayer(media));
    }

    private void makeBallLostSound() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/ball-lost-sound.mp3")).toExternalForm());
        setBallLostSound(new MediaPlayer(media));
    }

    private void makeNextLevelSound() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/next-level-sound.mp3")).toExternalForm());
        setNextLevelSound(new MediaPlayer(media));
    }

    private void makeGameFailMusic() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/game-fail-music.mp3")).toExternalForm());
        setGameFailMusic(new MediaPlayer(media));
    }

    private void makeGamePassMusic() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/game-pass-music.mp3")).toExternalForm());
        setGamePassMusic(new MediaPlayer(media));
    }

    private void makeInspiringMusic() {
        Media media = new Media(Objects.requireNonNull(
            StartGame.class.getResource(
                "audio/inspiring-music.mp3")).toExternalForm());
        setInspiringMusic(new MediaPlayer(media));
    }

}
