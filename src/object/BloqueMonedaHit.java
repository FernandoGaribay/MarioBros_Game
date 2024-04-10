package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class BloqueMonedaHit extends GameObject {

    // OBJETOS
    private Texturas texturas;

    public BloqueMonedaHit(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.CoinBlock, width, height, scale);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new BloqueMonedaHit((int) x, (int) y, (int) width, (int) height, 1);
    }
}
