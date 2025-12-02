  public class Medicamento {

    private String idMed;
    private String nombre;
    private int stock;
    private String descripcion;
    private String tipo;


    public Medicamento(String idMed, String nombre, int stock, String descripcion, String tipo) {
        this.idMed = idMed;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }


    public String getIdMed() { 
        return idMed; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public int getStock() { 
        return stock; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
    public String getTipo() { 
        return tipo; 
    }


    public void setIdMed(String idMed) { 
        this.idMed = idMed; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setStock(int stock) { 
        this.stock = stock; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public void reducirStock(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
        } else {
            System.out.println("No hay suficiente stock para este medicamento.");
        }
    }

    public boolean hayStock() {
        return stock > 0;
    }

    @Override
    public String toString() {
        return idMed + " - " + nombre + " (" + tipo + ") Stock: " + stock;
    }
}
