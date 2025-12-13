package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private Texture enemyRight;
    private Texture enemyLeft;
    private final Texture texture;
    private float x, y;
    private float width, height;

    private float speed = 100f;   // Movement speed
    private float minX, maxX;     // Patrol boundaries
    private int direction = 1;    // 1 = right, -1 = left

    // Constructor with custom patrol range
    public Enemy(String texturePath, float startX, float startY, float width, float height, float patrolLeft, float patrolRight) {
        this.texture = new Texture(texturePath);
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        enemyRight = new Texture("enemy1.png");
        enemyLeft  = new Texture("enemy_1_Left.png");

        // Custom patrol range
        this.minX = startX - patrolLeft;
        this.maxX = startX + patrolRight;
    }

    // Overloaded constructor for default patrol range (backwards compatibility)
    public Enemy(String texturePath, float startX, float startY, float width, float height) {
        this(texturePath, startX, startY, width, height, 100f, 200f);
    }

    public void update(float dt) {
        // Move enemy
        x += direction * speed * dt;

        // Turn around at patrol edges
        if (x <= minX) {
            x = minX;
            direction = 1;
        }
        if (x + width >= maxX) {
            x = maxX - width;
            direction = -1;
        }
    }

    public void render(SpriteBatch batch) {
        Texture current = (direction < 0) ? enemyLeft : enemyRight;
        batch.draw(current, x, y, width, height);
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }

    // Optional: Methods to modify patrol range after creation
    public void setPatrolRange(float patrolLeft, float patrolRight) {
        this.minX = x - patrolLeft;
        this.maxX = x + patrolRight;
    }

    public void setPatrolBoundaries(float minX, float maxX) {
        this.minX = minX;
        this.maxX = maxX;
    }

    public void dispose() {
        texture.dispose();
        enemyRight.dispose();
        enemyLeft.dispose();
    }
}
