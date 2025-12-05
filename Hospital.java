package hospital.modelo;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private String nombre;
    private String direccion;

    private List<Paciente> pacientes;
    private List<Doctor> doctores;
    private List<Enfermero> enfermeros;
    private List<Administrativo> administrativos;

    private List<Cita> citas;
    private List<Cirugia> cirugias; 
    private List<Medicamento> medicamentos;
    private List<Insumo> insumos;

    private List<Habitacion> habitaciones;

    public Hospital(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        this.pacientes = new ArrayList<>();
        this.doctores = new ArrayList<>();
        this.enfermeros = new ArrayList<>();
        this.administrativos = new ArrayList<>();
        this.citas = new ArrayList<>();

        this.cirugias = new ArrayList<>();

        this.medicamentos = new ArrayList<>();
        this.insumos = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Paciente> getPacientes() { return pacientes; }
    public List<Doctor> getDoctores() { return doctores; }
    public List<Enfermero> getEnfermeros() { return enfermeros; }
    public List<Administrativo> getAdministrativos() { return administrativos; }
    public List<Cita> getCitas() { return citas; }

    public List<Cirugia> getCirugias() {
        return cirugias;
    }

    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Insumo> getInsumos() { return insumos; }
    public List<Habitacion> getHabitaciones() { return habitaciones; }

    public void agregarPaciente(Paciente p) { pacientes.add(p); }
    public void agregarDoctor(Doctor d) { doctores.add(d); }
    public void agregarEnfermero(Enfermero e) { enfermeros.add(e); }
    public void agregarAdministrativo(Administrativo a) { administrativos.add(a); }
    public void agregarCita(Cita c) { citas.add(c); }

    public void agregarCirugia(Cirugia c) {
        cirugias.add(c);
    }

    public void agregarMedicamento(Medicamento m) { medicamentos.add(m); }
    public void agregarInsumo(Insumo i) { insumos.add(i); }
    public void agregarHabitacion(Habitacion h) { habitaciones.add(h); }

    @Override
    public String toString() {
        return "Hospital " + nombre + " - " + direccion +
               "\nPacientes: " + pacientes.size() +
               ", Doctores: " + doctores.size() +
               ", Enfermeros: " + enfermeros.size() +
               ", Habitaciones: " + habitaciones.size();
    }

}
