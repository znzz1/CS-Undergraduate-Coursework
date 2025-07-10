package com.ning.breakout.view;

import com.ning.breakout.model.BrickModel;
import com.ning.breakout.view.graphics.*;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;


/**
 * BrickFactory is the class which takes the responsibility of making bricks for
 * all levels of the game.
 *
 * @see ClayBrick
 * @see SteelBrick
 * @see CementBrick
 * @see GoldenBrick
 *
 * @author Ning ZHU - modified
 */
public class BrickFactory {

    /**
     * Indicates the type of the brick.
     */
    private final int CLAY_BRICK = 1;
    private final int STEEL_BRICK = 2;
    private final int CEMENT_BRICK = 3;
    private final int GOLDEN_BRICK = 4;

    /**
     * The container of the view of the bricks.
     */
    private Group m_Group;

    /**
     * Pre-defined number of bricks in a level, not the real number of bricks.
     */
    private int m_BrickCount;

    /**
     * The number of bricks line in a level.
     */
    private int m_LineCount;

    /**
     * The ratio of the width and height of the brick.
     */
    private double m_BrickDimensionRatio;


    private Group getGroup() {
        return this.m_Group;
    }

    private int getBrickCount() {
        return this.m_BrickCount;
    }

    private int getLineCount() {
        return this.m_LineCount;
    }

    private double getBrickDimensionRatio() {
        return this.m_BrickDimensionRatio;
    }


    private void setGroup(Group group) {
        this.m_Group = group;
    }

    private void setBrickCount(int brickCount) {
        this.m_BrickCount = brickCount;
    }

    private void setLineCount(int lineCount) {
        this.m_LineCount = lineCount;
    }

    private void setBrickDimensionRatio(double ratio) {
        this.m_BrickDimensionRatio = ratio;
    }


    /**
     * The class constructor sets the container of the view of the bricks
     * produced in the brick factory, the number of bricks and the number of
     * brick lines (rows) in a level, and the ratio of the width and height of
     * the produced bricks.
     * <p>
     * The brickCount here is not the real number of bricks after making. To
     * make sure each line of bricks is full, the real number of the bricks
     * may slightly change.
     *
     * @param group the container of the view of produced bricks
     * @param brickCount the pre-defined number of bricks in a level
     * @param lineCount the number of bricks line in a level
     * @param brickDimensionRatio the ratio of the width and height of the brick
     * @see Group
     */
    public BrickFactory(Group group, int brickCount, int lineCount,
                        double brickDimensionRatio) {
        setGroup(group);
        setBrickCount(brickCount);
        setLineCount(lineCount);
        setBrickDimensionRatio(brickDimensionRatio);
    }


    /**
     * Checks whether the line is even line.
     */
    private boolean divisibleByTwo(int number) {
        return number % 2 == 0;
    }

    /**
     * Makes specified type of brick.
     */
    private Brick makeBrick(BrickModel model, int type) {
        return switch (type) {
            case CLAY_BRICK -> new ClayBrick(model, getGroup());
            case STEEL_BRICK -> new SteelBrick(model, getGroup());
            case CEMENT_BRICK -> new CementBrick(model, getGroup());
            case GOLDEN_BRICK -> new GoldenBrick(model, getGroup());
            default -> throw new IllegalArgumentException(String.format(
                "Unknown Type:%d\n", type));
        };
    }

    /**
     * Makes bricks with specified types for a level.
     */
    private Brick[] makeLevel(Rectangle drawArea, int typeA, int typeB) {
        int brickCnt = getBrickCount() - getBrickCount() % getLineCount();
        int brickOnLine = brickCnt / getLineCount();

        double HALF = 0.5;
        double centerL = brickOnLine * HALF - 1;
        double centerR = brickOnLine * HALF + 1;
        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / getBrickDimensionRatio();

        brickCnt += getLineCount() * HALF;
        Brick[] bricks = new Brick[brickCnt];

        Point2D point;
        int i;
        for (i = 0; i < bricks.length; i++) {
            int line = i / brickOnLine;
            if (line == getLineCount())
                break;
            int x = i % brickOnLine;
            double posX = x * brickLen;
            posX = divisibleByTwo(line) ? posX : (posX - (brickLen * HALF));
            double posY = (line) * brickHgt;
            point = new Point2D(posX, posY);
            boolean typeBool = ((divisibleByTwo(line) && divisibleByTwo(i))
                                    || (!divisibleByTwo(line)
                                            && x > centerL && x <= centerR));
            bricks[i] = typeBool
                            ? makeBrick(
                                new BrickModel(
                                point, (int) brickLen, (int) brickHgt), typeA)
                            : makeBrick(new BrickModel(
                                point, (int) brickLen, (int) brickHgt), typeB);
        }
        int DOUBLE = 2;
        for (double posY = brickHgt; i < bricks.length;
             i++, posY += DOUBLE * brickHgt) {
            double posX = (brickOnLine * brickLen) - (brickLen * HALF);
            point = new Point2D(posX, posY);
            bricks[i] = makeBrick(
                new BrickModel(point, (int) brickLen, (int) brickHgt), typeA);
        }
        return bricks;
    }

    /**
     * Makes bricks of specified level for the game.
     * <p>
     * The position of the bricks depends on the draw area of the game board
     * (boundary of the game board).
     *
     * @param level the game level user will play
     * @param drawArea the boundary of the game board
     * @return bricks of specified level
     * @see Rectangle
     * @see Brick
     */
    public Brick[] makeLevel(int level, Rectangle drawArea) {

        final int LEVEL_ONE = 0;
        final int LEVEL_TWO = 1;
        final int LEVEL_THREE = 2;
        final int LEVEL_FOUR = 3;
        final int LEVEL_FIVE = 4;
        final int LEVEL_SIX = 5;
        final int LEVEL_SEVEN = 6;

        return switch (level) {
            case LEVEL_ONE -> makeLevel(
                drawArea, CLAY_BRICK, CLAY_BRICK);
            case LEVEL_TWO -> makeLevel(
                drawArea, CLAY_BRICK, CEMENT_BRICK);
            case LEVEL_THREE -> makeLevel(
                drawArea, CLAY_BRICK, STEEL_BRICK);
            case LEVEL_FOUR -> makeLevel(
                drawArea, STEEL_BRICK, CEMENT_BRICK);
            case LEVEL_FIVE -> makeLevel(
                drawArea, CEMENT_BRICK, GOLDEN_BRICK);
            case LEVEL_SIX -> makeLevel(
                drawArea, GOLDEN_BRICK, STEEL_BRICK);
            case LEVEL_SEVEN -> makeLevel(
                drawArea, STEEL_BRICK, STEEL_BRICK);
            default -> throw new IllegalStateException(
                "Unexpected level: " + level);
        };
    }

}
