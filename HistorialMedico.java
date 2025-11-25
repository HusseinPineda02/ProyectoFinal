import java.util.ArrayList;
import java.util.Date;

public class HistorialMedico {
    private int dniPaciente;
    private ArrayList<String> registros = new ArrayList<>(); 

    public HistorialMedico(int dniPaciente) {
        this.dniPaciente = dniPaciente;
        agregarRegistro("Historial creado el " + new Date());
    }

    public void agregarRegistro(String descripcion) {
        registros.add(new Date() + " → " + descripcion);
    }

    public void mostrar() {
        System.out.println("--- HISTORIAL MÉDICO - DNI: " + dniPaciente + " ---");
        for (String r : registros) {
            System.out.println(r);
        }
    }
}
