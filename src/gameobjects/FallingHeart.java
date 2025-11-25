package gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class FallingHeart extends GameObject {

    private final GameObject mainPaddle; // Save a reference to the SPECIFIC paddle

    public FallingHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObject mainPaddle) {
        super(topLeftCorner, dimensions, renderable);
        this.mainPaddle = mainPaddle;

        // Ensure the heart falls down (Behavior 2.2.4.3)
        this.setVelocity(Vector2.DOWN.mult(100));
    }

    @Override
    public boolean shouldCollideWith(GameObject object) {
        // This is the "Reference Comparison".
        // It checks if the object defined as 'other' is strictly the same object in memory
        // as the 'mainPaddle' we saved in the constructor.
        return object == this.mainPaddle;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // Logic for adding a life goes here or in the strategy
        // Since shouldCollideWith returns false for everything else,
        // we know 'other' MUST be the mainPaddle here.
    }
}