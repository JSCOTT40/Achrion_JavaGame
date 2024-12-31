package Main;

import Entities.NPC_Goblin;
import objects.ObjKey;

public class AssetCreator {
	GamePanel gp; 
	
	AssetCreator(GamePanel gp){
		this.gp = gp; 
	}
	
	public void setObjects() {
		gp.objects[0] = new ObjKey(); 
		gp.objects[0].worldx = 30*gp.TILE_SIZE; 
		gp.objects[0].worldy = 14*gp.TILE_SIZE;
		
		gp.objects[1] = new ObjKey(); 
		gp.objects[1].worldx = 22*gp.TILE_SIZE; 
		gp.objects[1].worldy = 54*gp.TILE_SIZE;
		
		
		gp.objects[2] = new ObjKey(); 
		gp.objects[2].worldx = 44*gp.TILE_SIZE; 
		gp.objects[2].worldy = 53*gp.TILE_SIZE;
	}
	public void setNPCS() {
		gp.npcs[0] = new NPC_Goblin(gp);
	}
	

}
