package com.ning.breakout.view.graphics;


import com.ning.breakout.model.PaddleModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * RubberPaddle is the concrete class extends from the abstract class Paddle,
 * with width <b>150</b> and height <b>10</b>.
 *
 * @see Paddle
 *
 * @author Ning ZHU - modified
 */
public class RubberPaddle extends Paddle {

    /**
     * The width and the height of rubber paddle.
     */
    private static final int WIDTH = 150;
    private static final int HEIGHT = 10;


    /**
     * The class constructor sets the model and the moving boundary of the
     * rubber paddle, binds the layout of the view of the rubber paddle with the
     * model and adds the view to the container by calling the constructor of
     * super class.
     *
     * @param ballPoint the top-center point of the paddle
     * @param boundary the moving boundary of the paddle
     * @param container the container of rubber paddle's view
     * @see Paddle#Paddle(PaddleModel, Rectangle, Group)
     */
    public RubberPaddle(Point2D ballPoint, Rectangle boundary, Group container){
        super(new PaddleModel(ballPoint, WIDTH, HEIGHT), boundary, container);
    }

}