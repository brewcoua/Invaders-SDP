package entity;

import engine.Core;
import engine.DrawManager;
import engine.DrawManager.SpriteType;
import java.awt.Color;

/**
 * Implements a bullet that moves vertically up or down.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class Bullet extends Entity {

	/**
	 * Speed of the bullet, positive or negative depending on direction -
	 * positive is down.
	 */
	private int speed;

	/**
	 * Pierce of the bullet
	 */


	/**
	 * Constructor, establishes the bullet's properties.
	 * 
	 * @param positionX
	 *            Initial position of the bullet in the X axis.
	 * @param positionY
	 *            Initial position of the bullet in the Y axis.
	 * @param speed
	 *            Speed of the bullet, positive or negative depending on
	 *            direction - positive is down.
	 */
	public Bullet(final int positionX, final int positionY, final int speed, Entity shooter) {
		super(positionX, positionY, 3 * 2, 5 * 2, Color.WHITE);
		this.speed = speed;
		setSprite(shooter);
	}

	/**
	 * Sets correct sprite and color for the bullet, based on speed.
	 */
	public final void setSprite(Entity shooter) {
		if(shooter instanceof Ship) {
			switch (shooter.spriteType) {
				case DrawManager.SpriteType.Ship: // Star Defender
					this.spriteType = SpriteType.BulletType3;
					this.color = Color.WHITE;
					break;
				case DrawManager.SpriteType.Ship2: // Void Reaper
					this.spriteType = SpriteType.BulletType1;
					this.color = Color.GREEN;
					break;
				case DrawManager.SpriteType.Ship3: // Galactic Guardian
					this.spriteType = SpriteType.BulletType4;
					this.color = Color.RED;
					break;
				case DrawManager.SpriteType.Ship4: // Cosmic Cruiser
					this.spriteType = SpriteType.BulletType2;
					this.color = Color.BLUE;
					break;
			}
		} else {
			this.spriteType = SpriteType.EnemyBullet;
			this.color = shooter.getColor();
		}
	}

	/**
	 * Updates the bullet's position.
	 */
	public final void update() {
		this.positionY += this.speed;
	}

	/**
	 * Setter of the speed of the bullet.
	 * 
	 * @param speed
	 *            New speed of the bullet.
	 */
	public final void setSpeed(final int speed) {
		this.speed = speed;
	}

	/**
	 * Getter for the speed of the bullet.
	 * 
	 * @return Speed of the bullet.
	 */
	public final int getSpeed() {
		return this.speed;
	}
}
