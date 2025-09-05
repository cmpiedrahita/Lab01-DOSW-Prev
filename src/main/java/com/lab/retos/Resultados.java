package com.lab.retos;

/**
 * DTO inmutable que concentra los resultados finales del análisis de listas.
 * Se modela como record para reducir boilerplate.
 */
public record Resultados(
        int maximo,
        int minimo,
        long cantidad,
        boolean mayorMultiploDe2,
        boolean mayorDivisorDe2,
        boolean cantidadPar,
        boolean cantidadImpar
) {
    @Override
    public String toString() {
        return "Resultados{" +
                "max=" + maximo +
                ", min=" + minimo +
                ", cantidad=" + cantidad +
                ", mayorMultiploDe2=" + mayorMultiploDe2 +
                ", mayorDivisorDe2=" + mayorDivisorDe2 +
                ", cantidadPar=" + cantidadPar +
                ", cantidadImpar=" + cantidadImpar +
                '}';
    }
}
