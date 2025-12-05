package hospital.modelo;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {

    private int idHistoria;
    private Paciente paciente;
    private List<RegistroClinico> registros;

    public HistoriaClinica(int idHistoria, Paciente paciente) {
        this.idHistoria = idHistoria;
        this.paciente = paciente;
        this.registros = new ArrayList<>();
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<RegistroClinico> getRegistros() {
        return registros;
    }

    public void agregarRegistro(RegistroClinico registro) {
        this.registros.add(registro);
    }

    @Override
    public String toString() {
        return "Historia #" + idHistoria + " de " + paciente.getNombreCompleto() +
               " (Registros: " + registros.size() + ")";
    }
}
