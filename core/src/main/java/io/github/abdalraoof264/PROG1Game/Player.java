package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player  {
    private final Texture texture;
    private float x, y;
    private float speed = 4;

    public Player(String texturePath, float startX, float startY) {
        texture = new Texture(texturePath);
        x = startX;
        y = startY;
    }
    public void Update (float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * delta;        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        x=x -4;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        x=x +4;
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
//        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}


