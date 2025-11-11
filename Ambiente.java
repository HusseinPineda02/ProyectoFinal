public class Ambiente {
    private int id;
    private boolean disponible;
    private int piso;
    private int numero;

    // Constructor inicial
    public Ambiente(int id, boolean disponible, int piso, int numero) {
        this.id = id;
        this.disponible = disponible;
        this.piso = piso;
        this.numero = numero;
    }

    // Métodos principales (básicos)
    public void ocupar() {
        disponible = false;
        System.out.println("El ambiente ahora está ocupado.");
    }

    public void mostrarInformacion() {
        System.out.println("Ambiente ID: " + id + " | Piso: " + piso + " | N°: " + numero);
    }
}
