package object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Player;

public class KeyInput extends KeyAdapter {

    private boolean[] keyDown = new boolean[4];
    private Player player;

    private static final float ACELERACION = 1f;
    private static final int ACELERACION_DELAY = 50;
    private static final int ACELERACION_LIMITE = 4;
    private Thread hiloAceleracion;

    public KeyInput(Player player, Handler handler) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.MOUSE_EVENT_MASK) {
            System.exit(0);
        }

        // SALTO
        if (key == KeyEvent.VK_W) {
            if (!player.hasJumped()) {
                player.setVelY(-12);
                keyDown[0] = true;
                player.setJumped(true);
            }
        }

        // IZQUIERDA
        if (key == KeyEvent.VK_A) {
            if (!keyDown[1]) {
                player.setAdelante(false);
                keyDown[1] = true;
                aceleracionIzquierda();
            }
        }

        // DERECHA
        if (key == KeyEvent.VK_D) {
            if (!keyDown[2]) {
                player.setAdelante(true);
                keyDown[2] = true;
                aceleracionDerecha();
            }
        }
    }

    private void aceleracionDerecha() {
        if (player.getVelX() < 4) {
            hiloAceleracion = new Thread(() -> {
                while (player.getVelX() < 4 && keyDown[2]) {
                    delay();

                    player.setVelX(player.getVelX() + ACELERACION);
                    if (player.getVelX() > 4) {
                        player.setVelX(4);
                    }
                }
            });
            hiloAceleracion.start();
            System.out.println("derecha - " + player.getVelX());
        }
    }

    private void aceleracionIzquierda() {
        if (player.getVelX() > -4) {
            hiloAceleracion = new Thread(() -> {
                while (player.getVelX() > -4 && keyDown[1]) {
                    delay();

                    player.setVelX(player.getVelX() - ACELERACION);
                    if (player.getVelX() < -4) {
                        player.setVelX(-4);
                    }
                }
            });
            hiloAceleracion.start();
            System.out.println("izquierda - " + player.getVelX());
        }
    }

    private void aceleracionNula() {
        if (player.getVelX() != 0) {
            hiloAceleracion = new Thread(() -> {
                while (player.getVelX() != 0) {
                    delay();

                    if (player.getVelX() > 0) { // Si la velocidad es positiva, disminuye
                        player.setVelX(Math.max(player.getVelX() - ACELERACION, 0));
                    } else if (player.getVelX() < 0) { // Si la velocidad es negativa, aumenta
                        player.setVelX(Math.min(player.getVelX() + ACELERACION, 0));
                    }
                }
            });
            hiloAceleracion.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            keyDown[0] = false;
        }

        if (key == KeyEvent.VK_A) {
            keyDown[1] = false;
        }

        if (key == KeyEvent.VK_D) {
            keyDown[2] = false;
        }

        if (!keyDown[1] && !keyDown[2]) {
            aceleracionNula();
        }
    }

    private void delay() {
        try {
            Thread.sleep(ACELERACION_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

//    private void aceleracionDireccion(int direccion) {
//        int limiteVelocidad = (direccion == 1) ? ACELERACION_LIMITE : -ACELERACION_LIMITE;
//        boolean teclaPrecionada = (direccion == 1) ? keyDown[2] : keyDown[1];
//        int multiplicador = (direccion == 1) ? 1 : -1;
//
//        if ((player.getVelX() < limiteVelocidad)
//                || (player.getVelX() > limiteVelocidad)) {
//
//            hiloAceleracion = new Thread(() -> {
//                while ((direccion == 1 && player.getVelX() < limiteVelocidad && teclaPrecionada)
//                        || (direccion == -1 && player.getVelX() > limiteVelocidad && teclaPrecionada)) {
//
//                    try {
//                        Thread.sleep(ACELERACION_DELAY);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    player.setVelX(player.getVelX() + multiplicador * ACELERACION);
//
//                    if ((direccion == 1 && player.getVelX() > limiteVelocidad)
//                            || (direccion == -1 && player.getVelX() < limiteVelocidad)) {
//                        player.setVelX(limiteVelocidad);
//                    }
//                    System.out.println(player.getVelX() + "avanzando");
//                }
//            });
//            hiloAceleracion.start();
//        }
//    }
//    private void aceleracionDerecha() {
//        if (player.getVelX() < 4) {
//            hiloAceleracion = new Thread(() -> {
//                while (player.getVelX() < 4 && keyDown[2]) {
//                    try {
//                        Thread.sleep(ACELERACION_DELAY);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    player.setVelX(player.getVelX() + ACELERACION);
//                    if (player.getVelX() > 4) {
//                        player.setVelX(4);
//                    }
////                    System.out.println("derecha");
//                }
//            });
//            hiloAceleracion.start();
//        }
//    }
//    private void aceleracionIzquierda() {
//        if (player.getVelX() > -4) {
//            hiloAceleracion = new Thread(() -> {
//                while (player.getVelX() > -4 && keyDown[1]) {
//                    try {
//                        Thread.sleep(ACELERACION_DELAY);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    player.setVelX(player.getVelX() - ACELERACION);
//                    if (player.getVelX() < -4) {
//                        player.setVelX(-4);
//                    }
////                    System.out.println("izquierda");
//                }
//            });
//            hiloAceleracion.start();
//        }
//    }
