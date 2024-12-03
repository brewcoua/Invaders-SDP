import engine.Sound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoundTest {
    @Test
    @DisplayName("Sound")
    public void testSound() {
        Sound sound = Sound.UFO_APPEAR;
        assertEquals("UFO_APPEAR", sound.toString());
    }
}
