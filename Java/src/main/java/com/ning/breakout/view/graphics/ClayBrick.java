package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.scene.Group;

/**
 * ClayBrick is the concrete class extends from the abstract class Brick, with
 * full strength of <b>1</b>.
 *
 * @see Brick
 *
 * @author Ning ZHU - modified
 */
public class ClayBrick extends Brick {

    /**
     * The full strength of the clay brick.
     * <p>
     * Clay brick can only withstand one effective impact.
     */
    private static final int CLAY_STRENGTH = 1;


    /**
     * The Class constructor sets the model and the view container of the clay
     * brick, binds the layout of the view of the clay brick with the model,
     * adds the view to the container, and specifies the number of effective
     * impacts the clay brick can withstand in total by calling the constructor
     * of super class.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Brick2(Point point, Dimension size){
     *      super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
     *  }
     * } </pre>
     *
     * @param model the model of clay brick
     * @param group the container of clay brick's view
     * @see Brick#Brick(BrickModel, int, Group)
     */
    public ClayBrick(BrickModel model, Group group) {
        super(model, CLAY_STRENGTH, group);
    }

}
