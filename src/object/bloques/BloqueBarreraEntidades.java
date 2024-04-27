package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueBarreraEntidades extends GameObjeto {

    // OBJETOS
    private Texturas texturas;

    public BloqueBarreraEntidades(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.BarreraEntidades, width, height, xDesplasamiento);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
//        g.drawImage(texturas.getTextura("bloqueBarreraEntidades"), (int) (getX()), (int) (getY()));
//        g.drawRect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueBarreraEntidades((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
