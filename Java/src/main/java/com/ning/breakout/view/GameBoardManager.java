package com.ning.breakout.view;


import com.ning.breakout.model.BallModel;
import com.ning.breakout.model.PaddleModel;
import com.ning.breakout.view.graphics.*;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;


/**
 * GameBoardManager is the class which takes the responsibility of controlling
 * some game objects (ball, paddle and bricks) and store the status info of the
 * game.
 *
 * @see Ball
 * @see Paddle
 * @see Brick
 * @see BrickFactory
 *
 * @author Ning ZHU - modified
 */
public class GameBoardManager {

    private final int BALL_COUNT = 3;


    private Rectangle m_DrawArea;

    private Point2D m_StartPoint;

    private Ball m_Ball;
    private Paddle m_Player;
    private Brick[] m_Bricks;

    /**
     * The container of ball's view, paddle's view and bricks' view
     * (cracks' view).
     */
    private Group m_Container;

    /**
     * The current level of the game.
     */
    private int m_Level;

    /**
     * The number of bricks remaining in the current level of the game.
     */
    private int m_LevelBrickCount;

    /**
     * The number of balls (chance) user still has.
     */
    private int m_BallCount;

    private boolean m_BallLost;

    /**
     * Brick factory used to make bricks for the game board.
     */
    private BrickFactory m_BrickFactory;

    /**
     * The score user gets in this round of game.
     */
    private int m_GameScore;

    /**
     * Media player to play the music when specific events happen.
     */
    private MediaPlayer m_ImpactPaddleSound;
    private MediaPlayer m_BreakBrickSound;
    private MediaPlayer m_BallLostSound;


    private Rectangle getDrawArea() {
        return this.m_DrawArea;
    }

    private Point2D getStartPoint() {
        return this.m_StartPoint;
    }

    /**
     * @return the Ball instance of the game board
     */
    public Ball getBall() {
        return this.m_Ball;
    }

    /**
     * @return the Paddle instance of the game board
     */
    public Paddle getPlayer() {
        return this.m_Player;
    }

    /**
     * @return the array of Brick instance of the game board
     */
    public Brick[] getBricks() {
        return this.m_Bricks;
    }

    /**
     * @return the container of the view of some game objects (ball, paddle and
     * bricks).
     * @see Group
     */
    public Group getContainer() {
        return this.m_Container;
    }

    /**
     * @return the current level of the game
     */
    public int getLevel() {
        return this.m_Level;
    }

    /**
     * @return the number of bricks remaining in the current level of the game
     */
    public int getLevelBrickCount() {
        return this.m_LevelBrickCount;
    }

    /**
     * @return the number of balls user still has in this round of game
     */
    public int getBallCount() {
        return this.m_BallCount;
    }

    /**
     * Returns the lost status of the ball, true if the ball is lost.
     *
     * @return true if the ball is lost
     */
    public boolean getBallLost() {
        return this.m_BallLost;
    }

    private BrickFactory getBrickFactory() {
        return m_BrickFactory;
    }

    /**
     * Returns the score user gets in this round of game.
     *
     * @return the score user gets in this round of game
     */
    public int getGameScore() {
        return m_GameScore;
    }

    private MediaPlayer getImpactPaddleSound() {
        return this.m_ImpactPaddleSound;
    }

    private MediaPlayer getBreakBrickSound() {
        return this.m_BreakBrickSound;
    }

    private MediaPlayer getBallLostSound() {
        return this.m_BallLostSound;
    }


    private void setDrawArea(Rectangle area) {
        this.m_DrawArea = area;
    }

    private void setStartPoint(Point2D startPoint) {
        this.m_StartPoint = startPoint;
    }

    private void setBall(Ball ball) {
        this.m_Ball = ball;
    }

    private void setPlayer(Paddle player) {
        this.m_Player = player;
    }

    private void setBricks(Brick[] bricks) {
        this.m_Bricks = bricks;
    }

    private void setContainer(Group container) {
        this.m_Container = container;
    }

    private void setLevel(int level) {
        this.m_Level = level;
    }

    private void setLevelBrickCount(int brickCount) {
        this.m_LevelBrickCount = brickCount;
    }

    private void setBallCount(int ballCount) {
        this.m_BallCount = ballCount;
    }

    private void setBallLost(boolean ballLost) {
        this.m_BallLost = ballLost;
    }

    private void setBrickFactory(BrickFactory factory) {
        this.m_BrickFactory = factory;
    }

    private void setGameScore(int score) {
        this.m_GameScore = score;
    }

    /**
     * Sets the MediaPlayer of the game board which used to play the sound when
     * the ball impacts the paddle.
     *
     * @param impactPaddleSound MediaPlayer instance used to play the sound when
     *                          the ball impacts the paddle
     */
    public void setImpactPaddleSound(MediaPlayer impactPaddleSound) {
        m_ImpactPaddleSound = impactPaddleSound;
    }

    /**
     * Sets the MediaPlayer of the game board which used to play the sound when
     * the ball breaks the brick.
     *
     * @param breakBrickSound MediaPlayer instance used to play the sound when
     *                        the ball breaks the brick
     */
    public void setBreakBrickSound(MediaPlayer breakBrickSound) {
        m_BreakBrickSound = breakBrickSound;
    }

    /**
     * Sets the MediaPlayer of the game board which used to play the sound when
     * the ball is lost.
     *
     * @param ballLostSound MediaPlayer instance used to play the sound when the
     *                      ball is lost
     */
    public void setBallLostSound(MediaPlayer ballLostSound) {
        m_BallLostSound = ballLostSound;
    }


    /**
     * The class constructor specifies the boundary of the game board, creates
     * game objects and initializes the game status info.
     * <pre> {@code
     *  Modified from the original source code:
     *  public Wall(Rectangle drawArea, int brickCount, int lineCount,
     *  double brickDimensionRatio, Point ballPos){
     *
     *      this.startPoint = new Point(ballPos);
     *
     *      levels = makeLevels(drawArea,brickCount,lineCount,
     *                                                  brickDimensionRatio);
     *      level = 0;
     *
     *      ballCount = 3;
     *      ballLost = false;
     *
     *      rnd = new Random();
     *
     *      makeBall(ballPos);
     *      int speedX,speedY;
     *      do{
     *          speedX = rnd.nextInt(5) - 2;
     *      }while(speedX == 0);
     *      do{
     *         speedY = -rnd.nextInt(3);
     *      }while(speedY == 0);
     *
     *      ball.setSpeed(speedX,speedY);
     *
     *      player = new Paddle((Point) ballPos.clone(),150,10, drawArea);
     *
     *      area = drawArea;
     *  }
     * } </pre>
     *
     * @param drawArea the boundary of the game board
     * @param brickCount the number of bricks in a level
     * @param lineCount the number of brick rows (lines) in a level
     * @param brickDimensionRatio the ratio of the width and height of the brick
     * @param ballPos the initial position of ball and paddle
     * @see Ball#Ball(BallModel, Group)
     * @see Paddle#Paddle(PaddleModel, Rectangle, Group)
     * @see Brick
     * @see BrickFactory#makeLevel(int, Rectangle)
     */
    public GameBoardManager(Rectangle drawArea, int brickCount, int lineCount,
                            double brickDimensionRatio, Point2D ballPos) {
        setContainer(new Group());

        setBall(new RubberBall(ballPos, getContainer()));
        getBall().resetSpeed();

        setPlayer(new RubberPaddle(ballPos, drawArea, getContainer()));

        setDrawArea(drawArea);
        setStartPoint(ballPos);

        setBrickFactory(new BrickFactory(
            getContainer(), brickCount, lineCount, brickDimensionRatio));

        setBricks(makeLevel());
        setBallCount(BALL_COUNT);
        setLevelBrickCount(getBricks().length);
    }


    private Brick[] makeLevel() {
        return getBrickFactory().makeLevel(getLevel(), getDrawArea());
    }


    /**
     * Moves the ball and the paddle by their speed.
     * @see Paddle#move()
     * @see Ball#move()
     */
    public void move() {
        getPlayer().move();
        getBall().move();
    }

    /**
     * Finds and handles the impact.
     * <p>
     * Notices that when ball impacts the border (left or right boundary of the
     * game board), the ball also can impact the paddle or brick at the same
     * time.
     * <p>
     * When the speed of the ball is too fast (changed by using the debug
     * console), misjudgment may occur.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void findImpacts(){
     *      if(player.impact(ball)){
     *          ball.reverseY();
     *      }
     *      else if(impactWall()){
     *          brickCount--;
     *      }
     *      else if(impactBorder()) {
     *          ball.reverseX();
     *      }
     *      else if(ball.getPosition().getY() < area.getY()){
     *          ball.reverseY();
     *      }
     *      else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
     *          ballCount--;
     *          ballLost = true;
     *      }
     *  }
     * } </pre>
     *
     * @see Ball
     */
    public void handleImpacts() {
        if (impactPaddle()) {
            //Solves the problem that the ball may stick on the paddle if the
            //speed of ball is too slow
            if (getBall().getSpeedY() > 0) {
                getBall().reverseY();

                getImpactPaddleSound().play();
                getImpactPaddleSound().seek(
                    getImpactPaddleSound().getStartTime());
            }
        } else if (impactBrick()) {
            setLevelBrickCount(getLevelBrickCount() - 1);

            getBreakBrickSound().play();
            getBreakBrickSound().seek(getBreakBrickSound().getStartTime());
        } else if (getBall().getCenterPoint().getY() < getDrawArea().getY()) {
            getBall().reverseY();
        } else if (getBall().getCenterPoint().getY() >
                       getDrawArea().getY() + getDrawArea().getHeight()) {
            setBallCount(getBallCount() - 1);
            setBallLost(true);

            if (getBallCount() != 0) {
                getBallLostSound().play();
                getBallLostSound().seek(getBallLostSound().getStartTime());
            }
        }
        //Solve the problem that the ball may move out of the draw area
        if (impactBorder()) {
            getBall().reverseX();
        }
    }

    /**
     * Checks whether the ball impacts the paddle.
     */
    private boolean impactPaddle() {
        return getPlayer().impact(getBall());
    }

    /**
     * Checks whether the ball impacts a brick.
     */
    private boolean impactBrick() {
        for (Brick brick : getBricks()) {
            switch (brick.findImpact(getBall())) {

                case Brick.UP_IMPACT -> {
                    getBall().reverseY();
                    return brick.setImpact(
                        getBall().getDownPoint(), Brick.UP_IMPACT);
                }

                case Brick.DOWN_IMPACT -> {
                    getBall().reverseY();
                    return brick.setImpact(
                        getBall().getUpPoint(), Brick.DOWN_IMPACT);
                }

                case Brick.LEFT_IMPACT -> {
                    getBall().reverseX();
                    return brick.setImpact(
                        getBall().getRightPoint(), Brick.LEFT_IMPACT);
                }

                case Brick.RIGHT_IMPACT -> {
                    getBall().reverseX();
                    return brick.setImpact(
                        getBall().getLeftPoint(), Brick.RIGHT_IMPACT);
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the ball impacts the border (left or right boundary of the
     * draw area).
     */
    private boolean impactBorder() {
        Point2D point = getBall().getCenterPoint();
        return ((point.getX() < getDrawArea().getX()) ||
                    (point.getX() >
                         (getDrawArea().getX() + getDrawArea().getWidth())));
    }


    /**
     * @return true if user break all bricks in the current level
     */
    public boolean isLevelPassed() {
        return getLevelBrickCount() == 0;
    }

    /**
     * @return true if user run out of the ball
     */
    public boolean isGameFail() {
        return getBallCount() == 0;
    }

    /**
     * @return false if user pass all the levels
     */
    public boolean hasNextLevel() {
        int TOTAL_NUMBER_0F_LEVELS = 6;
        return getLevel() < TOTAL_NUMBER_0F_LEVELS;
    }


    private void removeBricks() {
        for (int i = 0; i < getBricks().length; i++) {
            //Calls repair() method to clean the cracks of the brick
            getBricks()[i].repair();
            getContainer().getChildren().remove(getBricks()[i].getBrickView());
        }
    }


    /**
     * Adds the score get in the current level to the total score before
     * entering the next level or failing the game.
     */
    public void getBricksScore() {
        for (Brick brick : getBricks()) {
            setGameScore(getGameScore() + brick.getScore());
        }
    }

    /**
     * Calculates the extra points for passing the level of the game.
     */
    public void getLevelScore() {
        int PASS_LEVEL_BASE = 5;
        int levelPoint = (getLevel() + 1) * PASS_LEVEL_BASE * getBallCount();
        setGameScore(getGameScore() + levelPoint);
    }


    /**
     * Moves the ball and paddle to the start point, resets the speed of ball
     * and sets the lost status of the ball to false.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void ballReset(){
     *      player.moveTo(startPoint);
     *      ball.moveTo(startPoint);
     *      int speedX,speedY;
     *      do{
     *          speedX = rnd.nextInt(5) - 2;
     *      }while(speedX == 0);
     *      do{
     *          speedY = -rnd.nextInt(3);
     *      }while(speedY == 0);
     *
     *      ball.setSpeed(speedX,speedY);
     *      ballLost = false;
     *  }
     * } </pre>
     */
    public void resetBall() {
        getBall().moveTo(getStartPoint());
        getPlayer().moveTo(getStartPoint());
        getBall().resetSpeed();
        setBallLost(false);
    }

    /**
     * Repairs all bricks and updates the brick count.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void wallReset(){
     *      for(Brick b : bricks)
     *          b.repair();
     *      brickCount = bricks.length;
     *      ballCount = 3;
     *  }
     * } </pre>
     *
     * @see Brick#repair()
     */
    public void resetBricks() {
        for (int i = 0; i < getBricks().length; i++) {
            getBricks()[i].repair();
        }

        setLevelBrickCount(getBricks().length);
    }

    /**
     * Resets the ball count of the game board.
     */
    public void resetBallCount() {
        setBallCount(BALL_COUNT);
    }


    /**
     * Sets the game to the next level.
     * <pre> {@code
     *  Modified from the original source code:
     *  public void nextLevel(){
     *      bricks = levels[level++];
     *      this.brickCount = bricks.length;
     *  }
     * } </pre>
     */
    public void nextLevel() {
        resetBall();
        getBricksScore();
        removeBricks();

        setLevel(getLevel() + 1);
        setBricks(makeLevel());

        setLevelBrickCount(getBricks().length);
        resetBallCount();
    }

    /**
     * Restarts the current level of the game.
     */
    public void restartLevel() {
        resetBall();
        resetBricks();
        resetBallCount();
    }

    /**
     * Starts a new round of game from the first level.
     */
    public void replayGame() {
        setGameScore(0);

        resetBall();
        resetBallCount();

        setLevel(0);
        removeBricks();
        setBricks(makeLevel());
        setLevelBrickCount(getBricks().length);
    }

}
