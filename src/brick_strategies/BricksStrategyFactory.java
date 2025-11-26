package brick_strategies;

import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import gameobjects.Brick;
import bricker.main.GameConstants;
import java.util.Random;

public class BricksStrategyFactory {
    private static final int SPECIAL_BRICKS_COUNT = 5;
    private static final int PUCKS_STRATEGY_INDEX = 1;
    private static final int EXTRA_PADDLE_STRATEGY_INDEX = 2;
    private static final int EXPLODE_STRATEGY_INDEX = 3;
    private static final int LIVES_STRATEGY_INDEX = 4;
    private static final int DOUBLE_STRATEGY_INDEX = 5;
    private static final int STRATEGY_SAMPLE_SPACE = 10;
    public static final int SET_DOUBLE_STRATEGY_COUNTER = 1;

    private final GameObjectCollection gameObjects;
    private final Counter brickCounter;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;
    private final Brick[][] bricksGrid; //

    public BricksStrategyFactory(GameObjectCollection gameObjects,
                                 Counter brickCounter,
                                 ImageReader imageReader, SoundReader soundReader,
                                 UserInputListener inputListener,
                                 Brick[][] bricksGrid) {

        this.gameObjects = gameObjects;
        this.brickCounter = brickCounter;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.windowDimensions = GameConstants.WINDOW_DIMENSIONS;
        this.bricksGrid = bricksGrid;
    }

    public CollisionStrategy getStrategy(final int strategySampleSpace) {
        Random random = new Random();
        int chosenStrategy = random.nextInt(strategySampleSpace) + 1;

        return switch (chosenStrategy) {
            case PUCKS_STRATEGY_INDEX -> // Puck
                    new PuckStrategy(gameObjects, imageReader, soundReader, brickCounter);
            case EXTRA_PADDLE_STRATEGY_INDEX -> // Extra Paddle
                    new ExtraPaddleStrategy(gameObjects, brickCounter, imageReader, inputListener);
            case EXPLODE_STRATEGY_INDEX -> // Exploding
                    new ExplodingBrickStrategy(gameObjects, brickCounter, bricksGrid, soundReader);

            // case LIVES_STRATEGY_INDEX -> // Lives
                // TODO: Implement Recover Life Strategy

            case DOUBLE_STRATEGY_INDEX -> // Double
                    new DoubleStrategy(gameObjects, brickCounter, imageReader, soundReader,
                            inputListener, bricksGrid, SET_DOUBLE_STRATEGY_COUNTER);
            default -> new BasicCollisionStrategy(gameObjects, brickCounter);
        };
    }

    public static void resetStrategies() {
        ExtraPaddleStrategy.resetCounter();
        // Reset other strategy-related counters if needed in the future
    }
}



