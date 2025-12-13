package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Singleton MusicManager
 * Verwaltet die Hintergrundmusik im gesamten Spiel
 */
public class MusicManager {

    // Singleton Instance
    private static MusicManager instance;

    // Die Musik-Objekte
    private Music menuMusic;
    private Music gameMusic;
    private Music gameOverMusic;
    private Music victoryMusic;

    // Aktuell spielende Musik
    private Music currentMusic;

    // Lautstärke (0.0 bis 1.0)
    private float volume = 0.5f;

    /**
     * Private Konstruktor (Singleton Pattern)
     */
    private MusicManager() {
        // Lade alle Musik-Dateien
        try {
            menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu_music.mp3"));
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_music.mp3"));
            gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("gameover_music.mp3"));
            victoryMusic = Gdx.audio.newMusic(Gdx.files.internal("victory_music.mp3"));

            // Alle Musik auf Loop setzen
            menuMusic.setLooping(true);
            gameMusic.setLooping(true);
            gameOverMusic.setLooping(false);  // Game Over nur einmal
            victoryMusic.setLooping(false);   // Victory nur einmal

        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Musik: " + e.getMessage());
        }
    }

    /**
     * Singleton Instance holen
     */
    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    /**
     * Spiele Menu-Musik
     */
    public void playMenuMusic() {
        playMusic(menuMusic);
    }

    /**
     * Spiele Game-Musik
     */
    public void playGameMusic() {
        playMusic(gameMusic);
    }

    /**
     * Spiele GameOver-Musik
     */
    public void playGameOverMusic() {
        playMusic(gameOverMusic);
    }

    /**
     * Spiele Victory-Musik
     */
    public void playVictoryMusic() {
        playMusic(victoryMusic);
    }

    /**
     * Interne Methode zum Wechseln der Musik
     */
    private void playMusic(Music music) {
        if (music == null) {
            System.err.println("Musik-Datei nicht gefunden!");
            return;
        }

        // Stoppe aktuelle Musik
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.stop();
        }

        // Spiele neue Musik
        currentMusic = music;
        currentMusic.setVolume(volume);
        currentMusic.play();
    }

    /**
     * Stoppe alle Musik
     */
    public void stopMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.stop();
        }
    }

    /**
     * Pause Musik
     */
    public void pauseMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.pause();
        }
    }

    /**
     * Resume Musik
     */
    public void resumeMusic() {
        if (currentMusic != null && !currentMusic.isPlaying()) {
            currentMusic.play();
        }
    }

    /**
     * Setze Lautstärke (0.0 bis 1.0)
     */
    public void setVolume(float volume) {
        this.volume = Math.max(0f, Math.min(1f, volume));  // Clamp zwischen 0 und 1
        if (currentMusic != null) {
            currentMusic.setVolume(this.volume);
        }
    }

    /**
     * Erhöhe Lautstärke
     */
    public void increaseVolume() {
        setVolume(volume + 0.1f);
    }

    /**
     * Verringere Lautstärke
     */
    public void decreaseVolume() {
        setVolume(volume - 0.1f);
    }

    /**
     * Hole aktuelle Lautstärke
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Ist Musik am Spielen?
     */
    public boolean isPlaying() {
        return currentMusic != null && currentMusic.isPlaying();
    }

    /**
     * Dispose - Ressourcen freigeben
     */
    public void dispose() {
        if (menuMusic != null) menuMusic.dispose();
        if (gameMusic != null) gameMusic.dispose();
        if (gameOverMusic != null) gameOverMusic.dispose();
        if (victoryMusic != null) victoryMusic.dispose();
    }
}
