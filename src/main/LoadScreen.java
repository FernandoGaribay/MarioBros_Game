package main;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadScreen extends Canvas implements Runnable {

    // Constantes
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
        g2.drawImage(Texturas.getMarioDerecha_S(), 150, 150);

        // Dibujar buffer de mi libreria grafica
        g.drawImage(g2.getBuffer(), 0, 0, null);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        while (end - start < 0) {
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
