import java.util.ArrayList;

public class ServicioMedicamento {

    private Hospital hospital; 
 
    public ServicioMedicamento(Hospital hospital) {
        this.hospital = hospital;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        hospital.getMedicamentos().add(medicamento);
    }

    public void actualizarStock(String idMed, int cantidad) {
        Medicamento med = buscarMed(idMed);

        if (med != null && cantidad >= 0) {
            med.setStock(cantidad);
        }
    }

    public void aumentarStock(String idMed, int cantidad) {
        Medicamento med = buscarMed(idMed);

        if (med != null && cantidad > 0) {
            med.setStock(med.getStock() + cantidad);
        }
    }

    public void reducirStock(String idMed, int cantidad) {
        Medicamento med = buscarMed(idMed);

        if (med != null && cantidad > 0 && med.getStock() >= cantidad) {
            med.setStock(med.getStock() - cantidad);
        }
    }

    public Medicamento buscarMed(String idMed) {
        for (Medicamento m : hospital.getMedicamentos()) {
            if (m.getIdMed().equals(idMed)) {
                return m;
            }
        }
        return null;
    }
    public ArrayList<Medicamento> listaMed() {
        return hospital.getMedicamentos();
    }
    
    public boolean medDisponible(String idMed) {
        Medicamento med = buscarMed(idMed);
        return med != null && med.hayStock();
    }

    public void eliminarMedicamento(String idMed) {
        hospital.getMedicamentos().removeIf(m -> m.getIdMed().equals(idMed));
    }
}
