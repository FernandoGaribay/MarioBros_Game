package object.bloques;

import object.padres.BloqueEnigma;
import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import main.Game;
import object.ObjectID;
import utils.ReproductorMP3;

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
//        g.drawRectangle(getBounds(), Color.RED);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueHongoVerde((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    @Override
    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            return;
        }
        if (contAnimacionGolpe == 0) {
            if (Game.SONIDO) {
                ReproductorMP3.reproducirSonido("ItemBlockSound.wav");
            }
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
