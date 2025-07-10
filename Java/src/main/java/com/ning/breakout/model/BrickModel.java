package com.ning.breakout.model;

import javafx.geometry.Point2D;

/**
 * Model class of the brick, which defines the attributes (width, height and
 * the position of the left-top point) of the brick and provides methods to
 * access/modify these attributes.
 *
 * @author Ning ZHU - modified
 */
public class BrickModel {

    private int m_Width;
    private int m_Height;

    /**
     * The left-top point of the brick.
     */
    private Point2D m_StartPoint;


    /**
     * @return the width of the brick
     */
    public int getWidth() {
        return this.m_Width;
    }

    /**
     * @return the height of the brick
     */
    public int getHeight() {
        return this.m_Height;
    }

    /**
     * Returns the left-top point of the brick.
     *
     * @return the left-top point of the brick
     */
    public Point2D getStartPoint() {
        return this.m_StartPoint;
    }


    private void setWidth(int width) {
        this.m_Width = width;
    }

    private void setHeight(int height) {
        this.m_Height = height;
    }

    private void setStartPoint(Point2D startPoint) {
        this.m_StartPoint = startPoint;
    }


    /**
     * The class constructor specifies the width and the height of the brick,
     * as well as the position of the left-top point of the brick.
     *
     * @param point the left-top point of the brick
     * @param width the width of the brick
     * @param height the height of the brick
     */
    public BrickModel(Point2D point, int width, int height) {
        setStartPoint(point);
        setWidth(width);
        setHeight(height);
    }

}
