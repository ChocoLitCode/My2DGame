package main;



public class EventHandler {
	GamePanel gp;
	
	EventRect eventRect[][][];
	int previousEventX,previousEventY;
	boolean canTouchEvent = true;

	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int map=0;
		int col =0;
		int row = 0;
		while(map<gp.maxMap && col<gp.maxWorldCol && row<gp.maxWorldRow) {
		
			eventRect[map][col][row]= new EventRect();
		eventRect[map][col][row].x=23;
		eventRect[map][col][row].y=23;
		eventRect[map][col][row].width = 2;
		eventRect[map][col][row].height = 2;
		eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
		eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
		col++;
		if(col ==gp.maxWorldCol) {
			col = 0;
			row++;
			
			if(row ==gp.maxWorldRow) {
				row=0;
				map++;
			}
		}
		}
		
		}
	
	public void checkEvent() {
//		Check if the player character is more than 1 tile away from the last event
		
		int xDistance = Math.abs(gp.player.worldx -previousEventX);
		int yDistance = Math.abs(gp.player.worldy - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance>gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent==true) {
		
		if(hit(0,20,13,"left")==true) {teleport(1,2,10);}
		else if(hit(1,2,10,"down")==true){ teleport(0,20,13);}
		}
	}
	public boolean hit(int map,int col,int row, String reqDirection) {
		boolean hit=false;
		if(map ==gp.currentMap) {
		gp.player.solidArea.x = gp.player.worldx +gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldy +gp.player.solidArea.y;
		eventRect[map][col][row].x = col*gp.tileSize +eventRect[map][col][row].x;
		eventRect[map][col][row].y = row*gp.tileSize +eventRect[map][col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[map][col][row])&& eventRect[map][col][row].eventDone==false) {
			if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("")) {
					hit=true;
					
					previousEventX = gp.player.worldx;
					previousEventY = gp.player.worldy;
					}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y =gp.player.solidAreaDefaultY;
		eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
		eventRect[map][col][row].y =eventRect[map][col][row]. eventRectDefaultY;
		}
		return hit;
	}
	public void teleport(int map,int col,int row) {
		gp.currentMap = map;
		gp.tileM.refreshMap();
		gp.player.worldx = gp.tileSize *col;
		gp.player.worldy = gp.tileSize*row;
		previousEventX = gp.player.worldx;
		previousEventY = gp.player.worldy;
		canTouchEvent=false;
	}
	
	
}
