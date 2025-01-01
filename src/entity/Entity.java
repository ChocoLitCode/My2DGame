package entity;

import java.awt.Rectangle;

public class Entity {
	
	public int worldx,worldy;
	public int speed;
	
//	public BufferedImage up1, up2,up3,up4,upAFK,upAFK2,upAFK3,upAFK4,down1,down2, down3,down4,downAFK,downAFK2,downAFK3,downAFK4,
//	left1,left2,left3,left4,leftAFK,leftAFK2,leftAFK3,leftAFK4,right1,right2,right3,right4,rightAFK,rightAFK2,rightAFK3,rightAFK4;
	
	//CHARACTER STATUS
	public int maxLife;
	public int life;
	
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
//
	
	public Rectangle solidArea; 
	// solidArea = new Rectangle(36,28,56,100);
	 public int solidAreaDefaultX,solidAreaDefaultY;
     
	public boolean collisionOn = false;
	// public boolean moving = false;
	
	
}
