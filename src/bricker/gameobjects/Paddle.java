package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * A class representing the paddle controlled by the user.
 * Extends the GameObject class and includes user input handling for movement.
 *
 * @author Amit Tzur and Zohar Mattatia
 */
public class Paddle extends GameObject {

    //============================ private constants =========================== //
    /* Movement speed of the paddle */
    private static final float MOVEMENT_SPEED = 600;
    /* paddle width */
    public static final float PADDLE_WIDTH = 100;
    /* paddle height - it's thickness */
    public static final float PADDLE_HEIGHT = 15;
    /* paddle dimensions vector - width and height */
    public static final Vector2 PADDLE_DIMENSIONS = new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT);
    //============================ fields =========================== //
    /* User input listener for handling user inputs */
    private final UserInputListener inputListener;
    /* Dimensions of the game window - to restrict paddle movement within the window */
    private final Vector2 windowDimensions;

    /**
     * Constructor for the Paddle class.
     *
     * @param topLeftCorner    The top-left corner position of the paddle.
     * @param renderable       The visual representation of the paddle.
     * @param inputListener    The user input listener for handling user inputs.
     * @param windowDimensions The dimensions of the game window -
     *                         to restrict paddle movement within the window.
     */
    public Paddle(Vector2 topLeftCorner,
                  Renderable renderable,
                  UserInputListener inputListener,
                  Vector2 windowDimensions) {
        super(topLeftCorner, PADDLE_DIMENSIONS, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;

    }

    /**
     * Updates the paddle's position based on user input.
     * Overrides the update method from GameObject.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        // if left key is pressed, add left direction
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }

        // if right key is pressed, add right direction
        // since they are in opposite directions, they will cancel out if both are pressed
        // so that handles this behavior naturally
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }
        // set the velocity according to the movement direction and speed
        setVelocity(movementDir.mult(MOVEMENT_SPEED));

        // check if we reached the left or right edge of the window
        boolean didReachLeftEdge = (this.getTopLeftCorner().x() <= 0);
        boolean didReachRightEdge =
                (this.getTopLeftCorner().x() >=
                        windowDimensions.x() - this.getDimensions().x());

        // if we did, and we are still moving in that direction, stop the movement
        if (didReachLeftEdge && movementDir.x() < 0 ||
                didReachRightEdge && movementDir.x() > 0) {
            setVelocity(Vector2.ZERO);
        }
    }
}
