package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteServicio {

    private Hospital hospital;

    public PacienteServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarPaciente(Paciente p) {
        hospital.getPacientes().add(p);
    }

    public Paciente buscarPaciente(String dni) {
        for (Paciente p : hospital.getPacientes()) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    public boolean eliminarPaciente(String dni) {
        return hospital.getPacientes().removeIf(p -> p.getDni().equals(dni));
    }

    public boolean actualizarPaciente(String dni, String nuevoNombre,
                                      String apellidoP, String apellidoM,
                                      String direccion, String telefono) {
        Paciente p = buscarPaciente(dni);
        if (p == null) return false;

        p.setNombre(nuevoNombre);
        p.setApellidoP(apellidoP);
        p.setApellidoM(apellidoM);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        return true;
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(hospital.getPacientes());
    }

    public HistoriaClinica crearHistoriaSiNoExiste(Paciente p, int idHistoria) {
        if (p.getHistoriaClinica() == null) {
            HistoriaClinica h = new HistoriaClinica(idHistoria, p);
            p.setHistoriaClinica(h);
            return h;
        }
        return p.getHistoriaClinica();
    }

    public RegistroClinico agregarRegistro(Paciente p, RegistroClinico registro) {
        if (p.getHistoriaClinica() == null) return null;

        p.getHistoriaClinica().agregarRegistro(registro);
        return registro;
    }

    public HistoriaClinica obtenerHistoria(String dni) {
        Paciente p = buscarPaciente(dni);
        return (p != null) ? p.getHistoriaClinica() : null;
    }

    public List<RegistroClinico> registrosPaciente(String dni) {
        HistoriaClinica h = obtenerHistoria(dni);
        return (h != null) ? h.getRegistros() : new ArrayList<>();
    }
}