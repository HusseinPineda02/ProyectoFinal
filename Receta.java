import java.time.LocalDate;
import java.util.ArrayList;

public class Receta {

    private String idReceta;
    private Paciente paciente;
    private Doctor doctor;
    private LocalDate fecha;
    private String indicaciones;
    private ArrayList<MedPrescrito> medicamentos;

    public Receta(String idReceta, Paciente paciente, Doctor doctor, LocalDate fecha, String indicaciones) {
        this.idReceta = idReceta;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.indicaciones = indicaciones;
        this.medicamentos = new ArrayList<>();
    }

    public void generarReceta(MedPrescrito med) {
        medicamentos.add(med);
    }

    public void eliminarReceta(String idMedicamento) {
        medicamentos.removeIf(m -> m.getMedicamento().getIdMed().equals(idMedicamento));
    }
    
    public void modificarIndicaciones(String nuevasIndicaciones) {
        this.indicaciones = nuevasIndicaciones;
    }

    public void mostrarReceta() {
        System.out.println("=== RECETA MÉDICA ===");
        System.out.println("ID Receta: " + idReceta);
        System.out.println("Fecha: " + fecha);
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Doctor: " + doctor.getNombre());
        System.out.println("Indicaciones: " + indicaciones);
        System.out.println("Medicamentos:");

        for (MedPrescrito m : medicamentos) {
            System.out.println("- " + m.getMedicamento().getNombre() +
                               " | Dosis: " + m.getDosis() +
                               " | Frecuencia: " + m.getFrecuencia());
        }
    }

    public String getIdReceta() { 
        return idReceta; 
    }
    public Paciente getPaciente() {
        return paciente; 
    }
    public Doctor getDoctor() { 
        return doctor; 
    }
    public LocalDate getFecha() { 
        return fecha; 
    }
    public String getIndicaciones() { 
        return indicaciones;
    }
    public ArrayList<MedPrescrito> getMedicamentos() { 
        return medicamentos; 
    }

    public void setIdReceta(String idReceta) { 
        this.idReceta = idReceta;
    }
    public void setPaciente(Paciente paciente) { 
        this.paciente = paciente; 
    }
    public void setDoctor(Doctor doctor) { 
        this.doctor = doctor; 
    }
    public void setFecha(LocalDate fecha) { 
        this.fecha = fecha;
    }
    public void setIndicaciones(String indicaciones) { 
        this.indicaciones = indicaciones;
    }
    public void setMedicamentos(ArrayList<MedPrescrito> medicamentos) { 
        this.medicamentos = medicamentos; 
    }
}

 
