import java.util.ArrayList;

public class ServicioInventario {

    private Hospital hospital;  
  
    public ServicioInventario(Hospital hospital) {
        this.hospital = hospital;
    }

    public void agregarItem(Inventario item) {
        hospital.getInventario().add(item);
    }

    public Inventario buscarItem(String idItem) {
        for (Inventario i : hospital.getInventario()) {
            if (i.getIdItem().equals(idItem)) {
                return i;
            }
        }
        return null;
    }

    public void actualizarStock(String idItem, int nuevaCantidad) {
        Inventario item = buscarItem(idItem);

        if (item != null && nuevaCantidad >= 0) {
            item.setCantidad(nuevaCantidad);
        }
    }


    public void aumentarStock(String idItem, int cantidad) {
        Inventario item = buscarItem(idItem);
        if (item != null && cantidad > 0) {
            item.setCantidad(item.getCantidad() + cantidad);
        }
    }

    public void reducirStock(String idItem, int cantidad) {
        Inventario item = buscarItem(idItem);
        if (item != null && cantidad > 0 && item.getCantidad() >= cantidad) {
            item.setCantidad(item.getCantidad() - cantidad);
        }
    }

    public void eliminarItem(String idItem) {
        hospital.getInventario().removeIf(i -> i.getIdItem().equals(idItem));
    }

    public ArrayList<Inventario> listaItem() {
        return hospital.getInventario();
    }
}
