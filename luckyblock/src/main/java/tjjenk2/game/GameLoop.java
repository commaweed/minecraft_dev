package tjjenk2.game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import org.lwjgl.opengl.Display;

public class GameLoop {
	
	private final Game game;
	
	public GameLoop(
		Game game
	) {
		if (game == null) {
			throw new IllegalArgumentException("Invalid game; it cannot be null!");
		}
		this.game = game;
	}
	
	
	private static void clearFrame() {
		// clear the frame before updating it so that images don't draw on top of each other
		// specify what you want to clear by using a bit mask (multiple values can be bitwise OR)
		glClear(GL_COLOR_BUFFER_BIT);
		
		// clear the current matrix (should be modelview) - multiply identity matrix
		glLoadIdentity();
	}
	
	private void getInput() {
		game.getInput();
	}
	
	private void updateFrame() {
		game.update();
		
		// show whatever we have drawn so far
		Display.update();
	}
	
	private void render() {
		clearFrame();
		game.render();
		updateFrame();
	}
	
	public void runGameLoop() {
		while (!Display.isCloseRequested()) {
			getInput();
			render();
		}
	}
}