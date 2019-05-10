package game;

import java.awt.Rectangle;

public class Component {

    int x;
    int y;
    int width;
    int height;
    
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
	
    public void setwidth(int n) {
		this.width = n;
	}
    
	public void setheight(int n) {
		this.height = n;
	}
	
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}



/*
public class Component {
	int width;
	int height;
	int x;
	int y;

	public Component(int x1, int y1, int width1, int height1) {
		this.width= width1;
		this.height= height1;
		this.x= x1;
		this.y = y1;
	}
	
	int getx() {
		return x;
	}
	int gety() {
		return y;
	}
	void setx(int n) {
		this.x = n;
	}
	void sety(int n) {
		this.y = n;
	}
	int getwidth() {
		return width;
	}
	int getheight() {
		return height;
	}
	void setwidth(int n) {
		this.width = n;
	}
	void setheight(int n) {
		this.height = n;
	}
}
*/