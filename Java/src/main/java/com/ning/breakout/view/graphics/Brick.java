package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;


/**
 * Brick is the abstract base class for all brick classes which defines the
 * common attributes and behaviors of all brick classes.
 * <p>
 * The layout of the view of the brick binds with the model of the brick. By
 * adding the view of the brick into container, the brick could be displayed
 * onto the stage.
 * <p>
 * By extending the abstract class Brick, sub-classes could introduce new
 * features like making cracks on brick after impact or ignoring the impact.
 *
 * @see BrickModel
 *
 * @author Ning ZHU - modified
 */
abstract public class Brick {

    /**
     * Indicates the impact direction when the ball impacts the up part of the
     * brick.
     */
    public static final int UP_IMPACT = 100;

    /**
     * Indicates the impact direction when the ball impacts the down part of the
     * brick.
     */
    public static final int DOWN_IMPACT = 200;

    /**
     * Indicates the impact direction when the ball impacts the left part of the
     * brick.
     */
    public static final int LEFT_IMPACT = 300;

    /**
     * Indicates the impact direction when the ball impacts the right part of
     * the brick.
     */
    public static final int RIGHT_IMPACT = 400;


    private BrickModel m_BrickModel;

    private Group m_Container;

    private ImageView m_BrickView;

    /**
     * The number of effective impacts the brick can withstand in total.
     */
    private int m_FullStrength;

    /**
     * The number of effective impacts the brick still can withstand.
     */
    private int m_CurStrength;

    /**
     * The broken status of the brick.
     * <p>
     * Broken brick will be removed from the container and can not be impacted.
     */
    private boolean m_Broken;

    /**
     * The current score of the brick.
     * <p>
     * The brick will get its score when it is broken.
     * <p>
     * The score usually equals to the full strength of the brick, except
     * bricks like steel brick which could ignore the impact.
     */
    private int m_Score;


    private BrickModel getBrickModel() {
        return this.m_BrickModel;
    }

    /**
     * Returns the container of brick's view.
     *
     * @return the container of brick's view
     * @see Group
     */
    public Group getContainer() {
        return this.m_Container;
    }

    /**
     * Returns the view of the brick.
     * <p>
     * The layout of the ImageView object binds with the model of the brick. By
     * setting the source image of the ImageView object and adding it to the
     * container, different bricks can be displayed onto the screen.
     *
     * @return the view of the brick
     * @see ImageView
     */
    public ImageView getBrickView() {
        return this.m_BrickView;
    }

    /**
     * Returns the number of effective impacts the brick can withstand in total.
     *
     * @return the number of effective impacts the brick can withstand in total
     */
    public int getFullStrength() {
        return this.m_FullStrength;
    }

    private int getCurStrength() {
        return this.m_CurStrength;
    }

    /**
     * Returns the broken status of the brick.
     * <p>
     * The brick will be set to broken when the current strength of the brick
     * drops to <b>0</b>.
     *
     * @return true if the brick is broken
     */
    public boolean isBroken() {
        return this.m_Broken;
    }

    /**
     * Returns <b>0</b> if the brick is not broken, otherwise the specified
     * score for breaking the brick.
     *
     * @return <b>0</b> or the specified score for breaking the brick
     */
    public int getScore() {
        return m_Score;
    }


    private void setBrickModel(BrickModel model) {
        this.m_BrickModel = model;
    }

    private void setContainer(Group container) {
        this.m_Container = container;
    }

    private void setBrickView(ImageView view) {
        this.m_BrickView = view;
    }

    private void setFullStrength(int fullStrength) {
        this.m_FullStrength = fullStrength;
    }

    /**
     * Used to update the number of effective impacts the brick still can
     * withstand.
     */
    private void setCurStrength(int curStrength) {
        this.m_CurStrength = curStrength;
    }

    /**
     * Sets the broken status of the brick.
     * <p>
     * Sets to true only if the current strength of the brick drops to <b>0</b>,
     * otherwise false.
     *
     * @param isBroken the broken status of the brick
     */
    public void setBroken(boolean isBroken) {
        this.m_Broken = isBroken;
    }

    /**
     * Sets the score of the brick.
     * <p>
     * Sets to 0 if the brick is not broken and sets to the specified score if
     * the brick is broken. (The score varies in different brick classes)
     *
     * @param score <b>0</b> or the specified score for breaking the brick
     */
    public void setScore(int score) {
        this.m_Score = score;
    }


    /**
     * The class constructor sets the model and the view container of the brick,
     * binds the layout of the view of the brick with the model, adds the view
     * to the container, and specifies the number of effective impacts that the
     * brick can withstand in total.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Brick(String name, Point pos,Dimension size,Color border,
     *  Color inner,int strength){
     *      rnd = new Random();
     *      broken = false;
     *      this.name = name;
     *      brickFace = makeBrickFace(pos,size);
     *      this.border = border;
     *      this.inner = inner;
     *      this.fullStrength = this.strength = strength;
     *  }
     * } </pre>
     *
     * @param model the model of brick
     * @param strength the number of effective impacts the brick can withstand
     *                 in total
     * @param group the container of brick's view
     * @see BrickModel
     * @see Group
     */
    public Brick(BrickModel model, int strength, Group group) {
        setBrickModel(model);
        setContainer(group);

        setBrickView(makeBrickView());

        getContainer().getChildren().add(getBrickView());

        setFullStrength(strength);
        setCurStrength(strength);
    }


    /**
     * Creates a new ImageView object and binds the layout of the object with
     * the model of the brick.
     * <p>
     * Returns the created object as the view of the brick.
     */
    private ImageView makeBrickView() {
        ImageView brickView = new ImageView();
        //Binds the layout
        brickView.setX(getBrickModel().getStartPoint().getX());
        brickView.setY(getBrickModel().getStartPoint().getY());
        brickView.setFitWidth(getBrickModel().getWidth());
        brickView.setFitHeight(getBrickModel().getHeight());

        return brickView;
    }


    /**
     * Simulates impact on brick.
     * <p>
     * After impact, the current strength of the brick should minus itself by
     * one and if the current strength drops to <b>0</b>, sets the broken status
     * of the brick to true (broken).
     * <pre> {@code
     *  Modified from the original source code:
     *  public void impact(){
     *      strength--;
     *      broken = (strength == 0);
     *  }
     * } </pre>
     */
    public void impact() {
        setCurStrength(getCurStrength() - 1);
        setBroken(getCurStrength() == 0);
    }

    /**
     * Sets impact on brick.
     * <p>
     * This method is used for brick classes which do not have cracks and cannot
     * ignore impact.
     * <p>
     * If the brick is broken after impact, the view of the brick will be
     * removed from the container (screen), and the score of the brick will be
     * set from <b>0</b> to the specified score (equals to the full strength of
     * the brick) for breaking it.
     * <p>
     * Notes that arguments are not used here, but for method overriding, these
     * arguments are needed.
     * <pre> {@code
     *  Modified from the original source code:
     *  public boolean setImpact(Point2D point, int dir){
     *      if(broken)
     *          return false;
     *      impact();
     *      return broken;
     *  }
     * } </pre>
     *
     * @param point the point where the ball impacts the brick
     * @param dir the impact direction
     * @return the broken status of the brick after impact
     */
    public boolean setImpact(Point2D point, int dir) {
        impact();
        if (isBroken()) {
            getContainer().getChildren().remove(getBrickView());
            setScore(getFullStrength());
        }
        return isBroken();
    }


    /**
     * Finds the direction where the ball impacts the brick.
     * <p>
     * Broken brick cannot be impacted, just returns <b>0</b>. For brick not
     * broken, returns <b>0</b> if no impact found, otherwise returns the number
     * which indicates the impact direction.
     * <p>
     * Determines the impact direction based on the position of the feature
     * points of the ball, the moving direction of the ball and the layout of
     * the brick's view (checks whether points within the view of the brick).
     * <pre> {@code
     *  Modified from the original source code:
     *  public final int findImpact(Ball b){
     *      if(broken)
     *          return 0;
     *      int out  = 0;
     *      if(brickFace.contains(b.right))
     *          out = LEFT_IMPACT;
     *      else if(brickFace.contains(b.left))
     *          out = RIGHT_IMPACT;
     *      else if(brickFace.contains(b.up))
     *          out = DOWN_IMPACT;
     *      else if(brickFace.contains(b.down))
     *          out = UP_IMPACT;
     *      return out;
     *  }
     * } </pre>
     *
     * @param ball the instance of class Ball
     * @return the impact direction
     */
    public int findImpact(Ball ball) {
        if (isBroken())
            return 0;
        //For brick not broken
        int out = 0;
        //The logic has been revised to minimise the misjudgment
        if (getBrickView().getLayoutBounds().contains(ball.getRightPoint())){
            if (ball.getSpeedX() > 0) {
                out = LEFT_IMPACT;
            } else {
                out = ball.getSpeedY() > 0 ? UP_IMPACT : DOWN_IMPACT;
            }
        }
        else if(getBrickView().getLayoutBounds().contains(ball.getLeftPoint())){
            if (ball.getSpeedX() < 0) {
                out = RIGHT_IMPACT;
            } else {
                out = ball.getSpeedY() > 0 ? UP_IMPACT : DOWN_IMPACT;
            }
        }
        else if (getBrickView().getLayoutBounds().contains(ball.getUpPoint())){
            if (ball.getSpeedY() < 0) {
                out = DOWN_IMPACT;
            } else {
                out = ball.getSpeedX() > 0 ? LEFT_IMPACT : RIGHT_IMPACT;
            }
        }
        else if(getBrickView().getLayoutBounds().contains(ball.getDownPoint())){
            if (ball.getSpeedY() > 0) {
                out = UP_IMPACT;
            } else {
                out = ball.getSpeedX() > 0 ? LEFT_IMPACT : RIGHT_IMPACT;
            }
        }
        return out;
    }


    /**
     * Repairs the brick.
     * <p>
     * This method is used for brick classes which do not have cracks.
     * <p>
     * Sets the current strength to the full strength. If the brick is broken,
     * additionally resets the score of brick to <b>0</b>, the broken status of
     * the brick to false and adds the view of the brick back to the container.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void repair() {
     *      broken = false;
     *      strength = fullStrength;
     *  }
     * } </pre>
     */
    public void repair() {
        setCurStrength(getFullStrength());
        if (!isBroken()) {
            return;
        }
        setScore(0);
        setBroken(false);
        getContainer().getChildren().add(getBrickView());
    }

}





