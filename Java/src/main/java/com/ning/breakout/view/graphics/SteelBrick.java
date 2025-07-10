package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;

import java.util.Random;

/**
 * SteelBrick is the concrete class extend from the abstract class Brick, with
 * full strength of <b>1</b>.
 * <p>
 * SteelBrick has a new feature that the steel brick can probably ignore the
 * impact.
 *
 * @see Brick
 *
 * @author Ning ZHU - modified
 */
public class SteelBrick extends Brick {

    /**
     * The full strength of the steel brick.
     * <p>
     * Steel brick can only withstand one effective impact.
     */
    private static final int STEEL_STRENGTH = 1;


    /**
     * The Class constructor sets the model and the view container of the steel
     * brick, binds the layout of the view of the steel brick with the model,
     * adds the view to the container, and specifies the number of effective
     * impacts the steel brick can withstand in total by calling the constructor
     * of super class.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Brick3(Point point, Dimension size){
     *      super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
     *      rnd = new Random();
     *      brickFace = super.brickFace;
     *  }
     * } </pre>
     *
     * @param model the model of steel brick
     * @param group the container of steel brick's view
     * @see Brick#Brick(BrickModel, int, Group)
     */
    public SteelBrick(BrickModel model, Group group) {
        super(model, STEEL_STRENGTH, group);
    }


    /**
     * Simulates impact on steel brick
     * <p>
     * The impact probably invalid for steel brick. If the impact is
     * effective, the impact() method of super class will be called.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void impact(){
     *      if(rnd.nextDouble() < STEEL_PROBABILITY){
     *          super.impact();
     *      }
     *  }
     * } </pre>
     *
     * @see Brick#impact()
     */
    @Override
    public void impact() {
        double INVALID_IMPACT_PROBABILITY = 0.6;
        if (new Random().nextDouble() > INVALID_IMPACT_PROBABILITY) {
            super.impact();
        }
    }

    /**
     * Sets impact on steel brick.
     * <p>
     * If the steel brick is broken after impact, the view of the steel brick
     * will be removed from the container (screen), and the score of steel brick
     * will be set from <b>0</b> to specified score <b>3</b> for breaking it.
     * <p>
     * Notes that arguments are not used here, but for method overriding, these
     * arguments are needed.
     * <pre> {@code
     *  Modified from the original source code:
     *  public boolean setImpact(Point2D point, int dir){
     *      if(super.isBroken())
     *          return false;
     *      impact();
     *      return super.isBroken();
     *  }
     * } </pre>
     *
     * @param point the point where the ball impacts the steel brick
     * @param dir   the impact direction
     * @return the broken status of steel brick after impact
     * @see Brick#setImpact(Point2D, int)
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        impact();
        if (isBroken()) {
            getContainer().getChildren().remove(getBrickView());
            //Every steel brick worth 3 points
            int STEEL_BRICK_SCORE = 3;
            setScore(STEEL_BRICK_SCORE);
        }
        return isBroken();
    }

}
