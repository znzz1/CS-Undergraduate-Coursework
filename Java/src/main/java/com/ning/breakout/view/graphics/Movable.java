package com.ning.breakout.view.graphics;

import javafx.geometry.Point2D;

/**
 * Graphics class implements Interface Movable should be able to move in the
 * game board.
 *
 * @author Ning ZHU
 */
public interface Movable {

    /**
     * Moves graphics by speed.
     */
    void move();

    /**
     * Moves graphics to specified point.
     *
     * @param point the point graphics will move to
     */
    void moveTo(Point2D point);

}
