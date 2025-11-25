import java.util.ArrayList;

public class GestorMedicamentos {

    private ArrayList<Medicamento> lista;

    public Medicamentos() {
        this.lista = new ArrayList<>();
    }

    public void agregarMedicamento(Medicamento m) {
        lista.add(m);
    }

    public Medicamento buscar(String idMed) {
        for (Medicamento m : lista) {
            if (m.getIdMed().equals(idMed)) return m;
        }
        return null;
    }
}
