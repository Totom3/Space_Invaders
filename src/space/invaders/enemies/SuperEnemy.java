package space.invaders.enemies;

import java.util.Random;
import javafx.scene.image.Image;
import space.invaders.GameConstants;
import space.invaders.GameLogic;
import space.invaders.projectiles.NormalProjectile;
import space.invaders.util.AnimationResources;
import space.invaders.util.ImageResources;
import space.invaders.util.IntegerCoordinates;
import space.invaders.util.MediaResources;
import space.invaders.util.RectBounds;
import space.invaders.util.Vec2D;

/**
 *
 * @author Frankie
 */
public class SuperEnemy extends NormalEnemy {

	private static final Image PROJECTILE_SUPER = ImageResources.PROJECTILE_SUPER.getImage();
	
	public SuperEnemy(IntegerCoordinates coords) {
		super(AnimationResources.ENEMY_SUPER, coords);
	}

	@Override
	protected void shootProjectile(GameLogic logic) {
		// Shoot projectile
		Vec2D pos1 = getPosition(logic.getEnemyPosition())
				.plus(GameConstants.ENEMY_SIZE.scale(0.5, 1))
				.minus(GameConstants.PROJECTILE_SIZE.scale(1, 0));

		RectBounds bounds1 = new RectBounds(pos1, GameConstants.PROJECTILE_SIZE);
		NormalProjectile proj1 = new NormalProjectile(false, bounds1, new Vec2D(0, GameConstants.PROJECTILE_SPEED_SUPER), PROJECTILE_SUPER);
		logic.addProjectile(proj1);

		Vec2D pos2 = pos1.plus(GameConstants.PROJECTILE_SIZE.scale(2, 0));
		RectBounds bounds2 = new RectBounds(pos2, GameConstants.PROJECTILE_SIZE);
		NormalProjectile proj2 = new NormalProjectile(false, bounds2, new Vec2D(0, GameConstants.PROJECTILE_SPEED_SUPER), PROJECTILE_SUPER);
		logic.addProjectile(proj2);

		MediaResources.SUPER_SHOOT_SOUND.playSound(1);
	}

	@Override
	protected long getCooldown(Random rand) {
		// Random cooldown between 5 and 7 seconds
		return (long) ((5 + 2 * rand.nextDouble()) * GameConstants.GAME_TPS);
	}

}
