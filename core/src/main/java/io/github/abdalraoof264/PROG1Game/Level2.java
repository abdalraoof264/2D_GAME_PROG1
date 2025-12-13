package io.github.abdalraoof264.PROG1Game;

/**
 * Level 2 - Schwieriger
 * Mehr Gegner, mehr Münzen, komplexere Plattformen
 */
public class Level2 extends Level {

    @Override
    public void loadLevel() {
        // Hintergrund (kannst du später einen anderen nutzen)
        backgroundTexture = "gameBackground.png";

        // Mehr Münzen als Level 1
        requiredCoins = 3;

        // Mehr Gegner, die sich weiter bewegen
        enemies.add(new Enemy("enemy1.png", 300, 100, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy1.png", 700, 100, 95, 95, 100f, 300f));
        enemies.add(new Enemy("enemy1.png", 1100, 100, 95, 95, 150f, 150f));

        // Mehr Münzen, schwieriger zu erreichen
        items.add(new Item("Muenzen.png", 400, 350));
        items.add(new Item("Muenzen.png", 800, 250));
        items.add(new Item("Muenzen.png", 1200, 300));

        // Mehr Plattformen für mehr Herausforderung
        blocks.add(new Block("Block.png", 400, 200));
        blocks.add(new Block("Block.png", 600, 250));
        blocks.add(new Block("Block.png", 800, 200));
        blocks.add(new Block("Block.png", 1000, 280));
    }
}
