package main;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOverScreen extends Canvas implements Runnable {

    // Constantes
    private final int TIMEPO_CARGA = 2500;
    private final int WIDTH;
    private final int HEIGHT;

    private LibreriaGrafica g2;
    private static Thread hilo;
    boolean running = true;

    public GameOverScreen(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.WIDTH = SCREEN_WIDTH;
        this.HEIGHT = SCREEN_HEIGHT;

        g2 = new LibreriaGrafica(WIDTH, HEIGHT);
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void paint(Graphics g) {
        g2.fillRect(0, 0, WIDTH, HEIGHT, Color.BLACK);

        int mitadDeAncho = WIDTH / 2;
        int mitadDeAlto = HEIGHT / 2;
        g2.drawText("game over", mitadDeAncho, mitadDeAlto, 2);

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

                System.out.println("GameOverScreen en ejecucion");

                end = System.currentTimeMillis();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        running = false;
    }

    public static Thread getHilo() {
        return hilo;
    }

    public boolean isRunning() {
        return running;
    }

}
