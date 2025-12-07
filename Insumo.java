package hospital.modelo;

public class Insumo {

    private int idInsumo;
    private String nombre;
    private String categoria;   // Jeringa, Gasa, Guantes, etc.
    private String descripcion;
    private int stock;
    private double precioUnitario;

    public Insumo(int idInsumo, String nombre, String categoria,
                  String descripcion, int stock, double precioUnitario) {
        this.idInsumo = idInsumo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        return "[" + idInsumo + "] " + nombre + " (" + categoria + ") - Stock: " +
               stock + " - S/ " + precioUnitario;
    }
}
