import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {

    private String idFactura;
    private LocalDate fecha;
    private String paciente;
    private String medico;
    private ArrayList<Medicamento> items;
    private double total;

    public Factura(String idFactura, String paciente, String medico) {
        this.idFactura = idFactura;
        this.fecha = LocalDate.now();
        this.paciente = paciente;
        this.medico = medico;
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public void agregarItem(Medicamento m, double precio) {
        items.add(m);
        total += precio;
    }

    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Factura{" + idFactura + ", total=" + total + "}";
    }
}

