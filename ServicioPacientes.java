import java.util.ArrayList;

public class ServicioPacientes {

    private Hospital hospital;

    public ServicioPacientes(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registrarPaciente(Paciente paciente) {
        hospital.getPacientes().add(paciente);
    }

    public Paciente buscarPaciente(String dni) {
        for(Paciente p : hospital.getPacientes()) {
            if(p.getDni().equals(dni)) return p;
        }
        return null;
    }

    public void eliminarPaciente(String dni) {
        hospital.getPacientes().removeIf(p -> p.getDni().equals(dni));
    }

    public void modificarDatos(String dni, String nombre, String apellidoP, String apellidoM) {
        Paciente p = buscarPaciente(dni);
        if(p != null) {
            p.setNombre(nombre);
            p.setApellidoP(apellidoP);
            p.setApellidoM(apellidoM);
        }
    }

    public ArrayList<Paciente> listaPacientes() {
        return hospital.getPacientes();
    }

    public Historia historiaPaciente(String dni) {
        Paciente p = buscarPaciente(dni);
        if(p != null){
            return p.getHistoria();
        }

        return null;
    }
}