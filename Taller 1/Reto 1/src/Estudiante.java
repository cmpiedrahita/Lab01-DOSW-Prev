/**
 * Clase que representa a un Estudiante con sus atributos básicos.
 * Contiene nombre, edad, correo institucional y semestre.
 */
public class Estudiante {
    private final String nombre;
    private final int edad;
    private final String correo;
    private final int semestre;
    /**
     * Constructor de la clase Estudiante.
     * @param nombre Nombre completo del estudiante
     * @param edad Edad del estudiante
     * @param correo Correo institucional
     * @param semestre Semestre que cursa
     */
    public Estudiante(String nombre, int edad, String correo, int semestre){
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.semestre = semestre;
    }
    // Métodos de acceso
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public String getCorreo() {
        return correo;
    }
    public int getSemestre() {
        return semestre;
    }
}
