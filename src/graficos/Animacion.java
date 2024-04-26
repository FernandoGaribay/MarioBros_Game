package graficos;

import java.awt.image.BufferedImage;

public class Animacion {

    private int frames = 0;
    private int velocidad = 0;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] imagenes;
    private BufferedImage spriteActual;

    public Animacion(int velocidad, BufferedImage... args) {
        this.velocidad = velocidad;

        this.imagenes = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            imagenes[i] = args[i];
        }

        this.frames = args.length;
    }

    public void runAnimacion() {
        index++;
        if (index > velocidad) {
            index = 0;
            siguienteFrame();
        }
    }

    public void siguienteFrame() {
        spriteActual = imagenes[count];
        count++;

        if (count >= frames) {
            count = 0;
        }
    }

    public void drawSprite(LibreriaGrafica g, int x, int y) {
        if (spriteActual != null) {
            g.drawImage(spriteActual, x, y);
        }
    }

    public void drawSpriteInverso(LibreriaGrafica g, int x, int y) {
        if (spriteActual != null) {
            g.drawImage(spriteActual, x + spriteActual.getWidth(), y, -spriteActual.getWidth(), spriteActual.getHeight());
        }
    }
}
