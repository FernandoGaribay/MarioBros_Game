package graficos;

import utils.PixelArtReader;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class LibreriaGrafica extends Canvas {

    private final int WIDTH;
    private final int HEIGHT;
//    private final Tipografia tipografia;
    private BufferedImage buffer;

    // Variables para la traslación
    private int translateX = 0;
    private int translateY = 0;

    public LibreriaGrafica(int width, int height) {
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
//        tipografia = new Tipografia();
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void fillRect(int x0, int y0, int x1, int y1, Color color) {
        x0 += translateX;
        y0 += translateY;
        x1 += translateX;
        y1 += translateY;

        int nx0 = Math.min(x0, x1);
        int ny0 = Math.min(y0, y1);
        int nx1 = Math.max(x0, x1);
        int ny1 = Math.max(y0, y1);

        for (int y = ny0; y <= ny1; y++) {
            int xInicio = -1;
            for (int x = nx0; x <= nx1; x++) {
                if (xInicio == -1) {
                    xInicio = x;
                }
                if (xInicio != -1) {
                    putPixel(xInicio, y, color);
                    xInicio = -1;
                }
            }
        }
    }

    public void drawRectangle(Rectangle rectangle, Color color) {
        drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y, color);
        drawLine(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, color);
        drawLine(rectangle.x + rectangle.width, rectangle.y + rectangle.height, rectangle.x, rectangle.y + rectangle.height, color);
        drawLine(rectangle.x, rectangle.y + rectangle.height, rectangle.x, rectangle.y, color);
    }

    public void drawRect(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color);
        drawLine(x0, y1, x1, y1, color);
        drawLine(x0, y0, x0, y1, color);
        drawLine(x1, y0, x1, y1, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        x1 += translateX;
        y1 += translateY;
        x2 += translateX;
        y2 += translateY;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x1, y1, color);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void drawImage(BufferedImage img, int x, int y) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, null);
    }

    public void drawImage(BufferedImage img, int x, int y, int width, int height) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, width, height, null);
    }

    public void drawText(String texto, int x, int y, int fontSize) {
        int cantidadLetras = texto.length();
        int interlineado = 0;
        int separacion = 5 * fontSize;
        int offSetX = cantidadLetras * 13 * fontSize + separacion * (cantidadLetras - 1); // 13 es la unidad minima en width para cada letra (15x13)
        int offSetY = 13 * fontSize; // 15 es la unidad minima en height para cada letra (15x13)

        Tipografia.setFontSize(fontSize);
        for (int i = 0; i < cantidadLetras; i++) {
            BufferedImage letra = Tipografia.getLetra(texto.substring(i, i + 1));
            buffer.getGraphics().drawImage(letra, x + interlineado - offSetX / 2, y - offSetY / 2, null);

            interlineado += letra.getWidth() + separacion;
        }
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void translate(int x, int y) {
        this.translateX = x;
        this.translateY = y;
    }

    public void limpiarBuffer() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
}

class Tipografia {

    private static HashMap<String, BufferedImage> tipografiaMap;
    private static PixelArtReader lectorMatriz;

    static {
        tipografiaMap = new HashMap<>();
        lectorMatriz = new PixelArtReader(1);

        getTipografiaTexturas();
    }

    private static void getTipografiaTexturas() {
        tipografiaMap.put("a", lectorMatriz.drawPixelArt("Tipografia/a"));
        tipografiaMap.put("b", lectorMatriz.drawPixelArt("Tipografia/b"));
        tipografiaMap.put("c", lectorMatriz.drawPixelArt("Tipografia/c"));
        tipografiaMap.put("d", lectorMatriz.drawPixelArt("Tipografia/d"));
        tipografiaMap.put("e", lectorMatriz.drawPixelArt("Tipografia/e"));
        tipografiaMap.put("f", lectorMatriz.drawPixelArt("Tipografia/f"));
        tipografiaMap.put("g", lectorMatriz.drawPixelArt("Tipografia/g"));
        tipografiaMap.put("h", lectorMatriz.drawPixelArt("Tipografia/h"));
        tipografiaMap.put("i", lectorMatriz.drawPixelArt("Tipografia/i"));
        tipografiaMap.put("j", lectorMatriz.drawPixelArt("Tipografia/j"));
        tipografiaMap.put("k", lectorMatriz.drawPixelArt("Tipografia/k"));
        tipografiaMap.put("l", lectorMatriz.drawPixelArt("Tipografia/l"));
        tipografiaMap.put("m", lectorMatriz.drawPixelArt("Tipografia/m"));
        tipografiaMap.put("n", lectorMatriz.drawPixelArt("Tipografia/n"));
        tipografiaMap.put("o", lectorMatriz.drawPixelArt("Tipografia/o"));
        tipografiaMap.put("p", lectorMatriz.drawPixelArt("Tipografia/p"));
        tipografiaMap.put("q", lectorMatriz.drawPixelArt("Tipografia/q"));
        tipografiaMap.put("r", lectorMatriz.drawPixelArt("Tipografia/r"));
        tipografiaMap.put("s", lectorMatriz.drawPixelArt("Tipografia/s"));
        tipografiaMap.put("t", lectorMatriz.drawPixelArt("Tipografia/t"));
        tipografiaMap.put("u", lectorMatriz.drawPixelArt("Tipografia/u"));
        tipografiaMap.put("v", lectorMatriz.drawPixelArt("Tipografia/v"));
        tipografiaMap.put("w", lectorMatriz.drawPixelArt("Tipografia/w"));
        tipografiaMap.put("x", lectorMatriz.drawPixelArt("Tipografia/x"));
        tipografiaMap.put("y", lectorMatriz.drawPixelArt("Tipografia/y"));
        tipografiaMap.put("z", lectorMatriz.drawPixelArt("Tipografia/z"));
        tipografiaMap.put("0", lectorMatriz.drawPixelArt("Tipografia/0"));
        tipografiaMap.put("1", lectorMatriz.drawPixelArt("Tipografia/1"));
        tipografiaMap.put("2", lectorMatriz.drawPixelArt("Tipografia/2"));
        tipografiaMap.put("3", lectorMatriz.drawPixelArt("Tipografia/3"));
        tipografiaMap.put("4", lectorMatriz.drawPixelArt("Tipografia/4"));
        tipografiaMap.put("5", lectorMatriz.drawPixelArt("Tipografia/5"));
        tipografiaMap.put("6", lectorMatriz.drawPixelArt("Tipografia/6"));
        tipografiaMap.put("7", lectorMatriz.drawPixelArt("Tipografia/7"));
        tipografiaMap.put("8", lectorMatriz.drawPixelArt("Tipografia/8"));
        tipografiaMap.put("9", lectorMatriz.drawPixelArt("Tipografia/9"));
        tipografiaMap.put(" ", lectorMatriz.drawPixelArt("Tipografia/espacio"));
        tipografiaMap.put("-", lectorMatriz.drawPixelArt("Tipografia/-"));

    }

    public static BufferedImage getLetra(String letra) {
        return tipografiaMap.get(letra);
    }

    public static void setFontSize(int size) {
        tipografiaMap = new HashMap<>();

        lectorMatriz.setTamanoPixel(size);
        getTipografiaTexturas();
    }
}
