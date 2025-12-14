package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    // Alle notwendigen Instanzvariablen
    // Die Texturen, welche unser Player benötigt
    private Texture playerPng;
    private Texture playerPngAktuell;
    private Texture playerLeftPng;
    private Texture playerLookingUpPng;
    private Texture playerLookingDownPng;
    private Texture playerLookingUp_LeftPng;
    private Texture playerLookingDownLeftPng;

    // Fuer Position, Groeße und Geschwindigkeit
    private float x, y;
    private float width, height;
    private float speed = 300f;

    // Unsere Variablen fuer die Sprung-Physik
    private boolean isGrounded = false;
    private float gravity = 0.5f;
    private float jumpVelocity = -15.0f;
    private float velocityY = 0f;
    private float maxFallSpeed = 15f;

    // Tastatur, welche wir benutzen für die Eingaben
    private Tastatur tastatur;

    // Unser Konstruktor, mit dem wir den Spieler initialisieren
    public Player(String texturePath, float startX, float startY, Tastatur tastatur) {
        this.playerPng = new Texture(texturePath);
        this.playerPngAktuell = playerPng;
        this.x = startX;
        this.y = startY;
        this.width = 100;
        this.height = 100;
        this.tastatur = tastatur;

        // Wichtig für die Sprite-Rotationen
        playerLeftPng = new Texture("playerLeft.png");
        playerLookingUpPng = new Texture("playerLookingUp.png");
        playerLookingDownPng = new Texture("playerLookingDown.png");
        playerLookingUp_LeftPng = new Texture("playerLookingUp_Left.png");
        playerLookingDownLeftPng = new Texture("playerLookingDownLeft.png");
    }

    // Diese Methode ermöglicht es, das der Spieler sich im Spiel bewegt
    public void Update(float delta) {

        // Bringt den Spieler in Bewegung, durch Tastendruck veraendert sich seine Position
        if (tastatur.left) {
            x -= speed * delta;
        }

        if (tastatur.right){
            x += speed * delta;
        }


        // Hier entscheidet sich, in welche Richtung der Spieler schaut, je nach eingabe
        if (tastatur.up && tastatur.left) {
            playerPngAktuell = playerLookingUp_LeftPng;
            height = 100;
        }

        else if (tastatur.down && tastatur.left) {
            playerPngAktuell = playerLookingDownLeftPng;
            height = 50;
        }

        else if (tastatur.up && tastatur.right) {
            playerPngAktuell = playerLookingUpPng;
            height = 100;
        }

        else if (tastatur.down && tastatur.right) {
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        }

        else if (tastatur.up) {
            playerPngAktuell = playerLookingUpPng;
            height = 100;
        }

        else if (tastatur.down) {
            playerPngAktuell = playerLookingDownPng;
            height = 50;
        }

        else if (tastatur.left) {
            playerPngAktuell = playerLeftPng;
            height = 100;
        }

        else if (tastatur.right) {
            playerPngAktuell = playerPng;
            height = 100;
        }

        else {
            playerPngAktuell = playerPng;
            height = 100;
        }

        // Sobald der Spieler sich auf dem Boden befindet und dann gesprungen wird, springt er
        if (isGrounded && tastatur.spaceJustPressed) {
            velocityY = jumpVelocity;
            isGrounded = false;
        }

        // Der Spieler wird permanent von oben nach unten gezogen
        velocityY += gravity;

        // Maximale Fallgeschwindigkeit
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }

        // Position aktualisieren
        y -= velocityY;

        // Der Spieler soll nicht nach unten fallen
        if (y <= 105) {
            y = 105;
            velocityY = 0;
            isGrounded = true;
        }

        // Er darf nicht nach Links oder Rechts aus dem Rand
        if (x < 0) x = 0;

        if(x > 5000){
            x = 5000;
        }

        // Die Tastatur aktulaisiert sich
        tastatur.update();
    }

    // Hier wird unser Spieler gezeichnet
    public void render(SpriteBatch batch) {
        batch.draw(playerPngAktuell, x, y, width, height);
    }

    // Hier sind sämtliche getter und setter Methoden, welche wir benutzt hatten
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

    public void setIsGrounded(boolean grounded) {  // NEU!
        this.isGrounded = grounded;
    }

    public boolean isGrounded() {  // NEU!
        return isGrounded;
    }

    // Am Ende wird alles unnötige gelöscht, wenn es nicht mehr gebraucht wird
    public void dispose() {
        playerPng.dispose();
        playerLeftPng.dispose();
        playerLookingUpPng.dispose();
        playerLookingDownPng.dispose();
        playerLookingUp_LeftPng.dispose();
        playerLookingDownLeftPng.dispose();
    }
}
