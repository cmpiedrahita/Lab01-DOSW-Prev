import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase principal que demuestra el uso de Streams, Lambdas y Collectors en Java
 * para construir un mensaje de bienvenida a partir de una lista de estudiantes.
 */
public class Main {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = Arrays.asList(
                new Estudiante("Carlos Piedrahita",19,"carlos.piedrahita-a@mail.escuelaing.edu.co",6),
                new Estudiante("Marianella Polo",19,"marianella.polo-p@mail.escuelaing.edu.co",6)
        );
        String saludoInicial = estudiantes.stream().map(e -> String.format("%s estudiante de la escuela de %d semestre de %d años",
                e.getNombre(), e.getSemestre(), e.getEdad())).collect(Collectors.joining(" y ","¡Hola Bienvenidos! Nosotros somos la pareja conformada por ",","));
        String segundaParte = estudiantes.stream().map(Estudiante :: getCorreo).collect(Collectors.joining(" y ","nuestros correos institucionales son ",""));
        String resultado = saludoInicial + segundaParte;
        System.out.println(resultado);
    }
}