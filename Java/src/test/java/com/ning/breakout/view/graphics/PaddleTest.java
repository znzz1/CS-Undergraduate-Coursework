package com.ning.breakout.view.graphics;

import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaddleTest {

    private static Paddle paddle;

    private final int MOVE_AMOUNT = 4;


    @BeforeAll
    static void init() {
        paddle = Mockito.mock(Paddle.class, Mockito.CALLS_REAL_METHODS);
    }


    @Test
    void testGetPaddleView() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_PaddleView = Paddle.class.getDeclaredField("m_PaddleView");
        m_PaddleView.setAccessible(true);
        ImageView paddleView = new ImageView();
        m_PaddleView.set(paddle, paddleView);
        assertEquals(paddleView, paddle.getPaddleView());
    }

    @Test
    void testMoveLeft() throws NoSuchFieldException, IllegalAccessException {
        Field m_MoveAmount = Paddle.class.getDeclaredField("m_MoveAmount");
        m_MoveAmount.setAccessible(true);
        paddle.moveLeft();
        assertEquals(-MOVE_AMOUNT, m_MoveAmount.get(paddle));
    }

    @Test
    void testMoveRight() throws NoSuchFieldException, IllegalAccessException {
        Field m_MoveAmount = Paddle.class.getDeclaredField("m_MoveAmount");
        m_MoveAmount.setAccessible(true);
        paddle.moveRight();
        assertEquals(MOVE_AMOUNT, m_MoveAmount.get(paddle));
    }

    @Test
    void testStop() throws NoSuchFieldException, IllegalAccessException {
        Field m_MoveAmount = Paddle.class.getDeclaredField("m_MoveAmount");
        m_MoveAmount.setAccessible(true);
        paddle.stop();
        assertEquals(0, m_MoveAmount.get(paddle));
    }

}