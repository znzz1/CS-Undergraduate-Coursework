package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.scene.Group;

/**
 * Golden brick is the concrete class extends from the abstract class
 * CrackedBrick, with full strength of <b>4</b>.
 *
 * @see Brick
 * @see CrackedBrick
 *
 * @author Ning ZHU
 */
public class GoldenBrick extends CrackedBrick {

    /**
     * The full strength of the golden brick.
     * <p>
     * Golden brick could withstand four effective impacts.
     */
    private static final int GOLDEN_STRENGTH = 4;


    /**
     * The class constructor sets the model and the view container of the golden
     * brick, binds the layout of the view of the golden brick with the model,
     * adds the view to the container, specifies the number of effective impacts
     * the golden brick can withstand and initializes the crack array of the
     * golden brick by calling the constructor of super class.
     *
     * @param model the model of golden brick
     * @param group the container of golden brick's view and crack's view
     * @see CrackedBrick#CrackedBrick(BrickModel, int, Group)
     */
    public GoldenBrick(BrickModel model, Group group) {
        super(model, GOLDEN_STRENGTH, group);
    }

}
