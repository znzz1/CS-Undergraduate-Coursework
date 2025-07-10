package com.ning.breakout.view.graphics;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RubberPaddleTest {

    private final double HALF_WIDTH = 75;
    private final Rectangle CONTAINER = new Rectangle(0, 0, 600, 450);
    private final int LEFT_BOUND_X = 75;
    private final int RIGHT_BOUND_X = 525;
    private final int DEF_X = 300;
    private final int DEF_Y = 430;

    private static final JFXPanel panel = new JFXPanel();

    @Test
    void testMoveOutOfBoundary() {
        RubberPaddle leftBoundTest = new RubberPaddle(
            new Point2D(LEFT_BOUND_X, DEF_Y), CONTAINER, new Group());
        RubberPaddle rightBoundTest = new RubberPaddle(
            new Point2D(RIGHT_BOUND_X, DEF_Y), CONTAINER, new Group());

        leftBoundTest.moveLeft();
        leftBoundTest.move();
        assertEquals(
            LEFT_BOUND_X - HALF_WIDTH, leftBoundTest.getPaddleView().getX());

        rightBoundTest.moveRight();
        rightBoundTest.move();
        assertEquals(
            RIGHT_BOUND_X - HALF_WIDTH, rightBoundTest.getPaddleView().getX());
    }

    @RepeatedTest(5)
    void testMoveWithinBoundary() {
        int MOVE_AMOUNT = 4;
        int layoutX = new Random().nextInt(
            LEFT_BOUND_X + MOVE_AMOUNT, RIGHT_BOUND_X - MOVE_AMOUNT + 1);
        RubberPaddle paddle = new RubberPaddle(
            new Point2D(layoutX, DEF_Y), CONTAINER, new Group());

        paddle.moveLeft();
        paddle.move();
        assertEquals(
            layoutX - MOVE_AMOUNT - HALF_WIDTH, paddle.getPaddleView().getX());

        paddle.moveRight();
        paddle.move();
        paddle.move();
        assertEquals(
            layoutX + MOVE_AMOUNT - HALF_WIDTH, paddle.getPaddleView().getX());
    }

    @Test
    void testMoveTo() {
        int layoutX = new Random().nextInt(LEFT_BOUND_X, RIGHT_BOUND_X + 1);
        Point2D point = new Point2D(layoutX, DEF_Y);
        RubberPaddle paddle = new RubberPaddle(
            new Point2D(0, DEF_Y), CONTAINER, new Group());
        paddle.moveTo(point);
        assertEquals(layoutX - HALF_WIDTH, paddle.getPaddleView().getX());
    }

    @Test
    void testImpactWhenImpactHappens() {
        RubberBall in = new RubberBall(new Point2D(DEF_X, DEF_Y), new Group());
        RubberPaddle paddle = new RubberPaddle(
            new Point2D(DEF_X, DEF_Y), CONTAINER, new Group());

        assertTrue(paddle.impact(in));
    }

    @Test
    void testImpactWhenImpactDoesNotHappen() {
        RubberBall out = new RubberBall(
            new Point2D(DEF_X, DEF_Y - 1), new Group());
        RubberPaddle paddle = new RubberPaddle(
            new Point2D(DEF_X, DEF_Y), CONTAINER, new Group());

        assertFalse(paddle.impact(out));
    }

}