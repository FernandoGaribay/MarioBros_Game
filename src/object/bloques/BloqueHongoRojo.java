package object.bloques;

import object.padres.BloqueEnigma;
import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueHongoRojo extends BloqueEnigma {

    public BloqueHongoRojo(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BloqueHongoRojo, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
        super.render(g);
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueHongoRojo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    public boolean poderGenerarHongo() {
        if (contAnimacionGolpe == 16) {
            return true;
        }
        return false;
    }
}
