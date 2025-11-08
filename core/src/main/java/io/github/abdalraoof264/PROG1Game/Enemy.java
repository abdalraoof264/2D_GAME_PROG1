package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {

    private final Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    public Enemy(String texturePath, float startX, float startY,  float width, float height) {
        this.texture = new Texture(texturePath);
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y, width, height);

    }

    public void dispose() {
        texture.dispose();
    }

}

