package io.github.abdalraoof264.PROG1Game;

/**
 * Level 1 - Tutorial/Einfaches Level
 * Wenige Gegner, einfache Plattformen
 */
public class Level1 extends Level {

    @Override
    public void loadLevel() {
        // Hintergrund
        backgroundTexture = "gameBackground.png";

        // Wie viele Münzen muss der Spieler sammeln?
        requiredCoins = 2;

        // Gegner hinzufügen (wenige und einfach)
        enemies.add(new Enemy("enemy1.png", 400, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1.png", 600, 100, 95, 95));

        // Items/Münzen hinzufügen
        items.add(new Item("Muenzen.png", 500, 300));
        items.add(new Item("Muenzen.png", 1000, 200));

        // Blöcke/Plattformen hinzufügen
        blocks.add(new Block("Block.png", 500, 200));
        blocks.add(new Block("Block.png", 900, 250));
    }
}
