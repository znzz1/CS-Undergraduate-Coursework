package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BallModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * RubberBall is the concrete class extends from the abstract class Ball, with
 * radius of <b>5</b>.
 *
 * @see Ball
 *
 * @author Ning ZHU - modified
 */
public class RubberBall extends Ball {

    /**
     * The default radius of the rubber ball is <b>5</b>.
     */
    private static final int RADIUS = 5;


    /**
     * The class constructor sets the model of the rubber ball, binds the layout
     * of the view of the rubber ball with the model and adds the view to the
     * container by calling the constructor of super class.
     *
     * @param center the center point of the rubber ball
     * @param container  the container of rubber ball's view
     * @see Ball#Ball(BallModel, Group) 
     */
    public RubberBall(Point2D center, Group container) {
        super(new BallModel(center, RADIUS), container);
    }

}
