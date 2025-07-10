package com.ning.breakout.view.graphics;

import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    private static Ball ball;

    private final Random rnd = new Random();


    @BeforeAll
    static void init() {
        ball = Mockito.mock(Ball.class, Mockito.CALLS_REAL_METHODS);
    }


    @Test
    void testGetBallView() throws NoSuchFieldException, IllegalAccessException {
        Field m_BallView = Ball.class.getDeclaredField("m_BallView");
        m_BallView.setAccessible(true);
        ImageView ballView = new ImageView();
        m_BallView.set(ball, ballView);
        assertEquals(ballView, ball.getBallView());
    }

    @RepeatedTest(3)
    void testGetSpeedX() throws NoSuchFieldException, IllegalAccessException {
        Field m_SpeedX = Ball.class.getDeclaredField("m_SpeedX");
        m_SpeedX.setAccessible(true);
        int speedX = rnd.nextInt();
        m_SpeedX.set(ball, speedX);
        assertEquals(speedX, ball.getSpeedX());
    }

    @RepeatedTest(3)
    void testGetSpeedY() throws NoSuchFieldException, IllegalAccessException {
        Field m_SpeedY = Ball.class.getDeclaredField("m_SpeedY");
        m_SpeedY.setAccessible(true);
        int speedY = rnd.nextInt();
        m_SpeedY.set(ball, speedY);
        assertEquals(speedY, ball.getSpeedY());
    }

    @RepeatedTest(3)
    void testSetSpeedX() throws NoSuchFieldException, IllegalAccessException {
        Field m_SpeedX = Ball.class.getDeclaredField("m_SpeedX");
        m_SpeedX.setAccessible(true);
        int speedX = rnd.nextInt();
        ball.setSpeedX(speedX);
        assertEquals(speedX, m_SpeedX.get(ball));
    }

    @RepeatedTest(3)
    void testSetSpeedY() throws NoSuchFieldException, IllegalAccessException {
        Field m_SpeedY = Ball.class.getDeclaredField("m_SpeedY");
        m_SpeedY.setAccessible(true);
        int speedY = rnd.nextInt();
        ball.setSpeedY(speedY);
        assertEquals(speedY, m_SpeedY.get(ball));
    }

    @Test
    void testReverseX() {
        int speedX = ball.getSpeedX();
        ball.reverseX();
        assertEquals(-speedX, ball.getSpeedX());
        ball.reverseX();
        assertEquals(speedX, ball.getSpeedX());
    }

    @Test
    void testReverseY() {
        int speedY = ball.getSpeedY();
        ball.reverseY();
        assertEquals(-speedY, ball.getSpeedY());
        ball.reverseY();
        assertEquals(speedY, ball.getSpeedY());
    }

    @RepeatedTest(10)
    void testResetSpeed() {
        ball.resetSpeed();
        int speedX = ball.getSpeedX();
        int SPEED_X_UPPER_BOUND = 2;
        int SPEED_X_LOWER_BOUND = -2;

        assertTrue(speedX != 0 && speedX >= SPEED_X_LOWER_BOUND
                       && speedX <= SPEED_X_UPPER_BOUND);

        int speedY = ball.getSpeedY();
        int SPEED_Y_LOWER_BOUND = -2;
        assertTrue(speedY <0 && speedY >= SPEED_Y_LOWER_BOUND);
    }

}