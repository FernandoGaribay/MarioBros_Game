package object;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class BloquePiso extends GameObject {

    // OBJETOS
    private Texturas texturas;

    public BloquePiso(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.Block, width, height, scale);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("bloquePiso"), (int) (getX()), (int) (getY()));
//        g.drawRect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()),new Color(255,255,255));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    @Override
    public GameObject clone() {
        return new BloquePiso((int)x, (int)y, (int)width, (int)height, 1);
    }
}
