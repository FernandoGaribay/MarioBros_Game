package main;

import graficos.Background;
import graficos.Camara;
import graficos.LibreriaGrafica;
import java.awt.Canvas;
import graficos.Ventana;
import java.util.List;
import java.awt.Color;
import utils.CasillaNivel;
import utils.LevelReaderWritter;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.GameOverScreen;
import main.LoadScreen;
import object.bloques.BloqueBarrera;
import object.util.GameObjeto;
import object.player.Player;
import object.util.GameEntidad;
import object.util.HandlerEntidades;
import object.util.HandlerBloques;
import object.player.KeyInput;
import object.ObjectFactory;
import object.util.HandlerSonidos;
import utils.ReproductorMP3;

public class Game extends Canvas implements Runnable {

    // CONSTANTES
    private static final int NANOS_POR_SEGUNDO = 1000000000;
    private static final double NUM_FPS = 60.0;
    private static final String NOMBRE = "Super Mario Bros";

    private static final int VENTANA_WIDTH = 800;
    private static final int MAX_RENDERIZADO = 800;
    private static final int SCREEN_OFFSET = 32 * 1; // 1 bloque de offset
    private static final int VENTANA_HEIGHT = 480 + SCREEN_OFFSET; // 32 x 15 = 480 (15 bloques de 16x16 de alto)
    private static final int SCREEN_WIDTH = VENTANA_WIDTH;
    private static final int SCREEN_HEIGHT = VENTANA_HEIGHT;
    public static final boolean SONIDO = true;
    private static LibreriaGrafica g2 = new LibreriaGrafica(VENTANA_WIDTH, VENTANA_HEIGHT);

    // VARIABLES
    public static HashMap<List<Integer>, List<Integer>> ENTRADAS_SALIDAS = new HashMap<>();
    private boolean running;
    private boolean gameover;
    private int frames = 0;
    private int updates = 0;

    // GAME COMPONENTES
    private static HandlerBloques handlerBloques;
    private static HandlerEntidades handlerEntidades;
    private Thread hiloPrincipal;
    private Thread updateThread;
    private Thread renderThread;
    private Thread playerThread;
    private Ventana ventana;
    private Camara camara;
    private Player player;
    private Background background;
    private KeyInput keyInput;
    private LoadScreen loadScreen;
    private GameOverScreen gameOverScreen;

    public Game() {
        inicializarElementos();
        this.requestFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Inizializar Elementos">  
    private void inicializarElementos() {
        handlerBloques = new HandlerBloques();
        handlerEntidades = new HandlerEntidades();
        ventana = new Ventana(VENTANA_WIDTH, VENTANA_HEIGHT, NOMBRE);
        camara = new Camara(0, SCREEN_OFFSET);
        player = new Player(32 * 2, 32 * 12, handlerBloques, handlerEntidades);
        keyInput = new KeyInput(player);
        background = new Background(0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, SCREEN_OFFSET, camara);
        loadScreen = new LoadScreen(SCREEN_WIDTH, SCREEN_HEIGHT);

        this.addKeyListener(keyInput);

        ventana.setCanvas(loadScreen);
        handlerBloques.setPlayer(player);
        handlerEntidades.setPlayer(player);

        cargarNivel("NivelesFiles/mundo_1-1");
        cargarBarreras();

        try {
            // Esperar a que el hilo finalize
            loadScreen.getHilo().join();
            loadScreen = null;
            HandlerSonidos.playSound("SuperMarioBros_ThemeSong.wav");
            
            ventana.setCanvas(this);
            gameLoopInicio();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarBarreras() {
        // Barreras para no salir del mapa
        for (int i = 0; i < 500; i++) {
            handlerBloques.addObj(new BloqueBarrera(i * 32, 32 * 15, 32, 32, 1));
        }
        for (int i = 0; i < 15; i++) {
            handlerBloques.addObj(new BloqueBarrera(-32, 32 * i, 32, 32, 1));
        }
    }

    public void cargarNivel(String nombreArchivo) {
        CasillaNivel[][] matrizNivel;
        matrizNivel = LevelReaderWritter.cargarMatrizDesdeArchivo(nombreArchivo);
        List<Integer> entrada = null;
        List<Integer> salida = null;

        for (CasillaNivel[] casillaNivels : matrizNivel) {
            for (CasillaNivel casillaNivel : casillaNivels) {
                if (!casillaNivel.getNombreElemento().isEmpty()) {
                    String nombreElemento = casillaNivel.getNombreElemento();
                    GameObjeto objeto;
                    try {
                        if (nombreElemento.startsWith("bloque")) {

                            objeto = ObjectFactory.crearBloque(nombreElemento);
                            objeto.setX(casillaNivel.getX() * 32);
                            objeto.setY(casillaNivel.getY() * 32);
                            handlerBloques.addObj(objeto);

                        } else if (nombreElemento.startsWith("entidad")) {
                            GameEntidad obj = ObjectFactory.crearEntidad(nombreElemento);

                            obj.setX(casillaNivel.getX() * 32);
                            obj.setY(casillaNivel.getY() * 32);
                            handlerEntidades.addEntidad(obj);
                        } else if (nombreElemento.startsWith("entradaA")) {
                            // Guardar coordenada A
                            entrada = new ArrayList<>();
                            entrada.add(casillaNivel.getX());
                            entrada.add(casillaNivel.getY());

                            objeto = ObjectFactory.crearBloque("entradaA");
                            objeto.setX(casillaNivel.getX() * 32);
                            objeto.setY(casillaNivel.getY() * 32);
                            objeto.setWidth(nombreElemento.contains("Horizontal") ? 64 : objeto.getHeight());
                            objeto.setHeight(nombreElemento.contains("Vertical") ? 64 : objeto.getHeight());
                            handlerBloques.addObj(objeto);
                        } else if (nombreElemento.startsWith("salidaB")) {
                            // Guardar coordenada B
                            salida = new ArrayList<>();
                            salida.add(casillaNivel.getX());
                            salida.add(casillaNivel.getY());

                            objeto = ObjectFactory.crearBloque("salidaB");
                            objeto.setX(casillaNivel.getX() * 32);
                            objeto.setY(casillaNivel.getY() * 32);
                            objeto.setWidth(nombreElemento.contains("Horizontal") ? 64 : objeto.getHeight());
                            objeto.setHeight(nombreElemento.contains("Vertical") ? 64 : objeto.getHeight());
                            handlerBloques.addObj(objeto);
                            ENTRADAS_SALIDAS.put(entrada, salida);
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR CON: " + nombreElemento);
                    }
                }
            }
        }
        // Imprimir todas las entradas y salidas
        for (Map.Entry<List<Integer>, List<Integer>> entry : ENTRADAS_SALIDAS.entrySet()) {
            System.out.println("Entrada: " + entry.getKey() + " Salida: " + entry.getValue());
        }
    }
    // </editor-fold>  

    private synchronized void gameLoopInicio() {
        this.hiloPrincipal = new Thread(this);
        this.updateThread = new Thread(this::runUpdate);
        this.renderThread = new Thread(this::runRender);
        this.playerThread = new Thread(this::playerUpdate);

        this.running = true;
        this.gameover = false;
        hiloPrincipal.start();
    }

    private synchronized void gameLoopParar() {
        try {
            System.out.println("Apagando hilos");
            this.running = false;
            this.updateThread.join();
            this.renderThread.join();
            this.playerThread.join();
            System.out.println("Los hilos se pararon");
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        updateThread.start();
        renderThread.start();
        playerThread.start();

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            System.out.println("FPS: " + frames + " TPS: " + updates);
            updates = 0;
            frames = 0;

            if (!player.isIsVivo()) {
                gameLoopParar();
                gameOverScreen = new GameOverScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
                ventana.setCanvas(gameOverScreen);
                try {
                    System.out.println("Hilo GameOverScreen en proceso");
                    GameOverScreen.getHilo().join();
                    System.out.println("Hilo GameOverScreen finalizado");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!gameOverScreen.isRunning()) {
                    gameover = true;
                }
            }
        }
        System.out.println("GAME LOOP FINALIZADO");
    }

    private void runUpdate() {
        double nsTicks = NANOS_POR_SEGUNDO / NUM_FPS; // cuantos nanosegundos deben pasar entre cada actualizacion para alcanzar amountOfTicks por segundo

        double deltaTicks = 0; // controla cuando se debe ejecutar el metodo tick()

        long lastTime = System.nanoTime(); // calcular cuanto tiempo ha pasado desde la ultima actualizacion del juego

        while (running) {
            long now = System.nanoTime();
            deltaTicks += (now - lastTime) / nsTicks;
            lastTime = now;

            while (deltaTicks >= 1 && running) {
                objetosTick();
                background.tick();
                HandlerSonidos.tick();
                updates++;
                deltaTicks--;
            }
        }
    }

    private void runRender() {
        double nsFrames = NANOS_POR_SEGUNDO / NUM_FPS; // cuantos nanosegundos deben pasar entre cada frames para alcanzar amountOfTicks por segundo

        double deltaFrames = 0; // controla cuando se debe ejecutar el metodo render()

        long lastTime = System.nanoTime(); // calcular cuanto tiempo ha pasado desde la ultima renderización del juego

        while (running) {
            long now = System.nanoTime();
            deltaFrames += (now - lastTime) / nsFrames;
            lastTime = now;

            while (deltaFrames >= 1 && running) {
                render();
                frames++;
                deltaFrames--;
            }
        }
    }

    public void playerUpdate() {
        double nsTicks = NANOS_POR_SEGUNDO / NUM_FPS; // cuantos nanosegundos deben pasar entre cada actualizacion para alcanzar amountOfTicks por segundo

        double deltaTicks = 0; // controla cuando se debe ejecutar el metodo tick()

        long lastTime = System.nanoTime(); // calcular cuanto tiempo ha pasado desde la ultima actualizacion del juego

        while (running) {
            long now = System.nanoTime();
            deltaTicks += (now - lastTime) / nsTicks;
            lastTime = now;

            while (deltaTicks >= 1 && running) {
                playerTick();
                deltaTicks--;
            }
        }
    }

    private synchronized void objetosTick() {
        handlerBloques.tick();
        handlerEntidades.tick();
    }

    private synchronized void playerTick() {
        player.tick();
        camara.tick(player);
    }

    private synchronized void render() {
        BufferStrategy buf = this.getBufferStrategy();
        if (buf == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buf.getDrawGraphics();
        g2.limpiarBuffer();

        g2.translate(camara.getX(), camara.getY());

        background.render(g2);
        handlerBloques.render(g2);
        handlerEntidades.render(g2);
        player.render(g2);

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

    public static int getSCREEN_OFFSET() {
        return SCREEN_OFFSET;
    }

    public Thread getHiloPrincipal() {
        return hiloPrincipal;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public static int getMAX_RENDERIZADO() {
        return MAX_RENDERIZADO;
    }

    public static HandlerBloques getHandlerBloques() {
        return handlerBloques;
    }

    public static HandlerEntidades getHandlerEntidades() {
        return handlerEntidades;
    }

}
