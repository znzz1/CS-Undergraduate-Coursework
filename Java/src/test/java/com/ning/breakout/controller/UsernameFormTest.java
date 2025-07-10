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
class UsernameFormTest {

    StartGame startGame = new StartGame();

    @Start
    private void start(Stage stage) throws
        NoSuchFieldException, IllegalAccessException {
        startGame.start(stage);
        Field factory = StartGame.class.getDeclaredField("m_GameSoundFactory");
        factory.setAccessible(true);
        ((GameSoundFactory) factory.get(startGame)).getWelcomeMusic().stop();
        startGame.switchToUsernameForm();
    }


    @Test
    void testCancelUpload(FxRobot robot) {
        robot.clickOn("#m_CancelButton");
        Assertions.assertThat(robot.lookup("#m_ScoreLabel").queryLabeled()).
            hasText("See your score!");
    }

    @Test
    void testSeeRankingList(FxRobot robot) {
        robot.clickOn("#m_RankingListButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("TOP 5 IN HISTORY");
    }

}