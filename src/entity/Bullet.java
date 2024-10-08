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
     * Constructor, establishes the bullet's properties.
     * 
     * @param positionX Initial position of the bullet in the X axis.
     * @param positionY Initial position of the bullet in the Y axis.
     * @param isEnemy   If true, the bullet belongs to the enemy; otherwise, it belongs to the player.
     */
    public Bullet(final int positionX, final int positionY, final boolean isEnemy) {
		// Bullet speed set to 60 pixels per second
		super(positionX, positionY, 3 * 2, 5 * 2, isEnemy ? Color.RED : Color.GREEN);
		this.speed = isEnemy ? 60.0 / FPS : -60.0 / FPS; // 60 pixels per second
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
}
