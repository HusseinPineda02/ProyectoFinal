import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Persona {
    private LocalDate fechaNacimiento;
    private Historia historia;
    private ArrayList<Cita> citas;

    public Paciente(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento) {
        super(nombre, apellidoP, apellidoM, dni, fechaNacimiento);
        this.citas = new ArrayList<>();
    }

    public void reservarCita(Cita cita) {
        citas.add(cita);
    }

    // Getters y Setters
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Historia getHistoria() { 
        return historia;
    }
    public void setHistoria(Historia historia) {
        this.historia = historia;
    }
    public ArrayList<Cita> getCitas() {
        return citas;
    }
    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }
}