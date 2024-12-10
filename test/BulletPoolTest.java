import engine.DrawManager;
import engine.GameState;
import entity.Bullet;
import entity.BulletPool;
import entity.EnemyShip;
import entity.Ship;
import entity.ship.CosmicCruiser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BulletPoolTest {
    public GameState testState = new GameState(1, 0, Ship.ShipType.CosmicCruiser, 3,
            0, 0, 0, "", 0, 0, 0, 0, 0, 1);
    public EnemyShip testEnemy = new EnemyShip(200, 200, DrawManager.SpriteType.EnemyShipA1, testState);
    public CosmicCruiser testPlayer = new CosmicCruiser(0, 0);
    Set<Bullet> testSet = new HashSet<Bullet>();

    @Test
    @DisplayName("Get Bullet from Pool")
    public void GetBulletTest() {
        BulletPool.clear();
        Bullet bullet = BulletPool.getBullet(200, 100, 5, testEnemy);
        assertNotNull(bullet);
        assertEquals(197, bullet.getPositionX()); // actual x pos is subtracted by 3 when retrieved from pool
        assertEquals(100, bullet.getPositionY());
        assertEquals(5, bullet.getSpeed());
        assertEquals(DrawManager.SpriteType.EnemyBullet, bullet.getSpriteType());

        // Get bullet from nonempty pool
        testSet.add(new Bullet(1000, 1000, 10, testEnemy));
        BulletPool.recycle(testSet);
        bullet = BulletPool.getBullet(100, 200, 2, testPlayer);

        assertEquals(97, bullet.getPositionX()); // actual x pos is subtracted by 3 when retrieved from pool
        assertEquals(200, bullet.getPositionY());
        assertEquals(2, bullet.getSpeed());
        assertEquals(DrawManager.SpriteType.BulletType2, bullet.getSpriteType());
    }

    @Test
    @DisplayName("Recycle Bullets")
    public void RecycleBullets() {
        BulletPool.clear();
        testSet.clear();

        Bullet bullet1 = BulletPool.getBullet(200, 100, 5, testEnemy);
        Bullet bullet2 = BulletPool.getBullet(400, 200, -10, testPlayer);

        testSet.add(bullet1);
        testSet.add(bullet2);

        BulletPool.recycle(testSet);

        Bullet bullet3 = BulletPool.getBullet(5, 5, -20, testPlayer);
        Bullet bullet4 = BulletPool.getBullet(50, 50, 30, testEnemy);

        assertTrue(bullet1 == bullet3 || bullet1 == bullet4);
        if(bullet1 == bullet3) {
            assertEquals(bullet2, bullet4);
        } else {
            assertEquals(bullet2, bullet3);
        }
    }
}
