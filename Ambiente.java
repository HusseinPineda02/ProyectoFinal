import java.util.ArrayList;

public abstract class Ambiente {
    protected int id;
    protected int piso;
    protected int numero;
    protected boolean disponible;
    protected ArrayList<Cita> citas = new ArrayList<>(); 

    public Ambiente(int id, int piso, int numero) {
        this.id = id;
        this.piso = piso;
        this.numero = numero;
        this.disponible = true;
    }
    public void ocupar() {
        this.disponible = false;
        System.out.println("Ambiente " + numero + " ocupado.");
    }
    public void liberar() {
        this.disponible = true;
        System.out.println("Ambiente " + numero + " liberado.");
    }
    public void agregarCita(Cita cita) {
        citas.add(cita);
        ocupar();
    }
    
    public abstract void mostrarTipo();  
    
    public void mostrarInformacion() {
        System.out.println("Ambiente ID: " + id + " | Piso: " + piso + " | N°: " + numero);
        System.out.print("Estado: " + (disponible ? "LIBRE" : "OCUPADO"));
        System.out.print(" | Tipo: ");
        mostrarTipo();
        System.out.println(" | Citas: " + citas.size());
    }

    //GEETTERS
    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }
}
