package com.ning.breakout.view.graphics;

import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.Test;

import static com.ning.breakout.view.graphics.Brick.*;
import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {

    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;

    @Test
    void testImpactClayBrick() {
        ClayBrick brick = new ClayBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        assertFalse(brick.isBroken());

        brick.impact();
        assertTrue(brick.isBroken());
    }

    @Test
    void testSetImpactOnClayBrick() {
        ClayBrick brick = new ClayBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        assertNotEquals(brick.getFullStrength(), brick.getScore());
        assertFalse(brick.isBroken());
        assertTrue(brick.getContainer().getChildren().contains(
            brick.getBrickView()));

        brick.setImpact(new Point2D(0, 0), 0);

        assertEquals(brick.getFullStrength(), brick.getScore());
        assertTrue(brick.isBroken());
        assertFalse(brick.getContainer().getChildren().contains(
            brick.getBrickView()));
    }

    @Test
    void testFindImpactForClayBrickNotBroken() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        Point2D leftImpactTest = new Point2D(49, 60);
        Point2D rightImpactTest = new Point2D(111, 60);
        Point2D upImpactTest = new Point2D(80, 49);
        Point2D downImpactTest = new Point2D(80, 71);
        Point2D noImpactTest = new Point2D(0, 0);

        RubberBall ball = new RubberBall(noImpactTest, new Group());

        ball.moveTo(leftImpactTest);
        ball.setSpeedX(1);
        assertEquals(LEFT_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(rightImpactTest);
        ball.setSpeedX(-1);
        assertEquals(RIGHT_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(upImpactTest);
        ball.setSpeedY(1);
        assertEquals(UP_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(downImpactTest);
        ball.setSpeedY(-1);
        assertEquals(DOWN_IMPACT, testBrick.findImpact(ball));
    }

    @Test
    void testSpecialLeftImpact() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        //Points at the left-top and left-bottom corner
        Point2D downPointInsideBrick = new Point2D(51, 46);
        Point2D upPointInsideBrick = new Point2D(51, 74);

        RubberBall ball = new RubberBall(new Point2D(0, 0), new Group());

        ball.moveTo(downPointInsideBrick);
        ball.setSpeedX(POSITIVE);
        ball.setSpeedY(NEGATIVE);
        assertEquals(LEFT_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(upPointInsideBrick);
        ball.setSpeedX(POSITIVE);
        ball.setSpeedY(POSITIVE);
        assertEquals(LEFT_IMPACT, testBrick.findImpact(ball));
    }

    @Test
    void testSpecialRightImpact() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        //Points at the right-top and right-bottom corner
        Point2D downPointInsideBrick = new Point2D(109, 46);
        Point2D upPointInsideBrick = new Point2D(109, 74);

        RubberBall ball = new RubberBall(new Point2D(0, 0), new Group());

        ball.moveTo(downPointInsideBrick);
        ball.setSpeedX(NEGATIVE);
        ball.setSpeedY(NEGATIVE);
        assertEquals(RIGHT_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(upPointInsideBrick);
        ball.setSpeedX(NEGATIVE);
        ball.setSpeedY(POSITIVE);
        assertEquals(RIGHT_IMPACT, testBrick.findImpact(ball));

    }

    @Test
    void testSpecialUpImpact() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        //Points at the left-top and right-top corner
        Point2D leftPointInsideBrick = new Point2D(114, 51);
        Point2D rightPointInsideBrick = new Point2D(46, 51);

        RubberBall ball = new RubberBall(new Point2D(0, 0), new Group());

        ball.moveTo(leftPointInsideBrick);
        ball.setSpeedX(POSITIVE);
        ball.setSpeedY(POSITIVE);
        assertEquals(UP_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(rightPointInsideBrick);
        ball.setSpeedX(NEGATIVE);
        ball.setSpeedY(POSITIVE);
        assertEquals(UP_IMPACT, testBrick.findImpact(ball));
    }

    @Test
    void testSpecialDownImpact() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        //Points at the left-bottom and right-bottom corner
        Point2D leftPointInsideBrick = new Point2D(114, 69);
        Point2D rightPointInsideBrick = new Point2D(46, 69);

        RubberBall ball = new RubberBall(new Point2D(0, 0), new Group());

        ball.moveTo(leftPointInsideBrick);
        ball.setSpeedX(POSITIVE);
        ball.setSpeedY(NEGATIVE);
        assertEquals(DOWN_IMPACT, testBrick.findImpact(ball));

        ball.moveTo(rightPointInsideBrick);
        ball.setSpeedX(NEGATIVE);
        ball.setSpeedY(NEGATIVE);
        assertEquals(DOWN_IMPACT, testBrick.findImpact(ball));

    }

    @Test
    void testFindImpactForBrokenClayBrick() {
        ClayBrick testBrick = new ClayBrick(
            new BrickModel(new Point2D(50, 50), 60, 20), new Group());

        Point2D leftImpactTest = new Point2D(49, 60);
        Point2D rightImpactTest = new Point2D(111, 60);
        Point2D upImpactTest = new Point2D(80, 49);
        Point2D downImpactTest = new Point2D(80, 71);

        RubberBall ball = new RubberBall(new Point2D(0, 0), new Group());
        testBrick.impact();

        ball.moveTo(leftImpactTest);
        assertEquals(0, testBrick.findImpact(ball));

        ball.moveTo(rightImpactTest);
        assertEquals(0, testBrick.findImpact(ball));

        ball.moveTo(upImpactTest);
        assertEquals(0, testBrick.findImpact(ball));

        ball.moveTo(downImpactTest);
        assertEquals(0, testBrick.findImpact(ball));
    }

    @Test
    void testRepairClayBrickNotBroken() {
        ClayBrick brick = new ClayBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        assertEquals(0, brick.getScore());
        assertFalse(brick.isBroken());
        assertTrue(brick.getContainer().getChildren().contains(
            brick.getBrickView()));

        brick.repair();

        assertEquals(0, brick.getScore());
        assertFalse(brick.isBroken());
        assertTrue(brick.getContainer().getChildren().contains(
            brick.getBrickView()));
    }

    @Test
    void testRepairBrokenClayBrick() {
        ClayBrick brick = new ClayBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        brick.setImpact(new Point2D(0, 0), 0);

        assertEquals(brick.getFullStrength(), brick.getScore());
        assertTrue(brick.isBroken());
        assertFalse(brick.getContainer().getChildren().contains(
            brick.getBrickView()));

        brick.repair();

        assertEquals(0, brick.getScore());
        assertFalse(brick.isBroken());
        assertTrue(brick.getContainer().getChildren().contains(
            brick.getBrickView()));
    }

}