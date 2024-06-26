package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.ObjectID;

public class BanderaMastil extends GameObjeto {

    // OBJETOS
    private Bandera bandera;

    // VARIABLES
    private int heightBandera;
    private boolean animacionInicio;

    public BanderaMastil(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Bandera, width, height, xDesplasamiento);

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
        g.drawImage(Texturas.getTextura("bloqueBanderaMastil"), (int) (getX()), (int) (getY()));
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
    public GameObjeto clone() {
        return new BanderaMastil((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
    
}

class Bandera {

    // OBJETOS
    private BufferedImage bandera;

    public Bandera() {
        this.bandera = Texturas.getTextura("bandera");
    }

    public BufferedImage getBandera() {
        return bandera;
    }
}
