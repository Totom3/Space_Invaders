package space.invaders;

import space.invaders.util.Vec2D;

/**
 *
 * @author Tomer Moran
 */
public interface GameConstants {

	/**
	 * The size of the screen.
	 */
	Vec2D SCREEN_SIZE = new Vec2D(1200, 1000);

	/**
	 * The size of an enemy.
	 */
	Vec2D ENEMY_SIZE = new Vec2D(50, 50);

	/**
	 * The spacing between adjacent enemies.
	 */
	Vec2D ENEMY_SPACING = new Vec2D(30, 25);

	/**
	 * The sum of the size of an enemy and the spacing between enemies.
	 */
	Vec2D ENEMY_DELTA = ENEMY_SIZE.plus(ENEMY_SPACING);

	/**
	 * The size of the spaceship.
	 */
	Vec2D SPACESHIP_SIZE = new Vec2D(30, 30);

	/**
	 * The size of the spaceship model.
	 */
	Vec2D SPACESHIP_DISPLAY_SIZE = new Vec2D(50, 50);

	/**
	 * Left bound of the game area. Enemies bounce when they reach this.
	 */
	double LEFT_GAME_BOUND = 20;

	/**
	 * Top bound of the game area. Defines the starting position of enemies.
	 */
	double TOP_GAME_BOUND = 120;

	/**
	 * Right bound of the game area. Enemies bounce when they reach this.
	 */
	double RIGHT_GAME_BOUND = SCREEN_SIZE.getX() - 20;

	/**
	 * Bottom bound of the game area. This is where the spaceship moves around.
	 */
	double BOTTOM_GAME_BOUND = SCREEN_SIZE.getY() - 120;

	/**
	 * The Y component of the spaceship.
	 */
	double SPACESHIP_Y = BOTTOM_GAME_BOUND;

	/**
	 * The number of enemies in a row.
	 */
	int ENEMIES_GRID_LENGTH = 11;

	/**
	 * The number of enemies in a column.
	 */
	int ENEMIES_GRID_HEIGHT = 5;

	/**
	 * The total number of enemies to defeat.
	 */
	int ENEMIES_COUNT = ENEMIES_GRID_LENGTH * ENEMIES_GRID_HEIGHT;

	/**
	 * Number of game ticks per second.
	 */
	double GAME_TPS = 60;

	/**
	 * Number of rendering ticks per second.
	 */
	double RENDER_FPS = 60;

	/**
	 * The starting position of enemies: at the left and top game bounds.
	 */
	Vec2D START_ENEMIES_POSITION = new Vec2D(LEFT_GAME_BOUND, TOP_GAME_BOUND);

	/**
	 * The horizontal movement speed of enemies, in pixels per tick. Value
	 * adjusted so that it does not affected by {@link #GAME_TPS}.
	 */
	Vec2D ENEMY_MOVEMENT_SPEED = new Vec2D(50, 0).divide(GAME_TPS);

	/**
	 * The vertical jump enemies take when they reach the game bounds. The jump
	 * is instantaneous and therefore does not depend on {@link #GAME_TPS}.
	 */
	Vec2D ENEMY_DOWN_JUMP = new Vec2D(0, (ENEMY_SIZE.getY() + ENEMY_SPACING.getY()) / 2);

	/**
	 * The speed at which enemy projectiles move, in pixels per tick.
	 */
	double PROJECTILE_SPEED = 200 / GAME_TPS;

	/**
	 * The speed at which the super enemy's projectiles move, in pixels per tick.
	 */
	double PROJECTILE_SPEED_SUPER = 400 / GAME_TPS;
	
	/**
	 * The speed at which friendly (allied + player) projectiles move, in pixels
	 * per tick.
	 */
	double PROJECTILE_SPEED_FRIENDLY = -600 / GAME_TPS;

	/**
	 * The size of a normal projectile.
	 */
	Vec2D PROJECTILE_SIZE = new Vec2D(9, 25);

	/**
	 * The initial size of a laser projectile.
	 */
	Vec2D LASER_SIZE = new Vec2D(20, 0);

	/**
	 * The vertical speed of the laser, in pixels per tick.
	 */
	double LASER_SPEED = 700 / GAME_TPS;

	/**
	 * The lifetime of a laser, in ticks.
	 */
	long LASER_LIFETIME = (long) (3 * GAME_TPS);

	/**
	 * The number of hits the enemy tank's shield can take before breaking.
	 */
	int TANK_CAPACITY = 2;

	/**
	 * The delay between two volleys of shots by a spinner enemy.
	 */
	long SPINNER_SHOOT_DELAY = (long) (0.75 * GAME_TPS);

	/**
	 * The number of volleys of shots by a spinner enemy.
	 */
	long SPINNER_SHOOT_COUNT = 5;

	/**
	 * The number of pellets shots by a spinner enemy.
	 */
	long SPINNER_PELLET_COUNT = 9;

	/**
	 * The max angle at which the spinner can shoot.
	 */
	double SPINNER_MAX_ANGLE = 5 * Math.PI / 12;

	/**
	 * The size of a pellet projectile shot by a spinner enemy.
	 */
	Vec2D SPINNER_PELLET_SIZE = new Vec2D(10, 10);

	/**
	 * Delay between player shoots.
	 */
	long SHOOT_DELAY = 400;

	/**
	 * How many lives the player starts with.
	 */
	int PLAYER_LIVES = 3;

	/**
	 * The time to freeze the game for when the player loses a life, in ticks.
	 */
	long FREEZE_TIME = (long) (5 * GAME_TPS);

	/**
	 * Score received for killing a normal enemy.
	 */
	int SCORE_NORMAL_KILLED = 10;

	/**
	 * Score received for killing a super enemy.
	 */
	int SCORE_SUPER_KILLED = 20;

	/**
	 * Score received for killing a spinner enemy.
	 */
	int SCORE_SPINNER_KILLED = 30;

	/**
	 * Score received for killing a tank enemy.
	 */
	int SCORE_TANK_KILLED = 30;

	/**
	 * Score received for killing a laser enemy.
	 */
	int SCORE_LASER_KILLED = 40;

	/**
	 * Score received for destroying an enemy projectile.
	 */
	int SCORE_PROJECTILE_KILLED = 10;
}
