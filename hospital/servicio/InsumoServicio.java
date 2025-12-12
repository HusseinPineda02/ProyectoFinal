package hospital.servicio;

import hospital.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class InsumoServicio {

    private Hospital hospital;

    public InsumoServicio(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarInsumo(Insumo i) {
        hospital.getInsumos().add(i);
    }

    public Insumo buscarInsumo(int idInsumo) {
        for (Insumo i : hospital.getInsumos()) {
            if (i.getIdInsumo() == idInsumo) return i;
        }
        return null;
    }

    public boolean eliminarInsumo(int id) {
        return hospital.getInsumos()
                .removeIf(i -> i.getIdInsumo() == id);
    }

    public boolean actualizarInsumo(int id, String nombre, String categoria,
                                    String descripcion, int stock, double precioUnitario) {
        Insumo i = buscarInsumo(id);
        if (i == null) return false;

        i.setNombre(nombre);
        i.setCategoria(categoria);
        i.setDescripcion(descripcion);
        i.setStock(stock);
        i.setPrecioUnitario(precioUnitario);
        return true;
    }

    public List<Insumo> listarInsumos() {
        return new ArrayList<>(hospital.getInsumos());
    }

    public boolean disminuirStock(int id, int cantidad) {
        Insumo i = buscarInsumo(id);
        if (i == null || i.getStock() < cantidad) return false;
        i.setStock(i.getStock() - cantidad);
        return true;
    }

    public boolean aumentarStock(int id, int cantidad) {
        Insumo i = buscarInsumo(id);
        if (i == null) return false;
        i.setStock(i.getStock() + cantidad);
        return true;
    }
}