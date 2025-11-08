package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private final Texture texture;
    private float x, y;
    private final float speed = 275f;
    private final float width;
    private final float height;

    //Für die Sprung physik
    private boolean isJumping = false;
    private float gravity = -0.5f;
    private float jumpVelocity = 14f;
    private float velocityY = 0f;

    public Player(String texturePath, float startX, float startY, Tastatur tastatur) {
        texture = new Texture(texturePath);
        x = startX;
        y = startY;
        width = 65;
        height = 65;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void Update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * delta;

        if(!isJumping && (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP ))){
            isJumping = true;
            velocityY = velocityY + jumpVelocity;
        }

        if(isJumping){
            velocityY = velocityY + gravity;
            y = y + velocityY;
        }

        if (x < 0){
            x = 0;
        }

        if (y < 183){
            y = 183;
            velocityY = 0;
            isJumping = false;
        }

        if (y > texture.getHeight()){
            y = texture.getHeight();
        }


    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y, width, height);

    }

    public void dispose() {
        texture.dispose();
    }
}


