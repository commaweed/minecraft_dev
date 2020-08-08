package tjjenk2.pong;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import tjjenk2.game.Game;
import tjjenk2.game.GameObject;

public class PongGame extends Game {
    
    private final PongBall ball;
    private final PongPaddle leftPaddle;
    private final PongPaddle rightPaddle;
    
    public PongGame() {
        ball = new PongBall(Display.getWidth() / 2 - PongBall.SIZE, Display.getHeight() /2 - PongBall.SIZE);
        this.addGameObject(ball);
        
        leftPaddle = new PongPaddle(0, Display.getHeight() / 2 - PongPaddle.SIZEY / 2, ball);
        this.addGameObject(leftPaddle);
        
        rightPaddle = new PongPaddle(Display.getWidth() - PongPaddle.SIZEX, Display.getHeight() / 2 - PongPaddle.SIZEY / 2, ball);
        this.addGameObject(rightPaddle);
    }

    @Override
    public void getInput() {
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            leftPaddle.move(1);
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            leftPaddle.move(-1);
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            rightPaddle.move(1);
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            rightPaddle.move(-1);
        }
    }



}