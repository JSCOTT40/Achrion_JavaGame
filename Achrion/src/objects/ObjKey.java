package objects;

import java.io.IOException;

import javax.imageio.ImageIO;


public class ObjKey extends Superobject {
	
	public ObjKey() {
		name = "Key"; 
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Chest.png")); 
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
