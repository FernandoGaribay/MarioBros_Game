package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;
import object.padres.BloqueLadrillo;

public class BloqueLadrilloAzul extends BloqueLadrillo {

    public BloqueLadrilloAzul(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.LadrilloAzul, width, height, xDesplasamiento, "bloqueLadrilloAzul");
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
        super.render(g);
//        g.drawRect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueLadrilloAzul((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
