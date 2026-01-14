package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoServicio {

    private Hospital hospital;

    public MedicamentoServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarMedicamento(Medicamento m) {
        hospital.getMedicamentos().add(m);
    }

    public Medicamento buscarMedicamento(int id) {
        for (Medicamento m : hospital.getMedicamentos()) {
            if (m.getIdMedicamento() == id) return m;
        }
        return null;
    }

    public boolean eliminarMedicamento(int id) {
        return hospital.getMedicamentos()
                .removeIf(m -> m.getIdMedicamento() == id);
    }

    public boolean actualizarMedicamento(int id, String nombre, String tipo,
                                         String descripcion, int stock, double precio) {
        Medicamento m = buscarMedicamento(id);
        if (m == null) return false;

        m.setNombre(nombre);
        m.setTipo(tipo);
        m.setDescripcion(descripcion);
        m.setStock(stock);
        m.setPrecioUnitario(precio);
        return true;
    }

    public List<Medicamento> listarMedicamentos() {
        return new ArrayList<>(hospital.getMedicamentos());
    }

    public boolean disminuirStock(int id, int cantidad) {
        Medicamento m = buscarMedicamento(id);
        if (m == null || m.getStock() < cantidad) return false;
        m.setStock(m.getStock() - cantidad);
        return true;
    }

    public boolean aumentarStock(int id, int cantidad) {
        Medicamento m = buscarMedicamento(id);
        if (m == null) return false;
        m.setStock(m.getStock() + cantidad);
        return true;
    }
}
