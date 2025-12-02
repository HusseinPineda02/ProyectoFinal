public class Inventario {

    private String idItem;
    private String nombre;
    private String tipo;
    private int cantidad;
    private String descripcion;

    private Ambiente ambiente;

    public Inventario(String idItem, String nombre, String tipo, int cantidad,
                      String descripcion, Ambiente ambiente) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.ambiente = ambiente;
    }


    public String getIdItem() {
        return idItem;
    }
    public String getNombre() {
        return nombre;
    }
     public String getTipo() {
        return tipo;
    } 
    public int getCantidad() {
        return cantidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Ambiente getAmbiente() {
        return ambiente;
    }
    
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        }
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }


    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    public boolean reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= this.cantidad) {
            this.cantidad -= cantidad;
            return true;
        }
        return false;
    }

    public boolean hayStock() {
        return cantidad > 0;
    }

    public void moverA(Ambiente nuevoAmbiente) {
        this.ambiente = nuevoAmbiente;
    }

    public void mostrarItem() {
        System.out.println("==== ITEM INVENTARIO ====");
        System.out.println("ID: " + idItem);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Ubicación: " + ambiente);
        System.out.println("==========================");
    }

    @Override
    public String toString() {
        return idItem + " - " + nombre + " | Tipo: " + tipo +
                " | Cantidad: " + cantidad +
                " | Ambiente: " + (ambiente != null ? ambiente.getIdAmbiente() : "Sin asignar");
    }
}
