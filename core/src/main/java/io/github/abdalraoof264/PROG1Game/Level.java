package io.github.abdalraoof264.PROG1Game;

import java.util.ArrayList;

/**
 * Abstrakte Basis-Klasse für alle Levels
 * Jedes Level erbt von dieser Klasse und implementiert loadLevel()
 */
public abstract class Level {
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Item> items;
    protected ArrayList<Block> blocks;
    protected int requiredCoins;  // Wie viele Münzen man sammeln muss
    protected String backgroundTexture;  // Hintergrund für das Level

    public Level() {
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        blocks = new ArrayList<>();
    }

    /**
     * Diese Methode muss jedes Level implementieren
     * Hier werden Gegner, Items und Blöcke hinzugefügt
     */
    public abstract void loadLevel();

    // Getter-Methoden
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getRequiredCoins() {
        return requiredCoins;
    }

    public String getBackgroundTexture() {
        return backgroundTexture;
    }
}
