package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */

// Unsere Main Klasse
public class Main extends Game {

    // Setzt den Anfangsscreen
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

    // Loescht alles am ende
    @Override
    public void dispose() {
        MusicManager.getInstance().dispose();

        if (getScreen() != null) {
            getScreen().hide();
        }

        super.dispose();
    }
}
