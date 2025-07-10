package com.ning.breakout.model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BallModelTest {

    private static final Random rnd = new Random();

    private static BallModel ball;


    @BeforeAll
    static void init() {
        ball = new BallModel(new Point2D(rnd.nextInt(), rnd.nextInt()),
            rnd.nextInt());
    }


    @RepeatedTest(3)
    void testGetCenterPoint() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_CenterPoint = BallModel.class.getDeclaredField("m_CenterPoint");
        m_CenterPoint.setAccessible(true);

        Point2D centerPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_CenterPoint.set(ball, centerPoint);

        assertEquals(centerPoint, ball.getCenterPoint());
    }

    @RepeatedTest(3)
    void testGetUpPoint() throws NoSuchFieldException, IllegalAccessException {
        Field m_UpPoint = BallModel.class.getDeclaredField("m_UpPoint");
        m_UpPoint.setAccessible(true);

        Point2D upPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_UpPoint.set(ball, upPoint);

        assertEquals(upPoint, ball.getUpPoint());
    }

    @RepeatedTest(3)
    void testGetDownPoint() throws NoSuchFieldException, IllegalAccessException{
        Field m_DownPoint = BallModel.class.getDeclaredField("m_DownPoint");
        m_DownPoint.setAccessible(true);

        Point2D downPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_DownPoint.set(ball, downPoint);

        assertEquals(downPoint, ball.getDownPoint());
    }

    @RepeatedTest(3)
    void testGetLeftPoint() throws NoSuchFieldException, IllegalAccessException{
        Field m_LeftPoint = BallModel.class.getDeclaredField("m_LeftPoint");
        m_LeftPoint.setAccessible(true);

        Point2D leftPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_LeftPoint.set(ball, leftPoint);

        assertEquals(leftPoint, ball.getLeftPoint());
    }

    @RepeatedTest(3)
    void testGetRightPoint() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_RightPoint = BallModel.class.getDeclaredField("m_RightPoint");
        m_RightPoint.setAccessible(true);

        Point2D rightPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_RightPoint.set(ball, rightPoint);

        assertEquals(rightPoint, ball.getRightPoint());
    }

    @RepeatedTest(3)
    void testSetCenterPoint() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_CenterPoint = BallModel.class.getDeclaredField("m_CenterPoint");
        m_CenterPoint.setAccessible(true);

        Point2D centerPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        ball.setCenterPoint(centerPoint);

        assertEquals(centerPoint, m_CenterPoint.get(ball));
    }

    @Test
    void testUpdatePoints() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_Radius = BallModel.class.getDeclaredField("m_Radius");
        m_Radius.setAccessible(true);
        int radius = (int) m_Radius.get(ball);
        double x = ball.getCenterPoint().getX();
        double y = ball.getCenterPoint().getY();

        ball.updatePoints();

        assertEquals(x, ball.getUpPoint().getX());
        assertEquals(y - radius, ball.getUpPoint().getY());

        assertEquals(x, ball.getDownPoint().getX());
        assertEquals(y + radius, ball.getDownPoint().getY());

        assertEquals(x - radius, ball.getLeftPoint().getX());
        assertEquals(y, ball.getLeftPoint().getY());

        assertEquals(x + radius, ball.getRightPoint().getX());
        assertEquals(y, ball.getRightPoint().getY());
    }

}