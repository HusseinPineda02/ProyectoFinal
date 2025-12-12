package hospital.servicio;

import hospital.modelo.*;
import java.util.List;

public class HistoriaClinicaServicio {

    private Hospital hospital;

    public HistoriaClinicaServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public HistoriaClinica obtenerHistoriaPaciente(String dniPaciente) {
        Paciente p = buscarPaciente(dniPaciente);
        return (p != null) ? p.getHistoriaClinica() : null;
    }

    private Paciente buscarPaciente(String dni) {
        for (Paciente p : hospital.getPacientes()) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    public RegistroClinico agregarRegistro(String dni, RegistroClinico r) {
        Paciente p = buscarPaciente(dni);
        if (p == null || p.getHistoriaClinica() == null) return null;

        p.getHistoriaClinica().agregarRegistro(r);
        return r;
    }

    public List<RegistroClinico> listarRegistros(String dniPaciente) {
        HistoriaClinica h = obtenerHistoriaPaciente(dniPaciente);
        return (h != null) ? h.getRegistros() : List.of();
    }
}