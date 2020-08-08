package tjjenk2.pong;

import static org.lwjgl.opengl.GL11.glColor3f;
import tjjenk2.gui.Physics;

public class PongPaddle extends PongGameObject {
    public static final int SIZEX = 16;
    public static final int SIZEY = SIZEX * 7;
    public static final float SPEED = 4f;
    
    private final PongBall ball;
    
    public PongPaddle(float x, float y, PongBall ball) {
        super(x, y, SIZEX, SIZEY);
        
        this.ball = ball;
    }
    
    @Override
    public void render() {
        // specify a color (all values between 0 and 1)
        glColor3f(          
            1f,             // red 
            1f,             // green 
            1f              // blue 
        );  
        super.render();
    }
    
    public void move(float magnitude) {
        y += SPEED * magnitude;
    }
    
    @Override
    public void update() {
        if (Physics.checkCollisions(this, ball)) {
            ball.reverse(this.getCenterY());
        }
    }
}