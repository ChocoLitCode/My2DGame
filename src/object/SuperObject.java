package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldx, worldy;
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public int solidAreaDefaultx = 0;
    public int solidAreaDefaulty = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        int screeny = worldy - gp.player.worldy + gp.player.screeny;

        if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
            worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
            worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
            worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {
       
        g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
    }

    }
}
