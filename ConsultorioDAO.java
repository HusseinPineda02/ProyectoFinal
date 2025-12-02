import java.util.ArrayList;

public class ConsultorioDAO {

    private ArrayList<Consultorio> consultorios = new ArrayList<>();

    public void agregar(Consultorio c) {
        if (c == null) {
            System.out.println("No se puede agregar un consultorio nulo.");
            return;
        }
        consultorios.add(c);
        System.out.println("Consultorio " + c.getCodigoConsultorio() + " agregado.");
    }

    public Consultorio buscar(String codigo) {
        for (Consultorio c : consultorios)
            if (c.getCodigoConsultorio().equals(codigo))
                return c;

        System.out.println("Consultorio " + codigo + " no encontrado.");
        return null;
    }

    public void mostrar() {
        if (consultorios.isEmpty()) {
            System.out.println("No hay consultorios registrados.");
            return;
        }

        System.out.println("Lista de Consultorios:");
        for (Consultorio c : consultorios)
            System.out.println(c);
    }

    public int count() { return consultorios.size(); }
}
