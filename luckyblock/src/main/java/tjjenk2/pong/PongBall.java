package tjjenk2.pong;

import static org.lwjgl.opengl.GL11.glColor3f;

public class PongBall extends PongGameObject {
	
	public static final int SIZE = 16;
	public static final float MAX_SPEEDX = 4f;
	public static final float MAX_SPEEDY = 8f;
	public static final float DAMPING = 0.05f;
	
	public float velocityX;
	public float velocityY;

	public PongBall(float x, float y) {
		super(x, y, SIZE, SIZE);
		velocityX = -MAX_SPEEDX;
		velocityY = 0;
	}

	@Override
	public void render() {
		// specify a color (all values between 0 and 1)
		glColor3f(			
			0.25f,				// red 
			0.75f,				// green 
			0.5f				// blue 
		);	
		super.render();
	}
	
	/**
	 * What to do every frame.
	 */
	@Override
	public void update() {
		x += velocityX;
		y += velocityY;
	}
	
	public void reverse(float center) {
		velocityX *= -1;
		velocityY += (getCenterY() - center) * DAMPING;
		
		if (velocityY > MAX_SPEEDY) {
			velocityY = MAX_SPEEDY;
		}
		
		if (velocityY < -MAX_SPEEDY) {
			velocityY = -MAX_SPEEDY;
		}
	}
}