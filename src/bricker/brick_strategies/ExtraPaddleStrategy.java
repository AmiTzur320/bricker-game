package bricker.brick_strategies;

import bricker.gameobjects.ExtraPaddle;
import bricker.main.GameConstants;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * A collision strategy that adds an extra paddle to the game upon brick collision.
 * The extra paddle allows the player to have an additional paddle controlled by the same input.
 * There can be only one extra paddle at a time.
 * Extends the BasicCollisionStrategy.
 * Implements CollisionStrategy interface.
 *
 * @author Zohar Mattatia and Amit Tzur
 */
public class ExtraPaddleStrategy extends BasicCollisionStrategy implements CollisionStrategy {
    // =========================== fields =========================== //
    /* game objects is needed to add/remove objects from the game */
    private final GameObjectCollection gameObjects;
    /* image reader to read images for the strategies */
    private final ImageReader imageReader;
    /* user input listener to handle user inputs */
    private final UserInputListener inputListener;
    /* window dimensions for paddle placement */
    private final Vector2 windowDimensions;

    /**
     * Constructor for ExtraPaddleStrategy.
     *
     * @param gameObjects      The collection of game objects in the game.
     * @param brickCounter     Counter to keep track of remaining bricks.
     * @param imageReader      ImageReader for loading images.
     * @param inputListener    UserInputListener for handling user inputs.
     * @param windowDimensions The dimensions of the game window.
     *
     */
    public ExtraPaddleStrategy(GameObjectCollection gameObjects,
                               Counter brickCounter,
                               ImageReader imageReader,
                               UserInputListener inputListener,
                               Vector2 windowDimensions) {

        super(gameObjects, brickCounter);
        this.gameObjects = gameObjects;
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * Handles the collision event by adding an extra paddle to the game.
     *
     * @param firstObject  The brick game object that was collided with.
     * @param secondObject The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject firstObject, GameObject secondObject) {
        super.onCollision(firstObject, secondObject);
        Renderable paddleImage = imageReader.readImage(GameConstants.PADDLE_IMAGE, true);
        // this method creates and adds the extra paddle to the game
        // iff there is less than 1 extra paddle currently
        ExtraPaddle.createExtraPaddle(
                gameObjects,
                windowDimensions,
                paddleImage,
                inputListener
        );
    }
}