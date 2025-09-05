package com.lab.retos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Reto 4: Tesoro de las Llaves Duplicadas.
 * <p>
 * Flujo simulado de dos estudiantes trabajando en paralelo:
 * <ul>
 *   <li>Estudiante A: construye HashMap ignorando claves duplicadas (conserva el primer valor) y luego transforma claves a mayúsculas.</li>
 *   <li>Estudiante B: construye Hashtable (sincronizada) y agrega lógica para ordenar claves ascendentemente antes de imprimir.</li>
 *   <li>Ambos: crean el mismo método de combinación generando conflicto; se resuelve priorizando valores del Hashtable
 *       y aplicando transformaciones: mayúsculas + orden ascendente.</li>
 * </ul>
 * Requisitos: HashMap, Hashtable, stream(), map(), sorted(), Collectors.toMap().
 */
public final class TesoroLlaves {

    /** Par clave-valor usado como entrada (simula registros sin estructura). */
    public record Par(String clave, String valor) {}

    private TesoroLlaves() {}

    /**
     * Estudiante A: Construye un HashMap ignorando claves duplicadas (conserva el primer valor observado).
     */
    public static Map<String,String> construirHashMap(List<Par> pares) {
        Map<String,String> mapa = new HashMap<>();
        if (pares == null) return mapa;
        pares.stream()
                .filter(Objects::nonNull)
                .filter(p -> p.clave() != null)
                .forEach(p -> mapa.putIfAbsent(p.clave(), p.valor()));
        return mapa;
    }

    /**
     * Estudiante B: Construye un Hashtable (sincronizado) ignorando claves duplicadas (primer valor).
     */
    public static Hashtable<String,String> construirHashtable(List<Par> pares) {
        Hashtable<String,String> tabla = new Hashtable<>();
        if (pares == null) return tabla;
        pares.stream()
                .filter(Objects::nonNull)
                .filter(p -> p.clave() != null)
                .forEach(p -> tabla.putIfAbsent(p.clave(), p.valor()));
        return tabla;
    }

    /**
     * Estudiante A (fase posterior): Convierte todas las claves a mayúsculas en una nueva copia.
     */
    public static Map<String,String> clavesMayusculas(Map<String,String> origen) {
        return origen.entrySet().stream()
                .map(e -> Map.entry(e.getKey().toUpperCase(Locale.ROOT), e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b)->a, LinkedHashMap::new));
    }

    /**
     * Estudiante B (fase posterior): Devuelve un mapa ordenado ascendentemente por clave (usando TreeMap para simplicidad).
     */
    public static Map<String,String> ordenarAsc(Map<String,String> origen) {
        return new TreeMap<>(origen); // TreeMap mantiene orden ascendente por clave
    }

    /**
     * Método final combinado tras resolución de conflictos.
     * <p>
     * Pasos:
     * <ol>
     *   <li>Construir HashMap y Hashtable independientes.</li>
     *   <li>Fusionar priorizando valores del Hashtable en conflictos.</li>
     *   <li>Transformar claves a mayúsculas.</li>
     *   <li>Ordenar ascendentemente las claves.</li>
     *   <li>Retornar LinkedHashMap preservando orden de iteración final.</li>
     * </ol>
     */
    public static Map<String,String> combinarTesoroFinal(List<Par> entradasHashMap, List<Par> entradasHashtable) {
        Map<String,String> mapa = construirHashMap(entradasHashMap);
        Hashtable<String,String> tabla = construirHashtable(entradasHashtable);

        // Fusionar: comenzar con HashMap, sobreescribir con valores del Hashtable (prioridad B)
        Map<String,String> fusion = new ConcurrentHashMap<>(mapa);
        tabla.forEach((k,v) -> fusion.put(k, v));

        // Stream para: mayúsculas -> ordenar -> recopilar preserving order
        return fusion.entrySet().stream()
                .map(e -> Map.entry(e.getKey().toUpperCase(Locale.ROOT), e.getValue())) // mayúsculas
                .sorted(Map.Entry.comparingByKey())                                     // orden ascendente
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->b, // merge function (no debería usarse aquí salvo duplicado post-mayúsculas)
                        LinkedHashMap::new
                ));
    }

    /**
     * Formatea el mapa final como líneas CLAVE=valor usando stream().
     */
    public static String imprimir(Map<String,String> mapa) {
        return mapa.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        List<Par> a = List.of(new Par("oro","barra"), new Par("cofre","madera"), new Par("oro","duplicado"));
        List<Par> b = List.of(new Par("llave","hierro"), new Par("cofre","acero"));
        Map<String,String> resultado = combinarTesoroFinal(a, b);
        System.out.println(imprimir(resultado));
    }
}
