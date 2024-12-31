package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

    public GamePanel gp;
    public Font font;
    String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        this.font = new Font("Arial", Font.PLAIN, 40); 
    }

    // Draw the pause screen
    public void drawPause(Graphics2D g) {
       g.setFont(font);
       g.setColor(Color.BLACK);

       String text = "PAUSED";

       int x = (gp.SCREEN_WIDTH )/2 - 100;
       int y = (gp.SCREEN_HEIGHT )/2;
       

       g.drawString(text, x, y);
 
    }


    // Draw a rounded subwindow
    public void drawSubWindow(Graphics2D g, int x, int y, int width, int height) {
        Color backgroundColor = new Color(0, 0, 0, 150); // Semi-transparent black
        Color borderColor = Color.WHITE;

        // Fill background
        g.setColor(backgroundColor);
        g.fillRoundRect(x, y, width, height, 35, 35);

        // Draw border
        g.setColor(borderColor);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x, y, width, height, 35, 35);
    }
    
    public void drawDialogue(Graphics2D g) {
        int x = gp.TILE_SIZE * 2;
        int y = gp.TILE_SIZE / 2;
        int width = gp.SCREEN_WIDTH - 4 * gp.TILE_SIZE;
        int height = 4 * gp.TILE_SIZE;

        drawSubWindow(g, x, y, width, height);

        g.setFont(font);
        g.setColor(Color.WHITE);

        int textX = x + gp.TILE_SIZE/2;
        int textY = y + gp.TILE_SIZE;
        for (String line : currentDialogue.split("\n")) {
            g.drawString(line, textX, textY);
            textY += 40; // Adjust line spacing
        }
     
    }
    public void setDialogue(String dialogue) {
    	this.currentDialogue = dialogue;
    }

	public void drawTitle(Graphics2D g2d) {
		
		g2d.setColor(new Color(70,110,120));
		g2d.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
		
		g2d.setFont(g2d.getFont().deriveFont(Font.ITALIC, 90F));
		String text = "Achrion";
		
		g2d.setColor(Color.black);
		g2d.drawString(text,6*gp.SCREEN_WIDTH/20 +5, gp.SCREEN_HEIGHT/7 +5); 
		
		g2d.setColor(Color.white); 
		g2d.drawString(text, 6*gp.SCREEN_WIDTH/20, gp.SCREEN_HEIGHT/7);
		
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 22F));
		String subText = "Press S to Continue";
		
		g2d.setColor(Color.black);
		g2d.drawString(subText, 8*gp.SCREEN_WIDTH/20 +2, 4*gp.SCREEN_HEIGHT/5 +2);
		
		g2d.setColor(Color.white); 
		g2d.drawString(subText, 8*gp.SCREEN_WIDTH/20, 4*gp.SCREEN_HEIGHT/5);
		
		BufferedImage TitleFrame = null; 
        
		try {
			BufferedImage TitleTemp = ImageIO.read(getClass().getResourceAsStream("/Player1Frames/Char_004.png"));
	        TitleFrame = TitleTemp.getSubimage(30, 2, 15, 22);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		g2d.drawImage(TitleFrame,360,200,2*gp.TILE_SIZE, 2*gp.TILE_SIZE, null); 
        
	}
}
