import java.util.ArrayList;

public class PacienteDAO {

    private ArrayList<Paciente> pacientes = new ArrayList<>();

    public void agregar(Paciente p) {
        if (p == null) {
            System.out.println("No se puede agregar un paciente nulo.");
            return;
        }

        for (Paciente temp : pacientes) {
            if (temp.getDni() == p.getDni()) {
                System.out.println("Ya existe un paciente con DNI " + p.getDni());
                return;
            }
        }

        pacientes.add(p);
        System.out.println("Paciente " + p.getNombre() + " registrado.");
    }

    public void eliminar(int dni) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getDni() == dni) {
                Paciente eliminado = pacientes.remove(i);
                if (eliminado.getConsultorioActual() != null)
                    eliminado.liberarDeConsultorio();

                System.out.println("Paciente " + eliminado.getNombre() + " eliminado.");
                return;
            }
        }
        System.out.println("No se encontró paciente con DNI " + dni);
    }

    public Paciente buscar(int dni) {
        for (Paciente p : pacientes)
            if (p.getDni() == dni)
                return p;

        System.out.println("Paciente con DNI " + dni + " no encontrado.");
        return null;
    }

    public void mostrar() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Lista de Pacientes:");
        for (Paciente p : pacientes)
            System.out.println(p.mostrarResumen());
    }

    public int count() {
        return pacientes.size();
    }
}
