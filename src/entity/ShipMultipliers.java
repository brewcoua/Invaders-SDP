package entity;

/**
 * Multipliers for the ship's properties.
 * @param speed Movement of the ship for each unit of time.
 * @param bulletSpeed Speed of the bullets shot by the ship.
 * @param shootingInterval Time between shots.
 */
public record ShipMultipliers(float speed, float bulletSpeed, float shootingInterval) {
    public ShipMultipliers clone() {
        return new ShipMultipliers(speed, bulletSpeed, shootingInterval);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ShipMultipliers)) return false;
        ShipMultipliers other = (ShipMultipliers) o;
        return other.speed == speed && other.bulletSpeed == bulletSpeed && other.shootingInterval == shootingInterval;
    }
}
