package game;

public class Ball extends Component {

    private int xdir;
    private int ydir;
    private int widthPanel = 300;
    private int heightPanel = 450;
    public Ball() {

        initBall();
    }

    private void initBall() {
        
        xdir = 0;
        ydir = -1;
        
        resetState();
    }
    
    public void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == widthPanel - width) {
            setXDir(-1);
        }

        if (y == 50) {
            setYDir(1);
        }
    }

    private void resetState() {
        
    	width = 10;
        height = 10;
        x = (widthPanel- width )/2;
        y = heightPanel - 20 - height;

    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
}



/*
public class Ball {
	
	private Component ball;
	
	public Ball() {
		this.ball=new Component( 1, 1, 0, 0);
	}
	int getx() {
		return ball.getx();
	}
	int gety() {
		return ball.gety();
	}
	void setx(int n) {
		ball.setx(n);
	}
	void sety(int n) {
		ball.sety(n);
	}
}
*/
