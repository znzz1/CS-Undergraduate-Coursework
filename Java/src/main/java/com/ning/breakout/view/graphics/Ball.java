package com.ning.breakout.view.graphics;

import com.ning.breakout.model.BallModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.Random;


/**
 * Ball is the abstract base class for all ball classes which defines the common
 * attributes and behaviors of all ball classes.
 * <p>
 * The layout of the view of the ball binds with the model of the ball, every
 * time the ball moves, the layout of the view of the ball will also be updated.
 * By adding the view of the ball into container, the ball could be displayed
 * onto the stage.
 * <p>
 * By extending the abstract class Ball, sub-classes could have various radius
 * and different source images for their views.
 *
 * @see Movable
 * @see BallModel
 *
 * @author Ning ZHU - modified
 */
abstract public class Ball implements Movable {

    private final int INVERSE = -1;


    private BallModel m_BallModel;

    private ImageView m_BallView;

    private int m_SpeedX;

    private int m_SpeedY;


    private BallModel getBallModel() {
        return this.m_BallModel;
    }

    /**
     * Returns the view of the ball.
     * <p>
     * The layout of the ImageView object binds with the model of the ball. By
     * setting the source image of the ImageView object and adding it to the
     * container, different balls can be displayed onto the stage.
     *
     * @return the view of the ball
     * @see ImageView
     */
    public ImageView getBallView() {
        return this.m_BallView;
    }

    /**
     * @return the moving speed of ball on x-axis, in pixels per frame
     */
    public int getSpeedX() {
        return this.m_SpeedX;
    }

    /**
     * @return the moving speed of ball on y-axis, in pixels per frame
     */
    public int getSpeedY() {
        return this.m_SpeedY;
    }

    /**
     * @return the center point of the ball
     * @see BallModel#getCenterPoint()
     */
    public Point2D getCenterPoint() {
        return getBallModel().getCenterPoint();
    }

    /**
     * @return the up point of the ball
     * @see BallModel#getUpPoint()
     */
    public Point2D getUpPoint() {
        return getBallModel().getUpPoint();
    }

    /**
     * @return the down point of the ball
     * @see BallModel#getDownPoint()
     */
    public Point2D getDownPoint() {
        return getBallModel().getDownPoint();
    }

    /**
     * @return the left point of the ball
     * @see BallModel#getLeftPoint()
     */
    public Point2D getLeftPoint() {
        return getBallModel().getLeftPoint();
    }

    /**
     * @return the right point of the ball
     * @see BallModel#getRightPoint()
     */
    public Point2D getRightPoint() {
        return getBallModel().getRightPoint();
    }


    private void setBallModel(BallModel model) {
        this.m_BallModel = model;
    }

    private void setBallView(ImageView view) {
        this.m_BallView = view;
    }

    /**
     * @param speed the moving speed of ball on x-axis, in pixels per frame
     */
    public void setSpeedX(int speed) {
        this.m_SpeedX = speed;
    }

    /**
     * @param speed the moving speed of ball on y-axis, in pixels per frame.
     */
    public void setSpeedY(int speed) {
        this.m_SpeedY = speed;
    }

    /**
     * Sets the moving speed of ball.
     */
    private void setSpeed(int speedX, int speedY) {
        setSpeedX(speedX);
        setSpeedY(speedY);
    }


    /**
     * The class constructor sets the model of the ball, binds the layout of the
     * view of the ball with the model and adds the view to the container.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Ball(Point2D center,int radiusA,int radiusB,Color inner,
     *  Color border){
     *      this.center = center;
     *
     *      up = new Point2D.Double();
     *      down = new Point2D.Double();
     *      left = new Point2D.Double();
     *      right = new Point2D.Double();
     *
     *      up.setLocation(center.getX(),center.getY()-(radiusB / 2));
     *      down.setLocation(center.getX(),center.getY()+(radiusB / 2));
     *
     *      left.setLocation(center.getX()-(radiusA /2),center.getY());
     *      right.setLocation(center.getX()+(radiusA /2),center.getY());
     *
     *
     *      ballFace = makeBall(center,radiusA,radiusB);
     *      this.border = border;
     *      this.inner  = inner;
     *      speedX = 0;
     *      speedY = 0;
     *  }
     * } </pre>
     * @param model the model of ball
     * @param container the container of ball's view
     * @see BallModel
     * @see Group
     */
    public Ball(BallModel model, Group container) {
        setBallModel(model);

        setBallView(makeBallView());

        container.getChildren().add(getBallView());
    }


    /**
     * Creates a new ImageView object and binds the layout of the object with
     * the model of the ball.
     * <p>
     * Returns the created object as the view of the ball.
     */
    private ImageView makeBallView() {
        ImageView ballView = new ImageView();
        //Binds the layout
        ballView.setX(getLeftPoint().getX());
        ballView.setY(getUpPoint().getY());
        ballView.setFitWidth(getRightPoint().getX() - getLeftPoint().getX());
        ballView.setFitHeight(getDownPoint().getY() - getUpPoint().getY());

        return ballView;
    }


    /**
     * Updates the layout of the view of the ball.
     */
    private void updateBallView() {
        getBallView().setX(getBallModel().getLeftPoint().getX());
        getBallView().setY(getBallModel().getUpPoint().getY());
    }


    /**
     * Moves the ball by speed and updates the layout of ball's view.
     * <p>
     * The center point of the ball will firstly be moved, and then the other
     * four feature points and the layout of the view of the ball will be
     * updated.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void move(){
     *      RectangularShape tmp = (RectangularShape) ballFace;
     *      center.setLocation((center.getX()+speedX),(center.getY()+speedY));
     *      double w = tmp.getWidth();
     *      double h = tmp.getHeight();
     *
     *      tmp.setFrame((center.getX()-(w / 2)),(center.getY()-(h / 2)),w,h);
     *      setPoints(w,h);
     *
     *      ballFace = tmp;
     *  }
     * } </pre>
     *
     * @see Movable#move() 
     */
    @Override
    public void move() {
        //Moves the center point by speed
        getBallModel().setCenterPoint(new Point2D(
            (getCenterPoint().getX() + getSpeedX()),
            (getCenterPoint().getY() + getSpeedY())));

        getBallModel().updatePoints();

        updateBallView();
    }

    /**
     * Moves the ball to specified position and updates the layout of ball's
     * view.
     * <p>
     * The center point of the ball will firstly be moved to the specified
     * point, then the other four feature points of the ball and the layout of
     * the view of the ball will be updated.
     * <p>
     * Usually Used to recover ball's position when ball is lost.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void moveTo(Point p){
     *      center.setLocation(p);
     *
     *      RectangularShape tmp = (RectangularShape) ballFace;
     *      double w = tmp.getWidth();
     *      double h = tmp.getHeight();
     *
     *      tmp.setFrame((center.getX()-(w / 2)),(center.getY()-(h / 2)),w,h);
     *      ballFace = tmp;
     *  }
     * } </pre>
     *
     * @param point the position the center point of the ball will move to
     * @see Movable#moveTo(Point2D)
     */
    @Override
    public void moveTo(Point2D point) {
        //Moves the center point to specified position
        getBallModel().setCenterPoint(point);

        getBallModel().updatePoints();

        updateBallView();
    }


    /**
     * Reverses the moving direction of ball on x-axis.
     */
    public void reverseX() {
        setSpeedX(getSpeedX() * INVERSE);
    }

    /**
     * Reverses the moving direction of ball on y-axis.
     */
    public void reverseY() {
        setSpeedY(getSpeedY() * INVERSE);
    }


    /**
     * Resets the moving speed of ball.
     * <p>
     * After resetting, the speed on x-axis will range from [<b>-2,2</b>]
     * (excluding 0), and the speed on y-axis will range from [<b>-2, 0</b>).
     *
     * <pre> {@code
     *  Modified from the original source code:
     *         int speedX,speedY;
     *         do{
     *             speedX = rnd.nextInt(5) - 2;
     *         }while(speedX == 0);
     *         do{
     *             speedY = -rnd.nextInt(3);
     *         }while(speedY == 0);
     *
     *         ball.setSpeed(speedX,speedY);
     * } </pre>
     */
    public void resetSpeed() {
        int speedX, speedY;
        Random rnd = new Random();

        int SPPED_X_Bound = 5;
        int SPEED_X_OFFSET = 2;
        int SPEED_Y_BOUND = 3;

        //Makes the speed of x from [-2,2] (excluding 0)
        do {
            speedX = rnd.nextInt(SPPED_X_Bound) - SPEED_X_OFFSET;
        } while (speedX == 0);
        //Makes the speed of y from [-2,0)
        do {
            speedY = -rnd.nextInt(SPEED_Y_BOUND);
        } while (speedY == 0);

        setSpeed(speedX, speedY);
    }

}
