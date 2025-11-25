import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Trabajador {

    String especialidad;
    private ArrayList<Horario> horarios;

    public Doctor(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento, String idTrabajador, double salario, String especialidad) {
        super(nombre, apellidoP, apellidoM, dni, fechaNacimiento, idTrabajador, salario);
        this.especialidad = especialidad;
        this.horarios = new ArrayList<>();
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    public void setHorarios(ArrayList<Horario> horarios) { 
        this.horarios = horarios;
    }
    public String getEspecialidad(){
        return especialidad;
    }
    public ArrayList<Horario> getHorario(){
        return horarios;
    }
    
    public void asignarCita(Cita cita) {
     
    }

    public void verHistorialPaciente(Paciente paciente) {
    
    }

    @Override
    public void registrarEntrada() {

    }
    @Override
    public void registrarSalida() {

    }
    @Override
    public double calcularSalarioMensual() {
        return this.getSalario();
    }

}