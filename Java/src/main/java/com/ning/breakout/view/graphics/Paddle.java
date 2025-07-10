package com.ning.breakout.view.graphics;


import com.ning.breakout.model.PaddleModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


/**
 * Paddle is the abstract base class for all paddle classes which defines the
 * common attributes and behaviors of all paddle classes.
 * <p>
 * The layout of the view of the paddle binds with the model of the paddle,
 * every time the paddle moves, the layout of the view of the paddle will also
 * be updated. By adding the view of the paddle into container, the paddle could
 * be displayed onto the stage.
 * <p>
 * By extending the abstract class paddle, sub-classes could have various width
 * and height, and the different source images of their views.
 *
 * @see Movable
 * @see PaddleModel
 *
 * @author Ning ZHU - modified
 */
abstract public class Paddle implements Movable {

    /**
     * The default movement amount of the paddle, in pixels per frame.
     */
    private final int DEF_MOVE_AMOUNT = 4;

    /**
     * Used to get the half of the width or the height.
     */
    private final double HALF = 0.5;


    private PaddleModel m_PaddleModel;

    private ImageView m_PaddleView;

    private int m_MoveAmount;

    /**
     * The left moving boundary of the top-center point of the paddle.
     */
    private int m_LeftBound;

    /**
     * The right moving boundary of the top-center point of the paddle.
     */
    private int m_RightBound;


    private PaddleModel getPaddleModel() {
        return this.m_PaddleModel;
    }

    /**
     * Returns the view of the paddle.
     * <p>
     * The layout of the ImageView object binds with the model of the paddle. By
     * setting the source image of the ImageView object and adding it to the
     * container, different paddles can be displayed onto the stage.
     *
     * @return the view of the paddle
     * @see ImageView
     */
    public ImageView getPaddleView() {
        return this.m_PaddleView;
    }

    private int getMoveAmount() {
        return this.m_MoveAmount;
    }

    private int getLeftBound() {
        return this.m_LeftBound;
    }

    private int getRightBound() {
        return this.m_RightBound;
    }


    private void setPaddleModel(PaddleModel model) {
        this.m_PaddleModel = model;
    }

    private void setPaddleView(ImageView view) {
        this.m_PaddleView = view;
    }

    private void setMoveAmount(int moveAmount) {
        this.m_MoveAmount = moveAmount;
    }

    private void setLeftBound(int leftBound) {
        this.m_LeftBound = leftBound;
    }

    private void setRightBound(int rightBound) {
        this.m_RightBound = rightBound;
    }


    /**
     * The class constructor sets the model and the moving boundary of the
     * paddle, binds the layout of the view of the paddle with the model and
     * adds the view to the container.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Paddle(Point ballPoint, int width, int height,
     *  Rectangle container) {
     *      this.ballPoint = ballPoint;
     *      moveAmount = 0;
     *      paddleFace = makeRectangle(width, height);
     *      min = container.x + (width / 2);
     *      max = min + container.width - width;
     *  }
     * } </pre>
     *
     * @param model the model of paddle
     * @param boundary the moving boundary of the paddle
     * @param container the container of paddle's view
     * @see PaddleModel
     * @see Rectangle
     * @see Group
     */
    public Paddle(PaddleModel model, Rectangle boundary, Group container) {
        setPaddleModel(model);

        setPaddleView(makePaddleView());

        container.getChildren().add(getPaddleView());

        //Sets the moving boundary of the top-center point of the paddle
        setLeftBound((int) (boundary.getX() + model.getWidth() * HALF));
        setRightBound((int) (getLeftBound() + boundary.getWidth() -
                         model.getWidth()));
    }


    /**
     * Creates a new ImageView object and binds the layout of the object with
     * the model of the paddle.
     * <p>
     * Returns the created object as the view of the paddle.
     */
    private ImageView makePaddleView() {
        ImageView paddleView = new ImageView();
        //Binds the layout
        paddleView.setX(getPaddleModel().getBallPoint().getX()
                      - getPaddleModel().getWidth() * HALF);
        paddleView.setY(getPaddleModel().getBallPoint().getY());
        paddleView.setFitWidth(getPaddleModel().getWidth());
        paddleView.setFitHeight(getPaddleModel().getHeight());

        return paddleView;
    }


    /**
     * Updates the layout of the view of the paddle.
     */
    private void updatePaddleView() {
        getPaddleView().setX(getPaddleModel().getBallPoint().getX()
                                 - getPaddleView().getFitWidth() * HALF);
    }


    /**
     * Moves the paddle by speed and updates the layout of paddle's view.
     * <p>
     * Checks whether the paddle will move out of the boundary at first. The
     * movement will be canceled if the paddle will move out of the boundary.
     * When paddle moves within in the boundary, the top-center point of the
     * paddle will firstly be moved and then the layout of the view of the
     * paddle will be updated.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void move(){
     *      double x = ballPoint.getX() + moveAmount;
     *      if(x < min || x > max)
     *          return;
     *      ballPoint.setLocation(x,ballPoint.getY());
     *      paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,
     *          ballPoint.y);
     *  }
     * } </pre>
     *
     * @see Movable#move()
     */
    @Override
    public void move() {
        double x = getPaddleModel().getBallPoint().getX() + getMoveAmount();

        //Checks whether the paddle will move out of the boundary
        if (x < getLeftBound() || x > getRightBound())
            return;

        //Moves the top-center point by speed
        getPaddleModel().setBallPoint(new Point2D(x,
            getPaddleModel().getBallPoint().getY()));

        updatePaddleView();
    }

    /**
     * Moves the paddle to specified position and updates the layout of paddle's
     * view.
     * <p>
     * The top-center point of the paddle will firstly be moved to the specified
     * point, then the layout of the view of the paddle will be updated.
     * <p>
     * Usually used to recover paddle's position when ball is lost.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void moveTo(Point p){
     *      ballPoint.setLocation(p);
     *      paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,
     *          ballPoint.y);
     *  }
     * } </pre>
     *
     * @param point the position the top-center point of the paddle will move to
     * @see Movable#moveTo(Point2D)
     */
    @Override
    public void moveTo(Point2D point) {
        //Moves the top-center point to specified point
        getPaddleModel().setBallPoint(point);

        updatePaddleView();
    }


    /**
     * Sets the moving direction of the paddle to left, with default movement
     * amount.
     */
    public void moveLeft() {
        setMoveAmount(-DEF_MOVE_AMOUNT);
    }

    /**
     * Sets the moving direction of the paddle to right, with default movement
     * amount.
     */
    public void moveRight() {
        setMoveAmount(DEF_MOVE_AMOUNT);
    }

    /**
     * Stops the paddle by setting the movement amount of paddle to <b>0</b>.
     */
    public void stop() {
        setMoveAmount(0);
    }


    /**
     * Checks if the ball impacts the paddle.
     * <p>
     * Returns true only if both the center point and the down point of the ball
     * within the paddle, otherwise false.
     * <pre> {@code
     *  Modified from the original source code:
     *  public boolean impact(Ball b){
     *      return paddleFace.contains(b.getPosition())
     *              && paddleFace.contains(b.down);
     *  }
     * } </pre>
     *
     * @param ball the instance of class Ball
     * @return true if the ball impacts the paddle
     * @see Ball
     */
    public boolean impact(Ball ball) {
        return getPaddleView().getLayoutBounds().contains(ball.getCenterPoint())
                   && getPaddleView().getLayoutBounds().contains(
                       ball.getDownPoint());
    }

}
