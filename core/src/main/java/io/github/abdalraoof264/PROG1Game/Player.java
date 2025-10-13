package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private final Texture texture;
    private float x, y;
    private float speed = 200f;
    private float width, height;

    public Player(String texturePath, float startX, float startY, Tastatur tastatur) {
        texture = new Texture(texturePath);
        x = startX;
        y = startY;
        width = 50;
        height = 50;
    }

    public void Update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))    y += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }


    public void dispose() { texture.dispose(); }
}


