package object;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class Castillo extends GameObject{
    
    // OBJETOS
    private Texturas texturas;
    
    public Castillo(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.Background, width, height, scale);
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("castillo"), (int) (getX()), (int) (getY()));
//        g.drawRectangle(getBounds(), Color.red);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    
        @Override
    public GameObject clone() {
        return new Castillo((int)x, (int)y, (int)width, (int)height, 1);
    }
}
