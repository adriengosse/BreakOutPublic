package game;

import java.awt.event.KeyEvent;

public class Paddle extends Component {

    private int dx;
	private int widthPanel = 300;
	private int heightPanel = 450;
    
    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        resetState();
    }

    public void move() {

        x += dx;
        
        if (x <= 0) {
            x = 0;
        }
        
        if (x >= widthPanel - width) {
            x = widthPanel - width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {
        
    	width = 40;
        height = 10;
        x = (widthPanel - width)/2;
        y = heightPanel - 20;

    }
}

/*
public class Paddle {

	private Component paddle;
	int width;

	public Paddle(int width) {
		this.paddle= new Component(0, 10, width, 1);
	}
	int getx() {
		return paddle.getx();
	}
	void setwidth(int n) {
		paddle.setwidth(n);
	}
	void setx(int n) {
		paddle.setx(n);
	}
}
*/