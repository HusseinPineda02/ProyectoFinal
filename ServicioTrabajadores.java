import java.util.ArrayList;

public class ServicioTrabajadores {
    private Hospital hospital;

    public ServicioTrabajadores(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarTrabajador(Trabajador t) {
        if(t instanceof Doctor) hospital.getDoctores().add((Doctor)t);
        else if(t instanceof Enfermero) hospital.getEnfermeros().add((Enfermero)t);
        else if(t instanceof Administrativo) hospital.getAdministrativos().add((Administrativo)t);
    }

    public Trabajador buscarTrabajador(String id) {
        for(Doctor d : hospital.getDoctores()){
            if(d.getIdTrabajador().equals(id)){
                return d;
            }
        }
        for(Enfermero e : hospital.getEnfermeros()){
            if(e.getIdTrabajador().equals(id)){
                return e;
            }
        } 
        for(Administrativo a : hospital.getAdministrativos()){
            if(a.getIdTrabajador().equals(id)){
                return a;
            }
        }
        return null;
    }

    public ArrayList<Trabajador> mostrarTrabajadores() {
        ArrayList<Trabajador> lista = new ArrayList<>();
        lista.addAll(hospital.getDoctores());
        lista.addAll(hospital.getEnfermeros());
        lista.addAll(hospital.getAdministrativos());
        return lista;
    }
}