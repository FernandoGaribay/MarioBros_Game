package graficos;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.GameObject;
import object.Player;
import object.Tuberia;
import object.util.ObjectID;

public class Background extends GameObject {

    private Player player;

    public Background(int x, int y, int width, int height, Player player) {
        super(x, y, ObjectID.Background, width, height, 1);
        this.player = player;
    }

    @Override
    public void tick() {
        if (player != null) {
            this.setX((int) (player.getX() - getWidth() / 2 + 35));
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.fillRect((int) getX(), (int) getY() - 50, (int) (getWidth() + getX()), (int) getHeight(), new Color(111, 133, 255));
        
        // Mostrar cuadricula
//        for (int i = 0; i < getWidth() / 32; i++) {
//            g.drawLine((int) (getX() + i * 32), (int) getY(), (int) (getX() + i * 32), (int) (getY() + getHeight()), new Color(111, 99, 255));
//        }
//        for (int i = 0; i < getHeight() / 32; i++) {
//            g.drawLine((int) getX(), (int) (getY() + i * 32), (int) (getX() + getWidth()), (int) (getY() + i * 32), new Color(111, 99, 255));
//        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    
        @Override
    public GameObject clone() {
        return new Tuberia((int)x, (int)y, (int)width, (int)height, 1);
    }
}
