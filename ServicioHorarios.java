import java.util.ArrayList;

public class ServicioHorarios {
    private Hospital hospital;

    public ServicioHorarios(Hospital hospital) {
        this.hospital = hospital;
    }

    public void asignarHorariosTrab(String idTrabajador, Horario horario) {
        Trabajador t = hospital.getServicioTrabajadores().buscarTrabajador(idTrabajador);
        if(t instanceof Doctor) {
            ((Doctor) t).getHorarios().add(horario);
        }
    }

    public ArrayList<Horario> horariosTrabajador(String idTrabajador) {
        Trabajador t = hospital.getServicioTrabajadores().buscarTrabajador(idTrabajador);
        if(t instanceof Doctor) return ((Doctor) t).getHorarios();
        return new ArrayList<>();
    }

    public boolean disponible(String idTrabajador, java.time.LocalDate fecha, java.time.LocalTime hora) {
        ArrayList<Horario> horarios = horariosTrabajador(idTrabajador);
        for(Horario h : horarios) {
            if(h.getDia().equals(fecha.getDayOfWeek().toString())) {
                if(!hora.isBefore(h.getHoraInicio()) && !hora.isAfter(h.getHoraFin())) return true;
            }
        }
        return false;
    }
}