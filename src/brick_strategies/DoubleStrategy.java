package brick_strategies;

import bricker.main.GameConstants;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.collisions.GameObjectCollection;
import danogl.GameObject;
import danogl.util.Vector2;
import gameobjects.Brick;
import java.util.Random;

public class DoubleStrategy extends BasicCollisionStrategy implements  CollisionStrategy {

    private static final int MIN_NEW_STRATEGIES = 2;
    private static final int MAX_NEW_STRATEGIES = 3;
    private static final int MAX_DOUBLE_STRATEGIES = 2;
    private static final int EXCLUDE_DOUBLE_AND_BASIC = 4;
    private static final int EXCLUDE_BASIC = 5;
    private static final int DOUBLE_STRATEGY_INDEX = 5;
    private final CollisionStrategy[] strategies;
    private final int doubleStrategyCounter;
    private final BricksStrategyFactory bricksStrategyFactory;
    private final GameObjectCollection gameObjects;
    private final Counter brickCounter;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final Brick[][] bricksGrid; //

    public DoubleStrategy(GameObjectCollection gameObjects,
                          Counter brickCounter,
                          ImageReader imageReader, SoundReader soundReader,
                          UserInputListener inputListener,
                          Brick[][] bricksGrid, final int doubleStrategyCounter) {

        super(gameObjects, brickCounter);
        this.doubleStrategyCounter = doubleStrategyCounter;
        this.gameObjects = gameObjects;
        this.brickCounter = brickCounter;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.bricksGrid = bricksGrid;
        this.bricksStrategyFactory = new BricksStrategyFactory(
                gameObjects,
                brickCounter,
                imageReader,
                soundReader,
                inputListener,
                bricksGrid
        );

        if(this.doubleStrategyCounter < MAX_DOUBLE_STRATEGIES) {
            this.strategies = new CollisionStrategy[MAX_NEW_STRATEGIES];
        }
        else {
                this.strategies = new CollisionStrategy[MIN_NEW_STRATEGIES];
            }
        }

    @Override
    public void onCollision(GameObject firstObject, GameObject secondObject) {

        Random random = new Random();
        super.onCollision(firstObject, secondObject);

        for(int i = 0; i < strategies.length; i++) {
            int chosenStrategy = random.nextInt(EXCLUDE_BASIC) + 1;
            if(chosenStrategy == DOUBLE_STRATEGY_INDEX && doubleStrategyCounter < MAX_DOUBLE_STRATEGIES) {

                strategies[i] = new DoubleStrategy(
                        gameObjects,
                        brickCounter,
                        imageReader,
                        soundReader,
                        inputListener,
                        bricksGrid,
                        doubleStrategyCounter + 1);
            }

            if(doubleStrategyCounter < MAX_DOUBLE_STRATEGIES) {
                strategies[i] = bricksStrategyFactory.getStrategy(
                        EXCLUDE_DOUBLE_AND_BASIC);
            }

        }

        for(CollisionStrategy strategy : strategies) {
            strategy.onCollision(firstObject, secondObject);
        }

    }
}