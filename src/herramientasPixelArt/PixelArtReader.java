package herramientasPixelArt;

import graficos.LibreriaGrafica;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PixelArtReader extends Canvas {

    private LibreriaGrafica g;
    private int WIDTH;
    private int HEIGHT;
    private int ROWS;
    private int COLUMS;
    private Color[][] pixeles;
    private int tamanoPixel;

    public PixelArtReader(int tamanoPixel) {
        this.tamanoPixel = tamanoPixel;
    }

    public void cargarMatrizDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))){
            String lineaEscaneada;
            Color colorPixel = null;

            while ((lineaEscaneada = lector.readLine()) != null) {
                if (lineaEscaneada.equals("Dimensiones:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    ROWS = Integer.parseInt(partes[0]);
                    COLUMS = Integer.parseInt(partes[1]);

                    this.pixeles = new Color[ROWS][COLUMS];
                }
                if (lineaEscaneada.equals("Color (RGB):")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int r = Integer.parseInt(partes[0]);
                    int g = Integer.parseInt(partes[1]);
                    int b = Integer.parseInt(partes[2]);
                    int a = Integer.parseInt(partes[3]);
                    colorPixel = new Color(r, g, b, a);
                }
                if (lineaEscaneada.equals("Coordenada:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int filaArchivo = Integer.parseInt(partes[0]);
                    int columnaArchivo = Integer.parseInt(partes[1]);
                    pixeles[filaArchivo][columnaArchivo] = colorPixel;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el Pixel Art: " + e.getMessage());
        }
    }

    public BufferedImage drawPixelArt(String nombre) {
        cargarMatrizDesdeArchivo(nombre);

        this.WIDTH = COLUMS * tamanoPixel;
        this.HEIGHT = ROWS * tamanoPixel;

        g = new LibreriaGrafica(WIDTH, HEIGHT);
        
        int tamanoPixel = WIDTH / pixeles[0].length;
        for (int i = 0; i < pixeles.length; i++) {
            for (int j = 0; j < pixeles[0].length; j++) {
                int x = j * tamanoPixel;
                int y = i * tamanoPixel;
                g.fillRect(x, y, x + tamanoPixel, y + tamanoPixel, pixeles[i][j]);
            }
        }

        return g.getBuffer();
    }
    
        public void setTamanoPixel(int tamanoPixel) {
        this.tamanoPixel = tamanoPixel;
    }
}
