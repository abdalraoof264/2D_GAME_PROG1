package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Klasse Enemy
public class Enemy {
    // Instanzvariablen
    private Texture enemyRight;
    private Texture enemyLeft;
    private float x, y;
    private float width, height;

    // Bewegung
    private float speed = 100f;
    private float minX, maxX;
    private int direction = 1;

    // Unser Konstruktor
    public Enemy(String enemyRightPng, String enemyLeftPng, float startX, float startY, float width, float height, float patrolLeft, float patrolRight) {
        this.enemyRight = new Texture(enemyRightPng);
        this.enemyLeft = new Texture(enemyLeftPng);
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.minX = startX - patrolLeft;
        this.maxX = startX + patrolRight;
    }

    // Methode zum bewegen
    public void update(float dt) {

        x += direction * speed * dt;

        // Dreht den Spieler in Richtungen
        if (x <= minX) {
            x = minX;
            direction = 1;
        }
        if (x + width >= maxX) {
            x = maxX - width;
            direction = -1;
        }
    }

    // Rendert den Spieler
    public void render(SpriteBatch batch) {
        Texture current = (direction < 0) ? enemyLeft : enemyRight;
        batch.draw(current, x, y, width, height);
    }

    // Getter
    public float getX() { return x; }
    public float getY() { return y; }

    // Loescht alles am Ende
    public void dispose() {
        enemyRight.dispose();
        enemyLeft.dispose();
    }
}
