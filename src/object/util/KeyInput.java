package object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Player;

public class KeyInput extends KeyAdapter {

    private boolean[] teclaPresionada = new boolean[4];
    private Player player;

    private static final float ACELERACION = 1f;
    private static final int ACELERACION_DELAY = 50;
    private static final int ACELERACION_LIMITE = 4;
    private Thread hiloAceleracion;

    public KeyInput(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.MOUSE_EVENT_MASK) {
            System.exit(0);
        }

        if (player == null) {
            return;
        }

        // SALTO
        if (key == KeyEvent.VK_W) {
            if (!player.hasJumped() && !teclaPresionada[0]) {
                teclaPresionada[0] = true;
                player.setVelY(-12);
                player.setJumped(true);
            }
        }

        // IZQUIERDA
        if (key == KeyEvent.VK_A) {
            if (!teclaPresionada[1]) {
                teclaPresionada[1] = true;
                player.setAtras(true);
            }
        }

        // DERECHA
        if (key == KeyEvent.VK_D) {
            if (!teclaPresionada[2]) {
                teclaPresionada[2] = true;
                player.setAdelante(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            teclaPresionada[0] = false;
        }

        if (key == KeyEvent.VK_A) {
            teclaPresionada[1] = false;
            player.setAtras(false);
        }

        if (key == KeyEvent.VK_D) {
            teclaPresionada[2] = false;
            player.setAdelante(false);
        }

        if (!teclaPresionada[1] && !teclaPresionada[2]) {
            player.setVelX(0);
        }
    }

    public boolean getTeclaPrecionada(int i) {
        return teclaPresionada[i];
    }

    private void delay() {
        try {
            Thread.sleep(ACELERACION_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


/*

private void aceleracionDerecha() {
        if (player.getVelX() < 4) {
            hiloAceleracion = new Thread(() -> {
                while (player.getVelX() < 4 && keyDown[2]) {
                    delay();

                    player.setDerrapando(true);
                    player.setVelX(player.getVelX() + ACELERACION);
                    if (player.getVelX() > 4) {
                        player.setVelX(4.0f);
                        player.setDerrapando(false);
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

                    player.setDerrapando(true);
                    player.setVelX(player.getVelX() - ACELERACION);
                    if (player.getVelX() < -4) {
                        player.setVelX(-4.0f);
                        player.setDerrapando(false);
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

 */
