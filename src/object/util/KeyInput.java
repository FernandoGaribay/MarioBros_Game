package object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import object.Player;

public class KeyInput extends KeyAdapter {

    private boolean[] teclaPresionada = new boolean[4];
    private Player player;

    public KeyInput(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
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
                player.setMirarAdelante(false);
                player.setAtras(true);
            }
        }

        // DERECHA
        if (key == KeyEvent.VK_D) {
            if (!teclaPresionada[2]) {
                teclaPresionada[2] = true;
                player.setMirarAdelante(true);
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
    }
}