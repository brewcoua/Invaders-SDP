import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class BossEnemy {
    private int x, y;
    private int width, height;
    private int health = 100;
    private int maxHealth = 100;
    private int attackCooldown = 100;
    private int phase = 1;
    private boolean isAlive = true;

    public BossEnemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        if (!isAlive) return;

        // **Draw the cloak or cape (dark purple)**
        g.setColor(new Color(64, 0, 128)); // Dark purple color
        g.fillRect(x + (width / 8), y + (height / 4), width - (width / 4), height / 2); // Body cape

        // **Draw the head with a sinister shape**
        g.setColor(Color.BLACK); // Dark head
        g.fillOval(x + (width / 4), y, width / 2, height / 4); // Head (oval)

        // **Add glowing red eyes to make it look more evil**
        g.setColor(Color.RED); // Red eyes
        g.fillOval(x + (width / 3), y + height / 12, width / 10, height / 10); // Left eye
        g.fillOval(x + (width / 2), y + height / 12, width / 10, height / 10); // Right eye

        // **Add horns (villainous detail)**
        g.setColor(Color.DARK_GRAY); // Dark gray horns
        g.fillPolygon(new int[] {x + (width / 3), x + (width / 4), x + (width / 2)}, new int[] {y, y - height / 6, y}, 3); // Left horn
        g.fillPolygon(new int[] {x + (2 * width / 3), x + (3 * width / 4), x + (width / 2)}, new int[] {y, y - height / 6, y}, 3); // Right horn

        // **Draw the body (intimidating dark color)**
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + (width / 4), y + (height / 4), width / 2, height / 2); // Body

        // **Draw arms (sharp claws)**
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y + (height / 4), width, height / 8); // Arms
        g.setColor(Color.BLACK); // Claws
        g.fillPolygon(new int[] {x, x - 10, x}, new int[] {y + (height / 4), y + (height / 4) + 20, y + (height / 4) + 20}, 3); // Left claw
        g.fillPolygon(new int[] {x + width, x + width + 10, x + width}, new int[] {y + (height / 4), y + (height / 4) + 20, y + (height / 4) + 20}, 3); // Right claw

        // **Draw legs (dark and powerful)**
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + (width / 4), y + (3 * height / 4), width / 6, height / 4); // Left leg
        g.fillRect(x + (width / 2), y + (3 * height / 4), width / 6, height / 4); // Right leg

        // Draw the health bar
        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {
        int barWidth = 200;
        int barHeight = 20;
        int healthBarWidth = (int) ((double) health / maxHealth * barWidth);

        g.setColor(Color.BLACK);
        g.fillRect(50, 50, barWidth, barHeight); // Health bar background
        g.setColor(Color.GREEN);
        g.fillRect(50, 50, healthBarWidth, barHeight); // Dynamic green bar

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Boss Health: " + health + "/" + maxHealth, 55, 65);
    }

    public void update() {
        if (!isAlive) return;
        move();
        if (phase == 1) {
            phaseOneAttacks();
        } else if (phase == 2) {
            phaseTwoAttacks();
        }
        checkPhaseTransition();
    }

    private void move() {
        if (phase == 1) {
            x += 2;
            if (x > 600 || x < 50) {
                x = -x;
            }
        } else if (phase == 2) {
            Random random = new Random();
            x = random.nextInt(600);
            y = random.nextInt(400);
        }
    }

    private void phaseOneAttacks() {
        if (attackCooldown <= 0) {
            System.out.println("Boss uses Dark Flame!");
            attackCooldown = 100;
        }
        attackCooldown--;
    }

    private void phaseTwoAttacks() {
        if (attackCooldown <= 0) {
            System.out.println("Boss uses Shadow Strike!");
            attackCooldown = 80;
        }
        attackCooldown--;
    }

    private void checkPhaseTransition() {
        if (health <= 50 && phase == 1) {
            System.out.println("Boss is now in Phase 2!");
            phase = 2;
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println("Boss has been defeated!");
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}

