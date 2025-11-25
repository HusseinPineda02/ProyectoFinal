import java.util.ArrayList;

public abstract class Ambiente {
    protected int id;
    protected int piso;
    protected int numero;
    protected boolean disponible;
    protected ArrayList<Cita> citas = new ArrayList<>();  // ArrayList obligatorio

    public Ambiente(int id, int piso, int numero) {
        this.id = id;
        this.piso = piso;
        this.numero = numero;
        this.disponible = true;
    }
    public void ocupar() {
        this.disponible = false;
        System.out.println("Ambiente " + numero + " → OCUPADO");
    }
    public void liberar() {
        this.disponible = true;
        System.out.println("Ambiente " + numero + " → LIBRE");
    }
    public void agregarCita(Cita cita) {
        citas.add(cita);
        ocupar();
    }

// MEtodo abstracto
    public abstract void mostrarTipo();

    public void mostrarInformacion() {
        System.out.println("ID: " + id + " | Piso: " + piso + " | Número: " + numero);
        System.out.println("Estado: " + (disponible ? "LIBRE" : "OCUPADO"));
        System.out.print("Tipo: ");
        mostrarTipo();
        System.out.println("Citas programadas: " + citas.size());
    }

//Getters
    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }
}
