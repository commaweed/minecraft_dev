package tjjenk2.gui;

import java.awt.Rectangle;

import tjjenk2.game.GameObject;

public class Physics {
    public static boolean checkCollisions(GameObject one, GameObject two) {
        // determine if two objects intersected
        Rectangle rect1 = new Rectangle((int) one.getX(), (int) one.getY(), (int) one.getWidth(), (int) one.getHeight());
        Rectangle rect2 = new Rectangle((int) two.getX(), (int) two.getY(), (int) two.getWidth(), (int) two.getHeight());
        
        return rect1.intersects(rect2);
    }
}