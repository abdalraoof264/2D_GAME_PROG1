package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    private Main main;

    private float x, y;
    private final float speed = 300f;
    private float width;
    private float height;

    //Für die Sprung physik
    private boolean isJumping = false;
    private float gravity = -0.5f;
    private float jumpVelocity = 14f;
    private float velocityY = 0f;

    // Für die Sprites
    private Texture playerPngAktuell;
    private  Texture playerPng;
    private  Texture playerLookingUpPng;
    private  Texture playerLookingDownPng;

    public Player(String player1, float startX, float startY, Tastatur tastatur) {
        playerPng = new Texture(player1);
        playerLookingUpPng = new Texture("playerLookingUp.png");
        playerPngAktuell = playerPng;
        playerLookingDownPng = new Texture("playerLookingDown.png");
        x = startX;
        y = startY;
        width = 100;
        height = 100;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setVelocityY(float y) {
        this.velocityY = y;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public void Update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * delta;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerPngAktuell = playerLookingUpPng;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        }

        else{
            playerPngAktuell = playerPng;
            height = 100;
        }

        if(!isJumping && (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))){
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

      //  if (y > texture.getHeight()){
      //      y = texture.getHeight();
      //  }


    }

    public void render(SpriteBatch batch) {
        batch.draw(playerPngAktuell, x, y, width, height);
    }

    public void dispose() {
        playerPng.dispose();
    }
}


