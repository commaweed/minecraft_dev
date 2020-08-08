package tjjenk2;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import scala.util.Random;
import static org.lwjgl.opengl.GL11.*;

public class FirstOpenGl {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws LWJGLException {
		// the native DLL has to be loaded (I stole the value from minecraft)
		//-Djava.library.path="C:/Users/commaweed/.gradle/caches/minecraft/net/minecraft/minecraft_natives/1.8"
		System.setProperty("java.library.path", "C:/Users/commaweed/.gradle/caches/minecraft/net/minecraft/minecraft_natives/1.8");
		
		initializeDisplay(800, 600);
		createDisplay();
		initializeOpenGl();
		runGameLoop();
		performCleanup();
	}

	/**
	 * Sets the display mode.
	 * @param fullscreen Indicates whether or not to set it to full screen
	 * @param displayWidth The display width to use when not fullscreen
	 * @param displayHeight The display height to use when not fullscreen
	 * @throws LWJGLException If an error occurs while initializing display
	 */
	private static void initializeDisplay(
		int displayWidth,
		int displayHeight
	) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
	}
	
	private static void initializeOpenGl() {
		// opengl defaults in modelview matrix, so switch to projection matrix
		glMatrixMode(GL_PROJECTION);
		
		// start from scratch (clear whatever is in projection matrix)
		// same as multiplying identity matrix to projection matrix
		glLoadIdentity(); 
		
		// glOrtho describes a transformation that produces a parallel projection.
		// want projection matrix to be literally as our eye sees it
		// the values supplied provide the coordinate system; it does not have to match the display (in our case it does)
		// technical: multiply the current matrix with an orthographic matrix
		glOrtho(
			0,						// left 
			Display.getWidth(),		// right 
			0,						// bottom 
			Display.getHeight(), 	// top
			-1,						// zNear (how far into 3-d space we want to see) 
			1						// zFar Specify the distances to the nearer and farther depth clipping planes.  These values are negative if the plane is to be behind the viewer.
            
		);
		
		// switch back to model view matrix
		glMatrixMode(GL_MODELVIEW);
		
		// tell opengl what color to set the frame to every time we clear it
		glClearColor(
			0,				// red 
			0,				// green 
			0,				// blue 
			1				// alpha (1=opaque)
		);
		
		// need to disable the depth test; keeps track of all the information for 3-d purposes
		glDisable(GL_DEPTH_TEST);
	}
	
	private static void runGameLoop() {
		int x = 0;
		int y = 0;
		int width = 20;
		int height = 20;
		boolean forward = true;
		boolean up = true;
		int rotation = 0;
		while (!Display.isCloseRequested()) {
			// clear the frame before updating it so that images don't draw on top of each other
			// specify what you want to clear by using a bit mask (multiple values can be bitwise OR)
			glClear(GL_COLOR_BUFFER_BIT);
			
			// clear the current matrix (should be modelview) - multiply identity matrix
			glLoadIdentity();
			
			if (x + width + 5 > Display.getWidth()) {
				forward = false;
			} else if (x <= 0) {
				forward = true;
			}
			
			if (forward) {
				x += 5;
			} else {
				x -= 5;
			}
			
			if (y + height + 5 > Display.getHeight()) {
				up = false;
			} else if (y <= 0) {
				up = true;
			}
			
			if (up) {
				y += 5;
			} else {
				y -= 5;
			}
			
			rotation += 45;
			if (rotation == 360) {
				rotation = 0;
			}
			
//			Random random = new Random();
			drawRect(x, y, width, height, rotation);	//random.nextInt(360)
			

//			drawSquare();
//			Random random = new Random();
//			int x = random.nextInt(Display.getWidth());
//			int y = random.nextInt(Display.getHeight());
//			int width = random.nextInt(50);
//			int height = random.nextInt(50);
//			drawRect(x, y, width, height);
			
			// show whatever we have drawn so far
			Display.update();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private static void createDisplay() throws LWJGLException {
		Display.setTitle("Travis First Open GL");

		try {
//			Display.create((new PixelFormat()).withDepthBits(24));
			Display.create();
		} catch (LWJGLException lwjglexception) {
			logger.error("Couldn\'t set pixel format", lwjglexception);

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException interruptedexception) {
				;
			}
		}
	}
	
	/**
	 * Clean up open GL.
	 */
	private static void performCleanup() {
		logger.info("Destroying window");
		Display.destroy();
	}
	
	/**
	 * Draw a square at the bottom left.
	 */
	private static void drawSquare() {
		// specify a color (all values between 0 and 1)
		glColor3f(			
			0.25f,				// red 
			0.75f,				// green 
			0.5f				// blue 
		);
		
		// move the square
		// shifts the matrix by a certain amount to where we will start drawing
		glTranslatef(64, 64, 0);
		
		// rotate (45 degrees)
		glRotatef(45, 0, 0 , 1);
		
		// put what you want to draw between the begin and end markers
		// GL_QUADS cuz we are drawing one or more squares
		glBegin(GL_QUADS);
		glVertex2f(10, 10);			// bottom left
		glVertex2f(10, 74);			// top left
		glVertex2f(74, 74);			// top right
		glVertex2f(74, 10);			// bottom right	
		glEnd();
	}
	
	private static void drawRect(
		float x,
		float y,
		float width,
		float height,
		float rotation
	) {
		// push the current matrix to the top of the stack
		glPushMatrix();
		
		// move the square
		// shifts the matrix by a certain amount to where we will start drawing
		glTranslatef(x, y, 0);
		glRotated(
			rotation, 	// the angle 0-360
			0, 			// rotate on x-axis
			0, 			// rotate on y-axis
			1			// rotate on z-axis
		);  
		
		// put what you want to draw between the begin and end markers
		// GL_QUADS cuz we are drawing one or more squares
		// coordinates below are the grid coordinates for the shape itself and not the pixels that are on the screen.  We can get the translate to do that.
		glBegin(GL_QUADS);
		glVertex2f(0, 0);			// bottom left
		glVertex2f(0, height);		// top left
		glVertex2f(width, height);	// top right
		glVertex2f(width, 0);		// bottom right	
		glEnd();
		
		// pop the current matrix from the top of the stack
		glPopMatrix();
	}
	
}