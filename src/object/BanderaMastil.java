package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.util.ObjectID;

public class BanderaMastil extends GameObject {

    // OBJETOS
    private Texturas texturas;
    private Bandera bandera;

    // VARIABLES
    private int heightBandera;
    private boolean animacionInicio;

    public BanderaMastil(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Bandera, width, height, xDesplasamiento);

        this.texturas = new Texturas();
        this.bandera = new Bandera();
        this.heightBandera = (int) getY();
        this.animacionInicio = true;
    }

    @Override
    public void tick() {
        if (getVelY() != 0 && animacionInicio) {
            this.heightBandera += getVelY();

            if (heightBandera >= getY() + getHeight() - 55) {
                animacionInicio = false;
            }
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("banderaMastil"), (int) (getX()), (int) (getY()));
        g.drawImage(bandera.getBandera(), (int) (getX() - 16), heightBandera + 16);
//        g.drawRectangle(getBounds(), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.heightBandera = (int) (getY() + 5);
    }

    @Override
    public GameObject clone() {
        return new BanderaMastil((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
    
}

class Bandera {

    // OBJETOS
    private Texturas texturas;
    private BufferedImage bandera;

    public Bandera() {
        this.texturas = new Texturas();
        this.bandera = texturas.getTextura("bandera");
    }

    public BufferedImage getBandera() {
        return bandera;
    }
}
