package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class Entity {
	
	GamePanel gp; 

    // Position and movement
    public int worldX, worldY; // Entity's position in the game world
    public int speed;          // Movement speed

    // Direction and sprite animation
    public String direction = "down"; // Default direction
    public int spriteCounter = 0;     // Counter for animation updates
    public int spriteNum = 1;         // Current sprite frame
    public int actionLock = 0; 
    public String dialgoue; 
    
    // Collision properties
    public Rectangle solidArea;       // Hitbox for collision detection
    public int solidAreaDefaultX, solidAreaDefaultY; // Default hitbox offsets
    public boolean collision = false; // Whether the entity is colliding

    // Sprite frames for animations
    public BufferedImage image; 
    public BufferedImage upFrame1, upFrame2, upFrame3;
    public BufferedImage downFrame1, downFrame2, downFrame3;
    public BufferedImage leftFrame1, leftFrame2;
    public BufferedImage rightFrame1, rightFrame2;

    public Entity(GamePanel gp) {
        solidArea = new Rectangle(4*3, 10*3, 5*3, 5*3);
        this.gp = gp; 
    }

	public void draw(Graphics2D g2d) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX ;  
		int screenY = worldY - gp.player.worldY + gp.player.screenY; 
		
		
		if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
				worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
				worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
				worldY - gp.TILE_SIZE< gp.player.worldY + gp.player.screenY
				) {
			
		    switch (direction) {
	        case "up":
	        	image = (spriteNum == 0 || spriteNum == 2) ? upFrame2 : 
	        		(spriteNum == 1 ? upFrame1 : upFrame3);
	            break;
	        case "down":
	        	image = (spriteNum == 0 || spriteNum == 2) ? downFrame2 : 
	        		(spriteNum == 1 ? downFrame1 : downFrame3);
	            break;
	        case "left":
	            image = (spriteNum % 2 == 0) ? leftFrame1 : leftFrame2;
	            break;
	        case "right":
	            image = (spriteNum % 2 == 0) ? rightFrame1 : rightFrame2;
	            break;
	        
	    }
		
		g2d.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null); 
			
			
		}
		
	}
	public void setAction() {
		 
	}
	public void update() {
		setAction(); 
		collision = false;
		gp.collisionChecker.CheckTile(this); 
		
        if (!collision) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        spriteCounter++; 
        if (spriteCounter > 12) {
            spriteNum++;
            if(spriteNum > 3) {
            	spriteNum = 0; 
            }
            spriteCounter = 0;
        }
	}
	

	public String getDialogue() {
		return dialgoue;
	}
}

