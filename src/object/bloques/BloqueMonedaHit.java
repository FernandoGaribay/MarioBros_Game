package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueMonedaHit extends GameObjeto {

    public BloqueMonedaHit(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BloqueMoneda, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.RED);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueMonedaHit((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
