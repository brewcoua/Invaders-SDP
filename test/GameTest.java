import engine.*;
import entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screen.GameScreen;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    public GameState testState = new GameState(1, 0, Ship.ShipType.CosmicCruiser, 3,
            0, 0, 0, "", 0, 0, 0, 0, 0, 1);
    public GameSettings testSettings = new GameSettings(6, 5, 60, 2500);
    public Wallet testWallet = new Wallet();
    GameScreen testScreen;
    @BeforeEach
    public void setup() {
        testScreen = new GameScreen(testState, testSettings, false, 500, 500, 60, testWallet);
        testScreen.initialize();
    }

    @Test
    @DisplayName("Bullet Damage Test")
    public void bulletDamageTest() {
        try {
            Field lives = GameScreen.class.getDeclaredField("lives");
            Field bullets = GameScreen.class.getDeclaredField("bullets");
            Field enemyShipFormation = GameScreen.class.getDeclaredField("enemyShipFormation");
            Method manageCollisions = GameScreen.class.getDeclaredMethod("manageCollisions");

            lives.setAccessible(true);
            bullets.setAccessible(true);
            enemyShipFormation.setAccessible(true);
            manageCollisions.setAccessible(true);

            Bullet testBullet = new Bullet(250, 470, 5,
                    new EnemyShip(0, 0, DrawManager.SpriteType.EnemyShipA1, testState));
            ((Set<Bullet>)bullets.get(testScreen)).add(testBullet);
            manageCollisions.invoke(testScreen);
            assertEquals(2, (int)(lives.get(testScreen)));

        } catch(Exception e) {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Bullet High Damage Test")
    public void bulletHighDamageTest() {
        try {
            Field lives = GameScreen.class.getDeclaredField("lives");
            Field bullets = GameScreen.class.getDeclaredField("bullets");
            Field enemyShipFormation = GameScreen.class.getDeclaredField("enemyShipFormation");
            Field level = GameScreen.class.getDeclaredField("level");
            Method manageCollisions = GameScreen.class.getDeclaredMethod("manageCollisions");

            lives.setAccessible(true);
            bullets.setAccessible(true);
            enemyShipFormation.setAccessible(true);
            level.setAccessible(true);
            manageCollisions.setAccessible(true);

            level.set(testScreen, 9);

            Bullet testBullet = new Bullet(250, 470, 5,
                    new EnemyShip(0, 0, DrawManager.SpriteType.EnemyShipA1, testState));
            ((Set<Bullet>)bullets.get(testScreen)).add(testBullet);
            manageCollisions.invoke(testScreen);
            assertEquals(0, (int)(lives.get(testScreen)));

        } catch(Exception e) {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Diver Damage Test")
    public void diverDamageTest() {
        try {
            testScreen.initialize();
            Field lives = GameScreen.class.getDeclaredField("lives");
            Field enemyShipFormation = GameScreen.class.getDeclaredField("enemyShipFormation");
            Field diverState = EnemyShipDiver.class.getDeclaredField("state");

            Method manageCollisions = GameScreen.class.getDeclaredMethod("manageCollisions");

            lives.setAccessible(true);
            enemyShipFormation.setAccessible(true);
            diverState.setAccessible(true);
            manageCollisions.setAccessible(true);

            EnemyShipDiver diver = ((EnemyShipFormation)enemyShipFormation.get(testScreen)).getEnemyDivers().getFirst();


            diver.setPositionX(250);
            diver.setPositionY(470);
            diverState.set(diver, 2);

            manageCollisions.invoke(testScreen);
            assertEquals(2, (int)(lives.get(testScreen)));

        } catch(Exception e) {
            fail(e);
            e.printStackTrace();
        }
    }
}
