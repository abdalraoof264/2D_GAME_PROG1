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

// Klasse Gamescreen erbt von AbstractScreen
public class GameScreen extends AbstractScreen {

    // notwendige Variablen
    private Player player;
    private Tastatur tastatur;
    private Main main;
    private Texture gameBackground;

    // Schriften, Anzeige
    private int score = 0;
    private int health = 3;
    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private BitmapFont HealthbarFont;
    private BitmapFont startQuest;
    private final int maxHealth = 3;
    private float iFrames = 0f;

    private Texture heartFull;
    private Texture heartEmpty;

    // Fuer das aktuelle Level
    private Level currentLevel;
    private int currentLevelNumber = 1;
    private int coinsCollectedInLevel = 0;

    // Fuer Kollisionen
    private Rectangle playerRectangle = new Rectangle(0, 0, 0, 0);

    // Für verschiedene Gegner, Items, Bloecke in Liste
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private ArrayList<Block> blocks;

    // Wichtig für das Skalieren des Hintergrunds
    private OrthographicCamera camera;
    private Viewport viewport;

    // Für die Score leiste damit die es im Bild bleibt
    private OrthographicCamera cameraScore;

    private com.badlogic.gdx.graphics.glutils.ShapeRenderer shapes;

    // Konstruktor
    public GameScreen(Main main) {
        this.main = main;
    }

    // Alles was im Bild nachher gezeigt wird
    @Override
    public void show() {

        batch = new SpriteBatch();
        tastatur = new Tastatur();

        // Tastatur einbringen
        Gdx.input.setInputProcessor(tastatur);

        // Score anzeigen
        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.WHITE);
        scoreFont.getData().setScale(2f);

        // Für die Healthbar
        HealthbarFont = new BitmapFont();
        HealthbarFont.getData().setScale(2f);
        HealthbarFont.setColor(Color.RED);
        shapes = new com.badlogic.gdx.graphics.glutils.ShapeRenderer();

        // Laedt die Texturen
        heartFull = new Texture("heart_full.png");
        heartEmpty = new Texture("heart_empty.png");
        player = new Player("player.png", 70, 105, tastatur);

        // Level laden
        loadLevel(1);  // Starte mit Level 1

        // Für das die Skalierung
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(player.getX(), camera.viewportHeight / 2, 0);

        // Für die Score leiste
        cameraScore = new OrthographicCamera();
        cameraScore.setToOrtho(false, 800, 600);
        MusicManager.getInstance().playGameMusic();

        // Die Aufgabe auf dem Screen
        startQuest = new BitmapFont();
        startQuest.getData().setScale(2f);
    }

    // Level laden
    private void loadLevel(int levelNumber) {

        // Entscheiden, welches Spiel
         if (levelNumber == 1) {
            currentLevel = new Level1();
            gameBackground = new Texture("gameBackground.png");
        } else if (levelNumber == 2) {
            currentLevel = new Level2();
            gameBackground = new Texture("gameBackground2.png");
        } else {
            main.setScreen(new VictoryScreen(main));
            return;
        }

        // Level initialisieren
        currentLevel.loadLevel();

        // Hole die Listen aus dem Level
        enemies = currentLevel.getEnemies();
        items = currentLevel.getItems();
        blocks = currentLevel.getBlocks();

        // Reset für das neue Level
        coinsCollectedInLevel = 0;
        currentLevelNumber = levelNumber;

        // Spieler an Startposition setzen
        player.setX(70);
        player.setY(105);

        player.setVelocityY(0);
        player.setIsGrounded(true);

        System.out.println("Level " + levelNumber + " geladen! Benoetigte Muenzen: " + currentLevel.getRequiredCoins());
    }

    // Rendert alles im Bild
    @Override
    public void render(float delta) {

        // Haben wir zum Debuggen benutzt
    //    if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
     //       loadLevel(currentLevelNumber + 1);
   //     }

        // Spieler wird bewegt
        player.Update(delta);

        if (iFrames > 0f) {
            iFrames -= delta;
        }

        // Gibt dem Spieler die geeigete Hitbox, welche sich aktualisiert
        playerRectangle.x = player.getX();
        playerRectangle.y = player.getY();
        playerRectangle.width = 65;
        playerRectangle.height = 65;

        // Kamera verfolgt spieler
        camera.position.x = player.getX();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);

        // Zeichnen beginnt
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
            batch.draw(gameBackground, (i * newWidth), 0, newWidth, newHeight);
        }

        startQuest.draw(batch, "Sammle alle Münzen um zu Gewinnen", -100, 450);

        player.render(batch);

        // Gegner Hitbox und Kollision
        Rectangle enemyRectangle = new Rectangle(0, 0, 95, 108);

        // Eine For Schleife, welche durch jeden Gegner aus der Liste abfragt, ob er beruehrt wird und wie
        // Hier wird die Kollision mit den Gegnern verarbeitet
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
            enemy.render(batch);

            enemyRectangle.x = enemy.getX();
            enemyRectangle.y = enemy.getY();

            // Wenn der Spieler auf den Gegner springt
            if (playerRectangle.overlaps(enemyRectangle) && player.getY() > (enemy.getY() + enemyRectangle.height * 0.9)) {
                enemies.remove(enemy);
                player.setVelocityY(-8f);
                continue;
            }

            // Wenn der Spieler schaden bekommt
            if (playerRectangle.overlaps(enemyRectangle)
                && player.getY() < (enemy.getY() + enemyRectangle.height * 0.9)) {

                if (iFrames <= 0f) {
                    health -= 1;
                    iFrames = 2.0f;

                    player.setVelocityY(-8f);

                }

                if (health <= 0) {
                    main.setScreen(new GameOverScreen(main));
                    return;
                }
            }

        }

        // Dasselbe uer Items
        Rectangle itemRectangle = new Rectangle(0, 0, 70, 70);

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.render(batch);
            itemRectangle.x = item.getX();
            itemRectangle.y = item.getY();

            // Wenn mann eine Muenze sammelt
            if (playerRectangle.overlaps(itemRectangle)) {
                items.remove(item);
                score++;
                coinsCollectedInLevel++;

                // Ist das Ziel erreicht?
                if (coinsCollectedInLevel >= currentLevel.getRequiredCoins()) {
                    System.out.println("Level " + currentLevelNumber + " abgeschlossen!");
                    loadLevel(currentLevelNumber + 1);
                }
            }
        }

        // Und auch bei Bleocken das geliche verfahren
        Rectangle blockRectangle = new Rectangle(0, 0, 40, 60);

        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            block.render(batch);
            blockRectangle.x = block.getX();
            blockRectangle.y = block.getY();

            if (playerRectangle.overlaps(blockRectangle)) {

                // Berechne Überlappung von allen Seiten
                float overlapLeft = (playerRectangle.x + playerRectangle.width) - blockRectangle.x;
                float overlapRight = (blockRectangle.x + blockRectangle.width) - playerRectangle.x;
                float overlapTop = (playerRectangle.y + playerRectangle.height) - blockRectangle.y;
                float overlapBottom = (blockRectangle.y + blockRectangle.height) - playerRectangle.y;

                // Finde die kleinste Überlappung
                float minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                    Math.min(overlapTop, overlapBottom));

                // Reagiere basierend auf der kleinsten Überlappung
                if (minOverlap == overlapBottom && player.getVelocityY() >= 0) {

                    // Wenn man von oben drauf springt
                    player.setY(blockRectangle.y + blockRectangle.height);
                    player.setVelocityY(0f);
                    player.setIsGrounded(true);

                } else if (minOverlap == overlapTop && player.getVelocityY() < 0) {

                    // Wernn man unter den Block springt
                    player.setY(blockRectangle.y - playerRectangle.height);
                    player.setVelocityY(0.5f);

                } else if (minOverlap == overlapLeft) {
                    // Wenn man von Links springt
                    player.setX(blockRectangle.x - playerRectangle.width);

                } else if (minOverlap == overlapRight) {
                    // Wenn man von Rechts springt
                    player.setX(blockRectangle.x + blockRectangle.width);
                }

                // Update Rectangle nach Kollision
                playerRectangle.x = player.getX();
                playerRectangle.y = player.getY();
            }
        }

        batch.end();

        //Für Score, Level und Hearts
        batch.setProjectionMatrix(cameraScore.combined);
        batch.begin();

        scoreFont.draw(batch, "SCORE: " + score, 17, 580);
        scoreFont.draw(batch, "LEVEL: " + currentLevelNumber, 18, 550);

        // Zeichnet Herzen
        float heartX = 20;
        float heartY = 490;
        float heartSize = 32;
        float heartSpacing = 40;

        for (int i = 0; i < maxHealth; i++) {
            if (i < health) {
                batch.draw(heartFull, heartX + (i * heartSpacing), heartY, heartSize, heartSize);
            } else {
                batch.draw(heartEmpty, heartX + (i * heartSpacing), heartY, heartSize, heartSize);
            }
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            dispose();
            main.setScreen(new MenuScreen(main));
        }
    }

    // Skalierung
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    // Loescht alles am Ende
    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        heartFull.dispose();
        heartEmpty.dispose();

        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }

}
