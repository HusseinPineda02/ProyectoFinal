import java.util.ArrayList;

public class Historia {
    private String idHistoria;
    private Paciente paciente;
    private ArrayList<Registro> registros;
    private ArrayList<Receta> recetas;

    public Historia(String idHistoria, Paciente paciente) {
        this.idHistoria = idHistoria;
        this.paciente = paciente;
        this.registros = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public String getIdHistoria() {
        return idHistoria;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public ArrayList<Registro> getRegistros() {
        return registros;
    }
    public ArrayList<Receta> getRecetas() {
        return recetas;
    }
}