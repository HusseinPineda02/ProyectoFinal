package hospital.modelo;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private String tipo;        
    private String descripcion;
    private int stock;
    private double precioUnitario;

    public Medicamento(int idMedicamento, String nombre, String tipo,
                       String descripcion, int stock, double precioUnitario) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "[" + idMedicamento + "] " + nombre + " (" + tipo + ") - Stock: " +
               stock + " - S/ " + precioUnitario;
    }
}
