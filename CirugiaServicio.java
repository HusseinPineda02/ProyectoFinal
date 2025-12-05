package hospital.servicio;

import hospital.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CirugiaServicio {

    private Hospital hospital;

    public CirugiaServicio(Hospital hospital) {
        this.hospital = hospital;
    }
    public Cirugia programarCirugia(Cirugia c) {
        hospital.getCirugias().add(c);
      
        c.getPaciente().agregarCirugia(c);
        c.getCirujano().agregarCirugia(c);
        c.getQuirofano().setDisponible(false);

        return c;
    }
    public Cirugia buscarCirugia(int idCirugia) {
        for (Cirugia c : hospital.getCirugias()) {
            if (c.getIdCirugia() == idCirugia) {
                return c;
            }
        }
        return null;
    }
    public boolean cancelarCirugia(int idCirugia) {
        Cirugia c = buscarCirugia(idCirugia);
        if (c == null) return false;

        c.setEstado(Cirugia.Estado.CANCELADA);
        c.getQuirofano().setDisponible(true);
        return true;
    }
    public boolean marcarRealizada(int idCirugia) {
        Cirugia c = buscarCirugia(idCirugia);
        if (c == null) return false;

        c.setEstado(Cirugia.Estado.REALIZADA);
        c.getQuirofano().setDisponible(true);

        return true;
    }
    public List<Cirugia> cirugiasPaciente(String dni) {
        List<Cirugia> lista = new ArrayList<>();
        for (Cirugia c : hospital.getCirugias()) {
            if (c.getPaciente().getDni().equals(dni)) {
                lista.add(c);
            }
        }
        return lista;
    }
    public List<Cirugia> cirugiasDelDia(LocalDate fecha) {
        List<Cirugia> lista = new ArrayList<>();
        for (Cirugia c : hospital.getCirugias()) {
            if (c.getFecha().equals(fecha)) {
                lista.add(c);
            }
        }
        return lista;
    }
}
