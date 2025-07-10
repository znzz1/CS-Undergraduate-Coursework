package com.ning.breakout.model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PaddleModelTest {

    private static final Random rnd = new Random();

    private static PaddleModel paddle;


    @BeforeAll
    static void init() {
        paddle = new PaddleModel(new Point2D(rnd.nextInt(), rnd.nextInt()),
            rnd.nextInt(), rnd.nextInt());
    }


    @RepeatedTest(3)
    void testGetWidth() throws NoSuchFieldException, IllegalAccessException {
        Field m_Width = PaddleModel.class.getDeclaredField("m_Width");
        m_Width.setAccessible(true);

        int width = rnd.nextInt();
        m_Width.set(paddle, width);
        assertEquals(width, paddle.getWidth());
    }

    @RepeatedTest(3)
    void testGetHeight() throws NoSuchFieldException, IllegalAccessException {
        Field m_Height = PaddleModel.class.getDeclaredField("m_Height");
        m_Height.setAccessible(true);

        int height = rnd.nextInt();
        m_Height.set(paddle, height);
        assertEquals(height, paddle.getHeight());
    }

    @RepeatedTest(3)
    void testGetBallPoint() throws NoSuchFieldException, IllegalAccessException{
        Field m_BallPoint = PaddleModel.class.getDeclaredField("m_BallPoint");
        m_BallPoint.setAccessible(true);

        Point2D ballPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_BallPoint.set(paddle, ballPoint);
        assertEquals(ballPoint, paddle.getBallPoint());
    }

    @RepeatedTest(3)
    void testSetBallPoint() throws NoSuchFieldException, IllegalAccessException{
        Field m_BallPoint = PaddleModel.class.getDeclaredField("m_BallPoint");
        m_BallPoint.setAccessible(true);

        Point2D ballPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        paddle.setBallPoint(ballPoint);
        assertEquals(ballPoint, m_BallPoint.get(paddle));
    }

}