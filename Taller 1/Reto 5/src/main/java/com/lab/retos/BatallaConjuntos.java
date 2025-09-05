package com.lab.retos;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Reto 5: Batalla de Conjuntos.
 * <p>Dos estudiantes generan arenas de números aleatorios y aplican reglas de filtrado distintas
 * usando estructuras de conjunto diferentes; luego se unifican.</p>
 * <ul>
 *   <li>Estudiante A: HashSet, elimina múltiplos de 3.</li>
 *   <li>Estudiante B: TreeSet (orden natural), elimina múltiplos de 5.</li>
 *   <li>Unificación: union en TreeSet, sin duplicados, impresión formateada.</li>
 * </ul>
 */
public final class BatallaConjuntos {

    private BatallaConjuntos() {}

    /**
     * Genera un HashSet de tamaño hasta 'cantidad' con números aleatorios en [0, limite) eliminando múltiplos de 3.
     * Duplicados implícitamente ignorados por la naturaleza del Set.
     * @param cantidad cantidad tentativa de números a generar
     * @param limite límite superior exclusivo
     * @param seed semilla para reproducibilidad
     * @return conjunto filtrado
     */
    public static Set<Integer> generarArenaHashSet(int cantidad, int limite, long seed) {
        Random rnd = new Random(seed);
        Set<Integer> base = new HashSet<>();
        for (int i = 0; i < cantidad; i++) {
            base.add(rnd.nextInt(limite));
        }
        // Eliminar múltiplos de 3 usando stream + filter + colecta a nuevo Set
        return base.stream()
                .filter(n -> n % 3 != 0)
                .collect(Collectors.toSet());
    }

    /**
     * Genera un TreeSet (ordenado) con números aleatorios eliminando múltiplos de 5.
     * @param cantidad cantidad tentativa
     * @param limite límite superior exclusivo
     * @param seed semilla
     * @return TreeSet filtrado y ordenado
     */
    public static NavigableSet<Integer> generarArenaTreeSet(int cantidad, int limite, long seed) {
        Random rnd = new Random(seed);
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < cantidad; i++) {
            set.add(rnd.nextInt(limite));
        }
        return set.stream()
                .filter(n -> n % 5 != 0)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Unifica las arenas en un TreeSet (orden natural). Duplicados se descartan automáticamente.
     * @param hashSetArena arena del HashSet (A)
     * @param treeSetArena arena del TreeSet (B)
     * @return TreeSet unión
     */
    public static NavigableSet<Integer> unificar(Set<Integer> hashSetArena, Set<Integer> treeSetArena) {
        TreeSet<Integer> union = new TreeSet<>();
        if (hashSetArena != null) union.addAll(hashSetArena);
        if (treeSetArena != null) union.addAll(treeSetArena);
        return union;
    }

    /**
     * Imprime cada número en formato "Número en arena: X" usando stream + map + joining.
     * @param numeros conjunto ordenado
     * @return representación multilínea
     */
    public static String imprimir(NavigableSet<Integer> numeros) {
        return numeros.stream()
                .map(n -> "Número en arena: " + n)
                .collect(Collectors.joining("\n"));
    }

    /** Ejemplo rápido en main. */
    public static void main(String[] args) {
        Set<Integer> a = generarArenaHashSet(12, 30, 7L); // A elimina múltiplos de 3
        Set<Integer> b = generarArenaTreeSet(12, 30, 7L); // B elimina múltiplos de 5
        NavigableSet<Integer> union = unificar(a, b);
        System.out.println(imprimir(union));
    }
}
