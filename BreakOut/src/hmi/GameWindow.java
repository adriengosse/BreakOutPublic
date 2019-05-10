package hmi;

import javax.swing.JFrame;
import controlleur.Controlleur;


public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private int widthWindow = 350;
    private int heightWindow = 500;
   
    public GameWindow() {
    	this.add(new Controlleur());
    	this.setTitle("Breakout");
        this.setSize(widthWindow, heightWindow);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
    }
}

/*    
    private void initUI() {
        add(new Controlleur());
        setTitle("Breakout");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
*/
    
/*
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
        	GameWindow game = new GameWindow();
            game.setVisible(true);
        });
    }
}
*/
    
/*
public class GameWindow extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private Controlleur controlleur;
	
	public GameWindow(GamePanel gamepanel, Controlleur mycontrolleur){
		this.controlleur = mycontrolleur;
		this.gamePanel =gamepanel;
		int width = 300;
		int height = 400;
		Dimension d = new Dimension(width, height);
		this.setPreferredSize(d);
		this.add(gamePanel);
		this.addKeyListener(new TAdapter());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		char fleche=e.getKeyChar();
		this.controlleur.move(fleche);
		this.gamePanel.repaint();
	}
}
*/