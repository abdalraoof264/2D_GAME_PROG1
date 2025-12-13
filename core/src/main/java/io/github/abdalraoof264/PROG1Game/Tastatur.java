package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Tastatur-Klasse für Input-Handling
 * Verwaltet alle Tastatur-Eingaben
 */
public class Tastatur implements InputProcessor {

    // Bewegungstasten (gehalten)
    public boolean up, down, left, right;

    // Space-Taste
    public boolean space;              // Ist die Taste gedrückt?
    public boolean spaceJustPressed;   // Wurde sie gerade erst gedrückt?

    @Override
    public boolean keyDown(int keycode) {
        // Bewegungstasten
        if (keycode == Input.Keys.UP)    up = true;
        if (keycode == Input.Keys.DOWN)  down = true;
        if (keycode == Input.Keys.LEFT)  left = true;
        if (keycode == Input.Keys.RIGHT) right = true;

        // Space-Taste (nur beim ersten Drücken)
        if (keycode == Input.Keys.SPACE && !space) {
            space = true;
            spaceJustPressed = true;  // Nur beim ersten Mal true
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Bewegungstasten
        if (keycode == Input.Keys.UP)    up = false;
        if (keycode == Input.Keys.DOWN)  down = false;
        if (keycode == Input.Keys.LEFT)  left = false;
        if (keycode == Input.Keys.RIGHT) right = false;

        // Space-Taste
        if (keycode == Input.Keys.SPACE) space = false;

        return true;
    }

    /**
     * WICHTIG: Muss jeden Frame aufgerufen werden!
     * Setzt spaceJustPressed zurück
     */
    public void update() {
        spaceJustPressed = false;  // Reset nach jedem Frame
    }

    // Ungenutzte InputProcessor Methoden
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
