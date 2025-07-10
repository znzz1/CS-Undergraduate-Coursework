package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.GameSoundFactory;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.lang.reflect.Field;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class DebugConsoleTest {

    private final int BOUND = 4;

    StartGame startGame = new StartGame();

    @Start
    private void start(Stage stage) throws
        NoSuchFieldException, IllegalAccessException {
        startGame.start(stage);
        Field factory = StartGame.class.getDeclaredField("m_GameSoundFactory");
        factory.setAccessible(true);
        ((GameSoundFactory) factory.get(startGame)).getWelcomeMusic().stop();
        startGame.resetGameBoard();
        startGame.switchToGameBoard();
        startGame.getGameBoard().showDebugConsole();
    }


    @Test
    void testSliderOfSpeedX(FxRobot robot) throws InterruptedException {
        Slider slider = robot.lookup("#m_SpeedXSlider").query();
        int initSpeed =
            startGame.getGameBoard().getManager().getBall().getSpeedX();
        assertEquals(initSpeed, slider.getValue());

        slider.setValue(new Random().nextInt(-4, 5));
        int speed =
            startGame.getGameBoard().getManager().getBall().getSpeedX();
        assertEquals(speed, slider.getValue());

        sleep(1500);
    }

    @Test
    void testSliderOfSpeedY(FxRobot robot) throws InterruptedException {
        Slider slider = robot.lookup("#m_SpeedYSlider").query();
        int initSpeed =
            startGame.getGameBoard().getManager().getBall().getSpeedY();
        assertEquals(initSpeed, slider.getValue());

        slider.setValue(new Random().nextInt(-4, 5));
        int speed =
            startGame.getGameBoard().getManager().getBall().getSpeedY();
        assertEquals(speed, slider.getValue());

        sleep(1500);
    }

}