package gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;



public class FallingHeart extends GameObject {
    private final GameObjectCollection gameObjects;
    private final Vector2 windowDimensions;
    private final Counter livesCounter; //to add lives if paddle catch
    private final int maxLives; //max lives allowed
    private final Vector2 sizeOfFallingHeart;
    public FallingHeart(GameObjectCollection gameObjects,
                        Vector2 topLeftCorner,
                        Vector2 dimensions,
                        Vector2 windowDimensions,
                        Renderable renderable,
                        Counter livesCounter,
                        int maxLives
                        Vector2 sizeOfFallingHeart) {
        super(topLeftCorner, dimensions, renderable);
        this.gameObjects = gameObjects;
        this.windowDimensions=windowDimensions;
        this.livesCounter=livesCounter;
        this.maxLives=maxLives;
        this.sizeOfFallingHeart=sizeOfFallingHeart;
        this.setVelocity(Vector2.DOWN.mult(100));
    }

    @Override
    public boolean shouldCollideWith(GameObject object) {
        // This is the "Reference Comparison".
        // It checks if the object defined as 'other' is strictly the same object in memory
        // as the 'mainPaddle' we saved in the constructor.
        return object.getTag() .equals("MAIN_PADDLE");
    }

    @Override
    public  void update(float deltaTime) {
        super.update(deltaTime);
        // If the heart falls below the screen, remove it from the game
        if (this.getTopLeftCorner().y() >windowDimensions.y() ) { // Assuming screen height is 800
            gameObjects.removeGameObject(this);
        }
    }


    // on collisonEnter is being called when the falling heart collides with somthing
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // Logic for adding a life goes here or in the strategy
        // Since shouldCollideWith returns false for everything else,
        // we know 'other' MUST be the mainPaddle here.
        if (livesCounter.value() < maxLives) {
            livesCounter.increment();
            gameObjects.removeGameObject(this);
        }
    }
}