package entity;

import java.util.HashSet;
import java.util.Set;
import java.awt.Color;

/**
 * Implements a pool of recyclable bullets.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class BulletPool {

	/** Set of already created bullets. */
	private static Set<Bullet> pool = new HashSet<Bullet>();
	/** Set of already created red bullets (special bullets). */
	private static Set<Bullet> redBulletPool = new HashSet<Bullet>();

	/**
	 * Constructor, not called.
	 */
	private BulletPool() {

	}

	/**
	 * Returns a bullet from the pool if one is available, a new one if there
	 * isn't.
	 * 
	 * @param positionX
	 *            Requested position of the bullet in the X axis.
	 * @param positionY
	 *            Requested position of the bullet in the Y axis.
	 * @param speed
	 *            Requested speed of the bullet, positive or negative depending
	 *            on direction - positive is down.
	 * @param isenemyShipSpecial
	 *            Check if it is an enemyshipspecial.
	 * @return Requested bullet.
	 */
	public static Bullet getBullet(final int positionX,
			final int positionY, final int speed, final boolean isenemyShipSpecial) {
		Bullet bullet;
		if (isenemyShipSpecial) {
			// Get a red bullet from the red bullet pool if available
			if (!redBulletPool.isEmpty()) {
				bullet = redBulletPool.iterator().next();
				redBulletPool.remove(bullet);
			} else {
				bullet = new Bullet(positionX, positionY, speed);
				bullet.setColorToRed();  // Create new red bullet
			}
		} else {
			// Get a regular bullet from the regular pool if available
			if (!pool.isEmpty()) {
				bullet = pool.iterator().next();
				pool.remove(bullet);
			} else {
				bullet = new Bullet(positionX, positionY, speed);  // Create new regular bullet
			}
		}

		bullet.setPositionX(positionX - bullet.getWidth() / 2);
		bullet.setPositionY(positionY);
		bullet.setSpeed(speed);
		bullet.setSprite();

		return bullet;
	}

	/**
	 * Adds regular bullets to the regular pool for recycling.
	 *
	 * @param bullets
	 *            Regular bullets to recycle.
	 */
	public static void recycle(final Set<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			// Only recycle if the bullet is not red
			if (!bullet.getColor().equals(Color.RED)) {
				pool.add(bullet);
			}
		}
	}

	/**
	 * Adds red bullets to the red bullet pool for recycling.
	 *
	 * @param bullets
	 *            Red bullets to recycle.
	 */
	public static void recycleRedBullets(final Set<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			// Only recycle if the bullet is red
			if (bullet.getColor().equals(Color.RED)) {
				redBulletPool.add(bullet);
			}
		}
	}
}
