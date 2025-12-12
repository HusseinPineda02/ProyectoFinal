package hospital.modelo;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario {

    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario(DayOfWeek dia, LocalTime inicio, LocalTime fin) {
        this.dia = dia;
        this.horaInicio = inicio;
        this.horaFin = fin;
    }

    public boolean cubre(LocalTime hora) {
        return !hora.isBefore(horaInicio) && !hora.isAfter(horaFin);
    }

    public DayOfWeek getDia() { return dia; }
}