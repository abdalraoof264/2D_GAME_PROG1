package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Tastatur implements InputProcessor {

    // Boolsche Variablen
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    // Leertaste Taste
    public boolean space;
    // Wurde sie schon gedrueckt?
    public boolean spaceJustPressed;

    // Wir implementieren die Methode aus dem Interface
    @Override
    public boolean keyDown(int keycode) {

        // Bewegungstasten
        if (keycode == Input.Keys.UP){
            up = true;
        }

        if (keycode == Input.Keys.DOWN) {
            down = true;
        }
        if (keycode == Input.Keys.LEFT) {
            left = true;
        }

        if (keycode == Input.Keys.RIGHT){
            right = true;
        }

        // Space Taste nur beim ersten Druecken
        if (keycode == Input.Keys.SPACE && !space) {
            space = true;
            spaceJustPressed = true;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        // Bewegungstasten
        if (keycode == Input.Keys.UP) {
            up = false;
        }

        if (keycode == Input.Keys.DOWN){
            down = false;
        }

        if (keycode == Input.Keys.LEFT){
            left = false;
        }

        if (keycode == Input.Keys.RIGHT){
            right = false;
        }

        // Space Taste
        if (keycode == Input.Keys.SPACE) space = false;

        return true;
    }

   // Wir setzen es nach jedem Sprung zurueck
    public void update() {
        spaceJustPressed = false;
    }

    // Ungenutzte Methoden, welche wir einbauen muessen, weil es ein Interface ist
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
