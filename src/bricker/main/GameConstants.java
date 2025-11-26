package bricker.main;

import danogl.util.Vector2;

public class GameConstants {
    // the heart image properties
    public static final float HEART_WIDTH = 30;
    public static final float HEART_HEIGHT = 30;
    public static final Vector2 HEART_DIMENSIONS = new Vector2(HEART_WIDTH, HEART_HEIGHT);
    // the heart image path
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    // initial number of lives - according to the assignment's description
    public static final int INITIAL_LIVES = 3;
    public static final String MAIN_PADDLE_TAG = "MAIN_PADDLE";

    public static final float WINDOW_WIDTH = 700;
    public static final float WINDOW_HEIGHT = 500;
    public static final Vector2 WINDOW_DIMENSIONS = new Vector2(WINDOW_WIDTH, WINDOW_HEIGHT);
    public static final float BALL_SIZE = 20;
    public static final float PUCK_SIZE= BALL_SIZE *0.75f;
    public static final Vector2 BALL_DIMENSIONS = new Vector2(BALL_SIZE, BALL_SIZE);
    public static final float LEFT_WALL_WIDTH = 15;
    public static final float LEFT_WALL_HEIGHT = 500;
    public static final float RIGHT_WALL_WIDTH = 15;
    public static final float RIGHT_WALL_HEIGHT = 500;
    public static final float TOP_WALL_WIDTH = 700;
    public static final float TOP_WALL_HEIGHT = 15;
    public static final int DEFAULT_BRICK_ROWS=7;
    public static final int DEFAULT_BRICK_COLUMNS=8;
    public static final int STRATEGY_SAMPLE_SPACE = 10;
    public static final String BALL_IAMGE = "assets/ball.png";
    public static final String BALL_BLOP_COLLISION_SOUND = "assets/blop.wav";
    public static final String ASSETS_PADDLE_PNG = "assets/paddle.png";
    public static final int MAX_LIVES = 4;
    public static final float PADDLE_WIDTH = 100;
    public static final float PADDLE_HEIGHT = 15;
    public static final Vector2 PADDLE_DIMENSIONS = new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT);
    public static final String PADDLE_IMAGE = "assets/paddle.png";
    public static final String BALL_TAG = "ball";


    private GameConstants() {}

}
