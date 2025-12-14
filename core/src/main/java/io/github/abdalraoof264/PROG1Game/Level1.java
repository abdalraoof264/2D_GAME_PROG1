package io.github.abdalraoof264.PROG1Game;

public class Level1 extends Level {

    // Die Klasse erbt und nutzt die Methoden von Level
    @Override
    public void loadLevel() {

        // Anzahl zu sammelnder Muenzen
        requiredCoins = 5;

        // Gegner hinzufuegen in eine Liste
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  500, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  1200, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  1350, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  2250, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  3350, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  3000, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1Right.png", "enemy1Left.png",  3620, 350, 95, 95, 150f, 50f));

        // Items/Münzen hinzufuegen
        items.add(new Item("Muenzen.png", 855, 400));
        items.add(new Item("Muenzen.png", 1200, 200));
        items.add(new Item("Muenzen.png", 1895, 500));
        items.add(new Item("Muenzen.png", 2150, 200));
        items.add(new Item("Muenzen.png", 3805, 400));

        // Bloecke hinzufuegen
        blocks.add(new Block("Block.png", 550, 230));
        blocks.add(new Block("Block.png", 605, 230));
        blocks.add(new Block("Block.png", 660, 230));
        blocks.add(new Block("Block.png", 850, 330));
        blocks.add(new Block("Block.png", 1500, 100));
        blocks.add(new Block("Block.png", 1555, 100));
        blocks.add(new Block("Block.png", 1610, 100));
        blocks.add(new Block("Block.png", 1555, 150));
        blocks.add(new Block("Block.png", 1610, 150));
        blocks.add(new Block("Block.png", 1610, 200));
        blocks.add(new Block("Block.png", 1890, 400));
        blocks.add(new Block("Block.png", 2350, 250));
        blocks.add(new Block("Block.png", 2700, 350));
        blocks.add(new Block("Block.png", 3080, 300));
        blocks.add(new Block("Block.png", 3135, 300));
        blocks.add(new Block("Block.png", 3480, 300));
        blocks.add(new Block("Block.png", 3590, 300));
        blocks.add(new Block("Block.png", 3535, 300));
        blocks.add(new Block("Block.png", 3800, 300));
    }
}
