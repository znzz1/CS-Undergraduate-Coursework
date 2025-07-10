package com.ning.breakout.model;

import javafx.geometry.Point2D;

/**
 * Model class of the ball, which defines the attributes (radius, position of
 * five feature points) of the ball and provides methods to access/modify these
 * attributes.
 *
 * @author Ning ZHU - modified
 */
public class BallModel {

    /**
     * Different balls may have different radius.
     */
    private int m_Radius;

    private Point2D m_CenterPoint;

    private Point2D m_UpPoint;
    private Point2D m_DownPoint;
    private Point2D m_LeftPoint;
    private Point2D m_RightPoint;


    private int getRadius() {
        return this.m_Radius;
    }

    /**
     * @return the center point of the ball
     */
    public Point2D getCenterPoint() {
        return this.m_CenterPoint;
    }

    /**
     * Returns the up point of the ball.
     * <p>
     * Assume the coordinate of the center point is (x, y) and the radius of the
     * ball is r, the coordinate of the up point is (x, y - r).
     *
     * @return the up point of the ball
     */
    public Point2D getUpPoint() {
        return this.m_UpPoint;
    }

    /**
     * Returns the down point of the ball.
     * <p>
     * Assume the coordinate of the center point is (x, y) and the radius of the
     * ball is r, the coordinate of the down point is (x, y + r).
     *
     * @return the down point of the ball
     */
    public Point2D getDownPoint() {
        return this.m_DownPoint;
    }

    /**
     * Returns the left point of the ball.
     * <p>
     * Assume the coordinate of the center point is (x, y) and the radius of the
     * ball is r, the coordinate of the left point is (x - r, y).
     *
     * @return the left point of the ball
     */
    public Point2D getLeftPoint() {
        return this.m_LeftPoint;
    }

    /**
     * Returns the right point of the ball.
     * <p>
     * Assume the coordinate of the center point is (x, y) and the radius of the
     * ball is r, the coordinate of the up point is (x + r, y).
     *
     * @return the right point of the ball
     */
    public Point2D getRightPoint() {
        return this.m_RightPoint;
    }


    private void setRadius(int radius) {
        this.m_Radius = radius;
    }

    /**
     * @param pos the position of the center point
     */
    public void setCenterPoint(Point2D pos) {
        this.m_CenterPoint = pos;
    }

    private void setUpPoint(double posX, double posY) {
        this.m_UpPoint = new Point2D(posX, posY);
    }

    private void setDownPoint(double posX, double posY) {
        this.m_DownPoint = new Point2D(posX, posY);
    }

    private void setLeftPoint(double posX, double posY) {
        this.m_LeftPoint = new Point2D(posX, posY);
    }

    private void setRightPoint(double posX, double posY) {
        this.m_RightPoint = new Point2D(posX, posY);
    }


    /**
     * The class constructor specifies the radius and the position of five
     * feature points of the ball.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     */
    public BallModel(Point2D center, int radius) {
        setCenterPoint(center);
        setRadius(radius);
        updatePoints();
    }


    /**
     * Updates the up, down, left and right points of the ball.
     * <p>
     * Based on the position of the center point and the radius of the ball,
     * updates the position of up, down, left and right points of the ball.
     *
     * <pre> {@code
     *  Modified from the original source code:
     *  public void setPoints(double width,double height){
     *      up.setLocation(center.getX(),center.getY()-(height / 2));
     *      down.setLocation(center.getX(),center.getY()+(height / 2));
     *
     *      left.setLocation(center.getX()-(width / 2),center.getY());
     *      right.setLocation(center.getX()+(width / 2),center.getY());
     *  }
     * } </pre>
     */
    public void updatePoints() {
        setUpPoint(getCenterPoint().getX(),
            getCenterPoint().getY() - getRadius());

        setDownPoint(getCenterPoint().getX(),
            getCenterPoint().getY() + getRadius());

        setLeftPoint(getCenterPoint().getX() - getRadius(),
            getCenterPoint().getY());

        setRightPoint(getCenterPoint().getX() + getRadius(),
            getCenterPoint().getY());
    }

}
