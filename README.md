# Lab01-DOSW-Prev

## Reto 2 - Carrera en Paralelo

Implementación en un proyecto Maven ubicado en `Taller 1/Reto 2/` con estructura estándar `src/main/java` y `src/test/java`.

Clase principal: `CarreraParalela` con la función final `analizarListas(List<Integer> a, List<Integer> b)` que retorna un objeto `Resultados`:

Campos en `Resultados`:
- maximo
- minimo
- cantidad (elementos totales de ambas listas combinadas)
- mayorMultiploDe2 (true si max % 2 == 0)
- mayorDivisorDe2 (true si 2 % max == 0)
- cantidadPar
- cantidadImpar

Se usaron: `stream()`, `filter()`, `map()`, `collect()` y `flatMap()`.

### Paso a Paso (Simulación de Flujo de Trabajo)

👉 Estudiante A (Carril 2):
1. Crea rama `feature/reto2` y archivo base `CarreraParalela.java` con la estructura y paquete.
2. Commit inicial.
3. En subrama `carril2`, implementa lambdas para: mínimo (`CALCULAR_MINIMO`) y cantidad (`CALCULAR_CANTIDAD`). Documenta ejemplo en README.
4. Ejemplo (mínimo + cantidad):
	- Entrada: `[5, 2, 9]` → mínimo = 2, cantidad = 3.

👉 Estudiante B (Carril 1):
1. Desde `feature/reto2` crea subrama `carril1`.
2. Implementa lambda de máximo (`CALCULAR_MAXIMO`).
3. Sube cambios sin borrar la rama.
4. Ejemplo (máximo):
	- Entrada: `[4, 7, 10]` → máximo = 10.

👉 Ambos (Unificación y Choques):
1. Crean misma función con igual firma para unificar → `analizarListas(...)` causando conflicto y resolviéndolo.
2. Primer merge: se combinan máximo, mínimo y cantidad en `Resultados`.
3. Segunda vuelta:
	- Estudiante B: agrega si el mayor es múltiplo de 2 (ternario → se dejó como expresión booleana directa).
	- Estudiante A: agrega si el mayor es divisor de 2 (2 % max == 0).
4. Tercer choque:
	- Estudiante B: agrega `cantidadPar`.
	- Estudiante A: agrega `cantidadImpar`.
5. Merge final en `feature/reto2` dejando ambos flags para reflejar el historial.
6. Se añaden pruebas JUnit (`CarreraParalelaTest`).

### Ejemplo Final

Entrada:
- Lista 1: `[5, 2, 9]`
- Lista 2: `[4, 2, 7, 10]`

Salida (`toString()`):
```
Resultados{max=10, min=2, cantidad=7, mayorMultiploDe2=true, mayorDivisorDe2=false, cantidadPar=false, cantidadImpar=true}
```

### Cómo ejecutar (desde la carpeta del reto)

Ir a la carpeta:
```
cd "Taller 1/Reto 2"
```
Compilar y ejecutar pruebas:
```
mvn clean install
```
Ejecutar manualmente:
```
java -cp target/carrera-paralela-1.0.0-SNAPSHOT.jar com.lab.retos.CarreraParalela
```

---

## Reto 3

### Paso a Paso del Reto

1. Estudiante A (feature/reto3_builder): método con StringBuilder que recibe un mensaje y lo repite 3 veces con espacio.

2. Estudiante B (feature/reto3_buffer): método con StringBuffer que recibe un mensaje y lo invierte.

3. Primer Choque (merge conflict): debemos unificar en una sola función que primero repita el mensaje 3 veces y luego lo invierta, tenemos que resolver el econflicto en el merge

## Reto 6

### Paso a Paso del reto 

1. Estudiante A: crea la rama feature/reto4_fragmento1 y programa su switch con los comandos:
SALUDAR, DESPEDIR, CANTAR, DANZAR.

2. Estudiante B: crea la rama feature/reto4_fragmento2 y programa su switch con los comandos:
BROMEAR, GRITAR, SUSURRAR, ANALIZAR.

3. Merge conflict: al unir en feature, tendremos hecho un método con el mismo nombre (ejecutarComando), por lo que habrá conflicto.

4. Resolución: dejamos un único switch + un Map<String, Runnable> que asigna los comandos a sus acciones con lambdas.

## Reto 4 - Tesoro de las Llaves Duplicadas

Proyecto Maven en `Taller 1/Reto 4`.

Objetivo: Resolver duplicados de claves provenientes de dos fuentes (HashMap y Hashtable) simulando trabajo paralelo y conflictos de merge.

### Flujo (Simulación Roles)

👉 Estudiante A:
1. Función `construirHashMap(List<Par>)` que ignora claves duplicadas (conserva el primer valor) usando `HashMap` y `putIfAbsent`.
2. Documenta ejemplo.
3. Agrega `clavesMayusculas(Map)` para transformar claves a mayúsculas.

👉 Estudiante B:
1. Función `construirHashtable(List<Par>)` equivalente pero con `Hashtable` (sincronizada).
2. Documenta ejemplo.
3. Agrega `ordenarAsc(Map)` para ordenar por clave (usa `TreeMap`).

👉 Ambos (Conflicto y Unificación):
1. Crean el mismo método `combinarTesoroFinal(...)` produciendo conflicto de merge.
2. Resolución final: priorizar valores del `Hashtable` en conflicto de clave.
3. Aplicar transformaciones: claves mayúsculas + orden ascendente.
4. Imprimir con `stream()`, `map()`, `sorted()`, `Collectors.toMap()`.

### API Principal

`combinarTesoroFinal(List<Par> entradasHashMap, List<Par> entradasHashtable)` → retorna `Map<String,String>` final (claves mayúsculas, orden ascendente, valores del Hashtable prevalecen).

### Ejemplo

Entradas:
```
HashMap (A): [ ("oro","barra"), ("cofre","madera") ]
Hashtable (B): [ ("cofre","acero"), ("llave","hierro") ]
```
Proceso:
1. HashMap ignora duplicados.
2. Hashtable ignora duplicados.
3. Merge prioriza valor de `cofre -> acero`.
4. Claves a mayúsculas y orden ascendente.

Salida (impresión):
```
COFRE=acero
LLAVE=hierro
ORO=barra
```

### Ejecución

Desde la carpeta del reto:
```
cd "Taller 1/Reto 4"
mvn clean test
```
Ejecución manual:
```
java -cp target/tesoro-llaves-1.0.0-SNAPSHOT.jar com.lab.retos.TesoroLlaves
```

### Requisitos Cubiertos
- HashMap y Hashtable.
- `stream()`, `map()`, `sorted()`, `Collectors.toMap()`.
- Conflicto simulado (método unificado) con prioridad definida.

## Reto 5 - Batalla de Conjuntos

Proyecto Maven en `Taller 1/Reto 5`.

Objetivo: Dos estudiantes generan conjuntos con reglas distintas y luego se unifican ordenadamente.

👉 Estudiante A:
1. Método `generarArenaHashSet(int cantidad, int limite, long seed)` → HashSet con aleatorios; elimina múltiplos de 3 vía `stream().filter()`.
2. Ejemplo: con seed 7, cantidad 12, límite 30 se generan valores (previos) y tras filtrar quedan solo los no múltiplos de 3.

👉 Estudiante B:
1. Método `generarArenaTreeSet(int cantidad, int limite, long seed)` → TreeSet ordenado; elimina múltiplos de 5.
2. Ejemplo: con mismos parámetros elimina valores 0,5,10,15,20,25...

👉 Ambos:
1. Método común `unificar(Set a, Set b)` → TreeSet unión sin duplicados.
2. Impresión formateada con `imprimir(TreeSet)` usando `stream().map().joining()` en formato: "Número en arena: X".
3. Conflicto hipotético: firma de `unificar` y formato de impresión; resolución deja versión final mostrada.

### Ejemplo Final
```
Set A (sin múltiplos 3):  [2, 4, 7, 8, 11, 14, 17, 19, 20, ...]
Set B (sin múltiplos 5):  [1, 2, 4, 7, 8, 11, 13, 14, 16, ...]
Unión ordenada:
Número en arena: 1
Número en arena: 2
Número en arena: 4
Número en arena: 7
...
```

### Ejecución
```
cd "Taller 1/Reto 5"
mvn clean test
java -cp target/batalla-conjuntos-1.0.0-SNAPSHOT.jar com.lab.retos.BatallaConjuntos
```

### Requisitos Cubiertos
- HashSet, TreeSet.
- `stream().filter()` y expresiones lambda.
- Ejemplo documentado y JavaDoc en clase.