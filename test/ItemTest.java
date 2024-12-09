import engine.*;
import entity.Barrier;
import entity.EnemyShipFormation;
import entity.Ship;
import entity.ship.CosmicCruiser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    public GameState testState = new GameState(1, 0, Ship.ShipType.CosmicCruiser, 3,
            0, 0, 0, "", 0, 0, 0, 0, 0, 1);
    public GameSettings testSettings = new GameSettings(6, 5, 60, 2500);
    public SoundManager soundManager = SoundManager.getInstance();
    public EnemyShipFormation testFormation;
    public CosmicCruiser testPlayer;
    Set<Barrier> testBarriers;
    public ItemManager testItemManager;

    @BeforeEach
    public void reset() {
        testFormation = new EnemyShipFormation(testSettings, testState);
        testPlayer = new CosmicCruiser(0, 0);
        testBarriers = new HashSet<>();
        testItemManager = new ItemManager(testPlayer, testFormation, testBarriers,
                500, 500, 0);
    }

    @Test
    @DisplayName("Bomb Test")
    public void testBomb() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.Bomb);
        assertEquals(new SimpleEntry<>(300, 9), out);

        out = testItemManager.operateItem(ItemManager.ItemType.Bomb);
        assertEquals(new SimpleEntry<>(300, 9), out);

        out = testItemManager.operateItem(ItemManager.ItemType.Bomb);
        assertEquals(new SimpleEntry<>(60, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.Bomb);
        assertEquals(new SimpleEntry<>(60, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.Bomb);
        assertEquals(new SimpleEntry<>(0, 0), out);
    }
    @Test
    @DisplayName("Line Bomb Test")
    public void testLineBomb() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(60, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(60, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(120, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(180, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(300, 6), out);

        out = testItemManager.operateItem(ItemManager.ItemType.LineBomb);
        assertEquals(new SimpleEntry<>(0, 0), out);
    }
    @Test
    @DisplayName("Barrier Test")
    public void testBarrier() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.Barrier);
        assertNull(out);
        assertEquals(3, testBarriers.size());
    }

    @Test
    @DisplayName("Ghost Test")
    public void testGhost() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.Ghost);
        assertNull(out);
        assertEquals(Color.DARK_GRAY, testPlayer.getColor());
        assertTrue(testItemManager.isGhostActive());
    }

    @Test
    @DisplayName("Time Stop Test")
    public void testTimeStop() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.TimeStop);
        assertNull(out);
        assertTrue(testItemManager.isTimeStopActive());
    }

    @Test
    @DisplayName("Multishot Test")
    public void testMultishot() {
        Entry<Integer, Integer> out = testItemManager.operateItem(ItemManager.ItemType.MultiShot);
        assertNull(out);
        assertEquals(2, testItemManager.getShotNum());

        out = testItemManager.operateItem(ItemManager.ItemType.MultiShot);
        assertNull(out);
        assertEquals(3, testItemManager.getShotNum());

        out = testItemManager.operateItem(ItemManager.ItemType.MultiShot);
        assertNull(out);
        assertEquals(3, testItemManager.getShotNum());
    }
}
