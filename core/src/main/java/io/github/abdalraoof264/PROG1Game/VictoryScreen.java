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

public class VictoryScreen extends AbstractScreen {

    // Unsere Instanzvariablen
    private Main main;
    private Texture background;

    // Wichtig fuer Kamera und Schriften
    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private Viewport viewport;

    // Der Konstruktor
    public VictoryScreen(Main main) {
        this.main = main;
    }

    // Methode, welche den Screen zeigt
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);
        font.setColor(Color.GOLD);

        // Hier laden wir den background rein
        background = new Texture("victoryBackground.png");

        // Einstellungen fuer die Kamera
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        // Fuer die Musik nachher
        MusicManager.getInstance().playVictoryMusic();
    }

    // Die Methode, welche den Hintergrund rendert
    @Override
    public void render(float delta) {
        // Dunkler Hintergrund
        ScreenUtils.clear(0, 0.2f, 0, 1);

        // Er faengt an zu zeichnen
        batch.begin();

        batch.draw(background, 0, 0, 800, 600);

        // Victory Text
        font.setColor(Color.GOLD);
        font.getData().setScale(4f);
        font.draw(batch, "VICTORY!", 290, 400);

        // Text fuer Victory
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
        font.draw(batch, "Du hast alle Level bestanden", 250, 300);
        font.draw(batch, "Drücke ENTER um ins Menü zu kommen", 150, 200);

        batch.end();

        // Zurück zum Menue
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            main.setScreen(new MenuScreen(main));
        }
    }

    // vergroeßert/verkleinert den Hintergrund
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    // Loescht alles am Ende
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (background != null) {
            background.dispose();
        }
    }
}
