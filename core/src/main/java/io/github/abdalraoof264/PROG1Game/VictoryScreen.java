package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Victory Screen - Wird angezeigt wenn alle Levels abgeschlossen wurden
 */
public class VictoryScreen extends AbstractScreen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Main main;
    private Texture background;

    public VictoryScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);
        font.setColor(Color.GOLD);
        MusicManager.getInstance().playVictoryMusic();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.2f, 0, 1); // Dunkler Hintergrund

        batch.begin();

        // Optional: Hintergrund zeichnen
        // if (background != null) {
        //     batch.draw(background, 0, 0, 800, 600);
        // }

        // Victory Text
        font.setColor(Color.GOLD);
        font.getData().setScale(4f);
        font.draw(batch, "VICTORY!", 250, 400);

        // Kleinere Schrift für Anweisungen
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
        font.draw(batch, "You completed all levels!", 180, 300);
        font.draw(batch, "Press ENTER to return to menu", 150, 200);

        batch.end();

        // Zurück zum Menü
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            main.setScreen(new MenuScreen(main));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (background != null) {
            background.dispose();
        }
    }
}
