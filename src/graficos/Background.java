package graficos;

import graficos.LibreriaGrafica;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.GameObjeto;
import object.bloques.Tuberia;
import object.ObjectID;

public class Background {

    // CONSTANTES
    private final int SCREEN_OFFSET;

    // VARIABLES
    private Camara camara;

    private static float x;
    private static float y;
    private static float width, height;
    private static Color color;

    public Background(int x, int y, int width, int height, int SCREEN_OFFSET, Camara camara) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = new Color(111, 133, 255);
        this.SCREEN_OFFSET = SCREEN_OFFSET;
        this.camara = camara;
    }

    public void tick() {
        if (camara != null) {
            this.setX((int) (-camara.getX()));
        }
    }

    public void render(LibreriaGrafica g) {
        g.fillRect((int) getX(), (int) getY() - SCREEN_OFFSET, (int) (getWidth() + getX()), (int) (getY() + getHeight()), color);

        // Mostrar cuadricula
//        for (int i = 0; i < getWidth() / 32; i++) {
//            g.drawLine((int) (getX() + i * 32), (int) getY(), (int) (getX() + i * 32), (int) (getY() + getHeight()), new Color(111, 99, 255));
//        }
//        for (int i = 0; i < getHeight() / 32; i++) {
//            g.drawLine((int) getX(), (int) (getY() + i * 32), (int) (getX() + getWidth()), (int) (getY() + i * 32), new Color(111, 99, 255));
//        }
    }

    public static float getX() {
        return x;
    }

    public static void setX(float x) {
        Background.x = x;
    }

    public static float getY() {
        return y;
    }

    public static void setY(float y) {
        Background.y = y;
    }

    public static float getWidth() {
        return width;
    }

    public static void setWidth(float width) {
        Background.width = width;
    }

    public static float getHeight() {
        return height;
    }

    public static void setHeight(float height) {
        Background.height = height;
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        Background.color = color;
    }

    
}
