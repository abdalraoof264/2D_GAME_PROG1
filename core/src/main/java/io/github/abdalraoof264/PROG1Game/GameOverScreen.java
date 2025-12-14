package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// Klasse, welche erbt von AbstractScreen
public class GameOverScreen extends AbstractScreen {
    // Instanzvariablen
    private Main main;
    private Texture background;
    // Zum Text anzeigen

    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont infoFont;

    // Fuer die Kamera
    private OrthographicCamera camera;
    private Viewport viewport;

    // Konstruktor
    public GameOverScreen(Main main) {
        this.main = main;
    }

    // Zeigt alles uaf dem Screen
    public void show(){

        batch =  new SpriteBatch();

        // Hintergrund
        background = new Texture("GameOver.png");

        // Schrift zeichnen
        titleFont = new BitmapFont();
        titleFont.setColor(Color.RED);
        titleFont.getData().setScale(3f);

        // Schrift zeichnen
        infoFont = new BitmapFont();
        infoFont.setColor(Color.WHITE);
        infoFont.getData().setScale(2f);

        // Kamera positionieren
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        // Musik einfuegen
        MusicManager.getInstance().playGameOverMusic();
    }

    // Rendert das Bild 60/Sekunde
    public void render(float delta){
        batch.begin();

        batch.draw(background, 0, 0, 800, 600);
        titleFont.draw(batch, "GAME OVER", 280, 380);
        infoFont.draw(batch, "Drücke ENTER um für einen weiteren Versuch", 100, 180);

        // Wenn ENTER gedrueckt, kommt man in den GameScreen
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            main.setScreen(new GameScreen(main));
        }
        batch.end();
    }

    // Fuer die Skalierung
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    // Loescht alles
    public void dispose(){
        batch.dispose();
        background.dispose();
        titleFont.dispose();
        infoFont.dispose();
    }


}
