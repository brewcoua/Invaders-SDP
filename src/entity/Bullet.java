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
	private int speed;

	/**
	 * Protected color for recycling purposes when using BulletPool.
	 */
	protected Color bulletColor;

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
	 * @param speed     Speed of the bullet.
	 */
	public Bullet(final int positionX, final int positionY, final boolean isEnemy, final ShapeType shapeType, final int speed) {
		super(positionX, positionY, 3 * 2, 5 * 2, isEnemy ? Color.RED : Color.GREEN);
		this.speed = speed; // Set the speed directly as an integer
		this.shapeType = shapeType;
		this.bulletColor = isEnemy ? Color.RED : Color.GREEN;
		setShape(shapeType);  // Set the bullet shape
		setSprite();
	}

	/**
	 * Constructor that only takes position and ownership to maintain compatibility.
	 *
	 * @param positionX Initial position of the bullet in the X axis.
	 * @param positionY Initial position of the bullet in the Y axis.
	 * @param isEnemy   If true, the bullet belongs to the enemy; otherwise, it belongs to the player.
	 */
	public Bullet(final int positionX, final int positionY, final boolean isEnemy) {
		this(positionX, positionY, isEnemy, ShapeType.RECTANGLE, isEnemy ? 10 : -10);
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
	public void update() {
		this.positionY += this.speed;
	}

	/**
	 * Setter for the speed of the bullet.
	 *
	 * @param speed New speed of the bullet.
	 */
	public void setSpeed(final int speed) {
		this.speed = speed;
	}

	/**
	 * Getter for the speed of the bullet.
	 *
	 * @return Speed of the bullet.
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Setter for the bullet shape.
	 *
	 * @param shapeType New shape of the bullet.
	 */
	public void setShapeType(final ShapeType shapeType) {
		this.shapeType = shapeType;
		setShape(shapeType); // Update shape size when shape type changes
	}

	/**
	 * Getter for the bullet shape.
	 *
	 * @return ShapeType of the bullet.
	 */
	public ShapeType getShapeType() {
		return this.shapeType;
	}

	/**
	 * Updates the color of the bullet.
	 *
	 * @param isEnemy If true, sets the color to enemy color; otherwise, player color.
	 */
	public void updateColor(final boolean isEnemy) {
		this.bulletColor = isEnemy ? Color.RED : Color.GREEN;
		this.color = this.bulletColor;
	}
}

