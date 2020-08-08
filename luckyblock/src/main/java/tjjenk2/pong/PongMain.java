package tjjenk2.pong;

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
import tjjenk2.game.GameLoop;
import tjjenk2.gui.GuiConfiguration;
import static org.lwjgl.opengl.GL11.*;

public class PongMain {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws LWJGLException {
		GuiConfiguration.initialize("Pong");

		GameLoop gameLoop = new GameLoop(new PongGame());
		gameLoop.runGameLoop();
		
		GuiConfiguration.performCleanup();
	}
	
	
//	private static void initializeOpenGl() {
//		// opengl defaults in modelview matrix, so switch to projection matrix
//		glMatrixMode(GL_PROJECTION);
//		
//		// start from scratch (clear whatever is in projection matrix)
//		// same as multiplying identity matrix to projection matrix
//		glLoadIdentity(); 
//		
//		// glOrtho describes a transformation that produces a parallel projection.
//		// want projection matrix to be literally as our eye sees it
//		// the values supplied provide the coordinate system; it does not have to match the display (in our case it does)
//		// technical: multiply the current matrix with an orthographic matrix
//		glOrtho(
//			0,						// left 
//			Display.getWidth(),		// right 
//			0,						// bottom 
//			Display.getHeight(), 	// top
//			-1,						// zNear (how far into 3-d space we want to see) 
//			1						// zFar Specify the distances to the nearer and farther depth clipping planes.  These values are negative if the plane is to be behind the viewer.
//            
//		);
//		
//		// switch back to model view matrix
//		glMatrixMode(GL_MODELVIEW);
//		
//		// tell opengl what color to set the frame to every time we clear it
//		glClearColor(
//			0,				// red 
//			0,				// green 
//			0,				// blue 
//			1				// alpha (1=opaque)
//		);
//		
//		// need to disable the depth test; keeps track of all the information for 3-d purposes
//		glDisable(GL_DEPTH_TEST);
//	}	
	
	
}