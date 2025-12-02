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
        System.out.println("--- HISTORIAL MÉDICO - DNI: " + dniPaciente + "---");
        for (String r : registros) {
            System.out.println(r);
        }
    }

    public int getTotalRegistros() {
        return registros.size();
    }

    public String getUltimoRegistro() {
        if (registros.isEmpty()) {
            return "Sin registros";
        }
        return registros.get(registros.size() - 1);
    }

    public boolean tieneDiagnostico(String palabra) {
        for (String r : registros) {
            if (r.toLowerCase().contains(palabra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void mostrarConNumeros() {
        System.out.println("HISTORIAL COMPLETO - DNI: " + dniPaciente);
        System.out.println("===========================================");
        for (int i = 0; i < registros.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, registros.get(i));
        }
        System.out.println("===========================================");
        System.out.println("Total de registros: " + registros.size() + "\n");
    }
}
