# Lab01-DOSW-Prev

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