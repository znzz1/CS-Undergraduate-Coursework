package com.ning.breakout;

import com.ning.breakout.controller.*;
import com.ning.breakout.view.GameBoard;
import com.ning.breakout.view.GameSoundFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * StartGame is the Main class of the project which takes the responsibility of
 * initializing the game board, switching scenes, transferring data between
 * scenes and starting the program.
 *
 * @see Application
 * @see GameBoard
 * @see GameSoundFactory
 * @see KeyInputListener
 *
 * @author Ning ZHU - modified
 */
public class StartGame extends Application {

    private Scene m_Scene;
    private GameBoard m_GameBoard;
    private int m_Preference;

    private KeyInputListener m_Listener;

    private GameSoundFactory m_GameSoundFactory;


    private Scene getScene() {
        return this.m_Scene;
    }

    /**
     * @return the GameBoard instance used to interact data
     */
    public GameBoard getGameBoard() {
        return this.m_GameBoard;
    }

    private int getPreference() {
        return this.m_Preference;
    }

    /**
     * @return the key input listener for all scenes
     */
    public KeyInputListener getListener() {
        return this.m_Listener;
    }

    private GameSoundFactory getGameSoundFactory() {
        return this.m_GameSoundFactory;
    }


    private void setScene(Scene scene) {
        this.m_Scene = scene;
    }

    private void setGameBoard(GameBoard gameBoard) {
        this.m_GameBoard = gameBoard;
    }

    /**
     * Sets the color theme of the game board
     *
     * @param choice the color theme of the game board
     */
    public void setPreference(int choice) {
        this.m_Preference = choice;
    }

    private void setListener(KeyInputListener listener) {
        this.m_Listener = listener;
    }

    private void setGameSoundFactory(GameSoundFactory gameSoundFactory) {
        this.m_GameSoundFactory = gameSoundFactory;
    }

    /**
     * Initializes the game board and sets the media players of the game board.
     *
     * @see GameBoard
     */
    public void initializeGameBoard() {
        setGameBoard(new GameBoard());

        getGameBoard().setStartGame(this);

        getGameBoard().setNextLevelSound(
            getGameSoundFactory().getNextLevelSound());
        getGameBoard().setGameFailMusic(
            getGameSoundFactory().getGameFailMusic());
        getGameBoard().setGamePassMusic(
            getGameSoundFactory().getGamePassMusic());

        getGameBoard().getManager().setImpactPaddleSound(
            getGameSoundFactory().getImpactPaddleSound());
        getGameBoard().getManager().setBreakBrickSound(
            getGameSoundFactory().getBreakBrickSound());
        getGameBoard().getManager().setBallLostSound(
            getGameSoundFactory().getBallLostSound());
    }

    /**
     * Resets the game board when user clicks the play button in the main menu.
     * <p>
     * Because the color theme of the game board may be changed, we need to
     * reset the source image of the view of the components of the game board.
     *
     * @see GameBoard
     */
    public void resetGameBoard() {
        getGameBoard().getManager().replayGame();

        //Resets the view because the color theme may be changed
        getGameBoard().getGameBoardFactory().setPreference(getPreference());
        getGameBoard().setView();

        getGameBoard().updateGameStatus();
    }


    /**
     * Starts the game.
     *
     * @param stage the stage of the javafx application
     */
    @Override
    public void start(Stage stage) {
        setGameSoundFactory(new GameSoundFactory());

        initializeGameBoard();

        int SCENE_WIDTH = 600;
        int SCENE_HEIGHT = 490;

        setScene(new Scene(new Group(), SCENE_WIDTH, SCENE_HEIGHT));
        setListener(new KeyInputListener(getScene()));
        getListener().setGameBoard(getGameBoard());

        switchToMainMenu();

        stage.setScene(getScene());
        setGameLostFocus();
        stage.setTitle("Breakout game");
        stage.show();
    }


    private void setGameLostFocus() {
        getScene().getWindow().focusedProperty().addListener(
            (ob, oldVal, newVal) -> {
                if (!newVal)
                    getGameBoard().getGameTimer().stop();
            }
        );
    }


    /**
     * Changes the root of the scene to switch to the main menu.
     *
     * @see MainMenuController
     */
    public void switchToMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/MainMenu.fxml"));
            MainMenuController controller = new MainMenuController(
                getGameSoundFactory().getWelcomeMusic(),
                getGameSoundFactory().getClickButtonSound(),
                this);
            loader.setController(controller);
            getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root of the scene to switch to the game board.
     *
     * @see GameBoard
     */
    public void switchToGameBoard() {
        getListener().setIsPlaying(true);
        getScene().setRoot(getGameBoard().getGameBoard());
    }

    /**
     * Changes the root of the scene to switch to the game end menu.
     *
     * @see GameEndMenuController
     */
    public void switchToGameEndMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/GameEndMenu.fxml"));
            Parent root = loader.load();
            GameEndMenuController controller = loader.getController();
            controller.setStartGame(this);
            getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root of the scene to switch to the username form.
     *
     * @see UsernameFormController
     */
    public void switchToUsernameForm() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/UsernameForm.fxml"));
            Parent root = loader.load();
            UsernameFormController controller = loader.getController();
            controller.setStartGame(this);
            getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root of the scene to switch to the ranking list.
     *
     * @see RankingListController
     */
    public void switchToRankingList() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/RankingList.fxml"));
            RankingListController controller = new RankingListController(
                getGameSoundFactory().getInspiringMusic(), this);
            loader.setController(controller);
            getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root of the scene to switch to the preference scene.
     *
     * @see PreferencesController
     */
    public void switchToPreferences() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/Preferences.fxml"));
            Parent root = loader.load();
            PreferencesController controller = loader.getController();
            controller.setStartGame(this);
            getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root of the scene to switch to the guidance scene.
     *
     * @see GuidanceController
     */
    public void switchToGuidance() {
        try {
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/Guidance.fxml"));
            Parent root = loader.load();
            GuidanceController controller = loader.getController();
            controller.setStartGame(this);
            getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * The main method to run the program.
     * <pre> {@code
     *  Modified from the original source code:
     *  public static void main(String[] args){
     *      EventQueue.invokeLater(() -> new GameFrame().initialize());
     *  }
     * } </pre>
     *
     * @param args null
     */
    public static void main(String[] args) {
        launch();
    }

}
