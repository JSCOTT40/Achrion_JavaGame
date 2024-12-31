package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_Goblin extends Entity {
	GamePanel gamep;
	String Dialgoue; 
	
	
	public NPC_Goblin(GamePanel gamep) {
		super(gamep); 
		direction = "down"; 
		worldX = gamep.TILE_SIZE*14; 
		worldY = gamep.TILE_SIZE*18; 
		speed = 0;
		dialgoue = "I require 3 Treasures\n"
				+ "from around the Island."; 
		 
		getGoblinImage(); 
	}
	public void getGoblinImage() {
		
	    try {
	        int colchange = 24;
	        int rowchange = 24;
		    BufferedImage playerTemp = ImageIO.read(getClass().getResourceAsStream("/Player1Frames/Char_005.png"));

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
		    } 
	    	
	    	catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	
	@Override 
	
	public void setAction() {
        if(actionLock == 30){
			String[] directions = {"up", "down", "left", "right"};
	        Random random = new Random();
	        direction = directions[random.nextInt(directions.length)];
	        actionLock =0; 
        }
        actionLock++; 
	}
}
