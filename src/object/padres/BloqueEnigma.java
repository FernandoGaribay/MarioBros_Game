package object.padres;

import object.util.GameObjeto;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Game;
import object.ObjectID;
import object.bloques.BloqueMoneda;
import utils.ReproductorMP3;

public class BloqueEnigma extends GameObjeto {

    // OBJETOS
    private BufferedImage[] bloquesMoneda;
    protected Animacion animacion;

    // Variables
    protected boolean golpeado = false;
    protected int contAnimacionGolpe = 0;

    public BloqueEnigma(int x, int y, ObjectID objectID, int width, int height, int xDesplasamiento) {
        super(x, y, objectID, width, height, xDesplasamiento);

        bloquesMoneda = Texturas.getBloquesMoneda();
        animacion = new Animacion(12, bloquesMoneda);
    }

    @Override
    public void tick() {
        animacion.runAnimacion();

        if (golpeado) {
            runAnimacionGolpe();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (!golpeado) {
            animacion.drawSprite(g, (int) getX(), (int) getY());
        } else {
            g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueMoneda((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    public void golpeado() {
        golpeado = true;
    }

    public boolean poderGenerarDrops() {
        if (contAnimacionGolpe == 16) {
            return true;
        }
        return false;
    }

    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            return;
        }
        
        if (contAnimacionGolpe < 8) {
            setY(getY() - 3);
        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
            setY(getY() + 3);
        }
        contAnimacionGolpe++;
    }

    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }

    public boolean isGolpeado() {
        return golpeado;
    }

}
