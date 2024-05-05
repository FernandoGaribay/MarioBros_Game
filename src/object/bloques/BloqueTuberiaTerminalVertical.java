package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Rectangle;
import object.ObjectID;

public class BloqueTuberiaTerminalVertical extends GameObjeto {

    public BloqueTuberiaTerminalVertical(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Bloque, width, height, xDesplasamiento);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(Texturas.getTextura("bloqueTuberiaTerminalVertical"), (int) (getX()), (int) (getY()));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueTuberiaTerminalVertical((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
