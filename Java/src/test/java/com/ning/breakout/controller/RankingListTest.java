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

@ExtendWith(ApplicationExtension.class)
class RankingListTest {

    StartGame startGame = new StartGame();

    @Start
    private void start(Stage stage) throws
        NoSuchFieldException, IllegalAccessException {
        startGame.start(stage);
        Field factory = StartGame.class.getDeclaredField("m_GameSoundFactory");
        factory.setAccessible(true);
        ((GameSoundFactory) factory.get(startGame)).getWelcomeMusic().stop();
        startGame.resetGameBoard();
        startGame.switchToRankingList();
    }


    @Test
    void testPlayAgain(FxRobot robot) {
        robot.clickOn("#m_PlayAgainButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Level: 0");
    }

    @Test
    void testBackToMain(FxRobot robot) {
        robot.clickOn("#m_BackToMainButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Breakout Game");
    }

}