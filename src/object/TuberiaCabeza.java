package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Rectangle;
import object.util.ObjectID;

public class TuberiaCabeza extends GameObject{
    
    // OBJETOS
    private Texturas texturas;
    
    // VARIABLES
    public boolean enterable;
    
    public TuberiaCabeza(int x, int y, int width, int height, int scale, boolean enterable) {
        super(x, y, ObjectID.PipeHead, width, height, scale);
        this.enterable = enterable;
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("tuberiaCabeza"), (int) (getX()), (int) (getY()));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    
        @Override
    public GameObject clone() {
        return new TuberiaCabeza((int)x, (int)y, (int)width, (int)height, 1, true);
    }
}
