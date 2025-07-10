package com.ning.breakout.model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrickModelTest {

    private static final Random rnd = new Random();

    private static BrickModel brick;


    @BeforeAll
    static void init() {
        brick = new BrickModel(new Point2D(rnd.nextInt(), rnd.nextInt()),
            rnd.nextInt(), rnd.nextInt());
    }

    @RepeatedTest(3)
    void testGetWidth() throws NoSuchFieldException, IllegalAccessException {
        Field m_Width = BrickModel.class.getDeclaredField("m_Width");
        m_Width.setAccessible(true);

        int width = rnd.nextInt();
        m_Width.set(brick, width);
        assertEquals(width, brick.getWidth());
    }

    @RepeatedTest(3)
    void testGetHeight() throws NoSuchFieldException, IllegalAccessException {
        Field m_Height = BrickModel.class.getDeclaredField("m_Height");
        m_Height.setAccessible(true);

        int height = rnd.nextInt();
        m_Height.set(brick, height);
        assertEquals(height, brick.getHeight());
    }

    @RepeatedTest(3)
    void testGetStartPoint() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_StartPoint = BrickModel.class.getDeclaredField("m_StartPoint");
        m_StartPoint.setAccessible(true);

        Point2D startPoint = new Point2D(rnd.nextInt(), rnd.nextInt());
        m_StartPoint.set(brick, startPoint);
        assertEquals(startPoint, brick.getStartPoint());
    }
}