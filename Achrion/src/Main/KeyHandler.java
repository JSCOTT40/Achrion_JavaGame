package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {

    private final GamePanel gp;
    private final Map<Integer, Boolean> keyStates = new HashMap<>();

    // Constructor that initializes the GamePanel reference and keyStates
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
        keyStates.put(KeyEvent.VK_UP, false);
        keyStates.put(KeyEvent.VK_DOWN, false);
        keyStates.put(KeyEvent.VK_LEFT, false);
        keyStates.put(KeyEvent.VK_RIGHT, false);
        keyStates.put(KeyEvent.VK_SPACE, false);
        keyStates.put(KeyEvent.VK_S, false);
    }

    // Check if a specific key is pressed
    public boolean isKeyPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }

    public boolean isUpPressed() {
        return isKeyPressed(KeyEvent.VK_UP);
    }

    public boolean isDownPressed() {
        return isKeyPressed(KeyEvent.VK_DOWN);
    }

    public boolean isLeftPressed() {
        return isKeyPressed(KeyEvent.VK_LEFT);
    }

    public boolean isRightPressed() {
        return isKeyPressed(KeyEvent.VK_RIGHT);
    }

    public boolean isSpacePressed() {
        return isKeyPressed(KeyEvent.VK_SPACE);
    }
    
    public boolean isSPressed() {
    	return isKeyPressed(KeyEvent.VK_S); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (keyStates.containsKey(code)) {
            keyStates.put(code, true);
        }

        if (code == KeyEvent.VK_SPACE) {
            if (gp.gameState == gp.PLAY_STATE) {
                gp.gameState = gp.PAUSE_STATE;
            } else if (gp.gameState == gp.PAUSE_STATE) {
                gp.gameState = gp.PLAY_STATE;
            }
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (keyStates.containsKey(code)) {
            keyStates.put(code, false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}

