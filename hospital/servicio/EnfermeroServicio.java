package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class EnfermeroServicio {

    private Hospital hospital;

    public EnfermeroServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarEnfermero(Enfermero e) {
        hospital.getEnfermeros().add(e);
    }

    public Enfermero buscarEnfermero(int idTrabajador) {
        for (Enfermero e : hospital.getEnfermeros()) {
            if (e.getIdTrabajador() == idTrabajador) return e;
        }
        return null;
    }

    public boolean eliminarEnfermero(int idTrabajador) {
        return hospital.getEnfermeros()
                .removeIf(e -> e.getIdTrabajador() == idTrabajador);
    }

    public boolean actualizarEnfermero(int idTrabajador,
                                       String nombre, String apP, String apM,
                                       double salario, String turno) {
        Enfermero e = buscarEnfermero(idTrabajador);
        if (e == null) return false;

        e.setNombre(nombre);
        e.setApellidoP(apP);
        e.setApellidoM(apM);
        e.setSalario(salario);
        e.setTurno(turno);

        return true;
    }

    public List<Enfermero> listarEnfermeros() {
        return new ArrayList<>(hospital.getEnfermeros());
    }
}