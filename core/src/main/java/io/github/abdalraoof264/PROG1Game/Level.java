package io.github.abdalraoof264.PROG1Game;

import java.util.ArrayList;

// Unsere Abstrakte Klasse, welche die Basis fuer die beiden Level ist
public abstract class Level {

    // Die Gegner Liste und Muenzen die gesammelt werden
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Item> items;
    protected ArrayList<Block> blocks;
    protected int requiredCoins;

    // Konstruktor
    public Level() {
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        blocks = new ArrayList<>();
    }

   // Diese Methode nimmt jede Klasse zum hinzufuegen
   public abstract void loadLevel();

    // Getter Methoden
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
}
