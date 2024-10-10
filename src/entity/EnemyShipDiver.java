package entity;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager;

import java.awt.*;

public class EnemyShipDiver extends EnemyShip {

    private static final int POINTS = 50;
    private final int DIVE_INTERVAL = 4000;
    private final int DIVE_VARIANCE = -1000;
    public static final int SPEED_X = 10;
    public static final int SPEED_DIVE = 3;
    private Cooldown diveCooldown;
    private int state;


    /**
     * Creates a diver ship with the given x and y positions
     * @param positionX
     * @param positionY
     */
    public EnemyShipDiver(final int positionX, final int positionY) {
        super(positionX, positionY, DrawManager.SpriteType.EnemyShipE1);
        this.diveCooldown = Core.getVariableCooldown(DIVE_INTERVAL, DIVE_VARIANCE);
        this.diveCooldown.reset();
        this.pointValue = POINTS;
        this.state = (int)Math.round(Math.random());
    }

    /**
     * Updates attributes, mainly used for animation purposes.
     */
    public final void update() {
        if((this.state / 10) % 2 == 0 && this.state != 2) {
            this.color = Color.YELLOW;
        } else {
            this.color = Color.RED;
        }

        if (this.animationCooldown.checkFinished()) {
            this.animationCooldown.reset();

            switch (this.spriteType) {
                case EnemyShipE1:
                    this.spriteType = DrawManager.SpriteType.EnemyShipE2;
                    break;
                case EnemyShipE2:
                    this.spriteType = DrawManager.SpriteType.EnemyShipE1;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @return The state of the diver
     */
    public int getState() {
        return state;
    }

    /**
     * Updates the state
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return The Cooldown for diving
     */
    public Cooldown getDiveCooldown() {
        return diveCooldown;
    }
}
