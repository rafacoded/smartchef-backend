package com.smartchef.util;

import com.smartchef.model.UnidadMedida;
import java.util.HashMap;
import java.util.Map;

public class UnidadConversion {

    // Conversión base → destino (aproximaciones típicas)
    private static final Map<String, Double> conversiones = new HashMap<>();

    static {
        // volumen
        conversiones.put("TAZA->MILILITRO", 250.0);
        conversiones.put("CUCHARADA->MILILITRO", 15.0);
        conversiones.put("CUCHARADITA->MILILITRO", 5.0);

        // peso
        conversiones.put("TAZA->GRAMO", 120.0); // harina aprox
        conversiones.put("CUCHARADA->GRAMO", 8.0);

        // equivalencias entre medidas básicas
        conversiones.put("LITRO->MILILITRO", 1000.0);
        conversiones.put("KILO->GRAMO", 1000.0);
    }

    public static double convertir(double cantidad, UnidadMedida origen, UnidadMedida destino) {
        if (origen == destino) return cantidad;

        String clave = origen.name() + "->" + destino.name();
        if (conversiones.containsKey(clave)) {
            return cantidad * conversiones.get(clave);
        }

        String inversa = destino.name() + "->" + origen.name();
        if (conversiones.containsKey(inversa)) {
            return cantidad / conversiones.get(inversa);
        }

        throw new IllegalArgumentException("Conversión no definida entre " + origen + " y " + destino);
    }
}
