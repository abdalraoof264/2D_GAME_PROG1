package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Player player;
    private Tastatur tastatur;
    private Main main;
    private Texture gameBackground;
    private BitmapFont scoreFont;
    private int score = 0;
    private int health = 3;
    private BitmapFont HealthbarFont;
    private final int maxHealth = 3;
    private float iFrames = 0f;
    private boolean isTouching = false;

    // Heart textures
    private Texture heartFull;
    private Texture heartEmpty;

    // Für Kollisionen
    private Rectangle playerRectangle = new Rectangle(70, 183, 65, 65);

    // Für verschiedene Gegner
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();

    // Wichtig für das Skalieren des Hintergrunds
    private OrthographicCamera camera;
    private Viewport viewport;

    // Für die Score leiste damit die es im Bild bleibt
    private OrthographicCamera cameraScore;

    //***************************************//
    private com.badlogic.gdx.graphics.glutils.ShapeRenderer shapes;

    public GameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        tastatur = new Tastatur();

        // Gdx.input.setInputProcessor(tastatur);   //Tastatur klasse einbringen

        //Score
        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.WHITE);
        scoreFont.getData().setScale(2f);

        // Für die Healthbar
        HealthbarFont = new BitmapFont();
        HealthbarFont.getData().setScale(2f);
        HealthbarFont.setColor(Color.RED);
        shapes = new com.badlogic.gdx.graphics.glutils.ShapeRenderer();

        // Load heart textures
        heartFull = new Texture("heart_full.png");
        heartEmpty = new Texture("heart_empty.png");

        gameBackground = new Texture("gameBackground.png");
        player=new Player("player.png",70,105, tastatur);

        // Hier kann man Gegner hinzufügen
        // Default Patrol-Range (100px links, 200px rechts)
        enemies.add(new Enemy("enemy1.png", 400, 100, 95, 95, 150f, 50f));
        enemies.add(new Enemy("enemy1.png", 600, 100, 95, 95 ));

        // Hier kann man Items hinzufügen
        items.add(new Item("Muenzen.png", 500, 300));
        items.add(new Item("Muenzen.png", 1000, 200));

        //Hier kann man Blöcke hinzufügen
        blocks.add(new Block("Block.png", 500, 200));

        // Für das die Skalierung
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(player.getX(), camera.viewportHeight / 2, 0);

        // Für die Score leiste
        cameraScore = new OrthographicCamera();
        cameraScore.setToOrtho(false, 800, 600);
    }

    @Override
    public void render(float delta) {

        player.Update(delta);

        if (iFrames > 0f) {
            iFrames -= delta;
        }

        playerRectangle.x = player.getX();
        playerRectangle.y = player.getY();

        camera.position.x = player.getX();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        // Brauchen wir alles für die Skalierung
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float imgWidth = gameBackground.getWidth();
        float imgHeight = gameBackground.getHeight();

        float scale = Math.max(worldWidth / imgWidth, worldHeight / imgHeight);
        float newWidth = imgWidth * scale;
        float newHeight = imgHeight * scale;

        float x = (worldWidth - newWidth) / 2f;
        float y = (worldHeight - newHeight) / 2f;

        //Für Hintergrund wiederholungen
        for (int i = -1; i <= 5; i++) {
            batch.draw(gameBackground, (i * newWidth),0,newWidth,newHeight);
        }

        player.render(batch);

        Rectangle enemyRectangle = new Rectangle(0, 0, 95, 108);

        for (int i = 0; i < enemies.size(); i++ ) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
            enemy.render(batch);

            enemyRectangle.x = enemy.getX();
            enemyRectangle.y = enemy.getY();


            if(playerRectangle.overlaps(enemyRectangle) && player.getY() > (enemy.getY() + enemyRectangle.height * 0.9)){
                enemies.remove(enemy);
                player.setVelocityY(10f);
                player.setIsJumping(true);
                continue;
            }

            if (playerRectangle.overlaps(enemyRectangle)
                && player.getY() < (enemy.getY() + enemyRectangle.height * 0.9)) {

                if (iFrames <= 0f) {
                    health -= 1;
                    iFrames = 2.0f;

                    player.setVelocityY(8f);
                    player.setIsJumping(true);

                }

                if (health <= 0) {
                    main.setScreen(new GameOverScreen(main));
                    return;
                }
            }

        }

        Rectangle itemRectangle = new Rectangle(0, 0, 70, 70);

        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            item.render(batch);
            itemRectangle.x = item.getX();
            itemRectangle.y = item.getY();

            if(playerRectangle.overlaps(itemRectangle)){
                items.remove(item);
                score++;
            }
        }

        Rectangle blockRectangle = new Rectangle(0, 0, 60, 60);

        for(int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            block.render(batch);
            blockRectangle.x = block.getX();
            blockRectangle.y = block.getY();

            if(playerRectangle.overlaps(blockRectangle)){

                if(player.getY() >= (block.getY() + blockRectangle.height) -20 && player.getVelocityY() <= 0) {
                    player.setY(block.getY() + blockRectangle.height);
                    player.setVelocityY(0f);
                    player.setIsJumping(false);
                }
                else{
                    player.setVelocityY(-3f);
                    player.setIsJumping(true);
                }
            }
        }

        batch.end();

        //Für Score und Hearts
        batch.setProjectionMatrix(cameraScore.combined);
        batch.begin();
        scoreFont.draw(batch, "SCORE: " + score, 18, 580);

        // Draw hearts
        float heartX = 20;
        float heartY = 515;
        float heartSize = 32; // Size of each heart
        float heartSpacing = 40; // Space between hearts

        for (int i = 0; i < maxHealth; i++) {
            if (i < health) {
                // Draw full heart
                batch.draw(heartFull, heartX + (i * heartSpacing), heartY, heartSize, heartSize);
            } else {
                // Draw empty heart
                batch.draw(heartEmpty, heartX + (i * heartSpacing), heartY, heartSize, heartSize);
            }
        }

        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            main.setScreen(new MenuScreen(main));
        }
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        heartFull.dispose();
        heartEmpty.dispose();
    }

}
