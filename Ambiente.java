public class Ambiente {
    private int id;
    private boolean disponible;
    private int piso;
    private int numero;
//constructor
    public Ambiente(int id, boolean disponible, int piso, int numero) {
        this.id = id;
        this.disponible = disponible;
        this.piso = piso;
        this.numero = numero;
    }
  //metodos
    public void ocupar() {
        disponible = false;
        System.out.println("El ambiente ahora está ocupado.");
    }

    public void mostrarInformacion() {
        System.out.println("Ambiente ID: " + id + " | Piso: " + piso + " | N°: " + numero);
    }
}
