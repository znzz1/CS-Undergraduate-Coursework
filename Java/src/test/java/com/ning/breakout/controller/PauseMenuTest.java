package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.GameSoundFactory;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.lang.reflect.Field;

import static java.lang.Thread.sleep;

@ExtendWith(ApplicationExtension.class)
class PauseMenuTest {

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
        startGame.getGameBoard().showPauseMenu();
    }


    @Test
    void testContinueToPlay(FxRobot robot) throws InterruptedException {
        int level = startGame.getGameBoard().getManager().getLevel();
        robot.clickOn("#m_ContinueButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Level: " + level);
        sleep(1000);
    }

    @Test
    void testBackToMain(FxRobot robot) throws InterruptedException {
        robot.clickOn("#m_BackToMainButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Breakout Game");
        sleep(1000);
    }

}