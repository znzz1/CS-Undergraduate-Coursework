package com.ning.breakout.view.graphics;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {

    private static Brick brick;

    private final Random rnd = new Random();


    @BeforeAll
    static void init() {
        brick = Mockito.mock(Brick.class, Mockito.CALLS_REAL_METHODS);
    }


    @RepeatedTest(3)
    void testGetContainer() throws NoSuchFieldException, IllegalAccessException{
        Field m_Container = Brick.class.getDeclaredField("m_Container");
        m_Container.setAccessible(true);
        Group container = new Group();
        m_Container.set(brick, container);
        assertEquals(container, brick.getContainer());
    }

    @RepeatedTest(3)
    void testGetBrickView() throws NoSuchFieldException, IllegalAccessException{
        Field m_BrickView = Brick.class.getDeclaredField("m_BrickView");
        m_BrickView.setAccessible(true);
        ImageView brickView = new ImageView();
        m_BrickView.set(brick, brickView);
        assertEquals(brickView, brick.getBrickView());
    }

    @RepeatedTest(3)
    void testGetFullStrength() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_FullStrength = Brick.class.getDeclaredField("m_FullStrength");
        m_FullStrength.setAccessible(true);
        int fullStrength = rnd.nextInt();
        m_FullStrength.set(brick, fullStrength);
        assertEquals(fullStrength, brick.getFullStrength());
    }

    @RepeatedTest(3)
    void testIsBrickBroken() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_Broken = Brick.class.getDeclaredField("m_Broken");
        m_Broken.setAccessible(true);
        m_Broken.set(brick, true);
        assertTrue(brick.isBroken());
        m_Broken.set(brick, false);
        assertFalse(brick.isBroken());
    }

    @RepeatedTest(3)
    void testGetScore() throws NoSuchFieldException, IllegalAccessException {
        Field m_Score = Brick.class.getDeclaredField("m_Score");
        m_Score.setAccessible(true);
        int score = rnd.nextInt();
        m_Score.set(brick, score);
        assertEquals(score, brick.getScore());
    }

    @RepeatedTest(3)
    void testSetBroken() throws NoSuchFieldException, IllegalAccessException {
        Field m_Broken = Brick.class.getDeclaredField("m_Broken");
        m_Broken.setAccessible(true);
        brick.setBroken(true);
        assertEquals(true, m_Broken.get(brick));
        brick.setBroken(false);
        assertEquals(false, m_Broken.get(brick));
    }

    @RepeatedTest(3)
    void testSetScore() throws NoSuchFieldException, IllegalAccessException {
        Field m_Score = Brick.class.getDeclaredField("m_Score");
        m_Score.setAccessible(true);
        int score = rnd.nextInt();
        brick.setScore(score);
        assertEquals(score, m_Score.get(brick));
    }

}