package space.invaders.enemies;

import javafx.scene.image.Image;
import space.invaders.GameConstants;
import space.invaders.GameLogic;
import space.invaders.IntegerCoordinates;
import space.invaders.Vec2D;
import space.invaders.projectiles.Projectile;

/**
 *
 * @author Totom3
 */
public abstract class Enemy {

	protected Image image;
	private final IntegerCoordinates coords;

	public Enemy(Image image, IntegerCoordinates coords) {
		this.image = image;
		this.coords = coords;
	}

	/**
	 *
	 * @return the visual image of this enemy.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 *
	 * @return the grid coordinates of this enemy, relative to the top left.
	 */
	public IntegerCoordinates getCoordinates() {
		return coords;
	}

	/**
	 * Returns the position of this enemy.
	 *
	 * @param origin the position of the top-left enemy.
	 * @return the position of this enemy.
	 */
	public Vec2D getPosition(Vec2D origin) {
		return origin.plus(GameConstants.ENEMY_DELTA.scale(coords.getX(), coords.getY()));
	}

	/**
	 * Ticks this enemy.
	 *
	 * @param logic the game logic instance.
	 */
	public abstract void tick(GameLogic logic);

	/**
	 * Handles any logic that must be handled when hit by a projectile, and
	 * returns whether or not this enemy is killed.
	 *
	 * @param proj the projectile that hit the enemy.
	 * @return {@code true} if this enemy is now dead, {@code false} otherwise.
	 */
	public abstract boolean onHit(Projectile proj);
}