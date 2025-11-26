package brick_strategies;

import bricker.main.GameConstants;
import danogl.util.Counter;
import gameobjects.ExtraPaddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class ExtraPaddleStrategy extends BasicCollisionStrategy  {

    private final GameObjectCollection gameObjects; //TODO IS THERE A NEED
    private final ImageReader imageReader;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;

    public ExtraPaddleStrategy(GameObjectCollection gameObjects,
                               Counter brickCounter,
                               ImageReader imageReader,
                               UserInputListener inputListener) {

        super(gameObjects, brickCounter);
        this.gameObjects = gameObjects;
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = GameConstants.WINDOW_DIMENSIONS;
    }

    @Override
    public void onCollision(GameObject brick, GameObject ball) {
        super.onCollision(brick, ball);
        Renderable paddleImage = imageReader.readImage(GameConstants.PADDLE_IMAGE, true);

        ExtraPaddle.createExtraPaddle(
                gameObjects,
                windowDimensions,
                paddleImage,
                inputListener
        );
    }

    public static void resetCounter() {
        ExtraPaddle.resetCounter();

    }
}