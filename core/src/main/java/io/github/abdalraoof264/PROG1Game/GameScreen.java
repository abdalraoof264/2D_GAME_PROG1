package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Player player;
    private Tastatur tastatur;
    private Main main;
    public GameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        tastatur = new Tastatur();
        Gdx.input.setInputProcessor(tastatur);

        player=new Player("mario.jpg",10,10,tastatur);
    }

    @Override
    public void render(float delta) {
        player.Update(delta);
        ScreenUtils.clear(Color.GREEN);
        batch.begin();
        player.render(batch);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            main.setScreen(new MenuScreen(main));
        }
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
