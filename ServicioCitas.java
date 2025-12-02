import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ServicioCitas {
    private Hospital hospital;

    public ServicioCitas(Hospital hospital) {
        this.hospital = hospital;
    }
    public void generarCita(Cita cita) {
        cita.getAmbiente().agregarCita(cita);
        System.out.println("Cita #" + cita.getIdCita() + " generada con éxito.");
    }
    public void cancelarCita(String idCitaStr) {
        int id = Integer.parseInt(idCitaStr);
        for (Ambiente a : hospital.getConsultorios()) {
            if (a.eliminarCita(id)) {
                System.out.println("Cita #" + id + " cancelada.");
                return;
            }
        }
        for (Ambiente a : hospital.getSalas()) {
            if (a.eliminarCita(id)) {
                System.out.println("Cita #" + id + " cancelada.");
                return;
            }
        }
        System.out.println("Cita no encontrada.");
    }
    public void reprogramarCita(String idCitaStr, LocalDate fecha, LocalTime hora) {
        Cita cita = buscarCita(idCitaStr);
        if (cita != null) {
            cita.setFechaCita(java.sql.Date.valueOf(fecha));
            cita.setHora(hora.toString());
            System.out.println("Cita #" + idCitaStr + " reprogramada para " + fecha + " " + hora);
        } else {
            System.out.println("Cita no encontrada.");
        }
    }
    public Cita buscarCita(String idCitaStr) {
        int id = Integer.parseInt(idCitaStr);
        for (Ambiente a : hospital.getConsultorios()) {
            for (Cita c : a.getCitas()) {
                if (c.getIdCita() == id) return c;
            }
        }
        for (Ambiente a : hospital.getSalas()) {
            for (Cita c : a.getCitas()) {
                if (c.getIdCita() == id) return c;
            }
        }
        return null;
    }
    public ArrayList<Cita> mostrarCitasDia(LocalDate fecha) {
        ArrayList<Cita> delDia = new ArrayList<>();
        java.sql.Date sqlFecha = java.sql.Date.valueOf(fecha);
        for (Ambiente a : hospital.getConsultorios()) {
            for (Cita c : a.getCitas()) {
                if (c.getFechaCita().toString().equals(sqlFecha.toString())) {
                    delDia.add(c);
                }
            }
        }
        for (Ambiente a : hospital.getSalas()) {
            for (Cita c : a.getCitas()) {
                if (c.getFechaCita().toString().equals(sqlFecha.toString())) {
                    delDia.add(c);
                }
            }
        }
        return delDia;
    }
    public ArrayList<Cita> mostrarCitasPaciente(String dni) {
        ArrayList<Cita> citasPaciente = new ArrayList<>();
        Paciente p = hospital.getServicioPacientes().buscarPaciente(dni);
        if (p == null) return citasPaciente;

        for (Ambiente a : hospital.getConsultorios()) {
            for (Cita c : a.getCitas()) {
                if (c.getPaciente().getDni().equals(dni)) {
                    citasPaciente.add(c);
                }
            }
        }
        return citasPaciente;
    }
}
