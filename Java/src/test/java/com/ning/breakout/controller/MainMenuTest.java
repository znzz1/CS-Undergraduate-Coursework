package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static java.lang.Thread.sleep;

@ExtendWith(ApplicationExtension.class)
class MainMenuTest {

    StartGame startGame = new StartGame();

    @Start
    private void start(Stage stage) {
        startGame.start(stage);
        startGame.resetGameBoard();
    }


    @Test
    void testPlayGame(FxRobot robot) {
        robot.clickOn("#m_PlayButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Level: 0");
    }

    @Test
    void testSetPreference(FxRobot robot) {
        robot.clickOn("#m_PreferencesButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Preferences");
    }

    @Test
    void testOpenGuidance(FxRobot robot) {
        robot.clickOn("#m_GuidanceButton");
        Assertions.assertThat(robot.lookup(".label").queryLabeled()).
            hasText("Moves the paddle to left/right");
    }

    @Test
    void testMuteWelcomeMusic(FxRobot robot) throws InterruptedException {
        robot.clickOn("#m_MusicIcon");
        sleep(1000);
        robot.clickOn("#m_MusicIcon");
        sleep(1000);
    }

}