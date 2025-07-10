package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSoundFactoryTest {

    private static GameSoundFactory sound;


    @BeforeAll
    static void init() {
        JFXPanel panel = new JFXPanel();
        sound = new GameSoundFactory();
    }


    @Test
    void testSourceOfWelcomeMusic() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/welcome-music.mp3")).toExternalForm();
        assertEquals(src, sound.getWelcomeMusic().getMedia().getSource());
    }

    @Test
    void testSourceOfClickButtonSound() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/click-button-sound.mp3")).toExternalForm();
        assertEquals(src, sound.getClickButtonSound().getMedia().getSource());
    }

    @Test
    void testSourceOfImpactPaddleSound() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/impact-paddle-sound.mp3")).toExternalForm();
        assertEquals(src, sound.getImpactPaddleSound().getMedia().getSource());
    }

    @Test
    void testSourceOfBreakBrickSound() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/break-brick-sound.wav")).toExternalForm();
        assertEquals(src, sound.getBreakBrickSound().getMedia().getSource());
    }

    @Test
    void testSourceOfBallLostSound() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/ball-lost-sound.mp3")).toExternalForm();
        assertEquals(src, sound.getBallLostSound().getMedia().getSource());
    }

    @Test
    void testSourceOfNextLevelSound() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/next-level-sound.mp3")).toExternalForm();
        assertEquals(src, sound.getNextLevelSound().getMedia().getSource());
    }

    @Test
    void testSourceOfGameFailMusic() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/game-fail-music.mp3")).toExternalForm();
        assertEquals(src, sound.getGameFailMusic().getMedia().getSource());
    }

    @Test
    void testSourceOfGamePassMusic() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/game-pass-music.mp3")).toExternalForm();
        assertEquals(src, sound.getGamePassMusic().getMedia().getSource());
    }

    @Test
    void testSourceOfInspiringMusic() {
        String src = Objects.requireNonNull(StartGame.class.getResource(
            "audio/inspiring-music.mp3")).toExternalForm();
        assertEquals(src, sound.getInspiringMusic().getMedia().getSource());
    }

}