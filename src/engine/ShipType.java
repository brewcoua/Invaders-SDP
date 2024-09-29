package engine;

/**
 * Represents a type of ship.
 * @param id Unique identifier.
 * @param name Name of the ship.
 * @param life Number of hits the ship can take.
 * @param shootingInterval Time between shots in milliseconds.
 * @param bulletSpeed Speed of the bullets shot by the ship.
 * @param speed Movement of the ship for each unit of time.
 */
public record ShipType(String id, String name, int life, int shootingInterval, int bulletSpeed, int speed) {
    public ShipType {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Ship ID cannot be null or empty.");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Ship name cannot be null or empty.");
        if (life <= 0)
            throw new IllegalArgumentException("Ship life must be greater than 0.");
        if (shootingInterval <= 0)
            throw new IllegalArgumentException("Shooting interval must be greater than 0.");
        if (bulletSpeed == 0)
            throw new IllegalArgumentException("Bullet speed cannot be 0.");
        if (speed == 0)
            throw new IllegalArgumentException("Speed cannot be 0.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ShipType shipType = (ShipType) obj;
        return life == shipType.life && shootingInterval == shipType.shootingInterval && bulletSpeed == shipType.bulletSpeed && speed == shipType.speed && id.equals(shipType.id) && name.equals(shipType.name);
    }

    @Override
    public String toString() {
        return "ShipType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", life=" + life +
                ", shootingInterval=" + shootingInterval +
                ", bulletSpeed=" + bulletSpeed +
                ", speed=" + speed +
                '}';
    }
}
