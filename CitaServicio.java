package hospital.servicio;

import hospital.modelo.Cita;
import hospital.modelo.Doctor;
import hospital.modelo.Hospital;
import hospital.modelo.Paciente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitaServicio {

    private Hospital hospital;

    public CitaServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarCita(Cita c) {
        hospital.getCitas().add(c);
    }

    public Cita buscarCita(int idCita) {
        for (Cita c : hospital.getCitas()) {
            if (c.getIdCita() == idCita) {
                return c;
            }
        }
        return null;
    }

    public boolean actualizarCita(Cita c) {
        Cita existente = buscarCita(c.getIdCita());
        if (existente == null) return false;

        existente.setPaciente(c.getPaciente());
        existente.setDoctor(c.getDoctor());
        existente.setFecha(c.getFecha());
        existente.setHora(c.getHora());
        existente.setMotivo(c.getMotivo());
        existente.setEstado(c.getEstado());

        return true;
    }

    public boolean eliminarCita(int idCita) {
        Cita encontrada = buscarCita(idCita);
        if (encontrada != null) {
            return hospital.getCitas().remove(encontrada);
        }
        return false;
    }

    public boolean cancelarCita(int idCita) {
        Cita cita = buscarCita(idCita);
        if (cita == null) return false;

        cita.setEstado(Cita.Estado.CANCELADA);
        return true;
    }

    public boolean marcarAtendida(int idCita) {
        Cita cita = buscarCita(idCita);
        if (cita == null) return false;

        cita.setEstado(Cita.Estado.ATENDIDA);
        return true;
    }

    public List<Cita> citasPaciente(String dniPaciente) {
        List<Cita> lista = new ArrayList<>();
        for (Cita c : hospital.getCitas()) {
            Paciente p = c.getPaciente();
            if (p != null && p.getDni().equals(dniPaciente)) {
                lista.add(c);
            }
        }
        return lista;
    }

    public List<Cita> citasDoctor(int idDoctor) {
        List<Cita> lista = new ArrayList<>();
        for (Cita c : hospital.getCitas()) {
            Doctor d = c.getDoctor();
            if (d != null && d.getIdTrabajador() == idDoctor) {
                lista.add(c);
            }
        }
        return lista;
    }

    public List<Cita> citasDelDia(LocalDate fecha) {
        List<Cita> lista = new ArrayList<>();
        for (Cita c : hospital.getCitas()) {
            if (c.getFecha().equals(fecha)) {
                lista.add(c);
            }
        }
        return lista;
    }
}
