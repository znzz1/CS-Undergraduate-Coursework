package com.ning.breakout.model;

import javafx.geometry.Point2D;

/**
 * Model class of the paddle, which defines the attributes (width, height and
 * the position of the top-center point) of the paddle and provides methods to
 * access/modify these attributes.
 *
 * @author Ning ZHU - modified
 */
public class PaddleModel {

    private int m_Width;
    private int m_Height;

    /**
     * The top-center point of the paddle.
     */
    private Point2D m_BallPoint;


    /**
     * @return the width of the paddle
     */
    public int getWidth() {
        return this.m_Width;
    }

    /**
     * @return the height of the paddle
     */
    public int getHeight() {
        return this.m_Height;
    }

    /**
     * Returns the top-center point of the paddle.
     * <p>
     * When the game has not started, the top-center point of the paddle is at
     * the same position as the center point of the ball. That's why this field
     * called m_BallPoint.
     *
     * @return the top-center point of the paddle
     */
    public Point2D getBallPoint() {
        return this.m_BallPoint;
    }


    private void setWidth(int width) {
        this.m_Width = width;
    }

    private void setHeight(int height) {
        this.m_Height = height;
    }

    /**
     * @param ballPoint the position of the top-center point
     */
    public void setBallPoint(Point2D ballPoint) {
        this.m_BallPoint = ballPoint;
    }


    /**
     * The class constructor specifies the width and the height of the paddle,
     * as well as the position of the top-center point of the paddle.
     *
     * @param point the top-center point of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public PaddleModel(Point2D point, int width, int height) {
        setBallPoint(point);
        setWidth(width);
        setHeight(height);
    }

}
