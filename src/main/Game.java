package main;

import graficos.Background;
import graficos.Camara;
import graficos.LibreriaGrafica;
import java.awt.Canvas;
import graficos.Ventana;
import utils.CasillaNivel;
import utils.EscritorLector_Niveles;
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
    private static final double NUM_FRAMES = 75.0;
    private static final String NOMBRE = "Super Mario Bros";

    private static final int SCREEN_OFFSET = 32 * 1; // 1 bloque de offset
    private static final int VENTANA_WIDTH = 960;
    private static final int VENTANA_HEIGHT = 480 + SCREEN_OFFSET; // 32 x 15 = 480 (15 bloques de 16x16 de alto)
    private static final int SCREEN_WIDTH = VENTANA_WIDTH;
    private static final int SCREEN_HEIGHT = VENTANA_HEIGHT;
    private static LibreriaGrafica g2 = new LibreriaGrafica(VENTANA_WIDTH, VENTANA_HEIGHT);

    // VARIABLES
    private boolean running;

    // GAME COMPONENTS
    private Thread hilo;
    private Handler handler;
    private Ventana ventana;
    private Camara camara;
    private LoadScreen loadScreen;

    public Game() {
        inicializarElementos();
    }

    // <editor-fold defaultstate="collapsed" desc="Inizializar Elementos">  
    private void inicializarElementos() {
        this.addKeyListener(new KeyInput(handler));

        handler = new Handler();
        ventana = new Ventana(VENTANA_WIDTH, VENTANA_HEIGHT, NOMBRE);
        camara = new Camara(0, SCREEN_OFFSET);
        loadScreen = new LoadScreen(SCREEN_WIDTH, SCREEN_HEIGHT);

        ventana.setCanvas(loadScreen);
        handler.addObj(new Background(0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, SCREEN_OFFSET, camara));

        cargarNivel("NivelesFiles/mundo_1-1");
        cargarBarreras();

        handler.setPlayer(new Player(32 * 1, 32, 1, handler));

        try {
            // Esperar a que el hilo finalize
            loadScreen.getHilo().join();
            loadScreen = null;

            ventana.setCanvas(this);
            gameLoopInicio();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarBarreras() {
        // Barreras para no salir del mapa
        for (int i = 0; i < 500; i++) {
            handler.addObj(new Barrera(i * 32, 32 * 16, 32, 32, 1));
        }
        for (int i = 0; i < 15; i++) {
            handler.addObj(new Barrera(-32, 32 * i, 32, 32, 1));
        }
    }

    public void cargarNivel(String nombreArchivo) {
        CasillaNivel[][] matrizNivel;
        matrizNivel = EscritorLector_Niveles.cargarMatrizDesdeArchivo(nombreArchivo);

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
    }
    // </editor-fold>  

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
        double ticksDeseados = NUM_TICKS; // representa cuantas veces se actualiza el juego por segundo
        double framesDeseados = NUM_FRAMES; // representa cuantas veces se actualiza el juego por segundo

        double nsTicks = NANOS_POR_SEGUNDO / ticksDeseados; // cuantos nanosegundos deben pasar entre cada actualizacion para alcanzar amountOfTicks por segundo
        double nsFrames = NANOS_POR_SEGUNDO / framesDeseados; // cuantos nanosegundos deben pasar entre cada frames para alcanzar amountOfTicks por segundo

        double deltaTicks = 0; // controla cuando se debe ejecutar el metodo tick()
        double deltaFrames = 0; // controla cuando se debe ejecutar el metodo tick()

        int frames = 0; // estadisticas de FPS (cuadros por segundo) y TPS (actualizaciones por segundo).
        int updates = 0; // estadisticas de FPS (cuadros por segundo) y TPS (actualizaciones por segundo).

        long lastTime = System.nanoTime(); // calcular cuanto tiempo ha pasado desde la ultima actualizacion del juego
        long timer = System.currentTimeMillis(); // controla cuando imprimir FPS y TPS y restablecer el contador de updates) y frames

        while (running) {
            long now = System.nanoTime();
            deltaTicks += (now - lastTime) / nsTicks;
            deltaFrames += (now - lastTime) / nsFrames;
            lastTime = now;

            while (deltaTicks >= 1) {
                tick();
                updates++;
                deltaTicks--;
            }
            while (deltaFrames >= 1) {
                render();
                frames++;
                deltaFrames--;
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
