package tictactoe;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEffect {
    EAT_FOOD("src/tictactoe/goodresult-82807.wav"),
    EXPLODE("src/tictactoe/goodresult-82807.wav"),
    DIE("src/tictactoe/goodresult-82807.wav"),
    WELCOME("src/tictactoe/wpopular.wav"),
    ALPHABA("src/tictactoe/alphabo.WAV"),
    GLINDA("src/tictactoe/galindo.WAV"),
    KAROKE("src/tictactoe/karoke.WAV"); // Add this line

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    private Clip clip;

    private SoundEffect(String soundFileName) {
        try {
            File audioFile = new File(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    static void initGame() {
        values(); // Load all sound effects
    }
}