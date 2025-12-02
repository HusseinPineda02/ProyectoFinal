import java.util.ArrayList;

public class Ambiente {

    private int id;
    private int piso;
    private int numero;
    private boolean disponible;
    private ArrayList<Cita> citas;

    public Ambiente(int id, int piso, int numero) {
        this.id = id;
        this.piso = piso;
        this.numero = numero;
        this.disponible = true;
        this.citas = new ArrayList<>();
    }

    public int getId() { return id; }
    public int getPiso() { return piso; }
    public int getNumero() { return numero; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
        if (cita == null) return;
        citas.add(cita);
        ocupar();
    }

    public void mostrarInformacion() {
        System.out.println("Ambiente ID: " + id);
        System.out.println("Piso: " + piso);
        System.out.println("Número: " + numero);
        System.out.println("Estado: " + (disponible ? "LIBRE" : "OCUPADO"));
        System.out.println("Total de citas: " + citas.size());
    }
}
