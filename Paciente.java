package hospital.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {

    private LocalDate fechaNacimiento;
    private String telefono;
    private String direccion;
    private HistoriaClinica historiaClinica;
    private List<Cita> citas;
    private List<Cirugia> cirugias;

    public Paciente(String dni, String nombre, String apellidoP, String apellidoM,
                    LocalDate fechaNacimiento, String telefono, String direccion) {
        super(dni, nombre, apellidoP, apellidoM);
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.citas = new ArrayList<>();
        this.cirugias = new ArrayList<>();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public List<Cirugia> getCirugias() {
        return cirugias;
    }

    public void agregarCita(Cita cita) {
        this.citas.add(cita);
    }

    public void agregarCirugia(Cirugia cirugia) {
        this.cirugias.add(cirugia);
    }

    @Override
    public String toString() {
        return super.toString() + " - Tel: " + telefono + ", Dir: " + direccion;
    }
}
