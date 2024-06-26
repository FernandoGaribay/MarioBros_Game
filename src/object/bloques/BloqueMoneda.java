package object.bloques;

import object.padres.BloqueEnigma;
import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Game;
import object.ObjectID;
import object.util.HandlerSonidos;
import utils.ReproductorMP3;

public class BloqueMoneda extends BloqueEnigma {

    // OBJETOS
    private Moneda moneda;

    // Variables
    private int contAnimacionMoneda = 0;

    public BloqueMoneda(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BloqueMoneda, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {
        animacion.runAnimacion();

        if (golpeado) {
            runAnimacionGolpe();
            runAnimacionMoneda();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (!golpeado) {
            animacion.drawSprite(g, (int) getX(), (int) getY());
        } else {
            g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
            g.drawImage(moneda.getMoneda(), (int) moneda.getX(), (int) moneda.getY());
        }
//        g.drawRectangle(getBounds(), Color.RED);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueMoneda((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    @Override
    public void golpeado() {
        golpeado = true;
        moneda = new Moneda(x + 8, y - 16);
    }

    public void runAnimacionMoneda() {
        if (contAnimacionMoneda == 24) {
            moneda.setMoneda(null);
            return;
        }
        if (contAnimacionMoneda == 0) {
            HandlerSonidos.playSound("CoinSound.wav");
        } else if (contAnimacionMoneda < 12) {
            moneda.setY(moneda.getY() - 8f);
        } else if (contAnimacionMoneda < 24 && contAnimacionMoneda >= 12) {
            moneda.setY(moneda.getY() + 5f);
        }
        contAnimacionMoneda++;
    }
}

class Moneda {

    // OBJETOS
    private BufferedImage moneda;
    private float x, y;

    public Moneda(float x, float y) {
        this.moneda = Texturas.getEntidadesTextura("entidadMoneda1");
        this.x = x;
        this.y = y;
    }

    public BufferedImage getMoneda() {
        return moneda;
    }

    public void setMoneda(BufferedImage moneda) {
        this.moneda = moneda;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
