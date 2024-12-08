package screen;

import engine.*;
import engine.Cooldown;
import engine.Core;
import engine.InputManager;
import entity.Ship;
import java.awt.event.KeyEvent;

/**
 * Implements the game setting screen.
 *
 * @author <a href="mailto:dayeon.dev@gmail.com">Dayeon Oh</a>
 *
 */
public class GameSettingScreen extends Screen {
	private static GameSettingScreen instance;

	/** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 200;
	/** Maximum number of characters for player name.
	 * draw를 용이하게 하기 위해 NAME_LIMIT을 4로 제한 */
	private static final int NAME_LIMIT = 4;


	/** Player name1 for record input. */
	private static String name1;
	/** Player name2 for record input. */
	private static String name2;
	/** Multiplayer mode. */
	private static boolean isMultiplayer = false;
	/** Difficulty level. */
	private int difficultyLevel;
	/** Selected row. */
	private int selectedRow;
	/** Player 1 Ship type. */
	private Ship.ShipType shipTypeP1;
	/** Player 2 Ship type. */
	private Ship.ShipType shipTypeP2;
	/** Time between changes in user selection. */
	private final Cooldown selectionCooldown;

	/** Total number of rows for selection. */
	private static final int TOTAL_ROWS = 5; // Multiplayer, Difficulty, P1 Ship Type, P2 Ship Type Start

	/** Singleton instance of SoundManager */
	private final SoundManager soundManager = SoundManager.getInstance();

	/**
	 * Constructor, establishes the properties of the screen.
	 *
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public GameSettingScreen(final int width, final int height, final int fps, final Ship.ShipType shipType) {
		super(width, height, fps);

		// row 0: multiplayer
		name1 = "P1";
		name2 = "P2";
		isMultiplayer = false;

		// row 1: difficulty level
		this.difficultyLevel = 1; 	// 0: easy, 1: normal, 2: hard

		// row 2: ship type
		this.shipTypeP1 = shipType;

		// row 3 (if multiplayer): ship type
		this.shipTypeP2 = shipType;

		// row 3 (4 if multiplayer): start

		this.selectedRow = 0;

		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
	}

	/**
	 * Starts the action.
	 *
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		draw();
		if (this.inputDelay.checkFinished() && this.selectionCooldown.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_UP)){
				this.selectedRow = (this.selectedRow - 1 + getTotalRows()) % getTotalRows();
				this.selectionCooldown.reset();
				soundManager.playSound(Sound.MENU_MOVE);
			} else if (inputManager.isKeyDown(KeyEvent.VK_DOWN)) {
				this.selectedRow = (this.selectedRow + 1) % getTotalRows();
				this.selectionCooldown.reset();
				soundManager.playSound(Sound.MENU_MOVE);
			}

			if (this.selectedRow == 0) {
				if (inputManager.isKeyDown(KeyEvent.VK_LEFT)) {
					isMultiplayer = false;
					this.selectionCooldown.reset();
					soundManager.playSound(Sound.MENU_MOVE);
				} else if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
					isMultiplayer = true;
					this.selectionCooldown.reset();
					soundManager.playSound(Sound.MENU_MOVE);
				} else if (inputManager.isKeyDown(KeyEvent.VK_BACK_SPACE)) {
					if (isMultiplayer) {
						if (!name2.isEmpty()) {
							name2 = name2.substring(0, name2.length() - 1);
							this.selectionCooldown.reset();
							soundManager.playSound(Sound.MENU_TYPING);
						}
					} else {
						if (!name1.isEmpty()) {
							name1 = name1.substring(0, name1.length() - 1);
							this.selectionCooldown.reset();
							soundManager.playSound(Sound.MENU_TYPING);
						}
					}
				}
				handleNameInput(inputManager);
			} else if (this.selectedRow == 1) {
				if (inputManager.isKeyDown(KeyEvent.VK_LEFT)) {
					if (this.difficultyLevel != 0) {
						this.difficultyLevel--;
						this.selectionCooldown.reset();
						soundManager.playSound(Sound.MENU_MOVE);
					}
				} else if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
					if (this.difficultyLevel != 2) {
						this.difficultyLevel++;
						this.selectionCooldown.reset();
						soundManager.playSound(Sound.MENU_MOVE);
					}
				}
			} else if (this.selectedRow == 2) {
				if (inputManager.isKeyDown(KeyEvent.VK_LEFT) || inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
					Ship.ShipType[] shipTypes = Ship.ShipType.values();
					int index = 0;
					for (int i = 0; i < shipTypes.length; i++) {
						if (shipTypes[i] == this.shipTypeP1) {
							index = i;
							break;
						}
					}

					if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
						if (index < shipTypes.length - 1) {
							this.shipTypeP1 = shipTypes[index + 1];
						}
					} else {
						if (index > 0) {
							this.shipTypeP1 = shipTypes[index - 1];
						}
					}
					this.selectionCooldown.reset();
				}
			} else if (this.selectedRow == 3 && isMultiplayer) { // Select P2 Ship
				if (inputManager.isKeyDown(KeyEvent.VK_LEFT) || inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
					Ship.ShipType[] shipTypes = Ship.ShipType.values();
					int index = 0;
					for (int i = 0; i < shipTypes.length; i++) {
						if (shipTypes[i] == this.shipTypeP2) {
							index = i;
							break;
						}
					}

					if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
						if (index < shipTypes.length - 1) {
							this.shipTypeP2 = shipTypes[index + 1];
						}
					} else {
						if (index > 0) {
							this.shipTypeP2 = shipTypes[index - 1];
						}
					}
					this.selectionCooldown.reset();
				}
			} else {
				if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
					this.returnCode = isMultiplayer ? 8 : 2;
					this.isRunning = false;
					soundManager.playSound(Sound.MENU_CLICK);
				}
			}
			if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
				// Return to main menu.
				this.returnCode = 1;
				this.isRunning = false;
				soundManager.playSound(Sound.MENU_BACK);
			}
		}

	}

	/**
	 * Handles the input for player name.
	 *
	 * @param inputManager
	 *            Input manager.
	 */
	private void handleNameInput(InputManager inputManager) {
		for (int keyCode = KeyEvent.VK_A; keyCode <= KeyEvent.VK_Z; keyCode++) {
			if (inputManager.isKeyDown(keyCode)) {
				if (isMultiplayer) {
					if (name2.length() < NAME_LIMIT) {
						name2 += (char) keyCode;
						this.selectionCooldown.reset();
						soundManager.playSound(Sound.MENU_TYPING);
					}
				} else{
					if (name1.length() < NAME_LIMIT) {
						name1 += (char) keyCode;
						this.selectionCooldown.reset();
						soundManager.playSound(Sound.MENU_TYPING);
					}
				}
			}
		}
	}
	public static GameSettingScreen getInstance() {
		if (instance == null) {
			instance = new GameSettingScreen(0,0,0, Ship.ShipType.StarDefender);
		}
		return instance;
	}
	public static boolean getMultiPlay() {return isMultiplayer; }

	/**
	 * Get player's name by number
	 *
	 * @param playerNumber
	 * 			Player's number
	 * @return Player's name
	 */
	public static String getName(int playerNumber) { return playerNumber == 0 ? name1 : name2; }

	private int getTotalRows() {
		return isMultiplayer ? TOTAL_ROWS : TOTAL_ROWS - 1;
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawGameSetting(this);

		drawManager.drawGameSettingRow(this, this.selectedRow, isMultiplayer);

		drawManager.drawGameSettingElements(this, this.selectedRow, isMultiplayer, name1, name2,this.difficultyLevel, this.shipTypeP1, this.shipTypeP2);

		drawManager.completeDrawing(this);

		Core.setLevelSetting(this.difficultyLevel);
		Core.BASE_SHIP_P1 = this.shipTypeP1;
		Core.BASE_SHIP_P2 = this.shipTypeP2;
	}
}