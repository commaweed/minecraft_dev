package tjjenk2.pong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tjjenk2.game.GameObject;
import tjjenk2.gui.Draw;

public abstract class PongGameObject extends GameObject {
	
	public PongGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public float getCenterY() {
		return getY() + getHeight() / 2f;
	}
	
	public float getCenterX() {
		return getX() + getWidth() / 2f;
	}
	
	public void render() {
		Draw.rect(getX(), getY(), getWidth(), getHeight());
	}
}