package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
	
	GamePanel GameP; 
	KeyHandler KeyH; 
	
	public final int screenX; 
	public final int screenY;
	
	public int HasKeys; 

	
	
	public Player(GamePanel gamep, KeyHandler keyh) {
		super(gamep); 
		this.GameP = gamep; 
		this.KeyH = keyh; 
		
		screenX = gamep.SCREEN_WIDTH/2 -(gamep.TILE_SIZE/2); 
		screenY = gamep.SCREEN_HEIGHT/2 - (gamep.TILE_SIZE/2); 
		
		solidArea = new Rectangle(); 
		solidArea.x = 4*3; 
		solidArea.y = 10*3; 
		solidArea.width = 5*3; 
		solidArea.height =5*3; 
		
		solidAreaDefaultX = solidArea.x; 
		solidAreaDefaultY = solidArea.y; 
						
		setDefault(); 
		getPlayerImage(); 
	}
	
	public void setDefault() {
		worldX = GameP.TILE_SIZE*15; 
		worldY = GameP.TILE_SIZE*13; 
		speed = 4; 
	}
	
	public void update() {
	    if ((KeyH.isUpPressed() || KeyH.isLeftPressed() || KeyH.isRightPressed() || KeyH.isDownPressed() 
	    		|| KeyH.isSpacePressed())&& GameP.gameState == GameP.PLAY_STATE) {
	        // Determine direction
	        if (KeyH.isUpPressed()) direction = "up";
	        if (KeyH.isDownPressed()) direction = "down";
	        if (KeyH.isLeftPressed()) direction = "left";
	        if (KeyH.isRightPressed()) direction = "right";

	        // Reset collision state
	        collision = false;

	        // Check collisions
	        GameP.collisionChecker.CheckTile(this);
	        int objIndex = GameP.collisionChecker.checkObj(this, true);
	        int NPCIndex = GameP.collisionChecker.interactNPC(this, true);
	        PickUpObj(objIndex);
	        NPCInteraction(NPCIndex); 
	        

	        // Move if no collision
	        if (!collision) {
	            switch (direction) {
	                case "up": worldY -= speed; break;
	                case "down": worldY += speed; break;
	                case "left": worldX -= speed; break;
	                case "right": worldX += speed; break;
	            }
	        }
	    }
	}
	public void draw(Graphics2D G2D) {
	    BufferedImage image = null;

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

	    // Draw player
	    G2D.drawImage(image, screenX, screenY, GameP.TILE_SIZE, GameP.TILE_SIZE, null);

	    // Update sprite animation
	    if ((KeyH.isUpPressed() || KeyH.isLeftPressed() 
	    		|| KeyH.isRightPressed() || KeyH.isDownPressed())&& GameP.gameState ==GameP.PLAY_STATE) {
	        spriteCounter++;
	        if (spriteCounter > 12) {
	            spriteNum++;
	            if(spriteNum > 3) {
	            	spriteNum = 0; 
	            }
	            spriteCounter = 0;
	        }
	    }
	}
	
	
	public void getPlayerImage() {
	    try {
	        int colchange = 24;
	        int rowchange = 24;
	        BufferedImage playerTemp = ImageIO.read(getClass().getResourceAsStream("/Player1Frames/Char_001.png"));

	        // Load frames
	        upFrame1 = playerTemp.getSubimage(6, 2 + 3 * rowchange, 15, 22);
	        upFrame2 = playerTemp.getSubimage(6 + colchange, 2 + 3 * rowchange, 15, 22);
	        upFrame3 = playerTemp.getSubimage(6 + 2 * colchange, 2 + 3 * rowchange, 15, 22);
	        downFrame1 = playerTemp.getSubimage(6, 2, 15, 22);
	        downFrame2 = playerTemp.getSubimage(6 + colchange, 2, 15, 22);
	        downFrame3 = playerTemp.getSubimage(6 + 2 * colchange, 2, 15, 22);
	        rightFrame1 = playerTemp.getSubimage(6, 2 + 2 * rowchange, 15, 22);
	        rightFrame2 = playerTemp.getSubimage(6 + colchange, 2 + 2 * rowchange, 15, 22);
	        leftFrame1 = playerTemp.getSubimage(6, 2 + rowchange, 15, 22);
	        leftFrame2 = playerTemp.getSubimage(6 + colchange, 2 + rowchange, 15, 22);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void PickUpObj(int i) {
		if(i != 999) {
			HasKeys++;
			GameP.playSoundEffect(1);
			GameP.objects[i] = null; 
		}
		//implement switch for obj name @param
	}
	public void NPCInteraction(int i) {
	    if (i != 999) {
	        GameP.gameState = GameP.DIALOGUE_STATE;

	        // Set dialogue text for the NPC
	        String npcDialogue = GameP.npcs[i].getDialogue();
	        GameP.uiGame.setDialogue(npcDialogue);
	    }
	}

}
