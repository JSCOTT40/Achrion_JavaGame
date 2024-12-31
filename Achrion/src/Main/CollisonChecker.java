package Main;

import Entities.Entity;

public class CollisonChecker {
	
	GamePanel gp; 
	
	public CollisonChecker(GamePanel gp) {
		this.gp = gp; 
	}
	
	public void CheckTile(Entity E) {
	    // Calculate entity boundaries
	    int leftX = E.worldX + E.solidArea.x;
	    int rightX = E.worldX + E.solidArea.x + E.solidArea.width;
	    int topY = E.worldY + E.solidArea.y;
	    int bottomY = E.worldY + E.solidArea.y + E.solidArea.height;

	    // Convert to tile positions
	    int leftCol = leftX / gp.TILE_SIZE;
	    int rightCol = rightX / gp.TILE_SIZE;
	    int topRow = topY / gp.TILE_SIZE;
	    int bottomRow = bottomY / gp.TILE_SIZE;

	    int tileNum1, tileNum2;

	    switch (E.direction) {
	        case "down":
	            bottomRow = (bottomY + E.speed) / gp.TILE_SIZE; // Adjust for movement
	            tileNum1 = gp.tileManager.mapTileNum[leftCol][bottomRow];
	            tileNum2 = gp.tileManager.mapTileNum[rightCol][bottomRow];
	            break;
	        case "up":
	            topRow = (topY - E.speed) / gp.TILE_SIZE;
	            tileNum1 = gp.tileManager.mapTileNum[leftCol][topRow];
	            tileNum2 = gp.tileManager.mapTileNum[rightCol][topRow];
	            break;
	        case "left":
	            leftCol = (leftX - E.speed) / gp.TILE_SIZE;
	            tileNum1 = gp.tileManager.mapTileNum[leftCol][topRow];
	            tileNum2 = gp.tileManager.mapTileNum[leftCol][bottomRow];
	            break;
	        case "right":
	            rightCol = (rightX + E.speed) / gp.TILE_SIZE;
	            tileNum1 = gp.tileManager.mapTileNum[rightCol][topRow];
	            tileNum2 = gp.tileManager.mapTileNum[rightCol][bottomRow];
	            break;
	        default:
	            return; // Exit if direction is invalid
	    }

	    // Check collision
	    if (gp.tileManager.tile[tileNum1].collison || gp.tileManager.tile[tileNum2].collison) {
	        E.collision = true;
	    }
	}

	
	public int checkObj(Entity E, boolean player) {
	    int index = 999;

	    for (int i = 0; i < gp.objects.length; i++) {
	        if (gp.objects[i] != null && gp.objects[i].solidArea != null) {
	            // Save original positions
	            int eSolidAreaX = E.solidArea.x;
	            int eSolidAreaY = E.solidArea.y;
	            int objSolidAreaX = gp.objects[i].solidArea.x;
	            int objSolidAreaY = gp.objects[i].solidArea.y;

	            // Update positions
	            E.solidArea.x = E.worldX + eSolidAreaX;
	            E.solidArea.y = E.worldY + eSolidAreaY;
	            gp.objects[i].solidArea.x = gp.objects[i].worldx + objSolidAreaX;
	            gp.objects[i].solidArea.y = gp.objects[i].worldy + objSolidAreaY;

	            // Move entity's solid area based on direction
	            switch (E.direction) {
	                case "up":
	                    E.solidArea.y -= E.speed;
	                    break;
	                case "down":
	                    E.solidArea.y += E.speed;
	                    break;
	                case "left":
	                    E.solidArea.x -= E.speed;
	                    break;
	                case "right":
	                    E.solidArea.x += E.speed;
	                    break;
	            }

	            // Check for collision
	            if (E.solidArea.intersects(gp.objects[i].solidArea)) {
	                if (gp.objects[i].collison) {
	                    E.collision = true;
	                }
	                if (player) {
	                    index = i;
	                }
	            }

	            // Reset positions
	            E.solidArea.x = eSolidAreaX;
	            E.solidArea.y = eSolidAreaY;
	            gp.objects[i].solidArea.x = objSolidAreaX;
	            gp.objects[i].solidArea.y = objSolidAreaY;
	        }
	    }

	    return index;
	}
	
	
	public int interactNPC(Entity E, boolean player) {
	    int index = 999;

	    for (int i = 0; i < gp.npcs.length; i++) {
	        if (gp.npcs[i] != null && gp.npcs[i].solidArea != null) {
	            // Save original positions
	            int eSolidAreaX = E.solidArea.x;
	            int eSolidAreaY = E.solidArea.y;
	            int npcSolidAreaX = gp.npcs[i].solidArea.x;
	            int npcSolidAreaY = gp.npcs[i].solidArea.y;

	            // Update solid areas to world positions
	            E.solidArea.x = E.worldX + eSolidAreaX;
	            E.solidArea.y = E.worldY + eSolidAreaY;
	            gp.npcs[i].solidArea.x = gp.npcs[i].worldX + npcSolidAreaX;
	            gp.npcs[i].solidArea.y = gp.npcs[i].worldY + npcSolidAreaY;

	            // Adjust entity's solid area based on direction
	            switch (E.direction) {
	                case "up" -> E.solidArea.y -= E.speed;
	                case "down" -> E.solidArea.y += E.speed;
	                case "left" -> E.solidArea.x -= E.speed;
	                case "right" -> E.solidArea.x += E.speed;
	            }

	            // Check for collision
	            if (E.solidArea.intersects(gp.npcs[i].solidArea)) {
	                if (gp.npcs[i].collision) {
	                    E.collision = true;
	                }
	                if (player) {
	                    index = i;
	                }
	            }

	            // Reset solid areas to original positions
	            E.solidArea.x = eSolidAreaX;
	            E.solidArea.y = eSolidAreaY;
	            gp.npcs[i].solidArea.x = npcSolidAreaX;
	            gp.npcs[i].solidArea.y = npcSolidAreaY;
	        }
	    }

	    return index;
	}


}
