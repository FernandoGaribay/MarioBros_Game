package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueHongoVerde extends BloqueEnigma {

    public BloqueHongoVerde(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BloqueHongoVerde, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (golpeado) {
            g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
        }
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueHongoVerde((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    public boolean poderGenerarHongo() {
        if (contAnimacionGolpe == 16) {
            return true;
        }
        return false;
    }
}
