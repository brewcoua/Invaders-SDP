package entity.ship;

import entity.Ship;
import entity.ShipMultipliers;

/**
 * Default ship controlled by the player.
 * It does not have any special abilities, nor any special properties.
 */
public class StarDefender extends Ship {
    public StarDefender(final int positionX, final int positionY) {
        super(positionX, positionY);
    }

    public String getShipName() {
        return "Star Defender";
    }

    protected ShipMultipliers getMultipliers() {
        return new ShipMultipliers(1, 1, 0.2f);
    }
}
