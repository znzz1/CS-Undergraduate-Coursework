package com.ning.breakout.view.graphics;

import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {

    private CementBrick brick;


    @BeforeEach
    void initialize() {
        brick = new CementBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());
    }


    @Test
    void testSetImpactOnCementBrickHasNotBeenImpacted() {
        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(1, brick.getContainer().getChildren().size());

        brick.setImpact(new Point2D(0, 0), 0);

        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(2, brick.getContainer().getChildren().size());
    }

    @Test
    void testSetImpactOnCementBrickAlreadyBeenImpactedButNotBroken() {
        brick.setImpact(new Point2D(0, 0), 0);
        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(2, brick.getContainer().getChildren().size());

        brick.setImpact(new Point2D(0, 0), 0);

        assertTrue(brick.isBroken());
        assertEquals(brick.getFullStrength(), brick.getScore());
        assertEquals(0, brick.getContainer().getChildren().size());
    }

    @Test
    void testRepairCementBrickHasNotBeenImpacted() {
        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(1, brick.getContainer().getChildren().size());

        brick.repair();

        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(1, brick.getContainer().getChildren().size());
    }

    @Test
    void testRepairCementBrickAlreadyBeenImpactedButNotBroken() {
        brick.setImpact(new Point2D(0, 0), 0);
        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(2, brick.getContainer().getChildren().size());

        brick.repair();

        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(1, brick.getContainer().getChildren().size());
        assertEquals(brick.getBrickView(),
            brick.getContainer().getChildren().get(0));
    }

    @Test
    void testRepairBrokenCementBrick() {
        brick.setImpact(new Point2D(0, 0), 0);
        brick.setImpact(new Point2D(0, 0), 0);

        assertTrue(brick.isBroken());
        assertEquals(brick.getFullStrength(), brick.getScore());
        assertEquals(0, brick.getContainer().getChildren().size());

        brick.repair();

        assertFalse(brick.isBroken());
        assertEquals(0, brick.getScore());
        assertEquals(1, brick.getContainer().getChildren().size());
        assertEquals(brick.getBrickView(),
            brick.getContainer().getChildren().get(0));
    }

}