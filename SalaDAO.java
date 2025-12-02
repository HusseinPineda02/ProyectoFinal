import java.util.ArrayList;

public class SalaDAO {

    private ArrayList<Sala> salas = new ArrayList<>();

    public void agregar(Sala s) {
        if (s == null) {
            System.out.println("No se puede agregar una sala nula.");
            return;
        }
        salas.add(s);
        System.out.println("Sala agregada.");
    }

    public void mostrar() {
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
            return;
        }

        System.out.println("Lista de Salas:");
        for (Sala s : salas) {
            System.out.println(
                "Sala: aforo=" + s.getAforo() +
                ", camillas=" + s.getCamillas() +
                ", tipo=" + s.getTipoSala()
            );
        }
    }

    public int count() {
        return salas.size();
    }
}
