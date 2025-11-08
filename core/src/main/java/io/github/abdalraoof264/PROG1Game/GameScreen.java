package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Rectangle;


import java.awt.*;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Player player;
    private Enemy enemy1;
    private Tastatur tastatur;
    private Main main;
    private Texture gameBackground;

    // Für Kollisionen
    private Rectangle playerRectangle = new Rectangle(70, 183, 65, 65);
    private Rectangle enemyRectangle  = new Rectangle(400, 168, 95, 95);


    // Wichtig für das Skalieren des Hintergrunds
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        tastatur = new Tastatur();

        // Gdx.input.setInputProcessor(tastatur);   //Tastatur klasse einbringen

        gameBackground = new Texture("gameBackground.png");
        player=new Player("mario.png",70,183,tastatur);
        enemy1= new Enemy("enemy.png", 400, 168, 95, 95);

        // Für das die Skalierung
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(player.getX(), camera.viewportHeight / 2, 0);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {

        player.Update(delta);

        playerRectangle.x = player.getX();
        playerRectangle.y = player.getY();

        enemyRectangle.x = enemy1.getX();
        enemyRectangle.y = enemy1.getY();

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
        enemy1.render(batch);

        if(playerRectangle.overlaps(enemyRectangle)){
            enemy1.setY(-1000);
        }

        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            main.setScreen(new MenuScreen(main));
        }
    }
    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
