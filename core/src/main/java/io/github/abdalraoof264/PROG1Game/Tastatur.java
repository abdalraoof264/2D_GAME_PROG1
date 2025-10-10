package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Tastatur implements InputProcessor {
    public boolean up, down, left, right;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) up = true;
        if (keycode == Input.Keys.DOWN) down = true;
        if (keycode == Input.Keys.LEFT) left = true;
        if (keycode == Input.Keys.RIGHT) right = true;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) up = false;
        if (keycode == Input.Keys.DOWN) down = false;
        if (keycode == Input.Keys.LEFT) left = false;
        if (keycode == Input.Keys.RIGHT) right = false;
        return true;
    }
}
