package graficos;

import herramientasPixelArt.PixelArtReader;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Texturas {

    private static LinkedHashMap<String, BufferedImage> texturasMap;

    private static HashMap<String, BufferedImage> marioDerechaCaminando_S_Map;
    private static HashMap<String, BufferedImage> marioIzquierdaCaminando_S_Map;
    private static BufferedImage marioDerechaSaltando_S;
    private static BufferedImage marioIzquierdaSaltando_S;
    private static BufferedImage marioDerecha_S;
    private static BufferedImage marioIzquierda_S;

    private static HashMap<String, BufferedImage> bloqueModena_Map;

    private static PixelArtReader lectorMatriz;

    // Inicializar variables y valores estaticas de la clase
    static {
        texturasMap = new LinkedHashMap<>();
        marioDerechaCaminando_S_Map = new HashMap<>();
        marioIzquierdaCaminando_S_Map = new HashMap<>();
        bloqueModena_Map = new HashMap<>();
        lectorMatriz = new PixelArtReader(2);

        getBloquesTecturas();
        getTuberiaTexturas();
        getMontanasTexturas();
        getArbustosTexturas();
        getNubesTexturas();
        getElementosFondo();

        getMarioDerechaCaminandoTexturas_S();
        getMarioIzquierdaCaminandoTexturas_S();
        getMarioTexturas_S();

        getBloqueMonedaTexturas();
    }

    public Texturas() {

    }

    private static void getMarioDerechaCaminandoTexturas_S() {
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando1", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioDerechaCaminando1"));
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando2", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioDerechaCaminando2"));
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando3", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioDerechaCaminando3"));
    }

    private static void getMarioIzquierdaCaminandoTexturas_S() {
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando1", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioIzquierdaCaminando1"));
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando2", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioIzquierdaCaminando2"));
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando3", lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioIzquierdaCaminando3"));
    }

    private static void getMarioTexturas_S() {
        marioDerechaSaltando_S = lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioDerechaSaltando");
        marioIzquierdaSaltando_S = lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioIzquierdaSaltando");
        marioDerecha_S = lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioDerecha");
        marioIzquierda_S = lectorMatriz.drawPixelArt("SpritesFiles/MarioArchivos/marioIzquierda");
    }

    private static void getTuberiaTexturas() {
        texturasMap.put("tuberiaCabeza", lectorMatriz.drawPixelArt("SpritesFiles/Tuberias/tuberiaCabeza"));
        texturasMap.put("tuberia", lectorMatriz.drawPixelArt("SpritesFiles/Tuberias/tuberia"));
    }

    private static void getBloqueMonedaTexturas() {
        bloqueModena_Map.put("bloqueMoneda1", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueMoneda1"));
        bloqueModena_Map.put("bloqueMoneda2", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueMoneda2"));
        bloqueModena_Map.put("bloqueMoneda3", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueMoneda3"));
    }

    private static void getBloquesTecturas() {
        texturasMap.put("bloquePiso", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloquePiso"));
        texturasMap.put("bloqueBandera", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueBandera"));
        texturasMap.put("bloqueMoneda1", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueMoneda1"));
        texturasMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueMonedaHit"));
        texturasMap.put("bloqueLadrillo", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueLadrillo"));
        texturasMap.put("bloqueBarrera", lectorMatriz.drawPixelArt("SpritesFiles/Bloques/bloqueBarrera"));
    }

    private static void getElementosFondo() {
        texturasMap.put("banderaMastil", lectorMatriz.drawPixelArt("SpritesFiles/Backgrounds/banderaMastil"));
        texturasMap.put("bandera", lectorMatriz.drawPixelArt("SpritesFiles/Backgrounds/bandera"));
        texturasMap.put("castillo", lectorMatriz.drawPixelArt("SpritesFiles/Backgrounds/castillo"));
    }

    private static void getMontanasTexturas() {
        texturasMap.put("montanaChica", lectorMatriz.drawPixelArt("SpritesFiles/Montanas/montanaChica"));
        texturasMap.put("montanaGrande", lectorMatriz.drawPixelArt("SpritesFiles/Montanas/montanaGrande"));
    }

    private static void getArbustosTexturas() {
        texturasMap.put("arbustoChico", lectorMatriz.drawPixelArt("SpritesFiles/Arbustos/arbustoChico"));
        texturasMap.put("arbustoMediano", lectorMatriz.drawPixelArt("SpritesFiles/Arbustos/arbustoMediano"));
        texturasMap.put("arbustoGrande", lectorMatriz.drawPixelArt("SpritesFiles/Arbustos/arbustoGrande"));
    }

    private static void getNubesTexturas() {
        texturasMap.put("nubeChica", lectorMatriz.drawPixelArt("SpritesFiles/Nubes/nubeChica"));
        texturasMap.put("nubeMediana", lectorMatriz.drawPixelArt("SpritesFiles/Nubes/nubeMediana"));
        texturasMap.put("nubeGrande", lectorMatriz.drawPixelArt("SpritesFiles/Nubes/nubeGrande"));
    }

    public static BufferedImage getTextura(String textura) {
        BufferedImage selectedImage = texturasMap.get(textura);
        return selectedImage;
    }

    public static LinkedHashMap<String, BufferedImage> getImagenesMap() {
        return texturasMap;
    }

    public static BufferedImage[] getMarioDerechaCaminando_S() {
        int tamano = marioDerechaCaminando_S_Map.size();
        BufferedImage[] marioImages = new BufferedImage[tamano];

        int index = 0;
        for (Map.Entry<String, BufferedImage> elemento : marioDerechaCaminando_S_Map.entrySet()) {
            marioImages[index] = elemento.getValue();
            index++;
        }

        return marioImages;
    }

    public static BufferedImage[] getMarioIzquierdaCaminando_S() {
        int tamano = marioIzquierdaCaminando_S_Map.size();
        BufferedImage[] marioImages = new BufferedImage[tamano];

        int index = 0;
        for (Map.Entry<String, BufferedImage> elemento : marioIzquierdaCaminando_S_Map.entrySet()) {
            marioImages[index] = elemento.getValue();
            index++;
        }

        return marioImages;
    }

    public static BufferedImage getMarioDerechaSaltando_S() {
        return marioDerechaSaltando_S;
    }

    public static BufferedImage getMarioIzquierdaSaltando_S() {
        return marioIzquierdaSaltando_S;
    }

    public static BufferedImage getMarioDerecha_S() {
        return marioDerecha_S;
    }

    public static BufferedImage getMarioIzquierda_S() {
        return marioIzquierda_S;
    }

    public static BufferedImage[] getBloquesMoneda() {
        int tamano = bloqueModena_Map.size();
        BufferedImage[] bloquesMoneda = new BufferedImage[tamano];

        int index = 0;
        for (Map.Entry<String, BufferedImage> elemento : bloqueModena_Map.entrySet()) {
            bloquesMoneda[index] = elemento.getValue();
            index++;
        }

        return bloquesMoneda;
    }
}
