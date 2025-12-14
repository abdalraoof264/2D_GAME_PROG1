package io.github.abdalraoof264.PROG1Game;

// Selber Aufbau wie in Level1
public class Level2 extends Level {

    @Override
    public void loadLevel() {

        // Muenzenanzahl
        requiredCoins = 6;

        // Gegner
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  400, 95, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  1800, 95, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  2000, 95, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  2750, 95, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  2950, 95, 95, 95, 200f, 200f));
        enemies.add(new Enemy("enemy2Right.png", "enemy2Left.png",  3250, 95, 95, 95, 200f, 200f));

        // Fliegende Gegner
        enemies.add(new Enemy("batRight.png", "batLeft.png",  1300, 380, 95, 95, 150f, 150f));
        enemies.add(new Enemy("batRight.png", "batLeft.png",  2340, 210, 95, 95, 150f, 150f));
        enemies.add(new Enemy("batRight.png", "batLeft.png",  3140, 210, 95, 95, 150f, 150f));
        enemies.add(new Enemy("batRight.png", "batLeft.png",  3440, 210, 95, 95, 150f, 150f));

        // Muenzen
        items.add(new Item("Muenzen.png", 400, 250));
        items.add(new Item("Muenzen.png", 1455, 400));
        items.add(new Item("Muenzen.png", 2755, 450));
        items.add(new Item("Muenzen.png", 3750, 455));
        items.add(new Item("Muenzen.png", 4150, 355));
        items.add(new Item("Muenzen.png", 4750, 290));

        // Bloecke
        blocks.add(new Block("Block2.png", 1100, 280));
        blocks.add(new Block("Block2.png", 800, 240));
        blocks.add(new Block("Block2.png", 1450, 95));
        blocks.add(new Block("Block2.png", 1450, 195));
        blocks.add(new Block("Block2.png", 1450, 145));
        blocks.add(new Block("Block2.png", 1450, 245));
        blocks.add(new Block("Block2.png", 1450, 295));
        blocks.add(new Block("Block2.png", 2450, 185));
        blocks.add(new Block("Block2.png", 2450, 135));
        blocks.add(new Block("Block2.png", 2450, 95));
        blocks.add(new Block("Block2.png", 2750, 350));
        blocks.add(new Block("Block2.png", 3550, 250));
        blocks.add(new Block("Block2.png", 3750, 390));
        blocks.add(new Block("Block2.png", 4150, 290));
        blocks.add(new Block("Block2.png", 4150, 290));
        blocks.add(new Block("Block2.png", 4500, 95));
        blocks.add(new Block("Block2.png", 4500, 135));
        blocks.add(new Block("Block2.png", 4500, 185));
        blocks.add(new Block("Block2.png", 4500, 235));
        blocks.add(new Block("Block2.png", 4500, 285));
    }
}
