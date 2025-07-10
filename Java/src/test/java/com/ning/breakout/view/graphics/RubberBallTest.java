package com.ning.breakout.view.graphics;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RubberBallTest {

    private final int RADIUS = 5;

    private final Random rnd = new Random();


    @RepeatedTest(5)
    void testMove() {
        Point2D center = new Point2D(rnd.nextInt(), rnd.nextInt());
        RubberBall ball = new RubberBall(center, new Group());
        ball.resetSpeed();
        int speedX = ball.getSpeedX();
        int speedY = ball.getSpeedY();
        ball.move();

        //Center point
        assertEquals(center.getX() + speedX, ball.getCenterPoint().getX());
        assertEquals(center.getY() + speedY, ball.getCenterPoint().getY());

        //Up point
        assertEquals(ball.getCenterPoint().getX(), ball.getUpPoint().getX());
        assertEquals(
            ball.getCenterPoint().getY() - RADIUS, ball.getUpPoint().getY());

        //Down point
        assertEquals(ball.getCenterPoint().getX(), ball.getDownPoint().getX());
        assertEquals(
            ball.getCenterPoint().getY() + RADIUS, ball.getDownPoint().getY());

        //Left point
        assertEquals(
            ball.getCenterPoint().getX() - RADIUS, ball.getLeftPoint().getX());
        assertEquals(ball.getCenterPoint().getY(), ball.getLeftPoint().getY());

        //Right point
        assertEquals(
            ball.getCenterPoint().getX() + RADIUS, ball.getRightPoint().getX());
        assertEquals(ball.getCenterPoint().getY(), ball.getRightPoint().getY());

        //The layout of the view of the rubber ball
        assertEquals(ball.getLeftPoint().getX(), ball.getBallView().getX());
        assertEquals(ball.getUpPoint().getY(), ball.getBallView().getY());
    }

    @RepeatedTest(5)
    void testMoveTo() {
        Point2D center = new Point2D(rnd.nextInt(), rnd.nextInt());
        RubberBall ball = new RubberBall(center, new Group());
        Point2D newPos = new Point2D(rnd.nextInt(), rnd.nextInt());
        ball.moveTo(newPos);

        //Center point
        assertEquals(newPos.getX(), ball.getCenterPoint().getX());
        assertEquals(newPos.getY(), ball.getCenterPoint().getY());

        //Up point
        assertEquals(ball.getCenterPoint().getX(), ball.getUpPoint().getX());
        assertEquals(
            ball.getCenterPoint().getY() - RADIUS, ball.getUpPoint().getY());

        //Down point
        assertEquals(ball.getCenterPoint().getX(), ball.getDownPoint().getX());
        assertEquals(
            ball.getCenterPoint().getY() + RADIUS, ball.getDownPoint().getY());

        //Left point
        assertEquals(
            ball.getCenterPoint().getX() - RADIUS, ball.getLeftPoint().getX());
        assertEquals(ball.getCenterPoint().getY(), ball.getLeftPoint().getY());

        //Right point
        assertEquals(
            ball.getCenterPoint().getX() + RADIUS, ball.getRightPoint().getX());
        assertEquals(ball.getCenterPoint().getY(), ball.getRightPoint().getY());

        //The layout of the view of the rubber ball
        assertEquals(ball.getLeftPoint().getX(), ball.getBallView().getX());
        assertEquals(ball.getUpPoint().getY(), ball.getBallView().getY());
    }

}