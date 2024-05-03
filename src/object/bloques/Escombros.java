package object.bloques;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.image.BufferedImage;
import main.Game;
import object.ObjectID;
import static object.ObjectID.LadrilloRojo;

public class Escombros {

    private BufferedImage[] escombros;
    private float width, height, velX, velY;
    private float[] x, y;

    public Escombros(float x, float y, float width, float height, ObjectID objectID) {
        this.x = new float[4];
        this.y = new float[4];

        this.x[0] = (float) (x - (0.5 * width));
        this.x[1] = (float) (x - (0.5 * width));
        this.x[2] = (float) (x + (0.5 * width));
        this.x[3] = (float) (x + (0.5 * width));

        this.y[0] = (float) (y + (0.5 * height));
        this.y[1] = (float) (y - (0.5 * height));
        this.y[2] = (float) (y + (0.5 * height));
        this.y[3] = (float) (y - (0.5 * height));

        this.width = width / 2;
        this.height = height / 2;

        this.escombros = new BufferedImage[4];
        switch (objectID) {
            case LadrilloRojo:
                this.escombros[0] = Texturas.getEscombroTextura("ladrilloEscombro");
                this.escombros[1] = Texturas.getEscombroTextura("ladrilloEscombro");
                this.escombros[2] = Texturas.getEscombroTextura("ladrilloEscombro");
                this.escombros[3] = Texturas.getEscombroTextura("ladrilloEscombro");
                break;
            case LadrilloAzul:
                this.escombros[0] = Texturas.getEscombroTextura("ladrilloEscombroAzul");
                this.escombros[1] = Texturas.getEscombroTextura("ladrilloEscombroAzul");
                this.escombros[2] = Texturas.getEscombroTextura("ladrilloEscombroAzul");
                this.escombros[3] = Texturas.getEscombroTextura("ladrilloEscombroAzul");
                break;
        }

        this.velX = 1f;
        this.velY = -5f;
    }

    public void aplicarGravedad() {
        velY += 0.5f;
    }

    public boolean sePuedeEliminar() {
        if (y[1] > Game.getWindowHeight()) {
            return true;
        }
        return false;
    }

    public void tick() {
        x[0] = -velX + x[0];
        x[1] = -velX + x[1];
        x[2] = velX + x[2];
        x[3] = velX + x[3];

        y[0] = velY + y[0];
        y[1] = (float) (velY + y[1] - 1);
        y[2] = velY + y[2];
        y[3] = (float) (velY + y[3] - 1);

        aplicarGravedad();
    }

    public void render(LibreriaGrafica g) {
        for (int i = 0; i < 4; i++) {
            g.drawImage(escombros[i], (int) x[i], (int) y[i], (int) width, (int) height);
        }
    }

}
