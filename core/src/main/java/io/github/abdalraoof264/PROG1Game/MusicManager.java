package io.github.abdalraoof264.PROG1Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {

    // Singleton Instanz
    private static MusicManager instance;

    // Die Musik Objekte
    private Music menuMusic;
    private Music gameMusic;
    private Music gameOverMusic;
    private Music victoryMusic;

    // Aktuell spielende Musik
    private Music currentMusic;

    // Lautstärkevon 0.0 bis 1.0
    private float volume = 0.5f;

    // Privater Singleton Pattern Konstruktor
    private MusicManager() {
        // Lade alle Musik-Dateien
        // Falls ein Problem, entsteht fangen wir es ab
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


    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    // Spielt Menue Musik
    public void playMenuMusic() {
        playMusic(menuMusic);
    }

    // Spielt Spiel Musik
    public void playGameMusic() {
        playMusic(gameMusic);
    }

    // Spielt Gameover Musik
    public void playGameOverMusic() {
        playMusic(gameOverMusic);
    }

    // Spielt die Victory
    public void playVictoryMusic() {
        playMusic(victoryMusic);
    }


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

    // Soppt die Musik
    public void stopMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.stop();
        }
    }

    // Pausiert die Musik
    public void pauseMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.pause();
        }
    }

   // Setzt Musik fort
    public void resumeMusic() {
        if (currentMusic != null && !currentMusic.isPlaying()) {
            currentMusic.play();
        }
    }

   // Setzt die Lautstärke
    public void setVolume(float volume) {
        this.volume = Math.max(0f, Math.min(1f, volume));  // Clamp zwischen 0 und 1
        if (currentMusic != null) {
            currentMusic.setVolume(this.volume);
        }
    }

    public void increaseVolume() {
        setVolume(volume + 0.1f);
    }

    public void decreaseVolume() {
        setVolume(volume - 0.1f);
    }

    public float getVolume() {
        return volume;
    }

    public boolean isPlaying() {
        return currentMusic != null && currentMusic.isPlaying();
    }

    // Loescht die Musik
    public void dispose() {
        if (menuMusic != null) menuMusic.dispose();
        if (gameMusic != null) gameMusic.dispose();
        if (gameOverMusic != null) gameOverMusic.dispose();
        if (victoryMusic != null) victoryMusic.dispose();
    }
}
