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

    // Inicializar variables estatica de la clase
    static {
        texturasMap = new LinkedHashMap<>();
        marioDerechaCaminando_S_Map = new HashMap<>();
        marioIzquierdaCaminando_S_Map = new HashMap<>();
        bloqueModena_Map = new HashMap<>();
        lectorMatriz = new PixelArtReader(2);
    }

    public Texturas() {
        try {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void getMarioDerechaCaminandoTexturas_S() {
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando1", lectorMatriz.drawPixelArt("marioDerechaCaminando1"));
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando2", lectorMatriz.drawPixelArt("marioDerechaCaminando2"));
        marioDerechaCaminando_S_Map.put("marioDerechaCaminando3", lectorMatriz.drawPixelArt("marioDerechaCaminando3"));
    }

    private void getMarioIzquierdaCaminandoTexturas_S() {
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando1", lectorMatriz.drawPixelArt("marioIzquierdaCaminando1"));
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando2", lectorMatriz.drawPixelArt("marioIzquierdaCaminando2"));
        marioIzquierdaCaminando_S_Map.put("marioIzquierdaCaminando3", lectorMatriz.drawPixelArt("marioIzquierdaCaminando3"));
    }

    private void getMarioTexturas_S() {
        marioDerechaSaltando_S = lectorMatriz.drawPixelArt("marioDerechaSaltando");
        marioIzquierdaSaltando_S = lectorMatriz.drawPixelArt("marioIzquierdaSaltando");
        marioDerecha_S = lectorMatriz.drawPixelArt("marioDerecha");
        marioIzquierda_S = lectorMatriz.drawPixelArt("marioIzquierda");
    }

    private void getTuberiaTexturas() {
        texturasMap.put("tuberiaCabeza", lectorMatriz.drawPixelArt("tuberiaCabeza"));
        texturasMap.put("tuberia", lectorMatriz.drawPixelArt("tuberia"));
    }

    private void getBloqueMonedaTexturas() {
        bloqueModena_Map.put("bloqueMoneda1", lectorMatriz.drawPixelArt("bloqueMoneda1"));
        bloqueModena_Map.put("bloqueMoneda2", lectorMatriz.drawPixelArt("bloqueMoneda2"));
        bloqueModena_Map.put("bloqueMoneda3", lectorMatriz.drawPixelArt("bloqueMoneda3"));
    }

    private void getBloquesTecturas() {
        texturasMap.put("bloquePiso", lectorMatriz.drawPixelArt("bloquePiso"));
        texturasMap.put("bloqueBandera", lectorMatriz.drawPixelArt("bloqueBandera"));
        texturasMap.put("bloqueMoneda1", lectorMatriz.drawPixelArt("bloqueMoneda1"));
        texturasMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("bloqueMonedaHit"));
        texturasMap.put("ladrillo", lectorMatriz.drawPixelArt("ladrillo"));
        texturasMap.put("barrera", lectorMatriz.drawPixelArt("barrera"));
    }

    private void getElementosFondo() {
        texturasMap.put("banderaMastil", lectorMatriz.drawPixelArt("banderaMastil"));
        texturasMap.put("bandera", lectorMatriz.drawPixelArt("bandera"));
        texturasMap.put("castillo", lectorMatriz.drawPixelArt("castillo"));
    }

    private void getMontanasTexturas() {
        texturasMap.put("montanaChica", lectorMatriz.drawPixelArt("montanaChica"));
        texturasMap.put("montanaGrande", lectorMatriz.drawPixelArt("montanaGrande"));
    }

    private void getArbustosTexturas() {
        texturasMap.put("arbustoChico", lectorMatriz.drawPixelArt("arbustoChico"));
        texturasMap.put("arbustoMediano", lectorMatriz.drawPixelArt("arbustoMediano"));
        texturasMap.put("arbustoGrande", lectorMatriz.drawPixelArt("arbustoGrande"));
    }

    private void getNubesTexturas() {
        texturasMap.put("nubeChica", lectorMatriz.drawPixelArt("nubeChica"));
        texturasMap.put("nubeMediana", lectorMatriz.drawPixelArt("nubeMediana"));
        texturasMap.put("nubeGrande", lectorMatriz.drawPixelArt("nubeGrande"));
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
