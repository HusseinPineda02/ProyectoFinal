package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorServicio {

    private Hospital hospital;

    public DoctorServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarDoctor(Doctor d) {
        hospital.getDoctores().add(d);
    }

    public Doctor buscarDoctor(int idTrabajador) {
        for (Doctor d : hospital.getDoctores()) {
            if (d.getIdTrabajador() == idTrabajador) return d;
        }
        return null;
    }

    public boolean eliminarDoctor(int idTrabajador) {
        return hospital.getDoctores()
                .removeIf(d -> d.getIdTrabajador() == idTrabajador);
    }

    public boolean actualizarDoctor(int idTrabajador, String nombre,
                                    String apP, String apM,
                                    double salario, String especialidad) {
        Doctor d = buscarDoctor(idTrabajador);
        if (d == null) return false;

        d.setNombre(nombre);
        d.setApellidoP(apP);
        d.setApellidoM(apM);
        d.setSalario(salario);
        d.setEspecialidad(especialidad);
        return true;
    }

    public List<Doctor> listarDoctores() {
        return new ArrayList<>(hospital.getDoctores());
    }
}