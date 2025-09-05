package com.lab.retos;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reto 2: Carrera en Paralelo.
 * Simula trabajo en paralelo de dos ramas que calculan propiedades sobre listas de enteros
 * y unifican en {@link Resultados}.
 */
public final class CarreraParalela {

    public static final Function<List<Integer>, Integer> CALCULAR_MINIMO = lista ->
            Optional.ofNullable(lista).orElseThrow(() -> new IllegalArgumentException("Lista null"))
                    .stream().filter(Objects::nonNull).min(Integer::compareTo)
                    .orElseThrow(() -> new IllegalArgumentException("Lista vacía"));

    public static final Function<List<Integer>, Long> CALCULAR_CANTIDAD = lista ->
            Optional.ofNullable(lista).map(l -> l.stream().filter(Objects::nonNull).count()).orElse(0L);

    public static final Function<List<Integer>, Integer> CALCULAR_MAXIMO = lista ->
            Optional.ofNullable(lista).orElseThrow(() -> new IllegalArgumentException("Lista null"))
                    .stream().filter(Objects::nonNull).max(Integer::compareTo)
                    .orElseThrow(() -> new IllegalArgumentException("Lista vacía"));

    private CarreraParalela() {}

    /**
     * Une ambas listas y calcula métricas finales.
     */
    public static Resultados analizarListas(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> combinada = Stream.of(lista1, lista2)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        if (combinada.isEmpty()) throw new IllegalArgumentException("Se requiere al menos un número");

        int maximo = combinada.stream().max(Integer::compareTo).orElseThrow();
        int minimo = combinada.stream().min(Integer::compareTo).orElseThrow();
        long cantidad = combinada.size();
        boolean mayorMultiploDe2 = maximo % 2 == 0;
        boolean mayorDivisorDe2 = 2 % maximo == 0;
        boolean cantidadPar = cantidad % 2 == 0;
        boolean cantidadImpar = cantidad % 2 != 0;
        return new Resultados(maximo, minimo, cantidad, mayorMultiploDe2, mayorDivisorDe2, cantidadPar, cantidadImpar);
    }

    public static void main(String[] args) {
        Resultados r = analizarListas(Arrays.asList(5,2,9), Arrays.asList(4,2,7,10));
        System.out.println(r);
    }
}
