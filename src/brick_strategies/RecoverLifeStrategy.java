package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import gameobjects.FallingHeart;
import bricker.main.GameConstants;
import gameobjects.LivesManager;

public class RecoverLifeStrategy extends BasicCollisionStrategy{

    // the heart image path
    private static final String HEART_IMAGE_PATH = "assets/heart.png";
    private final Renderable heartImage;
    private final LivesManager livesManager;
    public RecoverLifeStrategy(GameObjectCollection gameObjects,
                               Counter brickCounter,
                               ImageReader imageReader,
                               LivesManager livesManager) {
        super(gameObjects, brickCounter);
        this.livesManager = livesManager;
        this.heartImage = imageReader.readImage(HEART_IMAGE_PATH,true);
    }

    // onColliosn is being called when a ball collides with a brick that has this strategy
    @Override
    public void onCollision(danogl.GameObject brick, danogl.GameObject ball) {
        super.onCollision(brick, ball);
        // TODO: implement life recovery logic here
        Vector2 brickCenter = brick.getCenter();
        Vector2 heartDims= GameConstants.HEART_DIMENSIONS;
        Vector2 heartTopLeft = brickCenter.subtract(heartDims.mult(0.5f));
        FallingHeart fallingHeart = new FallingHeart(
                gameObjects,
                heartTopLeft,
                heartDims,
                this.heartImage,
                this.livesManager);
        this.gameObjects.addGameObject(fallingHeart, Layer.DEFAULT);
    }

}
