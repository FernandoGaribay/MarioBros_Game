package main;

import graficos.Background;
import graficos.Camara;
import graficos.LibreriaGrafica;
import java.awt.Canvas;
import graficos.Ventana;
import herramientaLevelCreator.CasillaNivel;
import herramientaLevelCreator.EscritorLector_Niveles;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Barrera;
import object.util.GameObject;
import object.Player;
import object.util.Handler;
import object.util.KeyInput;
import object.util.ObjectFactory;

public class Game extends Canvas implements Runnable {

    // CONSTANTES
    private static final int MILLIS_POR_SEGUNDO = 1000;
    private static final int NANOS_POR_SEGUNDO = 1000000000;
    private static final double NUM_TICKS = 60.0;
    private static final String NAME = "Super Mario Bros";

    private static final int SCREEN_OFFSET = 32 * 1; // 1 bloque de offset
    private static final int VENTANA_WIDTH = 960;
    private static final int VENTANA_HEIGHT = 480 + SCREEN_OFFSET; // 32 x 15 = 480 (15 bloques de 16x16 de alto)
    private static final int SCREEN_WIDTH = VENTANA_WIDTH - 67;
    private static final int SCREEN_HEIGHT = VENTANA_HEIGHT;
    private static LibreriaGrafica g2 = new LibreriaGrafica(VENTANA_WIDTH, VENTANA_HEIGHT);

    // VARIABLES
    private boolean running;

    // GAME COMPONENTS
    private Thread hilo;
    private Handler handler;
    private Camara camara;

    public Game() {
        inicializarElementos();
    }

    private void inicializarElementos() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        camara = new Camara(0, SCREEN_OFFSET);
        handler.addObj(new Background(0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, SCREEN_OFFSET, camara));

        CasillaNivel[][] matrizNivel;
        matrizNivel = EscritorLector_Niveles.cargarMatrizDesdeArchivo("mundo_1-1");

        for (CasillaNivel[] casillaNivels : matrizNivel) {
            for (CasillaNivel casillaNivel : casillaNivels) {
                if (!casillaNivel.getNombreElemento().isEmpty()) {
                    GameObject obj = ObjectFactory.createObject(casillaNivel.getNombreElemento());
                    obj.setX(casillaNivel.getX() * 32);
                    obj.setY(casillaNivel.getY() * 32);
                    handler.addObj(obj);
                }
            }
        }

        // Barreras para no salir del mapa
        for (int i = 0; i < 500; i++) {
            handler.addObj(new Barrera(i * 32, 32 * 16, 32, 32, 1));
        }
        for (int i = 0; i < 15; i++) {
            handler.addObj(new Barrera(-32, 32 * i, 32, 32, 1));
        }

        handler.setPlayer(new Player(32 * 1, 32, 1, handler));
        new Ventana(VENTANA_WIDTH, VENTANA_HEIGHT, NAME, this);

        gameLoopInicio();
    }

    private synchronized void gameLoopInicio() {
        this.hilo = new Thread(this);
        this.hilo.start();
        this.running = true;
    }

    private synchronized void gameLoopParar() {
        try {
            this.hilo.join();
            this.running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.running = false;
    }

    @Override
    public void run() {
        double ticksDeseados = NUM_TICKS; // representa cuantas veces queremos actualizar el juego por segundo
        double ns = NANOS_POR_SEGUNDO / ticksDeseados; // cuantos nanosegundos deben pasar entre cada actualizacion para alcanzar amountOfTicks por segundo
        double delta = 0; // controla cuando se debe ejecutar el metodo tick()

        int frames = 0; // estadisticas de FPS (cuadros por segundo) y TPS (actualizaciones por segundo).
        int updates = 0; // estadisticas de FPS (cuadros por segundo) y TPS (actualizaciones por segundo).

        long lastTime = System.nanoTime(); // calcular cuanto tiempo ha pasado desde la ultima actualizacion del juego
        long timer = System.currentTimeMillis(); // controla cuando imprimir FPS y TPS y restablecer el contador de updates) y frames

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if (System.currentTimeMillis() - timer > MILLIS_POR_SEGUNDO) {
                timer += MILLIS_POR_SEGUNDO;
                System.out.println("FPS: " + frames + " TPS: " + updates);
                updates = 0;
                frames = 0;
            }
        }
        gameLoopParar();
    }

    private void tick() {
        handler.tick();
        camara.tick(handler.getPlayer());
    }

    private void render() {
        BufferStrategy buf = this.getBufferStrategy();
        if (buf == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buf.getDrawGraphics();
        g2.limpiarBuffer();

        g2.translate(camara.getX(), camara.getY());
        handler.render(g2);

        g.drawImage(g2.getBuffer(), 0, 0, null);
        g.dispose();

        buf.show();
    }

    public static int getWindowHeight() {
        return VENTANA_HEIGHT;
    }

    public static int getWindowWidth() {
        return VENTANA_WIDTH;
    }

    public static int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public static int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

}
