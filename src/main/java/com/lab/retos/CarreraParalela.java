package com.lab.retos;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reto 2: Carrera en Paralelo.
 * <p>
 * Simula el trabajo en paralelo de dos "carriles" (ramas) que calculan propiedades de listas de enteros
 * y al final unifican los resultados en un objeto {@link Resultados}.
 * <br><br>
 * Requisitos cubiertos:
 * - Uso de stream(), map(), filter(), collect().
 * - Unificación de máximo, mínimo, cantidad y derivaciones lógicas (par/impar, múltiplo/divisor de 2).
 * - Ejemplo de funciones (lambdas) independientes creadas por cada estudiante antes de la unificación.
 */
public final class CarreraParalela {

    /** Lambda (Carril 2 - Estudiante A): calcula el mínimo de una lista. */
    public static final Function<List<Integer>, Integer> CALCULAR_MINIMO = lista ->
            Optional.ofNullable(lista)
                    .orElseThrow(() -> new IllegalArgumentException("La lista no puede ser null"))
                    .stream()
                    .filter(Objects::nonNull)
                    .min(Integer::compareTo)
                    .orElseThrow(() -> new IllegalArgumentException("La lista no puede estar vacía"));

    /** Lambda (Carril 2 - Estudiante A): calcula la cantidad de elementos de una lista. */
    public static final Function<List<Integer>, Long> CALCULAR_CANTIDAD = lista ->
            Optional.ofNullable(lista)
                    .map(l -> l.stream().filter(Objects::nonNull).count())
                    .orElse(0L);

    /** Lambda (Carril 1 - Estudiante B): calcula el máximo de una lista. */
    public static final Function<List<Integer>, Integer> CALCULAR_MAXIMO = lista ->
            Optional.ofNullable(lista)
                    .orElseThrow(() -> new IllegalArgumentException("La lista no puede ser null"))
                    .stream()
                    .filter(Objects::nonNull)
                    .max(Integer::compareTo)
                    .orElseThrow(() -> new IllegalArgumentException("La lista no puede estar vacía"));

    private CarreraParalela() {
        // Utility class
    }

    /**
     * Función final unificada tras la resolución de conflictos.
     * <p>
     * Dado dos listados, los combina y retorna un objeto {@link Resultados} con:
     * máximo, mínimo, cantidad total, si el mayor es múltiplo de 2, si el mayor es divisor de 2,
     * y si la cantidad es par o impar (se exponen ambos flags para reflejar el "choque" de ramas).
     *
     * @param lista1 primera lista (puede ser null, se ignora)
     * @param lista2 segunda lista (puede ser null, se ignora)
     * @return resultados calculados
     * @throws IllegalArgumentException si no hay elementos combinados
     */
    public static Resultados analizarListas(List<Integer> lista1, List<Integer> lista2) {
        // Combinar listas (uso de stream(), filter(), flatMap(), collect())
        List<Integer> combinada = Stream.of(lista1, lista2)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(Objects::nonNull) // limpieza
                .map(Integer::valueOf)    // demostración de map()
                .collect(Collectors.toList());

        if (combinada.isEmpty()) {
            throw new IllegalArgumentException("Se requiere al menos un número en las listas");
        }

        int maximo = combinada.stream().max(Integer::compareTo).orElseThrow();
        int minimo = combinada.stream().min(Integer::compareTo).orElseThrow();
        long cantidad = combinada.size();

        boolean mayorMultiploDe2 = maximo % 2 == 0;                 // Estudiante B (segunda vuelta)
        boolean mayorDivisorDe2 = 2 % maximo == 0;                  // Estudiante A (segunda vuelta)
        boolean cantidadPar = cantidad % 2 == 0;                    // Estudiante B (tercer choque)
        boolean cantidadImpar = cantidad % 2 != 0;                  // Estudiante A (tercer choque)

        return new Resultados(maximo, minimo, cantidad, mayorMultiploDe2, mayorDivisorDe2, cantidadPar, cantidadImpar);
    }

    /**
     * Ejemplo de uso rápido en consola.
     */
    public static void main(String[] args) {
        Resultados r = analizarListas(Arrays.asList(5, 2, 9), Arrays.asList(4, 2, 7, 10));
        System.out.println(r);
    }
}
