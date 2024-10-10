package entity;

import java.awt.Color;
import engine.DrawManager.SpriteType;

/**
 * Implements a bullet that moves vertically up or down.
 * 
 * @author ...
 */
public class Bullet extends Entity {

    /**
     * Speed of the bullet, positive or negative depending on direction - positive is down.
     */
    private double speed;

    private static final int FPS = 60;
    
    /**
     * Enum to define different bullet shapes.
     */
    public enum ShapeType {
        RECTANGLE, OVAL, TRIANGLE // You can add more shapes if needed
    }
    
    private ShapeType shapeType;

    /**
     * Constructor, establishes the bullet's properties.
     * 
     * @param positionX Initial position of the bullet in the X axis.
     * @param positionY Initial position of the bullet in the Y axis.
     * @param isEnemy   If true, the bullet belongs to the enemy; otherwise, it belongs to the player.
     * @param shapeType The shape of the bullet (rectangle, oval, triangle).
     */
    public Bullet(final int positionX, final int positionY, final boolean isEnemy, final ShapeType shapeType) {
        // Bullet speed set to 60 pixels per second
        super(positionX, positionY, 3 * 2, 5 * 2, isEnemy ? Color.RED : Color.GREEN);
        this.speed = isEnemy ? 60.0 / FPS : -60.0 / FPS; // 60 pixels per second
        this.shapeType = shapeType;
        setShape(shapeType);  // Set the bullet shape
        setSprite();
    }

    /**
     * Sets the correct sprite for the bullet based on its speed.
     */
    public final void setSprite() {
        if (speed < 0) {
            this.spriteType = SpriteType.Bullet; // Player bullet sprite
        } else {
            this.spriteType = SpriteType.EnemyBullet; // Enemy bullet sprite
        }
    }

    /**
     * Sets the shape of the bullet.
     * 
     * @param shapeType The type of shape for the bullet.
     */
    public final void setShape(final ShapeType shapeType) {
        switch (shapeType) {
            case RECTANGLE:
                this.width = 3 * 2;
                this.height = 5 * 2;
                break;
            case OVAL:
                this.width = 4 * 2;
                this.height = 4 * 2;
                break;
            case TRIANGLE:
                this.width = 5 * 2;
                this.height = 6 * 2;
                break;
            // Add more cases if needed for additional shapes
        }
    }

    /**
     * Updates the bullet's position.
     */
    @Override
    public final void update() {
        this.positionY += this.speed;
    }

    /**
     * Setter for the speed of the bullet.
     * 
     * @param speed New speed of the bullet.
     */
    public final void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * Getter for the speed of the bullet.
     * 
     * @return Speed of the bullet.
     */
    public final double getSpeed() {
        return this.speed;
    }

    /**
     * Setter for the bullet shape.
     * 
     * @param shapeType New shape of the bullet.
     */
    public final void setShapeType(final ShapeType shapeType) {
        this.shapeType = shapeType;
        setShape(shapeType); // Update shape size when shape type changes
    }

    /**
     * Getter for the bullet shape.
     * 
     * @return ShapeType of the bullet.
     */
    public final ShapeType getShapeType() {
        return this.shapeType;
    }
}

