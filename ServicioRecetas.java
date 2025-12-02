import java.util.ArrayList;

public class ServicioRecetas {

    private Hospital hospital;  


    public ServicioRecetas(Hospital hospital) {
        this.hospital = hospital;
    }

    public void generarReceta(Receta receta) {
        hospital.getRecetas().add(receta);
    }

    public Receta buscarReceta(String idReceta) {
        for (Receta r : hospital.getRecetas()) {
            if (r.getIdReceta().equals(idReceta)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Receta> listaRecetasPaciente(String idPaciente) {
        ArrayList<Receta> resultado = new ArrayList<>();

        for (Receta r : hospital.getRecetas()) {
            if (r.getPaciente().getDni().equals(idPaciente)) {
                resultado.add(r);
            }
        }

        return resultado;
    }

    public ArrayList<Receta> listaRecetasDoctor(String idDoctor) {
        ArrayList<Receta> resultado = new ArrayList<>();

        for (Receta r : hospital.getRecetas()) {
            if (r.getDoctor().getIdTrabajador().equals(idDoctor)) {
                resultado.add(r);
            }
        }

        return resultado;
    }
}
