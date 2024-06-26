package object;

import java.util.HashMap;
import java.util.Map;
import object.bloques.ArbustoChico;
import object.bloques.ArbustoGrande;
import object.bloques.ArbustoMediano;
import object.bloques.BanderaMastil;
import object.bloques.BloqueBarrera;
import object.bloques.BloqueBandera;
import object.bloques.BloqueBarreraEntidades;
import object.bloques.BloqueEntrada;
import object.bloques.BloqueHongoRojo;
import object.bloques.BloqueHongoVerde;
import object.bloques.BloqueLadrilloAzul;
import object.bloques.BloqueMoneda;
import object.bloques.BloqueMonedaHit;
import object.bloques.BloquePiso;
import object.bloques.BloquePisoAzul;
import object.bloques.Castillo;
import object.entidades.EntidadGoomba;
import object.bloques.BloqueLadrilloRojo;
import object.bloques.BloqueSalida;
import object.bloques.BloqueLadrilloMonedas;
import object.bloques.MontanaChica;
import object.bloques.MontanaGrande;
import object.bloques.NubeChica;
import object.bloques.NubeGrande;
import object.bloques.NubeMediana;
import object.bloques.BloqueTuberia;
import object.bloques.BloqueTuberiaCabezaVertical;
import object.bloques.BloqueTuberiaTerminalVertical;
import object.bloques.BloqueTuberiaVertical;
import object.bloques.BloqueTuberiaCabeza;
import object.entidades.EntidadHongoRojo;
import object.entidades.EntidadHongoVerde;
import object.entidades.EntidadKoopa;
import object.entidades.EntidadKoopaCaparazon;
import object.entidades.EntidadMonedaAzul;
import object.entidades.EntidadMonedaRoja;
import object.util.GameObjeto;
import object.util.GameEntidad;

public class ObjectFactory {

    private static final Map<String, GameObjeto> bloquesPrototipos = new HashMap<>();
    private static final Map<String, GameEntidad> entidadesPrototipos = new HashMap<>();

    static {
        bloquesPrototipos.put("bloqueTuberiaCabeza", new BloqueTuberiaCabeza(0, 0, 64, 32, 0, true));
        bloquesPrototipos.put("bloqueTuberiaCabezaVertical", new BloqueTuberiaCabezaVertical(0, 0, 32, 64, 0, true));
        bloquesPrototipos.put("bloqueTuberia", new BloqueTuberia(0, 0, 64, 32, 0));
        bloquesPrototipos.put("bloqueTuberiaVertical", new BloqueTuberiaVertical(0, 0, 32, 64, 0));
        bloquesPrototipos.put("bloqueTuberiaTerminalVertical", new BloqueTuberiaTerminalVertical(0, 0, 64, 64, 0));
        bloquesPrototipos.put("bloquePiso", new BloquePiso(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloquePisoAzul", new BloquePisoAzul(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueBandera", new BloqueBandera(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueMoneda1", new BloqueMoneda(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueHongoRojo", new BloqueHongoRojo(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueHongoVerde", new BloqueHongoVerde(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueMonedaHit", new BloqueMonedaHit(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueLadrillo", new BloqueLadrilloRojo(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueLadrilloAzul", new BloqueLadrilloAzul(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueLadrilloMonedas", new BloqueLadrilloMonedas(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueBanderaMastil", new BanderaMastil(0, 0, 32, 288, 0));
        bloquesPrototipos.put("bloqueCastillo", new Castillo(0, 0, 160, 160, 1));
        bloquesPrototipos.put("bloqueMontanaChica", new MontanaChica(0, 0, 96, 32, 0));
        bloquesPrototipos.put("bloqueMontanaGrande", new MontanaGrande(0, 0, 160, 64, 0));
        bloquesPrototipos.put("bloqueArbustoChico", new ArbustoChico(0, 0, 64, 32, 16));
        bloquesPrototipos.put("bloqueArbustoMediano", new ArbustoMediano(0, 0, 32, 256, 16));
        bloquesPrototipos.put("bloqueArbustoGrande", new ArbustoGrande(0, 0, 96, 32, 16));
        bloquesPrototipos.put("bloqueNubeChica", new NubeChica(0, 0, 64, 32, 16));
        bloquesPrototipos.put("bloqueNubeMediana", new NubeMediana(0, 0, 96, 32, 16));
        bloquesPrototipos.put("bloqueNubeGrande", new NubeGrande(0, 0, 128, 32, 16));
        bloquesPrototipos.put("bloqueBarreraJugador", new BloqueBarrera(0, 0, 32, 32, 0));
        bloquesPrototipos.put("bloqueBarreraEntidades", new BloqueBarreraEntidades(0, 0, 32, 32, 0));
        
        bloquesPrototipos.put("entradaA", new BloqueEntrada(0, 0, 32, 32, 0));
        bloquesPrototipos.put("salidaB", new BloqueSalida(0, 0, 32, 32, 0));

        entidadesPrototipos.put("entidadMoneda1", new EntidadMonedaRoja(0, 0, 20, 28));
        entidadesPrototipos.put("entidadMonedaAzul1", new EntidadMonedaAzul(0, 0, 20, 28));
        entidadesPrototipos.put("entidadGoomba", new EntidadGoomba(0, 0, 32, 32));
        entidadesPrototipos.put("entidadKoopa", new EntidadKoopa(0, 0, 32, 46));
        entidadesPrototipos.put("entidadKoopaCaparazon", new EntidadKoopaCaparazon(0, 0, 32, 26));
        entidadesPrototipos.put("entidadHongoRojo", new EntidadHongoRojo(0, 0, 32, 32));
        entidadesPrototipos.put("entidadHongoVerde", new EntidadHongoVerde(0, 0, 32, 32));
    }

    public static GameObjeto crearBloque(String type) {
        GameObjeto bloquePrototipo = bloquesPrototipos.get(type);
        if (bloquePrototipo != null) {
            // Clona el prototipo
            return bloquePrototipo.clone();
        }
        return null;
    }

    public static GameEntidad crearEntidad(String type) {
        GameEntidad entidadPrototipo = entidadesPrototipos.get(type);
        if (entidadPrototipo != null) {
            // Clona el prototipo
            return entidadPrototipo.clone();
        }
        return null;
    }
}
