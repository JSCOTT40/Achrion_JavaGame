package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {
	
	public Tile[] tile;
	GamePanel gp; 
	public int mapTileNum [][]; 
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp; 
		tile = new Tile[10]; 
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow]; 
		getTileImage(); 
		loadMap("/maps/worldmap.txt"); 
		
	}
	
	public void getTileImage() {
		
	try { 
		BufferedImage grassImage = ImageIO.read(getClass().getResourceAsStream("/AreaTiles/Cliff_Tile.png"));
		BufferedImage WaterImage = ImageIO.read(getClass().getResourceAsStream("/AreaTiles/Water_Tile.png"));
		BufferedImage PathImage = ImageIO.read(getClass().getResourceAsStream("/AreaTiles/Path_Tile.png"));

		tile[0] = new Tile();  
		tile[0].image = grassImage.getSubimage(16, 82, 16, 14); 
		
		tile[1] = new Tile(); 
		tile[1].image = ImageIO.read(getClass().getResourceAsStream("/AreaTiles/Wall.png"));
		tile[1].collison = true; 
		
		tile[2] = new Tile(); 
		tile[2].image = WaterImage.getSubimage(16, 80, 16, 14); 
		tile[2].collison = true; 
		
		tile[3] = new Tile(); 
		tile[3].image = ImageIO.read(getClass().getResourceAsStream("/AreaTiles/floor.png"));
		
		tile[4] = new Tile(); 
		tile[4].image = grassImage.getSubimage(0, 50, 30, 30); 
		tile[4].collison = true; 
		
		tile[5] = new Tile(); 
		tile[5].image = PathImage.getSubimage(0, 0, 48, 48); 

	}catch(IOException e){
		e.printStackTrace(); 
		
		}
	}
	
	public void draw (Graphics2D g2) {
		
		int worldcol = 0;
		int worldrow = 0; 

		
		while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldcol][worldrow]; 
			
			int worldX = worldcol* gp.TILE_SIZE; 
			int worldY = worldrow*gp.TILE_SIZE; 
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX ;  
			int screenY = worldY - gp.player.worldY + gp.player.screenY; 
			
			
			if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
					worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
					worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
					worldY - gp.TILE_SIZE< gp.player.worldY + gp.player.screenY
					) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null); 	
				
			}
			
			worldcol++; 
			if(worldcol == gp.maxWorldCol) {
				worldcol = 0;  
				worldrow++; 
			}
		}

	}
	
	
	public void loadMap(String path) {
		try {
			InputStream is = getClass().getResourceAsStream(path); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			
			int col = 0;
			int row = 0; 
 	
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine(); 
				
				while(col < gp.maxWorldCol) {
					String numbers [] = line.split(" "); 
					int num = Integer.parseInt(numbers[col]); 
					
					mapTileNum[col][row] = num; 
					col++; 					
				}
				if(col == gp.maxWorldCol) {
					col = 0; 
					row++; 
				} 
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace(); 
			
			
		} 
	}

}
