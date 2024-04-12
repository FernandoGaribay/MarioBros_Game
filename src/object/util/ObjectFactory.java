package object.util;

import java.util.HashMap;
import java.util.Map;
import object.ArbustoChico;
import object.ArbustoGrande;
import object.ArbustoMediano;
import object.BanderaMastil;
import object.Barrera;
import object.BloqueBandera;
import object.BloqueMoneda;
import object.BloqueMonedaHit;
import object.BloquePiso;
import object.Castillo;
import object.Ladrillo;
import object.MontanaChica;
import object.MontanaGrande;
import object.NubeChica;
import object.NubeGrande;
import object.NubeMediana;
import object.Tuberia;
import object.TuberiaCabeza;

public class ObjectFactory {

    private static final Map<String, GameObject> prototypes = new HashMap<>();

    static {
        prototypes.put("tuberiaCabeza", new TuberiaCabeza(0, 0, 64, 32, 1, true));
        prototypes.put("tuberia", new Tuberia(0, 0, 64, 32, 1));
        prototypes.put("bloquePiso", new BloquePiso(0, 0, 32, 32, 1));
        prototypes.put("bloqueBandera", new BloqueBandera(0, 0, 32, 32, 1));
        prototypes.put("bloqueMoneda1", new BloqueMoneda(0, 0, 32, 32, 1));
        prototypes.put("bloqueMonedaHit", new BloqueMonedaHit(0, 0, 32, 32, 1));
        prototypes.put("bloqueLadrillo", new Ladrillo(0, 0, 32, 32, 1));
        prototypes.put("banderaMastil", new BanderaMastil(0, 0, 32, 288, 1));
        prototypes.put("castillo", new Castillo(0, 0, 160, 160, 1));
        prototypes.put("montanaChica", new MontanaChica(0, 0, 96, 32, 1));
        prototypes.put("montanaGrande", new MontanaGrande(0, 0, 160, 64, 1));
        prototypes.put("arbustoChico", new ArbustoChico(0, 0, 64, 32, 1));
        prototypes.put("arbustoMediano", new ArbustoMediano(0, 0, 32, 256, 1));
        prototypes.put("arbustoGrande", new ArbustoGrande(0, 0, 96, 32, 1));
        prototypes.put("nubeChica", new NubeChica(0, 0, 64, 32, 1));
        prototypes.put("nubeMediana", new NubeMediana(0, 0, 96, 32, 1));
        prototypes.put("nubeGrande", new NubeGrande(0, 0, 128, 32, 1));
        prototypes.put("bloqueBarrera", new Barrera(0, 0, 32, 32, 1));
    }

    public static GameObject createObject(String type) {
        GameObject prototype = prototypes.get(type);
        if (prototype != null) {
            // Clona el prototipo
            return prototype.clone();
        }
        return null;
    }
}