package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;
import object.padres.BloqueEntradaSalida;

public class BloqueEntrada extends BloqueEntradaSalida {

    public BloqueEntrada(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Entrada, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
//        if (height == 64) {
//            g.drawImage(Texturas.getTextura("entradaA_Vertical"), (int) (getX()), (int) (getY()));
//        } else {
//            g.drawImage(Texturas.getTextura("entradaA_Horizontal"), (int) (getX()), (int) (getY()));
//        }
//        g.drawRect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueEntrada((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
