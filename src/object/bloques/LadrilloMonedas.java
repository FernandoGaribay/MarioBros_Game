package object.bloques;

import object.padres.BloqueEnigma;
import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.ObjectID;

public class LadrilloMonedas extends BloqueEnigma {

    // OBJETOS
    private MonedaLadrillo moneda;

    // Variables
    private int numMonedas = 5;
    private int contAnimacionMoneda = 0;

    public LadrilloMonedas(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.LadrilloMonedas, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {
        if (golpeado) {
            runAnimacionGolpe();
            runAnimacionMoneda();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (golpeado) {
            g.drawImage(moneda.getMoneda(), (int) moneda.getX(), (int) moneda.getY());
        }
        if (numMonedas == 0) {
            g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
        } else {
            g.drawImage(Texturas.getTextura("bloqueLadrillo"), (int) (getX()), (int) (getY()));
        }
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new LadrilloMonedas((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    @Override
    public void golpeado() {
        if (numMonedas != 0) {
            numMonedas--;
            golpeado = true;
            moneda = new MonedaLadrillo(x + 8, y - 16);
        }
    }

    public void runAnimacionMoneda() {
        if (contAnimacionMoneda == 24) {
            contAnimacionGolpe = 0;
            contAnimacionMoneda = 0;

            golpeado = false;
            moneda.setMoneda(null);
            return;
        }
        if (contAnimacionMoneda < 12) {
            moneda.setY(moneda.getY() - 8f);
        } else if (contAnimacionMoneda < 24 && contAnimacionMoneda >= 12) {
            moneda.setY(moneda.getY() + 5f);
        }
        contAnimacionMoneda++;
    }
}

class MonedaLadrillo {

    // OBJETOS
    private BufferedImage moneda;
    private float x, y;

    public MonedaLadrillo(float x, float y) {
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
