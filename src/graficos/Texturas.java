package graficos;

import herramientasPixelArt.PixelArtReader;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public class Texturas {

    private static LinkedHashMap<String, BufferedImage> imagenesMap;
    private static PixelArtReader lectorMatriz;

    public Texturas() {
        imagenesMap = new LinkedHashMap<>();

        lectorMatriz = new PixelArtReader(2);

        try {
            getBloquesTecturas();
            getTuberiaTexturas();
            getMontanasTexturas();
            getArbustosTexturas();
            getNubesTexturas();
            getElementosFondo();
            getMarioTextures();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void getMarioTextures() {
        imagenesMap.put("marioDerecha", lectorMatriz.drawPixelArt("marioDerecha"));
    }

    private void getTuberiaTexturas() {
        imagenesMap.put("tuberiaCabeza", lectorMatriz.drawPixelArt("tuberiaCabeza"));
        imagenesMap.put("tuberia", lectorMatriz.drawPixelArt("tuberia"));
    }

    private void getBloquesTecturas() {
        imagenesMap.put("bloquePiso", lectorMatriz.drawPixelArt("bloquePiso"));
        imagenesMap.put("bloqueBandera", lectorMatriz.drawPixelArt("bloqueBandera"));
        imagenesMap.put("bloqueMoneda", lectorMatriz.drawPixelArt("bloqueMoneda"));
        imagenesMap.put("bloqueMonedaHit", lectorMatriz.drawPixelArt("bloqueMonedaHit"));
        imagenesMap.put("ladrillo", lectorMatriz.drawPixelArt("ladrillo"));
        imagenesMap.put("barrera", lectorMatriz.drawPixelArt("barrera"));
    }

    private void getElementosFondo() {
        imagenesMap.put("banderaMastil", lectorMatriz.drawPixelArt("banderaMastil"));
        imagenesMap.put("bandera", lectorMatriz.drawPixelArt("bandera"));
        imagenesMap.put("castillo", lectorMatriz.drawPixelArt("castillo"));
    }

    private void getMontanasTexturas() {
        imagenesMap.put("montanaChica", lectorMatriz.drawPixelArt("montanaChica"));
        imagenesMap.put("montanaGrande", lectorMatriz.drawPixelArt("montanaGrande"));
    }

    private void getArbustosTexturas() {
        imagenesMap.put("arbustoChico", lectorMatriz.drawPixelArt("arbustoChico"));
        imagenesMap.put("arbustoMediano", lectorMatriz.drawPixelArt("arbustoMediano"));
        imagenesMap.put("arbustoGrande", lectorMatriz.drawPixelArt("arbustoGrande"));
    }

    private void getNubesTexturas() {
        imagenesMap.put("nubeChica", lectorMatriz.drawPixelArt("nubeChica"));
        imagenesMap.put("nubeMediana", lectorMatriz.drawPixelArt("nubeMediana"));
        imagenesMap.put("nubeGrande", lectorMatriz.drawPixelArt("nubeGrande"));
    }

    public static BufferedImage getTextura(String textura) {
        BufferedImage selectedImage = imagenesMap.get(textura);
        return selectedImage;
    }

    public static LinkedHashMap<String, BufferedImage> getImagenesMap() {
        return imagenesMap;
    }

}
