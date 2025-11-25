package brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.util.Counter;
import danogl.util.Vector2;

public class BasicCollisionStrategy implements CollisionStrategy {
    protected final GameObjectCollection gameObjects;
    private final Counter brickCounter;
    protected final Vector2 windowDimensions;

    public BasicCollisionStrategy(GameObjectCollection gameObjects, Counter brickCounter,
                                  Vector2 windowDimensions) {
        this.gameObjects = gameObjects;
        this.brickCounter = brickCounter;
        this.windowDimensions = windowDimensions;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (gameObjects.removeGameObject(thisObj, Layer.STATIC_OBJECTS)) {
            brickCounter.decrement();
        }
    }
}