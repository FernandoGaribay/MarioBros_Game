package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.ObjectID;
import object.padres.BloqueLadrillo;

public class BloqueLadrilloRojo extends BloqueLadrillo {

    public BloqueLadrilloRojo(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.LadrilloRojo, width, height, xDesplasamiento, "bloqueLadrillo");

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
        super.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueLadrilloRojo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
