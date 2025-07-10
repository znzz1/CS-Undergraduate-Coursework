package com.ning.breakout.view.graphics;

import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {

    private SteelBrick brick;


    @BeforeEach
    void init() {
        brick = new SteelBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());
    }


    @RepeatedTest(10)
    void testImpactSteelBrick() {
        assertFalse(brick.isBroken());

        int count = 0;
        while(!brick.isBroken()) {
            brick.impact();
            count++;
        }

        System.out.println("Number of invalid impacts: " + count);
        assertTrue(brick.isBroken());
    }

    @Test
    void setImpactOnSteelBrick() {
        int STEEL_BRICK_SCORE = 3;

        assertNotEquals(STEEL_BRICK_SCORE, brick.getScore());
        assertFalse(brick.isBroken());
        assertTrue(brick.getContainer().getChildren().contains(
            brick.getBrickView()));

        while(!brick.isBroken())
            brick.setImpact(new Point2D(0, 0), 0);

        assertEquals(STEEL_BRICK_SCORE, brick.getScore());
        assertTrue(brick.isBroken());
        assertFalse(brick.getContainer().getChildren().contains(
            brick.getBrickView()));
    }
}