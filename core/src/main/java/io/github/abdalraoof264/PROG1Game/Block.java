package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    public Block(String block, float x, float y) {
        this.texture = new Texture(block);
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 80;
    }

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
