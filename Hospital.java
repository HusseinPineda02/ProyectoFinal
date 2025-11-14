public class Hospital {
    private String nombre;
    private String distrito;
    private String direccion;

    public Hospital(String nombre, String distrito, String direccion) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public String getDistrito() { return distrito; }
    public String getDireccion() { return direccion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public void nuevoTrabajador() {}
    public void eliminarTrabajador() {}
    public void consultaTrabajadores() {}
    public void consultaInventario() {}
    public void consultaMedicamentos() {}
    public void consultasAmbientes() {}

    public static void main(String[] args) {}
}
