package hospital.modelo;

public class Habitacion {

    private int idHabitacion;
    private TipoHabitacion tipo;
    private int piso;
    private String numero;
    private boolean disponible;

    public Habitacion(int idHabitacion, TipoHabitacion tipo, int piso,
                      String numero, boolean disponible) {
        this.idHabitacion = idHabitacion;
        this.tipo = tipo;
        this.piso = piso;
        this.numero = numero;
        this.disponible = disponible;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "ID " + idHabitacion + " - Piso " + piso + " N° " + numero + " (" + tipo + ")" +
            (disponible ? "" : " [NO DISP.]");
    }
}