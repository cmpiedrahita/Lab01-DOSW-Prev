package com.lab.retos;

/** DTO inmutable de resultados del Reto 2 */
public record Resultados(
        int maximo,
        int minimo,
        long cantidad,
        boolean mayorMultiploDe2,
        boolean mayorDivisorDe2,
        boolean cantidadPar,
        boolean cantidadImpar
) {
    @Override public String toString() {
        return "Resultados{" +
                "max=" + maximo +
                ", min=" + minimo +
                ", cantidad=" + cantidad +
                ", mayorMultiploDe2=" + mayorMultiploDe2 +
                ", mayorDivisorDe2=" + mayorDivisorDe2 +
                ", cantidadPar=" + cantidadPar +
                ", cantidadImpar=" + cantidadImpar + '}';
    }
}
