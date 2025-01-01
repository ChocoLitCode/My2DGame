//PLAYER
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity {
    
    
	GamePanel gp;
    KeyHandler keyH;

    public int screenx;
    public int screeny;

    BufferedImage spriteSheet;
    BufferedImage[][] animations; // 4 directions (down, left, right, up) with 4 frames each
    BufferedImage[][] idleAnimations; // 4 directions for AFK (breathing), 4 frames each
    public String direction;
    
    int spriteCounter = 0;
    int spriteNum = 1;
    int directionIndex = 0; // 0 = down, 1 = left, 2 = right, 3 = up
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
 
//        center screen position
        screenx = (gp.screenWidth/2) - (gp.tileSize/2);
        screeny = (gp.screenHeight/2) - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9 * gp.scale;
        solidArea.y = 7 * gp.scale;
         solidAreaDefaultX =solidArea.x;
      solidAreaDefaultY=solidArea.y;
        solidArea.width = 14 * gp.scale;
        solidArea.height = 25 * gp.scale;
        
        
        setDefaultValues();
        loadPlayerSprites();
    }
    
    public void setDefaultValues() {
//    	player position in map
        direction = "";
        worldx = gp.tileSize*21;
        worldy = gp.tileSize*6;
        speed = 4;

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
      
    }
    
    public void loadPlayerSprites() {
        try {
        	if (gp.selectedCharacter == null) {
                // Handle the case where selectedCharacter is null (set to default if needed)
                gp.selectedCharacter = "Girl"; // Set default if not set
//              gp.selectedCharacter = "Boy";
            }

        	 if (gp.selectedCharacter=="Boy") {
            	
                spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/boy_spritesheet.png"));
               
            } else if (gp.selectedCharacter == "Girl") {
            
                spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/girl_spritesheet.png"));
                
            }
        	
        //    Initialize walking animations and idle (AFK) animations
            animations = new BufferedImage[4][4]; // 4 directions, 4 frames each
            idleAnimations = new BufferedImage[4][4]; // 4 AFK directions, 4 frames each
            
            for (int dir = 0; dir < 4; dir++) {
                for (int frame = 0; frame < 4; frame++) {
                    // Walking frames (rows 0, 2, 4, 6)
                    animations[dir][frame] = getSprite(frame, dir * 2, 32, 32);
                    // Idle (AFK) frames (rows 1, 3, 5, 7)
                    idleAnimations[dir][frame] = getSprite(frame, dir * 2 + 1, 32, 32);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public BufferedImage getSprite(int col, int row, int tileWidth, int tileHeight) {
        return spriteSheet.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
    }
    
   public void update() {
    boolean moving = false;
    // Update direction based on key presses
    if (keyH.upPressed) {
        direction = "up";
        directionIndex = 3;
        moving = true;
    } else if (keyH.downPressed) {
        direction = "down";
        directionIndex = 0;
        moving = true;
    } else if (keyH.leftPressed) {
        direction = "left";
        directionIndex = 1;
        moving = true;
    } else if (keyH.rightPressed) {
        direction = "right";
        directionIndex = 2;
        moving = true;
    } else {
        direction = "";
    }

    // Check collision before moving
    collisionOn = false;
    gp.cChecker.checkTile(this);
    gp.eHandler.checkEvent();

    if (collisionOn==false) {
        // Only update position if there's no collision
        switch(direction) {
            case "up":
                worldy -= speed;
                break;
            case "down":
                worldy += speed;
                break;
            case "left":
                worldx -= speed;
                break;
            case "right":
                worldx += speed;
                break;
        }
    }
    
    spriteCounter++;
    if (moving) {
        if (spriteCounter > 10) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }
    } else {
        if (spriteCounter > 40) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }
    }
}


    
    /**
     * Draws the player on the screen based on direction and frame number.
     */
   public void draw(Graphics2D g2) {
       BufferedImage image;
       // System.out.println("Drawing player at position: " + screenx + ", " + screeny);

       // If the player is moving, show walking animations
       if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
           image = animations[directionIndex][spriteNum - 1];
       } else {
           // Player is AFK, show idle animation (breathing)
           image = idleAnimations[directionIndex][spriteNum - 1];
       }
       
       int x = screenx;
       int y = screeny;
       
       if(screenx>worldx) {
       	x = worldx;
       }
       if(screeny>worldy) {
       	y= worldy;
       }
      

       
       g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
g2.setColor(Color.red);
g2.drawRect(screenx+solidArea.x, screeny+solidArea.y,solidArea.width , solidArea.height);
   }
}































