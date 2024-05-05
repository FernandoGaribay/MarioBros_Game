package object.padres;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueEntradaSalida extends GameObjeto {


    public BloqueEntradaSalida(int x, int y, ObjectID objectID, int width, int height, int xDesplasamiento) {
        super(x, y, objectID, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        if (height == 32) {
            g.drawImage(Texturas.getTextura("entradaA_Horizontal"), (int) (getX()), (int) (getY()));
        } else {
            g.drawImage(Texturas.getTextura("entradaA_Vertical"), (int) (getX()), (int) (getY()));
        }
//        g.drawRect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        // No implementado 
        return null;
    }
}
