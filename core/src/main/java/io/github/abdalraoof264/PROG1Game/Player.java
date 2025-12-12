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
    private Texture playerPng;
    private Texture playerLookingUpPng;
    private Texture playerLookingDownPng;
    private Texture playerLeftPng;
    private Texture playerLookingDownLeftPng;
    private Texture playerLookingUp_LeftPng;

    public Player(String player1, float startX, float startY, Tastatur tastatur) {
        playerPng = new Texture(player1);
        playerLookingUpPng = new Texture("playerLookingUp.png");
        playerLookingDownPng = new Texture("playerLookingDown.png");
        playerLeftPng= new Texture("playerLeft.png");
        playerLookingDownLeftPng= new Texture("playerLookingDownLeft.png");
        playerLookingUp_LeftPng= new Texture("playerLookingUp_Left.png");
        playerPngAktuell = playerPng;

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

        boolean left  = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up    = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down  = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        // Bewegung
        if (left)  x -= speed * delta;
        if (right) x += speed * delta;

        if (up && left) {
            playerPngAktuell = playerLookingUp_LeftPng;
            height = 100;
        } else if (down && left) {
            playerPngAktuell = playerLookingDownLeftPng;
            height = 50;
        } else if (up && right) {
        } else if (down && right) {
        } else if (up) {
            playerPngAktuell = playerLookingUpPng;
            height = 100;
        } else if (down) {
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        } else if (left) {
            playerPngAktuell = playerLeftPng;
            height = 100;
        } else if (right) {
            playerPngAktuell = playerPng;
            height = 100;
        } else {
            playerPngAktuell = playerPng;
            height = 100;
        }

        // Sprung
        if (!isJumping && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            isJumping = true;
            velocityY += jumpVelocity;
        }

        if (isJumping) {
            velocityY += gravity;
            y += velocityY;
        }

        if (x < 0) x = 0;
        if (y < 100) { y = 105; velocityY = 0; isJumping = false; }
    }


    public void render(SpriteBatch batch) {
        batch.draw(playerPngAktuell, x, y, width, height);
    }

    public void dispose() {
        playerPng.dispose();
    }
}
