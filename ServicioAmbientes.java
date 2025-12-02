import java.util.ArrayList;

public class ServicioAmbientes {

    private ArrayList<Sala> salas;
    private ArrayList<Consultorio> consultorios;

    public ServicioAmbientes() {
        salas = new ArrayList<>();
        consultorios = new ArrayList<>();
    }

    public void agregarSala(Sala s) { salas.add(s); }
    public void agregarConsultorio(Consultorio c) { consultorios.add(c); }

    public ArrayList<Sala> listarSalas() { return salas; }
    public ArrayList<Consultorio> listarConsultorios() { return consultorios; }

}
