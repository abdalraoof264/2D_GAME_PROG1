package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Player-Klasse mit MARIO-STYLE Physik
 * FIXED: Keine Teleport-Loops mehr!
 */
public class Player {
    // Texturen
    private Texture playerPng;
    private Texture playerPngAktuell;
    private Texture playerLeftPng;
    private Texture playerLookingUpPng;
    private Texture playerLookingDownPng;
    private Texture playerLookingUp_LeftPng;
    private Texture playerLookingDownLeftPng;

    // Position und Größe
    private float x, y;
    private float width, height;
    private float speed = 200f;

    // Sprung Physik - MARIO-STYLE (ANGEPASST FÜR HÖHEREN SPRUNG)
    private boolean isJumping = false;
    private boolean isGrounded = false;  // NEU: Ist der Spieler am Boden?
    private float gravity = 0.5f;
    private float jumpVelocity = -15.0f;
    private float velocityY = 0f;
    private float maxFallSpeed = 15f;

    // Tastatur-Referenz
    private Tastatur tastatur;

    /**
     * Konstruktor
     */
    public Player(String texturePath, float startX, float startY, Tastatur tastatur) {
        this.playerPng = new Texture(texturePath);
        this.playerPngAktuell = playerPng;
        this.x = startX;
        this.y = startY;
        this.width = 100;
        this.height = 100;
        this.tastatur = tastatur;

        // Lade alle Sprite-Varianten
        playerLeftPng = new Texture("playerLeft.png");
        playerLookingUpPng = new Texture("playerLookingUp.png");
        playerLookingDownPng = new Texture("playerLookingDown.png");
        playerLookingUp_LeftPng = new Texture("playerLookingUp_Left.png");
        playerLookingDownLeftPng = new Texture("playerLookingDownLeft.png");
    }

    /**
     * Update-Methode - wird jeden Frame aufgerufen
     * WICHTIG: Nur Physik, keine Kollision!
     */
    public void Update(float delta) {

        // ========== BEWEGUNG ==========
        if (tastatur.left)  x -= speed * delta;
        if (tastatur.right) x += speed * delta;

        // ========== SPRITE RICHTUNG ==========
        if (tastatur.up && tastatur.left) {
            playerPngAktuell = playerLookingUp_LeftPng;
            height = 100;
        } else if (tastatur.down && tastatur.left) {
            playerPngAktuell = playerLookingDownLeftPng;
            height = 50;
        } else if (tastatur.up && tastatur.right) {
            playerPngAktuell = playerLookingUpPng;
            height = 100;
        } else if (tastatur.down && tastatur.right) {
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        } else if (tastatur.up) {
            playerPngAktuell = playerLookingUpPng;
            height = 100;
        } else if (tastatur.down) {
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        } else if (tastatur.left) {
            playerPngAktuell = playerLeftPng;
            height = 100;
        } else if (tastatur.right) {
            playerPngAktuell = playerPng;
            height = 100;
        } else {
            playerPngAktuell = playerPng;
            height = 100;
        }

        // ========== SPRUNG PHYSIK ==========

        // Springen (nur wenn am Boden)
        if (isGrounded && tastatur.spaceJustPressed) {
            velocityY = jumpVelocity;
            isJumping = true;
            isGrounded = false;
        }

        // Schwerkraft IMMER anwenden
        velocityY += gravity;

        // Maximale Fallgeschwindigkeit
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }

        // Position aktualisieren
        y -= velocityY;

        // ========== BODEN-KOLLISION (FALLBACK) ==========
        if (y <= 105) {
            y = 105;
            velocityY = 0;
            isJumping = false;
            isGrounded = true;
        }

        // Linke Grenze
        if (x < 0) x = 0;

        // ========== TASTATUR UPDATE ==========
        tastatur.update();
    }

    /**
     * Render-Methode - zeichnet den Spieler
     */
    public void render(SpriteBatch batch) {
        batch.draw(playerPngAktuell, x, y, width, height);
    }

    // ========== GETTER & SETTER ==========

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setIsJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public void setIsGrounded(boolean grounded) {  // NEU!
        this.isGrounded = grounded;
    }

    public boolean isGrounded() {  // NEU!
        return isGrounded;
    }

    /**
     * Dispose - gibt Ressourcen frei
     */
    public void dispose() {
        playerPng.dispose();
        playerLeftPng.dispose();
        playerLookingUpPng.dispose();
        playerLookingDownPng.dispose();
        playerLookingUp_LeftPng.dispose();
        playerLookingDownLeftPng.dispose();
    }
}
