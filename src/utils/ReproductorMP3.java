package utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReproductorMP3 {

    private static List<Clip> clips;
    private static ClassLoader classLoader;

    static {
        clips = new ArrayList<>();
    }

    public static void reproducirSonido(String nombreSonido) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = obtenerAudioInputStream(nombreSonido);
            if (audioInputStream != null) {
                clip.open(audioInputStream);
                clip.start();
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
//                        clips.remove(clip);
                    }
                });
                clips.add(clip);
            }
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private static AudioInputStream obtenerAudioInputStream(String nombreSonido) {
        try {
            classLoader = ReproductorMP3.class.getClassLoader();
            return AudioSystem.getAudioInputStream(classLoader.getResource("recursos/" + nombreSonido));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void pararSonido() {
        for (Clip clip : clips) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }
        clips.removeAll(clips);
    }
}

