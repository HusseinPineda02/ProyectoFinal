import java.util.ArrayList;

public class ServicioPacientes {
    private ArrayList<Paciente> pacientes;

    public ServicioPacientes() {
        this.pacientes = new ArrayList<>();
    }

    public void registrarPaciente(Paciente p) {
        pacientes.add(p);
    }
    public Paciente buscarPaciente(String dni) {
        for(Paciente p : pacientes){
            if(p.getDni().equals(dni)){
                return p;
            }
        }
        return null;
    }
    public void eliminarPaciente(String dni) {
        for(Paciente p:pacientes){
            if(p.getDni().equals(dni)){
                pacientes.remove(p);
            }
        }
    }
    public ArrayList<Paciente> listaPacientes() {
        return pacientes;
    }
}