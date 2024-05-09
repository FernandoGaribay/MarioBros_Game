package object.util;

import java.util.ArrayList;
import java.util.List;
import main.Game;
import utils.ReproductorMP3;

public class HandlerSonidos {

    // OBJETOS
    private static List<String> gameSounds;

    static {
        gameSounds = new ArrayList<>();
    }

    public static void tick() {
        if (!Game.SONIDO) {
            return;
        }
        for (String nombreSonido : gameSounds) {
            ReproductorMP3.reproducirSonido(nombreSonido);
        }
        gameSounds.removeAll(gameSounds);
    }

    public static void playSound(String sonido) {
        gameSounds.add(sonido);
    }
    
    public static void stopAllSound(){
        gameSounds.removeAll(gameSounds);
        ReproductorMP3.pararSonido();
    }

    public static List<String> getGameSounds() {
        return gameSounds;
    }

    public static void setGameSounds(List<String> gameSounds) {
        HandlerSonidos.gameSounds = gameSounds;
    }

}
