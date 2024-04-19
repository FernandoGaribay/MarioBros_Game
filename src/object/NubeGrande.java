package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class NubeGrande extends GameObject {

    // OBJETOS
    private Texturas texturas;

    public NubeGrande(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Background, width, height, xDesplasamiento);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("nubeGrande"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new NubeGrande((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
}
