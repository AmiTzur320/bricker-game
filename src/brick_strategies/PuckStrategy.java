package brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.rendering.ImageRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import gameobjects.Puck;
import bricker.main.GameConstants;
import java.util.Random;

public class PuckStrategy extends BasicCollisionStrategy{
    private static final String PUCK_IMAGE= "assets/mockBall.png";
    private static final float PUCK_RATIO_OF_BALL= 0.75f ;
    private static final float PUCK_SPEED= 200f;

    private final Renderable puckImage;
    private final Sound collisionSound;
    private final float puckSize;


    public PuckStrategy(GameObjectCollection gameObjects, Counter brickCounter, ImageReader imageReader,
                        SoundReader soundReader) {
        super(gameObjects, brickCounter);
        this.puckImage = imageReader.readImage(PUCK_IMAGE, true);
        this.collisionSound = soundReader.readSound(GameConstants.BALL_BLOP_COLLISION_SOUND);
        this.puckSize = GameConstants.PUCK_SIZE;
    }



@Override
    public void onCollision(GameObject brick, GameObject ball) {
        super.onCollision(brick, ball);

        Vector2 brickCenter = brick.getCenter();
        Vector2 puckDimensions = new Vector2(this.puckSize, this.puckSize);
        Vector2 topLeftOfPuck = brickCenter.subtract(puckDimensions.mult(0.5f));

        for (int i = 0; i < 2; i++) {
            addPuck(topLeftOfPuck,puckDimensions);
        }
    }

    private void addPuck(Vector2 topLeftOfPuck,Vector2 puckDimensions) {

        Puck puck = new Puck(topLeftOfPuck,puckImage,collisionSound,gameObjects);
        puck.setVelocity(randomVelocityUpper());
        super.gameObjects.addGameObject(puck, Layer.DEFAULT);
    }

    private Vector2 randomVelocityUpper(){
        Random random = new Random();
        double angle = random.nextDouble() * Math.PI;
        float velocityX = (float)Math.cos(angle) * PUCK_SPEED;
        float velocityY = (float)Math.sin(angle) * PUCK_SPEED;
        return new Vector2(-velocityX, -velocityY); //TODO ADDED MINUS
    }
}
