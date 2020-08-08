package tjjenk2.gui;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GuiConfiguration {
    
    private static final Logger logger = LogManager.getLogger();
    
    public static void initialize (
        String title
    ) throws LWJGLException {
        initialize(title, null, null);
    }
    
    public static void initialize(
        String title,
        Integer width,
        Integer height
    ) throws LWJGLException {
        // the native DLL has to be loaded (I stole the value from minecraft)
        //-Djava.library.path="C:/Users/commaweed/.gradle/caches/minecraft/net/minecraft/minecraft_natives/1.8"
//      System.setProperty("java.library.path", "C:/Users/commaweed/.gradle/caches/minecraft/net/minecraft/minecraft_natives/1.8");
        
        initializeDisplay(width == null ? 800 : width, height == null ? 600 : height);
        createDisplay(title == null ? "No Title" : title);
        initializeOpenGl();
        initializeInput();
    }
    
    private static void initializeInput() throws LWJGLException {
        logger.info("initializeInput...");
        Keyboard.create();
    }
    
    private static void cleanupInput() throws LWJGLException {
        Keyboard.destroy();
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
    
    private static void createDisplay(
        String title
    ) throws LWJGLException {
        logger.info("initializeDisplay...");
        Display.setTitle(title);

        try {
//          Display.create((new PixelFormat()).withDepthBits(24));
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            logger.error("Unable to create display", e);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException interruptedexception) {
                ;
            }
        }
    }
    
    private static void initializeOpenGl() {
        logger.info("initializeOpenGl...");
        
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
            0,                      // left 
            Display.getWidth(),     // right 
            0,                      // bottom 
            Display.getHeight(),    // top
            -1,                     // zNear (how far into 3-d space we want to see) 
            1                       // zFar Specify the distances to the nearer and farther depth clipping planes.  These values are negative if the plane is to be behind the viewer.
            
        );
        
        // switch back to model view matrix
        glMatrixMode(GL_MODELVIEW);
        
        // tell opengl what color to set the frame to every time we clear it
        glClearColor(
            0,              // red 
            0,              // green 
            0,              // blue 
            1               // alpha (1=opaque)
        );
        
        // need to disable the depth test; keeps track of all the information for 3-d purposes
        glDisable(GL_DEPTH_TEST);
    }
    
    /**
     * Clean up open GL.
     */
    public static void performCleanup() throws LWJGLException {
        logger.info("Performing GUI cleanup...");
        Display.destroy();
        cleanupInput();
    }
}