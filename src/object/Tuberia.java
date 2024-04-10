package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Rectangle;
import object.util.ObjectID;

public class Tuberia extends GameObject {

    // OBJETOS
    private Texturas texturas;

    public Tuberia(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.Block, width, height, scale);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("tuberia"), (int) (getX()), (int) (getY()));
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
