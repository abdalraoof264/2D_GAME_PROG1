package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Main main;
    private Texture background;
    private BitmapFont titleFont;
    private BitmapFont infoFont;
    private boolean showResume;

    // Wichtig für das Skalieren des Hintergrunds
    private OrthographicCamera camera;
    private Viewport viewport;

    public MenuScreen(Main main) {
        this.main = main;
        this.showResume = true;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("background.png");

        titleFont = new BitmapFont();
        titleFont.setColor(Color.WHITE);
        titleFont.getData().setScale(3f);

        infoFont = new BitmapFont();
        infoFont.setColor(Color.YELLOW);
        infoFont.getData().setScale(1.5f);

        // Für das die Skalierung
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    @Override
    public void render(float v) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(Color.BLACK);
        batch.begin();

        // Brauchen wir alles für die Skalierung
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float imgWidth = background.getWidth();
        float imgHeight = background.getHeight();

        float scale = Math.max(worldWidth / imgWidth, worldHeight / imgHeight);
        float newWidth = imgWidth * scale;
        float newHeight = imgHeight * scale;

        float x = camera.position.x - (newWidth / 2);
        float y = camera.position.y - (newHeight / 2);

        batch.draw(background, x, y, newWidth, newHeight);


        titleFont.draw(batch, "MENU",
            viewport.getWorldWidth() / 2f -75,
            viewport.getWorldHeight() / 2f + 150);

        if (showResume) {
            infoFont.draw(batch, "Press R to Resume",
                viewport.getWorldHeight() / 2f - 0,
                viewport.getWorldWidth() / 2f -50);
        }

        infoFont.draw(batch, "Press ENTER to Start",
            viewport.getWorldHeight() / 2f - 0,
            viewport.getWorldWidth() / 2f -100);

        infoFont.draw(batch, "Press ESC to Exit",
            viewport.getWorldHeight() / 2f - 0,
            viewport.getWorldWidth() / 2f -150);

        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            main.setScreen(new GameScreen(main));
        }

        if (showResume && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            main.setScreen(new GameScreen(main));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit(); // Spiel beenden
        }

    }

    @Override
    public void resume(){

    }

    @Override
    public void pause(){
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        titleFont.dispose();
        infoFont.dispose();
    }
}
