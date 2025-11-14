import java.util.Date;

public class Paciente {
    private int dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;

    public Paciente(int dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public Date getFechaNacimiento() { return fechaNacimiento; }

    public void setDni(int dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidoPaterno(String ap) { this.apellidoPaterno = ap; }
    public void setApellidoMaterno(String am) { this.apellidoMaterno = am; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public void reservarCita() {
    }
}
