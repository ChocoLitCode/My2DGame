//game panel
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	private BufferedImage img;
	// private Camera camera;
	public boolean isOptionOverlayActive = false;
	public boolean isRuleOverlayActive = false;
	
	//Screen settings
	final int originalTileSize=32; 
	 public int scale = 4;
	
	public  int tileSize = originalTileSize*scale;
	public final int maxScreenCol = 12;
	public final int maxScreenRow = 7;
	public  int screenWidth = tileSize*maxScreenCol;
	public  int screenHeight = tileSize *maxScreenRow;
	public String selectedCharacter;
	
// World Settings	
	public int maxWorldCol;
	public int maxWorldRow;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public final int maxMap = 4;
	public int currentMap = 0;
	
// For full screen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	//FPS
	int FPS=60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	MouseHandler mouseH = new MouseHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);
	UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	//Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int characterselectState = 2;
	public final int pauseState = 3;
	public final int dialogueState = 4;
	public final int optionState = 5;
	public final int ruleState = 6;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.addMouseMotionListener(mouseH);
		this.setFocusable(true);
		
		setupGame();
		startGameThread();
		selectedCharacter = "Boy";
	}

	public void setScale(int scale) {
        this.scale = scale;
        this.tileSize = originalTileSize * scale;
        this.screenWidth = tileSize * maxScreenCol;
        this.screenHeight = tileSize * maxScreenRow;
    }
	
	public void setupGame() {
		gameState = titleState;
//		gameState = characterselectState;
	}

	public void startGameThread() {
		gameThread= new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/drawInterval;
			
			timer +=(currentTime-lastTime);
			
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
			    repaint();
			    delta--;
			    drawCount++;
			}
			 if (timer >= 1000000000) {
				 drawCount = 0;
				 timer = 0;
			 }
		}	
	}
	
	public void update() {
		
		if (player != null) {
			player.update();
		}
    }
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		long drawStart=0;
		if(keyH.showDebugText==true) {
			drawStart=System.nanoTime();
		}
		
		//Title Screen
		if (gameState == titleState) {
			ui.draw(g2);
		}
		else if (gameState == characterselectState) { // NEW SCREEN
			ui.draw(g2);
	    }
		if (isOptionOverlayActive) {
            ui.drawOptionOverlay(g2); // Draw options overlay on top of the title screen
        }
		if (isRuleOverlayActive) {
            ui.drawRuleOverlay(g2); // Draw options overlay on top of the title screen
        }
		else if (gameState == playState){
			// camera.applyOffset(g2);
			tileM.draw(g2);
			player.draw(g2);
		}
		
		if(keyH.showDebugText ==true) {
			long drawEnd =System.nanoTime();
			long passed = drawEnd -drawStart;
			
			g2.setFont(new Font("Arial",Font.PLAIN,20));
			g2.setColor(Color.white);
			int x = 10;
			int y= 400;
			int lineHeight =20;
			
			g2.drawString("WorldX:" +player.worldx, x, y); y+=lineHeight;
			g2.drawString("WorldY:" +player.worldy, x, y); y+=lineHeight;
			g2.drawString("Col:" +(player.worldx+player.solidArea.x)/tileSize, x, y);y+=lineHeight;
			g2.drawString("Row:" +(player.worldy+player.solidArea.y)/tileSize, x, y);y+=lineHeight;
			
			g2.drawString("Draw Time:" +passed, x, y);
		}
		g2.dispose();
	}	
}