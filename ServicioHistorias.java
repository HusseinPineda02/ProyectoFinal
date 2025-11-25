import java.util.ArrayList;

public class ServicioHistorias {
    
    private Hospital hospital;

    public ServicioHistorias(Hospital hospital) {
        this.hospital = hospital;
    }

    public Historia crearHistoria(Paciente paciente) {
        Historia h = new Historia("HIST-" + paciente.getDni(), paciente);
        paciente.setHistoria(h);
        return h;
    }

    public void agregarRegistro(Paciente paciente, Registro registro) {
        if(paciente.getHistoria() != null) {
            paciente.getHistoria().getRegistros().add(registro);
        }
    }

    public Historia obtenerHistoria(String dniPaciente) {
        Paciente p = hospital.getServicioPacientes().buscarPaciente(dniPaciente);
        if(p != null) return p.getHistoria();
        return null;
    }

    public ArrayList<Registro> listaRegistros(String dniPaciente) {
        Historia h = obtenerHistoria(dniPaciente);
        if(h != null){
            return h.getRegistros();
        }
        System.out.println("Registro vacio");
        return new ArrayList<>();
    }
}