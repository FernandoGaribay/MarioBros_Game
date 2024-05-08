package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.EntidadID;
import static object.EntidadID.HongoRojo;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueMoneda;
import static object.ObjectID.TuberiaCabeza;
import static object.ObjectID.BloqueHongoRojo;
import static object.ObjectID.LadrilloRojo;
import object.util.HandlerSonidos;
import utils.ReproductorMP3;

public class EntidadGoomba extends GameEntidad {

    // OBJETOS
    private Animacion animacion;

    private BufferedImage imgGoombaMuerto;

    public EntidadGoomba(float x, float y, int width, int height) {
        super(x, y, EntidadID.Goomba, width, height);
        setVelX(-1.2f);

        animacion = new Animacion(10,
                Texturas.getEntidadesTextura("entidadGoombaCaminando1"),
                Texturas.getEntidadesTextura("entidadGoombaCaminando2")
        );
    }

    @Override
    public void tick() {
        if (!(aplastado || atropellado)) {
            animacion.runAnimacion();
            aplicarMovimiento();
            aplicarGravedad();
            aplicarColisiones();
        } else {
            animacionMuerte();
        }

    }

    @Override
    public void render(LibreriaGrafica g) {
        if (atropellado) {
            g.drawImage(imgGoombaMuerto, (int) (getX() + getWidth()), (int) (getY() + getHeight()), (int) -getWidth(), (int) -getHeight());
        } else if (aplastado) {
            g.drawImage(imgGoombaMuerto, (int) getX(), (int) (getY() + getHeight() / 2), (int) getWidth(), (int) getHeight() / 2);
        } else {
            animacion.drawSprite(g, (int) getX(), (int) getY());
        }
//        showBounds(g);
    }

    @Override
    public Rectangle getBounds() {
        if (!(aplastado || atropellado)) {
            return new Rectangle((int) (getX() + getWidth() / 4), (int) (getY() + getWidth() / 2), (int) (getWidth() / 2), (int) (getHeight() / 2));
        } else {
            return new Rectangle();
        }
    }

    public Rectangle getBoundsSides() {
        return new Rectangle((int) getX(),
                (int) getY(),
                (int) getWidth(),
                (int) getHeight() / 2);
    }

    @Override
    public GameEntidad clone() {
        return new EntidadGoomba((int) x, (int) y, (int) width, (int) height);
    }

    public void aplicarMovimiento() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());
    }

    private void aplicarColisiones() {
        int size = Game.getHandlerBloques().getGameObj().size() - 1;

        for (int i = 0; i < size; i++) {
            GameObjeto temp = Game.getHandlerBloques().getGameObj().get(i);

            switch (temp.getID()) {
                case Bloque:
                case BarreraEntidades:
                case TuberiaCabeza:
                case BloqueMoneda:
                case BloqueHongoRojo:
                case LadrilloRojo:
                    handleColisionSolida(temp);
                    break;
            }
        }

        size = Game.getHandlerEntidades().getGameEntidades().size();
        for (int i = 0; i < size; i++) {
            GameEntidad temp = Game.getHandlerEntidades().getGameEntidades().get(i);

            if (temp == this) {
                continue;
            }

            switch (temp.getID()) {
                case Goomba:
                case KoopaCaparazon:
                    handleColisionEntidad(temp);
                    break;
            }
        }
    }

    private void handleColisionSolida(GameObjeto temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            setY(temp.getY() - getHeight());
            setVelY(0);
        }
        // Bounding Box de los lados
        if (getBoundsSides().intersects(temp.getBounds())) {
            setVelX(getVelX() * -1);
        }
    }

    private void handleColisionEntidad(GameEntidad temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            switch (temp.getID()) {
                case KoopaCaparazon:
                    HandlerSonidos.playSound("KickShellSound.wav");
                    if (temp.getVelX() != 0) {
                        atropellado = true;
                        GameEntidad.addEntidadABorrar(this);
                    }
                    break;
            }
        }

        if (getBounds().intersects(temp.getBounds())) {
            switch (temp.getID()) {
                case Goomba:
                    setVelX(-getVelX());
                    break;
            }
        }
    }

    private void animacionMuerte() {
        aplicarMovimiento();
        if (aplastado) {
            if (contAnimacion == 0) {
                imgGoombaMuerto = Texturas.getEntidadesTextura("entidadGoomba");
                setVelY(0);
                setVelX(0);
            } else if (contAnimacion <= 40) {

            } else {
                animacionCompletada = true;
                return;
            }
        } else if (atropellado) {
            if (contAnimacion == 0) {
                imgGoombaMuerto = Texturas.getEntidadesTextura("entidadGoomba");
                setVelY(0);
                setVelX((getVelX() > 0) ? 0.7f : -0.7f);
            } else if (contAnimacion <= 10) {
                setVelY(getVelY() - 0.5f);
            } else if (contAnimacion <= 40) {
                setVelY(getVelY() + 0.9f);
            } else {
                animacionCompletada = true;
                return;
            }
        }
        contAnimacion++;
    }

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsSides(), Color.red);
    }
}
