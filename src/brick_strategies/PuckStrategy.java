package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.util.Counter;

public class PuckStrategy extends BasicCollisionStrategy{
    private static final String PUCK_IMAGE= "assets/mockBall.png";
    private static final float PUCK_SIZE= 0.75f * 30f; //TODO- IT NEED TO BE 3/4 OF THE BALL SIZE
    private static final String PUCK_SOUND= "assets/blop.wav";
    private final ImageReader imageReader;
    private final SoundReader soundReader;

    public PuckStrategy(GameObjectCollection gameObjects, ImageReader imageReader, SoundReader soundReader) {
        super(gameObjects, Counter brickCounter);
        this.imageReader = imageReader;
        this.soundReader = soundReader;
    }
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        //todo add puck
    }
}
