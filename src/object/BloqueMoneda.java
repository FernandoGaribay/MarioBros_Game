package object;

import object.util.GameObject;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.util.ObjectID;

public class BloqueMoneda extends GameObject {

    // OBJETOS
    private Texturas texturas;
    private BufferedImage[] bloquesMoneda;
    private Animacion animacion;

    public BloqueMoneda(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.CoinBlock, width, height, scale);
        this.texturas = new Texturas();

        bloquesMoneda = texturas.getBloquesMoneda();
        animacion = new Animacion(12, bloquesMoneda);
    }

    @Override
    public void tick() {
        animacion.runAnimacion();
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (!isGolpeado()) {
            animacion.drawSprite(g, (int) getX(), (int) getY());
        } else {
            g.drawImage(texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
        }
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new BloqueMoneda((int) x, (int) y, (int) width, (int) height, 1);
    }
}
