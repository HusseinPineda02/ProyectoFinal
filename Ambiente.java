import java.util.ArrayList;

public abstract class Ambiente {

    protected int id;
    protected int piso;
    protected int numero;
    protected boolean disponible;
    protected ArrayList<Cita> citas;

    public Ambiente(int id, int piso, int numero) {
        this.id = id;
        this.piso = piso;
        this.numero = numero;
        this.disponible = true;
        this.citas = new ArrayList<>();
    }
    
    public int getId() { 
        return id;
    }
    public int getPiso() { 
        return piso; 
    }
    public int getNumero() { 
        return numero; 
    }
    public boolean isDisponible() { 
        return disponible; 
    }
    public ArrayList<Cita> getCitas() { 
        return citas; 
    }

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
        if (cita == null) {
            System.out.println("ERROR: Cita nula no permitida.");
            return;
        }
        citas.add(cita);
        ocupar();
        System.out.println("Cita #" + cita.getIdCita() + " agregada al ambiente " + numero);
    }

    public boolean eliminarCita(int idCita) {
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).getIdCita() == idCita) {
                citas.remove(i);
            if (citas.isEmpty()) liberar();
        return true;
            }
        }
        return false;
    }

    public abstract void mostrarTipo();

    public void mostrarInformacion() {
        System.out.println("══════════════════════════════════════");
        System.out.println("Ambiente ID: " + getId() + " | Piso: " + getPiso() + " | Nº: " + getNumero());
        System.out.println("Estado: " + (isDisponible() ? "LIBRE" : "OCUPADO"));
        System.out.print("Tipo: ");
        mostrarTipo();
        System.out.println(" | Total citas: " + getCitas().size());
        System.out.println("══════════════════════════════════════");
    }

