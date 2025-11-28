package bricker.brick_strategies;

import bricker.gameobjects.FallingHeart;
import bricker.gameobjects.LivesManager;
import bricker.main.GameConstants;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * A collision strategy that spawns a falling heart upon brick collision,
 * which recovers a life when collected.
 * Extends the BasicCollisionStrategy.
 *
 * @author Amit Tzur and Zohar Mattatia
 */
public class RecoverLifeStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    // ============================ fields =========================== //
    /* Image for the heart */
    private final Renderable heartImage;
    /* Lives manager to handle life recovery */
    private final LivesManager livesManager;
    /* Dimensions of the game window - to know when the heart has "fallen out" of the screen */
    private final Vector2 windowDimensions;

    /* heart dimensions vector */
    public static final Vector2 HEART_DIMENSIONS = new Vector2(GameConstants.HEART_WIDTH,
            GameConstants.HEART_HEIGHT);

    /**
     * Constructor for RecoverLifeStrategy.
     * Here we initialize the heart image and lives manager.
     *
     * @param gameObjects      The collection of game objects in the game.
     * @param brickCounter     Counter to keep track of remaining bricks.
     * @param imageReader      ImageReader for loading images.
     * @param livesManager     LivesManager for managing player lives.
     * @param windowDimensions The dimensions of the game window.
     */
    public RecoverLifeStrategy(GameObjectCollection gameObjects,
                               Counter brickCounter,
                               ImageReader imageReader,
                               LivesManager livesManager,
                               Vector2 windowDimensions) {
        super(gameObjects, brickCounter);
        this.livesManager = livesManager;
        this.heartImage = imageReader.readImage(GameConstants.HEART_IMAGE_PATH, true);
        this.windowDimensions = windowDimensions;
    }

    /**
     * Handles the collision event by spawning a falling heart at the brick's location.
     *
     * @param firstObject  The brick game object that was collided with.
     * @param secondObject The other game object involved in the collision.
     */
    @Override
    public void onCollision(danogl.GameObject firstObject, danogl.GameObject secondObject) {
        super.onCollision(firstObject, secondObject);

        Vector2 brickCenter = firstObject.getCenter();
        Vector2 heartDims = HEART_DIMENSIONS;
        Vector2 heartTopLeft = brickCenter.subtract(heartDims.mult(GameConstants.HALF_FACTOR));
        // Create and add the falling heart to the game
        FallingHeart fallingHeart = new FallingHeart(
                gameObjects,
                heartTopLeft,
                heartDims,
                this.heartImage,
                this.livesManager,
                windowDimensions);
        this.gameObjects.addGameObject(fallingHeart, Layer.DEFAULT);
    }

}
