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

    public Enemy(String texturePath, float startX, float startY, float width, float height) {
        this.texture = new Texture(texturePath);
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        enemyRight = new Texture("enemy1.png");
        enemyLeft  = new Texture("enemy_1_Left.png");

        // Patrol range: 200px left to 200px right of spawn
        this.minX = startX - 100f;
        this.maxX = startX + 200f;
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

    public float getX() { return x; }
    public float getY() { return y; }
    public void dispose() { texture.dispose(); }
}
