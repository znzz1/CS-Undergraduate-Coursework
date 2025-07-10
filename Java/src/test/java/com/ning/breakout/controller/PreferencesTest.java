package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.GameSoundFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class PreferencesTest {

    private final int DEFAULT_THEME = 0;
    private final int BW_THEME = 1;
    private final int COLORFUL_THEME = 2;

    StartGame startGame = new StartGame();

    @Start
    private void start(Stage stage) throws
        NoSuchFieldException, IllegalAccessException {
        startGame.start(stage);
        Field factory = StartGame.class.getDeclaredField("m_GameSoundFactory");
        factory.setAccessible(true);
        ((GameSoundFactory) factory.get(startGame)).getWelcomeMusic().stop();
        startGame.switchToPreferences();
    }


    @Test
    void testChooseDefaultThemeByClickingImage(FxRobot robot) {
        robot.clickOn("#m_DefaultTheme");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Default theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
                queryComboBox()).hasSelectedItem("Default");
    }

    @Test
    void testChooseBWThemeByClickingImage(FxRobot robot) {
        robot.clickOn("#m_BwTheme");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Black & White theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
            queryComboBox()).hasSelectedItem("Black & White");
    }

    @Test
    void testChooseColorfulThemeByClickingImage(FxRobot robot) {
        robot.clickOn("#m_ColorfulTheme");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Colorful theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
            queryComboBox()).hasSelectedItem("Colorful");
    }

    @Test
    void testChooseDefaultThemeBySelectingItemsInComboBox(FxRobot robot) {
        ComboBox<String> comboBox = robot.lookup("#m_ThemeChoiceBox").
            queryComboBox();
        ObservableList<String> choices = comboBox.getItems();
        robot.clickOn("#m_ThemeChoiceBox");
        robot.clickOn(choices.get(DEFAULT_THEME));
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Default theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
            queryComboBox()).hasSelectedItem("Default");
    }

    @Test
    void testChooseBWThemeBySelectingItemsInComboBox(FxRobot robot) {
        ComboBox<String> comboBox = robot.lookup("#m_ThemeChoiceBox").
            queryComboBox();
        ObservableList<String> choices = comboBox.getItems();
        robot.clickOn("#m_ThemeChoiceBox");
        robot.clickOn(choices.get(BW_THEME));
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Black & White theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
            queryComboBox()).hasSelectedItem("Black & White");
    }

    @Test
    void testChooseColorfulThemeBySelectingItemsInComboBox(FxRobot robot) {
        ComboBox<String> comboBox = robot.lookup("#m_ThemeChoiceBox").
            queryComboBox();
        ObservableList<String> choices = comboBox.getItems();
        robot.clickOn("#m_ThemeChoiceBox");
        robot.clickOn(choices.get(COLORFUL_THEME));
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Colorful theme chosen, save your choice now!");
        Assertions.assertThat(robot.lookup("#m_ThemeChoiceBox").
            queryComboBox()).hasSelectedItem("Colorful");
    }

    @Test
    void testSaveChoice(FxRobot robot)
        throws NoSuchFieldException, IllegalAccessException {
        Field preference = StartGame.class.getDeclaredField("m_Preference");
        preference.setAccessible(true);

        robot.clickOn("#m_DefaultTheme");
        robot.clickOn("#m_SaveButton");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Choice saved, play again now!");
        assertEquals(DEFAULT_THEME, preference.get(startGame));

        robot.clickOn("#m_BwTheme");
        robot.clickOn("#m_SaveButton");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Choice saved, play again now!");
        assertEquals(BW_THEME, preference.get(startGame));

        robot.clickOn("#m_ColorfulTheme");
        robot.clickOn("#m_SaveButton");
        Assertions.assertThat(robot.lookup("#m_PromptLabel").queryLabeled()).
            hasText("Choice saved, play again now!");
        assertEquals(COLORFUL_THEME, preference.get(startGame));
    }

}