package hospital.modelo;

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected String apellidoP;
    protected String apellidoM;

    public Persona(String dni, String nombre, String apellidoP, String apellidoM) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidoP + " " + apellidoM;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (DNI: " + dni + ")";
    }
}