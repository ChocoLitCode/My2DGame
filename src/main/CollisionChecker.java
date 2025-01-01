package main;

import entity.Entity;
import entity.Player;


public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Player entity) {
		int entityLeftWorldX = entity.worldx + entity.solidArea.x;
		int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldy + entity.solidArea.y;
		int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;
	
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
	System.out.println("Entity Left Col: " + entityLeftCol + ", Entity Top Row: " + entityTopRow);
    System.out.println("Entity Right Col: " + entityRightCol + ", Entity Bottom Row: " + entityBottomRow);
		int tileNum1, tileNum2;
	System.out.println("Checking TileNum1: (" + entityLeftCol + ", " + entityTopRow + ")");
System.out.println("Checking TileNum2: (" + entityRightCol + ", " + entityTopRow + ")");
		// Check the direction and perform collision detection
		switch (entity.direction) {
			case "up":
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
				System.out.println("tileNum1: " + tileNum1 + ", tileNum2: " + tileNum2);
	
				if (gp.tileM.tile[tileNum1].collision ==true || gp.tileM.tile[tileNum2].collision==true) {
					entity.collisionOn = true;
					System.out.println("Collision detected at tiles: " + tileNum1 + ", " + tileNum2);
					// Adjust player position after collision
					entity.worldy = (entityTopRow + 1) * gp.tileSize - entity.solidArea.y;
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
	
				if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision==true) {
					entity.collisionOn = true;
					entity.worldy = entityBottomRow * gp.tileSize - entity.solidArea.y - entity.solidArea.height;
				}
				break;
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
				
				tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
	
				if (gp.tileM.tile[tileNum1].collision==true|| gp.tileM.tile[tileNum2].collision==true) {
					entity.collisionOn = true;
					entity.worldx = (entityLeftCol + 1) * gp.tileSize - entity.solidArea.x;
				}
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
	
				if (gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true) {
					entity.collisionOn = true;
					entity.worldx = entityRightCol * gp.tileSize - entity.solidArea.x - entity.solidArea.width;
				}
				break;
		}
	}
	

}
