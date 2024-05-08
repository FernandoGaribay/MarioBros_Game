package object.bloques;

import object.padres.BloqueEnigma;
import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import java.awt.Color;
import java.awt.Rectangle;
import main.Game;
import object.ObjectID;
import object.util.HandlerSonidos;
import utils.ReproductorMP3;

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
//        g.drawRectangle(getBounds(), Color.RED);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueHongoRojo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    @Override
    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            return;
        }
        if (contAnimacionGolpe == 0) {
            HandlerSonidos.playSound("ItemBlockSound.wav");
        }
        if (contAnimacionGolpe < 8) {
            setY(getY() - 3);
        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
            setY(getY() + 3);
        }
        contAnimacionGolpe++;
    }

    public boolean poderGenerarHongo() {
        if (contAnimacionGolpe == 16) {
            return true;
        }
        return false;
    }
}
