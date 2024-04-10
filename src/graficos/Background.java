package graficos;

import graficos.LibreriaGrafica;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.GameObject;
import object.Tuberia;
import object.util.ObjectID;

public class Background extends GameObject {

    // CONSTANTES
    private final int SCREEN_OFFSET;

    // VARIABLES
    private Camara camara;

    public Background(int x, int y, int width, int height, int SCREEN_OFFSET, Camara camara) {
        super(x, y, ObjectID.Background, width + 10, height, 1);
        this.SCREEN_OFFSET = SCREEN_OFFSET;
        this.camara = camara;
    }

    @Override
    public void tick() {
        if (camara != null) {
            this.setX((int) -camara.getX());
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.fillRect((int) getX(), (int) getY() - SCREEN_OFFSET, (int) (getWidth() + getX()), (int) getHeight(), new Color(111, 133, 255));

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
        return new Tuberia((int) x, (int) y, (int) width, (int) height, 1);
    }
}
