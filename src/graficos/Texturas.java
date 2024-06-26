package graficos;

import utils.PixelArtReader;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Texturas {

    // LECTOR DE MATRICES
    private static PixelArtReader lectorMatriz;

    // TEXTURAS
    private static LinkedHashMap<String, BufferedImage> texturasMap;
    private static HashMap<String, BufferedImage> entidadesMap;
    private static HashMap<String, BufferedImage> marioTexturas_Map;
    private static HashMap<String, BufferedImage> bloqueModena_Map;
    private static HashMap<String, BufferedImage> bloquesEscombros_Map;

    // Inicializar variables y valores estaticas de la clase
    static {
        lectorMatriz = new PixelArtReader(2);
        
        texturasMap = new LinkedHashMap<>();
        entidadesMap = new HashMap<>();
        marioTexturas_Map = new HashMap<>();
        bloqueModena_Map = new HashMap<>();
        bloquesEscombros_Map = new HashMap<>();

        // Texturas Map
        getBloquesTexturas();
        getTuberiaTexturas();
        getMontanasTexturas();
        getArbustosTexturas();
        getNubesTexturas();
        getElementosFondo();

        // Entidades Map
        getEntidadesTexturas();

        // Mario Map
        getMarioTexturas();

        // Bloque moneda y Bloque escombros Map
        getBloqueMonedaTexturas();
        getBloquesEscombrosTexturas();
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
        texturasMap.put("bloqueTuberiaTerminalVertical", lectorMatriz.drawPixelArt("Sprites/Tuberias/bloqueTuberiaTerminalVertical"));
        texturasMap.put("bloqueTuberiaVertical", lectorMatriz.drawPixelArt("Sprites/Tuberias/bloqueTuberiaVertical"));
        texturasMap.put("bloqueTuberiaCabezaVertical", lectorMatriz.drawPixelArt("Sprites/Tuberias/bloqueTuberiaCabezaVertical"));
    }

    private static void getBloqueMonedaTexturas() {
        bloqueModena_Map.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        bloqueModena_Map.put("bloqueMoneda2", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda2"));
        bloqueModena_Map.put("bloqueMoneda3", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda3"));
    }

    private static void getBloquesEscombrosTexturas() {
        bloquesEscombros_Map.put("ladrilloEscombro", lectorMatriz.drawPixelArt("Sprites/Escombros/ladrilloEscombro"));
        bloquesEscombros_Map.put("ladrilloEscombroAzul", lectorMatriz.drawPixelArt("Sprites/Escombros/ladrilloEscombroAzul"));
    }

    private static void getBloquesTexturas() {
        texturasMap.put("bloquePiso", lectorMatriz.drawPixelArt("Sprites/Bloques/bloquePiso"));
        texturasMap.put("bloquePisoAzul", lectorMatriz.drawPixelArt("Sprites/Bloques/bloquePisoAzul"));
        texturasMap.put("bloqueBandera", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBandera"));
        texturasMap.put("bloqueMoneda1", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMoneda1"));
        texturasMap.put("bloqueHongoRojo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueHongoRojo"));
        texturasMap.put("bloqueHongoVerde", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueHongoVerde"));
        texturasMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueMonedaHit"));
        texturasMap.put("bloqueLadrillo", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueLadrillo"));
        texturasMap.put("bloqueLadrilloAzul", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueLadrilloAzul"));
        texturasMap.put("bloqueLadrilloMonedas", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueLadrilloMonedas"));
        texturasMap.put("bloqueBarreraJugador", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBarreraJugador"));
        texturasMap.put("bloqueBarreraEntidades", lectorMatriz.drawPixelArt("Sprites/Bloques/bloqueBarreraEntidades"));

        texturasMap.put("entidadGoomba", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoomba"));
        texturasMap.put("entidadKoopa", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopa"));
        texturasMap.put("entidadKoopaCaparazon", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopaCaparazon"));
        texturasMap.put("entidadMoneda1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMoneda1"));
        texturasMap.put("entidadMonedaAzul1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMonedaAzul1"));

        texturasMap.put("entradaA_Horizontal", lectorMatriz.drawPixelArt("Sprites/Bloques/entradaA_Horizontal"));
        texturasMap.put("entradaA_Vertical", lectorMatriz.drawPixelArt("Sprites/Bloques/entradaA_Vertical"));
        texturasMap.put("salidaB_Horizontal", lectorMatriz.drawPixelArt("Sprites/Bloques/salidaB_Horizontal"));
        texturasMap.put("salidaB_Vertical", lectorMatriz.drawPixelArt("Sprites/Bloques/salidaB_Vertical"));
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

    private static void getEntidadesTexturas() {
        entidadesMap.put("entidadMoneda1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMoneda1"));
        entidadesMap.put("entidadMoneda2", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMoneda2"));
        entidadesMap.put("entidadMoneda3", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMoneda3"));
        entidadesMap.put("entidadMonedaAzul1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMonedaAzul1"));
        entidadesMap.put("entidadMonedaAzul2", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMonedaAzul2"));
        entidadesMap.put("entidadMonedaAzul3", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadMonedaAzul3"));
        entidadesMap.put("entidadHongoRojo", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadHongoRojo"));
        entidadesMap.put("entidadHongoVerde", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadHongoVerde"));
        entidadesMap.put("entidadGoomba", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoomba"));
        entidadesMap.put("entidadGoombaCaminando1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoombaCaminando1"));
        entidadesMap.put("entidadGoombaCaminando2", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadGoombaCaminando2"));
        entidadesMap.put("entidadKoopa", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopa"));
        entidadesMap.put("entidadKoopaCaparazon", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopaCaparazon"));
        entidadesMap.put("entidadKoopaCaminando1", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopaCaminando1"));
        entidadesMap.put("entidadKoopaCaminando2", lectorMatriz.drawPixelArt("Sprites/Entidades/entidadKoopaCaminando2"));
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
