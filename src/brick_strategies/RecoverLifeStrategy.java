//package brick_strategies;
//
//import danogl.GameObject;
//import danogl.collisions.GameObjectCollection;
//import danogl.gui.ImageReader;
//import danogl.gui.rendering.Renderable;
//import danogl.util.Counter;
//import danogl.util.Vector2;
//import gameobjects.FallingHeart;
//
//public class RecoverLifeStrategy extends BasicCollisionStrategy implements CollisionStrategy {
//
//    // the heart image properties
//    private static final float HEART_WIDTH = 30;
//    private static final float HEART_HEIGHT = 30;
//
//    // the heart image path
//    private static final String HEART_IMAGE_PATH = "assets/heart.png";
//    private final GameObjectCollection GameObjects;
//    private final Vector2 heartTopLeft;
//    private final Vector2 sizeOfFallingHeart;
//    private final Renderable imageReader;
//    public RecoverLifeStrategy(GameObjectCollection gameObjects,
//                               Vector2 brickCenterPosition,
//                               Vector2 dimensions,
//                               Vector2 windowDimensions,
//                               ImageReader imageReader,
//                               Counter brickCounter,
//                               int maxLives,
//                               Vector2 sizeOfFallingHeart) {
//        super(gameObjects, brickCounter);
//        GameObjects = gameObjects;
//        this.heartTopLeft = brickCenterPosition;
//        this.sizeOfFallingHeart = sizeOfFallingHeart;
//        this.imageReader = imageReader.readImage(HEART_IMAGE_PATH,true);
//    }
//
//    // onColliosn is being called when a ball collides with a brick that has this strategy
//    @Override
//    public void onCollision(danogl.GameObject thisObj, danogl.GameObject otherObj) {
//        super.onCollision(thisObj, otherObj);
//        // TODO: implement life recovery logic here
//        FallingHeart fallingHeart = new FallingHeart(
//                gameObjects,
//                brickCenterPosition,
//                dimensions,
//                windowDimensions,
//                imageReader,
//                brickCounter,
//                maxLives,
//                sizeOfFallingHeart
//        );
//        this.gameObjects.addGameObject(fallingHeart);
//    }
//
//}
