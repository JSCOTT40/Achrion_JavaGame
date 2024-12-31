package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class Superobject {
	
	public BufferedImage image;
	public String name; 
	public boolean collison; 
	public int worldx, worldy; 
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefY, solidAreaDefX; 
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldx - gp.player.worldX + gp.player.screenX ;  
		int screenY = worldy - gp.player.worldY + gp.player.screenY; 
		
		
		if(worldx + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
				worldx - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
				worldy + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
				worldy - gp.TILE_SIZE< gp.player.worldY + gp.player.screenY
				) {
			g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null); 	
			
		}
	}

}
