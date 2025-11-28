public class Medicamento {
    private String idMed;
    private String nombre;
    private String lote;
    private String descripcion;
    private String tipo;

    public Medicamento(String idMed, String nombre, String lote, String descripcion, String tipo) {
        this.idMed = idMed;
        this.nombre = nombre;
        this.lote = lote;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getIdMed() { return idMed; }
    public String getNombre() { return nombre; }
    public String getLote() { return lote; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Medicamento{" + nombre + ", lote=" + lote + "}";
    }
}
