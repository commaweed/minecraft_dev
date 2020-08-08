package tjjenk2.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Game {
	
	private List<GameObject> gameObjects = new ArrayList<>();
	

	public void render() {
		for (GameObject gameObject : getGameObjects()) {
			gameObject.render();
		}
	}

	public void update() {
		for (GameObject gameObject : getGameObjects()) {
			gameObject.update();
		}
	}
	
	public abstract void getInput();
	
	public void addGameObject(
		GameObject gameObject
	) {
		if (gameObject != null) {
			gameObjects.add(gameObject);
		}
	}
	
	/**
	 * @return the gameObjects
	 */
	public List<GameObject> getGameObjects() {
		return Collections.unmodifiableList(gameObjects);
	}

}