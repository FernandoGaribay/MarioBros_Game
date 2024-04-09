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
import object.GameObject;
import object.ArbustoChico;
import object.ArbustoGrande;
import object.ArbustoMediano;
import object.BanderaMastil;
import object.BloqueMoneda;
import object.BloqueBandera;
import object.BloquePiso;
import object.Castillo;
import object.Ladrillo;
import object.MontanaChica;
import object.MontanaGrande;
import object.NubeChica;
import object.NubeGrande;
import object.NubeMediana;
import object.TuberiaCabeza;
import object.Tuberia;
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
    private Camara cam;

    public Game() {
        inicializarElementos();
    }

    private void inicializarElementos() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        Player player = new Player(32 * 1, 32, 1, handler);
        handler.addObj(new Background(0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, player));

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

        // Barrera para no caer al vacio
        for (int i = 0; i < 500; i++) {
            handler.addObj(new Barrera(i * 32, 32 * 16, 32, 32, 1));
        }

        // <editor-fold defaultstate="colapsed" desc="Nivel Temporal Test"> 
//        for (int i = 0; i < 20; i++) {
//            handler.addObj(new BloqueMoneda(i * 32, 32 * 10, 32, 32, 1));
//        }
//        for (int i = 0; i < 55; i++) {
//            handler.addObj(new BloquePiso(i * 32, 32 * 15, 32, 32, 1));
//        }
//        handler.addObj(new BloquePiso(32 * 25, 32 * 14, 32, 32, 1));
//        handler.addObj(new BloquePiso(32 * 25, 32 * 13, 32, 32, 1));
//        handler.addObj(new BloquePiso(32 * 25, 32 * 12, 32, 32, 1));
//        handler.addObj(new BloquePiso(32 * 25, 32 * 11, 32, 32, 1));
//        handler.addObj(new BloquePiso(32 * 25, 32 * 10, 32, 32, 1));
//        handler.addObj(new BloquePiso(32 * 25, 32 * 9, 32, 32, 1));
//
//        // bloques test
//        handler.addObj(new TuberiaCabeza(32 * 29, 32 * 13, 64, 32, 1, true));
//        handler.addObj(new Tuberia(32 * 29, 32 * 14, 64, 32, 1));
//        handler.addObj(new BloqueBandera(32 * 34, 32 * 14, 32, 32, 1));
//        handler.addObj(new BanderaMastil(32 * 34, 32 * 13 - 16 * 14, 32, 256, 1));
//        handler.addObj(new BloqueMoneda(32 * 29, 32 * 5, 32, 32, 1));
//        handler.addObj(new ArbustoChico(32 * 17, 32 * 14, 64, 32, 1));
//        handler.addObj(new ArbustoMediano(32 * 20, 32 * 14, 96, 32, 1));
//        handler.addObj(new ArbustoGrande(32 * 0, 32 * 14, 128, 32, 1));
//        handler.addObj(new NubeChica(32 * 10, 32 * 5, 64, 32, 1));
//        handler.addObj(new NubeMediana(32 * 15, 32 * 2, 96, 32, 1));
//        handler.addObj(new NubeGrande(32 * 5, 32 * 3, 128, 32, 1));
//        handler.addObj(new MontanaGrande(32 * 10, 32 * 13, 160, 64, 1));
//        handler.addObj(new MontanaChica(32 * 6, 32 * 14, 96, 32, 1));
//        handler.addObj(new Castillo(32 * 38, 32 * 10, 160, 160, 1));
        //</editor-fold>
        handler.setPlayer(player);
        cam = new Camara(0, SCREEN_OFFSET);
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
        cam.tick(handler.getPlayer());
    }

    private void render() {
        BufferStrategy buf = this.getBufferStrategy();
        if (buf == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buf.getDrawGraphics();
        g2.limpiarBuffer();

        g2.translate(cam.getX(), cam.getY());
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
