package graficos;

import herramientasPixelArt.PixelArtReader;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Texturas {

    private static LinkedHashMap<String, BufferedImage> texturasMap;

    private static HashMap<String, BufferedImage> dropsMap;

    private static HashMap<String, BufferedImage> marioTexturas_Map;
    
    private static HashMap<String, BufferedImage> goombaTexturas_Map;

    private static HashMap<String, BufferedImage> bloqueModena_Map;

    private static HashMap<String, BufferedImage> bloquesEscombros_Map;

    private static PixelArtReader lectorMatriz;

    // Inicializar variables y valores estaticas de la clase
    static {
        texturasMap = new LinkedHashMap<>();
        dropsMap = new HashMap<>();
        marioTexturas_Map = new HashMap<>();
        goombaTexturas_Map = new HashMap<>();
        bloqueModena_Map = new HashMap<>();
        bloquesEscombros_Map = new HashMap<>();
        lectorMatriz = new PixelArtReader(2);

        getBloquesTecturas();
        getTuberiaTexturas();
        getMontanasTexturas();
        getArbustosTexturas();
        getNubesTexturas();
        getElementosFondo();

        getDropsTexturas();

        getMarioTexturas();
        getGoombaTexturas();

        getBloqueMonedaTexturas();
        getBloquesEscombrosTexturas();
    }

    public Texturas() {

    }

    private static void getMarioTexturas() {
        marioTexturas_Map.put("S_mario", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/mario"));
        marioTexturas_Map.put("S_marioSaltando", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioSaltando"));
        marioTexturas_Map.put("S_marioDerrapando", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioDerrapando"));
        marioTexturas_Map.put("S_marioCaminando1", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando1"));
        marioTexturas_Map.put("S_marioCaminando2", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando2"));
        marioTexturas_Map.put("S_marioCaminando3", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando3"));

        marioTexturas_Map.put("L_mario", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/mario"));
        marioTexturas_Map.put("L_marioSaltando", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/marioSaltando"));
        marioTexturas_Map.put("L_marioDerrapando", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/marioDerrapando"));
        marioTexturas_Map.put("L_marioCaminando1", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/marioCaminando1"));
        marioTexturas_Map.put("L_marioCaminando2", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/marioCaminando2"));
        marioTexturas_Map.put("L_marioCaminando3", lectorMatriz.drawPixelArt("Sprites/Mario/Grande/marioCaminando3"));
    }
    
    private static void getGoombaTexturas() {
        goombaTexturas_Map.put("S_mario", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/mario"));
        goombaTexturas_Map.put("S_marioSaltando", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioSaltando"));
        goombaTexturas_Map.put("S_marioDerrapando", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioDerrapando"));
        goombaTexturas_Map.put("S_marioCaminando1", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando1"));
        goombaTexturas_Map.put("S_marioCaminando2", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando2"));
        goombaTexturas_Map.put("S_marioCaminando3", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioCaminando3"));
    }

    private static void getTuberiaTexturas() {
        texturasMap.put("tuberiaCabeza", lectorMatriz.drawPixelArt("Sprites/Tuberias/tuberiaCabeza"));
        texturasMap.put("tuberia", lectorMatriz.drawPixelArt("Sprites/Tuberias/tuberia"));
    }

    private static void getBloqueMonedaTexturas() {
        bloqueModena_Map.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        bloqueModena_Map.put("bloqueMoneda2", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda2"));
        bloqueModena_Map.put("bloqueMoneda3", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda3"));
    }

    private static void getBloquesEscombrosTexturas() {
        bloquesEscombros_Map.put("ladrilloEscombro", lectorMatriz.drawPixelArt("Sprites/Escombros/ladrilloEscombro"));
    }

    private static void getBloquesTecturas() {
        texturasMap.put("bloquePiso", lectorMatriz.drawPixelArt("Sprites/Bloques/bloquePiso"));
        texturasMap.put("bloqueBandera", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBandera"));
        texturasMap.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        texturasMap.put("bloqueHongo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueHongo"));
        texturasMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMonedaHit"));
        texturasMap.put("bloqueLadrillo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueLadrillo"));
        texturasMap.put("bloqueBarrera", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBarrera"));
    }

    private static void getElementosFondo() {
        texturasMap.put("banderaMastil", lectorMatriz.drawPixelArt("Sprites/Backgrounds/banderaMastil"));
        texturasMap.put("bandera", lectorMatriz.drawPixelArt("Sprites/Backgrounds/bandera"));
        texturasMap.put("castillo", lectorMatriz.drawPixelArt("Sprites/Backgrounds/castillo"));
    }

    private static void getMontanasTexturas() {
        texturasMap.put("montanaChica", lectorMatriz.drawPixelArt("Sprites/Montanas/montanaChica"));
        texturasMap.put("montanaGrande", lectorMatriz.drawPixelArt("Sprites/Montanas/montanaGrande"));
    }

    private static void getArbustosTexturas() {
        texturasMap.put("arbustoChico", lectorMatriz.drawPixelArt("Sprites/Arbustos/arbustoChico"));
        texturasMap.put("arbustoMediano", lectorMatriz.drawPixelArt("Sprites/Arbustos/arbustoMediano"));
        texturasMap.put("arbustoGrande", lectorMatriz.drawPixelArt("Sprites/Arbustos/arbustoGrande"));
    }

    private static void getNubesTexturas() {
        texturasMap.put("nubeChica", lectorMatriz.drawPixelArt("Sprites/Nubes/nubeChica"));
        texturasMap.put("nubeMediana", lectorMatriz.drawPixelArt("Sprites/Nubes/nubeMediana"));
        texturasMap.put("nubeGrande", lectorMatriz.drawPixelArt("Sprites/Nubes/nubeGrande"));
    }

    private static void getDropsTexturas() {
        dropsMap.put("moneda", lectorMatriz.drawPixelArt("Sprites/Drops/moneda"));
        dropsMap.put("hongo", lectorMatriz.drawPixelArt("Sprites/Drops/hongo"));
    }

    public static BufferedImage getTextura(String textura) {
        BufferedImage selectedImage = texturasMap.get(textura);
        return selectedImage;
    }

    public static BufferedImage getDropsTextura(String textura) {
        BufferedImage selectedImage = dropsMap.get(textura);
        return selectedImage;
    }

    public static BufferedImage getEscombroTextura(String textura) {
        BufferedImage selectedImage = bloquesEscombros_Map.get(textura);
        return selectedImage;
    }

    public static LinkedHashMap<String, BufferedImage> getImagenesMap() {
        return texturasMap;
    }

    public static BufferedImage getMarioTextura(String nombre) {
        return marioTexturas_Map.get(nombre);
    }
    
    public static BufferedImage getGoombaTextura(String nombre) {
        return goombaTexturas_Map.get(nombre);
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
