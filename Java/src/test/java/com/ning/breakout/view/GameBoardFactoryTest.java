package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import com.ning.breakout.model.BrickModel;
import com.ning.breakout.view.graphics.CementBrick;
import com.ning.breakout.view.graphics.ClayBrick;
import com.ning.breakout.view.graphics.GoldenBrick;
import com.ning.breakout.view.graphics.SteelBrick;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameBoardFactoryTest {

    private final int DEFAULT_THEME = 0;
    private final int BLACK_AND_WHITE_THEME = 1;
    private final int COLORFUL_THEME = 2;

    private static GameBoardFactory factory;


    @BeforeAll
    static void init() {
        JFXPanel panel = new JFXPanel();
        factory = new GameBoardFactory();
    }


    @RepeatedTest(3)
    void testSetPreference() throws
        NoSuchFieldException, IllegalAccessException {
        Field m_Preference =
            GameBoardFactory.class.getDeclaredField("m_Preference");
        m_Preference.setAccessible(true);
        int preference = new Random().nextInt();
        factory.setPreference(preference);
        assertEquals(preference, m_Preference.get(factory));
    }

    @Test
    void testMakeDefaultBall() {
        factory.setPreference(DEFAULT_THEME);
        String ball = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/rubber-ball.jpg")).toExternalForm();
        assertEquals(ball, factory.makeBallView().getUrl());
    }

    @Test
    void testMakeBlackAndWhiteBall() {
        factory.setPreference(BLACK_AND_WHITE_THEME);
        String ball = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/rubber-ball.jpg")).toExternalForm();
        assertEquals(ball, factory.makeBallView().getUrl());
    }

    @Test
    void testMakeColorfulBall() {
        factory.setPreference(COLORFUL_THEME);
        String ball = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/rubber-ball.jpg")).toExternalForm();
        assertEquals(ball, factory.makeBallView().getUrl());
    }

    @Test
    void testMakeDefaultPaddle() {
        factory.setPreference(DEFAULT_THEME);
        String paddle = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/rubber-paddle.jpg")).toExternalForm();
        assertEquals(paddle, factory.makePaddleView().getUrl());
    }

    @Test
    void testMakeBlackAndWhitePaddle() {
        factory.setPreference(BLACK_AND_WHITE_THEME);
        String paddle = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/rubber-paddle.jpg")).toExternalForm();
        assertEquals(paddle, factory.makePaddleView().getUrl());
    }

    @Test
    void testMakeColorfulPaddle() {
        factory.setPreference(COLORFUL_THEME);
        String paddle = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/rubber-paddle.jpg")).toExternalForm();
        assertEquals(paddle, factory.makePaddleView().getUrl());
    }

    @Test
    void testMakeDefaultBackground() {
        factory.setPreference(DEFAULT_THEME);
        String background = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/background.jpg")).toExternalForm();
        assertEquals(background, factory.makeBackground().getUrl());
    }

    @Test
    void testMakeBlackAndWhiteBackground() {
        factory.setPreference(BLACK_AND_WHITE_THEME);
        String background = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/background.jpg")).toExternalForm();
        assertEquals(background, factory.makeBackground().getUrl());
    }

    @Test
    void testMakeColorfulBackground() {
        factory.setPreference(COLORFUL_THEME);
        String background = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/background.jpg")).toExternalForm();
        assertEquals(background, factory.makeBackground().getUrl());
    }

    @Test
    void testMakeClayBrick() {
        ClayBrick brick = new ClayBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        String clayBrick;

        factory.setPreference(DEFAULT_THEME);
        clayBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/clay-brick.jpg")).toExternalForm();
        assertEquals(clayBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(BLACK_AND_WHITE_THEME);
        clayBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/clay-brick.jpg")).toExternalForm();
        assertEquals(clayBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(COLORFUL_THEME);
        clayBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/clay-brick.jpg")).toExternalForm();
        assertEquals(clayBrick, factory.makeBrickView(brick).getUrl());
    }

    @Test
    void testMakeCementBrick() {
        CementBrick brick = new CementBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        String cementBrick;

        factory.setPreference(DEFAULT_THEME);
        cementBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/cement-brick.jpg")).toExternalForm();
        assertEquals(cementBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(BLACK_AND_WHITE_THEME);
        cementBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/cement-brick.jpg")).toExternalForm();
        assertEquals(cementBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(COLORFUL_THEME);
        cementBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/cement-brick.jpg")).toExternalForm();
        assertEquals(cementBrick, factory.makeBrickView(brick).getUrl());
    }

    @Test
    void testMakeSteelBrick() {
        SteelBrick brick = new SteelBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        String steelBrick;

        factory.setPreference(DEFAULT_THEME);
        steelBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/steel-brick.jpg")).toExternalForm();
        assertEquals(steelBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(BLACK_AND_WHITE_THEME);
        steelBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/steel-brick.jpg")).toExternalForm();
        assertEquals(steelBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(COLORFUL_THEME);
        steelBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/steel-brick.jpg")).toExternalForm();
        assertEquals(steelBrick, factory.makeBrickView(brick).getUrl());
    }

    @Test
    void testMakeGoldenBrick() {
        GoldenBrick brick = new GoldenBrick(
            new BrickModel(new Point2D(0, 0), 0, 0), new Group());

        String goldenBrick;

        factory.setPreference(DEFAULT_THEME);
        goldenBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/default/golden-brick.jpg")).toExternalForm();
        assertEquals(goldenBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(BLACK_AND_WHITE_THEME);
        goldenBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/black&white/golden-brick.jpg")).toExternalForm();
        assertEquals(goldenBrick, factory.makeBrickView(brick).getUrl());

        factory.setPreference(COLORFUL_THEME);
        goldenBrick = Objects.requireNonNull(StartGame.class.getResource(
            "image/colorful/golden-brick.jpg")).toExternalForm();
        assertEquals(goldenBrick, factory.makeBrickView(brick).getUrl());
    }

}