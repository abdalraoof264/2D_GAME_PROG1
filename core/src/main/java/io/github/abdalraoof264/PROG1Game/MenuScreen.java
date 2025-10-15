package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Main main;
    private Texture background;
    private BitmapFont titleFont;
    private BitmapFont infoFont;
    private boolean showResume;
    public  MenuScreen(Main main) {
        this.main = main;
        this.showResume = true;
    }
    public MenuScreen(Main main, boolean showResume) {
        this.main = main;
        this.showResume = showResume;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("menu_background.png"); // dein Hintergrundbild im assets-Ordner

        titleFont = new BitmapFont(); // Standardfont
        titleFont.setColor(Color.WHITE);
        titleFont.getData().setScale(3f); // größerer Text für Titel

        infoFont = new BitmapFont();
        infoFont.setColor(Color.YELLOW);
        infoFont.getData().setScale(1.5f);
    }

    @Override
    public void resume(){

    }

    @Override
    public void pause(){
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.RED);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Titeltext
        titleFont.draw(batch, "MENU",
            Gdx.graphics.getWidth() / 2f - 250,
            Gdx.graphics.getHeight() / 2f + 100);

        if (showResume) {
            infoFont.draw(batch, "Press R to Resume",
                Gdx.graphics.getWidth() / 2f - 130,
                Gdx.graphics.getHeight() / 2f + 20);
        }

        // Anweisungen
        infoFont.draw(batch, "Press ENTER to Start",
            Gdx.graphics.getWidth() / 2f - 150,
            Gdx.graphics.getHeight() / 2f - 50);

        infoFont.draw(batch, "Press ESC to Exit",
            Gdx.graphics.getWidth() / 2f - 130,
            Gdx.graphics.getHeight() / 2f - 100);
        batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            main.setScreen(new GameScreen(main));
        }
        if (showResume && Gdx.input.isKeyPressed(Input.Keys.R)) {
            main.setScreen(new GameScreen(main));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit(); // Spiel beenden
        }

    }
    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        titleFont.dispose();
        infoFont.dispose();
    }
}
