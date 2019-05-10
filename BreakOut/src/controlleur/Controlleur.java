package controlleur;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import game.Brick;
import game.Ball;
import game.Paddle;

public class Controlleur extends JPanel {

	private static final long serialVersionUID = 1L;
	private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    private boolean inGame = true;
    private static final int heightPanel = 450;
    private static final int widthPanel = 300;
    private static final int BOTTOM_EDGE = 440;
    private static final int nbreBricks = 12;
    private int score = 0;
    private String msgscore = "score : " + score;
    private int life = 3;
    private String msglife = "lifes : " + life;

    
    public Controlleur() {

        initBoard();
    }

    private void initBoard() {
    	int DELAY = 1000;
    	int PERIOD = 10;
        addKeyListener(new TAdapter());
        setFocusable(true);

        bricks = new Brick[nbreBricks];
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }

    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 100);
                k++;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (life > 0 && inGame) {
            drawObjects(g);
            drawScore(g);
        } 
        
        else {
            gameFinished(g);
        }
    }
    
    private void drawScore(Graphics g) {
    	
    	msgscore = "score : " + score;
    	msglife = "Lifes : " + life;
    	Font font = new Font("TimesRoman", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(font);

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(msgscore , 20, 30);
        g.drawString(msglife , widthPanel - metr.stringWidth(msglife) - 20, 30);
    	g.drawRect(0,0,widthPanel,heightPanel);
        g.drawLine(0, 50, widthPanel, 50);
    }
    
    private void drawObjects(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        g.setColor(Color.blue);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        
        for (int i = 0; i < nbreBricks; i++) {
        	if (i%4 == 0) {
        		g.setColor(Color.green);
        	}
        	if (i%4 == 1) {
        		g.setColor(Color.cyan);
        	}
        	if (i%4 == 2) {
        		g.setColor(Color.orange);
        	}
        	if (i%4 == 3) {
        		g.setColor(Color.yellow);
        	}
            if (!bricks[i].isDestroyed()) {
                g.fillRect(bricks[i].getX(), bricks[i].getY(), bricks[i].getWidth(), bricks[i].getHeight());
            }
        }
    }
    
    private void gameFinished(Graphics g) {

        Font font = new Font("TimesRoman", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(font);

        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawRect(0,0,widthPanel,heightPanel);
        g.drawString(message, (widthPanel - metr.stringWidth(message)) / 2, (heightPanel / 2) - 30);
        g.drawString(msgscore, (widthPanel - metr.stringWidth(msgscore)) / 2, (heightPanel / 2) + 30);
        
    }

    public class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        public void run() {

            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        }
    }

    private void loseOneLife() {
    	life = life - 1;
    	if (life < 0) {
    		stopGame();
    	}
    	else {
        	score = 0;
    	}
    }
    
    private void stopGame() {

        inGame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > BOTTOM_EDGE) {
        	loseOneLife();
        	gameInit();
        }

        for (int i = 0, j = 0; i < nbreBricks; i++) {
            
            if (bricks[i].isDestroyed()) {
                j++;
            }
            
            if (j == nbreBricks) {
                message = "Vous avez gagné !";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0; i < nbreBricks; i++) {
            
            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    } 
                    else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    } 
                    else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                    score = score + life; 
                }
            }
        }
    }
}

/*
import game.Brick;
import game.Ball;
import game.Paddle;
public class Controlleur {

	int widthpaddle= 10;
	Brick[] bricks = new Brick[5];
	Ball ball = new Ball();
	Paddle paddle = new Paddle(widthpaddle);
	int lives;
	int points;
	
	Controlleur(Brick[] brick1, Ball ball1, Paddle paddle1, int lives1, int points1 ) {
		for (int j=0; j<5; j++) {
			bricks[j]= new Brick(5*j, 100);		
		}
		this.ball= ball1;
		this.paddle= paddle1;
		this.lives = lives1;
		this.points = points1;
	}
	
	public void loseLife() {
		
	}
	
	public void breakBrick() {
		
	}
	
	public void move(char fleche) {
		
	}
	public int score() {
		return points;
	}
	public Boolean isOver() {
		return true;
	}
}
*/
