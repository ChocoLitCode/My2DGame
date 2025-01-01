//MENU
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.OBJ_Heart;
import object.SuperObject;


public class UI {

	GamePanel gp;
	Graphics2D g2;
	BufferedImage heart_full, heart_half, heart_blank;
	
	public BufferedImage startButton, startButtonHover, optButton, optButtonHover, ruleButton, ruleButtonHover;

	public int startX, startY, startWidth, startHeight;
    public int optX, optY, optWidth, optHeight;
    public int ruleX, ruleY, ruleWidth, ruleHeight;
    public int boyX, boyY, boyWidth, boyHeight;
    public int girlX, girlY, girlWidth, girlHeight;
	public int overlayX, overlayWidth, overlayY, overlayHeight;
	
	public UI(GamePanel gp) {
		this.gp = gp;

        try {
	        // Load the spritesheet
			BufferedImage spritesheet = ImageIO.read(getClass().getResource("/button/buttons.png"));			
	        // Extract the button images from the spritesheet
	        startButtonHover = spritesheet.getSubimage(10, 64,92, 34); // Hover state of the Start button
	        startButton = spritesheet.getSubimage(128, 64, 96, 34);    // Normal state of the Start button
	        optButtonHover = spritesheet.getSubimage(10, 128,92, 34); // Hover state of the Start button
	        optButton = spritesheet.getSubimage(128, 128, 96, 34);
	        ruleButtonHover = spritesheet.getSubimage(10, 0,92, 34); // Hover state of the Start button
	        ruleButton = spritesheet.getSubimage(128, 0, 96, 34);

	    } catch (IOException e) {
	        e.printStackTrace(); // Handle any loading errors 
	}
	// CREATE A HUD OBJ
	// SuperObject heart = new OBJ_Heart(gp);
	// heart_full = heart.image;
	// heart_half = heart.image2;
	// heart_blank = heart.image3;
}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		//Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if (gp.gameState == gp.characterselectState) { // NEW SCREEN
	        // drawPlayerLife();
			drawCharacterSelectScreen();
	    }
		if (gp.gameState == gp.optionState){
			drawOptionOverlay(g2);
		}
		//  if (gp.gameState == gp.playState) { // NEW SCREEN
	    //     drawPlayerLife();
	    // }
	}

	// public void drawPlayerLife() {
	// 	int x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
	// 	int y = gp.tileSize * 2;
	// 	int i = 0;
	
	// 	// Debugging coordinates
	// 	System.out.println("Drawing hearts at starting position: x = " + x + ", y = " + y);
	
	// 	while (i < gp.player.maxLife / 2) {
	// 		if (heart_blank == null) {
	// 			System.out.println("heart_blank image is null!");
	// 			break;
	// 		}
	// 		System.out.println("Drawing heart at x: " + x + ", y: " + y);
	// 		g2.drawImage(heart_blank, x, y, null); // Draw the heart
	// 		i++;
	// 		x += gp.tileSize; // Move to the next position
	// 	}
	// }
	

	public void drawTitleScreen() {
		
		g2.setColor(new Color (70,120,80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//Title Name
		g2.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "College Odyssey: CpE Edition";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 1;
        
        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
       
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
        //START BUTTON
        startX = gp.screenWidth/2 - (gp.tileSize*2)/2;
        startY = gp.tileSize * 2;
        BufferedImage startImage = gp.mouseH.isStartHovered ? startButtonHover : startButton;
        g2.drawImage(startImage, startX, startY, 128, 64, null);

        optX = gp.screenWidth/2 - (gp.tileSize*2)/2;;
        optY = startY + startHeight + 60;
        BufferedImage optionImage = gp.mouseH.isOptHovered ? optButtonHover : optButton;
        g2.drawImage(optionImage, optX, optY, 128, 64, null);
        
        ruleX = gp.screenWidth/2 - (gp.tileSize*2)/2;;
        ruleY = optY + optHeight + 60;
        BufferedImage ruleImage = gp.mouseH.isRuleHovered ? ruleButtonHover : ruleButton;
        g2.drawImage(ruleImage, ruleX, ruleY, 128, 64, null);
        
	}
	
public void drawCharacterSelectScreen() {
		
		g2.setColor(new Color (70,120,80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		
		g2.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "SELECT YOUR CHARACTER ";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 1;
        
//        Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
		x = gp.screenWidth/16 - (gp.tileSize*16)/16;
		g2.setFont(new Font("Arial", Font.BOLD, 35));
        
        text = "BOY";
        boyX = gp.screenWidth/2 - (gp.tileSize*2)/2;
        boyY = gp.tileSize * 2;
        boyWidth = g2.getFontMetrics().stringWidth(text);
        boyHeight = g2.getFontMetrics().getHeight();
        g2.drawString(text, boyX, boyY);
        
        text = "GIRL";
        girlX = gp.screenWidth/2 - (gp.tileSize*2)/2;;
        girlY = startY + startHeight + 40;
        girlWidth = g2.getFontMetrics().stringWidth(text);
        girlHeight = g2.getFontMetrics().getHeight();
        g2.drawString(text, girlX, girlY);
        
	}
	
	public int getXforCenteredText (String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
		
	}

	public void drawOptionOverlay(Graphics2D g2) {
		overlayWidth = gp.screenWidth / 3;  // Reduce the width
		overlayHeight = (gp.screenHeight * 3) / 5;  // Increase the height
		overlayX = (gp.screenWidth - overlayWidth) / 2;
		overlayY = (gp.screenHeight - overlayHeight) / 2;
	
		// Draw background
		g2.setColor(new Color(0, 0, 0, 200)); // Semi-transparent black
		g2.fillRect(overlayX, overlayY, overlayWidth, overlayHeight);
	
		// Draw border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(overlayX, overlayY, overlayWidth, overlayHeight);
	
		// Draw options text
		g2.setFont(new Font("Arial", Font.BOLD, 24));
		g2.setColor(Color.white);
	
		String title = "Options";
		int titleX = overlayX + (overlayWidth - getTextWidth(g2, title)) / 2;
		int titleY = overlayY + 50;
		g2.drawString(title, titleX, titleY);
	
		String option1 = "Sound: ON/OFF";
		String back = "Back";
	
		int option1X = overlayX + 20;
		int option1Y = overlayY + 120;
		g2.drawString(option1, option1X, option1Y);

		int backWidth = getTextWidth(g2, back);
		int backX = overlayX + (overlayWidth - backWidth) / 2;
		int backY = overlayY + overlayHeight - 40;
		g2.drawString(back, backX, backY);
	}

	public void drawRuleOverlay(Graphics2D g2) {
		overlayWidth = gp.screenWidth / 3;  // Reduce the width
		overlayHeight = (gp.screenHeight * 3) / 5;  // Increase the height
		overlayX = (gp.screenWidth - overlayWidth) / 2;
		overlayY = (gp.screenHeight - overlayHeight) / 2;
	
		// Draw background
		g2.setColor(new Color(0, 0, 0, 200)); // Semi-transparent black
		g2.fillRect(overlayX, overlayY, overlayWidth, overlayHeight);
	
		// Draw border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(overlayX, overlayY, overlayWidth, overlayHeight);
	
		// Draw options text
		g2.setFont(new Font("Arial", Font.BOLD, 24));
		g2.setColor(Color.white);
	
		String title = "RULES";
		int titleX = overlayX + (overlayWidth - getTextWidth(g2, title)) / 2;
		int titleY = overlayY + 50;
		g2.drawString(title, titleX, titleY);
	
		String rule1 = "RULE 1:";
		String back = "Back";
	
		int ruleX = overlayX + 20;
		int ruleY = overlayY + 120;
		g2.drawString(rule1, ruleX, ruleY);
	
		int backWidth = getTextWidth(g2, back);
		int backX = overlayX + (overlayWidth - backWidth) / 2;
		int backY = overlayY + overlayHeight - 40;
		g2.drawString(back, backX, backY);
	}
	
	private int getTextWidth(Graphics2D g2, String text) {
		return g2.getFontMetrics().stringWidth(text);
	}
		
}