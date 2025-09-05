import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Reto3Cueva {
    public static String ecoEspecial(String mensaje) {
        String repetido = IntStream.range(0, 3)
                .mapToObj(i -> mensaje)
                .collect(Collectors.joining(" "));
        StringBuilder builder = new StringBuilder(repetido);
        StringBuffer buffer = new StringBuffer(builder.toString());
        return buffer.reverse().toString();
    }
    public static void main(String[] args) {
        java.util.function.Function<String, String> ecoLambda = Reto3Cueva::ecoEspecial;
        String mensaje = "HolaCueva";
        String resultado = ecoLambda.apply(mensaje);
        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Eco especial: " + resultado);
    }
}