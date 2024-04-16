package object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import object.Player;



public class KeyInput extends KeyAdapter {

    private boolean[] keyDown = new boolean[4];
    private Handler handler;
    private Player player;

    public KeyInput(Player player, Handler handler) {
        this.handler = handler;
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        // jump
        if (key == KeyEvent.VK_W) {
            if (!player.hasJumped()) {
                player.setVelY(-12);
                keyDown[0] = true;
                player.setJumped(true);
            }
        }

        // left
        if (key == KeyEvent.VK_A) {
            player.setVelX(-4);
            keyDown[1] = true;
        }

        // right
        if (key == KeyEvent.VK_D) {
            player.setVelX(4);
            keyDown[2] = true;
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
            player.setVelX(0);
        }
    }

}
