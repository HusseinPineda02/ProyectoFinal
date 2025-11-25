public class Farmacia {

    private Medicamentos catalogo;
    private Inventario inventario;

    public Farmacia() {
        this.catalogo = new Medicamentos();
        this.inventory = new Inventario();
    }

    public void registrarMedicamento(Medicamento m, int cantidad) {
        catalogo.agregarMedicamento(m);
        inventario.agregarStock(m.getIdMed(), cantidad);
    }

    public boolean vender(String idMed, int cantidad) {
        return inventario.reducirStock(idMed, cantidad);
    }

    public int consultarStock(String idMed) {
        return inventario.consultarStock(idMed);
    }
}
