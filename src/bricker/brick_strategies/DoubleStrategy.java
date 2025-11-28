package bricker.brick_strategies;

import bricker.gameobjects.Brick;
import bricker.gameobjects.LivesManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class DoubleStrategy extends BasicCollisionStrategy implements CollisionStrategy {
    // =========================== private constats =========================== //
    /* maximum number of double strategies allowed */
    private static final int MAX_DOUBLE_STRATEGIES = 2;
    /* exclude double and basic strategies index */
    private static final int EXCLUDE_DOUBLE_AND_BASIC = 4;
    /* exclude basic strategy index */
    private static final int EXCLUDE_BASIC = 5;
    /* index for double strategy to be chosen in 1/10 chance */
    private static final int DOUBLE_STRATEGY_INDEX = 5;

    // =========================== fields =========================== //
    /* random number generator for strategy selection */
    private static final Random RANDOM = new Random();

    /* array of collision strategies to be executed */
    private final CollisionStrategy[] strategies;

    /**
     * Constructor for DoubleStrategy.
     * All of these parameters are needed to create new strategies within the double strategy,
     * using the BricksStrategyFactory -
     * which requires all of them to run all the different strategies.
     *
     * @param gameObjects           The collection of game objects in the game.
     * @param brickCounter          Counter to keep track of remaining bricks.
     * @param imageReader           ImageReader for loading images.
     * @param soundReader           SoundReader for loading sounds.
     * @param inputListener         UserInputListener for handling user inputs.
     * @param bricksGrid            2D array representing the grid of bricks.
     * @param livesManager          LivesManager to manage player's lives.
     * @param doubleStrategyCounter Counter for the number of double strategies applied.
     * @param windowDimensions      Dimensions of the game window.
     */
    public DoubleStrategy(GameObjectCollection gameObjects,
                          Counter brickCounter,
                          ImageReader imageReader,
                          SoundReader soundReader,
                          UserInputListener inputListener,
                          Brick[][] bricksGrid, LivesManager livesManager,
                          int doubleStrategyCounter,
                          Vector2 windowDimensions) {

        super(gameObjects, brickCounter);
        /* counter for the number of double strategies applied */
        /* game objects is needed to add/remove objects from the game */
        /* brick counter to keep track of remaining bricks */
        /* image reader to read images for the strategies */
        /* sound reader to read sounds for the strategies */
        /* user input listener to handle user inputs */
        /* 2D array representing the grid of bricks */
        //
        /* lives manager to manage player's lives */
        /* window dimensions for paddle placement */
        /* factory to create various brick strategies */
        BricksStrategyFactory bricksStrategyFactory = new BricksStrategyFactory(
                gameObjects,
                brickCounter,
                imageReader,
                soundReader,
                inputListener,
                bricksGrid,
                livesManager,
                windowDimensions);

        this.strategies = new CollisionStrategy[MAX_DOUBLE_STRATEGIES];
        for (int i = 0; i < strategies.length; i++) {
            // to get a 1-based index for strategy selection
            int chosenStrategy = RANDOM.nextInt(EXCLUDE_BASIC) + 1;

            // decide which strategy to assign based on the chosen index
            // makes sure we don't exceed the max double strategies allowed, which is 2
            if (chosenStrategy == DOUBLE_STRATEGY_INDEX && doubleStrategyCounter < MAX_DOUBLE_STRATEGIES) {
                strategies[i] = new DoubleStrategy(
                        gameObjects,
                        brickCounter,
                        imageReader,
                        soundReader,
                        inputListener,
                        bricksGrid,
                        livesManager,
                        ++doubleStrategyCounter,
                        windowDimensions);
                // if we reached the max double strategies, exclude double (and basic strategies which
                // aren't allowed anyway here).
                // to make the math work, even if double strategy hasn't been chosen, we dont give it
                // another try to be chosen again
            } else {
                strategies[i] = bricksStrategyFactory.getStrategy(EXCLUDE_DOUBLE_AND_BASIC);

            }
        }
    }

    /**
     * Handles the collision event by executing multiple strategies.
     * Chooses new strategies based on random selection and the current
     * double strategy counter.
     * Overrides the onCollision method from BasicCollisionStrategy.
     *
     * @param firstObject  The brick game object that was collided with.
     * @param secondObject The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstObject, GameObject secondObject) {
        super.onCollision(firstObject, secondObject);

        // execute all chosen strategies
        for (CollisionStrategy strategy : strategies) {
            if (strategy != null) { // defensive
                strategy.onCollision(firstObject, secondObject);
            }

        }

    }
}