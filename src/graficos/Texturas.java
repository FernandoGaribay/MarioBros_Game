package graficos;

import utils.PixelArtReader;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Texturas {

    private static LinkedHashMap<String, BufferedImage> texturasMap;

    private static HashMap<String, BufferedImage> entidadesMap;

    private static HashMap<String, BufferedImage> marioTexturas_Map;
    
    private static HashMap<String, BufferedImage> bloqueModena_Map;

    private static HashMap<String, BufferedImage> bloquesEscombros_Map;

    private static PixelArtReader lectorMatriz;

    // Inicializar variables y valores estaticas de la clase
    static {
        texturasMap = new LinkedHashMap<>();
        entidadesMap = new HashMap<>();
        marioTexturas_Map = new HashMap<>();
        bloqueModena_Map = new HashMap<>();
        bloquesEscombros_Map = new HashMap<>();
        lectorMatriz = new PixelArtReader(2);

        getBloquesTexturas();
        getTuberiaTexturas();
        getMontanasTexturas();
        getArbustosTexturas();
        getNubesTexturas();
        getElementosFondo();

        getEntidadesTeturas();

        getMarioTexturas();

        getBloqueMonedaTexturas();
        getBloquesEscombrosTexturas();
    }

    public Texturas() {

    }

    private static void getMarioTexturas() {
        marioTexturas_Map.put("S_mario", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/mario"));
        marioTexturas_Map.put("S_marioMuerte", lectorMatriz.drawPixelArt("Sprites/Mario/Chico/marioMuerte"));
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
    
    private static void getTuberiaTexturas() {
        texturasMap.put("bloqueTuberiaCabeza", lectorMatriz.drawPixelArt("Sprites/Tuberias/bloqueTuberiaCabeza"));
        texturasMap.put("bloqueTuberia", lectorMatriz.drawPixelArt("Sprites/Tuberias/bloqueTuberia"));
    }

    private static void getBloqueMonedaTexturas() {
        bloqueModena_Map.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        bloqueModena_Map.put("bloqueMoneda2", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda2"));
        bloqueModena_Map.put("bloqueMoneda3", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda3"));
    }

    private static void getBloquesEscombrosTexturas() {
        bloquesEscombros_Map.put("ladrilloEscombro", lectorMatriz.drawPixelArt("Sprites/Escombros/ladrilloEscombro"));
    }

    private static void getBloquesTexturas() {
        texturasMap.put("bloquePiso", lectorMatriz.drawPixelArt("Sprites/Bloques/bloquePiso"));
        texturasMap.put("bloqueBandera", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBandera"));
        texturasMap.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        texturasMap.put("bloqueHongo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueHongo"));
        texturasMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMonedaHit"));
        texturasMap.put("bloqueLadrillo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueLadrillo"));
        texturasMap.put("bloqueBarreraJugador", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBarreraJugador"));
        texturasMap.put("bloqueBarreraEntidades", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBarreraEntidades"));
        texturasMap.put("entidadGoomba", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoomba"));
    }

    private static void getElementosFondo() {
        texturasMap.put("bloqueBanderaMastil", lectorMatriz.drawPixelArt("Sprites/Backgrounds/bloqueBanderaMastil"));
        texturasMap.put("bandera", lectorMatriz.drawPixelArt("Sprites/Backgrounds/bandera"));
        texturasMap.put("bloqueCastillo", lectorMatriz.drawPixelArt("Sprites/Backgrounds/bloqueCastillo"));
    }

    private static void getMontanasTexturas() {
        texturasMap.put("bloqueMontanaChica", lectorMatriz.drawPixelArt("Sprites/Montanas/bloqueMontanaChica"));
        texturasMap.put("bloqueMontanaGrande", lectorMatriz.drawPixelArt("Sprites/Montanas/bloqueMontanaGrande"));
    }

    private static void getArbustosTexturas() {
        texturasMap.put("bloqueArbustoChico", lectorMatriz.drawPixelArt("Sprites/Arbustos/bloqueArbustoChico"));
        texturasMap.put("bloqueArbustoMediano", lectorMatriz.drawPixelArt("Sprites/Arbustos/bloqueArbustoMediano"));
        texturasMap.put("bloqueArbustoGrande", lectorMatriz.drawPixelArt("Sprites/Arbustos/bloqueArbustoGrande"));
    }

    private static void getNubesTexturas() {
        texturasMap.put("bloqueNubeChica", lectorMatriz.drawPixelArt("Sprites/Nubes/bloqueNubeChica"));
        texturasMap.put("bloqueNubeMediana", lectorMatriz.drawPixelArt("Sprites/Nubes/bloqueNubeMediana"));
        texturasMap.put("bloqueNubeGrande", lectorMatriz.drawPixelArt("Sprites/Nubes/bloqueNubeGrande"));
    }

    private static void getEntidadesTeturas() {
        entidadesMap.put("entidadMoneda", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMoneda"));
        entidadesMap.put("entidadHongo", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadHongo"));
        entidadesMap.put("entidadGoomba", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoomba"));
        entidadesMap.put("entidadGoombaCaminando1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoombaCaminando1"));
        entidadesMap.put("entidadGoombaCaminando2", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoombaCaminando2"));
    }

    public static BufferedImage getTextura(String textura) {
        BufferedImage selectedImage = texturasMap.get(textura);
        return selectedImage;
    }

    public static BufferedImage getEntidadesTextura(String textura) {
        BufferedImage selectedImage = entidadesMap.get(textura);
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
