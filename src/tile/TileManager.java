package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[]tile;
	public int mapTileNum[][][];
//	public int mapTileNum[][];
	boolean drawPath = false;
	public ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		refreshMap();

//		Read tiles data file
		
////		loadMap("/maps/worldV3.txt",0); 
////		loadMap("/maps/interior01.txt",1);
	}
	
	public void refreshMap() {
		
		InputStream is = getClass(). getResourceAsStream("/maps/campuscol.txt");
		
		
		(gp.currentMap) {	
		case 0:is = getClass(). getResourceAsStream("/maps/campuscol.txt");break;
		case 1:is = getClass(). getResourceAsStream("/maps/maincolone.txt");break;
		case switch2:is = getClass(). getResourceAsStream("/maps/maintwocol.txt");break;
		case 3:is = getClass(). getResourceAsStream("/maps/mainthreecol.txt");break;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
////		Getting tile names and collision info from the file
		String line;
//		
	try {	
		while((line = br.readLine())!=null) {
			fileNames.add(line);
			collisionStatus.add(br.readLine());
//		
		}
		br.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
//		
		tile = new Tile [fileNames.size()];//No. of tiles	
		getTileImage();
////		 mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//		
//		
		switch(gp.currentMap) {	
		case 0:is = getClass().getResourceAsStream("/maps/campusmap.txt");break;
		case 1:is = getClass().getResourceAsStream("/maps/mainone.txt");break;
		case 2:is = getClass().getResourceAsStream("/maps/maintwo.txt");break;
		case 3:is = getClass().getResourceAsStream("/maps/mainthree.txt");break;
		}
////		
////		
////	
//	
		br = new BufferedReader(new InputStreamReader(is));
//		
		try {
	    
			String line2 = br.readLine();
			String maxTile[]  = line2.split(" ");
//			
			 gp.maxWorldCol = maxTile.length;
//			 
			 int rowCount = 1;
			    while (br.readLine() != null) {
			        rowCount++;
			    }
//
//			    // Set maxWorldRow to the total number of rows in the file
			    gp.maxWorldRow = rowCount;
//			    
//			
			 mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//		// mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
//
			br.close();
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//			
		}catch(IOException e) {
			System.out.println("Exception");
		}
//	
		switch(gp.currentMap) {	
		case 0:loadMap("/maps/campusmap.txt",0);break;
		case 1:loadMap("/maps/mainone.txt",1);break;
		case 2:loadMap("/maps/maintwo.txt",2);break;
		case 3:loadMap("/maps/mainthree.txt",3);break;
		}
////		
////		
////		
//	
		
	}
	
	public void getTileImage() {
		
		for(int i = 0; i<fileNames.size();i++) {
			
			String fileName;
			boolean collision;
			
			fileName = fileNames.get(i);
			
			if(collisionStatus.get(i).equals("true")) {
				collision = true;
			}
			else {
				collision = false;
			}
			
			setup(i,fileName, collision);
			
			
		}
	
	}
	
 public void loadMap(String filePath,int map) {
 	try {
		
 		InputStream is = getClass(). getResourceAsStream(filePath);
 		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
 		int col = 0;
 		int row =0;
		
 		while (row < gp.maxWorldRow) {
            String line = br.readLine();
            if (line == null) break; // If the line is null, break out of the loop

            // Split the line into numbers (space-separated)
            String[] numbers = line.split(" ");

            // Loop through each column in the row
            for (col = 0; col < numbers.length; col++) {
                if (col < gp.maxWorldCol) {
                    // Parse the tile number and store it in the mapTileNum array
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                }
            }
            // Move to the next row
            row++;
        }
 			br.close();
		
		
 	}catch(Exception e) {
		
 	}
 }
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			switch(gp.currentMap) {
			case 0: tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/campus/"+imageName));break;
			case 1: tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/main/"+imageName));break;
			case 2: tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/main/"+imageName));break;
			case 3: tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/main/"+imageName));break;
			}
			
			tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			System.out.println("Failed to load image for tile: " + imageName);
			e.printStackTrace();
		}
	}
		
	public void draw(Graphics2D g2) {
		
		int worldcol = 0;
		int worldrow = 0;
	
		// Calculate the boundaries for the camera
		int maxCameraX = gp.maxWorldCol * gp.tileSize - gp.screenWidth;
		int maxCameraY = gp.maxWorldRow * gp.tileSize - gp.screenHeight;
	
		// Calculate player's camera position, clamped to the map's edges
		int cameraX = gp.player.worldx - gp.player.screenx;
		int cameraY = gp.player.worldy - gp.player.screeny;
	
		// Clamp camera position to the boundaries of the map
		if (cameraX < 0) {
			cameraX = 0;
		} else if (cameraX > maxCameraX) {
			cameraX = maxCameraX;
		}
	
		if (cameraY < 0) {
			cameraY = 0;
		} else if (cameraY > maxCameraY) {
			cameraY = maxCameraY;
		}
	
		while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
			int tileNum = mapTileNum[gp.currentMap][worldcol][worldrow];
			
	
			int worldx = worldcol * gp.tileSize;
			int worldy = worldrow * gp.tileSize;
			
			
	
			// Calculate the screen position of each tile
			int screenx = worldx - cameraX;
			int screeny = worldy- cameraY;
	
			// Only draw tiles that are within the visible area
			if (screenx + gp.tileSize > 0 && screenx < gp.screenWidth && 
				screeny + gp.tileSize > 0 && screeny < gp.screenHeight) {
				g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
			}
	
			worldcol++;
			if (worldcol == gp.maxWorldCol) {
				worldcol = 0;
				worldrow++;
				
			}
			
		}
		
	}
}

