package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionServicio {

    private Hospital hospital;

    public HabitacionServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarHabitacion(Habitacion h) {
        hospital.getHabitaciones().add(h);
    }

    public Habitacion buscarHabitacion(int id) {
        for (Habitacion h : hospital.getHabitaciones()) {
            if (h.getIdHabitacion() == id) return h;
        }
        return null;
    }

    public List<Habitacion> listarHabitaciones() {
        return new ArrayList<>(hospital.getHabitaciones());
    }

    public List<Habitacion> listarPorTipo(TipoHabitacion tipo) {
        List<Habitacion> lista = new ArrayList<>();
        for (Habitacion h : hospital.getHabitaciones()) {
            if (h.getTipo() == tipo) lista.add(h);
        }
        return lista;
    }

    public boolean ocupar(int id) {
        Habitacion h = buscarHabitacion(id);
        if (h == null || !h.isDisponible()) return false;
        h.setDisponible(false);
        return true;
    }

    public boolean liberar(int id) {
        Habitacion h = buscarHabitacion(id);
        if (h == null || h.isDisponible()) return false;
        h.setDisponible(true);
        return true;
    }
}