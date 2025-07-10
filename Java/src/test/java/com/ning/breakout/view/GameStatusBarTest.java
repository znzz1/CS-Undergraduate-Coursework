package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameStatusBarTest {

    private static GameStatusBar gameStatus;


    @BeforeAll
    static void init() {
        JFXPanel panel = new JFXPanel();
        gameStatus = new GameStatusBar();
    }


    @Test
    void testUpdateBallCountVisualWhenThreeBalls() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_BallCount =
            GameStatusBar.class.getDeclaredField("m_BallCountVisual");
        m_BallCount.setAccessible(true);
        int THREE_BALLS = 3;
        gameStatus.updateBallCountVisual(THREE_BALLS);
        ImageView threeBalls = (ImageView) m_BallCount.get(gameStatus);
        String url = Objects.requireNonNull(StartGame.class.getResource(
            "image/icons/three-lives.jpg")).toExternalForm();
        assertEquals(url, threeBalls.getImage().getUrl());
    }

    @Test
    void testUpdateBallCountVisualWhenTwoBalls() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_BallCount =
            GameStatusBar.class.getDeclaredField("m_BallCountVisual");
        m_BallCount.setAccessible(true);
        int TWO_BALLS = 2;
        gameStatus.updateBallCountVisual(TWO_BALLS);
        ImageView threeBalls = (ImageView) m_BallCount.get(gameStatus);
        String url = Objects.requireNonNull(StartGame.class.getResource(
            "image/icons/two-lives.jpg")).toExternalForm();
        assertEquals(url, threeBalls.getImage().getUrl());
    }

    @Test
    void testUpdateBallCountVisualWhenOneBall() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_BallCount =
            GameStatusBar.class.getDeclaredField("m_BallCountVisual");
        m_BallCount.setAccessible(true);
        int ONE_BALL = 1;
        gameStatus.updateBallCountVisual(ONE_BALL);
        ImageView threeBalls = (ImageView) m_BallCount.get(gameStatus);
        String url = Objects.requireNonNull(StartGame.class.getResource(
            "image/icons/one-life.jpg")).toExternalForm();
        assertEquals(url, threeBalls.getImage().getUrl());
    }

    @RepeatedTest(5)
    void testUpdateLevelVisual() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_Level =
            GameStatusBar.class.getDeclaredField("m_LevelVisual");
        m_Level.setAccessible(true);
        int level = new Random().nextInt();
        gameStatus.updateLevelVisual(level);
        Label levelLabel = (Label) m_Level.get(gameStatus);
        String text = "Level: " + level;
        assertEquals(text, levelLabel.getText());
    }

    @RepeatedTest(20)
    void testUpdateBrickCountVisual() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_BrickCount =
            GameStatusBar.class.getDeclaredField("m_BrickCountVisual");
        m_BrickCount.setAccessible(true);
        int brickCount = new Random().nextInt();
        gameStatus.updateBrickCountVisual(brickCount);
        Label brickCountLabel = (Label) m_BrickCount.get(gameStatus);
        String text = "Bricks: " + brickCount;
        assertEquals(brickCountLabel.getText(), text);
    }

}