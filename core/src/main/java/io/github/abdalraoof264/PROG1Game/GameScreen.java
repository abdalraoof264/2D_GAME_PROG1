package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Player player;

    @Override
    public void show() {
        batch = new SpriteBatch();
        player=new Player("Red_Socccer_Ball.png",100,100);
    }

    @Override
    public void render(float delta) {
        player.Update(delta);
        ScreenUtils.clear(Color.GREEN);
        batch.begin();
        player.render(batch);
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
