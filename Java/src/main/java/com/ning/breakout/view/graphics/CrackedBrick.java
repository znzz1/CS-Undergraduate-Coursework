package com.ning.breakout.view.graphics;


import com.ning.breakout.model.BrickModel;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Random;

/**
 * CrackedBrick is the abstract class extends from the abstract class Brick.
 * Compares to the Brick, CrackedBrick could make cracks on the brick when
 * the brick is impacted.
 *
 * @see Brick
 *
 * @author Ning ZHU - modified
 */
abstract public class CrackedBrick extends Brick {

    /**
     * The array stores the cracks of the cracked brick.
     */
    private Crack[] m_Cracks;

    /**
     * Counts the number of cracks the cracked brick currently has, used as
     * the index of the crack array.
     * <p>
     * Although it can be avoided by using full strength minus current strength,
     * for better code readability, I prefer to keep this field.
     */
    private int m_CrackCount;


    /**
     * Crack is the inner class of the class CrackedBrick which defines the
     * methods to create cracks on cracked brick.
     */
    public class Crack {

        private final Random RND = new Random();

        /**
         * Indicates the direction in which the crack extends.
         * <p>
         * Vertical if up or down impact and horizontal if left or right impact.
         */
        private final int VERTICAL = 500;
        private final int HORIZONTAL = 600;


        /**
         * To make the view of the crack, we need a lots of points and connects
         * the adjacent points.
         * <p>
         * We already got the start point and end point. To gets other points,
         * just draws a straight line between the start and end point, evenly
         * divides the straight line. And adds offset on the layout y of these
         * points.
         * <p>
         * Field m_CrackDepth indicates the normal offset bound (no jump) and
         * field m_Steps indicates the number of points/polyline.
         */
        private Path m_CrackView;
        private int m_CrackDepth;
        private int m_Steps;


        private Path getCrackView() {
            return this.m_CrackView;
        }

        private int getCrackDepth() {
            return this.m_CrackDepth;
        }

        private int getSteps() {
            return this.m_Steps;
        }


        private void setCrackView(Path path) {
            this.m_CrackView = path;
        }

        private void setCrackDepth(int depth) {
            this.m_CrackDepth = depth;
        }

        private void setSteps(int step) {
            this.m_Steps = step;
        }


        /**
         * The class constructor initializes the view of the crack, specifies
         * the normal bound of offset on y-axis when crack extends and the
         * times of crack propagation
         *
         * @param crackDepth the normal bound of offset on y-axis when crack
         *                   extends
         * @param steps the times of crack propagation
         */
        public Crack(int crackDepth, int steps) {
            setCrackView(new Path());
            setCrackDepth(crackDepth);
            setSteps(steps);
        }


        private Point2D getStart(Point2D point, ImageView bounds) {

            double posX = point.getX();
            double posY = point.getY();

            if (posX < bounds.getX())
                posX = bounds.getX();
            if (posX > bounds.getX() + bounds.getFitWidth())
                posX = bounds.getX() + bounds.getFitWidth();
            if (posY < bounds.getY())
                posY = bounds.getY();
            if (posY > bounds.getY() + bounds.getFitHeight())
                posY = bounds.getY() + bounds.getFitHeight();

            return new Point2D(posX, posY);
        }

        /**
         * Makes crack based on the impact point and impact direction.
         * <p>
         * Calls method makeRandomPoint(Point2D, Point2D, int) first to get the
         * end point, then calls the makeCrack(Point2D, Point2D) to make crack
         * between the impact point and the end point.
         */
        private void makeCrack(Point2D point, int direction) {
            ImageView bounds = CrackedBrick.super.getBrickView();

            Point2D start = getStart(point, bounds);

            Point2D lowBound;
            Point2D upBound;
            switch (direction) {
                case LEFT_IMPACT:
                    lowBound = new Point2D(
                        bounds.getX() + bounds.getFitWidth(), bounds.getY());
                    upBound = new Point2D(
                        bounds.getX() + bounds.getFitWidth(),
                        bounds.getY() + bounds.getFitHeight());
                    Point2D end = makeRandomPoint(lowBound, upBound, VERTICAL);
                    makeCrack(start, end);
                    break;
                case RIGHT_IMPACT:
                    lowBound = new Point2D(bounds.getX(), bounds.getY());
                    upBound = new Point2D(
                        bounds.getX(), bounds.getY() + bounds.getFitHeight());
                    end = makeRandomPoint(lowBound, upBound, VERTICAL);
                    makeCrack(start, end);
                    break;
                case UP_IMPACT:
                    lowBound = new Point2D(
                        bounds.getX(), bounds.getY() + bounds.getFitHeight());
                    upBound = new Point2D(
                        bounds.getX() + bounds.getFitWidth(),
                        bounds.getY() + bounds.getFitHeight());
                    end = makeRandomPoint(lowBound, upBound, HORIZONTAL);
                    makeCrack(start, end);
                    break;
                case DOWN_IMPACT:
                    lowBound = new Point2D(bounds.getX(), bounds.getY());
                    upBound = new Point2D(
                        bounds.getX() + bounds.getFitWidth(), bounds.getY());
                    end = makeRandomPoint(lowBound, upBound, HORIZONTAL);
                    makeCrack(start, end);
                    break;
            }
        }

        /**
         * Makes crack between two specified points.
         * <p>
         * Points in the middle part may jump (have bigger offset).
         */
        private void makeCrack(Point2D start, Point2D end) {
            getCrackView().getElements().add(
                new MoveTo(start.getX(), start.getY()));

            double stepWidth = (end.getX() - start.getX()) / getSteps();
            double stepHeight = (end.getY() - start.getY()) / getSteps();

            int bound = getCrackDepth();
            int JUMP_FACTOR = 3;
            int jump = bound * JUMP_FACTOR;

            double posX, posY;

            for (int curSteps = 1; curSteps < getSteps(); curSteps++) {
                posX = (curSteps * stepWidth) + start.getX();
                //Add offset on the layout y of the point
                posY = (curSteps * stepHeight) + start.getY() +
                           randomInBounds(bound);

                //Points in the middle part may jump
                if (inMiddle(curSteps, getSteps()))
                    posY += jumps(jump);

                getCrackView().getElements().add(new LineTo(posX, posY));
            }

            getCrackView().getElements().add(
                new LineTo(end.getX(), end.getY()));
        }

        /**
         * Gets the offset on the y-axis when the crack extends.
         */
        private int randomInBounds(int bound) {
            //The range of the offset [-bound, bound]
            int DOUBLE = 2;
            int n = (bound * DOUBLE) + 1;
            return RND.nextInt(n) - bound;
        }

        /**
         * Equally divides the line into three parts, checks whether the point
         * is in the middle part.
         */
        private boolean inMiddle(int cur, int total) {
            int CRACK_SECTIONS = 3;
            int low = (total / CRACK_SECTIONS);
            int up = low * (CRACK_SECTIONS - 1);

            return (cur > low) && (cur < up);
        }

        /**
         * Simulates the random jump on y-axis when the crack extends in the
         * middle.
         */
        private int jumps(int bound) {
            double JUMP_PROBABILITY = 0.25;
            if (RND.nextDouble() > (1 - JUMP_PROBABILITY))
                return randomInBounds(bound);
            return 0;

        }

        /**
         * Randomly finds a point (the end point) on the given line.
         */
        private Point2D makeRandomPoint(Point2D start, Point2D end, int dir) {
            Point2D out = new Point2D(0, 0);
            double pos;

            switch (dir) {
                case HORIZONTAL -> {
                    pos = RND.nextInt((int) end.getX() - (int) start.getX()) +
                              start.getX();
                    out = new Point2D(pos, end.getY());
                }
                case VERTICAL -> {
                    pos = RND.nextInt((int) end.getY() - (int) start.getY()) +
                              start.getY();
                    out = new Point2D(end.getX(), pos);
                }
            }
            return out;
        }

    }


    private Crack[] getCracks() {
        return this.m_Cracks;
    }

    private int getCrackCount() {
        return this.m_CrackCount;
    }


    private void setCracks(Crack[] cracks) {
        this.m_Cracks = cracks;
    }

    private void setCrackCount(int crackCount) {
        this.m_CrackCount = crackCount;
    }


    /**
     * The Class constructor sets the model and the view container of the
     * cracked brick, binds the layout of the view of the cracked brick with the
     * model, adds the view to the container, and specifies the number of
     * effective impacts the cracked brick can withstand in total by calling
     * the constructor of super class. Additionally, initializes the crack array
     * of the cracked brick with specified length (full strength - <b>1</b>).
     *
     * @param model the model of cracked brick
     * @param strength the number of effective impacts that the crack brick can
     *                 withstand in total
     * @param group the container of cracked brick's view and cracks' view
     * @see Brick#Brick(BrickModel, int, Group) 
     */
    public CrackedBrick(BrickModel model, int strength, Group group) {
        super(model, strength, group);
        setCracks(new Crack[strength - 1]);
    }


    /**
     * Simulates impact on cracked brick.
     * <p>
     * If the cracked brick is broken after impact, the view of the cracked
     * brick and the view of its cracks will be removed from the container, and
     * the score of cracked brick will be set from <b>0</b> to the specified
     * score (equals to the full strength) for breaking it.
     * <p>
     * Otherwise, if the cracked brick is not broken after impact, a new crack
     * will be created based on the impact point and the impact direction. Then
     * adds the crack into the crack array of the cracked brick, updates the
     * crack count and adds the view of the crack into the container.
     * <pre> {@code
     *  Modified from the original source code:
     *  public boolean setImpact(Point2D point, int dir) {
     *      if(super.isBroken())
     *          return false;
     *      super.impact();
     *      if(!super.isBroken()){
     *          crack.makeCrack(point,dir);
     *          updateBrick();
     *          return false;
     *      }
     *      return true;
     *  }
     * } </pre>
     *
     * @param point the point where the ball impacted the cracked brick
     * @param dir the impact direction
     * @return the broken status of the cracked brick after impact
     * @see Brick#setImpact(Point2D, int)
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        super.impact();
        if (isBroken()) {
            getContainer().getChildren().remove(getBrickView());
            for (Crack crack : getCracks()) {
                getContainer().getChildren().remove(crack.getCrackView());
            }
            setScore(getFullStrength());
        } else {
            int DEF_CRACK_DEPTH = 1;
            int DEF_STEPS = 20;
            Crack crack = new Crack(DEF_CRACK_DEPTH, DEF_STEPS);
            crack.makeCrack(point, dir);
            getContainer().getChildren().add(crack.getCrackView());
            getCracks()[getCrackCount()] = crack;
            setCrackCount(getCrackCount() + 1);
        }
        return isBroken();
    }


    /**
     * Repairs the cracked brick.
     * <p>
     * Removes the view of all cracks of the cracked brick from the container,
     * empties the crack array and sets the crack count to <b>0</b>.
     * <p>
     * Then, sets the current strength to the initial state (full strength). If
     * the cracked brick is broken, additionally sets the score of the cracked
     * brick to <b>0</b>, sets the broken status of the cracked brick to false
     * and adds the view of the cracked brick back to the container. (calls the
     * repair() method of super class)
     * <pre> {@code
     *  Modified from the original source code:
     *  public void repair(){
     *      super.repair();
     *      crack.reset();
     *      brickFace = super.brickFace;
     *  }
     * } </pre>
     *
     * @see Brick#repair()
     */
    @Override
    public void repair() {
        for (Crack crack : getCracks()) {
            if (crack != null) {
                getContainer().getChildren().remove(crack.getCrackView());
            } else {
                break;
            }
        }

        setCracks(new Crack[getFullStrength() - 1]);
        setCrackCount(0);

        super.repair();
    }

}
