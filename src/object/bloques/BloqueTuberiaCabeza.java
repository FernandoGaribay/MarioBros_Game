package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueTuberiaCabeza extends GameObjeto {

    // VARIABLES
    public boolean enterable;

    public BloqueTuberiaCabeza(int x, int y, int width, int height, int xDesplasamiento, boolean enterable) {
        super(x, y, ObjectID.TuberiaCabeza, width, height, xDesplasamiento);
        this.enterable = enterable;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(Texturas.getTextura("bloqueTuberiaCabeza"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.RED);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueTuberiaCabeza((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento, true);
    }
}
