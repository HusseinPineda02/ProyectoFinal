package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoServicio {

    private Hospital hospital;

    public AdministrativoServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarAdministrativo(Administrativo a) {
        hospital.getAdministrativos().add(a);
    }

    public Administrativo buscarAdministrativo(int idTrabajador) {
        for (Administrativo a : hospital.getAdministrativos()) {
            if (a.getIdTrabajador() == idTrabajador) return a;
        }
        return null;
    }

    public boolean eliminarAdministrativo(int idTrabajador) {
        return hospital.getAdministrativos()
                .removeIf(a -> a.getIdTrabajador() == idTrabajador);
    }

    public boolean actualizarAdministrativo(int idTrabajador,
                                            String nombre, String apP, String apM,
                                            double salario, String area) {
        Administrativo a = buscarAdministrativo(idTrabajador);
        if (a == null) return false;

        a.setNombre(nombre);
        a.setApellidoP(apP);
        a.setApellidoM(apM);
        a.setSalario(salario);
        a.setArea(area);

        return true;
    }

    public List<Administrativo> listarAdministrativos() {
        return new ArrayList<>(hospital.getAdministrativos());
    }
}