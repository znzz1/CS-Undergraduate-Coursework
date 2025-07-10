package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import com.ning.breakout.controller.DebugConsoleController;
import com.ning.breakout.controller.PauseMenuController;
import com.ning.breakout.view.graphics.Brick;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;


/**
 * GameBoard is the class that integrates classes GameBoardFactory,
 * GameBoardManager and GameStatusBar. And by using the class Timeline and
 * KeyFrame, class GameBoard animates the game and defines the action of game
 * objects for every frame.
 *
 * @see GameBoardFactory
 * @see GameBoardManager
 * @see GameStatusBar
 * @see Timeline
 * @see KeyFrame
 *
 * @author Ning ZHU - modified
 */
public class GameBoard {

    private final int DEF_WIDTH = 600;
    private final int DEF_HEIGHT = 450;

    private Timeline m_GameTimer;
    private KeyFrame m_KeyFrame;

    private MediaPlayer m_NextLevelSound;
    private MediaPlayer m_GameFailMusic;
    private MediaPlayer m_GamePassMusic;

    private GameBoardFactory m_Factory;
    private ImageView m_Background;
    private GameBoardManager m_manager;
    private GameStatusBar m_GameStatus;

    private Parent m_PauseMenu;
    private boolean m_ShowPauseMenu;

    private Parent m_DebugConsole;
    private boolean m_ShowDebugConsole;

    private Pane m_GameBoard;

    private StartGame m_StartGame;


    /**
     * Returns the Timeline object used to animate the game.
     *
     * @return the Timeline object used to animate the game
     * @see Timeline
     */
    public Timeline getGameTimer() {
        return this.m_GameTimer;
    }

    private KeyFrame getKeyFrame() {
        return this.m_KeyFrame;
    }

    private MediaPlayer getNextLevelSound() {
        return this.m_NextLevelSound;
    }

    private MediaPlayer getGameFailMusic() {
        return this.m_GameFailMusic;
    }

    private MediaPlayer getGamePassMusic() {
        return this.m_GamePassMusic;
    }

    /**
     * @return the GameBoardFactory object used to produce images of the
     * game objects
     * @see GameBoardFactory
     */
    public GameBoardFactory getGameBoardFactory() {
        return this.m_Factory;
    }

    private ImageView getBackground() {
        return this.m_Background;
    }

    /**
     * @return the GameBoardManager object used to control the game objects
     * @see GameBoardManager
     */
    public GameBoardManager getManager() {
        return this.m_manager;
    }

    private GameStatusBar getGameStatus() {
        return this.m_GameStatus;
    }

    private Parent getPauseMenu() {
        return this.m_PauseMenu;
    }

    /**
     * Returns whether the pause menu opened in the game board.
     *
     * @return true if the pause menu opened in the game board
     */
    public boolean getShowPauseMenu() {
        return this.m_ShowPauseMenu;
    }

    private Parent getDebugConsole() {
        return this.m_DebugConsole;
    }

    /**
     * Returns whether the debug console opened in the game board.
     *
     * @return true if the debug console opened in the game board
     */
    public boolean getShowDebugConsole() {
        return this.m_ShowDebugConsole;
    }

    /**
     * Returns the container of all game objects.
     *
     * @return the highest level container of the game board which contains
     * everything of the game board.
     * @see Pane
     */
    public Pane getGameBoard() {
        return this.m_GameBoard;
    }

    private StartGame getStartGame() {
        return this.m_StartGame;
    }


    private void setGameTimer(Timeline timeline) {
        this.m_GameTimer = timeline;
    }

    private void setKeyFrame(KeyFrame keyFrame) {
        this.m_KeyFrame = keyFrame;
    }

    /**
     * @param nextLevelSound the MediaPlayer object used to play the media when
     *                       user passes the current level and is going to enter
     *                       the next level
     */
    public void setNextLevelSound(MediaPlayer nextLevelSound) {
        this.m_NextLevelSound = nextLevelSound;
    }

    /**
     * @param gameFailMusic the MediaPlayer object used to play the media when
     *                      user runs out of the balls in the game.
     */
    public void setGameFailMusic(MediaPlayer gameFailMusic) {
        this.m_GameFailMusic = gameFailMusic;
    }

    /**
     * @param gamePassMusic the MediaPlayer object used to play the media when
     *                      user passes all levels of the game
     */
    public void setGamePassMusic(MediaPlayer gamePassMusic) {
        this.m_GamePassMusic = gamePassMusic;
    }

    private void setGameBoardFactory(GameBoardFactory factory) {
        this.m_Factory = factory;
    }

    private void setBackground(ImageView background) {
        this.m_Background = background;
    }

    private void setManager(GameBoardManager manager) {
        this.m_manager = manager;
    }

    private void setGameStatus(GameStatusBar gameStatus) {
        this.m_GameStatus = gameStatus;
    }

    private void setPauseMenu(Parent pauseMenu) {
        this.m_PauseMenu = pauseMenu;
    }

    /**
     * @param bool boolean variable used to indicate whether the pause menu
     *             is opened in the game board
     */
    public void setShowPauseMenu(boolean bool) {
        this.m_ShowPauseMenu = bool;
    }

    private void setDebugConsole(Parent debugConsole) {
        this.m_DebugConsole = debugConsole;
    }

    /**
     * @param bool boolean variable used to indicate whether the debug console
     *             is opened in the game board
     */
    public void setShowDebugConsole(boolean bool) {
        this.m_ShowDebugConsole = bool;
    }

    private void setGameBoard(Pane pane) {
        this.m_GameBoard = pane;
    }

    /**
     * @param startGame the StartGame instance used to interact data with other
     *                  scenes
     */
    public void setStartGame(StartGame startGame) {
        this.m_StartGame = startGame;
    }


    /**
     * The constructor initializes the game objects and animates the game.
     * <pre> {@code
     *  Modified from the original source code:
     *  public GameBoard(JFrame owner){
     *      super();
     *
     *      strLen = 0;
     *      showPauseMenu = false;
     *
     *
     *
     *      menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
     *
     *
     *      this.initialize();
     *      message = "Press SPACE to start";
     *      wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,
     *                                                      new Point(300,430));
     *
     *      debugConsole = new DebugConsole(owner,wall,this);
     *      //initialize the first level
     *      wall.nextLevel();
     *
     *      gameTimer = new Timer(10,e ->{
     *      wall.move();
     *      wall.findImpacts();
     *      message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),
     *                                                  wall.getBallCount());
     *      if(wall.isBallLost()){
     *          if(wall.ballEnd()){
     *              wall.wallReset();
     *              message = "Game over";
     *          }
     *          wall.ballReset();
     *          gameTimer.stop();
     *      }
     *      else if(wall.isDone()){
     *          if(wall.hasLevel()){
     *              message = "Go to Next Level";
     *              gameTimer.stop();
     *              wall.ballReset();
     *              wall.wallReset();
     *              wall.nextLevel();
     *          }
     *          else{
     *              message = "ALL WALLS DESTROYED";
     *              gameTimer.stop();
     *          }
     *      }
     *
     *      repaint();
     *      });
     *
     *  }
     * } </pre>
     *
     * @see GameBoardManager
     * @see GameBoardFactory
     * @see GameStatusBar
     * @see Timeline
     * @see KeyFrame
     */
    public GameBoard() {
        setGameBoard(new Pane());

        int DEF_BRICK_COUNT = 30;
        int DEF_LINE_COUNT = 3;
        double DEF_BRICK_DIMENSION_RATIO = 3;
        Point2D DEF_BALL_POINT = new Point2D(300, 430);

        setManager(new GameBoardManager(
            new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT),
            DEF_BRICK_COUNT, DEF_LINE_COUNT, DEF_BRICK_DIMENSION_RATIO,
            DEF_BALL_POINT));

        setGameBoardFactory(new GameBoardFactory());

        setGameStatus(new GameStatusBar());

        initializeBackground();

        getGameBoard().getChildren().addAll(getBackground(),
            getManager().getContainer(), getGameStatus().getContainer());

        int FRAME_DURATION = 7;
        setKeyFrame(new KeyFrame(Duration.millis(FRAME_DURATION),
            event -> frameAction()));
        setGameTimer(new Timeline());
        getGameTimer().setCycleCount(Timeline.INDEFINITE);
        getGameTimer().getKeyFrames().add(getKeyFrame());
    }


    private void setBallView() {
        getManager().getBall().getBallView().setImage(
            getGameBoardFactory().makeBallView());
    }

    private void setPaddleView() {
        getManager().getPlayer().getPaddleView().setImage(
            getGameBoardFactory().makePaddleView());
    }

    /**
     * Updates the view of bricks.
     * <p>
     * The view of bricks differ in different level and different color theme.
     *
     * @see GameBoardFactory#makeBrickView(Brick)
     */
    public void setBricksView() {
        for (int i = 0; i < getManager().getLevelBrickCount(); i++) {
            getManager().getBricks()[i].getBrickView().setImage(
                getGameBoardFactory().makeBrickView(
                    getManager().getBricks()[i]));
        }
    }

    private void initializeBackground() {
        setBackground(new ImageView());
        getBackground().setX(0);
        getBackground().setY(0);
        getBackground().setFitWidth(DEF_WIDTH);
        getBackground().setFitHeight(DEF_HEIGHT);
    }

    private void setBackground() {
        getBackground().setImage(getGameBoardFactory().makeBackground());
    }

    /**
     * Set the source image of the game objects.
     */
    public void setView() {
        setBallView();
        setPaddleView();
        setBricksView();
        setBackground();
    }


    /**
     * Updates the visualization of game status info everytime the game status
     * info changes.
     *
     * @see GameStatusBar
     */
    public void updateGameStatus() {
        getGameStatus().updateBallCountVisual(getManager().getBallCount());
        getGameStatus().updateLevelVisual(getManager().getLevel());
        getGameStatus().updateBrickCountVisual(
            getManager().getLevelBrickCount());
    }


    /**
     * Replays the game (from the first level).
     */
    public void replayGame() {
        getManager().replayGame();
        setBricksView();
        updateGameStatus();
    }


    /**
     * Actions for every frame.
     */
    private void frameAction() {
        getManager().move();
        getManager().handleImpacts();
        updateGameStatus();
        if (getManager().getBallLost()) {
            if (getManager().isGameFail()) {
                getGameFailMusic().play();
                getGameFailMusic().seek(getGameFailMusic().getStartTime());

                getManager().getBricksScore();
                getGameTimer().stop();
                getStartGame().getListener().setIsPlaying(false);
                getStartGame().switchToGameEndMenu();
            } else {
                getManager().resetBall();
                getGameTimer().stop();
            }
        } else if (getManager().isLevelPassed()) {
            if (getManager().hasNextLevel()) {
                getNextLevelSound().play();
                getNextLevelSound().seek(getNextLevelSound().getStartTime());

                getGameTimer().stop();
                getManager().getLevelScore();
                getManager().nextLevel();
                updateGameStatus();
                setBricksView();
            } else {
                getGamePassMusic().play();
                getGamePassMusic().seek(getGameFailMusic().getStartTime());

                getManager().getBricksScore();
                getManager().getLevelScore();
                getGameTimer().stop();
                getStartGame().getListener().setIsPlaying(false);
                getStartGame().switchToGameEndMenu();
            }
        }
    }


    /**
     * Opens the pause menu in the game board.
     */
    public void showPauseMenu() {
        try {
            setShowPauseMenu(true);
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/PauseMenu.fxml"));
            setPauseMenu(loader.load());
            PauseMenuController controller = loader.getController();
            controller.setStartGame(getStartGame());
            controller.setGameBoard(this);
            getGameBoard().getChildren().add(getPauseMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the pause menu in the game board.
     */
    public void hidePauseMenu() {
        setShowPauseMenu(false);
        getGameBoard().getChildren().remove(getPauseMenu());
    }

    /**
     * Opens the debug console in the game board.
     */
    public void showDebugConsole() {
        try {
            setShowDebugConsole(true);
            FXMLLoader loader = new FXMLLoader(
                StartGame.class.getResource("fxml/DebugConsole.fxml"));
            loader.setController(new DebugConsoleController(this));
            setDebugConsole(loader.load());
            getGameBoard().getChildren().add(getDebugConsole());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the debug console in the game board.
     */
    public void hideDebugConsole() {
        setShowDebugConsole(false);
        getGameBoard().getChildren().remove(getDebugConsole());
    }

}
