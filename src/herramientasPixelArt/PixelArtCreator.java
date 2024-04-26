package herramientasPixelArt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PixelArtCreator extends JPanel {

    // Constantes
    private int HEIGHT;
    private int WIDTH;
    private int NUM_FILAS;
    private int NUM_COLUMNAS;
    private int TAMANO_PIXEL;

    private Color[][] pixeles;
    private Color color = Color.BLACK;
    private Color background = new Color(200, 200, 200);
    private LinkedList<Color> listaColores;
    private boolean botonIzquierdo = false;
    private boolean botonDerecho = false;
    private boolean funcionRelleno = false;

    private BufferedImage buffer;

    public PixelArtCreator(int filas, int columnas) {
        SwingUtilities.invokeLater(() -> {
            equilibrarTamanoPixeles(filas, columnas);
            setSize(new Dimension(WIDTH, HEIGHT));
            setOpaque(true);

            initPixeles();
            initEvents();
        });
    }

    private void equilibrarTamanoPixeles(int filas, int columnas) {
        // Inicializar valores
        HEIGHT = 770;
        WIDTH = 776;
        NUM_FILAS = filas;
        NUM_COLUMNAS = columnas;

        // Calcular el tamano del pixel
        int maxFilasColumnas = Math.max(filas, columnas);
        int maxWidthHeight = Math.max(HEIGHT, WIDTH);
        this.TAMANO_PIXEL = maxWidthHeight / maxFilasColumnas;

        // Actualizar el Width y el Height
        HEIGHT = TAMANO_PIXEL * NUM_FILAS;
        WIDTH = TAMANO_PIXEL * NUM_COLUMNAS;

        // Inicializar buffer
        this.buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // Actualizar localicacion
        int nuevoX = (getParent().getWidth() - WIDTH) / 2;
        int nuevoY = (getParent().getHeight() - HEIGHT) / 2;
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocation(nuevoX, nuevoY);
    }

    private void initEvents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    botonIzquierdo = true;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    botonDerecho = true;
                }
                actualizarPixel(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    botonIzquierdo = false;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    botonDerecho = false;
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                actualizarPixel(e);
            }
        });
    }

    private void initPixeles() {
        // Inicializar la matriz de pixeles con color blanco transparente
        pixeles = new Color[NUM_FILAS][NUM_COLUMNAS];
        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_COLUMNAS; j++) {
                pixeles[i][j] = new Color(0, 0, 0, 0);
            }
        }
    }

    private void actualizarPixel(MouseEvent e) {
        int fila = e.getY() / TAMANO_PIXEL;
        int columna = e.getX() / TAMANO_PIXEL;
        if (fila >= 0 && fila < NUM_FILAS && columna >= 0 && columna < NUM_COLUMNAS) {
            if (botonIzquierdo) {
                if (!funcionRelleno) {
                    pixeles[fila][columna] = color;
                } else {
                    Color colorClick = pixeles[fila][columna];
                    funcionRelleno(columna, fila, color, colorClick);
                }
            } else if (botonDerecho) {
                pixeles[fila][columna] = new Color(0, 0, 0, 0);
            }
            repaint();
        }
    }

    private void funcionRelleno(int x, int y, Color nuevoColor, Color colorClick) {
        if (pixeles[y][x].equals(colorClick) && !pixeles[y][x].equals(nuevoColor)) {
            pixeles[y][x] = nuevoColor;
            if (x > 0) {
                funcionRelleno(x - 1, y, nuevoColor, colorClick);
            }
            if (y > 0) {
                funcionRelleno(x, y - 1, nuevoColor, colorClick);
            }
            if (x < NUM_COLUMNAS - 1) {
                funcionRelleno(x + 1, y, nuevoColor, colorClick);
            }
            if (y < NUM_FILAS - 1) {
                funcionRelleno(x, y + 1, nuevoColor, colorClick);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Dibujado"> 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fillRectangle(0, 0, WIDTH, HEIGHT, background);
        pintarPixeles();
        pintarMallado();

        g.drawImage(buffer, 0, 0, null);
    }

    private void pintarPixeles() {
        for (int i = 0; i < NUM_FILAS; i++) {
            for (int j = 0; j < NUM_COLUMNAS; j++) {
                int x = j * TAMANO_PIXEL;
                int y = i * TAMANO_PIXEL;
                if (pixeles[i][j].getRGB() == new Color(0, 0, 0, 0).getRGB()) {
                    drawRectangle(x, y, x + TAMANO_PIXEL, y + TAMANO_PIXEL, Color.DARK_GRAY);
                } else {
                    fillRectangle(x, y, x + TAMANO_PIXEL, y + TAMANO_PIXEL, pixeles[i][j]);
                }
            }
        }
    }

    private void pintarMallado() {
        for (int i = 0; i < NUM_FILAS; i++) {
            drawLine(0, i * TAMANO_PIXEL, WIDTH, i * TAMANO_PIXEL, Color.DARK_GRAY);
        }
        drawLine(0, NUM_FILAS * TAMANO_PIXEL - 1, WIDTH, NUM_FILAS * TAMANO_PIXEL - 1, Color.DARK_GRAY);

        for (int i = 0; i < NUM_COLUMNAS; i++) {
            drawLine(i * TAMANO_PIXEL, 0, i * TAMANO_PIXEL, HEIGHT, Color.DARK_GRAY);
        }
        drawLine(NUM_COLUMNAS * TAMANO_PIXEL - 1, 0, NUM_COLUMNAS * TAMANO_PIXEL - 1, HEIGHT, Color.DARK_GRAY);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Metodos Graficos"> 
    public void fillRectangle(int x0, int y0, int x1, int y1, Color color) {
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

    public void drawRectangle(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color);
        drawLine(x0, y0, x0, y1, color);
        drawLine(x1, y0, x1, y1, color);
        drawLine(x0, y1, x1, y1, color);
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        int pasos = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncremento = (float) dx / pasos;
        float yIncremento = (float) dy / pasos;

        float x = x0;
        float y = y0;

        putPixel(Math.round(x), Math.round(y), color);
        for (int i = 0; i <= pasos; i++) {
            x += xIncremento;
            y += yIncremento;
            putPixel(Math.round(x), Math.round(y), color);
        }
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }
    // </editor-fold> 

    public void guardarMatrizComoArchivo(String nombreArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {

            escritor.write("Color y coordenadas -> " + nombreArchivo + ".txt\n\n");
            escritor.write("Dimensiones:\n" + pixeles.length + "," + pixeles[0].length + "\n\n");

            for (int i = 0; i < NUM_FILAS; i++) {
                for (int j = 0; j < NUM_COLUMNAS; j++) {
                    Color color = pixeles[i][j];
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    int a = color.getAlpha();
                    escritor.write("Color (RGB):\n" + r + "," + g + "," + b + "," + a + "\n");
                    escritor.write("Coordenada:\n" + i + "," + j + "\n\n");
                }
            }
            System.out.println("Matriz guardada correctamente en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarMatrizDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String lineaEscaneada;
            Color colorPixel = null;
            listaColores = new LinkedList<>();

            while ((lineaEscaneada = lector.readLine()) != null) {
                if (lineaEscaneada.equals("Dimensiones:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int filas = Integer.parseInt(partes[0]);
                    int columnas = Integer.parseInt(partes[1]);

                    pixeles = new Color[filas][columnas];
                    equilibrarTamanoPixeles(filas, columnas);

                    this.getParent().repaint();
                    this.getParent().revalidate();

                }
                if (lineaEscaneada.equals("Color (RGB):")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int r = Integer.parseInt(partes[0]);
                    int g = Integer.parseInt(partes[1]);
                    int b = Integer.parseInt(partes[2]);
                    int a = Integer.parseInt(partes[3]);

                    colorPixel = new Color(r, g, b, a);
                    if (!(listaColores.contains(colorPixel))) {
                        listaColores.add(colorPixel);
                    }
                }
                if (lineaEscaneada.equals("Coordenada:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int filaArchivo = Integer.parseInt(partes[0]);
                    int columnaArchivo = Integer.parseInt(partes[1]);
                    pixeles[filaArchivo][columnaArchivo] = colorPixel;
                }
            }
            repaint();
            System.out.println("Matriz cargada correctamente desde: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFuncionRelleno() {
        return funcionRelleno;
    }

    public LinkedList<Color> getListaColores() {
        return listaColores;
    }

    public void setFuncionRelleno(boolean funcionRelleno) {
        this.funcionRelleno = funcionRelleno;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

}
