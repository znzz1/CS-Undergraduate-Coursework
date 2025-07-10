package com.ning.breakout.view;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardManagerTest {

    private static JFXPanel panel;

    private static final Random rnd = new Random();

    private static GameBoardManager gameBoardManager;

    @BeforeAll
    static void init() {
        panel = new JFXPanel();
        gameBoardManager = new GameBoardManager(
            new Rectangle(0, 0, 600, 450), 30, 3, 3, new Point2D(300, 430));
        GameSoundFactory factory = new GameSoundFactory();
        gameBoardManager.setBreakBrickSound(factory.getBreakBrickSound());
        gameBoardManager.setImpactPaddleSound(factory.getImpactPaddleSound());
        gameBoardManager.setBallLostSound(factory.getBallLostSound());
    }


    @Test
    void testHandlePaddleImpact() {
        gameBoardManager.getBall().moveTo(new Point2D(300, 431));

        int validSpeedY = rnd.nextInt(1, 5);
        gameBoardManager.getBall().setSpeedY(validSpeedY);
        gameBoardManager.handleImpacts();
        assertEquals(-validSpeedY, gameBoardManager.getBall().getSpeedY());

        int invalidSpeedY = -rnd.nextInt(1, 5);
        gameBoardManager.getBall().setSpeedY(invalidSpeedY);
        gameBoardManager.handleImpacts();
        assertEquals(invalidSpeedY, gameBoardManager.getBall().getSpeedY());
    }

    @Test
    void testHandleBallLost() {
        gameBoardManager.getBall().moveTo(new Point2D(200, 460));
        int ballCount = gameBoardManager.getBallCount();
        gameBoardManager.handleImpacts();
        assertTrue(gameBoardManager.getBallLost());
        assertEquals(ballCount - 1, gameBoardManager.getBallCount());
    }

    @RepeatedTest(10)
    void testIsLevelPassedWhenBrickCountBiggerThanZero() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_LevelBrickCount =
            GameBoardManager.class.getDeclaredField("m_LevelBrickCount");
        m_LevelBrickCount.setAccessible(true);

        m_LevelBrickCount.set(gameBoardManager, rnd.nextInt(1, 1000));
        assertFalse(gameBoardManager.isLevelPassed());
    }

    @Test
    void testIsLevelPassesWhenBrickCountEqualsToZero() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_LevelBrickCount =
            GameBoardManager.class.getDeclaredField("m_LevelBrickCount");
        m_LevelBrickCount.setAccessible(true);

        m_LevelBrickCount.set(gameBoardManager, 0);
        assertTrue(gameBoardManager.isLevelPassed());
    }

    @Test
    void testIsGameFail() throws NoSuchFieldException, IllegalAccessException {
        Field m_BallCount =
            GameBoardManager.class.getDeclaredField("m_BallCount");
        m_BallCount.setAccessible(true);

        assertFalse(gameBoardManager.isGameFail());
        m_BallCount.set(gameBoardManager, 2);
        assertFalse(gameBoardManager.isGameFail());
        m_BallCount.set(gameBoardManager, 1);
        assertFalse(gameBoardManager.isGameFail());
        m_BallCount.set(gameBoardManager, 0);
        assertTrue(gameBoardManager.isGameFail());
    }

    @Test
    void testHasNextLevel() throws NoSuchFieldException, IllegalAccessException{
        Field m_Level = GameBoardManager.class.getDeclaredField("m_Level");
        m_Level.setAccessible(true);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 1);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 2);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 3);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 4);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 5);
        assertTrue(gameBoardManager.hasNextLevel());
        m_Level.set(gameBoardManager, 6);
        assertFalse(gameBoardManager.hasNextLevel());
    }

    @Test
    void testResetBall() throws NoSuchFieldException, IllegalAccessException {
        Point2D startPoint = new Point2D(300, 430);

        gameBoardManager.getBall().moveTo(
            new Point2D(rnd.nextInt(), rnd.nextInt()));
        gameBoardManager.getPlayer().moveTo(
            new Point2D(rnd.nextInt(), rnd.nextInt()));

        gameBoardManager.getBall().setSpeedX(rnd.nextInt());
        gameBoardManager.getBall().setSpeedY(rnd.nextInt());

        Field m_BallLost =
            GameBoardManager.class.getDeclaredField("m_BallLost");
        m_BallLost.setAccessible(true);
        m_BallLost.set(gameBoardManager, true);
        assertTrue(gameBoardManager.getBallLost());

        gameBoardManager.resetBall();

        assertEquals(startPoint.getX(),
            gameBoardManager.getBall().getCenterPoint().getX());
        assertEquals(startPoint.getY(),
            gameBoardManager.getBall().getCenterPoint().getY());

        int HALF_WIDTH = 75;
        assertEquals(startPoint.getX(),
            gameBoardManager.getPlayer().getPaddleView().getX() +HALF_WIDTH);
        assertEquals(startPoint.getY(),
            gameBoardManager.getPlayer().getPaddleView().getY());

        assertFalse(gameBoardManager.getBallLost());

        int SPEED_X_UPPER_BOUND = 2;
        int SPEED_X_LOWER_BOUND = -2;
        int speedX = gameBoardManager.getBall().getSpeedX();
        assertTrue(speedX != 0 && speedX >= SPEED_X_LOWER_BOUND
                       && speedX <= SPEED_X_UPPER_BOUND);

        int SPEED_Y_LOWER_BOUND = -2;
        int speedY = gameBoardManager.getBall().getSpeedY();
        assertTrue(speedY <0 && speedY >= SPEED_Y_LOWER_BOUND);
    }

    @Test
    void testResetBricks() {
        for (int i = 0; i < gameBoardManager.getBricks().length; i++) {
            if(rnd.nextDouble() > 0.5) {
                gameBoardManager.getBricks()[i].setImpact(null, 0);
                assertTrue(gameBoardManager.getBricks()[i].isBroken());
            }
        }

        gameBoardManager.resetBricks();
        for (int i = 0; i < gameBoardManager.getBricks().length; i++) {
            assertFalse(gameBoardManager.getBricks()[i].isBroken());
        }
    }

    @RepeatedTest(5)
    void testResetBallCount() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_BallCount =
            GameBoardManager.class.getDeclaredField("m_BallCount");
        m_BallCount.setAccessible(true);

        m_BallCount.set(gameBoardManager, rnd.nextInt());
        gameBoardManager.resetBallCount();
        assertEquals(3, gameBoardManager.getBallCount());
    }

}