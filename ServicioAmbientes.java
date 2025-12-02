import java.util.ArrayList;

public class ServicioAmbientes {
private Hospital hospital;

    public ServicioAmbientes(Hospital hospital) {
        this.hospital = hospital;
    }
    public void agregarAmbiente(Ambiente ambiente) {
        if (ambiente instanceof Consultorio) {
            hospital.getConsultorios().add((Consultorio) ambiente);
            System.out.println("Consultorio " + ambiente.getNumero() + " agregado.");
        } else if (ambiente instanceof Sala) {
            hospital.getSalas().add((Sala) ambiente);
            System.out.println("Sala " + ambiente.getNumero() + " agregada.");
        }
    }
    public Ambiente buscarAmbiente(String codigo) {
        for (Consultorio c : hospital.getConsultorios()) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) return c;
        }
        for (Sala s : hospital.getSalas()) {
            if (s.getCodigo().equalsIgnoreCase(codigo)) return s;
        }
        return null;
    }
    public ArrayList<Ambiente> listaAmbientes() {
        ArrayList<Ambiente> todos = new ArrayList<>();
        todos.addAll(hospital.getConsultorios());
        todos.addAll(hospital.getSalas());
        return todos;
    }
    public ArrayList<Consultorio> listaConsultorios() {
        return hospital.getConsultorios();
    }
    public ArrayList<Sala> listaSalas() {
        return hospital.getSalas();
    }
    public void mostrarTodos() {
        System.out.println("=== AMBIENTES DISPONIBLES ===");
        for (Ambiente a : listaAmbientes()) {
            a.mostrarInformacion();
        }
    }
}
