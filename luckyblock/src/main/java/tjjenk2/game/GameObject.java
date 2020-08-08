package tjjenk2.game;

import javax.vecmath.Point2f;

public abstract class GameObject {
    
    protected float x;
    protected float y;
    private final float width;
    private final float height;
    
    public GameObject(
        float x,
        float y,
        float width,
        float height
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void render();
    public abstract void update();
    
    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }
}