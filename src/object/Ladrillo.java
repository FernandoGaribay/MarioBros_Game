package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class Ladrillo extends GameObject {

    // OBJETOS
    private Texturas texturas;
    
    // VARIABLES
    private int contAnimacionGolpe = 0;

    public Ladrillo(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Ladrillo, width, height, xDesplasamiento);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {
        if (isGolpeado()) {
            runAnimacionGolpe();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("bloqueLadrillo"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.white);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new Ladrillo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }

    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            contAnimacionGolpe = 0;
            setGolpeado(false);

            return;
        }

        if (contAnimacionGolpe < 8) {
            setY(getY() - 3);
        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
            setY(getY() + 3);
        }
        contAnimacionGolpe++;
    }
}
