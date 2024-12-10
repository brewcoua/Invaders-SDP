import engine.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {
    @Test
    @Disabled
    @DisplayName("Play Sounds")
    public void testSound() {
        try {
            SoundManager soundManager = SoundManager.getInstance();

            // lower volume to avoid loud sounds while running test
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();
            soundManager.volumeDown();

            for(Sound sound : Sound.values()) {
                assertTrue(soundManager.playSound(sound), "Could not play sound " + sound);
                soundManager.stopSound(sound);
            }

            assertFalse(soundManager.playSound(null), "Null sound should not be played");
        } catch (Exception e) {
            fail(e);
            e.printStackTrace();
        }
    }
}
