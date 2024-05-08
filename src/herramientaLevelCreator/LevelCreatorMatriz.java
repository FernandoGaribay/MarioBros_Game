package herramientaLevelCreator;

import utils.CasillaNivel;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LevelCreatorMatriz extends JPanel implements InterfazLevelCreator {

    // CONSTANTES
    private static final int TAMANO_CELDA = 32;

    // OBJETOS
    private static LibreriaGrafica g2Cuadriculado;
    private static LibreriaGrafica g2Elementos;
    private static CasillaNivel[][] matrizElementos;

    // VARIBLES
    private static int NUM_FILAS;
    private static int NUM_COLUMNAS;
    private static float velocidad;
    private static float zoom;
    private static boolean moviendo; // true mover, false pintar
    private static boolean borrando;
    private static String elementoSelecionado;

    // COORDENADAS      
    private static Point ultimaPosicion;
    private static Point offsetPosicion;

    public LevelCreatorMatriz() {
        setBackground(new Color(203, 203, 203));

        SwingUtilities.invokeLater(() -> {
            g2Cuadriculado = new LibreriaGrafica(getWidth(), getHeight());
            g2Elementos = new LibreriaGrafica(getWidth(), getHeight());
            velocidad = 1f;
            zoom = 1f;
            moviendo = false;
            borrando = false;
            ultimaPosicion = new Point(0, 0);
            offsetPosicion = new Point();
            elementoSelecionado = "bloquePiso";

            limpiarMatriz(500, 15);
            initEventos();
        });
    }

    private void initEventos() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // MOVIENDO 
                if (moviendo) {
                    // Guardar las coordenadas del clic
                    offsetPosicion.x = e.getX();
                    offsetPosicion.y = e.getY();
                }

                // PINTANDO
                if (!moviendo) {
                    int NEW_TAMANO_CELDA = (int) (TAMANO_CELDA * zoom);
                    int xClick = (int) ((e.getX() - ultimaPosicion.x) / NEW_TAMANO_CELDA);
                    int yClick = (int) ((e.getY() - ultimaPosicion.y) / NEW_TAMANO_CELDA);

                    // Verificar si el click esta a dentro de la matriz
                    if (!(xClick >= 0 && yClick >= 0 && xClick < NUM_COLUMNAS && yClick < NUM_FILAS)) {
                        return;
                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        borrando = true;
                    }
                    actualizarPixel(xClick, yClick);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    borrando = false;
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // MOVIENDO
                if (moviendo) {
                    // Calcular el desplazamiento, posicion actual menos la anterior
                    int dx = (int) ((e.getX() - offsetPosicion.x) * velocidad);
                    int dy = (int) ((e.getY() - offsetPosicion.y) * velocidad);

                    // Actualizar la posicion anterior
                    offsetPosicion.x = e.getX();
                    offsetPosicion.y = e.getY();

                    // Actualizar el desplazamiento de la matriz
                    ultimaPosicion.x += dx;
                    ultimaPosicion.y += dy;
                }

                // PINTANDO
                if (!moviendo) {
                    int NEW_TAMANO_CELDA = (int) (TAMANO_CELDA * zoom);
                    int xClick = (int) ((e.getX() - ultimaPosicion.x) / NEW_TAMANO_CELDA);
                    int yClick = (int) ((e.getY() - ultimaPosicion.y) / NEW_TAMANO_CELDA);

                    // Verificar si el click esta a dentro de la matriz
                    if (!(xClick >= 0 && yClick >= 0 && xClick < NUM_COLUMNAS && yClick < NUM_FILAS)) {
                        return;
                    }

                    if (e.getButton() == MouseEvent.BUTTON3) {
                        borrando = true;
                    }
                    actualizarPixel(xClick, yClick);
                }
                repaint();
            }

        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    zoom += 0.1f;
                    if (zoom >= 2f) {
                        zoom = 2f;
                    }
                } else {
                    zoom -= 0.1f;
                    if (zoom <= 0.4f) {
                        zoom = 0.4f;
                    }
                }
                repaint();
            }
        });
    }

    @Override
    public void elementoSeleccionado(String elemento) {
        this.elementoSelecionado = elemento;
        System.out.println("Elemento seleccionado: " + elementoSelecionado);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dibujar la matriz cuadriculado --------------------------------------
        g2Cuadriculado.limpiarBuffer();
        g2Elementos.limpiarBuffer();
        for (int i = 0; i < matrizElementos.length; i++) {
            for (int j = 0; j < matrizElementos[0].length; j++) {
                int NEW_TAMANO_CELDA = (int) (TAMANO_CELDA * zoom);
                int x = i * NEW_TAMANO_CELDA + ultimaPosicion.x; // Coordenada X con desplazamiento
                int y = j * NEW_TAMANO_CELDA + ultimaPosicion.y; // Coordenada Y con desplazamiento

                // Verificar si la celda esta dentro del area visible
                if (x + NEW_TAMANO_CELDA > 0 && y + NEW_TAMANO_CELDA > 0 && x < getWidth() && y < getHeight()) {
                    g2Cuadriculado.fillRect(x, y, x + NEW_TAMANO_CELDA, y + NEW_TAMANO_CELDA, Color.WHITE);
                    g2Cuadriculado.drawRect(x, y, x + NEW_TAMANO_CELDA, y + NEW_TAMANO_CELDA, Color.BLACK);

                    // Verificar que el elemento de la matriz no es nulo
                    if (matrizElementos[i][j] != null && matrizElementos[i][j].getImagenElemento() != null) {
                        int newWidth = (int) (matrizElementos[i][j].getImagenElemento().getWidth() * zoom);
                        int newHeight = (int) (matrizElementos[i][j].getImagenElemento().getHeight() * zoom);
                        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, matrizElementos[i][j].getImagenElemento().getType());

                        Graphics2D g2d = resizedImage.createGraphics();
                        g2d.drawImage(matrizElementos[i][j].getImagenElemento(), 0, 0, newWidth, newHeight, null);
                        g2d.dispose();

                        g2Elementos.drawImage(resizedImage, x, y);
                    }
                }
            }
        }

        g.drawImage(g2Cuadriculado.getBuffer(), 0, 0, null);
        g.drawImage(g2Elementos.getBuffer(), 0, 0, null);
    }

    private void actualizarPixel(int xClick, int yClick) {
        if (!borrando) {
            matrizElementos[xClick][yClick].setImagenElemento(Texturas.getTextura(elementoSelecionado));
            matrizElementos[xClick][yClick].setNombreElemento(elementoSelecionado);
            matrizElementos[xClick][yClick].setCoordenadas(xClick, yClick);
        } else {
            matrizElementos[xClick][yClick] = new CasillaNivel();
        }
    }

    public void limpiarMatriz(int columnas, int filas) {
        this.NUM_FILAS = filas;
        this.NUM_COLUMNAS = columnas;

        matrizElementos = new CasillaNivel[NUM_COLUMNAS][NUM_FILAS];
        for (int i = 0; i < matrizElementos.length; i++) {
            for (int j = 0; j < matrizElementos[0].length; j++) {
                matrizElementos[i][j] = new CasillaNivel();
            }
        }
        repaint();
    }

    public void addColumna() {
        CasillaNivel[][] nuevaMatriz = new CasillaNivel[matrizElementos.length + 1][matrizElementos[0].length];

        for (int i = 0; i < matrizElementos.length; i++) {
            nuevaMatriz[i] = matrizElementos[i];
        }
        for (int j = 0; j < nuevaMatriz[0].length; j++) {
            nuevaMatriz[matrizElementos.length][j] = new CasillaNivel();
        }
        matrizElementos = nuevaMatriz;
        NUM_COLUMNAS = nuevaMatriz.length;
        repaint();
    }

    public void removeColumna() {
        CasillaNivel[][] nuevaMatriz = new CasillaNivel[matrizElementos.length - 1][matrizElementos[0].length];

        if (matrizElementos.length <= 1) {
            return;
        }

        for (int i = 0; i < nuevaMatriz.length; i++) {
            nuevaMatriz[i] = matrizElementos[i];
        }

        matrizElementos = nuevaMatriz;
        NUM_COLUMNAS = nuevaMatriz.length;
        repaint();
    }

    public void addFila() {
        CasillaNivel[][] nuevaMatriz = new CasillaNivel[matrizElementos.length][matrizElementos[0].length + 1];

        for (int i = 0; i < matrizElementos.length; i++) {
            for (int j = 0; j < matrizElementos[i].length; j++) {
                nuevaMatriz[i][j] = matrizElementos[i][j];
            }
        }

        for (int i = 0; i < nuevaMatriz.length; i++) {
            nuevaMatriz[i][matrizElementos[0].length] = new CasillaNivel();
        }

        matrizElementos = nuevaMatriz;
        NUM_FILAS = nuevaMatriz[0].length;
        repaint();
    }

    public void removeFila() {
        CasillaNivel[][] nuevaMatriz = new CasillaNivel[matrizElementos.length][matrizElementos[0].length - 1];

        if (matrizElementos[0].length <= 1) {
            return;
        }

        for (int i = 0; i < nuevaMatriz.length; i++) {
            for (int j = 0; j < nuevaMatriz[i].length; j++) {
                nuevaMatriz[i][j] = matrizElementos[i][j];
            }
        }

        matrizElementos = nuevaMatriz;
        NUM_FILAS = nuevaMatriz[0].length;
        repaint();
    }

    public void recorrerMatriz(String direccion) {
        int columnas = matrizElementos.length;
        int filas = matrizElementos[0].length;

        CasillaNivel[][] nuevaMatriz = new CasillaNivel[columnas][filas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                switch (direccion) {
                    case "arriba":
                        if (j > 0) {
                            nuevaMatriz[i][j - 1] = matrizElementos[i][j];
                        }
                        break;
                    case "abajo":
                        if (j < filas - 1) {
                            nuevaMatriz[i][j + 1] = matrizElementos[i][j];
                        }
                        break;
                    case "izquierda":
                        if (i > 0) {
                            nuevaMatriz[i - 1][j] = matrizElementos[i][j];
                        }
                        break;
                    case "derecha":
                        if (i < columnas - 1) {
                            nuevaMatriz[i + 1][j] = matrizElementos[i][j];
                        }
                        break;
                }
            }
        }
        matrizElementos = nuevaMatriz;
        repaint();
    }

    public void centrar() {
        ultimaPosicion.x = 0;
        ultimaPosicion.y = 0;
        zoom = 1f;
        repaint();
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public boolean isMoviendo() {
        return moviendo;
    }

    public void setMoviendo(boolean mover_pintar) {
        this.moviendo = mover_pintar;
    }

    public CasillaNivel[][] getMatrizElementos() {
        return matrizElementos;
    }

    public void setMatrizElementos(CasillaNivel[][] matrizElementos) {
        this.matrizElementos = matrizElementos;
        this.NUM_COLUMNAS = matrizElementos.length;
        this.NUM_FILAS = matrizElementos[0].length;
        repaint();
    }

}
