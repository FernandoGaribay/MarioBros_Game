package main;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadScreen extends Canvas implements Runnable {

    // Constantes
    private final int TIMEPO_CARGA = 50050;
    private final int WIDTH;
    private final int HEIGHT;

    private LibreriaGrafica g2;
    private Thread hilo;

    public LoadScreen(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.WIDTH = SCREEN_WIDTH;
        this.HEIGHT = SCREEN_HEIGHT;

        g2 = new LibreriaGrafica(WIDTH, HEIGHT);
        hilo = new Thread(this);
        hilo.start();

    }

    @Override
    public void paint(Graphics g) {
        g2.fillRect(0, 0, WIDTH, HEIGHT, Color.BLACK);
        
        int cuartoDePantalla = WIDTH / 5;
        g2.drawText("mario", cuartoDePantalla * 1, 50, 1);
        g2.drawText("000000", cuartoDePantalla * 1, 80, 1);
        g2.drawText("x00", cuartoDePantalla * 2, 65, 1);
        g2.drawText("world", cuartoDePantalla * 3, 50, 1);
        g2.drawText("1-1", cuartoDePantalla * 3, 80, 1);
        g2.drawText("time", cuartoDePantalla * 4, 50, 1);
        g2.drawText("000000", cuartoDePantalla * 4, 80, 1);
        
        g2.drawText("world 1-1", WIDTH / 4 * 2, 200, 1);
        
        int medioDePantalla = WIDTH / 2;
        g2.drawText("x 3", medioDePantalla + 25 - 5, 265, 1);
        g2.drawImage(Texturas.getMarioTextura("S_mario"), medioDePantalla - (32 / 2) - 30 - 5, 250);
        
        // Dibujar buffer de mi libreria grafica
        g.drawImage(g2.getBuffer(), 0, 0, null);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        while (end - start < TIMEPO_CARGA) {
            try {
                hilo.sleep(500);

                System.out.println("loadscreen");
                
                end = System.currentTimeMillis();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Thread getHilo() {
        return hilo;
    }

}
