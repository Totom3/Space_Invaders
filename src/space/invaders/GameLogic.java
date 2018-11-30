package space.invaders;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import space.invaders.enemies.DummyEnemy;
import space.invaders.enemies.Enemy;
import space.invaders.projectiles.Projectile;

/**
 *
 * @author Tomer Moran
 */
public class GameLogic {

	private final Random random;
	private final Enemy[][] enemies;
	private final Set<Projectile> friendlyProjectiles;
	private final Set<Projectile> enemyProjectiles;

	private int leftmostEnemy;
	private int rightmostEnemy;
	private int downmostEnemy;
	private int remainingLives;

	/**
	 * The direction in which enemies move. Left is {@code true} while right is
	 * {@code false}.
	 */
	private boolean enemyMovementDirection;

	private Vec2D enemyPosition;

	public GameLogic() {
		this.random = new Random();
		this.enemies = new Enemy[GameConstants.ENEMIES_GRID_LENGTH][GameConstants.ENEMIES_GRID_HEIGHT];
		this.friendlyProjectiles = new HashSet<>();
		this.enemyProjectiles = new HashSet<>();
	}

	public Random getRandom() {
		return random;
	}

	public Enemy[][] getEnemies() {
		return enemies;
	}

	public Enemy getEnemy(int gridX, int gridY) {
		return enemies[gridX][gridY];
	}

	public void forEachEnemy(Consumer<Enemy> cons) {
		for (int x = 0; x < GameConstants.ENEMIES_GRID_LENGTH; ++x) {
			for (int y = 0; y < GameConstants.ENEMIES_GRID_HEIGHT; ++y) {
				cons.accept(enemies[x][y]);
			}
		}
	}

	public Set<Projectile> getFriendlyProjectiles() {
		return friendlyProjectiles;
	}

	public Set<Projectile> getEnemyProjectiles() {
		return friendlyProjectiles;
	}

	public boolean addProjectile(Projectile proj) {
		return (proj.isFriendly() ? friendlyProjectiles : enemyProjectiles).add(proj);
	}

	public boolean removeProjectile(Projectile proj) {
		return (proj.isFriendly() ? friendlyProjectiles : enemyProjectiles).remove(proj);
	}

	public int getRemainingLives() {
		return remainingLives;
	}

	public Vec2D getEnemyPosition() {
		return enemyPosition;
	}

	public Vec2D getEnemyPosition(int gridX, int gridY) {
		return enemyPosition.plus(GameConstants.ENEMY_DELTA.scale(gridX, gridY));
	}

	public void generateGame() {
		// Create enemies
		for (int x = 0; x < GameConstants.ENEMIES_GRID_LENGTH; ++x) {
			for (int y = 0; y < GameConstants.ENEMIES_GRID_HEIGHT; ++y) {
				enemies[x][y] = new DummyEnemy(new IntegerCoordinates(x, y));
			}
		}

		// Make enemies move right
		enemyMovementDirection = true;

		// Initialize start position
		enemyPosition = GameConstants.START_ENEMIES_POSITION;

		// Initialize index pointers
		leftmostEnemy = 0;
		rightmostEnemy = GameConstants.ENEMIES_GRID_LENGTH - 1;
		downmostEnemy = GameConstants.ENEMIES_GRID_HEIGHT - 1;
	}

	public void tickGame() {
		// 1. Move the enemies around
		moveEnemies();

		// 2. Tick each enemy
		forEachEnemy(e -> e.tick(this));

		// 3. Tick each projectile
		friendlyProjectiles.forEach(Projectile::tick);
		enemyProjectiles.forEach(Projectile::tick);

		// 4. Check collisions!
		// TODO
	}

	private void moveEnemies() {
		// Check if wall was hit
		if (shouldEnemiesJumpDown()) {

			// Move down
			this.enemyPosition = enemyPosition.plus(GameConstants.ENEMY_DOWN_JUMP);

			// Invert direction
			this.enemyMovementDirection = !enemyMovementDirection;
			return;
		}

		// Move right or left
		Vec2D move = GameConstants.ENEMY_MOVEMENT_SPEED;
		this.enemyPosition = (enemyMovementDirection) ? enemyPosition.plus(move) : enemyPosition.minus(move);
	}

	private boolean shouldEnemiesJumpDown() {
		if (enemyMovementDirection) {
			// Jump down when moving right & hit right wall
			return getEnemyPosition(rightmostEnemy, 0).getX() + GameConstants.ENEMY_SIZE.getX() >= GameConstants.RIGHT_GAME_BOUND;
		} else {
			// Jump down when moving left & hit left wall
			return getEnemyPosition(leftmostEnemy, 0).getX() <= GameConstants.LEFT_GAME_BOUND;
		}
	}

	private void handleFriendlyProjectilesToEnemyCollision() {
		for (Iterator<Projectile> it = friendlyProjectiles.iterator(); it.hasNext();) {
			Projectile proj = it.next();

			forEachEnemy(e -> {
				if (proj.collidesWith(e, enemyPosition)) {
					// TODO
				}
			});
		}
	}
}
