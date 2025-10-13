package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Main main;
    public  MenuScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
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
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            main.setScreen(new GameScreen(main));
        }
        batch.end();
    }
}
