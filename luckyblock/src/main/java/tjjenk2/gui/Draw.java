package tjjenk2.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Draw {
	
	public static void rect(
		float x,
		float y,
		float width,
		float height
	) {
		rect(x, y, width, height, 0);
	}
	
	public static void rect(
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