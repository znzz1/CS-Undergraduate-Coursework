package com.ning.breakout.view;

import com.ning.breakout.StartGame;
import com.ning.breakout.view.graphics.Brick;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

/**
 * GameBoardFactory is the class takes the responsibility of producing the image
 * as the source of the view of the game objects, which used to show the game
 * board with different color theme.
 *
 * @author Ning ZHU
 */
public class GameBoardFactory {

    /**
     * The current color theme of the game board
     */
    private int m_Preference;


    private int getPreference() {
        return this.m_Preference;
    }


    /**
     * Sets the color theme of the game objects.
     *
     * @param preference the current color theme of the game board
     */
    public void setPreference(int preference) {
        this.m_Preference = preference;
    }


    /**
     * Returns the Image object used as the source of the view of the ball for
     * a specific color theme.
     *
     * @return the Image object used as the source of the view of the ball for
     * a specific color theme
     * @see Image
     */
    public Image makeBallView() {
        URL[] balls = new URL[]{
            StartGame.class.getResource("image/default/rubber-ball.jpg"),
            StartGame.class.getResource("image/black&white/rubber-ball.jpg"),
            StartGame.class.getResource("image/colorful/rubber-ball.jpg")
        };
        return new Image(
            Objects.requireNonNull(balls[getPreference()]).toExternalForm());
    }

    /**
     * Returns the Image object used as the source of the view of the paddle for
     * a specific color theme.
     *
     * @return the Image object used as the source of the view of the paddle for
     * a specific color theme
     * @see Image
     */
    public Image makePaddleView() {
        URL[] paddles = new URL[]{
            StartGame.class.getResource("image/default/rubber-paddle.jpg"),
            StartGame.class.getResource("image/black&white/rubber-paddle.jpg"),
            StartGame.class.getResource("image/colorful/rubber-paddle.jpg")
        };
        return new Image(
            Objects.requireNonNull(paddles[getPreference()]).toExternalForm());
    }

    /**
     * Returns the Image object used as the background image of the game board
     * for a specific color theme.
     *
     * @return the Image object used as the background image of the game board
     * for a specific color theme.
     * @see Image
     */
    public Image makeBackground() {
        URL[] backgrounds = new URL[]{
            StartGame.class.getResource("image/default/background.jpg"),
            StartGame.class.getResource("image/black&white/background.jpg"),
            StartGame.class.getResource("image/colorful/background.jpg")
        };
        return new Image(Objects.requireNonNull(
            backgrounds[getPreference()]).toExternalForm());
    }

    /**
     * Returns the Image object used as the source of the view of the specified
     * type of brick for a specific color theme.
     *
     * @param brick the type of the brick
     * @return the Image object used as the source of the view of the specified
     * type of brick for a specific color theme.
     * @see Image
     * @see Brick
     */
    public Image makeBrickView(Brick brick) {
        URL[] bricks = switch (brick.getClass().getSimpleName()) {
            case "ClayBrick" -> new URL[]{
                StartGame.class.getResource("image/default/clay-brick.jpg"),
                StartGame.class.getResource("image/black&white/clay-brick.jpg"),
                StartGame.class.getResource("image/colorful/clay-brick.jpg")
            };
            case "CementBrick" -> new URL[]{
                StartGame.class.getResource("image/default/cement-brick.jpg"),
                StartGame.class.getResource(
                    "image/black&white/cement-brick.jpg"),
                StartGame.class.getResource("image/colorful/cement-brick.jpg")
            };
            case "SteelBrick" -> new URL[]{
                StartGame.class.getResource("image/default/steel-brick.jpg"),
                StartGame.class.getResource(
                    "image/black&white/steel-brick.jpg"),
                StartGame.class.getResource("image/colorful/steel-brick.jpg")
            };
            default -> new URL[]{
                StartGame.class.getResource("image/default/golden-brick.jpg"),
                StartGame.class.getResource(
                    "image/black&white/golden-brick.jpg"),
                StartGame.class.getResource("image/colorful/golden-brick.jpg")
            };
        };
        return new Image(bricks[getPreference()].toExternalForm());
    }

}
