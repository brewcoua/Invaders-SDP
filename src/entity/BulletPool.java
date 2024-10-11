package entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implements a pool of recyclable bullets.
 * 
 */
public final class BulletPool {

    /** Set of already created bullets. */
    private static Set<Bullet> pool = new HashSet<>();

    /**
     * Constructor, not called.
     */
    private BulletPool() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns a bullet from the pool if one is available, a new one if there isn't.
     * 
     * @param positionX Requested position of the bullet in the X axis.
     * @param positionY Requested position of the bullet in the Y axis.
     * @param isEnemy   If true, the bullet belongs to the enemy; otherwise, it belongs to the player.
     * @param speed     Requested speed of the bullet, positive or negative depending on direction - positive is down.
     * @param shapeType The shape of the bullet.
     * @return Requested bullet.
     */
    public static Bullet getBullet(final int positionX,
            final int positionY, final boolean isEnemy, final int speed, final Bullet.ShapeType shapeType) {
        Bullet bullet;
        Iterator<Bullet> iterator = pool.iterator();
        if (iterator.hasNext()) {
            bullet = iterator.next();
            iterator.remove();
            bullet.setPositionX(positionX - bullet.getWidth() / 2);
            bullet.setPositionY(positionY);
            bullet.setSpeed(speed);
            bullet.setShapeType(shapeType);
            bullet.updateColor(isEnemy); // Update color based on ownership
            bullet.setSprite();
        } else {
            bullet = new Bullet(positionX, positionY, isEnemy, shapeType, speed);
            bullet.setPositionX(positionX - bullet.getWidth() / 2);
        }
        return bullet;
    }

    /**
     * Adds one or more bullets to the list of available ones.
     * 
     * @param bullets Bullets to recycle.
     */
    public static void recycle(final Set<Bullet> bullets) {
        pool.addAll(bullets);
    }
    
    /**
     * Overloaded method to recycle a single bullet.
     * 
     * @param bullet Bullet to recycle.
     */
    public static void recycle(final Bullet bullet) {
        pool.add(bullet);
    }
}
