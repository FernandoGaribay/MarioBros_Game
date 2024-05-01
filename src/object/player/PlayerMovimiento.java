package object.player;

public class PlayerMovimiento {

    // Objeto jugador
    Player player;

    private static boolean caminarAdelante = false;
    private static boolean caminarAtras = false;

    public PlayerMovimiento(Player player) {
        this.player = player;
    }

    public void aplicarMovimiento() {
        player.setX(player.getVelX() + player.getX());
        player.setY(player.getVelY() + player.getY());

        // si el jugador esta en una animacion no se puede mover
        if (player.isIgnorarInput()) {
            neutralizarMovimiento();
            return;
        }

        if (caminarAdelante) {
            player.setVelX(player.getVelX() + 0.2f);
            if (player.getVelX() > 4) {
                player.setVelX(4);
            }
        } else if (caminarAtras) {
            player.setVelX(player.getVelX() - 0.2f);
            if (player.getVelX() < -4) {
                player.setVelX(-4);
            }
        } else if (player.getVelX() != 0) {
            if (Math.abs(player.getVelX()) < 0.01f) {
                player.setVelX(0);
            } else if (player.getVelX() > 0) {
                player.setVelX(player.getVelX() - 0.1f);
            } else if (player.getVelX() < 0) {
                player.setVelX(player.getVelX() + 0.1f);
            }
        }
    }

    public void aplicarGravedad() {
        // Si el jugador esta en una animacion no sera afectado por la gravedad
        if (player.isIgnorarColisiones()) {
            return;
        }

        player.setVelY(player.getVelY() + 0.5f);
    }

    public void neutralizarMovimiento() {
        player.setVelX(0);
        player.setX(player.getX());
    }

    public static boolean isCaminarAdelante() {
        return caminarAdelante;
    }

    public static void setCaminarAdelante(boolean caminarAdelante) {
        PlayerMovimiento.caminarAdelante = caminarAdelante;
    }

    public static boolean isCaminarAtras() {
        return caminarAtras;
    }

    public static void setCaminarAtras(boolean caminarAtras) {
        PlayerMovimiento.caminarAtras = caminarAtras;
    }

}
