package game;

public class Brick extends Component {

    private boolean destroyed;

    public Brick(int x, int y) {
        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) {
        
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
        
        destroyed = false;
    }
    
    public boolean isDestroyed() {
        
        return destroyed;
    }

    public void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}

/*
public class Brick {

	private Component brick;
	int x;
	int y;
	
	public Brick(int x,int y) {
		this.brick= new Component(x, y, 5, 2);
	}
	int getx() {
		return brick.getx();
	}
	int gety() {
		return brick.gety();
	}
}
*/
