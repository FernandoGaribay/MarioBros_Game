package object;

import object.util.GameObject;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.util.ObjectID;

public class BloqueHongo extends GameObject {

    // OBJETOS
    private BufferedImage[] bloquesMoneda;
    private Hongo hongo;
    private Animacion animacion;

    // Variables
    private boolean golpeado = false;
    private int contAnimacionGolpe = 0;
    private int contAnimacionMoneda = 0;

    public BloqueHongo(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BloqueHongo, width, height, xDesplasamiento);

        bloquesMoneda = Texturas.getBloquesMoneda();
        animacion = new Animacion(12, bloquesMoneda);
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
            g.drawImage(hongo.getMoneda(), (int) hongo.getX(), (int) hongo.getY());
        }
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new BloqueHongo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    public void golpeado() {
        golpeado = true;
        hongo = new Hongo(x, y - 32);
    }

    public void runAnimacionGolpe() {
//        if (contAnimacionGolpe == 16) {
//            return;
//        }
//
//        if (contAnimacionGolpe < 8) {
//            setY(getY() - 3);
//        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
//            setY(getY() + 3);
//        }
//        contAnimacionGolpe++;
    }

    public void runAnimacionMoneda() {
//        if (contAnimacionMoneda == 24) {
//            moneda.setMoneda(null);
//            return;
//        }
//        if (contAnimacionMoneda < 12) {
//            moneda.setY(moneda.getY() - 8f);
//        } else if (contAnimacionMoneda < 24 && contAnimacionMoneda >= 12) {
//            moneda.setY(moneda.getY() + 5f);
//        }
//        contAnimacionMoneda++;
    }
}

class Hongo {

    // OBJETOS
    private BufferedImage moneda;
    private float x, y;

    public Hongo(float x, float y) {
        this.moneda = Texturas.getDropsTextura("hongo");
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
