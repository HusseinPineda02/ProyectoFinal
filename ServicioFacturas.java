import java.util.ArrayList;

public class ServicioFacturas {

    private Hospital hospital;   


    public ServicioFacturas(Hospital hospital) {
        this.hospital = hospital;
    }

    public void generarFactura(Factura factura) {
        hospital.getFacturas().add(factura);
    }

    public Factura buscarFactura(String idFactura) {
        for (Factura f : hospital.getFacturas()) {
            if (f.getIdFactura().equals(idFactura)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Factura> listaFacturasPaciente(String idPaciente) {
        ArrayList<Factura> resultado = new ArrayList<>();

        for (Factura f : hospital.getFacturas()) {
            if (f.getPaciente().getDni().equals(idPaciente)) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    public double calcularTotal(Factura factura) {
        double total = 0;

        for (Item i : factura.getItems()) {
            total += i.getPrecio();
        }

        return total;
    }
}

