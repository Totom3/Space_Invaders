package space.invaders;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author cstuser
 */
public class Main extends Application {

	private ScheduledFuture<?> gameTickTask;
	private ScheduledFuture<?> renderTask;

	@Override
	public void start(Stage stage) {
		GamePane pane = new GamePane();
		Scene scene = new Scene(pane, GameConstants.SCREEN_SIZE.getX(), GameConstants.SCREEN_SIZE.getY());
		pane.initialize();

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Space Invaders");
		stage.show();

		int renderTick = (int) (1_000 / GameConstants.RENDER_FPS);
		int gameTick = (int) (1_000_000 / GameConstants.GAME_TPS);
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		gameTickTask = executor.scheduleAtFixedRate(pane::tick, gameTick, gameTick, TimeUnit.MICROSECONDS);
		renderTask = executor.scheduleAtFixedRate(() -> Platform.runLater(pane::drawCanvas), renderTick, renderTick, TimeUnit.MILLISECONDS);
	}

	@Override
	public void stop() throws Exception {
		gameTickTask.cancel(true);
		renderTask.cancel(true);
		System.exit(0);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
