package object.bloques;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Rectangle;
import object.ObjectID;

public class TuberiaCabeza extends GameObjeto{
    
    // OBJETOS
    private Texturas texturas;
    
    // VARIABLES
    public boolean enterable;
    
    public TuberiaCabeza(int x, int y, int width, int height, int xDesplasamiento, boolean enterable) {
        super(x, y, ObjectID.TuberiaCabeza, width, height, xDesplasamiento);
        this.enterable = enterable;
        this.texturas = new Texturas();
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("bloqueTuberiaCabeza"), (int) (getX()), (int) (getY()));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    
        @Override
    public GameObjeto clone() {
        return new TuberiaCabeza((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento, true);
    }
}