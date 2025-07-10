package com.ning.breakout.controller;

import com.ning.breakout.StartGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.HashMap;

/**
 * The controller class of the preference scene, extends from the abstract class
 * NormalSceneController and defines the event handlers of the preference scene.
 *
 * @see com.ning.breakout.controller.NormalSceneController
 *
 * @author Ning ZHU
 */
public class PreferencesController extends NormalSceneController {

    private final int DEFAULT_THEME = 0;
    private final int BLACK_AND_WHITE_THEME = 1;
    private final int COLORFUL_THEME = 2;


    /**
     * key: the name of the color theme.
     * <br>
     * value: the corresponding number to indicate the preference.
     */
    private HashMap<String, Integer> m_HashMap;

    /**
     * The currently selected preference.
     */
    private Integer m_Preference;

    /**
     * The combo box used to choose theme.
     */
    @FXML
    private ComboBox<String> m_ThemeChoiceBox;

    /**
     * Used to display prompt messages
     */
    @FXML
    private Label m_PromptLabel;


    private HashMap<String, Integer> getHashMap() {
        return this.m_HashMap;
    }

    private Integer getPreference() {
        return this.m_Preference;
    }

    private ComboBox<String> getThemeChoiceBox() {
        return this.m_ThemeChoiceBox;
    }

    private Label getPromptLabel() {
        return this.m_PromptLabel;
    }


    private void setHashMap(HashMap<String, Integer> hashMap) {
        this.m_HashMap = hashMap;
    }

    private void setPreference(Integer preference) {
        this.m_Preference = preference;
    }


    /**
     * Initializes the hash table of the color themes and sets the items of the
     * combo box.
     */
    @FXML
    public void initialize() {
        ObservableList<String> themeOptions = FXCollections.observableArrayList(
            "Default", "Black & White", "Colorful");
        getThemeChoiceBox().setItems(themeOptions);
        //Build the hash map
        setHashMap(new HashMap<>());
        getHashMap().put("Default", DEFAULT_THEME);
        getHashMap().put("Black & White", BLACK_AND_WHITE_THEME);
        getHashMap().put("Colorful", COLORFUL_THEME);
    }

    /**
     * Passes selected preference to the Main class (StartGame).
     * <p>
     * The selected preference will be passed to Main class (StartGame) when the
     * save button clicked. Notes that if no item selected, user will receive an
     * alert.
     */
    @FXML
    public void saveChoice() {
        //User cannot save the preference before choosing theme.
        if (getPreference() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                "You have not chosen your color theme!");
            alert.showAndWait();
        } else {
            getStartGame().setPreference(getPreference());
            getPromptLabel().setText("Choice saved, play again now!");
        }
    }

    /**
     * Switches to the main menu when the back button clicked.
     *
     * @see StartGame#switchToMainMenu()
     */
    @FXML
    public void backToMain() {
        getStartGame().switchToMainMenu();
    }

    /**
     * Chooses the color theme of the game board when items in combo box
     * selected.
     */
    @FXML
    public void themeSelected() {
        String item = getThemeChoiceBox().getSelectionModel().getSelectedItem();
        setPreference(getHashMap().get(item));
        getPromptLabel().setText(item + " theme chosen, " +
                                     "save your choice now!");
    }

    /**
     * Chooses the default theme when the image of the default theme clicked.
     */
    @FXML
    public void chooseDefault() {
        getThemeChoiceBox().getSelectionModel().select(DEFAULT_THEME);
    }

    /**
     * Chooses the black and white theme when the image of black and white theme
     * clicked.
     */
    @FXML
    public void chooseBlackAndWhite() {
        getThemeChoiceBox().getSelectionModel().select(BLACK_AND_WHITE_THEME);
    }

    /**
     * Chooses the colorful theme when the image of colorful theme clicked.
     */
    @FXML
    public void chooseColorful() {
        getThemeChoiceBox().getSelectionModel().select(COLORFUL_THEME);
    }

}
