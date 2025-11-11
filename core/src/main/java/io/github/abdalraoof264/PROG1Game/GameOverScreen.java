package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Main main;
    private Texture background;
    private BitmapFont titleFont;
    private BitmapFont infoFont;

    public GameOverScreen(Main main) {
        this.main = main;
    }

    public void show(){
        batch =  new SpriteBatch();
        background = new Texture("GameOver.png");
        titleFont = new BitmapFont();
        titleFont.setColor(Color.RED);
        titleFont.getData().setScale(3f);
        infoFont = new BitmapFont();
        infoFont.setColor(Color.WHITE);
        infoFont.getData().setScale(2f);
    }

    public void render(float delta){
        batch.begin();
        batch.draw(background, 0, 0, 640, 480);
        titleFont.draw(batch, "GAME OVER", 190, 380);
        infoFont.draw(batch, "Press Enter to restart", 180, 180);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            main.setScreen(new GameScreen(main));
        }
        batch.end();
    }
}
