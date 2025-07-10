package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.scene.Group;

/**
 * CementBrick is the concrete class extends from the abstract class
 * CrackedBrick, with full strength of <b>2</b>.
 *
 * @see Brick
 * @see CrackedBrick
 *
 * @author Ning ZHU - modified
 */
public class CementBrick extends CrackedBrick {

    /**
     * The full strength of the cement brick.
     * <p>
     * Cement brick could withstand two effective impacts.
     */
    private static final int CEMENT_STRENGTH = 2;


    /**
     * The class constructor sets the model and the view container of the cement
     * brick, binds the layout of the view of the cement brick with the model,
     * adds the view to the container, specifies the number of effective impacts
     * the cement brick can withstand and initializes the crack array of the
     * cement brick by calling the constructor of super class.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Brick1(Point point, Dimension size){
     *      super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
     *      crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
     *      brickFace = super.brickFace;
     *  }
     * } </pre>
     *
     * @param model the model of cement brick
     * @param group the container of cement brick's view and crack's view
     * @see CrackedBrick#CrackedBrick(BrickModel, int, Group)
     */
    public CementBrick(BrickModel model, Group group) {
        super(model, CEMENT_STRENGTH, group);
    }

}
