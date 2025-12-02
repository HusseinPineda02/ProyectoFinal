import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {

    private String idFactura;
    private Paciente paciente;
    private String tipoPago;
    private double monto;
    private LocalDate fecha;

    private ArrayList<Item> items; 

    public Factura(String idFactura, Paciente paciente, String tipoPago, LocalDate fecha) {
        this.idFactura = idFactura;
        this.paciente = paciente;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.items = new ArrayList<>();
        this.monto = 0;
    }


    public String getIdFactura() {
        return idFactura;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public double getMonto() {
        return monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void agregarItem(Item item) {
        items.add(item);
        calcularTotal();
    }

    public void eliminarItem(String id) {
        items.removeIf(i -> i.getIdItem().equals(id));
        calcularTotal();
    }

    public void calcularTotal() {
        this.monto = 0;
        for (Item i : items) {
            this.monto += i.getPrecio();
        }
    }


    public void mostrarFactura() {
        System.out.println("=========== FACTURA ===========");
        System.out.println("ID Factura: " + idFactura);
        System.out.println("Fecha: " + fecha);
        System.out.println("Paciente: " + paciente.getNombreCompleto());
        System.out.println("Tipo de Pago: " + tipoPago);
        System.out.println("-------------- ITEMS --------------");
        for (Item i : items) {
            System.out.println(i);
        }
        System.out.println("----------------------------------");
        System.out.println("TOTAL A PAGAR: S/ " + monto);
        System.out.println("==================================");
    }

    @Override
    public String toString() {
        return "Factura " + idFactura + " - " + fecha + " - Total: S/" + monto;
    }
}
