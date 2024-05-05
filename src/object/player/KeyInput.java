package object.player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import object.player.Player;
import object.player.PlayerMovimiento;

public class KeyInput extends KeyAdapter {

    private boolean[] teclaPresionada = new boolean[5];
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
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            if (!player.isSaltando() && !teclaPresionada[0]) {
                teclaPresionada[0] = true;
                player.setVelY(-12);
                player.setSaltando(true);
            }
        }

        // IZQUIERDA
        if (key == KeyEvent.VK_A) {
            if (!teclaPresionada[1]) {
                teclaPresionada[1] = true;
                player.setMirarAdelante(false);
                PlayerMovimiento.setCaminarAtras(true);
            }
        }

        // DERECHA
        if (key == KeyEvent.VK_D) {
            if (!teclaPresionada[2]) {
                teclaPresionada[2] = true;
                player.setMirarAdelante(true);
                PlayerMovimiento.setCaminarAdelante(true);
            }
        }

        // CORRER
        if (key == KeyEvent.VK_SHIFT) {
            if (!teclaPresionada[3]) {
                teclaPresionada[3] = true;
                player.setVELOCIDAD_MAXIMA(5.5f, 3);
            }
        }

        // INTERACCION
        if (key == KeyEvent.VK_E) {
            if (!teclaPresionada[4]) {
                teclaPresionada[4] = true;
                player.setDecenderTuberia(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            teclaPresionada[0] = false;
        }

        if (key == KeyEvent.VK_A) {
            teclaPresionada[1] = false;
            PlayerMovimiento.setCaminarAtras(false);
        }

        if (key == KeyEvent.VK_D) {
            teclaPresionada[2] = false;
            PlayerMovimiento.setCaminarAdelante(false);
        }

        if (key == KeyEvent.VK_SHIFT) {
            teclaPresionada[3] = false;
            player.setVELOCIDAD_MAXIMA(4.5f, 5);
        }
        
        if (key == KeyEvent.VK_E) {
            teclaPresionada[4] = false;
            player.setDecenderTuberia(false);
        }
    }
}
