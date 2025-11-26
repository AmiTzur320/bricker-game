package gameobjects;

import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.main.GameConstants;

public class Puck extends Ball {
    private final Vector2 puckDimensions;
    private final Vector2 windowDimensions;
    private final GameObjectCollection gameObjects;
    public Puck(Vector2 topLeftCorner,
                Renderable renderable,
                Sound collisionSound,
                GameObjectCollection gameObjects) {

        super(topLeftCorner, renderable, collisionSound);

        this.puckDimensions = GameConstants.BALL_DIMENSIONS;
        this.windowDimensions = GameConstants.WINDOW_DIMENSIONS;
        this.gameObjects = gameObjects;
    }
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if (getCenter().y()> windowDimensions.y()+ puckDimensions.y()){
            gameObjects.removeGameObject(this);
        }
    }

}
