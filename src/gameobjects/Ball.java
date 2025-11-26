package gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.main.GameConstants;

public class Ball extends GameObject {
    private Sound collisionSound;


    public Ball(Vector2 topLeftCorner,
                Renderable renderable,
                Sound collisionSound) {
        super(topLeftCorner, GameConstants.BALL_DIMENSIONS, renderable);
        this.collisionSound=collisionSound;
        this.setTag(GameConstants.BALL_TAG);

    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel= getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisionSound.play();
    }

    private void setTag() {
        this.setTag();
    }

}
