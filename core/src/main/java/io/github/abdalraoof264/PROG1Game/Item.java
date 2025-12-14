package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Klasse Item
public class Item {

    // Instanzvariablen
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    // Konstruktor
    public Item(String item, float x, float y) {
        this.texture = new Texture(item);
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    // Getter und Setter
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
