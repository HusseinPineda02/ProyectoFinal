import java.time.LocalDate;

public abstract class Persona {
    private String nombre, apellidoP, apellidoM, dni;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento){
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApePaterno(String apellidoP){
        this.apellidoP= apellidoP;
    }
    public void setApeMaterno(String apellidoM){
        this.apellidoM= apellidoM;
    }
   public void setDni(String dni){
        this.dni = dni;
    }
   public String getNombre(){
        return nombre;
    }
    public String getapellidoP(){
        return apellidoP;
    }
    public String getapellidoM(){
        return apellidoM;
    } 
    public String getDni(){
        return dni;
    }
    public String mostrarInfoPersonal() {
        return nombre + " " + apellidoP + " " + apellidoM + " | DNI: " + dni;
    }



}
