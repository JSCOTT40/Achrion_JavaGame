# Achrion Interactive Java Game

This is a 2D game where the player can move around a world, interact with NPCs, and pick up objects. The game is built using Java and Swing for the graphical interface. 
The player can interact with NPCs to trigger dialogue and perform various actions throughout the world. This projects main goal is to explore a more practical approach to some 
OOP Theory. 

## Features
- Player movement with keyboard controls.
- NPC interaction with dialogue display.
- Object pickup system (e.g., collecting keys).
- Tile-based world with collision detection.
- Game state management (Play, Pause, Dialogue).

## Requirements
- Java Development Kit (JDK) 8 or higher.
- Java Compilier 

# Controls
Arrow Keys: Move the player around the world.
Spacebar: Interact with objects and NPCs.
Escape: Pause the game.

How to Play

Move the player using the arrow keys to explore the world.
Interact with NPCs by moving near them and pressing the Spacebar. This will trigger a dialogue box where the NPC will talk to you.
Pick up objects such as keys by moving near them and interacting.
Pause the game by pressing the Escape key.

# Game States
- TITLE_STATE: The game is in the title screen (not implemented in this version).
- PLAY_STATE: The game is being played, and the player can move around.
- PAUSE_STATE: The game is paused, and you can resume by pressing a key.
- DIALOGUE_STATE: The player is interacting with an NPC, and dialogue is shown.
- INVENTORY_STATE: The player can view their collected items (to be Implemented in a later version).

# File Structure
```bash
src/
├── Main/
│   ├── GamePanel.java          # Main game logic and rendering.
│   ├── KeyHandler.java         # Handles keyboard input.
│   ├── Sound.java              # Manages game sounds and music.
|   ├── Main.Java               # Handles main Game call on running. 
│   ├── UI.java                 # Handles the user interface and drawing dialogue.
│   ├── CollisionChecker.java   # Checks for collisions between objects and the player.
│   └── AssetCreator.java       # Loads and sets up game assets (objects, NPCs, etc.).
├── Entities/
│   ├── Entity.java             # Base class for all entities in the game.
│   ├── Player.java             # Player class with movement and interaction logic.
│   └── NPC_Goblin.java         # NPC class with dialogue and interaction.
├── objects/
│   ├── KeyObj.java             # Key Class for specific instance of object for player. 
│   └── Superobject.java        # Class for objects that the player can interact with.
├── tiles/
│   ├── Tile.java               # Template class for all Tiles. 
│   └── TileManager.java        # Handles the drawing of tiles in the world.
└── PlayerFrames/
    ├── AreaTiles/              # Contains images for tiles. 
    ├── Player1Frames           # Contains images for entities.
    ├── Maps/                   # Contains map templates. 
    ├── objects/                # Contains Images for objects. 
    └── Sounds/                 # Contains all sounds assests.
```
# Contributing
Feel free to fork the repository, make changes, and submit pull requests. We welcome any improvements, bug fixes, or feature suggestions!


# Acknowledgments

The majority of the sprites and Assests were taken from: [Itch.io.](https://itch.io/game-assets/free/tag-16-bit) /n \n
[RyiSnow](https://www.youtube.com/@RyiSnow) was extreamly helpful for some of the more difficult areas of the Design and Implemantion. 

