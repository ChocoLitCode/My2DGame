//KEYHANDLER
package main;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean shotKeyPressed, upPressed, downPressed, leftPressed, rightPressed, enterpressed;
	boolean showDebugText=false;
	
	public KeyHandler(GamePanel gp) {
        this.gp = gp; // Store the reference to the GamePanel
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			upPressed = true;
			// gp.player.direction = "up";
		}
		if(code == KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed= true;
			// gp.player.direction = "down";
		}
		if(code == KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			leftPressed= true;
			// gp.player.direction = "left";
		}
		if(code == KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			rightPressed=true;
			// gp.player.direction = "right";
		}
		
		if(code ==KeyEvent.VK_ESCAPE) {	
			//Imageicon icon = new Imageicon("boy_down1.png");
			int confirm = JOptionPane.showConfirmDialog(null , "Are you sure you want to exit?","Confirm Exit", JOptionPane.YES_NO_OPTION);
			if ( confirm  == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null , "Thanks for playing!","Confirm Exit",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
		if (code==KeyEvent.VK_T) {
			if(showDebugText==false) {
				showDebugText=true;
			}
			else if(showDebugText==true) {
				showDebugText=false;
			}
		}
		
		if(code == KeyEvent.VK_R) {
			switch(gp.currentMap) {
			case 0: gp.tileM.loadMap("/maps/campusmap.txt",0);break;
			case 1: gp.tileM.loadMap("/maps/mainone.txt",1);break;
			case 2: gp.tileM.loadMap("/maps/maintwo.txt",2);break;
			case 3: gp.tileM.loadMap("/maps/mainthree.txt",3);break;
			}
			
		}
		
		//		Debug
		switch(code) {
		case KeyEvent.VK_F1: gp.currentMap=0;gp.tileM.refreshMap();break;
		case KeyEvent.VK_F2: gp.currentMap=1;gp.tileM.refreshMap();break;
		case KeyEvent.VK_F3: gp.currentMap=2;gp.tileM.refreshMap();break;
		case KeyEvent.VK_F4: gp.currentMap=3;gp.tileM.refreshMap();break;
		}
			
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code =e.getKeyCode();

		if(code == KeyEvent.VK_W || code==KeyEvent.VK_UP ) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed= false;
		}
		if(code == KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			leftPressed= false;
		}
		if(code == KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			rightPressed=false;
		}
	}
}
