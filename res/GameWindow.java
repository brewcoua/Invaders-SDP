import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JPanel{
    private BossEnemy boss;
    private boolean gameOver = false;

    public GameWindow() {
        // Initialize the boss with a starting position and size
        boss = new BossEnemy(300, 200, 100, 100);

        // Handle user input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // If the player presses space, deal damage to the boss
                if (e.getKeyCode() == KeyEvent.VK_SPACE && boss.isAlive()) {
                    boss.takeDamage(10); // Deal 10 damage on spacebar press
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!boss.isAlive()) {
            g.drawString("You Defeated the Boss!", 300, 200);
            gameOver = true;
        } else {
            boss.draw(g); // Draw the boss and HUD
        }
    }

    // Game loop
    public void update() {
        if (!gameOver) {
            boss.update(); // Update boss behavior
            repaint();     // Repaint the screen
        }
    }

    public static void main(String[] args) {
        // Create the game window
        JFrame frame = new JFrame("Boss Fight: Aetherius");
        GameWindow game = new GameWindow();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Main game loop
        while (true) {
            game.update();
            try {
                Thread.sleep(16); // 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
