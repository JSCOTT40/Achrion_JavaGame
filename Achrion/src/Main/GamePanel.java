package Main;

import java.awt.*;
import javax.swing.*;

import Entities.Entity;
import Entities.Player;
import objects.Superobject;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // Constants
    public final int FPS = 60;
    public final int ORG_TILE_SIZE = 16;
    public final int SCALE = 3;
    public final int TILE_SIZE = ORG_TILE_SIZE * SCALE;
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    private static final long serialVersionUID = 1L;

    // World settings
    public final int maxWorldCol = 56;
    public final int maxWorldRow = 65;

    // Game states
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DIALOGUE_STATE = 3;
    public final int INVENTORY_STATE = 4;

    public int gameState = TITLE_STATE;

    // Game components
    public final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final Sound sound = new Sound();
    public final CollisonChecker collisionChecker = new CollisonChecker(this);
    public final Player player = new Player(this, keyHandler);
    public final Superobject[] objects = new Superobject[10];
    private final AssetCreator assetCreator = new AssetCreator(this);
    public final UI uiGame = new UI(this);
    public final Entity[] npcs = new Entity[10];

    private Thread gameThread;

    // Constructor
    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    // Start game thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Set up game objects
    public void setUpObjs() {
        assetCreator.setObjects();
        assetCreator.setNPCS();
        //playMusic(0);
    }

    // Main game loop
    @Override
    public void run() {
        double frameInterval = 1_000_000_000.0 / FPS;
        double nextDrawTime = System.nanoTime() + frameInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                Thread.sleep(Math.max((long) (remainingTime / 1_000_000.0), 0));
                nextDrawTime += frameInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Update game logic
    private void update() {
        switch (gameState) {
            case TITLE_STATE -> {
                if (keyHandler.isSPressed()) gameState = PLAY_STATE;
            }

            case DIALOGUE_STATE -> {
                if (keyHandler.isSPressed()) gameState = PLAY_STATE;
            }
            
            case PLAY_STATE -> {
                player.update();
                for (Entity npc : npcs) {
                    if (npc != null) npc.update();
                }
            }
        }
    }

    // Render game components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameState == TITLE_STATE) {
            uiGame.drawTitle(g2d);
        } else {
            tileManager.draw(g2d);

            for (Superobject obj : objects) {
                if (obj != null) obj.draw(g2d, this);
            }

            for (Entity npc : npcs) {
                if (npc != null) npc.draw(g2d);
            }

            player.draw(g2d);

            if (gameState == PAUSE_STATE) uiGame.drawPause(g2d);
            if (gameState == DIALOGUE_STATE) {
                if (player.HasKeys == 3) uiGame.setDialogue("Good Job.");
                uiGame.drawDialogue(g2d);
            }
        }

        g2d.dispose();
    }

    // Music and sound effects
    public void playMusic(int track) {
        sound.setFile(track);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int effect) {
        sound.setFile(effect);
        sound.play();
    }
}



 