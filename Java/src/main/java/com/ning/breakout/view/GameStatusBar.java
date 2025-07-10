package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Objects;

/**
 * GameStatusBar is the class visualizes the game status info.
 * <p>
 * The game status info includes:
 * <ul>
 * <li>the number of balls user still has (ball count)</li>
 * <li>the current level user is playing (level)</li>
 * <li>the number of bricks remained in the current level (brick count) </li>
 * </ul>
 *
 * @author Ning ZHU
 */
public class GameStatusBar {

    private final int DEF_FONT_SIZE = 18;


    private Group m_Container;

    private ImageView m_BallCountVisual;
    private Label m_LevelVisual;
    private Label m_BrickCountVisual;


    /**
     * Returns the container of the ImageView and Label objects which used to
     * visualize the game status info.
     *
     * @return the container of the ImageView and Label objects which used to
     * visualize the game status info
     *
     * @see Group
     */
    public Group getContainer() {
        return this.m_Container;
    }

    private ImageView getBallCountVisual() {
        return this.m_BallCountVisual;
    }

    private Label getLevelVisual() {
        return this.m_LevelVisual;
    }

    private Label getBrickCountVisual() {
        return this.m_BrickCountVisual;
    }


    private void setContainer(Group container) {
        this.m_Container = container;
    }

    private void setBallCountVisual(ImageView imageView) {
        this.m_BallCountVisual = imageView;
    }

    private void setLevelVisual(Label label) {
        this.m_LevelVisual = label;
    }

    private void setBrickCountVisual(Label label) {
        this.m_BrickCountVisual = label;
    }


    /**
     * The class constructor initializes and sets the lay out of the ImageView
     * and Label objects which used to visualize the ball count, level, brick
     * count of the game, and adds these Javafx objects into a new container
     * (which will be added to the bigger container in the game board to display
     * the game status info).
     */
    public GameStatusBar() {
        setContainer(new Group());
        makeBallCountVisual();
        makeLevelVisual();
        makeBrickCountVisual();
        getContainer().getChildren().addAll(
            getBallCountVisual(), getLevelVisual(), getBrickCountVisual());
    }


    /**
     * Initializes and sets the layout of the ImageView object which used to
     * visualize the ball count.
     */
    private void makeBallCountVisual() {
        setBallCountVisual(new ImageView());
        //The layout of the ball count visual
        getBallCountVisual().setLayoutX(20);
        getBallCountVisual().setLayoutY(460);
    }

    /**
     * Initializes and sets the layout of the Label object which used to
     * visualize the level.
     */
    private void makeLevelVisual() {
        setLevelVisual(new Label());
        //The layout of the level visual
        getLevelVisual().setLayoutX(280);
        getLevelVisual().setLayoutY(460);
        getLevelVisual().setFont(Font.font("impact", DEF_FONT_SIZE));
        getLevelVisual().setTextAlignment(TextAlignment.CENTER);
    }

    /**
     * Initializes and sets the layout of the Label object which used to
     * visualize the brick count.
     */
    private void makeBrickCountVisual() {
        setBrickCountVisual(new Label());
        //The layout of the brick count visual
        getBrickCountVisual().setLayoutX(500);
        getBrickCountVisual().setLayoutY(460);
        getBrickCountVisual().setFont(Font.font("impact", DEF_FONT_SIZE));
        getBrickCountVisual().setTextAlignment(TextAlignment.CENTER);
    }


    /**
     * Updates the source image of the ImageView object used to visualize the
     * ball count when the ball count of the game changes.
     *
     * @param count current ball count of the game
     */
    public void updateBallCountVisual(int count) {
        if (count == 0)
            return;
        URL[] img = new URL[]{
            StartGame.class.getResource("image/icons/one-life.jpg"),
            StartGame.class.getResource("image/icons/two-lives.jpg"),
            StartGame.class.getResource("image/icons/three-lives.jpg")
        };
        getBallCountVisual().setImage(new Image(
            Objects.requireNonNull(img[count - 1]).toExternalForm()));
    }

    /**
     * Updates the text of the Label object used to visualize the level when the
     * level of the game changes.
     *
     * @param level game level that user is playing
     */
    public void updateLevelVisual(int level) {
        getLevelVisual().setText("Level: " + level);
    }

    /**
     * Updates the text of the Label object used to visualize the brick count
     * when the brick count of the game changes.
     *
     * @param count number of bricks remained in the current level
     */
    public void updateBrickCountVisual(int count) {
        getBrickCountVisual().setText("Bricks: " + count);
    }

}
